package com.iflytek.idata.extension;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.C3722bg;
import java.io.File;

/* loaded from: classes3.dex */
public class IFlyCollectorExt {

    /* renamed from: a */
    private static volatile IFlyCollectorExt f3490a;

    /* renamed from: b */
    private Class<?> f3491b;

    /* renamed from: c */
    private Object f3492c;

    /* renamed from: d */
    private Context f3493d;

    /* renamed from: e */
    private volatile String f3494e;

    /* renamed from: f */
    private volatile boolean f3495f;

    /* renamed from: g */
    private volatile String f3496g;

    /* JADX WARN: Type inference failed for: r6v10, types: [com.iflytek.idata.extension.IFlyCollectorExt$1] */
    private IFlyCollectorExt(Context context) {
        boolean z = true;
        try {
            this.f3493d = context.getApplicationContext();
            this.f3491b = getClass(this.f3493d, m2313g(), m2316b());
            if (this.f3491b != null) {
                this.f3492c = this.f3491b.newInstance();
                this.f3491b.getMethod("init", Context.class).invoke(this.f3492c, this.f3493d);
                new Thread() { // from class: com.iflytek.idata.extension.IFlyCollectorExt.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        SystemClock.sleep(5000L);
                        new RunnableC3753c(IFlyCollectorExt.this.f3493d).run();
                    }
                }.start();
                z = false;
            }
        } catch (Throwable unused) {
            this.f3491b = null;
            this.f3492c = null;
        }
        m2311a(z);
    }

    /* renamed from: a */
    public static IFlyCollectorExt m2309a(Context context) {
        if (f3490a == null) {
            synchronized (IFlyCollectorExt.class) {
                if (f3490a == null) {
                    f3490a = new IFlyCollectorExt(context);
                }
            }
        }
        return f3490a;
    }

    /* renamed from: a */
    private void m2310a(File file) {
        File[] listFiles;
        if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
            return;
        }
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i] != null) {
                m2312b(listFiles[i]);
            }
        }
    }

    /* renamed from: a */
    private void m2311a(boolean z) {
        try {
            if (this.f3495f) {
                return;
            }
            new Thread(new RunnableC3751a(this.f3493d, z)).start();
            this.f3495f = true;
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    private static void m2312b(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m2312b(file2);
            }
        }
        file.delete();
    }

    /* renamed from: g */
    private String m2313g() {
        File file = new File(m2316b());
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles.length == 1 && listFiles[0].exists() && listFiles[0].isFile()) {
            return listFiles[0].getName();
        }
        m2318c();
        return null;
    }

    private native Class<?> getClass(Context context, String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public String m2314a() {
        try {
            if (this.f3491b == null || this.f3492c == null) {
                return "-1";
            }
            Object invoke = this.f3491b.getMethod("getVersion", new Class[0]).invoke(this.f3492c, new Object[0]);
            return invoke instanceof String ? (String) invoke : "-1";
        } catch (Throwable unused) {
            return "-1";
        }
    }

    /* renamed from: a */
    public void m2315a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f3494e = str;
        SharedPreferences m2045b = C3722bg.m2045b(this.f3493d);
        if (m2045b == null || str.equals(m2045b.getString("appid", ""))) {
            return;
        }
        SharedPreferences.Editor edit = m2045b.edit();
        edit.putString("appid", str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public String m2316b() {
        String str = this.f3493d.getFilesDir().getAbsolutePath() + File.separator + "iflytek/idata/cache/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    /* renamed from: b */
    public void m2317b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f3496g = str;
        SharedPreferences m2045b = C3722bg.m2045b(this.f3493d);
        if (m2045b != null) {
            if (this.f3496g.equals(m2045b.getString("subid", ""))) {
                return;
            }
            SharedPreferences.Editor edit = m2045b.edit();
            edit.putString("subid", str);
            edit.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m2318c() {
        m2310a(new File(m2316b()));
    }

    /* renamed from: d */
    public String m2319d() {
        return this.f3494e;
    }

    /* renamed from: e */
    public String m2320e() {
        return this.f3496g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public byte[] m2321f() {
        try {
            if (this.f3491b == null || this.f3492c == null) {
                return null;
            }
            Object invoke = this.f3491b.getMethod("getData", new Class[0]).invoke(this.f3492c, new Object[0]);
            if (invoke instanceof byte[]) {
                return (byte[]) invoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
