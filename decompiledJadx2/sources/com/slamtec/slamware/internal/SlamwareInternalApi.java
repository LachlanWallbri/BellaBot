package com.slamtec.slamware.internal;

import com.slamtec.slamware.AbstractSlamwarePlatform;
import com.slamtec.slamware.SlamwareCorePlatform;
import com.slamtec.slamware.diagnosis.IMessageLogFetcher;
import com.slamtec.slamware.sdp.SlamwareSdpPlatform;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
class SlamwareInternalApi {
    public static native IMessageLogFetcher createMessageLogFetcher(SlamwareSdpPlatform slamwareSdpPlatform);

    public static native SysLogsFetchResult fetchSysLog(SysLogsFetchArg sysLogsFetchArg);

    SlamwareInternalApi() {
    }

    static {
        System.loadLibrary("rpsdk");
    }

    public static IMessageLogFetcher createMessageLogFetcher(AbstractSlamwarePlatform abstractSlamwarePlatform) {
        SlamwareCorePlatform slamwareCorePlatform;
        if (abstractSlamwarePlatform == null || !SlamwareCorePlatform.class.isInstance(abstractSlamwarePlatform) || (slamwareCorePlatform = (SlamwareCorePlatform) abstractSlamwarePlatform) == null) {
            return null;
        }
        return createMessageLogFetcher(slamwareCorePlatform.getPlatformImpl());
    }
}
