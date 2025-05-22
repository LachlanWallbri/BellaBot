package com.aliyun.alink.linksdk.alcs.lpbs.api;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProvider;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsProvider;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.component.jsengine.IJSEngine;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalConnectParams;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalSubMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p032a.C0921a;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p033b.C0923a;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p035d.C0925a;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.AbstractC0930e;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.C0931f;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.C0936k;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.C0937l;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0943d;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.IPlugin;
import com.aliyun.alink.linksdk.alcs.lpbs.plugin.PluginConfig;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PluginMgr extends IAlcsPal {
    private static final String TAG = "[AlcsLPBS]PluginMgr";
    private C0921a mChannelMgr;
    private C0923a mConnectMgr;
    private IDevInfoTrans mDevInfoTrans;
    private Map<String, String> mDevPluginIndex;
    private C0925a mDevStateListenerMgr;
    private Map<String, PalDiscoveryDeviceInfo> mFoundDevList;
    private Map<String, IPlugin> mPluginList;
    private PluginMgrConfig mPluginMgrConfig;
    private AbstractC0930e mRegisterLayer;

    private PluginMgr() {
        this.mPluginList = new ConcurrentHashMap();
        this.mDevPluginIndex = new ConcurrentHashMap();
        this.mFoundDevList = new ConcurrentHashMap();
        this.mChannelMgr = new C0921a();
        this.mConnectMgr = new C0923a();
        this.mDevStateListenerMgr = new C0925a(this.mConnectMgr);
        initLayer();
    }

    private void initLayer() {
        this.mRegisterLayer = new C0937l(this.mDevStateListenerMgr, this.mChannelMgr, new C0931f(this.mConnectMgr, this.mChannelMgr, new C0936k(this.mConnectMgr, this.mChannelMgr, null)));
    }

    public static PluginMgr getInstance() {
        return InstanceHolder.mInstace;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalProbe
    public void probeDevice(PalDeviceInfo palDeviceInfo, PalProbeListener palProbeListener) {
        this.mRegisterLayer.probeDevice(palDeviceInfo, palProbeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class InstanceHolder {
        private static PluginMgr mInstace = new PluginMgr();

        private InstanceHolder() {
        }
    }

    public boolean addPlugin(IPlugin iPlugin) {
        ALog.m479d(TAG, "addPlugin plugin:" + iPlugin);
        if (iPlugin == null) {
            ALog.m480e(TAG, "addPlugin plugin:" + iPlugin);
            return false;
        }
        PluginConfig pluginConfig = new PluginConfig();
        pluginConfig.context = this.mPluginMgrConfig.context;
        pluginConfig.initData = this.mPluginMgrConfig.initData;
        iPlugin.startPlugin(iPlugin.getPluginId(), pluginConfig);
        this.mPluginList.put(iPlugin.getPluginId(), iPlugin);
        return true;
    }

    public void removePlugin(String str) {
        ALog.m479d(TAG, "removePlugin pluginId:" + str);
        IPlugin remove = this.mPluginList.remove(str);
        if (remove != null) {
            remove.stopPlugin(str);
        }
    }

    public boolean initPluginMgr(PluginMgrConfig pluginMgrConfig) {
        ALog.m479d(TAG, "initPluginMgr config:" + pluginMgrConfig);
        if (pluginMgrConfig == null) {
            ALog.m480e(TAG, "initPluginMgr error config null");
            return false;
        }
        this.mPluginMgrConfig = pluginMgrConfig;
        this.mDevInfoTrans = pluginMgrConfig.devInfoTrans;
        this.mRegisterLayer.regCloudChannelFactory(this.mPluginMgrConfig.cloudChannelFactory);
        return true;
    }

    public IJsProvider getJsProvider() {
        return this.mPluginMgrConfig.jsProvider;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void initAlcs(PalInitData palInitData) {
        this.mRegisterLayer.initAlcs(palInitData);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void deInitAlcs() {
        this.mRegisterLayer.deInitAlcs();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startDiscovery(int i, PalDiscoveryListener palDiscoveryListener) {
        return this.mRegisterLayer.startDiscovery(i, palDiscoveryListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopDiscovery() {
        return this.mRegisterLayer.stopDiscovery();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startNotifyMonitor(PalDiscoveryListener palDiscoveryListener) {
        ALog.m479d(TAG, "startNotifyMonitor listener:" + palDiscoveryListener);
        return this.mRegisterLayer.startNotifyMonitor(palDiscoveryListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopNotifyMonitor() {
        ALog.m479d(TAG, "stopNotifyMonitor");
        return this.mRegisterLayer.stopNotifyMonitor();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void startConnect(PalConnectParams palConnectParams, PalConnectListener palConnectListener) {
        updateDataFormat(palConnectParams);
        this.mRegisterLayer.startConnect(palConnectParams, palConnectListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void stopConnect(PalDeviceInfo palDeviceInfo) {
        this.mRegisterLayer.stopConnect(palDeviceInfo);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean isDeviceConnected(PalDeviceInfo palDeviceInfo) {
        return this.mRegisterLayer.isDeviceConnected(palDeviceInfo);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean asyncSendRequest(PalReqMessage palReqMessage, PalMsgListener palMsgListener) {
        return this.mRegisterLayer.asyncSendRequest(palReqMessage, palMsgListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean subscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener, PalMsgListener palMsgListener2) {
        return this.mRegisterLayer.subscribe(palSubMessage, palMsgListener, palMsgListener2);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unsubscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener) {
        return this.mRegisterLayer.unsubscribe(palSubMessage, palMsgListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean regDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        return this.mRegisterLayer.regDeviceStateListener(palDeviceInfo, palDeviceStateListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unregDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        return this.mRegisterLayer.unregDeviceStateListener(palDeviceInfo, palDeviceStateListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void onCloudChannelCreate(IThingCloudChannel iThingCloudChannel) {
        ALog.m480e(TAG, "onCloudChannelCreate empty impl");
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public boolean regAuthProvider(String str, IAuthProvider iAuthProvider) {
        return this.mRegisterLayer.regAuthProvider(str, iAuthProvider);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void regCloudChannelFactory(ICloudChannelFactory iCloudChannelFactory) {
        this.mRegisterLayer.regCloudChannelFactory(iCloudChannelFactory);
    }

    public boolean insertDiscoveryDev(String str, String str2, PalDiscoveryDeviceInfo palDiscoveryDeviceInfo) {
        if (palDiscoveryDeviceInfo == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ALog.m480e(TAG, "insertDiscoveryDev params null");
            return false;
        }
        ALog.m479d(TAG, "insertDiscoveryDev devId:" + str + " pluginId:" + str2 + " discoveryDeviceInfo:" + palDiscoveryDeviceInfo);
        this.mDevPluginIndex.put(str, str2);
        this.mFoundDevList.put(str, palDiscoveryDeviceInfo);
        return true;
    }

    public IPlugin getPluginByPluginId(String str) {
        IPlugin iPlugin = this.mPluginList.get(str);
        ALog.m479d(TAG, "getPluginByPluginId pluginId:" + str + " plugin:" + iPlugin);
        return iPlugin;
    }

    public IPlugin getPluginByDevId(String str) {
        ALog.m479d(TAG, "getPluginByDevId devId:" + str);
        String str2 = this.mDevPluginIndex.get(str);
        if (TextUtils.isEmpty(str2)) {
            ALog.m480e(TAG, "getPluginByDevId devId pluginId null");
            return null;
        }
        return this.mPluginList.get(str2);
    }

    public int getPluginCount() {
        return this.mPluginList.size();
    }

    public Map<String, IPlugin> getPluginList() {
        return this.mPluginList;
    }

    public IJSEngine getJsEngine() {
        PluginMgrConfig pluginMgrConfig = this.mPluginMgrConfig;
        if (pluginMgrConfig == null) {
            return null;
        }
        return pluginMgrConfig.jsEngine;
    }

    public PalDeviceInfo toPrivatePkDn(PalDeviceInfo palDeviceInfo) {
        IDevInfoTrans iDevInfoTrans;
        return (!isPkDnNeedConvert(palDeviceInfo) || (iDevInfoTrans = this.mDevInfoTrans) == null) ? palDeviceInfo : iDevInfoTrans.toPrivateDeviceInfo(palDeviceInfo);
    }

    public PalDeviceInfo toAliIoTPkDn(PalDeviceInfo palDeviceInfo) {
        IDevInfoTrans iDevInfoTrans = this.mDevInfoTrans;
        return iDevInfoTrans != null ? iDevInfoTrans.toAliIoTDeviceInfo(palDeviceInfo) : palDeviceInfo;
    }

    public void updateDiscoveryInfo(String str, String str2, String str3) {
        ALog.m479d(TAG, "updateDiscoveryInfo oldId:" + str + " productKey:" + str2 + " devName:" + str3);
        String remove = this.mDevPluginIndex.remove(str);
        String m370b = C0943d.m370b(str2, str3);
        if (!TextUtils.isEmpty(remove)) {
            this.mDevPluginIndex.put(m370b, remove);
        }
        PalDiscoveryDeviceInfo remove2 = this.mFoundDevList.remove(str);
        if (remove2 != null) {
            remove2.deviceInfo.productModel = str2;
            remove2.deviceInfo.deviceId = str3;
            this.mFoundDevList.put(m370b, remove2);
        }
    }

    public void initDevTrans(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo, IDevInfoTrans.IDevInfoTransListener iDevInfoTransListener) {
        ALog.m479d(TAG, "initDevTrans deviceInfo:" + palDiscoveryDeviceInfo + " listener:" + iDevInfoTransListener);
        IDevInfoTrans iDevInfoTrans = this.mDevInfoTrans;
        if (iDevInfoTrans != null && iDevInfoTransListener != null) {
            iDevInfoTrans.init(palDiscoveryDeviceInfo, iDevInfoTransListener);
        } else if (iDevInfoTransListener != null) {
            iDevInfoTransListener.onComplete(true, null);
        }
    }

    private boolean isPkDnNeedConvert(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(TAG, "isPkDnNeedConvert deviceInfo null");
            return false;
        }
        PalDiscoveryDeviceInfo palDiscoveryDeviceInfo = this.mFoundDevList.get(palDeviceInfo.getDevId());
        if (palDiscoveryDeviceInfo != null) {
            ALog.m479d(TAG, "palDiscoveryDeviceInfo not null isPkDnNeedConvert deviceInfo:" + palDeviceInfo.getDevId() + " isPkDnNeedConvert:" + palDiscoveryDeviceInfo.isPkDnNeedConvert());
            return palDiscoveryDeviceInfo.isPkDnNeedConvert();
        }
        ALog.m479d(TAG, "palDiscoveryDeviceInfo null isPkDnNeedConvert deviceInfo:" + palDeviceInfo.getDevId() + " isPkDnNeedConvert: false");
        return false;
    }

    public boolean isDataToCloud(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(TAG, "isDataToCloud deviceInfo null");
            return false;
        }
        PalDiscoveryDeviceInfo palDiscoveryDeviceInfo = this.mFoundDevList.get(palDeviceInfo.getDevId());
        if (palDiscoveryDeviceInfo != null) {
            ALog.m479d(TAG, "isDataToCloud deviceInfo:" + palDeviceInfo.getDevId() + " isDataToCloud:" + palDiscoveryDeviceInfo.isDataToCloud);
            return palDiscoveryDeviceInfo.isDataToCloud;
        }
        ALog.m479d(TAG, "isDataToCloud deviceInfo:" + palDeviceInfo.getDevId() + " false");
        return false;
    }

    public boolean isDataNeedConvert(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(TAG, "isDataNeedConvert false deviceInfo null");
        }
        PalDiscoveryDeviceInfo palDiscoveryDeviceInfo = this.mFoundDevList.get(palDeviceInfo.getDevId());
        if (palDiscoveryDeviceInfo != null) {
            ALog.m479d(TAG, "isDataNeedConvert deviceInfo:" + palDeviceInfo.getDevId() + " palDiscoveryDeviceInfo.dataFormat:" + palDiscoveryDeviceInfo.dataFormat);
            return !"ALINK_FORMAT".equalsIgnoreCase(palDiscoveryDeviceInfo.dataFormat);
        }
        ALog.m479d(TAG, "isDataNeedConvert deviceInfo:" + palDeviceInfo.getDevId() + " isDataNeedConvert:false");
        return false;
    }

    public void updateDataFormat(PalConnectParams palConnectParams) {
        if (palConnectParams == null) {
            ALog.m480e(TAG, "updateDataFormat connectParams null");
            return;
        }
        if (TextUtils.isEmpty(palConnectParams.dataFormat)) {
            return;
        }
        PalDiscoveryDeviceInfo palDiscoveryDeviceInfo = this.mFoundDevList.get(palConnectParams.getDevId());
        ALog.m479d(TAG, "updateDataFormat connectdev:" + palConnectParams.getDevId() + " dataFormat:" + palConnectParams.dataFormat + " palDiscoveryDeviceInfo:" + palDiscoveryDeviceInfo);
        if (palDiscoveryDeviceInfo != null) {
            palDiscoveryDeviceInfo.dataFormat = palConnectParams.dataFormat;
        }
    }

    public PalDiscoveryDeviceInfo getDiscoveryedDevInfo(String str) {
        PalDiscoveryDeviceInfo palDiscoveryDeviceInfo = this.mFoundDevList.get(str);
        ALog.m479d(TAG, "getDiscoveryedDevInfo devId:" + str + " discoveryDeviceInfo:" + palDiscoveryDeviceInfo);
        return palDiscoveryDeviceInfo;
    }

    public void clearDiscoveryDevInfo() {
        ALog.m479d(TAG, "clearDiscoveryDevInfo");
        this.mFoundDevList.clear();
        this.mDevPluginIndex.clear();
    }
}
