package com.loc;

import android.content.Context;
import java.util.List;

/* compiled from: UpdateLogDBOperation.java */
/* renamed from: com.loc.aq */
/* loaded from: classes4.dex */
public final class C3815aq {

    /* renamed from: a */
    private C3804af f3575a;

    /* renamed from: b */
    private Context f3576b;

    public C3815aq(Context context) {
        this.f3576b = context;
        this.f3575a = m2457a(this.f3576b);
    }

    /* renamed from: a */
    private static C3804af m2457a(Context context) {
        try {
            return new C3804af(context, C3804af.m2421a((Class<? extends InterfaceC3803ae>) C3811am.class));
        } catch (Throwable th) {
            C3897w.m3249a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    /* renamed from: a */
    public final C3816ar m2458a() {
        try {
            if (this.f3575a == null) {
                this.f3575a = m2457a(this.f3576b);
            }
            List m2433b = this.f3575a.m2433b("1=1", C3816ar.class);
            if (m2433b.size() > 0) {
                return (C3816ar) m2433b.get(0);
            }
            return null;
        } catch (Throwable th) {
            C3897w.m3249a(th, "UpdateLogDB", "getUpdateLog");
            return null;
        }
    }

    /* renamed from: a */
    public final void m2459a(C3816ar c3816ar) {
        if (c3816ar == null) {
            return;
        }
        try {
            if (this.f3575a == null) {
                this.f3575a = m2457a(this.f3576b);
            }
            List m2433b = this.f3575a.m2433b("1=1", C3816ar.class);
            if (m2433b != null && m2433b.size() != 0) {
                this.f3575a.m2431a("1=1", c3816ar);
                return;
            }
            this.f3575a.m2428a((C3804af) c3816ar);
        } catch (Throwable th) {
            C3897w.m3249a(th, "UpdateLogDB", "updateLog");
        }
    }
}
