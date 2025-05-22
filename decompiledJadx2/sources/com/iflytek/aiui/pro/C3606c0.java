package com.iflytek.aiui.pro;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.iflytek.aiui.pro.C3644v;
import com.loc.C3898x;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* renamed from: com.iflytek.aiui.pro.c0 */
/* loaded from: classes4.dex */
public class C3606c0 {

    /* renamed from: a */
    static int f2450a = 1000;

    /* renamed from: b */
    private static ExecutorService f2451b;

    /* renamed from: c */
    private static Context f2452c;

    /* renamed from: d */
    private static C3644v f2453d;

    /* renamed from: com.iflytek.aiui.pro.c0$a */
    /* loaded from: classes4.dex */
    static final class a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C3598b0 f2454a;

        /* renamed from: b */
        final /* synthetic */ Context f2455b;

        a(C3598b0 c3598b0, Context context) {
            this.f2454a = c3598b0;
            this.f2455b = context;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0048, code lost:
        
            if (r2 != null) goto L65;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            C3644v c3644v;
            byte[] m1119b;
            synchronized (C3606c0.class) {
                OutputStream outputStream = null;
                try {
                    try {
                        m1119b = this.f2454a.m1119b();
                        c3644v = C3644v.d(new File(C3609d0.m1262a(this.f2455b, C3898x.f4339h)), 1, 1, 2097152L);
                    } catch (Throwable th) {
                        th = th;
                        c3644v = null;
                    }
                    try {
                        c3644v.g(C3606c0.f2450a);
                        C3644v.a l = c3644v.l(Long.toString(System.currentTimeMillis()));
                        outputStream = l.b(0);
                        outputStream.write(m1119b);
                        l.c();
                        c3644v.m();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } finally {
                                th.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            th.printStackTrace();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th3) {
                                    th3.printStackTrace();
                                }
                            }
                            if (c3644v != null) {
                                try {
                                    c3644v.close();
                                } catch (Throwable th4) {
                                }
                            }
                        } finally {
                        }
                    }
                } catch (Throwable th5) {
                }
            }
        }
    }

    /* renamed from: a */
    private static String m1209a(String str) {
        StringBuilder sb = new StringBuilder();
        f2453d = null;
        try {
            boolean z = true;
            f2453d = C3644v.d(new File(str), 1, 1, 2097152L);
            File file = new File(str);
            if (file.exists()) {
                for (String str2 : file.list()) {
                    if (str2.contains(".0")) {
                        String m = C3628n.m(C3609d0.m1263b(f2453d, str2.split("\\.")[0], false));
                        if (z) {
                            z = false;
                        } else {
                            sb.append(",");
                        }
                        sb.append("{\"log\":\"");
                        sb.append(m);
                        sb.append("\"}");
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static void m1210b() {
        synchronized (C3606c0.class) {
            try {
                f2452c = null;
                f2451b.shutdown();
                f2451b = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    public static void m1211c(Context context) {
        try {
            f2452c = context.getApplicationContext();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: d */
    private static void m1212d(Context context, long j) {
        Throwable th;
        FileOutputStream fileOutputStream;
        try {
            File file = new File(C3609d0.m1262a(context, "f.log"));
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            fileOutputStream.write(C3628n.j(String.valueOf(j)));
        } catch (Throwable th3) {
            th = th3;
            try {
                th.printStackTrace();
                if (fileOutputStream == null) {
                    return;
                }
                fileOutputStream.close();
            } catch (Throwable th4) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                throw th4;
            }
        }
        try {
            fileOutputStream.close();
        } catch (Throwable th6) {
            th6.printStackTrace();
        }
    }

    /* renamed from: e */
    public static void m1213e(C3598b0 c3598b0, Context context) {
        synchronized (C3606c0.class) {
            f2452c = context.getApplicationContext();
            m1214f().submit(new a(c3598b0, context));
        }
    }

    /* renamed from: f */
    public static ExecutorService m1214f() {
        ExecutorService executorService;
        synchronized (C3606c0.class) {
            try {
                ExecutorService executorService2 = f2451b;
                if (executorService2 == null || executorService2.isShutdown()) {
                    f2451b = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f2451b;
        }
        return executorService;
    }

    /* renamed from: g */
    private static void m1215g(Context context) {
        C3644v c3644v = f2453d;
        if (c3644v != null) {
            try {
                c3644v.o();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: h */
    private static String m1216h(Context context) {
        String m1209a = m1209a(C3609d0.m1262a(context, C3898x.f4339h));
        if (TextUtils.isEmpty(m1209a)) {
            return null;
        }
        return "{\"pinfo\":\"" + m1220l(context) + "\",\"els\":[" + m1209a + "]}";
    }

    /* renamed from: i */
    public static void m1217i() {
        try {
            try {
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        } catch (Throwable th2) {
            try {
                th2.printStackTrace();
                C3644v c3644v = f2453d;
                if (c3644v == null) {
                    return;
                } else {
                    c3644v.close();
                }
            } catch (Throwable th3) {
                C3644v c3644v2 = f2453d;
                if (c3644v2 != null) {
                    try {
                        c3644v2.close();
                        f2453d = null;
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
                throw th3;
            }
        }
        if (!m1219k(f2452c)) {
            C3644v c3644v3 = f2453d;
            if (c3644v3 != null) {
                try {
                    c3644v3.close();
                    f2453d = null;
                    return;
                } catch (Throwable th5) {
                    th5.printStackTrace();
                    return;
                }
            }
            return;
        }
        String m1216h = m1216h(f2452c);
        if (TextUtils.isEmpty(m1216h)) {
            C3644v c3644v4 = f2453d;
            if (c3644v4 != null) {
                try {
                    c3644v4.close();
                    f2453d = null;
                    return;
                } catch (Throwable th6) {
                    th6.printStackTrace();
                    return;
                }
            }
            return;
        }
        byte[] k = C3628n.k(C3628n.j(m1216h));
        C3569a0 c3569a0 = new C3569a0();
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf(k.length));
        byte[] j = C3628n.j("9aj&#k81");
        byte[] bArr = new byte[j.length + 50];
        System.arraycopy(k, 0, bArr, 0, 50);
        System.arraycopy(j, 0, bArr, 50, j.length);
        String format = String.format("http://logs.amap.com/ws/log/upload?product=%s&type=%s&platform=%s&channel=%s&sign=%s", "1", "6", "1", "open", C3628n.r(C3624l.m1397b(bArr)));
        c3569a0.m882e(hashMap);
        c3569a0.m885h(format);
        c3569a0.m883f(k);
        JSONObject jSONObject = new JSONObject(new String(C3652z.h(c3569a0, false)));
        if (jSONObject.has("code") && jSONObject.getInt("code") == 1) {
            m1215g(f2452c);
        }
        C3644v c3644v5 = f2453d;
        if (c3644v5 != null) {
            c3644v5.close();
            f2453d = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long m1218j(Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        File file = new File(C3609d0.m1262a(context, "f.log"));
        if (!file.exists()) {
            return 0L;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            long parseLong = Long.parseLong(C3628n.m(bArr));
            try {
                fileInputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            return parseLong;
        } catch (Throwable th4) {
            th = th4;
            try {
                th.printStackTrace();
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Throwable th5) {
                    th5.printStackTrace();
                    return System.currentTimeMillis();
                }
                return System.currentTimeMillis();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th6) {
                        th6.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: k */
    private static boolean m1219k(Context context) {
        synchronized (C3606c0.class) {
            try {
                if (C3618i.m1370n(context) == 1) {
                    if (System.currentTimeMillis() - m1218j(context) < 14400000) {
                        return false;
                    }
                    m1212d(context, System.currentTimeMillis());
                    return true;
                }
            } finally {
                return false;
            }
            return false;
        }
    }

    /* renamed from: l */
    private static String m1220l(Context context) {
        return C3622k.f(context, C3628n.j(m1221m(context)));
    }

    /* renamed from: m */
    private static String m1221m(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"");
            sb.append(C3618i.m1366j(context));
            sb.append("\",\"platform\":\"android\",\"diu\":\"");
            sb.append(C3618i.q(context));
            sb.append("\",\"mac\":\"");
            sb.append(C3618i.m1368l(context));
            sb.append("\",\"tid\":\"");
            sb.append(C3618i.m1367k(context));
            sb.append("\",\"manufacture\":\"");
            sb.append(Build.MANUFACTURER);
            sb.append("\",\"device\":\"");
            sb.append(Build.DEVICE);
            sb.append("\",\"sim\":\"");
            sb.append(C3618i.r(context));
            sb.append("\",\"pkg\":\"");
            sb.append(C3618i.m1362f(context));
            sb.append("\",\"model\":\"");
            sb.append(Build.MODEL);
            sb.append("\",\"appversion\":\"");
            sb.append(C3618i.m1364h(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }
}
