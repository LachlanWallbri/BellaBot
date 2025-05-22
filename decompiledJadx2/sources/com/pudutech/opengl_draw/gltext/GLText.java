package com.pudutech.opengl_draw.gltext;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLUtils;
import android.util.Log;
import javax.microedition.khronos.opengles.GL10;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes5.dex */
public class GLText {
    public static final int CHAR_BATCH_SIZE = 100;
    public static final int FONT_SIZE_MAX = 180;
    public static final int FONT_SIZE_MIN = 6;
    public int CHAR_CNT;
    public int CHAR_END;
    public int CHAR_UNKNOWN;
    AssetManager assets;
    SpriteBatch batch;
    int cellHeight;
    int cellWidth;
    float charHeight;
    TextureRegion[] charRgn;
    float charWidthMax;
    final float[] charWidths;
    int colCnt;
    float fontAscent;
    float fontDescent;
    float fontHeight;
    int fontPadX;
    int fontPadY;

    /* renamed from: gl */
    GL10 f6824gl;
    int rowCnt;
    float scaleX;
    float scaleY;
    float spaceX;
    private String text;
    int textureId;
    TextureRegion textureRgn;
    int textureSize;
    public int CHAR_START = 0;
    public int CHAR_NONE = 32;

    public GLText(GL10 gl10, AssetManager assetManager, String str) {
        this.CHAR_END = 126;
        this.CHAR_CNT = (this.CHAR_END - this.CHAR_START) + 1 + 1;
        this.CHAR_UNKNOWN = this.CHAR_CNT - 1;
        this.f6824gl = gl10;
        this.assets = assetManager;
        this.CHAR_END = str.length();
        this.text = str;
        this.CHAR_CNT = (this.CHAR_END - this.CHAR_START) + 1 + 1;
        this.batch = new SpriteBatch(gl10, 100);
        int i = this.CHAR_CNT;
        this.charWidths = new float[i];
        this.charRgn = new TextureRegion[i];
        this.fontPadX = 0;
        this.fontPadY = 0;
        this.fontHeight = 0.0f;
        this.fontAscent = 0.0f;
        this.fontDescent = 0.0f;
        this.textureId = -1;
        this.textureSize = 0;
        this.charWidthMax = 0.0f;
        this.charHeight = 0.0f;
        this.cellWidth = 0;
        this.cellHeight = 0;
        this.rowCnt = 0;
        this.colCnt = 0;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.spaceX = 0.0f;
    }

    public boolean load(String str, int i, int i2, int i3) {
        return load(Typeface.createFromAsset(this.assets, str), i, i2, i3);
    }

    public boolean load(Typeface typeface, int i, int i2, int i3) {
        this.fontPadX = i2;
        this.fontPadY = i3;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(i);
        paint.setColor(-1);
        paint.setTypeface(typeface);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        this.fontHeight = (float) Math.ceil(Math.abs(fontMetrics.bottom) + Math.abs(fontMetrics.top));
        this.fontAscent = (float) Math.ceil(Math.abs(fontMetrics.ascent));
        this.fontDescent = (float) Math.ceil(Math.abs(fontMetrics.descent));
        char[] cArr = new char[4];
        this.charHeight = 0.0f;
        this.charWidthMax = 0.0f;
        float[] fArr = new float[4];
        int i4 = 0;
        for (int i5 = this.CHAR_START; i5 < this.CHAR_END; i5++) {
            cArr[0] = this.text.charAt(i5);
            paint.getTextWidths(cArr, 0, 1, fArr);
            float[] fArr2 = this.charWidths;
            fArr2[i4] = fArr[0];
            if (fArr2[i4] > this.charWidthMax) {
                this.charWidthMax = fArr2[i4];
            }
            i4++;
        }
        cArr[0] = (char) this.CHAR_NONE;
        paint.getTextWidths(cArr, 0, 1, fArr);
        float[] fArr3 = this.charWidths;
        fArr3[i4] = fArr[0];
        if (fArr3[i4] > this.charWidthMax) {
            this.charWidthMax = fArr3[i4];
        }
        this.charHeight = this.fontHeight;
        this.cellWidth = ((int) this.charWidthMax) + (this.fontPadX * 2);
        this.cellHeight = ((int) this.charHeight) + (this.fontPadY * 2);
        int i6 = this.cellWidth;
        int i7 = this.cellHeight;
        if (i6 <= i7) {
            i6 = i7;
        }
        if (i6 < 6 || i6 > 180) {
            return false;
        }
        if (i6 <= 24) {
            this.textureSize = 256;
        } else if (i6 <= 40) {
            this.textureSize = 512;
        } else if (i6 <= 80) {
            this.textureSize = 1024;
        } else {
            this.textureSize = 2048;
        }
        int i8 = this.textureSize;
        Bitmap createBitmap = Bitmap.createBitmap(i8, i8, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(createBitmap);
        createBitmap.eraseColor(0);
        this.colCnt = this.textureSize / this.cellWidth;
        this.rowCnt = (int) Math.ceil(this.CHAR_CNT / this.colCnt);
        float f = this.fontPadX;
        float f2 = ((this.cellHeight - 1) - this.fontDescent) - this.fontPadY;
        for (int i9 = this.CHAR_START; i9 < this.CHAR_END; i9++) {
            cArr[0] = this.text.charAt(i9);
            canvas.drawText(cArr, 0, 1, f, f2, paint);
            int i10 = this.cellWidth;
            f += i10;
            int i11 = this.fontPadX;
            if ((f + i10) - i11 > this.textureSize) {
                f2 += this.cellHeight;
                f = i11;
            }
        }
        cArr[0] = (char) this.CHAR_NONE;
        canvas.drawText(cArr, 0, 1, f, f2, paint);
        int[] iArr = new int[1];
        this.f6824gl.glGenTextures(1, iArr, 0);
        Log.i("text handle", "" + iArr[0]);
        this.textureId = iArr[0];
        this.f6824gl.glBindTexture(3553, this.textureId);
        this.f6824gl.glTexParameterf(3553, 10241, 9728.0f);
        this.f6824gl.glTexParameterf(3553, TarConstants.DEFAULT_BLKSIZE, 9729.0f);
        this.f6824gl.glTexParameterf(3553, 10242, 33071.0f);
        this.f6824gl.glTexParameterf(3553, 10243, 33071.0f);
        GLUtils.texImage2D(3553, 0, createBitmap, 0);
        this.f6824gl.glBindTexture(3553, 0);
        createBitmap.recycle();
        float f3 = 0.0f;
        float f4 = 0.0f;
        for (int i12 = 0; i12 < this.CHAR_CNT; i12++) {
            TextureRegion[] textureRegionArr = this.charRgn;
            int i13 = this.textureSize;
            textureRegionArr[i12] = new TextureRegion(i13, i13, f3, f4, this.cellWidth - 1, this.cellHeight - 1);
            int i14 = this.cellWidth;
            f3 += i14;
            if (i14 + f3 > this.textureSize) {
                f4 += this.cellHeight;
                f3 = 0.0f;
            }
        }
        int i15 = this.textureSize;
        this.textureRgn = new TextureRegion(i15, i15, 0.0f, 0.0f, i15, i15);
        return true;
    }

    public void begin() {
        begin(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void begin(float f) {
        begin(1.0f, 1.0f, 1.0f, f);
    }

    public void begin(float f, float f2, float f3, float f4) {
        this.f6824gl.glColor4f(f, f2, f3, f4);
        this.f6824gl.glBindTexture(3553, this.textureId);
        this.batch.beginBatch();
    }

    public void end() {
        this.batch.endBatch();
        this.f6824gl.glBindTexture(3553, 0);
        this.f6824gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void draw(String str, float f, float f2, float f3) {
        float f4 = this.cellHeight * this.scaleY;
        float f5 = this.cellWidth * this.scaleX;
        int length = str.length();
        float f6 = f5 / 2.0f;
        int i = this.fontPadX;
        float f7 = this.scaleX;
        float f8 = f + (f6 - (i * f7));
        float f9 = f2 + ((f4 / 2.0f) - (this.fontPadY * this.scaleY));
        float f10 = f6 - (i * f7);
        for (int i2 = length; i2 >= 0; i2--) {
            float[] fArr = this.charWidths;
            int i3 = i2 - 1;
            if (i3 < 0) {
                i3 = length;
            }
            f10 += (fArr[i3] + this.spaceX) * this.scaleX;
        }
        float f11 = f8;
        for (int i4 = length; i4 >= 0; i4--) {
            this.batch.drawSprite(f11, f9, f5, f4, this.charRgn[i4], f10, f3);
            float[] fArr2 = this.charWidths;
            int i5 = i4 - 1;
            if (i5 < 0) {
                i5 = length;
            }
            f11 += (fArr2[i5] + this.spaceX) * this.scaleX;
        }
    }

    public float drawC(String str, float f, float f2, float f3) {
        float length = getLength(str);
        draw(str, f - (length / 2.0f), f2 - (getCharHeight() / 2.0f), f3);
        return length;
    }

    public float drawCX(String str, float f, float f2, float f3) {
        float length = getLength(str);
        draw(str, f - (length / 2.0f), f2, f3);
        return length;
    }

    public void drawCY(String str, float f, float f2, float f3) {
        draw(str, f, f2 - (getCharHeight() / 2.0f), f3);
    }

    public void setScale(float f) {
        this.scaleY = f;
        this.scaleX = f;
    }

    public void setScale(float f, float f2) {
        this.scaleX = f;
        this.scaleY = f2;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void setSpace(float f) {
        this.spaceX = f;
    }

    public float getSpace() {
        return this.spaceX;
    }

    public float getLength(String str) {
        int length = str.length();
        float f = 0.0f;
        for (int i = 0; i < length; i++) {
            f += this.charWidths[i] * this.scaleX;
        }
        return f + (length > 1 ? this.scaleX * (length - 1) * this.spaceX : 0.0f);
    }

    public float getCharWidth(char c) {
        return this.charWidths[c - this.CHAR_START] * this.scaleX;
    }

    public float getCharWidthMax() {
        return this.charWidthMax * this.scaleX;
    }

    public float getCharHeight() {
        return this.charHeight * this.scaleY;
    }

    public float getAscent() {
        return this.fontAscent * this.scaleY;
    }

    public float getDescent() {
        return this.fontDescent * this.scaleY;
    }

    public float getHeight() {
        return this.fontHeight * this.scaleY;
    }
}
