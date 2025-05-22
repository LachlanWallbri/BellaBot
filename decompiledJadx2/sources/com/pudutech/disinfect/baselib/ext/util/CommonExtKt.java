package com.pudutech.disinfect.baselib.ext.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.activity.BaseVmActivity;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.disinfect.baselib.util.ToastUtils;
import com.pudutech.disinfect.baselib.util.UiUtils;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: CommonExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\u001a\u0010\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u001a7\u0010\f\u001a\u00020\r2\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00100\u000f\"\u0004\u0018\u00010\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\u0012¢\u0006\u0002\u0010\u0013\u001aA\u0010\f\u001a\u00020\r2\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00100\u000f\"\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\u0012¢\u0006\u0002\u0010\u0016\u001a\u001c\u0010\u0017\u001a\u00020\u0018*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\b\b\u0001\u0010\u001a\u001a\u00020\u0001\u001a\n\u0010\u001b\u001a\u00020\u001c*\u00020\u0004\u001a\u0012\u0010\u001d\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0001\u001a\u0012\u0010\u001d\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0001\u001a\f\u0010 \u001a\u0004\u0018\u00010\u0018*\u00020\u0010\u001a\u0016\u0010 \u001a\u0004\u0018\u00010\u0018*\u00020\u00102\b\b\u0001\u0010!\u001a\u00020\u0003\u001a\n\u0010\"\u001a\u00020\u0003*\u00020\u0004\u001a\u0014\u0010#\u001a\u00020\n*\u00020\u00102\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u001a@\u0010$\u001a\u00020\r\"\u0006\b\u0000\u0010%\u0018\u0001*\u0004\u0018\u0001H%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\r0\u00122\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020\r0(H\u0086\b¢\u0006\u0002\u0010)\u001a\u0012\u0010*\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0001\u001a\u0012\u0010*\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0001\u001a\u0012\u0010+\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0001\u001a\u0012\u0010+\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0001\u001a\u0012\u0010,\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0001\u001a\u0012\u0010,\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0001\u001a\u0014\u0010-\u001a\u00020.*\u00020\n2\b\b\u0002\u0010/\u001a\u00020\u0003\u001a\u0016\u00100\u001a\u00020\r*\u0006\u0012\u0002\b\u0003012\u0006\u00102\u001a\u00020\n\u001a\n\u00103\u001a\u00020\u0003*\u000204\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0015\u0010\u0007\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u00065"}, m3961d2 = {"BITMAP_SCALE", "", "screenHeight", "", "Landroid/content/Context;", "getScreenHeight", "(Landroid/content/Context;)I", "screenWidth", "getScreenWidth", "getSting", "", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "setOnClick", "", "views", "", "Landroid/view/View;", "onClick", "Lkotlin/Function1;", "([Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "interval", "", "([Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "blur", "Landroid/graphics/Bitmap;", "context", "radius", "checkCanDrawOverlaysPermission", "", "dp2px", "px", "dp", "getBitmapFromView", "colorBg", "getFullScreenHeight", "getString", "notNull", ExifInterface.GPS_DIRECTION_TRUE, "notNullAction", "nullAction", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "px2dp", "px2sp", "sp2px", "toHtml", "Landroid/text/Spanned;", "flag", "toast", "Lcom/pudutech/disinfect/baselib/base/activity/BaseVmActivity;", NotificationCompat.CATEGORY_MESSAGE, "transformNumber", "", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CommonExtKt {
    private static final float BITMAP_SCALE = 0.2f;

    public static final int getScreenWidth(Context screenWidth) {
        Intrinsics.checkParameterIsNotNull(screenWidth, "$this$screenWidth");
        Resources resources = screenWidth.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return resources.getDisplayMetrics().widthPixels;
    }

    public static final int getScreenHeight(Context screenHeight) {
        Intrinsics.checkParameterIsNotNull(screenHeight, "$this$screenHeight");
        Resources resources = screenHeight.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return resources.getDisplayMetrics().heightPixels;
    }

    public static /* synthetic */ void notNull$default(Object obj, Function1 notNullAction, Function0 nullAction, int i, Object obj2) {
        if ((i & 2) != 0) {
            nullAction = new Function0<Unit>() { // from class: com.pudutech.disinfect.baselib.ext.util.CommonExtKt$notNull$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(notNullAction, "notNullAction");
        Intrinsics.checkParameterIsNotNull(nullAction, "nullAction");
        if (obj != null) {
            notNullAction.invoke(obj);
        } else {
            nullAction.invoke();
        }
    }

    public static final /* synthetic */ <T> void notNull(T t, Function1<? super T, Unit> notNullAction, Function0<Unit> nullAction) {
        Intrinsics.checkParameterIsNotNull(notNullAction, "notNullAction");
        Intrinsics.checkParameterIsNotNull(nullAction, "nullAction");
        if (t != null) {
            notNullAction.invoke(t);
        } else {
            nullAction.invoke();
        }
    }

    public static final int px2dp(Context px2dp, float f) {
        Intrinsics.checkParameterIsNotNull(px2dp, "$this$px2dp");
        return UiUtils.px2dip(f);
    }

    public static final int px2sp(Context px2sp, float f) {
        Intrinsics.checkParameterIsNotNull(px2sp, "$this$px2sp");
        return UiUtils.px2sp(f);
    }

    public static final int dp2px(Context dp2px, float f) {
        Intrinsics.checkParameterIsNotNull(dp2px, "$this$dp2px");
        return UiUtils.dip2px(f);
    }

    public static final int sp2px(Context sp2px, float f) {
        Intrinsics.checkParameterIsNotNull(sp2px, "$this$sp2px");
        return UiUtils.sp2px(f);
    }

    public static final int dp2px(View dp2px, float f) {
        Intrinsics.checkParameterIsNotNull(dp2px, "$this$dp2px");
        return UiUtils.dip2px(f);
    }

    public static final int sp2px(View sp2px, float f) {
        Intrinsics.checkParameterIsNotNull(sp2px, "$this$sp2px");
        return UiUtils.sp2px(f);
    }

    public static final int px2dp(View px2dp, float f) {
        Intrinsics.checkParameterIsNotNull(px2dp, "$this$px2dp");
        return UiUtils.px2dip(f);
    }

    public static final int px2sp(View px2sp, float f) {
        Intrinsics.checkParameterIsNotNull(px2sp, "$this$px2sp");
        return UiUtils.px2sp(f);
    }

    public static /* synthetic */ void setOnClick$default(View[] viewArr, long j, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 500;
        }
        setOnClick(viewArr, j, function1);
    }

    public static /* synthetic */ Spanned toHtml$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return toHtml(str, i);
    }

    public static final Spanned toHtml(String toHtml, int i) {
        Intrinsics.checkParameterIsNotNull(toHtml, "$this$toHtml");
        if (Build.VERSION.SDK_INT >= 24) {
            Spanned fromHtml = Html.fromHtml(toHtml, i);
            Intrinsics.checkExpressionValueIsNotNull(fromHtml, "Html.fromHtml(this, flag)");
            return fromHtml;
        }
        Spanned fromHtml2 = Html.fromHtml(toHtml);
        Intrinsics.checkExpressionValueIsNotNull(fromHtml2, "Html.fromHtml(this)");
        return fromHtml2;
    }

    public static final int getFullScreenHeight(Context getFullScreenHeight) {
        Intrinsics.checkParameterIsNotNull(getFullScreenHeight, "$this$getFullScreenHeight");
        Object systemService = getFullScreenHeight.getSystemService("window");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
        }
        WindowManager windowManager = (WindowManager) systemService;
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    public static final String getString(View getString, int i) {
        Intrinsics.checkParameterIsNotNull(getString, "$this$getString");
        String string = getString.getContext().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(res)");
        return string;
    }

    public static final String getSting(int i) {
        String string = BaseApp.INSTANCE.getINSTANCE().getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "BaseApp.INSTANCE.getString(res)");
        return string;
    }

    public static final Bitmap getBitmapFromView(View getBitmapFromView, int i) {
        Intrinsics.checkParameterIsNotNull(getBitmapFromView, "$this$getBitmapFromView");
        if (getBitmapFromView.getWidth() <= 0 && getBitmapFromView.getHeight() <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(getBitmapFromView.getWidth(), getBitmapFromView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        getBitmapFromView.draw(canvas);
        canvas.drawColor(i);
        return createBitmap;
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

    public static final void toast(BaseVmActivity<?> toast, String msg) {
        Intrinsics.checkParameterIsNotNull(toast, "$this$toast");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        ToastUtils.INSTANCE.showToastSys(msg);
    }

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

    public static final int transformNumber(char c) {
        return Character.getNumericValue(c);
    }

    public static final void setOnClick(View[] views, final Function1<? super View, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(views, "views");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.disinfect.baselib.ext.util.CommonExtKt$setOnClick$$inlined$forEach$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        Function1 function1 = Function1.this;
                        Intrinsics.checkExpressionValueIsNotNull(view2, "view");
                        function1.invoke(view2);
                    }
                });
            }
        }
    }

    public static final void setOnClick(View[] views, final long j, final Function1<? super View, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(views, "views");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        for (View view : views) {
            if (view != null) {
                ViewExtKt.clickNotRepeat(view, j, new Function1<View, Unit>() { // from class: com.pudutech.disinfect.baselib.ext.util.CommonExtKt$setOnClick$$inlined$forEach$lambda$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                        invoke2(view2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View view2) {
                        Intrinsics.checkParameterIsNotNull(view2, "view");
                        onClick.invoke(view2);
                    }
                });
            }
        }
    }
}
