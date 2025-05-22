package com.pudutech.bumblebee.business.movementCallback;

import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import kotlin.Metadata;

/* compiled from: DeliverCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "Lcom/pudutech/bumblebee/business/movementCallback/BaseTaskCallback;", "onStatusChanged", "", "destination", "", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "range", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface DeliverCallback extends BaseTaskCallback {
    void onStatusChanged(String destination, TaskStatus status, double range);
}
