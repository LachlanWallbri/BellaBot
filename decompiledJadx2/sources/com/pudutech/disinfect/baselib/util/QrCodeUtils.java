package com.pudutech.disinfect.baselib.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class QrCodeUtils {
    private static int imageHalfWidth = 10;

    public static Bitmap createQrCode(String str, int i, int i2) {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix deleteWhite = deleteWhite(new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable));
            int width = deleteWhite.getWidth();
            int height = deleteWhite.getHeight();
            int[] iArr = new int[width * height];
            for (int i3 = 0; i3 < height; i3++) {
                for (int i4 = 0; i4 < width; i4++) {
                    if (deleteWhite.get(i4, i3)) {
                        iArr[(i3 * width) + i4] = -16777216;
                    } else {
                        iArr[(i3 * width) + i4] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeWithLogo(String str, int i, int i2, Bitmap bitmap) {
        try {
            imageHalfWidth = i / 10;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hashtable.put(EncodeHintType.MARGIN, 0);
            BitMatrix deleteWhite = deleteWhite(new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable));
            int width = deleteWhite.getWidth();
            int height = deleteWhite.getHeight();
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            int i3 = width / 2;
            int i4 = height / 2;
            Matrix matrix = new Matrix();
            matrix.setScale((imageHalfWidth * 2.0f) / createScaledBitmap.getWidth(), (imageHalfWidth * 2.0f) / createScaledBitmap.getHeight());
            Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap, 0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight(), matrix, false);
            int[] iArr = new int[width * height];
            for (int i5 = 0; i5 < height; i5++) {
                for (int i6 = 0; i6 < width; i6++) {
                    if (i6 > i3 - imageHalfWidth && i6 < imageHalfWidth + i3 && i5 > i4 - imageHalfWidth && i5 < imageHalfWidth + i4) {
                        iArr[(i5 * width) + i6] = createBitmap.getPixel((i6 - i3) + imageHalfWidth, (i5 - i4) + imageHalfWidth);
                    } else if (deleteWhite.get(i6, i5)) {
                        iArr[(i5 * width) + i6] = -16777216;
                    } else {
                        iArr[(i5 * width) + i6] = -1;
                    }
                }
            }
            Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeBitmap(String str, int i) {
        return createQRCodeBitmap(str, i, i, "utf-8", "H", "0", ViewCompat.MEASURED_STATE_MASK, -1, null, 0.3f, null);
    }

    public static Bitmap createQRCodeBitmap(String str, int i, int i2, String str2, String str3, String str4, int i3, int i4, Bitmap bitmap, float f, Bitmap bitmap2) {
        Bitmap bitmap3 = bitmap2;
        if (!TextUtils.isEmpty(str) && i >= 0 && i2 >= 0) {
            try {
                Hashtable hashtable = new Hashtable();
                if (!TextUtils.isEmpty(str2)) {
                    hashtable.put(EncodeHintType.CHARACTER_SET, str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    hashtable.put(EncodeHintType.ERROR_CORRECTION, str3);
                }
                if (!TextUtils.isEmpty(str4)) {
                    hashtable.put(EncodeHintType.MARGIN, str4);
                }
                BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable);
                if (bitmap3 != null) {
                    bitmap3 = Bitmap.createScaledBitmap(bitmap3, i, i2, false);
                }
                int[] iArr = new int[i * i2];
                for (int i5 = 0; i5 < i2; i5++) {
                    for (int i6 = 0; i6 < i; i6++) {
                        if (!encode.get(i6, i5)) {
                            iArr[(i5 * i) + i6] = i4;
                        } else if (bitmap3 != null) {
                            iArr[(i5 * i) + i6] = bitmap3.getPixel(i6, i5);
                        } else {
                            iArr[(i5 * i) + i6] = i3;
                        }
                    }
                }
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
                return bitmap != null ? addLogo(createBitmap, bitmap, f) : createBitmap;
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2, float f) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        if (f < 0.0f || f > 1.0f) {
            f = 0.2f;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        float f2 = (width * f) / width2;
        float height2 = (height * f) / bitmap2.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.scale(f2, height2, width / 2, height / 2);
        canvas.drawBitmap(bitmap2, r2 - (width2 / 2), r3 - (r5 / 2), (Paint) null);
        return createBitmap;
    }

    private static BitMatrix deleteWhite(BitMatrix bitMatrix) {
        int[] enclosingRectangle = bitMatrix.getEnclosingRectangle();
        int i = enclosingRectangle[2] + 1;
        int i2 = enclosingRectangle[3] + 1;
        BitMatrix bitMatrix2 = new BitMatrix(i, i2);
        bitMatrix2.clear();
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (bitMatrix.get(enclosingRectangle[0] + i3, enclosingRectangle[1] + i4)) {
                    bitMatrix2.set(i3, i4);
                }
            }
        }
        return bitMatrix2;
    }
}
