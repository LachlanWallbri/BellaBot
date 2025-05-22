package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.util.ContactManager;

/* renamed from: com.iflytek.cloud.thirdparty.az */
/* loaded from: classes3.dex */
public class C3714az extends ContactManager {

    /* renamed from: a */
    private static C3714az f3126a = null;

    /* renamed from: b */
    private static Context f3127b = null;

    /* renamed from: c */
    private static int f3128c = 4;

    /* renamed from: d */
    private static AbstractC3721bf f3129d;

    /* renamed from: e */
    private static C3713ay f3130e;

    /* renamed from: f */
    private static a f3131f;

    /* renamed from: h */
    private static ContactManager.ContactListener f3132h;

    /* renamed from: g */
    private HandlerThread f3133g;

    /* renamed from: i */
    private Handler f3134i;

    /* renamed from: j */
    private long f3135j = 0;

    /* renamed from: a */
    public static C3714az m1997a() {
        return f3126a;
    }

    /* renamed from: a */
    public static C3714az m1998a(Context context, ContactManager.ContactListener contactListener) {
        f3132h = contactListener;
        f3127b = context;
        if (f3126a == null) {
            f3126a = new C3714az();
            f3127b.getContentResolver().registerContentObserver(f3129d.mo2036a(), true, f3131f);
        }
        return f3126a;
    }

    private C3714az() {
        this.f3133g = null;
        if (Build.VERSION.SDK_INT > f3128c) {
            f3129d = new C3720be(f3127b);
        } else {
            f3129d = new C3719bd(f3127b);
        }
        f3130e = new C3713ay(f3127b, f3129d);
        this.f3133g = new HandlerThread("ContactManager_worker");
        this.f3133g.start();
        this.f3134i = new Handler(this.f3133g.getLooper());
        this.f3133g.setPriority(1);
        f3131f = new a(this.f3134i);
    }

    @Override // com.iflytek.cloud.util.ContactManager
    public String queryAllContactsName() {
        if (f3130e == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : f3130e.m1995a()) {
            sb.append(str + '\n');
        }
        return sb.toString();
    }

    @Override // com.iflytek.cloud.util.ContactManager
    public void asyncQueryAllContactsName() {
        this.f3134i.post(new Runnable() { // from class: com.iflytek.cloud.thirdparty.az.1
            @Override // java.lang.Runnable
            public void run() {
                C3714az.this.m2002d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m2002d() {
        try {
            if (f3132h != null && f3130e != null) {
                String m2023a = C3717bb.m2023a(f3130e.m1995a(), '\n');
                String str = f3127b.getFilesDir().getParent() + "/name.txt";
                String m2021a = C3716ba.m2021a(str);
                if (m2023a != null && m2021a != null && m2023a.equals(m2021a)) {
                    DebugLog.LogD("iFly_ContactManager", "contact name is not change.");
                    f3132h.onContactQueryFinish(m2023a, false);
                } else {
                    C3716ba.m2020a(str, m2023a, true);
                    f3132h.onContactQueryFinish(m2023a, true);
                }
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
    }

    /* renamed from: b */
    public void m2003b() {
        if (f3131f != null) {
            f3127b.getContentResolver().unregisterContentObserver(f3131f);
            HandlerThread handlerThread = this.f3133g;
            if (handlerThread != null) {
                handlerThread.quit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.az$a */
    /* loaded from: classes3.dex */
    public class a extends ContentObserver {
        public a(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            DebugLog.LogD("iFly_ContactManager", "ContactObserver_Contact| onChange");
            if (System.currentTimeMillis() - C3714az.this.f3135j < 5000) {
                DebugLog.LogD("iFly_ContactManager", "onChange too much");
                return;
            }
            C3714az.this.f3135j = System.currentTimeMillis();
            C3714az.this.m2002d();
        }
    }

    /* renamed from: c */
    public static void m2001c() {
        C3714az c3714az = f3126a;
        if (c3714az != null) {
            c3714az.m2003b();
            f3126a = null;
        }
    }
}
