package com.pudutech.robot.peripherals.peanut;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.common.QrScanTaskListener;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import kotlin.Metadata;

/* compiled from: IPeanutRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J)\u0010\u0006\u001a\u00020\u00032\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/peanut/IPeanutRobotPeripherals;", "Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "addQrScanNotifyListener", "", "l", "Lcom/pudutech/robot/peripherals/common/QrScanTaskListener;", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "removeQrScanNotifyListener", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IPeanutRobotPeripherals extends IRobotPeripherals {
    void addQrScanNotifyListener(QrScanTaskListener l);

    void playBreathing(LightBeltType[] lightBelts, BreathingLightColor color);

    void removeQrScanNotifyListener(QrScanTaskListener l);
}
