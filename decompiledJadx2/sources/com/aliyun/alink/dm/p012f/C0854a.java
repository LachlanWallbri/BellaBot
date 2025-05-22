package com.aliyun.alink.dm.p012f;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.p017k.C0859a;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: LocalData.java */
/* renamed from: com.aliyun.alink.dm.f.a */
/* loaded from: classes.dex */
public class C0854a {

    /* renamed from: a */
    private Context f402a;

    /* renamed from: b */
    private DeviceInfo f403b;

    private C0854a() {
        this.f402a = null;
        this.f403b = null;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: LocalData.java */
    /* renamed from: com.aliyun.alink.dm.f.a$a */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a */
        private static final C0854a f404a = new C0854a();
    }

    /* renamed from: a */
    public static C0854a m116a() {
        return a.f404a;
    }

    /* renamed from: a */
    public void m118a(Context context, DeviceInfo deviceInfo) {
        this.f402a = context.getApplicationContext();
        this.f403b = deviceInfo;
    }

    /* renamed from: b */
    public Context m120b() {
        return this.f402a;
    }

    /* renamed from: c */
    public DeviceInfo m121c() {
        return this.f403b;
    }

    /* renamed from: a */
    public boolean m119a(String str, String str2) {
        C0859a.m131a("LocalData", "writeSPData() called with: key = [" + str + "], data = [" + str2 + "]");
        if (m120b() == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            SharedPreferences.Editor edit = m120b().getSharedPreferences("linkkit-data", 0).edit();
            edit.putString(str, str2);
            edit.apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /* renamed from: a */
    public String m117a(String str) {
        C0859a.m131a("LocalData", "readSPData() called with: key = [" + str + "]");
        if (m120b() == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return m120b().getSharedPreferences("linkkit-data", 0).getString(str, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
