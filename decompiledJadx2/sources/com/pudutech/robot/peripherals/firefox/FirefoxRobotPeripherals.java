package com.pudutech.robot.peripherals.firefox;

import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.common.LightBeltsPlayHelper;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.manager.CANConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: FirefoxRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0001/B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J(\u0010\u0013\u001a\u00020\u00142\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J2\u0010\u0018\u001a\u00020\u00142\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u001dH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J!\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\b2\u0006\u0010 \u001a\u00020!H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010$J)\u0010%\u001a\u00020\u00142\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020(0'\"\u00020(2\u0006\u0010)\u001a\u00020*H\u0016¢\u0006\u0002\u0010+J\b\u0010,\u001a\u00020\u0014H\u0002J\b\u0010-\u001a\u00020\u0014H\u0002J\b\u0010.\u001a\u00020\u0014H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000eø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/FirefoxRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/firefox/IFirefoxRobotPeripherals;", "()V", "controlHatchStates", "Landroid/util/SparseArray;", "Lkotlin/UByte;", "controlHatchsRespCount", "", "currentControlHatchs", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/firefox/Hatch;", "Lkotlin/collections/ArrayList;", "hatchsControlListener", "Lcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;", "isOpen", "", "timeoutJob", "Lkotlinx/coroutines/Job;", "callbackHatchsControlState", "", "hatchs", "state", "Lcom/pudutech/robot/peripherals/firefox/HatchesStatus;", "controlHatch", "convertHatchNumber", "hatch", "(Lcom/pudutech/robot/peripherals/firefox/Hatch;)B", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "handleControlHatchsRespData", "data", "", "parseData", "id", "(I[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "resetField", "startTimeoutJob", "stopTimeoutJob", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FirefoxRobotPeripherals extends CommonRobotPeripherals implements IFirefoxRobotPeripherals {
    private static final byte CONTROL_HATCH_CLOSE;
    private static final byte CONTROL_HATCH_OPEN;
    private static final byte CONTROL_HATCH_RESP_FAILED_MOTOR_OVER_CURRENT;
    private static final byte CONTROL_HATCH_RESP_SUCCEED;
    private static final String TAG = "FirefoxRobotPeripherals";
    private static final int TIMEOUT = 20000;
    private SparseArray<UByte> controlHatchStates;
    private int controlHatchsRespCount;
    private ArrayList<Hatch> currentControlHatchs;
    private IHatchsControlListener hatchsControlListener;
    private boolean isOpen;
    private Job timeoutJob;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte CONTROL_HATCH_RESP_FAILED_OTHER = UByte.m4528constructorimpl((byte) 2);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<FirefoxRobotPeripherals>() { // from class: com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FirefoxRobotPeripherals invoke() {
            return new FirefoxRobotPeripherals(null);
        }
    });

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[HatchesStatus.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[HatchesStatus.Opening.ordinal()] = 1;
            $EnumSwitchMapping$0[HatchesStatus.Closing.ordinal()] = 2;
            $EnumSwitchMapping$0[HatchesStatus.Opened.ordinal()] = 3;
            $EnumSwitchMapping$0[HatchesStatus.OpenFailed.ordinal()] = 4;
            $EnumSwitchMapping$0[HatchesStatus.Closed.ordinal()] = 5;
            $EnumSwitchMapping$0[HatchesStatus.CloseFailed.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[Hatch.values().length];
            $EnumSwitchMapping$1[Hatch.H_01.ordinal()] = 1;
            $EnumSwitchMapping$1[Hatch.H_02.ordinal()] = 2;
            $EnumSwitchMapping$1[Hatch.H_03.ordinal()] = 3;
            $EnumSwitchMapping$1[Hatch.H_04.ordinal()] = 4;
        }
    }

    private FirefoxRobotPeripherals() {
        this.controlHatchStates = new SparseArray<>();
    }

    public /* synthetic */ FirefoxRobotPeripherals(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: FirefoxRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0007\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\b\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\t\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/FirefoxRobotPeripherals$Companion;", "", "()V", "CONTROL_HATCH_CLOSE", "Lkotlin/UByte;", "B", "CONTROL_HATCH_OPEN", "CONTROL_HATCH_RESP_FAILED_MOTOR_OVER_CURRENT", "CONTROL_HATCH_RESP_FAILED_OTHER", "CONTROL_HATCH_RESP_SUCCEED", "INSTANCE", "Lcom/pudutech/robot/peripherals/firefox/FirefoxRobotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/firefox/FirefoxRobotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "TIMEOUT", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final FirefoxRobotPeripherals getINSTANCE() {
            Lazy lazy = FirefoxRobotPeripherals.INSTANCE$delegate;
            Companion companion = FirefoxRobotPeripherals.INSTANCE;
            return (FirefoxRobotPeripherals) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        byte b = (byte) 0;
        CONTROL_HATCH_OPEN = UByte.m4528constructorimpl(b);
        byte b2 = (byte) 1;
        CONTROL_HATCH_CLOSE = UByte.m4528constructorimpl(b2);
        CONTROL_HATCH_RESP_SUCCEED = UByte.m4528constructorimpl(b);
        CONTROL_HATCH_RESP_FAILED_MOTOR_OVER_CURRENT = UByte.m4528constructorimpl(b2);
    }

    @Override // com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals
    public void controlHatch(ArrayList<Hatch> hatchs, boolean isOpen, IHatchsControlListener hatchsControlListener) {
        Intrinsics.checkParameterIsNotNull(hatchs, "hatchs");
        Pdlog.m3273d(TAG, "controlHatch hatchs:" + hatchs + ", isOpen:" + isOpen + ", hatchControlListener:" + hatchsControlListener);
        this.currentControlHatchs = hatchs;
        this.isOpen = isOpen;
        this.hatchsControlListener = hatchsControlListener;
        callbackHatchsControlState(hatchs, isOpen ? HatchesStatus.Opening : HatchesStatus.Closing);
        if (hatchs.isEmpty()) {
            callbackHatchsControlState(hatchs, isOpen ? HatchesStatus.OpenFailed : HatchesStatus.CloseFailed);
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new FirefoxRobotPeripherals$controlHatch$1(this, hatchs, isOpen, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callbackHatchsControlState(ArrayList<Hatch> hatchs, HatchesStatus state) {
        Pdlog.m3273d(TAG, "callbackHatchsControlState hatchs:" + hatchs + ", state:" + state);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new FirefoxRobotPeripherals$callbackHatchsControlState$1(this, hatchs, state, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte convertHatchNumber(Hatch hatch) {
        int i = WhenMappings.$EnumSwitchMapping$1[hatch.ordinal()];
        if (i == 1) {
            return UByte.m4528constructorimpl((byte) 1);
        }
        if (i == 2) {
            return UByte.m4528constructorimpl((byte) 2);
        }
        if (i == 3) {
            return UByte.m4528constructorimpl((byte) 3);
        }
        if (i == 4) {
            return UByte.m4528constructorimpl((byte) 4);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetField() {
        this.hatchsControlListener = (IHatchsControlListener) null;
        this.isOpen = false;
        this.currentControlHatchs = (ArrayList) null;
        this.controlHatchsRespCount = 0;
        this.controlHatchStates.clear();
        Pdlog.m3273d(TAG, "4.处理舱门响应完毕，重置状态");
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public Object parseData(int i, byte[] bArr, Continuation<? super Unit> continuation) {
        StringBuilder sb = new StringBuilder();
        sb.append("parse receive data id = ");
        sb.append(i);
        sb.append(", data = ");
        String arrays = Arrays.toString(bArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        sb.append(", dataLength = ");
        sb.append(bArr.length);
        Pdlog.m3273d(TAG, sb.toString());
        if (UByte.m4528constructorimpl((byte) i) == CANConfig.INSTANCE.getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH()) {
            handleControlHatchsRespData(bArr);
        }
        return Unit.INSTANCE;
    }

    @Override // com.pudutech.robot.peripherals.firefox.IFirefoxRobotPeripherals
    public void playBreathing(LightBeltType[] lightBelts, BreathingLightColor color) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(color, "color");
        LightBeltsPlayHelper.INSTANCE.playBreathing((LightBeltType[]) Arrays.copyOf(lightBelts, lightBelts.length), color);
    }

    private final void handleControlHatchsRespData(byte[] data) {
        this.controlHatchsRespCount++;
        Pdlog.m3273d(TAG, "1.舱门响应数据 count:" + this.controlHatchsRespCount + ", data size:" + data.length);
        if ((data.length == 0) || data.length != 8) {
            return;
        }
        byte b = data[3];
        byte m4528constructorimpl = UByte.m4528constructorimpl(data[2]);
        this.controlHatchStates.put(b, UByte.m4522boximpl(m4528constructorimpl));
        Pdlog.m3273d(TAG, "2.处理舱门响应数据 hatchNumber:" + ((int) b) + ", hatchState:" + UByte.m4563toStringimpl(m4528constructorimpl));
        ArrayList<Hatch> arrayList = this.currentControlHatchs;
        if (arrayList == null || this.controlHatchsRespCount != arrayList.size()) {
            return;
        }
        SparseArray<UByte> sparseArray = this.controlHatchStates;
        int size = sparseArray.size();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            sparseArray.keyAt(i);
            if (sparseArray.valueAt(i).getData() != CONTROL_HATCH_RESP_SUCCEED) {
                z = false;
            }
        }
        Pdlog.m3273d(TAG, "3.处理舱门响应数据 is succeed:" + z);
        if (!z) {
            callbackHatchsControlState(arrayList, this.isOpen ? HatchesStatus.OpenFailed : HatchesStatus.CloseFailed);
        } else {
            callbackHatchsControlState(arrayList, this.isOpen ? HatchesStatus.Opened : HatchesStatus.Closed);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimeoutJob() {
        Job launch$default;
        stopTimeoutJob();
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new FirefoxRobotPeripherals$startTimeoutJob$1(this, null), 3, null);
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

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public byte[] getRecvCmds() {
        return new byte[]{CANConfig.INSTANCE.getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH()};
    }
}
