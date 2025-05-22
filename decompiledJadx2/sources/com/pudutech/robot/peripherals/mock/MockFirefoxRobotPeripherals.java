package com.pudutech.robot.peripherals.mock;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.firefox.Hatch;
import com.pudutech.robot.peripherals.firefox.HatchesStatus;
import com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals;
import com.pudutech.robot.peripherals.firefox.IHatchsControlListener;
import com.pudutech.robot.peripherals.manager.CANConfig;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: MockFirefoxRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0001!B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J(\u0010\u000e\u001a\u00020\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J2\u0010\u0013\u001a\u00020\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u0014\u001a\u00020\u0015H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0016J)\u0010\u0017\u001a\u00020\u000f2\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0\u0019\"\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u0010\u001f\u001a\u00020\u000fH\u0002J\b\u0010 \u001a\u00020\u000fH\u0002R\"\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, m3961d2 = {"Lcom/pudutech/robot/peripherals/mock/MockFirefoxRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/firefox/IFirefoxRobotPeripherals;", "()V", "currentControlHatchs", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/firefox/Hatch;", "Lkotlin/collections/ArrayList;", "hatchsControlListener", "Lcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;", "isOpen", "", "timeoutJob", "Lkotlinx/coroutines/Job;", "callbackHatchsControlState", "", "hatchs", "state", "Lcom/pudutech/robot/peripherals/firefox/HatchesStatus;", "controlHatch", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "resetField", "startTimeoutJob", "stopTimeoutJob", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MockFirefoxRobotPeripherals extends CommonRobotPeripherals implements IFirefoxRobotPeripherals {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MockFirefoxRobotPeripherals>() { // from class: com.pudutech.robot.peripherals.mock.MockFirefoxRobotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MockFirefoxRobotPeripherals invoke() {
            return new MockFirefoxRobotPeripherals(null);
        }
    });
    private static final String TAG = "MockRobotPeripherals";
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

    @Override // com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals
    public void playBreathing(LightBeltType[] lightBelts, BreathingLightColor color) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(color, "color");
    }

    private MockFirefoxRobotPeripherals() {
    }

    public /* synthetic */ MockFirefoxRobotPeripherals(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: MockFirefoxRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/mock/MockFirefoxRobotPeripherals$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/robot/peripherals/mock/MockFirefoxRobotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/mock/MockFirefoxRobotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "TIMEOUT", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final MockFirefoxRobotPeripherals getINSTANCE() {
            Lazy lazy = MockFirefoxRobotPeripherals.INSTANCE$delegate;
            Companion companion = MockFirefoxRobotPeripherals.INSTANCE;
            return (MockFirefoxRobotPeripherals) lazy.getValue();
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

    @Override // com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals
    public void controlHatch(ArrayList<Hatch> hatchs, boolean isOpen, IHatchsControlListener hatchsControlListener) {
        Intrinsics.checkParameterIsNotNull(hatchs, "hatchs");
        Pdlog.m3273d(TAG, "controlHatch() hatchs = " + hatchs + ", hatchControlListener = " + hatchsControlListener);
        this.currentControlHatchs = hatchs;
        this.isOpen = isOpen;
        this.hatchsControlListener = hatchsControlListener;
        callbackHatchsControlState(hatchs, isOpen ? HatchesStatus.Opening : HatchesStatus.Closing);
        if (hatchs.isEmpty()) {
            callbackHatchsControlState(hatchs, isOpen ? HatchesStatus.OpenFailed : HatchesStatus.CloseFailed);
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new MockFirefoxRobotPeripherals$controlHatch$1(this, hatchs, isOpen, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callbackHatchsControlState(ArrayList<Hatch> hatchs, HatchesStatus state) {
        Pdlog.m3273d(TAG, "callbackHatchsControlState() hatchs = " + hatchs + ", state = " + state);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new MockFirefoxRobotPeripherals$callbackHatchsControlState$1(this, hatchs, state, null), 3, null);
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
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new MockFirefoxRobotPeripherals$startTimeoutJob$1(this, null), 3, null);
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
}
