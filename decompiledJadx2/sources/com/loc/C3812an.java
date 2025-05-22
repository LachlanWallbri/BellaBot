package com.loc;

import android.content.Context;
import java.util.List;

/* compiled from: LogDBOperation.java */
/* renamed from: com.loc.an */
/* loaded from: classes4.dex */
public final class C3812an {

    /* renamed from: a */
    private C3804af f3568a;

    public C3812an(Context context) {
        try {
            this.f3568a = new C3804af(context, C3804af.m2421a((Class<? extends InterfaceC3803ae>) C3811am.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m2439c(String str, Class<? extends AbstractC3813ao> cls) {
        this.f3568a.m2430a(AbstractC3813ao.m2446c(str), (Class) cls);
    }

    /* renamed from: a */
    public final List<? extends AbstractC3813ao> m2440a(int i, Class<? extends AbstractC3813ao> cls) {
        try {
            return this.f3568a.m2433b(AbstractC3813ao.m2445c(i), cls);
        } catch (Throwable th) {
            C3897w.m3249a(th, "LogDB", "ByState");
            return null;
        }
    }

    /* renamed from: a */
    public final void m2441a(AbstractC3813ao abstractC3813ao) {
        if (abstractC3813ao == null) {
            return;
        }
        String m2446c = AbstractC3813ao.m2446c(abstractC3813ao.m2450b());
        List m2427a = this.f3568a.m2427a(m2446c, (Class) abstractC3813ao.getClass(), true);
        if (m2427a == null || m2427a.size() == 0) {
            this.f3568a.m2434b((C3804af) abstractC3813ao);
            return;
        }
        AbstractC3813ao abstractC3813ao2 = (AbstractC3813ao) m2427a.get(0);
        if (abstractC3813ao.m2447a() == 0) {
            abstractC3813ao2.m2451b(abstractC3813ao2.m2453c() + 1);
        } else {
            abstractC3813ao2.m2451b(0);
        }
        this.f3568a.m2432a(m2446c, (Object) abstractC3813ao2, true);
    }

    /* renamed from: a */
    public final void m2442a(String str, Class<? extends AbstractC3813ao> cls) {
        try {
            m2439c(str, cls);
        } catch (Throwable th) {
            C3897w.m3249a(th, "LogDB", "delLog");
        }
    }

    /* renamed from: b */
    public final void m2443b(AbstractC3813ao abstractC3813ao) {
        try {
            this.f3568a.m2431a(AbstractC3813ao.m2446c(abstractC3813ao.m2450b()), abstractC3813ao);
        } catch (Throwable th) {
            C3897w.m3249a(th, "LogDB", "updateLogInfo");
        }
    }

    /* renamed from: b */
    public final void m2444b(String str, Class<? extends AbstractC3813ao> cls) {
        try {
            m2439c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
