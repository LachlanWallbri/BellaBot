package com.loc;

import android.content.Context;
import java.lang.Thread;

/* compiled from: BasicLogHandler.java */
/* renamed from: com.loc.w */
/* loaded from: classes4.dex */
public class C3897w {

    /* renamed from: a */
    protected static C3897w f4329a;

    /* renamed from: b */
    protected Thread.UncaughtExceptionHandler f4330b;

    /* renamed from: c */
    protected boolean f4331c = true;

    /* renamed from: a */
    public static void m3249a(Throwable th, String str, String str2) {
        th.printStackTrace();
        C3897w c3897w = f4329a;
        if (c3897w != null) {
            c3897w.mo3252a(th, 1, str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3250a(Context context, C3893s c3893s, boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3251a(C3893s c3893s, String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3252a(Throwable th, int i, String str, String str2) {
    }
}
