package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectFactory;
import com.aliyun.alink.linksdk.tmp.data.auth.ServerEncryptInfo;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.payload.cloud.PrefixGetPayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CreateServerConnectTask extends CreateConnectTask {
    protected String mSecondConnectId;

    public CreateServerConnectTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(deviceImpl, deviceBasicData, deviceConfig, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        DefaultServerConfig defaultServerConfig = (DefaultServerConfig) this.mConfig;
        if (defaultServerConfig == null) {
            taskError(null, null);
            return true;
        }
        ALog.m479d("[Tmp]CreateConnectTask", "getConnectType:" + defaultServerConfig.getConnectType());
        if (defaultServerConfig.getConnectType() == DefaultServerConfig.ConnectType.COAP) {
            if (!TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).mIotProductKey) && ((TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).getPrefix()) || TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).getSecret())) && !TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).mIotDeviceName))) {
                queryPrefx();
            } else {
                onFail(this.mTag, null);
            }
        } else if (defaultServerConfig.getConnectType() == DefaultServerConfig.ConnectType.MQTT) {
            this.mConnectId = ConnectSDK.getInstance().getPersistentConnectId();
            onSuccess(this.mTag, null);
        } else if (defaultServerConfig.getConnectType() == DefaultServerConfig.ConnectType.COAP_AND_MQTT) {
            this.mConnectId = ConnectSDK.getInstance().getPersistentConnectId();
            if (!TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).mIotProductKey) && ((TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).getPrefix()) || TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).getSecret())) && !TextUtils.isEmpty(((DefaultServerConfig) this.mConfig).mIotDeviceName))) {
                queryPrefx();
            } else {
                onFail(this.mTag, null);
            }
        } else {
            createConnect();
        }
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateConnectTask
    protected void createConnect() {
        if (this.mConnect == null) {
            ALog.m479d("[Tmp]CreateConnectTask", "createConnect this :" + this);
            if (TextUtils.isEmpty(this.mConnectId)) {
                this.mConnectId = ConnectFactory.createConnectId(this.mDeviceBasicData, this.mConfig, this);
                return;
            } else {
                this.mSecondConnectId = ConnectFactory.createConnectId(this.mDeviceBasicData, this.mConfig, this);
                return;
            }
        }
        onSuccess(null, null);
    }

    protected void queryPrefx() {
        ALog.m479d("[Tmp]CreateConnectTask", "queryPrefx start");
        DefaultServerConfig defaultServerConfig = (DefaultServerConfig) this.mConfig;
        final String combineStr = TextHelper.combineStr(defaultServerConfig.mIotProductKey, defaultServerConfig.mIotDeviceName);
        ServerEncryptInfo serverEnptInfo = TmpStorage.getInstance().getServerEnptInfo(combineStr);
        if (serverEnptInfo != null) {
            defaultServerConfig.setPrefix(serverEnptInfo.mPrefix);
            defaultServerConfig.setSecret(serverEnptInfo.mSecret);
            createConnect();
            return;
        }
        CloudUtils.queryPrefixSecret(this.mConfig.getBasicData().getProductKey(), this.mConfig.getBasicData().getDeviceName(), new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateServerConnectTask.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                ALog.m479d("[Tmp]CreateConnectTask", "registerPersistentConnect send onResponse:" + aResponse);
                if (aResponse != null && aResponse.data != null) {
                    PrefixGetPayload prefixGetPayload = (PrefixGetPayload) GsonUtils.fromJson(aResponse.data.toString(), new TypeToken<PrefixGetPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateServerConnectTask.1.1
                    }.getType());
                    if (prefixGetPayload != null && prefixGetPayload.data != null) {
                        DefaultServerConfig defaultServerConfig2 = (DefaultServerConfig) CreateServerConnectTask.this.mConfig;
                        defaultServerConfig2.setPrefix(prefixGetPayload.data.prefix);
                        defaultServerConfig2.setSecret(prefixGetPayload.data.deviceSecret);
                        TmpStorage.getInstance().saveServerEnptInfo(combineStr, prefixGetPayload.data.prefix, prefixGetPayload.data.deviceSecret);
                    }
                } else {
                    ALog.m480e("[Tmp]CreateConnectTask", "registerPersistentConnect send onResponse error");
                    ServerEncryptInfo serverEnptInfo2 = TmpStorage.getInstance().getServerEnptInfo(CreateServerConnectTask.this.mConfig.getDevId(), "local");
                    if (serverEnptInfo2 != null && !TextUtils.isEmpty(serverEnptInfo2.mPrefix) && !TextUtils.isEmpty(serverEnptInfo2.mSecret)) {
                        DefaultServerConfig defaultServerConfig3 = (DefaultServerConfig) CreateServerConnectTask.this.mConfig;
                        defaultServerConfig3.setPrefix(serverEnptInfo2.mPrefix);
                        defaultServerConfig3.setSecret(serverEnptInfo2.mSecret);
                    }
                }
                CreateServerConnectTask.this.createConnect();
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m480e("[Tmp]CreateConnectTask", "queryPrefx onResponse  error:" + aError);
                ServerEncryptInfo serverEnptInfo2 = TmpStorage.getInstance().getServerEnptInfo(CreateServerConnectTask.this.mConfig.getDevId(), "local");
                if (serverEnptInfo2 != null && !TextUtils.isEmpty(serverEnptInfo2.mPrefix) && !TextUtils.isEmpty(serverEnptInfo2.mSecret)) {
                    DefaultServerConfig defaultServerConfig2 = (DefaultServerConfig) CreateServerConnectTask.this.mConfig;
                    defaultServerConfig2.setPrefix(serverEnptInfo2.mPrefix);
                    defaultServerConfig2.setSecret(serverEnptInfo2.mSecret);
                }
                CreateServerConnectTask.this.createConnect();
            }
        });
    }
}
