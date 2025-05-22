package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.Constructor;

/* compiled from: InstanceFactory.java */
/* renamed from: com.loc.au */
/* loaded from: classes4.dex */
public final class C3819au {
    /* renamed from: a */
    public static <T> T m2476a(Context context, C3893s c3893s, String str, Class cls, Class[] clsArr, Object[] objArr) throws C3884j {
        T t = (T) m2477a(m2482b(context, c3893s), str, clsArr, objArr);
        if (t != null) {
            return t;
        }
        T t2 = (T) m2478a(cls, clsArr, objArr);
        if (t2 != null) {
            return t2;
        }
        throw new C3884j("获取对象错误");
    }

    /* renamed from: a */
    private static <T> T m2477a(AbstractC3820av abstractC3820av, String str, Class[] clsArr, Object[] objArr) {
        boolean z;
        Class<?> loadClass;
        if (abstractC3820av != null) {
            try {
                if (abstractC3820av.m2483a() && abstractC3820av.f3600d) {
                    z = true;
                    if (!z && (loadClass = abstractC3820av.loadClass(str)) != null) {
                        Constructor<?> declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                        declaredConstructor.setAccessible(true);
                        return (T) declaredConstructor.newInstance(objArr);
                    }
                }
            } catch (Throwable th) {
                C3897w.m3249a(th, "IFactory", "getWrap");
                return null;
            }
        }
        z = false;
        return !z ? null : null;
    }

    /* renamed from: a */
    private static <T> T m2478a(Class cls, Class[] clsArr, Object[] objArr) {
        if (cls == null) {
            return null;
        }
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(objArr);
        } catch (Throwable th) {
            C3897w.m3249a(th, "IFactory", "gIns2()");
            return null;
        }
    }

    /* renamed from: a */
    public static void m2479a(Context context, C3818at c3818at, C3893s c3893s) {
        if (c3818at == null) {
            return;
        }
        try {
            if (!TextUtils.isEmpty(c3818at.m2474a()) && !TextUtils.isEmpty(c3818at.m2475b()) && !TextUtils.isEmpty(c3818at.f3588c)) {
                new C3817as(context, c3818at, c3893s).m2469a();
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "IFactory", "dDownload()");
        }
    }

    /* renamed from: a */
    public static void m2480a(final Context context, final String str) {
        try {
            C3900z.m3265b().submit(new Runnable() { // from class: com.loc.au.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        C3823ay.m2498a(new C3804af(context, C3827bb.m2516b()), context, str);
                    } catch (Throwable th) {
                        C3897w.m3249a(th, "InstanceFactory", "rollBack");
                    }
                }
            });
        } catch (Throwable th) {
            C3897w.m3249a(th, "InstanceFactory", "rollBack");
        }
    }

    /* renamed from: a */
    public static boolean m2481a(Context context, C3893s c3893s) {
        try {
            File file = new File(C3823ay.m2499b(context, c3893s.m3206a(), c3893s.m3208b()));
            if (file.exists()) {
                return true;
            }
            File parentFile = file.getParentFile();
            if (!file.exists() && parentFile != null && parentFile.exists()) {
                C3823ay.m2503c(context, c3893s.m3206a(), c3893s.m3208b());
            }
            return false;
        } catch (Throwable th) {
            C3897w.m3249a(th, "IFactory", "isdowned");
            return false;
        }
    }

    /* renamed from: b */
    private static AbstractC3820av m2482b(Context context, C3893s c3893s) {
        try {
            if (m2481a(context, c3893s)) {
                return C3821aw.m2485a().m2486a(context, c3893s);
            }
            return null;
        } catch (Throwable th) {
            C3897w.m3249a(th, "IFactory", "gIns1");
            return null;
        }
    }
}
