package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.loc.C3823ay;
import com.loc.C3828bc;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DynamicClassLoader.java */
/* renamed from: com.loc.az */
/* loaded from: classes4.dex */
public final class C3824az extends AbstractC3820av {

    /* renamed from: g */
    private PublicKey f3609g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3824az(final Context context, C3893s c3893s) throws Exception {
        super(context, c3893s);
        this.f3609g = null;
        final String m2499b = C3823ay.m2499b(context, c3893s.m3206a(), c3893s.m3208b());
        final String m2490a = C3823ay.m2490a(context);
        if (TextUtils.isEmpty(m2499b) || TextUtils.isEmpty(m2490a)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
        String str = m2490a + File.separator + C3823ay.m2494a(new File(m2499b).getName());
        try {
            if (this.f3599c == null) {
                m2484b();
                this.f3599c = DexFile.loadDex(m2499b, str, 0);
            }
            C3900z.m3265b().submit(new Runnable() { // from class: com.loc.az.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        C3824az.this.m2509a(context, m2499b, m2490a);
                    } catch (Throwable th) {
                        C3897w.m3249a(th, "dLoader", "run()");
                    }
                }
            });
        } catch (Throwable th) {
            C3897w.m3249a(th, "dLoader", "loadFile");
            throw new Exception("load file fail");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0016, code lost:
    
        return;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void m2506a(JarFile jarFile, JarEntry jarEntry) throws IOException {
        InputStream inputStream;
        try {
            inputStream = jarFile.getInputStream(jarEntry);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            do {
            } while (inputStream.read(new byte[8192]) > 0);
        } catch (Throwable th2) {
            th = th2;
            try {
                C3897w.m3249a(th, "DyLoader", "loadJa");
                try {
                    C3829bd.m2539a(inputStream);
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            } finally {
                try {
                    C3829bd.m2539a(inputStream);
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2507a(File file) {
        JarFile jarFile;
        JarFile jarFile2 = null;
        try {
            if (this.f3609g == null) {
                this.f3609g = C3829bd.m2538a();
            }
            jarFile = new JarFile(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            JarEntry jarEntry = jarFile.getJarEntry("classes.dex");
            if (jarEntry == null) {
                try {
                    jarFile.close();
                } catch (Throwable unused) {
                }
                return false;
            }
            m2506a(jarFile, jarEntry);
            Certificate[] certificates = jarEntry.getCertificates();
            if (certificates == null) {
                try {
                    jarFile.close();
                } catch (Throwable unused2) {
                }
                return false;
            }
            boolean m2508a = m2508a(certificates);
            try {
                jarFile.close();
            } catch (Throwable unused3) {
            }
            return m2508a;
        } catch (Throwable th2) {
            th = th2;
            jarFile2 = jarFile;
            try {
                C3897w.m3249a(th, "DyLoader", "verify");
                if (jarFile2 != null) {
                    try {
                        jarFile2.close();
                    } catch (Throwable unused4) {
                    }
                }
                return false;
            } catch (Throwable th3) {
                if (jarFile2 != null) {
                    try {
                        jarFile2.close();
                    } catch (Throwable unused5) {
                    }
                }
                throw th3;
            }
        }
    }

    /* renamed from: a */
    private boolean m2508a(Certificate[] certificateArr) {
        int length;
        try {
            if (certificateArr.length <= 0 || (length = certificateArr.length - 1) < 0) {
                return false;
            }
            certificateArr[length].verify(this.f3609g);
            return true;
        } catch (Exception e) {
            C3897w.m3249a(e, "DyLoader", "check");
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00f0 A[Catch: all -> 0x00f8, TRY_LEAVE, TryCatch #0 {all -> 0x00f8, blocks: (B:3:0x0008, B:5:0x0020, B:6:0x0026, B:8:0x0038, B:10:0x004e, B:12:0x0067, B:13:0x0070, B:17:0x0077, B:19:0x009e, B:21:0x00b6, B:25:0x00f0, B:27:0x00bd, B:29:0x00c5), top: B:2:0x0008 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final void m2509a(Context context, String str, String str2) throws Exception {
        C3804af c3804af;
        File file;
        boolean z;
        new Date().getTime();
        try {
            c3804af = new C3804af(context, C3827bb.m2516b());
            file = new File(str);
            C3828bc m2504a = C3823ay.a.m2504a(c3804af, file.getName());
            if (m2504a != null) {
                this.f3602f = m2504a.m2527e();
            }
            C3893s c3893s = this.f3601e;
            String absolutePath = file.getAbsolutePath();
            z = false;
            if (!(m2507a(new File(absolutePath)) ? C3829bd.m2541a(c3804af, C3823ay.m2493a(this.f3597a, c3893s.m3206a(), c3893s.m3208b()), absolutePath, c3893s) : false)) {
                this.f3600d = false;
                C3823ay.m2496a(this.f3597a, c3804af, file.getName());
                String m2491a = C3823ay.m2491a(this.f3597a, c3804af, this.f3601e);
                if (!TextUtils.isEmpty(m2491a)) {
                    this.f3602f = m2491a;
                    C3823ay.m2497a(this.f3597a, this.f3601e);
                }
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "dLoader", "verifyD()");
        }
        if (file.exists()) {
            if (new File(str2 + File.separator + C3823ay.m2494a(file.getName())).exists()) {
                String m2494a = C3823ay.m2494a(file.getName());
                String str3 = this.f3602f;
                String m2492a = C3823ay.m2492a(this.f3597a, m2494a);
                if (!C3829bd.m2541a(c3804af, m2494a, m2492a, this.f3601e)) {
                    if (C3823ay.a.m2504a(c3804af, m2494a) == null) {
                        if (!TextUtils.isEmpty(this.f3602f)) {
                            c3804af.m2429a(new C3828bc.a(m2494a, C3890p.m3185a(m2492a), this.f3601e.m3206a(), this.f3601e.m3208b(), str3).m2535a("useod").m2536a(), C3828bc.m2520b(m2494a));
                        }
                    } else if (!z) {
                        C3823ay.m2497a(this.f3597a, this.f3601e);
                    }
                }
                z = true;
                if (!z) {
                }
            }
            new Date().getTime();
        }
    }

    @Override // java.lang.ClassLoader
    protected final Class<?> findClass(String str) throws ClassNotFoundException {
        Class<?> cls;
        try {
            if (this.f3599c == null) {
                throw new ClassNotFoundException(str);
            }
            Class<?> cls2 = null;
            try {
            } catch (Throwable th) {
                cls = cls2;
                C3897w.m3249a(th, "dLoader", "findCl");
            }
            synchronized (this.f3598b) {
                try {
                    cls = this.f3598b.get(str);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    if (cls != null) {
                        return cls;
                    }
                    Class<?> loadClass = this.f3599c.loadClass(str, this);
                    if (loadClass == null) {
                        throw new ClassNotFoundException(str);
                    }
                    try {
                        synchronized (this.f3598b) {
                            this.f3598b.put(str, loadClass);
                        }
                    } catch (Throwable th3) {
                        C3897w.m3249a(th3, "dLoader", "findCl");
                    }
                    return loadClass;
                } catch (Throwable th4) {
                    th = th4;
                    cls2 = cls;
                    throw th;
                }
            }
        } catch (Throwable th5) {
            C3897w.m3249a(th5, "dLoader", "findCl");
            throw new ClassNotFoundException(str);
        }
    }
}
