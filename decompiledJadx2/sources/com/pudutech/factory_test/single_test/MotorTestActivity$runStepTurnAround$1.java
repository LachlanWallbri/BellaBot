package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.MotorTestActivity;
import com.pudutech.mirsdk.hardware.HardwareInterface;
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
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MotorTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.MotorTestActivity$runStepTurnAround$1", m3970f = "MotorTestActivity.kt", m3971i = {0}, m3972l = {106}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class MotorTestActivity$runStepTurnAround$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5193p$;
    final /* synthetic */ MotorTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MotorTestActivity$runStepTurnAround$1(MotorTestActivity motorTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = motorTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MotorTestActivity$runStepTurnAround$1 motorTestActivity$runStepTurnAround$1 = new MotorTestActivity$runStepTurnAround$1(this.this$0, completion);
        motorTestActivity$runStepTurnAround$1.f5193p$ = (CoroutineScope) obj;
        return motorTestActivity$runStepTurnAround$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MotorTestActivity$runStepTurnAround$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5193p$;
            this.this$0.setListener();
            HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
            if (hdInterface != null) {
                hdInterface.clearWheelError();
            }
            C45191 c45191 = new C45191(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(5000L, c45191, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 != null) {
            hdInterface2.controlWheel(0.0d, 0.0d, false);
        }
        this.this$0.removeListener();
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "distanceLeft=" + this.this$0.getDistanceLeft() + " distanceRight=" + this.this$0.getDistanceRight());
        if (this.this$0.getDistanceLeft() >= -0.1d || this.this$0.getDistanceRight() <= 0.1d) {
            str2 = this.this$0.TAG;
            Pdlog.m3274e(str2, "distance should be bigger than 0.1 with right direction");
            this.this$0.getMTestItem().setFailDescription("轮子的移动距离不够，可能是电机连线问题");
            this.this$0.setStep(MotorTestActivity.Step.FAIL);
        } else {
            this.this$0.setStep(MotorTestActivity.Step.SUCCESS);
        }
        this.this$0.FSM();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MotorTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.MotorTestActivity$runStepTurnAround$1$1", m3970f = "MotorTestActivity.kt", m3971i = {0}, m3972l = {109}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.factory_test.single_test.MotorTestActivity$runStepTurnAround$1$1 */
    /* loaded from: classes.dex */
    public static final class C45191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5194p$;

        C45191(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45191 c45191 = new C45191(completion);
            c45191.f5194p$ = (CoroutineScope) obj;
            return c45191;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5194p$;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            do {
                HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                if (hdInterface != null) {
                    hdInterface.controlWheel(0.0d, 0.2d, true);
                }
                this.L$0 = coroutineScope;
                this.label = 1;
            } while (DelayKt.delay(100L, this) != coroutine_suspended);
            return coroutine_suspended;
        }
    }
}
