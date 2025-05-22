package com.iflytek.aiui.pro;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ab */
/* loaded from: classes.dex */
public class C3572ab {

    /* renamed from: a */
    private Context f2266a;

    /* renamed from: b */
    private C3626m f2267b;

    /* renamed from: c */
    private String f2268c;

    public C3572ab(Context context, C3626m c3626m, String str) {
        this.f2266a = context.getApplicationContext();
        this.f2267b = c3626m;
        this.f2268c = str;
    }

    /* renamed from: a */
    private static String m939a(Context context, C3626m c3626m, String str) {
        return C3622k.m1390b(context, C3628n.m1422c(C3628n.m1415a(m940b(context, c3626m, str))));
    }

    /* renamed from: b */
    private static String m940b(Context context, C3626m c3626m, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(c3626m.f2580b);
            sb.append("\",\"product\":\"");
            sb.append(c3626m.f2579a);
            sb.append("\",\"nt\":\"");
            sb.append(C3618i.m1365i(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public byte[] m941a() {
        return C3628n.m1415a(m939a(this.f2266a, this.f2267b, this.f2268c));
    }
}
