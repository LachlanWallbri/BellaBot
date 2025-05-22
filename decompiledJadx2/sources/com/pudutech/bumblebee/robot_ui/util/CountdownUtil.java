package com.pudutech.bumblebee.robot_ui.util;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: CountdownUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u0003\u001a\u00020\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/CountdownUtil;", "", "()V", "countDown", "Lkotlinx/coroutines/flow/Flow;", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCountDown", "Lio/reactivex/Observable;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CountdownUtil {
    public static final CountdownUtil INSTANCE = new CountdownUtil();

    private CountdownUtil() {
    }

    public final Observable<Long> createCountDown(final long countDown) {
        Observable<Long> subscribeOn = Observable.interval(0L, 1L, TimeUnit.SECONDS).take(1 + countDown).map((Function) new Function<T, R>() { // from class: com.pudutech.bumblebee.robot_ui.util.CountdownUtil$createCountDown$1
            @Override // io.reactivex.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return Long.valueOf(apply((Long) obj));
            }

            public final long apply(Long it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return countDown - it.longValue();
            }
        }).subscribeOn(Schedulers.computation());
        Intrinsics.checkExpressionValueIsNotNull(subscribeOn, "Observable.interval(0, 1…Schedulers.computation())");
        return subscribeOn;
    }

    public final Object countDown(long j, Continuation<? super Flow<Long>> continuation) {
        return FlowKt.flow(new CountdownUtil$countDown$2(j, null));
    }
}
