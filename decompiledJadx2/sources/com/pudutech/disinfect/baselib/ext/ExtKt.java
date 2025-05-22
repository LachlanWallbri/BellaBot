package com.pudutech.disinfect.baselib.ext;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import com.pudutech.base.Pdlog;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: Ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\u0005\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0001\u001a\f\u0010\f\u001a\u0004\u0018\u00010\u0003*\u00020\r\u001a\n\u0010\u000e\u001a\u00020\n*\u00020\u0005\u001a\u0012\u0010\u000f\u001a\u00020\n*\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"BITMAP_SCALE", "", "blur", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "radius", "checkCanDrawOverlaysPermission", "", "dp2px", "", "dpValue", "getBitmapFromView", "Landroid/view/View;", "getScreenWidth", "sp2px", "spValue", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ExtKt {
    private static final float BITMAP_SCALE = 0.2f;

    public static final Bitmap blur(Bitmap blur, Context context, float f) {
        Intrinsics.checkParameterIsNotNull(blur, "$this$blur");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(blur, MathKt.roundToInt(blur.getWidth() * 0.2f), MathKt.roundToInt(blur.getHeight() * 0.2f), false);
        Bitmap outBitmap = Bitmap.createBitmap(createScaledBitmap);
        RenderScript create = RenderScript.create(context);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, createScaledBitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, outBitmap);
        create2.setRadius(f);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(outBitmap);
        Intrinsics.checkExpressionValueIsNotNull(outBitmap, "outBitmap");
        return outBitmap;
    }

    public static final Bitmap getBitmapFromView(View getBitmapFromView) {
        Intrinsics.checkParameterIsNotNull(getBitmapFromView, "$this$getBitmapFromView");
        if (getBitmapFromView.getWidth() <= 0 && getBitmapFromView.getHeight() <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(getBitmapFromView.getWidth(), getBitmapFromView.getHeight(), Bitmap.Config.ARGB_8888);
        getBitmapFromView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static final int sp2px(Context sp2px, float f) {
        Intrinsics.checkParameterIsNotNull(sp2px, "$this$sp2px");
        Resources resources = sp2px.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((f * resources.getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static final int getScreenWidth(Context getScreenWidth) {
        Intrinsics.checkParameterIsNotNull(getScreenWidth, "$this$getScreenWidth");
        Resources resources = getScreenWidth.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return resources.getDisplayMetrics().widthPixels;
    }

    public static final int dp2px(Context dp2px, float f) {
        Intrinsics.checkParameterIsNotNull(dp2px, "$this$dp2px");
        Resources resources = dp2px.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((f * resources.getDisplayMetrics().density) + 0.5f);
    }

    public static final boolean checkCanDrawOverlaysPermission(Context checkCanDrawOverlaysPermission) {
        Intrinsics.checkParameterIsNotNull(checkCanDrawOverlaysPermission, "$this$checkCanDrawOverlaysPermission");
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            Method declaredMethod = Settings.class.getDeclaredMethod("canDrawOverlays", Context.class);
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "clazz.getDeclaredMethod(…ys\", Context::class.java)");
            Object invoke = declaredMethod.invoke(null, checkCanDrawOverlaysPermission);
            if (invoke != null) {
                return ((Boolean) invoke).booleanValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        } catch (Exception e) {
            Pdlog.m3274e("PermissionFragment", e.getMessage());
            return false;
        }
    }
}
