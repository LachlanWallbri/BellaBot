package com.pudutech.robot.peripherals.firefox;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: IPhoenixRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH&ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0010\u001a\u00020\u00032\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0012\"\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&¢\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/IPhoenixRobotPeripherals;", "Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "controlHatch", "", "operation", "Lkotlin/UByte;", "hatchs", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/firefox/Hatch;", "Lkotlin/collections/ArrayList;", "isOpen", "", "hatchsControlListener", "Lcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;", "controlHatch-OM5YLlI", "(BLjava/util/ArrayList;ZLcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;)V", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IPhoenixRobotPeripherals extends IRobotPeripherals {
    /* renamed from: controlHatch-OM5YLlI, reason: not valid java name */
    void mo4492controlHatchOM5YLlI(byte operation, ArrayList<Hatch> hatchs, boolean isOpen, IHatchsControlListener hatchsControlListener);

    void playBreathing(LightBeltType[] lightBelts, BreathingLightColor color);
}
