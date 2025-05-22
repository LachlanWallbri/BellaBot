package com.pudutech.mirsdk.hardware;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.cameralib.CameraLib;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.StepState;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 1}, m3972l = {553, 597}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "openCameraResult", "recv_camera", "checkLoop", "$this$launch", "openCameraResult", "recv_camera", "checkLoop"}, m3975s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$checkCamera$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HardwareInterfaceStub.SelfCheckSensorInfo $selfCheckSensorInfo;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5899p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$checkCamera$2(HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation) {
        super(2, continuation);
        this.$selfCheckSensorInfo = selfCheckSensorInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$checkCamera$2 hardwareInterfaceStub$checkCamera$2 = new HardwareInterfaceStub$checkCamera$2(this.$selfCheckSensorInfo, completion);
        hardwareInterfaceStub$checkCamera$2.f5899p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$checkCamera$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$checkCamera$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x01db  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00ee -> B:19:0x00f3). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ThreadSafeListener threadSafeListener;
        CameraImpl cameraImpl;
        String str;
        CameraImpl cameraImpl2;
        HardwareInterfaceStub$checkCamera$2 hardwareInterfaceStub$checkCamera$2;
        CoroutineScope coroutineScope;
        Object obj2;
        final Ref.BooleanRef booleanRef;
        CameraLib.Result result;
        int i;
        ThreadSafeListener threadSafeListener2;
        CameraImpl cameraImpl3;
        String str2;
        MachineInfoProcess machineInfoProcess;
        CameraImpl cameraImpl4;
        Object withTimeoutOrNull;
        ThreadSafeListener threadSafeListener3;
        ThreadSafeListener threadSafeListener4;
        final Boolean bool;
        CameraImpl cameraImpl5;
        String str3;
        ThreadSafeListener threadSafeListener5;
        HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo;
        CameraImpl cameraImpl6;
        ThreadSafeListener threadSafeListener6;
        CameraImpl cameraImpl7;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5899p$;
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            threadSafeListener = HardwareInterfaceStub.hardwareListener;
            threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str4) {
                    invoke2(iHardware, str4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware l, String str4) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                    l.onOpenStep(HardwareOpenStep.CameraCheck, StepState.Running, "");
                }
            });
            HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
            cameraImpl = HardwareInterfaceStub.cameraImpl;
            final CameraLib.Result open = cameraImpl.open();
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                return Unit.INSTANCE;
            }
            HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
            str = HardwareInterfaceStub.TAG;
            Pdlog.m3275i(str, "MarkerCamera.open result:" + open);
            if (!open.isSuccess()) {
                HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.2
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str4) {
                        invoke2(iHardware, str4);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str4) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                        l.onOpenStep(HardwareOpenStep.CameraCheck, StepState.Fail, CameraLib.Result.this.getDescription());
                    }
                });
                HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2 = this.$selfCheckSensorInfo;
                if (selfCheckSensorInfo2 != null) {
                    selfCheckSensorInfo2.onState(HardwareInterfaceStub.SelfCheckSensor.MarkerCamera, false);
                }
                return Unit.INSTANCE;
            }
            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            booleanRef2.element = false;
            HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
            cameraImpl2 = HardwareInterfaceStub.cameraImpl;
            cameraImpl2.addMarkerCameraListener("self_check", new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.3
                @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
                public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                    Ref.BooleanRef.this.element = true;
                }
            });
            hardwareInterfaceStub$checkCamera$2 = this;
            coroutineScope = coroutineScope2;
            obj2 = coroutine_suspended;
            booleanRef = booleanRef2;
            result = open;
            i = 0;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
            HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
            cameraImpl3 = HardwareInterfaceStub.cameraImpl;
            cameraImpl3.removeMarkerCameraListener("self_check");
            HardwareInterfaceStub hardwareInterfaceStub7 = HardwareInterfaceStub.INSTANCE;
            str2 = HardwareInterfaceStub.TAG;
            Pdlog.m3275i(str2, "camera check at least one frame:" + booleanRef.element);
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else {
            if (i2 != 1) {
                if (i2 == 2) {
                    booleanRef = (Ref.BooleanRef) this.L$2;
                    ResultKt.throwOnFailure(obj);
                    withTimeoutOrNull = obj;
                    hardwareInterfaceStub$checkCamera$2 = this;
                    bool = (Boolean) withTimeoutOrNull;
                    HardwareInterfaceStub hardwareInterfaceStub8 = HardwareInterfaceStub.INSTANCE;
                    cameraImpl5 = HardwareInterfaceStub.cameraImpl;
                    cameraImpl5.removeMonocularCameraListener("self_check");
                    HardwareInterfaceStub hardwareInterfaceStub9 = HardwareInterfaceStub.INSTANCE;
                    str3 = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str3, "monocular camera check at least one frame:" + bool);
                    if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                        HardwareInterfaceStub hardwareInterfaceStub10 = HardwareInterfaceStub.INSTANCE;
                        cameraImpl6 = HardwareInterfaceStub.cameraImpl;
                        cameraImpl6.closeMonocularCamera();
                        HardwareInterfaceStub hardwareInterfaceStub11 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener6 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener6.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.7
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str4) {
                                invoke2(iHardware, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IHardware it, String str4) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                                it.onOpenStep(HardwareOpenStep.CameraCheck, StepState.Success, "");
                            }
                        });
                        HardwareInterfaceStub hardwareInterfaceStub12 = HardwareInterfaceStub.INSTANCE;
                        cameraImpl7 = HardwareInterfaceStub.cameraImpl;
                        cameraImpl7.closeMonocularCamera();
                    } else {
                        HardwareInterfaceStub hardwareInterfaceStub13 = HardwareInterfaceStub.INSTANCE;
                        threadSafeListener5 = HardwareInterfaceStub.hardwareListener;
                        threadSafeListener5.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.8
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str4) {
                                invoke2(iHardware, str4);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(IHardware l, String str4) {
                                Intrinsics.checkParameterIsNotNull(l, "l");
                                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                                l.onOpenStep(HardwareOpenStep.CameraCheck, StepState.Fail, "monocular camera check at least one frame:" + bool);
                            }
                        });
                    }
                    selfCheckSensorInfo = hardwareInterfaceStub$checkCamera$2.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo != null) {
                        selfCheckSensorInfo.onState(HardwareInterfaceStub.SelfCheckSensor.MarkerCamera, booleanRef.element);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = this.I$0;
            Ref.BooleanRef booleanRef3 = (Ref.BooleanRef) this.L$2;
            result = (CameraLib.Result) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            hardwareInterfaceStub$checkCamera$2 = this;
            Object obj3 = coroutine_suspended;
            booleanRef = booleanRef3;
            Object obj4 = obj3;
            i = i3 + 10;
            obj2 = obj4;
            if (!CoroutineScopeKt.isActive(coroutineScope) && !booleanRef.element && i < 30000) {
                hardwareInterfaceStub$checkCamera$2.L$0 = coroutineScope;
                hardwareInterfaceStub$checkCamera$2.L$1 = result;
                hardwareInterfaceStub$checkCamera$2.L$2 = booleanRef;
                hardwareInterfaceStub$checkCamera$2.I$0 = i;
                hardwareInterfaceStub$checkCamera$2.label = 1;
                if (DelayKt.delay(10L, hardwareInterfaceStub$checkCamera$2) == obj2) {
                    return obj2;
                }
                int i4 = i;
                obj3 = obj2;
                i3 = i4;
                Object obj42 = obj3;
                i = i3 + 10;
                obj2 = obj42;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
                HardwareInterfaceStub hardwareInterfaceStub62 = HardwareInterfaceStub.INSTANCE;
                cameraImpl3 = HardwareInterfaceStub.cameraImpl;
                cameraImpl3.removeMarkerCameraListener("self_check");
                HardwareInterfaceStub hardwareInterfaceStub72 = HardwareInterfaceStub.INSTANCE;
                str2 = HardwareInterfaceStub.TAG;
                Pdlog.m3275i(str2, "camera check at least one frame:" + booleanRef.element);
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else {
                HardwareInterfaceStub hardwareInterfaceStub622 = HardwareInterfaceStub.INSTANCE;
                cameraImpl3 = HardwareInterfaceStub.cameraImpl;
                cameraImpl3.removeMarkerCameraListener("self_check");
                HardwareInterfaceStub hardwareInterfaceStub722 = HardwareInterfaceStub.INSTANCE;
                str2 = HardwareInterfaceStub.TAG;
                Pdlog.m3275i(str2, "camera check at least one frame:" + booleanRef.element);
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                if (!booleanRef.element) {
                    HardwareInterfaceStub hardwareInterfaceStub14 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener4 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener4.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.4
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str4) {
                            invoke2(iHardware, str4);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str4) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.CameraCheck, StepState.Fail, "marker camera check at least one frame:" + Ref.BooleanRef.this.element);
                        }
                    });
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo3 = hardwareInterfaceStub$checkCamera$2.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo3 != null) {
                        selfCheckSensorInfo3.onState(HardwareInterfaceStub.SelfCheckSensor.MarkerCamera, booleanRef.element);
                    }
                    return Unit.INSTANCE;
                }
                HardwareInterfaceStub hardwareInterfaceStub15 = HardwareInterfaceStub.INSTANCE;
                machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
                if (machineInfoProcess.getMachineInfo().getMonocularDeviceType() == MachineInfo.MonocularType.NoDevice) {
                    HardwareInterfaceStub hardwareInterfaceStub16 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.5
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str4) {
                            invoke2(iHardware, str4);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str4) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.CameraCheck, StepState.Success, "");
                        }
                    });
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo4 = hardwareInterfaceStub$checkCamera$2.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo4 != null) {
                        selfCheckSensorInfo4.onState(HardwareInterfaceStub.SelfCheckSensor.MarkerCamera, booleanRef.element);
                    }
                    return Unit.INSTANCE;
                }
                booleanRef.element = false;
                HardwareInterfaceStub hardwareInterfaceStub17 = HardwareInterfaceStub.INSTANCE;
                cameraImpl4 = HardwareInterfaceStub.cameraImpl;
                cameraImpl4.addMonocularCameraListener("self_check", new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkCamera$2.6
                    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
                    public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                        Ref.BooleanRef.this.element = true;
                    }
                });
                HardwareInterfaceStub$checkCamera$2$rgbResult$1 hardwareInterfaceStub$checkCamera$2$rgbResult$1 = new HardwareInterfaceStub$checkCamera$2$rgbResult$1(booleanRef, null);
                hardwareInterfaceStub$checkCamera$2.L$0 = coroutineScope;
                hardwareInterfaceStub$checkCamera$2.L$1 = result;
                hardwareInterfaceStub$checkCamera$2.L$2 = booleanRef;
                hardwareInterfaceStub$checkCamera$2.I$0 = i;
                hardwareInterfaceStub$checkCamera$2.label = 2;
                withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(30000L, hardwareInterfaceStub$checkCamera$2$rgbResult$1, hardwareInterfaceStub$checkCamera$2);
                if (withTimeoutOrNull == obj2) {
                    return obj2;
                }
                bool = (Boolean) withTimeoutOrNull;
                HardwareInterfaceStub hardwareInterfaceStub82 = HardwareInterfaceStub.INSTANCE;
                cameraImpl5 = HardwareInterfaceStub.cameraImpl;
                cameraImpl5.removeMonocularCameraListener("self_check");
                HardwareInterfaceStub hardwareInterfaceStub92 = HardwareInterfaceStub.INSTANCE;
                str3 = HardwareInterfaceStub.TAG;
                Pdlog.m3275i(str3, "monocular camera check at least one frame:" + bool);
                if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
                }
                selfCheckSensorInfo = hardwareInterfaceStub$checkCamera$2.$selfCheckSensorInfo;
                if (selfCheckSensorInfo != null) {
                }
                return Unit.INSTANCE;
            }
        }
    }
}
