package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.SingleBatteryListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GreeterFaceActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterFaceActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/SingleBatteryListener;", "currentPower", "", "showChargerEvent", "", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GreeterFaceActivity$singleBatteryListener$1 extends SingleBatteryListener {
    private int currentPower;
    final /* synthetic */ GreeterFaceActivity this$0;

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SingleBatteryListener, com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GreeterFaceActivity$singleBatteryListener$1(GreeterFaceActivity greeterFaceActivity) {
        this.this$0 = greeterFaceActivity;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SingleBatteryListener, com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        String str;
        Intrinsics.checkParameterIsNotNull(model, "model");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "showChargerEvent " + model);
        if (!model.isCharging()) {
            PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        } else {
            showPowerEvent(BatteryInfoManager.INSTANCE.getPowerEvent());
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SingleBatteryListener, com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        int powerPercent = model.getPowerPercent();
        if (!BatteryInfoManager.INSTANCE.getChargerEvent().isCharging() || powerPercent == this.currentPower) {
            return;
        }
        PeripheralsSceneUtil.INSTANCE.showCharging(powerPercent);
        this.currentPower = powerPercent;
    }
}
