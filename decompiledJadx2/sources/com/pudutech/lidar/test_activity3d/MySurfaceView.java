package com.pudutech.lidar.test_activity3d;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.test_activity3d.shape.Circle;
import com.pudutech.lidar.test_activity3d.shape.Line;
import com.pudutech.lidar.test_activity3d.shape.Point;
import java.util.HashMap;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MySurfaceView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0014\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/lidar/test_activity3d/MySurfaceView;", "Landroid/opengl/GLSurfaceView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TOUCH_SCALE_FACTOR", "", "mCloudPointDatas", "Lcom/pudutech/lidar/ListLidarNodePool;", "myPreviousX", "myPreviousY", "myRenderer", "Lcom/pudutech/lidar/test_activity3d/MySurfaceView$SceneRenderer;", "onTouchEvent", "", "event", "Landroid/view/MotionEvent;", "updateLidar", "", "list", "", "Lcom/pudutech/lidar/LidarNode;", "SceneRenderer", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MySurfaceView extends GLSurfaceView {
    private final float TOUCH_SCALE_FACTOR;
    private HashMap _$_findViewCache;
    private ListLidarNodePool mCloudPointDatas;
    private float myPreviousX;
    private float myPreviousY;
    private SceneRenderer myRenderer;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public MySurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TOUCH_SCALE_FACTOR = 0.5625f;
        this.mCloudPointDatas = ListLidarNodePool.INSTANCE.obtain();
        this.myRenderer = new SceneRenderer();
        setRenderer(this.myRenderer);
        setRenderMode(0);
    }

    public /* synthetic */ MySurfaceView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        float y = event.getY();
        float x = event.getX();
        int action = event.getAction();
        if (action != 0 && action == 2) {
            float f = y - this.myPreviousY;
            float f2 = x - this.myPreviousX;
            SceneRenderer sceneRenderer = this.myRenderer;
            if (sceneRenderer == null) {
                Intrinsics.throwNpe();
            }
            Line line = sceneRenderer.getLine();
            line.setYAngle(line.getYAngle() + (this.TOUCH_SCALE_FACTOR * f2));
            SceneRenderer sceneRenderer2 = this.myRenderer;
            if (sceneRenderer2 == null) {
                Intrinsics.throwNpe();
            }
            Line line2 = sceneRenderer2.getLine();
            line2.setZAngle(line2.getZAngle() + (this.TOUCH_SCALE_FACTOR * f));
            SceneRenderer sceneRenderer3 = this.myRenderer;
            if (sceneRenderer3 == null) {
                Intrinsics.throwNpe();
            }
            Circle circle = sceneRenderer3.getCircle();
            circle.setYAngle(circle.getYAngle() + (f2 * this.TOUCH_SCALE_FACTOR));
            SceneRenderer sceneRenderer4 = this.myRenderer;
            if (sceneRenderer4 == null) {
                Intrinsics.throwNpe();
            }
            Circle circle2 = sceneRenderer4.getCircle();
            circle2.setZAngle(circle2.getZAngle() + (f * this.TOUCH_SCALE_FACTOR));
            requestRender();
        }
        this.myPreviousY = y;
        this.myPreviousX = x;
        return true;
    }

    public final void updateLidar(List<? extends LidarNode> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        float[] fArr = new float[list.size() * 3];
        int i = 0;
        for (LidarNode lidarNode : list) {
            int i2 = i + 1;
            fArr[i] = (float) lidarNode.ptX;
            int i3 = i2 + 1;
            fArr[i2] = (float) lidarNode.ptY;
            fArr[i3] = (float) lidarNode.ptZ;
            lidarNode.recycle();
            i = i3 + 1;
        }
        SceneRenderer sceneRenderer = this.myRenderer;
        if (sceneRenderer == null) {
            Intrinsics.throwNpe();
        }
        sceneRenderer.getPoint().getCloudPointQueue().offer(fArr);
        requestRender();
    }

    /* compiled from: MySurfaceView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J \u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006 "}, m3961d2 = {"Lcom/pudutech/lidar/test_activity3d/MySurfaceView$SceneRenderer;", "Landroid/opengl/GLSurfaceView$Renderer;", "()V", "circle", "Lcom/pudutech/lidar/test_activity3d/shape/Circle;", "getCircle", "()Lcom/pudutech/lidar/test_activity3d/shape/Circle;", "setCircle", "(Lcom/pudutech/lidar/test_activity3d/shape/Circle;)V", "line", "Lcom/pudutech/lidar/test_activity3d/shape/Line;", "getLine", "()Lcom/pudutech/lidar/test_activity3d/shape/Line;", "setLine", "(Lcom/pudutech/lidar/test_activity3d/shape/Line;)V", "point", "Lcom/pudutech/lidar/test_activity3d/shape/Point;", "getPoint", "()Lcom/pudutech/lidar/test_activity3d/shape/Point;", "setPoint", "(Lcom/pudutech/lidar/test_activity3d/shape/Point;)V", "onDrawFrame", "", "gl", "Ljavax/microedition/khronos/opengles/GL10;", "onSurfaceChanged", "width", "", "height", "onSurfaceCreated", "config", "Ljavax/microedition/khronos/egl/EGLConfig;", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    private static final class SceneRenderer implements GLSurfaceView.Renderer {
        private Line line = new Line();
        private Point point = new Point();
        private Circle circle = new Circle();

        public final Line getLine() {
            return this.line;
        }

        public final void setLine(Line line) {
            Intrinsics.checkParameterIsNotNull(line, "<set-?>");
            this.line = line;
        }

        public final Point getPoint() {
            return this.point;
        }

        public final void setPoint(Point point) {
            Intrinsics.checkParameterIsNotNull(point, "<set-?>");
            this.point = point;
        }

        public final Circle getCircle() {
            return this.circle;
        }

        public final void setCircle(Circle circle) {
            Intrinsics.checkParameterIsNotNull(circle, "<set-?>");
            this.circle = circle;
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onDrawFrame(GL10 gl) {
            Intrinsics.checkParameterIsNotNull(gl, "gl");
            gl.glEnable(2884);
            gl.glShadeModel(7425);
            gl.glFrontFace(2305);
            gl.glClear(16640);
            gl.glMatrixMode(5888);
            gl.glLoadIdentity();
            gl.glTranslatef(0.0f, 0.0f, 0.0f);
            GLU.gluLookAt(gl, 0.0f, 0.0f, 2.5f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            this.line.draw(gl);
            this.point.draw(gl);
            this.circle.draw(gl);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            Intrinsics.checkParameterIsNotNull(gl, "gl");
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(5889);
            gl.glLoadIdentity();
            float f = width / height;
            gl.glFrustumf(-f, f, -1.0f, 1.0f, 1.0f, 20.0f);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            Intrinsics.checkParameterIsNotNull(gl, "gl");
            Intrinsics.checkParameterIsNotNull(config, "config");
            gl.glDisable(3024);
            gl.glHint(3152, 4353);
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            gl.glEnable(2929);
        }
    }
}
