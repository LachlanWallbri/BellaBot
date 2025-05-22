package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DefaultProvisionReceiverConfig;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.data.auth.ServerEncryptInfo;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CreateProvisionReceiverConnectTask extends CreateConnectTask {
    public CreateProvisionReceiverConnectTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(deviceImpl, deviceBasicData, deviceConfig, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        String str;
        String str2;
        if (TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).getPrefix()) || TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).getSecret())) {
            DefaultProvisionReceiverConfig defaultProvisionReceiverConfig = (DefaultProvisionReceiverConfig) this.mConfig;
            ServerEncryptInfo serverEnptInfo = TmpStorage.getInstance().getServerEnptInfo(defaultProvisionReceiverConfig.getDevId());
            ServerEncryptInfo serverEnptInfo2 = TmpStorage.getInstance().getServerEnptInfo(defaultProvisionReceiverConfig.getDevId(), "local");
            if (serverEnptInfo != null && !TextUtils.isEmpty(serverEnptInfo.mPrefix) && !TextUtils.isEmpty(serverEnptInfo.mSecret)) {
                str = serverEnptInfo.mPrefix;
                str2 = serverEnptInfo.mSecret;
            } else if (serverEnptInfo2 == null || TextUtils.isEmpty(serverEnptInfo2.mPrefix) || TextUtils.isEmpty(serverEnptInfo2.mSecret)) {
                str = "Xtau@iot";
                str2 = "Yx3DdsyetbSezlvc";
            } else {
                String str3 = serverEnptInfo2.mPrefix;
                String str4 = serverEnptInfo2.mSecret;
                str = str3;
                str2 = str4;
            }
            defaultProvisionReceiverConfig.setPrefix(str);
            defaultProvisionReceiverConfig.setSecret(str2);
        }
        createConnect();
        return true;
    }
}
