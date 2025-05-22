package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import com.aliyun.alink.linksdk.cmp.manager.connect.IRegisterConnectListener;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CreateMqttConnectTask extends DeviceAsyncTask<CreateMqttConnectTask> {
    protected static final String TAG = "[Tmp]CreateMqttConnectTask";

    public CreateMqttConnectTask(DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(null, iDevListener);
        setConfig(deviceConfig);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        if (this.mConfig != null && (this.mConfig instanceof DefaultServerConfig)) {
            ALog.m479d(TAG, "action mConfig");
            DefaultServerConfig defaultServerConfig = (DefaultServerConfig) this.mConfig;
            CloudUtils.registerPersistentConnect(defaultServerConfig.mIotProductKey, defaultServerConfig.mIotDeviceName, defaultServerConfig.mIotSecret, new IRegisterConnectListener() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateMqttConnectTask.1
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
                public void onSuccess() {
                    ALog.m479d(CreateMqttConnectTask.TAG, "action onSuccess");
                    CreateMqttConnectTask.this.taskSuccess(null, null);
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
                public void onFailure(AError aError) {
                    ALog.m480e(CreateMqttConnectTask.TAG, "action onFailure aError:" + aError);
                    CreateMqttConnectTask.this.taskSuccess(null, null);
                }
            });
            return true;
        }
        ALog.m479d(TAG, "action mConfig null or not server return ");
        taskSuccess(null, null);
        return true;
    }
}
