package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.InitMappingServiceListener;
import com.pudutech.mirsdk.mircore.MirMappingCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep;
import com.pudutech.mirsdk.mircore.p057ui.MappingActivity;
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
import kotlinx.coroutines.TimeoutKt;

/* compiled from: MappingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1", m3970f = "MappingActivity.kt", m3971i = {0, 0}, m3972l = {111}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "state"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes4.dex */
final class MappingActivity$openCore$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6272p$;
    final /* synthetic */ MappingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MappingActivity$openCore$1(MappingActivity mappingActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mappingActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MappingActivity$openCore$1 mappingActivity$openCore$1 = new MappingActivity$openCore$1(this.this$0, completion);
        mappingActivity$openCore$1.f6272p$ = (CoroutineScope) obj;
        return mappingActivity$openCore$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MappingActivity$openCore$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [T, com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        String str;
        AIDLConnection aIDLConnection2;
        AIDLConnection aIDLConnection3;
        HardwareInterface hardwareInterface;
        HardwareInterface hardwareInterface2;
        String str2;
        AIDLConnection aIDLConnection4;
        AIDLConnection aIDLConnection5;
        AIDLConnection aIDLConnection6;
        AIDLConnection aIDLConnection7;
        AIDLConnection aIDLConnection8;
        MirMappingCoreInterface mirMappingCoreInterface;
        HardwareInterface hardwareInterface3;
        CameraInterface camera;
        HardwareInterface hardwareInterface4;
        CameraInterface camera2;
        MirMappingCoreInterface mirMappingCoreInterface2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6272p$;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (HardwareOpenStep) 0;
            aIDLConnection = this.this$0.hardwareService;
            if (aIDLConnection != null && (hardwareInterface2 = (HardwareInterface) aIDLConnection.getInterface()) != null) {
                hardwareInterface2.addListener("testCore", new BinderC52541(objectRef));
            }
            str = this.this$0.TAG;
            Object[] objArr = new Object[2];
            objArr[0] = "hardwareService getInterface";
            aIDLConnection2 = this.this$0.hardwareService;
            objArr[1] = aIDLConnection2 != null ? (HardwareInterface) aIDLConnection2.getInterface() : null;
            Pdlog.m3273d(str, objArr);
            aIDLConnection3 = this.this$0.hardwareService;
            if (aIDLConnection3 != null && (hardwareInterface = (HardwareInterface) aIDLConnection3.getInterface()) != null) {
                hardwareInterface.open();
            }
            C52552 c52552 = new C52552(objectRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = objectRef;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(30000L, c52552, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        str2 = this.this$0.TAG;
        Object[] objArr2 = new Object[2];
        objArr2[0] = "mappingCoreService getInterface";
        aIDLConnection4 = this.this$0.mappingCoreService;
        objArr2[1] = aIDLConnection4 != null ? (MirMappingCoreInterface) aIDLConnection4.getInterface() : null;
        Pdlog.m3273d(str2, objArr2);
        aIDLConnection5 = this.this$0.mappingCoreService;
        if (aIDLConnection5 != null && (mirMappingCoreInterface2 = (MirMappingCoreInterface) aIDLConnection5.getInterface()) != null) {
            mirMappingCoreInterface2.initModules(new BinderC52563());
        }
        aIDLConnection6 = this.this$0.hardwareService;
        if (aIDLConnection6 != null && (hardwareInterface4 = (HardwareInterface) aIDLConnection6.getInterface()) != null && (camera2 = hardwareInterface4.getCamera()) != null) {
            camera2.openMarkerCamera();
        }
        aIDLConnection7 = this.this$0.hardwareService;
        if (aIDLConnection7 != null && (hardwareInterface3 = (HardwareInterface) aIDLConnection7.getInterface()) != null && (camera = hardwareInterface3.getCamera()) != null) {
            camera.closeMonocularCamera();
        }
        aIDLConnection8 = this.this$0.mappingCoreService;
        if (aIDLConnection8 != null && (mirMappingCoreInterface = (MirMappingCoreInterface) aIDLConnection8.getInterface()) != null) {
            mirMappingCoreInterface.addMappingSensorListener();
        }
        return Unit.INSTANCE;
    }

    /* compiled from: MappingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0011H\u0016J&\u0010\u0012\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00132\b\u0010\b\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J1\u0010\u0017\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0019\u0018\u00010\u00182\u0010\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0019\u0018\u00010\u0018H\u0016¢\u0006\u0002\u0010\u001a¨\u0006\u001b"}, m3961d2 = {"com/pudutech/mirsdk/mircore/ui/MappingActivity$openCore$1$1", "Lcom/pudutech/mirsdk/hardware/IHardware$Stub;", "onAntiCollisionSwitch", "", "p0", "", "onBattery", "", "p1", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatterySOH", "onCameraIRDLED", "onChargingFail", "onConnectedChargingPile", "onDisinfectionPower", "onHarewareEmergencyBrake", "onHarewareInfoReport", "", "onOpenStep", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "p2", "onSlamCorePower", "onWheelError", "", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "([Lcom/pudutech/mirsdk/hardware/serialize/WheelError;[Lcom/pudutech/mirsdk/hardware/serialize/WheelError;)V", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$1 */
    /* loaded from: classes4.dex */
    public static final class BinderC52541 extends IHardware.Stub {
        final /* synthetic */ Ref.ObjectRef $state;

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onAntiCollisionSwitch(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBattery(int p0, ChargeState p1) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBatterySOH(int p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onCameraIRDLED(boolean p0) {
        }

        public void onChargingFail() {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onConnectedChargingPile() {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onDisinfectionPower(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onHarewareEmergencyBrake(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onHarewareInfoReport(String p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onSlamCorePower(boolean p0) {
        }

        BinderC52541(Ref.ObjectRef objectRef) {
            this.$state = objectRef;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onOpenStep(final HardwareOpenStep p0, final StepState p1, String p2) {
            String str;
            str = MappingActivity$openCore$1.this.this$0.TAG;
            Pdlog.m3273d(str, "step " + p0 + " state " + p1 + " description " + p2);
            this.$state.element = p0;
            MappingActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$1$onOpenStep$1
                @Override // java.lang.Runnable
                public final void run() {
                    TextView status_text = (TextView) MappingActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                    Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                    StringBuilder sb = new StringBuilder();
                    sb.append(p0);
                    sb.append(' ');
                    sb.append(p1);
                    status_text.setText(sb.toString());
                }
            });
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onWheelError(WheelError[] p0, WheelError[] p1) {
            MappingActivity$openCore$1.this.this$0.wheelError = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MappingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$2", m3970f = "MappingActivity.kt", m3971i = {0}, m3972l = {113}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$2 */
    /* loaded from: classes4.dex */
    public static final class C52552 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $state;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6273p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C52552(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$state = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52552 c52552 = new C52552(this.$state, completion);
            c52552.f6273p$ = (CoroutineScope) obj;
            return c52552;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52552) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f6273p$;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (((HardwareOpenStep) this.$state.element) != HardwareOpenStep.Finish) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(10L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: MappingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/mircore/ui/MappingActivity$openCore$1$3", "Lcom/pudutech/mirsdk/mircore/InitMappingServiceListener$Stub;", "initMappingCoreServiceState", "", "step", "Lcom/pudutech/mirsdk/mircore/coreparcel/MappingCoreInitStep;", "state", "Lcom/pudutech/mirsdk/mircore/coreparcel/MappingCoreInitState;", "description", "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$3 */
    /* loaded from: classes4.dex */
    public static final class BinderC52563 extends InitMappingServiceListener.Stub {
        BinderC52563() {
        }

        @Override // com.pudutech.mirsdk.mircore.InitMappingServiceListener
        public void initMappingCoreServiceState(MappingCoreInitStep step, MappingCoreInitState state, final String description) {
            String str;
            String str2;
            String str3;
            str = MappingActivity$openCore$1.this.this$0.TAG;
            Pdlog.m3273d(str, "core init step " + step + " state " + state + " description " + description);
            if (step != null && MappingActivity.WhenMappings.$EnumSwitchMapping$2[step.ordinal()] == 1) {
                if (state != null && MappingActivity.WhenMappings.$EnumSwitchMapping$0[state.ordinal()] == 1) {
                    str3 = MappingActivity$openCore$1.this.this$0.TAG;
                    Pdlog.m3273d(str3, "MappingCoreInitState Succeed");
                    MappingActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$3$initMappingCoreServiceState$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            TextView status_text = (TextView) MappingActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                            status_text.setText("Success");
                        }
                    });
                    return;
                } else {
                    str2 = MappingActivity$openCore$1.this.this$0.TAG;
                    Pdlog.m3273d(str2, "MappingCoreInitState Failed");
                    MappingActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$3$initMappingCoreServiceState$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            TextView status_text = (TextView) MappingActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                            status_text.setText("Failed");
                        }
                    });
                    return;
                }
            }
            if (state != null && MappingActivity.WhenMappings.$EnumSwitchMapping$1[state.ordinal()] == 1) {
                MappingActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.MappingActivity$openCore$1$3$initMappingCoreServiceState$3
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextView status_text = (TextView) MappingActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                        Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                        status_text.setText(description);
                    }
                });
            }
        }
    }
}
