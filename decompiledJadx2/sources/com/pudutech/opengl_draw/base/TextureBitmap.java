package com.pudutech.opengl_draw.base;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.opengl.GLUtils;
import androidx.core.util.Preconditions;
import com.pudutech.opengl_draw.geometry.Transform;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.jboss.netty.buffer.ChannelBuffer;

/* loaded from: classes5.dex */
public class TextureBitmap implements OpenGlDrawable {
    public static final int HEIGHT = 1024;
    public static final int STRIDE = 1024;
    private int[] handle;
    private Transform origin;
    private double scaledHeight;
    private double scaledWidth;
    private final int[] pixels = new int[1048576];
    private final FloatBuffer surfaceVertices = Vertices.toFloatBuffer(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f});
    private final FloatBuffer textureVertices = Vertices.toFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    private Bitmap bitmapFront = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_4444);
    private Bitmap bitmapBack = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_4444);
    private final Object mutex = new Object();
    private boolean reload = true;

    public void updateFromPixelArray(int[] iArr, int i, float f, Transform transform, int i2) {
        Preconditions.checkArgument(iArr.length % i == 0);
        int length = iArr.length / i;
        for (int i3 = 0; i3 < 1024; i3++) {
            for (int i4 = 0; i4 < 1024; i4++) {
                int i5 = (i3 * i) + i4;
                int i6 = (i3 * 1024) + i4;
                if (i4 < i && i3 < length) {
                    this.pixels[i6] = iArr[i5];
                } else {
                    this.pixels[i6] = i2;
                }
            }
        }
        update(transform, i, f, i2);
    }

    public void updateFromPixelBuffer(ChannelBuffer channelBuffer, int i, float f, Transform transform, int i2) {
        Preconditions.checkNotNull(channelBuffer);
        Preconditions.checkNotNull(transform);
        int i3 = 0;
        int i4 = 0;
        while (i3 < 1024) {
            int i5 = i4;
            int i6 = 0;
            while (i6 < 1024) {
                if (i6 < i && channelBuffer.readable()) {
                    this.pixels[i5] = channelBuffer.readInt();
                } else {
                    this.pixels[i5] = i2;
                }
                i6++;
                i5++;
            }
            i3++;
            i4 = i5;
        }
        update(transform, i, f, i2);
    }

    public void clearHandle() {
        this.handle = null;
    }

    private void update(Transform transform, int i, float f, int i2) {
        this.origin = transform;
        double d = f * 1024.0f;
        this.scaledWidth = d;
        this.scaledHeight = d;
        this.bitmapBack.setPixels(this.pixels, 0, 1024, 0, 0, 1024, 1024);
        synchronized (this.mutex) {
            Bitmap bitmap = this.bitmapFront;
            this.bitmapFront = this.bitmapBack;
            this.bitmapBack = bitmap;
            this.reload = true;
        }
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
                GLUtils.texImage2D(3553, 0, this.bitmapFront, 0);
                this.reload = false;
            }
        }
    }

    public Bitmap horverImage(Bitmap bitmap, boolean z, boolean z2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
        }
        if (z2) {
            matrix.postScale(1.0f, -1.0f);
        }
        if (z && z2) {
            matrix.postScale(-1.0f, -1.0f);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    @Override // com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        gl10.glEnable(3553);
        bind(gl10);
        gl10.glPushMatrix();
        OpenGlTransform.apply(gl10, this.origin);
        gl10.glScalef((float) this.scaledWidth, (float) this.scaledHeight, 1.0f);
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
}
