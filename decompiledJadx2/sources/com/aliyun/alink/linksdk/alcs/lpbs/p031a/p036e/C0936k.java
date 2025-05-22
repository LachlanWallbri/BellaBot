package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalProbe;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalConnectParams;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalProbeResult;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalSubMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p032a.C0921a;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p033b.C0923a;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: PkDnConvertLayer.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.k */
/* loaded from: classes.dex */
public class C0936k extends AbstractC0930e {

    /* renamed from: a */
    private static final String f780a = "[AlcsLPBS]PkDnConvertLayer";

    /* renamed from: b */
    private C0923a f781b;

    /* renamed from: c */
    private C0921a f782c;

    public C0936k(C0923a c0923a, C0921a c0921a, AbstractC0930e abstractC0930e) {
        super(abstractC0930e);
        this.f781b = c0923a;
        this.f782c = c0921a;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startDiscovery(int i, PalDiscoveryListener palDiscoveryListener) {
        for (Map.Entry<String, IPlugin> entry : PluginMgr.getInstance().getPluginList().entrySet()) {
            if (entry.getValue() != null && entry.getValue().getPalBridge() != null && entry.getValue().getPalBridge().getPalDiscovery() != null) {
                entry.getValue().getPalBridge().getPalDiscovery().startDiscovery(i, new C0929d(entry.getValue().getPluginId(), palDiscoveryListener));
            }
        }
        return true;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalProbe
    public void probeDevice(PalDeviceInfo palDeviceInfo, PalProbeListener palProbeListener) {
        IPlugin pluginByDevId = PluginMgr.getInstance().getPluginByDevId(palDeviceInfo.getDevId());
        if (pluginByDevId == null) {
            palProbeListener.onComplete(palDeviceInfo, new PalProbeResult(2));
            ALog.m480e(f780a, "startConnect error plugin not found");
            return;
        }
        PalDeviceInfo privatePkDn = PluginMgr.getInstance().toPrivatePkDn(palDeviceInfo);
        try {
            IPalProbe palProbe = pluginByDevId.getPalBridge().getPalProbe();
            if (palProbe != null) {
                palProbe.probeDevice(privatePkDn, new C0935j(palProbeListener));
            }
        } catch (AbstractMethodError e) {
            ALog.m484w(f780a, e.toString());
        } catch (Exception e2) {
            ALog.m484w(f780a, e2.toString());
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startNotifyMonitor(PalDiscoveryListener palDiscoveryListener) {
        for (Map.Entry<String, IPlugin> entry : PluginMgr.getInstance().getPluginList().entrySet()) {
            if (entry.getValue() != null && entry.getValue().getPalBridge() != null && entry.getValue().getPalBridge().getPalDiscovery() != null) {
                try {
                    entry.getValue().getPalBridge().getPalDiscovery().startNotifyMonitor(new C0929d(entry.getValue().getPluginId(), palDiscoveryListener));
                } catch (Throwable th) {
                    ALog.m480e(f780a, "startNotifyMonitor Throwable:" + th.toString());
                }
            }
        }
        return true;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void startConnect(PalConnectParams palConnectParams, PalConnectListener palConnectListener) {
        ALog.m479d(f780a, "params:" + palConnectParams + " listener:" + palConnectListener);
        if (palConnectParams == null || palConnectParams.deviceInfo == null || palConnectListener == null) {
            ALog.m480e(f780a, "startConnect params null");
            return;
        }
        IPlugin pluginByDevId = PluginMgr.getInstance().getPluginByDevId(palConnectParams.getDevId());
        if (pluginByDevId == null) {
            palConnectListener.onLoad(1, null, palConnectParams.deviceInfo);
            ALog.m480e(f780a, "startConnect error plugin not found");
            return;
        }
        PalDeviceInfo privatePkDn = PluginMgr.getInstance().toPrivatePkDn(palConnectParams.deviceInfo);
        PalDeviceInfo palDeviceInfo = palConnectParams.deviceInfo;
        IPalConnect palConnect = pluginByDevId.getPalBridge().getPalConnect(privatePkDn);
        if (palConnect == null) {
            palConnectListener.onLoad(1, null, palConnectParams.deviceInfo);
            ALog.m480e(f780a, "startConnect error connect not found");
            return;
        }
        this.f781b.m347a(palDeviceInfo.getDevId(), palConnect);
        palConnectParams.deviceInfo = PluginMgr.getInstance().toPrivatePkDn(palConnectParams.deviceInfo);
        ALog.m479d(f780a, "startConnect params:" + palConnectParams + " devid:" + palConnectParams.deviceInfo.getDevId());
        palConnect.startConnect(palConnectParams, new C0934i(new C0926a(palDeviceInfo, palConnectListener, new C0927b(palConnect, privatePkDn), this.f782c, palConnect)));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void stopConnect(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(f780a, "stopConnect deviceInfo null");
            return;
        }
        IPalConnect m348b = this.f781b.m348b(palDeviceInfo.getDevId());
        this.f781b.m346a(palDeviceInfo.getDevId());
        if (m348b != null) {
            m348b.stopConnect(PluginMgr.getInstance().toPrivatePkDn(palDeviceInfo));
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean asyncSendRequest(PalReqMessage palReqMessage, PalMsgListener palMsgListener) {
        IPalConnect m348b = this.f781b.m348b(palReqMessage.deviceInfo.getDevId());
        PalDeviceInfo palDeviceInfo = palReqMessage.deviceInfo;
        palReqMessage.deviceInfo = PluginMgr.getInstance().toPrivatePkDn(palReqMessage.deviceInfo);
        palReqMessage.topic = m362a(palReqMessage.topic, palDeviceInfo, palReqMessage.deviceInfo);
        ALog.m479d(f780a, "asyncSendRequest connect:" + m348b + " callback:" + palMsgListener);
        if (m348b != null) {
            return m348b.asyncSendRequest(palReqMessage, new C0933h(palDeviceInfo, palMsgListener));
        }
        if (palMsgListener == null) {
            return false;
        }
        PalRspMessage palRspMessage = new PalRspMessage();
        palRspMessage.code = 1;
        palMsgListener.onLoad(palRspMessage);
        return false;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean subscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener, PalMsgListener palMsgListener2) {
        IPalConnect m348b = this.f781b.m348b(palSubMessage.deviceInfo.getDevId());
        if (m348b == null) {
            if (palMsgListener != null) {
                PalRspMessage palRspMessage = new PalRspMessage();
                palRspMessage.code = 1;
                palMsgListener.onLoad(palRspMessage);
            }
            ALog.m480e(f780a, "subscribe connect null");
            return false;
        }
        PalDeviceInfo palDeviceInfo = palSubMessage.deviceInfo;
        palSubMessage.deviceInfo = PluginMgr.getInstance().toPrivatePkDn(palSubMessage.deviceInfo);
        palSubMessage.topic = m362a(palSubMessage.topic, palDeviceInfo, palSubMessage.deviceInfo);
        ALog.m479d(f780a, "subscribe topic:" + palSubMessage.topic);
        return m348b.subscribe(palSubMessage, new C0933h(palDeviceInfo, palMsgListener), new C0933h(palDeviceInfo, palMsgListener2));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unsubscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener) {
        IPalConnect m348b = this.f781b.m348b(palSubMessage.deviceInfo.getDevId());
        if (m348b == null) {
            if (palMsgListener == null) {
                return false;
            }
            PalRspMessage palRspMessage = new PalRspMessage();
            palRspMessage.code = 1;
            palMsgListener.onLoad(palRspMessage);
            return false;
        }
        PalDeviceInfo palDeviceInfo = palSubMessage.deviceInfo;
        palSubMessage.deviceInfo = PluginMgr.getInstance().toPrivatePkDn(palSubMessage.deviceInfo);
        palSubMessage.topic = m362a(palSubMessage.topic, palDeviceInfo, palSubMessage.deviceInfo);
        ALog.m479d(f780a, "unsubscribe topic:" + palSubMessage.topic);
        return m348b.unsubscribe(palSubMessage, new C0933h(palDeviceInfo, palMsgListener));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean isDeviceConnected(PalDeviceInfo palDeviceInfo) {
        IPalConnect m348b = this.f781b.m348b(palDeviceInfo.getDevId());
        if (m348b != null) {
            return m348b.isDeviceConnected(PluginMgr.getInstance().toPrivatePkDn(palDeviceInfo));
        }
        return false;
    }

    /* renamed from: a */
    public String m362a(String str, PalDeviceInfo palDeviceInfo, PalDeviceInfo palDeviceInfo2) {
        return (str.isEmpty() || palDeviceInfo == null || palDeviceInfo2 == null) ? str : (palDeviceInfo.productModel.equalsIgnoreCase(palDeviceInfo2.productModel) && palDeviceInfo.deviceId.equalsIgnoreCase(palDeviceInfo2.deviceId)) ? str : str.replace(palDeviceInfo.productModel, palDeviceInfo2.productModel).replace(palDeviceInfo.deviceId, palDeviceInfo2.deviceId);
    }
}
