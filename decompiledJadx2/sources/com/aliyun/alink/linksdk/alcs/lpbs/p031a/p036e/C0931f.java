package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.C0944a;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.C0951b;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsQeuryCallback;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalSubMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p032a.C0921a;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p033b.C0923a;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0943d;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MainDataConvertLayer.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.f */
/* loaded from: classes.dex */
public class C0931f extends AbstractC0930e {

    /* renamed from: a */
    private static final String f758a = "[AlcsLPBS]MainDataConvertLayer";

    /* renamed from: b */
    private C0923a f759b;

    /* renamed from: c */
    private C0921a f760c;

    public C0931f(C0923a c0923a, C0921a c0921a, AbstractC0930e abstractC0930e) {
        super(abstractC0930e);
        this.f759b = c0923a;
        this.f760c = c0921a;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void initAlcs(PalInitData palInitData) {
        ALog.m479d(f758a, "initAlcs initData:" + palInitData);
        for (Map.Entry<String, IPlugin> entry : PluginMgr.getInstance().getPluginList().entrySet()) {
            if (entry.getValue().getPalBridge() != null) {
                entry.getValue().getPalBridge().initBridge(palInitData);
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void deInitAlcs() {
        ALog.m479d(f758a, "deInitAlcs initData");
        for (Map.Entry<String, IPlugin> entry : PluginMgr.getInstance().getPluginList().entrySet()) {
            entry.getValue().getPalBridge().deInitBridge();
            entry.getValue().stopPlugin(entry.getValue().getPluginId());
        }
        PluginMgr.getInstance().getPluginList().clear();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void stopConnect(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(f758a, "stopConnect deviceInfo null error");
            return;
        }
        ALog.m479d(f758a, "stopConnect deviceInfo:" + palDeviceInfo.getDevId());
        if (this.f759b.m348b(palDeviceInfo.getDevId()) != null) {
            this.f760c.m341a(palDeviceInfo);
        }
        super.stopConnect(palDeviceInfo);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean asyncSendRequest(final PalReqMessage palReqMessage, final PalMsgListener palMsgListener) {
        ALog.m479d(f758a, "asyncSendRequest reqMessageInfo:" + palReqMessage + " callback:" + palMsgListener);
        if (PluginMgr.getInstance().isDataNeedConvert(palReqMessage.deviceInfo) && PluginMgr.getInstance().getJsProvider() != null && PluginMgr.getInstance().getJsEngine() != null) {
            PluginMgr.getInstance().getJsProvider().queryJsCode(palReqMessage.deviceInfo.productModel, palReqMessage.deviceInfo.deviceId, new IJsQeuryCallback() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.f.1
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsQeuryCallback
                public void onLoad(String str, String str2) {
                    PalReqMessage palReqMessage2 = palReqMessage;
                    palReqMessage2.topic = C0943d.m368a(palReqMessage2.topic, palReqMessage.deviceInfo.productModel, palReqMessage.deviceInfo.deviceId);
                    try {
                        if (!TextUtils.isEmpty(str2)) {
                            byte[] protocolToRawData = PluginMgr.getInstance().getJsEngine().protocolToRawData(str2, new String(palReqMessage.payload, "UTF-8"));
                            if (protocolToRawData != null) {
                                palReqMessage.payload = protocolToRawData;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    C0931f.super.asyncSendRequest(palReqMessage, new C0951b(palMsgListener, str2, PluginMgr.getInstance().getJsEngine()));
                }
            });
            return true;
        }
        super.asyncSendRequest(palReqMessage, palMsgListener);
        return true;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean subscribe(final PalSubMessage palSubMessage, final PalMsgListener palMsgListener, final PalMsgListener palMsgListener2) {
        ALog.m479d(f758a, "subscribe getJsProvider:" + PluginMgr.getInstance().getJsProvider() + " getJsEngine:" + PluginMgr.getInstance().getJsEngine());
        final IThingCloudChannel m343b = this.f760c.m343b(palSubMessage.deviceInfo);
        if (PluginMgr.getInstance().isDataNeedConvert(palSubMessage.deviceInfo) && PluginMgr.getInstance().getJsProvider() != null && PluginMgr.getInstance().getJsEngine() != null) {
            PluginMgr.getInstance().getJsProvider().queryJsCode(palSubMessage.deviceInfo.productModel, palSubMessage.deviceInfo.deviceId, new IJsQeuryCallback() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.f.2
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsQeuryCallback
                public void onLoad(String str, String str2) {
                    String str3 = palSubMessage.topic;
                    PalSubMessage palSubMessage2 = palSubMessage;
                    palSubMessage2.topic = C0943d.m367a(palSubMessage2.deviceInfo.productModel, palSubMessage.deviceInfo.deviceId);
                    try {
                        if (!TextUtils.isEmpty(str2)) {
                            byte[] protocolToRawData = PluginMgr.getInstance().getJsEngine().protocolToRawData(str2, new String(palSubMessage.payload, "UTF-8"));
                            if (protocolToRawData != null) {
                                palSubMessage.payload = protocolToRawData;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    C0931f.super.subscribe(palSubMessage, new C0951b(palMsgListener, str2, PluginMgr.getInstance().getJsEngine()), new C0944a(new C0938m(palSubMessage.deviceInfo, m343b, str3, palMsgListener2), str2, PluginMgr.getInstance().getJsEngine()));
                }
            });
            return true;
        }
        return super.subscribe(palSubMessage, palMsgListener, new C0938m(palSubMessage.deviceInfo, m343b, palSubMessage.topic, palMsgListener2));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unsubscribe(final PalSubMessage palSubMessage, final PalMsgListener palMsgListener) {
        if (PluginMgr.getInstance().isDataNeedConvert(palSubMessage.deviceInfo) && PluginMgr.getInstance().getJsProvider() != null && PluginMgr.getInstance().getJsEngine() != null) {
            PluginMgr.getInstance().getJsProvider().queryJsCode(palSubMessage.deviceInfo.productModel, palSubMessage.deviceInfo.deviceId, new IJsQeuryCallback() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.f.3
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsQeuryCallback
                public void onLoad(String str, String str2) {
                    PalSubMessage palSubMessage2 = palSubMessage;
                    palSubMessage2.topic = C0943d.m367a(palSubMessage2.deviceInfo.productModel, palSubMessage.deviceInfo.deviceId);
                    try {
                        if (!TextUtils.isEmpty(str2)) {
                            byte[] protocolToRawData = PluginMgr.getInstance().getJsEngine().protocolToRawData(str2, new String(palSubMessage.payload, "UTF-8"));
                            if (protocolToRawData != null) {
                                palSubMessage.payload = protocolToRawData;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    C0931f.super.unsubscribe(palSubMessage, palMsgListener);
                }
            });
            return true;
        }
        return super.unsubscribe(palSubMessage, palMsgListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startDiscovery(int i, PalDiscoveryListener palDiscoveryListener) {
        ALog.m479d(f758a, "startDiscovery timeOut:" + i + " listener:" + palDiscoveryListener);
        return super.startDiscovery(i, new C0928c(i, palDiscoveryListener));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopDiscovery() {
        for (Map.Entry<String, IPlugin> entry : PluginMgr.getInstance().getPluginList().entrySet()) {
            if (entry.getValue() != null && entry.getValue().getPalBridge() != null && entry.getValue().getPalBridge().getPalDiscovery() != null) {
                entry.getValue().getPalBridge().getPalDiscovery().stopDiscovery();
            }
        }
        return true;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopNotifyMonitor() {
        for (Map.Entry<String, IPlugin> entry : PluginMgr.getInstance().getPluginList().entrySet()) {
            if (entry.getValue() != null && entry.getValue().getPalBridge() != null && entry.getValue().getPalBridge().getPalDiscovery() != null) {
                try {
                    entry.getValue().getPalBridge().getPalDiscovery().stopNotifyMonitor();
                } catch (Throwable th) {
                    ALog.m480e(f758a, "stopNotifyMonitor throwable:" + th.toString());
                }
            }
        }
        return true;
    }
}
