package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.s */
/* loaded from: classes.dex */
public class C3638s extends ClassLoader {

    /* renamed from: c */
    private static C3638s f2638c = null;

    /* renamed from: h */
    private static boolean f2639h = true;

    /* renamed from: a */
    volatile boolean f2640a;

    /* renamed from: b */
    private final Context f2641b;

    /* renamed from: d */
    private final Map<String, Class<?>> f2642d;

    /* renamed from: e */
    private DexFile f2643e;

    /* renamed from: f */
    private String f2644f;

    /* renamed from: g */
    private C3626m f2645g;

    /* renamed from: com.iflytek.aiui.pro.s$a */
    /* loaded from: classes4.dex */
    static final class a extends Thread {

        /* renamed from: a */
        final /* synthetic */ Context f2649a;

        /* renamed from: b */
        final /* synthetic */ String f2650b;

        /* renamed from: c */
        final /* synthetic */ String f2651c;

        a(Context context, String str, String str2) {
            this.f2649a = context;
            this.f2650b = str;
            this.f2651c = str2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                C3638s.e(C3638s.j(), this.f2649a, this.f2650b, this.f2651c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private C3638s(Context context, ClassLoader classLoader) {
        super(classLoader);
        this.f2642d = new HashMap();
        this.f2643e = null;
        this.f2640a = true;
        this.f2641b = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r4v3, types: [com.iflytek.aiui.pro.s$1] */
    /* renamed from: a */
    public static C3638s m1522a(final Context context, C3626m c3626m, final String str, final String str2, String str3, ClassLoader classLoader) {
        C3638s c3638s;
        synchronized (C3638s.class) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                C3640t.m1536a(context, c3626m);
                File file = new File(str);
                file.getParentFile();
                if (file.exists()) {
                    if (f2638c == null) {
                        new Date().getTime();
                        try {
                            C3638s c3638s2 = new C3638s(context.getApplicationContext(), classLoader);
                            f2638c = c3638s2;
                            c3638s2.f2645g = c3626m;
                            f2638c.m1527a(str, str2 + File.separator + C3636r.m1505a(file.getName()));
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        new Date().getTime();
                        new Thread() { // from class: com.iflytek.aiui.pro.s.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                try {
                                    C3638s.f2638c.m1531b(context, str, str2);
                                } catch (Throwable th2) {
                                    th2.printStackTrace();
                                }
                            }
                        }.start();
                    }
                    c3638s = f2638c;
                }
            }
            c3638s = null;
        }
        return c3638s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1523a(Context context, C3626m c3626m, String str, String str2, String str3, ClassLoader classLoader, String str4) {
        synchronized (C3638s.class) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                try {
                    String str5 = str2 + File.separator + C3636r.m1505a(new File(str).getName());
                    DexFile loadDex = DexFile.loadDex(str, str5, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        m1524a(context, new File(str5), str5, str4, c3626m);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static void m1524a(Context context, File file, String str, String str2, C3626m c3626m) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        String m1392a = C3624l.m1392a(str);
        if (TextUtils.isEmpty(m1392a)) {
            return;
        }
        file.getName();
        String str3 = c3626m.f2579a;
        C3642u c3642u = new C3642u(m1392a, c3626m.f2580b, str2);
        c3642u.f2663d = "useodex";
        C3636r.m1508a(context, c3642u);
    }

    /* renamed from: a */
    private void m1525a(Context context, String str) {
        C3642u m1502a = C3636r.m1502a(context, str);
        if (m1502a != null) {
            this.f2644f = m1502a.f2662c;
        }
    }

    /* renamed from: a */
    private void m1527a(String str, String str2) {
        try {
            this.f2642d.clear();
            m1533c();
            this.f2643e = DexFile.loadDex(str, str2, 0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private boolean m1528a(Context context, C3626m c3626m, String str) {
        return C3636r.m1509a(context, C3636r.m1512b(this.f2641b, c3626m.f2580b), str, c3626m);
    }

    /* renamed from: a */
    private boolean m1529a(Context context, String str, String str2) {
        String m1513c = C3636r.m1513c(this.f2641b, str);
        if (!C3636r.m1509a(context, str, m1513c, this.f2645g)) {
            if (C3636r.m1502a(context, str) != null) {
                return false;
            }
            if (!TextUtils.isEmpty(this.f2644f)) {
                C3642u c3642u = new C3642u(C3624l.m1392a(m1513c), this.f2645g.f2580b, str2);
                c3642u.f2663d = "useodex";
                C3636r.m1508a(context, c3642u);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m1531b(Context context, String str, String str2) {
        File file;
        new Date().getTime();
        try {
            file = new File(str);
            m1525a(context, file.getName());
            if (!m1528a(context, this.f2645g, file.getAbsolutePath())) {
                this.f2640a = false;
                C3636r.m1516d(this.f2641b, file.getName());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (file.exists()) {
            String str3 = str2 + File.separator + C3636r.m1505a(file.getName());
            File file2 = new File(str3);
            if (file2.exists() && !m1529a(context, C3636r.m1505a(file.getName()), this.f2644f)) {
                m1532b(str, str2 + File.separator + C3636r.m1505a(file.getName()));
                m1524a(context, file2, str3, this.f2644f, this.f2645g);
            }
            new Date().getTime();
        }
    }

    /* renamed from: b */
    private void m1532b(String str, String str2) {
        try {
            DexFile.loadDex(str, str2, 0).close();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m1533c() {
        DexFile dexFile = this.f2643e;
        if (dexFile != null) {
            try {
                dexFile.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m1534a() {
        return this.f2643e != null;
    }

    @Override // java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        try {
            if (this.f2643e == null) {
                throw new ClassNotFoundException(str);
            }
            Class<?> cls = this.f2642d.get(str);
            if (cls != null) {
                return cls;
            }
            Class<?> loadClass = this.f2643e.loadClass(str, this);
            this.f2642d.put(str, loadClass);
            if (loadClass != null) {
                return loadClass;
            }
            throw new ClassNotFoundException(str);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new ClassNotFoundException(str);
        }
    }
}
