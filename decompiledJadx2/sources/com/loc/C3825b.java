package com.loc;

import android.content.Context;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* compiled from: GeoFenceNetManager.java */
/* renamed from: com.loc.b */
/* loaded from: classes4.dex */
public final class C3825b {

    /* renamed from: a */
    C3834bi f3614a;

    public C3825b() {
        this.f3614a = null;
        this.f3614a = C3834bi.m2600a();
    }

    /* renamed from: a */
    private String m2510a(Context context, String str, Map<String, String> map) {
        if (C3876cx.m2967a(C3876cx.m2997c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        C3864cl c3864cl = new C3864cl();
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.3.0");
        String m3136a = C3887m.m3136a();
        String m3137a = C3887m.m3137a(context, m3136a, C3894t.m3234b(map));
        map.put("ts", m3136a);
        map.put("scode", m3137a);
        c3864cl.m2847b(map);
        c3864cl.m2846a(hashMap);
        c3864cl.m2845a(str);
        c3864cl.m2627a(C3891q.m3194a(context));
        c3864cl.m2626a(C3880f.f4181c);
        c3864cl.m2628b(C3880f.f4181c);
        try {
            C3834bi c3834bi = this.f3614a;
            return new String(C3834bi.m2602a(c3864cl), "utf-8");
        } catch (Throwable th) {
            C3880f.m3097a(th, "GeoFenceNetManager", "post");
            return null;
        }
    }

    /* renamed from: b */
    private static Map<String, String> m2511b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap hashMap = new HashMap();
        hashMap.put(TransferTable.COLUMN_KEY, C3885k.m3127f(context));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("keywords", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("types", str2);
        }
        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str6)) {
            hashMap.put(RequestParameters.SUBRESOURCE_LOCATION, str6 + "," + str5);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("city", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(TypedValues.Cycle.S_WAVE_OFFSET, str4);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put("radius", str7);
        }
        return hashMap;
    }

    /* renamed from: a */
    public final String m2512a(Context context, String str, String str2) {
        Map<String, String> m2511b = m2511b(context, str2, null, null, null, null, null, null);
        m2511b.put("extensions", "all");
        return m2510a(context, str, m2511b);
    }

    /* renamed from: a */
    public final String m2513a(Context context, String str, String str2, String str3, String str4, String str5) {
        Map<String, String> m2511b = m2511b(context, str2, str3, str4, str5, null, null, null);
        m2511b.put("children", "1");
        m2511b.put("page", "1");
        m2511b.put("extensions", "base");
        return m2510a(context, str, m2511b);
    }

    /* renamed from: a */
    public final String m2514a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map<String, String> m2511b = m2511b(context, str2, str3, null, str4, str5, str6, str7);
        m2511b.put("children", "1");
        m2511b.put("page", "1");
        m2511b.put("extensions", "base");
        return m2510a(context, str, m2511b);
    }
}
