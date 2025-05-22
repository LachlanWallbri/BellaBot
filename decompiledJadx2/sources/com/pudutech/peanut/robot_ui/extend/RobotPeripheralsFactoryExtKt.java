package com.pudutech.peanut.robot_ui.extend;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.hola.HolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.IHolaBotPeripherals;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.PeanutRobotPeripherals;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotPeripheralsFactoryExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, m3961d2 = {TmpConstant.PROPERTY_IDENTIFIER_GET, "Lcom/pudutech/robot/peripherals/peanut/IPeanutRobotPeripherals;", "Lcom/pudutech/robot/peripherals/RobotPeripheralsFactory;", "robot_ui_peanutRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotPeripheralsFactoryExtKt {
    public static final IPeanutRobotPeripherals get(RobotPeripheralsFactory get) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        RobotPeripheralsFactory robotPeripheralsFactory = RobotPeripheralsFactory.INSTANCE;
        if (Intrinsics.areEqual(IPeanutRobotPeripherals.class, IFirefoxRobotPeripherals.class)) {
            Object instance = FirefoxRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance);
        } else if (Intrinsics.areEqual(IPeanutRobotPeripherals.class, IPeanutRobotPeripherals.class)) {
            Object instance2 = PeanutRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance2);
        } else if (Intrinsics.areEqual(IPeanutRobotPeripherals.class, IDisinfectRobotPeripherals.class)) {
            Object instance3 = DisinfectRobotPeripherals.INSTANCE.getINSTANCE();
            if (instance3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance3);
        } else {
            if (!Intrinsics.areEqual(IPeanutRobotPeripherals.class, IHolaBotPeripherals.class)) {
                throw new IllegalArgumentException("getRobotPeripherals " + IPeanutRobotPeripherals.class + " not find ");
            }
            Object instance4 = HolaBotPeripherals.INSTANCE.getINSTANCE();
            if (instance4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals");
            }
            obj = (IRobotPeripherals) ((IPeanutRobotPeripherals) instance4);
        }
        return (IPeanutRobotPeripherals) obj;
    }
}
