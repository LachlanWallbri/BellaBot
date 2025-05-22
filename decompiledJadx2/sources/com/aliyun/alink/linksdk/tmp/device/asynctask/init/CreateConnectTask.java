package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectFactory;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CreateConnectTask extends DeviceAsyncTask<CreateConnectTask> implements IDevListener {
    protected static final String TAG = "[Tmp]CreateConnectTask";
    protected String mConnectId;

    public CreateConnectTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceImpl(deviceImpl);
        setDeviceBasicData(deviceBasicData);
        setConfig(deviceConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createConnect() {
        if (this.mConnect == null) {
            this.mConnectId = ConnectFactory.createConnectId(this.mDeviceBasicData, this.mConfig, this);
            DeviceImpl deviceImpl = this.mDeviceImplRef.get();
            ALog.m479d(TAG, "create connect connectId:" + this.mConnectId);
            if (deviceImpl == null || TextUtils.isEmpty(this.mConnectId)) {
                return;
            }
            deviceImpl.setConnect(ConnectFactory.createConnect(this.mConnectId));
            return;
        }
        onSuccess(null, null);
    }

    public void updateProDev(String str, String str2) {
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        if (deviceImpl != null) {
            deviceImpl.updateProDev(str, str2);
        }
    }

    public void onSuccess(Object obj, OutputParams outputParams) {
        ALog.m479d(TAG, "onSuccess returnValue:" + outputParams + " this :" + this + " mConnectId:" + this.mConnectId);
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        if (deviceImpl != null) {
            if (!TextUtils.isEmpty(this.mConnectId)) {
                ALog.m479d(TAG, "onSuccess mConnectId:" + this.mConnectId);
            } else if (TextUtils.isEmpty(this.mConnectId) && outputParams != null) {
                this.mConnectId = String.valueOf(outputParams.get(ConnectFactory.mAlcsConnecteId).getValue());
            } else {
                onFail(null, new ErrorInfo(300, "param is invalid"));
                ALog.m480e(TAG, "create connect fail");
                return;
            }
            ALog.m479d(TAG, "create connect connectId:" + this.mConnectId);
            deviceImpl.setConnect(ConnectFactory.createConnect(this.mConnectId));
        }
        taskSuccess(null, null);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onFail(Object obj, ErrorInfo errorInfo) {
        ALog.m479d(TAG, "onFail errorInfo:" + errorInfo);
        if (errorInfo != null && (errorInfo.getErrorCode() == 502 || errorInfo.getErrorCode() == 506 || errorInfo.getErrorCode() == 501)) {
            ALog.m479d(TAG, "onFail AUTH_ACCESS_TOKEN_INVALID clear storage");
            if (TextUtils.isEmpty(this.mConfig.getBasicData().getDevId())) {
                TmpStorage.DeviceInfo deviceInfo = TmpStorage.getInstance().getDeviceInfo(this.mConfig.getBasicData().getIotId());
                if (deviceInfo != null) {
                    TmpStorage.getInstance().saveAccessInfo(deviceInfo.getId(), "", "");
                }
            } else {
                TmpStorage.getInstance().saveAccessInfo(this.mConfig.getBasicData().getDevId(), "", "");
            }
        }
        taskError(null, errorInfo);
    }
}
