package com.pudutech.module_project_common.statusBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.pudutech.disinfect.baselib.util.DensityUtil;

/* loaded from: classes3.dex */
public class RobotBatteryView extends View {
    private static final String TAG = RobotBatteryView.class.getSimpleName();
    private int battery_height;
    private int battery_inside_margin;
    private int battery_left;
    private int battery_top;
    private int battery_width;
    private boolean isWhite;
    private int lowColor;
    private int mPower;
    private int normalColor;
    private Paint paint;
    private Paint paint2;
    private RectF rect;
    private Rect rect2;

    public RobotBatteryView(Context context) {
        super(context);
        this.mPower = 100;
        this.paint = new Paint();
        this.paint2 = new Paint();
        this.rect2 = new Rect();
        this.normalColor = Color.parseColor("#FF222222");
        this.lowColor = Color.parseColor("#FFFB313B");
        init(context);
    }

    public RobotBatteryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPower = 100;
        this.paint = new Paint();
        this.paint2 = new Paint();
        this.rect2 = new Rect();
        this.normalColor = Color.parseColor("#FF222222");
        this.lowColor = Color.parseColor("#FFFB313B");
        init(context);
    }

    private void init(Context context) {
        this.paint.setColor(this.normalColor);
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint2.setAntiAlias(true);
        this.paint2.setStyle(Paint.Style.FILL);
        this.battery_left = DensityUtil.INSTANCE.dip2px(context, 3.0f);
        this.battery_top = DensityUtil.INSTANCE.dip2px(context, 1.0f);
        this.battery_width = DensityUtil.INSTANCE.dip2px(context, 33.0f);
        this.battery_height = DensityUtil.INSTANCE.dip2px(context, 16.0f);
        this.battery_inside_margin = DensityUtil.INSTANCE.dip2px(context, 2.0f);
        this.rect = new RectF(this.battery_left, this.battery_top, r0 + this.battery_width, r2 + this.battery_height);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setColor(this.normalColor);
        canvas.drawRoundRect(this.rect, 2.0f, 2.0f, this.paint);
        float f = this.mPower / 100.0f;
        if (f != 0.0f) {
            int i = this.battery_left;
            int i2 = this.battery_inside_margin;
            int i3 = i + i2;
            int i4 = this.battery_top + i2;
            this.rect2.set(i3, i4, ((int) (((this.battery_width - i2) - i2) * f)) + i3, (this.battery_height + i4) - (i2 * 2));
            if (this.mPower <= 10) {
                this.paint2.setColor(this.lowColor);
            } else {
                this.paint2.setColor(this.normalColor);
            }
            canvas.drawRect(this.rect2, this.paint2);
        }
    }

    public void setIsWhiteBg(Boolean bool) {
        this.isWhite = bool.booleanValue();
        if (bool.booleanValue()) {
            this.normalColor = Color.parseColor("#FFFFFFFF");
        } else {
            this.normalColor = Color.parseColor("#FF222222");
        }
        invalidate();
    }

    public void setPower(int i) {
        if (this.mPower == i) {
            return;
        }
        this.mPower = i;
        if (this.mPower < 0) {
            this.mPower = 0;
        }
        invalidate();
    }
}
