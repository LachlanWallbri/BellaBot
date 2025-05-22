package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.UpdateBlackListHandler;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.UpdatePrefixHandler;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class UpdateSvrInfoTask extends DeviceAsyncTask<UpdateSvrInfoTask> {
    protected static final String TAG = "[Tmp]UpdateSvrInfoTask";
    protected UpdateBlackListHandler mBKListHandler;
    protected UpdatePrefixHandler mPrefixHandler;

    public UpdateSvrInfoTask(DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener, UpdateBlackListHandler updateBlackListHandler, UpdatePrefixHandler updatePrefixHandler) {
        super(null, iDevListener);
        setDeviceBasicData(deviceBasicData);
        setConfig(deviceConfig);
        this.mBKListHandler = updateBlackListHandler;
        this.mPrefixHandler = updatePrefixHandler;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        if (this.mConfig != null && (this.mConfig instanceof DefaultServerConfig)) {
            ALog.m479d(TAG, "action mConfig DefaultServerConfig true");
            updatePrefx();
            updateBlackList();
        }
        taskSuccess(null, null);
        return true;
    }

    protected void updatePrefx() {
        ALog.m479d(TAG, "updatePrefx start");
        CloudUtils.subPrefixUpdateRrpc(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName(), this.mPrefixHandler);
    }

    protected void updateBlackList() {
        ALog.m479d(TAG, "updateBlackList start");
        CloudUtils.subBlacklistUpdateRrpc(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName(), this.mBKListHandler);
    }
}
