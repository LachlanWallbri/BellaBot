package com.loc;

import android.content.Context;
import java.util.Iterator;
import java.util.List;

/* compiled from: SDKDBOperation.java */
/* renamed from: com.loc.ap */
/* loaded from: classes4.dex */
public final class C3814ap {

    /* renamed from: a */
    private C3804af f3573a;

    /* renamed from: b */
    private Context f3574b;

    public C3814ap(Context context, boolean z) {
        this.f3574b = context;
        this.f3573a = m2454a(this.f3574b, z);
    }

    /* renamed from: a */
    private static C3804af m2454a(Context context, boolean z) {
        try {
            return new C3804af(context, C3804af.m2421a((Class<? extends InterfaceC3803ae>) C3811am.class));
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
            } else {
                C3897w.m3249a(th, "SDKDB", "getDB");
            }
            return null;
        }
    }

    /* renamed from: a */
    public final List<C3893s> m2455a() {
        try {
            return this.f3573a.m2427a(C3893s.m3205g(), C3893s.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public final void m2456a(C3893s c3893s) {
        if (c3893s == null) {
            return;
        }
        try {
            boolean z = false;
            if (this.f3573a == null) {
                this.f3573a = m2454a(this.f3574b, false);
            }
            String m3202a = C3893s.m3202a(c3893s.m3206a());
            List m2433b = this.f3573a.m2433b(m3202a, C3893s.class);
            if (m2433b != null && m2433b.size() != 0) {
                Iterator it = m2433b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    } else if (((C3893s) it.next()).equals(c3893s)) {
                        break;
                    }
                }
                if (z) {
                    this.f3573a.m2431a(m3202a, c3893s);
                    return;
                }
                return;
            }
            this.f3573a.m2428a((C3804af) c3893s);
        } catch (Throwable th) {
            C3897w.m3249a(th, "SDKDB", "insert");
            th.printStackTrace();
        }
    }
}
