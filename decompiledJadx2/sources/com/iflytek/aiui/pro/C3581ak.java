package com.iflytek.aiui.pro;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ak */
/* loaded from: classes.dex */
public class C3581ak {

    /* renamed from: a */
    HashMap<String, String> f2319a = new HashMap<>();

    public C3581ak() {
    }

    public C3581ak(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null && !TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                this.f2319a.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /* renamed from: a */
    public String m1015a(String str) {
        return this.f2319a.get(str);
    }

    /* renamed from: a */
    public void m1016a() {
        this.f2319a.clear();
    }

    /* renamed from: a */
    public void m1017a(String str, String str2) {
        m1018a(str, str2, true);
    }

    /* renamed from: a */
    public void m1018a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (z || !this.f2319a.containsKey(str)) {
            this.f2319a.put(str, str2);
        }
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C3581ak clone() {
        C3581ak c3581ak = new C3581ak();
        c3581ak.f2319a = (HashMap) this.f2319a.clone();
        return c3581ak;
    }

    /* renamed from: b */
    public String m1020b(String str, String str2) {
        String str3 = this.f2319a.get(str);
        return str3 == null ? str2 : str3;
    }

    /* renamed from: c */
    public HashMap<String, String> m1021c() {
        return this.f2319a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.f2319a.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(entry.getValue());
            stringBuffer.append(",");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        String stringBuffer2 = stringBuffer.toString();
        C3589as.m1065c(stringBuffer2);
        return stringBuffer2;
    }
}
