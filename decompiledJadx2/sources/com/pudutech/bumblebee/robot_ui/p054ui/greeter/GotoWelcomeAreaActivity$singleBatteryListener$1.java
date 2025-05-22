package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import android.content.Intent;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.SingleBatteryListener;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.track.task.GoToWelcomeTrack;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GotoWelcomeAreaActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/greeter/GotoWelcomeAreaActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/SingleBatteryListener;", "showChargerEvent", "", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showPowerChange", "i", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GotoWelcomeAreaActivity$singleBatteryListener$1 extends SingleBatteryListener {
    final /* synthetic */ GotoWelcomeAreaActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GotoWelcomeAreaActivity$singleBatteryListener$1(GotoWelcomeAreaActivity gotoWelcomeAreaActivity) {
        this.this$0 = gotoWelcomeAreaActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SingleBatteryListener, com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        String str;
        BackToWaitAreaPresenter backToWaitAreaPresenter;
        Intrinsics.checkParameterIsNotNull(model, "model");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "showChargerEvent " + model);
        if (model.isCharging()) {
            GoToWelcomeTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Charge);
            backToWaitAreaPresenter = this.this$0.getBackToWaitAreaPresenter();
            backToWaitAreaPresenter.actionPauseNoTimer();
            this.this$0.jumpAndFinish(new Intent(this.this$0, (Class<?>) RobotChargingActivity.class));
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SingleBatteryListener, com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        ((MyStatusBarLayout) this.this$0._$_findCachedViewById(C4188R.id.layout_my_status_bar)).setBattery(i);
    }
}
