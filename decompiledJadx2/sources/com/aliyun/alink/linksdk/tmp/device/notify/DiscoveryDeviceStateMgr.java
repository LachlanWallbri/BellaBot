package com.aliyun.alink.linksdk.tmp.device.notify;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.listener.IDiscoveryDeviceStateChangeListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DiscoveryDeviceStateMgr {
    private static final String TAG = "[Tmp]DiscoveryDeviceStateMgr";
    private Map<Integer, WeakReference<IDiscoveryDeviceStateChangeListener>> mDiscoveryDevStateChangeListenerList;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class InstanceHolder {
        protected static DiscoveryDeviceStateMgr mInstance = new DiscoveryDeviceStateMgr();

        private InstanceHolder() {
        }
    }

    public static DiscoveryDeviceStateMgr getInstance() {
        return InstanceHolder.mInstance;
    }

    private DiscoveryDeviceStateMgr() {
        this.mDiscoveryDevStateChangeListenerList = new ConcurrentHashMap();
    }

    public void addDiscoveryDeviceStateChangeListener(IDiscoveryDeviceStateChangeListener iDiscoveryDeviceStateChangeListener) {
        ALog.m479d(TAG, "addDiscoveryDeviceStateChangeListener listener:" + iDiscoveryDeviceStateChangeListener);
        if (iDiscoveryDeviceStateChangeListener == null) {
            return;
        }
        this.mDiscoveryDevStateChangeListenerList.put(Integer.valueOf(iDiscoveryDeviceStateChangeListener.hashCode()), new WeakReference<>(iDiscoveryDeviceStateChangeListener));
    }

    public void removeDiscoveryDeviceStateChangeListener(IDiscoveryDeviceStateChangeListener iDiscoveryDeviceStateChangeListener) {
        ALog.m479d(TAG, "removeDiscoveryDeviceStateChangeListener listener:" + iDiscoveryDeviceStateChangeListener);
        if (iDiscoveryDeviceStateChangeListener == null) {
            return;
        }
        this.mDiscoveryDevStateChangeListenerList.remove(Integer.valueOf(iDiscoveryDeviceStateChangeListener.hashCode()));
    }

    public void onDiscoveryDeviceStateChange(DeviceBasicData deviceBasicData, TmpEnum.DiscoveryDeviceState discoveryDeviceState) {
        StringBuilder sb = new StringBuilder();
        sb.append("onDiscoveryDeviceStateChange basicData:");
        sb.append(deviceBasicData == null ? "" : deviceBasicData.toString());
        sb.append(" state:");
        sb.append(discoveryDeviceState);
        sb.append(" mDiscoveryDevStateChangeListenerList:");
        sb.append(this.mDiscoveryDevStateChangeListenerList);
        ALog.m479d(TAG, sb.toString());
        Map<Integer, WeakReference<IDiscoveryDeviceStateChangeListener>> map = this.mDiscoveryDevStateChangeListenerList;
        if (map == null || map.isEmpty()) {
            ALog.m484w(TAG, "onDiscoveryDeviceStateChange mDiscoveryDevStateChangeListenerList empty");
            return;
        }
        Iterator<Map.Entry<Integer, WeakReference<IDiscoveryDeviceStateChangeListener>>> it = this.mDiscoveryDevStateChangeListenerList.entrySet().iterator();
        while (it.hasNext()) {
            IDiscoveryDeviceStateChangeListener iDiscoveryDeviceStateChangeListener = it.next().getValue().get();
            if (iDiscoveryDeviceStateChangeListener != null) {
                iDiscoveryDeviceStateChangeListener.onDiscoveryDeviceStateChange(deviceBasicData, discoveryDeviceState);
            }
        }
    }
}
