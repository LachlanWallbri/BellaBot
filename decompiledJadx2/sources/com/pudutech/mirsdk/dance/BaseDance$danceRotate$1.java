package com.pudutech.mirsdk.dance;

import android.os.SystemClock;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.aidl.serialize.Dance;
import com.pudutech.mirsdk.aidl.serialize.DanceDirection;
import com.pudutech.mirsdk.base.Monitorable;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseDance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.BaseDance$danceRotate$1", m3970f = "BaseDance.kt", m3971i = {0, 0, 0, 0}, m3972l = {HttpStatus.SC_GONE}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "startTimer", SpeechUtility.TAG_RESOURCE_RESULT, "lastTimer"}, m3975s = {"L$0", "J$0", "L$1", "J$1"})
/* loaded from: classes4.dex */
public final class BaseDance$danceRotate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.DoubleRef $angle;
    final /* synthetic */ Dance $dance;
    final /* synthetic */ Ref.DoubleRef $direction;
    final /* synthetic */ Ref.DoubleRef $goalDirction;
    final /* synthetic */ Ref.ObjectRef $goalVector;
    final /* synthetic */ Ref.DoubleRef $rotateDanceDirection;
    final /* synthetic */ Ref.LongRef $speedTimer;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5773p$;
    final /* synthetic */ BaseDance this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDance$danceRotate$1(BaseDance baseDance, Ref.DoubleRef doubleRef, Ref.DoubleRef doubleRef2, Ref.ObjectRef objectRef, Dance dance, Ref.DoubleRef doubleRef3, Ref.DoubleRef doubleRef4, Ref.LongRef longRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseDance;
        this.$goalDirction = doubleRef;
        this.$angle = doubleRef2;
        this.$goalVector = objectRef;
        this.$dance = dance;
        this.$rotateDanceDirection = doubleRef3;
        this.$direction = doubleRef4;
        this.$speedTimer = longRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseDance$danceRotate$1 baseDance$danceRotate$1 = new BaseDance$danceRotate$1(this.this$0, this.$goalDirction, this.$angle, this.$goalVector, this.$dance, this.$rotateDanceDirection, this.$direction, this.$speedTimer, completion);
        baseDance$danceRotate$1.f5773p$ = (CoroutineScope) obj;
        return baseDance$danceRotate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseDance$danceRotate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x038e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:75:0x0220 -> B:5:0x023c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:77:0x0225 -> B:5:0x023c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:79:0x0239 -> B:5:0x023c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        BaseDance$danceRotate$1 baseDance$danceRotate$1;
        RobotHardware robotHardware;
        RobotHardware robotHardware2;
        AIDLConnection aIDLConnection;
        RobotHardware robotHardware3;
        HardwareInterface hardwareInterface;
        Double boxDouble;
        Double boxDouble2;
        MirCoreInterface mirCoreInterface;
        NavigationInterface navigator;
        AIDLConnection aIDLConnection2;
        RobotHardware robotHardware4;
        Double boxDouble3;
        RobotStatus robotStatus;
        Monitorable<RobotStatus.RobotSpeed> speed;
        RobotStatus.RobotSpeed value;
        Double boxDouble4;
        MirCoreInterface mirCoreInterface2;
        LocalizationInterface localizer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        int i2 = 2;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5773p$;
            obj2 = coroutine_suspended;
            baseDance$danceRotate$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i == 1) {
            long j = this.J$1;
            RotateResult rotateResult = (RotateResult) this.L$1;
            long j2 = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            RotateResult rotateResult2 = rotateResult;
            obj2 = coroutine_suspended;
            baseDance$danceRotate$1 = this;
            aIDLConnection2 = baseDance$danceRotate$1.this$0.coreService;
            if (aIDLConnection2 != null || (mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface()) == null || (localizer = mirCoreInterface2.getLocalizer()) == null || !localizer.isLocalizationFinishInitialization()) {
                String tag = baseDance$danceRotate$1.this$0.getTAG();
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("angular speed ");
                sb.append((rotateResult2 != null || (boxDouble4 = Boxing.boxDouble(rotateResult2.getAngularspeed())) == null) ? null : CommonKt.format(boxDouble4.doubleValue(), i2));
                sb.append(" has rotate direction ");
                sb.append(baseDance$danceRotate$1.$rotateDanceDirection.element);
                sb.append(" goal direction ");
                sb.append(baseDance$danceRotate$1.$direction.element);
                objArr[0] = sb.toString();
                Pdlog.m3273d(tag, objArr);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Ref.DoubleRef doubleRef = baseDance$danceRotate$1.$rotateDanceDirection;
                double d = doubleRef.element;
                double d2 = (elapsedRealtime - baseDance$danceRotate$1.$speedTimer.element) / 1000.0d;
                robotHardware4 = baseDance$danceRotate$1.this$0.robotHardware;
                boxDouble3 = (robotHardware4 != null || (robotStatus = robotHardware4.getRobotStatus()) == null || (speed = robotStatus.getSpeed()) == null || (value = speed.getValue()) == null) ? null : Boxing.boxDouble(value.getAngular());
                if (boxDouble3 == null) {
                    Intrinsics.throwNpe();
                }
                doubleRef.element = d + (d2 * Math.abs(boxDouble3.doubleValue()));
                baseDance$danceRotate$1.$speedTimer.element = elapsedRealtime;
                if (Math.abs(baseDance$danceRotate$1.$rotateDanceDirection.element - Math.abs(baseDance$danceRotate$1.$direction.element)) <= 0.1d) {
                    if (baseDance$danceRotate$1.this$0.getMLooperIndex() >= baseDance$danceRotate$1.this$0.getDanceList().size() - 1) {
                        baseDance$danceRotate$1.this$0.setMLooperIndex(0);
                    } else {
                        BaseDance baseDance = baseDance$danceRotate$1.this$0;
                        baseDance.setMLooperIndex(baseDance.getMLooperIndex() + 1);
                    }
                    Pdlog.m3273d(baseDance$danceRotate$1.this$0.getTAG(), "not initialization:has finish dancing angle=" + baseDance$danceRotate$1.$angle.element + " next mLooperIndex=" + baseDance$danceRotate$1.this$0.getMLooperIndex());
                    return Unit.INSTANCE;
                }
            }
            i2 = 2;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                if (baseDance$danceRotate$1.this$0.getMDanceStatus() == i2 || baseDance$danceRotate$1.this$0.getMDanceStatus() == 3 || baseDance$danceRotate$1.this$0.getMDanceStatus() == 0) {
                    Pdlog.m3273d(baseDance$danceRotate$1.this$0.getTAG(), "runBlocking request stop " + baseDance$danceRotate$1.this$0.getMDanceStatus());
                    return Unit.INSTANCE;
                }
                String tag2 = baseDance$danceRotate$1.this$0.getTAG();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("dancing rotate ");
                robotHardware = baseDance$danceRotate$1.this$0.robotHardware;
                sb2.append(robotHardware.getRobotStatus().getPose().getValue().getZ());
                Pdlog.m3273d(tag2, sb2.toString());
                robotHardware2 = baseDance$danceRotate$1.this$0.robotHardware;
                if (Math.abs(robotHardware2.getRobotStatus().getPose().getValue().getZ() - baseDance$danceRotate$1.$goalDirction.element) < 0.01d) {
                    if (baseDance$danceRotate$1.this$0.getMLooperIndex() >= baseDance$danceRotate$1.this$0.getDanceList().size() - 1) {
                        baseDance$danceRotate$1.this$0.setMLooperIndex(0);
                    } else {
                        BaseDance baseDance2 = baseDance$danceRotate$1.this$0;
                        baseDance2.setMLooperIndex(baseDance2.getMLooperIndex() + 1);
                    }
                    Pdlog.m3273d(baseDance$danceRotate$1.this$0.getTAG(), "near: has finish dancing angle=" + baseDance$danceRotate$1.$angle.element + " next mLooperIndex=" + baseDance$danceRotate$1.this$0.getMLooperIndex());
                    return Unit.INSTANCE;
                }
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                aIDLConnection = baseDance$danceRotate$1.this$0.coreService;
                if (aIDLConnection == null || (mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface()) == null || (navigator = mirCoreInterface.getNavigator()) == null) {
                    rotateResult2 = null;
                } else {
                    rotateResult2 = navigator.directRotate((Vector3d) baseDance$danceRotate$1.$goalVector.element, baseDance$danceRotate$1.$dance.getDirection() == DanceDirection.RIGHT ? baseDance$danceRotate$1.$dance.getSpeed() : -baseDance$danceRotate$1.$dance.getSpeed());
                }
                String tag3 = baseDance$danceRotate$1.this$0.getTAG();
                Object[] objArr2 = new Object[1];
                StringBuilder sb3 = new StringBuilder();
                sb3.append("rotate result ");
                sb3.append(rotateResult2 != null ? rotateResult2.getStatus() : null);
                objArr2[0] = sb3.toString();
                Pdlog.m3273d(tag3, objArr2);
                if ((rotateResult2 != null ? rotateResult2.getStatus() : null) == RotateResult.RotateStatus.Finish) {
                    if (baseDance$danceRotate$1.this$0.getMLooperIndex() >= baseDance$danceRotate$1.this$0.getDanceList().size() - 1) {
                        baseDance$danceRotate$1.this$0.setMLooperIndex(0);
                    } else {
                        BaseDance baseDance3 = baseDance$danceRotate$1.this$0;
                        baseDance3.setMLooperIndex(baseDance3.getMLooperIndex() + 1);
                    }
                    Pdlog.m3273d(baseDance$danceRotate$1.this$0.getTAG(), "has finish dancing angle=" + baseDance$danceRotate$1.$angle.element + " next mLooperIndex=" + baseDance$danceRotate$1.this$0.getMLooperIndex());
                    return Unit.INSTANCE;
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                robotHardware3 = baseDance$danceRotate$1.this$0.robotHardware;
                if (robotHardware3 != null && (hardwareInterface = robotHardware3.getInterface()) != null) {
                    hardwareInterface.controlWheel((rotateResult2 == null || (boxDouble2 = Boxing.boxDouble(rotateResult2.getLinespeed())) == null) ? 0.0d : boxDouble2.doubleValue(), (rotateResult2 == null || (boxDouble = Boxing.boxDouble(rotateResult2.getAngularspeed())) == null) ? 0.5d : boxDouble.doubleValue(), true);
                }
                long elapsedRealtime3 = SystemClock.elapsedRealtime() - elapsedRealtime2;
                long j3 = 99;
                if (1 <= elapsedRealtime3 && j3 >= elapsedRealtime3) {
                    baseDance$danceRotate$1.L$0 = coroutineScope;
                    baseDance$danceRotate$1.J$0 = elapsedRealtime2;
                    baseDance$danceRotate$1.L$1 = rotateResult2;
                    baseDance$danceRotate$1.J$1 = elapsedRealtime3;
                    baseDance$danceRotate$1.label = 1;
                    if (DelayKt.delay(100 - elapsedRealtime3, baseDance$danceRotate$1) == obj2) {
                        return obj2;
                    }
                }
                aIDLConnection2 = baseDance$danceRotate$1.this$0.coreService;
                if (aIDLConnection2 != null) {
                }
                String tag4 = baseDance$danceRotate$1.this$0.getTAG();
                Object[] objArr3 = new Object[1];
                StringBuilder sb4 = new StringBuilder();
                sb4.append("angular speed ");
                sb4.append((rotateResult2 != null || (boxDouble4 = Boxing.boxDouble(rotateResult2.getAngularspeed())) == null) ? null : CommonKt.format(boxDouble4.doubleValue(), i2));
                sb4.append(" has rotate direction ");
                sb4.append(baseDance$danceRotate$1.$rotateDanceDirection.element);
                sb4.append(" goal direction ");
                sb4.append(baseDance$danceRotate$1.$direction.element);
                objArr3[0] = sb4.toString();
                Pdlog.m3273d(tag4, objArr3);
                long elapsedRealtime4 = SystemClock.elapsedRealtime();
                Ref.DoubleRef doubleRef2 = baseDance$danceRotate$1.$rotateDanceDirection;
                double d3 = doubleRef2.element;
                double d22 = (elapsedRealtime4 - baseDance$danceRotate$1.$speedTimer.element) / 1000.0d;
                robotHardware4 = baseDance$danceRotate$1.this$0.robotHardware;
                if (robotHardware4 != null) {
                }
                if (boxDouble3 == null) {
                }
                doubleRef2.element = d3 + (d22 * Math.abs(boxDouble3.doubleValue()));
                baseDance$danceRotate$1.$speedTimer.element = elapsedRealtime4;
                if (Math.abs(baseDance$danceRotate$1.$rotateDanceDirection.element - Math.abs(baseDance$danceRotate$1.$direction.element)) <= 0.1d) {
                }
                i2 = 2;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
