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

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ac */
/* loaded from: classes.dex */
public class C3573ac {

    /* renamed from: a */
    static int f2269a = 1000;

    /* renamed from: b */
    private static ExecutorService f2270b;

    /* renamed from: c */
    private static Context f2271c;

    /* renamed from: d */
    private static C3644v f2272d;

    /* renamed from: a */
    private static String m942a(String str) {
        StringBuilder sb = new StringBuilder();
        f2272d = null;
        try {
            boolean z = true;
            f2272d = C3644v.m1553a(new File(str), 1, 1, 2097152L);
            File file = new File(str);
            if (file.exists()) {
                for (String str2 : file.list()) {
                    if (str2.contains(".0")) {
                        String m1418b = C3628n.m1418b(C3574ad.m956a(f2272d, str2.split("\\.")[0], false));
                        if (z) {
                            z = false;
                        } else {
                            sb.append(",");
                        }
                        sb.append("{\"log\":\"");
                        sb.append(m1418b);
                        sb.append("\"}");
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static void m943a() {
        synchronized (C3573ac.class) {
            try {
                f2271c = null;
                f2270b.shutdown();
                f2270b = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static void m944a(Context context) {
        try {
            f2271c = context.getApplicationContext();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m945a(Context context, long j) {
        Throwable th;
        FileOutputStream fileOutputStream;
        try {
            try {
                File file = new File(C3574ad.m955a(context, "f.log"));
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
            try {
                fileOutputStream.write(C3628n.m1415a(String.valueOf(j)));
                fileOutputStream.close();
            } catch (Throwable th3) {
                th = th3;
                try {
                    th.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
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
        } catch (Throwable th6) {
            th6.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m946a(final C3572ab c3572ab, final Context context) {
        synchronized (C3573ac.class) {
            f2271c = context.getApplicationContext();
            m947b().submit(new Runnable() { // from class: com.iflytek.aiui.pro.ac.1
                @Override // java.lang.Runnable
                public void run() {
                    C3644v c3644v;
                    byte[] m941a;
                    synchronized (C3573ac.class) {
                        OutputStream outputStream = null;
                        try {
                            try {
                                m941a = C3572ab.this.m941a();
                                c3644v = C3644v.m1553a(new File(C3574ad.m955a(context, C3898x.f4339h)), 1, 1, 2097152L);
                            } catch (Throwable th) {
                                th = th;
                                c3644v = null;
                            }
                            try {
                                c3644v.m1575a(C3573ac.f2269a);
                                C3644v.a m1576b = c3644v.m1576b(Long.toString(System.currentTimeMillis()));
                                outputStream = m1576b.m1583a(0);
                                outputStream.write(m941a);
                                m1576b.m1584a();
                                c3644v.m1577b();
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (Throwable th2) {
                                        th2.printStackTrace();
                                    }
                                }
                                if (c3644v != null) {
                                    try {
                                        c3644v.close();
                                    } catch (Throwable th3) {
                                        th = th3;
                                        th.printStackTrace();
                                    }
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                try {
                                    th.printStackTrace();
                                    if (outputStream != null) {
                                        try {
                                            outputStream.close();
                                        } catch (Throwable th5) {
                                            th5.printStackTrace();
                                        }
                                    }
                                    if (c3644v != null) {
                                        try {
                                            c3644v.close();
                                        } catch (Throwable th6) {
                                            th = th6;
                                            th.printStackTrace();
                                        }
                                    }
                                } finally {
                                }
                            }
                        } catch (Throwable th7) {
                            throw th7;
                        }
                    }
                }
            });
        }
    }

    /* renamed from: b */
    public static ExecutorService m947b() {
        ExecutorService executorService;
        synchronized (C3573ac.class) {
            try {
                if (f2270b == null || f2270b.isShutdown()) {
                    f2270b = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f2270b;
        }
        return executorService;
    }

    /* renamed from: b */
    private static void m948b(Context context) {
        C3644v c3644v = f2272d;
        if (c3644v != null) {
            try {
                c3644v.m1578c();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    private static String m949c(Context context) {
        String m942a = m942a(C3574ad.m955a(context, C3898x.f4339h));
        if (TextUtils.isEmpty(m942a)) {
            return null;
        }
        return "{\"pinfo\":\"" + m953f(context) + "\",\"els\":[" + m942a + "]}";
    }

    /* renamed from: c */
    public static void m950c() {
        try {
            try {
                if (!m952e(f2271c)) {
                    C3644v c3644v = f2272d;
                    if (c3644v != null) {
                        c3644v.close();
                        f2272d = null;
                        return;
                    }
                    return;
                }
                String m949c = m949c(f2271c);
                if (TextUtils.isEmpty(m949c)) {
                    C3644v c3644v2 = f2272d;
                    if (c3644v2 != null) {
                        c3644v2.close();
                        f2272d = null;
                        return;
                    }
                    return;
                }
                byte[] m1416a = C3628n.m1416a(C3628n.m1415a(m949c));
                C3571aa c3571aa = new C3571aa();
                HashMap hashMap = new HashMap();
                hashMap.put("Content-Type", "application/zip");
                hashMap.put("Content-Length", String.valueOf(m1416a.length));
                byte[] m1415a = C3628n.m1415a("9aj&#k81");
                byte[] bArr = new byte[m1415a.length + 50];
                System.arraycopy(m1416a, 0, bArr, 0, 50);
                System.arraycopy(m1415a, 0, bArr, 50, m1415a.length);
                String format = String.format("http://logs.amap.com/ws/log/upload?product=%s&type=%s&platform=%s&channel=%s&sign=%s", "1", "6", "1", "open", C3628n.m1423d(C3624l.m1393a(bArr)));
                c3571aa.mo923a(hashMap);
                c3571aa.mo921a(format);
                c3571aa.m924a(m1416a);
                JSONObject jSONObject = new JSONObject(new String(C3652z.m1634a(c3571aa, false)));
                if (jSONObject.has("code") && jSONObject.getInt("code") == 1) {
                    m948b(f2271c);
                }
                C3644v c3644v3 = f2272d;
                if (c3644v3 != null) {
                    c3644v3.close();
                    f2272d = null;
                }
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    C3644v c3644v4 = f2272d;
                    if (c3644v4 != null) {
                        c3644v4.close();
                        f2272d = null;
                    }
                } catch (Throwable th2) {
                    C3644v c3644v5 = f2272d;
                    if (c3644v5 != null) {
                        try {
                            c3644v5.close();
                            f2272d = null;
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th4) {
            th4.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long m951d(Context context) {
        FileInputStream fileInputStream;
        File file = new File(C3574ad.m955a(context, "f.log"));
        if (!file.exists()) {
            return 0L;
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            long parseLong = Long.parseLong(C3628n.m1418b(bArr));
            try {
                fileInputStream.close();
                return parseLong;
            } catch (Throwable th2) {
                th2.printStackTrace();
                return parseLong;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            try {
                th.printStackTrace();
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Throwable th4) {
                    th4.printStackTrace();
                    return System.currentTimeMillis();
                }
                return System.currentTimeMillis();
            } finally {
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: e */
    private static boolean m952e(Context context) {
        boolean z;
        synchronized (C3573ac.class) {
            z = true;
            try {
            } finally {
                z = false;
                return z;
            }
            if (C3618i.m1365i(context) == 1) {
                if (System.currentTimeMillis() - m951d(context) >= 14400000) {
                    m945a(context, System.currentTimeMillis());
                }
            }
            z = false;
        }
        return z;
    }

    /* renamed from: f */
    private static String m953f(Context context) {
        return C3622k.m1390b(context, C3628n.m1415a(m954g(context)));
    }

    /* renamed from: g */
    private static String m954g(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"");
            sb.append(C3618i.m1361e(context));
            sb.append("\",\"platform\":\"android\",\"diu\":\"");
            sb.append(C3618i.m1368l(context));
            sb.append("\",\"mac\":\"");
            sb.append(C3618i.m1363g(context));
            sb.append("\",\"tid\":\"");
            sb.append(C3618i.m1362f(context));
            sb.append("\",\"manufacture\":\"");
            sb.append(Build.MANUFACTURER);
            sb.append("\",\"device\":\"");
            sb.append(Build.DEVICE);
            sb.append("\",\"sim\":\"");
            sb.append(C3618i.m1369m(context));
            sb.append("\",\"pkg\":\"");
            sb.append(C3618i.m1357b(context));
            sb.append("\",\"model\":\"");
            sb.append(Build.MODEL);
            sb.append("\",\"appversion\":\"");
            sb.append(C3618i.m1359c(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }
}
