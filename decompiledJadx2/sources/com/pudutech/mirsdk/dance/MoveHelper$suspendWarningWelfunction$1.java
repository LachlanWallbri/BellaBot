package com.pudutech.mirsdk.dance;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
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
/* compiled from: MoveHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.MoveHelper$suspendWarningWelfunction$1", m3970f = "MoveHelper.kt", m3971i = {0, 1}, m3972l = {440, 444}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
public final class MoveHelper$suspendWarningWelfunction$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Pair $warning;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5785p$;
    final /* synthetic */ MoveHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveHelper$suspendWarningWelfunction$1(MoveHelper moveHelper, Pair pair, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveHelper;
        this.$warning = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveHelper$suspendWarningWelfunction$1 moveHelper$suspendWarningWelfunction$1 = new MoveHelper$suspendWarningWelfunction$1(this.this$0, this.$warning, completion);
        moveHelper$suspendWarningWelfunction$1.f5785p$ = (CoroutineScope) obj;
        return moveHelper$suspendWarningWelfunction$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveHelper$suspendWarningWelfunction$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0067  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        Function2 function2;
        RobotHardware robotHardware;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5785p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "start error job when wheel warning");
            MoveHelper moveHelper = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (moveHelper.stopAndWaitBrake(this) == coroutine_suspended) {
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
        function2 = this.this$0._onStateChange;
        RobotState robotState = RobotState.Error;
        String str2 = (String) this.$warning.getSecond();
        if (str2 == null) {
            str2 = "";
        }
        function2.invoke(robotState, str2);
        while (CoroutineScopeKt.isActive(coroutineScope)) {
        }
        return Unit.INSTANCE;
    }
}
