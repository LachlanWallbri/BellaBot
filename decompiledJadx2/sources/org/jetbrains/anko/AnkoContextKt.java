package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
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
/* compiled from: AnkoContext.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0086\b\u001a:\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0086\b\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\u00072\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H\u0086\b\u001a'\u0010\b\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\f*\b\u0012\u0004\u0012\u0002H\u000b0\r2\u0006\u0010\u000e\u001a\u0002H\u000b¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"UI", "Lorg/jetbrains/anko/AnkoContext;", "Landroid/app/Fragment;", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "Landroid/content/Context;", "setContentView", "", "Landroid/view/View;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/app/Activity;", "Lorg/jetbrains/anko/AnkoComponent;", "activity", "(Lorg/jetbrains/anko/AnkoComponent;Landroid/app/Activity;)Landroid/view/View;", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AnkoContextKt {
    /* renamed from: UI */
    public static final AnkoContext<Context> m4166UI(Context receiver, boolean z, Function1<? super AnkoContext<? extends Context>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(receiver, receiver, z);
        init.invoke(ankoContextImpl);
        return ankoContextImpl;
    }

    /* renamed from: UI */
    public static final AnkoContext<Context> m4165UI(Context receiver, Function1<? super AnkoContext<? extends Context>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(receiver, receiver, false);
        init.invoke(ankoContextImpl);
        return ankoContextImpl;
    }

    /* renamed from: UI */
    public static final AnkoContext<Fragment> m4164UI(Fragment receiver, Function1<? super AnkoContext<? extends Fragment>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        Activity activity = receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(activity, receiver, false);
        init.invoke(ankoContextImpl);
        return ankoContextImpl;
    }

    public static final <T extends Activity> View setContentView(AnkoComponent<? super T> receiver, T activity) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        return receiver.createView(new AnkoContextImpl(activity, activity, true));
    }
}
