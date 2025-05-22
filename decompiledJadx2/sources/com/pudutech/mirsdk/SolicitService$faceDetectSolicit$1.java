package com.pudutech.mirsdk;

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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$faceDetectSolicit$1", m3970f = "SolicitService.kt", m3971i = {0}, m3972l = {361}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class SolicitService$faceDetectSolicit$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $angle;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5586p$;
    final /* synthetic */ SolicitService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SolicitService$faceDetectSolicit$1(SolicitService solicitService, double d, Continuation continuation) {
        super(2, continuation);
        this.this$0 = solicitService;
        this.$angle = d;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitService$faceDetectSolicit$1 solicitService$faceDetectSolicit$1 = new SolicitService$faceDetectSolicit$1(this.this$0, this.$angle, completion);
        solicitService$faceDetectSolicit$1.f5586p$ = (CoroutineScope) obj;
        return solicitService$faceDetectSolicit$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitService$faceDetectSolicit$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Vector3d vector3d;
        double extraAngleSpeed;
        RobotHardware robotHardware;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5586p$;
            this.this$0.closeFaceDetectNoNPU();
            SolicitService solicitService = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (solicitService.stopAndWaitBrake(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        double d = this.$angle;
        vector3d = this.this$0.robotPose;
        double z = d + vector3d.getZ();
        SolicitService solicitService2 = this.this$0;
        extraAngleSpeed = solicitService2.getExtraAngleSpeed(this.$angle);
        solicitService2.rotateDegree(z, extraAngleSpeed);
        robotHardware = this.this$0.robotHardware;
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlWheel(0.0d, 0.0d, false);
        }
        return Unit.INSTANCE;
    }
}
