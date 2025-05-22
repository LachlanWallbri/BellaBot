package com.aliyun.alink.linksdk.tmp.service;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceRemoveListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.SubDeviceInfo;
import com.aliyun.alink.linksdk.channel.mobile.api.MobileChannel;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.IProvision;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DefaultProvisionConfig;
import com.aliyun.alink.linksdk.tmp.data.SubDevInfo;
import com.aliyun.alink.linksdk.tmp.data.auth.AuthenRegisterRspPayload;
import com.aliyun.alink.linksdk.tmp.data.service.DevTripleInfo;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.Secure;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.iot.breeze.api.IBreeze;
import com.aliyun.iot.breeze.api.IBreezeDevice;
import com.aliyun.iot.breeze.biz.BreezeHelper;
import com.pudutech.mirsdk.SolicitService;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DevService {
    public static final String BUNDLE_KEY_DEVICENAME = "bundlekey_devicename";
    public static final String BUNDLE_KEY_PRODUCTKEY = "bundlekey_productkey";
    public static final String BUNDLE_KEY_SUBCHANNEL = "bundlekey_subchannel";
    private static final String TAG = "[Tmp]DevService";
    private static Map<String, String> mDevSecList = new ConcurrentHashMap();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface ServiceListener {
        void onComplete(boolean z, Object obj);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class OnlyOnceServiceListener implements ServiceListener {
        private ServiceListener mServiceListener;

        public OnlyOnceServiceListener(ServiceListener serviceListener) {
            this.mServiceListener = serviceListener;
        }

        @Override // com.aliyun.alink.linksdk.tmp.service.DevService.ServiceListener
        public void onComplete(boolean z, Object obj) {
            ServiceListener serviceListener = this.mServiceListener;
            if (serviceListener == null) {
                ALog.m484w(DevService.TAG, "onComplete called twice mServiceListener empty");
            } else {
                serviceListener.onComplete(z, obj);
                this.mServiceListener = null;
            }
        }
    }

    public static void subDeviceAuthenLogin(JSONObject jSONObject, ServiceListener serviceListener) {
        ALog.m479d(TAG, "subDeviceAuthenLogin params:" + jSONObject + " listener:" + serviceListener);
        if (serviceListener == null) {
            ALog.m480e(TAG, "subDeviceAuthenLogin listener null");
            return;
        }
        if (jSONObject == null) {
            ALog.m480e(TAG, "subDeviceAuthenLogin params null");
            serviceListener.onComplete(false, null);
            return;
        }
        try {
            SubDevInfo subDevInfo = (SubDevInfo) com.alibaba.fastjson.JSONObject.parseObject(String.valueOf(jSONObject), SubDevInfo.class);
            if (subDevInfo == null) {
                ALog.m480e(TAG, "subDeviceAuthenLogin subDeviceInfo null");
                serviceListener.onComplete(false, null);
            } else {
                subDeviceAuthenLogin(subDevInfo.productKey, subDevInfo.deviceName, true, serviceListener);
            }
        } catch (Exception e) {
            ALog.m484w(TAG, "subDeviceAuthenLogin error:" + e.toString());
        }
    }

    public static void subDeviceAuthenLogin(String str, String str2, ServiceListener serviceListener) {
        subDeviceAuthenLogin(str, str2, true, serviceListener);
    }

    public static void subDeviceAuthenLogin(String str, String str2, boolean z, ServiceListener serviceListener) {
        ALog.m479d(TAG, "subDeviceAuthenLogin productKey:" + str + " deviceName:" + str2 + " listener:" + serviceListener);
        SubDeviceInfo subDeviceInfo = new SubDeviceInfo();
        subDeviceInfo.productKey = str;
        subDeviceInfo.deviceName = str2;
        if (serviceListener == null) {
            ALog.m480e(TAG, "subDeviceAuthenLogin listener empty");
            return;
        }
        DeviceBasicData deviceBasicData = DeviceManager.getInstance().getDeviceBasicData(TextHelper.combineStr(str, str2));
        if (deviceBasicData != null && "2".equalsIgnoreCase(deviceBasicData.getModelType())) {
            breezeSubDevLogin(str, str2, serviceListener);
            return;
        }
        String clientId = MobileChannel.getInstance().getClientId();
        if (TextUtils.isEmpty(clientId)) {
            ALog.m484w(TAG, "请检查长连接通道是否初始化成功");
        } else {
            CloudUtils.registerPersistentConnect(null, null, null, null);
            CloudUtils.subPushDeviceInfo(str, str2, new C11261(str, str2, serviceListener, clientId, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.tmp.service.DevService$1 */
    /* loaded from: classes.dex */
    public static class C11261 implements IConnectRrpcListener {
        final /* synthetic */ String val$clientId;
        final /* synthetic */ String val$deviceName;
        final /* synthetic */ boolean val$isDelayCallback;
        final /* synthetic */ ServiceListener val$listener;
        final /* synthetic */ String val$productKey;

        C11261(String str, String str2, ServiceListener serviceListener, String str3, boolean z) {
            this.val$productKey = str;
            this.val$deviceName = str2;
            this.val$listener = serviceListener;
            this.val$clientId = str3;
            this.val$isDelayCallback = z;
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
        public void onSubscribeSuccess(ARequest aRequest) {
            ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onSubscribe Success");
            DevService.mDevSecList.remove(TextHelper.combineStr(this.val$productKey, this.val$deviceName));
            CloudUtils.subDevAuthenRegister(this.val$productKey, this.val$deviceName, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.1.1
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest2, AResponse aResponse) {
                    String str;
                    ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onResponse aResponse:" + aResponse);
                    if (aResponse == null || aResponse.data == null) {
                        ALog.m480e(DevService.TAG, "subDeviceAuthenLogin onResponse aResponse: empty");
                        if (C11261.this.val$listener != null) {
                            C11261.this.val$listener.onComplete(false, null);
                            return;
                        }
                        return;
                    }
                    if (aResponse.data instanceof byte[]) {
                        try {
                            str = new String((byte[]) aResponse.data, "UTF-8");
                        } catch (Exception e) {
                            ALog.m480e(DevService.TAG, e.toString());
                            str = null;
                        }
                    } else {
                        str = aResponse.data.toString();
                    }
                    AuthenRegisterRspPayload authenRegisterRspPayload = (AuthenRegisterRspPayload) com.alibaba.fastjson.JSONObject.parseObject(str, AuthenRegisterRspPayload.class);
                    if (authenRegisterRspPayload.code == 6619) {
                        ALog.m479d(DevService.TAG, "subDeviceAuthenLogin delay login");
                        TmpSdk.mHandler.postDelayed(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                String str2 = (String) DevService.mDevSecList.get(TextHelper.combineStr(C11261.this.val$productKey, C11261.this.val$deviceName));
                                ALog.m479d(DevService.TAG, "subDeviceAuthenLogin delay login productKey: " + C11261.this.val$productKey + " deviceName:" + C11261.this.val$deviceName + " mLastDevSec:" + str2);
                                if (TextUtils.isEmpty(str2)) {
                                    if (C11261.this.val$listener != null) {
                                        C11261.this.val$listener.onComplete(false, null);
                                    }
                                } else {
                                    String signValue = Secure.getSignValue(C11261.this.val$clientId, C11261.this.val$productKey, C11261.this.val$deviceName, str2);
                                    HashMap hashMap = new HashMap();
                                    hashMap.put(TmpConstant.KEY_SIGN_METHOD, TmpConstant.VALUE_SHA256);
                                    hashMap.put(TmpConstant.KEY_SIGN_VALUE, signValue);
                                    DevService.logIn(C11261.this.val$productKey, C11261.this.val$deviceName, hashMap, C11261.this.val$isDelayCallback, C11261.this.val$listener);
                                }
                            }
                        }, 12000L);
                        return;
                    }
                    if (authenRegisterRspPayload.data == null || authenRegisterRspPayload.data.isEmpty() || TextUtils.isEmpty(authenRegisterRspPayload.data.get(0).productKey) || TextUtils.isEmpty(authenRegisterRspPayload.data.get(0).deviceName) || TextUtils.isEmpty(authenRegisterRspPayload.data.get(0).deviceSecret)) {
                        ALog.m480e(DevService.TAG, "subDeviceAuthenLogin authenRegisterRspPayload  empty");
                        if (C11261.this.val$listener != null) {
                            C11261.this.val$listener.onComplete(false, null);
                            return;
                        }
                        return;
                    }
                    String signValue = Secure.getSignValue(C11261.this.val$clientId, authenRegisterRspPayload.data.get(0).productKey, authenRegisterRspPayload.data.get(0).deviceName, authenRegisterRspPayload.data.get(0).deviceSecret);
                    HashMap hashMap = new HashMap();
                    hashMap.put(TmpConstant.KEY_SIGN_METHOD, TmpConstant.VALUE_SHA256);
                    hashMap.put(TmpConstant.KEY_SIGN_VALUE, signValue);
                    DevService.logIn(C11261.this.val$productKey, C11261.this.val$deviceName, hashMap, C11261.this.val$isDelayCallback, C11261.this.val$listener);
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest2, AError aError) {
                    ALog.m480e(DevService.TAG, "subDeviceAuthenLogin onFailure aError:" + aError);
                    if (C11261.this.val$listener != null) {
                        C11261.this.val$listener.onComplete(false, null);
                    }
                }
            });
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
        public void onSubscribeFailed(ARequest aRequest, AError aError) {
            ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onSubscribeFailed");
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
        public void onReceived(ARequest aRequest, IConnectRrpcHandle iConnectRrpcHandle) {
            ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onReceived");
            if (aRequest == null || !(aRequest instanceof MqttRrpcRequest)) {
                ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onReceived aRequest null");
                return;
            }
            MqttRrpcRequest mqttRrpcRequest = (MqttRrpcRequest) aRequest;
            String str = null;
            if (mqttRrpcRequest.payloadObj instanceof byte[]) {
                try {
                    str = new String((byte[]) mqttRrpcRequest.payloadObj, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                str = String.valueOf(mqttRrpcRequest.payloadObj);
            }
            com.alibaba.fastjson.JSONObject parseObject = JSON.parseObject(str);
            if (parseObject == null) {
                ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onReceived jsonObjectPayload null");
                return;
            }
            com.alibaba.fastjson.JSONObject jSONObject = parseObject.getJSONObject("params");
            if (jSONObject == null) {
                ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onReceived jsonObjectParams null");
                return;
            }
            DevTripleInfo devTripleInfo = (DevTripleInfo) JSON.parseObject(jSONObject.toString(), DevTripleInfo.class);
            if (devTripleInfo != null && !TextUtils.isEmpty(devTripleInfo.deviceSecret)) {
                DevService.mDevSecList.put(devTripleInfo.getId(), devTripleInfo.deviceSecret);
            } else {
                ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onReceived devSec null");
            }
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
        public void onResponseSuccess(ARequest aRequest) {
            ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onResponseSuccess");
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
        public void onResponseFailed(ARequest aRequest, AError aError) {
            ALog.m479d(DevService.TAG, "subDeviceAuthenLogin onResponseFailed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logIn(String str, String str2, Map<String, Object> map, boolean z, ServiceListener serviceListener) {
        SubDeviceInfo subDeviceInfo = new SubDeviceInfo();
        subDeviceInfo.productKey = str;
        subDeviceInfo.deviceName = str2;
        String str3 = map != null ? (String) map.get(TmpConstant.KEY_SIGN_METHOD) : null;
        String str4 = map != null ? (String) map.get(TmpConstant.KEY_SIGN_VALUE) : null;
        ALog.m479d(TAG, "logIn productKey:" + str + " deviceName:" + str2 + " params:" + map + " listener:" + serviceListener + " signMethod:" + str3 + " signValue:" + str4);
        try {
            GatewayChannel.getInstance().addSubDevice(subDeviceInfo, new C11272(str3, str4, serviceListener, z, str, str2));
        } catch (Exception e) {
            ALog.m480e(TAG, "logIn Exception:" + e.toString());
        } catch (Throwable th) {
            ALog.m480e(TAG, "logIn Throwable:" + th.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.tmp.service.DevService$2 */
    /* loaded from: classes.dex */
    public static class C11272 implements ISubDeviceConnectListener {
        final /* synthetic */ String val$deviceName;
        final /* synthetic */ boolean val$isDelayCallback;
        final /* synthetic */ ServiceListener val$listener;
        final /* synthetic */ String val$productKey;
        final /* synthetic */ String val$signMethod;
        final /* synthetic */ String val$signValue;

        @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener
        public Map<String, Object> getSignExtraData() {
            return null;
        }

        @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener
        public void onDataPush(String str, AMessage aMessage) {
        }

        C11272(String str, String str2, ServiceListener serviceListener, boolean z, String str3, String str4) {
            this.val$signMethod = str;
            this.val$signValue = str2;
            this.val$listener = serviceListener;
            this.val$isDelayCallback = z;
            this.val$productKey = str3;
            this.val$deviceName = str4;
        }

        @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener
        public String getSignMethod() {
            return this.val$signMethod;
        }

        @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener
        public String getSignValue() {
            return this.val$signValue;
        }

        @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener
        public String getClientId() {
            String clientId = MobileChannel.getInstance().getClientId();
            ALog.m479d(DevService.TAG, "getClientId:" + clientId);
            return clientId;
        }

        @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener
        public void onConnectResult(boolean z, final ISubDeviceChannel iSubDeviceChannel, AError aError) {
            ALog.m479d(DevService.TAG, "onConnectResult b :" + z + " iSubDeviceChannel:" + iSubDeviceChannel + " aeeor:" + aError);
            if (iSubDeviceChannel == null) {
                ServiceListener serviceListener = this.val$listener;
                if (serviceListener != null) {
                    serviceListener.onComplete(false, null);
                    return;
                }
                return;
            }
            iSubDeviceChannel.online(new ISubDeviceActionListener() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.2.1
                @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener
                public void onSuccess() {
                    if (C11272.this.val$isDelayCallback) {
                        TmpSdk.mHandler.postDelayed(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (C11272.this.val$listener != null) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put(DevService.BUNDLE_KEY_PRODUCTKEY, C11272.this.val$productKey);
                                    hashMap.put(DevService.BUNDLE_KEY_DEVICENAME, C11272.this.val$deviceName);
                                    hashMap.put(DevService.BUNDLE_KEY_SUBCHANNEL, iSubDeviceChannel);
                                    C11272.this.val$listener.onComplete(true, hashMap);
                                }
                            }
                        }, SolicitService.CAMERA_OPEN_TIME_OUT);
                        return;
                    }
                    if (C11272.this.val$listener != null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put(DevService.BUNDLE_KEY_PRODUCTKEY, C11272.this.val$productKey);
                        hashMap.put(DevService.BUNDLE_KEY_DEVICENAME, C11272.this.val$deviceName);
                        hashMap.put(DevService.BUNDLE_KEY_SUBCHANNEL, iSubDeviceChannel);
                        C11272.this.val$listener.onComplete(true, hashMap);
                    }
                }

                @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener
                public void onFailed(AError aError2) {
                    if (C11272.this.val$listener != null) {
                        C11272.this.val$listener.onComplete(false, null);
                    }
                }
            });
        }
    }

    private static void logOut(String str, String str2, ServiceListener serviceListener) {
        try {
            SubDeviceInfo subDeviceInfo = new SubDeviceInfo();
            subDeviceInfo.productKey = str;
            subDeviceInfo.deviceName = str2;
            GatewayChannel.getInstance().removeSubDevice(subDeviceInfo, new ISubDeviceRemoveListener() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.3
                @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceRemoveListener
                public void onSuceess() {
                    ALog.m479d(DevService.TAG, "logOut onSuceess");
                }

                @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceRemoveListener
                public void onFailed(AError aError) {
                    ALog.m479d(DevService.TAG, "logOut onFailed aError:" + aError);
                }
            });
        } catch (Exception e) {
            ALog.m480e(TAG, "logOut Exception:" + e.toString());
        } catch (Throwable th) {
            ALog.m480e(TAG, "logOut Throwable:" + th.toString());
        }
    }

    public static void breezeSubDevLogin(final String str, final String str2, final ServiceListener serviceListener) {
        ALog.m479d(TAG, "breezeSubDevLogin productKey:" + str + " deviceName:" + str2 + " listener:" + serviceListener);
        final Runnable runnable = new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.4
            private AtomicBoolean idDone = new AtomicBoolean(false);

            @Override // java.lang.Runnable
            public void run() {
                if (this.idDone.compareAndSet(false, true)) {
                    DevService.breezeSubDevLoginInner(str, str2, serviceListener);
                }
            }
        };
        TmpSdk.BREEZE.close(str2, new IBreeze.ConnectionCallback() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.5
            public void onConnectionStateChange(IBreezeDevice iBreezeDevice, int i, int i2) {
                ALog.m479d(DevService.TAG, "close onConnectionStateChange mac:" + str2 + " state:" + i);
                if (i == 0) {
                    TmpSdk.mHandler.removeCallbacks(runnable);
                    TmpSdk.mHandler.post(runnable);
                }
            }
        });
        TmpSdk.mHandler.postDelayed(runnable, 5000L);
    }

    protected static void breezeSubDevLoginInner(String str, String str2, ServiceListener serviceListener) {
        ALog.m479d(TAG, "breezeSubDevLoginInner productKey:" + str + " deviceName:" + str2 + " listener:" + serviceListener);
        OnlyOnceServiceListener onlyOnceServiceListener = new OnlyOnceServiceListener(serviceListener);
        if (TmpSdk.BREEZE == null) {
            onlyOnceServiceListener.onComplete(false, null);
            return;
        }
        breezeSubDevLoginCallback breezesubdevlogincallback = new breezeSubDevLoginCallback(str, str2, onlyOnceServiceListener);
        breezesubdevlogincallback.setOpening(true);
        TmpSdk.BREEZE.open(false, str2, breezesubdevlogincallback);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class breezeSubDevLoginCallback implements IBreeze.ConnectionCallback {
        protected String deviceName;
        protected boolean isOpening = false;
        protected boolean isRetry = false;
        protected ServiceListener onlyOnceServiceListener;
        protected String productKey;

        public breezeSubDevLoginCallback(String str, String str2, ServiceListener serviceListener) {
            this.productKey = str;
            this.deviceName = str2;
            this.onlyOnceServiceListener = serviceListener;
        }

        public void setOpening(boolean z) {
            this.isOpening = z;
        }

        public boolean isOpening() {
            return this.isOpening;
        }

        public void onConnectionStateChange(IBreezeDevice iBreezeDevice, int i, int i2) {
            ALog.m479d(DevService.TAG, "onConnectionStateChange mac:" + this.deviceName + " state:" + i);
            if (i == 2) {
                setOpening(false);
                BreezeHelper.getDeviceInfo(iBreezeDevice, new C11331());
                return;
            }
            if (i == 0) {
                ALog.m479d(DevService.TAG, "onConnectionStateChange STATE_DISCONNECTED call error isRetry:" + this.isRetry);
                if (isOpening()) {
                    if (!this.isRetry) {
                        this.isRetry = true;
                        TmpSdk.mHandler.postDelayed(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.breezeSubDevLoginCallback.2
                            @Override // java.lang.Runnable
                            public void run() {
                                TmpSdk.BREEZE.open(false, breezeSubDevLoginCallback.this.deviceName, breezeSubDevLoginCallback.this);
                            }
                        }, 5000L);
                    } else {
                        this.onlyOnceServiceListener.onComplete(false, null);
                        setOpening(false);
                    }
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* renamed from: com.aliyun.alink.linksdk.tmp.service.DevService$breezeSubDevLoginCallback$1 */
        /* loaded from: classes.dex */
        class C11331 implements BreezeHelper.IDeviceInfoCallback {
            C11331() {
            }

            public void onDeviceInfo(final BreezeHelper.DeviceInfo deviceInfo) {
                if (deviceInfo == null) {
                    breezeSubDevLoginCallback.this.onlyOnceServiceListener.onComplete(false, null);
                    TmpSdk.BREEZE.close(breezeSubDevLoginCallback.this.deviceName, (IBreeze.ConnectionCallback) null);
                    return;
                }
                ALog.m479d(DevService.TAG, "onDeviceInfo sign:" + deviceInfo.sign);
                BreezeHelper.online(deviceInfo, new BreezeHelper.OnlineCallback() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.breezeSubDevLoginCallback.1.1
                    public void onOnlineResult(final ISubDeviceChannel iSubDeviceChannel, int i) {
                        ALog.m479d(DevService.TAG, "onOnlineResult i:" + i);
                        if (i == 0) {
                            CloudUtils.updateBreezeMac(deviceInfo.productKey, deviceInfo.deviceName, breezeSubDevLoginCallback.this.deviceName, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.breezeSubDevLoginCallback.1.1.1
                                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                                public void onResponse(ARequest aRequest, AResponse aResponse) {
                                    ALog.m479d(DevService.TAG, "updateBreezeMac onResponse productKey:" + deviceInfo.productKey + " deviceName:" + deviceInfo.deviceName + " mac:" + breezeSubDevLoginCallback.this.deviceName);
                                    if (aResponse == null || aResponse.data == null) {
                                        ALog.m480e(DevService.TAG, "updateBreezeMac onResponse productKey aResponse or data empty");
                                        breezeSubDevLoginCallback.this.onlyOnceServiceListener.onComplete(false, null);
                                        TmpSdk.BREEZE.close(breezeSubDevLoginCallback.this.deviceName, (IBreeze.ConnectionCallback) null);
                                        return;
                                    }
                                    String formatResponseData = TextHelper.formatResponseData(aResponse.data);
                                    if (TextUtils.isEmpty(formatResponseData)) {
                                        ALog.m480e(DevService.TAG, "updateBreezeMac onResponse payload empty");
                                        breezeSubDevLoginCallback.this.onlyOnceServiceListener.onComplete(false, null);
                                        TmpSdk.BREEZE.close(breezeSubDevLoginCallback.this.deviceName, (IBreeze.ConnectionCallback) null);
                                        return;
                                    }
                                    CommonResponsePayload commonResponsePayload = (CommonResponsePayload) JSON.parseObject(formatResponseData, CommonResponsePayload.class);
                                    if (commonResponsePayload == null || commonResponsePayload.getCode() != 200) {
                                        ALog.m480e(DevService.TAG, "updateBreezeMac onResponse commonResponsePayload empty or error");
                                        breezeSubDevLoginCallback.this.onlyOnceServiceListener.onComplete(false, null);
                                        TmpSdk.BREEZE.close(breezeSubDevLoginCallback.this.deviceName, (IBreeze.ConnectionCallback) null);
                                    } else {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put(DevService.BUNDLE_KEY_PRODUCTKEY, deviceInfo.productKey);
                                        hashMap.put(DevService.BUNDLE_KEY_DEVICENAME, deviceInfo.deviceName);
                                        hashMap.put(DevService.BUNDLE_KEY_SUBCHANNEL, iSubDeviceChannel);
                                        breezeSubDevLoginCallback.this.onlyOnceServiceListener.onComplete(true, hashMap);
                                        TmpSdk.BREEZE.close(breezeSubDevLoginCallback.this.deviceName, (IBreeze.ConnectionCallback) null);
                                    }
                                }

                                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                                public void onFailure(ARequest aRequest, AError aError) {
                                    ALog.m479d(DevService.TAG, "updateBreezeMac onFailure productKey:" + deviceInfo.productKey + " deviceName:" + deviceInfo.deviceName + " mac:" + breezeSubDevLoginCallback.this.deviceName);
                                    breezeSubDevLoginCallback.this.onlyOnceServiceListener.onComplete(false, null);
                                    TmpSdk.BREEZE.close(breezeSubDevLoginCallback.this.deviceName, (IBreeze.ConnectionCallback) null);
                                }
                            });
                        } else {
                            breezeSubDevLoginCallback.this.onlyOnceServiceListener.onComplete(false, null);
                            TmpSdk.BREEZE.close(breezeSubDevLoginCallback.this.deviceName, (IBreeze.ConnectionCallback) null);
                        }
                    }
                });
            }
        }
    }

    public static void notifySubDeviceBinded(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        sb.append("notifySubDeviceBinded  params:");
        sb.append(jSONObject);
        ALog.m479d(TAG, sb.toString() == null ? "null" : jSONObject.toString());
        if (jSONObject == null) {
            ALog.m480e(TAG, "notifySubDeviceBinded params empty");
        } else {
            notifySubDeviceBinded((SubDevInfo) JSON.parseObject(jSONObject.toString(), SubDevInfo.class));
        }
    }

    public static void notifySubDeviceBinded(final SubDevInfo subDevInfo) {
        if (subDevInfo == null || TextUtils.isEmpty(subDevInfo.productKey) || TextUtils.isEmpty(subDevInfo.deviceName)) {
            ALog.m480e(TAG, "notifySubDeviceBinded params parse error");
            return;
        }
        ALog.m479d(TAG, "notifySubDeviceBinded  subDevInfo pk:" + subDevInfo.productKey + " dn:" + subDevInfo.deviceName);
        DeviceBasicData deviceBasicData = DeviceManager.getInstance().getDeviceBasicData(TextHelper.combineStr(subDevInfo.productKey, subDevInfo.deviceName));
        if (deviceBasicData == null) {
            ALog.m480e(TAG, "getDeviceBasicData basicData null");
            return;
        }
        if ("2".equalsIgnoreCase(deviceBasicData.getModelType())) {
            CloudUtils.setDeviceExtendProperty(subDevInfo.iotId, TmpConstant.DATA_KEY_DEVICENAME, subDevInfo.deviceName, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.6
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    ALog.m479d(DevService.TAG, "setDeviceExtendProperty onResponse response:" + aResponse);
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    ALog.m480e(DevService.TAG, "setDeviceExtendProperty onFailure error:" + aError + " iotid:" + SubDevInfo.this.iotId + " deviceName:" + SubDevInfo.this.deviceName);
                }
            });
        }
        if ("3".equalsIgnoreCase(deviceBasicData.getModelType())) {
            DefaultProvisionConfig defaultProvisionConfig = new DefaultProvisionConfig(deviceBasicData);
            defaultProvisionConfig.setAuthChangeType(DefaultProvisionConfig.AuthChangeType.CloudAuth);
            final IProvision createProvision = DeviceManager.getInstance().createProvision(defaultProvisionConfig);
            createProvision.provisionInit(null, new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.service.DevService.7
                @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                public void onSuccess(Object obj, OutputParams outputParams) {
                    ALog.m479d(DevService.TAG, "provision init onSuccess returnValue:" + outputParams);
                    IProvision.this.unInit();
                }

                @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                public void onFail(Object obj, ErrorInfo errorInfo) {
                    ALog.m479d(DevService.TAG, "provision init onFail errorInfo:" + errorInfo);
                    IProvision.this.unInit();
                }
            });
        }
    }
}
