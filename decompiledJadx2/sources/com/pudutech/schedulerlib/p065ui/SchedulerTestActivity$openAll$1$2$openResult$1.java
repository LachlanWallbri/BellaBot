package com.pudutech.schedulerlib.p065ui;

import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.p065ui.SchedulerTestActivity$openAll$1;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$2$openResult$1", m3970f = "SchedulerTestActivity.kt", m3971i = {0}, m3972l = {220}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
final class SchedulerTestActivity$openAll$1$2$openResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7499p$;
    final /* synthetic */ SchedulerTestActivity$openAll$1.C57482 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerTestActivity$openAll$1$2$openResult$1(SchedulerTestActivity$openAll$1.C57482 c57482, Continuation continuation) {
        super(2, continuation);
        this.this$0 = c57482;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerTestActivity$openAll$1$2$openResult$1 schedulerTestActivity$openAll$1$2$openResult$1 = new SchedulerTestActivity$openAll$1$2$openResult$1(this.this$0, completion);
        schedulerTestActivity$openAll$1$2$openResult$1.f7499p$ = (CoroutineScope) obj;
        return schedulerTestActivity$openAll$1$2$openResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((SchedulerTestActivity$openAll$1$2$openResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ScheduleController scheduleController;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7499p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            scheduleController = SchedulerTestActivity$openAll$1.this.this$0.controller;
            Boolean espIsConnected = scheduleController.espIsConnected();
            if (espIsConnected == null) {
                Intrinsics.throwNpe();
            }
            if (!espIsConnected.booleanValue()) {
                this.L$0 = coroutineScope;
                this.label = 1;
            } else {
                return Boxing.boxBoolean(true);
            }
        } while (DelayKt.delay(20L, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
