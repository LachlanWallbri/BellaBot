package com.aliyun.alink.linksdk.tmp.service;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.aliyun.alink.linksdk.tmp.data.p044ut.PerformancePointData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class UTPoint {
    private static final String PerformanceTag = "PerformanceTag";
    private static final String TAG = "[Tmp]UTPoint";
    private PerformancePointData mPerformancePointData;

    public UTPoint(String str, String str2, String str3, String str4) {
        this.mPerformancePointData = new PerformancePointData(str, str2, str3, str4);
    }

    public UTPoint(PerformancePointData performancePointData) {
        this.mPerformancePointData = performancePointData;
    }

    public void trackStart() {
        PerformancePointData performancePointData = this.mPerformancePointData;
        if (performancePointData == null || TextUtils.isEmpty(performancePointData.f1022id)) {
            return;
        }
        this.mPerformancePointData.event = TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ;
        ALog.m479d(TAG, "trackStart toJSONString start");
        ALog.m483i("PerformanceTag", getPerformanceLog(this.mPerformancePointData));
    }

    public void trackEnd() {
        PerformancePointData performancePointData = this.mPerformancePointData;
        if (performancePointData == null || TextUtils.isEmpty(performancePointData.f1022id)) {
            return;
        }
        this.mPerformancePointData.event = TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES;
        ALog.m479d(TAG, "trackEnd toJSONString start");
        ALog.m483i("PerformanceTag", getPerformanceLog(this.mPerformancePointData));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static UTPoint createUTPoint(Map<String, Object> map, String str) {
        String str2;
        Object obj;
        if (map == null) {
            return null;
        }
        try {
            obj = map.get(TmpConstant.KEY_IOT_PERFORMANCE_ID);
        } catch (Exception unused) {
        }
        if (obj != null) {
            str2 = String.valueOf(obj);
            if (!TextUtils.isEmpty(str2)) {
                return null;
            }
            PerformancePointData performancePointData = new PerformancePointData();
            performancePointData.method = str;
            performancePointData.event = TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ;
            performancePointData.mod = "tmp";
            performancePointData.f1022id = str2;
            return new UTPoint(performancePointData);
        }
        str2 = null;
        if (!TextUtils.isEmpty(str2)) {
        }
    }

    public static String getPerformanceLog(PerformancePointData performancePointData) {
        if (performancePointData == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", performancePointData.f1022id);
        hashMap.put("mod", performancePointData.mod);
        hashMap.put("method", performancePointData.method);
        hashMap.put("event", performancePointData.event);
        return JSON.toJSONString(hashMap);
    }
}
