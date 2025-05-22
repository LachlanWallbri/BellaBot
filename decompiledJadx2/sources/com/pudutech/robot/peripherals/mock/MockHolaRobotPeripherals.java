package com.pudutech.robot.peripherals.mock;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.config.LightBeltAnimationFrame;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.firefox.Hatch;
import com.pudutech.robot.peripherals.firefox.HatchesStatus;
import com.pudutech.robot.peripherals.firefox.IHatchsControlListener;
import com.pudutech.robot.peripherals.hola.ExpressionType;
import com.pudutech.robot.peripherals.hola.IFunctionPanelListener;
import com.pudutech.robot.peripherals.hola.IHolaBotPeripherals;
import com.pudutech.robot.peripherals.hola.ILoRaDataReceivedCallback;
import com.pudutech.robot.peripherals.hola.ILoRaStatusCallback;
import com.pudutech.robot.peripherals.hola.INFCSwipeCardListener;
import com.pudutech.robot.peripherals.hola.LightColor;
import com.pudutech.robot.peripherals.hola.LightMode;
import com.pudutech.robot.peripherals.hola.LoRaType;
import com.pudutech.robot.peripherals.manager.CANConfig;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: MockHolaRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 S2\u00020\u00012\u00020\u0002:\u0001SB\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J(\u0010\u0017\u001a\u00020\u00142\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\"H\u0016ø\u0001\u0000¢\u0006\u0002\u0010#J$\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0012\u0010+\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020\u0014H\u0016J)\u0010/\u001a\u00020\u00142\u0012\u00100\u001a\n\u0012\u0006\b\u0001\u0012\u00020201\"\u0002022\u0006\u00103\u001a\u000204H\u0016¢\u0006\u0002\u00105J\u0012\u00106\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u00107\u001a\u00020\u0014H\u0002J\u0010\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u00020\u00142\u0006\u0010<\u001a\u00020\u001eH\u0016JN\u0010=\u001a\u00020\u00142\u0012\u0010>\u001a\n\u0012\u0006\b\u0001\u0012\u00020201\"\u0002022\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020DH\u0016ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ2\u0010I\u001a\u00020\u00142\u0006\u0010>\u001a\u0002022\u0006\u0010J\u001a\u00020D2\u0006\u0010K\u001a\u00020D2\u0006\u0010L\u001a\u00020DH\u0016ø\u0001\u0000¢\u0006\u0004\bM\u0010NJ\u0010\u0010O\u001a\u00020\u00142\u0006\u0010P\u001a\u00020 H\u0016J\b\u0010Q\u001a\u00020\u0014H\u0002J\b\u0010R\u001a\u00020\u0014H\u0002R\"\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006T"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/mock/MockHolaRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/hola/IHolaBotPeripherals;", "()V", "currentControlHatchs", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/firefox/Hatch;", "Lkotlin/collections/ArrayList;", "hatchsControlListener", "Lcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;", ES6Iterator.VALUE_PROPERTY, "", "isMute", "()Z", "setMute", "(Z)V", "isOpen", "timeoutJob", "Lkotlinx/coroutines/Job;", "addFunctionPanelListener", "", "listener", "Lcom/pudutech/robot/peripherals/hola/IFunctionPanelListener;", "callbackHatchsControlState", "hatchs", "state", "Lcom/pudutech/robot/peripherals/firefox/HatchesStatus;", "closeLoRa", "flushLoRa", "getFace", "Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "getLoRaType", "Lcom/pudutech/robot/peripherals/hola/LoRaType;", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "initLoRa", "context", "Landroid/content/Context;", "loRaStatusCallback", "Lcom/pudutech/robot/peripherals/hola/ILoRaStatusCallback;", "loRaDataReceivedCallback", "Lcom/pudutech/robot/peripherals/hola/ILoRaDataReceivedCallback;", "initNFC", "nfcSwipeCardListener", "Lcom/pudutech/robot/peripherals/hola/INFCSwipeCardListener;", "openLoRa", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", C3898x.f4339h, "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;)V", "removeFunctionPanelListener", "resetField", "sendData", "data", "", "setFace", "expression", "setLightMode", "led", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/hola/LightColor;", "lightMode", "Lcom/pudutech/robot/peripherals/hola/LightMode;", "params2", "Lkotlin/UByte;", "params1", "params0", "setLightMode-3UDGHhA", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/hola/LightColor;Lcom/pudutech/robot/peripherals/hola/LightMode;BBB)V", "setLightRGB", "red", "green", "blue", "setLightRGB-Fh2MPcY", "(Lcom/pudutech/robot/peripherals/config/LightBeltType;BBB)V", "setLoRaType", "loRaType", "startTimeoutJob", "stopTimeoutJob", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MockHolaRobotPeripherals extends CommonRobotPeripherals implements IHolaBotPeripherals {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MockHolaRobotPeripherals>() { // from class: com.pudutech.robot.peripherals.mock.MockHolaRobotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MockHolaRobotPeripherals invoke() {
            return new MockHolaRobotPeripherals();
        }
    });
    private static final String TAG = "MockHolaRobotPeripherals";
    private static final int TIMEOUT = 3000;
    private ArrayList<Hatch> currentControlHatchs;
    private IHatchsControlListener hatchsControlListener;
    private boolean isOpen;
    private Job timeoutJob;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[HatchesStatus.values().length];

        static {
            $EnumSwitchMapping$0[HatchesStatus.Opening.ordinal()] = 1;
            $EnumSwitchMapping$0[HatchesStatus.Closing.ordinal()] = 2;
            $EnumSwitchMapping$0[HatchesStatus.Opened.ordinal()] = 3;
            $EnumSwitchMapping$0[HatchesStatus.OpenFailed.ordinal()] = 4;
            $EnumSwitchMapping$0[HatchesStatus.Closed.ordinal()] = 5;
            $EnumSwitchMapping$0[HatchesStatus.CloseFailed.ordinal()] = 6;
        }
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void setMute(boolean z) {
    }

    /* compiled from: MockHolaRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/mock/MockHolaRobotPeripherals$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/robot/peripherals/mock/MockHolaRobotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/mock/MockHolaRobotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "TIMEOUT", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final MockHolaRobotPeripherals getINSTANCE() {
            Lazy lazy = MockHolaRobotPeripherals.INSTANCE$delegate;
            Companion companion = MockHolaRobotPeripherals.INSTANCE;
            return (MockHolaRobotPeripherals) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public byte[] getRecvCmds() {
        return new byte[]{CANConfig.INSTANCE.getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH()};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callbackHatchsControlState(ArrayList<Hatch> hatchs, HatchesStatus state) {
        Pdlog.m3273d(TAG, "callbackHatchsControlState() hatchs = " + hatchs + ", state = " + state);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new MockHolaRobotPeripherals$callbackHatchsControlState$1(this, hatchs, state, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetField() {
        this.hatchsControlListener = (IHatchsControlListener) null;
        this.isOpen = false;
        this.currentControlHatchs = (ArrayList) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimeoutJob() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new MockHolaRobotPeripherals$startTimeoutJob$1(this, null), 3, null);
        this.timeoutJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopTimeoutJob() {
        Job job = this.timeoutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.timeoutJob = (Job) null;
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public boolean isMute() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public LoRaType getLoRaType() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void setLoRaType(LoRaType loRaType) {
        Intrinsics.checkParameterIsNotNull(loRaType, "loRaType");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void initLoRa(Context context, ILoRaStatusCallback loRaStatusCallback, ILoRaDataReceivedCallback loRaDataReceivedCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void openLoRa() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void closeLoRa() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void flushLoRa() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void sendData(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void initNFC(INFCSwipeCardListener nfcSwipeCardListener) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void addFunctionPanelListener(IFunctionPanelListener listener) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void removeFunctionPanelListener(IFunctionPanelListener listener) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    /* renamed from: setLightRGB-Fh2MPcY */
    public void mo4499setLightRGBFh2MPcY(LightBeltType led, byte red, byte green, byte blue) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    /* renamed from: setLightMode-3UDGHhA */
    public void mo4498setLightMode3UDGHhA(LightBeltType[] led, LightColor color, LightMode lightMode, byte params2, byte params1, byte params0) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        Intrinsics.checkParameterIsNotNull(color, "color");
        Intrinsics.checkParameterIsNotNull(lightMode, "lightMode");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void setFace(ExpressionType expression) {
        Intrinsics.checkParameterIsNotNull(expression, "expression");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public ExpressionType getFace() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.robot.peripherals.hola.IHolaBotPeripherals
    public void playBreathing(LightBeltType[] lightBelts, LightBeltAnimationFrame f) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(f, "f");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }
}
