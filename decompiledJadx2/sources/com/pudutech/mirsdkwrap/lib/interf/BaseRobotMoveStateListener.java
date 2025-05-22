package com.pudutech.mirsdkwrap.lib.interf;

import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseRobotMoveStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "", "onAvoid", "", "onElevatorCallback", "state", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorUtilizeState;", "onElevatorUtilizeState", "p1", "Lcom/pudutech/mirsdkwrap/lib/move/bean/ElevatorEventParam;", "onError", "eh", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "onMoving", "event", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotMoveEvent;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface BaseRobotMoveStateListener {
    void onAvoid();

    void onElevatorCallback(ElevatorUtilizeState state);

    void onElevatorUtilizeState(ElevatorUtilizeState state, ElevatorEventParam p1);

    void onError(MoveErrorHelper eh);

    void onMoving(RobotMoveEvent event);
}
