package com.pudutech.factory_test;

import android.util.Log;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/factory_test/CrashHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "()V", "TAG", "", "onHandle", "Lkotlin/Function0;", "", "getOnHandle", "()Lkotlin/jvm/functions/Function0;", "setOnHandle", "(Lkotlin/jvm/functions/Function0;)V", "getCrashInfo", "ex", "", "uncaughtException", "t", "Ljava/lang/Thread;", C3898x.f4338g, "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final String TAG = "CrashHandler";
    private Function0<Unit> onHandle;

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread t, Throwable e) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Intrinsics.checkParameterIsNotNull(e, "e");
        try {
            Pdlog.m3274e(this.TAG, String.valueOf(getCrashInfo(e)));
        } catch (Exception unused) {
            Log.e(this.TAG, String.valueOf(getCrashInfo(e)));
        }
        Function0<Unit> function0 = this.onHandle;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final Function0<Unit> getOnHandle() {
        return this.onHandle;
    }

    public final void setOnHandle(Function0<Unit> function0) {
        this.onHandle = function0;
    }

    public final String getCrashInfo(Throwable ex) {
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.setStackTrace(ex.getStackTrace());
        ex.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }
}
