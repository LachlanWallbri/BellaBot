package com.pudutech.opengl_draw.base;

import android.view.MotionEvent;
import com.pudutech.opengl_draw.node.NodeMainExecutor;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public interface Layer extends OpenGlDrawable {
    String getName();

    void init(NodeMainExecutor nodeMainExecutor);

    boolean isEvent();

    void onShutdown(VisualizationView visualizationView);

    void onStart(VisualizationView visualizationView);

    void onSurfaceChanged(VisualizationView visualizationView, GL10 gl10, int i, int i2);

    void onSurfaceCreated(VisualizationView visualizationView, GL10 gl10, EGLConfig eGLConfig);

    boolean onTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent);

    void setEvent(boolean z);

    void setName(String str);
}
