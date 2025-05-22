package com.pudutech.schedulerlib.p065ui;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerPressTestActivity$ackTestStart$5", m3970f = "SchedulerPressTestActivity.kt", m3971i = {0}, m3972l = {391}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes7.dex */
public final class SchedulerPressTestActivity$ackTestStart$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $info;
    final /* synthetic */ Ref.LongRef $recvIdAgo;
    final /* synthetic */ Ref.LongRef $recvIdNow;
    final /* synthetic */ long $sendDelayTime;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7474p$;
    final /* synthetic */ SchedulerPressTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerPressTestActivity$ackTestStart$5(SchedulerPressTestActivity schedulerPressTestActivity, Ref.LongRef longRef, Ref.ObjectRef objectRef, Ref.LongRef longRef2, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerPressTestActivity;
        this.$recvIdNow = longRef;
        this.$info = objectRef;
        this.$recvIdAgo = longRef2;
        this.$sendDelayTime = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerPressTestActivity$ackTestStart$5 schedulerPressTestActivity$ackTestStart$5 = new SchedulerPressTestActivity$ackTestStart$5(this.this$0, this.$recvIdNow, this.$info, this.$recvIdAgo, this.$sendDelayTime, completion);
        schedulerPressTestActivity$ackTestStart$5.f7474p$ = (CoroutineScope) obj;
        return schedulerPressTestActivity$ackTestStart$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerPressTestActivity$ackTestStart$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0028 */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        AtomicLong atomicLong;
        String str;
        AtomicLong atomicLong2;
        AtomicLong atomicLong3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7474p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (!z) {
            Ref.LongRef longRef = this.$recvIdNow;
            atomicLong = this.this$0.recvMsgId;
            longRef.element = atomicLong.get();
            this.this$0.sendMsgForTest((RobotScheduleInfo) this.$info.element, this.$recvIdNow.element);
            if (this.$recvIdNow.element != this.$recvIdAgo.element) {
                atomicLong3 = this.this$0.sendMsgs;
                atomicLong3.getAndIncrement();
                this.$recvIdAgo.element = this.$recvIdNow.element;
            }
            str = this.this$0.TAG;
            atomicLong2 = this.this$0.recvMsgId;
            Pdlog.m3273d(str, "recvMsgForTest 从机应答的ID号是： ", atomicLong2);
            long j = this.$sendDelayTime;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
