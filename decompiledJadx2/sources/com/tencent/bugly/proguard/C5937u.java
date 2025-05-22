package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import com.tencent.bugly.C5865b;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.u */
/* loaded from: classes7.dex */
public final class C5937u {

    /* renamed from: b */
    private static C5937u f8220b;

    /* renamed from: a */
    public boolean f8221a;

    /* renamed from: d */
    private final Context f8223d;

    /* renamed from: f */
    private long f8225f;

    /* renamed from: g */
    private long f8226g;

    /* renamed from: k */
    private String f8230k;

    /* renamed from: e */
    private Map<Integer, Long> f8224e = new HashMap();

    /* renamed from: h */
    private LinkedBlockingQueue<Runnable> f8227h = new LinkedBlockingQueue<>();

    /* renamed from: i */
    private LinkedBlockingQueue<Runnable> f8228i = new LinkedBlockingQueue<>();

    /* renamed from: j */
    private final Object f8229j = new Object();

    /* renamed from: l */
    private byte[] f8231l = null;

    /* renamed from: m */
    private long f8232m = 0;

    /* renamed from: n */
    private byte[] f8233n = null;

    /* renamed from: o */
    private long f8234o = 0;

    /* renamed from: p */
    private String f8235p = null;

    /* renamed from: q */
    private long f8236q = 0;

    /* renamed from: r */
    private final Object f8237r = new Object();

    /* renamed from: s */
    private boolean f8238s = false;

    /* renamed from: t */
    private final Object f8239t = new Object();

    /* renamed from: u */
    private int f8240u = 0;

    /* renamed from: c */
    private final C5932p f8222c = C5932p.m3740a();

    /* renamed from: a */
    static /* synthetic */ boolean m3780a(C5937u c5937u, boolean z) {
        c5937u.f8238s = false;
        return false;
    }

    /* renamed from: b */
    static /* synthetic */ int m3783b(C5937u c5937u) {
        int i = c5937u.f8240u - 1;
        c5937u.f8240u = i;
        return i;
    }

    private C5937u(Context context) {
        this.f8230k = null;
        this.f8221a = true;
        this.f8223d = context;
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException unused) {
            C5940x.m3818a("[UploadManager] Error: Can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.f8221a = false;
        }
        if (this.f8221a) {
            this.f8230k = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB";
        }
    }

    /* renamed from: a */
    public static synchronized C5937u m3774a(Context context) {
        C5937u c5937u;
        synchronized (C5937u.class) {
            if (f8220b == null) {
                f8220b = new C5937u(context);
            }
            c5937u = f8220b;
        }
        return c5937u;
    }

    /* renamed from: a */
    public static synchronized C5937u m3773a() {
        C5937u c5937u;
        synchronized (C5937u.class) {
            c5937u = f8220b;
        }
        return c5937u;
    }

    /* renamed from: a */
    public final void m3795a(int i, C5912ap c5912ap, String str, String str2, InterfaceC5936t interfaceC5936t, long j, boolean z) {
        try {
            m3779a(new RunnableC5938v(this.f8223d, i, c5912ap.f8078g, C5896a.m3621a((Object) c5912ap), str, str2, interfaceC5936t, this.f8221a, z), true, true, j);
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m3793a(int i, int i2, byte[] bArr, String str, String str2, InterfaceC5936t interfaceC5936t, int i3, int i4, boolean z, Map<String, String> map) {
        try {
            m3779a(new RunnableC5938v(this.f8223d, i, i2, bArr, str, str2, interfaceC5936t, this.f8221a, i3, i4, false, map), z, false, 0L);
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m3796a(int i, C5912ap c5912ap, String str, String str2, InterfaceC5936t interfaceC5936t, boolean z) {
        m3793a(i, c5912ap.f8078g, C5896a.m3621a((Object) c5912ap), str, str2, interfaceC5936t, 0, 0, z, null);
    }

    /* renamed from: a */
    public final long m3792a(boolean z) {
        long j;
        long m3876b = C5942z.m3876b();
        int i = z ? 5 : 3;
        List<C5934r> m3757a = this.f8222c.m3757a(i);
        if (m3757a != null && m3757a.size() > 0) {
            j = 0;
            try {
                C5934r c5934r = m3757a.get(0);
                if (c5934r.f8214e >= m3876b) {
                    j = C5942z.m3889c(c5934r.f8216g);
                    if (i == 3) {
                        this.f8225f = j;
                    } else {
                        this.f8226g = j;
                    }
                    m3757a.remove(c5934r);
                }
            } catch (Throwable th) {
                C5940x.m3819a(th);
            }
            if (m3757a.size() > 0) {
                this.f8222c.m3759a(m3757a);
            }
        } else {
            j = z ? this.f8226g : this.f8225f;
        }
        C5940x.m3823c("[UploadManager] Local network consume: %d KB", Long.valueOf(j / 1024));
        return j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final synchronized void m3798a(long j, boolean z) {
        int i = z ? 5 : 3;
        C5934r c5934r = new C5934r();
        c5934r.f8211b = i;
        c5934r.f8214e = C5942z.m3876b();
        c5934r.f8212c = "";
        c5934r.f8213d = "";
        c5934r.f8216g = C5942z.m3892c(j);
        this.f8222c.m3763b(i);
        this.f8222c.m3762a(c5934r);
        if (z) {
            this.f8226g = j;
        } else {
            this.f8225f = j;
        }
        C5940x.m3823c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / 1024));
    }

    /* renamed from: a */
    public final synchronized void m3794a(int i, long j) {
        if (i < 0) {
            C5940x.m3825e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
            return;
        }
        this.f8224e.put(Integer.valueOf(i), Long.valueOf(j));
        C5934r c5934r = new C5934r();
        c5934r.f8211b = i;
        c5934r.f8214e = j;
        c5934r.f8212c = "";
        c5934r.f8213d = "";
        c5934r.f8216g = new byte[0];
        this.f8222c.m3763b(i);
        this.f8222c.m3762a(c5934r);
        C5940x.m3823c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), C5942z.m3853a(j));
    }

    /* renamed from: a */
    public final synchronized long m3791a(int i) {
        long j = 0;
        if (i >= 0) {
            Long l = this.f8224e.get(Integer.valueOf(i));
            if (l != null) {
                return l.longValue();
            }
            List<C5934r> m3757a = this.f8222c.m3757a(i);
            if (m3757a != null && m3757a.size() > 0) {
                if (m3757a.size() > 1) {
                    for (C5934r c5934r : m3757a) {
                        if (c5934r.f8214e > j) {
                            j = c5934r.f8214e;
                        }
                    }
                    this.f8222c.m3763b(i);
                } else {
                    try {
                        j = m3757a.get(0).f8214e;
                    } catch (Throwable th) {
                        C5940x.m3819a(th);
                    }
                }
            }
        } else {
            C5940x.m3825e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return j;
    }

    /* renamed from: b */
    public final boolean m3803b(int i) {
        if (C5865b.f7644c) {
            C5940x.m3823c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - m3791a(i);
        C5940x.m3823c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(currentTimeMillis / 1000), Integer.valueOf(i));
        if (currentTimeMillis >= 30000) {
            return true;
        }
        C5940x.m3818a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
        return false;
    }

    /* renamed from: c */
    private static boolean m3785c() {
        C5940x.m3823c("[UploadManager] Drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            C5932p m3740a = C5932p.m3740a();
            if (m3740a == null) {
                C5940x.m3824d("[UploadManager] Failed to get Database", new Object[0]);
                return false;
            }
            return m3740a.m3760a(555, "security_info", (InterfaceC5931o) null, true);
        } catch (Throwable th) {
            C5940x.m3819a(th);
            return false;
        }
    }

    /* renamed from: d */
    private boolean m3788d() {
        C5940x.m3823c("[UploadManager] Record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            C5932p m3740a = C5932p.m3740a();
            if (m3740a == null) {
                C5940x.m3824d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            StringBuilder sb = new StringBuilder();
            if (this.f8233n != null) {
                sb.append(Base64.encodeToString(this.f8233n, 0));
                sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (this.f8234o != 0) {
                    sb.append(Long.toString(this.f8234o));
                } else {
                    sb.append("null");
                }
                sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (this.f8235p != null) {
                    sb.append(this.f8235p);
                } else {
                    sb.append("null");
                }
                sb.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (this.f8236q != 0) {
                    sb.append(Long.toString(this.f8236q));
                } else {
                    sb.append("null");
                }
                m3740a.m3761a(555, "security_info", sb.toString().getBytes(), (InterfaceC5931o) null, true);
                return true;
            }
            C5940x.m3823c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            C5940x.m3819a(th);
            m3785c();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00de A[Catch: all -> 0x00e2, TRY_LEAVE, TryCatch #0 {all -> 0x00e2, blocks: (B:3:0x0020, B:5:0x0026, B:8:0x002e, B:10:0x0037, B:12:0x003d, B:14:0x0052, B:17:0x005c, B:23:0x006e, B:25:0x0076, B:27:0x007e, B:33:0x0090, B:35:0x0096, B:37:0x009e, B:39:0x00a6, B:41:0x00ac, B:43:0x00b5, B:49:0x00c7, B:52:0x00de, B:54:0x00cb, B:46:0x00bd, B:20:0x0064, B:30:0x0086), top: B:2:0x0020, inners: #1, #2, #3 }] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m3790e() {
        boolean z;
        C5940x.m3823c("[UploadManager] Load security info from database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            C5932p m3740a = C5932p.m3740a();
            if (m3740a == null) {
                C5940x.m3824d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            Map<String, byte[]> m3758a = m3740a.m3758a(555, (InterfaceC5931o) null, true);
            if (m3758a != null && m3758a.containsKey("security_info")) {
                String str = new String(m3758a.get("security_info"));
                String[] split = str.split(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (split.length == 4) {
                    if (!split[0].isEmpty() && !split[0].equals("null")) {
                        try {
                            this.f8233n = Base64.decode(split[0], 0);
                        } catch (Throwable th) {
                            C5940x.m3819a(th);
                            z = true;
                        }
                    }
                    z = false;
                    if (!z && !split[1].isEmpty() && !split[1].equals("null")) {
                        try {
                            this.f8234o = Long.parseLong(split[1]);
                        } catch (Throwable th2) {
                            C5940x.m3819a(th2);
                            z = true;
                        }
                    }
                    if (!z && !split[2].isEmpty() && !split[2].equals("null")) {
                        this.f8235p = split[2];
                    }
                    if (!z && !split[3].isEmpty() && !split[3].equals("null")) {
                        try {
                            this.f8236q = Long.parseLong(split[3]);
                        } catch (Throwable th3) {
                            C5940x.m3819a(th3);
                        }
                    }
                    if (z) {
                        m3785c();
                    }
                } else {
                    C5940x.m3818a("SecurityInfo = %s, Strings.length = %d", str, Integer.valueOf(split.length));
                }
                z = true;
                if (z) {
                }
            }
            return true;
        } catch (Throwable th4) {
            C5940x.m3819a(th4);
            return false;
        }
    }

    /* renamed from: b */
    protected final boolean m3802b() {
        if (this.f8235p == null || this.f8236q == 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() + this.f8232m;
        long j = this.f8236q;
        if (j >= currentTimeMillis) {
            return true;
        }
        C5940x.m3823c("[UploadManager] Session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(j), new Date(this.f8236q).toString(), Long.valueOf(currentTimeMillis), new Date(currentTimeMillis).toString());
        return false;
    }

    /* renamed from: b */
    protected final void m3801b(boolean z) {
        synchronized (this.f8237r) {
            C5940x.m3823c("[UploadManager] Clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.f8233n = null;
            this.f8235p = null;
            this.f8236q = 0L;
        }
        if (z) {
            m3785c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006c A[Catch: all -> 0x0159, TRY_LEAVE, TryCatch #3 {, blocks: (B:9:0x001c, B:12:0x0049, B:13:0x0050, B:19:0x0062, B:23:0x006c, B:29:0x008d, B:31:0x0080, B:36:0x0093, B:42:0x00b4, B:44:0x00a7, B:47:0x00b7, B:93:0x0059, B:95:0x005d, B:39:0x009d, B:26:0x0076), top: B:8:0x001c, inners: #0, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0093 A[Catch: all -> 0x0159, TRY_LEAVE, TryCatch #3 {, blocks: (B:9:0x001c, B:12:0x0049, B:13:0x0050, B:19:0x0062, B:23:0x006c, B:29:0x008d, B:31:0x0080, B:36:0x0093, B:42:0x00b4, B:44:0x00a7, B:47:0x00b7, B:93:0x0059, B:95:0x005d, B:39:0x009d, B:26:0x0076), top: B:8:0x001c, inners: #0, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m3784c(int i) {
        final int i2;
        int i3;
        int i4;
        int i5;
        if (i < 0) {
            C5940x.m3818a("[UploadManager] Number of task to execute should >= 0", new Object[0]);
            return;
        }
        C5939w m3810a = C5939w.m3810a();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        final LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        synchronized (this.f8229j) {
            C5940x.m3823c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            int size = this.f8227h.size();
            int size2 = this.f8228i.size();
            if (size == 0 && size2 == 0) {
                C5940x.m3823c("[UploadManager] There is no upload task in queue.", new Object[0]);
                return;
            }
            if (i != 0) {
                if (i < size) {
                    size = i;
                    i2 = 0;
                } else if (i < size + size2) {
                    i2 = i - size;
                }
                if (m3810a != null || !m3810a.m3815c()) {
                    i2 = 0;
                }
                for (i3 = 0; i3 < size; i3++) {
                    Runnable peek = this.f8227h.peek();
                    if (peek == null) {
                        break;
                    }
                    try {
                        linkedBlockingQueue.put(peek);
                        this.f8227h.poll();
                    } catch (Throwable th) {
                        C5940x.m3825e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th.getMessage());
                    }
                }
                for (i4 = 0; i4 < i2; i4++) {
                    Runnable peek2 = this.f8228i.peek();
                    if (peek2 == null) {
                        break;
                    }
                    try {
                        linkedBlockingQueue2.put(peek2);
                        this.f8228i.poll();
                    } catch (Throwable th2) {
                        C5940x.m3825e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th2.getMessage());
                    }
                }
                if (size > 0) {
                    C5940x.m3823c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(size), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                }
                for (i5 = 0; i5 < size; i5++) {
                    final Runnable runnable = (Runnable) linkedBlockingQueue.poll();
                    if (runnable == null) {
                        break;
                    }
                    synchronized (this.f8229j) {
                        if (this.f8240u >= 2 && m3810a != null) {
                            m3810a.m3812a(runnable);
                        } else {
                            C5940x.m3818a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
                            if (C5942z.m3860a(new Runnable() { // from class: com.tencent.bugly.proguard.u.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    runnable.run();
                                    synchronized (C5937u.this.f8229j) {
                                        C5937u.m3783b(C5937u.this);
                                    }
                                }
                            }, "BUGLY_ASYNC_UPLOAD") != null) {
                                synchronized (this.f8229j) {
                                    this.f8240u++;
                                }
                            } else {
                                C5940x.m3824d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new Object[0]);
                                m3781a(runnable, true);
                            }
                        }
                    }
                }
                if (i2 > 0) {
                    C5940x.m3823c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(i2), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                }
                if (m3810a == null) {
                    m3810a.m3812a(new Runnable(this) { // from class: com.tencent.bugly.proguard.u.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            Runnable runnable2;
                            for (int i6 = 0; i6 < i2 && (runnable2 = (Runnable) linkedBlockingQueue2.poll()) != null; i6++) {
                                runnable2.run();
                            }
                        }
                    });
                    return;
                }
                return;
            }
            i2 = size2;
            if (m3810a != null) {
            }
            i2 = 0;
            while (i3 < size) {
            }
            while (i4 < i2) {
            }
            if (size > 0) {
            }
            while (i5 < size) {
            }
            if (i2 > 0) {
            }
            if (m3810a == null) {
            }
        }
    }

    /* renamed from: a */
    private boolean m3781a(Runnable runnable, boolean z) {
        if (runnable == null) {
            C5940x.m3818a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            C5940x.m3823c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f8229j) {
                if (z) {
                    this.f8227h.put(runnable);
                } else {
                    this.f8228i.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            C5940x.m3825e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3778a(Runnable runnable, long j) {
        if (runnable == null) {
            C5940x.m3824d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        C5940x.m3823c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread m3860a = C5942z.m3860a(runnable, "BUGLY_SYNC_UPLOAD");
        if (m3860a == null) {
            C5940x.m3825e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            m3781a(runnable, true);
            return;
        }
        try {
            m3860a.join(j);
        } catch (Throwable th) {
            C5940x.m3825e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            m3781a(runnable, true);
            m3784c(0);
        }
    }

    /* renamed from: a */
    private void m3779a(Runnable runnable, boolean z, boolean z2, long j) {
        if (runnable == null) {
            C5940x.m3824d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        C5940x.m3823c("[UploadManager] Add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.f8235p != null) {
            if (m3802b()) {
                C5940x.m3823c("[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (z2) {
                    m3778a(runnable, j);
                    return;
                } else {
                    m3781a(runnable, z);
                    m3784c(0);
                    return;
                }
            }
            C5940x.m3818a("[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            m3801b(false);
        }
        synchronized (this.f8239t) {
            if (this.f8238s) {
                m3781a(runnable, z);
                return;
            }
            this.f8238s = true;
            C5940x.m3823c("[UploadManager] Initialize security context now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            if (z2) {
                m3778a(new a(this.f8223d, runnable, j), 0L);
                return;
            }
            m3781a(runnable, z);
            a aVar = new a(this.f8223d);
            C5940x.m3818a("[UploadManager] Create and start a new thread to execute a task of initializing security context: %s", "BUGLY_ASYNC_UPLOAD");
            if (C5942z.m3860a(aVar, "BUGLY_ASYNC_UPLOAD") == null) {
                C5940x.m3824d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
                C5939w m3810a = C5939w.m3810a();
                if (m3810a != null) {
                    m3810a.m3812a(aVar);
                    return;
                }
                C5940x.m3825e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
                synchronized (this.f8239t) {
                    this.f8238s = false;
                }
            }
        }
    }

    /* renamed from: a */
    public final void m3797a(int i, C5913aq c5913aq) {
        if (this.f8221a) {
            boolean z = true;
            if (i == 2) {
                C5940x.m3823c("[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                m3801b(true);
            } else {
                synchronized (this.f8239t) {
                    if (!this.f8238s) {
                        return;
                    }
                    if (c5913aq != null) {
                        C5940x.m3823c("[UploadManager] Record security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        try {
                            Map<String, String> map = c5913aq.f8104g;
                            if (map != null && map.containsKey("S1") && map.containsKey("S2")) {
                                this.f8232m = c5913aq.f8102e - System.currentTimeMillis();
                                C5940x.m3823c("[UploadManager] Time lag of server is: %d", Long.valueOf(this.f8232m));
                                this.f8235p = map.get("S1");
                                C5940x.m3823c("[UploadManager] Session ID from server is: %s", this.f8235p);
                                if (this.f8235p.length() > 0) {
                                    try {
                                        this.f8236q = Long.parseLong(map.get("S2"));
                                        C5940x.m3823c("[UploadManager] Session expired time from server is: %d(%s)", Long.valueOf(this.f8236q), new Date(this.f8236q).toString());
                                        if (this.f8236q < 1000) {
                                            C5940x.m3824d("[UploadManager] Session expired time from server is less than 1 second, will set to default value", new Object[0]);
                                            this.f8236q = 259200000L;
                                        }
                                    } catch (NumberFormatException unused) {
                                        C5940x.m3824d("[UploadManager] Session expired time is invalid, will set to default value", new Object[0]);
                                        this.f8236q = 259200000L;
                                    }
                                    if (m3788d()) {
                                        z = false;
                                    } else {
                                        C5940x.m3823c("[UploadManager] Failed to record database", new Object[0]);
                                    }
                                    m3784c(0);
                                } else {
                                    C5940x.m3823c("[UploadManager] Session ID from server is invalid, try next time", new Object[0]);
                                }
                            }
                        } catch (Throwable th) {
                            C5940x.m3819a(th);
                        }
                        if (z) {
                            m3801b(false);
                        }
                    } else {
                        C5940x.m3823c("[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        m3801b(false);
                    }
                }
            }
            synchronized (this.f8239t) {
                if (this.f8238s) {
                    this.f8238s = false;
                    C5942z.m3884b(this.f8223d, "security_info");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.u$a */
    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: a */
        private final Context f8245a;

        /* renamed from: b */
        private final Runnable f8246b;

        /* renamed from: c */
        private final long f8247c;

        public a(Context context) {
            this.f8245a = context;
            this.f8246b = null;
            this.f8247c = 0L;
        }

        public a(Context context, Runnable runnable, long j) {
            this.f8245a = context;
            this.f8246b = runnable;
            this.f8247c = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (C5942z.m3865a(this.f8245a, "security_info", 30000L)) {
                if (!C5937u.this.m3790e()) {
                    C5940x.m3824d("[UploadManager] Failed to load security info from database", new Object[0]);
                    C5937u.this.m3801b(false);
                }
                if (C5937u.this.f8235p != null) {
                    if (C5937u.this.m3802b()) {
                        C5940x.m3823c("[UploadManager] Sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        Runnable runnable = this.f8246b;
                        if (runnable != null) {
                            C5937u.this.m3778a(runnable, this.f8247c);
                        }
                        C5937u.this.m3784c(0);
                        C5942z.m3884b(this.f8245a, "security_info");
                        synchronized (C5937u.this.f8239t) {
                            C5937u.m3780a(C5937u.this, false);
                        }
                        return;
                    }
                    C5940x.m3818a("[UploadManager] Session ID is expired, drop it.", new Object[0]);
                    C5937u.this.m3801b(true);
                }
                byte[] m3869a = C5942z.m3869a(128);
                if (m3869a != null && (m3869a.length << 3) == 128) {
                    C5937u.this.f8233n = m3869a;
                    C5940x.m3823c("[UploadManager] Execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    Runnable runnable2 = this.f8246b;
                    if (runnable2 != null) {
                        C5937u.this.m3778a(runnable2, this.f8247c);
                        return;
                    } else {
                        C5937u.this.m3784c(1);
                        return;
                    }
                }
                C5940x.m3824d("[UploadManager] Failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                C5937u.this.m3801b(false);
                C5942z.m3884b(this.f8245a, "security_info");
                synchronized (C5937u.this.f8239t) {
                    C5937u.m3780a(C5937u.this, false);
                }
                return;
            }
            C5940x.m3823c("[UploadManager] Sleep %d try to lock security file again (pid=%d | tid=%d)", 5000, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            C5942z.m3881b(5000L);
            if (C5942z.m3860a(this, "BUGLY_ASYNC_UPLOAD") == null) {
                C5940x.m3824d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
                C5939w m3810a = C5939w.m3810a();
                if (m3810a != null) {
                    m3810a.m3812a(this);
                } else {
                    C5940x.m3825e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
                }
            }
        }
    }

    /* renamed from: a */
    public final byte[] m3800a(byte[] bArr) {
        byte[] bArr2 = this.f8233n;
        if (bArr2 != null && (bArr2.length << 3) == 128) {
            return C5942z.m3870a(1, bArr, bArr2);
        }
        C5940x.m3824d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: b */
    public final byte[] m3804b(byte[] bArr) {
        byte[] bArr2 = this.f8233n;
        if (bArr2 != null && (bArr2.length << 3) == 128) {
            return C5942z.m3870a(2, bArr, bArr2);
        }
        C5940x.m3824d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: a */
    public final boolean m3799a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        C5940x.m3823c("[UploadManager] Integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        String str = this.f8235p;
        if (str != null) {
            map.put("secureSessionId", str);
            return true;
        }
        byte[] bArr = this.f8233n;
        if (bArr == null || (bArr.length << 3) != 128) {
            C5940x.m3824d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        }
        if (this.f8231l == null) {
            this.f8231l = Base64.decode(this.f8230k, 0);
            if (this.f8231l == null) {
                C5940x.m3824d("[UploadManager] Failed to decode RSA public key", new Object[0]);
                return false;
            }
        }
        byte[] m3885b = C5942z.m3885b(1, this.f8233n, this.f8231l);
        if (m3885b == null) {
            C5940x.m3824d("[UploadManager] Failed to encrypt AES key", new Object[0]);
            return false;
        }
        String encodeToString = Base64.encodeToString(m3885b, 0);
        if (encodeToString == null) {
            C5940x.m3824d("[UploadManager] Failed to encode AES key", new Object[0]);
            return false;
        }
        map.put("raKey", encodeToString);
        return true;
    }
}
