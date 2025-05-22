package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.amap.apis.utils.core.crash.d;
import com.loc.C3830be;
import com.loc.C3893s;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LogProcessor.java */
/* renamed from: com.loc.ad */
/* loaded from: classes4.dex */
public abstract class AbstractC3802ad {

    /* renamed from: a */
    static final List<d.b> f3554a = Collections.synchronizedList(new ArrayList());

    /* renamed from: b */
    private C3893s f3555b;

    /* renamed from: c */
    private int f3556c;

    /* renamed from: d */
    private InterfaceC3831bf f3557d;

    /* renamed from: e */
    private C3830be f3558e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LogProcessor.java */
    /* renamed from: com.loc.ad$a */
    /* loaded from: classes4.dex */
    public class a implements InterfaceC3831bf {

        /* renamed from: b */
        private C3812an f3560b;

        a(C3812an c3812an) {
            this.f3560b = c3812an;
        }

        @Override // com.loc.InterfaceC3831bf
        /* renamed from: a */
        public final void mo2415a(String str) {
            try {
                this.f3560b.m2444b(str, C3898x.m3253a(AbstractC3802ad.this.m2411b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC3802ad(int i) {
        this.f3556c = i;
    }

    /* renamed from: a */
    private static C3830be m2391a(Context context, String str) {
        try {
            File file = new File(C3898x.m3254a(context, str));
            if (file.exists() || file.mkdirs()) {
                return C3830be.m2543a(file, 20480L);
            }
            return null;
        } catch (IOException e) {
            C3897w.m3249a(e, "LogProcessor", "initDiskLru");
            return null;
        }
    }

    /* renamed from: a */
    private InterfaceC3831bf m2392a(C3812an c3812an) {
        try {
            if (this.f3557d == null) {
                this.f3557d = new a(c3812an);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f3557d;
    }

    /* renamed from: a */
    private static String m2393a(Throwable th) {
        try {
            return C3894t.m3224a(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static List<d.b> m2394a() {
        return f3554a;
    }

    /* renamed from: a */
    private static void m2395a(C3812an c3812an, String str, String str2, int i) {
        AbstractC3813ao m3258b = C3898x.m3258b(i);
        m3258b.m2448a(0);
        m3258b.m2452b(str);
        m3258b.m2449a(str2);
        c3812an.m2441a(m3258b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private void m2396a(List<? extends AbstractC3813ao> list, C3812an c3812an) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (AbstractC3813ao abstractC3813ao : list) {
            if (m2401c(abstractC3813ao.m2450b())) {
                c3812an.m2442a(abstractC3813ao.m2450b(), (Class<? extends AbstractC3813ao>) abstractC3813ao.getClass());
            } else {
                abstractC3813ao.m2448a(2);
                c3812an.m2443b(abstractC3813ao);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.loc.be] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11, types: [com.loc.be] */
    /* JADX WARN: Type inference failed for: r5v14, types: [com.loc.be] */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.loc.be] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.loc.be$b] */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.loc.be$b] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v15, types: [com.loc.be$b] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [com.loc.be$b] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* renamed from: a */
    private boolean m2397a(Context th, String str, String str2, String str3, C3812an c3812an) {
        Throwable th2;
        IOException e;
        OutputStream outputStream = null;
        try {
            try {
                try {
                    File file = new File(C3898x.m3254a(th, str2));
                    if (!file.exists() && !file.mkdirs()) {
                        return false;
                    }
                    th = C3830be.m2543a(file, 20480L);
                    try {
                        th.m2568a(m2392a(c3812an));
                        str2 = th.m2566a(str);
                        if (str2 != 0) {
                            if (str2 != 0) {
                                try {
                                    str2.close();
                                } catch (Throwable th3) {
                                    th3.printStackTrace();
                                }
                            }
                            if (th != 0 && !th.m2570b()) {
                                try {
                                    th.close();
                                } catch (Throwable th4) {
                                    th4.printStackTrace();
                                }
                            }
                            return false;
                        }
                        try {
                            byte[] m3232a = C3894t.m3232a(str3);
                            C3830be.a m2569b = th.m2569b(str);
                            outputStream = m2569b.m2578a();
                            outputStream.write(m3232a);
                            m2569b.m2579b();
                            th.m2571c();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th5) {
                                    th5.printStackTrace();
                                }
                            }
                            if (str2 != 0) {
                                try {
                                    str2.close();
                                } catch (Throwable th6) {
                                    th6.printStackTrace();
                                }
                            }
                            if (th == 0 || th.m2570b()) {
                                return true;
                            }
                            try {
                                th.close();
                                return true;
                            } catch (Throwable th7) {
                                th7.printStackTrace();
                                return true;
                            }
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th8) {
                                    th8.printStackTrace();
                                }
                            }
                            if (str2 != 0) {
                                try {
                                    str2.close();
                                } catch (Throwable th9) {
                                    th9.printStackTrace();
                                }
                            }
                            if (th != 0 && !th.m2570b()) {
                                th.close();
                                th = th;
                                str2 = str2;
                            }
                            return false;
                        } catch (Throwable th10) {
                            th2 = th10;
                            th2.printStackTrace();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th11) {
                                    th11.printStackTrace();
                                }
                            }
                            if (str2 != 0) {
                                try {
                                    str2.close();
                                } catch (Throwable th12) {
                                    th12.printStackTrace();
                                }
                            }
                            if (th != 0 && !th.m2570b()) {
                                th.close();
                                th = th;
                                str2 = str2;
                            }
                            return false;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        str2 = 0;
                    } catch (Throwable th13) {
                        th2 = th13;
                        str2 = 0;
                    }
                } catch (IOException e4) {
                    e = e4;
                    th = 0;
                    str2 = 0;
                } catch (Throwable th14) {
                    th2 = th14;
                    th = 0;
                    str2 = 0;
                }
            } catch (Throwable th15) {
                th = th15;
                th.printStackTrace();
                return false;
            }
        } finally {
        }
    }

    /* renamed from: a */
    public static boolean m2398a(String[] strArr, String str) {
        if (strArr != null && str != null) {
            try {
                String str2 = str;
                for (String str3 : strArr) {
                    str2 = str2.trim();
                    if (str2.startsWith("at ")) {
                        if (str2.contains(str3 + ".") && str2.endsWith(")")) {
                            return true;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* renamed from: b */
    private static String m2399b(String str) {
        try {
            return C3887m.m3143c(C3894t.m3232a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static boolean m2400b(String[] strArr, String str) {
        if (strArr != null && str != null) {
            try {
                for (String str2 : str.split("\n")) {
                    if (m2398a(strArr, str2.trim())) {
                        return true;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* renamed from: c */
    private boolean m2401c(String str) {
        C3830be c3830be = this.f3558e;
        if (c3830be == null) {
            return false;
        }
        try {
            return c3830be.m2572c(str);
        } catch (Throwable th) {
            C3897w.m3249a(th, "LogUpdateProcessor", "deleteLogData");
            return false;
        }
    }

    /* renamed from: d */
    private static int m2402d(String str) {
        C3899y c3899y = new C3899y(C3894t.m3238c(C3894t.m3232a(str)));
        try {
            C3834bi.m2600a();
            byte[] m2602a = C3834bi.m2602a(c3899y);
            if (m2602a == null) {
                return 0;
            }
            try {
                JSONObject jSONObject = new JSONObject(C3894t.m3226a(m2602a));
                if (jSONObject.has("code")) {
                    return jSONObject.getInt("code");
                }
                return 0;
            } catch (JSONException e) {
                C3897w.m3249a(e, "LogProcessor", "processUpdate");
                return 1;
            }
        } catch (C3884j e2) {
            int i = e2.m3119b() == 27 ? 0 : 1;
            C3897w.m3249a(e2, "LogProcessor", "processUpdate");
            return i;
        }
    }

    /* renamed from: d */
    private static List<C3893s> m2403d(Context context) {
        List<C3893s> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                list = new C3814ap(context, false).m2455a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }

    /* renamed from: e */
    private static String m2404e(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"");
            sb.append(C3885k.m3127f(context));
            sb.append("\",\"platform\":\"android\",\"diu\":\"");
            sb.append(C3888n.m3168q(context));
            sb.append("\",\"pkg\":\"");
            sb.append(C3885k.m3124c(context));
            sb.append("\",\"model\":\"");
            sb.append(Build.MODEL);
            sb.append("\",\"appname\":\"");
            sb.append(C3885k.m3122b(context));
            sb.append("\",\"appversion\":\"");
            sb.append(C3885k.m3125d(context));
            sb.append("\",\"sysversion\":\"");
            sb.append(Build.VERSION.RELEASE);
            sb.append("\",");
        } catch (Throwable th) {
            C3897w.m3249a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }

    /* renamed from: e */
    private String m2405e(String str) {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        C3830be.b m2566a;
        try {
            if (this.f3558e == null || (m2566a = this.f3558e.m2566a(str)) == null) {
                return null;
            }
            inputStream = m2566a.m2581a();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                String m3226a = C3894t.m3226a(byteArrayOutputStream.toByteArray());
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    C3897w.m3249a(e, "LogProcessor", "readLog1");
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        C3897w.m3249a(e2, "LogProcessor", "readLog2");
                    }
                }
                return m3226a;
            } catch (Throwable th2) {
                th = th2;
                try {
                    C3897w.m3249a(th, "LogProcessor", "readLog");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                            C3897w.m3249a(e3, "LogProcessor", "readLog1");
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            C3897w.m3249a(e4, "LogProcessor", "readLog2");
                        }
                    }
                    return null;
                } finally {
                }
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            byteArrayOutputStream = null;
        }
    }

    /* renamed from: f */
    private static String m2406f(Context context) {
        try {
            String m2404e = m2404e(context);
            if ("".equals(m2404e)) {
                return null;
            }
            return C3887m.m3142b(C3894t.m3232a(m2404e));
        } catch (Throwable th) {
            C3897w.m3249a(th, "LogProcessor", "getPublicInfo");
            return null;
        }
    }

    /* renamed from: a */
    protected String mo2390a(String str) {
        return C3890p.m3189c(str);
    }

    /* renamed from: a */
    protected abstract String mo2388a(List<C3893s> list);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2407a(Context context, Throwable th, String str, String str2) {
        String m2393a;
        List<C3893s> m2403d = m2403d(context);
        if (m2403d == null || m2403d.size() == 0 || (m2393a = m2393a(th)) == null || "".equals(m2393a)) {
            return;
        }
        for (C3893s c3893s : m2403d) {
            if (m2400b(c3893s.m3212f(), m2393a)) {
                m2410a(c3893s, context, th, m2393a.replaceAll("\n", "<br/>"), str, str2);
                return;
            }
        }
        if (m2393a.contains("com.amap.api.col")) {
            try {
                m2410a(new C3893s.a("collection", "1.0", "AMap_collection_1.0").m3221a(new String[]{"com.amap.api.collection"}).m3222a(), context, th, m2393a, str, str2);
            } catch (C3884j e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m2408a(C3893s c3893s) {
        this.f3555b = c3893s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2409a(C3893s c3893s, Context context, String str, String str2, String str3, String str4) {
        this.f3555b = c3893s;
        String m2646a = C3845bt.m2646a();
        String m2647a = C3845bt.m2647a(context, c3893s);
        C3885k.m3120a(context);
        if (str == null || "".equals(str)) {
            return;
        }
        int i = this.f3556c;
        StringBuilder sb = new StringBuilder();
        if (str3 != null) {
            sb.append("class:");
            sb.append(str3);
        }
        if (str4 != null) {
            sb.append(" method:");
            sb.append(str4);
            sb.append("$<br/>");
        }
        sb.append(str2);
        String mo2390a = mo2390a(str2);
        String m2648a = C3845bt.m2648a(m2647a, m2646a, i, str, sb.toString());
        if (m2648a == null || "".equals(m2648a)) {
            return;
        }
        String m2399b = m2399b(m2648a);
        String m3260c = C3898x.m3260c(this.f3556c);
        synchronized (Looper.getMainLooper()) {
            C3812an c3812an = new C3812an(context);
            m2397a(context, mo2390a, m3260c, m2399b, c3812an);
            m2395a(c3812an, c3893s.m3206a(), mo2390a, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2410a(C3893s c3893s, Context context, Throwable th, String str, String str2, String str3) {
        m2409a(c3893s, context, th.toString(), str, str2, str3);
    }

    /* renamed from: a */
    protected abstract boolean mo2389a(Context context);

    /* renamed from: b */
    protected final int m2411b() {
        return this.f3556c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m2412b(Context context) {
        String mo2388a;
        List<C3893s> m2403d = m2403d(context);
        if (m2403d == null || m2403d.size() == 0 || (mo2388a = mo2388a(m2403d)) == null || "".equals(mo2388a)) {
            return;
        }
        String m2646a = C3845bt.m2646a();
        String m2647a = C3845bt.m2647a(context, this.f3555b);
        C3885k.m3120a(context);
        int i = this.f3556c;
        String m2648a = C3845bt.m2648a(m2647a, m2646a, i, "ANR", mo2388a);
        if (m2648a == null || "".equals(m2648a)) {
            return;
        }
        String mo2390a = mo2390a(mo2388a);
        String m2399b = m2399b(m2648a);
        String m3260c = C3898x.m3260c(this.f3556c);
        synchronized (Looper.getMainLooper()) {
            C3812an c3812an = new C3812an(context);
            m2397a(context, mo2390a, m3260c, m2399b, c3812an);
            m2395a(c3812an, this.f3555b.m3206a(), mo2390a, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final void m2413c() {
        try {
            if (this.f3558e == null || this.f3558e.m2570b()) {
                return;
            }
            this.f3558e.close();
        } catch (IOException e) {
            C3897w.m3249a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable th) {
            C3897w.m3249a(th, "LogProcessor", "closeDiskLru");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0074 A[Catch: all -> 0x00db, TryCatch #2 {, blocks: (B:10:0x0021, B:14:0x003d, B:16:0x004a, B:19:0x0052, B:20:0x006e, B:22:0x0074, B:25:0x0084, B:28:0x008c, B:31:0x00ad, B:34:0x00a8, B:41:0x00ca, B:43:0x00cc, B:45:0x00d2, B:46:0x00d7, B:48:0x00bf, B:49:0x00d9, B:53:0x0036, B:56:0x001a, B:13:0x0029, B:9:0x000c), top: B:8:0x000c, outer: #3, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ca A[Catch: all -> 0x00db, DONT_GENERATE, TryCatch #2 {, blocks: (B:10:0x0021, B:14:0x003d, B:16:0x004a, B:19:0x0052, B:20:0x006e, B:22:0x0074, B:25:0x0084, B:28:0x008c, B:31:0x00ad, B:34:0x00a8, B:41:0x00ca, B:43:0x00cc, B:45:0x00d2, B:46:0x00d7, B:48:0x00bf, B:49:0x00d9, B:53:0x0036, B:56:0x001a, B:13:0x0029, B:9:0x000c), top: B:8:0x000c, outer: #3, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00cc A[Catch: all -> 0x00db, TryCatch #2 {, blocks: (B:10:0x0021, B:14:0x003d, B:16:0x004a, B:19:0x0052, B:20:0x006e, B:22:0x0074, B:25:0x0084, B:28:0x008c, B:31:0x00ad, B:34:0x00a8, B:41:0x00ca, B:43:0x00cc, B:45:0x00d2, B:46:0x00d7, B:48:0x00bf, B:49:0x00d9, B:53:0x0036, B:56:0x001a, B:13:0x0029, B:9:0x000c), top: B:8:0x000c, outer: #3, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bf A[Catch: all -> 0x00db, TryCatch #2 {, blocks: (B:10:0x0021, B:14:0x003d, B:16:0x004a, B:19:0x0052, B:20:0x006e, B:22:0x0074, B:25:0x0084, B:28:0x008c, B:31:0x00ad, B:34:0x00a8, B:41:0x00ca, B:43:0x00cc, B:45:0x00d2, B:46:0x00d7, B:48:0x00bf, B:49:0x00d9, B:53:0x0036, B:56:0x001a, B:13:0x0029, B:9:0x000c), top: B:8:0x000c, outer: #3, inners: #0, #1 }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2414c(Context context) {
        C3812an c3812an;
        List<? extends AbstractC3813ao> m2440a;
        boolean z;
        String sb;
        try {
            if (mo2389a(context)) {
                synchronized (Looper.getMainLooper()) {
                    try {
                        this.f3558e = m2391a(context, C3898x.m3260c(this.f3556c));
                    } finally {
                        c3812an = new C3812an(context);
                        m2396a(c3812an.m2440a(2, C3898x.m3253a(this.f3556c)), c3812an);
                        m2440a = c3812an.m2440a(0, C3898x.m3253a(this.f3556c));
                        if (m2440a != null) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("{\"pinfo\":\"");
                            sb2.append(m2406f(context));
                            sb2.append("\",\"els\":[");
                            z = true;
                            while (r11.hasNext()) {
                            }
                            if (z) {
                            }
                            if (sb != null) {
                            }
                        }
                    }
                    c3812an = new C3812an(context);
                    try {
                        m2396a(c3812an.m2440a(2, C3898x.m3253a(this.f3556c)), c3812an);
                    } catch (Throwable th) {
                        C3897w.m3249a(th, "LogProcessor", "processDeleteFail");
                    }
                    m2440a = c3812an.m2440a(0, C3898x.m3253a(this.f3556c));
                    if (m2440a != null && m2440a.size() != 0) {
                        StringBuilder sb22 = new StringBuilder();
                        sb22.append("{\"pinfo\":\"");
                        sb22.append(m2406f(context));
                        sb22.append("\",\"els\":[");
                        z = true;
                        for (AbstractC3813ao abstractC3813ao : m2440a) {
                            String m2405e = m2405e(abstractC3813ao.m2450b());
                            if (m2405e != null && !"".equals(m2405e)) {
                                String str = m2405e + "||" + abstractC3813ao.m2453c();
                                if (z) {
                                    z = false;
                                } else {
                                    sb22.append(",");
                                }
                                sb22.append("{\"log\":\"");
                                sb22.append(str);
                                sb22.append("\"}");
                            }
                        }
                        if (z) {
                            sb22.append("]}");
                            sb = sb22.toString();
                        } else {
                            sb = null;
                        }
                        if (sb != null) {
                            return;
                        }
                        if (m2402d(sb) == 1) {
                            int i = this.f3556c;
                            m2396a(m2440a, c3812an);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C3897w.m3249a(th2, "LogProcessor", "processUpdateLog");
        }
    }
}
