package com.pudutech.peanut.robot_ui.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QRCodeUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000bJ4\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016J*\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J$\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ,\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u00122\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000bJ$\u0010#\u001a\u0004\u0018\u00010\u000b2\b\u0010$\u001a\u0004\u0018\u00010\u00122\u0006\u0010%\u001a\u00020\u00042\b\u0010&\u001a\u0004\u0018\u00010\u000bJ\"\u0010'\u001a\u0004\u0018\u00010\u000b2\b\u0010(\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004J*\u0010)\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004J$\u0010*\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0004H\u0002J&\u0010+\u001a\u0004\u0018\u00010\u000b2\b\u0010,\u001a\u0004\u0018\u00010\u000b2\b\u0010-\u001a\u0004\u0018\u00010\u000b2\b\u0010.\u001a\u0004\u0018\u00010/R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u00060"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/util/QRCodeUtils;", "", "()V", "BLACK", "", "FRAME_WIDTH", "IMAGE_HALFWIDTH", "IMAGE_HALF_WIDTH", "IMAGE_HEIGHT", "IMAGE_WIDTH", "addLogo", "Landroid/graphics/Bitmap;", "src", "logo", "creatBarcode", "context", "Landroid/content/Context;", "contents", "", "desiredWidth", "desiredHeight", "displayCode", "", "creatCodeBitmap", "width", "height", "createCode", "string", "mBitmap", "format", "Lcom/google/zxing/BarcodeFormat;", "createImage", "text", "w", "h", "createQRCode", "str", "widthAndHeight", "iconBit", "createQRImage", "url", "encodeAsBitmap", "getScaleLogo", "mixtureBitmap", "first", "second", "fromPoint", "Landroid/graphics/PointF;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class QRCodeUtils {
    private static final int BLACK = -16777216;
    private static final int FRAME_WIDTH = 2;
    private static final int IMAGE_HALFWIDTH = 40;
    private static final int IMAGE_HALF_WIDTH = 30;
    private static final int IMAGE_HEIGHT = 60;
    private static final int IMAGE_WIDTH = 60;
    public static final QRCodeUtils INSTANCE = new QRCodeUtils();

    private QRCodeUtils() {
    }

    public final Bitmap createQRImage(String url, int width, int height) {
        if (url != null) {
            try {
                if (!Intrinsics.areEqual("", url) && url.length() >= 1) {
                    Hashtable hashtable = new Hashtable();
                    hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    BitMatrix encode = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hashtable);
                    int[] iArr = new int[width * height];
                    for (int i = 0; i < height; i++) {
                        for (int i2 = 0; i2 < width; i2++) {
                            if (encode.get(i2, i)) {
                                iArr[(i * width) + i2] = -16777216;
                            } else {
                                iArr[(i * width) + i2] = -1;
                            }
                        }
                    }
                    Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
                    return createBitmap;
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final Bitmap creatBarcode(Context context, String contents, int desiredWidth, int desiredHeight, boolean displayCode) {
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
        if (displayCode) {
            return mixtureBitmap(encodeAsBitmap(contents, barcodeFormat, desiredWidth, desiredHeight), creatCodeBitmap(contents, desiredWidth + 40, desiredHeight, context), new PointF(0.0f, desiredHeight));
        }
        return encodeAsBitmap(contents, barcodeFormat, desiredWidth, desiredHeight);
    }

    public final Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int desiredWidth, int desiredHeight) {
        BitMatrix bitMatrix = (BitMatrix) null;
        try {
            bitMatrix = new MultiFormatWriter().encode(contents, format, desiredWidth, desiredHeight, null);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        if (bitMatrix == null) {
            Intrinsics.throwNpe();
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] iArr = new int[width * height];
        for (int i = 0; i < height; i++) {
            int i2 = i * width;
            for (int i3 = 0; i3 < width; i3++) {
                iArr[i2 + i3] = bitMatrix.get(i3, i) ? -16777216 : -1;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
        return bitmap;
    }

    public final Bitmap creatCodeBitmap(String contents, int width, int height, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        textView.setText(contents);
        textView.setHeight(height);
        textView.setGravity(1);
        textView.setWidth(width);
        textView.setDrawingCacheEnabled(true);
        textView.setTextColor(-16777216);
        textView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
        textView.buildDrawingCache();
        Bitmap drawingCache = textView.getDrawingCache();
        Intrinsics.checkExpressionValueIsNotNull(drawingCache, "tv.drawingCache");
        return drawingCache;
    }

    public final Bitmap mixtureBitmap(Bitmap first, Bitmap second, PointF fromPoint) {
        if (first == null || second == null || fromPoint == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(first.getWidth() + second.getWidth() + 20, first.getHeight() + second.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(first, 20, 0.0f, (Paint) null);
        canvas.drawBitmap(second, fromPoint.x, fromPoint.y, (Paint) null);
        canvas.save();
        canvas.restore();
        return createBitmap;
    }

    public final Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }
        if (logo == null) {
            return src;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        int width2 = logo.getWidth();
        int height2 = logo.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return src;
        }
        float f = width;
        float f2 = ((1.0f * f) / 5) / width2;
        float f3 = (height2 / width2) * f2;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(src, 0.0f, 0.0f, (Paint) null);
            float f4 = 2;
            canvas.scale(f2, f3, f / f4, height / f4);
            canvas.drawBitmap(logo, (width - width2) / f4, (height - height2) / f4, (Paint) null);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e) {
            Bitmap bitmap = (Bitmap) null;
            e.getStackTrace();
            return bitmap;
        }
    }

    public final Bitmap createCode(String string, Bitmap mBitmap, BarcodeFormat format) throws WriterException {
        Intrinsics.checkParameterIsNotNull(mBitmap, "mBitmap");
        Matrix matrix = new Matrix();
        matrix.setScale(80.0f / mBitmap.getWidth(), 80.0f / mBitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, false);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(\n   …eight, m, false\n        )");
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix2 = multiFormatWriter.encode(string, format, 400, 400, hashtable);
        Intrinsics.checkExpressionValueIsNotNull(matrix2, "matrix");
        int width = matrix2.getWidth();
        int height = matrix2.getHeight();
        int i = width / 2;
        int i2 = height / 2;
        int[] iArr = new int[width * height];
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                if (i4 > i - 40 && i4 < i + 40 && i3 > i2 - 40 && i3 < i2 + 40) {
                    iArr[(i3 * width) + i4] = createBitmap.getPixel((i4 - i) + 40, (i3 - i2) + 40);
                } else if (matrix2.get(i4, i3)) {
                    iArr[(i3 * width) + i4] = -16777216;
                }
            }
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        createBitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00dd, code lost:
    
        if (r7 < (r15 + 2)) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ed, code lost:
    
        if (r7 < (r1 + 2)) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bitmap createQRCode(String str, int widthAndHeight, Bitmap iconBit) throws WriterException {
        int i = 0;
        Bitmap scaleImage = Bitmap.createScaledBitmap(iconBit, 60, 60, false);
        int[][] iArr = new int[60];
        for (int i2 = 0; i2 < 60; i2++) {
            iArr[i2] = new int[60];
        }
        int[][] iArr2 = iArr;
        Intrinsics.checkExpressionValueIsNotNull(scaleImage, "scaleImage");
        int width = scaleImage.getWidth();
        for (int i3 = 0; i3 < width; i3++) {
            int height = scaleImage.getHeight();
            for (int i4 = 0; i4 < height; i4++) {
                iArr2[i3][i4] = scaleImage.getPixel(i3, i4);
            }
        }
        new Hashtable().put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
        Intrinsics.checkExpressionValueIsNotNull(matrix, "matrix");
        int width2 = matrix.getWidth();
        int height2 = matrix.getHeight();
        int width3 = matrix.getWidth() / 2;
        int height3 = matrix.getHeight() / 2;
        int[] iArr3 = new int[width2 * height2];
        int height4 = matrix.getHeight();
        int i5 = 0;
        while (i5 < height4) {
            int width4 = matrix.getWidth();
            for (int i6 = i; i6 < width4; i6++) {
                int i7 = width3 - 30;
                if (i6 > i7 && i6 < width3 + 30 && i5 > height3 - 30 && i5 < height3 + 30) {
                    iArr3[(i5 * width2) + i6] = iArr2[(i6 - width3) + 30][(i5 - height3) + 30];
                } else {
                    int i8 = i7 - 2;
                    if (i6 <= i8 || i6 >= i7 + 2 || i5 <= (height3 - 30) - 2 || i5 >= height3 + 30 + 2) {
                        int i9 = width3 + 30;
                        if (i6 <= i9 - 2 || i6 >= i9 + 2 || i5 <= (height3 - 30) - 2 || i5 >= height3 + 30 + 2) {
                            if (i6 > i8 && i6 < i9 + 2) {
                                int i10 = height3 - 30;
                                if (i5 > i10 - 2) {
                                }
                            }
                            if (i6 > i8 && i6 < i9 + 2) {
                                int i11 = height3 + 30;
                                if (i5 > i11 - 2) {
                                }
                            }
                            iArr3[(i5 * width2) + i6] = matrix.get(i6, i5) ? -16777216 : 268435455;
                        }
                    }
                    iArr3[(i5 * width2) + i6] = 268435455;
                }
            }
            i5++;
            i = 0;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr3, 0, width2, 0, 0, width2, height2);
        return createBitmap;
    }

    public final Bitmap createImage(String text, int w, int h, Bitmap logo) {
        int i;
        int i2;
        int i3;
        int i4;
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        try {
            Bitmap scaleLogo = getScaleLogo(logo, w, h);
            int i5 = w / 2;
            int i6 = h / 2;
            int i7 = 0;
            if (scaleLogo != null) {
                int width = scaleLogo.getWidth();
                int height = scaleLogo.getHeight();
                i3 = width;
                i4 = height;
                i = (w - width) / 2;
                i2 = (h - height) / 2;
            } else {
                i = i5;
                i2 = i6;
                i3 = 0;
                i4 = 0;
            }
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hashtable.put(EncodeHintType.MARGIN, 0);
            BitMatrix encode = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, w, h, hashtable);
            int[] iArr = new int[w * h];
            int i8 = 0;
            while (i8 < h) {
                for (int i9 = i7; i9 < w; i9++) {
                    if (i9 >= i && i9 < i + i3 && i8 >= i2 && i8 < i2 + i4) {
                        if (scaleLogo == null) {
                            Intrinsics.throwNpe();
                        }
                        int pixel = scaleLogo.getPixel(i9 - i, i8 - i2);
                        if (pixel == 0) {
                            pixel = encode.get(i9, i8) ? -16777216 : -1;
                        }
                        iArr[(i8 * w) + i9] = pixel;
                    } else if (encode.get(i9, i8)) {
                        iArr[(i8 * w) + i9] = -16777216;
                    } else {
                        iArr[(i8 * w) + i9] = -1;
                    }
                }
                i8++;
                i7 = 0;
            }
            Bitmap createBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, w, 0, 0, w, h);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private final Bitmap getScaleLogo(Bitmap logo, int w, int h) {
        if (logo == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        float max = Math.max(60.0f / logo.getWidth(), 60.0f / logo.getHeight());
        matrix.postScale(max, max);
        return Bitmap.createBitmap(logo, 0, 0, logo.getWidth(), logo.getHeight(), matrix, true);
    }
}
