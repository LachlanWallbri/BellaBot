package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.t */
/* loaded from: classes.dex */
public class C3640t implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private static C3640t f2652a;

    /* renamed from: b */
    private Thread.UncaughtExceptionHandler f2653b = Thread.getDefaultUncaughtExceptionHandler();

    /* renamed from: c */
    private Context f2654c;

    /* renamed from: d */
    private C3626m f2655d;

    private C3640t(Context context, C3626m c3626m) {
        this.f2654c = context.getApplicationContext();
        this.f2655d = c3626m;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C3640t m1536a(Context context, C3626m c3626m) {
        C3640t c3640t;
        synchronized (C3640t.class) {
            if (f2652a == null) {
                f2652a = new C3640t(context, c3626m);
            }
            c3640t = f2652a;
        }
        return c3640t;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        String m1407a = C3628n.m1407a(th);
        try {
            if (!TextUtils.isEmpty(m1407a) && m1407a.contains("amapdynamic") && m1407a.contains("com.amap.api")) {
                C3636r.m1506a(this.f2654c, this.f2655d);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f2653b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
