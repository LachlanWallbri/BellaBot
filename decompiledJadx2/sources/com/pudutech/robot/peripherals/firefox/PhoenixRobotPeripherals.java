package com.pudutech.robot.peripherals.firefox;

import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.common.LightBeltsPlayHelper;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.manager.CANConfig;
import java.lang.ref.WeakReference;
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
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: PhoenixRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0001BB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J,\u0010\u001d\u001a\u00020\u001e2\u001a\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011j\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001`\u00132\u0006\u0010 \u001a\u00020\fH\u0002J*\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018ø\u0001\u0000¢\u0006\u0004\b$\u0010%JD\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\b2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u00132\u0006\u0010#\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u0018\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020,H\u0016ø\u0001\u0000¢\u0006\u0002\u0010-J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u000200H\u0002J\u0006\u00101\u001a\u00020\u001eJ\u0006\u0010\u0019\u001a\u00020\u0005J!\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\n2\u0006\u0010/\u001a\u000200H\u0096@ø\u0001\u0000¢\u0006\u0002\u00104J)\u00105\u001a\u00020\u001e2\u0012\u00106\u001a\n\u0012\u0006\b\u0001\u0012\u00020807\"\u0002082\u0006\u00109\u001a\u00020:H\u0016¢\u0006\u0002\u0010;J\u0006\u0010<\u001a\u00020\u001eJ\b\u0010=\u001a\u00020\u001eH\u0002J\u000e\u0010>\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020\u0016J\b\u0010@\u001a\u00020\u001eH\u0002J\b\u0010A\u001a\u00020\u001eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000eø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u00020\bX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011j\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001`\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006C"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/PhoenixRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/firefox/IPhoenixRobotPeripherals;", "()V", "canInitHatch", "", "controlHatchStates", "Landroid/util/SparseArray;", "Lkotlin/UByte;", "controlHatchsRespCount", "", "curHatchStatus", "Lcom/pudutech/robot/peripherals/firefox/HatchesStatus;", "curOperation", "B", "curSendDataCount", "currentControlHatchs", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/peripherals/firefox/Hatch;", "Lkotlin/collections/ArrayList;", "hatchsControlErrorListener", "Ljava/lang/ref/WeakReference;", "Lcom/pudutech/robot/peripherals/firefox/IHatchControlErrorListener;", "hatchsControlListener", "Lcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;", "isEemergencyKeyOpen", "isOpen", "timeoutJob", "Lkotlinx/coroutines/Job;", "callbackHatchsControlState", "", "hatchs", "state", "controlHatch", "operation", "isOpen_", "controlHatch-jqtAJmk", "(BZLcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;)V", "controlHatch-OM5YLlI", "(BLjava/util/ArrayList;ZLcom/pudutech/robot/peripherals/firefox/IHatchsControlListener;)V", "convertHatchNumber", "hatch", "(Lcom/pudutech/robot/peripherals/firefox/Hatch;)B", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "handleControlHatchsRespData", "data", "", "initHatch", "parseData", "id", "(I[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playBreathing", "lightBelts", "", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", TypedValues.Custom.S_COLOR, "Lcom/pudutech/robot/peripherals/common/BreathingLightColor;", "([Lcom/pudutech/robot/peripherals/config/LightBeltType;Lcom/pudutech/robot/peripherals/common/BreathingLightColor;)V", "removeHatchControllErrorListener", "resetField", "setHatchControllErrorListener", "listener", "startTimeoutJob", "stopTimeoutJob", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PhoenixRobotPeripherals extends CommonRobotPeripherals implements IPhoenixRobotPeripherals {
    private static final byte CONTROL_HATCH_CLOSE;
    private static final byte CONTROL_HATCH_OPEN;
    private static final byte CONTROL_HATCH_OPERATION_ASK;
    private static final byte CONTROL_HATCH_OPERATION_ASK_RESPONSE;
    private static final byte CONTROL_HATCH_OPERATION_INIT;
    private static final byte CONTROL_HATCH_OPERATION_OPEN_CLOSE;
    private static final byte CONTROL_HATCH_RESP_CLOSE_SUCCEED;
    private static final byte CONTROL_HATCH_RESP_FAILED_MOTOR_OVER_CURRENT;
    private static final byte CONTROL_HATCH_RESP_FAILED_OTHER;
    private static final byte CONTROL_HATCH_RESP_OPEN_SUCCEED;
    private static final int RETRY_COUNT = 3;
    private static final String TAG = "PhoenixRobotPeripherals";
    private static final double TIMEOUT = 3500.0d;
    private boolean canInitHatch;
    private SparseArray<UByte> controlHatchStates;
    private int controlHatchsRespCount;
    private HatchesStatus curHatchStatus;
    private byte curOperation;
    private int curSendDataCount;
    private ArrayList<Hatch> currentControlHatchs;
    private WeakReference<IHatchControlErrorListener> hatchsControlErrorListener;
    private WeakReference<IHatchsControlListener> hatchsControlListener;
    private boolean isEemergencyKeyOpen;
    private boolean isOpen;
    private Job timeoutJob;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte CONTROL_HATCH_OPERATION_OPEN_CLOSE_CALLBACK = UByte.m4528constructorimpl((byte) 4);
    private static final byte CONTROL_HATCH_OPERATION_OPEN_CLOSE_RESPONSE = UByte.m4528constructorimpl((byte) 5);
    private static final byte CONTROL_HATCH_OPERATION_RESPONSE_CALLBACK = UByte.m4528constructorimpl((byte) 6);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<PhoenixRobotPeripherals>() { // from class: com.pudutech.robot.peripherals.firefox.PhoenixRobotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PhoenixRobotPeripherals invoke() {
            return new PhoenixRobotPeripherals(null);
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
            $EnumSwitchMapping$0[HatchesStatus.OpenFailed.ordinal()] = 3;
            $EnumSwitchMapping$0[HatchesStatus.CloseFailed.ordinal()] = 4;
            $EnumSwitchMapping$0[HatchesStatus.Closed.ordinal()] = 5;
            $EnumSwitchMapping$0[HatchesStatus.Opened.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[Hatch.values().length];
            $EnumSwitchMapping$1[Hatch.H_01.ordinal()] = 1;
            $EnumSwitchMapping$1[Hatch.H_02.ordinal()] = 2;
            $EnumSwitchMapping$1[Hatch.H_03.ordinal()] = 3;
            $EnumSwitchMapping$1[Hatch.H_04.ordinal()] = 4;
        }
    }

    private PhoenixRobotPeripherals() {
        this.controlHatchStates = new SparseArray<>();
        this.curOperation = UByte.m4528constructorimpl((byte) 0);
        this.curHatchStatus = HatchesStatus.Closing;
        this.isEemergencyKeyOpen = true;
        addHardWareConnectListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.robot.peripherals.firefox.PhoenixRobotPeripherals.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Pdlog.m3273d(PhoenixRobotPeripherals.TAG, "HardWareConnectListener connect = " + z);
                PhoenixRobotPeripherals.this.canInitHatch = z;
            }
        });
    }

    public /* synthetic */ PhoenixRobotPeripherals(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: PhoenixRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0007\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000b\u0010\tR\u0016\u0010\f\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\r\u0010\tR\u0016\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\tR\u0016\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0011\u0010\tR\u0016\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0013\u0010\tR\u0016\u0010\u0014\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0015\u0010\tR\u0013\u0010\u0016\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0017\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0018\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0019\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u001b\u0010\u001a\u001a\u00020\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010 \u001a\u00020!X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/PhoenixRobotPeripherals$Companion;", "", "()V", "CONTROL_HATCH_CLOSE", "Lkotlin/UByte;", "B", "CONTROL_HATCH_OPEN", "CONTROL_HATCH_OPERATION_ASK", "getCONTROL_HATCH_OPERATION_ASK", "()B", "CONTROL_HATCH_OPERATION_ASK_RESPONSE", "getCONTROL_HATCH_OPERATION_ASK_RESPONSE", "CONTROL_HATCH_OPERATION_INIT", "getCONTROL_HATCH_OPERATION_INIT", "CONTROL_HATCH_OPERATION_OPEN_CLOSE", "getCONTROL_HATCH_OPERATION_OPEN_CLOSE", "CONTROL_HATCH_OPERATION_OPEN_CLOSE_CALLBACK", "getCONTROL_HATCH_OPERATION_OPEN_CLOSE_CALLBACK", "CONTROL_HATCH_OPERATION_OPEN_CLOSE_RESPONSE", "getCONTROL_HATCH_OPERATION_OPEN_CLOSE_RESPONSE", "CONTROL_HATCH_OPERATION_RESPONSE_CALLBACK", "getCONTROL_HATCH_OPERATION_RESPONSE_CALLBACK", "CONTROL_HATCH_RESP_CLOSE_SUCCEED", "CONTROL_HATCH_RESP_FAILED_MOTOR_OVER_CURRENT", "CONTROL_HATCH_RESP_FAILED_OTHER", "CONTROL_HATCH_RESP_OPEN_SUCCEED", "INSTANCE", "Lcom/pudutech/robot/peripherals/firefox/PhoenixRobotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/firefox/PhoenixRobotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "RETRY_COUNT", "", "TAG", "", "TIMEOUT", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final PhoenixRobotPeripherals getINSTANCE() {
            Lazy lazy = PhoenixRobotPeripherals.INSTANCE$delegate;
            Companion companion = PhoenixRobotPeripherals.INSTANCE;
            return (PhoenixRobotPeripherals) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte getCONTROL_HATCH_OPERATION_INIT() {
            return PhoenixRobotPeripherals.CONTROL_HATCH_OPERATION_INIT;
        }

        public final byte getCONTROL_HATCH_OPERATION_ASK() {
            return PhoenixRobotPeripherals.CONTROL_HATCH_OPERATION_ASK;
        }

        public final byte getCONTROL_HATCH_OPERATION_ASK_RESPONSE() {
            return PhoenixRobotPeripherals.CONTROL_HATCH_OPERATION_ASK_RESPONSE;
        }

        public final byte getCONTROL_HATCH_OPERATION_OPEN_CLOSE() {
            return PhoenixRobotPeripherals.CONTROL_HATCH_OPERATION_OPEN_CLOSE;
        }

        public final byte getCONTROL_HATCH_OPERATION_OPEN_CLOSE_CALLBACK() {
            return PhoenixRobotPeripherals.CONTROL_HATCH_OPERATION_OPEN_CLOSE_CALLBACK;
        }

        public final byte getCONTROL_HATCH_OPERATION_OPEN_CLOSE_RESPONSE() {
            return PhoenixRobotPeripherals.CONTROL_HATCH_OPERATION_OPEN_CLOSE_RESPONSE;
        }

        public final byte getCONTROL_HATCH_OPERATION_RESPONSE_CALLBACK() {
            return PhoenixRobotPeripherals.CONTROL_HATCH_OPERATION_RESPONSE_CALLBACK;
        }
    }

    static {
        byte b = (byte) 3;
        CONTROL_HATCH_OPEN = UByte.m4528constructorimpl(b);
        byte b2 = (byte) 0;
        CONTROL_HATCH_CLOSE = UByte.m4528constructorimpl(b2);
        CONTROL_HATCH_RESP_OPEN_SUCCEED = UByte.m4528constructorimpl(b2);
        byte b3 = (byte) 1;
        CONTROL_HATCH_RESP_CLOSE_SUCCEED = UByte.m4528constructorimpl(b3);
        byte b4 = (byte) 2;
        CONTROL_HATCH_RESP_FAILED_MOTOR_OVER_CURRENT = UByte.m4528constructorimpl(b4);
        CONTROL_HATCH_RESP_FAILED_OTHER = UByte.m4528constructorimpl(b);
        CONTROL_HATCH_OPERATION_INIT = UByte.m4528constructorimpl(b2);
        CONTROL_HATCH_OPERATION_ASK = UByte.m4528constructorimpl(b3);
        CONTROL_HATCH_OPERATION_ASK_RESPONSE = UByte.m4528constructorimpl(b4);
        CONTROL_HATCH_OPERATION_OPEN_CLOSE = UByte.m4528constructorimpl(b);
    }

    /* renamed from: isEemergencyKeyOpen, reason: from getter */
    public final boolean getIsEemergencyKeyOpen() {
        return this.isEemergencyKeyOpen;
    }

    public final void initHatch() {
        Pdlog.m3273d(TAG, "initHatch");
        m4494controlHatchjqtAJmk(CONTROL_HATCH_OPERATION_INIT, false, new IHatchsControlListener() { // from class: com.pudutech.robot.peripherals.firefox.PhoenixRobotPeripherals$initHatch$1
            @Override // com.pudutech.robot.peripherals.firefox.IHatchsControlListener
            public void callbackState(ArrayList<Hatch> hatchs, HatchesStatus state) {
                Intrinsics.checkParameterIsNotNull(state, "state");
                if (state == HatchesStatus.Closed) {
                    PhoenixRobotPeripherals.this.curHatchStatus = HatchesStatus.Closed;
                }
            }
        });
    }

    public final void setHatchControllErrorListener(IHatchControlErrorListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.hatchsControlErrorListener = new WeakReference<>(listener);
    }

    public final void removeHatchControllErrorListener() {
        this.hatchsControlErrorListener = (WeakReference) null;
    }

    /* renamed from: controlHatch-jqtAJmk, reason: not valid java name */
    public final void m4494controlHatchjqtAJmk(byte operation, boolean isOpen_, IHatchsControlListener hatchsControlListener) {
        mo4492controlHatchOM5YLlI(operation, CollectionsKt.arrayListOf(Hatch.H_03), isOpen_, hatchsControlListener);
    }

    @Override // com.pudutech.robot.peripherals.firefox.IPhoenixRobotPeripherals
    /* renamed from: controlHatch-OM5YLlI */
    public void mo4492controlHatchOM5YLlI(byte operation, ArrayList<Hatch> hatchs, boolean isOpen_, IHatchsControlListener hatchsControlListener) {
        Intrinsics.checkParameterIsNotNull(hatchs, "hatchs");
        Pdlog.m3273d(TAG, "controlHatch() isOpen_ = " + isOpen_ + ", operation = " + UByte.m4563toStringimpl(operation));
        resetField();
        this.curSendDataCount = this.curSendDataCount + 1;
        this.controlHatchsRespCount = 0;
        this.currentControlHatchs = hatchs;
        this.isOpen = isOpen_;
        this.hatchsControlListener = new WeakReference<>(hatchsControlListener);
        this.curOperation = operation;
        if (this.curOperation == CONTROL_HATCH_OPERATION_INIT) {
            this.isOpen = false;
        }
        callbackHatchsControlState(hatchs, this.isOpen ? HatchesStatus.Opening : HatchesStatus.Closing);
        if (hatchs.isEmpty()) {
            callbackHatchsControlState(hatchs, this.isOpen ? HatchesStatus.OpenFailed : HatchesStatus.CloseFailed);
        }
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new PhoenixRobotPeripherals$controlHatch$1(this, hatchs, operation, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callbackHatchsControlState(ArrayList<Hatch> hatchs, HatchesStatus state) {
        Pdlog.m3273d(TAG, "callbackHatchsControlState() hatchs = " + hatchs + ", state = " + state);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new PhoenixRobotPeripherals$callbackHatchsControlState$1(this, hatchs, state, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte convertHatchNumber(Hatch hatch) {
        int i = WhenMappings.$EnumSwitchMapping$1[hatch.ordinal()];
        if (i == 1) {
            return UByte.m4528constructorimpl((byte) 3);
        }
        if (i == 2) {
            return UByte.m4528constructorimpl((byte) 3);
        }
        if (i == 3) {
            return UByte.m4528constructorimpl((byte) 3);
        }
        if (i == 4) {
            return UByte.m4528constructorimpl((byte) 3);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetField() {
        this.hatchsControlListener = (WeakReference) null;
        this.isOpen = false;
        this.currentControlHatchs = (ArrayList) null;
        this.controlHatchStates.clear();
        stopTimeoutJob();
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public Object parseData(int i, byte[] bArr, Continuation<? super Unit> continuation) {
        boolean z = true;
        StringBuilder sb = new StringBuilder();
        sb.append("parseData() id = ");
        sb.append(i);
        sb.append(", data = ");
        String arrays = Arrays.toString(bArr);
        Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
        sb.append(arrays);
        sb.append(", dataLength = ");
        sb.append(bArr.length);
        Pdlog.m3273d(TAG, sb.toString());
        byte m4528constructorimpl = UByte.m4528constructorimpl((byte) i);
        if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH()) {
            handleControlHatchsRespData(bArr);
        } else if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_47_PROTOCOL()) {
            try {
                if (((bArr[1] >> 7) & 1) != 1) {
                    z = false;
                }
                this.isEemergencyKeyOpen = z;
            } catch (Exception unused) {
            }
        }
        return Unit.INSTANCE;
    }

    @Override // com.pudutech.robot.peripherals.firefox.IPhoenixRobotPeripherals
    public void playBreathing(LightBeltType[] lightBelts, BreathingLightColor color) {
        Intrinsics.checkParameterIsNotNull(lightBelts, "lightBelts");
        Intrinsics.checkParameterIsNotNull(color, "color");
        LightBeltsPlayHelper.INSTANCE.playBreathing((LightBeltType[]) Arrays.copyOf(lightBelts, lightBelts.length), color);
    }

    private final void handleControlHatchsRespData(byte[] data) {
        WeakReference<IHatchControlErrorListener> weakReference;
        IHatchControlErrorListener iHatchControlErrorListener;
        if ((data.length == 0) || data.length != 8) {
            Pdlog.m3273d(TAG, "handleControlHatchsRespData data is null");
            return;
        }
        byte m4528constructorimpl = UByte.m4528constructorimpl(data[1]);
        Pdlog.m3273d(TAG, "handleControlHatchsRespData responseOperation = " + UByte.m4563toStringimpl(m4528constructorimpl));
        if (m4528constructorimpl == CONTROL_HATCH_OPERATION_ASK_RESPONSE || m4528constructorimpl == CONTROL_HATCH_OPERATION_OPEN_CLOSE_RESPONSE) {
            this.controlHatchsRespCount++;
            byte b = data[2];
            byte m4528constructorimpl2 = UByte.m4528constructorimpl(data[3]);
            byte m4528constructorimpl3 = UByte.m4528constructorimpl(data[4]);
            Pdlog.m3273d(TAG, "handleControlHatchsRespData hatchState = " + UByte.m4563toStringimpl(m4528constructorimpl2) + " curOperation = " + UByte.m4563toStringimpl(this.curOperation) + "  isOpen = " + this.isOpen);
            if ((this.isOpen && m4528constructorimpl2 != CONTROL_HATCH_RESP_OPEN_SUCCEED) || (!this.isOpen && m4528constructorimpl2 != CONTROL_HATCH_RESP_CLOSE_SUCCEED)) {
                if (this.curOperation == CONTROL_HATCH_OPERATION_INIT) {
                    byte b2 = CONTROL_HATCH_OPERATION_OPEN_CLOSE;
                    this.curOperation = b2;
                    m4494controlHatchjqtAJmk(b2, false, null);
                } else {
                    Pdlog.m3273d(TAG, "errorState " + UByte.m4563toStringimpl(m4528constructorimpl3));
                    int i = ((m4528constructorimpl3 & 255) >>> 3) & 1;
                    if (i == 1) {
                        Pdlog.m3273d(TAG, "(errorstate.toInt().ushr(3)) and 1) " + i);
                        startTimeoutJob();
                    } else {
                        callbackHatchsControlState(this.currentControlHatchs, this.isOpen ? HatchesStatus.OpenFailed : HatchesStatus.CloseFailed);
                    }
                }
                int i2 = m4528constructorimpl3 & 255;
                if (i2 == 0 || this.controlHatchsRespCount != 2 || (weakReference = this.hatchsControlErrorListener) == null || (iHatchControlErrorListener = weakReference.get()) == null) {
                    return;
                }
                iHatchControlErrorListener.errorState(HatchesErrorStatus.INSTANCE.byValue(i2));
                return;
            }
            if (m4528constructorimpl == CONTROL_HATCH_OPERATION_OPEN_CLOSE_RESPONSE) {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new PhoenixRobotPeripherals$handleControlHatchsRespData$1(this, data, null), 3, null);
            }
            if (this.controlHatchsRespCount == 2) {
                callbackHatchsControlState(this.currentControlHatchs, this.isOpen ? HatchesStatus.Opened : HatchesStatus.Closed);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimeoutJob() {
        Job launch$default;
        Job job = this.timeoutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.timeoutJob = (Job) null;
        launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new PhoenixRobotPeripherals$startTimeoutJob$1(this, null), 3, null);
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
