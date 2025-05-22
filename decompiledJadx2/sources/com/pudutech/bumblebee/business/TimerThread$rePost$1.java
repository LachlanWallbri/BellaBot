package com.pudutech.bumblebee.business;

import android.os.Handler;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TimerThread.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TimerThread$rePost$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $classStr;
    final /* synthetic */ long $delay_ms;
    final /* synthetic */ boolean $keepOld;
    final /* synthetic */ long $loop_ms;
    final /* synthetic */ Function0 $method;
    final /* synthetic */ String $tag;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimerThread$rePost$1(String str, String str2, boolean z, Function0 function0, long j, long j2) {
        super(0);
        this.$classStr = str;
        this.$tag = str2;
        this.$keepOld = z;
        this.$method = function0;
        this.$loop_ms = j;
        this.$delay_ms = j2;
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
        Handler handler2;
        Handler handler3;
        final String str = (this.$classStr + "_") + this.$tag;
        TimerThread timerThread = TimerThread.INSTANCE;
        hashMap = TimerThread.runnableCache;
        Runnable runnable = (Runnable) hashMap.get(str);
        if (runnable != null) {
            if (this.$keepOld) {
                return;
            }
            TimerThread timerThread2 = TimerThread.INSTANCE;
            handler3 = TimerThread.mHandler;
            handler3.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: com.pudutech.bumblebee.business.TimerThread$rePost$1$new$1
            @Override // java.lang.Runnable
            public final void run() {
                HashMap hashMap3;
                Handler handler4;
                TimerThread$rePost$1.this.$method.invoke();
                if (TimerThread$rePost$1.this.$loop_ms > 0) {
                    TimerThread timerThread3 = TimerThread.INSTANCE;
                    hashMap3 = TimerThread.runnableCache;
                    Runnable runnable3 = (Runnable) hashMap3.get(str);
                    if (runnable3 != null) {
                        TimerThread timerThread4 = TimerThread.INSTANCE;
                        handler4 = TimerThread.mHandler;
                        handler4.postDelayed(runnable3, TimerThread$rePost$1.this.$loop_ms);
                    }
                }
            }
        };
        TimerThread timerThread3 = TimerThread.INSTANCE;
        hashMap2 = TimerThread.runnableCache;
        hashMap2.put(str, runnable2);
        if (this.$delay_ms > 0) {
            TimerThread timerThread4 = TimerThread.INSTANCE;
            handler2 = TimerThread.mHandler;
            handler2.postDelayed(runnable2, this.$delay_ms);
        } else {
            TimerThread timerThread5 = TimerThread.INSTANCE;
            handler = TimerThread.mHandler;
            handler.post(runnable2);
        }
    }
}
