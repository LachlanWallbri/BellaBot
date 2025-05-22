package com.pudutech.mirsdkwrap.lib.interf;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SingleMoveByDestinationStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J(\u0010\b\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00060\tj\b\u0012\u0004\u0012\u00020\u0006`\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/interf/SingleMoveByDestinationStateListener;", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByDestinationStateListener;", "()V", "onArrive", "", LinkFormat.DOMAIN, "Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "onAvoid", "onCancel", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "hasLeft", "", "onDone", "onElevatorCallback", "state", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorUtilizeState;", "onElevatorUtilizeState", "p1", "Lcom/pudutech/mirsdkwrap/lib/move/bean/ElevatorEventParam;", "onError", "eh", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "onMoving", "event", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotMoveEvent;", "onPause", "onStart", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class SingleMoveByDestinationStateListener implements MoveByDestinationStateListener {
    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
    public void onArrive(Destination d) {
        Intrinsics.checkParameterIsNotNull(d, "d");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
    public void onAvoid() {
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
    public void onCancel(ArrayList<Destination> d, boolean hasLeft) {
        Intrinsics.checkParameterIsNotNull(d, "d");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
    public void onDone(Destination d) {
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

    @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
    public void onMoving(RobotMoveEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
    public void onPause(Destination d) {
        Intrinsics.checkParameterIsNotNull(d, "d");
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
    public void onStart(Destination d) {
        Intrinsics.checkParameterIsNotNull(d, "d");
    }
}
