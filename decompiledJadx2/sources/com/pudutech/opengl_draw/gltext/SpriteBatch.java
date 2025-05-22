package com.pudutech.opengl_draw.gltext;

import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class SpriteBatch {
    static final int INDICES_PER_SPRITE = 6;
    static final int VERTEX_SIZE = 4;
    static final int VERTICES_PER_SPRITE = 4;

    /* renamed from: gl */
    GL10 f6825gl;
    int maxSprites;
    float[] vertexBuffer;
    Vertices vertices;
    int bufferIndex = 0;
    int numSprites = 0;

    public SpriteBatch(GL10 gl10, int i) {
        this.f6825gl = gl10;
        int i2 = i * 4;
        this.vertexBuffer = new float[i2 * 4];
        int i3 = i * 6;
        this.vertices = new Vertices(gl10, i2, i3, false, true, false);
        this.maxSprites = i;
        short[] sArr = new short[i3];
        int length = sArr.length;
        int i4 = 0;
        short s = 0;
        while (i4 < length) {
            short s2 = (short) (s + 0);
            sArr[i4 + 0] = s2;
            sArr[i4 + 1] = (short) (s + 1);
            short s3 = (short) (s + 2);
            sArr[i4 + 2] = s3;
            sArr[i4 + 3] = s3;
            sArr[i4 + 4] = (short) (s + 3);
            sArr[i4 + 5] = s2;
            i4 += 6;
            s = (short) (s + 4);
        }
        this.vertices.setIndices(sArr, 0, length);
    }

    public void beginBatch() {
        this.numSprites = 0;
        this.bufferIndex = 0;
    }

    public void endBatch() {
        if (this.numSprites > 0) {
            this.vertices.setVertices(this.vertexBuffer, 0, this.bufferIndex);
            this.vertices.bind();
            this.vertices.draw(4, 0, this.numSprites * 6);
            this.vertices.unbind();
        }
    }

    public void drawSprite(float f, float f2, float f3, float f4, TextureRegion textureRegion, float f5, float f6) {
        if (this.numSprites == this.maxSprites) {
            endBatch();
            this.numSprites = 0;
            this.bufferIndex = 0;
        }
        float f7 = f5 / 2.0f;
        float f8 = (f - ((f3 / 2.0f) * 2.0f)) - f7;
        float f9 = (f2 - ((f4 / 2.0f) * 2.0f)) - f6;
        float f10 = f - f7;
        float f11 = f2 - f6;
        float[] fArr = this.vertexBuffer;
        int i = this.bufferIndex;
        this.bufferIndex = i + 1;
        fArr[i] = f10;
        int i2 = this.bufferIndex;
        this.bufferIndex = i2 + 1;
        fArr[i2] = f9;
        int i3 = this.bufferIndex;
        this.bufferIndex = i3 + 1;
        fArr[i3] = textureRegion.f6826u1;
        float[] fArr2 = this.vertexBuffer;
        int i4 = this.bufferIndex;
        this.bufferIndex = i4 + 1;
        fArr2[i4] = textureRegion.f6829v2;
        float[] fArr3 = this.vertexBuffer;
        int i5 = this.bufferIndex;
        this.bufferIndex = i5 + 1;
        fArr3[i5] = f8;
        int i6 = this.bufferIndex;
        this.bufferIndex = i6 + 1;
        fArr3[i6] = f9;
        int i7 = this.bufferIndex;
        this.bufferIndex = i7 + 1;
        fArr3[i7] = textureRegion.f6827u2;
        float[] fArr4 = this.vertexBuffer;
        int i8 = this.bufferIndex;
        this.bufferIndex = i8 + 1;
        fArr4[i8] = textureRegion.f6829v2;
        float[] fArr5 = this.vertexBuffer;
        int i9 = this.bufferIndex;
        this.bufferIndex = i9 + 1;
        fArr5[i9] = f8;
        int i10 = this.bufferIndex;
        this.bufferIndex = i10 + 1;
        fArr5[i10] = f11;
        int i11 = this.bufferIndex;
        this.bufferIndex = i11 + 1;
        fArr5[i11] = textureRegion.f6827u2;
        float[] fArr6 = this.vertexBuffer;
        int i12 = this.bufferIndex;
        this.bufferIndex = i12 + 1;
        fArr6[i12] = textureRegion.f6828v1;
        float[] fArr7 = this.vertexBuffer;
        int i13 = this.bufferIndex;
        this.bufferIndex = i13 + 1;
        fArr7[i13] = f10;
        int i14 = this.bufferIndex;
        this.bufferIndex = i14 + 1;
        fArr7[i14] = f11;
        int i15 = this.bufferIndex;
        this.bufferIndex = i15 + 1;
        fArr7[i15] = textureRegion.f6826u1;
        float[] fArr8 = this.vertexBuffer;
        int i16 = this.bufferIndex;
        this.bufferIndex = i16 + 1;
        fArr8[i16] = textureRegion.f6828v1;
        this.numSprites++;
    }
}
