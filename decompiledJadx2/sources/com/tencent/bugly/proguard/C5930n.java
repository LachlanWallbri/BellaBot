package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.C5873a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.n */
/* loaded from: classes7.dex */
public final class C5930n {

    /* renamed from: a */
    public static final long f8174a = System.currentTimeMillis();

    /* renamed from: b */
    private static C5930n f8175b;

    /* renamed from: c */
    private Context f8176c;

    /* renamed from: f */
    private SharedPreferences f8179f;

    /* renamed from: e */
    private Map<Integer, Map<String, C5929m>> f8178e = new HashMap();

    /* renamed from: d */
    private String f8177d = C5873a.m3390b().f7756d;

    private C5930n(Context context) {
        this.f8176c = context;
        this.f8179f = context.getSharedPreferences("crashrecord", 0);
    }

    /* renamed from: a */
    public static synchronized C5930n m3719a(Context context) {
        C5930n c5930n;
        synchronized (C5930n.class) {
            if (f8175b == null) {
                f8175b = new C5930n(context);
            }
            c5930n = f8175b;
        }
        return c5930n;
    }

    /* renamed from: a */
    public static synchronized C5930n m3718a() {
        C5930n c5930n;
        synchronized (C5930n.class) {
            c5930n = f8175b;
        }
        return c5930n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public synchronized boolean m3725b(int i) {
        try {
            List<C5929m> m3728c = m3728c(i);
            if (m3728c == null) {
                return false;
            }
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (C5929m c5929m : m3728c) {
                if (c5929m.f8168b != null && c5929m.f8168b.equalsIgnoreCase(this.f8177d) && c5929m.f8170d > 0) {
                    arrayList.add(c5929m);
                }
                if (c5929m.f8169c + 86400000 < currentTimeMillis) {
                    arrayList2.add(c5929m);
                }
            }
            Collections.sort(arrayList);
            if (arrayList.size() >= 2) {
                if (arrayList.size() <= 0 || ((C5929m) arrayList.get(arrayList.size() - 1)).f8169c + 86400000 >= currentTimeMillis) {
                    return true;
                }
                m3728c.clear();
                m3722a(i, (int) m3728c);
                return false;
            }
            m3728c.removeAll(arrayList2);
            m3722a(i, (int) m3728c);
            return false;
        } catch (Exception unused) {
            C5940x.m3825e("isFrequentCrash failed", new Object[0]);
            return false;
        }
    }

    /* renamed from: a */
    public final void m3729a(int i, final int i2) {
        final int i3 = 1004;
        C5939w.m3810a().m3812a(new Runnable() { // from class: com.tencent.bugly.proguard.n.1
            @Override // java.lang.Runnable
            public final void run() {
                C5929m c5929m;
                try {
                    if (TextUtils.isEmpty(C5930n.this.f8177d)) {
                        return;
                    }
                    List<C5929m> m3728c = C5930n.this.m3728c(i3);
                    if (m3728c == null) {
                        m3728c = new ArrayList();
                    }
                    if (C5930n.this.f8178e.get(Integer.valueOf(i3)) == null) {
                        C5930n.this.f8178e.put(Integer.valueOf(i3), new HashMap());
                    }
                    if (((Map) C5930n.this.f8178e.get(Integer.valueOf(i3))).get(C5930n.this.f8177d) != null) {
                        c5929m = (C5929m) ((Map) C5930n.this.f8178e.get(Integer.valueOf(i3))).get(C5930n.this.f8177d);
                        c5929m.f8170d = i2;
                    } else {
                        c5929m = new C5929m();
                        c5929m.f8167a = i3;
                        c5929m.f8173g = C5930n.f8174a;
                        c5929m.f8168b = C5930n.this.f8177d;
                        c5929m.f8172f = C5873a.m3390b().f7762j;
                        C5873a.m3390b().getClass();
                        c5929m.f8171e = "3.0.0";
                        c5929m.f8169c = System.currentTimeMillis();
                        c5929m.f8170d = i2;
                        ((Map) C5930n.this.f8178e.get(Integer.valueOf(i3))).put(C5930n.this.f8177d, c5929m);
                    }
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    for (C5929m c5929m2 : m3728c) {
                        if (c5929m2.f8173g == c5929m.f8173g && c5929m2.f8168b != null && c5929m2.f8168b.equalsIgnoreCase(c5929m.f8168b)) {
                            z = true;
                            c5929m2.f8170d = c5929m.f8170d;
                        }
                        if ((c5929m2.f8171e != null && !c5929m2.f8171e.equalsIgnoreCase(c5929m.f8171e)) || ((c5929m2.f8172f != null && !c5929m2.f8172f.equalsIgnoreCase(c5929m.f8172f)) || c5929m2.f8170d <= 0)) {
                            arrayList.add(c5929m2);
                        }
                    }
                    m3728c.removeAll(arrayList);
                    if (!z) {
                        m3728c.add(c5929m);
                    }
                    C5930n.this.m3722a(i3, (int) m3728c);
                } catch (Exception unused) {
                    C5940x.m3825e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0052, code lost:
    
        if (r6 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0046, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0044, code lost:
    
        if (r6 == null) goto L31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized <T extends List<?>> T m3728c(int i) {
        ObjectInputStream objectInputStream;
        try {
            File dir = this.f8176c.getDir("crashrecord", 0);
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            File file = new File(dir, sb.toString());
            ?? exists = file.exists();
            try {
                if (exists == 0) {
                    return null;
                }
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException unused) {
                    objectInputStream = null;
                } catch (ClassNotFoundException unused2) {
                    objectInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    exists = 0;
                    if (exists != 0) {
                        exists.close();
                    }
                    throw th;
                }
                try {
                    T t = (T) objectInputStream.readObject();
                    objectInputStream.close();
                    return t;
                } catch (IOException unused3) {
                    C5940x.m3818a("open record file error", new Object[0]);
                } catch (ClassNotFoundException unused4) {
                    C5940x.m3818a("get object error", new Object[0]);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused5) {
            C5940x.m3825e("readCrashRecord error", new Object[0]);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized <T extends List<?>> void m3722a(int i, T t) {
        File dir;
        StringBuilder sb;
        ObjectOutputStream objectOutputStream;
        if (t == null) {
            return;
        }
        try {
            dir = this.f8176c.getDir("crashrecord", 0);
            sb = new StringBuilder();
            sb.append(i);
            objectOutputStream = null;
        } catch (Exception unused) {
            C5940x.m3825e("writeCrashRecord error", new Object[0]);
        }
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(new File(dir, sb.toString())));
                try {
                    objectOutputStream2.writeObject(t);
                    objectOutputStream2.close();
                } catch (IOException e) {
                    e = e;
                    objectOutputStream = objectOutputStream2;
                    e.printStackTrace();
                    C5940x.m3818a("open record file error", new Object[0]);
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public final synchronized boolean m3730a(final int i) {
        boolean z;
        z = true;
        try {
            z = this.f8179f.getBoolean(i + "_" + this.f8177d, true);
            C5939w.m3810a().m3812a(new Runnable() { // from class: com.tencent.bugly.proguard.n.2
                @Override // java.lang.Runnable
                public final void run() {
                    boolean m3725b = C5930n.this.m3725b(i);
                    C5930n.this.f8179f.edit().putBoolean(i + "_" + C5930n.this.f8177d, !m3725b).commit();
                }
            });
        } catch (Exception unused) {
            C5940x.m3825e("canInit error", new Object[0]);
            return z;
        }
        return z;
    }
}
