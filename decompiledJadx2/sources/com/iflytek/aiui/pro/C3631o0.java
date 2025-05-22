package com.iflytek.aiui.pro;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.iflytek.aiui.pro.o0 */
/* loaded from: classes4.dex */
public class C3631o0 {

    /* renamed from: a */
    HashMap<String, String> f2596a = new HashMap<>();

    /* renamed from: a */
    public void m1461a() {
        this.f2596a.clear();
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C3631o0 clone() {
        C3631o0 c3631o0 = new C3631o0();
        c3631o0.f2596a = (HashMap) this.f2596a.clone();
        return c3631o0;
    }

    /* renamed from: c */
    public HashMap<String, String> m1463c() {
        return this.f2596a;
    }

    /* renamed from: d */
    public int m1464d(String str, int i) {
        String str2 = this.f2596a.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            return Integer.parseInt(str2);
        } catch (Exception unused) {
            return i;
        }
    }

    /* renamed from: e */
    public String m1465e(String str) {
        return this.f2596a.get(str);
    }

    /* renamed from: f */
    public String m1466f(String str, String str2) {
        String str3 = this.f2596a.get(str);
        return str3 == null ? str2 : str3;
    }

    /* renamed from: g */
    public void m1467g(String str, String str2) {
        m1468h(str, str2, true);
    }

    /* renamed from: h */
    public void m1468h(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (z || !this.f2596a.containsKey(str)) {
            this.f2596a.put(str, str2);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.f2596a.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(entry.getValue());
            stringBuffer.append(",");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        String stringBuffer2 = stringBuffer.toString();
        C3651y0.m1624f(stringBuffer2);
        return stringBuffer2;
    }
}
