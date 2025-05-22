package com.aliyun.alink.linksdk.tmp.device.configuration;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.config.ProvisionReceiver;
import com.aliyun.alink.linksdk.tmp.connect.ConnectFactory;
import com.aliyun.alink.linksdk.tmp.data.auth.ProvisionAuthData;
import com.aliyun.alink.linksdk.tmp.data.auth.SetupData;
import com.aliyun.alink.linksdk.tmp.data.config.LocalConfigData;
import com.aliyun.alink.linksdk.tmp.device.payload.setup.SetupRequestPayload;
import com.aliyun.alink.linksdk.tmp.listener.IProvisionListener;
import com.aliyun.alink.linksdk.tmp.listener.IProvisionResponser;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ConfigMgr {
    public static final String DEFAULT_PROVISION_AUTHCODE = "Xtau@iot";
    public static final String DEFAULT_PROVISION_AUTHKEY = "Yx3DdsyetbSezlvc";
    private static final String TAG = "[Tmp]ConfigMgr";
    private LocalConfigData mLocalConfigData;

    private ConfigMgr() {
    }

    public static ConfigMgr getInstance() {
        return Holder.mInstance;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Holder {
        private static ConfigMgr mInstance = new ConfigMgr();
    }

    public void init() {
        ProvisionReceiver.getInstance().addListener(new IProvisionListener() { // from class: com.aliyun.alink.linksdk.tmp.device.configuration.ConfigMgr.1
            @Override // com.aliyun.alink.linksdk.tmp.listener.IProvisionListener
            public void onMsg(String str, String str2, Object obj, IProvisionResponser iProvisionResponser) {
                if (TextUtils.isEmpty(str2) || !str2.endsWith(TmpConstant.PATH_SETUP)) {
                    return;
                }
                SetupRequestPayload setupRequestPayload = (SetupRequestPayload) GsonUtils.fromJson(String.valueOf(obj), new TypeToken<SetupRequestPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.configuration.ConfigMgr.1.1
                }.getType());
                if (ConfigMgr.this.saveSetupData(setupRequestPayload.getParams())) {
                    if (setupRequestPayload.getParams().configType.equalsIgnoreCase("ServerAuthInfo")) {
                        for (int i = 0; i < setupRequestPayload.getParams().configValue.size(); i++) {
                            ConnectFactory.updateAlcsServerConnectOption(setupRequestPayload.getParams().configValue.get(i).authCode, setupRequestPayload.getParams().configValue.get(i).authSecret);
                            ConnectFactory.removeAlcsServerAuthInfo("Xtau@iot");
                        }
                    }
                    iProvisionResponser.onComplete(str, null, null);
                }
            }
        });
    }

    public void setLocalConfigData(LocalConfigData localConfigData) {
        this.mLocalConfigData = localConfigData;
    }

    public LocalConfigData getLocalConfigData() {
        return this.mLocalConfigData;
    }

    public boolean saveSetupData(SetupData setupData) {
        int i = 0;
        if (setupData == null || setupData.configValue == null || setupData.configValue.isEmpty() || TextUtils.isEmpty(setupData.configType)) {
            return false;
        }
        if (setupData.configType.equalsIgnoreCase(TmpConstant.CONFIG_TYPE_CLIENT)) {
            while (i < setupData.configValue.size()) {
                ProvisionAuthData provisionAuthData = setupData.configValue.get(i);
                TmpStorage.getInstance().saveAccessInfo(provisionAuthData.getId(), provisionAuthData.accessKey, provisionAuthData.accessToken, true, "local");
                i++;
            }
            return true;
        }
        if (!setupData.configType.equalsIgnoreCase("ServerAuthInfo")) {
            return true;
        }
        while (i < setupData.configValue.size()) {
            ProvisionAuthData provisionAuthData2 = setupData.configValue.get(i);
            TmpStorage.getInstance().saveServerEnptInfo(provisionAuthData2.getId(), provisionAuthData2.authCode, provisionAuthData2.authSecret, "local");
            i++;
        }
        return true;
    }
}
