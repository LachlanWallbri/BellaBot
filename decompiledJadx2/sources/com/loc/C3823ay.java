package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.loc.C3828bc;
import dalvik.system.DexFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexFileManager.java */
/* renamed from: com.loc.ay */
/* loaded from: classes4.dex */
public final class C3823ay {

    /* compiled from: DexFileManager.java */
    /* renamed from: com.loc.ay$a */
    /* loaded from: classes4.dex */
    public static class a {
        /* renamed from: a */
        public static C3828bc m2504a(C3804af c3804af, String str) {
            List m2433b = c3804af.m2433b(C3828bc.m2520b(str), C3828bc.class);
            if (m2433b == null || m2433b.size() <= 0) {
                return null;
            }
            return (C3828bc) m2433b.get(0);
        }

        /* renamed from: a */
        public static List<C3828bc> m2505a(C3804af c3804af, String str, String str2) {
            return c3804af.m2433b(C3828bc.m2521b(str, str2), C3828bc.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2490a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "pngex";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2491a(Context context, C3804af c3804af, C3893s c3893s) {
        List m2433b = c3804af.m2433b(C3828bc.m2521b(c3893s.m3206a(), "copy"), C3828bc.class);
        String str = null;
        if (m2433b != null && m2433b.size() != 0) {
            C3829bd.m2540a((List<C3828bc>) m2433b);
            for (int i = 0; i < m2433b.size(); i++) {
                C3828bc c3828bc = (C3828bc) m2433b.get(i);
                String m2522a = c3828bc.m2522a();
                if (C3829bd.m2541a(c3804af, m2522a, m2492a(context, m2522a), c3893s)) {
                    try {
                        m2495a(context, c3804af, c3893s, m2492a(context, c3828bc.m2522a()), c3828bc.m2527e());
                        str = c3828bc.m2527e();
                        break;
                    } catch (Throwable th) {
                        C3897w.m3249a(th, "FileManager", "loadAvailableD");
                    }
                } else {
                    m2502c(context, c3804af, c3828bc.m2522a());
                }
            }
        }
        return str;
    }

    /* renamed from: a */
    public static String m2492a(Context context, String str) {
        return m2490a(context) + File.separator + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2493a(Context context, String str, String str2) {
        return C3890p.m3188b(str + str2 + C3888n.m3168q(context)) + ".jar";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2494a(String str) {
        return str + ".o";
    }

    /* renamed from: a */
    public static void m2495a(Context context, C3804af c3804af, C3893s c3893s, String str, String str2) throws Throwable {
        RandomAccessFile randomAccessFile;
        FileInputStream fileInputStream;
        String m3206a;
        String m2493a;
        File file;
        try {
            m3206a = c3893s.m3206a();
            m2493a = m2493a(context, m3206a, c3893s.m3208b());
            m2496a(context, c3804af, m2493a);
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileInputStream.read(new byte[32]);
                file = new File(m2499b(context, m3206a, c3893s.m3208b()));
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            int i = 0;
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                if (read == 1024) {
                    randomAccessFile.seek(i);
                    randomAccessFile.write(bArr);
                } else {
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    randomAccessFile.seek(i);
                    randomAccessFile.write(bArr2);
                }
                i += read;
            }
            C3828bc m2536a = new C3828bc.a(m2493a, C3890p.m3185a(file.getAbsolutePath()), m3206a, c3893s.m3208b(), str2).m2535a("used").m2536a();
            c3804af.m2429a(m2536a, C3828bc.m2520b(m2536a.m2522a()));
            try {
                C3829bd.m2539a(fileInputStream);
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            try {
                C3829bd.m2539a(randomAccessFile);
            } catch (Throwable th4) {
                th4.printStackTrace();
            }
        } catch (Throwable th5) {
            th = th5;
            try {
                throw th;
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2496a(Context context, C3804af c3804af, String str) {
        m2502c(context, c3804af, str);
        m2502c(context, c3804af, m2494a(str));
    }

    /* renamed from: a */
    public static void m2497a(Context context, C3893s c3893s) {
        try {
            String m2499b = m2499b(context, c3893s.m3206a(), c3893s.m3208b());
            if (TextUtils.isEmpty(m2499b)) {
                return;
            }
            File file = new File(m2499b);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile == null || !parentFile.exists()) {
                    return;
                }
                m2503c(context, c3893s.m3206a(), c3893s.m3208b());
                return;
            }
            String m2492a = m2492a(context, m2494a(file.getName()));
            DexFile loadDex = DexFile.loadDex(m2499b, m2492a, 0);
            if (loadDex != null) {
                loadDex.close();
                C3804af c3804af = new C3804af(context, C3827bb.m2516b());
                C3828bc m2504a = a.m2504a(c3804af, file.getName());
                String m2527e = m2504a != null ? m2504a.m2527e() : null;
                File file2 = new File(m2492a);
                if (TextUtils.isEmpty(m2527e) || !file2.exists()) {
                    return;
                }
                String m3185a = C3890p.m3185a(m2492a);
                String name = file2.getName();
                c3804af.m2429a(new C3828bc.a(name, m3185a, c3893s.m3206a(), c3893s.m3208b(), m2527e).m2535a("useod").m2536a(), C3828bc.m2520b(name));
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "BaseClassLoader", "getInstanceByThread()");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2498a(C3804af c3804af, Context context, String str) {
        List<C3828bc> m2505a = a.m2505a(c3804af, str, "used");
        if (m2505a == null || m2505a.size() <= 0) {
            return;
        }
        for (C3828bc c3828bc : m2505a) {
            if (c3828bc != null && c3828bc.m2524c().equals(str)) {
                m2496a(context, c3804af, c3828bc.m2522a());
                List m2433b = c3804af.m2433b(C3828bc.m2518a(str, c3828bc.m2527e()), C3828bc.class);
                if (m2433b != null && m2433b.size() > 0) {
                    C3828bc c3828bc2 = (C3828bc) m2433b.get(0);
                    c3828bc2.m2525c("errorstatus");
                    c3804af.m2429a(c3828bc2, C3828bc.m2520b(c3828bc2.m2522a()));
                    File file = new File(m2492a(context, c3828bc2.m2522a()));
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m2499b(Context context, String str, String str2) {
        return m2492a(context, m2493a(context, str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m2501b(Context context, String str) {
        C3804af c3804af = new C3804af(context, C3827bb.m2516b());
        List<C3828bc> m2505a = a.m2505a(c3804af, str, "copy");
        C3829bd.m2540a(m2505a);
        if (m2505a != null) {
            if (m2505a.size() > 1) {
                int size = m2505a.size();
                for (int i = 1; i < size; i++) {
                    m2502c(context, c3804af, m2505a.get(i).m2522a());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m2502c(Context context, C3804af c3804af, String str) {
        File file = new File(m2492a(context, str));
        if (file.exists()) {
            file.delete();
        }
        c3804af.m2430a(C3828bc.m2520b(str), C3828bc.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static void m2503c(final Context context, final String str, final String str2) {
        C3900z.m3265b().submit(new Runnable() { // from class: com.loc.ay.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    C3804af c3804af = new C3804af(context, C3827bb.m2516b());
                    List<C3828bc> m2433b = c3804af.m2433b(C3828bc.m2517a(str), C3828bc.class);
                    if (m2433b == null || m2433b.size() <= 0) {
                        return;
                    }
                    for (C3828bc c3828bc : m2433b) {
                        if (!str2.equalsIgnoreCase(c3828bc.m2526d())) {
                            C3823ay.m2502c(context, c3804af, c3828bc.m2522a());
                        }
                    }
                } catch (Throwable th) {
                    C3897w.m3249a(th, "FileManager", "clearUnSuitableV");
                }
            }
        });
    }
}
