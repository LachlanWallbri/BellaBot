package com.amazonaws.cognito.clientcontext.datacollection;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ApplicationDataCollector extends DataCollector {
    private static final int ALL_FLAGS_OFF = 0;
    private static final String TAG = ApplicationDataCollector.class.getSimpleName();

    @Override // com.amazonaws.cognito.clientcontext.datacollection.DataCollector
    public Map<String, String> collect(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put(DataRecordKey.APP_NAME, getAppName(context));
        hashMap.put(DataRecordKey.APP_TARGET_SDK, getAppTargetSdk(context));
        hashMap.put(DataRecordKey.APP_VERSION, getAppVersion(context));
        return hashMap;
    }

    private String getAppName(Context context) {
        return (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(TAG, "Unable to get app version. Provided package name could not be found.");
            return "";
        }
    }

    private String getAppTargetSdk(Context context) {
        return String.valueOf(context.getApplicationInfo().targetSdkVersion);
    }
}
