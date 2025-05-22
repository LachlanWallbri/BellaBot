package com.pudutech.opengl_draw.base;

import android.view.MotionEvent;
import com.pudutech.opengl_draw.util.OpenglMath;

/* loaded from: classes5.dex */
public class RotateGestureDetector {
    private static final double MAX_DELTA_ANGLE = 0.1d;
    private final OnRotateGestureListener listener;
    private MotionEvent previousMotionEvent;

    /* loaded from: classes5.dex */
    public interface OnRotateGestureListener {
        boolean onRotate(MotionEvent motionEvent, MotionEvent motionEvent2, double d);
    }

    public RotateGestureDetector(OnRotateGestureListener onRotateGestureListener) {
        this.listener = onRotateGestureListener;
    }

    private double angle(MotionEvent motionEvent) {
        return Math.atan2(motionEvent.getY(0) - motionEvent.getY(1), motionEvent.getX(0) - motionEvent.getX(1));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != 2) {
            return false;
        }
        MotionEvent motionEvent2 = this.previousMotionEvent;
        if (motionEvent2 == null) {
            this.previousMotionEvent = MotionEvent.obtain(motionEvent);
            return false;
        }
        boolean onRotate = this.listener.onRotate(this.previousMotionEvent, motionEvent, OpenglMath.clamp(angle(motionEvent2) - angle(motionEvent), -0.1d, MAX_DELTA_ANGLE));
        this.previousMotionEvent.recycle();
        this.previousMotionEvent = MotionEvent.obtain(motionEvent);
        return onRotate;
    }
}
