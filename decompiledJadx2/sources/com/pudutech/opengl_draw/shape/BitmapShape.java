package com.pudutech.opengl_draw.shape;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import com.pudutech.opengl_draw.base.OpenGlDrawable;
import com.pudutech.opengl_draw.base.OpenGlTransform;
import com.pudutech.opengl_draw.base.Vertices;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes5.dex */
public class BitmapShape implements OpenGlDrawable {
    private Bitmap bitmap;
    private int[] handle;
    private Transform origin;
    private double scaledHeight;
    private double scaledWidth;
    private String TAG = "BitmapShape";
    private final FloatBuffer surfaceVertices = Vertices.toFloatBuffer(new float[]{-0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, -0.5f, 0.5f, 0.0f, 0.5f, 0.5f, 0.0f});
    private final FloatBuffer textureVertices = Vertices.toFloatBuffer(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f});
    private final Object mutex = new Object();
    private boolean reload = true;

    public void updata(Transform transform, float f, Bitmap bitmap) {
        this.origin = transform;
        double d = f;
        this.scaledWidth = bitmap.getWidth() * 0.05d * d;
        this.scaledHeight = bitmap.getHeight() * 0.05d * d;
        synchronized (this.mutex) {
            this.bitmap = bitmap;
            this.reload = true;
        }
    }

    @Override // com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        gl10.glEnable(3553);
        bind(gl10);
        gl10.glPushMatrix();
        OpenGlTransform.apply(gl10, this.origin);
        gl10.glScalef(((float) this.scaledWidth) * 100.0f, ((float) this.scaledHeight) * 100.0f, 1.0f);
        gl10.glScalef(1.0f / ((float) visualizationView.getCamera().getZoom()), 1.0f / ((float) visualizationView.getCamera().getZoom()), 1.0f);
        gl10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gl10.glEnableClientState(32884);
        gl10.glEnableClientState(32888);
        gl10.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, this.surfaceVertices);
        gl10.glTexCoordPointer(2, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, this.textureVertices);
        gl10.glDrawArrays(5, 0, 4);
        gl10.glDisableClientState(32884);
        gl10.glDisableClientState(32888);
        gl10.glPopMatrix();
        gl10.glBindTexture(3553, 0);
        gl10.glDisable(3553);
    }

    private void bind(GL10 gl10) {
        if (this.handle == null) {
            this.handle = new int[1];
            gl10.glGenTextures(1, this.handle, 0);
            this.reload = true;
        }
        gl10.glBindTexture(3553, this.handle[0]);
        gl10.glTexParameterf(3553, 10241, 9728.0f);
        gl10.glTexParameterf(3553, TarConstants.DEFAULT_BLKSIZE, 9728.0f);
        synchronized (this.mutex) {
            if (this.reload) {
                GLUtils.texImage2D(3553, 0, this.bitmap, 0);
                this.reload = false;
            }
        }
    }
}
