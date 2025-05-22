package com.pudutech.bumblebee.business.peripherals_task.led_screen_task;

import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import kotlin.Metadata;

/* compiled from: LEDScreenUpdateFontListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_screen_task/LEDScreenUpdateFontListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onUpdateEvent", "", "event", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateEvent;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface LEDScreenUpdateFontListener extends BaseListener {
    void onUpdateEvent(UpdateEvent event);
}
