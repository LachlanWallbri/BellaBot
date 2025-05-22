package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$fixDegree$1", m3970f = "SolicitService.kt", m3971i = {0, 0, 0, 0}, m3972l = {282}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "tmpX", "tmpY", "diff"}, m3975s = {"L$0", "D$0", "D$1", "D$2"})
/* loaded from: classes4.dex */
public final class SolicitService$fixDegree$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    double D$0;
    double D$1;
    double D$2;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5589p$;
    final /* synthetic */ SolicitService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SolicitService$fixDegree$1(SolicitService solicitService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = solicitService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitService$fixDegree$1 solicitService$fixDegree$1 = new SolicitService$fixDegree$1(this.this$0, completion);
        solicitService$fixDegree$1.f5589p$ = (CoroutineScope) obj;
        return solicitService$fixDegree$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitService$fixDegree$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        Vector3d vector3d;
        Vector3d vector3d2;
        String str2;
        Job launch$default;
        String str3;
        RobotHardware robotHardware;
        HardwareInterface hardwareInterface;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5589p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "fix Linarjob start");
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            double d = this.D$2;
            double d2 = this.D$1;
            double d3 = this.D$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            vector3d = this.this$0.robotPose;
            double x = vector3d.getX();
            vector3d2 = this.this$0.robotPose;
            double y = vector3d2.getY();
            double sqrt = Math.sqrt(Math.pow(x - this.this$0.getStartPose().getX(), 2.0d) + Math.pow(y - this.this$0.getStartPose().getY(), 2.0d));
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "diff distance is " + sqrt);
            if (sqrt >= 0.1d) {
                str3 = this.this$0.TAG;
                Pdlog.m3273d(str3, "fix Linarjob controlWheel start");
                robotHardware = this.this$0.robotHardware;
                if (robotHardware != null && (hardwareInterface = robotHardware.getInterface()) != null) {
                    hardwareInterface.controlWheel(0.3d, 0.0d, true);
                }
                this.L$0 = coroutineScope;
                this.D$0 = x;
                this.D$1 = y;
                this.D$2 = sqrt;
                this.label = 1;
                if (DelayKt.delay(100L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                SolicitService solicitService = this.this$0;
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48111(null), 3, null);
                solicitService.fixAngarJob = launch$default;
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SolicitService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$fixDegree$1$1", m3970f = "SolicitService.kt", m3971i = {0, 0, 0}, m3972l = {275}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "theta", "angle"}, m3975s = {"L$0", "D$0", "D$1"})
    /* renamed from: com.pudutech.mirsdk.SolicitService$fixDegree$1$1 */
    /* loaded from: classes4.dex */
    public static final class C48111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        double D$0;
        double D$1;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5590p$;

        C48111(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48111 c48111 = new C48111(completion);
            c48111.f5590p$ = (CoroutineScope) obj;
            return c48111;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            String str;
            Vector3d vector3d;
            RobotHardware robotHardware;
            HardwareInterface hardwareInterface;
            String str2;
            RobotHardware robotHardware2;
            HardwareInterface hardwareInterface2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5590p$;
                str = SolicitService$fixDegree$1.this.this$0.TAG;
                Pdlog.m3273d(str, "fix AngarJob start");
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                double d = this.D$1;
                double d2 = this.D$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                vector3d = SolicitService$fixDegree$1.this.this$0.robotPose;
                double z = vector3d.getZ() - SolicitService$fixDegree$1.this.this$0.getStartPose().getZ();
                double d3 = (180 * z) / 3.141592653589793d;
                if (Math.abs(d3) < 2.0f) {
                    robotHardware = SolicitService$fixDegree$1.this.this$0.robotHardware;
                    if (robotHardware != null && (hardwareInterface = robotHardware.getInterface()) != null) {
                        hardwareInterface.controlWheel(0.0d, 0.0d, false);
                    }
                    return Unit.INSTANCE;
                }
                str2 = SolicitService$fixDegree$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "fix AngarJob controlWheel start");
                robotHardware2 = SolicitService$fixDegree$1.this.this$0.robotHardware;
                if (robotHardware2 != null && (hardwareInterface2 = robotHardware2.getInterface()) != null) {
                    hardwareInterface2.controlWheel(0.0d, 0.2d, true);
                }
                this.L$0 = coroutineScope;
                this.D$0 = z;
                this.D$1 = d3;
                this.label = 1;
                if (DelayKt.delay(100L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }
}
