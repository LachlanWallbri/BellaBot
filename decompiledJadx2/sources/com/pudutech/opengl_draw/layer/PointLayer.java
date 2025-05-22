package com.pudutech.opengl_draw.layer;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.TfLayer;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.shape.BitmapShape;
import com.pudutech.opengl_draw.shape.TextShape;
import com.pudutech.opengl_draw.shape.TextShapeFactory;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class PointLayer extends DefaultLayer implements TfLayer {
    private Bitmap bitmap;
    private TextShape textShape;
    private TextShapeFactory textShapeFactory;
    private Transform transform;
    private Color colorRobot = Color.fromHexAndAlpha("ffffff", 1.0f);
    private String name = "";
    private final Object mutex = new Object();
    private BitmapShape bitmapShape = new BitmapShape();

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(VisualizationView visualizationView) {
    }

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return GraphName.m3302of("PointLayer");
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        if (this.textShapeFactory == null && this.bitmap != null) {
            this.textShapeFactory = new TextShapeFactory(visualizationView, gl10, this.name);
            this.textShapeFactory.loadFont(Typeface.DEFAULT, 20, 2, 2);
            this.textShape = this.textShapeFactory.newTextShape(this.name, this.bitmap.getHeight());
        }
        if (this.bitmapShape == null || this.transform == null) {
            return;
        }
        synchronized (this.mutex) {
            double[] dArr = {0.0d, 0.0d, (-visualizationView.getCamera().getDelta()) - 1.5707963267948966d};
            double[] dArr2 = {0.0d, 0.0d, (-visualizationView.getCamera().getDelta()) + 1.5707963267948966d};
            Transform transform = new Transform(this.transform.getTranslation(), new Quaternion(dArr));
            Transform transform2 = new Transform(this.transform.getTranslation(), new Quaternion(dArr2));
            if (this.textShape != null) {
                this.textShape.setColor(this.colorRobot);
                this.textShape.setTransform(transform);
                this.textShape.draw(visualizationView, gl10);
            }
            if (this.bitmap != null) {
                this.bitmapShape.updata(transform2, 0.3f, this.bitmap);
                this.bitmapShape.draw(visualizationView, gl10);
            }
        }
    }

    public void update(Transform transform, Bitmap bitmap, String str) {
        synchronized (this.mutex) {
            this.transform = transform;
            this.bitmap = bitmap;
            this.name = str;
        }
    }
}
