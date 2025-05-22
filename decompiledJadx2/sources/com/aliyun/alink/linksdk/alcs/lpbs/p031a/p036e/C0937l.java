package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProvider;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p032a.C0921a;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p035d.C0925a;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: RegisterLayer.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.l */
/* loaded from: classes.dex */
public class C0937l extends AbstractC0930e {

    /* renamed from: c */
    private static final String f783c = "[AlcsLPBS]RegisterLayer";

    /* renamed from: a */
    private C0921a f784a;

    /* renamed from: b */
    private C0925a f785b;

    public C0937l(C0925a c0925a, C0921a c0921a, AbstractC0930e abstractC0930e) {
        super(abstractC0930e);
        this.f784a = c0921a;
        this.f785b = c0925a;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public boolean regAuthProvider(String str, IAuthProvider iAuthProvider) {
        IPlugin pluginByPluginId = PluginMgr.getInstance().getPluginByPluginId(str);
        ALog.m479d(f783c, "regAuthProvider pluginId:" + str + " provider:" + iAuthProvider + " plugin:" + pluginByPluginId);
        if (pluginByPluginId == null || pluginByPluginId.getPalBridge().getPalAuthRegister() == null) {
            return true;
        }
        pluginByPluginId.getPalBridge().getPalAuthRegister().setAuthProvider(iAuthProvider);
        return true;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void regCloudChannelFactory(ICloudChannelFactory iCloudChannelFactory) {
        ALog.m479d(f783c, "regCloudChannelFactory mChannelMgr:" + this.f784a + " factory:" + iCloudChannelFactory);
        this.f784a.m340a(iCloudChannelFactory);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean regDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        ALog.m479d(f783c, "regDeviceStateListener mDevStateListenerMgr:" + this.f785b + " listener:" + palDeviceStateListener);
        return this.f785b.m349a(palDeviceInfo, palDeviceStateListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e, com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unregDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        ALog.m479d(f783c, "unregDeviceStateListener mDevStateListenerMgr:" + this.f785b + " listener:" + palDeviceStateListener);
        return this.f785b.m350b(palDeviceInfo, palDeviceStateListener);
    }
}
