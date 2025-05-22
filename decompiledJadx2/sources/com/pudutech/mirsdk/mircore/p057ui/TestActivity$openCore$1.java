package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.p057ui.TestActivity;
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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1", m3970f = "TestActivity.kt", m3971i = {0, 0}, m3972l = {131}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "state"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
final class TestActivity$openCore$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6288p$;
    final /* synthetic */ TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TestActivity$openCore$1(TestActivity testActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = testActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TestActivity$openCore$1 testActivity$openCore$1 = new TestActivity$openCore$1(this.this$0, completion);
        testActivity$openCore$1.f6288p$ = (CoroutineScope) obj;
        return testActivity$openCore$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestActivity$openCore$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        HardwareInterface hardwareInterface;
        HardwareInterface hardwareInterface2;
        AIDLConnection aIDLConnection3;
        MirCoreInterface mirCoreInterface;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6288p$;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (HardwareOpenStep) 0;
            aIDLConnection = this.this$0.hardwareService;
            if (aIDLConnection != null && (hardwareInterface2 = (HardwareInterface) aIDLConnection.getInterface()) != null) {
                hardwareInterface2.addListener("testCore", new BinderC52671(objectRef));
            }
            aIDLConnection2 = this.this$0.hardwareService;
            if (aIDLConnection2 != null && (hardwareInterface = (HardwareInterface) aIDLConnection2.getInterface()) != null) {
                hardwareInterface.open();
            }
            C52682 c52682 = new C52682(objectRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = objectRef;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(30000L, c52682, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        aIDLConnection3 = this.this$0.coreService;
        if (aIDLConnection3 != null && (mirCoreInterface = (MirCoreInterface) aIDLConnection3.getInterface()) != null) {
            str = this.this$0.mapFile;
            mirCoreInterface.initModules(0, str, null, new BinderC52693());
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: TestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u001a\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0012\u0010\u0016\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J&\u0010\u0018\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00192\b\u0010\u0006\u001a\u0004\u0018\u00010\u001a2\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J1\u0010\u001c\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001e\u0018\u00010\u001d2\u0010\u0010\u0006\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001e\u0018\u00010\u001dH\u0016¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\""}, m3961d2 = {"com/pudutech/mirsdk/mircore/ui/TestActivity$openCore$1$1", "Lcom/pudutech/mirsdk/hardware/IHardware$Stub;", "geomagneticAntiDrop", "", "p0", "", "p1", "", "p2", "p3", "onAntiCollisionSwitch", "", "onBattery", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatteryFloorLevelLimit", "onBatterySOH", "onBumperSwitchStatus", "onCameraIRDLED", "onChargingFail", "onConnectedChargingPile", "onDisinfectionPower", "onHarewareEmergencyBrake", "onHarewareInfoReport", "onLockMotorStatus", "onOpenStep", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "onSlamCorePower", "onWheelError", "", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "([Lcom/pudutech/mirsdk/hardware/serialize/WheelError;[Lcom/pudutech/mirsdk/hardware/serialize/WheelError;)V", "securityFeedback", "sensormagnetic", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$1 */
    /* loaded from: classes6.dex */
    public static final class BinderC52671 extends IHardware.Stub {
        final /* synthetic */ Ref.ObjectRef $state;

        public void geomagneticAntiDrop(String p0, int p1, int p2, int p3) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onAntiCollisionSwitch(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBattery(int p0, ChargeState p1) {
        }

        public void onBatteryFloorLevelLimit(int p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBatterySOH(int p0) {
        }

        public void onBumperSwitchStatus(boolean p0) {
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

        public void onLockMotorStatus(boolean p0) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onSlamCorePower(boolean p0) {
        }

        public void securityFeedback(boolean p0) {
        }

        public void sensormagnetic(int p0) {
        }

        BinderC52671(Ref.ObjectRef objectRef) {
            this.$state = objectRef;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onOpenStep(final HardwareOpenStep p0, final StepState p1, String p2) {
            String str;
            str = TestActivity$openCore$1.this.this$0.TAG;
            Pdlog.m3273d(str, "step " + p0 + " state " + p1 + " description " + p2);
            this.$state.element = p0;
            TestActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$1$onOpenStep$1
                @Override // java.lang.Runnable
                public final void run() {
                    TextView status_text = (TextView) TestActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
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
            TestActivity$openCore$1.this.this$0.wheelError = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: TestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$2", m3970f = "TestActivity.kt", m3971i = {0}, m3972l = {133}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$2 */
    /* loaded from: classes6.dex */
    public static final class C52682 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $state;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6289p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C52682(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$state = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52682 c52682 = new C52682(this.$state, completion);
            c52682.f6289p$ = (CoroutineScope) obj;
            return c52682;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52682) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f6289p$;
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

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: TestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/mircore/ui/TestActivity$openCore$1$3", "Lcom/pudutech/mirsdk/mircore/InitServiceListener$Stub;", "initCoreServiceState", "", "step", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitStep;", "state", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "description", "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$3 */
    /* loaded from: classes6.dex */
    public static final class BinderC52693 extends InitServiceListener.Stub {
        BinderC52693() {
        }

        @Override // com.pudutech.mirsdk.mircore.InitServiceListener
        public void initCoreServiceState(CoreInitStep step, CoreInitState state, final String description) {
            String str;
            String str2;
            String str3;
            str = TestActivity$openCore$1.this.this$0.TAG;
            Pdlog.m3273d(str, "core init step " + step + " state " + state + " description " + description);
            if (step != null && TestActivity.WhenMappings.$EnumSwitchMapping$2[step.ordinal()] == 1) {
                if (state != null && TestActivity.WhenMappings.$EnumSwitchMapping$0[state.ordinal()] == 1) {
                    str3 = TestActivity$openCore$1.this.this$0.TAG;
                    Pdlog.m3273d(str3, "CoreInitState Succeed");
                    TestActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$3$initCoreServiceState$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            TextView status_text = (TextView) TestActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                            status_text.setText("Success");
                        }
                    });
                    return;
                } else {
                    str2 = TestActivity$openCore$1.this.this$0.TAG;
                    Pdlog.m3273d(str2, "CoreInitState Failed");
                    TestActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$3$initCoreServiceState$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            TextView status_text = (TextView) TestActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                            status_text.setText("Failed");
                        }
                    });
                    return;
                }
            }
            if (state != null && TestActivity.WhenMappings.$EnumSwitchMapping$1[state.ordinal()] == 1) {
                TestActivity$openCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$openCore$1$3$initCoreServiceState$3
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextView status_text = (TextView) TestActivity$openCore$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                        Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                        status_text.setText(description);
                    }
                });
            }
        }
    }
}
