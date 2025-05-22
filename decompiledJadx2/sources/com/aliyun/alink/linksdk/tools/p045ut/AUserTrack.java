package com.aliyun.alink.linksdk.tools.p045ut;

import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AUserTrack {
    private static final String TAG = "AUserTrack";
    public static final String UTKEY_DEVICE_IOTID = "deviceIotId";
    public static final String UTKEY_END_TIME = "endTime";
    public static final String UTKEY_ERROR_CODE = "errorCode";
    public static final String UTKEY_EVENT_NAME = "eventName";
    public static final String UTKEY_MODULE = "module";
    public static final String UTKEY_PAGE_NAME = "pageName";
    public static final String UTKEY_PERFORMANCEID = "performanceId";
    public static final String UTKEY_START_TIME = "startTime";
    public static final String UTKEY_SUB_ERROR_CODE = "subErrorCode";
    protected static IUserTrackDispatch mDispatch;

    public static void setDispatch(IUserTrackDispatch iUserTrackDispatch) {
        ALog.m479d(TAG, "setDispatch dispatch:" + iUserTrackDispatch);
        mDispatch = iUserTrackDispatch;
    }

    public static void record(String str, Map<String, String> map) {
        IUserTrackDispatch iUserTrackDispatch = mDispatch;
        if (iUserTrackDispatch != null) {
            iUserTrackDispatch.record(str, map);
        }
    }

    public static void record(String str, long j, long j2) {
        if (mDispatch != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(UTKEY_START_TIME, String.valueOf(j));
            hashMap.put(UTKEY_END_TIME, String.valueOf(j2));
            record(str, hashMap);
        }
    }

    public static void record(String str, long j, long j2, String str2) {
        if (mDispatch != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(UTKEY_START_TIME, String.valueOf(j));
            hashMap.put(UTKEY_END_TIME, String.valueOf(j2));
            hashMap.put(UTKEY_ERROR_CODE, str2);
            record(str, hashMap);
        }
    }

    public static void record(String str, String str2, long j, long j2) {
        if (mDispatch != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(UTKEY_START_TIME, String.valueOf(j));
            hashMap.put(UTKEY_END_TIME, String.valueOf(j2));
            hashMap.put(UTKEY_PAGE_NAME, str2);
            record(str, hashMap);
        }
    }
}
