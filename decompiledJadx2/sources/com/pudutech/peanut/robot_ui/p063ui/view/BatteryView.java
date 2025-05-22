package com.pudutech.peanut.robot_ui.p063ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.warkiz.widget.SizeUtils;

/* loaded from: classes5.dex */
public class BatteryView extends View {
    private static final String TAG = BatteryView.class.getSimpleName();
    private int battery_head_height;
    private int battery_head_width;
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
    private Paint paint3;
    private RectF rect;
    private Rect rect2;
    private RectF rect3;
    private int strokeColor;
    private int warnColor;

    public BatteryView(Context context) {
        super(context);
        this.mPower = 100;
        this.paint = new Paint();
        this.paint2 = new Paint();
        this.paint3 = new Paint();
        this.rect2 = new Rect();
        this.strokeColor = Color.parseColor("#FF2D2D2D");
        this.normalColor = Color.parseColor("#FF222222");
        this.lowColor = Color.parseColor("#FFFB313B");
        this.warnColor = Color.parseColor("#FFFFA247");
        init(context);
    }

    public BatteryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPower = 100;
        this.paint = new Paint();
        this.paint2 = new Paint();
        this.paint3 = new Paint();
        this.rect2 = new Rect();
        this.strokeColor = Color.parseColor("#FF2D2D2D");
        this.normalColor = Color.parseColor("#FF222222");
        this.lowColor = Color.parseColor("#FFFB313B");
        this.warnColor = Color.parseColor("#FFFFA247");
        init(context);
    }

    private void init(Context context) {
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(SizeUtils.dp2px(context, 2.0f));
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint2.setAntiAlias(true);
        this.paint2.setStyle(Paint.Style.FILL);
        this.paint3.setAntiAlias(true);
        this.paint3.setStyle(Paint.Style.FILL);
        this.battery_left = SizeUtils.dp2px(context, 3.0f);
        this.battery_top = SizeUtils.dp2px(context, 1.0f);
        this.battery_width = SizeUtils.dp2px(context, 33.0f);
        this.battery_height = SizeUtils.dp2px(context, 16.0f);
        this.battery_inside_margin = SizeUtils.dp2px(context, 2.0f);
        this.battery_head_width = SizeUtils.dp2px(context, 3.0f);
        this.battery_head_height = SizeUtils.dp2px(context, 8.0f);
        this.rect = new RectF(this.battery_left, this.battery_top, r0 + this.battery_width, r2 + this.battery_height);
        int i = this.battery_left;
        int i2 = this.battery_width;
        int i3 = this.battery_top;
        int i4 = this.battery_height;
        int i5 = this.battery_head_height;
        this.rect3 = new RectF(i + i2, ((i4 / 2) + i3) - (i5 / 2), i + i2 + this.battery_head_width, i3 + (i4 / 2) + (i5 / 2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setColor(this.strokeColor);
        canvas.drawRoundRect(this.rect, 2.0f, 2.0f, this.paint);
        this.paint3.setColor(this.strokeColor);
        canvas.drawRoundRect(this.rect3, 4.0f, 4.0f, this.paint3);
        float f = this.mPower / 100.0f;
        if (f != 0.0f) {
            int i = this.battery_left;
            int i2 = this.battery_inside_margin;
            int i3 = i + i2;
            int i4 = this.battery_top + i2;
            this.rect2.set(i3, i4, ((int) (((this.battery_width - i2) - i2) * f)) + i3, (this.battery_height + i4) - (i2 * 2));
            int i5 = this.mPower;
            if (i5 <= 10) {
                this.paint2.setColor(this.lowColor);
            } else if (i5 <= 20) {
                this.paint2.setColor(this.warnColor);
            } else {
                this.paint2.setColor(this.normalColor);
            }
            canvas.drawRect(this.rect2, this.paint2);
        }
    }

    public void setIsWhiteBg(Boolean bool) {
        this.isWhite = bool.booleanValue();
        if (bool.booleanValue()) {
            this.normalColor = Color.parseColor("#FFA8A8A8");
            this.strokeColor = Color.parseColor("#FFA8A8A8");
        } else {
            this.normalColor = Color.parseColor("#FF222222");
            this.strokeColor = Color.parseColor("#FF2D2D2D");
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
