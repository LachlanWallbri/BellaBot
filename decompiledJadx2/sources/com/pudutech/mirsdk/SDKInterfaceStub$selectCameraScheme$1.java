package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
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
import kotlinx.coroutines.DelayKt;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$selectCameraScheme$1", m3970f = "SDKService.kt", m3971i = {0, 1}, m3972l = {939, 946}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$selectCameraScheme$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CameraType $p0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5576p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SDKInterfaceStub$selectCameraScheme$1(CameraType cameraType, Continuation continuation) {
        super(2, continuation);
        this.$p0 = cameraType;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$selectCameraScheme$1 sDKInterfaceStub$selectCameraScheme$1 = new SDKInterfaceStub$selectCameraScheme$1(this.$p0, completion);
        sDKInterfaceStub$selectCameraScheme$1.f5576p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$selectCameraScheme$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$selectCameraScheme$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0086  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        RobotHardware robotHardware;
        RobotHardware robotHardware2;
        RobotHardware robotHardware3;
        CameraInterface camera;
        CameraInterface camera2;
        RobotHardware robotHardware4;
        RobotHardware robotHardware5;
        HardwareInterface hardwareInterface;
        RobotHardware robotHardware6;
        HardwareInterface hardwareInterface2;
        CameraInterface camera3;
        CameraInterface camera4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5576p$;
            if (this.$p0 == CameraType.MARKER_CAMERA) {
                Pdlog.m3273d("CameraType , type is open MarkerCamera", new Object[0]);
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            if (this.$p0 == CameraType.FACE_CAMERA) {
                Pdlog.m3273d("CameraType , type is closeMarkerCamera", new Object[0]);
                this.L$0 = coroutineScope;
                this.label = 2;
                if (DelayKt.delay(500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                robotHardware4 = SDKInterfaceStub.robotHardware;
                robotHardware4.switchWatchCamera("Camera");
                SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                robotHardware5 = SDKInterfaceStub.robotHardware;
                hardwareInterface = robotHardware5.getInterface();
                if (hardwareInterface != null) {
                    camera4.closeMarkerCamera();
                }
                Pdlog.m3273d("CameraType , open openMonocularCamera", new Object[0]);
                SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
                robotHardware6 = SDKInterfaceStub.robotHardware;
                hardwareInterface2 = robotHardware6.getInterface();
                if (hardwareInterface2 != null) {
                    camera3.openMonocularCamera();
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                SDKInterfaceStub sDKInterfaceStub4 = SDKInterfaceStub.INSTANCE;
                robotHardware4 = SDKInterfaceStub.robotHardware;
                robotHardware4.switchWatchCamera("Camera");
                SDKInterfaceStub sDKInterfaceStub22 = SDKInterfaceStub.INSTANCE;
                robotHardware5 = SDKInterfaceStub.robotHardware;
                hardwareInterface = robotHardware5.getInterface();
                if (hardwareInterface != null && (camera4 = hardwareInterface.getCamera()) != null) {
                    camera4.closeMarkerCamera();
                }
                Pdlog.m3273d("CameraType , open openMonocularCamera", new Object[0]);
                SDKInterfaceStub sDKInterfaceStub32 = SDKInterfaceStub.INSTANCE;
                robotHardware6 = SDKInterfaceStub.robotHardware;
                hardwareInterface2 = robotHardware6.getInterface();
                if (hardwareInterface2 != null && (camera3 = hardwareInterface2.getCamera()) != null) {
                    camera3.openMonocularCamera();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        coroutineScope = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        SDKInterfaceStub sDKInterfaceStub5 = SDKInterfaceStub.INSTANCE;
        robotHardware = SDKInterfaceStub.robotHardware;
        robotHardware.switchWatchCamera("FaceCamera");
        SDKInterfaceStub sDKInterfaceStub6 = SDKInterfaceStub.INSTANCE;
        robotHardware2 = SDKInterfaceStub.robotHardware;
        HardwareInterface hardwareInterface3 = robotHardware2.getInterface();
        if (hardwareInterface3 != null && (camera2 = hardwareInterface3.getCamera()) != null) {
            camera2.closeMonocularCamera();
        }
        SDKInterfaceStub sDKInterfaceStub7 = SDKInterfaceStub.INSTANCE;
        robotHardware3 = SDKInterfaceStub.robotHardware;
        HardwareInterface hardwareInterface4 = robotHardware3.getInterface();
        if (hardwareInterface4 != null && (camera = hardwareInterface4.getCamera()) != null) {
            camera.openMarkerCamera();
        }
        if (this.$p0 == CameraType.FACE_CAMERA) {
        }
        return Unit.INSTANCE;
    }
}
