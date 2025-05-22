package com.aliyun.alink.linksdk.tmp.device.asynctask.setup;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectInfo;
import com.aliyun.alink.linksdk.cmp.core.base.AConnectInfo;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DefaultProvisionConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.data.auth.AuthPairData;
import com.aliyun.alink.linksdk.tmp.data.auth.ProvisionAuthData;
import com.aliyun.alink.linksdk.tmp.data.auth.SetupData;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.payload.cloud.PrefixGetPayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.AuthInfoCreater;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ChgPrvsRcerAuthTask extends SetupTask {
    private static final String TAG = "[Tmp]ChgPrvsRcerAuthTask";
    private AuthPairData mAuthInfo;
    private DefaultProvisionConfig mConfig;

    public ChgPrvsRcerAuthTask(DeviceImpl deviceImpl, Object obj, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(obj, deviceBasicData, deviceImpl, iDevListener);
        setDeviceImpl(deviceImpl);
        if (DeviceConfig.DeviceType.PROVISION == deviceConfig.getDeviceType()) {
            this.mConfig = (DefaultProvisionConfig) deviceConfig;
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.setup.SetupTask, com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        ALog.m479d(TAG, "ChgPrvsRcerAuthTask action");
        DefaultProvisionConfig defaultProvisionConfig = this.mConfig;
        if (defaultProvisionConfig == null || !defaultProvisionConfig.IsAutoChangeAuth()) {
            ALog.m479d(TAG, "action config not IsAutoChangeAuth or not default");
            taskSuccess((TmpCommonRequest) null, (TmpCommonResponse) null);
            return true;
        }
        if (DefaultProvisionConfig.AuthChangeType.LocalAuth == this.mConfig.getAuthChangeType()) {
            AuthPairData createAuthInfo = AuthInfoCreater.getInstance().createAuthInfo(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName(), "0");
            this.mAuthInfo = createAuthInfo;
            if (createAuthInfo == null) {
                ALog.m479d(TAG, "action mAuthInfo null");
                taskSuccess((TmpCommonRequest) null, (TmpCommonResponse) null);
                return true;
            }
            return setupServerAuthInfo();
        }
        if (DefaultProvisionConfig.AuthChangeType.CloudAuth == this.mConfig.getAuthChangeType()) {
            CloudUtils.registerPersistentConnect(null, null, null, null);
            AConnectInfo connectInfo = ConnectSDK.getInstance().getConnectInfo(ConnectSDK.getInstance().getPersistentConnectId());
            if (connectInfo == null || !(connectInfo instanceof PersistentConnectInfo)) {
                ALog.m480e(TAG, "PersistentConnectInfo empty");
                taskError(null, new ErrorInfo(301, "param is invalid"));
                return false;
            }
            PersistentConnectInfo persistentConnectInfo = (PersistentConnectInfo) connectInfo;
            CloudUtils.queryPrefixSecret(persistentConnectInfo.productKey, persistentConnectInfo.deviceName, this.mConfig.getBasicData().getProductKey(), this.mConfig.getBasicData().getDeviceName(), new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.setup.ChgPrvsRcerAuthTask.1
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    ALog.m479d(ChgPrvsRcerAuthTask.TAG, "registerPersistentConnect queryPrefixSecret send onResponse:" + aResponse);
                    if (aResponse == null || aResponse.data == null) {
                        ChgPrvsRcerAuthTask.this.taskError(null, new ErrorInfo(300, "param is invalid"));
                        return;
                    }
                    PrefixGetPayload prefixGetPayload = (PrefixGetPayload) GsonUtils.fromJson(aResponse.data.toString(), new TypeToken<PrefixGetPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.setup.ChgPrvsRcerAuthTask.1.1
                    }.getType());
                    if (prefixGetPayload == null || prefixGetPayload.data == null || TextUtils.isEmpty(prefixGetPayload.data.prefix) || TextUtils.isEmpty(prefixGetPayload.data.deviceSecret)) {
                        ChgPrvsRcerAuthTask.this.taskError(null, new ErrorInfo(300, "param is invalid"));
                        return;
                    }
                    ChgPrvsRcerAuthTask.this.mAuthInfo = new AuthPairData(null, null, prefixGetPayload.data.prefix, prefixGetPayload.data.deviceSecret);
                    ChgPrvsRcerAuthTask.this.setupServerAuthInfo();
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    ALog.m480e(ChgPrvsRcerAuthTask.TAG, "queryPrefx onResponse  error:" + aError);
                    ChgPrvsRcerAuthTask.this.taskError(null, aError == null ? null : new ErrorInfo(aError.getCode(), aError.getMsg()));
                }
            });
        } else {
            ALog.m480e(TAG, "ChgPrvsRcerAuthTask action getAuthChangeType error :" + this.mConfig.getAuthChangeType());
            taskSuccess((TmpCommonRequest) null, (TmpCommonResponse) null);
        }
        return true;
    }

    public boolean setupServerAuthInfo() {
        this.mSetupData = new SetupData();
        this.mSetupData.configType = "ServerAuthInfo";
        ProvisionAuthData provisionAuthData = new ProvisionAuthData();
        provisionAuthData.authCode = this.mAuthInfo.authCode;
        provisionAuthData.authSecret = this.mAuthInfo.authSecret;
        provisionAuthData.productKey = this.mDeviceBasicData.getProductKey();
        provisionAuthData.deviceName = this.mDeviceBasicData.getDeviceName();
        this.mSetupData.configValue.add(provisionAuthData);
        return super.action();
    }
}
