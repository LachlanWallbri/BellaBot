package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.apis.utils.core.crash.d;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log.java */
/* renamed from: com.loc.x */
/* loaded from: classes4.dex */
public final class C3898x {

    /* renamed from: a */
    public static final String f4332a = "/a/";

    /* renamed from: b */
    static final String f4333b = "b";

    /* renamed from: c */
    static final String f4334c = "c";

    /* renamed from: d */
    static final String f4335d = "d";

    /* renamed from: e */
    public static final String f4336e = "g";

    /* renamed from: f */
    public static final String f4337f = "h";

    /* renamed from: g */
    public static final String f4338g = "e";

    /* renamed from: h */
    public static final String f4339h = "f";

    /* renamed from: a */
    public static Class<? extends AbstractC3813ao> m3253a(int i) {
        if (i == 0) {
            return C3808aj.class;
        }
        if (i == 1) {
            return C3810al.class;
        }
        if (i != 2) {
            return null;
        }
        return C3807ai.class;
    }

    /* renamed from: a */
    public static String m3254a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + f4332a + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3255a(Context context) {
        try {
            AbstractC3802ad m3261d = m3261d(2);
            if (m3261d == null) {
                return;
            }
            m3261d.m2412b(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3256a(final Context context, final C3893s c3893s, final String str, final String str2) {
        ExecutorService m3265b;
        try {
            if (c3893s.m3211e() && (m3265b = C3900z.m3265b()) != null && !m3265b.isShutdown()) {
                m3265b.submit(new Runnable() { // from class: com.loc.x.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            Context context2 = context;
                            AbstractC3802ad m3261d = C3898x.m3261d(1);
                            if (TextUtils.isEmpty(str2)) {
                                m3261d.m2410a(c3893s, context, new Throwable("gpsstatistics"), str, (String) null, (String) null);
                            } else {
                                m3261d.m2409a(c3893s, context, str2, str, (String) null, (String) null);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException unused) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m3257a(final Context context, final Throwable th, final int i, final String str, final String str2) {
        try {
            ExecutorService m3265b = C3900z.m3265b();
            if (m3265b != null && !m3265b.isShutdown()) {
                m3265b.submit(new Runnable() { // from class: com.loc.x.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            Context context2 = context;
                            AbstractC3802ad m3261d = C3898x.m3261d(i);
                            if (m3261d == null) {
                                return;
                            }
                            m3261d.m2407a(context, th, str, str2);
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException unused) {
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* renamed from: b */
    public static AbstractC3813ao m3258b(int i) {
        if (i == 0) {
            return new C3808aj();
        }
        if (i == 1) {
            return new C3810al();
        }
        if (i != 2) {
            return null;
        }
        return new C3807ai();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m3259b(final Context context) {
        try {
            ExecutorService m3265b = C3900z.m3265b();
            if (m3265b != null && !m3265b.isShutdown()) {
                m3265b.submit(new Runnable() { // from class: com.loc.x.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        AbstractC3802ad abstractC3802ad;
                        AbstractC3802ad abstractC3802ad2;
                        AbstractC3802ad abstractC3802ad3;
                        AbstractC3802ad abstractC3802ad4 = null;
                        try {
                            Context context2 = context;
                            abstractC3802ad = C3898x.m3261d(0);
                            try {
                                Context context3 = context;
                                abstractC3802ad2 = C3898x.m3261d(1);
                            } catch (RejectedExecutionException unused) {
                                abstractC3802ad3 = null;
                            } catch (Throwable th) {
                                th = th;
                                abstractC3802ad2 = null;
                            }
                            try {
                                Context context4 = context;
                                abstractC3802ad4 = C3898x.m3261d(2);
                                abstractC3802ad.m2414c(context);
                                abstractC3802ad2.m2414c(context);
                                abstractC3802ad4.m2414c(context);
                                C3844bs.m2640a(context);
                                C3842bq.m2633a(context);
                                List<d.b> m2394a = AbstractC3802ad.m2394a();
                                if (m2394a.size() > 0) {
                                    Iterator<d.b> it = m2394a.iterator();
                                    while (it.hasNext()) {
                                        it.next();
                                        try {
                                            Context context5 = context;
                                        } catch (Throwable unused2) {
                                        }
                                    }
                                }
                            } catch (RejectedExecutionException unused3) {
                                abstractC3802ad3 = abstractC3802ad4;
                                abstractC3802ad4 = abstractC3802ad2;
                                if (abstractC3802ad != null) {
                                    abstractC3802ad.m2413c();
                                }
                                if (abstractC3802ad4 != null) {
                                    abstractC3802ad4.m2413c();
                                }
                                if (abstractC3802ad3 != null) {
                                    abstractC3802ad3.m2413c();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    C3897w.m3249a(th, "Log", "processLog");
                                    if (abstractC3802ad != null) {
                                        abstractC3802ad.m2413c();
                                    }
                                    if (abstractC3802ad2 != null) {
                                        abstractC3802ad2.m2413c();
                                    }
                                    if (abstractC3802ad4 != null) {
                                        abstractC3802ad4.m2413c();
                                    }
                                } finally {
                                    if (abstractC3802ad != null) {
                                        abstractC3802ad.m2413c();
                                    }
                                    if (abstractC3802ad2 != null) {
                                        abstractC3802ad2.m2413c();
                                    }
                                    if (abstractC3802ad4 != null) {
                                        abstractC3802ad4.m2413c();
                                    }
                                }
                            }
                        } catch (RejectedExecutionException unused4) {
                            abstractC3802ad = null;
                            abstractC3802ad3 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            abstractC3802ad = null;
                            abstractC3802ad2 = null;
                        }
                    }
                });
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "Log", "processLog");
        }
    }

    /* renamed from: c */
    public static String m3260c(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : f4335d : f4333b : f4334c;
    }

    /* renamed from: d */
    static AbstractC3802ad m3261d(int i) {
        if (i == 0) {
            return new C3800ab(i);
        }
        if (i == 1) {
            return new C3801ac(i);
        }
        if (i != 2) {
            return null;
        }
        return new C3799aa(i);
    }
}
