package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import com.pudutech.peanut.robot_ui.C5508R;

/* loaded from: classes5.dex */
public class ZoomTextView extends TextView {
    public static final int AMPLIFY = 1;
    public static final int REDUCE = 2;
    private boolean isZoom;
    private Context mContext;
    private Handler mHandler;
    private SpannableString msp;
    float strethScale;

    public void reduce() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.pudutech.peanut.robot_ui.widget.ZoomTextView.2
            @Override // java.lang.Runnable
            public void run() {
                if (ZoomTextView.this.mContext != null) {
                    ZoomTextView zoomTextView = ZoomTextView.this;
                    zoomTextView.setTextColor(zoomTextView.mContext.getResources().getColor(C5508R.color.theme_main_color));
                }
                ZoomTextView.this.mHandler.sendEmptyMessage(1);
            }
        }, 1500L);
    }

    public void amplify() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.pudutech.peanut.robot_ui.widget.ZoomTextView.3
            @Override // java.lang.Runnable
            public void run() {
                if (ZoomTextView.this.mContext != null) {
                    ZoomTextView zoomTextView = ZoomTextView.this;
                    zoomTextView.setTextColor(zoomTextView.mContext.getResources().getColor(C5508R.color.white));
                }
                ZoomTextView.this.mHandler.sendEmptyMessage(2);
            }
        }, 1500L);
    }

    public ZoomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.strethScale = 1.3f;
        this.mHandler = new Handler() { // from class: com.pudutech.peanut.robot_ui.widget.ZoomTextView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    ZoomTextView.this.amplify();
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    ZoomTextView.this.reduce();
                }
            }
        };
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(1500L);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        startAnimation(scaleAnimation);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public ZoomTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.mContext = context;
    }

    public ZoomTextView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public boolean isZoom() {
        return this.isZoom;
    }

    public void setZoom(boolean z) {
        this.isZoom = z;
        if (z) {
            reduce();
        }
    }
}
