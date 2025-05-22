package com.aliyun.alink.linksdk.tools.tracker;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class BaseSdkTrack implements ISDKTracker, IValidityChecker {
    private boolean hasCheckSupport = false;
    protected boolean isSupportTrack = false;

    public boolean isSupport() {
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tools.tracker.ISDKTracker
    public void sendEvent(String str, Map<String, String> map) {
        synchronized (this) {
            if (!this.hasCheckSupport) {
                this.hasCheckSupport = true;
                this.isSupportTrack = isSupport();
            }
        }
    }
}
