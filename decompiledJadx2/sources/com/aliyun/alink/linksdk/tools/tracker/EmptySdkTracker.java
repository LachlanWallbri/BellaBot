package com.aliyun.alink.linksdk.tools.tracker;

import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class EmptySdkTracker extends BaseSdkTrack {
    private static final String TAG = "EmptySdkTracker";

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.IValidityChecker
    public boolean isSupport() {
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.ISDKTracker
    public void sendEvent(String str, Map<String, String> map) {
        super.sendEvent(str, map);
        if (this.isSupportTrack) {
            ALog.m479d(TAG, "sendEvent(),name = " + str);
        }
    }
}
