package com.pudutech.mirsdk.hardware;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.schedulerlib.ScheduleController;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: ScheduleCommunicationImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.ScheduleCommunicationImpl$close$1", m3970f = "ScheduleCommunicationImpl.kt", m3971i = {0}, m3972l = {40}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class ScheduleCommunicationImpl$close$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5941p$;
    final /* synthetic */ ScheduleCommunicationImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScheduleCommunicationImpl$close$1(ScheduleCommunicationImpl scheduleCommunicationImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = scheduleCommunicationImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ScheduleCommunicationImpl$close$1 scheduleCommunicationImpl$close$1 = new ScheduleCommunicationImpl$close$1(this.this$0, completion);
        scheduleCommunicationImpl$close$1.f5941p$ = (CoroutineScope) obj;
        return scheduleCommunicationImpl$close$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ScheduleCommunicationImpl$close$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
    
        r1 = r4.this$0.schJob;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ScheduleController scheduleController;
        String str;
        Job job;
        Job job2;
        ScheduleController scheduleController2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5941p$;
            scheduleController = this.this$0.scheduler;
            ThreadSafeListener<ScheduleController.ScheInfoCallback> infoListener = scheduleController.getInfoListener();
            str = this.this$0.TAG;
            infoListener.remove(str);
            job = this.this$0.schJob;
            if (job != null && job2 != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        scheduleController2 = this.this$0.scheduler;
        scheduleController2.destroyScheduler();
        return Unit.INSTANCE;
    }
}
