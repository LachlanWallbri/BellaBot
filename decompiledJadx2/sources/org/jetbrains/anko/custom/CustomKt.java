package org.jetbrains.anko.custom;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewManager;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Custom.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u00008\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a`\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u000f\u001a`\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00062!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u0010\u001a`\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00112!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u0012\u001aA\u0010\u0013\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u0014\u001aA\u0010\u0013\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u0015\u001aA\u0010\u0013\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, m3961d2 = {"ankoView", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "Landroid/app/Activity;", "factory", "Lkotlin/Function1;", "Landroid/content/Context;", "Lkotlin/ParameterName;", "name", "ctx", "theme", "", "init", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/app/Activity;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "Landroid/view/ViewManager;", "(Landroid/view/ViewManager;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "customView", "(Landroid/app/Activity;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/content/Context;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/view/ViewManager;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class CustomKt {
    public static final <T extends View> T ankoView(ViewManager receiver, Function1<? super Context, ? extends T> factory, int i, Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        T invoke = factory.invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver, (ViewManager) invoke);
        return invoke;
    }

    public static final <T extends View> T ankoView(Context receiver, Function1<? super Context, ? extends T> factory, int i, Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        T invoke = factory.invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver, (Context) invoke);
        return invoke;
    }

    public static final <T extends View> T ankoView(Activity receiver, Function1<? super Context, ? extends T> factory, int i, Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        T invoke = factory.invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver, (Activity) invoke);
        return invoke;
    }

    private static final <T extends View> T customView(ViewManager viewManager, int i, Function1<? super T, Unit> function1) {
        Context wrapContextIfNeeded = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) AnkoInternals.initiateView(wrapContextIfNeeded, View.class);
        function1.invoke(t);
        AnkoInternals.INSTANCE.addView(viewManager, (ViewManager) t);
        return t;
    }

    static /* bridge */ /* synthetic */ View customView$default(ViewManager viewManager, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Context wrapContextIfNeeded = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewManager), i);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        View initiateView = AnkoInternals.initiateView(wrapContextIfNeeded, View.class);
        function1.invoke(initiateView);
        AnkoInternals.INSTANCE.addView(viewManager, (ViewManager) initiateView);
        return initiateView;
    }

    private static final <T extends View> T customView(Context context, int i, Function1<? super T, Unit> function1) {
        Context wrapContextIfNeeded = AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) AnkoInternals.initiateView(wrapContextIfNeeded, View.class);
        function1.invoke(t);
        AnkoInternals.INSTANCE.addView(context, (Context) t);
        return t;
    }

    static /* bridge */ /* synthetic */ View customView$default(Context context, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Context wrapContextIfNeeded = AnkoInternals.INSTANCE.wrapContextIfNeeded(context, i);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        View initiateView = AnkoInternals.initiateView(wrapContextIfNeeded, View.class);
        function1.invoke(initiateView);
        AnkoInternals.INSTANCE.addView(context, (Context) initiateView);
        return initiateView;
    }

    private static final <T extends View> T customView(Activity activity, int i, Function1<? super T, Unit> function1) {
        Context wrapContextIfNeeded = AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) AnkoInternals.initiateView(wrapContextIfNeeded, View.class);
        function1.invoke(t);
        AnkoInternals.INSTANCE.addView(activity, (Activity) t);
        return t;
    }

    static /* bridge */ /* synthetic */ View customView$default(Activity activity, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Context wrapContextIfNeeded = AnkoInternals.INSTANCE.wrapContextIfNeeded(activity, i);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        View initiateView = AnkoInternals.initiateView(wrapContextIfNeeded, View.class);
        function1.invoke(initiateView);
        AnkoInternals.INSTANCE.addView(activity, (Activity) initiateView);
        return initiateView;
    }
}
