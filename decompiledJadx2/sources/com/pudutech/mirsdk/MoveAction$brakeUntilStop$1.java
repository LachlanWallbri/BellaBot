package com.pudutech.mirsdk;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationResult;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import java.util.concurrent.atomic.AtomicBoolean;
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
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$brakeUntilStop$1", m3970f = "MoveAction.kt", m3971i = {0, 0, 0}, m3972l = {1550}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "machineType", "brakeInterval"}, m3975s = {"L$0", "L$1", "J$0"})
/* loaded from: classes5.dex */
public final class MoveAction$brakeUntilStop$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5525p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$brakeUntilStop$1(MoveAction moveAction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$brakeUntilStop$1 moveAction$brakeUntilStop$1 = new MoveAction$brakeUntilStop$1(this.this$0, completion);
        moveAction$brakeUntilStop$1.f5525p$ = (CoroutineScope) obj;
        return moveAction$brakeUntilStop$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$brakeUntilStop$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RobotHardware robotHardware;
        ProductMachineType productMachineType;
        MachineInfo machineInfo;
        RobotHardware robotHardware2;
        AtomicBoolean atomicBoolean;
        AtomicBoolean atomicBoolean2;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5525p$;
            robotHardware = this.this$0.robotHardware;
            HardwareInterface hardwareInterface = robotHardware.getInterface();
            if (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null || (productMachineType = machineInfo.getProductMachineType()) == null) {
                productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
            }
            long j = MoveAction.WhenMappings.$EnumSwitchMapping$4[productMachineType.getModel().ordinal()] != 1 ? 1000 : 2000;
            C47861 c47861 = new C47861(productMachineType, null);
            this.L$0 = coroutineScope;
            this.L$1 = productMachineType;
            this.J$0 = j;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(j, c47861, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j2 = this.J$0;
            ResultKt.throwOnFailure(obj);
        }
        robotHardware2 = this.this$0.robotHardware;
        HardwareInterface hardwareInterface2 = robotHardware2.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.controlWheel(0.0d, 0.0d, false);
        }
        atomicBoolean = this.this$0.mActionStopFlag;
        atomicBoolean.set(false);
        atomicBoolean2 = this.this$0.mJobBreakFlag;
        atomicBoolean2.set(false);
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "has stopped------>");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MoveAction.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$brakeUntilStop$1$1", m3970f = "MoveAction.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {1571, 1589}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull", SpeechUtility.TAG_RESOURCE_RESULT, "$this$withTimeoutOrNull", "delayCnt", SpeechUtility.TAG_RESOURCE_RESULT}, m3975s = {"L$0", "L$1", "L$0", "I$0", "L$1"})
    /* renamed from: com.pudutech.mirsdk.MoveAction$brakeUntilStop$1$1 */
    /* loaded from: classes5.dex */
    public static final class C47861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ProductMachineType $machineType;
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5526p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C47861(ProductMachineType productMachineType, Continuation continuation) {
            super(2, continuation);
            this.$machineType = productMachineType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C47861 c47861 = new C47861(this.$machineType, completion);
            c47861.f5526p$ = (CoroutineScope) obj;
            return c47861;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C47861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x024a  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0293 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:24:? A[LOOP:0: B:7:0x01fc->B:24:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0265  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            String str;
            RobotStatus robotStatus;
            RobotStatus robotStatus2;
            Object obj2;
            String str2;
            C47861 c47861;
            int i;
            CoroutineScope coroutineScope2;
            RobotStatus robotStatus3;
            String str3;
            RobotStatus robotStatus4;
            RobotStatus robotStatus5;
            AIDLConnection aIDLConnection;
            NavigationResult safelyStop;
            NavigationInterface navigator;
            RobotHardware robotHardware;
            RobotHardware robotHardware2;
            RobotStatus robotStatus6;
            AIDLConnection aIDLConnection2;
            NavigationResult safelyStop2;
            NavigationInterface navigator2;
            RobotHardware robotHardware3;
            RobotHardware robotHardware4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5526p$;
                str = MoveAction$brakeUntilStop$1.this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getName());
                sb.append("]Brake until stop current speed:");
                robotStatus = MoveAction$brakeUntilStop$1.this.this$0.robotStatus;
                sb.append(robotStatus.getSpeed().getValue().getLine());
                sb.append(' ');
                robotStatus2 = MoveAction$brakeUntilStop$1.this.this$0.robotStatus;
                sb.append(robotStatus2.getSpeed().getValue().getAngular());
                Pdlog.m3275i(str, sb.toString());
            } else {
                if (i2 != 1) {
                    if (i2 == 2) {
                        int i3 = this.I$0;
                        coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        obj2 = coroutine_suspended;
                        i = i3;
                        c47861 = this;
                        while (CoroutineScopeKt.isActive(coroutineScope2) && i > 0) {
                            if (c47861.$machineType.getModel() != MachineModel.Hls || c47861.$machineType.getModel() == MachineModel.BellaBot) {
                                aIDLConnection2 = MoveAction$brakeUntilStop$1.this.this$0.coreService;
                                MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection2.getInterface();
                                safelyStop2 = (mirCoreInterface != null || (navigator2 = mirCoreInterface.getNavigator()) == null) ? null : navigator2.safelyStop();
                            } else {
                                safelyStop2 = new NavigationResult(0.0d, 0.0d, 0.0d, null, 12, null);
                            }
                            if (safelyStop2 != null) {
                                robotHardware4 = MoveAction$brakeUntilStop$1.this.this$0.robotHardware;
                                HardwareInterface hardwareInterface = robotHardware4.getInterface();
                                if (hardwareInterface != null) {
                                    hardwareInterface.controlWheel(0.0d, 0.0d, true);
                                }
                            } else {
                                robotHardware3 = MoveAction$brakeUntilStop$1.this.this$0.robotHardware;
                                HardwareInterface hardwareInterface2 = robotHardware3.getInterface();
                                if (hardwareInterface2 != null) {
                                    hardwareInterface2.controlWheel(safelyStop2.getLinear_vel(), safelyStop2.getAngular_vel(), true);
                                }
                            }
                            i--;
                            c47861.L$0 = coroutineScope2;
                            c47861.I$0 = i;
                            c47861.L$1 = safelyStop2;
                            c47861.label = 2;
                            if (DelayKt.delay(20L, c47861) != obj2) {
                                return obj2;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            obj2 = coroutine_suspended;
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                robotStatus3 = MoveAction$brakeUntilStop$1.this.this$0.robotStatus;
                if (Math.abs(robotStatus3.getSpeed().getValue().getLine()) <= 0.05d) {
                    robotStatus6 = MoveAction$brakeUntilStop$1.this.this$0.robotStatus;
                    if (Math.abs(robotStatus6.getSpeed().getValue().getAngular()) <= 0.05d) {
                        break;
                    }
                }
                str3 = MoveAction$brakeUntilStop$1.this.this$0.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Brake until stop, current speed:");
                robotStatus4 = MoveAction$brakeUntilStop$1.this.this$0.robotStatus;
                sb2.append(CommonKt.format(robotStatus4.getSpeed().getValue().getLine(), 2));
                sb2.append(',');
                robotStatus5 = MoveAction$brakeUntilStop$1.this.this$0.robotStatus;
                sb2.append(CommonKt.format(robotStatus5.getSpeed().getValue().getAngular(), 2));
                Pdlog.m3275i(str3, sb2.toString());
                if (this.$machineType.getModel() == MachineModel.Hls || this.$machineType.getModel() == MachineModel.BellaBot) {
                    aIDLConnection = MoveAction$brakeUntilStop$1.this.this$0.coreService;
                    MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection.getInterface();
                    safelyStop = (mirCoreInterface2 == null || (navigator = mirCoreInterface2.getNavigator()) == null) ? null : navigator.safelyStop();
                } else {
                    safelyStop = new NavigationResult(0.0d, 0.0d, 0.0d, null, 12, null);
                }
                if (safelyStop == null) {
                    robotHardware2 = MoveAction$brakeUntilStop$1.this.this$0.robotHardware;
                    HardwareInterface hardwareInterface3 = robotHardware2.getInterface();
                    if (hardwareInterface3 != null) {
                        hardwareInterface3.controlWheel(0.0d, 0.0d, true);
                    }
                } else {
                    robotHardware = MoveAction$brakeUntilStop$1.this.this$0.robotHardware;
                    HardwareInterface hardwareInterface4 = robotHardware.getInterface();
                    if (hardwareInterface4 != null) {
                        hardwareInterface4.controlWheel(safelyStop.getLinear_vel(), safelyStop.getAngular_vel(), true);
                    }
                }
                this.L$0 = coroutineScope;
                this.L$1 = safelyStop;
                this.label = 1;
                if (DelayKt.delay(20L, this) == obj2) {
                    return obj2;
                }
            }
            if (this.$machineType.getModel() == MachineModel.BellaBot) {
                str2 = MoveAction$brakeUntilStop$1.this.this$0.TAG;
                Pdlog.m3273d(str2, "BellaBot delay brake open loop");
                CoroutineScope coroutineScope3 = coroutineScope;
                c47861 = this;
                i = 10;
                coroutineScope2 = coroutineScope3;
                while (CoroutineScopeKt.isActive(coroutineScope2)) {
                    if (c47861.$machineType.getModel() != MachineModel.Hls) {
                    }
                    aIDLConnection2 = MoveAction$brakeUntilStop$1.this.this$0.coreService;
                    MirCoreInterface mirCoreInterface3 = (MirCoreInterface) aIDLConnection2.getInterface();
                    if (mirCoreInterface3 != null) {
                    }
                    if (safelyStop2 != null) {
                    }
                    i--;
                    c47861.L$0 = coroutineScope2;
                    c47861.I$0 = i;
                    c47861.L$1 = safelyStop2;
                    c47861.label = 2;
                    if (DelayKt.delay(20L, c47861) != obj2) {
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }
}
