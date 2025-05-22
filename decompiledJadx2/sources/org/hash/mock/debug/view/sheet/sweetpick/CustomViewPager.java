package org.hash.mock.debug.view.sheet.sweetpick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class CustomViewPager extends ViewPager {
    private float preX;
    private float preY;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            this.preX = motionEvent.getX();
            this.preY = motionEvent.getY();
        } else {
            if (Math.abs(motionEvent.getX() - this.preX) > 4.0f && Math.abs(motionEvent.getX() - this.preX) >= Math.abs(motionEvent.getY() - this.preY)) {
                return true;
            }
            this.preX = motionEvent.getX();
            this.preY = motionEvent.getY();
        }
        return onInterceptTouchEvent;
    }
}
