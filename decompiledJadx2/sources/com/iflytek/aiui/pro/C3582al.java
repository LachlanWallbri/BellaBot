package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.data.audio.player.PcmPlayer;
import com.iflytek.aiui.error.AIUIError;
import com.iflytek.aiui.pro.AbstractC3578ah;
import com.iflytek.aiui.pro.C3591au;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.al */
/* loaded from: classes.dex */
public class C3582al {

    /* renamed from: a */
    public static Map<String, C3591au.a> f2320a;

    /* renamed from: b */
    private static Map<String, JSONObject> f2321b = new HashMap();

    /* renamed from: com.iflytek.aiui.pro.al$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 implements PcmPlayer.PcmPlayerListener {
        AnonymousClass1() {
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onError(AIUIError aIUIError) {
            if (C3582al.j(C3582al.this) != null) {
                C3582al.k(C3582al.this).mo987a(aIUIError.getErrorCode(), aIUIError.getDes());
            }
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onPaused() {
            if (C3582al.h(C3582al.this) != null) {
                C3582al.i(C3582al.this).mo989b();
            }
            C3582al.a(C3582al.this, false);
            C3582al.c(C3582al.this, AbstractC3578ah.b.STATE_PAUSED);
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onPercent(int i, int i2, int i3) {
            if (!C3582al.e(C3582al.this)) {
                C3582al.a(C3582al.this, true);
                C3582al.b(C3582al.this, AbstractC3578ah.b.STATE_PLAYING);
            }
            if (C3582al.f(C3582al.this) != null) {
                C3582al.g(C3582al.this).mo986a(i, i2, i3);
            }
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onResume() {
            if (C3582al.c(C3582al.this) != null) {
                C3582al.d(C3582al.this).mo990c();
            }
        }

        @Override // com.iflytek.aiui.data.audio.player.PcmPlayer.PcmPlayerListener
        public void onStoped(boolean z) {
            if (C3582al.a(C3582al.this) != null) {
                C3582al.b(C3582al.this).mo988a(z);
            }
            C3582al.a(C3582al.this, false);
            C3582al.a(C3582al.this, AbstractC3578ah.b.STATE_STOPPED);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f2320a = hashMap;
        hashMap.put(C3591au.a.assets.name(), C3591au.a.assets);
        f2320a.put(C3591au.a.path.name(), C3591au.a.path);
        f2320a.put(C3591au.a.res.name(), C3591au.a.res);
    }

    /* renamed from: a */
    public static int m1022a(String str, String str2, int i) {
        JSONObject m1031c = m1031c(str);
        return m1031c == null ? i : m1031c.optInt(str2, i);
    }

    /* renamed from: a */
    public static C3591au.a m1023a(String str) {
        return f2320a.get(str);
    }

    /* renamed from: a */
    public static String m1024a(Context context, String str) {
        C3591au.a m1023a;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(keys.next());
                if (jSONObject2.has(AIUIConstant.KEY_RES_TYPE)) {
                    String optString = jSONObject2.optString(AIUIConstant.KEY_RES_TYPE);
                    String optString2 = jSONObject2.optString(AIUIConstant.KEY_RES_PATH);
                    if (optString2.indexOf(VoiceWakeuperAidl.PARAMS_SEPARATE) == -1 && (m1023a = m1023a(optString)) != null) {
                        jSONObject2.put(AIUIConstant.KEY_RES_PATH, C3591au.m1071a(context, m1023a, optString2));
                    }
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: a */
    public static String m1025a(String str, String str2, String str3) {
        JSONObject m1031c = m1031c(str);
        return m1031c == null ? str3 : m1031c.optString(str2, str3);
    }

    /* renamed from: a */
    public static void m1026a() {
        f2321b = new HashMap();
    }

    /* renamed from: a */
    public static boolean m1027a(String str, String str2, boolean z) {
        JSONObject m1031c = m1031c(str);
        if (m1031c != null) {
            String optString = m1031c.optString(str2);
            if (!TextUtils.isEmpty(optString)) {
                return ("0".equals(optString) || "false".equals(optString)) ? false : true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m1028a(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || jSONObject == null) {
            return false;
        }
        if (!f2321b.containsKey(str)) {
            f2321b.put(str, jSONObject);
            return true;
        }
        JSONObject jSONObject2 = f2321b.get(str);
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

    /* renamed from: a */
    private static String[] m1029a(String str, String str2) {
        String[] split = str.split(str2);
        if (split == null || 2 != split.length) {
            return null;
        }
        return split;
    }

    /* renamed from: b */
    public static boolean m1030b(String str) {
        if (str == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    m1028a(next, jSONObject.getJSONObject(next));
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

    /* renamed from: c */
    public static JSONObject m1031c(String str) {
        return f2321b.get(str);
    }

    /* renamed from: d */
    public static C3581ak m1032d(String str) {
        if (TextUtils.isEmpty(str)) {
            return new C3581ak();
        }
        Matcher matcher = Pattern.compile(",[ ]*[\\w\\.:]+=").matcher(str);
        HashMap hashMap = new HashMap();
        int i = 0;
        while (matcher.find()) {
            String substring = str.substring(i, matcher.start());
            int start = matcher.start() + 1;
            String[] m1029a = m1029a(substring, "=");
            if (m1029a != null) {
                hashMap.put(m1029a[0], m1029a[1]);
            }
            i = start;
        }
        String[] m1029a2 = m1029a(str.substring(i), "=");
        if (m1029a2 != null) {
            hashMap.put(m1029a2[0], m1029a2[1]);
        }
        C3581ak c3581ak = new C3581ak();
        for (Map.Entry entry : hashMap.entrySet()) {
            c3581ak.m1017a(((String) entry.getKey()).trim(), (String) entry.getValue());
        }
        return c3581ak;
    }
}
