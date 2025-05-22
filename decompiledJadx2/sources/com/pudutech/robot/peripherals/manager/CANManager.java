package com.pudutech.robot.peripherals.manager;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: CANManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/manager/CANManager;", "", "()V", "receiveCmds", "Lkotlin/UByteArray;", "receiveCmds$annotations", "getReceiveCmds", "()[B", "[B", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CANManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<CANManager>() { // from class: com.pudutech.robot.peripherals.manager.CANManager$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CANManager invoke() {
            return new CANManager(null);
        }
    });
    private final byte[] receiveCmds;

    public static /* synthetic */ void receiveCmds$annotations() {
    }

    private CANManager() {
        this.receiveCmds = new byte[]{CANConfig.INSTANCE.getCAN_CMD_HEAD_BO_PROTOCOL_SHUTDOWN_EVENT()};
    }

    public /* synthetic */ CANManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final byte[] getReceiveCmds() {
        return this.receiveCmds;
    }

    /* compiled from: CANManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/manager/CANManager$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/robot/peripherals/manager/CANManager;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/manager/CANManager;", "INSTANCE$delegate", "Lkotlin/Lazy;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final CANManager getINSTANCE() {
            Lazy lazy = CANManager.INSTANCE$delegate;
            Companion companion = CANManager.INSTANCE;
            return (CANManager) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
