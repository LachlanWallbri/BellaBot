package com.aliyun.alink.linksdk.tmp.api;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.tmp.component.pkdnconvert.DefaultDevInfoTrans;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectFactory;
import com.aliyun.alink.linksdk.tmp.data.devdetail.DevDTO;
import com.aliyun.alink.linksdk.tmp.data.devdetail.DevDetailData;
import com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTaskFlow;
import com.aliyun.alink.linksdk.tmp.device.asynctask.discovery.DiscoveryTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.discovery.RecNotifyTask;
import com.aliyun.alink.linksdk.tmp.device.configuration.Provision;
import com.aliyun.alink.linksdk.tmp.device.wrapper.ClientWrapper;
import com.aliyun.alink.linksdk.tmp.device.wrapper.ServerWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.network.NetConnected;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceManager {
    private static final String TAG = "[Tmp]DeviceManager";
    private Map<String, DeviceBasicData> mDeviceBasicDataList;
    private DiscoveryTask mDiscoveryTask;
    private RecNotifyTask mRecNotifyTask;

    private DeviceManager() {
        this.mDeviceBasicDataList = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class InstanceHolder {
        protected static DeviceManager mInstance = new DeviceManager();

        private InstanceHolder() {
        }
    }

    public static DeviceManager getInstance() {
        return InstanceHolder.mInstance;
    }

    public void discoverDevices(Object obj, boolean z, long j, Object obj2, IDiscoveryFilter iDiscoveryFilter, IDevListener iDevListener) {
        DiscoveryTask discoveryTask = this.mDiscoveryTask;
        if (discoveryTask != null) {
            discoveryTask.stop(false);
        }
        if (z) {
            this.mDeviceBasicDataList.clear();
        }
        ALog.m479d(TAG, "discoverDevices tag:" + obj + " clearCache:" + z + " timeOutInMilSec:" + j + " filter:" + iDiscoveryFilter + " handler:" + iDevListener + " extraParams:" + obj2);
        DiscoveryTask discoveryTask2 = new DiscoveryTask(ConnectFactory.createConnect(), iDevListener);
        this.mDiscoveryTask = discoveryTask2;
        discoveryTask2.setExtraParams(obj2).setTag(obj).setFilter(iDiscoveryFilter).setIsSecure(false).setTimeout(j);
        new AsyncTaskFlow().appendTask(this.mDiscoveryTask).action();
        NetConnected.startNetChangeListen();
    }

    public void discoverDevices(Object obj, boolean z, long j, IDiscoveryFilter iDiscoveryFilter, IDevListener iDevListener) {
        discoverDevices(obj, z, j, null, iDiscoveryFilter, iDevListener);
    }

    public void discoverDevices(Object obj, boolean z, long j, IDevListener iDevListener) {
        discoverDevices(obj, z, j, null, iDevListener);
    }

    public void discoverDevices(Object obj, long j, IDevListener iDevListener) {
        discoverDevices(obj, false, j, iDevListener);
    }

    public void stopDiscoverDevices() {
        DiscoveryTask discoveryTask = this.mDiscoveryTask;
        if (discoveryTask != null) {
            discoveryTask.stop(false);
            this.mDiscoveryTask = null;
        }
    }

    public void startNotifyMonitor() {
        ALog.m479d(TAG, "startNotifyMonitor");
        this.mRecNotifyTask = new RecNotifyTask(ConnectFactory.createConnect(), null);
        new AsyncTaskFlow().appendTask(this.mRecNotifyTask).action();
    }

    public void stopNotifyMonitor() {
        ALog.m479d(TAG, "stopNotifyMonitor");
        RecNotifyTask recNotifyTask = this.mRecNotifyTask;
        if (recNotifyTask != null) {
            recNotifyTask.stop();
        }
    }

    public IDevice createDevice(DeviceConfig deviceConfig) {
        if (deviceConfig == null) {
            ALog.m480e(TAG, "createDevice config empty");
            return null;
        }
        if (DeviceConfig.DeviceType.CLIENT == deviceConfig.getDeviceType()) {
            ALog.m479d(TAG, "createDevice ClientWrapper");
            return new ClientWrapper(deviceConfig);
        }
        if (DeviceConfig.DeviceType.SERVER != deviceConfig.getDeviceType()) {
            return null;
        }
        ALog.m479d(TAG, "createDevice ServerWrapper");
        return new ServerWrapper(deviceConfig);
    }

    public IProvision createProvision(DeviceConfig deviceConfig) {
        if (deviceConfig == null) {
            ALog.m480e(TAG, "createProvision config empty");
            return null;
        }
        return new Provision(deviceConfig);
    }

    public void clearAccessTokenCache() {
        TmpStorage.getInstance().clearAccessTokenCache();
    }

    public void removeDevice(String str) {
        this.mDeviceBasicDataList.remove(str);
    }

    public DeviceBasicData getDeviceBasicData(String str) {
        DeviceBasicData deviceBasicData = this.mDeviceBasicDataList.get(str);
        ALog.m479d(TAG, "getDeviceBasicData mDeviceBasicDataList:" + this.mDeviceBasicDataList.toString());
        return deviceBasicData;
    }

    public List<DeviceBasicData> getAllDeviceDataList() {
        ALog.m479d(TAG, "getAllDeviceDataList size:" + this.mDeviceBasicDataList.size());
        ArrayList arrayList = new ArrayList();
        try {
            for (DeviceBasicData deviceBasicData : this.mDeviceBasicDataList.values()) {
                StringBuilder sb = new StringBuilder();
                sb.append("getAllDeviceDataList basicData:");
                sb.append(deviceBasicData);
                ALog.m479d(TAG, sb.toString() == null ? "null" : deviceBasicData.toString());
                arrayList.add((DeviceBasicData) deviceBasicData.clone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public JSONArray getLocalAuthedDeviceDataList() {
        ALog.m479d(TAG, "getLocalAuthedDeviceDataList");
        JSONArray jSONArray = new JSONArray();
        for (DeviceBasicData deviceBasicData : getAllDeviceDataList()) {
            String devDetailInfo = TmpStorage.getInstance().getDevDetailInfo(deviceBasicData.getDevId());
            ALog.m479d(TAG, "getLocalAuthedDeviceDataList id:" + deviceBasicData.getDevId() + " detailDataInfo:" + devDetailInfo);
            DevDetailData devDetailData = (DevDetailData) GsonUtils.fromJson(devDetailInfo, new TypeToken<DevDetailData>() { // from class: com.aliyun.alink.linksdk.tmp.api.DeviceManager.1
            }.getType());
            if (devDetailData != null && devDetailData.data != null && !devDetailData.data.isEmpty()) {
                DevDTO devDTO = devDetailData.data.get(0);
                devDTO.status = 1;
                try {
                    jSONArray.put(new JSONObject(GsonUtils.toJson(devDTO)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public boolean saveDeviceDetailInfo(String str, String str2) {
        return TmpStorage.getInstance().saveDevDetailInfo(str, str2);
    }

    public boolean removeDeviceDetailInfo(String str, String str2) {
        return TmpStorage.getInstance().removeDevDetailInfo(TextHelper.combineStr(str, str2));
    }

    public boolean isDeviceDetailCache(String str) {
        String devDetailInfo = TmpStorage.getInstance().getDevDetailInfo(str);
        ALog.m479d(TAG, "isDeviceDetailCache id:" + str + " detailData:" + devDetailInfo);
        return !TextUtils.isEmpty(devDetailInfo);
    }

    public DeviceBasicData addDeviceBasicData(DeviceBasicData deviceBasicData) {
        if (deviceBasicData == null) {
            ALog.m480e(TAG, "addDeviceBasicData basicData null");
            return null;
        }
        ALog.m479d(TAG, "addDeviceBasicData basicData:" + deviceBasicData.getDevId());
        return this.mDeviceBasicDataList.put(deviceBasicData.getDevId(), deviceBasicData);
    }

    public DeviceBasicData removeDeviceBasicData(String str) {
        ALog.m479d(TAG, "removeDeviceBasicData id:" + str + " mDeviceBasicDataList:" + this.mDeviceBasicDataList.toString());
        return this.mDeviceBasicDataList.remove(str);
    }

    public void updateDeviceInfo(String str, String str2, String str3, String str4) {
        ALog.m479d(TAG, "updateDeviceInfo oldPk:" + str + " oldDn:" + str2 + " produceKey:" + str3 + " deviceName:" + str4);
        String combineStr = TextHelper.combineStr(str, str2);
        DeviceBasicData removeDeviceBasicData = removeDeviceBasicData(combineStr);
        if (removeDeviceBasicData == null) {
            ALog.m480e(TAG, "updateDeviceInfo basicData empty");
            return;
        }
        removeDeviceBasicData.setDeviceName(str4);
        removeDeviceBasicData.setProductKey(str3);
        addDeviceBasicData(removeDeviceBasicData);
        PluginMgr.getInstance().updateDiscoveryInfo(combineStr, str3, str4);
        DefaultDevInfoTrans.getInstance().updatePubDevInfo(combineStr, str3, str4);
        TmpStorage.getInstance().saveDnToMac(str4, str2);
    }

    public void addDevIotId(String str, String str2) {
        DeviceBasicData deviceBasicData = this.mDeviceBasicDataList.get(str);
        if (deviceBasicData != null) {
            deviceBasicData.setIotId(str2);
        }
    }

    public String getDevIotId(String str) {
        DeviceBasicData deviceBasicData = this.mDeviceBasicDataList.get(str);
        if (deviceBasicData != null) {
            return deviceBasicData.getIotId();
        }
        return null;
    }

    public void clearBasicDataList() {
        ALog.m479d(TAG, "clearBasicDataList");
        this.mDeviceBasicDataList.clear();
    }
}
