package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.alcs.api.ICAConnectListener;
import com.aliyun.alink.linksdk.alcs.api.ICAMsgListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthServerParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAConnectParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.ica.ICASetupRequestPayload;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0940a;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0943d;
import com.aliyun.alink.linksdk.alcs.pal.ica.ICAAlcsNative;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAProvisionerImpl.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.a.e */
/* loaded from: classes.dex */
public class C0949e implements InterfaceC0948d {

    /* renamed from: d */
    private ICADiscoveryDeviceInfo f820d;

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.InterfaceC0948d
    /* renamed from: a */
    public void mo379a(ICADiscoveryDeviceInfo iCADiscoveryDeviceInfo, ICAConnectListener iCAConnectListener) {
        this.f820d = iCADiscoveryDeviceInfo;
        ICAAlcsNative.connectDevice(iCADiscoveryDeviceInfo.addr, iCADiscoveryDeviceInfo.port, new ICAConnectParams(new ICADeviceInfo(iCADiscoveryDeviceInfo.deviceInfo.productKey, iCADiscoveryDeviceInfo.deviceInfo.deviceName), iCADiscoveryDeviceInfo.pal, C0946b.m375a("Xtau@iot", "Yx3DdsyetbSezlvc", "0")), iCAConnectListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.InterfaceC0948d
    /* renamed from: a */
    public void mo378a() {
        ICAAlcsNative.disConnectDevice(this.f820d.deviceInfo);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.InterfaceC0948d
    /* renamed from: a */
    public boolean mo380a(ICAAuthServerParams iCAAuthServerParams, ICAMsgListener iCAMsgListener) {
        ICAReqMessage iCAReqMessage = new ICAReqMessage();
        ICASetupRequestPayload iCASetupRequestPayload = new ICASetupRequestPayload();
        ICASetupRequestPayload.ICASetupData iCASetupData = new ICASetupRequestPayload.ICASetupData();
        ICASetupRequestPayload.ICAProvisionAuthData iCAProvisionAuthData = new ICASetupRequestPayload.ICAProvisionAuthData();
        iCAProvisionAuthData.authCode = iCAAuthServerParams.authCode;
        iCAProvisionAuthData.authSecret = iCAAuthServerParams.authSecret;
        iCAProvisionAuthData.productKey = this.f820d.deviceInfo.productKey;
        iCAProvisionAuthData.deviceName = this.f820d.deviceInfo.deviceName;
        iCASetupData.configValue.add(iCAProvisionAuthData);
        iCASetupData.configType = "ServerAuthInfo";
        iCASetupRequestPayload.params = iCASetupData;
        iCASetupRequestPayload.f865id = String.valueOf(C0940a.m363a());
        iCASetupRequestPayload.method = "core.service.setup";
        iCASetupRequestPayload.version = "1.0";
        String jSONString = JSONObject.toJSONString(iCASetupRequestPayload);
        iCAReqMessage.deviceInfo = this.f820d.deviceInfo;
        iCAReqMessage.topic = C0943d.m369a(this.f820d.deviceInfo.productKey, this.f820d.deviceInfo.deviceName, C0943d.f793a, "core.service.setup");
        iCAReqMessage.payload = jSONString.getBytes();
        iCAReqMessage.type = 0;
        iCAReqMessage.code = 3;
        return ICAAlcsNative.sendRequest(iCAReqMessage, iCAMsgListener);
    }
}
