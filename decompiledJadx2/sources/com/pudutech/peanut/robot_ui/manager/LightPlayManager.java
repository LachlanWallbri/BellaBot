package com.pudutech.peanut.robot_ui.manager;

import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.robot_ui.extend.RobotPeripheralsFactoryExtKt;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.common.BreathingLightColor;
import com.pudutech.robot.peripherals.config.LightBeltAnimation;
import com.pudutech.robot.peripherals.config.LightBeltAnimationColor;
import com.pudutech.robot.peripherals.config.LightBeltAnimationFrame;
import com.pudutech.robot.peripherals.config.LightBeltAnimationType;
import com.pudutech.robot.peripherals.config.LightBeltType;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LightPlayManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\u000b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\tJ\u0006\u0010\r\u001a\u00020\tJ\u0006\u0010\u000e\u001a\u00020\tJ\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\tJ\u0006\u0010\u0016\u001a\u00020\tJ\u0006\u0010\u0017\u001a\u00020\tJ\u0006\u0010\u0018\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/LightPlayManager;", "", "()V", "TAG", "", "modeBlueLight", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;", "modeYLightBlink", "playAngryAvoid", "", "playArrive", "playEmergencyStop", "playError", "playGuideArrive", "playInit", "playLostLocation", "playLowPower", "", "playMove", C3898x.f4338g, "Lcom/pudutech/peanut/robot_ui/manager/LightPlayManager$MoveEvent;", "playNormalStatus", "playSayHi", "playSleep", "playThanks", "MoveEvent", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LightPlayManager {
    public static final LightPlayManager INSTANCE = new LightPlayManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final LightBeltAnimation modeBlueLight = new LightBeltAnimation(LightBeltAnimationType.MODE, CollectionsKt.arrayListOf(new LightBeltAnimationFrame(new LightBeltAnimationColor(22, 160, 255, false, 8, null), new LightBeltAnimationColor(22, 160, 255, false, 8, null), 10000)));
    private static final LightBeltAnimation modeYLightBlink = new LightBeltAnimation(LightBeltAnimationType.MODE, CollectionsKt.arrayListOf(new LightBeltAnimationFrame(new LightBeltAnimationColor(255, 255, 0, false, 8, null), new LightBeltAnimationColor(255, 255, 0, false, 8, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(0, 0, 0, false, 15, null), new LightBeltAnimationColor(0, 0, 0, false, 15, null), 500)));

    /* compiled from: LightPlayManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/LightPlayManager$MoveEvent;", "", "(Ljava/lang/String;I)V", "FORWARD", "LEFT", "RIGHT", "STOP", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum MoveEvent {
        FORWARD,
        LEFT,
        RIGHT,
        STOP
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MoveEvent.values().length];

        static {
            $EnumSwitchMapping$0[MoveEvent.FORWARD.ordinal()] = 1;
            $EnumSwitchMapping$0[MoveEvent.STOP.ordinal()] = 2;
            $EnumSwitchMapping$0[MoveEvent.LEFT.ordinal()] = 3;
            $EnumSwitchMapping$0[MoveEvent.RIGHT.ordinal()] = 4;
        }
    }

    private LightPlayManager() {
    }

    public final void playError() {
        Pdlog.m3273d(TAG, "playError ");
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, new LightBeltAnimation(LightBeltAnimationType.MODE, CollectionsKt.arrayListOf(new LightBeltAnimationFrame(new LightBeltAnimationColor(255, 0, 0, false, 8, null), new LightBeltAnimationColor(255, 0, 0, false, 8, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(0, 0, 0, false, 15, null), new LightBeltAnimationColor(0, 0, 0, false, 15, null), 500))));
    }

    public final void playEmergencyStop() {
        Pdlog.m3273d(TAG, "playEmergencyStop ");
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, new LightBeltAnimation(LightBeltAnimationType.MODE, CollectionsKt.arrayListOf(new LightBeltAnimationFrame(new LightBeltAnimationColor(255, 0, 0, false, 8, null), new LightBeltAnimationColor(255, 0, 0, false, 8, null), 10000))));
    }

    public final boolean playLowPower() {
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        if ((power != null ? power.intValue() : 0) >= 10) {
            return false;
        }
        Pdlog.m3273d(TAG, "playLowPower ");
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).playBreathing(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, BreathingLightColor.Yellow);
        return true;
    }

    public final void playLostLocation() {
        Pdlog.m3273d(TAG, "playLostLocation ");
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).playBreathing(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, BreathingLightColor.Yellow);
    }

    public final void playInit() {
        Pdlog.m3273d(TAG, "playInit ");
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).playBreathing(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, BreathingLightColor.Blue);
    }

    public final void playSleep() {
        Pdlog.m3273d(TAG, "playSleep ");
        if (playLowPower()) {
            return;
        }
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).playBreathing(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, BreathingLightColor.Blue);
    }

    public final void playMove(MoveEvent e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        Pdlog.m3273d(TAG, "playMove : e = " + e + "; ");
        if (playLowPower()) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[e.ordinal()];
        if (i == 1 || i == 2) {
            RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, modeBlueLight);
            return;
        }
        if (i == 3) {
            RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisRight}, modeBlueLight);
            RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft}, modeYLightBlink);
            return;
        }
        if (i != 4) {
            return;
        }
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisRight}, modeYLightBlink);
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft}, modeBlueLight);
    }

    public final void playArrive() {
        Pdlog.m3273d(TAG, "playArrive ");
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, modeBlueLight);
    }

    public final void playThanks() {
        Pdlog.m3273d(TAG, "playThanks ");
        if (playLowPower()) {
            return;
        }
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, new LightBeltAnimation(LightBeltAnimationType.TEMP, CollectionsKt.arrayListOf(new LightBeltAnimationFrame(new LightBeltAnimationColor(22, 160, 255, false, 8, null), new LightBeltAnimationColor(22, 160, 255, false, 8, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(0, 0, 0, false, 15, null), new LightBeltAnimationColor(0, 0, 0, false, 15, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(22, 160, 255, false, 8, null), new LightBeltAnimationColor(22, 160, 255, false, 8, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(0, 0, 0, false, 15, null), new LightBeltAnimationColor(0, 0, 0, false, 15, null), 500))));
    }

    public final void playAngryAvoid() {
        Pdlog.m3273d(TAG, "playAngryAvoid ");
        if (playLowPower()) {
            return;
        }
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).playBreathing(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, BreathingLightColor.Yellow);
    }

    public final void playNormalStatus() {
        Pdlog.m3273d(TAG, "playNormalStatus ");
        if (playLowPower()) {
            return;
        }
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, modeBlueLight);
    }

    public final void playGuideArrive() {
        Pdlog.m3273d(TAG, "playThanks ");
        if (playLowPower()) {
            return;
        }
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).controlLight(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, new LightBeltAnimation(LightBeltAnimationType.TEMP, CollectionsKt.arrayListOf(new LightBeltAnimationFrame(new LightBeltAnimationColor(22, 160, 255, false, 8, null), new LightBeltAnimationColor(22, 160, 255, false, 8, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(0, 0, 0, false, 15, null), new LightBeltAnimationColor(0, 0, 0, false, 15, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(22, 160, 255, false, 8, null), new LightBeltAnimationColor(22, 160, 255, false, 8, null), 500), new LightBeltAnimationFrame(new LightBeltAnimationColor(0, 0, 0, false, 15, null), new LightBeltAnimationColor(0, 0, 0, false, 15, null), 500))));
    }

    public final void playSayHi() {
        RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).playBreathing(new LightBeltType[]{LightBeltType.BottomChassisLeft, LightBeltType.BottomChassisRight}, BreathingLightColor.Blue);
    }
}
