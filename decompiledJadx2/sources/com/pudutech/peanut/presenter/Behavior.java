package com.pudutech.peanut.presenter;

import android.os.Handler;
import android.os.HandlerThread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Behavior.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\u0010\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0011J\u0016\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/Behavior;", "", "()V", "TAG", "", "handler", "Landroid/os/Handler;", "<set-?>", "", "initDone", "getInitDone", "()Z", "removeTaskDelayOnTaskThread", "", "task", "Ljava/lang/Runnable;", "runOnTaskThread", "Lkotlin/Function0;", "delay_ms", "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Behavior {
    private static Handler handler;
    private static boolean initDone;
    public static final Behavior INSTANCE = new Behavior();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    static {
        HandlerThread handlerThread = new HandlerThread("TaskThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    private Behavior() {
    }

    public final boolean getInitDone() {
        return initDone;
    }

    public final void runOnTaskThread(final Function0<Unit> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: com.pudutech.peanut.presenter.Behavior$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
                }
            });
        }
    }

    public final void runOnTaskThread(long delay_ms, Runnable task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.postDelayed(task, delay_ms);
        }
    }

    public final void removeTaskDelayOnTaskThread(Runnable task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.removeCallbacks(task);
        }
    }
}
