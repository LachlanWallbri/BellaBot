package com.stub;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import com.qihoo.util.C5777c;
import dalvik.system.DexFile;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class StubApp extends Application {

    /* renamed from: d */
    private static Context f7611d;

    /* renamed from: a */
    private static Application f7608a = null;
    public static String strEntryApplication = "entryRunApplication";

    /* renamed from: b */
    private static Application f7609b = null;

    /* renamed from: c */
    private static String f7610c = "libjiagu";
    private static boolean loadFromLib = false;
    private static boolean needX86Bridge = true;
    private static boolean returnIntern = true;

    /* renamed from: e */
    private static String f7612e = null;

    /* renamed from: f */
    private static String f7613f = null;

    /* renamed from: g */
    private static String f7614g = null;

    /* renamed from: h */
    private static String f7615h = null;

    /* renamed from: i */
    private static String f7616i = null;

    /* renamed from: j */
    private static Map<Integer, String> f7617j = new ConcurrentHashMap();

    public static native void interface11(int i);

    public static native Enumeration<String> interface12(DexFile dexFile);

    public static native long interface13(int i, long j, long j2, long j3, int i2, int i3, long j4);

    public static native String interface14(int i);

    public static native AssetFileDescriptor interface17(AssetManager assetManager, String str);

    public static native InputStream interface18(Class cls, String str);

    public static native InputStream interface19(ClassLoader classLoader, String str);

    public static native void interface20();

    public static native void interface21(Application application);

    public static native void interface22(int i, String[] strArr, int[] iArr);

    public static native void interface5(Application application);

    public static native String interface6(String str);

    public static native boolean interface7(Application application, Context context);

    public static native boolean interface8(Application application, Context context);

    public static native Location mark(LocationManager locationManager, String str);

    public static native void mark();

    public static native void mark(Location location);

    public static native void n0110();

    public static native int n0111();

    public static native void n01110(int i);

    public static native boolean n01111(int i);

    public static native int n0111111131(int i, int i2, int i3, int i4, int i5, Object obj);

    public static native Object n01113(int i);

    public static native void n0111330(int i, Object obj, Object obj2);

    public static native long n0112();

    public static native boolean n01122221(double d, double d2, double d3, double d4);

    public static native void n011222220(double d, double d2, double d3, double d4, double d5);

    public static native Object n0113();

    public static native void n01130(Object obj);

    public static native boolean n01131(Object obj);

    public static native void n011310(Object obj, boolean z);

    public static native int n011311(Object obj, int i);

    public static native Object n0113113(Object obj, int i, int i2);

    public static native Object n011313(Object obj, boolean z);

    public static native void n0113130(Object obj, int i, Object obj2);

    public static native Object n0113133(Object obj, int i, Object obj2);

    public static native void n0113220(Object obj, double d, double d2);

    public static native Object n01133(Object obj);

    public static native void n011330(Object obj, Object obj2);

    public static native Object n011333(Object obj, Object obj2);

    public static native void n0113330(Object obj, Object obj2, Object obj3);

    public static native void n0113331330(Object obj, Object obj2, Object obj3, boolean z, Object obj4, Object obj5);

    public static native long n0113331333112(Object obj, Object obj2, Object obj3, int i, Object obj4, Object obj5, Object obj6, int i2, int i3);

    public static native Object n0113333(Object obj, Object obj2, Object obj3);

    public static native void pmark(Context context);

    public static native void rmark();

    public native synchronized void n1100();

    public native synchronized void n11030(Object obj);

    public native void n1110();

    public native int n1111();

    public native void n11110(int i);

    public native int n11111(int i);

    public native boolean n111111(int i, int i2);

    public native boolean n1111111(int i, int i2, int i3);

    public native boolean n11111111(int i, int i2, int i3, int i4);

    public native boolean n111111111(float f, float f2, float f3, float f4, int i);

    public native Object n1111113(float f, float f2, float f3);

    public native long n11112(int i);

    public native Object n1111223(int i, long j, long j2);

    public native Object n11113(int i);

    public native void n111130(int i, Object obj);

    public native boolean n111131(int i, Object obj);

    public native boolean n1111311(int i, Object obj, int i2);

    public native Object n111133(float f, Object obj);

    public native boolean n11113311(int i, Object obj, Object obj2, int i2);

    public native double n1112();

    public native void n11120(long j);

    public native Object n1113();

    public native void n11130(Object obj);

    public native boolean n11131(Object obj);

    public native void n111310(Object obj, int i);

    public native Object n1113113(Object obj, boolean z, boolean z2);

    public native void n11131130(Object obj, int i, int i2, Object obj2);

    public native void n1113130(Object obj, int i, Object obj2);

    public native void n111320(Object obj, double d);

    public native Object n11133(Object obj);

    public native void n111330(Object obj, Object obj2);

    public native boolean n111331(Object obj, Object obj2);

    public native void n11133110(Object obj, Object obj2, boolean z, int i);

    public native Object n1113313(Object obj, Object obj2, float f);

    public native Object n111333(Object obj, Object obj2);

    public native void n1113330(Object obj, Object obj2, Object obj3);

    public native boolean n1113331(Object obj, Object obj2, Object obj3);

    public native void n11133310(Object obj, Object obj2, Object obj3, int i);

    public native void n111333133310(Object obj, Object obj2, Object obj3, int i, Object obj4, Object obj5, Object obj6, int i2);

    public native Object n1113333(Object obj, Object obj2, Object obj3);

    public native boolean n11133331(Object obj, Object obj2, Object obj3, Object obj4);

    public native void n111333330(Object obj, Object obj2, Object obj3, Object obj4, Object obj5);

    public static String getSoPath1() {
        return f7613f;
    }

    public static String getSoPath2() {
        return f7614g;
    }

    public static String getDir() {
        return f7615h;
    }

    public static Context getAppContext() {
        return f7611d;
    }

    public static Context getOrigApplicationContext(Context context) {
        if (context == f7608a) {
            return f7609b;
        }
        return context;
    }

    /* renamed from: a */
    private static Application m3328a(Context context) {
        ClassLoader classLoader;
        Class<?> loadClass;
        try {
            if (f7609b == null && (classLoader = context.getClassLoader()) != null && (loadClass = classLoader.loadClass(strEntryApplication)) != null) {
                f7609b = (Application) loadClass.newInstance();
            }
        } catch (Exception e) {
        }
        return f7609b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    @Override // android.app.Application
    public final void onCreate() {
        System.currentTimeMillis();
        super.onCreate();
        try {
            interface7(f7609b, f7608a.getBaseContext());
        } catch (Exception e) {
        }
        if (f7609b != null) {
            f7609b.onCreate();
        }
        interface21(f7609b);
        ?? r5 = this;
        if (f7609b != null) {
            r5 = f7609b;
        }
        Context context = f7611d;
        if (r5 != 0 && context != null && C5777c.m3314a(context)) {
            try {
                ?? declaredMethod = Class.forName(C5777c.m3311a("s\u007f}>zw>rx>Bu`\u007fbdcDy}u")).getDeclaredMethod(C5777c.m3311a("BuwycdubQsdyfydiSq||Rqs{c"), Application.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(null, new Object[]{r5});
            } catch (Exception e2) {
            }
        }
    }

    @Override // android.content.ContextWrapper
    protected final void attachBaseContext(Context context) {
        boolean m3315a;
        System.currentTimeMillis();
        super.attachBaseContext(context);
        C5777c.m3317b();
        f7611d = context;
        if (f7608a == null) {
            f7608a = this;
        }
        if (f7609b == null) {
            Boolean valueOf = Boolean.valueOf(C5777c.m3313a());
            Boolean bool = false;
            Boolean bool2 = false;
            if (Build.CPU_ABI.contains("64") || Build.CPU_ABI2.contains("64")) {
                bool = true;
            }
            if (Build.CPU_ABI.contains("mips") || Build.CPU_ABI2.contains("mips")) {
                bool2 = true;
            }
            if (valueOf.booleanValue() && needX86Bridge) {
                System.loadLibrary("X86Bridge");
            }
            if (loadFromLib) {
                if (valueOf.booleanValue() && !needX86Bridge) {
                    System.loadLibrary("jiagu_x86");
                } else {
                    System.loadLibrary("jiagu");
                }
            } else {
                String absolutePath = context.getFilesDir().getParentFile().getAbsolutePath();
                try {
                    absolutePath = context.getFilesDir().getParentFile().getCanonicalPath();
                } catch (Exception e) {
                }
                String str = absolutePath + "/.jiagu";
                f7616i = m3329a(str, bool.booleanValue(), bool2.booleanValue());
                f7612e = m3329a(str, false, false);
                f7613f = str + File.separator + f7612e;
                f7614g = str + File.separator + f7616i;
                f7615h = str;
                if (bool2.booleanValue()) {
                    C5777c.m3315a(context, f7610c + "_mips.so", str, f7612e);
                } else if (valueOf.booleanValue() && !needX86Bridge) {
                    C5777c.m3315a(context, f7610c + "_x86.so", str, f7612e);
                } else {
                    C5777c.m3315a(context, f7610c + ".so", str, f7612e);
                }
                if (bool.booleanValue() && !bool2.booleanValue()) {
                    if (valueOf.booleanValue() && !needX86Bridge) {
                        m3315a = C5777c.m3315a(context, f7610c + "_x64.so", str, f7616i);
                    } else {
                        m3315a = C5777c.m3315a(context, f7610c + "_a64.so", str, f7616i);
                    }
                    if (m3315a) {
                        System.load(str + "/" + f7616i);
                    } else {
                        System.load(str + "/" + f7612e);
                    }
                } else {
                    System.load(str + "/" + f7612e);
                }
            }
        }
        interface5(f7608a);
        if (f7609b == null) {
            f7609b = m3328a(context);
            if (f7609b != null) {
                try {
                    Method declaredMethod = Application.class.getDeclaredMethod("attach", Context.class);
                    if (declaredMethod != null) {
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(f7609b, context);
                    }
                    interface8(f7609b, context);
                    return;
                } catch (Exception e2) {
                    throw new RuntimeException("Failed to call attachBaseContext.", e2);
                }
            }
            System.exit(1);
        }
    }

    /* renamed from: a */
    private static String m3329a(String str, boolean z, boolean z2) {
        String str2 = f7610c;
        if (Build.VERSION.SDK_INT < 23) {
            str2 = str2 + str.hashCode();
        }
        if (z && !z2) {
            return str2 + "_64.so";
        }
        return str2 + ".so";
    }

    public static String getString2(int i) {
        String str = f7617j.get(Integer.valueOf(i));
        if (str == null) {
            str = interface14(i);
            f7617j.put(Integer.valueOf(i), str);
        }
        if (str != null && returnIntern) {
            return str.intern();
        }
        return str;
    }

    public static String getString2(String str) {
        try {
            return getString2(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
