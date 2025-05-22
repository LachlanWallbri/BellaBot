package com.pudutech.mirsdk.dance;

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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoveHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.MoveHelper$robotStop$2", m3970f = "MoveHelper.kt", m3971i = {0, 0, 0}, m3972l = {384}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull", "machineType", SpeechUtility.TAG_RESOURCE_RESULT}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class MoveHelper$robotStop$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5781p$;
    final /* synthetic */ MoveHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveHelper$robotStop$2(MoveHelper moveHelper, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveHelper$robotStop$2 moveHelper$robotStop$2 = new MoveHelper$robotStop$2(this.this$0, completion);
        moveHelper$robotStop$2.f5781p$ = (CoroutineScope) obj;
        return moveHelper$robotStop$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveHelper$robotStop$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RobotHardware robotHardware;
        ProductMachineType productMachineType;
        String str;
        RobotStatus robotStatus;
        RobotStatus robotStatus2;
        Object obj2;
        MoveHelper$robotStop$2 moveHelper$robotStop$2;
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
            CoroutineScope coroutineScope2 = this.f5781p$;
            robotHardware = this.this$0.robotHardware;
            HardwareInterface hardwareInterface = robotHardware.getInterface();
            if (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null || (productMachineType = machineInfo.getProductMachineType()) == null) {
                productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
            }
            str = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            sb.append(currentThread.getName());
            sb.append("]Brake until stop current speed:");
            robotStatus = this.this$0.robotStatus;
            sb.append(robotStatus.getSpeed().getValue().getLine());
            sb.append(' ');
            robotStatus2 = this.this$0.robotStatus;
            sb.append(robotStatus2.getSpeed().getValue().getAngular());
            Pdlog.m3275i(str, sb.toString());
            obj2 = coroutine_suspended;
            moveHelper$robotStop$2 = this;
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
            moveHelper$robotStop$2 = this;
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            robotStatus3 = moveHelper$robotStop$2.this$0.robotStatus;
            if (Math.abs(robotStatus3.getSpeed().getValue().getLine()) <= 0.05d) {
                robotStatus6 = moveHelper$robotStop$2.this$0.robotStatus;
                if (Math.abs(robotStatus6.getSpeed().getValue().getAngular()) <= 0.05d) {
                    break;
                }
            }
            str2 = moveHelper$robotStop$2.this$0.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Brake until stop, current speed:");
            robotStatus4 = moveHelper$robotStop$2.this$0.robotStatus;
            sb2.append(CommonKt.format(robotStatus4.getSpeed().getValue().getLine(), 2));
            sb2.append(',');
            robotStatus5 = moveHelper$robotStop$2.this$0.robotStatus;
            sb2.append(CommonKt.format(robotStatus5.getSpeed().getValue().getAngular(), 2));
            Pdlog.m3275i(str2, sb2.toString());
            if (productMachineType2.getModel() == MachineModel.Hls) {
                aIDLConnection = moveHelper$robotStop$2.this$0.coreService;
                MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                navigationResult = (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) ? null : navigator.safelyStop();
            } else {
                navigationResult = new NavigationResult(0.0d, 0.0d, 0.0d, null, 12, null);
            }
            if (navigationResult == null) {
                robotHardware3 = moveHelper$robotStop$2.this$0.robotHardware;
                HardwareInterface hardwareInterface2 = robotHardware3.getInterface();
                if (hardwareInterface2 != null) {
                    hardwareInterface2.controlWheel(0.0d, 0.0d, true);
                }
            } else {
                robotHardware2 = moveHelper$robotStop$2.this$0.robotHardware;
                HardwareInterface hardwareInterface3 = robotHardware2.getInterface();
                if (hardwareInterface3 != null) {
                    hardwareInterface3.controlWheel(navigationResult.getLinear_vel(), navigationResult.getAngular_vel(), true);
                }
            }
            moveHelper$robotStop$2.L$0 = coroutineScope;
            moveHelper$robotStop$2.L$1 = productMachineType2;
            moveHelper$robotStop$2.L$2 = navigationResult;
            moveHelper$robotStop$2.label = 1;
            if (DelayKt.delay(20L, moveHelper$robotStop$2) == obj2) {
                return obj2;
            }
        }
        return Unit.INSTANCE;
    }
}
