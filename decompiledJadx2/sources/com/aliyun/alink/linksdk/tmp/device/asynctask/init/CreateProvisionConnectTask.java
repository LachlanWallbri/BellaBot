package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DefaultProvisionConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.data.auth.AccessInfo;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.AuthInfoCreater;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CreateProvisionConnectTask extends CreateConnectTask {
    public CreateProvisionConnectTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(deviceImpl, deviceBasicData, deviceConfig, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        if (TextUtils.isEmpty(((DefaultProvisionConfig) this.mConfig).getAccessKey()) || TextUtils.isEmpty(((DefaultProvisionConfig) this.mConfig).getAccessToken())) {
            AccessInfo accessInfo = TmpStorage.getInstance().getAccessInfo(this.mConfig.getDevId());
            AccessInfo accessInfo2 = TmpStorage.getInstance().getAccessInfo(this.mConfig.getDevId(), TmpStorage.FLAG_PROVISIONER);
            DefaultProvisionConfig defaultProvisionConfig = (DefaultProvisionConfig) this.mConfig;
            if (accessInfo == null || TextUtils.isEmpty(accessInfo.mAccessKey) || TextUtils.isEmpty(accessInfo.mAccessToken)) {
                if (accessInfo2 == null || TextUtils.isEmpty(accessInfo2.mAccessKey) || TextUtils.isEmpty(accessInfo2.mAccessToken)) {
                    accessInfo = AuthInfoCreater.getInstance().createAccessInfo("Xtau@iot", "Yx3DdsyetbSezlvc", "0");
                    DeviceImpl deviceImpl = this.mDeviceImplRef.get();
                    if (deviceImpl != null) {
                        deviceImpl.setIsDefaultAuthInfo(true);
                    }
                } else {
                    accessInfo = accessInfo2;
                }
            }
            defaultProvisionConfig.setAccessKey(accessInfo.mAccessKey);
            defaultProvisionConfig.setAccessToken(accessInfo.mAccessToken);
        }
        createConnect();
        return true;
    }
}
