package com.aliyun.alink.linksdk.alcs.lpbs.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.C0959j;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.C0945a;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.ICAPlugin;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsPalSdk {
    public static Context mContext;
    public static Handler mHandler;

    public static void init(Context context, PluginMgrConfig pluginMgrConfig, List<IPlugin> list) {
        mHandler = new Handler(Looper.getMainLooper());
        mContext = context;
        PluginMgr.getInstance().initPluginMgr(pluginMgrConfig);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                PluginMgr.getInstance().addPlugin(list.get(i));
            }
        }
        if (PluginMgr.getInstance().getPluginByPluginId(AlcsPalConst.ICA_PLUGIN_ID) == null) {
            ICAPlugin iCAPlugin = new ICAPlugin(pluginMgrConfig);
            iCAPlugin.getPalBridge().getPalAuthRegister().setAuthProvider(new C0959j(new C0945a(mContext)));
            PluginMgr.getInstance().addPlugin(iCAPlugin);
        }
        if (pluginMgrConfig == null) {
            PluginMgr.getInstance().initAlcs(null);
        } else {
            PluginMgr.getInstance().initAlcs(pluginMgrConfig.initData);
        }
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }
}
