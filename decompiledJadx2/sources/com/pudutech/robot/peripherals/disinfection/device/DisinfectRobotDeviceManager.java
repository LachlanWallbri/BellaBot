package com.pudutech.robot.peripherals.disinfection.device;

import android.content.Context;
import com.pudutech.robot.peripherals.BuildConfig;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.hola.HolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.IHolaBotPeripherals;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import com.pudutech.robot.peripherals.mock.MockDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.mock.MockFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.mock.MockHolaRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.PeanutRobotPeripherals;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DisinfectRobotDeviceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u0004\u0018\u00010\u0004J\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/device/DisinfectRobotDeviceManager;", "", "()V", "disinfectDeviceHelper", "Lcom/pudutech/robot/peripherals/disinfection/device/DisinfectDeviceHelper;", "disinfectRobotPeripherals", "Lcom/pudutech/robot/peripherals/disinfection/IDisinfectRobotPeripherals;", "isMock", "", "()Z", "setMock", "(Z)V", "getDisinfectDeviceHelper", "getDisinfectDeviceRobotPeripherals", "init", "", "context", "Landroid/content/Context;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DisinfectRobotDeviceManager {
    public static final DisinfectRobotDeviceManager INSTANCE = new DisinfectRobotDeviceManager();
    private static DisinfectDeviceHelper disinfectDeviceHelper;
    private static IDisinfectRobotPeripherals disinfectRobotPeripherals;
    private static boolean isMock;

    private DisinfectRobotDeviceManager() {
    }

    public final boolean isMock() {
        return isMock;
    }

    public final void setMock(boolean z) {
        isMock = z;
    }

    public final void init(Context context, boolean isMock2) {
        Object obj;
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals;
        DisinfectDeviceHelper disinfectDeviceHelper2;
        Object obj2;
        Intrinsics.checkParameterIsNotNull(context, "context");
        RobotPeripheralsFactory robotPeripheralsFactory = RobotPeripheralsFactory.INSTANCE;
        if (isMock2) {
            if (Intrinsics.areEqual(IDisinfectRobotPeripherals.class, IFirefoxRobotPeripherals.class)) {
                Object instance = MockFirefoxRobotPeripherals.INSTANCE.getINSTANCE();
                if (instance == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals");
                }
                obj2 = (IRobotPeripherals) ((IDisinfectRobotPeripherals) instance);
            } else if (Intrinsics.areEqual(IDisinfectRobotPeripherals.class, IDisinfectRobotPeripherals.class)) {
                Object instance2 = MockDisinfectRobotPeripherals.INSTANCE.getINSTANCE();
                if (instance2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals");
                }
                obj2 = (IRobotPeripherals) ((IDisinfectRobotPeripherals) instance2);
            } else {
                if (!Intrinsics.areEqual(IDisinfectRobotPeripherals.class, IHolaBotPeripherals.class)) {
                    throw new IllegalArgumentException("getRobotPeripherals " + IDisinfectRobotPeripherals.class + " not find ");
                }
                Object instance3 = MockHolaRobotPeripherals.INSTANCE.getINSTANCE();
                if (instance3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals");
                }
                obj2 = (IRobotPeripherals) ((IDisinfectRobotPeripherals) instance3);
            }
            iDisinfectRobotPeripherals = (IDisinfectRobotPeripherals) obj2;
        } else {
            if (Intrinsics.areEqual(IDisinfectRobotPeripherals.class, IFirefoxRobotPeripherals.class)) {
                Object instance4 = FirefoxRobotPeripherals.INSTANCE.getINSTANCE();
                if (instance4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals");
                }
                obj = (IRobotPeripherals) ((IDisinfectRobotPeripherals) instance4);
            } else if (Intrinsics.areEqual(IDisinfectRobotPeripherals.class, IPeanutRobotPeripherals.class)) {
                Object instance5 = PeanutRobotPeripherals.INSTANCE.getINSTANCE();
                if (instance5 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals");
                }
                obj = (IRobotPeripherals) ((IDisinfectRobotPeripherals) instance5);
            } else if (Intrinsics.areEqual(IDisinfectRobotPeripherals.class, IDisinfectRobotPeripherals.class)) {
                Object instance6 = DisinfectRobotPeripherals.INSTANCE.getINSTANCE();
                if (instance6 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals");
                }
                obj = (IRobotPeripherals) ((IDisinfectRobotPeripherals) instance6);
            } else {
                if (!Intrinsics.areEqual(IDisinfectRobotPeripherals.class, IHolaBotPeripherals.class)) {
                    throw new IllegalArgumentException("getRobotPeripherals " + IDisinfectRobotPeripherals.class + " not find ");
                }
                Object instance7 = HolaBotPeripherals.INSTANCE.getINSTANCE();
                if (instance7 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals");
                }
                obj = (IRobotPeripherals) ((IDisinfectRobotPeripherals) instance7);
            }
            iDisinfectRobotPeripherals = (IDisinfectRobotPeripherals) obj;
        }
        disinfectRobotPeripherals = iDisinfectRobotPeripherals;
        IDisinfectRobotPeripherals iDisinfectRobotPeripherals2 = disinfectRobotPeripherals;
        if (iDisinfectRobotPeripherals2 != null) {
            iDisinfectRobotPeripherals2.init(context, isMock2);
        }
        disinfectDeviceHelper = new DisinfectDeviceHelper();
        isMock = isMock2;
        Boolean bool = BuildConfig.module_robot_peripherals_is_debug;
        Intrinsics.checkExpressionValueIsNotNull(bool, "BuildConfig.module_robot_peripherals_is_debug");
        if (!bool.booleanValue() || (disinfectDeviceHelper2 = disinfectDeviceHelper) == null) {
            return;
        }
        disinfectDeviceHelper2.initListener();
    }

    public final IDisinfectRobotPeripherals getDisinfectDeviceRobotPeripherals() {
        return disinfectRobotPeripherals;
    }

    public final DisinfectDeviceHelper getDisinfectDeviceHelper() {
        return disinfectDeviceHelper;
    }
}
