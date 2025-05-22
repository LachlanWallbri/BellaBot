package com.loc;

import android.content.Context;

/* compiled from: OfflineLocEntity.java */
/* renamed from: com.loc.bp */
/* loaded from: classes4.dex */
public final class C3841bp {

    /* renamed from: a */
    private Context f3702a;

    /* renamed from: b */
    private C3893s f3703b;

    /* renamed from: c */
    private String f3704c;

    public C3841bp(Context context, C3893s c3893s, String str) {
        this.f3702a = context.getApplicationContext();
        this.f3703b = c3893s;
        this.f3704c = str;
    }

    /* renamed from: a */
    private static String m2629a(Context context, C3893s c3893s, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(c3893s.m3209c());
            sb.append("\",\"product\":\"");
            sb.append(c3893s.m3206a());
            sb.append("\",\"nt\":\"");
            sb.append(C3888n.m3153c(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final byte[] m2630a() {
        return C3894t.m3232a(C3887m.m3142b(C3894t.m3236b(C3894t.m3232a(m2629a(this.f3702a, this.f3703b, this.f3704c)))));
    }
}
