package com.pudutech.bumblebee.business.robotsdk.sdk_listeners;

import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Metadata;

/* compiled from: BatteryListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/BatteryListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onBattery", "", "p0", "", "p1", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatteryFloorLevelLimitResult", "state", "level", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BatteryListener extends BaseListener {
    void onBattery(int p0, ChargeState p1);

    void onBatteryFloorLevelLimitResult(int state, int level);
}
