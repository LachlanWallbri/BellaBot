package com.iflytek.aiui.pro;

import android.content.Context;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.bc */
/* loaded from: classes.dex */
public class C3602bc {
    /* renamed from: a */
    public static JSONObject m1165a(Context context) {
        return new C3600ba(context).m1126a();
    }

    /* renamed from: a */
    public static JSONObject m1166a(boolean z, C3581ak c3581ak, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry<String, String> entry : c3581ak.m1021c().entrySet()) {
            try {
                jSONObject2.put(entry.getKey(), entry.getValue());
            } catch (JSONException unused) {
                C3589as.m1068e("CollectProc", "convert hashParam to json error");
            }
        }
        jSONObject.put(str, jSONObject2);
        return z ? jSONObject : jSONObject2;
    }

    /* renamed from: b */
    public static String m1167b(Context context) {
        JSONObject m1165a = m1165a(context);
        return m1165a != null ? m1165a.toString() : "";
    }
}
