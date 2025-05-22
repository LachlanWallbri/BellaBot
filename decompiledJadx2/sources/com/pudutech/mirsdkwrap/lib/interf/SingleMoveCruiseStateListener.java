package com.pudutech.mirsdkwrap.lib.interf;

import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SingleMoveCruiseStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001a\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/interf/SingleMoveCruiseStateListener;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveCruiseStateListener;", "()V", "onAvoid", "", "onCancel", "onElevatorCallback", "state", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorUtilizeState;", "onElevatorUtilizeState", "p1", "Lcom/pudutech/mirsdkwrap/lib/move/bean/ElevatorEventParam;", "onError", "eh", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "onGoalCruise", "onMoving", "event", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotMoveEvent;", "onPause", "onStayPointArrive", "s", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class SingleMoveCruiseStateListener implements MoveCruiseStateListener {
    @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
    public void onAvoid() {
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
    public void onCancel() {
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
    public void onElevatorCallback(ElevatorUtilizeState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
    public void onElevatorUtilizeState(ElevatorUtilizeState state, ElevatorEventParam p1) {
        Intrinsics.checkParameterIsNotNull(state, "state");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
    public void onError(MoveErrorHelper eh) {
        Intrinsics.checkParameterIsNotNull(eh, "eh");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
    public void onGoalCruise() {
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
    public void onMoving(RobotMoveEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
    public void onPause() {
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
    public void onStayPointArrive(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
    }
}
