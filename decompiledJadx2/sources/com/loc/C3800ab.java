package com.loc;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* compiled from: CrashLogProcessor.java */
/* renamed from: com.loc.ab */
/* loaded from: classes4.dex */
public final class C3800ab extends AbstractC3802ad {

    /* renamed from: b */
    private static boolean f3552b = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public C3800ab(int i) {
        super(i);
    }

    @Override // com.loc.AbstractC3802ad
    /* renamed from: a */
    protected final String mo2390a(String str) {
        return C3890p.m3189c(str + C3894t.m3223a(new Date().getTime()));
    }

    @Override // com.loc.AbstractC3802ad
    /* renamed from: a */
    protected final String mo2388a(List<C3893s> list) {
        return null;
    }

    @Override // com.loc.AbstractC3802ad
    /* renamed from: a */
    protected final boolean mo2389a(Context context) {
        if (!f3552b) {
            return false;
        }
        f3552b = false;
        synchronized (Looper.getMainLooper()) {
            C3815aq c3815aq = new C3815aq(context);
            C3816ar m2458a = c3815aq.m2458a();
            if (m2458a == null) {
                return true;
            }
            if (!m2458a.m2461a()) {
                return false;
            }
            m2458a.m2460a(false);
            c3815aq.m2459a(m2458a);
            return true;
        }
    }
}
