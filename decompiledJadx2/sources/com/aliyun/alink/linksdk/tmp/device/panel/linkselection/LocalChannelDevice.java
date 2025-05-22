package com.aliyun.alink.linksdk.tmp.device.panel.linkselection;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.IDevice;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener;
import com.aliyun.alink.linksdk.tmp.config.DefaultClientConfig;
import com.aliyun.alink.linksdk.tmp.data.auth.AccessInfo;
import com.aliyun.alink.linksdk.tmp.data.devdetail.DevDetailPayload;
import com.aliyun.alink.linksdk.tmp.data.p044ut.ExtraData;
import com.aliyun.alink.linksdk.tmp.device.panel.data.AccessInfoPayload;
import com.aliyun.alink.linksdk.tmp.device.panel.data.GetPropPayload;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.aliyun.alink.linksdk.tmp.device.panel.data.ProductInfoPayload;
import com.aliyun.alink.linksdk.tmp.device.panel.data.SetPropData;
import com.aliyun.alink.linksdk.tmp.device.panel.data.StatusPayload;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.AlcsEventListenerWrapper;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.AlcsMulChannelEventListenerWrapper;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelDeviceLocalInitListener;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelEventCallback;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.service.UTPoint;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.ResponseUtils;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LocalChannelDevice {
    private static final String TAG = "[Tmp]LocalChannelDevice";
    private DeviceBasicData mBasicData;
    TmpEnum.ChannelStrategy mChannelType;
    private IDevice mDevice;
    private String mIotId;
    public AlcsEventListenerWrapper mListenerWrapper;
    private CopyOnWriteArrayList<Runnable> mLocalTaskList = new CopyOnWriteArrayList<>();
    private boolean mIsLocalIniting = false;

    public LocalChannelDevice(String str, TmpEnum.ChannelStrategy channelStrategy) {
        this.mIotId = str;
        this.mChannelType = channelStrategy;
        TmpStorage.DeviceInfo deviceInfo = TmpStorage.getInstance().getDeviceInfo(this.mIotId);
        if (deviceInfo != null) {
            DeviceBasicData deviceBasicData = new DeviceBasicData(false);
            this.mBasicData = deviceBasicData;
            deviceBasicData.setIotId(this.mIotId);
            this.mBasicData.setProductKey(deviceInfo.mProductKey);
            this.mBasicData.setDeviceName(deviceInfo.mDeviceName);
        }
    }

    public boolean isInit() {
        return isLocalConnected();
    }

    public void uninit() {
        ALog.m479d(TAG, "uninit mDevice:" + this.mDevice);
        IDevice iDevice = this.mDevice;
        if (iDevice != null) {
            AlcsEventListenerWrapper alcsEventListenerWrapper = this.mListenerWrapper;
            if (alcsEventListenerWrapper != null) {
                iDevice.removeDeviceStateChangeListener(alcsEventListenerWrapper);
                this.mListenerWrapper = null;
            }
            this.mDevice.unInit();
            this.mDevice = null;
        }
        this.mIsLocalIniting = false;
    }

    public String getProductKey() {
        DeviceBasicData deviceBasicData = this.mBasicData;
        if (deviceBasicData != null) {
            return deviceBasicData.getProductKey();
        }
        return null;
    }

    public String getDeviceName() {
        DeviceBasicData deviceBasicData = this.mBasicData;
        if (deviceBasicData != null) {
            return deviceBasicData.getDeviceName();
        }
        return null;
    }

    public boolean isLocalIniting() {
        IDevice iDevice;
        boolean z = this.mIsLocalIniting || ((iDevice = this.mDevice) != null && iDevice.getDeviceState() == TmpEnum.DeviceState.CONNECTING);
        ALog.m479d(TAG, "isLocalIniting :" + z);
        return z;
    }

    public void setIsLocalIniting(boolean z) {
        ALog.m479d(TAG, "setIsLocalIniting :" + z);
        this.mIsLocalIniting = z;
    }

    public void init(final IPanelCallback iPanelCallback, final IPanelDeviceLocalInitListener iPanelDeviceLocalInitListener) {
        ALog.m479d(TAG, "init callback:" + iPanelCallback + " localInitListener:" + iPanelDeviceLocalInitListener);
        if (isLocalIniting()) {
            initEndCallback(iPanelCallback, false, null);
            return;
        }
        if (isInit()) {
            initEndCallback(iPanelCallback, true, null);
            return;
        }
        setIsLocalIniting(true);
        ProductInfoPayload.ProductInfo productInfo = TmpStorage.getInstance().getProductInfo(this.mIotId);
        if (productInfo == null) {
            initWithCloud(iPanelCallback, iPanelDeviceLocalInitListener);
            return;
        }
        final TmpStorage.DeviceInfo deviceInfo = TmpStorage.getInstance().getDeviceInfo(this.mIotId);
        if (deviceInfo == null) {
            initWithCloud(iPanelCallback, iPanelDeviceLocalInitListener);
            return;
        }
        final AccessInfo accessInfo = TmpStorage.getInstance().getAccessInfo(deviceInfo.getId());
        if (accessInfo == null) {
            initWithCloud(iPanelCallback, iPanelDeviceLocalInitListener);
            return;
        }
        String dnMac = TmpStorage.getInstance().getDnMac(deviceInfo.mDeviceName);
        if (!TextUtils.isEmpty(dnMac)) {
            DeviceManager.getInstance().updateDeviceInfo(deviceInfo.mProductKey, dnMac, deviceInfo.mProductKey, deviceInfo.mDeviceName);
        } else if ("NET_BT".equalsIgnoreCase(productInfo.netType) || "NET_OTHER".equalsIgnoreCase(productInfo.netType)) {
            CloudUtils.getDeviceExtendProperty(this.mIotId, TmpConstant.DATA_KEY_DEVICENAME, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.1
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    if (aResponse != null && aResponse.data != null) {
                        String obj = aResponse.data.toString();
                        if (!TextUtils.isEmpty(obj)) {
                            try {
                                CommonResponsePayload commonResponsePayload = (CommonResponsePayload) JSONObject.parseObject(obj, CommonResponsePayload.class);
                                if (commonResponsePayload != null && !TextUtils.isEmpty((CharSequence) commonResponsePayload.getData())) {
                                    DeviceManager.getInstance().updateDeviceInfo(deviceInfo.mProductKey, (String) commonResponsePayload.getData(), deviceInfo.mProductKey, deviceInfo.mDeviceName);
                                }
                            } catch (Exception e) {
                                ALog.m480e(LocalChannelDevice.TAG, "getDeviceExtendProperty onResponse error:" + e.toString());
                            }
                        } else {
                            ALog.m480e(LocalChannelDevice.TAG, "getDeviceExtendProperty data empty");
                        }
                    } else {
                        ALog.m480e(LocalChannelDevice.TAG, "getDeviceExtendProperty onResponse response or response.data empty");
                    }
                    LocalChannelDevice.this.initDevice(deviceInfo.mProductKey, deviceInfo.mDeviceName, accessInfo.mAccessKey, accessInfo.mAccessToken, iPanelCallback, iPanelDeviceLocalInitListener);
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("getDeviceExtendProperty onFailure error:");
                    sb.append(aError == null ? "" : aError.toString());
                    ALog.m484w(LocalChannelDevice.TAG, sb.toString());
                    LocalChannelDevice.this.initDevice(deviceInfo.mProductKey, deviceInfo.mDeviceName, accessInfo.mAccessKey, accessInfo.mAccessToken, iPanelCallback, iPanelDeviceLocalInitListener);
                }
            });
            return;
        }
        if (accessInfo == null) {
            initWithCloud(iPanelCallback, iPanelDeviceLocalInitListener);
        } else {
            initDevice(deviceInfo.mProductKey, deviceInfo.mDeviceName, accessInfo.mAccessKey, accessInfo.mAccessToken, iPanelCallback, iPanelDeviceLocalInitListener);
        }
    }

    private void initWithCloud(final IPanelCallback iPanelCallback, final IPanelDeviceLocalInitListener iPanelDeviceLocalInitListener) {
        ALog.m479d(TAG, "init  callback:" + iPanelCallback + " localInitListener:" + iPanelDeviceLocalInitListener);
        if (TmpSdk.getCloudProxy() == null) {
            initEndCallback(iPanelCallback, false, null);
        } else {
            TmpSdk.getCloudProxy().queryProductInfo(this.mIotId, new ICloudProxyListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.2
                @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
                public void onResponse(String str, Object obj) {
                    if (obj == null) {
                        ALog.m480e(LocalChannelDevice.TAG, "queryProductInfo aResponse error null callback:" + iPanelCallback);
                        LocalChannelDevice.this.initEndCallback(iPanelCallback, false, null);
                        return;
                    }
                    ProductInfoPayload productInfoPayload = (ProductInfoPayload) GsonUtils.fromJson(obj.toString(), new TypeToken<ProductInfoPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.2.1
                    }.getType());
                    if (productInfoPayload != null && productInfoPayload.data != null && !TextUtils.isEmpty(productInfoPayload.data.dataFormat)) {
                        TmpStorage.getInstance().saveProductInfo(LocalChannelDevice.this.mIotId, productInfoPayload.data);
                        ALog.m479d(LocalChannelDevice.TAG, "queryProductInfo onResponse dataFormat:" + productInfoPayload.data.dataFormat + " payload:" + productInfoPayload);
                        TmpSdk.getCloudProxy().queryAccessInfo(LocalChannelDevice.this.mIotId, new AccessInfoListener(iPanelCallback, iPanelDeviceLocalInitListener));
                        return;
                    }
                    ALog.m480e(LocalChannelDevice.TAG, "queryProductInfo payload error :" + iPanelCallback);
                    LocalChannelDevice.this.initEndCallback(iPanelCallback, false, null);
                }

                @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
                public void onFailure(String str, AError aError) {
                    AccessInfo accessInfo;
                    StringBuilder sb = new StringBuilder();
                    sb.append("queryProductInfo onFailure id:");
                    sb.append(str);
                    sb.append(" error:");
                    sb.append(aError == null ? "null" : aError.toString());
                    ALog.m480e(LocalChannelDevice.TAG, sb.toString());
                    TmpStorage.DeviceInfo deviceInfo = TmpStorage.getInstance().getDeviceInfo(LocalChannelDevice.this.mIotId);
                    if (deviceInfo != null) {
                        accessInfo = TmpStorage.getInstance().getAccessInfo(deviceInfo.getId());
                        String dnMac = TmpStorage.getInstance().getDnMac(deviceInfo.mDeviceName);
                        if (!TextUtils.isEmpty(dnMac)) {
                            DeviceManager.getInstance().updateDeviceInfo(deviceInfo.mProductKey, dnMac, deviceInfo.mProductKey, deviceInfo.mDeviceName);
                        }
                    } else {
                        accessInfo = null;
                    }
                    if (accessInfo != null) {
                        LocalChannelDevice.this.initDevice(deviceInfo.mProductKey, deviceInfo.mDeviceName, accessInfo.mAccessKey, accessInfo.mAccessToken, iPanelCallback, iPanelDeviceLocalInitListener);
                    } else {
                        ALog.m479d(LocalChannelDevice.TAG, "queryProductInfo onFailure callback false return");
                        LocalChannelDevice.this.initEndCallback(iPanelCallback, false, null);
                    }
                }
            });
        }
    }

    public void getProperties(final IPanelCallback iPanelCallback, boolean z) {
        ALog.m479d(TAG, "getProperties  callback:" + iPanelCallback + " bCache:" + z);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getProperties callback null");
            return;
        }
        Runnable runnable = null;
        if (!isDeviceLocalFoud()) {
            iPanelCallback.onComplete(false, null);
            return;
        }
        if (isLocalConnected()) {
            getProperties(new NextTaskCallback(this, iPanelCallback));
            return;
        }
        if (z) {
            runnable = new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.3
                @Override // java.lang.Runnable
                public void run() {
                    LocalChannelDevice.this.getProperties(iPanelCallback, false);
                }
            };
        } else {
            iPanelCallback.onComplete(false, null);
        }
        startLocalConnectAndCacheTask(runnable, z);
    }

    private void getProperties(final IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "getProperties  callback:" + iPanelCallback + " mDevice:" + this.mDevice);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getProperties callback null");
            return;
        }
        if (this.mDevice == null) {
            iPanelCallback.onComplete(false, null);
            ALog.m480e(TAG, "getProperties mDevice null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<Property> properties = this.mDevice.getProperties();
        if (properties != null && !properties.isEmpty()) {
            for (Property property : properties) {
                if (property != null) {
                    arrayList.add(property.getIdentifier());
                }
            }
        }
        this.mDevice.getPropertyValue(arrayList, null, new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.4
            /* JADX WARN: Removed duplicated region for block: B:19:0x00a0  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x00ab  */
            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onSuccess(Object obj, OutputParams outputParams) {
                org.json.JSONObject jSONObject;
                if (LocalChannelDevice.this.mDevice != null) {
                    Map<String, ValueWrapper> allPropertyValue = LocalChannelDevice.this.mDevice.getAllPropertyValue();
                    if (allPropertyValue != null && !allPropertyValue.isEmpty()) {
                        GetPropPayload getPropPayload = new GetPropPayload();
                        long currentTimeMillis = System.currentTimeMillis();
                        for (Map.Entry<String, ValueWrapper> entry : allPropertyValue.entrySet()) {
                            ALog.m479d(LocalChannelDevice.TAG, "key:" + entry.getKey() + " value:" + entry.getValue());
                            getPropPayload.data.put(entry.getKey(), new GetPropPayload.PropItem(currentTimeMillis, entry.getValue()));
                        }
                        ALog.m479d(LocalChannelDevice.TAG, "getProperties data::" + getPropPayload);
                        try {
                            jSONObject = new org.json.JSONObject(GsonUtils.toJson(getPropPayload.data));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (jSONObject == null) {
                            iPanelCallback.onComplete(true, ResponseUtils.getSuccessRspJson(jSONObject));
                            return;
                        } else {
                            ALog.m479d(LocalChannelDevice.TAG, "getPropertyValue onSuccess object null");
                            iPanelCallback.onComplete(false, null);
                            return;
                        }
                    }
                    jSONObject = null;
                    if (jSONObject == null) {
                    }
                } else {
                    onFail(obj, null);
                }
            }

            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            public void onFail(Object obj, ErrorInfo errorInfo) {
                iPanelCallback.onComplete(false, null);
            }
        });
    }

    public void setProperties(final String str, final IPanelCallback iPanelCallback, boolean z) {
        ALog.m479d(TAG, "setProperties params:" + str + " callback:" + iPanelCallback + " bCache:" + z);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getProperties callback null");
            return;
        }
        Runnable runnable = null;
        if (!isDeviceLocalFoud()) {
            iPanelCallback.onComplete(false, null);
            return;
        }
        if (isLocalConnected()) {
            setProperties(str, new NextTaskCallback(this, iPanelCallback));
            return;
        }
        if (z) {
            runnable = new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.5
                @Override // java.lang.Runnable
                public void run() {
                    LocalChannelDevice.this.setProperties(str, iPanelCallback, false);
                }
            };
        } else {
            iPanelCallback.onComplete(false, null);
        }
        startLocalConnectAndCacheTask(runnable, z);
    }

    private void setProperties(String str, final IPanelCallback iPanelCallback) {
        ExtraData extraData;
        ALog.m479d(TAG, "setProperties params:" + str + " callback:" + iPanelCallback + " mDevice:" + this.mDevice);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "setProperties callback null");
            return;
        }
        if (this.mDevice == null) {
            iPanelCallback.onComplete(false, null);
            ALog.m480e(TAG, "setProperties mDevice null");
            return;
        }
        JSONObject parseObject = JSON.parseObject(str);
        final UTPoint createUTPoint = UTPoint.createUTPoint(parseObject, "/thing/properties/set");
        if (createUTPoint != null) {
            createUTPoint.trackStart();
        }
        SetPropData setPropData = (SetPropData) GsonUtils.fromJson(str, new TypeToken<SetPropData>() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.6
        }.getType());
        ArrayList arrayList = new ArrayList();
        if (setPropData != null && setPropData.items != null) {
            for (Map.Entry<String, ValueWrapper> entry : setPropData.items.entrySet()) {
                arrayList.add(new KeyValuePair(entry.getKey(), entry.getValue()));
            }
        }
        if (parseObject == null || !parseObject.containsKey(TmpConstant.KEY_IOT_PERFORMANCE_ID)) {
            extraData = null;
        } else {
            try {
                extraData = new ExtraData();
            } catch (Exception unused) {
                extraData = null;
            }
            try {
                extraData.performanceId = Integer.valueOf(parseObject.get(TmpConstant.KEY_IOT_PERFORMANCE_ID).toString()).intValue();
            } catch (Exception unused2) {
                ALog.m480e(TAG, "KEY_IOT_PERFORMANCE_ID error");
                this.mDevice.setPropertyValue(extraData, arrayList, (Object) null, new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.7
                    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                    public void onSuccess(Object obj, OutputParams outputParams) {
                        ALog.m479d(LocalChannelDevice.TAG, "setProperties onSuccess:" + outputParams);
                        UTPoint uTPoint = createUTPoint;
                        if (uTPoint != null) {
                            uTPoint.trackEnd();
                        }
                        iPanelCallback.onComplete(true, ResponseUtils.getSuccessRspJson(new org.json.JSONObject()));
                    }

                    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                    public void onFail(Object obj, ErrorInfo errorInfo) {
                        ALog.m479d(LocalChannelDevice.TAG, "setProperties onFail:" + errorInfo);
                        UTPoint uTPoint = createUTPoint;
                        if (uTPoint != null) {
                            uTPoint.trackEnd();
                        }
                        iPanelCallback.onComplete(false, null);
                    }
                });
            }
        }
        this.mDevice.setPropertyValue(extraData, arrayList, (Object) null, new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.7
            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            public void onSuccess(Object obj, OutputParams outputParams) {
                ALog.m479d(LocalChannelDevice.TAG, "setProperties onSuccess:" + outputParams);
                UTPoint uTPoint = createUTPoint;
                if (uTPoint != null) {
                    uTPoint.trackEnd();
                }
                iPanelCallback.onComplete(true, ResponseUtils.getSuccessRspJson(new org.json.JSONObject()));
            }

            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            public void onFail(Object obj, ErrorInfo errorInfo) {
                ALog.m479d(LocalChannelDevice.TAG, "setProperties onFail:" + errorInfo);
                UTPoint uTPoint = createUTPoint;
                if (uTPoint != null) {
                    uTPoint.trackEnd();
                }
                iPanelCallback.onComplete(false, null);
            }
        });
    }

    public void invokeService(final String str, final IPanelCallback iPanelCallback, boolean z) {
        ALog.m479d(TAG, "invokeServiceLocal params:" + str + " callback:" + iPanelCallback + " bCache:" + z);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getProperties callback null");
            return;
        }
        Runnable runnable = null;
        if (!isDeviceLocalFoud()) {
            iPanelCallback.onComplete(false, null);
            return;
        }
        if (isLocalConnected()) {
            invokeService(str, new NextTaskCallback(this, iPanelCallback));
            return;
        }
        if (z) {
            runnable = new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.8
                @Override // java.lang.Runnable
                public void run() {
                    LocalChannelDevice.this.invokeService(str, iPanelCallback, false);
                }
            };
        } else {
            iPanelCallback.onComplete(false, null);
        }
        startLocalConnectAndCacheTask(runnable, z);
    }

    private void invokeService(String str, final IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "invokeServiceLocal params:" + str + " callback:" + iPanelCallback + " mDevice:" + this.mDevice);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "setProperties callback null");
            return;
        }
        if (this.mDevice == null) {
            iPanelCallback.onComplete(false, null);
            ALog.m480e(TAG, "setProperties mDevice null");
            return;
        }
        InvokeServiceData invokeServiceData = (InvokeServiceData) GsonUtils.fromJson(str, new TypeToken<InvokeServiceData>() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.9
        }.getType());
        ArrayList arrayList = new ArrayList();
        if (invokeServiceData.args != null && !invokeServiceData.args.isEmpty()) {
            for (Map.Entry<String, ValueWrapper> entry : invokeServiceData.args.entrySet()) {
                arrayList.add(new KeyValuePair(entry.getKey(), entry.getValue()));
            }
        }
        this.mDevice.invokeService(invokeServiceData.identifier, arrayList, null, new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.10
            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            public void onSuccess(Object obj, OutputParams outputParams) {
                ALog.m479d(LocalChannelDevice.TAG, "invokeService onSuccess:" + outputParams);
                String json = GsonUtils.toJson(outputParams);
                org.json.JSONObject jSONObject = new org.json.JSONObject();
                try {
                    if (!TextUtils.isEmpty(json)) {
                        jSONObject = new org.json.JSONObject(json);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iPanelCallback.onComplete(true, ResponseUtils.getSuccessRspJson(jSONObject));
            }

            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            public void onFail(Object obj, ErrorInfo errorInfo) {
                ALog.m479d(LocalChannelDevice.TAG, "invokeService onFail:" + errorInfo);
                iPanelCallback.onComplete(false, null);
            }
        });
    }

    public void getStatus(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "simulateGetStatus callback:" + iPanelCallback);
        StatusPayload statusPayload = new StatusPayload();
        statusPayload.code = 200;
        statusPayload.f1025id = UUID.randomUUID().toString();
        statusPayload.data.time = System.currentTimeMillis();
        statusPayload.data.status = 1;
        iPanelCallback.onComplete(true, GsonUtils.toJson(statusPayload));
    }

    public void subAllEvents(final IPanelEventCallback iPanelEventCallback, final IPanelCallback iPanelCallback, boolean z) {
        ALog.m479d(TAG, "subAllEvents listener:" + iPanelEventCallback + " callback:" + iPanelCallback + " bCache:" + z + " device:" + this.mDevice);
        if (iPanelEventCallback == null) {
            ALog.m480e(TAG, "getProperties listener null");
            return;
        }
        Runnable runnable = null;
        if (!isDeviceLocalFoud()) {
            if (iPanelCallback != null) {
                iPanelCallback.onComplete(false, null);
            }
        } else {
            if (isLocalConnected() || isLocalIniting()) {
                subAllEvents(iPanelEventCallback, new NextTaskCallback(this, iPanelCallback));
                return;
            }
            if (z) {
                runnable = new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.11
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalChannelDevice.this.subAllEvents(iPanelEventCallback, iPanelCallback, false);
                    }
                };
            } else if (iPanelCallback != null) {
                iPanelCallback.onComplete(false, null);
            }
            startLocalConnectAndCacheTask(runnable, z);
        }
    }

    private void subAllEvents(IPanelEventCallback iPanelEventCallback, IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "subAllEvents listener:" + iPanelEventCallback + " callback:" + iPanelCallback + " mDevice:" + this.mDevice);
        if (iPanelEventCallback == null) {
            ALog.m480e(TAG, "subAllEvents callback null");
            return;
        }
        IDevice iDevice = this.mDevice;
        if (iDevice == null) {
            if (iPanelCallback != null) {
                iPanelCallback.onComplete(false, null);
                return;
            }
            return;
        }
        AlcsEventListenerWrapper alcsEventListenerWrapper = this.mListenerWrapper;
        if (alcsEventListenerWrapper != null) {
            iDevice.removeDeviceStateChangeListener(alcsEventListenerWrapper);
        }
        AlcsMulChannelEventListenerWrapper alcsMulChannelEventListenerWrapper = new AlcsMulChannelEventListenerWrapper(this, this.mBasicData, iPanelCallback, iPanelEventCallback);
        this.mListenerWrapper = alcsMulChannelEventListenerWrapper;
        this.mDevice.addDeviceStateChangeListener(alcsMulChannelEventListenerWrapper);
        this.mDevice.subAllEvents(null, this.mListenerWrapper);
    }

    public void startLocalConnect(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "startLocalConnect callback:" + iPanelCallback);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getProperties callback null");
            return;
        }
        if (!isDeviceLocalFoud()) {
            iPanelCallback.onComplete(false, null);
        } else if (isLocalConnected() || isLocalIniting()) {
            iPanelCallback.onComplete(true, null);
        } else {
            init(iPanelCallback, null);
        }
    }

    public void stopLocalConnect(final IPanelCallback iPanelCallback) {
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "stopLocalConnect callback null");
            return;
        }
        if (!isDeviceLocalFoud()) {
            iPanelCallback.onComplete(true, null);
            return;
        }
        if (isLocalDisconnected() || isLocalIniting()) {
            addTask(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.12
                @Override // java.lang.Runnable
                public void run() {
                    if (LocalChannelDevice.this.mDevice != null) {
                        LocalChannelDevice.this.mDevice.unInit();
                        LocalChannelDevice.this.mDevice = null;
                    }
                    iPanelCallback.onComplete(true, null);
                }
            });
            if (isLocalConnected()) {
                runNextTask();
                return;
            }
            return;
        }
        iPanelCallback.onComplete(true, null);
    }

    public void getLocalConnectionState(IPanelCallback iPanelCallback) {
        IDevice iDevice;
        TmpEnum.DeviceState deviceState = TmpEnum.DeviceState.DISCONNECTED;
        ALog.m479d(TAG, "getLocalConnectionState islocaliniting:" + isLocalIniting() + " isIniting:" + isInit());
        if (isDeviceLocalFoud() && (iDevice = this.mDevice) != null) {
            deviceState = iDevice.getDeviceState();
        } else if (isLocalIniting() && isDeviceLocalFoud()) {
            deviceState = TmpEnum.DeviceState.CONNECTING;
        } else if (isInit() && isDeviceLocalFoud()) {
            deviceState = TmpEnum.DeviceState.CONNECTED;
        } else if (!isDeviceLocalFoud()) {
            deviceState = TmpEnum.DeviceState.DISCONNECTED;
        }
        ALog.m479d(TAG, "getLocalConnectionState() deviceState:" + deviceState);
        iPanelCallback.onComplete(true, deviceState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initEndCallback(IPanelCallback iPanelCallback, boolean z, Object obj) {
        setIsLocalIniting(false);
        if (iPanelCallback != null) {
            iPanelCallback.onComplete(z, obj);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private class AccessInfoListener implements IConnectSendListener, ICloudProxyListener {
        private IPanelCallback mCallback;
        private IPanelDeviceLocalInitListener mLocalInitListener;

        public AccessInfoListener(IPanelCallback iPanelCallback, IPanelDeviceLocalInitListener iPanelDeviceLocalInitListener) {
            this.mCallback = iPanelCallback;
            this.mLocalInitListener = iPanelDeviceLocalInitListener;
        }

        protected void onComplete(AccessInfoPayload accessInfoPayload) {
            if (accessInfoPayload == null || accessInfoPayload.data == null || accessInfoPayload.data.isEmpty()) {
                ALog.m480e(LocalChannelDevice.TAG, "queryAccessInfo payload error null callback:" + this.mCallback);
                LocalChannelDevice.this.initEndCallback(this.mCallback, false, null);
                return;
            }
            final AccessInfoPayload.AlcsDeviceInfo alcsDeviceInfo = accessInfoPayload.data.get(0);
            ALog.m479d(LocalChannelDevice.TAG, "productKey:" + alcsDeviceInfo.productKey + " deviceName:" + alcsDeviceInfo.deviceName + " iotid:" + alcsDeviceInfo.iotId);
            AccessInfo accessInfo = new AccessInfo();
            accessInfo.mAccessKey = alcsDeviceInfo.accessKey;
            accessInfo.mAccessToken = alcsDeviceInfo.accessToken;
            CloudUtils.getDeviceExtendProperty(alcsDeviceInfo.iotId, TmpConstant.DATA_KEY_DEVICENAME, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.AccessInfoListener.1
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    if (aResponse != null && aResponse.data != null) {
                        String obj = aResponse.data.toString();
                        if (!TextUtils.isEmpty(obj)) {
                            try {
                                CommonResponsePayload commonResponsePayload = (CommonResponsePayload) JSONObject.parseObject(obj, CommonResponsePayload.class);
                                if (commonResponsePayload != null && !TextUtils.isEmpty((CharSequence) commonResponsePayload.getData())) {
                                    DeviceManager.getInstance().updateDeviceInfo(alcsDeviceInfo.productKey, (String) commonResponsePayload.getData(), alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName);
                                }
                            } catch (Exception e) {
                                ALog.m480e(LocalChannelDevice.TAG, "getDeviceExtendProperty onResponse error:" + e.toString());
                            }
                        } else {
                            ALog.m480e(LocalChannelDevice.TAG, "getDeviceExtendProperty data empty");
                        }
                    } else {
                        ALog.m480e(LocalChannelDevice.TAG, "getDeviceExtendProperty onResponse response or response.data empty");
                    }
                    LocalChannelDevice.this.initDevice(alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName, alcsDeviceInfo.accessKey, alcsDeviceInfo.accessToken, AccessInfoListener.this.mCallback, AccessInfoListener.this.mLocalInitListener);
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("getDeviceExtendProperty onFailure error:");
                    sb.append(aError == null ? "" : aError.toString());
                    ALog.m484w(LocalChannelDevice.TAG, sb.toString());
                    LocalChannelDevice.this.initDevice(alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName, alcsDeviceInfo.accessKey, alcsDeviceInfo.accessToken, AccessInfoListener.this.mCallback, AccessInfoListener.this.mLocalInitListener);
                }
            });
            TmpStorage.DeviceInfo deviceInfo = new TmpStorage.DeviceInfo(alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName);
            TmpStorage.getInstance().saveDeviceInfo(LocalChannelDevice.this.mIotId, alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName);
            TmpStorage.getInstance().saveAccessInfo(deviceInfo.getId(), alcsDeviceInfo.accessKey, alcsDeviceInfo.accessToken);
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
        public void onResponse(ARequest aRequest, AResponse aResponse) {
            if (aResponse == null || aResponse.data == null) {
                ALog.m480e(LocalChannelDevice.TAG, "queryAccessInfo aResponse error null callback:" + this.mCallback);
                LocalChannelDevice.this.initEndCallback(this.mCallback, false, null);
                return;
            }
            ALog.m479d(LocalChannelDevice.TAG, "queryAccessInfo onResponse payload data:" + aResponse.data.toString().trim());
            onComplete((AccessInfoPayload) JSON.parseObject(aResponse.data.toString().trim(), new TypeReference<AccessInfoPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.AccessInfoListener.2
            }, new Feature[0]));
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
        public void onFailure(ARequest aRequest, AError aError) {
            LocalChannelDevice.this.initEndCallback(this.mCallback, false, null);
        }

        @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
        public void onResponse(String str, Object obj) {
            if (obj == null) {
                ALog.m480e(LocalChannelDevice.TAG, "queryAccessInfo aResponse error null callback:" + this.mCallback);
                LocalChannelDevice.this.initEndCallback(this.mCallback, false, null);
                return;
            }
            ALog.m479d(LocalChannelDevice.TAG, "queryAccessInfo onResponse payload data:" + obj.toString().trim());
            onComplete((AccessInfoPayload) JSON.parseObject(obj.toString().trim(), new TypeReference<AccessInfoPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.AccessInfoListener.3
            }, new Feature[0]));
        }

        @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
        public void onFailure(String str, AError aError) {
            LocalChannelDevice.this.initEndCallback(this.mCallback, false, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDevice(final String str, final String str2, String str3, String str4, final IPanelCallback iPanelCallback, final IPanelDeviceLocalInitListener iPanelDeviceLocalInitListener) {
        DeviceBasicData deviceBasicData = new DeviceBasicData(false);
        this.mBasicData = deviceBasicData;
        deviceBasicData.setIotId(this.mIotId);
        this.mBasicData.setProductKey(str);
        this.mBasicData.setDeviceName(str2);
        TmpSdk.mHandler.post(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.13
            @Override // java.lang.Runnable
            public void run() {
                LocalChannelDevice localChannelDevice = LocalChannelDevice.this;
                localChannelDevice.cacheDevDetail(localChannelDevice.mIotId, str, str2);
            }
        });
        if (!isDeviceLocalFoud()) {
            ALog.m480e(TAG, "initDevice isDeviceLocalFoud not found return error");
            initEndCallback(iPanelCallback, false, null);
            return;
        }
        this.mBasicData.setLocal(true);
        DeviceBasicData deviceBasicData2 = DeviceManager.getInstance().getDeviceBasicData(this.mBasicData.getDevId());
        this.mBasicData.setPort(deviceBasicData2.getPort());
        this.mBasicData.setAddr(deviceBasicData2.getAddr());
        ALog.m479d(TAG, "initLocalDevice mIsLocalIniting:" + this.mIsLocalIniting + " callback:" + iPanelCallback);
        if (isInit()) {
            initEndCallback(iPanelCallback, true, null);
            return;
        }
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig(this.mBasicData);
        defaultClientConfig.setAccessKey(str3);
        defaultClientConfig.setAccessToken(str4);
        IDevice createDevice = DeviceManager.getInstance().createDevice(defaultClientConfig);
        this.mDevice = createDevice;
        if (createDevice == null) {
            ALog.m480e(TAG, "mDevice null");
            initEndCallback(iPanelCallback, true, null);
        } else {
            createDevice.init(null, new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.14
                @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                public void onSuccess(Object obj, OutputParams outputParams) {
                    ALog.m479d(LocalChannelDevice.TAG, "onSuccess returnValue:" + outputParams + " callback:" + iPanelCallback + " mDevice:" + LocalChannelDevice.this.mDevice);
                    TmpEnum.DeviceState deviceState = TmpEnum.DeviceState.DISCONNECTED;
                    if (LocalChannelDevice.this.mDevice == null) {
                        LocalChannelDevice.this.localInitStateNotify(iPanelDeviceLocalInitListener, TmpEnum.DeviceState.DISCONNECTED);
                        LocalChannelDevice.this.initEndCallback(iPanelCallback, false, null);
                    } else {
                        LocalChannelDevice.this.localInitStateNotify(iPanelDeviceLocalInitListener, LocalChannelDevice.this.mDevice.getDeviceState());
                        LocalChannelDevice.this.initEndCallback(iPanelCallback, true, null);
                    }
                }

                @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                public void onFail(Object obj, ErrorInfo errorInfo) {
                    ALog.m480e(LocalChannelDevice.TAG, "onFail errorInfo:" + errorInfo + " callback:" + iPanelCallback + " mDevice:" + LocalChannelDevice.this.mDevice);
                    TmpEnum.DeviceState deviceState = TmpEnum.DeviceState.DISCONNECTED;
                    if (LocalChannelDevice.this.mDevice != null) {
                        deviceState = LocalChannelDevice.this.mDevice.getDeviceState();
                    }
                    LocalChannelDevice.this.localInitStateNotify(iPanelDeviceLocalInitListener, deviceState);
                    LocalChannelDevice.this.initEndCallback(iPanelCallback, false, null);
                }
            });
            localInitStateNotify(iPanelDeviceLocalInitListener, TmpEnum.DeviceState.CONNECTING);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void localInitStateNotify(IPanelDeviceLocalInitListener iPanelDeviceLocalInitListener, TmpEnum.DeviceState deviceState) {
        ALog.m479d(TAG, "localInitStateNotify state:" + deviceState + " IPanelDeviceLocalInitListener:" + iPanelDeviceLocalInitListener);
        if (iPanelDeviceLocalInitListener != null) {
            iPanelDeviceLocalInitListener.onDevStateChange(this.mIotId, deviceState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheDevDetail(String str, final String str2, final String str3) {
        TmpSdk.getCloudProxy().queryDeviceDetail(str, new ICloudProxyListener() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.15
            @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
            public void onResponse(String str4, Object obj) {
                DevDetailPayload devDetailPayload = (DevDetailPayload) GsonUtils.fromJson(obj.toString(), new TypeToken<DevDetailPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.15.1
                }.getType());
                if (devDetailPayload == null || devDetailPayload.data == null) {
                    ALog.m480e(LocalChannelDevice.TAG, "listBindingByDev onResponse payload data null");
                } else {
                    DeviceManager.getInstance().saveDeviceDetailInfo(TextHelper.combineStr(str2, str3), GsonUtils.toJson(devDetailPayload.data));
                }
            }

            @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
            public void onFailure(String str4, AError aError) {
                ALog.m480e(LocalChannelDevice.TAG, "listBindingByDev onFailure aError:" + aError);
            }
        });
    }

    private boolean isDeviceLocalFoud() {
        if (this.mBasicData != null) {
            ALog.m479d(TAG, "isDeviceLocalFoud pk:" + this.mBasicData.getProductKey() + " dn:" + this.mBasicData.getDeviceName() + " Device:" + this.mDevice);
            if (DeviceManager.getInstance().getDeviceBasicData(this.mBasicData.getDevId()) != null) {
                return true;
            }
            ALog.m479d(TAG, "isDeviceLocalFoud false");
            return false;
        }
        ALog.m480e(TAG, "isDeviceLocalFoud mBasicData null");
        return false;
    }

    private boolean isLocalConnected() {
        boolean z = false;
        if (this.mDevice != null && TmpEnum.DeviceState.CONNECTED == this.mDevice.getDeviceState()) {
            z = true;
        }
        ALog.m479d(TAG, "isLocalConnected isLocalConnected:" + z);
        return z;
    }

    private boolean isLocalDisconnected() {
        return this.mDevice == null || TmpEnum.DeviceState.DISCONNECTED == this.mDevice.getDeviceState() || TmpEnum.DeviceState.UNKNOW == this.mDevice.getDeviceState();
    }

    public void runNextTask() {
        Runnable remove;
        ALog.m479d(TAG, "runNextTask mLocalTaskList:" + this.mLocalTaskList.size());
        CopyOnWriteArrayList<Runnable> copyOnWriteArrayList = this.mLocalTaskList;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty() || (remove = this.mLocalTaskList.remove(0)) == null) {
            return;
        }
        remove.run();
    }

    public void addTask(Runnable runnable) {
        ALog.m479d(TAG, "addTask runnable:" + runnable);
        if (runnable == null) {
            return;
        }
        this.mLocalTaskList.add(runnable);
    }

    public void startLocalConnectAndCacheTask(Runnable runnable, boolean z) {
        addTask(runnable);
        if (isLocalConnected()) {
            runNextTask();
        } else {
            if (isLocalIniting()) {
                return;
            }
            if (z) {
                init(new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice.16
                    @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                    public void onComplete(boolean z2, Object obj) {
                        LocalChannelDevice.this.runNextTask();
                    }
                }, null);
            } else {
                runNextTask();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class NextTaskCallback implements IPanelCallback {
        private WeakReference<LocalChannelDevice> mLocalChannelDeviceRef;
        private IPanelCallback mPanelCallback;

        public NextTaskCallback(LocalChannelDevice localChannelDevice, IPanelCallback iPanelCallback) {
            this.mLocalChannelDeviceRef = new WeakReference<>(localChannelDevice);
            this.mPanelCallback = iPanelCallback;
        }

        @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
        public void onComplete(boolean z, Object obj) {
            LocalChannelDevice localChannelDevice = this.mLocalChannelDeviceRef.get();
            IPanelCallback iPanelCallback = this.mPanelCallback;
            if (iPanelCallback != null) {
                iPanelCallback.onComplete(z, obj);
            }
            if (localChannelDevice != null) {
                localChannelDevice.runNextTask();
            }
        }
    }
}
