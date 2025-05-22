package com.iflytek.aiui.pro;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.net.Proxy;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.o */
/* loaded from: classes.dex */
public class C3630o extends Thread {

    /* renamed from: a */
    private C3652z f2590a;

    /* renamed from: b */
    private C3571aa f2591b;

    /* renamed from: c */
    private C3632p f2592c;

    /* renamed from: d */
    private C3626m f2593d;

    /* renamed from: e */
    private String f2594e;

    /* renamed from: f */
    private Context f2595f;

    public C3630o(Context context, C3632p c3632p, C3626m c3626m) {
        try {
            this.f2595f = context.getApplicationContext();
            this.f2593d = c3626m;
            if (c3632p == null) {
                return;
            }
            this.f2592c = c3632p;
            C3571aa c3571aa = new C3571aa();
            this.f2591b = c3571aa;
            c3571aa.mo921a(this.f2592c.f2597a);
            Proxy proxy = null;
            this.f2591b.m927b((Map<String, String>) null);
            if (this.f2591b.f2265z != null) {
                proxy = this.f2591b.f2265z;
            }
            C3652z c3652z = new C3652z(this.f2591b.f2263x, this.f2591b.f2264y, proxy);
            this.f2590a = c3652z;
            c3652z.m1640b(-1L);
            this.f2590a.m1637a(0L);
            this.f2594e = C3636r.m1513c(context, this.f2592c.f2600d);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private boolean m1451a(Context context) {
        return C3618i.m1365i(context) == 1;
    }

    /* renamed from: a */
    private boolean m1452a(Context context, String str, String str2) {
        Context context2 = this.f2595f;
        if (new File(C3636r.m1513c(context2, C3636r.m1512b(context2, str))).exists()) {
            return true;
        }
        String m1503a = C3636r.m1503a(this.f2595f);
        return !TextUtils.isEmpty(m1503a) && m1503a.equals(str2);
    }

    /* renamed from: a */
    private boolean m1453a(String str, String str2) {
        C3626m c3626m = this.f2593d;
        return c3626m != null && c3626m.f2579a.equals(str) && this.f2593d.f2580b.equals(str2);
    }

    /* renamed from: a */
    private boolean m1454a(String str, String str2, String str3, String str4, String str5) {
        String m1503a = C3636r.m1503a(this.f2595f);
        if (!TextUtils.isEmpty(m1503a) && m1503a.equals(str2)) {
            return true;
        }
        String m1511b = C3636r.m1511b(this.f2595f);
        if (C3628n.m1406a(m1511b, str2) > 0) {
            return true;
        }
        if (C3628n.m1406a(m1511b, str2) < 0) {
            return false;
        }
        return m1452a(this.f2595f, str4, str2);
    }

    /* renamed from: c */
    private boolean m1455c() {
        return Build.VERSION.SDK_INT >= this.f2592c.f2605i && Build.VERSION.SDK_INT <= this.f2592c.f2604h;
    }

    /* renamed from: d */
    private boolean m1456d() {
        try {
            if (m1453a(this.f2592c.f2601e, this.f2592c.f2602f) && !m1454a(this.f2592c.f2600d, this.f2592c.f2603g, this.f2592c.f2601e, this.f2592c.f2602f, this.f2592c.f2598b) && m1451a(this.f2595f)) {
                return m1455c();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void m1457a() {
        try {
            start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m1458a(Throwable th) {
        this.f2592c.m1471a(th);
    }

    /* renamed from: a */
    public void m1459a(byte[] bArr, long j) {
        this.f2592c.m1470a(this.f2595f, bArr, j);
    }

    /* renamed from: b */
    public void m1460b() {
        this.f2592c.m1469a(this.f2595f, this.f2593d);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (m1456d()) {
                this.f2590a.m1638a(this.f2591b.mo925b(), this.f2591b.mo919a(), this.f2591b.m934g(), this);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
