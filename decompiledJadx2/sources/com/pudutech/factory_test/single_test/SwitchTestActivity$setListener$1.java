package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: SwitchTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0010H\u0016J&\u0010\u0011\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00122\b\u0010\b\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J1\u0010\u0016\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0018\u0018\u00010\u00172\u0010\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0018\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, m3961d2 = {"com/pudutech/factory_test/single_test/SwitchTestActivity$setListener$1", "Lcom/pudutech/mirsdk/hardware/IHardware$Stub;", "onAntiCollisionSwitch", "", "p0", "", "onBattery", "", "p1", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatterySOH", "onCameraIRDLED", "onConnectedChargingPile", "onDisinfectionPower", "onHarewareEmergencyBrake", "onHarewareInfoReport", "", "onOpenStep", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "p2", "onSlamCorePower", "onWheelError", "", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "([Lcom/pudutech/mirsdk/hardware/serialize/WheelError;[Lcom/pudutech/mirsdk/hardware/serialize/WheelError;)V", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SwitchTestActivity$setListener$1 extends IHardware.Stub {
    final /* synthetic */ SwitchTestActivity this$0;

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBattery(int p0, ChargeState p1) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBatterySOH(int p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onCameraIRDLED(boolean p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onConnectedChargingPile() {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onDisinfectionPower(boolean p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareInfoReport(String p0) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onOpenStep(HardwareOpenStep p0, StepState p1, String p2) {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onSlamCorePower(boolean p0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SwitchTestActivity$setListener$1(SwitchTestActivity switchTestActivity) {
        this.this$0 = switchTestActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareEmergencyBrake(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onHarewareEmergencyBrake p0=" + p0);
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onAntiCollisionSwitch(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onAntiCollisionSwitch p0=" + p0);
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onWheelError(WheelError[] p0, WheelError[] p1) {
        String str;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onWheelError p0=");
        sb.append(p0 != null ? ArraysKt.toList(p0) : null);
        sb.append("  p1=");
        sb.append(p1 != null ? ArraysKt.toList(p1) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if ((p0 != null && ArraysKt.contains(p0, WheelError.EmergencyKeyPressed)) || (p1 != null && ArraysKt.contains(p1, WheelError.EmergencyKeyPressed))) {
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.SwitchTestActivity$setListener$1$onWheelError$1
                @Override // java.lang.Runnable
                public final void run() {
                    Job launch$default;
                    Job delayJob = SwitchTestActivity$setListener$1.this.this$0.getDelayJob();
                    if (delayJob != null) {
                        Job.DefaultImpls.cancel$default(delayJob, (CancellationException) null, 1, (Object) null);
                    }
                    Function1<Boolean, Unit> onEmergency = SwitchTestActivity$setListener$1.this.this$0.getOnEmergency();
                    if (onEmergency != null) {
                        onEmergency.invoke(true);
                    }
                    SwitchTestActivity switchTestActivity = SwitchTestActivity$setListener$1.this.this$0;
                    launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C45261(null), 2, null);
                    switchTestActivity.setDelayJob(launch$default);
                }

                /* compiled from: SwitchTestActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.SwitchTestActivity$setListener$1$onWheelError$1$1", m3970f = "SwitchTestActivity.kt", m3971i = {0}, m3972l = {210}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
                /* renamed from: com.pudutech.factory_test.single_test.SwitchTestActivity$setListener$1$onWheelError$1$1 */
                /* loaded from: classes.dex */
                static final class C45261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f5199p$;

                    C45261(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C45261 c45261 = new C45261(completion);
                        c45261.f5199p$ = (CoroutineScope) obj;
                        return c45261;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C45261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.L$0 = this.f5199p$;
                            this.label = 1;
                            if (DelayKt.delay(500L, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        Function1<Boolean, Unit> onEmergency = SwitchTestActivity$setListener$1.this.this$0.getOnEmergency();
                        if (onEmergency != null) {
                            onEmergency.invoke(Boxing.boxBoolean(false));
                        }
                        return Unit.INSTANCE;
                    }
                }
            });
        }
        if ((p0 == null || !ArraysKt.contains(p0, WheelError.BumpSwitchReset)) && (p1 == null || !ArraysKt.contains(p1, WheelError.BumpSwitchReset))) {
            return;
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.SwitchTestActivity$setListener$1$onWheelError$2
            @Override // java.lang.Runnable
            public final void run() {
                Function1<Boolean, Unit> onCollision = SwitchTestActivity$setListener$1.this.this$0.getOnCollision();
                if (onCollision != null) {
                    onCollision.invoke(true);
                }
            }
        });
    }
}
