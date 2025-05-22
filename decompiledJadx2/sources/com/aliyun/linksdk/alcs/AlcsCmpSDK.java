package com.aliyun.linksdk.alcs;

import android.content.Context;
import com.aliyun.alink.linksdk.alcs.api.client.AlcsClientConfig;
import com.aliyun.alink.linksdk.alcs.api.client.IDeviceHandler;
import com.aliyun.alink.linksdk.alcs.api.server.AlcsServerConfig;
import com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalProbeResult;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsCmpSDK {
    public static String DISCOVERY_ADDR = "224.0.1.187";
    private static int DISCOVERY_PORT = 5683;
    private static String DISCOVERY_TOPIC = "/dev/core/service/dev";
    private static final String TAG = "AlcsCmpSDK";
    private static IAlcsPal discoveryClient;
    private static volatile IDiscoveryDevicesListener discoveryDevicesListener;
    private static volatile IDiscoveryDevicesListener notifyDevicesListener;
    private static IAlcsServer server;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface IDiscoveryCertainDeviceListener {
        void onFail(PalDeviceInfo palDeviceInfo);

        void onSuccess(PalDeviceInfo palDeviceInfo);

        void onTimeout(PalDeviceInfo palDeviceInfo);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface IDiscoveryDevicesListener {
        void onFound(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo);
    }

    public static void init(Context context) {
        ALog.m479d(TAG, "init()");
    }

    public static void destroy() {
        ALog.m479d(TAG, "destroy()");
    }

    public static void startDiscoverDevices(int i, IDiscoveryDevicesListener iDiscoveryDevicesListener) {
        ALog.m479d(TAG, "startDiscoverDevices");
        discoveryDevicesListener = iDiscoveryDevicesListener;
        if (discoveryClient == null) {
            discoveryClient = PluginMgr.getInstance();
        }
        AlcsClientConfig alcsClientConfig = new AlcsClientConfig();
        alcsClientConfig.setDstAddr(DISCOVERY_ADDR);
        alcsClientConfig.setDstPort(DISCOVERY_PORT);
        alcsClientConfig.setIsNeddAuth(false);
        discoveryClient.startDiscovery(i, new PalDiscoveryListener() { // from class: com.aliyun.linksdk.alcs.AlcsCmpSDK.1
            @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
            public void onDiscoveryFinish() {
            }

            @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
            public void onDiscoveryDevice(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo) {
                ALog.m479d(AlcsCmpSDK.TAG, "onReqComplete(), result = " + palDiscoveryDeviceInfo);
                if (palDiscoveryDeviceInfo == null || AlcsCmpSDK.discoveryDevicesListener == null) {
                    return;
                }
                AlcsCmpSDK.discoveryDevicesListener.onFound(palDiscoveryDeviceInfo);
            }
        });
    }

    public static void stopDiscoveryDevices() {
        ALog.m479d(TAG, "stopDiscoveryDevices()");
        IAlcsPal iAlcsPal = discoveryClient;
        if (iAlcsPal == null) {
            return;
        }
        iAlcsPal.stopDiscovery();
    }

    public static void discoveryCertainDevice(PalDeviceInfo palDeviceInfo, final IDiscoveryCertainDeviceListener iDiscoveryCertainDeviceListener) {
        ALog.m479d(TAG, "discoveryCertainDevice");
        if (discoveryClient == null) {
            discoveryClient = PluginMgr.getInstance();
        }
        discoveryClient.probeDevice(palDeviceInfo, new PalProbeListener() { // from class: com.aliyun.linksdk.alcs.AlcsCmpSDK.2
            @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener
            public void onComplete(PalDeviceInfo palDeviceInfo2, PalProbeResult palProbeResult) {
                StringBuilder sb = new StringBuilder();
                sb.append("discoveryCertainDevice,onComplete(), code = ");
                sb.append(palProbeResult != null ? Integer.valueOf(palProbeResult.probeResult) : "null");
                ALog.m479d(AlcsCmpSDK.TAG, sb.toString());
                if (IDiscoveryCertainDeviceListener.this == null || palProbeResult == null) {
                    return;
                }
                int i = palProbeResult.probeResult;
                if (i == 0) {
                    IDiscoveryCertainDeviceListener.this.onSuccess(palDeviceInfo2);
                } else if (i == 1) {
                    IDiscoveryCertainDeviceListener.this.onTimeout(palDeviceInfo2);
                } else {
                    if (i != 2) {
                        return;
                    }
                    IDiscoveryCertainDeviceListener.this.onFail(palDeviceInfo2);
                }
            }
        });
    }

    public static void startNotifyMonitor(IDiscoveryDevicesListener iDiscoveryDevicesListener) {
        ALog.m479d(TAG, "startNotifyMonitor");
        notifyDevicesListener = iDiscoveryDevicesListener;
        if (discoveryClient == null) {
            discoveryClient = PluginMgr.getInstance();
        }
        discoveryClient.startNotifyMonitor(new PalDiscoveryListener() { // from class: com.aliyun.linksdk.alcs.AlcsCmpSDK.3
            @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
            public void onDiscoveryFinish() {
            }

            @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
            public void onDiscoveryDevice(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo) {
                ALog.m479d(AlcsCmpSDK.TAG, "onReqComplete(), result = " + palDiscoveryDeviceInfo);
                if (palDiscoveryDeviceInfo == null || AlcsCmpSDK.notifyDevicesListener == null) {
                    return;
                }
                AlcsCmpSDK.notifyDevicesListener.onFound(palDiscoveryDeviceInfo);
            }
        });
    }

    public static void stopNotifyMonitor() {
        if (discoveryClient == null) {
            discoveryClient = PluginMgr.getInstance();
        }
        notifyDevicesListener = null;
        discoveryClient.stopNotifyMonitor();
    }

    public static IAlcsClient initClientConnect(AlcsClientConfig alcsClientConfig, IDeviceHandler iDeviceHandler) {
        ALog.m479d(TAG, "initDeviceConnect()");
        AlcsClientWrapper alcsClientWrapper = new AlcsClientWrapper();
        alcsClientWrapper.init(alcsClientConfig, iDeviceHandler);
        return alcsClientWrapper;
    }

    public static IAlcsServer initServer(AlcsServerConfig alcsServerConfig) {
        ALog.m479d(TAG, "initServer()");
        if (server == null) {
            server = new AlcsServerWrapper(alcsServerConfig);
        }
        return server;
    }

    public static IAlcsServer getServer() {
        ALog.m479d(TAG, "getServer()");
        return server;
    }
}
