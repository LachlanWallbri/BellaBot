package com.pudutech.robot.peripherals.common;

import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.common.RobotShutDownManager;
import com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.hola.HolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.IHolaBotPeripherals;
import com.pudutech.robot.peripherals.interf.IRobotPeripherals;
import com.pudutech.robot.peripherals.interf.IShutdownEventListener;
import com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals;
import com.pudutech.robot.peripherals.peanut.PeanutRobotPeripherals;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotShutDownManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000/\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/RobotShutDownManager;", "", "()V", "TAG", "", "initShutDownListener", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/common/RobotShutDownManager$ShutDownListener;", "Lkotlin/collections/ArrayList;", "shutdownEventListener", "com/pudutech/robot/peripherals/common/RobotShutDownManager$shutdownEventListener$1", "Lcom/pudutech/robot/peripherals/common/RobotShutDownManager$shutdownEventListener$1;", "addShutDownListener", "", "listener", "addShutdownEventListener", "removeShotDownListener", "ShutDownListener", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotShutDownManager {
    public static final RobotShutDownManager INSTANCE = new RobotShutDownManager();
    private static final String TAG;
    private static final ArrayList<ShutDownListener> initShutDownListener;
    private static final RobotShutDownManager$shutdownEventListener$1 shutdownEventListener;

    /* compiled from: RobotShutDownManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/RobotShutDownManager$ShutDownListener;", "", "shutDownNotify", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface ShutDownListener {
        void shutDownNotify();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.pudutech.robot.peripherals.common.RobotShutDownManager$shutdownEventListener$1] */
    static {
        String simpleName = RobotShutDownManager.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "RobotShutDownManager::class.java.simpleName");
        TAG = simpleName;
        shutdownEventListener = new IShutdownEventListener() { // from class: com.pudutech.robot.peripherals.common.RobotShutDownManager$shutdownEventListener$1
            @Override // com.pudutech.robot.peripherals.interf.IShutdownEventListener
            public void onShutdownConfirm() {
                ArrayList arrayList;
                RobotShutDownManager robotShutDownManager = RobotShutDownManager.INSTANCE;
                arrayList = RobotShutDownManager.initShutDownListener;
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((RobotShutDownManager.ShutDownListener) it.next()).shutDownNotify();
                    }
                }
            }
        };
        initShutDownListener = new ArrayList<>();
    }

    private RobotShutDownManager() {
    }

    public final void addShutDownListener(ShutDownListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (initShutDownListener.contains(listener)) {
            return;
        }
        initShutDownListener.add(listener);
    }

    public final void removeShotDownListener(ShutDownListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        initShutDownListener.remove(listener);
    }

    public final void addShutdownEventListener() {
        Object obj;
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
        ((IPeanutRobotPeripherals) obj).addShutdownEventListener(shutdownEventListener);
    }
}
