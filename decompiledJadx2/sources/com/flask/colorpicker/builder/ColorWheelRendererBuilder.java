package com.flask.colorpicker.builder;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.renderer.ColorWheelRenderer;
import com.flask.colorpicker.renderer.FlowerColorWheelRenderer;
import com.flask.colorpicker.renderer.SimpleColorWheelRenderer;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class ColorWheelRendererBuilder {

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* renamed from: com.flask.colorpicker.builder.ColorWheelRendererBuilder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C16481 {
        static final /* synthetic */ int[] $SwitchMap$com$flask$colorpicker$ColorPickerView$WHEEL_TYPE;

        static {
            int[] iArr = new int[ColorPickerView.WHEEL_TYPE.values().length];
            $SwitchMap$com$flask$colorpicker$ColorPickerView$WHEEL_TYPE = iArr;
            try {
                iArr[ColorPickerView.WHEEL_TYPE.CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$flask$colorpicker$ColorPickerView$WHEEL_TYPE[ColorPickerView.WHEEL_TYPE.FLOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheel_type) {
        int i = C16481.$SwitchMap$com$flask$colorpicker$ColorPickerView$WHEEL_TYPE[wheel_type.ordinal()];
        if (i == 1) {
            return new SimpleColorWheelRenderer();
        }
        if (i == 2) {
            return new FlowerColorWheelRenderer();
        }
        throw new IllegalArgumentException("wrong WHEEL_TYPE");
    }
}
