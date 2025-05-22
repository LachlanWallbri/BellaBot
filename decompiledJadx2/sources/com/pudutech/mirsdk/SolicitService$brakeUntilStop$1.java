package com.pudutech.mirsdk;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationResult;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$brakeUntilStop$1", m3970f = "SolicitService.kt", m3971i = {0}, m3972l = {817}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class SolicitService$brakeUntilStop$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5583p$;
    final /* synthetic */ SolicitService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SolicitService$brakeUntilStop$1(SolicitService solicitService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = solicitService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitService$brakeUntilStop$1 solicitService$brakeUntilStop$1 = new SolicitService$brakeUntilStop$1(this.this$0, completion);
        solicitService$brakeUntilStop$1.f5583p$ = (CoroutineScope) obj;
        return solicitService$brakeUntilStop$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitService$brakeUntilStop$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SolicitService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$brakeUntilStop$1$1", m3970f = "SolicitService.kt", m3971i = {0, 0, 0}, m3972l = {843}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull", "machineType", SpeechUtility.TAG_RESOURCE_RESULT}, m3975s = {"L$0", "L$1", "L$2"})
    /* renamed from: com.pudutech.mirsdk.SolicitService$brakeUntilStop$1$1 */
    /* loaded from: classes4.dex */
    public static final class C48101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5584p$;

        C48101(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48101 c48101 = new C48101(completion);
            c48101.f5584p$ = (CoroutineScope) obj;
            return c48101;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            RobotHardware robotHardware;
            ProductMachineType productMachineType;
            String str;
            RobotStatus robotStatus;
            RobotStatus robotStatus2;
            Object obj2;
            C48101 c48101;
            CoroutineScope coroutineScope;
            ProductMachineType productMachineType2;
            MachineInfo machineInfo;
            RobotStatus robotStatus3;
            String str2;
            RobotStatus robotStatus4;
            RobotStatus robotStatus5;
            NavigationResult navigationResult;
            RobotHardware robotHardware2;
            RobotHardware robotHardware3;
            AIDLConnection aIDLConnection;
            NavigationInterface navigator;
            RobotStatus robotStatus6;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = this.f5584p$;
                robotHardware = SolicitService$brakeUntilStop$1.this.this$0.robotHardware;
                HardwareInterface hardwareInterface = robotHardware.getInterface();
                if (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null || (productMachineType = machineInfo.getProductMachineType()) == null) {
                    productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
                }
                str = SolicitService$brakeUntilStop$1.this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getName());
                sb.append("]Brake until stop current speed:");
                robotStatus = SolicitService$brakeUntilStop$1.this.this$0.robotStatus;
                sb.append(robotStatus.getSpeed().getValue().getLine());
                sb.append(' ');
                robotStatus2 = SolicitService$brakeUntilStop$1.this.this$0.robotStatus;
                sb.append(robotStatus2.getSpeed().getValue().getAngular());
                Pdlog.m3275i(str, sb.toString());
                obj2 = coroutine_suspended;
                c48101 = this;
                ProductMachineType productMachineType3 = productMachineType;
                coroutineScope = coroutineScope2;
                productMachineType2 = productMachineType3;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                productMachineType2 = (ProductMachineType) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                c48101 = this;
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                robotStatus3 = SolicitService$brakeUntilStop$1.this.this$0.robotStatus;
                if (Math.abs(robotStatus3.getSpeed().getValue().getLine()) <= 0.05d) {
                    robotStatus6 = SolicitService$brakeUntilStop$1.this.this$0.robotStatus;
                    if (Math.abs(robotStatus6.getSpeed().getValue().getAngular()) <= 0.05d) {
                        break;
                    }
                }
                str2 = SolicitService$brakeUntilStop$1.this.this$0.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Brake until stop, current speed:");
                robotStatus4 = SolicitService$brakeUntilStop$1.this.this$0.robotStatus;
                sb2.append(CommonKt.format(robotStatus4.getSpeed().getValue().getLine(), 2));
                sb2.append(',');
                robotStatus5 = SolicitService$brakeUntilStop$1.this.this$0.robotStatus;
                sb2.append(CommonKt.format(robotStatus5.getSpeed().getValue().getAngular(), 2));
                Pdlog.m3275i(str2, sb2.toString());
                if (productMachineType2.getModel() == MachineModel.Hls) {
                    aIDLConnection = SolicitService$brakeUntilStop$1.this.this$0.coreService;
                    MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                    navigationResult = (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) ? null : navigator.safelyStop();
                } else {
                    navigationResult = new NavigationResult(0.0d, 0.0d, 0.0d, null, 12, null);
                }
                if (navigationResult == null) {
                    robotHardware3 = SolicitService$brakeUntilStop$1.this.this$0.robotHardware;
                    HardwareInterface hardwareInterface2 = robotHardware3.getInterface();
                    if (hardwareInterface2 != null) {
                        hardwareInterface2.controlWheel(0.0d, 0.0d, true);
                    }
                } else {
                    robotHardware2 = SolicitService$brakeUntilStop$1.this.this$0.robotHardware;
                    HardwareInterface hardwareInterface3 = robotHardware2.getInterface();
                    if (hardwareInterface3 != null) {
                        hardwareInterface3.controlWheel(navigationResult.getLinear_vel(), navigationResult.getAngular_vel(), true);
                    }
                }
                c48101.L$0 = coroutineScope;
                c48101.L$1 = productMachineType2;
                c48101.L$2 = navigationResult;
                c48101.label = 1;
                if (DelayKt.delay(20L, c48101) == obj2) {
                    return obj2;
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RobotHardware robotHardware;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5583p$;
            C48101 c48101 = new C48101(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(1000L, c48101, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        robotHardware = this.this$0.robotHardware;
        HardwareInterface hardwareInterface = robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlWheel(0.0d, 0.0d, false);
        }
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("has stopped------> ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        Pdlog.m3273d(str, sb.toString());
        return Unit.INSTANCE;
    }
}
