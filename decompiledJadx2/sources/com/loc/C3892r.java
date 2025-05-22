package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.loc.C3836bk;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

/* compiled from: SDKCoordinatorDownload.java */
/* renamed from: com.loc.r */
/* loaded from: classes4.dex */
public final class C3892r extends Thread implements C3836bk.a {

    /* renamed from: h */
    private static String f4295h = "sodownload";

    /* renamed from: i */
    private static String f4296i = "sofail";

    /* renamed from: a */
    private C3836bk f4297a;

    /* renamed from: b */
    private a f4298b;

    /* renamed from: c */
    private RandomAccessFile f4299c;

    /* renamed from: d */
    private String f4300d;

    /* renamed from: e */
    private String f4301e;

    /* renamed from: f */
    private String f4302f;

    /* renamed from: g */
    private Context f4303g;

    /* compiled from: SDKCoordinatorDownload.java */
    /* renamed from: com.loc.r$a */
    /* loaded from: classes4.dex */
    private static class a extends AbstractC3839bn {

        /* renamed from: a */
        private String f4304a;

        a(String str) {
            this.f4304a = str;
        }

        @Override // com.loc.AbstractC3839bn
        /* renamed from: a */
        public final Map<String, String> mo2487a() {
            return null;
        }

        @Override // com.loc.AbstractC3839bn
        /* renamed from: b */
        public final String mo2488b() {
            return this.f4304a;
        }

        @Override // com.loc.AbstractC3839bn
        /* renamed from: c */
        public final Map<String, String> mo2489c() {
            return null;
        }
    }

    public C3892r(Context context, String str, String str2, String str3) {
        this.f4303g = context;
        this.f4302f = str3;
        this.f4300d = m3199a(context, str + "temp.so");
        this.f4301e = m3199a(context, "libwgs2gcj.so");
        this.f4298b = new a(str2);
        this.f4297a = new C3836bk(this.f4298b);
    }

    /* renamed from: a */
    public static String m3199a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    /* renamed from: e */
    private void m3200e() {
        File file = new File(this.f4300d);
        if (file.exists()) {
            file.delete();
        }
    }

    /* renamed from: a */
    public final void m3201a() {
        a aVar = this.f4298b;
        if (aVar == null || TextUtils.isEmpty(aVar.mo2488b()) || !this.f4298b.mo2488b().contains("libJni_wgs2gcj.so") || !this.f4298b.mo2488b().contains(Build.CPU_ABI) || new File(this.f4301e).exists()) {
            return;
        }
        start();
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: a */
    public final void mo2470a(byte[] bArr, long j) {
        try {
            if (this.f4299c == null) {
                File file = new File(this.f4300d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                try {
                    this.f4299c = new RandomAccessFile(file, "rw");
                } catch (FileNotFoundException e) {
                    C3897w.m3249a(e, "SDKCoordinatorDownload", "onDownload");
                    m3200e();
                }
            }
            if (this.f4299c == null) {
                return;
            }
            try {
                this.f4299c.seek(j);
                this.f4299c.write(bArr);
            } catch (IOException e2) {
                m3200e();
                C3897w.m3249a(e2, "SDKCoordinatorDownload", "onDownload");
            }
        } catch (Throwable th) {
            m3200e();
            C3897w.m3249a(th, "SDKCoordinatorDownload", "onDownload");
        }
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: b */
    public final void mo2471b() {
        m3200e();
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: c */
    public final void mo2472c() {
        try {
            if (this.f4299c != null) {
                this.f4299c.close();
            }
            String m3185a = C3890p.m3185a(this.f4300d);
            if (m3185a == null || !m3185a.equalsIgnoreCase(this.f4302f)) {
                m3200e();
            } else if (new File(this.f4301e).exists()) {
                m3200e();
            } else {
                new File(this.f4300d).renameTo(new File(this.f4301e));
            }
        } catch (Throwable th) {
            m3200e();
            File file = new File(this.f4301e);
            if (file.exists()) {
                file.delete();
            }
            C3897w.m3249a(th, "SDKCoordinatorDownload", "onFinish");
        }
    }

    @Override // com.loc.C3836bk.a
    /* renamed from: d */
    public final void mo2473d() {
        try {
            if (this.f4299c != null) {
                this.f4299c.close();
            }
            m3200e();
            File file = new File(m3199a(this.f4303g, "tempfile"));
            if (file.exists()) {
                return;
            }
            try {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            } catch (Throwable th) {
                C3897w.m3249a(th, "SDKCoordinatorDownload", "onException");
            }
        } catch (Throwable th2) {
            C3897w.m3249a(th2, "SDKCoordinatorDownload", "onException");
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            File file = new File(m3199a(this.f4303g, "tempfile"));
            if (file.exists()) {
                file.delete();
            }
            this.f4297a.m2615a(this);
        } catch (Throwable th) {
            C3897w.m3249a(th, "SDKCoordinatorDownload", "run");
            m3200e();
        }
    }
}
