package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal;
import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProvider;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalConnectParams;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalSubMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: IAlcsPalLayer.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.e */
/* loaded from: classes.dex */
public abstract class AbstractC0930e extends IAlcsPal {

    /* renamed from: a */
    private static final String f756a = "[AlcsLPBS]IAlcsPalLayer";

    /* renamed from: b */
    private AbstractC0930e f757b;

    public AbstractC0930e(AbstractC0930e abstractC0930e) {
        this.f757b = abstractC0930e;
    }

    /* renamed from: a */
    public AbstractC0930e m358a() {
        return this.f757b;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void initAlcs(PalInitData palInitData) {
        if (m358a() != null) {
            m358a().initAlcs(palInitData);
        } else {
            ALog.m480e(f756a, "initAlcs on error Layer");
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void deInitAlcs() {
        if (m358a() != null) {
            m358a().deInitAlcs();
        } else {
            ALog.m480e(f756a, "deInitAlcs on error Layer");
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public boolean regAuthProvider(String str, IAuthProvider iAuthProvider) {
        if (m358a() != null) {
            return m358a().regAuthProvider(str, iAuthProvider);
        }
        ALog.m480e(f756a, "regAuthProvider on error Layer");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.api.IAlcsPal
    public void regCloudChannelFactory(ICloudChannelFactory iCloudChannelFactory) {
        if (m358a() != null) {
            m358a().regCloudChannelFactory(iCloudChannelFactory);
        } else {
            ALog.m480e(f756a, "regCloudChannelFactory on error Layer");
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void startConnect(PalConnectParams palConnectParams, PalConnectListener palConnectListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "startConnect on error Layer");
            if (palConnectListener != null) {
                palConnectListener.onLoad(1, null, palConnectParams.deviceInfo);
                return;
            }
            return;
        }
        m358a().startConnect(palConnectParams, palConnectListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void stopConnect(PalDeviceInfo palDeviceInfo) {
        if (m358a() != null) {
            m358a().stopConnect(palDeviceInfo);
        } else {
            ALog.m480e(f756a, "stopConnect on error Layer");
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean asyncSendRequest(PalReqMessage palReqMessage, PalMsgListener palMsgListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "asyncSendRequest on error Layer");
            if (palMsgListener == null) {
                return false;
            }
            palMsgListener.onLoad(null);
            return false;
        }
        return m358a().asyncSendRequest(palReqMessage, palMsgListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean subscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener, PalMsgListener palMsgListener2) {
        if (m358a() == null) {
            ALog.m480e(f756a, "subscribe on error Layer");
            if (palMsgListener == null) {
                return false;
            }
            palMsgListener.onLoad(null);
            return false;
        }
        return m358a().subscribe(palSubMessage, palMsgListener, palMsgListener2);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unsubscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "unsubscribe on error Layer");
            if (palMsgListener == null) {
                return false;
            }
            palMsgListener.onLoad(null);
            return false;
        }
        return m358a().unsubscribe(palSubMessage, palMsgListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean isDeviceConnected(PalDeviceInfo palDeviceInfo) {
        if (m358a() == null) {
            ALog.m480e(f756a, "isDeviceConnected on error Layer");
            return false;
        }
        return m358a().isDeviceConnected(palDeviceInfo);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean regDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "regDeviceStateListener on error Layer");
            return false;
        }
        return m358a().regDeviceStateListener(palDeviceInfo, palDeviceStateListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public boolean unregDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "unregDeviceStateListener on error Layer");
            return false;
        }
        return m358a().unregDeviceStateListener(palDeviceInfo, palDeviceStateListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalProbe
    public void probeDevice(PalDeviceInfo palDeviceInfo, PalProbeListener palProbeListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "probeDevice on error Layer");
        } else {
            m358a().probeDevice(palDeviceInfo, palProbeListener);
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startDiscovery(int i, PalDiscoveryListener palDiscoveryListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "startDiscovery on error Layer");
            return false;
        }
        return m358a().startDiscovery(i, palDiscoveryListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopDiscovery() {
        if (m358a() == null) {
            ALog.m480e(f756a, "stopDiscovery on error Layer");
            return false;
        }
        return m358a().stopDiscovery();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startNotifyMonitor(PalDiscoveryListener palDiscoveryListener) {
        if (m358a() == null) {
            ALog.m480e(f756a, "startNotifyMonitor on error Layer");
            return false;
        }
        return m358a().startNotifyMonitor(palDiscoveryListener);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopNotifyMonitor() {
        if (m358a() == null) {
            ALog.m480e(f756a, "stopNotifyMonitor on error Layer");
            return false;
        }
        return m358a().stopNotifyMonitor();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect
    public void onCloudChannelCreate(IThingCloudChannel iThingCloudChannel) {
        ALog.m480e(f756a, "onCloudChannelCreate empty impl");
    }
}
