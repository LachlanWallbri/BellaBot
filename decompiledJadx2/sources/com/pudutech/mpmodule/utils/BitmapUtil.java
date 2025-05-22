package com.pudutech.mpmodule.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class BitmapUtil {
    public static Bitmap activityShot(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        Bitmap createBitmap = Bitmap.createBitmap(decorView.getDrawingCache(), 0, i, displayMetrics.widthPixels, displayMetrics.heightPixels - i);
        decorView.destroyDrawingCache();
        decorView.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static void renderScriptBlur(Context context, Bitmap bitmap, float f) {
        long currentTimeMillis = System.currentTimeMillis();
        RenderScript create = RenderScript.create(context);
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createTyped = Allocation.createTyped(create, createFromBitmap.getType());
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        create2.setRadius(f);
        create2.setInput(createFromBitmap);
        create2.forEach(createTyped);
        createTyped.copyTo(bitmap);
        Pdlog.m3273d("BitmapUtil", "renderScriptBlur() blur time:" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
    }

    public static void saveBitmap(Context context, Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory(), InternalConstant.DTYPE_IMAGE);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, "image.png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file2.getAbsolutePath(), "image.png", (String) null);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file:///sdcard/namecard/")));
    }

    public static Bitmap fastblur(Context context, Bitmap bitmap, int i) {
        int[] iArr;
        int i2 = i;
        if (bitmap == null) {
            return null;
        }
        try {
            Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
            if (i2 < 1) {
                return null;
            }
            int width = copy.getWidth();
            int height = copy.getHeight();
            int i3 = width * height;
            int[] iArr2 = new int[i3];
            copy.getPixels(iArr2, 0, width, 0, 0, width, height);
            int i4 = width - 1;
            int i5 = height - 1;
            int i6 = i2 + i2 + 1;
            int[] iArr3 = new int[i3];
            int[] iArr4 = new int[i3];
            int[] iArr5 = new int[i3];
            int[] iArr6 = new int[Math.max(width, height)];
            int i7 = (i6 + 1) >> 1;
            int i8 = i7 * i7;
            int i9 = i8 * 256;
            int[] iArr7 = new int[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                iArr7[i10] = i10 / i8;
            }
            int[][] iArr8 = (int[][]) Array.newInstance((Class<?>) int.class, i6, 3);
            int i11 = i2 + 1;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            while (i12 < height) {
                Bitmap bitmap2 = copy;
                int i15 = -i2;
                int i16 = 0;
                int i17 = 0;
                int i18 = 0;
                int i19 = 0;
                int i20 = 0;
                int i21 = 0;
                int i22 = 0;
                int i23 = 0;
                int i24 = 0;
                while (i15 <= i2) {
                    int i25 = height;
                    int i26 = i5;
                    int i27 = iArr2[i13 + Math.min(i4, Math.max(i15, 0))];
                    int[] iArr9 = iArr8[i15 + i2];
                    iArr9[0] = (i27 & 16711680) >> 16;
                    iArr9[1] = (i27 & 65280) >> 8;
                    iArr9[2] = i27 & 255;
                    int abs = i11 - Math.abs(i15);
                    i16 += iArr9[0] * abs;
                    i17 += iArr9[1] * abs;
                    i18 += iArr9[2] * abs;
                    if (i15 > 0) {
                        i22 += iArr9[0];
                        i23 += iArr9[1];
                        i24 += iArr9[2];
                    } else {
                        i19 += iArr9[0];
                        i20 += iArr9[1];
                        i21 += iArr9[2];
                    }
                    i15++;
                    height = i25;
                    i5 = i26;
                }
                int i28 = height;
                int i29 = i5;
                int i30 = i2;
                int i31 = 0;
                while (i31 < width) {
                    iArr3[i13] = iArr7[i16];
                    iArr4[i13] = iArr7[i17];
                    iArr5[i13] = iArr7[i18];
                    int i32 = i16 - i19;
                    int i33 = i17 - i20;
                    int i34 = i18 - i21;
                    int[] iArr10 = iArr8[((i30 - i2) + i6) % i6];
                    int i35 = i19 - iArr10[0];
                    int i36 = i20 - iArr10[1];
                    int i37 = i21 - iArr10[2];
                    if (i12 == 0) {
                        iArr = iArr7;
                        iArr6[i31] = Math.min(i31 + i2 + 1, i4);
                    } else {
                        iArr = iArr7;
                    }
                    int i38 = iArr2[i14 + iArr6[i31]];
                    iArr10[0] = (i38 & 16711680) >> 16;
                    iArr10[1] = (i38 & 65280) >> 8;
                    iArr10[2] = i38 & 255;
                    int i39 = i22 + iArr10[0];
                    int i40 = i23 + iArr10[1];
                    int i41 = i24 + iArr10[2];
                    i16 = i32 + i39;
                    i17 = i33 + i40;
                    i18 = i34 + i41;
                    i30 = (i30 + 1) % i6;
                    int[] iArr11 = iArr8[i30 % i6];
                    i19 = i35 + iArr11[0];
                    i20 = i36 + iArr11[1];
                    i21 = i37 + iArr11[2];
                    i22 = i39 - iArr11[0];
                    i23 = i40 - iArr11[1];
                    i24 = i41 - iArr11[2];
                    i13++;
                    i31++;
                    iArr7 = iArr;
                }
                i14 += width;
                i12++;
                copy = bitmap2;
                height = i28;
                i5 = i29;
            }
            Bitmap bitmap3 = copy;
            int i42 = height;
            int i43 = i5;
            int[] iArr12 = iArr7;
            int i44 = 0;
            while (i44 < width) {
                int i45 = -i2;
                int i46 = i45 * width;
                int i47 = 0;
                int i48 = 0;
                int i49 = 0;
                int i50 = 0;
                int i51 = 0;
                int i52 = 0;
                int i53 = 0;
                int i54 = 0;
                int i55 = 0;
                while (i45 <= i2) {
                    int[] iArr13 = iArr6;
                    int max = Math.max(0, i46) + i44;
                    int[] iArr14 = iArr8[i45 + i2];
                    iArr14[0] = iArr3[max];
                    iArr14[1] = iArr4[max];
                    iArr14[2] = iArr5[max];
                    int abs2 = i11 - Math.abs(i45);
                    i47 += iArr3[max] * abs2;
                    i48 += iArr4[max] * abs2;
                    i49 += iArr5[max] * abs2;
                    if (i45 > 0) {
                        i53 += iArr14[0];
                        i54 += iArr14[1];
                        i55 += iArr14[2];
                    } else {
                        i50 += iArr14[0];
                        i51 += iArr14[1];
                        i52 += iArr14[2];
                    }
                    int i56 = i43;
                    if (i45 < i56) {
                        i46 += width;
                    }
                    i45++;
                    i43 = i56;
                    iArr6 = iArr13;
                }
                int[] iArr15 = iArr6;
                int i57 = i43;
                int i58 = i54;
                int i59 = i55;
                int i60 = i42;
                int i61 = 0;
                int i62 = i2;
                int i63 = i53;
                int i64 = i52;
                int i65 = i51;
                int i66 = i50;
                int i67 = i49;
                int i68 = i48;
                int i69 = i47;
                int i70 = i44;
                while (i61 < i60) {
                    iArr2[i70] = (iArr2[i70] & ViewCompat.MEASURED_STATE_MASK) | (iArr12[i69] << 16) | (iArr12[i68] << 8) | iArr12[i67];
                    int i71 = i69 - i66;
                    int i72 = i68 - i65;
                    int i73 = i67 - i64;
                    int[] iArr16 = iArr8[((i62 - i2) + i6) % i6];
                    int i74 = i66 - iArr16[0];
                    int i75 = i65 - iArr16[1];
                    int i76 = i64 - iArr16[2];
                    if (i44 == 0) {
                        iArr15[i61] = Math.min(i61 + i11, i57) * width;
                    }
                    int i77 = iArr15[i61] + i44;
                    iArr16[0] = iArr3[i77];
                    iArr16[1] = iArr4[i77];
                    iArr16[2] = iArr5[i77];
                    int i78 = i63 + iArr16[0];
                    int i79 = i58 + iArr16[1];
                    int i80 = i59 + iArr16[2];
                    i69 = i71 + i78;
                    i68 = i72 + i79;
                    i67 = i73 + i80;
                    i62 = (i62 + 1) % i6;
                    int[] iArr17 = iArr8[i62];
                    i66 = i74 + iArr17[0];
                    i65 = i75 + iArr17[1];
                    i64 = i76 + iArr17[2];
                    i63 = i78 - iArr17[0];
                    i58 = i79 - iArr17[1];
                    i59 = i80 - iArr17[2];
                    i70 += width;
                    i61++;
                    i2 = i;
                }
                i44++;
                i2 = i;
                i42 = i60;
                i43 = i57;
                iArr6 = iArr15;
            }
            bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i42);
            return bitmap3;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
