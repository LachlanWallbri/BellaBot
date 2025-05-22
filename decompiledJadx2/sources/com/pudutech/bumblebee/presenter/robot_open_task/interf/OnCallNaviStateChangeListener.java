package com.pudutech.bumblebee.presenter.robot_open_task.interf;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import kotlin.Metadata;

/* compiled from: OnCallNaviStateChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/interf/OnCallNaviStateChangeListener;", "", "onChange", "", "state", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", TypedValues.Attributes.S_TARGET, "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface OnCallNaviStateChangeListener {
    void onChange(TaskStatus state, String target);
}
