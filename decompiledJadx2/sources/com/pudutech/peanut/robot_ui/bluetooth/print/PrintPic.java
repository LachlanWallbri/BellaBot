package com.pudutech.peanut.robot_ui.bluetooth.print;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes5.dex */
public class PrintPic {
    private static PrintPic instance = new PrintPic();
    public int width;
    public Canvas canvas = null;
    public Paint paint = null;

    /* renamed from: bm */
    public Bitmap f6978bm = null;
    public float length = 0.0f;
    public byte[] bitbuf = null;

    private PrintPic() {
    }

    public static PrintPic getInstance() {
        return instance;
    }

    public int getLength() {
        return ((int) this.length) + 20;
    }

    public void init(Bitmap bitmap) {
        if (bitmap != null) {
            initCanvas(bitmap.getWidth());
        }
        if (this.paint == null) {
            initPaint();
        }
        if (bitmap != null) {
            drawImage(0.0f, 0.0f, bitmap);
        }
    }

    public void initCanvas(int i) {
        this.f6978bm = Bitmap.createBitmap(i, i * 10, Bitmap.Config.RGB_565);
        this.canvas = new Canvas(this.f6978bm);
        this.canvas.drawColor(-1);
        this.width = i;
        this.bitbuf = new byte[this.width / 8];
    }

    public void initPaint() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.paint.setStyle(Paint.Style.STROKE);
    }

    public void drawImage(float f, float f2, Bitmap bitmap) {
        try {
            try {
                this.canvas.drawBitmap(bitmap, f, f2, (Paint) null);
                if (this.length < bitmap.getHeight() + f2) {
                    this.length = f2 + bitmap.getHeight();
                }
                if (bitmap == null) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (bitmap == null) {
                    return;
                }
            }
            bitmap.recycle();
        } catch (Throwable th) {
            if (bitmap != null) {
                bitmap.recycle();
            }
            throw th;
        }
    }

    public byte[] printDraw() {
        int i = 0;
        Bitmap createBitmap = Bitmap.createBitmap(this.f6978bm, 0, 0, this.width, getLength());
        byte[] bArr = new byte[((this.width / 8) * getLength()) + 8];
        bArr[0] = 29;
        int i2 = 1;
        bArr[1] = 118;
        char c = 2;
        bArr[2] = TarConstants.LF_NORMAL;
        bArr[3] = 0;
        bArr[4] = (byte) (this.width / 8);
        bArr[5] = 0;
        bArr[6] = (byte) (getLength() % 256);
        int i3 = 7;
        bArr[7] = (byte) (getLength() / 256);
        int i4 = 0;
        while (i4 < getLength()) {
            int i5 = i;
            while (i5 < this.width / 8) {
                int i6 = i5 * 8;
                this.bitbuf[i5] = (byte) (((createBitmap.getPixel(i6 + 0, i4) == -1 ? i : i2) * 128) + ((createBitmap.getPixel(i6 + 1, i4) == -1 ? i : i2) * 64) + ((createBitmap.getPixel(i6 + 2, i4) == -1 ? i : i2) * 32) + ((createBitmap.getPixel(i6 + 3, i4) == -1 ? i : i2) * 16) + ((createBitmap.getPixel(i6 + 4, i4) == -1 ? i : 1) * 8) + ((createBitmap.getPixel(i6 + 5, i4) == -1 ? 0 : 1) * 4) + ((createBitmap.getPixel(i6 + 6, i4) == -1 ? 0 : 1) * 2) + (createBitmap.getPixel(i6 + 7, i4) == -1 ? 0 : 1));
                i5++;
                c = 2;
                i2 = 1;
                i = 0;
            }
            char c2 = c;
            for (int i7 = 0; i7 < this.width / 8; i7++) {
                i3++;
                bArr[i3] = this.bitbuf[i7];
            }
            i4++;
            c = c2;
            i2 = 1;
            i = 0;
        }
        Bitmap bitmap = this.f6978bm;
        if (bitmap != null) {
            bitmap.recycle();
            this.f6978bm = null;
        }
        return bArr;
    }
}
