package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CmpNotifyManager implements IConnectNotifyListener {
    protected static final String TAG = "[Tmp]CmpNotifyManager";
    protected Map<String, Map<Integer, IDevStateChangeListener>> mConnectChangeListenerList;
    protected Map<Integer, Map<String, Map<String, IConnectNotifyListener>>> mNotifyHandlerList;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class InstanceHolder {
        protected static CmpNotifyManager instance = new CmpNotifyManager();
    }

    private CmpNotifyManager() {
        this.mNotifyHandlerList = new ConcurrentHashMap();
        this.mConnectChangeListenerList = new ConcurrentHashMap();
        ALog.m479d(TAG, "CmpNotifyManager registerNofityListener getPersistentConnectId,getAlcsDiscoveryConnectId,getAlcsServerConnectId");
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getPersistentConnectId(), this);
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getAlcsDiscoveryConnectId(), this);
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getAlcsServerConnectId(), this);
    }

    public static CmpNotifyManager getInstance() {
        return InstanceHolder.instance;
    }

    public String getRegistedTopic(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String substring = (str.equalsIgnoreCase(ConnectSDK.getInstance().getPersistentConnectId()) && str2.contains(TmpConstant.MQTT_TOPIC_PREFIX)) ? str2.substring(str2.indexOf(TmpConstant.MQTT_TOPIC_PREFIX)) : str2;
        ALog.m479d(TAG, "getRegistedTopic connectId:" + str + " topic:" + str2 + " realTopic:" + substring);
        return substring;
    }

    public void addConnectStateChangeListener(String str, IDevStateChangeListener iDevStateChangeListener) {
        Map<Integer, IDevStateChangeListener> map = this.mConnectChangeListenerList.get(str);
        if (map == null) {
            map = new ConcurrentHashMap<>();
            this.mConnectChangeListenerList.put(str, map);
        }
        map.put(Integer.valueOf(iDevStateChangeListener.hashCode()), iDevStateChangeListener);
    }

    public void removeConnectStateChangeListener(String str, IDevStateChangeListener iDevStateChangeListener) {
        Map<Integer, IDevStateChangeListener> map = this.mConnectChangeListenerList.get(str);
        if (map == null) {
            return;
        }
        map.remove(Integer.valueOf(iDevStateChangeListener.hashCode()));
    }

    public void addHandler(int i, String str, String str2, IConnectNotifyListener iConnectNotifyListener) {
        ALog.m479d(TAG, "addHandler connectId:" + str + " topic:" + str2 + " handler:" + iConnectNotifyListener + " ownerId:" + i);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ALog.m484w(TAG, "addHandler connectId or topic null");
            return;
        }
        Map<String, Map<String, IConnectNotifyListener>> map = this.mNotifyHandlerList.get(Integer.valueOf(i));
        if (map == null) {
            map = new ConcurrentHashMap<>();
            this.mNotifyHandlerList.put(Integer.valueOf(i), map);
        }
        Map<String, IConnectNotifyListener> map2 = map.get(str);
        if (map2 == null) {
            map2 = new HashMap<>();
            map.put(str, map2);
        }
        map2.put(str2, iConnectNotifyListener);
    }

    public void removeHandler(int i, String str, String str2) {
        Map<String, IConnectNotifyListener> map;
        ALog.m479d(TAG, "removeHandler connectId:" + str + " topic:" + str2 + " ownerId:" + i);
        Map<String, Map<String, IConnectNotifyListener>> map2 = this.mNotifyHandlerList.get(Integer.valueOf(i));
        if (map2 == null || (map = map2.get(str)) == null) {
            return;
        }
        map.remove(str2);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onNotify(String str, String str2, AMessage aMessage) {
        ALog.m479d(TAG, "onNotify connectId:" + str + " topic:" + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ALog.m484w(TAG, "onNotify null");
            return;
        }
        String registedTopic = getRegistedTopic(str, str2);
        Map<Integer, Map<String, Map<String, IConnectNotifyListener>>> map = this.mNotifyHandlerList;
        if (map == null || map.isEmpty()) {
            ALog.m484w(TAG, "mNotifyHandlerList null");
            return;
        }
        Iterator<Map.Entry<Integer, Map<String, Map<String, IConnectNotifyListener>>>> it = this.mNotifyHandlerList.entrySet().iterator();
        while (it.hasNext()) {
            Map<String, IConnectNotifyListener> map2 = it.next().getValue().get(str);
            if (map2 != null) {
                IConnectNotifyListener iConnectNotifyListener = map2.get(registedTopic);
                ALog.m479d(TAG, "onNotify handler:" + iConnectNotifyListener + " realTopic:" + registedTopic);
                if (iConnectNotifyListener != null) {
                    iConnectNotifyListener.onNotify(str, registedTopic, aMessage);
                }
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public boolean shouldHandle(String str, String str2) {
        ALog.m479d(TAG, "shouldHandle connectId:" + str + " topic:" + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            ALog.m484w(TAG, "shouldHandle null");
            return false;
        }
        getRegistedTopic(str, str2);
        Map<Integer, Map<String, Map<String, IConnectNotifyListener>>> map = this.mNotifyHandlerList;
        return (map == null || map.isEmpty()) ? false : true;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onConnectStateChange(String str, ConnectState connectState) {
        ALog.m479d(TAG, "onConnectStateChange s:" + str + " connectState:" + connectState);
        Map<Integer, IDevStateChangeListener> map = this.mConnectChangeListenerList.get(str);
        if (map == null || map.isEmpty()) {
            ALog.m479d(TAG, "onConnectStateChange devStateChangeListeners null");
            return;
        }
        Iterator it = new HashMap(map).entrySet().iterator();
        while (it.hasNext()) {
            ((IDevStateChangeListener) ((Map.Entry) it.next()).getValue()).onDevStateChange(TmpEnum.DeviceState.createConnectState(connectState));
        }
    }
}
