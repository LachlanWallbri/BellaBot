package com.pudutech.mirsdkwrap.lib.robot;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.robot.module.report.track2.ChargeType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BatteryInfoManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0097\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r*\u0003\u0006\u000e,\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004XYZ[B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010?\u001a\u00020\u001d2<\u0010@\u001a8\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0017j\u0002`\u001eJ\u000e\u0010A\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020$J\u000e\u0010B\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020(Jl\u0010C\u001a\u00020\u001d2d\u0010@\u001a`\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(0\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(2\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(3\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u001d0/j\u0002`5J\u000e\u0010D\u001a\u00020\u001d2\u0006\u0010@\u001a\u000209J\u0006\u0010E\u001a\u00020\u001dJ\u0010\u0010F\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u0010\u0010G\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u000e\u0010H\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020\u0018J\u000e\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\tJ\u001d\u0010M\u001a\u00020\u001d2\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\u0014H\u0000¢\u0006\u0002\bQJ\u0006\u0010R\u001a\u00020\u0010J\u001a\u0010S\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\tH\u0002JD\u0010T\u001a\u00020\u001d2<\u0010@\u001a8\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0017j\u0002`\u001eJ\u000e\u0010T\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020$J\u000e\u0010U\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020(Jl\u0010V\u001a\u00020\u001d2d\u0010@\u001a`\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(0\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(2\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(3\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u001d0/j\u0002`5J\u000e\u0010W\u001a\u00020\u001d2\u0006\u0010@\u001a\u000209R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000RW\u0010\u0015\u001a>\u0012:\u00128\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0017j\u0002`\u001e0\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R!\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\"\u001a\u0004\b%\u0010 R!\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\"\u001a\u0004\b)\u0010 R\u0010\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0004\n\u0002\u0010-R\u007f\u0010.\u001af\u0012b\u0012`\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(0\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(2\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(3\u0012\u0013\u0012\u001101¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(4\u0012\u0004\u0012\u00020\u001d0/j\u0002`50\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010\"\u001a\u0004\b6\u0010 R!\u00108\u001a\b\u0012\u0004\u0012\u0002090\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\"\u001a\u0004\b:\u0010 R$\u0010\u001b\u001a\u0004\u0018\u00010\u00182\b\u0010\b\u001a\u0004\u0018\u00010\u0018@BX\u0086\u000e¢\u0006\n\n\u0002\u0010>\u001a\u0004\b<\u0010=¨\u0006\\"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager;", "", "()V", "TAG", "", "batteryFloorLevelLimitResultListener", "com/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$batteryFloorLevelLimitResultListener$1", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$batteryFloorLevelLimitResultListener$1;", "<set-?>", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "currentBatteryEvent", "getCurrentBatteryEvent", "()Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "faceDetectResultListener", "com/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$faceDetectResultListener$1", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$faceDetectResultListener$1;", "", "isCharging", "()Z", "mirsdk", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", "onBatteryDataListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "power", "chargeState", "", "Lcom/pudutech/mirsdkwrap/lib/interf/BatteryListener;", "getOnBatteryDataListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "onBatteryDataListeners$delegate", "Lkotlin/Lazy;", "onBatteryEventListeners", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnBatteryEventListener;", "getOnBatteryEventListeners", "onBatteryEventListeners$delegate", "onBatteryFloorLevelLimitResultListeners", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnBatteryFloorLevelLimitResultListener;", "getOnBatteryFloorLevelLimitResultListeners", "onBatteryFloorLevelLimitResultListeners$delegate", "onBatteryListener", "com/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$onBatteryListener$1", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$onBatteryListener$1;", "onFaceDetectListeners", "Lkotlin/Function4;", "p0", "", "p1", "p2", "p3", "Lcom/pudutech/mirsdkwrap/lib/interf/FaceDetectResultListener;", "getOnFaceDetectListeners", "onFaceDetectListeners$delegate", "onPowerChangerListeners", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnPowerChangeListener;", "getOnPowerChangerListeners", "onPowerChangerListeners$delegate", "getPower", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "addBatteryDataListener", "l", "addBatteryEventListener", "addOnBatteryFloorLevelLimitResultListener", "addOnFaceDetectListener", "addPowerChangeListener", "checkBatteryLevel", "checkIsCharging", "checkIsError", "controlBatteryLevel", "i", "getChargingType", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$ChargingType;", "event", "init", "mirsdkListener", "Lcom/pudutech/mirsdkwrap/lib/robot/MirSdkListenerWrap;", "it", "init$module_robot_mirsdk_wrapper_release", "isChargingPile", "notify", "removeBatteryEventListener", "removeOnBatteryFloorLevelLimitResultListener", "removeOnFaceDetectListeners", "removePowerChangeListener", "ChargingType", "OnBatteryEventListener", "OnBatteryFloorLevelLimitResultListener", "OnPowerChangeListener", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class BatteryInfoManager {
    private static boolean isCharging;
    private static SDKInterface mirsdk;
    private static Integer power;
    public static final BatteryInfoManager INSTANCE = new BatteryInfoManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static ChargeState currentBatteryEvent = ChargeState.Idle;

    /* renamed from: onPowerChangerListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onPowerChangerListeners = LazyKt.lazy(new Function0<ListenerList<OnPowerChangeListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$onPowerChangerListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<BatteryInfoManager.OnPowerChangeListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onBatteryEventListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onBatteryEventListeners = LazyKt.lazy(new Function0<ListenerList<OnBatteryEventListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$onBatteryEventListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<BatteryInfoManager.OnBatteryEventListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onBatteryDataListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onBatteryDataListeners = LazyKt.lazy(new Function0<ListenerList<Function2<? super Integer, ? super ChargeState, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$onBatteryDataListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function2<? super Integer, ? super ChargeState, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onBatteryFloorLevelLimitResultListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onBatteryFloorLevelLimitResultListeners = LazyKt.lazy(new Function0<ListenerList<OnBatteryFloorLevelLimitResultListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$onBatteryFloorLevelLimitResultListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<BatteryInfoManager.OnBatteryFloorLevelLimitResultListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onFaceDetectListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onFaceDetectListeners = LazyKt.lazy(new Function0<ListenerList<Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit>>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$onFaceDetectListeners$2
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit>> invoke() {
            return new ListenerList<>();
        }
    });
    private static final BatteryInfoManager$onBatteryListener$1 onBatteryListener = new Function2<Integer, ChargeState, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$onBatteryListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, ChargeState chargeState) {
            invoke(num.intValue(), chargeState);
            return Unit.INSTANCE;
        }

        public void invoke(final int power2, final ChargeState chargeState) {
            ListenerList onBatteryDataListeners2;
            onBatteryDataListeners2 = BatteryInfoManager.INSTANCE.getOnBatteryDataListeners();
            onBatteryDataListeners2.forEach(new Function1<Function2<? super Integer, ? super ChargeState, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$onBatteryListener$1$invoke$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Integer, ? super ChargeState, ? extends Unit> function2) {
                    invoke2((Function2<? super Integer, ? super ChargeState, Unit>) function2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Function2<? super Integer, ? super ChargeState, Unit> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.invoke(Integer.valueOf(power2), chargeState);
                }
            });
            BatteryInfoManager.INSTANCE.notify(power2, chargeState);
        }
    };
    private static final BatteryInfoManager$batteryFloorLevelLimitResultListener$1 batteryFloorLevelLimitResultListener = new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$batteryFloorLevelLimitResultListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
            invoke(num.intValue(), num2.intValue());
            return Unit.INSTANCE;
        }

        public void invoke(final int p0, final int p1) {
            ListenerList onBatteryFloorLevelLimitResultListeners2;
            onBatteryFloorLevelLimitResultListeners2 = BatteryInfoManager.INSTANCE.getOnBatteryFloorLevelLimitResultListeners();
            onBatteryFloorLevelLimitResultListeners2.forEach(new Function1<BatteryInfoManager.OnBatteryFloorLevelLimitResultListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$batteryFloorLevelLimitResultListener$1$invoke$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(BatteryInfoManager.OnBatteryFloorLevelLimitResultListener onBatteryFloorLevelLimitResultListener) {
                    invoke2(onBatteryFloorLevelLimitResultListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BatteryInfoManager.OnBatteryFloorLevelLimitResultListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onBatteryFloorLevelLimitResultListener(p0, p1);
                }
            });
        }
    };
    private static final BatteryInfoManager$faceDetectResultListener$1 faceDetectResultListener = new Function4<Integer, Double, Double, Double, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$faceDetectResultListener$1
        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Double d, Double d2, Double d3) {
            invoke(num.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue());
            return Unit.INSTANCE;
        }

        public void invoke(final int p0, final double p1, final double p2, final double p3) {
            String str;
            ListenerList onFaceDetectListeners2;
            ListenerList onFaceDetectListeners3;
            BatteryInfoManager batteryInfoManager = BatteryInfoManager.INSTANCE;
            str = BatteryInfoManager.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("faceDetectResultListener size = ");
            onFaceDetectListeners2 = BatteryInfoManager.INSTANCE.getOnFaceDetectListeners();
            sb.append(onFaceDetectListeners2.size());
            sb.append(" p0 = ");
            sb.append(p0);
            sb.append("  p1=");
            sb.append(p1);
            sb.append(" p2 = ");
            sb.append(p2);
            sb.append(" p3 =");
            sb.append(p3);
            Pdlog.m3273d(str, sb.toString());
            onFaceDetectListeners3 = BatteryInfoManager.INSTANCE.getOnFaceDetectListeners();
            if (onFaceDetectListeners3 != null) {
                onFaceDetectListeners3.forEach(new Function1<Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$faceDetectResultListener$1$invoke$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Function4<? super Integer, ? super Double, ? super Double, ? super Double, ? extends Unit> function4) {
                        invoke2((Function4<? super Integer, ? super Double, ? super Double, ? super Double, Unit>) function4);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Function4<? super Integer, ? super Double, ? super Double, ? super Double, Unit> it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.invoke(Integer.valueOf(p0), Double.valueOf(p1), Double.valueOf(p2), Double.valueOf(p3));
                    }
                });
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: BatteryInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$ChargingType;", "", "(Ljava/lang/String;I)V", ChargeType.chargingPile, ChargeType.cableCharging, "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum ChargingType {
        ChargingPile,
        CableCharging
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: BatteryInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnBatteryEventListener;", "", "onBatteryError", "", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onCharging", "boolean", "", "onChargingType", "chargingType", "Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$ChargingType;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnBatteryEventListener {
        void onBatteryError(ChargeState chargeState);

        void onCharging(boolean r1);

        void onChargingType(ChargingType chargingType);
    }

    /* compiled from: BatteryInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnBatteryFloorLevelLimitResultListener;", "", "onBatteryFloorLevelLimitResultListener", "", "p0", "", "p1", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnBatteryFloorLevelLimitResultListener {
        void onBatteryFloorLevelLimitResultListener(int p0, int p1);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: BatteryInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/BatteryInfoManager$OnPowerChangeListener;", "", "onPowerChanger", "", "power", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnPowerChangeListener {
        void onPowerChanger(int power);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<Function2<Integer, ChargeState, Unit>> getOnBatteryDataListeners() {
        return (ListenerList) onBatteryDataListeners.getValue();
    }

    private final ListenerList<OnBatteryEventListener> getOnBatteryEventListeners() {
        return (ListenerList) onBatteryEventListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<OnBatteryFloorLevelLimitResultListener> getOnBatteryFloorLevelLimitResultListeners() {
        return (ListenerList) onBatteryFloorLevelLimitResultListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<Function4<Integer, Double, Double, Double, Unit>> getOnFaceDetectListeners() {
        return (ListenerList) onFaceDetectListeners.getValue();
    }

    private final ListenerList<OnPowerChangeListener> getOnPowerChangerListeners() {
        return (ListenerList) onPowerChangerListeners.getValue();
    }

    private BatteryInfoManager() {
    }

    public final Integer getPower() {
        return power;
    }

    public final boolean isCharging() {
        return isCharging;
    }

    public final ChargeState getCurrentBatteryEvent() {
        return currentBatteryEvent;
    }

    private final boolean checkIsCharging(ChargeState chargeState) {
        return chargeState != ChargeState.Idle;
    }

    private final boolean checkIsError(ChargeState chargeState) {
        return (chargeState == ChargeState.ChargeFull || chargeState == ChargeState.Charging || chargeState == ChargeState.Idle || chargeState == ChargeState.ChargeFullUsePile || chargeState == ChargeState.CharingUsePile) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notify(final int power2, final ChargeState chargeState) {
        Integer num = power;
        if (num == null || power2 != num.intValue()) {
            Pdlog.m3273d(TAG, "notify : power = " + power2 + "; chargeState = " + chargeState + "; ");
            getOnPowerChangerListeners().forEach(Dispatchers.getMain(), new Function1<OnPowerChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$notify$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(BatteryInfoManager.OnPowerChangeListener onPowerChangeListener) {
                    invoke2(onPowerChangeListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BatteryInfoManager.OnPowerChangeListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onPowerChanger(power2);
                }
            });
        }
        power = Integer.valueOf(power2);
        if (chargeState != null) {
            ChargeState chargeState2 = currentBatteryEvent;
            currentBatteryEvent = chargeState;
            final boolean checkIsCharging = INSTANCE.checkIsCharging(chargeState);
            if (isCharging != checkIsCharging) {
                Pdlog.m3273d(TAG, "notify charging change = " + checkIsCharging);
                INSTANCE.getOnBatteryEventListeners().forEach(Dispatchers.getMain(), new Function1<OnBatteryEventListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$notify$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BatteryInfoManager.OnBatteryEventListener onBatteryEventListener) {
                        invoke2(onBatteryEventListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BatteryInfoManager.OnBatteryEventListener onBatteryListener2) {
                        Intrinsics.checkParameterIsNotNull(onBatteryListener2, "onBatteryListener");
                        onBatteryListener2.onCharging(checkIsCharging);
                    }
                });
            }
            isCharging = checkIsCharging;
            if (INSTANCE.checkIsError(chargeState) || INSTANCE.checkIsError(chargeState2)) {
                Pdlog.m3273d(TAG, "notify error = " + chargeState);
                INSTANCE.getOnBatteryEventListeners().forEach(Dispatchers.getMain(), new Function1<OnBatteryEventListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$notify$2$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BatteryInfoManager.OnBatteryEventListener onBatteryEventListener) {
                        invoke2(onBatteryEventListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BatteryInfoManager.OnBatteryEventListener onBatteryListener2) {
                        Intrinsics.checkParameterIsNotNull(onBatteryListener2, "onBatteryListener");
                        onBatteryListener2.onBatteryError(ChargeState.this);
                    }
                });
            }
            if (INSTANCE.checkIsCharging(chargeState)) {
                Pdlog.m3273d(TAG, "goto charging");
                INSTANCE.getOnBatteryEventListeners().forEach(Dispatchers.getMain(), new Function1<OnBatteryEventListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager$notify$2$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BatteryInfoManager.OnBatteryEventListener onBatteryEventListener) {
                        invoke2(onBatteryEventListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BatteryInfoManager.OnBatteryEventListener onBatteryListener2) {
                        Intrinsics.checkParameterIsNotNull(onBatteryListener2, "onBatteryListener");
                        onBatteryListener2.onChargingType(BatteryInfoManager.INSTANCE.getChargingType(ChargeState.this));
                    }
                });
            }
        }
    }

    public final ChargingType getChargingType(ChargeState event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(TAG, "getChargingType = " + event);
        if (event == ChargeState.ChargeFullUsePile || event == ChargeState.CharingUsePile || event == ChargeState.StopChargeUsePile) {
            return ChargingType.ChargingPile;
        }
        return ChargingType.CableCharging;
    }

    public final boolean isChargingPile() {
        return currentBatteryEvent == ChargeState.ChargeFullUsePile || currentBatteryEvent == ChargeState.CharingUsePile;
    }

    public final void addPowerChangeListener(OnPowerChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnPowerChangerListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "addPowerChangeListener : l = " + l + "; size = " + getOnPowerChangerListeners().size());
    }

    public final void removePowerChangeListener(OnPowerChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnPowerChangerListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addBatteryEventListener(OnBatteryEventListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnBatteryEventListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "addBatteryEventListeners : l = " + l + "; size = " + getOnBatteryEventListeners().size());
    }

    public final void removeBatteryEventListener(OnBatteryEventListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnBatteryEventListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addBatteryDataListener(Function2<? super Integer, ? super ChargeState, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnBatteryDataListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "addBatteryDataListener : l = " + l + "; size = " + getOnBatteryDataListeners().size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void removeBatteryEventListener(Function2<? super Integer, ? super ChargeState, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnBatteryDataListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addOnBatteryFloorLevelLimitResultListener(OnBatteryFloorLevelLimitResultListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnBatteryFloorLevelLimitResultListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    public final void removeOnBatteryFloorLevelLimitResultListener(OnBatteryFloorLevelLimitResultListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnBatteryFloorLevelLimitResultListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addOnFaceDetectListener(Function4<? super Integer, ? super Double, ? super Double, ? super Double, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnFaceDetectListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void removeOnFaceDetectListeners(Function4<? super Integer, ? super Double, ? super Double, ? super Double, Unit> l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnFaceDetectListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void controlBatteryLevel(int i) {
        SDKInterface sDKInterface = mirsdk;
        if (sDKInterface != null) {
            sDKInterface.controlBatteryLevel(i);
        }
    }

    public final void checkBatteryLevel() {
        SDKInterface sDKInterface = mirsdk;
        if (sDKInterface != null) {
            sDKInterface.getBatteryLevel();
        }
    }

    public final void init$module_robot_mirsdk_wrapper_release(MirSdkListenerWrap mirsdkListener, SDKInterface it) {
        Intrinsics.checkParameterIsNotNull(mirsdkListener, "mirsdkListener");
        Intrinsics.checkParameterIsNotNull(it, "it");
        mirsdkListener.getBatteryListeners().addNotSame$module_robot_mirsdk_wrapper_release(onBatteryListener);
        mirsdkListener.getBatteryFloorLevelLimitResultListeners().addNotSame$module_robot_mirsdk_wrapper_release(batteryFloorLevelLimitResultListener);
        mirsdkListener.getFaceDetectResultListeners().addNotSame$module_robot_mirsdk_wrapper_release(faceDetectResultListener);
        mirsdk = it;
        Pdlog.m3273d(TAG, "init()");
    }
}
