package com.pudutech.mirsdk;

import android.os.SystemClock;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.coreparcel.RotateResult;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$rotateTask$1", m3970f = "MoveAction.kt", m3971i = {0, 0, 0, 0, 0, 0}, m3972l = {1644}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "rotateDirection", "speedTimer", "startTimer", SpeechUtility.TAG_RESOURCE_RESULT, "lastTimer"}, m3975s = {"L$0", "D$0", "J$0", "J$1", "L$1", "J$2"})
/* loaded from: classes5.dex */
public final class MoveAction$rotateTask$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $direction;
    final /* synthetic */ double $goalDirction;
    final /* synthetic */ Vector3d $goalVector;
    double D$0;
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5553p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$rotateTask$1(MoveAction moveAction, double d, Vector3d vector3d, double d2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
        this.$goalDirction = d;
        this.$goalVector = vector3d;
        this.$direction = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$rotateTask$1 moveAction$rotateTask$1 = new MoveAction$rotateTask$1(this.this$0, this.$goalDirction, this.$goalVector, this.$direction, completion);
        moveAction$rotateTask$1.f5553p$ = (CoroutineScope) obj;
        return moveAction$rotateTask$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$rotateTask$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x01b4 -> B:5:0x01d5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x01b9 -> B:5:0x01d5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x01d2 -> B:5:0x01d5). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        String str;
        String str2;
        long elapsedRealtime;
        CoroutineScope coroutineScope;
        double d;
        Object obj2;
        MoveAction$rotateTask$1 moveAction$rotateTask$1;
        String str3;
        RobotStatus robotStatus;
        RobotStatus robotStatus2;
        AIDLConnection aIDLConnection2;
        String str4;
        RobotHardware robotHardware;
        Double boxDouble;
        Double boxDouble2;
        String str5;
        RobotStatus robotStatus3;
        NavigationInterface navigator;
        AIDLConnection aIDLConnection3;
        int i;
        String str6;
        RobotHardware robotHardware2;
        Double boxDouble3;
        LocalizationInterface localizer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        char c = 0;
        int i3 = 1;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5553p$;
            aIDLConnection = this.this$0.coreService;
            MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
            if (mirCoreInterface == null || !mirCoreInterface.hasCoreReady()) {
                str = this.this$0.TAG;
                Pdlog.m3277w(str, "core not ready to run task");
                this.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"CoreNotReady\", \"level\":\"Error\", \"detail\":\"Core not ready, please check whether costmap updated by laser, or schedule or topomap\"}");
                str2 = this.this$0.TAG;
                Pdlog.m3277w(str2, "navigation quit");
                return Unit.INSTANCE;
            }
            elapsedRealtime = SystemClock.elapsedRealtime();
            coroutineScope = coroutineScope2;
            d = 0.0d;
            obj2 = coroutine_suspended;
            moveAction$rotateTask$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            long j = this.J$2;
            RotateResult rotateResult = (RotateResult) this.L$1;
            long j2 = this.J$1;
            elapsedRealtime = this.J$0;
            d = this.D$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            RotateResult rotate = rotateResult;
            obj2 = coroutine_suspended;
            moveAction$rotateTask$1 = this;
            aIDLConnection3 = moveAction$rotateTask$1.this$0.coreService;
            MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection3.getInterface();
            if (mirCoreInterface2 != null || (localizer = mirCoreInterface2.getLocalizer()) == null) {
                i = 1;
            } else {
                i = 1;
                if (localizer.isLocalizationFinishInitialization()) {
                    c = 0;
                    i3 = 1;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        str3 = moveAction$rotateTask$1.this$0.TAG;
                        Object[] objArr = new Object[i3];
                        StringBuilder sb = new StringBuilder();
                        sb.append("rotate to goal has least ");
                        robotStatus = moveAction$rotateTask$1.this$0.robotStatus;
                        sb.append(Math.abs(robotStatus.getPose().getValue().getZ() - moveAction$rotateTask$1.$goalDirction));
                        objArr[c] = sb.toString();
                        Pdlog.m3273d(str3, objArr);
                        robotStatus2 = moveAction$rotateTask$1.this$0.robotStatus;
                        if (Math.abs(robotStatus2.getPose().getValue().getZ() - moveAction$rotateTask$1.$goalDirction) < 0.01d) {
                            moveAction$rotateTask$1.this$0.brakeUntilStop();
                        } else {
                            long elapsedRealtime2 = SystemClock.elapsedRealtime();
                            aIDLConnection2 = moveAction$rotateTask$1.this$0.coreService;
                            MirCoreInterface mirCoreInterface3 = (MirCoreInterface) aIDLConnection2.getInterface();
                            rotate = (mirCoreInterface3 == null || (navigator = mirCoreInterface3.getNavigator()) == null) ? null : navigator.rotate(moveAction$rotateTask$1.$goalVector);
                            str4 = moveAction$rotateTask$1.this$0.TAG;
                            Object[] objArr2 = new Object[i3];
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("rotate result ");
                            sb2.append(rotate != null ? rotate.getStatus() : null);
                            objArr2[c] = sb2.toString();
                            Pdlog.m3273d(str4, objArr2);
                            if ((rotate != null ? rotate.getStatus() : null) == RotateResult.RotateStatus.Finish) {
                                str5 = moveAction$rotateTask$1.this$0.TAG;
                                Object[] objArr3 = new Object[1];
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("rotate to ");
                                sb3.append(CommonKt.format(moveAction$rotateTask$1.$goalDirction, 2));
                                sb3.append(" rad, current ");
                                robotStatus3 = moveAction$rotateTask$1.this$0.robotStatus;
                                sb3.append(CommonKt.format(robotStatus3.getPose().getValue().getZ(), 2));
                                sb3.append(" rad");
                                objArr3[c] = sb3.toString();
                                Pdlog.m3273d(str5, objArr3);
                                moveAction$rotateTask$1.this$0.brakeUntilStop();
                            } else if (CoroutineScopeKt.isActive(coroutineScope)) {
                                robotHardware = moveAction$rotateTask$1.this$0.robotHardware;
                                HardwareInterface hardwareInterface = robotHardware.getInterface();
                                if (hardwareInterface != null) {
                                    hardwareInterface.controlWheel((rotate == null || (boxDouble2 = Boxing.boxDouble(rotate.getLinespeed())) == null) ? 0.0d : boxDouble2.doubleValue(), (rotate == null || (boxDouble = Boxing.boxDouble(rotate.getAngularspeed())) == null) ? 0.5d : boxDouble.doubleValue(), true);
                                }
                                long elapsedRealtime3 = SystemClock.elapsedRealtime() - elapsedRealtime2;
                                long j3 = 99;
                                if (1 <= elapsedRealtime3 && j3 >= elapsedRealtime3) {
                                    moveAction$rotateTask$1.L$0 = coroutineScope;
                                    moveAction$rotateTask$1.D$0 = d;
                                    moveAction$rotateTask$1.J$0 = elapsedRealtime;
                                    moveAction$rotateTask$1.J$1 = elapsedRealtime2;
                                    moveAction$rotateTask$1.L$1 = rotate;
                                    moveAction$rotateTask$1.J$2 = elapsedRealtime3;
                                    moveAction$rotateTask$1.label = 1;
                                    if (DelayKt.delay(100 - elapsedRealtime3, moveAction$rotateTask$1) == obj2) {
                                        return obj2;
                                    }
                                }
                                aIDLConnection3 = moveAction$rotateTask$1.this$0.coreService;
                                MirCoreInterface mirCoreInterface22 = (MirCoreInterface) aIDLConnection3.getInterface();
                                if (mirCoreInterface22 != null) {
                                }
                                i = 1;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            }
            str6 = moveAction$rotateTask$1.this$0.TAG;
            Object[] objArr4 = new Object[i];
            StringBuilder sb4 = new StringBuilder();
            sb4.append("angular speed ");
            sb4.append((rotate == null || (boxDouble3 = Boxing.boxDouble(rotate.getAngularspeed())) == null) ? null : CommonKt.format(boxDouble3.doubleValue(), 2));
            sb4.append(" has rotate direction ");
            sb4.append(d);
            sb4.append(" goal direction ");
            sb4.append(moveAction$rotateTask$1.$direction);
            objArr4[0] = sb4.toString();
            Pdlog.m3273d(str6, objArr4);
            long elapsedRealtime4 = SystemClock.elapsedRealtime();
            robotHardware2 = moveAction$rotateTask$1.this$0.robotHardware;
            d += ((elapsedRealtime4 - elapsedRealtime) / 1000.0d) * Math.abs(robotHardware2.getRobotStatus().getSpeed().getValue().getAngular());
            if (Math.abs(d - Math.abs(moveAction$rotateTask$1.$direction)) <= 0.1d) {
                moveAction$rotateTask$1.this$0.brakeUntilStop();
                return Unit.INSTANCE;
            }
            elapsedRealtime = elapsedRealtime4;
            c = 0;
            i3 = 1;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
