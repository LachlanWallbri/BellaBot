package com.pudutech.bumblebee.business.robotsdk.peripherals_listeners;

import com.iflytek.speech.UtilityConfig;
import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import kotlin.Metadata;

/* compiled from: DeviceStatusChangedListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/DeviceStatusChangedListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onDeviceStatusChange", "", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;", "status", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", "description", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface DeviceStatusChangedListener extends BaseListener {
    void onDeviceStatusChange(PeripheralDevice device, PeripheralDeviceStatus status, String description);
}
