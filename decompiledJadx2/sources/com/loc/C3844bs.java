package com.loc;

import android.content.Context;
import android.os.Build;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* compiled from: StatisticsManager.java */
/* renamed from: com.loc.bs */
/* loaded from: classes4.dex */
public final class C3844bs {

    /* renamed from: a */
    private static boolean f3715a = true;

    /* renamed from: a */
    public static void m2640a(Context context) {
        try {
            if (m2645c(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(" ");
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(" ");
                if (stringBuffer.length() != 53) {
                    return;
                }
                byte[] m3232a = C3894t.m3232a(stringBuffer.toString());
                byte[] m2644b = m2644b(context);
                byte[] m2651a = C3845bt.m2651a(context, C3898x.f4338g);
                byte[] bArr = new byte[m2644b.length + m2651a.length];
                System.arraycopy(m2644b, 0, bArr, 0, m2644b.length);
                System.arraycopy(m2651a, 0, bArr, m2644b.length, m2651a.length);
                byte[] m2643a = m2643a(bArr);
                byte[] bArr2 = new byte[m3232a.length + m2643a.length];
                System.arraycopy(m3232a, 0, bArr2, 0, m3232a.length);
                System.arraycopy(m2643a, 0, bArr2, m3232a.length, m2643a.length);
                C3899y c3899y = new C3899y(C3894t.m3238c(bArr2), "2");
                C3834bi.m2600a();
                C3834bi.m2602a(c3899y);
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "StatisticsManager", "updateStaticsData");
        }
    }

    /* renamed from: a */
    public static synchronized void m2641a(final C3843br c3843br, final Context context) {
        synchronized (C3844bs.class) {
            C3900z.m3265b().submit(new Runnable() { // from class: com.loc.bs.1
                @Override // java.lang.Runnable
                public final void run() {
                    C3845bt.m2650a(context, C3898x.f4338g, c3843br.m2639a());
                }
            });
        }
    }

    /* renamed from: a */
    public static synchronized void m2642a(final List<C3843br> list, final Context context) {
        synchronized (C3844bs.class) {
            C3900z.m3265b().submit(new Runnable() { // from class: com.loc.bs.2
                @Override // java.lang.Runnable
                public final void run() {
                    ByteArrayOutputStream byteArrayOutputStream;
                    Throwable th;
                    byte[] bArr = new byte[0];
                    try {
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } catch (Throwable th2) {
                            byteArrayOutputStream = null;
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    try {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            byteArrayOutputStream.write(((C3843br) it.next()).m2639a());
                        }
                        bArr = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                    } catch (Throwable th4) {
                        th = th4;
                        try {
                            C3897w.m3249a(th, "StatisticsEntity", "applyStaticsBatch");
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            C3845bt.m2650a(context, C3898x.f4338g, bArr);
                        } catch (Throwable th5) {
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Throwable th6) {
                                    th6.printStackTrace();
                                }
                            }
                            throw th5;
                        }
                    }
                    C3845bt.m2650a(context, C3898x.f4338g, bArr);
                }
            });
        }
    }

    /* renamed from: a */
    private static byte[] m2643a(byte[] bArr) {
        try {
            return C3887m.m3141a(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static byte[] m2644b(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            try {
                C3894t.m3229a(byteArrayOutputStream, "1.2.13.6");
                C3894t.m3229a(byteArrayOutputStream, "Android");
                C3894t.m3229a(byteArrayOutputStream, C3888n.m3168q(context));
                C3894t.m3229a(byteArrayOutputStream, C3888n.m3160i(context));
                C3894t.m3229a(byteArrayOutputStream, C3888n.m3157f(context));
                C3894t.m3229a(byteArrayOutputStream, Build.MANUFACTURER);
                C3894t.m3229a(byteArrayOutputStream, Build.MODEL);
                C3894t.m3229a(byteArrayOutputStream, Build.DEVICE);
                C3894t.m3229a(byteArrayOutputStream, C3888n.m3169r(context));
                C3894t.m3229a(byteArrayOutputStream, C3885k.m3124c(context));
                C3894t.m3229a(byteArrayOutputStream, C3885k.m3125d(context));
                C3894t.m3229a(byteArrayOutputStream, C3885k.m3127f(context));
                byteArrayOutputStream.write(new byte[]{0});
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                try {
                    C3897w.m3249a(th, "StatisticsManager", "getHeader");
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    throw th2;
                }
            }
        } catch (Throwable th4) {
            th4.printStackTrace();
        }
        return bArr;
    }

    /* renamed from: c */
    private static boolean m2645c(Context context) {
        try {
            if (C3888n.m3164m(context) != 1 || !f3715a || C3845bt.m2653b(context, C3898x.f4338g) < 30) {
                return false;
            }
            long m2654c = C3845bt.m2654c(context, "c.log");
            long time = new Date().getTime();
            if (time - m2654c < 3600000) {
                return false;
            }
            C3845bt.m2649a(context, time, "c.log");
            f3715a = false;
            return true;
        } catch (Throwable th) {
            C3897w.m3249a(th, "StatisticsManager", "isUpdate");
        }
        return false;
    }
}
