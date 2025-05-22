package com.aliyun.alink.linksdk.tools.tracker;

import android.app.Application;
import com.alibaba.fastjson.JSON;
import com.aliyun.alink.business.ut.UTBusiness;
import com.aliyun.alink.business.ut.UTHelper;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class UTSdkTracker extends BaseSdkTrack {
    private static final String APPKEY = "23646633";
    private static final String TAG = "UTSdkTracker";

    public UTSdkTracker() {
    }

    public UTSdkTracker(Application application) {
        init(application);
    }

    public void init(Application application) {
        if (isSupport()) {
            UTHelper.initSDK(application);
        }
    }

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.IValidityChecker
    public boolean isSupport() {
        try {
            Class.forName("com.aliyun.alink.business.ut.UTBusiness");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.ISDKTracker
    public void sendEvent(String str, Map<String, String> map) {
        try {
            super.sendEvent(str, map);
            if (this.isSupportTrack) {
                String str2 = "{}";
                if (map != null && map.size() >= 0) {
                    try {
                        str2 = JSON.toJSONString(map);
                    } catch (Exception unused) {
                    }
                }
                UTBusiness.sendUTEventByAppKey(str, (String) null, "0", str2, APPKEY);
            }
        } catch (Exception e) {
            ALog.m479d(TAG, "sendEvent error," + e.toString());
        }
    }
}
