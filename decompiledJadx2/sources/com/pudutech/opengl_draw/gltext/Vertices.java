package com.pudutech.opengl_draw.gltext;

import com.slamtec.slamware.robot.HealthInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class Vertices {
    static final int COLOR_CNT = 4;
    static final int INDEX_SIZE = 2;
    static final int NORMAL_CNT = 3;
    static final int POSITION_CNT_2D = 2;
    static final int POSITION_CNT_3D = 3;
    static final int TEXCOORD_CNT = 2;

    /* renamed from: gl */
    final GL10 f6830gl;
    final boolean hasColor;
    final boolean hasNormals;
    final boolean hasTexCoords;
    final ShortBuffer indices;
    public int numIndices;
    public int numVertices;
    public final int positionCnt;
    final int[] tmpBuffer;
    public final int vertexSize;
    public final int vertexStride;
    final IntBuffer vertices;

    public Vertices(GL10 gl10, int i, int i2, boolean z, boolean z2, boolean z3) {
        this(gl10, i, i2, z, z2, z3, false);
    }

    public Vertices(GL10 gl10, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f6830gl = gl10;
        this.hasColor = z;
        this.hasTexCoords = z2;
        this.hasNormals = z3;
        this.positionCnt = z4 ? 3 : 2;
        this.vertexStride = this.positionCnt + (z ? 4 : 0) + (z2 ? 2 : 0) + (z3 ? 3 : 0);
        this.vertexSize = this.vertexStride * 4;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.vertexSize * i);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.vertices = allocateDirect.asIntBuffer();
        if (i2 > 0) {
            ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(i2 * 2);
            allocateDirect2.order(ByteOrder.nativeOrder());
            this.indices = allocateDirect2.asShortBuffer();
        } else {
            this.indices = null;
        }
        this.numVertices = 0;
        this.numIndices = 0;
        this.tmpBuffer = new int[(i * this.vertexSize) / 4];
    }

    public void setVertices(float[] fArr, int i, int i2) {
        this.vertices.clear();
        int i3 = i + i2;
        int i4 = 0;
        while (i < i3) {
            this.tmpBuffer[i4] = Float.floatToRawIntBits(fArr[i]);
            i++;
            i4++;
        }
        this.vertices.put(this.tmpBuffer, 0, i2);
        this.vertices.flip();
        this.numVertices = i2 / this.vertexStride;
    }

    public void setIndices(short[] sArr, int i, int i2) {
        this.indices.clear();
        this.indices.put(sArr, i, i2);
        this.indices.flip();
        this.numIndices = i2;
    }

    public void bind() {
        this.f6830gl.glEnableClientState(32884);
        this.vertices.position(0);
        this.f6830gl.glVertexPointer(this.positionCnt, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, this.vertexSize, this.vertices);
        if (this.hasColor) {
            this.f6830gl.glEnableClientState(32886);
            this.vertices.position(this.positionCnt);
            this.f6830gl.glColorPointer(4, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, this.vertexSize, this.vertices);
        }
        if (this.hasTexCoords) {
            this.f6830gl.glEnableClientState(32888);
            this.vertices.position(this.positionCnt + (this.hasColor ? 4 : 0));
            this.f6830gl.glTexCoordPointer(2, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, this.vertexSize, this.vertices);
        }
        if (this.hasNormals) {
            this.f6830gl.glEnableClientState(32885);
            this.vertices.position(this.positionCnt + (this.hasColor ? 4 : 0) + (this.hasTexCoords ? 2 : 0));
            this.f6830gl.glNormalPointer(HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, this.vertexSize, this.vertices);
        }
    }

    public void draw(int i, int i2, int i3) {
        ShortBuffer shortBuffer = this.indices;
        if (shortBuffer != null) {
            shortBuffer.position(i2);
            this.f6830gl.glDrawElements(i, i3, HealthInfo.BaseError.BaseComponentErrorTypeSensorCliffDown, this.indices);
        } else {
            this.f6830gl.glDrawArrays(i, i2, i3);
        }
    }

    public void unbind() {
        if (this.hasColor) {
            this.f6830gl.glDisableClientState(32886);
        }
        if (this.hasTexCoords) {
            this.f6830gl.glDisableClientState(32888);
        }
        if (this.hasNormals) {
            this.f6830gl.glDisableClientState(32885);
        }
    }

    public void drawFull(int i, int i2, int i3) {
        this.f6830gl.glEnableClientState(32884);
        this.vertices.position(0);
        this.f6830gl.glVertexPointer(this.positionCnt, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, this.vertexSize, this.vertices);
        if (this.hasColor) {
            this.f6830gl.glEnableClientState(32886);
            this.vertices.position(this.positionCnt);
            this.f6830gl.glColorPointer(4, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, this.vertexSize, this.vertices);
        }
        if (this.hasTexCoords) {
            this.f6830gl.glEnableClientState(32888);
            this.vertices.position(this.positionCnt + (this.hasColor ? 4 : 0));
            this.f6830gl.glTexCoordPointer(2, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, this.vertexSize, this.vertices);
        }
        ShortBuffer shortBuffer = this.indices;
        if (shortBuffer != null) {
            shortBuffer.position(i2);
            this.f6830gl.glDrawElements(i, i3, HealthInfo.BaseError.BaseComponentErrorTypeSensorCliffDown, this.indices);
        } else {
            this.f6830gl.glDrawArrays(i, i2, i3);
        }
        if (this.hasTexCoords) {
            this.f6830gl.glDisableClientState(32888);
        }
        if (this.hasColor) {
            this.f6830gl.glDisableClientState(32886);
        }
    }

    void setVtxPosition(int i, float f, float f2) {
        int i2 = i * this.vertexStride;
        this.vertices.put(i2 + 0, Float.floatToRawIntBits(f));
        this.vertices.put(i2 + 1, Float.floatToRawIntBits(f2));
    }

    void setVtxPosition(int i, float f, float f2, float f3) {
        int i2 = i * this.vertexStride;
        this.vertices.put(i2 + 0, Float.floatToRawIntBits(f));
        this.vertices.put(i2 + 1, Float.floatToRawIntBits(f2));
        this.vertices.put(i2 + 2, Float.floatToRawIntBits(f3));
    }

    void setVtxColor(int i, float f, float f2, float f3, float f4) {
        int i2 = (i * this.vertexStride) + this.positionCnt;
        this.vertices.put(i2 + 0, Float.floatToRawIntBits(f));
        this.vertices.put(i2 + 1, Float.floatToRawIntBits(f2));
        this.vertices.put(i2 + 2, Float.floatToRawIntBits(f3));
        this.vertices.put(i2 + 3, Float.floatToRawIntBits(f4));
    }

    void setVtxColor(int i, float f, float f2, float f3) {
        int i2 = (i * this.vertexStride) + this.positionCnt;
        this.vertices.put(i2 + 0, Float.floatToRawIntBits(f));
        this.vertices.put(i2 + 1, Float.floatToRawIntBits(f2));
        this.vertices.put(i2 + 2, Float.floatToRawIntBits(f3));
    }

    void setVtxColor(int i, float f) {
        this.vertices.put((i * this.vertexStride) + this.positionCnt + 3, Float.floatToRawIntBits(f));
    }

    void setVtxTexCoords(int i, float f, float f2) {
        int i2 = (i * this.vertexStride) + this.positionCnt + (this.hasColor ? 4 : 0);
        this.vertices.put(i2 + 0, Float.floatToRawIntBits(f));
        this.vertices.put(i2 + 1, Float.floatToRawIntBits(f2));
    }

    void setVtxNormal(int i, float f, float f2, float f3) {
        int i2 = (i * this.vertexStride) + this.positionCnt + (this.hasColor ? 4 : 0) + (this.hasTexCoords ? 2 : 0);
        this.vertices.put(i2 + 0, Float.floatToRawIntBits(f));
        this.vertices.put(i2 + 1, Float.floatToRawIntBits(f2));
        this.vertices.put(i2 + 2, Float.floatToRawIntBits(f3));
    }
}
