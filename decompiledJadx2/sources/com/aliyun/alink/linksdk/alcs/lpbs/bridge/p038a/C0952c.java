package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.data.ica.ICADiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgrConfig;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalAuthRegister;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalProbe;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;
import com.aliyun.alink.linksdk.alcs.pal.ica.ICAAlcsNative;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAAlcsBridge.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.c */
/* loaded from: classes.dex */
public class C0952c implements IPalBridge {

    /* renamed from: a */
    private static final String f825a = "[AlcsLPBS]ICAAlcsBridge";

    /* renamed from: c */
    private PalInitData f827c;

    /* renamed from: b */
    private Map<String, ICADiscoveryDeviceInfo> f826b = new HashMap();

    /* renamed from: d */
    private C0954e f828d = new C0954e(this);

    /* renamed from: e */
    private C0956g f829e = new C0956g();

    public C0952c(PluginMgrConfig pluginMgrConfig) {
    }

    /* renamed from: a */
    public void m382a(String str, ICADiscoveryDeviceInfo iCADiscoveryDeviceInfo) {
        this.f826b.put(str, iCADiscoveryDeviceInfo);
    }

    /* renamed from: a */
    public ICADiscoveryDeviceInfo m381a(String str) {
        return this.f826b.get(str);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge
    public void initBridge(PalInitData palInitData) {
        this.f827c = palInitData;
        ALog.m479d(f825a, " initBridge initData:" + palInitData);
        ICAAlcsNative.initPal(C0962m.m386a(palInitData));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge
    public void deInitBridge() {
        ALog.m479d(f825a, " deInitBridge");
        ICAAlcsNative.deInitPal();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge
    public IPalDiscovery getPalDiscovery() {
        return this.f828d;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge
    public IPalConnect getPalConnect(PalDeviceInfo palDeviceInfo) {
        return new C0953d(this, palDeviceInfo);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge
    public IPalAuthRegister getPalAuthRegister() {
        return this.f829e;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge
    public IPalProbe getPalProbe() {
        return new C0955f(this);
    }
}
