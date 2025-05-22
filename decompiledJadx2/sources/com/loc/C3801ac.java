package com.loc;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* compiled from: ExceptionLogProcessor.java */
/* renamed from: com.loc.ac */
/* loaded from: classes4.dex */
public final class C3801ac extends AbstractC3802ad {

    /* renamed from: b */
    private static boolean f3553b = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public C3801ac(int i) {
        super(i);
    }

    @Override // com.loc.AbstractC3802ad
    /* renamed from: a */
    protected final String mo2388a(List<C3893s> list) {
        return null;
    }

    @Override // com.loc.AbstractC3802ad
    /* renamed from: a */
    protected final boolean mo2389a(Context context) {
        if (C3888n.m3164m(context) != 1 || !f3553b) {
            return false;
        }
        f3553b = false;
        synchronized (Looper.getMainLooper()) {
            C3815aq c3815aq = new C3815aq(context);
            C3816ar m2458a = c3815aq.m2458a();
            if (m2458a == null) {
                return true;
            }
            if (!m2458a.m2463b()) {
                return false;
            }
            m2458a.m2462b(false);
            c3815aq.m2459a(m2458a);
            return true;
        }
    }
}
