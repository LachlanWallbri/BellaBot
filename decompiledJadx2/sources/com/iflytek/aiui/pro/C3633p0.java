package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.pro.C3599b1;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.aiui.pro.p0 */
/* loaded from: classes4.dex */
public class C3633p0 {

    /* renamed from: a */
    private static Map<String, JSONObject> f2607a = new HashMap();

    /* renamed from: b */
    public static Map<String, C3599b1.a> f2608b;

    static {
        HashMap hashMap = new HashMap();
        f2608b = hashMap;
        hashMap.put(AIUIConstant.RES_TYPE_ASSETS, C3599b1.a.assets);
        f2608b.put("path", C3599b1.a.path);
        f2608b.put(TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, C3599b1.a.res);
    }

    /* renamed from: a */
    public static boolean m1472a(String str, String str2, boolean z) {
        JSONObject m1475d = m1475d(str);
        if (m1475d == null) {
            return z;
        }
        String optString = m1475d.optString(str2);
        return TextUtils.isEmpty(optString) ? z : ("0".equals(optString) || "false".equals(optString)) ? false : true;
    }

    /* renamed from: b */
    public static int m1473b(String str, String str2, int i) {
        JSONObject m1475d = m1475d(str);
        return m1475d == null ? i : m1475d.optInt(str2, i);
    }

    /* renamed from: c */
    private static String[] m1474c(String str, String str2) {
        String[] split = str.split(str2);
        if (split == null || 2 != split.length) {
            return null;
        }
        return split;
    }

    /* renamed from: d */
    public static JSONObject m1475d(String str) {
        return f2607a.get(str);
    }

    /* renamed from: e */
    public static C3599b1.a m1476e(String str) {
        return f2608b.get(str);
    }

    /* renamed from: f */
    public static String m1477f(String str, String str2, String str3) {
        JSONObject m1475d = m1475d(str);
        return m1475d == null ? str3 : m1475d.optString(str2, str3);
    }

    /* renamed from: g */
    public static C3631o0 m1478g(String str) {
        if (TextUtils.isEmpty(str)) {
            return new C3631o0();
        }
        Matcher matcher = Pattern.compile(",[ ]*[\\w\\.:]+=").matcher(str);
        HashMap hashMap = new HashMap();
        int i = 0;
        while (matcher.find()) {
            String substring = str.substring(i, matcher.start());
            int start = matcher.start();
            String[] m1474c = m1474c(substring, "=");
            if (m1474c != null) {
                hashMap.put(m1474c[0], m1474c[1]);
            }
            i = start + 1;
        }
        String[] m1474c2 = m1474c(str.substring(i), "=");
        if (m1474c2 != null) {
            hashMap.put(m1474c2[0], m1474c2[1]);
        }
        C3631o0 c3631o0 = new C3631o0();
        for (Map.Entry entry : hashMap.entrySet()) {
            c3631o0.m1467g(((String) entry.getKey()).trim(), (String) entry.getValue());
        }
        return c3631o0;
    }

    /* renamed from: h */
    public static String m1479h(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                m1480i(context, jSONObject.getJSONObject(keys.next()));
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: i */
    private static void m1480i(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has(AIUIConstant.KEY_RES_TYPE)) {
            String optString = jSONObject.optString(AIUIConstant.KEY_RES_TYPE);
            String optString2 = jSONObject.optString(AIUIConstant.KEY_RES_PATH);
            C3599b1.a m1476e = m1476e(optString);
            if (m1476e != null) {
                String[] split = optString2.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
                StringBuilder sb = new StringBuilder();
                for (String str : split) {
                    sb.append(C3599b1.m1120a(context, m1476e, str));
                    sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
                jSONObject.put(AIUIConstant.KEY_RES_PATH, sb.subSequence(0, sb.length() - 1).toString());
            }
        }
        if (jSONObject.has("preloads")) {
            JSONArray jSONArray = jSONObject.getJSONArray("preloads");
            for (int i = 0; i < jSONArray.length(); i++) {
                m1480i(context, jSONArray.optJSONObject(i));
            }
        }
        if (jSONObject.has("update_slots")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("update_slots");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                m1480i(context, jSONArray2.optJSONObject(i2));
            }
        }
    }

    /* renamed from: j */
    public static void m1481j() {
        f2607a = new HashMap();
    }

    /* renamed from: k */
    public static boolean m1482k(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || jSONObject == null) {
            return false;
        }
        if (!f2607a.containsKey(str)) {
            f2607a.put(str, jSONObject);
            return true;
        }
        JSONObject jSONObject2 = f2607a.get(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                jSONObject2.put(next, jSONObject.optString(next));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /* renamed from: l */
    public static boolean m1483l(String str) {
        if (str == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    m1482k(next, jSONObject.getJSONObject(next));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
