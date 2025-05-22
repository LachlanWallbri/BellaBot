package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.api.ICADisconnectListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAConnectParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect;
import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProviderListener;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalConnectParams;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalSubMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.alcs.pal.ica.ICAAlcsNative;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAAlcsConnect.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.d */
/* loaded from: classes.dex */
public class C0953d implements IPalConnect {

    /* renamed from: d */
    private static final String f830d = "[AlcsLPBS]ICAAlcsConnect";

    /* renamed from: a */
    protected PalConnectParams f831a;

    /* renamed from: b */
    protected C0952c f832b;

    /* renamed from: c */
    protected PalDeviceInfo f833c;

    public C0953d(C0952c c0952c, PalDeviceInfo palDeviceInfo) {
        this.f832b = c0952c;
        this.f833c = palDeviceInfo;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void startConnect(final PalConnectParams palConnectParams, final PalConnectListener palConnectListener) {
        if (palConnectListener == null) {
            ALog.m480e(f830d, "startConnect listener null");
            return;
        }
        if (palConnectParams == null) {
            ALog.m480e(f830d, "startConnect params null");
            palConnectListener.onLoad(1, null, palConnectParams.deviceInfo);
            return;
        }
        ICADiscoveryDeviceInfo m381a = this.f832b.m381a(palConnectParams.getDevId());
        this.f831a = palConnectParams;
        if (m381a == null) {
            ALog.m480e(f830d, "startConnect discoveryDeviceInfo null params:" + palConnectParams.toString());
            palConnectListener.onLoad(1, null, palConnectParams.deviceInfo);
            return;
        }
        final String str = m381a.addr;
        final int i = m381a.port;
        final String str2 = m381a.pal;
        if (this.f831a.authInfo == null) {
            ALog.m479d(f830d, "authInfo null");
            if (this.f832b.getPalAuthRegister().getProvider() == null) {
                palConnectListener.onLoad(1, null, palConnectParams.deviceInfo);
                return;
            } else {
                this.f832b.getPalAuthRegister().getProvider().queryAuthInfo(palConnectParams.deviceInfo, null, m381a, new IAuthProviderListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.d.1
                    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProviderListener
                    public void onComplete(PalDeviceInfo palDeviceInfo, Object obj) {
                        if (obj == null) {
                            palConnectListener.onLoad(1, null, palConnectParams.deviceInfo);
                            return;
                        }
                        ICAConnectParams iCAConnectParams = new ICAConnectParams(new ICADeviceInfo(palConnectParams.deviceInfo.productModel, palConnectParams.deviceInfo.deviceId), str2, (ICAAuthParams) obj);
                        C0957h c0957h = new C0957h(palConnectListener);
                        if (iCAConnectParams.authInfo == null || TextUtils.isEmpty(iCAConnectParams.authInfo.accessKey) || TextUtils.isEmpty(iCAConnectParams.authInfo.accessToken)) {
                            ALog.m479d(C0953d.f830d, "startConnect params empty");
                            c0957h.onLoad(503, "invalid params", iCAConnectParams.deviceInfo);
                            return;
                        }
                        ALog.m479d(C0953d.f830d, "startConnect params:" + palConnectParams + " listener:" + palConnectListener);
                        ICAAlcsNative.connectDevice(str, i, iCAConnectParams, c0957h);
                    }
                });
                return;
            }
        }
        ICAConnectParams iCAConnectParams = new ICAConnectParams(new ICADeviceInfo(palConnectParams.deviceInfo.productModel, palConnectParams.deviceInfo.deviceId), m381a.pal, (ICAAuthParams) palConnectParams.authInfo);
        C0957h c0957h = new C0957h(palConnectListener);
        if (iCAConnectParams.authInfo == null || TextUtils.isEmpty(iCAConnectParams.authInfo.accessKey) || TextUtils.isEmpty(iCAConnectParams.authInfo.accessToken)) {
            ALog.m479d(f830d, "startConnect params empty");
            c0957h.onLoad(503, "invalid params", iCAConnectParams.deviceInfo);
            return;
        }
        ALog.m479d(f830d, "startConnect params:" + palConnectParams + " listener:" + palConnectListener);
        ICAAlcsNative.connectDevice(str, i, iCAConnectParams, c0957h);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void stopConnect(PalDeviceInfo palDeviceInfo) {
        ALog.m479d(f830d, "stopConnect deviceInfo:" + palDeviceInfo);
        ICAAlcsNative.disConnectDevice(C0962m.m385a(palDeviceInfo));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean asyncSendRequest(PalReqMessage palReqMessage, PalMsgListener palMsgListener) {
        ALog.m479d(f830d, "asyncSendRequest reqMessageInfo:" + palReqMessage + " callback:" + palMsgListener);
        if (palReqMessage == null) {
            ALog.m479d(f830d, "asyncSendRequest error:");
            return false;
        }
        ICAReqMessage m387a = C0962m.m387a(palReqMessage);
        if (m387a.topic != null && m387a.topic.contains("/thing/model/down_raw")) {
            m387a.code = 3;
        }
        return ICAAlcsNative.sendRequest(m387a, new C0960k(palMsgListener));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean subscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener, PalMsgListener palMsgListener2) {
        ALog.m479d(f830d, "subscribe  subMessage:" + palSubMessage + " PalMsgListener:" + palMsgListener + " eventListener:" + palMsgListener2);
        return ICAAlcsNative.subcribe(C0962m.m388a(palSubMessage), new C0960k(palMsgListener), new C0960k(palMsgListener2));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unsubscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener) {
        ALog.m479d(f830d, "unsubcribe reqMessageInfo:" + palSubMessage + " callback:" + palMsgListener);
        return ICAAlcsNative.unsubcribe(C0962m.m388a(palSubMessage), new C0960k(palMsgListener));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean isDeviceConnected(PalDeviceInfo palDeviceInfo) {
        boolean isDeviceOnline = ICAAlcsNative.isDeviceOnline(new ICADeviceInfo(palDeviceInfo.productModel, palDeviceInfo.deviceId));
        ALog.m479d(f830d, "isDeviceConnected deviceInfo:" + isDeviceOnline);
        return isDeviceOnline;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean regDeviceStateListener(final PalDeviceInfo palDeviceInfo, final PalDeviceStateListener palDeviceStateListener) {
        ICAAlcsNative.setDeviceDisconnectListener(new ICADeviceInfo(palDeviceInfo.productModel, palDeviceInfo.deviceId), new ICADisconnectListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.d.2
            @Override // com.aliyun.alink.linksdk.alcs.api.ICADisconnectListener
            public void onDisConnect(ICADeviceInfo iCADeviceInfo) {
                StringBuilder sb = new StringBuilder();
                sb.append("DeviceStatechange icaDeviceInfo:");
                sb.append(iCADeviceInfo == null ? "null" : iCADeviceInfo.toString());
                sb.append(" STATE_DISCONNECTED");
                sb.append(" listener:");
                sb.append(palDeviceStateListener);
                ALog.m479d(C0953d.f830d, sb.toString());
                if (palDeviceStateListener != null || iCADeviceInfo == null) {
                    palDeviceStateListener.onDeviceStateChange(palDeviceInfo, 0);
                }
            }
        });
        return false;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unregDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        ICAAlcsNative.removeDeviceDisconnectListener(new ICADeviceInfo(palDeviceInfo.productModel, palDeviceInfo.deviceId));
        return false;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void onCloudChannelCreate(IThingCloudChannel iThingCloudChannel) {
        ALog.m479d(f830d, "onCloudChannelCreate cloudChannel:" + iThingCloudChannel);
    }
}
