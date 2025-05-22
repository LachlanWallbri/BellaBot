package com.pudutech.bumblebee.robot_ui.manager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.led_task.Animation;
import com.pudutech.bumblebee.business.peripherals_task.led_task.LEDAnimationParser;
import com.pudutech.bumblebee.business.peripherals_task.led_task.LEDController;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.resources.led.LEDItem;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LedLightManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010\u0015\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000eJ\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000eJ\u0016\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u000eJ'\u0010\u001f\u001a\u00020\u00122\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0!\"\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u000e¢\u0006\u0002\u0010\"J\u000e\u0010#\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000eJ\u000e\u0010$\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000eJ\u0006\u0010%\u001a\u00020\u0012J\u0006\u0010&\u001a\u00020\u0012J\u000e\u0010'\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000eJ\u0016\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u000eJ\u001a\u0010*\u001a\u00020\u00122\n\u0010+\u001a\u00020,\"\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u000eJ\u000e\u0010-\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000eJ\u0006\u0010.\u001a\u00020\u0012J\b\u0010/\u001a\u00020\u0012H\u0002J\u001f\u00100\u001a\u00020\u00122\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0!\"\u00020\u001e¢\u0006\u0002\u00101J\u0010\u00102\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LedLightManager;", "", "()V", "SHOW_BIRTHDAY_ANIMATION", "", "SHOW_CHARING_ANIMATION", "SHOW_INIT_ANIMATION", "TAG", "", "kotlin.jvm.PlatformType", "currentAnimationLight", "mainHandle", "Landroid/os/Handler;", "palletAnimationItem", "Lcom/pudutech/resources/led/LEDItem;", "parser", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDAnimationParser;", "release", "", "resetEarLight", "resetPalletLight", "sendPalletAnimation", "i", "time", "", "showAllLedLight", "item", "showAllPalletLight", "showLedLight", "led", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SurfaceLED;", "showLedLights", "leds", "", "([Lcom/pudutech/bumblebee/robot/aidl/serialize/SurfaceLED;Lcom/pudutech/resources/led/LEDItem;)V", "showLedWithoutPallet", "showLeftPalletLight", "showPalletBirthdayAnimationLight", "showPalletChargingAnimationLight", "showPalletInitAnimationLight", "showPalletLightByIndex", "int", "showPalletLights", "ints", "", "showRightPalletLight", "stopAllLights", "stopAnimation", "stopLedLights", "([Lcom/pudutech/bumblebee/robot/aidl/serialize/SurfaceLED;)V", "stopPalletAnimation", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LedLightManager {
    public static final LedLightManager INSTANCE;
    private static final int SHOW_BIRTHDAY_ANIMATION;
    private static final int SHOW_CHARING_ANIMATION;
    private static final int SHOW_INIT_ANIMATION;
    private static final String TAG;
    private static int currentAnimationLight;
    private static final Handler mainHandle;
    private static LEDItem palletAnimationItem;
    private static final LEDAnimationParser parser;

    static {
        LedLightManager ledLightManager = new LedLightManager();
        INSTANCE = ledLightManager;
        TAG = ledLightManager.getClass().getSimpleName();
        parser = new LEDAnimationParser();
        currentAnimationLight = PalletCountHelper.INSTANCE.getCount();
        SHOW_INIT_ANIMATION = 100;
        SHOW_BIRTHDAY_ANIMATION = 101;
        SHOW_CHARING_ANIMATION = 102;
        mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.manager.LedLightManager$mainHandle$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2;
                int i3;
                int i4;
                int i5;
                LEDItem lEDItem;
                int i6;
                LEDItem lEDItem2;
                int i7;
                int i8;
                int i9;
                int i10;
                LEDItem lEDItem3;
                int i11;
                LEDItem lEDItem4;
                int i12;
                int i13;
                int i14;
                int i15;
                LEDItem lEDItem5;
                int i16;
                LEDItem lEDItem6;
                int i17;
                String str;
                int i18;
                int i19 = message.what;
                LedLightManager ledLightManager2 = LedLightManager.INSTANCE;
                i = LedLightManager.SHOW_INIT_ANIMATION;
                if (i19 == i) {
                    LedLightManager ledLightManager3 = LedLightManager.INSTANCE;
                    i14 = LedLightManager.currentAnimationLight;
                    LedLightManager.currentAnimationLight = i14 - 1;
                    LedLightManager ledLightManager4 = LedLightManager.INSTANCE;
                    i15 = LedLightManager.currentAnimationLight;
                    if (i15 < 0) {
                        LedLightManager ledLightManager5 = LedLightManager.INSTANCE;
                        LedLightManager.currentAnimationLight = PalletCountHelper.INSTANCE.getCount();
                        LedLightManager.INSTANCE.showPalletLights(new int[]{0, 1, 2, 3}, LEDItem.Led0_0_1);
                        LedLightManager ledLightManager6 = LedLightManager.INSTANCE;
                        LedLightManager ledLightManager7 = LedLightManager.INSTANCE;
                        i18 = LedLightManager.SHOW_INIT_ANIMATION;
                        ledLightManager6.sendPalletAnimation(i18, 500L);
                        return true;
                    }
                    LedLightManager ledLightManager8 = LedLightManager.INSTANCE;
                    lEDItem5 = LedLightManager.palletAnimationItem;
                    if (lEDItem5 == null) {
                        LedLightManager ledLightManager9 = LedLightManager.INSTANCE;
                        str = LedLightManager.TAG;
                        Pdlog.m3274e(str, "palletAnimationItem is null");
                        return true;
                    }
                    LedLightManager ledLightManager10 = LedLightManager.INSTANCE;
                    LedLightManager ledLightManager11 = LedLightManager.INSTANCE;
                    i16 = LedLightManager.currentAnimationLight;
                    LedLightManager ledLightManager12 = LedLightManager.INSTANCE;
                    lEDItem6 = LedLightManager.palletAnimationItem;
                    if (lEDItem6 == null) {
                        Intrinsics.throwNpe();
                    }
                    ledLightManager10.showPalletLightByIndex(i16, lEDItem6);
                    LedLightManager ledLightManager13 = LedLightManager.INSTANCE;
                    LedLightManager ledLightManager14 = LedLightManager.INSTANCE;
                    i17 = LedLightManager.SHOW_INIT_ANIMATION;
                    ledLightManager13.sendPalletAnimation(i17, 500L);
                } else {
                    int i20 = message.what;
                    LedLightManager ledLightManager15 = LedLightManager.INSTANCE;
                    i2 = LedLightManager.SHOW_BIRTHDAY_ANIMATION;
                    if (i20 == i2) {
                        LedLightManager ledLightManager16 = LedLightManager.INSTANCE;
                        i9 = LedLightManager.currentAnimationLight;
                        if (i9 == PalletCountHelper.INSTANCE.getCount()) {
                            LedLightManager ledLightManager17 = LedLightManager.INSTANCE;
                            LedLightManager.currentAnimationLight = 0;
                        }
                        LedLightManager ledLightManager18 = LedLightManager.INSTANCE;
                        i10 = LedLightManager.currentAnimationLight;
                        if (i10 < 4) {
                            LedLightManager ledLightManager19 = LedLightManager.INSTANCE;
                            lEDItem3 = LedLightManager.palletAnimationItem;
                            if (lEDItem3 != null) {
                                LedLightManager ledLightManager20 = LedLightManager.INSTANCE;
                                LedLightManager ledLightManager21 = LedLightManager.INSTANCE;
                                i11 = LedLightManager.currentAnimationLight;
                                LedLightManager ledLightManager22 = LedLightManager.INSTANCE;
                                lEDItem4 = LedLightManager.palletAnimationItem;
                                if (lEDItem4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ledLightManager20.showPalletLightByIndex(i11, lEDItem4);
                                LedLightManager ledLightManager23 = LedLightManager.INSTANCE;
                                i12 = LedLightManager.currentAnimationLight;
                                LedLightManager.currentAnimationLight = i12 + 1;
                                LedLightManager ledLightManager24 = LedLightManager.INSTANCE;
                                LedLightManager ledLightManager25 = LedLightManager.INSTANCE;
                                i13 = LedLightManager.SHOW_BIRTHDAY_ANIMATION;
                                ledLightManager24.sendPalletAnimation(i13, 500L);
                            }
                        }
                    } else {
                        int i21 = message.what;
                        LedLightManager ledLightManager26 = LedLightManager.INSTANCE;
                        i3 = LedLightManager.SHOW_CHARING_ANIMATION;
                        if (i21 == i3) {
                            LedLightManager ledLightManager27 = LedLightManager.INSTANCE;
                            i4 = LedLightManager.currentAnimationLight;
                            if (i4 == PalletCountHelper.INSTANCE.getCount()) {
                                LedLightManager ledLightManager28 = LedLightManager.INSTANCE;
                                LedLightManager.currentAnimationLight = 0;
                            }
                            LedLightManager ledLightManager29 = LedLightManager.INSTANCE;
                            i5 = LedLightManager.currentAnimationLight;
                            if (i5 < PalletCountHelper.INSTANCE.getCount()) {
                                LedLightManager ledLightManager30 = LedLightManager.INSTANCE;
                                lEDItem = LedLightManager.palletAnimationItem;
                                if (lEDItem != null) {
                                    LedLightManager ledLightManager31 = LedLightManager.INSTANCE;
                                    LedLightManager ledLightManager32 = LedLightManager.INSTANCE;
                                    i6 = LedLightManager.currentAnimationLight;
                                    LedLightManager ledLightManager33 = LedLightManager.INSTANCE;
                                    lEDItem2 = LedLightManager.palletAnimationItem;
                                    if (lEDItem2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    ledLightManager31.showPalletLightByIndex(i6, lEDItem2);
                                    LedLightManager ledLightManager34 = LedLightManager.INSTANCE;
                                    i7 = LedLightManager.currentAnimationLight;
                                    LedLightManager.currentAnimationLight = i7 + 1;
                                    LedLightManager ledLightManager35 = LedLightManager.INSTANCE;
                                    LedLightManager ledLightManager36 = LedLightManager.INSTANCE;
                                    i8 = LedLightManager.SHOW_CHARING_ANIMATION;
                                    ledLightManager35.sendPalletAnimation(i8, SolicitService.CAMERA_OPEN_TIME_OUT);
                                }
                            }
                        }
                    }
                }
                return true;
            }
        });
    }

    private LedLightManager() {
    }

    public final void showLedLight(SurfaceLED led, LEDItem item) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        Intrinsics.checkParameterIsNotNull(item, "item");
        Animation animation = parser.get(item);
        if (animation == null) {
            Pdlog.m3274e(TAG, "showLedLight failed , not find animation : " + item);
            return;
        }
        LEDController lEDController = Peripherals.INSTANCE.getLedControllers().get(led);
        if (lEDController == null) {
            Pdlog.m3274e(TAG, "showLedLight failed , not find ledLight : " + led);
            return;
        }
        try {
            lEDController.play(animation, null);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void showLedLights(SurfaceLED[] leds, LEDItem item) {
        Intrinsics.checkParameterIsNotNull(leds, "leds");
        Intrinsics.checkParameterIsNotNull(item, "item");
        Animation animation = parser.get(item);
        if (animation == null) {
            Pdlog.m3274e(TAG, "showLedLights failed , not find animation : " + item);
            return;
        }
        try {
            for (SurfaceLED surfaceLED : leds) {
                LEDController lEDController = Peripherals.INSTANCE.getLedControllers().get(surfaceLED);
                if (lEDController == null) {
                    Pdlog.m3274e(TAG, "showLedLight failed , not find ledLight : " + surfaceLED);
                    return;
                }
                lEDController.play(animation, null);
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void showPalletLights(int[] ints, LEDItem item) {
        Intrinsics.checkParameterIsNotNull(ints, "ints");
        Intrinsics.checkParameterIsNotNull(item, "item");
        Pdlog.m3273d(TAG, "showPalletLights");
        for (int i : ints) {
            INSTANCE.showPalletLightByIndex(i, item);
        }
    }

    public final void showPalletInitAnimationLight(LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        resetPalletLight();
        currentAnimationLight = PalletCountHelper.INSTANCE.getCount();
        palletAnimationItem = item;
        sendPalletAnimation(SHOW_INIT_ANIMATION, 500L);
    }

    public final void showPalletChargingAnimationLight() {
        resetPalletLight();
        currentAnimationLight = 0;
        palletAnimationItem = LEDItem.Led35_6_2;
        sendPalletAnimation(SHOW_CHARING_ANIMATION, SolicitService.CAMERA_OPEN_TIME_OUT);
    }

    public final void showPalletBirthdayAnimationLight() {
        if (palletAnimationItem == LEDItem.Led30_3_1) {
            return;
        }
        resetPalletLight();
        currentAnimationLight = 0;
        palletAnimationItem = LEDItem.Led30_3_1;
        sendPalletAnimation(SHOW_BIRTHDAY_ANIMATION, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendPalletAnimation(int i, long time) {
        Pdlog.m3273d(TAG, "sendPalletAnimation " + i);
        stopPalletAnimation(i);
        mainHandle.sendEmptyMessageDelayed(i, time);
    }

    private final void stopPalletAnimation(int i) {
        mainHandle.removeMessages(i);
    }

    public final void showPalletLightByIndex(int r4, LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (r4 == 0) {
            showLedLights(new SurfaceLED[]{SurfaceLED.Pallet1Right}, item);
            return;
        }
        if (r4 == 1) {
            showLedLights(new SurfaceLED[]{SurfaceLED.Pallet2Right}, item);
        } else if (r4 == 2) {
            showLedLights(new SurfaceLED[]{SurfaceLED.Pallet3Right}, item);
        } else {
            if (r4 != 3) {
                return;
            }
            showLedLights(new SurfaceLED[]{SurfaceLED.Pallet4Right}, item);
        }
    }

    public final void showAllLedLight(LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Pdlog.m3273d(TAG, "showAllLedLight");
        showLedLights(new SurfaceLED[]{SurfaceLED.Bottom, SurfaceLED.FunctionButton, SurfaceLED.LeftEar, SurfaceLED.RightEar, SurfaceLED.Pallet1Right, SurfaceLED.Pallet2Right, SurfaceLED.Pallet3Right, SurfaceLED.Pallet4Right}, item);
    }

    public final void showLeftPalletLight(LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        showLedLights(new SurfaceLED[]{SurfaceLED.Pallet1Right, SurfaceLED.Pallet2Right, SurfaceLED.Pallet3Right, SurfaceLED.Pallet4Right}, item);
    }

    public final void showRightPalletLight(LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        showLedLights(new SurfaceLED[]{SurfaceLED.Pallet1Right, SurfaceLED.Pallet2Right, SurfaceLED.Pallet3Right, SurfaceLED.Pallet4Right}, item);
    }

    public final void showLedWithoutPallet(LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Pdlog.m3273d(TAG, "showLedWithoutPallet");
        showLedLights(new SurfaceLED[]{SurfaceLED.FunctionButton, SurfaceLED.RightEar, SurfaceLED.LeftEar, SurfaceLED.Bottom}, item);
    }

    public final void stopLedLights(SurfaceLED... leds) {
        Intrinsics.checkParameterIsNotNull(leds, "leds");
        Pdlog.m3273d(TAG, "stopLedLights " + leds);
        try {
            for (SurfaceLED surfaceLED : leds) {
                LEDController lEDController = Peripherals.INSTANCE.getLedControllers().get(surfaceLED);
                if (lEDController == null) {
                    Pdlog.m3274e(TAG, "showLedLight failed , not find ledLight : " + surfaceLED);
                    return;
                }
                Animation animation = parser.get(LEDItem.Led0_0_1);
                if (animation != null) {
                    lEDController.play(animation, null);
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void stopAllLights() {
        showLedLights(new SurfaceLED[]{SurfaceLED.FunctionButton, SurfaceLED.Bottom, SurfaceLED.LeftEar, SurfaceLED.RightEar}, LEDItem.Led0_0_1);
        resetPalletLight();
    }

    public final void resetPalletLight() {
        Pdlog.m3273d(TAG, "resetPalletLight ");
        stopAnimation();
        showPalletLights(new int[]{0, 1, 2, 3}, LEDItem.Led0_0_1);
    }

    private final void stopAnimation() {
        Pdlog.m3273d(TAG, "stopAnimation ");
        stopPalletAnimation(SHOW_INIT_ANIMATION);
        stopPalletAnimation(SHOW_BIRTHDAY_ANIMATION);
        stopPalletAnimation(SHOW_CHARING_ANIMATION);
        palletAnimationItem = (LEDItem) null;
    }

    public final void showAllPalletLight(LEDItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Pdlog.m3273d(TAG, "showAllPalletLight : item = " + item + "; ");
        stopAnimation();
        showPalletLights(new int[]{0, 1, 2, 3}, item);
    }

    public final void resetEarLight() {
        Pdlog.m3273d(TAG, "resetEarLight");
        showLedLights(new SurfaceLED[]{SurfaceLED.RightEar, SurfaceLED.LeftEar}, LEDItem.Led8_1_1);
    }

    public final void release() {
        try {
            stopAnimation();
            Peripherals.INSTANCE.getLedControllers().getAll().forEach(new BiConsumer<SurfaceLED, LEDController>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LedLightManager$release$1
                @Override // java.util.function.BiConsumer
                public final void accept(SurfaceLED t, LEDController u) {
                    Intrinsics.checkParameterIsNotNull(t, "t");
                    Intrinsics.checkParameterIsNotNull(u, "u");
                    u.stop();
                }
            });
        } catch (Exception unused) {
        }
    }
}
