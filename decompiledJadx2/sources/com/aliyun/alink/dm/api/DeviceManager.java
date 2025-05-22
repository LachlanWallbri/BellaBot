package com.aliyun.alink.dm.api;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.dm.p006a.C0838a;
import com.aliyun.alink.dm.p007b.C0846a;
import com.aliyun.alink.dm.p008c.C0847a;
import com.aliyun.alink.dm.p011e.C0853a;
import com.aliyun.alink.dm.p012f.C0854a;
import com.aliyun.alink.dm.p013g.C0855a;
import com.aliyun.alink.dm.p014h.C0856a;
import com.aliyun.alink.dm.p015i.C0857a;
import com.aliyun.alink.dm.p016j.C0858a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.dm.p018l.C0861b;
import com.aliyun.alink.dm.p018l.InterfaceC0860a;
import com.aliyun.alink.dm.p019m.C0864b;
import com.aliyun.alink.dm.p020n.C0865a;
import com.aliyun.alink.dm.p021o.C0870e;
import com.aliyun.alink.dm.shadow.C0871a;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentNet;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayConnectState;
import com.aliyun.alink.linksdk.channel.gateway.api.IGatewayConnectListener;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.manager.connect.IRegisterConnectListener;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.api.TmpInitConfig;
import com.aliyun.alink.linksdk.tools.AError;
import com.pudutech.mirsdk.SolicitService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceManager {
    private static final String TAG = "DeviceManager";
    private IConnectNotifyListener dmConnectNotifyListener;
    private AtomicBoolean initHasSendCallback;
    private AtomicBoolean isDMInited;
    private AtomicBoolean isDMIniting;
    private AtomicBoolean isNeedResetDevice;
    private AtomicBoolean isResetDeviceDone;
    private IApiClient mApiClient;
    private IDeviceCOTA mCOTA;
    private Context mContext;
    private IDMCallback<InitResult> mDMCallback;
    private DeviceInfo mDeviceInfo;
    private DMConfigParams mDmConfigParams;
    private IGateway mGateway;
    private IDeviceLabel mLabel;
    private C0864b mOta;
    private IDeviceShadow mShadow;
    private IThing mThing;
    private InterfaceC0860a mToken;
    private IRegisterConnectListener registerConnectListener;

    public String getSdkVersion() {
        return "1.7.0-6b42aa0";
    }

    private DeviceManager() {
        this.isDMInited = new AtomicBoolean(false);
        this.isDMIniting = new AtomicBoolean(false);
        this.isNeedResetDevice = new AtomicBoolean(false);
        this.isResetDeviceDone = new AtomicBoolean(true);
        this.initHasSendCallback = new AtomicBoolean(false);
        this.mDeviceInfo = null;
        this.mLabel = null;
        this.mCOTA = null;
        this.mShadow = null;
        this.mGateway = null;
        this.mThing = null;
        this.mApiClient = null;
        this.mToken = null;
        this.mDmConfigParams = null;
        this.mContext = null;
        this.mDMCallback = null;
        this.registerConnectListener = new IRegisterConnectListener() { // from class: com.aliyun.alink.dm.api.DeviceManager.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onSuccess() {
                C0859a.m131a(DeviceManager.TAG, "success hasReturned=" + DeviceManager.this.initHasSendCallback.get());
                if (DeviceManager.this.mDmConfigParams == null || !DeviceManager.this.mDmConfigParams.enableThingModel) {
                    DeviceManager.this.isDMIniting.set(false);
                    DeviceManager.this.isDMInited.set(true);
                    if (DeviceManager.this.mDMCallback != null && DeviceManager.this.initHasSendCallback.compareAndSet(false, true)) {
                        DeviceManager.this.mDMCallback.onSuccess(null);
                    }
                } else {
                    DeviceManager.this.isDMIniting.set(true);
                    DeviceManager.this.isDMInited.set(false);
                    DeviceManager deviceManager = DeviceManager.this;
                    deviceManager.initDM(deviceManager.mContext, DeviceManager.this.mDmConfigParams, DeviceManager.this.mDMCallback);
                }
                DeviceManager deviceManager2 = DeviceManager.this;
                deviceManager2.notifyOnConnected(deviceManager2.mContext, DeviceManager.this.mDmConfigParams);
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onFailure(AError aError) {
                C0859a.m135d(DeviceManager.TAG, "onFailure " + C0847a.m89a().m96b(aError) + ", hasReturned=" + DeviceManager.this.initHasSendCallback.get());
                DeviceManager.this.isDMIniting.set(false);
                DeviceManager.this.isDMInited.set(false);
                if (DeviceManager.this.mDMCallback == null || !DeviceManager.this.initHasSendCallback.compareAndSet(false, true)) {
                    return;
                }
                DeviceManager.this.mDMCallback.onFailure(C0856a.m127a(aError));
            }
        };
        this.dmConnectNotifyListener = new IConnectNotifyListener() { // from class: com.aliyun.alink.dm.api.DeviceManager.3
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
            public void onNotify(String str, String str2, AMessage aMessage) {
                C0859a.m131a(DeviceManager.TAG, "onNotify() called with: connectId = [" + str + "], topic = [" + str2 + "], aMessage = [" + aMessage + "]");
                C0855a.m123c().onNotify(str, str2, aMessage);
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
            public boolean shouldHandle(String str, String str2) {
                C0859a.m131a(DeviceManager.TAG, "shouldHandle() called with: connectId = [" + str + "], topic = [" + str2 + "]");
                return C0855a.m123c().shouldHandle(str, str2);
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
            public void onConnectStateChange(String str, ConnectState connectState) {
                C0859a.m131a(DeviceManager.TAG, "onConnectStateChange() called with: s = [" + str + "], connectState = [" + connectState + "]");
                if (ConnectSDK.getInstance().getPersistentConnectId().equals(str) && (connectState == ConnectState.CONNECTFAIL || connectState == ConnectState.DISCONNECTED)) {
                    C0859a.m131a(DeviceManager.TAG, "isIniting=" + DeviceManager.this.isDMIniting.get() + ",inited=" + DeviceManager.this.isDMInited.get() + ", hassendCallback=" + DeviceManager.this.initHasSendCallback.get());
                    if (DeviceManager.this.isDMIniting.get() && !DeviceManager.this.isDMInited.get() && DeviceManager.this.registerConnectListener != null) {
                        AError aError = new AError();
                        aError.setCode(1101312);
                        aError.setMsg("mqtt disconnected before init done.");
                        DeviceManager.this.registerConnectListener.onFailure(aError);
                    }
                }
                C0855a.m123c().onConnectStateChange(str, connectState);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class SingletonHolder {
        private static final DeviceManager INSTANCE = new DeviceManager();

        private SingletonHolder() {
        }
    }

    public static DeviceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Context context, DMConfigParams dMConfigParams, IDMCallback<InitResult> iDMCallback) {
        C0859a.m133b(TAG, "init() called with: version=1.7.0-6b42aa0 context = [" + context + "], params = [" + dMConfigParams + "], listener = [" + iDMCallback + "]");
        if (iDMCallback == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if (dMConfigParams == null || context == null) {
            iDMCallback.onFailure(DMErrorCode.getErrorCode(DMErrorCode.ERROR_PARAMS_ERROR, DMErrorCode.DM_INIT_PARAMS_INVALID, "DM init params null."));
            return;
        }
        if (this.isDMIniting.get() || this.isDMInited.get()) {
            C0859a.m134c(TAG, "DM inited or initing, return.");
            iDMCallback.onFailure(DMErrorCode.getErrorCode(DMErrorCode.ERROR_DUPLICATE_SDK_INIT, 100, "DM SDK is initing or inited."));
            return;
        }
        this.isDMIniting.set(true);
        this.isDMInited.set(false);
        this.isNeedResetDevice.set(false);
        this.initHasSendCallback.set(false);
        this.mDmConfigParams = dMConfigParams;
        this.mContext = context.getApplicationContext();
        this.mDMCallback = iDMCallback;
        this.mDeviceInfo = dMConfigParams.deviceInfo;
        C0854a.m116a().m118a(context, this.mDeviceInfo);
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getPersistentConnectId(), this.dmConnectNotifyListener);
        ConnectSDK.getInstance().registerPersistentConnect(context, dMConfigParams.persistentConnectConfig, this.registerConnectListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDM(Context context, DMConfigParams dMConfigParams, final IDMCallback<InitResult> iDMCallback) {
        C0859a.m131a(TAG, "init() called with: context = [" + context + "], params = [" + dMConfigParams + "], callback = [" + iDMCallback + "]");
        try {
            TmpInitConfig tmpInitConfig = new TmpInitConfig();
            tmpInitConfig.mIcaPalDeviceInfo = new PalDeviceInfo(dMConfigParams.deviceInfo.productKey, dMConfigParams.deviceInfo.deviceName);
            TmpSdk.init(context, tmpInitConfig);
            IThing deviceThing = getDeviceThing();
            if (deviceThing instanceof C0865a) {
                ((C0865a) deviceThing).m180a(dMConfigParams.tsl, this.mDeviceInfo, dMConfigParams.propertyValues, new IDMCallback<InitResult>() { // from class: com.aliyun.alink.dm.api.DeviceManager.2
                    @Override // com.aliyun.alink.dm.api.IDMCallback
                    public void onSuccess(InitResult initResult) {
                        C0859a.m131a(DeviceManager.TAG, "init onSuccess result=" + initResult + ", hasReturned=" + DeviceManager.this.initHasSendCallback.get());
                        DeviceManager.this.isDMInited.set(true);
                        DeviceManager.this.isDMIniting.set(false);
                        if (iDMCallback == null || !DeviceManager.this.initHasSendCallback.compareAndSet(false, true)) {
                            return;
                        }
                        iDMCallback.onSuccess(initResult);
                    }

                    @Override // com.aliyun.alink.dm.api.IDMCallback
                    public void onFailure(AError aError) {
                        C0859a.m131a(DeviceManager.TAG, "init onFailure() called with: error = [" + C0847a.m89a().m96b(aError) + "]");
                        DeviceManager.this.isDMInited.set(false);
                        DeviceManager.this.isDMIniting.set(false);
                        if (iDMCallback == null || !DeviceManager.this.initHasSendCallback.compareAndSet(false, true)) {
                            return;
                        }
                        iDMCallback.onFailure(aError);
                    }
                });
            }
        } catch (Exception e) {
            C0859a.m132a(TAG, "initDM->TMP ", e);
            this.isDMIniting.set(false);
            this.isDMInited.set(false);
            if (iDMCallback == null || !this.initHasSendCallback.compareAndSet(false, true)) {
                return;
            }
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_TMP_INIT);
            aError.setMsg("tmp init exception=" + e);
            iDMCallback.onFailure(aError);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOnConnected(Context context, DMConfigParams dMConfigParams) {
        C0859a.m133b(TAG, "notifyOnConnected called.");
        if (dMConfigParams != null) {
            try {
                if (dMConfigParams.enableGateway) {
                    GatewayChannel.getInstance().startConnect(context, dMConfigParams.persistentConnectConfig, new IGatewayConnectListener() { // from class: com.aliyun.alink.dm.api.DeviceManager.4
                        @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayConnectListener
                        public void onConnectStateChange(GatewayConnectState gatewayConnectState) {
                            C0859a.m131a(DeviceManager.TAG, "onConnectStateChange() called with: gatewayConnectState = [" + gatewayConnectState + "]");
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            IApiClient ioTApiClient = getIoTApiClient();
            if ((ioTApiClient instanceof C0838a) && dMConfigParams != null) {
                ioTApiClient.init(context, dMConfigParams.connectConfig, this.mDeviceInfo);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if ("1".equals(C0854a.m116a().m117a("reset-" + this.mDeviceInfo.productKey + "-" + this.mDeviceInfo.deviceName))) {
                this.isNeedResetDevice.set(true);
                this.isResetDeviceDone.set(false);
                resetAndReport(dMConfigParams);
                return;
            }
            reportToken(dMConfigParams);
        } catch (Exception e3) {
            e3.printStackTrace();
            C0859a.m134c(TAG, "notifyOnConnected exception=" + e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAndReport(final DMConfigParams dMConfigParams) {
        C0859a.m133b(TAG, "resetAndReport() called isResetDeviceDone=" + this.isResetDeviceDone.get());
        if (this.isResetDeviceDone.get()) {
            C0859a.m131a(TAG, "reset done!");
        } else {
            resetDevice(new IConnectSendListener() { // from class: com.aliyun.alink.dm.api.DeviceManager.5
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    DeviceManager.this.reportToken(dMConfigParams);
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    try {
                        Thread.sleep(SolicitService.CAMERA_OPEN_TIME_OUT);
                    } catch (Exception unused) {
                    }
                    DeviceManager.this.resetAndReport(dMConfigParams);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportToken(DMConfigParams dMConfigParams) {
        C0859a.m131a(TAG, "report token called.");
        if (dMConfigParams != null) {
            try {
                if (dMConfigParams.enableLocalCommunication) {
                    getTokenBiz().mo136a(dMConfigParams);
                    getTokenBiz().mo137a(new IConnectSendListener() { // from class: com.aliyun.alink.dm.api.DeviceManager.6
                        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                        public void onResponse(ARequest aRequest, AResponse aResponse) {
                        }

                        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                        public void onFailure(ARequest aRequest, AError aError) {
                            C0859a.m135d(DeviceManager.TAG, "onFailure token post failed.");
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                C0859a.m134c(TAG, "report token and notify failed.");
            }
        }
    }

    public void registerOnPushListener(IConnectNotifyListener iConnectNotifyListener) {
        C0859a.m133b(TAG, "registerOnPushListener() called with: listener = [" + iConnectNotifyListener + "]");
        C0855a.m123c().m124a(iConnectNotifyListener);
    }

    public void unRegisterOnPushListener(IConnectNotifyListener iConnectNotifyListener) {
        C0859a.m133b(TAG, "unRegisterOnPushListener called.");
        C0855a.m123c().m125b(iConnectNotifyListener);
    }

    public IOta getOta() {
        C0864b c0864b;
        synchronized (this) {
            if (this.mOta == null) {
                this.mOta = new C0864b();
            }
            c0864b = this.mOta;
        }
        return c0864b;
    }

    public IDeviceLabel getDeviceLabel() {
        IDeviceLabel iDeviceLabel;
        if (this.mDeviceInfo == null) {
            C0859a.m134c(TAG, "SDK device info not initialized, return null.");
            return null;
        }
        synchronized (this) {
            if (this.mLabel == null) {
                this.mLabel = new C0858a(this.mDeviceInfo);
            }
            iDeviceLabel = this.mLabel;
        }
        return iDeviceLabel;
    }

    public IDeviceShadow getDeviceShadow() {
        IDeviceShadow iDeviceShadow;
        if (this.mDeviceInfo == null) {
            C0859a.m134c(TAG, "SDK device info not initialized, return null.");
            return null;
        }
        synchronized (this) {
            if (this.mShadow == null) {
                this.mShadow = new C0871a(this.mDeviceInfo);
            }
            iDeviceShadow = this.mShadow;
        }
        return iDeviceShadow;
    }

    public IGateway getGateway() {
        IGateway iGateway;
        if (this.mDeviceInfo == null) {
            C0859a.m134c(TAG, "SDK device info not initialized, return null.");
            return null;
        }
        DMConfigParams dMConfigParams = this.mDmConfigParams;
        if (dMConfigParams == null) {
            C0859a.m134c(TAG, "gateway not inited.");
            return null;
        }
        if (!dMConfigParams.enableGateway) {
            C0859a.m134c(TAG, "enableGateway=false, gateway not enabled");
            return null;
        }
        synchronized (this) {
            if (this.mGateway == null) {
                this.mGateway = new C0857a();
            }
            iGateway = this.mGateway;
        }
        return iGateway;
    }

    public IDeviceCOTA getDeviceCOTA() {
        IDeviceCOTA iDeviceCOTA;
        if (this.mDeviceInfo == null) {
            C0859a.m134c(TAG, "SDK device info not initialized, return null.");
            return null;
        }
        synchronized (this) {
            if (this.mCOTA == null) {
                this.mCOTA = new C0853a(this.mDeviceInfo);
            }
            iDeviceCOTA = this.mCOTA;
        }
        return iDeviceCOTA;
    }

    public IThing getDeviceThing() {
        IThing iThing;
        if (this.mDeviceInfo == null) {
            C0859a.m134c(TAG, "SDK device info not initialized, return null.");
            return null;
        }
        DMConfigParams dMConfigParams = this.mDmConfigParams;
        if (dMConfigParams == null) {
            C0859a.m134c(TAG, "thing model not inited.");
            return null;
        }
        if (!dMConfigParams.enableThingModel) {
            C0859a.m134c(TAG, "enableThingModel=false, thing model not enabled.");
            return null;
        }
        synchronized (this) {
            if (this.mThing == null) {
                this.mThing = new C0865a(this.mDeviceInfo, this.mDmConfigParams.enableLocalCommunication);
            }
            iThing = this.mThing;
        }
        return iThing;
    }

    public IApiClient getIoTApiClient() {
        IApiClient iApiClient;
        synchronized (this) {
            if (this.mApiClient == null) {
                this.mApiClient = new C0838a();
            }
            iApiClient = this.mApiClient;
        }
        return iApiClient;
    }

    public String generateBindToken(String str) {
        String m190a = C0870e.m190a(str);
        C0859a.m133b(TAG, "generateBindToken genToken=" + m190a);
        return m190a;
    }

    public void resetDevice(final IConnectSendListener iConnectSendListener) {
        C0859a.m131a(TAG, "resetDevice() called with: callback = [" + iConnectSendListener + "]");
        this.isResetDeviceDone.set(false);
        this.isNeedResetDevice.set(true);
        InterfaceC0860a interfaceC0860a = this.mToken;
        if (interfaceC0860a instanceof C0861b) {
            ((C0861b) interfaceC0860a).m148a(false);
        }
        DeviceInfo deviceInfo = this.mDeviceInfo;
        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.productKey) && !TextUtils.isEmpty(this.mDeviceInfo.deviceName)) {
            new C0846a().m87a(this.mDeviceInfo.productKey, this.mDeviceInfo.deviceName, new IConnectSendListener() { // from class: com.aliyun.alink.dm.api.DeviceManager.7
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    DeviceManager.this.isResetDeviceDone.set(true);
                    DeviceManager.this.isNeedResetDevice.set(false);
                    IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                    if (iConnectSendListener2 != null) {
                        iConnectSendListener2.onResponse(aRequest, aResponse);
                    }
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    DeviceManager.this.isResetDeviceDone.set(false);
                    IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                    if (iConnectSendListener2 != null) {
                        iConnectSendListener2.onFailure(aRequest, aError);
                    }
                }
            });
            return;
        }
        this.isResetDeviceDone.set(true);
        if (iConnectSendListener != null) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_DM_RESET_FAILED);
            aError.setMsg("device info invalid, info=" + this.mDeviceInfo);
            iConnectSendListener.onFailure(null, aError);
        }
    }

    public void destroy() {
        this.isDMInited.set(false);
        this.isDMIniting.set(false);
        this.isNeedResetDevice.set(false);
        this.isResetDeviceDone.set(true);
        this.initHasSendCallback.set(true);
        try {
            if (getDeviceThing() != null) {
                getDeviceThing().uninit();
            }
        } catch (Exception e) {
            C0859a.m134c(TAG, "destroy-> deviceThing uninit exec=" + e);
        }
        boolean z = !ConnectSDK.getInstance().isConnectRegisted(ConnectSDK.getInstance().getPersistentConnectId());
        try {
            ConnectSDK.getInstance().unregisterConnect(ConnectSDK.getInstance().getPersistentConnectId());
        } catch (Exception e2) {
            C0859a.m134c(TAG, "destroy-> unregisterConnect exec=" + e2);
        }
        try {
            ConnectSDK.getInstance().unregisterNofityListener(this.dmConnectNotifyListener);
        } catch (Exception e3) {
            C0859a.m134c(TAG, "destroy-> unregisterNofityListener exec=" + e3);
        }
        if (z) {
            C0859a.m131a(TAG, "needForceDestroyMqtt = true");
            try {
                PersistentNet.getInstance().destroy();
            } catch (Exception unused) {
            }
        }
        try {
            if (getGateway() != null && (getGateway() instanceof C0857a)) {
                ((C0857a) getGateway()).m130a();
            }
        } catch (Exception e4) {
            C0859a.m134c(TAG, "destroy-> gateway destroy exec=" + e4);
        }
        try {
            if (getIoTApiClient() instanceof C0838a) {
                ((C0838a) getIoTApiClient()).m86a();
            }
        } catch (Exception e5) {
            C0859a.m134c(TAG, "destroy-> apiclient deinit exec=" + e5);
        }
        try {
            if (this.mToken instanceof C0861b) {
                ((C0861b) this.mToken).m147a();
            }
        } catch (Exception e6) {
            C0859a.m134c(TAG, "destroy-> stopNotify exec=" + e6);
        }
        this.mDMCallback = null;
        this.mContext = null;
        this.mDmConfigParams = null;
        this.mLabel = null;
        this.mOta = null;
        this.mShadow = null;
        this.mThing = null;
        this.mGateway = null;
        this.mCOTA = null;
        this.mToken = null;
    }

    private InterfaceC0860a getTokenBiz() {
        InterfaceC0860a interfaceC0860a;
        synchronized (this) {
            if (this.mToken == null) {
                this.mToken = new C0861b();
            }
            interfaceC0860a = this.mToken;
        }
        return interfaceC0860a;
    }
}
