package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.loc.C3823ay;
import com.loc.C3828bc;
import com.loc.C3836bk;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexDownLoad.java */
/* renamed from: com.loc.as */
/* loaded from: classes4.dex */
public final class C3817as extends Thread implements C3836bk.a {

    /* renamed from: a */
    private C3818at f3580a;

    /* renamed from: b */
    private C3836bk f3581b;

    /* renamed from: c */
    private C3893s f3582c;

    /* renamed from: d */
    private String f3583d;

    /* renamed from: e */
    private RandomAccessFile f3584e;

    /* renamed from: f */
    private Context f3585f;

    public C3817as(Context context, C3818at c3818at, C3893s c3893s) {
        try {
            this.f3585f = context.getApplicationContext();
            this.f3582c = c3893s;
            if (c3818at == null) {
                return;
            }
            this.f3580a = c3818at;
            this.f3581b = new C3836bk(new C3822ax(this.f3580a));
            this.f3583d = C3823ay.m2492a(context, this.f3580a.f3586a);
        } catch (Throwable th) {
            C3897w.m3249a(th, "dDownLoad", "DexDownLoad()");
        }
    }

    /* renamed from: a */
    private boolean m2466a(C3804af c3804af) {
        try {
            List<C3828bc> m2505a = C3823ay.a.m2505a(c3804af, this.f3580a.f3587b, "used");
            if (m2505a != null && m2505a.size() > 0) {
                if (C3829bd.m2537a(m2505a.get(0).m2527e(), this.f3580a.f3589d) > 0) {
                    return true;
                }
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "dDownLoad", "isUsed()");
        }
        return false;
    }

    /* renamed from: a */
    private boolean m2467a(C3804af c3804af, C3828bc c3828bc, C3818at c3818at) {
        String str = c3818at.f3587b;
        String str2 = c3818at.f3588c;
        String str3 = c3818at.f3589d;
        String str4 = c3818at.f3590e;
        if ("errorstatus".equals(c3828bc.m2528f())) {
            if (!new File(C3823ay.m2499b(this.f3585f, this.f3582c.m3206a(), this.f3582c.m3208b())).exists() && !TextUtils.isEmpty(C3823ay.m2491a(this.f3585f, c3804af, this.f3582c))) {
                try {
                    C3823ay.m2497a(this.f3585f, this.f3582c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return true;
        }
        if (!new File(this.f3583d).exists()) {
            return false;
        }
        List m2433b = c3804af.m2433b(C3828bc.m2519a(C3823ay.m2493a(this.f3585f, str, str2), str, str2, str3), C3828bc.class);
        if (m2433b != null && m2433b.size() > 0) {
            return true;
        }
        try {
            C3823ay.m2493a(this.f3585f, str, this.f3582c.m3208b());
            C3823ay.m2495a(this.f3585f, c3804af, this.f3582c, this.f3583d, str3);
            C3823ay.m2497a(this.f3585f, this.f3582c);
        } catch (Throwable th2) {
            C3897w.m3249a(th2, "dDownLoad", "processDownloadedFile()");
        }
        return true;
    }

    /* renamed from: e */
    private boolean m2468e() {
        boolean m2467a;
        try {
            if (this.f3582c != null && this.f3582c.m3206a().equals(this.f3580a.f3587b) && this.f3582c.m3208b().equals(this.f3580a.f3588c)) {
                if (Build.VERSION.SDK_INT >= this.f3580a.f3592g && Build.VERSION.SDK_INT <= this.f3580a.f3591f) {
                    if (C3888n.m3164m(this.f3585f) == 1) {
                        C3804af c3804af = new C3804af(this.f3585f, C3827bb.m2516b());
                        if (m2466a(c3804af)) {
                            m2467a = true;
                        } else {
                            C3828bc m2504a = C3823ay.a.m2504a(c3804af, this.f3580a.f3586a);
                            m2467a = m2504a != null ? m2467a(c3804af, m2504a, this.f3580a) : false;
                        }
                        if (!m2467a) {
                            C3823ay.m2501b(this.f3585f, this.f3582c.m3206a());
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            C3897w.m3249a(th, "dDownLoad", "isNeedDownload()");
            return false;
        }
    }

    /* renamed from: a */
    public final void m2469a() {
        try {
            start();
        } catch (Throwable th) {
            C3897w.m3249a(th, "dDownLoad", "startDownload()");
        }
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: a */
    public final void mo2470a(byte[] bArr, long j) {
        try {
            if (this.f3584e == null) {
                File file = new File(this.f3583d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f3584e = new RandomAccessFile(file, "rw");
            }
            this.f3584e.seek(j);
            this.f3584e.write(bArr);
        } catch (Throwable th) {
            C3897w.m3249a(th, "dDownLoad", "onDownload()");
        }
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: b */
    public final void mo2471b() {
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: c */
    public final void mo2472c() {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "dDownLoad", "onFinish()");
        }
        if (this.f3584e == null) {
            return;
        }
        C3829bd.m2539a(this.f3584e);
        String m2475b = this.f3580a.m2475b();
        if (!C3829bd.m2542b(this.f3583d, m2475b)) {
            try {
                new File(this.f3583d).delete();
                return;
            } catch (Throwable th2) {
                C3897w.m3249a(th2, "dDownLoad", "onFinish");
                return;
            }
        }
        String str = this.f3580a.f3588c;
        C3804af c3804af = new C3804af(this.f3585f, C3827bb.m2516b());
        c3804af.m2429a(new C3828bc.a(this.f3580a.f3586a, m2475b, this.f3580a.f3587b, str, this.f3580a.f3589d).m2535a("copy").m2536a(), C3828bc.m2519a(this.f3580a.f3586a, this.f3580a.f3587b, str, this.f3580a.f3589d));
        try {
            SharedPreferences.Editor edit = this.f3585f.getSharedPreferences(this.f3580a.f3587b, 0).edit();
            edit.clear();
            edit.commit();
        } catch (Throwable th3) {
            C3897w.m3249a(th3, "dDownLoad", "clearMarker()");
        }
        try {
            C3823ay.m2495a(this.f3585f, c3804af, this.f3582c, this.f3583d, this.f3580a.f3589d);
            C3823ay.m2497a(this.f3585f, this.f3582c);
        } catch (Throwable th4) {
            C3897w.m3249a(th4, "dDownLoad", "onFinish1");
        }
        C3843br c3843br = new C3843br(this.f3585f, this.f3582c.m3206a(), this.f3582c.m3208b(), "O008");
        c3843br.m2638a("{\"param_int_first\":1}");
        C3844bs.m2641a(c3843br, this.f3585f);
        return;
        C3897w.m3249a(th, "dDownLoad", "onFinish()");
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: d */
    public final void mo2473d() {
        try {
            C3829bd.m2539a(this.f3584e);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            if (m2468e()) {
                C3843br c3843br = new C3843br(this.f3585f, this.f3582c.m3206a(), this.f3582c.m3208b(), "O008");
                c3843br.m2638a("{\"param_int_first\":0}");
                C3844bs.m2641a(c3843br, this.f3585f);
                this.f3581b.m2615a(this);
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "dDownLoad", "run()");
        }
    }
}
