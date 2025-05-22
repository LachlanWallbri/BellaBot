package com.iflytek.aiui.pro;

import android.content.Context;

/* renamed from: com.iflytek.aiui.pro.b0 */
/* loaded from: classes4.dex */
public class C3598b0 {

    /* renamed from: a */
    private Context f2431a;

    /* renamed from: b */
    private C3626m f2432b;

    /* renamed from: c */
    private String f2433c;

    public C3598b0(Context context, C3626m c3626m, String str) {
        this.f2431a = context.getApplicationContext();
        this.f2432b = c3626m;
        this.f2433c = str;
    }

    /* renamed from: a */
    private static String m1117a(Context context, C3626m c3626m, String str) {
        return C3622k.f(context, C3628n.q(C3628n.j(m1118c(context, c3626m, str))));
    }

    /* renamed from: c */
    private static String m1118c(Context context, C3626m c3626m, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(c3626m.f2580b);
            sb.append("\",\"product\":\"");
            sb.append(c3626m.f2579a);
            sb.append("\",\"nt\":\"");
            sb.append(C3618i.m1370n(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public byte[] m1119b() {
        return C3628n.j(m1117a(this.f2431a, this.f2432b, this.f2433c));
    }
}
