package com.pudutech.bumblebee.business.movementCallback;

import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;

/* compiled from: BaseTaskCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001c\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementCallback/BaseTaskCallback;", "", "onDone", "", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "", "onTaskSetup", "isLegalTask", "", "info", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BaseTaskCallback {
    void onDone();

    void onMovementChanged(RobotState state, String description);

    void onTaskSetup(boolean isLegalTask, String info);
}
