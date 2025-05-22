package com.pudutech.robot.peripherals.hola;

import kotlin.Metadata;

/* compiled from: IFunctionPanelListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/IFunctionPanelListener;", "", "onTrigger", "", "type", "Lcom/pudutech/robot/peripherals/hola/IFunctionPanelListener$TriggerType;", "TriggerType", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IFunctionPanelListener {

    /* compiled from: IFunctionPanelListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/IFunctionPanelListener$TriggerType;", "", "(Ljava/lang/String;I)V", "FUNCTION_BUTTON", "INFRARED_SENSOR", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum TriggerType {
        FUNCTION_BUTTON,
        INFRARED_SENSOR
    }

    void onTrigger(TriggerType type);
}
