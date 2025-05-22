package com.pudutech.opengl_draw.layer;

import android.graphics.Typeface;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.TfLayer;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.shape.LineShape;
import com.pudutech.opengl_draw.shape.Shape;
import com.pudutech.opengl_draw.shape.TextShape;
import com.pudutech.opengl_draw.shape.TextShapeFactory;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class LinesLayer extends DefaultLayer implements TfLayer {
    private TextShape textShape;
    private TextShapeFactory textShapeFactory;
    private Color colorBlue = Color.fromHexAndAlpha("FFFFFF", 1.0f);
    private List<TextShape> mTextShape = new CopyOnWriteArrayList();
    private float width = 4.0f;
    private Shape shape = new LineShape();
    private final Object mutex = new Object();
    private List<Line> mLines = new CopyOnWriteArrayList();

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return GraphName.m3302of("LineLayer");
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        super.draw(visualizationView, gl10);
        try {
            if (this.mTextShape.size() == 0) {
                for (int i = 0; i < this.mLines.size(); i++) {
                    this.textShapeFactory = new TextShapeFactory(visualizationView, gl10, this.mLines.get(i).getName());
                    this.textShapeFactory.loadFont(Typeface.DEFAULT, 20, 2, 2);
                    this.textShape = this.textShapeFactory.newTextShape(this.mLines.get(i).getName(), 0.0f);
                    this.mTextShape.add(this.textShape);
                }
            }
            if (this.shape == null || this.mLines == null || this.mLines.size() == 0) {
                return;
            }
            for (int i2 = 0; i2 < this.mLines.size(); i2++) {
                Line line = this.mLines.get(i2);
                if (line.getColor() != null) {
                    this.colorBlue = line.getColor();
                }
                Transform transform = new Transform(line.getCenter(), new Quaternion(new double[]{0.0d, 0.0d, (-visualizationView.getCamera().getDelta()) - 1.5707963267948966d}));
                if (this.mTextShape.size() == this.mLines.size()) {
                    this.mTextShape.get(i2).setColor(this.colorBlue);
                    this.mTextShape.get(i2).setTransform(transform);
                    this.mTextShape.get(i2).draw(visualizationView, gl10);
                }
                this.shape.setColor(this.colorBlue);
                this.shape.setFloatBuffer(line.getVertexFrontBuffer());
                if (line.getWidth() != 0.0f) {
                    this.width = line.getWidth();
                }
                this.shape.setWidth(this.width);
                this.shape.draw(visualizationView, gl10);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(VisualizationView visualizationView) {
        super.onStart(visualizationView);
    }

    public void add(Line line) {
        this.mLines.add(line);
    }

    public void reLast(Line line) {
        this.mLines.set(r0.size() - 1, line);
    }

    public void addAlll(List<Line> list) {
        this.mTextShape.clear();
        this.mLines.clear();
        this.mLines.addAll(list);
    }

    public void remove(String str) {
        this.mTextShape.clear();
        List<Line> list = this.mLines;
        if (list != null) {
            for (Line line : list) {
                if (line.getName().equals(str)) {
                    this.mLines.remove(line);
                }
            }
        }
    }

    public boolean reColor(String str, Color color) {
        this.mTextShape.clear();
        List<Line> list = this.mLines;
        boolean z = false;
        if (list != null) {
            for (Line line : list) {
                if (line.getName().equals(str)) {
                    line.setColor(color);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean reName(String str, String str2) {
        this.mTextShape.clear();
        List<Line> list = this.mLines;
        boolean z = false;
        if (list != null) {
            for (Line line : list) {
                if (line.getName().equals(str)) {
                    line.setName(str2);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean setWidth(String str, float f) {
        this.mTextShape.clear();
        List<Line> list = this.mLines;
        boolean z = false;
        if (list != null) {
            for (Line line : list) {
                if (line.getName().equals(str)) {
                    line.setWidth(f);
                    z = true;
                }
            }
        }
        return z;
    }

    public void clear() {
        this.mTextShape.clear();
        this.mLines.clear();
    }
}
