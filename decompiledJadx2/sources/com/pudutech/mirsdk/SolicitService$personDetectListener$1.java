package com.pudutech.mirsdk;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.aidl.ISolicitListener;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.PersonListener;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/mirsdk/SolicitService$personDetectListener$1", "Lcom/pudutech/mirsdk/mircore/PersonListener$Stub;", "onDetection", "", SpeechUtility.TAG_RESOURCE_RESULT, "", "id", "degree", "", "distance", "direction", "vx", "vy", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SolicitService$personDetectListener$1 extends PersonListener.Stub {
    final /* synthetic */ SolicitService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SolicitService$personDetectListener$1(SolicitService solicitService) {
        this.this$0 = solicitService;
    }

    @Override // com.pudutech.mirsdk.mircore.PersonListener
    public void onDetection(final int result, int id, final double degree, final double distance, double direction, double vx, double vy) {
        String str;
        int i;
        int i2;
        RobotHardware robotHardware;
        String str2;
        ThreadSafeListener threadSafeListener;
        Vector3d vector3d;
        double extraAngleSpeed;
        String str3;
        byte b;
        int i3;
        byte b2;
        String str4;
        SolicitService.Companion unused;
        SolicitService.Companion unused2;
        SolicitService.Companion unused3;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "person detect result=" + result + ",degree=" + degree + ",distance" + distance);
        if (result == 0) {
            SolicitService solicitService = this.this$0;
            i = solicitService.personDetectTime;
            solicitService.personDetectTime = i + 1;
            i2 = this.this$0.personDetectTime;
            unused3 = SolicitService.INSTANCE;
            if (i2 >= 30) {
                str2 = this.this$0.TAG;
                Pdlog.m3273d(str2, "startRote personDetectTime > 30 add solicitDegree");
                SolicitService solicitService2 = this.this$0;
                solicitService2.rotateDegree(solicitService2.getStartPose().getZ(), 0.0d);
            } else {
                robotHardware = this.this$0.robotHardware;
                HardwareInterface hardwareInterface = robotHardware.getInterface();
                if (hardwareInterface != null) {
                    hardwareInterface.controlWheel(0.0d, 0.0d, true);
                }
            }
        } else if (result == 1) {
            vector3d = this.this$0.robotPose;
            double z = vector3d.getZ() + degree;
            this.this$0.personDetectTime = 0;
            extraAngleSpeed = this.this$0.getExtraAngleSpeed(degree);
            this.this$0.rotateDegree(z, extraAngleSpeed);
            double d = (180 * degree) / 3.141592653589793d;
            str3 = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("angle = ");
            sb.append(Math.abs(d));
            sb.append(",solicitState = ");
            b = this.this$0.solicitState;
            sb.append((int) b);
            sb.append("，faceDetectCount=");
            i3 = this.this$0.faceDetectCount;
            sb.append(i3);
            Pdlog.m3273d(str3, sb.toString());
            if (Math.abs(d) <= 3.0f) {
                unused = SolicitService.INSTANCE;
                if (distance < 1.2d) {
                    b2 = this.this$0.solicitState;
                    if (b2 != 1) {
                        str4 = this.this$0.TAG;
                        Pdlog.m3273d(str4, "solicitState not correct please check");
                    } else {
                        SolicitService solicitService3 = this.this$0;
                        unused2 = SolicitService.INSTANCE;
                        solicitService3.solicitState = (byte) 2;
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new SolicitService$personDetectListener$1$onDetection$1(this, null), 3, null);
                    }
                }
            }
        }
        threadSafeListener = this.this$0.solicitListeners;
        threadSafeListener.notify(new Function2<ISolicitListener, String, Unit>() { // from class: com.pudutech.mirsdk.SolicitService$personDetectListener$1$onDetection$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISolicitListener iSolicitListener, String str5) {
                invoke2(iSolicitListener, str5);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISolicitListener it, String str5) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                it.onPersonDetection(result, degree, distance, 0);
            }
        });
    }
}
