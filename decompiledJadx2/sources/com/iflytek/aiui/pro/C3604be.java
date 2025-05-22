package com.iflytek.aiui.pro;

import android.content.Context;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.aiui.pro.be */
/* loaded from: classes3.dex */
public class C3604be {
    /* renamed from: a */
    public static JSONObject m1206a(Context context) {
        return new C3602bc(context).a();
    }

    /* renamed from: a */
    public static JSONObject m1207a(boolean z, C3583am c3583am, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry entry : c3583am.m1043c().entrySet()) {
            try {
                jSONObject2.put((String) entry.getKey(), entry.getValue());
            } catch (JSONException unused) {
                C3591au.f("CollectProc", "convert hashParam to json error");
            }
        }
        jSONObject.put(str, jSONObject2);
        return z ? jSONObject : jSONObject2;
    }

    /* renamed from: b */
    public static String m1208b(Context context) {
        JSONObject m1206a = m1206a(context);
        return m1206a != null ? m1206a.toString() : "";
    }
}
