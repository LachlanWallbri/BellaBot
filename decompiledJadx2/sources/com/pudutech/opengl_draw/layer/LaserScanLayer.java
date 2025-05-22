package com.pudutech.opengl_draw.layer;

import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.TfLayer;
import com.pudutech.opengl_draw.base.Vertices;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.LaserScan;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.shape.Shape;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class LaserScanLayer extends DefaultLayer implements TfLayer {
    private static final float LASER_SCAN_POINT_SIZE = 10.0f;
    private static final int LASER_SCAN_STRIDE = 15;
    private GraphName frame;
    private final Object mutex = new Object();
    private Shape shape;
    private FloatBuffer vertexBackBuffer;
    private FloatBuffer vertexFrontBuffer;
    private static final Color FREE_SPACE_COLOR = Color.fromHexAndAlpha("377dfa", 0.1f);
    private static final Color OCCUPIED_SPACE_COLOR = Color.fromHexAndAlpha("377dfa", 0.3f);

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        if (this.vertexFrontBuffer != null) {
            synchronized (this.mutex) {
                Vertices.drawTriangleFan(gl10, this.vertexFrontBuffer, FREE_SPACE_COLOR);
                FloatBuffer duplicate = this.vertexFrontBuffer.duplicate();
                duplicate.position(3);
                Vertices.drawPoints(gl10, duplicate, OCCUPIED_SPACE_COLOR, LASER_SCAN_POINT_SIZE);
            }
        }
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(VisualizationView visualizationView) {
        super.onStart(visualizationView);
    }

    public void updateVertexBuffer(LaserScan laserScan, int i, Transform transform) {
        float f;
        boolean z;
        float[] ranges = laserScan.getRanges();
        int length = ((ranges.length / i) + 2) * 3;
        FloatBuffer floatBuffer = this.vertexBackBuffer;
        if (floatBuffer == null || floatBuffer.capacity() < length) {
            this.vertexBackBuffer = Vertices.allocateBuffer(length);
        }
        this.vertexBackBuffer.clear();
        boolean z2 = false;
        this.vertexBackBuffer.put(0.0f);
        this.vertexBackBuffer.put(0.0f);
        this.vertexBackBuffer.put(0.0f);
        float rangeMin = laserScan.getRangeMin();
        float rangeMax = laserScan.getRangeMax();
        float angle = laserScan.getAngle();
        float angleIncrement = laserScan.getAngleIncrement();
        int i2 = 1;
        float f2 = angle;
        int i3 = 0;
        while (i3 < ranges.length) {
            float f3 = ranges[i3];
            if (rangeMin >= f3 || f3 >= rangeMax) {
                f = rangeMax;
                z = z2;
                i2 = i2;
            } else {
                double d = f3;
                double d2 = f2;
                f = rangeMax;
                this.vertexBackBuffer.put((float) (d * Math.cos(d2)));
                this.vertexBackBuffer.put((float) (d * Math.sin(d2)));
                z = false;
                this.vertexBackBuffer.put(0.0f);
                i2++;
            }
            f2 += i * angleIncrement;
            i3 += i;
            z2 = z;
            rangeMax = f;
        }
        this.vertexBackBuffer.position(0);
        this.vertexBackBuffer.limit(i2 * 3);
        synchronized (this.mutex) {
            FloatBuffer floatBuffer2 = this.vertexFrontBuffer;
            this.vertexFrontBuffer = this.vertexBackBuffer;
            this.vertexBackBuffer = floatBuffer2;
        }
    }

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return GraphName.m3302of("LaserScanLayer");
    }
}
