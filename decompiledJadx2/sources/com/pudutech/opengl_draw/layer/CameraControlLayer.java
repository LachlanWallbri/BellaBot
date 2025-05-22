package com.pudutech.opengl_draw.layer;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import androidx.core.util.Preconditions;
import androidx.core.view.GestureDetectorCompat;
import com.pudutech.opengl_draw.base.RotateGestureDetector;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.concurrent.ListenerGroup;
import com.pudutech.opengl_draw.concurrent.SignalRunnable;
import com.pudutech.opengl_draw.node.NodeMainExecutor;

/* loaded from: classes5.dex */
public class CameraControlLayer extends DefaultLayer {
    private CameraControlListener listener;
    private ListenerGroup<CameraControlListener> listeners;
    private RotateGestureDetector rotateGestureDetector;
    private GestureDetectorCompat translateGestureDetector;
    private ScaleGestureDetector zoomGestureDetector;

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void init(NodeMainExecutor nodeMainExecutor) {
    }

    public void addListener(CameraControlListener cameraControlListener) {
        Preconditions.checkNotNull(this.listeners);
        this.listeners.add(cameraControlListener);
    }

    public void setListener(CameraControlListener cameraControlListener) {
        this.listener = cameraControlListener;
    }

    public CameraControlListener getListener() {
        return this.listener;
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public boolean onTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent) {
        GestureDetectorCompat gestureDetectorCompat = this.translateGestureDetector;
        if (gestureDetectorCompat == null || this.rotateGestureDetector == null || this.zoomGestureDetector == null) {
            return false;
        }
        return gestureDetectorCompat.onTouchEvent(motionEvent) || this.rotateGestureDetector.onTouchEvent(motionEvent) || this.zoomGestureDetector.onTouchEvent(motionEvent) || super.onTouchEvent(visualizationView, motionEvent);
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(final VisualizationView visualizationView) {
        visualizationView.post(new Runnable() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1
            @Override // java.lang.Runnable
            public void run() {
                CameraControlLayer.this.translateGestureDetector = new GestureDetectorCompat(visualizationView.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1.1
                    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                    public boolean onDown(MotionEvent motionEvent) {
                        return true;
                    }

                    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, final float f, final float f2) {
                        visualizationView.getCamera().translate(-f, f2);
                        CameraControlLayer.this.listeners.signal(new SignalRunnable<CameraControlListener>() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1.1.1
                            @Override // com.pudutech.opengl_draw.concurrent.SignalRunnable
                            public void run(CameraControlListener cameraControlListener) {
                                cameraControlListener.onTranslate(-f, f2);
                            }
                        });
                        return true;
                    }

                    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
                    public boolean onDoubleTap(final MotionEvent motionEvent) {
                        CameraControlLayer.this.listeners.signal(new SignalRunnable<CameraControlListener>() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1.1.2
                            @Override // com.pudutech.opengl_draw.concurrent.SignalRunnable
                            public void run(CameraControlListener cameraControlListener) {
                                cameraControlListener.onDoubleTap(motionEvent.getX(), motionEvent.getY());
                            }
                        });
                        return true;
                    }
                });
                CameraControlLayer.this.rotateGestureDetector = new RotateGestureDetector(new RotateGestureDetector.OnRotateGestureListener() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1.2
                    @Override // com.pudutech.opengl_draw.base.RotateGestureDetector.OnRotateGestureListener
                    public boolean onRotate(MotionEvent motionEvent, MotionEvent motionEvent2, final double d) {
                        final float x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
                        final float y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                        visualizationView.getCamera().rotate(x, y, d);
                        CameraControlLayer.this.listeners.signal(new SignalRunnable<CameraControlListener>() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1.2.1
                            @Override // com.pudutech.opengl_draw.concurrent.SignalRunnable
                            public void run(CameraControlListener cameraControlListener) {
                                cameraControlListener.onRotate(x, y, d);
                            }
                        });
                        return true;
                    }
                });
                CameraControlLayer.this.zoomGestureDetector = new ScaleGestureDetector(visualizationView.getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1.3
                    @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
                    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                        if (!scaleGestureDetector.isInProgress()) {
                            return false;
                        }
                        final float focusX = scaleGestureDetector.getFocusX();
                        final float focusY = scaleGestureDetector.getFocusY();
                        final float scaleFactor = scaleGestureDetector.getScaleFactor();
                        visualizationView.getCamera().zoom(focusX, focusY, scaleFactor);
                        CameraControlLayer.this.listeners.signal(new SignalRunnable<CameraControlListener>() { // from class: com.pudutech.opengl_draw.layer.CameraControlLayer.1.3.1
                            @Override // com.pudutech.opengl_draw.concurrent.SignalRunnable
                            public void run(CameraControlListener cameraControlListener) {
                                cameraControlListener.onZoom(focusX, focusY, scaleFactor);
                            }
                        });
                        return true;
                    }
                });
            }
        });
    }
}
