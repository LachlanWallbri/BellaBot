package com.pudutech.opengl_draw.layer;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.TfLayer;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Point;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.shape.BitmapShape;
import com.pudutech.opengl_draw.shape.DefPointShape;
import com.pudutech.opengl_draw.shape.Shape;
import com.pudutech.opengl_draw.shape.SquareShape;
import com.pudutech.opengl_draw.shape.TextShape;
import com.pudutech.opengl_draw.shape.TextShapeFactory;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class PointsLayer extends DefaultLayer implements TfLayer {
    private BitmapShape bitmapShape;
    private List<Point> mPoints;
    private Shape shape;
    private TextShape textShape;
    private TextShapeFactory textShapeFactory;
    private List<TextShape> mTextShape = new CopyOnWriteArrayList();
    private Color colorBlue = Color.fromHexAndAlpha("FFFFFF", 1.0f);

    /* loaded from: classes5.dex */
    public enum PointType {
        CIRCLE,
        SQUARE
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(VisualizationView visualizationView) {
    }

    public PointsLayer(PointType pointType) {
        int i = C54681.$SwitchMap$com$pudutech$opengl_draw$layer$PointsLayer$PointType[pointType.ordinal()];
        if (i == 1) {
            this.shape = new DefPointShape();
        } else if (i == 2) {
            this.shape = new SquareShape();
        }
        this.mPoints = new CopyOnWriteArrayList();
        this.bitmapShape = new BitmapShape();
    }

    /* renamed from: com.pudutech.opengl_draw.layer.PointsLayer$1 */
    /* loaded from: classes5.dex */
    static /* synthetic */ class C54681 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$opengl_draw$layer$PointsLayer$PointType = new int[PointType.values().length];

        static {
            try {
                $SwitchMap$com$pudutech$opengl_draw$layer$PointsLayer$PointType[PointType.CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$opengl_draw$layer$PointsLayer$PointType[PointType.SQUARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        try {
            if (this.mTextShape.size() == 0) {
                for (int i = 0; i < this.mPoints.size(); i++) {
                    this.textShapeFactory = new TextShapeFactory(visualizationView, gl10, this.mPoints.get(i).getName());
                    this.textShapeFactory.loadFont(Typeface.DEFAULT, 20, 2, 2);
                    this.textShape = this.textShapeFactory.newTextShape(this.mPoints.get(i).getName(), r3.getBitmap().getHeight());
                    this.mTextShape.add(this.textShape);
                }
            }
            if (this.shape == null || this.mPoints == null || this.mPoints.size() == 0) {
                return;
            }
            for (int i2 = 0; i2 < this.mPoints.size(); i2++) {
                Point point = this.mPoints.get(i2);
                if (point.getColor() != null) {
                    this.colorBlue = point.getColor();
                }
                double[] dArr = {0.0d, 0.0d, (-visualizationView.getCamera().getDelta()) - 1.5707963267948966d};
                double[] dArr2 = {0.0d, 0.0d, (-visualizationView.getCamera().getDelta()) + 1.5707963267948966d};
                Transform transform = new Transform(point.getTransform().getTranslation(), new Quaternion(dArr));
                Transform transform2 = new Transform(point.getTransform().getTranslation(), new Quaternion(dArr2));
                if (this.mTextShape.size() == this.mPoints.size()) {
                    this.mTextShape.get(i2).setColor(this.colorBlue);
                    this.mTextShape.get(i2).setTransform(transform);
                    this.mTextShape.get(i2).draw(visualizationView, gl10);
                }
                this.shape.setColor(this.colorBlue);
                this.shape.setTransform(Transform.translation(point.getTransform().getTranslation()));
                this.shape.draw(visualizationView, gl10);
                this.bitmapShape.updata(transform2, 0.3f, point.getBitmap());
                this.bitmapShape.draw(visualizationView, gl10);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return GraphName.m3302of("PointLayer");
    }

    public void add(Point point) {
        this.mTextShape.clear();
        this.mPoints.add(point);
    }

    public void addAll(List<Point> list) {
        this.mPoints.clear();
        this.mTextShape.clear();
        this.mPoints.addAll(list);
    }

    public void remove(String str) {
        this.mTextShape.clear();
        List<Point> list = this.mPoints;
        if (list != null) {
            for (Point point : list) {
                if (point.getName().equals(str)) {
                    this.mPoints.remove(point);
                }
            }
        }
    }

    public boolean reName(String str, String str2) {
        this.mTextShape.clear();
        List<Point> list = this.mPoints;
        boolean z = false;
        if (list != null) {
            for (Point point : list) {
                if (point.getName().equals(str)) {
                    point.setName(str2);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean reColor(String str, Color color) {
        this.mTextShape.clear();
        List<Point> list = this.mPoints;
        boolean z = false;
        if (list != null) {
            for (Point point : list) {
                if (point.getName().equals(str)) {
                    point.setColor(color);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean reBitmap(String str, Bitmap bitmap) {
        this.mTextShape.clear();
        List<Point> list = this.mPoints;
        boolean z = false;
        if (list != null) {
            for (Point point : list) {
                if (point.getName().equals(str)) {
                    point.setBitmap(bitmap);
                    z = true;
                }
            }
        }
        return z;
    }

    public void clear() {
        this.mTextShape.clear();
        this.mPoints.clear();
    }
}
