package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.loc.C3830be;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.json.JSONObject;

/* compiled from: OfflineLocManager.java */
/* renamed from: com.loc.bq */
/* loaded from: classes4.dex */
public class C3842bq {

    /* renamed from: a */
    static int f3705a = 1000;

    /* renamed from: b */
    static boolean f3706b = false;

    /* renamed from: c */
    private static C3830be f3707c;

    /* renamed from: a */
    private static String m2631a(String str) {
        StringBuilder sb = new StringBuilder();
        f3707c = null;
        try {
            f3707c = C3830be.m2543a(new File(str), 2097152L);
            File file = new File(str);
            if (file.exists()) {
                boolean z = true;
                for (String str2 : file.list()) {
                    if (str2.contains(".0")) {
                        String m3226a = C3894t.m3226a(C3845bt.m2652a(f3707c, str2.split("\\.")[0], false));
                        if (z) {
                            z = false;
                        } else {
                            sb.append(",");
                        }
                        sb.append("{\"log\":\"");
                        sb.append(m3226a);
                        sb.append("\"}");
                    }
                }
            }
        } catch (IOException e) {
            C3897w.m3249a(e, "StatisticsManager", "getContent");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static synchronized void m2632a(int i, boolean z) {
        synchronized (C3842bq.class) {
            f3705a = i;
            f3706b = z;
        }
    }

    /* renamed from: a */
    public static void m2633a(Context context) {
        C3830be c3830be;
        String str;
        try {
            if (!m2636c(context)) {
                C3830be c3830be2 = f3707c;
                if (c3830be2 != null) {
                    try {
                        c3830be2.close();
                        f3707c = null;
                        return;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return;
                    }
                }
                return;
            }
            String m2631a = m2631a(C3898x.m3254a(context, C3898x.f4339h));
            if (TextUtils.isEmpty(m2631a)) {
                str = null;
            } else {
                str = "{\"pinfo\":\"" + C3887m.m3142b(C3894t.m3232a(m2637d(context))) + "\",\"els\":[" + m2631a + "]}";
            }
            if (TextUtils.isEmpty(str)) {
                C3830be c3830be3 = f3707c;
                if (c3830be3 != null) {
                    try {
                        c3830be3.close();
                        f3707c = null;
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        return;
                    }
                }
                return;
            }
            C3899y c3899y = new C3899y(C3894t.m3238c(C3894t.m3232a(str)), "6");
            C3834bi.m2600a();
            JSONObject jSONObject = new JSONObject(new String(C3834bi.m2602a(c3899y)));
            if (jSONObject.has("code") && jSONObject.getInt("code") == 1) {
                if (c3830be != null) {
                    try {
                        f3707c.m2573d();
                    } catch (Throwable th3) {
                        C3897w.m3249a(th3, "StatisticsManager", "getContent");
                    }
                }
            }
            C3830be c3830be4 = f3707c;
            if (c3830be4 != null) {
                try {
                    c3830be4.close();
                    f3707c = null;
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        } catch (Throwable th5) {
            try {
                C3897w.m3249a(th5, "OfflineLocManager", "updateOfflineLocData");
                C3830be c3830be5 = f3707c;
                if (c3830be5 != null) {
                    try {
                        c3830be5.close();
                        f3707c = null;
                    } catch (Throwable th6) {
                        th6.printStackTrace();
                    }
                }
            } finally {
                c3830be = f3707c;
                if (c3830be != null) {
                    try {
                        c3830be.close();
                        f3707c = null;
                    } catch (Throwable th7) {
                        th7.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m2634a(final C3841bp c3841bp, final Context context) {
        synchronized (C3842bq.class) {
            C3900z.m3265b().submit(new Runnable() { // from class: com.loc.bq.1
                @Override // java.lang.Runnable
                public final void run() {
                    C3830be c3830be;
                    byte[] m2630a;
                    synchronized (C3842bq.class) {
                        OutputStream outputStream = null;
                        try {
                            m2630a = C3841bp.this.m2630a();
                            c3830be = C3830be.m2543a(new File(C3898x.m3254a(context, C3898x.f4339h)), 2097152L);
                        } catch (Throwable th) {
                            th = th;
                            c3830be = null;
                        }
                        try {
                            c3830be.m2567a(C3842bq.f3705a);
                            C3830be.a m2569b = c3830be.m2569b(Long.toString(System.currentTimeMillis()));
                            outputStream = m2569b.m2578a();
                            outputStream.write(m2630a);
                            m2569b.m2579b();
                            c3830be.m2571c();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th2) {
                                    th2.printStackTrace();
                                }
                            }
                            if (c3830be != null) {
                                try {
                                    c3830be.close();
                                } catch (Throwable th3) {
                                    th = th3;
                                    th.printStackTrace();
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            try {
                                C3897w.m3249a(th, "OfflineLocManager", "applyOfflineLocEntity");
                                if (outputStream != null) {
                                    try {
                                        outputStream.close();
                                    } catch (Throwable th5) {
                                        th5.printStackTrace();
                                    }
                                }
                                if (c3830be != null) {
                                    try {
                                        c3830be.close();
                                    } catch (Throwable th6) {
                                        th = th6;
                                        th.printStackTrace();
                                    }
                                }
                            } finally {
                            }
                        }
                    }
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long m2635b(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        File file = new File(C3898x.m3254a(context, "f.log"));
        if (!file.exists()) {
            return 0L;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            long parseLong = Long.parseLong(C3894t.m3226a(bArr));
            try {
                fileInputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            return parseLong;
        } catch (Throwable th4) {
            th = th4;
            try {
                C3897w.m3249a(th, "OfflineLocManager", "getUpdateTime");
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

    /* renamed from: c */
    private static synchronized boolean m2636c(Context context) {
        synchronized (C3842bq.class) {
            try {
                if (C3888n.m3164m(context) == 1 || f3706b) {
                    if (System.currentTimeMillis() - m2635b(context) < 14400000) {
                        return false;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    FileOutputStream fileOutputStream = null;
                    try {
                        File file = new File(C3898x.m3254a(context, "f.log"));
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            fileOutputStream2.write(C3894t.m3232a(String.valueOf(currentTimeMillis)));
                        } catch (Throwable th) {
                            fileOutputStream = fileOutputStream2;
                            th = th;
                            try {
                                C3897w.m3249a(th, "OfflineLocManager", "updateLogUpdateTime");
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        th.printStackTrace();
                                        return true;
                                    }
                                }
                                return true;
                            } finally {
                            }
                        }
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                            return true;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    return true;
                }
            } catch (Throwable th5) {
                C3897w.m3249a(th5, "StatisticsManager", "isUpdate");
            }
            return false;
        }
    }

    /* renamed from: d */
    private static String m2637d(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"");
            sb.append(C3885k.m3127f(context));
            sb.append("\",\"platform\":\"android\",\"diu\":\"");
            sb.append(C3888n.m3168q(context));
            sb.append("\",\"mac\":\"");
            sb.append(C3888n.m3160i(context));
            sb.append("\",\"tid\":\"");
            sb.append(C3888n.m3157f(context));
            sb.append("\",\"manufacture\":\"");
            sb.append(Build.MANUFACTURER);
            sb.append("\",\"device\":\"");
            sb.append(Build.DEVICE);
            sb.append("\",\"sim\":\"");
            sb.append(C3888n.m3169r(context));
            sb.append("\",\"pkg\":\"");
            sb.append(C3885k.m3124c(context));
            sb.append("\",\"model\":\"");
            sb.append(Build.MODEL);
            sb.append("\",\"appversion\":\"");
            sb.append(C3885k.m3125d(context));
            sb.append("\"");
        } catch (Throwable th) {
            C3897w.m3249a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }
}
