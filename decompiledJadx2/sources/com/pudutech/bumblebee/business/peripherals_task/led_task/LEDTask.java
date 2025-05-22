package com.pudutech.bumblebee.business.peripherals_task.led_task;

import android.os.HandlerThread;
import android.os.Looper;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.SurfaceLED;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LEDTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0005J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bH\u0002J\u0018\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000bH\u0002R-\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0004j\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDTask;", "", "()V", "all", "Ljava/util/HashMap;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SurfaceLED;", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDController;", "Lkotlin/collections/HashMap;", "getAll", "()Ljava/util/HashMap;", "translateColors", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Color;", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDTask$ColorTranslate;", TmpConstant.PROPERTY_IDENTIFIER_GET, "led", "init", "", "robot", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "thread", "Landroid/os/HandlerThread;", "toIntRGB", "", TypedValues.Custom.S_COLOR, "translateColor", "sur", "ColorTranslate", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LEDTask {
    private final HashMap<Color, ColorTranslate> translateColors = new HashMap<>();
    private final HashMap<SurfaceLED, LEDController> all = new HashMap<>();

    public LEDTask() {
        this.translateColors.put(new Color(22, 160, 255), new ColorTranslate(new Color(22, 100, 255), new Color(22, 100, 255), new Color(22, 100, 255)));
        this.translateColors.put(new Color(255, 210, 0), new ColorTranslate(new Color(255, 180, 0), new Color(255, 80, 0), new Color(255, 120, 0)));
        this.translateColors.put(new Color(255, 43, 0), new ColorTranslate(new Color(255, 10, 0), new Color(255, 1, 0), new Color(255, 15, 0)));
        this.translateColors.put(new Color(255, 140, 0), new ColorTranslate(new Color(255, 100, 0), new Color(255, 27, 0), new Color(255, 70, 0)));
        this.translateColors.put(new Color(255, 0, 255), new ColorTranslate(new Color(255, 0, 255), new Color(255, 0, 132), new Color(255, 0, 255)));
        this.translateColors.put(new Color(40, 255, 40), new ColorTranslate(new Color(10, 255, 10), new Color(24, 255, 20), new Color(40, 255, 40)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LEDTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_task/LEDTask$ColorTranslate;", "", "earColor", "Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Color;", "funcColor", "bottomColor", "(Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Color;Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Color;Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Color;)V", "getBottomColor", "()Lcom/pudutech/bumblebee/business/peripherals_task/led_task/Color;", "getEarColor", "getFuncColor", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class ColorTranslate {
        private final Color bottomColor;
        private final Color earColor;
        private final Color funcColor;

        public static /* synthetic */ ColorTranslate copy$default(ColorTranslate colorTranslate, Color color, Color color2, Color color3, int i, Object obj) {
            if ((i & 1) != 0) {
                color = colorTranslate.earColor;
            }
            if ((i & 2) != 0) {
                color2 = colorTranslate.funcColor;
            }
            if ((i & 4) != 0) {
                color3 = colorTranslate.bottomColor;
            }
            return colorTranslate.copy(color, color2, color3);
        }

        /* renamed from: component1, reason: from getter */
        public final Color getEarColor() {
            return this.earColor;
        }

        /* renamed from: component2, reason: from getter */
        public final Color getFuncColor() {
            return this.funcColor;
        }

        /* renamed from: component3, reason: from getter */
        public final Color getBottomColor() {
            return this.bottomColor;
        }

        public final ColorTranslate copy(Color earColor, Color funcColor, Color bottomColor) {
            Intrinsics.checkParameterIsNotNull(earColor, "earColor");
            Intrinsics.checkParameterIsNotNull(funcColor, "funcColor");
            Intrinsics.checkParameterIsNotNull(bottomColor, "bottomColor");
            return new ColorTranslate(earColor, funcColor, bottomColor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ColorTranslate)) {
                return false;
            }
            ColorTranslate colorTranslate = (ColorTranslate) other;
            return Intrinsics.areEqual(this.earColor, colorTranslate.earColor) && Intrinsics.areEqual(this.funcColor, colorTranslate.funcColor) && Intrinsics.areEqual(this.bottomColor, colorTranslate.bottomColor);
        }

        public int hashCode() {
            Color color = this.earColor;
            int hashCode = (color != null ? color.hashCode() : 0) * 31;
            Color color2 = this.funcColor;
            int hashCode2 = (hashCode + (color2 != null ? color2.hashCode() : 0)) * 31;
            Color color3 = this.bottomColor;
            return hashCode2 + (color3 != null ? color3.hashCode() : 0);
        }

        public String toString() {
            return "ColorTranslate(earColor=" + this.earColor + ", funcColor=" + this.funcColor + ", bottomColor=" + this.bottomColor + ")";
        }

        public ColorTranslate(Color earColor, Color funcColor, Color bottomColor) {
            Intrinsics.checkParameterIsNotNull(earColor, "earColor");
            Intrinsics.checkParameterIsNotNull(funcColor, "funcColor");
            Intrinsics.checkParameterIsNotNull(bottomColor, "bottomColor");
            this.earColor = earColor;
            this.funcColor = funcColor;
            this.bottomColor = bottomColor;
        }

        public final Color getBottomColor() {
            return this.bottomColor;
        }

        public final Color getEarColor() {
            return this.earColor;
        }

        public final Color getFuncColor() {
            return this.funcColor;
        }
    }

    public final HashMap<SurfaceLED, LEDController> getAll() {
        return this.all;
    }

    public final void init(final RobotInterface robot, HandlerThread thread) {
        Intrinsics.checkParameterIsNotNull(robot, "robot");
        Intrinsics.checkParameterIsNotNull(thread, "thread");
        if (!thread.isAlive()) {
            thread.start();
        }
        this.all.clear();
        final Looper looper = thread.getLooper();
        for (final SurfaceLED surfaceLED : SurfaceLED.values()) {
            String name = surfaceLED.name();
            Intrinsics.checkExpressionValueIsNotNull(looper, "looper");
            LEDController lEDController = new LEDController(name, looper);
            Pdlog.m3273d("LEDController", "init " + surfaceLED.name() + " control RGB. robot=" + robot);
            lEDController.setControl(new Function1<Frame, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_task.LEDTask$init$$inlined$forEach$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Frame frame) {
                    invoke2(frame);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Frame frame) {
                    Color translateColor;
                    int intRGB;
                    Color translateColor2;
                    int intRGB2;
                    Intrinsics.checkParameterIsNotNull(frame, "frame");
                    RobotInterface robotInterface = robot;
                    SurfaceLED surfaceLED2 = SurfaceLED.this;
                    LEDTask lEDTask = this;
                    Color color = frame.head;
                    Intrinsics.checkExpressionValueIsNotNull(color, "frame.head");
                    translateColor = lEDTask.translateColor(surfaceLED2, color);
                    intRGB = lEDTask.toIntRGB(translateColor);
                    LEDTask lEDTask2 = this;
                    SurfaceLED surfaceLED3 = SurfaceLED.this;
                    Color color2 = frame.head;
                    Intrinsics.checkExpressionValueIsNotNull(color2, "frame.head");
                    translateColor2 = lEDTask2.translateColor(surfaceLED3, color2);
                    intRGB2 = lEDTask2.toIntRGB(translateColor2);
                    robotInterface.controlRGB(surfaceLED2, intRGB, intRGB2, frame.duration);
                }
            });
            this.all.put(surfaceLED, lEDController);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Color translateColor(SurfaceLED sur, Color color) {
        ColorTranslate colorTranslate;
        if (sur == SurfaceLED.RightEar || sur == SurfaceLED.LeftEar) {
            ColorTranslate colorTranslate2 = this.translateColors.get(color);
            if (colorTranslate2 != null) {
                return colorTranslate2.getEarColor();
            }
        } else if (sur == SurfaceLED.FunctionButton) {
            ColorTranslate colorTranslate3 = this.translateColors.get(color);
            if (colorTranslate3 != null) {
                return colorTranslate3.getFuncColor();
            }
        } else if (sur == SurfaceLED.Bottom && (colorTranslate = this.translateColors.get(color)) != null) {
            return colorTranslate.getBottomColor();
        }
        return color;
    }

    public final LEDController get(SurfaceLED led) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        return this.all.get(led);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toIntRGB(Color color) {
        return color.f4601B | (color.f4603R << 16) | (color.f4602G << 8);
    }
}
