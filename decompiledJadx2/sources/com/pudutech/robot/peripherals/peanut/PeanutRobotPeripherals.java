package com.pudutech.robot.peripherals.peanut;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.common.LightBeltsPlayHelper;
import com.pudutech.robot.peripherals.common.QrCodeScanHelper;
import com.pudutech.robot.peripherals.common.QrScanTaskListener;
import com.pudutech.robot.peripherals.config.LightBeltType;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeanutRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00162\u00020\u00012\u00020\u0002:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\u0005H\u0014J)\u0010\f\u001a\u00020\u00052\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/peanut/PeanutRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/peanut/IPeanutRobotPeripherals;", "()V", "addQrScanNotifyListener", "", "l", "Lcom/pudutech/robot/peripherals/common/QrScanTaskListener;", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "onHardwareConnect", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "removeQrScanNotifyListener", "useQrCodeScan", "", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PeanutRobotPeripherals extends CommonRobotPeripherals implements IPeanutRobotPeripherals {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<PeanutRobotPeripherals>() { // from class: com.pudutech.robot.peripherals.peanut.PeanutRobotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PeanutRobotPeripherals invoke() {
            return new PeanutRobotPeripherals(null);
        }
    });
    private static final String TAG = "PeanutRobotPeripherals";

    private PeanutRobotPeripherals() {
    }

    public /* synthetic */ PeanutRobotPeripherals(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: PeanutRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/peanut/PeanutRobotPeripherals$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/robot/peripherals/peanut/PeanutRobotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/peanut/PeanutRobotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final PeanutRobotPeripherals getINSTANCE() {
            Lazy lazy = PeanutRobotPeripherals.INSTANCE$delegate;
            Companion companion = PeanutRobotPeripherals.INSTANCE;
            return (PeanutRobotPeripherals) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    protected void onHardwareConnect() {
        if (useQrCodeScan()) {
            QrCodeScanHelper.INSTANCE.init(getContext());
        }
    }

    private final boolean useQrCodeScan() {
        MachineInfo machineInfo;
        HardwareInterface hardWareInterface = getHardWareInterface();
        return ((hardWareInterface == null || (machineInfo = hardWareInterface.getMachineInfo()) == null) ? null : machineInfo.getScanCodeDeviceType()) == MachineInfo.ScanCodeType.Default;
    }

    @Override // com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals
    public void addQrScanNotifyListener(QrScanTaskListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (useQrCodeScan()) {
            QrCodeScanHelper.INSTANCE.addNotifyListener(l);
        }
    }

    @Override // com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals
    public void removeQrScanNotifyListener(QrScanTaskListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (useQrCodeScan()) {
            QrCodeScanHelper.INSTANCE.removeNotifyListener(l);
        }
    }

    @Override // com.pudutech.robot.peripherals.peanut.IPeanutRobotPeripherals
    public void playBreathing(LightBeltType[] lightBelts, BreathingLightColor color) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(color, "color");
        LightBeltsPlayHelper.INSTANCE.playBreathing((LightBeltType[]) Arrays.copyOf(lightBelts, lightBelts.length), color);
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public byte[] getRecvCmds() {
        return UByteArray.m4571constructorimpl(0);
    }
}
