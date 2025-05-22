package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import kotlin.Metadata;

/* compiled from: RecycleTaskActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/recycle/RecycleTaskActivity$functionButton$1", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/FunctionButtonListener;", "onClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecycleTaskActivity$functionButton$1 implements FunctionButtonListener {
    final /* synthetic */ RecycleTaskActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecycleTaskActivity$functionButton$1(RecycleTaskActivity recycleTaskActivity) {
        this.this$0 = recycleTaskActivity;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener
    public void onClick() {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$functionButton$1$onClick$1
            @Override // java.lang.Runnable
            public final void run() {
                HomeSettingDialog homeSettingDialog;
                ShowTipMsgDialog showTipMsgDialog;
                String str;
                if (RecycleTaskActivity.access$getFaceAnimationView$p(RecycleTaskActivity$functionButton$1.this.this$0).getVisibility() == 0) {
                    str = RecycleTaskActivity$functionButton$1.this.this$0.TAG;
                    Pdlog.m3273d(str, "faceAnimationDialog is show, do not startRecycleTask");
                    return;
                }
                homeSettingDialog = RecycleTaskActivity$functionButton$1.this.this$0.homeSettingDialog;
                if (homeSettingDialog == null || !homeSettingDialog.isShowing()) {
                    showTipMsgDialog = RecycleTaskActivity$functionButton$1.this.this$0.tipsDialog;
                    if (showTipMsgDialog == null || !showTipMsgDialog.isShowing()) {
                        RecycleTaskActivity$functionButton$1.this.this$0.startGo();
                    }
                }
            }
        });
    }
}
