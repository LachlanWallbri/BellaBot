package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$suspendWarningWelfunction$2", m3970f = "MoveAction.kt", m3971i = {0, 1}, m3972l = {1077, 1081}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes5.dex */
public final class MoveAction$suspendWarningWelfunction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Pair $warning;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5557p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$suspendWarningWelfunction$2(MoveAction moveAction, Pair pair, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
        this.$warning = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$suspendWarningWelfunction$2 moveAction$suspendWarningWelfunction$2 = new MoveAction$suspendWarningWelfunction$2(this.this$0, this.$warning, completion);
        moveAction$suspendWarningWelfunction$2.f5557p$ = (CoroutineScope) obj;
        return moveAction$suspendWarningWelfunction$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$suspendWarningWelfunction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0064  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        RobotHardware robotHardware;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5557p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "start error job when wheel warning");
            MoveAction moveAction = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (MoveAction.stopAndWaitBrake$default(moveAction, false, this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    while (CoroutineScopeKt.isActive(coroutineScope)) {
                        robotHardware = this.this$0.robotHardware;
                        HardwareInterface hardwareInterface = robotHardware.getInterface();
                        if (hardwareInterface != null) {
                            hardwareInterface.clearWheelError();
                        }
                        this.L$0 = coroutineScope;
                        this.label = 2;
                        if (DelayKt.delay(20L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        MoveAction moveAction2 = this.this$0;
        SDKRobotState sDKRobotState = SDKRobotState.Error;
        String str2 = (String) this.$warning.getSecond();
        if (str2 == null) {
            str2 = "";
        }
        moveAction2.onStateChange(sDKRobotState, str2);
        while (CoroutineScopeKt.isActive(coroutineScope)) {
        }
        return Unit.INSTANCE;
    }
}
