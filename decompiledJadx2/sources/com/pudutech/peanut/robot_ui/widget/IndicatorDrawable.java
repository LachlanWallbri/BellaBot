package com.pudutech.peanut.robot_ui.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.util.DensityUtil;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class IndicatorDrawable extends Drawable {
    private View view;
    private final int INDICATOR_MARGIN = DensityUtil.dp2px(RobotContext.context, 24.0f);
    private final int INDICATOR_HEIGHT = DensityUtil.dp2px(RobotContext.context, 3.0f);
    private final int INDICATOR_RADIUS = DensityUtil.dp2px(RobotContext.context, 1.5f);
    private Paint paint = new Paint();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public IndicatorDrawable(View view) {
        this.view = view;
        this.paint.setColor(view.getContext().getResources().getColor(C5508R.color.theme_main_color));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int intValue = getIntValue("mIndicatorLeft");
        int intValue2 = getIntValue("mIndicatorRight");
        int i = this.INDICATOR_RADIUS;
        if (intValue < 0 || intValue2 <= intValue) {
            return;
        }
        RectF rectF = new RectF(intValue + this.INDICATOR_MARGIN, this.view.getHeight() - this.INDICATOR_HEIGHT, intValue2 - this.INDICATOR_MARGIN, this.view.getHeight());
        float f = i;
        canvas.drawRoundRect(rectF, f, f, this.paint);
    }

    private int getIntValue(String str) {
        try {
            Field declaredField = this.view.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return ((Integer) declaredField.get(this.view)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
