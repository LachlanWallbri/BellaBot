package com.flask.colorpicker.renderer;

import android.graphics.Color;
import android.graphics.Paint;
import com.flask.colorpicker.ColorCircle;
import com.flask.colorpicker.builder.PaintBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class FlowerColorWheelRenderer extends AbsColorWheelRenderer {
    private Paint selectorFill = PaintBuilder.newPaint().build();
    private float[] hsv = new float[3];
    private float sizeJitter = 1.2f;

    @Override // com.flask.colorpicker.renderer.ColorWheelRenderer
    public void draw() {
        int size = this.colorCircleList.size();
        float f = 2.0f;
        float width = this.colorWheelRenderOption.targetCanvas.getWidth() / 2.0f;
        int i = this.colorWheelRenderOption.density;
        float f2 = this.colorWheelRenderOption.strokeWidth;
        float f3 = this.colorWheelRenderOption.maxRadius;
        float f4 = this.colorWheelRenderOption.cSize;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            float f5 = i2;
            float f6 = i;
            float f7 = (f5 / (i - 1)) * f3;
            float max = Math.max(1.5f + f2, (i2 == 0 ? 0.0f : ((f5 - (f6 / f)) / f6) * this.sizeJitter * f4) + f4);
            int min = Math.min(calcTotalCount(f7, max), i * 2);
            int i4 = 0;
            while (i4 < min) {
                int i5 = i;
                int i6 = i2;
                double d = min;
                int i7 = min;
                int i8 = i4;
                double d2 = ((i4 * 6.283185307179586d) / d) + ((3.141592653589793d / d) * ((i6 + 1) % 2));
                double d3 = f7;
                float cos = ((float) (Math.cos(d2) * d3)) + width;
                float sin = ((float) (d3 * Math.sin(d2))) + width;
                float[] fArr = this.hsv;
                fArr[0] = (float) ((d2 * 180.0d) / 3.141592653589793d);
                fArr[1] = f7 / f3;
                fArr[2] = this.colorWheelRenderOption.lightness;
                this.selectorFill.setColor(Color.HSVToColor(this.hsv));
                this.selectorFill.setAlpha(getAlphaValueAsInt());
                this.colorWheelRenderOption.targetCanvas.drawCircle(cos, sin, max - f2, this.selectorFill);
                if (i3 >= size) {
                    this.colorCircleList.add(new ColorCircle(cos, sin, this.hsv));
                } else {
                    this.colorCircleList.get(i3).set(cos, sin, this.hsv);
                }
                i3++;
                i4 = i8 + 1;
                i2 = i6;
                i = i5;
                min = i7;
            }
            i2++;
            i = i;
            f = 2.0f;
        }
    }
}
