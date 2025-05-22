package com.aliyun.alink.linksdk.tools.tracker;

import com.alibaba.fastjson.JSON;
import com.aliyun.alink.business.ut.UTBusiness;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IoTUTSdkTracker extends BaseSdkTrack {
    private static final String TAG = "IoTUTSdkTracker";

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
                UTBusiness.sendUTEvent(str, (String) null, "0", str2);
            }
        } catch (Exception e) {
            ALog.m479d(TAG, "sendEvent error," + e.toString());
        }
    }
}
