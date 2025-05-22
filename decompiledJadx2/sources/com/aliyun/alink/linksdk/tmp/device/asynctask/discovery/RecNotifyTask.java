package com.aliyun.alink.linksdk.tmp.device.asynctask.discovery;

import com.aliyun.alink.linksdk.cmp.manager.discovery.DiscoveryMessage;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpResponse;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RecNotifyTask extends DeviceAsyncTask<DiscoveryTask> implements INotifyHandler {
    protected static final String TAG = "[Tmp]RecNotifyTask";

    public RecNotifyTask(ConnectWrapper connectWrapper, IDevListener iDevListener) {
        super(null, iDevListener);
        setConnectWrapper(connectWrapper);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        ALog.m479d(TAG, "action startNotifyMonitor");
        if (this.mConnect == null) {
            return true;
        }
        this.mConnect.startNotifyMonitor(this);
        return true;
    }

    public boolean stop() {
        if (this.mConnect == null) {
            return true;
        }
        this.mConnect.stopNotifyMonitor();
        return true;
    }

    public void onDeviceFound(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        ALog.m479d(TAG, "onDeviceFound response:" + tmpCommonResponse + " mDeviceHandler:" + this.mDeviceHandler);
        if (tmpCommonResponse == null || tmpCommonResponse.getResponse() == null) {
            LogCat.m471e(TAG, "addDevice error response null or unsuccess");
            return;
        }
        DiscoveryMessage discoveryMessage = (DiscoveryMessage) ((CpResponse) tmpCommonResponse).getResponse().data;
        if (discoveryMessage == null) {
            ALog.m480e(TAG, "onDeviceFound discoveryMessage or deviceInfo null");
        }
        DeviceBasicData deviceBasicData = new DeviceBasicData(true);
        deviceBasicData.setProductKey(discoveryMessage.productKey);
        deviceBasicData.setDeviceName(discoveryMessage.deviceName);
        deviceBasicData.setModelType(discoveryMessage.modelType);
        DeviceManager.getInstance().addDeviceBasicData(deviceBasicData);
    }

    @Override // com.aliyun.alink.linksdk.tmp.event.INotifyHandler
    public void onMessage(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        onDeviceFound(tmpCommonRequest, tmpCommonResponse);
    }
}
