package com.pudutech.mirsdkwrap.lib.interf;

import com.pudutech.mirsdkwrap.lib.move.bean.SuspendChargeArriveState;
import kotlin.Metadata;

/* compiled from: MoveBySuspendChargeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/interf/MoveBySuspendChargeListener;", "Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "onArrive", "", "state", "Lcom/pudutech/mirsdkwrap/lib/move/bean/SuspendChargeArriveState;", "onCancel", "onPause", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface MoveBySuspendChargeListener extends BaseRobotMoveStateListener {
    void onArrive(SuspendChargeArriveState state);

    void onCancel();

    void onPause();
}
