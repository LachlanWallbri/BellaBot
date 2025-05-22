package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.C5873a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.y */
/* loaded from: classes7.dex */
public final class C5941y {

    /* renamed from: a */
    public static boolean f8275a = true;

    /* renamed from: b */
    private static SimpleDateFormat f8276b = null;

    /* renamed from: c */
    private static int f8277c = 5120;

    /* renamed from: d */
    private static StringBuilder f8278d = null;

    /* renamed from: e */
    private static StringBuilder f8279e = null;

    /* renamed from: f */
    private static boolean f8280f = false;

    /* renamed from: g */
    private static a f8281g = null;

    /* renamed from: h */
    private static String f8282h = null;

    /* renamed from: i */
    private static String f8283i = null;

    /* renamed from: j */
    private static Context f8284j = null;

    /* renamed from: k */
    private static String f8285k = null;

    /* renamed from: l */
    private static boolean f8286l = false;

    /* renamed from: m */
    private static boolean f8287m = false;

    /* renamed from: n */
    private static int f8288n;

    /* renamed from: o */
    private static final Object f8289o = new Object();

    /* renamed from: b */
    static /* synthetic */ boolean m3835b(boolean z) {
        f8280f = false;
        return false;
    }

    static {
        try {
            f8276b = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    private static boolean m3834b(String str, String str2, String str3) {
        try {
            C5873a m3390b = C5873a.m3390b();
            if (m3390b == null || m3390b.f7705D == null) {
                return false;
            }
            return m3390b.f7705D.appendLogToNative(str, str2, str3);
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: f */
    private static String m3839f() {
        try {
            C5873a m3390b = C5873a.m3390b();
            if (m3390b == null || m3390b.f7705D == null) {
                return null;
            }
            return m3390b.f7705D.getLogFromNative();
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized void m3828a(Context context) {
        synchronized (C5941y.class) {
            if (f8286l || context == null || !f8275a) {
                return;
            }
            try {
                f8279e = new StringBuilder(0);
                f8278d = new StringBuilder(0);
                f8284j = context;
                C5873a m3389a = C5873a.m3389a(context);
                f8282h = m3389a.f7756d;
                m3389a.getClass();
                f8283i = "";
                f8285k = f8284j.getFilesDir().getPath() + "/buglylog_" + f8282h + "_" + f8283i + ".txt";
                f8288n = Process.myPid();
            } catch (Throwable unused) {
            }
            f8286l = true;
        }
    }

    /* renamed from: a */
    public static void m3827a(int i) {
        synchronized (f8289o) {
            f8277c = i;
            if (i < 0) {
                f8277c = 0;
            } else if (i > 10240) {
                f8277c = TarConstants.DEFAULT_BLKSIZE;
            }
        }
    }

    /* renamed from: a */
    public static void m3831a(boolean z) {
        C5940x.m3818a("[LogUtil] Whether can record user log into native: " + z, new Object[0]);
        f8287m = z;
    }

    /* renamed from: a */
    public static void m3830a(String str, String str2, Throwable th) {
        if (th == null) {
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        m3829a(str, str2, message + '\n' + C5942z.m3878b(th));
    }

    /* renamed from: a */
    public static synchronized void m3829a(String str, String str2, String str3) {
        synchronized (C5941y.class) {
            if (f8286l && f8275a) {
                if (f8287m && m3834b(str, str2, str3)) {
                    return;
                }
                long myTid = Process.myTid();
                f8278d.setLength(0);
                if (str3.length() > 30720) {
                    str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
                }
                Date date = new Date();
                String format = f8276b != null ? f8276b.format(date) : date.toString();
                StringBuilder sb = f8278d;
                sb.append(format);
                sb.append(" ");
                sb.append(f8288n);
                sb.append(" ");
                sb.append(myTid);
                sb.append(" ");
                sb.append(str);
                sb.append(" ");
                sb.append(str2);
                sb.append(": ");
                sb.append(str3);
                sb.append("\u0001\r\n");
                String sb2 = f8278d.toString();
                synchronized (f8289o) {
                    f8279e.append(sb2);
                    if (f8279e.length() <= f8277c) {
                        return;
                    }
                    if (f8280f) {
                        return;
                    }
                    f8280f = true;
                    C5939w.m3810a().m3812a(new Runnable() { // from class: com.tencent.bugly.proguard.y.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            synchronized (C5941y.f8289o) {
                                try {
                                    if (C5941y.f8281g == null) {
                                        a unused = C5941y.f8281g = new a(C5941y.f8285k);
                                    } else if (C5941y.f8281g.f8291b == null || C5941y.f8281g.f8291b.length() + C5941y.f8279e.length() > C5941y.f8281g.f8294e) {
                                        C5941y.f8281g.m3841a();
                                    }
                                    if (C5941y.f8281g.m3845a(C5941y.f8279e.toString())) {
                                        C5941y.f8279e.setLength(0);
                                        C5941y.m3835b(false);
                                    }
                                } catch (Throwable unused2) {
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    /* renamed from: a */
    public static byte[] m3832a() {
        if (!f8275a) {
            return null;
        }
        if (f8287m) {
            C5940x.m3818a("[LogUtil] Get user log from native.", new Object[0]);
            String m3839f = m3839f();
            if (m3839f != null) {
                C5940x.m3818a("[LogUtil] Got user log from native: %d bytes", Integer.valueOf(m3839f.length()));
                return C5942z.m3872a((File) null, m3839f, "BuglyNativeLog.txt");
            }
        }
        StringBuilder sb = new StringBuilder();
        synchronized (f8289o) {
            if (f8281g != null && f8281g.f8290a && f8281g.f8291b != null && f8281g.f8291b.length() > 0) {
                sb.append(C5942z.m3856a(f8281g.f8291b, 30720, true));
            }
            if (f8279e != null && f8279e.length() > 0) {
                sb.append(f8279e.toString());
            }
        }
        return C5942z.m3872a((File) null, sb.toString(), "BuglyLog.txt");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.y$a */
    /* loaded from: classes7.dex */
    public static class a {

        /* renamed from: a */
        private boolean f8290a;

        /* renamed from: b */
        private File f8291b;

        /* renamed from: c */
        private String f8292c;

        /* renamed from: d */
        private long f8293d;

        /* renamed from: e */
        private long f8294e = 30720;

        public a(String str) {
            if (str == null || str.equals("")) {
                return;
            }
            this.f8292c = str;
            this.f8290a = m3841a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public boolean m3841a() {
            try {
                this.f8291b = new File(this.f8292c);
                if (this.f8291b.exists() && !this.f8291b.delete()) {
                    this.f8290a = false;
                    return false;
                }
                if (this.f8291b.createNewFile()) {
                    return true;
                }
                this.f8290a = false;
                return false;
            } catch (Throwable th) {
                C5940x.m3819a(th);
                this.f8290a = false;
                return false;
            }
        }

        /* renamed from: a */
        public final boolean m3845a(String str) {
            FileOutputStream fileOutputStream;
            if (!this.f8290a) {
                return false;
            }
            try {
                fileOutputStream = new FileOutputStream(this.f8291b, true);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                fileOutputStream.write(str.getBytes("UTF-8"));
                fileOutputStream.flush();
                fileOutputStream.close();
                this.f8293d += r10.length;
                this.f8290a = true;
                try {
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                try {
                    C5940x.m3819a(th);
                    this.f8290a = false;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th3;
                }
            }
        }
    }
}
