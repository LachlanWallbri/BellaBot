package com.pudutech.opengl_draw.layer;

import android.graphics.Bitmap;
import android.graphics.Typeface;
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
import com.pudutech.opengl_draw.shape.DottedLineShape;
import com.pudutech.opengl_draw.shape.Shape;
import com.pudutech.opengl_draw.shape.TextShape;
import com.pudutech.opengl_draw.shape.TextShapeFactory;
import com.pudutech.opengl_draw.util.OpenglMath;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class DottedLineLayer extends DefaultLayer implements TfLayer {
    private Vector3 clickVector3;
    private Bitmap deleteBitmap;
    private DottedLineLayerListener dottedLineLayerListener;
    private long endTime;
    private double endX;
    private double endY;
    private int name;
    private Line selectLine;
    private long startTime;
    private double startX;
    private double startY;
    private TextShape textShape;
    private TextShapeFactory textShapeFactory;
    private Vector3 vector3Down;
    private Vector3 vector3Move;
    private String TAG = "DottedLineLayer";
    private boolean havFocus = false;
    private Color colorBlue = Color.fromHexAndAlpha("FF0000", 1.0f);
    private String dafName = "虚拟墙";
    private List<TextShape> mTextShape = new CopyOnWriteArrayList();
    Line line = null;
    private boolean isClick = false;
    private boolean isMove = false;
    private boolean isSelect = false;
    private double clickDistance = 0.5d;
    private Shape shape = new DottedLineShape();
    private List<Line> mLines = new CopyOnWriteArrayList();
    private List<Vector3> vectorList = new CopyOnWriteArrayList();
    private BitmapShape bitmapShape = new BitmapShape();

    /* loaded from: classes5.dex */
    public interface DottedLineLayerListener {
        void deleteLineName(String str);

        void dottedLineBack(Vector3 vector3, Vector3 vector32, String str);
    }

    public void setDottedLineLayerListener(DottedLineLayerListener dottedLineLayerListener) {
        this.dottedLineLayerListener = dottedLineLayerListener;
    }

    public void setDefName(String str) {
        this.dafName = str;
    }

    public boolean isHavFocus() {
        return this.havFocus;
    }

    public void setHavFocus(boolean z) {
        this.havFocus = z;
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public boolean onTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent) {
        if (!this.havFocus) {
            return false;
        }
        havFocusTouchEvent(visualizationView, motionEvent);
        return this.havFocus;
    }

    private void notFocusTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent) {
        Line line;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isClick = true;
            this.startTime = System.currentTimeMillis();
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
            this.clickVector3 = visualizationView.getCamera().toCameraFrame((int) motionEvent.getX(), (int) motionEvent.getY());
            return;
        }
        if (action != 1) {
            if (action != 2) {
                return;
            }
            this.endX = motionEvent.getX();
            this.endY = motionEvent.getY();
            if (OpenglMath.getLength(this.endX - this.startX, this.endY - this.startY) > 5.0d) {
                this.isMove = true;
                return;
            }
            return;
        }
        this.endTime = System.currentTimeMillis();
        if (!this.isMove && this.endTime - this.startTime < 500) {
            this.isClick = true;
            if (this.isSelect && (line = this.selectLine) != null && line.getCenter() != null && OpenglMath.getLength(this.clickVector3.getX() - this.selectLine.getCenter().getX(), this.clickVector3.getY() - this.selectLine.getCenter().getY()) < this.clickDistance) {
                this.mLines.remove(this.selectLine);
                DottedLineLayerListener dottedLineLayerListener = this.dottedLineLayerListener;
                if (dottedLineLayerListener != null) {
                    dottedLineLayerListener.deleteLineName(this.selectLine.getName());
                }
                this.isSelect = false;
                return;
            }
            Line selectDottedLine = selectDottedLine(this.mLines, this.clickVector3);
            if (selectDottedLine == null) {
                this.isSelect = false;
            } else {
                this.isSelect = true;
                this.selectLine = selectDottedLine;
            }
        }
        this.isMove = false;
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

    private void havFocusTouchEvent(VisualizationView visualizationView, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.line = new Line();
            this.line.setName(this.dafName + this.name);
            this.mLines.add(this.line);
            this.vector3Down = visualizationView.getCamera().toCameraFrame((int) motionEvent.getX(), (int) motionEvent.getY());
            return;
        }
        if (action != 1) {
            if (action != 2) {
                return;
            }
            this.vector3Move = visualizationView.getCamera().toCameraFrame((int) motionEvent.getX(), (int) motionEvent.getY());
            addPoint(this.vector3Down, this.vector3Move, this.line);
            this.vectorList.clear();
            return;
        }
        this.vector3Move = visualizationView.getCamera().toCameraFrame((int) motionEvent.getX(), (int) motionEvent.getY());
        addPoint(this.vector3Down, this.vector3Move, this.line);
        if (this.vectorList.size() >= 2) {
            DottedLineLayerListener dottedLineLayerListener = this.dottedLineLayerListener;
            if (dottedLineLayerListener != null) {
                Vector3 vector3 = this.vectorList.get(0);
                List<Vector3> list = this.vectorList;
                dottedLineLayerListener.dottedLineBack(vector3, list.get(list.size() - 1), this.dafName + this.name);
            }
        } else {
            DottedLineLayerListener dottedLineLayerListener2 = this.dottedLineLayerListener;
            if (dottedLineLayerListener2 != null) {
                dottedLineLayerListener2.dottedLineBack(null, null, this.dafName + this.name);
            }
            this.mLines.remove(this.line);
        }
        this.name++;
        this.vectorList.clear();
        this.havFocus = false;
        this.mTextShape.clear();
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

    public void addAll(List<Line> list) {
        this.mLines.clear();
        for (Line line : list) {
            if (line.getVector3List().size() >= 2) {
                addPoint(line.getVector3List().get(0), line.getVector3List().get(1), line);
                this.mLines.add(line);
                this.vectorList.clear();
            }
        }
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        Line line;
        super.draw(visualizationView, gl10);
        char c = 2;
        if (this.mTextShape.size() == 0) {
            for (int i = 0; i < this.mLines.size(); i++) {
                Line line2 = this.mLines.get(i);
                if (line2.getVector3List() == null || line2.getVector3List().size() == 0) {
                    break;
                }
                line2.setCenter(line2.getVector3List().get(line2.getVector3List().size() / 2));
                this.textShapeFactory = new TextShapeFactory(visualizationView, gl10, line2.getName());
                this.textShapeFactory.loadFont(Typeface.DEFAULT, 20, 2, 2);
                this.textShape = this.textShapeFactory.newTextShape(this.mLines.get(i).getName(), 0.0f);
                this.mTextShape.add(this.textShape);
            }
        }
        if (this.shape != null && this.mLines.size() != 0) {
            int i2 = 0;
            while (i2 < this.mLines.size()) {
                Line line3 = this.mLines.get(i2);
                double[] dArr = new double[3];
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[c] = (-visualizationView.getCamera().getDelta()) - 1.5707963267948966d;
                Transform transform = new Transform(line3.getCenter(), new Quaternion(dArr));
                if (this.mTextShape.size() > i2) {
                    this.mTextShape.get(i2).setColor(this.colorBlue);
                    this.mTextShape.get(i2).setTransform(transform);
                    this.mTextShape.get(i2).draw(visualizationView, gl10);
                }
                if (line3.getVertexFrontBuffer() != null) {
                    this.shape.setColor(this.colorBlue);
                    this.shape.setFloatBuffer(line3.getVertexFrontBuffer());
                    this.shape.draw(visualizationView, gl10);
                }
                i2++;
                c = 2;
            }
        }
        if (!this.isSelect || (line = this.selectLine) == null || this.deleteBitmap == null || line.getCenter() == null) {
            return;
        }
        this.bitmapShape.updata(new Transform(this.selectLine.getCenter(), new Quaternion(new double[]{0.0d, 0.0d, (-visualizationView.getCamera().getDelta()) + 1.5707963267948966d})), 0.3f, this.deleteBitmap);
        this.bitmapShape.draw(visualizationView, gl10);
    }

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return GraphName.m3302of("DottedLineLayer");
    }

    public void setBitmap(Bitmap bitmap) {
        this.deleteBitmap = bitmap;
    }

    public void remove(String str) {
        this.mTextShape.clear();
        if (this.mLines != null) {
            for (int i = 0; i < this.mLines.size(); i++) {
                Line line = this.mLines.get(i);
                if (line.getName().equals(str)) {
                    if (i == this.mLines.size() - 1) {
                        this.name--;
                    }
                    if (this.selectLine == line) {
                        this.isSelect = false;
                    }
                    this.mLines.remove(line);
                }
            }
        }
    }
}
