package com.pudutech.bumblebee.business.robotsdk;

import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;

/* compiled from: MoveStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007H&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/MoveStateListener;", "", "onAccessDoorChange", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "destination", "", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface MoveStateListener {
    void onAccessDoorChange(AccessDoorControlState state, String destination);

    void onStateChange(RobotState state, String description);
}
