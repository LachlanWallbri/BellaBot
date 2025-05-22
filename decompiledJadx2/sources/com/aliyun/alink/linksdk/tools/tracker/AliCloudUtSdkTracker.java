package com.aliyun.alink.linksdk.tools.tracker;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.aliyun.alink.linksdk.tools.ALog;
import com.ut.mini.IUTApplication;
import com.ut.mini.UTAnalytics;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import com.ut.mini.crashhandler.IUTCrashCaughtListner;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AliCloudUtSdkTracker extends BaseSdkTrack {
    private static final String APPKEY = "";
    private static final String TAG = "AliCloudUtSdkTracker";
    private Context context;

    public AliCloudUtSdkTracker() {
    }

    public AliCloudUtSdkTracker(Application application) {
        this.context = application;
        init(application);
    }

    public void init(Application application) {
        if (isSupport()) {
            if (application == null) {
                Log.e(TAG, "initUTSDK failed,Context is null");
            } else {
                initUT(application);
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.ISDKTracker
    public void sendEvent(String str, Map<String, String> map) {
        super.sendEvent(str, map);
        if (!this.isSupportTrack) {
        }
    }

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.IValidityChecker
    public boolean isSupport() {
        try {
            Class.forName("com.ut.mini.UTAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private void initUT(Application application) {
        UTAnalytics.getInstance().setAppApplicationInstance(application, new IUTApplication() { // from class: com.aliyun.alink.linksdk.tools.tracker.AliCloudUtSdkTracker.1
            public String getUTChannel() {
                return null;
            }

            public IUTCrashCaughtListner getUTCrashCraughtListener() {
                return null;
            }

            public boolean isAliyunOsSystem() {
                return false;
            }

            public boolean isUTCrashHandlerDisable() {
                return false;
            }

            public boolean isUTLogEnable() {
                return false;
            }

            public String getUTAppVersion() {
                return AliCloudUtSdkTracker.this.getVersionName();
            }

            public IUTRequestAuthentication getUTRequestAuthInstance() {
                return new UTSecuritySDKRequestAuthentication("", (String) null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getVersionName() {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(this.context.getPackageName(), 0);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception e) {
            ALog.m481e(TAG, "request version name failed", e);
            return "";
        }
    }
}
