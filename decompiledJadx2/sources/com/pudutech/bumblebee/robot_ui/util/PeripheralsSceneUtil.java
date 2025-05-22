package com.pudutech.bumblebee.robot_ui.util;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.LedLightManager;
import com.pudutech.resources.led.LEDItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PeripheralsSceneUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001MB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\u000eJ\u0010\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0006\u0010\u0015\u001a\u00020\u000eJ\u0006\u0010\u0016\u001a\u00020\u000eJ\u0006\u0010\u0017\u001a\u00020\u000eJ\b\u0010\u0018\u001a\u00020\u000eH\u0002J\u000e\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u000eJ\u0006\u0010\u001d\u001a\u00020\u000eJ\u0006\u0010\u001e\u001a\u00020\u000eJ\u0006\u0010\u001f\u001a\u00020\u000eJ\u0006\u0010 \u001a\u00020\u000eJ\u000e\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u001bJ\u0018\u0010#\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u001b2\b\b\u0002\u0010$\u001a\u00020\u0013J\u0006\u0010%\u001a\u00020\u000eJ!\u0010&\u001a\u00020\u000e2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010'\u001a\u00020\u0013¢\u0006\u0002\u0010(J\u0006\u0010)\u001a\u00020\u000eJ\u0006\u0010*\u001a\u00020\u000eJ\u0016\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020\u000eJ\u0006\u00100\u001a\u00020\u000eJ\u0006\u00101\u001a\u00020\u000eJ\u0006\u00102\u001a\u00020\u000eJ\u000e\u00103\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020.J\u0016\u00104\u001a\u00020\u000e2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000e06H\u0002J\u000e\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u0004J\u0006\u00109\u001a\u00020\u000eJ\u0006\u0010:\u001a\u00020\u000eJ\u0006\u0010;\u001a\u00020\u000eJ\u0006\u0010<\u001a\u00020\u000eJ\u0006\u0010=\u001a\u00020\u000eJ\u0006\u0010>\u001a\u00020\u000eJ\u0006\u0010?\u001a\u00020\u000eJ\u0006\u0010@\u001a\u00020\u000eJ\b\u0010A\u001a\u00020\u000eH\u0002J\b\u0010B\u001a\u00020\u000eH\u0002J\b\u0010C\u001a\u00020\u000eH\u0002J\u0006\u0010D\u001a\u00020\u000eJ\u000e\u0010E\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020.J\u0006\u0010F\u001a\u00020\u000eJ\u0006\u0010G\u001a\u00020\u000eJ\u0006\u0010H\u001a\u00020\u000eJ\u0006\u0010I\u001a\u00020\u000eJ\u0006\u0010J\u001a\u00020\u000eJ\u0006\u0010K\u001a\u00020\u000eJ\u0006\u0010L\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006N"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/PeripheralsSceneUtil;", "", "()V", "TAG", "", "arrivedFinishTime", "", "playMode", "Lcom/pudutech/bumblebee/robot_ui/util/PeripheralsSceneUtil$Mode;", "getPlayMode", "()Lcom/pudutech/bumblebee/robot_ui/util/PeripheralsSceneUtil$Mode;", "setPlayMode", "(Lcom/pudutech/bumblebee/robot_ui/util/PeripheralsSceneUtil$Mode;)V", "appInit", "", "appInitFailed", "editDeliverGoAlert", "idleState", "isBirthdayMode", "", "isLedBreathOpenByMode", "lostLocation", "playSayHi", "resetLedScreen", "showBirthdayLight", "showCharging", "power", "", "showCruiseOnTheWay", "showCruisePause", "showDeliveryArrived", "showDeliveryArrivedFinish", "showDeliveryArrivedNotify", "showDeliveryArrivedPalletNotify", "index", "showDeliveryArrivedTray", "show", "showDeliveryArrivedWrongPalletNotify", "showDeliveryArriving", "isDeliverMode", "(Ljava/lang/Integer;Z)V", "showDeliveryOnTheWay", "showDeliveryPause", "showEarTouchEvent", "isLeft", "event", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "showGreeterArrive", "showGreeterOnTheWay", "showGreeterPause", "showGreeterWait", "showHeadTouchEvent", "showLedBreathOrActually", "action", "Lkotlin/Function0;", "showLedString", "s", "showMoveBrake", "showMoveForward", "showMoveLeft", "showMoveObstructed", "showMoveObstructed2", "showMoveRight", "showMoveSchedule", "showMoveStopSchedule", "showPowerEmpty", "showPowerLow2_5", "showPowerLow5_10", "showRunError", "showTouchAngry", "showTurnBackOnTheWay", "showTurnBackPause", "sleep", "standby", "stopAll", "stopCharging", "stopDeliveryArrivedWrongPalletNotify", "Mode", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PeripheralsSceneUtil {
    private static long arrivedFinishTime;
    public static final PeripheralsSceneUtil INSTANCE = new PeripheralsSceneUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static Mode playMode = Mode.Delivery;

    /* compiled from: PeripheralsSceneUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/PeripheralsSceneUtil$Mode;", "", "(Ljava/lang/String;I)V", "Delivery", "Birthday", "Direct", "Special", "Greeter", "Cruise", "Welcome", "Back", "Recycle", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Mode {
        Delivery,
        Birthday,
        Direct,
        Special,
        Greeter,
        Cruise,
        Welcome,
        Back,
        Recycle
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[Mode.Delivery.ordinal()] = 1;
            $EnumSwitchMapping$0[Mode.Birthday.ordinal()] = 2;
            $EnumSwitchMapping$0[Mode.Direct.ordinal()] = 3;
            $EnumSwitchMapping$0[Mode.Special.ordinal()] = 4;
            $EnumSwitchMapping$0[Mode.Greeter.ordinal()] = 5;
            $EnumSwitchMapping$0[Mode.Cruise.ordinal()] = 6;
            $EnumSwitchMapping$0[Mode.Recycle.ordinal()] = 7;
            $EnumSwitchMapping$1 = new int[BatteryContract2.ViewEvent.values().length];
            $EnumSwitchMapping$1[BatteryContract2.ViewEvent.POWER_LOW_5_10.ordinal()] = 1;
            $EnumSwitchMapping$1[BatteryContract2.ViewEvent.POWER_LOW_2_5.ordinal()] = 2;
            $EnumSwitchMapping$1[BatteryContract2.ViewEvent.POWER_EMPTY.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[TouchSensorContract.Event.values().length];
            $EnumSwitchMapping$2[TouchSensorContract.Event.HAPPY.ordinal()] = 1;
            $EnumSwitchMapping$2[TouchSensorContract.Event.HAPPY_LEVEL2.ordinal()] = 2;
            $EnumSwitchMapping$2[TouchSensorContract.Event.HAPPY_LEVEL3.ordinal()] = 3;
            $EnumSwitchMapping$2[TouchSensorContract.Event.ANGER.ordinal()] = 4;
            $EnumSwitchMapping$2[TouchSensorContract.Event.ANGER_LEVEL2.ordinal()] = 5;
            $EnumSwitchMapping$3 = new int[TouchSensorContract.Event.values().length];
            $EnumSwitchMapping$3[TouchSensorContract.Event.HAPPY.ordinal()] = 1;
            $EnumSwitchMapping$3[TouchSensorContract.Event.HAPPY_LEVEL2.ordinal()] = 2;
            $EnumSwitchMapping$3[TouchSensorContract.Event.HAPPY_LEVEL3.ordinal()] = 3;
            $EnumSwitchMapping$3[TouchSensorContract.Event.ANGER.ordinal()] = 4;
            $EnumSwitchMapping$3[TouchSensorContract.Event.ANGER_LEVEL2.ordinal()] = 5;
            $EnumSwitchMapping$4 = new int[TouchSensorContract.Event.values().length];
            $EnumSwitchMapping$4[TouchSensorContract.Event.HAPPY.ordinal()] = 1;
            $EnumSwitchMapping$4[TouchSensorContract.Event.HAPPY_LEVEL2.ordinal()] = 2;
            $EnumSwitchMapping$4[TouchSensorContract.Event.HAPPY_LEVEL3.ordinal()] = 3;
            $EnumSwitchMapping$4[TouchSensorContract.Event.ANGER.ordinal()] = 4;
            $EnumSwitchMapping$4[TouchSensorContract.Event.ANGER_LEVEL2.ordinal()] = 5;
        }
    }

    private PeripheralsSceneUtil() {
    }

    public final Mode getPlayMode() {
        return playMode;
    }

    public final void setPlayMode(Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "<set-?>");
        playMode = mode;
    }

    public final void appInit() {
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led1_1_1);
        LedLightManager.INSTANCE.showPalletInitAnimationLight(LEDItem.Led0_0_0);
    }

    public final void stopAll() {
        Pdlog.m3273d(TAG, "stopAll ");
        LedLightManager.INSTANCE.stopAllLights();
        resetLedScreen();
    }

    public final void playSayHi() {
        LedLightManager.INSTANCE.showAllLedLight(LEDItem.Led1_1_1);
    }

    public final void lostLocation() {
        Pdlog.m3273d(TAG, "lostLocation ");
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led17_1_1);
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.LOST);
    }

    public final void appInitFailed() {
        LedLightManager.INSTANCE.resetPalletLight();
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.Bottom, SurfaceLED.RightEar, SurfaceLED.LeftEar, SurfaceLED.FunctionButton}, LEDItem.Led17_1_1);
    }

    public final void showRunError() {
        Pdlog.m3273d(TAG, "showRunError ");
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.ERROR);
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led22_1_1);
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
    }

    public final void showCruisePause() {
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showCruisePause$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led19_1_1);
            }
        });
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.PAUSE);
    }

    public final void showCruiseOnTheWay() {
        Pdlog.m3273d(TAG, "showCruiseOnTheWay ");
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showCruiseOnTheWay$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.FunctionButton, SurfaceLED.LeftEar, SurfaceLED.RightEar}, LEDItem.Led19_1_1);
                LedLightManager.INSTANCE.showLedLight(SurfaceLED.Bottom, LEDItem.Led0_0_1);
            }
        });
        LedLightManager.INSTANCE.resetPalletLight();
    }

    public final void showDeliveryPause() {
        Pdlog.m3273d(TAG, "showDeliveryPause ");
        if (!isLedBreathOpenByMode()) {
            LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led7_4_1);
        }
        if (playMode != Mode.Birthday) {
            LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.PAUSE);
        }
    }

    private final void showBirthdayLight() {
        Pdlog.m3273d(TAG, "showBirthdayLight ");
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showBirthdayLight$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedLight(SurfaceLED.Bottom, LEDItem.Led0_0_1);
            }
        });
        LedLightManager.INSTANCE.showPalletBirthdayAnimationLight();
    }

    public final void showTurnBackPause() {
        Pdlog.m3273d(TAG, "showTurnBackPause ");
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led7_4_1);
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.PAUSE);
    }

    public final void showGreeterPause() {
        Pdlog.m3273d(TAG, "showGreeterPause ");
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showGreeterPause$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led7_4_1);
            }
        });
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.PAUSE);
    }

    public final void showTurnBackOnTheWay() {
        Pdlog.m3273d(TAG, "showTurnBackOnTheWay ");
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.Bottom, SurfaceLED.FunctionButton}, LEDItem.Led0_0_1);
        LedLightManager.INSTANCE.resetPalletLight();
    }

    public final void showGreeterOnTheWay() {
        Pdlog.m3273d(TAG, "showGreeterOnTheWay ");
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showGreeterOnTheWay$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.Bottom, SurfaceLED.FunctionButton}, LEDItem.Led0_0_1);
                LedLightManager.INSTANCE.showLedLight(SurfaceLED.Bottom, LEDItem.Led0_0_1);
            }
        });
        LedLightManager.INSTANCE.resetPalletLight();
    }

    public final void showDeliveryOnTheWay() {
        Pdlog.m3273d(TAG, "showDeliveryOnTheWay " + playMode);
        if (playMode == Mode.Birthday) {
            showBirthdayLight();
        } else {
            showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showDeliveryOnTheWay$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    LedLightManager.INSTANCE.showLedLight(SurfaceLED.Bottom, LEDItem.Led0_0_1);
                }
            });
            LedLightManager.INSTANCE.resetPalletLight();
        }
    }

    private final boolean isLedBreathOpenByMode() {
        switch (playMode) {
            case Delivery:
                return Constans.INSTANCE.isDeliverLedSwitch();
            case Birthday:
                return Constans.INSTANCE.isBirthdayLedSwitch();
            case Direct:
                return Constans.INSTANCE.isDirectDeliverLedSwitch();
            case Special:
                return Constans.INSTANCE.isSpecialLedSwitch();
            case Greeter:
                return Constans.INSTANCE.isGreeterLedSwitch();
            case Cruise:
                return Constans.INSTANCE.isCruiseLedSwitch();
            case Recycle:
                return Constans.INSTANCE.isRecyclePlateLedSwitch();
            default:
                return false;
        }
    }

    private final void showLedBreathOrActually(Function0<Unit> action) {
        if (isLedBreathOpenByMode()) {
            LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led30_2_1);
        } else {
            action.invoke();
        }
    }

    public final void showDeliveryArrived() {
        Pdlog.m3273d(TAG, "showDeliveryArrived  " + playMode);
        if (playMode == Mode.Birthday) {
            showBirthdayLight();
        } else {
            showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showDeliveryArrived$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led0_0_1);
                }
            });
            LedLightManager.INSTANCE.resetPalletLight();
        }
    }

    public final void showGreeterArrive() {
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showGreeterArrive$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led0_0_1);
            }
        });
        LedLightManager.INSTANCE.resetPalletLight();
    }

    public final void showDeliveryArrivedNotify() {
        Pdlog.m3273d(TAG, "showDeliveryArrivedNotify ");
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led9_1_2);
    }

    public final void showDeliveryArrivedPalletNotify(int index) {
        Pdlog.m3273d(TAG, "showDeliveryArrivedPalletNotify : index = " + index + "; ");
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.FunctionButton, SurfaceLED.LeftEar, SurfaceLED.RightEar}, LEDItem.Led9_1_2);
    }

    public final void showDeliveryArrivedWrongPalletNotify() {
        Pdlog.m3273d(TAG, "showDeliveryArrivedWrongPalletNotify  ; ");
        LedLightManager.INSTANCE.stopLedLights(SurfaceLED.Bottom, SurfaceLED.LeftEar, SurfaceLED.RightEar);
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.Bottom, SurfaceLED.LeftEar, SurfaceLED.RightEar}, LEDItem.Led14_4_1);
    }

    public final void stopDeliveryArrivedWrongPalletNotify() {
        Pdlog.m3273d(TAG, "showDeliveryArrivedWrongPalletNotify  ; ");
        LedLightManager.INSTANCE.stopLedLights(SurfaceLED.Bottom, SurfaceLED.LeftEar, SurfaceLED.RightEar);
    }

    public final void showDeliveryArrivedFinish() {
        Pdlog.m3273d(TAG, "showDeliveryArrivedFinish ");
        arrivedFinishTime = SystemClock.elapsedRealtime();
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led10_1_2);
        LedLightManager.INSTANCE.resetPalletLight();
    }

    public static /* synthetic */ void showDeliveryArrivedTray$default(PeripheralsSceneUtil peripheralsSceneUtil, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        peripheralsSceneUtil.showDeliveryArrivedTray(i, z);
    }

    public final void showDeliveryArrivedTray(int index, boolean show) {
        Pdlog.m3273d(TAG, "showDeliveryArrivedTray : index = " + index + "; show = " + show + "; ");
        if (playMode == Mode.Birthday) {
            return;
        }
        if (show) {
            LedLightManager.INSTANCE.showPalletLightByIndex(index, LEDItem.Led9_1_1);
        } else {
            LedLightManager.INSTANCE.showPalletLightByIndex(index, LEDItem.Led0_0_1);
        }
    }

    public final void showLedString(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        Pdlog.m3273d(TAG, "showLedString : s = " + s + "; ");
        Peripherals.INSTANCE.getLedScreen().control(s, -1);
    }

    public static /* synthetic */ void showDeliveryArriving$default(PeripheralsSceneUtil peripheralsSceneUtil, Integer num, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        peripheralsSceneUtil.showDeliveryArriving(num, z);
    }

    public final void showDeliveryArriving(Integer index, boolean isDeliverMode) {
        int intValue;
        Pdlog.m3273d(TAG, "showDeliveryArriving ");
        if (playMode == Mode.Birthday) {
            showBirthdayLight();
            return;
        }
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showDeliveryArriving$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.stopLedLights(SurfaceLED.LeftEar, SurfaceLED.RightEar);
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led8_1_1);
            }
        });
        if (!Constans.INSTANCE.getAssignPalletLightSwitch() || !isDeliverMode) {
            LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
        } else {
            if (index == null || (intValue = index.intValue()) < 0 || 4 < intValue) {
                return;
            }
            LedLightManager.INSTANCE.showPalletLightByIndex(index.intValue(), LEDItem.Led9_1_1);
        }
    }

    public final void standby() {
        Pdlog.m3273d(TAG, "standby ");
        LedLightManager.INSTANCE.stopAllLights();
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.Bottom}, LEDItem.Led1_1_1);
    }

    private final void showPowerLow5_10() {
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.LOW_POWER);
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led14_1_1);
        LedLightManager.INSTANCE.resetPalletLight();
    }

    private final void showPowerLow2_5() {
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.LOW_POWER);
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led14_3_1);
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
    }

    private final void showPowerEmpty() {
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.LOW_POWER);
        LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led14_4_1);
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
    }

    public final void sleep() {
        LedLightManager.INSTANCE.stopAllLights();
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.RightEar, SurfaceLED.LeftEar}, LEDItem.Led1_1_1);
    }

    public final void editDeliverGoAlert() {
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led6_1_2);
    }

    public final void showMoveForward() {
        Pdlog.m3273d(TAG, "showMoveForward ");
        if (!isLedBreathOpenByMode() && SystemClock.elapsedRealtime() - arrivedFinishTime >= 3000) {
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led8_1_1);
            LedLightManager.INSTANCE.stopLedLights(SurfaceLED.Bottom);
        }
    }

    public final void showMoveSchedule() {
        Pdlog.m3273d(TAG, "showMoveSchedule ");
        if (playMode == Mode.Birthday) {
            showBirthdayLight();
            return;
        }
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showMoveSchedule$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led20_1_1);
            }
        });
        LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.WAIT_OTHERS);
    }

    public final void showMoveStopSchedule() {
        Pdlog.m3273d(TAG, "showMoveStopSchedule ");
        if (playMode == Mode.Birthday) {
            showBirthdayLight();
            return;
        }
        showLedBreathOrActually(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil$showMoveStopSchedule$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led0_0_1);
            }
        });
        LedLightManager.INSTANCE.resetPalletLight();
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.IDLE);
    }

    public final void showMoveLeft() {
        Pdlog.m3273d(TAG, "showMoveLeft ");
        if (isLedBreathOpenByMode()) {
            return;
        }
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.TURN_LEFT);
        if (SystemClock.elapsedRealtime() - arrivedFinishTime < 3000) {
            return;
        }
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.FunctionButton}, LEDItem.Led7_1_2);
        LedLightManager.INSTANCE.stopLedLights(SurfaceLED.Bottom);
    }

    public final void showMoveRight() {
        Pdlog.m3273d(TAG, "showMoveRight ");
        if (isLedBreathOpenByMode()) {
            return;
        }
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.TURN_RIGHT);
        if (SystemClock.elapsedRealtime() - arrivedFinishTime < 3000) {
            return;
        }
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led7_2_2);
        LedLightManager.INSTANCE.stopLedLights(SurfaceLED.Bottom);
    }

    public final void showMoveObstructed() {
        Pdlog.m3273d(TAG, "showMoveObstructed ");
        if (!isLedBreathOpenByMode()) {
            LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led13_1_1);
        }
        if (playMode != Mode.Birthday) {
            LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.IDLE);
        }
    }

    public final void showMoveObstructed2() {
        Pdlog.m3273d(TAG, "showMoveObstructed2 ");
        if (!isLedBreathOpenByMode()) {
            LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led13_1_1);
        }
        if (playMode != Mode.Birthday) {
            LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.IDLE);
        }
    }

    public final void showMoveBrake() {
        Pdlog.m3273d(TAG, "showMoveBrake ");
        if (!isLedBreathOpenByMode()) {
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led7_3_1);
        }
        if (playMode != Mode.Birthday) {
            LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_4_2);
        }
    }

    public final void resetLedScreen() {
        Pdlog.m3273d(TAG, "resetLedScreen ");
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.IDLE);
    }

    public static /* synthetic */ void idleState$default(PeripheralsSceneUtil peripheralsSceneUtil, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        peripheralsSceneUtil.idleState(z);
    }

    public final void idleState(boolean isBirthdayMode) {
        if (isBirthdayMode) {
            if (isLedBreathOpenByMode()) {
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led30_1_1);
            } else {
                LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led0_0_1);
            }
            LedLightManager.INSTANCE.showAllPalletLight(LEDItem.Led1_1_1);
            String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr9_1);
            Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr9_1)");
            showLedString(string);
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[BatteryInfoManager.INSTANCE.getPowerEvent().getEvent().ordinal()];
        if (i == 1) {
            showPowerLow5_10();
            return;
        }
        if (i == 2) {
            showPowerLow2_5();
            return;
        }
        if (i == 3) {
            showPowerEmpty();
            return;
        }
        if (isLedBreathOpenByMode()) {
            LedLightManager.INSTANCE.showLedWithoutPallet(LEDItem.Led30_1_1);
            LedLightManager.INSTANCE.resetPalletLight();
        } else {
            LedLightManager.INSTANCE.stopAllLights();
        }
        Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.IDLE);
    }

    public final void showGreeterWait() {
        playMode = Mode.Greeter;
        idleState$default(this, false, 1, null);
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_lattice_welcome_area", "");
        if (StringsKt.isBlank(str)) {
            return;
        }
        showLedString(str);
    }

    public final void showEarTouchEvent(boolean isLeft, TouchSensorContract.Event event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        SurfaceLED surfaceLED = isLeft ? SurfaceLED.LeftEar : SurfaceLED.RightEar;
        int i = WhenMappings.$EnumSwitchMapping$2[event.ordinal()];
        if (i == 1) {
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{surfaceLED, SurfaceLED.FunctionButton}, LEDItem.Led23_1_2);
            return;
        }
        if (i == 2) {
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{surfaceLED, SurfaceLED.FunctionButton}, LEDItem.Led23_1_2);
        } else if (i == 3) {
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led23_1_2);
        } else if (i != 4) {
        }
    }

    public final void showHeadTouchEvent(TouchSensorContract.Event event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$3[event.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            if (i != 4) {
            }
        } else {
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led24_1_2);
        }
    }

    public final void showTouchAngry(TouchSensorContract.Event event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$4[event.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return;
        }
        if (i == 4) {
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, LEDItem.Led25_1_2);
        } else {
            if (i != 5) {
                return;
            }
            LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton, SurfaceLED.Bottom}, LEDItem.Led26_1_2);
        }
    }

    public final void showCharging(int power) {
        LEDItem lEDItem;
        if (power < 20) {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.POWER_20);
            lEDItem = LEDItem.Led35_1_1;
        } else if (power < 40) {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.POWER_20);
            lEDItem = LEDItem.Led35_2_1;
        } else if (power < 60) {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.POWER_40);
            lEDItem = LEDItem.Led35_3_1;
        } else if (power < 80) {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.POWER_60);
            lEDItem = LEDItem.Led35_4_1;
        } else if (power < 100) {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.POWER_80);
            lEDItem = LEDItem.Led35_5_1;
        } else if (power >= 100) {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.POWER_100);
            lEDItem = LEDItem.Led35_5_1;
        } else {
            lEDItem = LEDItem.Led35_5_1;
        }
        LedLightManager.INSTANCE.showPalletChargingAnimationLight();
        LedLightManager.INSTANCE.showLedLights(new SurfaceLED[]{SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.FunctionButton}, lEDItem);
    }

    public final void stopCharging() {
        LedLightManager.INSTANCE.resetPalletLight();
        idleState$default(this, false, 1, null);
    }
}
