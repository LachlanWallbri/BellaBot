package com.pudutech.bumblebee.presenter.robot_open_task.listener;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import kotlin.Metadata;

/* compiled from: BeeperTaskListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/BeeperTaskListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onTask", "", "action", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/BeeperAction;", TypedValues.Attributes.S_TARGET, "", "type", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BeeperTaskListener extends BaseListener {
    void onTask(BeeperAction action, String target, CallFromType type);
}
