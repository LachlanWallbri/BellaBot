package com.aliyun.alink.linksdk.tmp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.aliyun.alink.linksdk.alcs.api.AlcsCoAPSdk;
import com.aliyun.alink.linksdk.alcs.jsengine.RhinoJsEngine;
import com.aliyun.alink.linksdk.alcs.lpbs.api.AlcsPalSdk;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgrConfig;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.TmpInitConfig;
import com.aliyun.alink.linksdk.tmp.component.cloud.DefaultCloudProxyImpl;
import com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxy;
import com.aliyun.alink.linksdk.tmp.component.pkdnconvert.DefaultDevInfoTrans;
import com.aliyun.alink.linksdk.tmp.connect.TmpSyncRequestHandler;
import com.aliyun.alink.linksdk.tmp.device.configuration.ConfigMgr;
import com.aliyun.alink.linksdk.tmp.device.configuration.LocalConfigurator;
import com.aliyun.alink.linksdk.tmp.network.NetConnected;
import com.aliyun.alink.linksdk.tmp.service.CloudChannelFactoryImpl;
import com.aliyun.alink.linksdk.tmp.storage.JSQueryApiGateProvider;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.iot.breeze.api.Config;
import com.aliyun.iot.breeze.api.Factory;
import com.aliyun.iot.breeze.api.IBreeze;
import com.aliyun.iot.breeze.lpbs.Plugin;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpSdk {
    public static IBreeze BREEZE = null;
    private static final String TAG = "[Tmp]TmpSdk";
    private static ICloudProxy mCloudProxy;
    private static TmpInitConfig mConfig;
    private static Context mContext;
    public static Handler mHandler;

    public static void init(Context context, TmpInitConfig tmpInitConfig) {
        mContext = context;
        mConfig = tmpInitConfig;
        if (tmpInitConfig == null || tmpInitConfig.mCloudProxy == null) {
            mCloudProxy = new DefaultCloudProxyImpl();
        } else {
            mCloudProxy = tmpInitConfig.mCloudProxy;
        }
        AlcsCoAPSdk.init();
        initLpbs(tmpInitConfig);
        ConnectSDK.getInstance().init(mContext);
        TmpStorage.getInstance().init(context);
        TmpSyncRequestHandler.init();
        mHandler = new Handler(Looper.getMainLooper());
        LocalConfigurator.start(null);
        ConfigMgr.getInstance().init();
        NetConnected.init(context);
        ALog.m479d(TAG, "init context: " + context + " mConfig:" + mConfig + " mCloudProxy:" + mCloudProxy);
        try {
            DeviceManager.getInstance().startNotifyMonitor();
        } catch (Throwable unused) {
            ALog.m480e(TAG, "");
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static TmpInitConfig getConfig() {
        return mConfig;
    }

    public static DeviceManager getDeviceManager() {
        return DeviceManager.getInstance();
    }

    public static ICloudProxy getCloudProxy() {
        return mCloudProxy;
    }

    public static void initLpbs(TmpInitConfig tmpInitConfig) {
        try {
            PluginMgrConfig pluginMgrConfig = new PluginMgrConfig();
            pluginMgrConfig.context = mContext;
            pluginMgrConfig.devInfoTrans = DefaultDevInfoTrans.getInstance();
            pluginMgrConfig.jsProvider = new JSQueryApiGateProvider();
            pluginMgrConfig.jsEngine = RhinoJsEngine.getInstance();
            pluginMgrConfig.cloudChannelFactory = new CloudChannelFactoryImpl();
            pluginMgrConfig.initData = new PalInitData(3);
            if (tmpInitConfig != null) {
                pluginMgrConfig.initData.deviceInfo = tmpInitConfig.mIcaPalDeviceInfo;
            }
            List<IPlugin> list = tmpInitConfig == null ? null : tmpInitConfig.mLpbsPluginList;
            try {
                try {
                    try {
                        Class<?> cls = Class.forName("com.aliyun.iot.breeze.lpbs.Plugin");
                        if (cls != null) {
                            Plugin plugin = (Plugin) cls.newInstance();
                            if (plugin != null) {
                                if (list == null) {
                                    list = new ArrayList();
                                }
                                list.add(plugin);
                                Config build = new Config.Builder().debug(true).log(true).logLevel(2).build();
                                IBreeze createBreeze = Factory.createBreeze(mContext);
                                BREEZE = createBreeze;
                                createBreeze.configure(build);
                            } else {
                                ALog.m484w(TAG, "(Plugin)pluginClass.newInstance() null");
                            }
                        }
                    } catch (Exception e) {
                        ALog.m484w(TAG, "Exception:" + e.toString());
                    } catch (NoClassDefFoundError e2) {
                        ALog.m484w(TAG, "noClassDefFoundError:" + e2.toString());
                    }
                } catch (Throwable th) {
                    ALog.m484w(TAG, "throwable1:" + th.toString());
                }
            } catch (ClassNotFoundException e3) {
                ALog.m484w(TAG, "ClassNotFoundException:" + e3.toString());
            }
            AlcsPalSdk.init(mContext, pluginMgrConfig, list);
        } catch (Throwable th2) {
            ALog.m484w(TAG, "throwable2:" + th2.toString());
        }
    }
}
