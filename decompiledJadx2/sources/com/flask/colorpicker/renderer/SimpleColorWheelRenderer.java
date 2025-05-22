package com.flask.colorpicker.renderer;

import android.graphics.Color;
import android.graphics.Paint;
import com.flask.colorpicker.ColorCircle;
import com.flask.colorpicker.builder.PaintBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class SimpleColorWheelRenderer extends AbsColorWheelRenderer {
    private Paint selectorFill = PaintBuilder.newPaint().build();
    private float[] hsv = new float[3];

    @Override // com.flask.colorpicker.renderer.ColorWheelRenderer
    public void draw() {
        int size = this.colorCircleList.size();
        float width = this.colorWheelRenderOption.targetCanvas.getWidth() / 2.0f;
        int i = this.colorWheelRenderOption.density;
        float f = this.colorWheelRenderOption.maxRadius;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            float f2 = (i2 / (i - 1)) * f;
            float f3 = this.colorWheelRenderOption.cSize;
            int calcTotalCount = calcTotalCount(f2, f3);
            int i4 = 0;
            while (i4 < calcTotalCount) {
                double d = calcTotalCount;
                int i5 = i2;
                double d2 = ((i4 * 6.283185307179586d) / d) + ((3.141592653589793d / d) * ((i2 + 1) % 2));
                double d3 = f2;
                float cos = ((float) (Math.cos(d2) * d3)) + width;
                float sin = ((float) (d3 * Math.sin(d2))) + width;
                float[] fArr = this.hsv;
                fArr[0] = (float) ((d2 * 180.0d) / 3.141592653589793d);
                fArr[1] = f2 / f;
                fArr[2] = this.colorWheelRenderOption.lightness;
                this.selectorFill.setColor(Color.HSVToColor(this.hsv));
                this.selectorFill.setAlpha(getAlphaValueAsInt());
                this.colorWheelRenderOption.targetCanvas.drawCircle(cos, sin, f3 - this.colorWheelRenderOption.strokeWidth, this.selectorFill);
                if (i3 >= size) {
                    this.colorCircleList.add(new ColorCircle(cos, sin, this.hsv));
                } else {
                    this.colorCircleList.get(i3).set(cos, sin, this.hsv);
                }
                i3++;
                i4++;
                i2 = i5;
            }
            i2++;
        }
    }
}
