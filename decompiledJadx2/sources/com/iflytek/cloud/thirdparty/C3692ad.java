package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.iflytek.cloud.thirdparty.ad */
/* loaded from: classes3.dex */
public class C3692ad {

    /* renamed from: a */
    HashMap<String, String> f2975a = new HashMap<>();

    public C3692ad() {
    }

    public C3692ad(String str, String[][] strArr) {
        m1821a(str);
        m1824a(strArr);
    }

    /* renamed from: a */
    public void m1818a() {
        this.f2975a.clear();
    }

    /* renamed from: a */
    public void m1821a(String str) {
        this.f2975a.clear();
        m1828b(str);
    }

    /* renamed from: b */
    public void m1828b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split(",")) {
            int indexOf = str2.indexOf("=");
            if (indexOf > 0 && indexOf < str2.length()) {
                this.f2975a.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
            }
        }
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C3692ad clone() {
        C3692ad c3692ad = new C3692ad();
        c3692ad.f2975a = (HashMap) this.f2975a.clone();
        return c3692ad;
    }

    /* renamed from: a */
    public void m1822a(String str, String str2) {
        m1823a(str, str2, true);
    }

    /* renamed from: a */
    public void m1820a(C3692ad c3692ad, String str) {
        if (c3692ad == null) {
            return;
        }
        m1822a(str, c3692ad.m1833e(str));
    }

    /* renamed from: c */
    public HashMap<String, String> m1830c() {
        return this.f2975a;
    }

    /* renamed from: a */
    public void m1819a(C3692ad c3692ad) {
        if (c3692ad != null) {
            this.f2975a.putAll(c3692ad.m1830c());
        }
    }

    /* renamed from: a */
    public void m1823a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (z || !this.f2975a.containsKey(str)) {
            this.f2975a.put(str, str2);
        }
    }

    /* renamed from: c */
    public Boolean m1829c(String str) {
        return Boolean.valueOf(this.f2975a.remove(str) != null);
    }

    /* renamed from: d */
    public String m1831d(String str) {
        return this.f2975a.remove(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.f2975a.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(entry.getValue());
            stringBuffer.append(",");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        String stringBuffer2 = stringBuffer.toString();
        DebugLog.LogS(stringBuffer2);
        return stringBuffer2;
    }

    /* renamed from: e */
    public String m1833e(String str) {
        return this.f2975a.get(str);
    }

    /* renamed from: b */
    public String m1827b(String str, String str2) {
        String str3 = this.f2975a.get(str);
        return str3 == null ? str2 : str3;
    }

    /* renamed from: a */
    public int m1816a(String str, int i) {
        return m1812b(this.f2975a.get(str), i);
    }

    /* renamed from: a */
    public long m1817a(String str, long j) {
        return m1813b(this.f2975a.get(str), j);
    }

    /* renamed from: a */
    public boolean m1825a(String str, boolean z) {
        return m1814b(this.f2975a.get(str), z);
    }

    /* renamed from: a */
    public void m1824a(String[][] strArr) {
        if (strArr == null) {
            return;
        }
        for (String[] strArr2 : strArr) {
            if (this.f2975a.containsKey(strArr2[0])) {
                String str = this.f2975a.get(strArr2[0]);
                this.f2975a.remove(strArr2[0]);
                for (int i = 1; i < strArr2.length; i++) {
                    this.f2975a.put(strArr2[i], str);
                }
            }
        }
    }

    /* renamed from: d */
    public void m1832d() {
        for (Map.Entry<String, String> entry : this.f2975a.entrySet()) {
            entry.setValue(m1815f(entry.getValue()));
        }
    }

    /* renamed from: f */
    public static String m1815f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replaceAll("[,\n ]", "|");
    }

    /* renamed from: g */
    public boolean m1834g(String str) {
        return this.f2975a.containsKey(str);
    }

    /* renamed from: b */
    public static boolean m1814b(String str, boolean z) {
        if (str == null) {
            return z;
        }
        if (str.equals("true") || str.equals("1")) {
            return true;
        }
        if (str.equals("false") || str.equals("0")) {
            return false;
        }
        return z;
    }

    /* renamed from: b */
    public static int m1812b(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    /* renamed from: b */
    public static long m1813b(String str, long j) {
        if (str == null) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j;
        }
    }
}
