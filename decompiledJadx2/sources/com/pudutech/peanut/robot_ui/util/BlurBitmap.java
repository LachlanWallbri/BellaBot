package com.pudutech.peanut.robot_ui.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.Window;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlurBitmap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/util/BlurBitmap;", "", "()V", "BITMAP_SCALE", "", "BLUR_RADIUS", "TAG", "", "kotlin.jvm.PlatformType", "blur", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", InternalConstant.DTYPE_IMAGE, "showBlurBg", "", "dialog", "Landroid/app/Dialog;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BlurBitmap {
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 15.0f;
    public static final BlurBitmap INSTANCE;
    private static final String TAG;

    static {
        BlurBitmap blurBitmap = new BlurBitmap();
        INSTANCE = blurBitmap;
        TAG = blurBitmap.getClass().getSimpleName();
    }

    private BlurBitmap() {
    }

    private final Bitmap blur(Context context, Bitmap image) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(image, Math.round(image.getWidth() * BITMAP_SCALE), Math.round(image.getHeight() * BITMAP_SCALE), false);
        Intrinsics.checkExpressionValueIsNotNull(createScaledBitmap, "Bitmap.createScaledBitma…ge, width, height, false)");
        Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(inputBitmap)");
        RenderScript create = RenderScript.create(context);
        Intrinsics.checkExpressionValueIsNotNull(create, "RenderScript.create(context)");
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Intrinsics.checkExpressionValueIsNotNull(create2, "ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))");
        Allocation createFromBitmap = Allocation.createFromBitmap(create, createScaledBitmap);
        Intrinsics.checkExpressionValueIsNotNull(createFromBitmap, "Allocation.createFromBitmap(rs, inputBitmap)");
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        Intrinsics.checkExpressionValueIsNotNull(createFromBitmap2, "Allocation.createFromBitmap(rs, outputBitmap)");
        create2.setRadius(BLUR_RADIUS);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        return createBitmap;
    }

    public final void showBlurBg(Context context, Dialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        Window window = dialog.getWindow();
        final Bitmap bitmap = (Bitmap) null;
        if (window != null) {
            long currentTimeMillis = System.currentTimeMillis();
            View decorView = window.getDecorView();
            if (decorView != null) {
                decorView.setDrawingCacheEnabled(true);
            }
            if (decorView != null) {
                decorView.destroyDrawingCache();
            }
            if (decorView != null) {
                decorView.buildDrawingCache();
            }
            Bitmap drawingCache = decorView != null ? decorView.getDrawingCache() : null;
            Pdlog.m3273d(TAG, "getDrawingCache take away:" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            Bitmap blur = drawingCache != null ? INSTANCE.blur(context, drawingCache) : null;
            Pdlog.m3273d(TAG, "blur take away:" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            window.setBackgroundDrawable(new BitmapDrawable(context != null ? context.getResources() : null, blur));
            if (drawingCache != null) {
                drawingCache.recycle();
            }
            bitmap = blur;
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.util.BlurBitmap$showBlurBg$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                Bitmap bitmap2 = bitmap;
                if (bitmap2 == null || bitmap2.isRecycled()) {
                    return;
                }
                bitmap.recycle();
            }
        });
    }
}
