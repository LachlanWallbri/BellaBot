package com.pudutech.opengl_draw.layer;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.TfLayer;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.Line;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.shape.BitmapShape;
import com.pudutech.opengl_draw.shape.LineShape;
import com.pudutech.opengl_draw.shape.Shape;
import com.pudutech.opengl_draw.util.OpenglMath;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class DoubleLineLayer extends DefaultLayer implements TfLayer {
    private Bitmap bitmap;
    private Vector3 clickVector3;
    private DoubleLineListener doubleLineListener;
    private long endTime;
    private double endX;
    private double endY;
    private long startTime;
    private double startX;
    private double startY;
    private Color colorBlue = Color.fromHexAndAlpha("FFFFFF", 1.0f);
    private float width = 4.0f;
    private boolean isClick = false;
    private boolean isMove = false;
    private double clickDistance = 0.5d;
    private Shape shape = new LineShape();
    private List<Line> mLines = new CopyOnWriteArrayList();
    private BitmapShape bitmapShape = new BitmapShape();
    private List<Vector3> vectorList = new CopyOnWriteArrayList();

    /* loaded from: classes5.dex */
    public interface DoubleLineListener {
        void selectLineNameBack(String str, boolean z);
    }

    public void setDoubleLineListener(DoubleLineListener doubleLineListener) {
        this.doubleLineListener = doubleLineListener;
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public boolean onTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isClick = true;
            this.startTime = System.currentTimeMillis();
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
            this.clickVector3 = visualizationView.getCamera().toCameraFrame((int) motionEvent.getX(), (int) motionEvent.getY());
        } else if (action == 1) {
            this.endTime = System.currentTimeMillis();
            if (!this.isMove && this.endTime - this.startTime < 500) {
                this.isClick = true;
                Line selectDottedLine = selectDottedLine(this.mLines, this.clickVector3);
                if (selectDottedLine != null) {
                    DoubleLineListener doubleLineListener = this.doubleLineListener;
                    if (doubleLineListener != null) {
                        doubleLineListener.selectLineNameBack(selectDottedLine.getName(), !selectDottedLine.isSelect());
                    }
                    selectDottedLine.setSelect(true ^ selectDottedLine.isSelect());
                }
            }
            this.isMove = false;
        } else if (action == 2) {
            this.endX = motionEvent.getX();
            this.endY = motionEvent.getY();
            if (OpenglMath.getLength(this.endX - this.startX, this.endY - this.startY) > 5.0d) {
                this.isMove = true;
            }
        }
        return super.onTouchEvent(visualizationView, motionEvent);
    }

    private Line selectDottedLine(List<Line> list, Vector3 vector3) {
        if (list != null && vector3 != null) {
            for (Line line : list) {
                List<Vector3> vector3List = line.getVector3List();
                for (int i = 0; i < vector3List.size(); i++) {
                    Vector3 vector32 = vector3List.get(i);
                    if (OpenglMath.getLength(vector3.getX() - vector32.getX(), vector3.getY() - vector32.getY()) < this.clickDistance) {
                        return line;
                    }
                }
            }
        }
        return null;
    }

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return GraphName.m3302of("DoubleLaneLayer");
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        List<Line> list;
        super.draw(visualizationView, gl10);
        if (this.shape == null || (list = this.mLines) == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < this.mLines.size(); i++) {
            Line line = this.mLines.get(i);
            if (line.getColor() != null) {
                this.colorBlue = line.getColor();
            }
            this.shape.setColor(this.colorBlue);
            this.shape.setFloatBuffer(line.getVertexFrontBuffer());
            if (line.getWidth() != 0.0f) {
                this.width = line.getWidth();
            }
            this.shape.setWidth(this.width);
            this.shape.draw(visualizationView, gl10);
            if (this.bitmap != null && line.isSelect()) {
                this.bitmapShape.updata(new Transform(line.getCenter(), new Quaternion(new double[]{0.0d, 0.0d, (-visualizationView.getCamera().getDelta()) + 1.5707963267948966d})), 0.3f, this.bitmap);
                this.bitmapShape.draw(visualizationView, gl10);
            }
        }
    }

    public void addAll(List<Line> list) {
        this.mLines.clear();
        for (Line line : list) {
            if (line.getVector3List().size() == 2) {
                addPoint(line.getVector3List().get(0), line.getVector3List().get(line.getVector3List().size() - 1), line);
                this.mLines.add(line);
                this.vectorList.clear();
            } else if (line.getVector3List().size() > 2) {
                this.mLines.add(line);
            }
        }
    }

    private void addPoint(Vector3 vector3, Vector3 vector32, Line line) {
        double x = vector32.getX() - vector3.getX();
        double y = vector32.getY() - vector3.getY();
        int length = (int) (OpenglMath.getLength(x, y) * 10.0d);
        double angleByPoint = OpenglMath.getAngleByPoint(x, y);
        for (int i = 0; i < length; i++) {
            double d = i;
            this.vectorList.add(new Vector3(vector3.getX() + ((Math.cos(angleByPoint) * d) / 10.0d), vector3.getY() + ((Math.sin(angleByPoint) * d) / 10.0d), 0.0d));
        }
        if (line != null) {
            line.setVector3List(new CopyOnWriteArrayList(this.vectorList));
        }
    }

    public boolean setColor(String str, Color color) {
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

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setSelect(String str, boolean z) {
        List<Line> list = this.mLines;
        if (list != null) {
            for (Line line : list) {
                if (line.getName().equals(str)) {
                    line.setSelect(z);
                }
            }
        }
    }
}
