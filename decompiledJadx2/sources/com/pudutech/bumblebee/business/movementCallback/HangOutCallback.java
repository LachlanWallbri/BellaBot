package com.pudutech.bumblebee.business.movementCallback;

import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import kotlin.Metadata;

/* compiled from: HangOutCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementCallback/HangOutCallback;", "Lcom/pudutech/bumblebee/business/movementCallback/BaseTaskCallback;", "onStatusChanged", "", "x_m", "", "y_m", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface HangOutCallback extends BaseTaskCallback {
    void onStatusChanged(double x_m, double y_m, TaskStatus status);
}
