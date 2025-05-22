package com.pudutech.bumblebee.robot_ui.p054ui;

import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener;
import kotlin.Metadata;

/* compiled from: CruiseSelectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/CruiseSelectActivity$functionButton$1", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/FunctionButtonListener;", "onClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CruiseSelectActivity$functionButton$1 implements FunctionButtonListener {
    final /* synthetic */ CruiseSelectActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CruiseSelectActivity$functionButton$1(CruiseSelectActivity cruiseSelectActivity) {
        this.this$0 = cruiseSelectActivity;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener
    public void onClick() {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$functionButton$1$onClick$1
            @Override // java.lang.Runnable
            public final void run() {
                CruiseSelectActivity$functionButton$1.this.this$0.goToCruise();
            }
        });
    }
}
