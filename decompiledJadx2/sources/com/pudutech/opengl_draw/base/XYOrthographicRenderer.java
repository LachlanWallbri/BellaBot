package com.pudutech.opengl_draw.base;

import android.opengl.GLSurfaceView;
import com.pudutech.opengl_draw.namespace.GraphName;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class XYOrthographicRenderer implements GLSurfaceView.Renderer {
    private static final Color BACKGROUND_COLOR = new Color(0.0f, 0.0f, 0.0f, 1.0f);
    private final VisualizationView view;

    public XYOrthographicRenderer(VisualizationView visualizationView) {
        this.view = visualizationView;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Iterator<Layer> it = this.view.getLayers().iterator();
        while (it.hasNext()) {
            it.next().onSurfaceCreated(this.view, gl10, eGLConfig);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        Viewport viewport = new Viewport(i, i2);
        viewport.apply(gl10);
        this.view.getCamera().setViewport(viewport);
        gl10.glMatrixMode(5888);
        gl10.glEnable(3042);
        gl10.glBlendFunc(770, 771);
        gl10.glDisable(2929);
        gl10.glClearColor(BACKGROUND_COLOR.getRed(), BACKGROUND_COLOR.getGreen(), BACKGROUND_COLOR.getBlue(), BACKGROUND_COLOR.getAlpha());
        Iterator<Layer> it = this.view.getLayers().iterator();
        while (it.hasNext()) {
            it.next().onSurfaceChanged(this.view, gl10, i, i2);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(16384);
        gl10.glLoadIdentity();
        this.view.getCamera().apply(gl10);
        drawLayers(gl10);
    }

    private void drawLayers(GL10 gl10) {
        for (Layer layer : this.view.getLayers()) {
            gl10.glPushMatrix();
            if (layer instanceof TfLayer) {
                GraphName frame = ((TfLayer) layer).getFrame();
                if (frame != null) {
                    this.view.getCamera().applyFrameTransform(gl10, frame);
                    layer.draw(this.view, gl10);
                } else if (frame != null && this.view.getCamera().applyFrameTransform(gl10, frame)) {
                    layer.draw(this.view, gl10);
                }
            } else {
                layer.draw(this.view, gl10);
            }
            gl10.glPopMatrix();
        }
    }
}
