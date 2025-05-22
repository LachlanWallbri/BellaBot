package com.pudutech.mirsdk;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.CliffDistanceStateListener;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationResultListener;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationResult;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationStatus;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/mirsdk/MoveAction$navigationTask$1", "Lcom/pudutech/mirsdk/mircore/NavigationResultListener$Stub;", "onResult", "", "navResult", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationResult;", "onStuckReplan", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MoveAction$navigationTask$1 extends NavigationResultListener.Stub {
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MoveAction$navigationTask$1(MoveAction moveAction) {
        this.this$0 = moveAction;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x033e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[LOOP:0: B:40:0x02b2->B:63:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x036c  */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.Object] */
    @Override // com.pudutech.mirsdk.mircore.NavigationResultListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onResult(NavigationResult navResult) {
        String str;
        RobotHardware robotHardware;
        String str2;
        boolean z;
        List list;
        List list2;
        Object obj;
        List list3;
        String str3;
        boolean z2;
        RobotStatus robotStatus;
        RobotStatus robotStatus2;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        int i;
        int i2;
        int i3;
        String str9;
        int i4;
        int i5;
        ThreadSafeListener threadSafeListener;
        RobotHardware robotHardware2;
        String str10;
        int i6;
        int i7;
        ThreadSafeListener threadSafeListener2;
        RobotHardware robotHardware3;
        float f;
        String str11;
        AIDLConnection aIDLConnection;
        boolean z3 = true;
        if (navResult == null) {
            str11 = this.this$0.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("navigation fail,result is null navigator:");
            aIDLConnection = this.this$0.coreService;
            MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
            sb.append(mirCoreInterface != null ? mirCoreInterface.getNavigator() : null);
            objArr[0] = sb.toString();
            Pdlog.m3277w(str11, objArr);
            this.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"InternalError\",\"level\":\"Error\",\"detail\":\"navResult is null\"}");
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getMoveActionWorker(), null, new MoveAction$navigationTask$1$onResult$1(this, null), 2, null);
            return;
        }
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "navigation result speed:" + navResult.getLinear_vel() + ' ' + navResult.getAngular_vel() + " cost:" + navResult.getCost() + " status:" + navResult.getNavigation_status());
        this.this$0.controlWatchDog = new Triple(Long.valueOf(SystemClock.elapsedRealtime()), Double.valueOf(navResult.getLinear_vel()), Double.valueOf(navResult.getAngular_vel()));
        if (navResult.getNavigation_status() == NavigationStatus.Navigating) {
            i = this.this$0.dropOccurflag;
            if (i != -1) {
                i2 = this.this$0.dropOccurflag;
                i3 = this.this$0.distancelimit;
                if (i2 < i3) {
                    str10 = this.this$0.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("moving into distancelimit area, dropOccurflag(");
                    i6 = this.this$0.dropOccurflag;
                    sb2.append(i6);
                    sb2.append(") < distancelimit(");
                    i7 = this.this$0.distancelimit;
                    sb2.append(i7);
                    sb2.append(')');
                    Pdlog.m3273d(str10, sb2.toString());
                    threadSafeListener2 = this.this$0.cliffDistanceStateListener;
                    threadSafeListener2.notify(new Function2<CliffDistanceStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$navigationTask$1$onResult$2
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(CliffDistanceStateListener cliffDistanceStateListener, String str12) {
                            invoke2(cliffDistanceStateListener, str12);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CliffDistanceStateListener it, String str12) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str12, "<anonymous parameter 1>");
                            it.enterSpeedLimitArea(true);
                        }
                    });
                    robotHardware3 = this.this$0.robotHardware;
                    HardwareInterface hardwareInterface = robotHardware3.getInterface();
                    if (hardwareInterface != null) {
                        f = this.this$0.speedLimit;
                        hardwareInterface.controlWheel(f, navResult.getAngular_vel(), true);
                    }
                } else {
                    str9 = this.this$0.TAG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("moving out distancelimit area, dropOccurflag(");
                    i4 = this.this$0.dropOccurflag;
                    sb3.append(i4);
                    sb3.append(") > distancelimit(");
                    i5 = this.this$0.distancelimit;
                    sb3.append(i5);
                    sb3.append(')');
                    Pdlog.m3273d(str9, sb3.toString());
                    threadSafeListener = this.this$0.cliffDistanceStateListener;
                    threadSafeListener.notify(new Function2<CliffDistanceStateListener, String, Unit>() { // from class: com.pudutech.mirsdk.MoveAction$navigationTask$1$onResult$3
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(CliffDistanceStateListener cliffDistanceStateListener, String str12) {
                            invoke2(cliffDistanceStateListener, str12);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CliffDistanceStateListener it, String str12) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str12, "<anonymous parameter 1>");
                            it.enterSpeedLimitArea(false);
                        }
                    });
                    robotHardware2 = this.this$0.robotHardware;
                    HardwareInterface hardwareInterface2 = robotHardware2.getInterface();
                    if (hardwareInterface2 != null) {
                        hardwareInterface2.controlWheel(navResult.getLinear_vel(), navResult.getAngular_vel(), true);
                    }
                }
                switch (navResult.getNavigation_status()) {
                    case 1:
                        str2 = this.this$0.TAG;
                        Pdlog.m3273d(str2, "navigation arrived");
                        z = this.this$0.cruiseTask;
                        if (z) {
                            list = this.this$0.stayPoints;
                            List list4 = list;
                            if (!(list4 == null || list4.isEmpty())) {
                                list2 = this.this$0.destinations;
                                Iterator it = list2.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        obj = it.next();
                                        Destination destination = (Destination) obj;
                                        list3 = this.this$0.stayPoints;
                                        if (list3 != null) {
                                            Iterator it2 = list3.iterator();
                                            while (true) {
                                                if (it2.hasNext()) {
                                                    str4 = it2.next();
                                                    if (Intrinsics.areEqual(destination.getName(), (String) str4)) {
                                                    }
                                                } else {
                                                    str4 = 0;
                                                }
                                            }
                                            str3 = str4;
                                        } else {
                                            str3 = null;
                                        }
                                        if (str3 != null) {
                                            double x = destination.getX();
                                            robotStatus = this.this$0.robotStatus;
                                            double pow = Math.pow(x - robotStatus.getPose().getValue().getX(), 2.0d);
                                            double y = destination.getY();
                                            robotStatus2 = this.this$0.robotStatus;
                                            if (Math.sqrt(pow + Math.pow(y - robotStatus2.getPose().getValue().getY(), 2.0d)) < 0.05d) {
                                                z2 = true;
                                                if (!z2) {
                                                }
                                            }
                                        }
                                        z2 = false;
                                        if (!z2) {
                                        }
                                    } else {
                                        obj = null;
                                    }
                                }
                                Destination destination2 = (Destination) obj;
                                MoveAction moveAction = this.this$0;
                                SDKRobotState sDKRobotState = SDKRobotState.Arrive;
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("{\"mode\":\"stay\",\"desc\":\"");
                                sb4.append(destination2 != null ? destination2.getName() : null);
                                sb4.append("\"}");
                                moveAction.onStateChange(sDKRobotState, sb4.toString());
                                break;
                            }
                        }
                        this.this$0.onStateChange(SDKRobotState.Arrive, "");
                        break;
                    case 2:
                    case 3:
                    case 4:
                        str5 = this.this$0.TAG;
                        Pdlog.m3273d(str5, "go to charging pile fail: " + navResult.getNavigation_status().name());
                        this.this$0.onStateChange(SDKRobotState.Arrive, "{\"mode\":\"autocharge\",\"desc\":\"" + navResult.getNavigation_status().name() + "\"}");
                        break;
                    case 5:
                        this.this$0.onStateChange(SDKRobotState.Moving, "");
                        z3 = false;
                        break;
                    case 6:
                        this.this$0.onStateChange(SDKRobotState.Stuck, "");
                        z3 = false;
                        break;
                    case 7:
                        str6 = this.this$0.TAG;
                        Pdlog.m3273d(str6, "navigation pointing to goal");
                        this.this$0.onStateChange(SDKRobotState.Approaching, "");
                        z3 = false;
                        break;
                    case 8:
                        str7 = this.this$0.TAG;
                        Pdlog.m3273d(str7, "navigation pointing to goal");
                        this.this$0.onStateChange(SDKRobotState.Approaching, "");
                        z3 = false;
                        break;
                    case 9:
                        str8 = this.this$0.TAG;
                        Pdlog.m3273d(str8, "robot need avoid");
                        this.this$0.onStateChange(SDKRobotState.Avoid, "");
                        z3 = false;
                        break;
                    default:
                        z3 = false;
                        break;
                }
                if (z3) {
                    return;
                }
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getMoveActionWorker(), null, new MoveAction$navigationTask$1$onResult$4(this, null), 2, null);
                return;
            }
        }
        robotHardware = this.this$0.robotHardware;
        HardwareInterface hardwareInterface3 = robotHardware.getInterface();
        if (hardwareInterface3 != null) {
            hardwareInterface3.controlWheel(navResult.getLinear_vel(), navResult.getAngular_vel(), true);
        }
        switch (navResult.getNavigation_status()) {
        }
        if (z3) {
        }
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationResultListener
    public void onStuckReplan() {
        String str;
        Function0 function0;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onStuckReplan");
        function0 = this.this$0._onStuckReplan;
        function0.invoke();
    }
}
