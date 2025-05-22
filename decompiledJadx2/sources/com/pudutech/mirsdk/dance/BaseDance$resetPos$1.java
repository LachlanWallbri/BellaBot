package com.pudutech.mirsdk.dance;

import android.os.SystemClock;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.coreparcel.RotateResult;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
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
/* compiled from: BaseDance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.BaseDance$resetPos$1", m3970f = "BaseDance.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0}, m3972l = {182}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "goalDirction", "goalVector", "angleDiff", "angleSpeed", "startTimer", SpeechUtility.TAG_RESOURCE_RESULT, "lastTimer"}, m3975s = {"L$0", "D$0", "L$1", "D$1", "D$2", "J$0", "L$2", "J$1"})
/* loaded from: classes4.dex */
public final class BaseDance$resetPos$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    double D$0;
    double D$1;
    double D$2;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5777p$;
    final /* synthetic */ BaseDance this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDance$resetPos$1(BaseDance baseDance, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseDance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseDance$resetPos$1 baseDance$resetPos$1 = new BaseDance$resetPos$1(this.this$0, completion);
        baseDance$resetPos$1.f5777p$ = (CoroutineScope) obj;
        return baseDance$resetPos$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseDance$resetPos$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0212, code lost:
    
        r4 = r17;
        r5 = r18;
        r7 = r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x021a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x0201 -> B:5:0x0206). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        RobotHardware robotHardware;
        double calAngularDistance;
        RobotHardware robotHardware2;
        Object obj2;
        CoroutineScope coroutineScope;
        double d;
        Vector3d vector3d;
        double d2;
        BaseDance$resetPos$1 baseDance$resetPos$1;
        RobotHardware robotHardware3;
        AIDLConnection aIDLConnection;
        RobotHardware robotHardware4;
        RobotHardware robotHardware5;
        HardwareInterface hardwareInterface;
        Double boxDouble;
        Double boxDouble2;
        MirCoreInterface mirCoreInterface;
        NavigationInterface navigator;
        AIDLConnection aIDLConnection2;
        int i;
        MirCoreInterface mirCoreInterface2;
        LocalizationInterface localizer;
        BaseDance$resetPos$1 baseDance$resetPos$12 = this;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = baseDance$resetPos$12.label;
        if (i2 != 0) {
            if (i2 == 1) {
                long j = baseDance$resetPos$12.J$1;
                long j2 = baseDance$resetPos$12.J$0;
                d = baseDance$resetPos$12.D$2;
                calAngularDistance = baseDance$resetPos$12.D$1;
                vector3d = (Vector3d) baseDance$resetPos$12.L$1;
                d2 = baseDance$resetPos$12.D$0;
                coroutineScope = (CoroutineScope) baseDance$resetPos$12.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                baseDance$resetPos$1 = baseDance$resetPos$12;
                aIDLConnection2 = baseDance$resetPos$1.this$0.coreService;
                if (aIDLConnection2 != null || (mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface()) == null || (localizer = mirCoreInterface2.getLocalizer()) == null) {
                    i = 1;
                } else {
                    i = 1;
                    if (localizer.isLocalizationFinishInitialization()) {
                        if (!CoroutineScopeKt.isActive(coroutineScope)) {
                            robotHardware3 = baseDance$resetPos$1.this$0.robotHardware;
                            if (Math.abs(robotHardware3.getRobotStatus().getPose().getValue().getZ() - d2) < 0.01d) {
                                baseDance$resetPos$1.this$0.stopRobot();
                                return Unit.INSTANCE;
                            }
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            aIDLConnection = baseDance$resetPos$1.this$0.coreService;
                            RotateResult directRotate = (aIDLConnection == null || (mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface()) == null || (navigator = mirCoreInterface.getNavigator()) == null) ? null : navigator.directRotate(vector3d, d);
                            String tag = baseDance$resetPos$1.this$0.getTAG();
                            Object[] objArr = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            Object obj3 = obj2;
                            sb.append("rotate result ");
                            sb.append(directRotate != null ? directRotate.getStatus() : null);
                            sb.append("  ");
                            robotHardware4 = baseDance$resetPos$1.this$0.robotHardware;
                            double d3 = d;
                            sb.append(robotHardware4.getRobotStatus().getPose().getValue().getZ());
                            objArr[0] = sb.toString();
                            Pdlog.m3273d(tag, objArr);
                            if ((directRotate != null ? directRotate.getStatus() : null) == RotateResult.RotateStatus.Finish) {
                                baseDance$resetPos$1.this$0.stopRobot();
                                return Unit.INSTANCE;
                            }
                            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                                return Unit.INSTANCE;
                            }
                            robotHardware5 = baseDance$resetPos$1.this$0.robotHardware;
                            if (robotHardware5 != null && (hardwareInterface = robotHardware5.getInterface()) != null) {
                                hardwareInterface.controlWheel((directRotate == null || (boxDouble2 = Boxing.boxDouble(directRotate.getLinespeed())) == null) ? 0.0d : boxDouble2.doubleValue(), (directRotate == null || (boxDouble = Boxing.boxDouble(directRotate.getAngularspeed())) == null) ? 0.5d : boxDouble.doubleValue(), true);
                            }
                            long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
                            long j3 = 99;
                            if (1 > elapsedRealtime2 || j3 < elapsedRealtime2) {
                                obj2 = obj3;
                                d = d3;
                                calAngularDistance = calAngularDistance;
                                aIDLConnection2 = baseDance$resetPos$1.this$0.coreService;
                                if (aIDLConnection2 != null) {
                                }
                                i = 1;
                            } else {
                                baseDance$resetPos$1.L$0 = coroutineScope;
                                baseDance$resetPos$1.D$0 = d2;
                                baseDance$resetPos$1.L$1 = vector3d;
                                baseDance$resetPos$1.D$1 = calAngularDistance;
                                double d4 = calAngularDistance;
                                baseDance$resetPos$1.D$2 = d3;
                                baseDance$resetPos$1.J$0 = elapsedRealtime;
                                baseDance$resetPos$1.L$2 = directRotate;
                                baseDance$resetPos$1.J$1 = elapsedRealtime2;
                                baseDance$resetPos$1.label = 1;
                                if (DelayKt.delay(100 - elapsedRealtime2, baseDance$resetPos$1) == obj3) {
                                    return obj3;
                                }
                                baseDance$resetPos$12 = baseDance$resetPos$1;
                                obj2 = obj3;
                                d = d3;
                                calAngularDistance = d4;
                                baseDance$resetPos$1 = baseDance$resetPos$12;
                                aIDLConnection2 = baseDance$resetPos$1.this$0.coreService;
                                if (aIDLConnection2 != null) {
                                }
                                i = 1;
                            }
                        } else {
                            return Unit.INSTANCE;
                        }
                    }
                }
                String tag2 = baseDance$resetPos$1.this$0.getTAG();
                Object[] objArr2 = new Object[i];
                objArr2[0] = "not  Localization not Finish Initialization";
                Pdlog.m3273d(tag2, objArr2);
                baseDance$resetPos$1.this$0.stopRobot();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope2 = baseDance$resetPos$12.f5777p$;
        double z = baseDance$resetPos$12.this$0.getMVector3d().getZ();
        Vector3d vector3d2 = new Vector3d(Math.cos(z), Math.sin(z), 0.0d);
        BaseDance baseDance = baseDance$resetPos$12.this$0;
        robotHardware = baseDance.robotHardware;
        calAngularDistance = baseDance.calAngularDistance(robotHardware.getRobotStatus().getPose().getValue().getZ(), z);
        double d5 = calAngularDistance < ((double) 0) ? -1.2d : 1.2d;
        String tag3 = baseDance$resetPos$12.this$0.getTAG();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("goalDirction=");
        sb2.append(z);
        sb2.append(" ,current pos theta=");
        robotHardware2 = baseDance$resetPos$12.this$0.robotHardware;
        sb2.append(robotHardware2.getRobotStatus().getPose().getValue().getZ());
        sb2.append(",angleDiff=");
        sb2.append(calAngularDistance);
        Pdlog.m3273d(tag3, sb2.toString());
        obj2 = coroutine_suspended;
        coroutineScope = coroutineScope2;
        d = d5;
        vector3d = vector3d2;
        d2 = z;
        baseDance$resetPos$1 = baseDance$resetPos$12;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
