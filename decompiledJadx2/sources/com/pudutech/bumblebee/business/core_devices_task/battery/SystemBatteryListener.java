package com.pudutech.bumblebee.business.core_devices_task.battery;

import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Metadata;

/* compiled from: SystemBatteryListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/battery/SystemBatteryListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onPowerChange", "", "powerPercent", "", "onStateChange", "powerState", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/PowerState;", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface SystemBatteryListener extends BaseListener {
    void onPowerChange(int powerPercent);

    void onStateChange(int powerPercent, PowerState powerState, ChargeState chargeState);
}
