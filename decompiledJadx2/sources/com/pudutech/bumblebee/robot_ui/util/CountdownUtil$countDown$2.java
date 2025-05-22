package com.pudutech.bumblebee.robot_ui.util;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: CountdownUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.util.CountdownUtil$countDown$2", m3970f = "CountdownUtil.kt", m3971i = {0, 0, 1, 1}, m3972l = {27, 28}, m3973m = "invokeSuspend", m3974n = {"$this$flow", "i", "$this$flow", "i"}, m3975s = {"L$0", "J$0", "L$0", "J$0"})
/* loaded from: classes4.dex */
final class CountdownUtil$countDown$2 extends SuspendLambda implements Function2<FlowCollector<? super Long>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $countDown;
    long J$0;
    long J$1;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private FlowCollector f4965p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountdownUtil$countDown$2(long j, Continuation continuation) {
        super(2, continuation);
        this.$countDown = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CountdownUtil$countDown$2 countdownUtil$countDown$2 = new CountdownUtil$countDown$2(this.$countDown, completion);
        countdownUtil$countDown$2.f4965p$ = (FlowCollector) obj;
        return countdownUtil$countDown$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super Long> flowCollector, Continuation<? super Unit> continuation) {
        return ((CountdownUtil$countDown$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0042  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x006c -> B:6:0x006f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        long j;
        long j2;
        Object obj2;
        CountdownUtil$countDown$2 countdownUtil$countDown$2;
        Object obj3;
        long j3;
        long j4;
        FlowCollector flowCollector2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = this.f4965p$;
            j = this.$countDown;
            j2 = 0;
            obj2 = coroutine_suspended;
            countdownUtil$countDown$2 = this;
            if (j >= j2) {
            }
        } else if (i == 1) {
            j4 = this.J$1;
            j3 = this.J$0;
            flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj3 = coroutine_suspended;
            countdownUtil$countDown$2 = this;
            countdownUtil$countDown$2.L$0 = flowCollector2;
            countdownUtil$countDown$2.J$0 = j3;
            countdownUtil$countDown$2.J$1 = j4;
            countdownUtil$countDown$2.label = 2;
            if (DelayKt.delay(1000L, countdownUtil$countDown$2) != obj3) {
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j4 = this.J$1;
            j3 = this.J$0;
            FlowCollector flowCollector3 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector3;
            obj2 = coroutine_suspended;
            countdownUtil$countDown$2 = this;
            long j5 = j4;
            j = j3 - 1;
            j2 = j5;
            if (j >= j2) {
                Long boxLong = Boxing.boxLong(j);
                countdownUtil$countDown$2.L$0 = flowCollector;
                countdownUtil$countDown$2.J$0 = j;
                countdownUtil$countDown$2.J$1 = j2;
                countdownUtil$countDown$2.label = 1;
                if (flowCollector.emit(boxLong, countdownUtil$countDown$2) == obj2) {
                    return obj2;
                }
                Object obj4 = obj2;
                flowCollector2 = flowCollector;
                obj3 = obj4;
                long j6 = j;
                j4 = j2;
                j3 = j6;
                countdownUtil$countDown$2.L$0 = flowCollector2;
                countdownUtil$countDown$2.J$0 = j3;
                countdownUtil$countDown$2.J$1 = j4;
                countdownUtil$countDown$2.label = 2;
                if (DelayKt.delay(1000L, countdownUtil$countDown$2) != obj3) {
                    return obj3;
                }
                FlowCollector flowCollector4 = flowCollector2;
                obj2 = obj3;
                flowCollector = flowCollector4;
                long j52 = j4;
                j = j3 - 1;
                j2 = j52;
                if (j >= j2) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
