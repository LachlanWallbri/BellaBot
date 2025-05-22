package com.pudutech.bumblebee.business.movementCallback;

import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;

/* compiled from: CruiseCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementCallback/CruiseCallback;", "", "onMovementChanged", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "", "onTaskSetup", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CruiseCallback {
    void onMovementChanged(RobotState state, String description);

    void onTaskSetup();
}
