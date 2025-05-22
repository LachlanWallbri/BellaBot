package com.iflytek.cloud.thirdparty;

import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.msc.util.AppInfoUtil;
import com.iflytek.cloud.msc.util.DataUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.speech.UtilityConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.cloud.thirdparty.u */
/* loaded from: classes3.dex */
public class C3742u {

    /* renamed from: a */
    JSONObject f3429a = new JSONObject();

    /* renamed from: b */
    long f3430b = 0;

    /* renamed from: c */
    long f3431c = 0;

    /* renamed from: a */
    public void m2255a(C3692ad c3692ad) {
        this.f3431c = System.currentTimeMillis();
        this.f3430b = SystemClock.elapsedRealtime();
        m2258a("app_start", DataUtil.getComplexDateTime(this.f3431c), false);
        String m1833e = c3692ad.m1833e(UtilityConfig.KEY_CALLER_APPID);
        if (!TextUtils.isEmpty(m1833e)) {
            m2258a("app_caller_appid", m1833e, false);
        }
        String m1833e2 = AppInfoUtil.getAppInfo(null).m1833e(AppInfoUtil.APP_VER_CODE);
        if (TextUtils.isEmpty(m1833e2)) {
            return;
        }
        m2258a("app_cver", m1833e2, false);
    }

    /* renamed from: a */
    public synchronized void m2256a(String str) {
        m2257a(str, SystemClock.elapsedRealtime() - this.f3430b, false);
    }

    /* renamed from: a */
    public synchronized void m2257a(String str, long j, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (!z) {
                this.f3429a.put(str, j);
            } else {
                JSONArray optJSONArray = this.f3429a.optJSONArray(str);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                    this.f3429a.put(str, optJSONArray);
                }
                if (optJSONArray != null) {
                    optJSONArray.put(j);
                }
            }
        } catch (JSONException e) {
            DebugLog.LogE(e);
        }
    }

    /* renamed from: a */
    public synchronized void m2258a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            if (!z) {
                this.f3429a.put(str, str2);
            } else {
                JSONArray optJSONArray = this.f3429a.optJSONArray(str);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                    this.f3429a.put(str, optJSONArray);
                }
                if (optJSONArray != null) {
                    optJSONArray.put(str2);
                }
            }
        } catch (JSONException e) {
            DebugLog.LogE(e);
        }
    }

    /* renamed from: a */
    public synchronized String m2254a() {
        return this.f3429a.toString();
    }
}
