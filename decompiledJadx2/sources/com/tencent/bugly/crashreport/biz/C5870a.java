package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.AbstractC5927k;
import com.tencent.bugly.proguard.C5896a;
import com.tencent.bugly.proguard.C5912ap;
import com.tencent.bugly.proguard.C5917au;
import com.tencent.bugly.proguard.C5932p;
import com.tencent.bugly.proguard.C5937u;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import com.tencent.bugly.proguard.InterfaceC5931o;
import com.tencent.bugly.proguard.InterfaceC5936t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.biz.a */
/* loaded from: classes7.dex */
public final class C5870a {

    /* renamed from: a */
    private Context f7669a;

    /* renamed from: b */
    private long f7670b;

    /* renamed from: c */
    private int f7671c;

    /* renamed from: d */
    private boolean f7672d;

    /* renamed from: a */
    static /* synthetic */ void m3345a(C5870a c5870a, UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> m3349a;
        if (userInfoBean != null) {
            if (!z && userInfoBean.f7651b != 1 && (m3349a = c5870a.m3349a(C5873a.m3389a(c5870a.f7669a).f7756d)) != null && m3349a.size() >= 20) {
                C5940x.m3818a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(m3349a.size()));
                return;
            }
            long m3755a = C5932p.m3740a().m3755a("t_ui", m3342a(userInfoBean), (InterfaceC5931o) null, true);
            if (m3755a >= 0) {
                C5940x.m3823c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(m3755a));
                userInfoBean.f7650a = m3755a;
            }
        }
    }

    public C5870a(Context context, boolean z) {
        this.f7672d = true;
        this.f7669a = context;
        this.f7672d = z;
    }

    /* renamed from: a */
    public final void m3351a(int i, boolean z, long j) {
        C5876a m3487a = C5876a.m3487a();
        if (m3487a != null && !m3487a.m3497c().f7791h && i != 1 && i != 3) {
            C5940x.m3825e("UserInfo is disable", new Object[0]);
            return;
        }
        if (i == 1 || i == 3) {
            this.f7671c++;
        }
        C5873a m3389a = C5873a.m3389a(this.f7669a);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.f7651b = i;
        userInfoBean.f7652c = m3389a.f7756d;
        userInfoBean.f7653d = m3389a.m3428g();
        userInfoBean.f7654e = System.currentTimeMillis();
        userInfoBean.f7655f = -1L;
        userInfoBean.f7663n = m3389a.f7762j;
        userInfoBean.f7664o = i == 1 ? 1 : 0;
        userInfoBean.f7661l = m3389a.m3415a();
        userInfoBean.f7662m = m3389a.f7768p;
        userInfoBean.f7656g = m3389a.f7769q;
        userInfoBean.f7657h = m3389a.f7770r;
        userInfoBean.f7658i = m3389a.f7771s;
        userInfoBean.f7660k = m3389a.f7772t;
        userInfoBean.f7667r = m3389a.m3393B();
        userInfoBean.f7668s = m3389a.m3398G();
        userInfoBean.f7665p = m3389a.m3399H();
        userInfoBean.f7666q = m3389a.m3400I();
        C5939w.m3810a().m3813a(new a(userInfoBean, z), 0L);
    }

    /* renamed from: a */
    public final void m3350a() {
        this.f7670b = C5942z.m3876b() + 86400000;
        C5939w.m3810a().m3813a(new b(), (this.f7670b - System.currentTimeMillis()) + 5000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$a */
    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: a */
        private boolean f7676a;

        /* renamed from: b */
        private UserInfoBean f7677b;

        public a(UserInfoBean userInfoBean, boolean z) {
            this.f7677b = userInfoBean;
            this.f7676a = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C5873a m3390b;
            try {
                if (this.f7677b != null) {
                    UserInfoBean userInfoBean = this.f7677b;
                    if (userInfoBean != null && (m3390b = C5873a.m3390b()) != null) {
                        userInfoBean.f7659j = m3390b.m3424e();
                    }
                    C5940x.m3823c("[UserInfo] Record user info.", new Object[0]);
                    C5870a.m3345a(C5870a.this, this.f7677b, false);
                }
                if (this.f7676a) {
                    C5870a c5870a = C5870a.this;
                    C5939w m3810a = C5939w.m3810a();
                    if (m3810a != null) {
                        m3810a.m3812a(new AnonymousClass2());
                    }
                }
            } catch (Throwable th) {
                if (C5940x.m3819a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00f2 A[Catch: all -> 0x018b, TryCatch #0 {, blocks: (B:3:0x0001, B:8:0x0007, B:12:0x000f, B:16:0x0017, B:18:0x001d, B:22:0x0027, B:24:0x003c, B:27:0x0045, B:29:0x004c, B:30:0x004f, B:32:0x0055, B:34:0x0069, B:36:0x0079, B:43:0x0081, B:45:0x008b, B:46:0x0090, B:48:0x0096, B:50:0x00a4, B:52:0x00b1, B:53:0x00b4, B:56:0x00c2, B:58:0x00c6, B:60:0x00cb, B:63:0x00d0, B:73:0x00d7, B:74:0x00ec, B:76:0x00f2, B:78:0x00f7, B:81:0x00ff, B:84:0x0117, B:86:0x011d, B:89:0x0126, B:91:0x012c, B:94:0x0135, B:97:0x013e, B:99:0x0146, B:102:0x014f, B:104:0x0160, B:105:0x0165, B:107:0x016a, B:108:0x016f, B:111:0x017d, B:115:0x016d, B:116:0x0163, B:119:0x0182, B:123:0x00e6), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x011d A[Catch: all -> 0x018b, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:8:0x0007, B:12:0x000f, B:16:0x0017, B:18:0x001d, B:22:0x0027, B:24:0x003c, B:27:0x0045, B:29:0x004c, B:30:0x004f, B:32:0x0055, B:34:0x0069, B:36:0x0079, B:43:0x0081, B:45:0x008b, B:46:0x0090, B:48:0x0096, B:50:0x00a4, B:52:0x00b1, B:53:0x00b4, B:56:0x00c2, B:58:0x00c6, B:60:0x00cb, B:63:0x00d0, B:73:0x00d7, B:74:0x00ec, B:76:0x00f2, B:78:0x00f7, B:81:0x00ff, B:84:0x0117, B:86:0x011d, B:89:0x0126, B:91:0x012c, B:94:0x0135, B:97:0x013e, B:99:0x0146, B:102:0x014f, B:104:0x0160, B:105:0x0165, B:107:0x016a, B:108:0x016f, B:111:0x017d, B:115:0x016d, B:116:0x0163, B:119:0x0182, B:123:0x00e6), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0126 A[Catch: all -> 0x018b, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:8:0x0007, B:12:0x000f, B:16:0x0017, B:18:0x001d, B:22:0x0027, B:24:0x003c, B:27:0x0045, B:29:0x004c, B:30:0x004f, B:32:0x0055, B:34:0x0069, B:36:0x0079, B:43:0x0081, B:45:0x008b, B:46:0x0090, B:48:0x0096, B:50:0x00a4, B:52:0x00b1, B:53:0x00b4, B:56:0x00c2, B:58:0x00c6, B:60:0x00cb, B:63:0x00d0, B:73:0x00d7, B:74:0x00ec, B:76:0x00f2, B:78:0x00f7, B:81:0x00ff, B:84:0x0117, B:86:0x011d, B:89:0x0126, B:91:0x012c, B:94:0x0135, B:97:0x013e, B:99:0x0146, B:102:0x014f, B:104:0x0160, B:105:0x0165, B:107:0x016a, B:108:0x016f, B:111:0x017d, B:115:0x016d, B:116:0x0163, B:119:0x0182, B:123:0x00e6), top: B:2:0x0001 }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void m3348c() {
        boolean z;
        C5917au m3614a;
        if (this.f7672d) {
            C5937u m3773a = C5937u.m3773a();
            if (m3773a == null) {
                return;
            }
            C5876a m3487a = C5876a.m3487a();
            if (m3487a == null) {
                return;
            }
            if (!m3487a.m3496b() || m3773a.m3803b(1001)) {
                String str = C5873a.m3389a(this.f7669a).f7756d;
                ArrayList arrayList = new ArrayList();
                final List<UserInfoBean> m3349a = m3349a(str);
                if (m3349a != null) {
                    int size = m3349a.size() - 20;
                    if (size > 0) {
                        int i = 0;
                        while (i < m3349a.size() - 1) {
                            int i2 = i + 1;
                            for (int i3 = i2; i3 < m3349a.size(); i3++) {
                                if (m3349a.get(i).f7654e > m3349a.get(i3).f7654e) {
                                    UserInfoBean userInfoBean = m3349a.get(i);
                                    m3349a.set(i, m3349a.get(i3));
                                    m3349a.set(i3, userInfoBean);
                                }
                            }
                            i = i2;
                        }
                        for (int i4 = 0; i4 < size; i4++) {
                            arrayList.add(m3349a.get(i4));
                        }
                    }
                    Iterator<UserInfoBean> it = m3349a.iterator();
                    int i5 = 0;
                    while (it.hasNext()) {
                        UserInfoBean next = it.next();
                        if (next.f7655f != -1) {
                            it.remove();
                            if (next.f7654e < C5942z.m3876b()) {
                                arrayList.add(next);
                            }
                        }
                        if (next.f7654e > System.currentTimeMillis() - 600000 && (next.f7651b == 1 || next.f7651b == 4 || next.f7651b == 3)) {
                            i5++;
                        }
                    }
                    if (i5 > 15) {
                        C5940x.m3824d("[UserInfo] Upload user info too many times in 10 min: %d", Integer.valueOf(i5));
                        z = false;
                        if (arrayList.size() > 0) {
                            m3346a(arrayList);
                        }
                        if (z && m3349a.size() != 0) {
                            C5940x.m3823c("[UserInfo] Upload user info(size: %d)", Integer.valueOf(m3349a.size()));
                            m3614a = C5896a.m3614a(m3349a, this.f7671c != 1 ? 1 : 2);
                            if (m3614a != null) {
                                C5940x.m3824d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
                                return;
                            }
                            byte[] m3620a = C5896a.m3620a((AbstractC5927k) m3614a);
                            if (m3620a == null) {
                                C5940x.m3824d("[UserInfo] Failed to encode data.", new Object[0]);
                                return;
                            }
                            C5912ap m3611a = C5896a.m3611a(this.f7669a, m3773a.f8221a ? 840 : DimensionsKt.XXXHDPI, m3620a);
                            if (m3611a == null) {
                                C5940x.m3824d("[UserInfo] Request package is null.", new Object[0]);
                                return;
                            }
                            InterfaceC5936t interfaceC5936t = new InterfaceC5936t() { // from class: com.tencent.bugly.crashreport.biz.a.1
                                @Override // com.tencent.bugly.proguard.InterfaceC5936t
                                /* renamed from: a */
                                public final void mo3353a(boolean z2) {
                                    if (z2) {
                                        C5940x.m3823c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                                        long currentTimeMillis = System.currentTimeMillis();
                                        for (UserInfoBean userInfoBean2 : m3349a) {
                                            userInfoBean2.f7655f = currentTimeMillis;
                                            C5870a.m3345a(C5870a.this, userInfoBean2, true);
                                        }
                                    }
                                }
                            };
                            StrategyBean m3497c = C5876a.m3487a().m3497c();
                            C5937u.m3773a().m3796a(1001, m3611a, m3773a.f8221a ? m3497c.f7801r : m3497c.f7803t, m3773a.f8221a ? StrategyBean.f7785b : StrategyBean.f7784a, interfaceC5936t, this.f7671c == 1);
                            return;
                        }
                        C5940x.m3823c("[UserInfo] There is no user info in local database.", new Object[0]);
                    }
                } else {
                    m3349a = new ArrayList<>();
                }
                z = true;
                if (arrayList.size() > 0) {
                }
                if (z) {
                    C5940x.m3823c("[UserInfo] Upload user info(size: %d)", Integer.valueOf(m3349a.size()));
                    m3614a = C5896a.m3614a(m3349a, this.f7671c != 1 ? 1 : 2);
                    if (m3614a != null) {
                    }
                }
                C5940x.m3823c("[UserInfo] There is no user info in local database.", new Object[0]);
            }
        }
    }

    /* renamed from: b */
    public final void m3352b() {
        C5939w m3810a = C5939w.m3810a();
        if (m3810a != null) {
            m3810a.m3812a(new AnonymousClass2());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public final class AnonymousClass2 implements Runnable {
        /* JADX INFO: Access modifiers changed from: package-private */
        public AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                C5870a.this.m3348c();
            } catch (Throwable th) {
                C5940x.m3819a(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$b */
    /* loaded from: classes7.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < C5870a.this.f7670b) {
                C5939w.m3810a().m3813a(new b(), (C5870a.this.f7670b - currentTimeMillis) + 5000);
            } else {
                C5870a.this.m3351a(3, false, 0L);
                C5870a.this.m3350a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.biz.a$c */
    /* loaded from: classes7.dex */
    public class c implements Runnable {

        /* renamed from: a */
        private long f7680a;

        public c(long j) {
            this.f7680a = 21600000L;
            this.f7680a = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C5870a c5870a = C5870a.this;
            C5939w m3810a = C5939w.m3810a();
            if (m3810a != null) {
                m3810a.m3812a(new AnonymousClass2());
            }
            C5870a c5870a2 = C5870a.this;
            long j = this.f7680a;
            C5939w.m3810a().m3813a(new c(j), j);
        }
    }

    /* renamed from: a */
    public final List<UserInfoBean> m3349a(String str) {
        Cursor cursor;
        String str2;
        try {
            if (C5942z.m3868a(str)) {
                str2 = null;
            } else {
                str2 = "_pc = '" + str + "'";
            }
            cursor = C5932p.m3740a().m3756a("t_ui", null, str2, null, null, true);
            if (cursor == null) {
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                while (cursor.moveToNext()) {
                    UserInfoBean m3343a = m3343a(cursor);
                    if (m3343a != null) {
                        arrayList.add(m3343a);
                    } else {
                        try {
                            long j = cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID));
                            sb.append(" or _id");
                            sb.append(" = ");
                            sb.append(j);
                        } catch (Throwable unused) {
                            C5940x.m3824d("[Database] unknown id.", new Object[0]);
                        }
                    }
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    C5940x.m3824d("[Database] deleted %s error data %d", "t_ui", Integer.valueOf(C5932p.m3740a().m3754a("t_ui", sb2.substring(4), (String[]) null, (InterfaceC5931o) null, true)));
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!C5940x.m3819a(th)) {
                        th.printStackTrace();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    /* renamed from: a */
    private static void m3346a(List<UserInfoBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() && i < 50; i++) {
            UserInfoBean userInfoBean = list.get(i);
            sb.append(" or _id");
            sb.append(" = ");
            sb.append(userInfoBean.f7650a);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            sb2 = sb2.substring(4);
        }
        String str = sb2;
        sb.setLength(0);
        try {
            C5940x.m3823c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(C5932p.m3740a().m3754a("t_ui", str, (String[]) null, (InterfaceC5931o) null, true)));
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private static ContentValues m3342a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f7650a > 0) {
                contentValues.put(TransferTable.COLUMN_ID, Long.valueOf(userInfoBean.f7650a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f7654e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f7655f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f7651b));
            contentValues.put("_pc", userInfoBean.f7652c);
            contentValues.put("_dt", C5942z.m3871a(userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static UserInfoBean m3343a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID));
            UserInfoBean userInfoBean = (UserInfoBean) C5942z.m3851a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.f7650a = j;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
