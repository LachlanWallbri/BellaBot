package com.pudutech.robot.peripherals.mock;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.common.QrScanTaskListener;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MockPeanutRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\nJ)\u0010\u000b\u001a\u00020\u00052\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/mock/PeanutRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/peanut/IPeanutRobotPeripherals;", "()V", "addQrScanNotifyListener", "", "l", "Lcom/pudutech/robot/peripherals/common/QrScanTaskListener;", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "removeQrScanNotifyListener", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PeanutRobotPeripherals extends CommonRobotPeripherals implements IPeanutRobotPeripherals {
    @Override // com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals
    public void addQrScanNotifyListener(QrScanTaskListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
    }

    @Override // com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals
    public void playBreathing(LightBeltType[] lightBelts, BreathingLightColor color) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(color, "color");
    }

    @Override // com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals
    public void removeQrScanNotifyListener(QrScanTaskListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
    }

    private PeanutRobotPeripherals() {
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public byte[] getRecvCmds() {
        return UByteArray.m4571constructorimpl(0);
    }
}
