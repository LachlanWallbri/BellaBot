package com.aliyun.alink.linksdk.alcs.lpbs.plugin;

import com.aliyun.alink.linksdk.alcs.lpbs.api.AlcsPalConst;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgrConfig;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalBridge;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.C0952c;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICAPlugin implements IPlugin {
    private C0952c mBridge;

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin
    public String getPluginId() {
        return AlcsPalConst.ICA_PLUGIN_ID;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin
    public boolean startPlugin(String str, PluginConfig pluginConfig) {
        return true;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin
    public boolean stopPlugin(String str) {
        return true;
    }

    public ICAPlugin(PluginMgrConfig pluginMgrConfig) {
        this.mBridge = new C0952c(pluginMgrConfig);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin
    public IPalBridge getPalBridge() {
        return this.mBridge;
    }
}
