package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.CommonResource;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsServerConnectOption;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResource;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectDiscovery;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.connect.IConnect;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpSyncRequestHandler;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonRequestPayload;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.resource.ITResRequestInnerHandler;
import com.aliyun.alink.linksdk.tmp.resource.TResPublicWrapper;
import com.aliyun.alink.linksdk.tmp.resource.TResRequestListener;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CmpConnect extends ConnectWrapper {
    protected static final String TAG = "[Tmp]CmpConnect";
    protected String mCoapConnId;
    protected String mConnectId;
    protected String mMqttConnId;
    protected Map<String, AResource> mResData;

    public CmpConnect(String str) {
        this.mConnectId = str;
        this.mResData = new HashMap();
        ConnectSDK.getInstance().registerNofityListener(this.mConnectId, CmpNotifyManager.getInstance());
    }

    public CmpConnect(String str, String str2) {
        this.mMqttConnId = str;
        this.mCoapConnId = str2;
        if (TextUtils.isEmpty(str2)) {
            this.mConnectId = str;
        } else if (TextUtils.isEmpty(str)) {
            this.mConnectId = str2;
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public String getConnectId(IConnect.ConnectType connectType) {
        if (IConnect.ConnectType.CONNECT_TYPE_COAP == connectType) {
            return this.mCoapConnId;
        }
        if (IConnect.ConnectType.CONNECT_TYPE_MQTT == connectType) {
            return this.mMqttConnId;
        }
        return this.mConnectId;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean send(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler) {
        ConnectSDK.getInstance().send(this.mConnectId, (ARequest) tmpCommonRequest.getRequest(), new CpConnectSendHandler(tmpCommonRequest, iRequestHandler));
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean subscribe(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler, INotifyHandler iNotifyHandler) {
        ALog.m479d(TAG, "subscribe request:" + tmpCommonRequest);
        registerNofityListener(tmpCommonRequest, new CpNotifyHandler(this.mConnectId, tmpCommonRequest, iNotifyHandler));
        ConnectSDK.getInstance().subscribe(this.mConnectId, (ARequest) tmpCommonRequest.getRequest(), new CpSubEventHandler(tmpCommonRequest, new TmpSyncRequestHandler(iRequestHandler, tmpCommonRequest)));
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean unsubscribe(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler) {
        ALog.m479d(TAG, "unsubscribe request:" + tmpCommonRequest);
        ConnectSDK.getInstance().unsubscribe(this.mConnectId, (ARequest) tmpCommonRequest.getRequest(), new CpUnsubEventHandler(tmpCommonRequest, new TmpSyncRequestHandler(iRequestHandler, tmpCommonRequest)));
        unregisterNofityListener(tmpCommonRequest);
        return true;
    }

    public boolean registerNofityListener(TmpCommonRequest tmpCommonRequest, CpNotifyHandler cpNotifyHandler) {
        ALog.m479d(TAG, "registerNofityListener request:" + tmpCommonRequest);
        if (cpNotifyHandler == null || tmpCommonRequest == null || TextUtils.isEmpty(tmpCommonRequest.getTopic())) {
            ALog.m484w(TAG, "registerNofityListener null");
            return false;
        }
        CmpNotifyManager.getInstance().addHandler(hashCode(), this.mConnectId, tmpCommonRequest.getTopic(), cpNotifyHandler);
        return true;
    }

    public boolean unregisterNofityListener(TmpCommonRequest tmpCommonRequest) {
        ALog.m479d(TAG, "unregisterNofityListener request:" + tmpCommonRequest);
        if (tmpCommonRequest == null || TextUtils.isEmpty(tmpCommonRequest.getTopic())) {
            ALog.m484w(TAG, "unregisterNofityListener null");
            return false;
        }
        CmpNotifyManager.getInstance().removeHandler(hashCode(), this.mConnectId, tmpCommonRequest.getTopic());
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean resourcePublish(String str, String str2, String str3, OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        CommonResource commonResource = new CommonResource();
        commonResource.topic = str3;
        CommonRequestPayload commonRequestPayload = new CommonRequestPayload(null, null);
        commonRequestPayload.setMethod(str2);
        commonRequestPayload.setVersion("1.0");
        commonRequestPayload.setParams(outputParams);
        String json = GsonUtils.toJson(commonRequestPayload);
        if (!TextUtils.isEmpty(json)) {
            commonResource.payload = json.getBytes();
        }
        ConnectSDK.getInstance().publishResource(commonResource, new TResPublicWrapper(str, iPublishResourceListener));
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean rawResourcePublish(String str, String str2, byte[] bArr, IPublishResourceListener iPublishResourceListener) {
        CommonResource commonResource = new CommonResource();
        commonResource.topic = str2;
        commonResource.payload = bArr;
        ConnectSDK.getInstance().publishResource(commonResource, new TResPublicWrapper(str, iPublishResourceListener));
        return true;
    }

    public boolean regTopic(String str, String str2, boolean z, ITResRequestInnerHandler iTResRequestInnerHandler) {
        ALog.m479d(TAG, "regTopic identifier:" + str + " topic:" + str2);
        CommonResource commonResource = new CommonResource();
        commonResource.topic = str2;
        commonResource.isNeedAuth = z;
        this.mResData.put(str, commonResource);
        ConnectSDK.getInstance().registerResource(commonResource, new TResRequestListener(str, iTResRequestInnerHandler));
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean regTopic(String str, String str2, String str3, boolean z, ITResRequestInnerHandler iTResRequestInnerHandler) {
        if (TextUtils.isEmpty(str)) {
            return regTopic(str2, str3, z, iTResRequestInnerHandler);
        }
        ALog.m479d(TAG, "regTopic connectId:" + str + " identifier:" + str2 + " topic:" + str3);
        IConnectResourceRegister connectResourceRegister = ConnectSDK.getInstance().getConnectResourceRegister(str);
        if (connectResourceRegister == null) {
            ALog.m479d(TAG, "regTopic faile getConnectResourceRegister null mConnectId:" + this.mConnectId);
            return false;
        }
        CommonResource commonResource = new CommonResource();
        commonResource.topic = str3;
        commonResource.isNeedAuth = z;
        this.mResData.put(str2, commonResource);
        connectResourceRegister.registerResource(commonResource, new TResRequestListener(str2, iTResRequestInnerHandler));
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean unRegTopic(String str, String str2) {
        ConnectSDK.getInstance().getConnectResourceRegister(this.mConnectId).unregisterResource(this.mResData.remove(str), null);
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean reDiscoveryDevice(TmpCommonRequest tmpCommonRequest, IRequestHandler iRequestHandler) {
        try {
            ConnectSDK.getInstance().getConnectDiscovery(this.mConnectId).discoveryCertainDevice((ARequest) tmpCommonRequest.getRequest(), new CmpReDiscoveryHandler(iRequestHandler, tmpCommonRequest));
            return true;
        } catch (Throwable th) {
            ALog.m484w(TAG, "reDiscoveryDevice t:" + th.toString());
            return true;
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean startDiscovery(int i, INotifyHandler iNotifyHandler) {
        IConnectDiscovery connectDiscovery = ConnectSDK.getInstance().getConnectDiscovery(this.mConnectId);
        if (connectDiscovery == null) {
            return true;
        }
        connectDiscovery.startDiscovery(i, new CpDiscoveryHandler(iNotifyHandler));
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean stopDiscovery() {
        IConnectDiscovery connectDiscovery = ConnectSDK.getInstance().getConnectDiscovery(this.mConnectId);
        if (connectDiscovery == null) {
            return true;
        }
        connectDiscovery.stopDiscovery();
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean startNotifyMonitor(INotifyHandler iNotifyHandler) {
        try {
            ConnectSDK.getInstance().getConnectDiscovery(this.mConnectId).startNotifyMonitor(new CpDiscoveryHandler(iNotifyHandler));
            return true;
        } catch (Throwable th) {
            ALog.m484w(TAG, "startNotifyMonitor t:" + th.toString());
            return true;
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean stopNotifyMonitor() {
        try {
            ConnectSDK.getInstance().getConnectDiscovery(this.mConnectId).stopNotifyMonitor();
            return true;
        } catch (Throwable th) {
            ALog.m484w(TAG, "stopNotifyMonitor error:" + th.toString());
            return true;
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper, com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean setOption(String str, int i, Object obj) {
        ALog.m479d(TAG, "setOption option:" + i + " value:" + obj);
        if (obj == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.mCoapConnId;
        }
        if (i == 1) {
            if (!(obj instanceof String)) {
                return false;
            }
            AlcsServerConnectOption alcsServerConnectOption = new AlcsServerConnectOption();
            alcsServerConnectOption.setPrefix((String) obj);
            ConnectSDK.getInstance().updateConnectOption(this.mConnectId, alcsServerConnectOption);
            return false;
        }
        if (i == 2) {
            if (!(obj instanceof String)) {
                return false;
            }
            AlcsServerConnectOption alcsServerConnectOption2 = new AlcsServerConnectOption();
            alcsServerConnectOption2.setBlackClients((String) obj);
            ConnectSDK.getInstance().updateConnectOption(this.mConnectId, alcsServerConnectOption2);
            return false;
        }
        return super.setOption(str, i, obj);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public TmpEnum.DeviceState getConnectState() {
        ConnectState connectState = ConnectSDK.getInstance().getConnectState(this.mConnectId);
        ALog.m479d(TAG, "getConnectState mConnectId:" + this.mConnectId + " connectState:" + connectState);
        return TmpEnum.DeviceState.createConnectState(connectState);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean addConnectChangeListener(IDevStateChangeListener iDevStateChangeListener) {
        CmpNotifyManager.getInstance().addConnectStateChangeListener(this.mConnectId, iDevStateChangeListener);
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean removeConnectChangeListener(IDevStateChangeListener iDevStateChangeListener) {
        CmpNotifyManager.getInstance().removeConnectStateChangeListener(this.mConnectId, iDevStateChangeListener);
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean stopConnect() {
        ConnectSDK.getInstance().destoryConnect(this.mConnectId);
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean unInit() {
        ALog.m479d(TAG, "unInit mConnectId:" + this.mConnectId);
        this.mResData.clear();
        ConnectSDK.getInstance().unregisterConnect(this.mConnectId);
        return true;
    }
}
