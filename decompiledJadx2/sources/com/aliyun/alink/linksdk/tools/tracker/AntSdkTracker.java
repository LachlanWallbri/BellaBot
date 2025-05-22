package com.aliyun.alink.linksdk.tools.tracker;

import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.monitor.Performance;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AntSdkTracker extends BaseSdkTrack {
    private static final String TAG = "AntSdkTracker";

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.IValidityChecker
    public boolean isSupport() {
        try {
            Class.forName("com.alipay.mobile.common.logging.api.monitor.Performance");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // com.aliyun.alink.linksdk.tools.tracker.BaseSdkTrack, com.aliyun.alink.linksdk.tools.tracker.ISDKTracker
    public void sendEvent(String str, Map<String, String> map) {
        ALog.m479d(TAG, "sendEvent(), name = " + str);
        super.sendEvent(str, map);
        if (this.isSupportTrack) {
            Performance performance = new Performance();
            performance.setSubType("IOT");
            performance.setParam1("IOT");
            performance.setParam2("FATAL");
            performance.setParam3(str);
            performance.getExtPramas().putAll(map);
            LoggerFactory.getMonitorLogger().performance("IOT", performance);
        }
    }
}
