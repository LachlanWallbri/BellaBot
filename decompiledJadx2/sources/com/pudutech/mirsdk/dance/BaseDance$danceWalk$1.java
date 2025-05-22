package com.pudutech.mirsdk.dance;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.Dance;
import com.pudutech.mirsdk.aidl.serialize.DanceDirection;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseDance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.dance.BaseDance$danceWalk$1", m3970f = "BaseDance.kt", m3971i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, m3972l = {TypedValues.Attributes.TYPE_PIVOT_TARGET, 344}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "tmpX", "tmpY", "diff", "walkDistance", "$this$runBlocking", "tmpX", "tmpY", "diff", "walkDistance"}, m3975s = {"L$0", "D$0", "D$1", "D$2", "D$3", "L$0", "D$0", "D$1", "D$2", "D$3"})
/* loaded from: classes4.dex */
public final class BaseDance$danceWalk$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Dance $dance;
    final /* synthetic */ Ref.ObjectRef $desPos;
    double D$0;
    double D$1;
    double D$2;
    double D$3;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5774p$;
    final /* synthetic */ BaseDance this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDance$danceWalk$1(BaseDance baseDance, Ref.ObjectRef objectRef, Dance dance, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseDance;
        this.$desPos = objectRef;
        this.$dance = dance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseDance$danceWalk$1 baseDance$danceWalk$1 = new BaseDance$danceWalk$1(this.this$0, this.$desPos, this.$dance, completion);
        baseDance$danceWalk$1.f5774p$ = (CoroutineScope) obj;
        return baseDance$danceWalk$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseDance$danceWalk$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x01aa -> B:6:0x01ab). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        BaseDance$danceWalk$1 baseDance$danceWalk$1;
        int i;
        RobotHardware robotHardware;
        RobotHardware robotHardware2;
        Vector3d vector3d;
        Vector3d vector3d2;
        RobotHardware robotHardware3;
        RobotHardware robotHardware4;
        MoveHelper moveHelper;
        HardwareInterface hardwareInterface;
        RobotHardware robotHardware5;
        HardwareInterface hardwareInterface2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        int i3 = 2;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5774p$;
            obj2 = coroutine_suspended;
            baseDance$danceWalk$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else {
            if (i2 == 1) {
                double d = this.D$3;
                double d2 = this.D$2;
                double d3 = this.D$1;
                double d4 = this.D$0;
                ResultKt.throwOnFailure(obj);
                baseDance$danceWalk$1 = this;
                i = 1;
                if (baseDance$danceWalk$1.this$0.getMLooperIndex() < baseDance$danceWalk$1.this$0.getDanceList().size() - i) {
                }
                String tag = baseDance$danceWalk$1.this$0.getTAG();
                Object[] objArr = new Object[i];
                objArr[0] = "finish walk next mLooperIndex=" + baseDance$danceWalk$1.this$0.getMLooperIndex();
                Pdlog.m3273d(tag, objArr);
                return Unit.INSTANCE;
            }
            if (i2 == 2) {
                double d5 = this.D$3;
                double d6 = this.D$2;
                double d7 = this.D$1;
                double d8 = this.D$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                int i4 = 2;
                baseDance$danceWalk$1 = this;
                i3 = i4;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    if (baseDance$danceWalk$1.this$0.getMDanceStatus() != i3 && baseDance$danceWalk$1.this$0.getMDanceStatus() != 3 && baseDance$danceWalk$1.this$0.getMDanceStatus() != 0) {
                        robotHardware = baseDance$danceWalk$1.this$0.robotHardware;
                        double x = robotHardware.getRobotStatus().getPose().getValue().getX();
                        robotHardware2 = baseDance$danceWalk$1.this$0.robotHardware;
                        double y = robotHardware2.getRobotStatus().getPose().getValue().getY();
                        double sqrt = Math.sqrt(Math.pow(x - ((Vector3d) baseDance$danceWalk$1.$desPos.element).getX(), 2.0d) + Math.pow(y - ((Vector3d) baseDance$danceWalk$1.$desPos.element).getY(), 2.0d));
                        vector3d = baseDance$danceWalk$1.this$0.currentPos;
                        double pow = Math.pow(x - vector3d.getX(), 2.0d);
                        vector3d2 = baseDance$danceWalk$1.this$0.currentPos;
                        Object obj3 = obj2;
                        double sqrt2 = Math.sqrt(pow + Math.pow(y - vector3d2.getY(), 2.0d));
                        String tag2 = baseDance$danceWalk$1.this$0.getTAG();
                        StringBuilder sb = new StringBuilder();
                        sb.append("tmpX=");
                        sb.append(x);
                        sb.append(",tmpY=");
                        sb.append(y);
                        sb.append(",z=");
                        robotHardware3 = baseDance$danceWalk$1.this$0.robotHardware;
                        sb.append(robotHardware3.getRobotStatus().getPose().getValue().getZ());
                        sb.append(",diff=");
                        sb.append(sqrt);
                        sb.append(",speed=");
                        sb.append(baseDance$danceWalk$1.$dance.getSpeed());
                        sb.append(" ,walkDistance=");
                        sb.append(sqrt2);
                        Pdlog.m3273d(tag2, sb.toString());
                        if (sqrt <= 0.05d || sqrt2 >= baseDance$danceWalk$1.$dance.getValue()) {
                            robotHardware4 = baseDance$danceWalk$1.this$0.robotHardware;
                            if (robotHardware4 != null && (hardwareInterface = robotHardware4.getInterface()) != null) {
                                hardwareInterface.controlWheel(0.0d, 0.0d, true);
                            }
                            moveHelper = baseDance$danceWalk$1.this$0.moveHelper;
                            baseDance$danceWalk$1.L$0 = coroutineScope;
                            baseDance$danceWalk$1.D$0 = x;
                            baseDance$danceWalk$1.D$1 = y;
                            baseDance$danceWalk$1.D$2 = sqrt;
                            baseDance$danceWalk$1.D$3 = sqrt2;
                            i = 1;
                            baseDance$danceWalk$1.label = 1;
                            if (moveHelper.robotStop$MirFunction_packRelease(baseDance$danceWalk$1) == obj3) {
                                return obj3;
                            }
                            if (baseDance$danceWalk$1.this$0.getMLooperIndex() < baseDance$danceWalk$1.this$0.getDanceList().size() - i) {
                                baseDance$danceWalk$1.this$0.setMLooperIndex(0);
                            } else {
                                BaseDance baseDance = baseDance$danceWalk$1.this$0;
                                baseDance.setMLooperIndex(baseDance.getMLooperIndex() + i);
                            }
                            String tag3 = baseDance$danceWalk$1.this$0.getTAG();
                            Object[] objArr2 = new Object[i];
                            objArr2[0] = "finish walk next mLooperIndex=" + baseDance$danceWalk$1.this$0.getMLooperIndex();
                            Pdlog.m3273d(tag3, objArr2);
                            return Unit.INSTANCE;
                        }
                        robotHardware5 = baseDance$danceWalk$1.this$0.robotHardware;
                        if (robotHardware5 != null && (hardwareInterface2 = robotHardware5.getInterface()) != null) {
                            hardwareInterface2.controlWheel(baseDance$danceWalk$1.$dance.getDirection() == DanceDirection.BACK ? -baseDance$danceWalk$1.$dance.getSpeed() : baseDance$danceWalk$1.$dance.getSpeed(), 0.0d, true);
                        }
                        baseDance$danceWalk$1.L$0 = coroutineScope;
                        baseDance$danceWalk$1.D$0 = x;
                        baseDance$danceWalk$1.D$1 = y;
                        baseDance$danceWalk$1.D$2 = sqrt;
                        baseDance$danceWalk$1.D$3 = sqrt2;
                        i4 = 2;
                        baseDance$danceWalk$1.label = 2;
                        if (DelayKt.delay(100L, baseDance$danceWalk$1) == obj3) {
                            return obj3;
                        }
                        obj2 = obj3;
                        i3 = i4;
                        if (CoroutineScopeKt.isActive(coroutineScope)) {
                        }
                    } else {
                        Pdlog.m3273d(baseDance$danceWalk$1.this$0.getTAG(), "danceWalk runBlocking request stop " + baseDance$danceWalk$1.this$0.getMDanceStatus());
                        return Unit.INSTANCE;
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
