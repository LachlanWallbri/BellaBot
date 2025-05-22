package com.pudutech.mirsdk.dance;

import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoveHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.MoveHelper$checkAndClearWheelError$result$1", m3970f = "MoveHelper.kt", m3971i = {0}, m3972l = {225}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class MoveHelper$checkAndClearWheelError$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5780p$;
    final /* synthetic */ MoveHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveHelper$checkAndClearWheelError$result$1(MoveHelper moveHelper, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveHelper$checkAndClearWheelError$result$1 moveHelper$checkAndClearWheelError$result$1 = new MoveHelper$checkAndClearWheelError$result$1(this.this$0, completion);
        moveHelper$checkAndClearWheelError$result$1.f5780p$ = (CoroutineScope) obj;
        return moveHelper$checkAndClearWheelError$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MoveHelper$checkAndClearWheelError$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        RobotHardware robotHardware;
        RobotHardware robotHardware2;
        RobotHardware robotHardware3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5780p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            robotHardware = this.this$0.robotHardware;
            if (!robotHardware.getWheelMulfunctionState()) {
                break;
            }
            robotHardware2 = this.this$0.robotHardware;
            HardwareInterface hardwareInterface = robotHardware2.getInterface();
            if (hardwareInterface != null) {
                hardwareInterface.clearWheelError();
            }
            robotHardware3 = this.this$0.robotHardware;
            HardwareInterface hardwareInterface2 = robotHardware3.getInterface();
            if (hardwareInterface2 != null) {
                hardwareInterface2.controlWheel(0.0d, 0.0d, true);
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(20L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Boxing.boxBoolean(true);
    }
}
