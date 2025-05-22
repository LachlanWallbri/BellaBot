package com.pudutech.opengl_draw.layer;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.pudutech.opengl_draw.base.RotateGestureDetector;
import com.pudutech.opengl_draw.base.VisualizationView;

/* loaded from: classes5.dex */
public class ViewControlLayer extends CameraControlLayer {
    private final Context context;
    private ControlListener controlListener;
    private VisualizationView mapView;
    private RotateGestureDetector rotateGestureDetector;
    private GestureDetector translateGestureDetector;
    private ScaleGestureDetector zoomGestureDetector;
    private String TAG = "ViewControlLayer";
    long twoCount = 0;

    /* loaded from: classes5.dex */
    public interface ControlListener {
        void control(boolean z);
    }

    public void setControlListener(ControlListener controlListener) {
        this.controlListener = controlListener;
    }

    public ViewControlLayer(Context context, VisualizationView visualizationView) {
        this.context = context;
        this.mapView = visualizationView;
        this.mapView.setClickable(false);
    }

    @Override // com.pudutech.opengl_draw.layer.CameraControlLayer, com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public boolean onTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent) {
        Log.d(this.TAG, "onTouchEvent: into");
        if (this.translateGestureDetector == null || this.rotateGestureDetector == null || this.zoomGestureDetector == null) {
            return false;
        }
        int pointerCount = motionEvent.getPointerCount();
        Log.d(this.TAG, "onTouchEvent count: " + pointerCount);
        if (pointerCount == 2) {
            this.twoCount = System.currentTimeMillis();
            return this.rotateGestureDetector.onTouchEvent(motionEvent) || this.zoomGestureDetector.onTouchEvent(motionEvent);
        }
        if (pointerCount == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.twoCount;
            if (j == 0) {
                return this.translateGestureDetector.onTouchEvent(motionEvent) || this.zoomGestureDetector.onTouchEvent(motionEvent);
            }
            if (currentTimeMillis - j > 100) {
                this.twoCount = 0L;
                return this.translateGestureDetector.onTouchEvent(motionEvent) || this.zoomGestureDetector.onTouchEvent(motionEvent);
            }
        }
        return true;
    }

    @Override // com.pudutech.opengl_draw.layer.CameraControlLayer, com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(final VisualizationView visualizationView) {
        visualizationView.post(new Runnable() { // from class: com.pudutech.opengl_draw.layer.ViewControlLayer.1
            @Override // java.lang.Runnable
            public void run() {
                ViewControlLayer viewControlLayer = ViewControlLayer.this;
                viewControlLayer.translateGestureDetector = new GestureDetector(viewControlLayer.context, new GestureDetector.SimpleOnGestureListener() { // from class: com.pudutech.opengl_draw.layer.ViewControlLayer.1.1
                    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                        Log.d(ViewControlLayer.this.TAG, "onScroll distanceX: " + f + "distanceY :" + f2);
                        if (ViewControlLayer.this.controlListener != null) {
                            ViewControlLayer.this.controlListener.control(true);
                        }
                        visualizationView.getCamera().translate(f, f2);
                        return true;
                    }
                });
                ViewControlLayer.this.rotateGestureDetector = new RotateGestureDetector(new RotateGestureDetector.OnRotateGestureListener() { // from class: com.pudutech.opengl_draw.layer.ViewControlLayer.1.2
                    @Override // com.pudutech.opengl_draw.base.RotateGestureDetector.OnRotateGestureListener
                    public boolean onRotate(MotionEvent motionEvent, MotionEvent motionEvent2, double d) {
                        float x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                        float y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                        Log.d(ViewControlLayer.this.TAG, "onRotate focusX: " + x + "focusY :" + y);
                        visualizationView.getCamera().rotate((double) x, (double) y, -d);
                        if (ViewControlLayer.this.controlListener != null) {
                            ViewControlLayer.this.controlListener.control(true);
                        }
                        return false;
                    }
                });
                ViewControlLayer viewControlLayer2 = ViewControlLayer.this;
                viewControlLayer2.zoomGestureDetector = new ScaleGestureDetector(viewControlLayer2.context, new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.pudutech.opengl_draw.layer.ViewControlLayer.1.3
                    @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
                    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                        if (!scaleGestureDetector.isInProgress()) {
                            return false;
                        }
                        float focusX = scaleGestureDetector.getFocusX();
                        float focusY = scaleGestureDetector.getFocusY();
                        float scaleFactor = scaleGestureDetector.getScaleFactor();
                        Log.d(ViewControlLayer.this.TAG, "onScale factor: " + scaleFactor);
                        visualizationView.getCamera().zoom((double) focusX, (double) focusY, (double) scaleFactor);
                        if (ViewControlLayer.this.controlListener != null) {
                            ViewControlLayer.this.controlListener.control(true);
                        }
                        return true;
                    }
                });
            }
        });
    }
}
