package com.pudutech.robot.peripherals;

import androidx.exifinterface.media.ExifInterface;
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
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotPeripheralsFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0002H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u0002H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\u0006¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/RobotPeripheralsFactory;", "", "()V", "getMockRobotPeripherals", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "()Lcom/pudutech/robot/peripherals/interf/IRobotPeripherals;", "getRobotPeripherals", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotPeripheralsFactory {
    public static final RobotPeripheralsFactory INSTANCE = new RobotPeripheralsFactory();

    private RobotPeripheralsFactory() {
    }

    public final /* synthetic */ <T extends IRobotPeripherals> T getRobotPeripherals() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Intrinsics.areEqual(IRobotPeripherals.class, IFirefoxRobotPeripherals.class)) {
            FirefoxRobotPeripherals instance = FirefoxRobotPeripherals.INSTANCE.getINSTANCE();
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return instance;
        }
        if (Intrinsics.areEqual(IRobotPeripherals.class, IPeanutRobotPeripherals.class)) {
            PeanutRobotPeripherals instance2 = PeanutRobotPeripherals.INSTANCE.getINSTANCE();
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return instance2;
        }
        if (Intrinsics.areEqual(IRobotPeripherals.class, IDisinfectRobotPeripherals.class)) {
            DisinfectRobotPeripherals instance3 = DisinfectRobotPeripherals.INSTANCE.getINSTANCE();
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return instance3;
        }
        if (Intrinsics.areEqual(IRobotPeripherals.class, IHolaBotPeripherals.class)) {
            HolaBotPeripherals instance4 = HolaBotPeripherals.INSTANCE.getINSTANCE();
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return instance4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getRobotPeripherals ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sb.append(IRobotPeripherals.class);
        sb.append(" not find ");
        throw new IllegalArgumentException(sb.toString());
    }

    public final /* synthetic */ <T extends IRobotPeripherals> T getMockRobotPeripherals() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Intrinsics.areEqual(IRobotPeripherals.class, IFirefoxRobotPeripherals.class)) {
            MockFirefoxRobotPeripherals instance = MockFirefoxRobotPeripherals.INSTANCE.getINSTANCE();
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return instance;
        }
        if (Intrinsics.areEqual(IRobotPeripherals.class, IDisinfectRobotPeripherals.class)) {
            MockDisinfectRobotPeripherals instance2 = MockDisinfectRobotPeripherals.INSTANCE.getINSTANCE();
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return instance2;
        }
        if (Intrinsics.areEqual(IRobotPeripherals.class, IHolaBotPeripherals.class)) {
            MockHolaRobotPeripherals instance3 = MockHolaRobotPeripherals.INSTANCE.getINSTANCE();
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return instance3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getRobotPeripherals ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sb.append(IRobotPeripherals.class);
        sb.append(" not find ");
        throw new IllegalArgumentException(sb.toString());
    }
}
