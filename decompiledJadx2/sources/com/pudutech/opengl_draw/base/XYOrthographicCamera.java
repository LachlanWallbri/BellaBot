package com.pudutech.opengl_draw.base;

import android.util.Log;
import androidx.core.util.Preconditions;
import com.pudutech.opengl_draw.geometry.FrameTransform;
import com.pudutech.opengl_draw.geometry.FrameTransformTree;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.util.OpenglMath;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class XYOrthographicCamera {
    private static final float MAXIMUM_ZOOM_FACTOR = 5.0f;
    private static final float MINIMUM_ZOOM_FACTOR = 0.1f;
    private static final double PIXELS_PER_METER = 100.0d;
    private static final Transform ROS_TO_SCREEN_TRANSFORM = Transform.zRotation(1.5707963267948966d).scale(PIXELS_PER_METER);
    private Transform cameraToRosTransform;
    private GraphName frame;
    private final FrameTransformTree frameTransformTree;
    private Transform laserTransform;
    private int midX;
    private int midY;
    private Viewport viewport;
    private String TAG = "XYOrthographicCamera";
    private double delta = 0.0d;
    private final Object mutex = new Object();

    public XYOrthographicCamera(FrameTransformTree frameTransformTree) {
        this.frameTransformTree = frameTransformTree;
        resetTransform();
    }

    public void setMid(int i, int i2) {
        this.midX = i;
        this.midY = i2;
    }

    private void resetTransform() {
        this.cameraToRosTransform = Transform.identity();
    }

    public void apply(GL10 gl10) {
        synchronized (this.mutex) {
            OpenGlTransform.apply(gl10, ROS_TO_SCREEN_TRANSFORM);
            OpenGlTransform.apply(gl10, this.cameraToRosTransform);
        }
    }

    public boolean applyFrameTransform(GL10 gl10, GraphName graphName) {
        FrameTransform transform;
        Preconditions.checkNotNull(graphName);
        GraphName graphName2 = this.frame;
        if (graphName2 == null || (transform = this.frameTransformTree.transform(graphName, graphName2)) == null) {
            return false;
        }
        OpenGlTransform.apply(gl10, transform.getTransform());
        return true;
    }

    public boolean applyLaserFrameTransform(GL10 gl10, GraphName graphName) {
        Preconditions.checkNotNull(graphName);
        if (this.frame == null) {
            return false;
        }
        if (this.laserTransform == null) {
            this.laserTransform = Transform.translation(toCameraFrame(this.midX, this.midY));
        }
        OpenGlTransform.apply(gl10, this.laserTransform);
        return true;
    }

    public void translate(double d, double d2) {
        synchronized (this.mutex) {
            this.cameraToRosTransform = ROS_TO_SCREEN_TRANSFORM.invert().multiply(Transform.translation(d, d2, 0.0d)).multiply(getCameraToScreenTransform());
            this.laserTransform = Transform.translation(toCameraFrame(0, 0)).multiply(Transform.zRotation(-this.delta));
        }
    }

    public void rotate(double d, double d2, double d3) {
        Log.d(this.TAG, "rotate :");
        synchronized (this.mutex) {
            int i = (int) d;
            int i2 = (int) d2;
            Transform translation = Transform.translation(toCameraFrame(i, i2));
            this.cameraToRosTransform = this.cameraToRosTransform.multiply(translation).multiply(Transform.zRotation(d3)).multiply(translation.invert());
            this.delta += d3;
            this.laserTransform = Transform.translation(toCameraFrame(i, i2)).multiply(Transform.zRotation(-this.delta));
        }
    }

    public double getDelta() {
        return this.delta;
    }

    public void zoom(double d, double d2, double d3) {
        synchronized (this.mutex) {
            Transform translation = Transform.translation(toCameraFrame((int) d, (int) d2));
            double scale = this.cameraToRosTransform.getScale();
            double clamp = OpenglMath.clamp(scale * d3, 0.10000000149011612d, 5.0d) / scale;
            Log.d(this.TAG, "zoom :" + clamp);
            this.cameraToRosTransform = this.cameraToRosTransform.multiply(translation).scale(clamp).multiply(translation.invert());
        }
    }

    public void fullPreview(double d) {
        resetTransform();
        this.delta = 0.0d;
        synchronized (this.mutex) {
            Transform translation = Transform.translation(new Vector3(0.0d, 0.0d, 0.0d));
            double scale = this.cameraToRosTransform.getScale();
            double clamp = OpenglMath.clamp(scale * d, 0.10000000149011612d, 5.0d) / scale;
            Log.d(this.TAG, "fullPreview :" + clamp);
            this.cameraToRosTransform = this.cameraToRosTransform.multiply(translation).scale(clamp).multiply(translation.invert());
        }
    }

    public double getZoom() {
        return this.cameraToRosTransform.getScale() * PIXELS_PER_METER;
    }

    private Transform getCameraToScreenTransform() {
        return ROS_TO_SCREEN_TRANSFORM.multiply(this.cameraToRosTransform);
    }

    public Transform getScreenTransform(GraphName graphName) {
        return this.frameTransformTree.transform(this.frame, graphName).getTransform().multiply(getCameraToScreenTransform().invert());
    }

    public Vector3 toCameraFrame(int i, int i2) {
        if (this.viewport == null) {
            return new Vector3(0.0d, 0.0d, 0.0d);
        }
        return getCameraToScreenTransform().invert().apply(new Vector3(-(i - (r0.getWidth() / 2.0d)), (this.viewport.getHeight() / 2.0d) - i2, 0.0d));
    }

    public Transform toFrame(int i, int i2, GraphName graphName) {
        return this.frameTransformTree.transform(this.frame, graphName).getTransform().multiply(Transform.translation(toCameraFrame(i, i2)));
    }

    public Transform toFrame(double d, double d2, GraphName graphName) {
        return this.frameTransformTree.transform(this.frame, graphName).getTransform().multiply(Transform.translation(d, d2, 0.0d));
    }

    public GraphName getFrame() {
        return this.frame;
    }

    public void setFrame(GraphName graphName) {
        FrameTransform transform;
        Preconditions.checkNotNull(graphName);
        synchronized (this.mutex) {
            if (this.frame != null && this.frame != graphName && (transform = this.frameTransformTree.transform(graphName, this.frame)) != null) {
                this.cameraToRosTransform = this.cameraToRosTransform.multiply(transform.getTransform());
            }
            this.frame = graphName;
        }
    }

    public void setFrame(String str) {
        setFrame(GraphName.m3302of(str));
    }

    public void jumpToFrame(GraphName graphName) {
        synchronized (this.mutex) {
            this.frame = graphName;
            double scale = this.cameraToRosTransform.getScale();
            resetTransform();
            this.cameraToRosTransform = this.cameraToRosTransform.scale(scale / this.cameraToRosTransform.getScale());
        }
    }

    public void jumpToFrame(String str) {
        jumpToFrame(GraphName.m3302of(str));
    }

    public void setViewport(Viewport viewport) {
        Preconditions.checkNotNull(viewport);
        this.viewport = viewport;
    }

    public Viewport getViewport() {
        Preconditions.checkNotNull(this.viewport);
        return this.viewport;
    }

    public Transform getCameraToRosTransform() {
        return this.cameraToRosTransform;
    }
}
