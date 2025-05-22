package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/* loaded from: classes4.dex */
public class FloatView extends LinearLayout {
    private boolean isAllowTouch;
    private IFloatViewClick listener;
    private float mTouchStartX;
    private float mTouchStartY;

    /* renamed from: wm */
    private WindowManager f4948wm;
    private WindowManager.LayoutParams wmParams;

    /* renamed from: x */
    private float f4949x;

    /* renamed from: y */
    private float f4950y;

    /* loaded from: classes4.dex */
    public interface IFloatViewClick {
        void onFloatViewClick();
    }

    public FloatView(Context context, int i, int i2, int i3) {
        super(context);
        this.isAllowTouch = true;
        init(LayoutInflater.from(getContext()).inflate(i3, (ViewGroup) null), i, i2);
    }

    public FloatView(Context context, int i, int i2, View view) {
        super(context);
        this.isAllowTouch = true;
        init(view, i, i2);
    }

    private void init(View view, int i, int i2) {
        this.f4948wm = (WindowManager) getContext().getSystemService("window");
        this.wmParams = new WindowManager.LayoutParams();
        WindowManager.LayoutParams layoutParams = this.wmParams;
        layoutParams.type = 2005;
        layoutParams.gravity = 51;
        layoutParams.format = 1;
        layoutParams.flags = 8;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.x = i;
        layoutParams.y = i2;
        if (view != null) {
            addView(view);
        }
    }

    public void updateFloatViewPosition(int i, int i2) {
        WindowManager.LayoutParams layoutParams = this.wmParams;
        layoutParams.x = i;
        layoutParams.y = i2;
        this.f4948wm.updateViewLayout(this, layoutParams);
    }

    public void setFloatViewClickListener(IFloatViewClick iFloatViewClick) {
        this.listener = iFloatViewClick;
    }

    public boolean addToWindow() {
        if (this.f4948wm != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (isAttachedToWindow()) {
                    return false;
                }
                this.f4948wm.addView(this, this.wmParams);
                return true;
            }
            try {
                if (getParent() == null) {
                    this.f4948wm.addView(this, this.wmParams);
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public void setIsAllowTouch(boolean z) {
        this.isAllowTouch = z;
    }

    public boolean removeFromWindow() {
        if (this.f4948wm != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (!isAttachedToWindow()) {
                    return false;
                }
                this.f4948wm.removeViewImmediate(this);
                return true;
            }
            try {
                if (getParent() != null) {
                    this.f4948wm.removeViewImmediate(this);
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isAllowTouch;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mTouchStartX = ((int) motionEvent.getRawX()) - (getMeasuredWidth() / 2);
            this.mTouchStartY = ((int) motionEvent.getRawY()) - (getMeasuredHeight() / 2);
            return true;
        }
        if (action == 1) {
            this.f4950y = ((int) motionEvent.getRawY()) - (getMeasuredHeight() / 2);
            this.f4949x = ((int) motionEvent.getRawX()) - (getMeasuredWidth() / 2);
            if (Math.abs(this.f4950y - this.mTouchStartY) > 10.0f || Math.abs(this.f4949x - this.mTouchStartX) > 10.0f) {
                this.f4948wm.updateViewLayout(this, this.wmParams);
            } else {
                IFloatViewClick iFloatViewClick = this.listener;
                if (iFloatViewClick != null) {
                    iFloatViewClick.onFloatViewClick();
                }
            }
            return true;
        }
        if (action != 2) {
            return false;
        }
        this.wmParams.x = ((int) motionEvent.getRawX()) - (getMeasuredWidth() / 2);
        this.wmParams.y = ((int) motionEvent.getRawY()) - (getMeasuredHeight() / 2);
        if (Math.abs(this.wmParams.y - this.mTouchStartY) > 10.0f || Math.abs(this.wmParams.x - this.mTouchStartX) > 10.0f) {
            this.f4948wm.updateViewLayout(this, this.wmParams);
        }
        return true;
    }
}
