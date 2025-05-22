package com.pudutech.bumblebee.business;

import android.os.Handler;
import android.os.HandlerThread;
import com.iflytek.aiui.AIUIConstant;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimerThread.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0012\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fJ\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0011JB\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fJ\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\bJ\u0016\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/TimerThread;", "", "()V", "mHandler", "Landroid/os/Handler;", "runnableCache", "Ljava/util/HashMap;", "", "Ljava/lang/Runnable;", "Lkotlin/collections/HashMap;", "thread", "Landroid/os/HandlerThread;", "loop", "", "method", "Lkotlin/Function0;", "period_ms", "", "post", "runnable", "delay_ms", "rePost", "classStr", AIUIConstant.KEY_TAG, "loop_ms", "keepOld", "", "remove", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TimerThread {
    private static final Handler mHandler;
    private static final HashMap<String, Runnable> runnableCache;
    public static final TimerThread INSTANCE = new TimerThread();
    private static final HandlerThread thread = new HandlerThread("TimerThread");

    static {
        thread.start();
        mHandler = new Handler(thread.getLooper());
        runnableCache = new HashMap<>();
    }

    private TimerThread() {
    }

    public final void post(final Function0<Unit> method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        mHandler.post(new Runnable() { // from class: com.pudutech.bumblebee.business.TimerThread$sam$java_lang_Runnable$0
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
            }
        });
    }

    public final void loop(final Function0<Unit> method, final long period_ms) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        mHandler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.business.TimerThread$loop$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
                TimerThread.INSTANCE.loop(Function0.this, period_ms);
            }
        }, period_ms);
    }

    public final void post(Runnable runnable, long delay_ms) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        if (delay_ms > 0) {
            mHandler.postDelayed(runnable, delay_ms);
        } else {
            mHandler.post(runnable);
        }
    }

    public final void remove(Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        mHandler.removeCallbacks(runnable);
    }

    public final void rePost(String classStr, String tag, long delay_ms, long loop_ms, boolean keepOld, Function0<Unit> method) {
        Intrinsics.checkParameterIsNotNull(classStr, "classStr");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(method, "method");
        post(new TimerThread$rePost$1(classStr, tag, keepOld, method, loop_ms, delay_ms));
    }

    public final void remove(final String classStr, final String tag) {
        Intrinsics.checkParameterIsNotNull(classStr, "classStr");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.TimerThread$remove$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                HashMap hashMap;
                HashMap hashMap2;
                Handler handler;
                String str = (classStr + "_") + tag;
                TimerThread timerThread = TimerThread.INSTANCE;
                hashMap = TimerThread.runnableCache;
                Runnable runnable = (Runnable) hashMap.get(str);
                if (runnable != null) {
                    TimerThread timerThread2 = TimerThread.INSTANCE;
                    handler = TimerThread.mHandler;
                    handler.removeCallbacks(runnable);
                }
                TimerThread timerThread3 = TimerThread.INSTANCE;
                hashMap2 = TimerThread.runnableCache;
                hashMap2.remove(str);
            }
        });
    }
}
