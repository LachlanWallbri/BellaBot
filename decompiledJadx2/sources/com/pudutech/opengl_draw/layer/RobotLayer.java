package com.pudutech.opengl_draw.layer;

import android.graphics.Bitmap;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.TfLayer;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.shape.BitmapShape;
import com.pudutech.opengl_draw.shape.PixelSpacePoiShape;
import com.pudutech.opengl_draw.shape.Shape;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class RobotLayer extends DefaultLayer implements TfLayer {
    private Bitmap bitmap;
    private Transform transform;
    private Color colorRobot = Color.fromHexAndAlpha("ffffff", 1.0f);
    private final Object mutex = new Object();
    private Shape shape = new PixelSpacePoiShape();
    private BitmapShape bitmapShape = new BitmapShape();

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(VisualizationView visualizationView) {
    }

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return GraphName.m3302of("RobotLayer");
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        if (this.shape == null || this.transform == null) {
            return;
        }
        synchronized (this.mutex) {
            this.shape.setColor(this.colorRobot);
            this.shape.setTransform(this.transform);
            this.shape.draw(visualizationView, gl10);
            if (this.bitmap != null) {
                this.bitmapShape.updata(this.transform, 0.3f, this.bitmap);
                this.bitmapShape.draw(visualizationView, gl10);
            }
        }
    }

    public void update(Transform transform, Bitmap bitmap) {
        synchronized (this.mutex) {
            this.transform = transform;
            this.bitmap = bitmap;
        }
    }
}
