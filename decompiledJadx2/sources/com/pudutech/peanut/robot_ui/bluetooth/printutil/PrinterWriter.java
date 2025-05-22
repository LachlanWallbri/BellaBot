package com.pudutech.peanut.robot_ui.bluetooth.printutil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.Character;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public abstract class PrinterWriter {
    private static final String CHARSET = "gb2312";
    public static final int HEIGHT_PARTING_DEFAULT = 255;
    private ByteArrayOutputStream bos;
    private int heightParting;

    protected abstract int getDrawableMaxWidth();

    protected abstract int getLineStringWidth(int i);

    protected abstract int getLineWidth();

    public PrinterWriter() throws IOException {
        this(255);
    }

    public PrinterWriter(int i) throws IOException {
        if (i <= 0 || i > 255) {
            this.heightParting = 255;
        } else {
            this.heightParting = i;
        }
        init();
    }

    @Deprecated
    public void reset() throws IOException {
        init();
    }

    public void init() throws IOException {
        this.bos = new ByteArrayOutputStream();
        write(PrinterUtils.initPrinter());
    }

    @Deprecated
    public byte[] getData() throws IOException {
        return getDataAndClose();
    }

    public byte[] getDataAndReset() throws IOException {
        this.bos.flush();
        byte[] byteArray = this.bos.toByteArray();
        this.bos.reset();
        return byteArray;
    }

    public byte[] getDataAndClose() throws IOException {
        this.bos.flush();
        byte[] byteArray = this.bos.toByteArray();
        this.bos.close();
        this.bos = null;
        return byteArray;
    }

    public void write(byte[] bArr) throws IOException {
        if (this.bos == null) {
            reset();
        }
        this.bos.write(bArr);
    }

    public void setAlignCenter() throws IOException {
        write(PrinterUtils.alignCenter());
    }

    public void setAlignLeft() throws IOException {
        write(PrinterUtils.alignLeft());
    }

    public void setAlignRight() throws IOException {
        write(PrinterUtils.alignRight());
    }

    public void setEmphasizedOn() throws IOException {
        write(PrinterUtils.emphasizedOn());
    }

    public void setEmphasizedOff() throws IOException {
        write(PrinterUtils.emphasizedOff());
    }

    public void setFontSize(int i) throws IOException {
        write(PrinterUtils.fontSizeSetBig(i));
    }

    public void setFontSizes(int i) throws IOException {
        write(PrinterUtils.fontSize(i));
    }

    public void setLineHeight(int i) throws IOException {
        if (i < 0 || i > 255) {
            return;
        }
        write(PrinterUtils.printLineHeight((byte) i));
    }

    public void print(String str) throws IOException {
        print(str, "gb2312");
    }

    public void print(String str, String str2) throws IOException {
        if (str == null) {
            return;
        }
        write(str.getBytes(str2));
    }

    public void printLine() throws IOException {
        String str = "";
        for (int lineWidth = getLineWidth(); lineWidth > 0; lineWidth--) {
            str = str + "- ";
        }
        print(str);
    }

    public void printInOneLine(String str, String str2, int i) throws IOException {
        printInOneLine(str, str2, i, "gb2312");
    }

    public void printInOneLine(String str, String str2, String str3, int i) throws IOException {
        printInOneLine(str, str2, str3, "gb2312");
    }

    public void printInOneLine(String str, String str2, int i, String str3) throws IOException {
        int lineStringWidth = getLineStringWidth(i);
        String str4 = "";
        for (int stringWidth = lineStringWidth - ((getStringWidth(str) + getStringWidth(str2)) % lineStringWidth); stringWidth > 0; stringWidth--) {
            str4 = str4 + " ";
        }
        print(str + str4 + str2, str3);
    }

    public void printInOneLine(String str, String str2, String str3, String str4) throws IOException {
        int lineStringWidth = getLineStringWidth(0);
        String str5 = "";
        for (int stringWidth = (lineStringWidth - (((getStringWidth(str) + getStringWidth(str2)) + getStringWidth(str3)) % lineStringWidth)) / 2; stringWidth > 0; stringWidth--) {
            str5 = str5 + " ";
        }
        print(str + str5 + str2 + str5 + str3, str4);
    }

    private int getStringWidth(String str) {
        int i = 0;
        for (char c : str.toCharArray()) {
            i += isChinese(c) ? 2 : 1;
        }
        return i;
    }

    @Deprecated
    public void printDrawable(Resources resources, int i) throws IOException {
        Bitmap scalingBitmap = scalingBitmap(resources, i, getDrawableMaxWidth());
        if (scalingBitmap == null) {
            return;
        }
        byte[] decodeBitmap = PrinterUtils.decodeBitmap(scalingBitmap, this.heightParting);
        scalingBitmap.recycle();
        if (decodeBitmap != null) {
            try {
                write(decodeBitmap);
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public ArrayList<byte[]> getImageByte(Resources resources, int i) {
        Bitmap scalingBitmap = scalingBitmap(resources, i, getDrawableMaxWidth());
        if (scalingBitmap == null) {
            return null;
        }
        ArrayList<byte[]> decodeBitmapToDataList = PrinterUtils.decodeBitmapToDataList(scalingBitmap, this.heightParting);
        scalingBitmap.recycle();
        return decodeBitmapToDataList;
    }

    public ArrayList<byte[]> getBitmapByte(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ArrayList<byte[]> decodeBitmapToDataList = PrinterUtils.decodeBitmapToDataList(bitmap, this.heightParting);
        bitmap.recycle();
        return decodeBitmapToDataList;
    }

    private Bitmap scalingBitmap(Resources resources, int i, int i2) {
        int floor;
        if (resources == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inJustDecodeBounds = false;
        if (i2 > 0 && options.outWidth > i2 && (floor = (int) Math.floor(options.outWidth / i2)) > 1) {
            options.inSampleSize = floor;
        }
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(resources, i, options);
            int width = decodeResource.getWidth();
            int height = decodeResource.getHeight();
            if (i2 > 0 && width > i2) {
                float f = i2 / width;
                Matrix matrix = new Matrix();
                matrix.postScale(f, f);
                Bitmap createBitmap = Bitmap.createBitmap(decodeResource, 0, 0, width, height, matrix, true);
                decodeResource.recycle();
                return createBitmap;
            }
            return decodeResource;
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    @Deprecated
    public void printDrawable(Drawable drawable) throws IOException {
        Bitmap scalingDrawable = scalingDrawable(drawable, getDrawableMaxWidth());
        if (scalingDrawable == null) {
            return;
        }
        byte[] decodeBitmap = PrinterUtils.decodeBitmap(scalingDrawable, this.heightParting);
        scalingDrawable.recycle();
        if (decodeBitmap != null) {
            try {
                write(decodeBitmap);
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public ArrayList<byte[]> getImageByte(Drawable drawable) {
        Bitmap scalingDrawable = scalingDrawable(drawable, getDrawableMaxWidth());
        if (scalingDrawable == null) {
            return null;
        }
        ArrayList<byte[]> decodeBitmapToDataList = PrinterUtils.decodeBitmapToDataList(scalingDrawable, this.heightParting);
        scalingDrawable.recycle();
        return decodeBitmapToDataList;
    }

    private Bitmap scalingDrawable(Drawable drawable, int i) {
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            try {
                Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(createBitmap);
                drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                drawable.draw(canvas);
                if (i > 0 && intrinsicWidth > i) {
                    float f = i / intrinsicWidth;
                    Matrix matrix = new Matrix();
                    matrix.postScale(f, f);
                    Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, intrinsicWidth, intrinsicHeight, matrix, true);
                    createBitmap.recycle();
                    return createBitmap2;
                }
                return createBitmap;
            } catch (OutOfMemoryError unused) {
            }
        }
        return null;
    }

    @Deprecated
    public void printBitmap(Bitmap bitmap) throws IOException {
        Bitmap scalingBitmap = scalingBitmap(bitmap, getDrawableMaxWidth());
        if (scalingBitmap == null) {
            return;
        }
        byte[] decodeBitmap = PrinterUtils.decodeBitmap(scalingBitmap, this.heightParting);
        scalingBitmap.recycle();
        if (decodeBitmap != null) {
            try {
                write(decodeBitmap);
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public ArrayList<byte[]> getImageByte(Bitmap bitmap) {
        if (scalingBitmap(bitmap, getDrawableMaxWidth()) == null) {
            return null;
        }
        ArrayList<byte[]> decodeBitmapToDataList = PrinterUtils.decodeBitmapToDataList(bitmap, this.heightParting);
        bitmap.recycle();
        return decodeBitmapToDataList;
    }

    private Bitmap scalingBitmap(Bitmap bitmap, int i) {
        if (bitmap != null && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
            try {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                float f = (i <= 0 || width <= i) ? i / width : 1.0f;
                Matrix matrix = new Matrix();
                matrix.postScale(f, f);
                return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            } catch (OutOfMemoryError unused) {
            }
        }
        return null;
    }

    @Deprecated
    public void printImageFile(String str) throws IOException {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            if (i > 0 && i2 > 0) {
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                printBitmap(BitmapFactory.decodeFile(str, options));
            }
        } catch (Exception | OutOfMemoryError unused) {
        }
    }

    public ArrayList<byte[]> getImageByte(String str) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            if (i > 0 && i2 > 0) {
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                return getImageByte(BitmapFactory.decodeFile(str, options));
            }
        } catch (Exception | OutOfMemoryError unused) {
        }
        return null;
    }

    public void printLineFeed() throws IOException {
        write(PrinterUtils.printLineFeed());
    }

    public void feedPaperCut() throws IOException {
        write(PrinterUtils.feedPaperCut());
    }

    public void feedPaperCutPartial() throws IOException {
        write(PrinterUtils.feedPaperCutPartial());
    }

    public void setHeightParting(int i) {
        if (i <= 0 || i > 255) {
            return;
        }
        this.heightParting = i;
    }

    public int getHeightParting() {
        return this.heightParting;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
}
