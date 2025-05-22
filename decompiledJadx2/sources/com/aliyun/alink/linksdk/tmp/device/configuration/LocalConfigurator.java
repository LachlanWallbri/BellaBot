package com.aliyun.alink.linksdk.tmp.device.configuration;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.data.config.LocalConfigData;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.ResHelper;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LocalConfigurator {
    private static final String CONFIG_FILE_NAME = "local_config";
    private static final String TAG = "[Tmp]LocalConfigurator";
    private static final String TMP_CONFIG_DIR = "tmp_config";

    public static void start(String str) {
        File externalFilesDir;
        File file = null;
        if (TextUtils.isEmpty(str) && (externalFilesDir = TmpSdk.getContext().getExternalFilesDir(null)) != null) {
            file = new File(new File(externalFilesDir.getAbsolutePath(), TMP_CONFIG_DIR).getAbsolutePath(), CONFIG_FILE_NAME);
        }
        if (file == null) {
            file = new File(str);
        }
        if (!file.exists()) {
            ALog.m480e(TAG, "LocalConfigurator config not exist");
            return;
        }
        ALog.m479d(TAG, "LocalConfigurator start path:" + file.getAbsolutePath());
        LocalConfigData localConfigData = (LocalConfigData) GsonUtils.fromJson(ResHelper.getFileStr(file), new TypeToken<LocalConfigData>() { // from class: com.aliyun.alink.linksdk.tmp.device.configuration.LocalConfigurator.1
        }.getType());
        ConfigMgr.getInstance().setLocalConfigData(localConfigData);
        if (localConfigData != null && localConfigData.configReceiver != null && !localConfigData.configReceiver.autoRun) {
            ALog.m483i(TAG, "configReceiver not autorun");
        } else {
            ALog.m483i(TAG, "configReceiver end");
        }
    }
}
