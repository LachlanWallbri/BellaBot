package com.pudutech.opengl_draw.layer;

import android.view.MotionEvent;
import com.pudutech.opengl_draw.base.Layer;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.node.NodeMainExecutor;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public abstract class DefaultLayer implements Layer {
    private boolean isEvent = true;
    private String name;

    @Override // com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public void init(NodeMainExecutor nodeMainExecutor) {
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public void onShutdown(VisualizationView visualizationView) {
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public void onStart(VisualizationView visualizationView) {
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public void onSurfaceChanged(VisualizationView visualizationView, GL10 gl10, int i, int i2) {
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public void onSurfaceCreated(VisualizationView visualizationView, GL10 gl10, EGLConfig eGLConfig) {
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public boolean onTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent) {
        return false;
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public String getName() {
        return this.name;
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public void setName(String str) {
        this.name = str;
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public boolean isEvent() {
        return this.isEvent;
    }

    @Override // com.pudutech.opengl_draw.base.Layer
    public void setEvent(boolean z) {
        this.isEvent = z;
    }
}
