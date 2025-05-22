package com.iflytek.cloud.thirdparty;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.TypedValue;
import java.io.InputStream;

/* renamed from: com.iflytek.cloud.thirdparty.as */
/* loaded from: classes3.dex */
public class C3707as {

    /* renamed from: a */
    private static int f3082a;

    /* renamed from: a */
    public static Drawable m1978a(Resources resources, TypedValue typedValue, InputStream inputStream, String str, BitmapFactory.Options options) {
        Rect rect = null;
        if (inputStream == null) {
            return null;
        }
        Rect rect2 = new Rect();
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        Bitmap m1976a = m1976a(resources, typedValue, inputStream, rect2, options);
        if (m1976a == null) {
            return null;
        }
        byte[] ninePatchChunk = m1976a.getNinePatchChunk();
        if (ninePatchChunk == null || !NinePatch.isNinePatchChunk(ninePatchChunk)) {
            ninePatchChunk = null;
        } else {
            rect = rect2;
        }
        return m1977a(resources, m1976a, ninePatchChunk, rect, str);
    }

    /* renamed from: a */
    public static Bitmap m1976a(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        if (options.inDensity == 0 && typedValue != null) {
            int i = typedValue.density;
            if (i == 0) {
                options.inDensity = 160;
            } else if (i != 65535) {
                options.inDensity = i;
            }
        }
        if (options.inTargetDensity == 0 && resources != null) {
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        }
        return BitmapFactory.decodeStream(inputStream, rect, options);
    }

    /* renamed from: a */
    private static Drawable m1977a(Resources resources, Bitmap bitmap, byte[] bArr, Rect rect, String str) {
        if (bArr != null) {
            return new NinePatchDrawable(resources, bitmap, bArr, rect, str);
        }
        return new BitmapDrawable(resources, bitmap);
    }
}
