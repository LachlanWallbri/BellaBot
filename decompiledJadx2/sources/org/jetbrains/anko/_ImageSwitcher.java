package org.jetbrains.anko;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Layouts.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0086\b¢\u0006\u0002\u0010\u000bJI\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0011J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\b¢\u0006\u0002\u0010\u0014J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0015J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0016H\u0086\b¢\u0006\u0002\u0010\u0017J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00162\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0018J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0086\b¢\u0006\u0002\u0010\u0019J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001aJ0\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0086\b¢\u0006\u0002\u0010\u001eJI\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001fJ8\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001cH\u0086\b¢\u0006\u0002\u0010!JQ\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001c2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\"¨\u0006#"}, m3961d2 = {"Lorg/jetbrains/anko/_ImageSwitcher;", "Landroid/widget/ImageSwitcher;", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lparams", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "c", "attrs", "Landroid/util/AttributeSet;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;)Landroid/view/View;", "init", "Lkotlin/Function1;", "Landroid/widget/FrameLayout$LayoutParams;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", MapElement.Source.SOURCE, "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "Landroid/view/ViewGroup$MarginLayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$MarginLayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$MarginLayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/widget/FrameLayout$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "width", "", "height", "(Landroid/view/View;II)Landroid/view/View;", "(Landroid/view/View;IILkotlin/jvm/functions/Function1;)Landroid/view/View;", "gravity", "(Landroid/view/View;III)Landroid/view/View;", "(Landroid/view/View;IIILkotlin/jvm/functions/Function1;)Landroid/view/View;", "anko-sdk27_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public class _ImageSwitcher extends ImageSwitcher {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public _ImageSwitcher(Context ctx) {
        super(ctx);
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
    }

    public final <T extends View> T lparams(T receiver$0, Context context, AttributeSet attributeSet, Function1<? super FrameLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (attributeSet == null) {
            Intrinsics.throwNpe();
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(context, attributeSet);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, Context context, AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (attributeSet == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(context, attributeSet));
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ImageSwitcher _imageswitcher, View receiver$0, int i, int i2, Function1 init, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
        }
        if ((i3 & 1) != 0) {
            i = -2;
        }
        if ((i3 & 2) != 0) {
            i2 = -2;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, Function1<? super FrameLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ImageSwitcher _imageswitcher, View receiver$0, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
        }
        if ((i3 & 1) != 0) {
            i = -2;
        }
        if ((i3 & 2) != 0) {
            i2 = -2;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(i, i2));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(i, i2));
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ImageSwitcher _imageswitcher, View receiver$0, int i, int i2, int i3, Function1 init, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
        }
        if ((i4 & 1) != 0) {
            i = -2;
        }
        if ((i4 & 2) != 0) {
            i2 = -2;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2, i3);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, int i3, Function1<? super FrameLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2, i3);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ImageSwitcher _imageswitcher, View receiver$0, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
        }
        if ((i4 & 1) != 0) {
            i = -2;
        }
        if ((i4 & 2) != 0) {
            i2 = -2;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(i, i2, i3));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(i, i2, i3));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams, Function1<? super FrameLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        init.invoke(layoutParams2);
        receiver$0.setLayoutParams(layoutParams2);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(layoutParams));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.MarginLayoutParams marginLayoutParams, Function1<? super FrameLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (marginLayoutParams == null) {
            Intrinsics.throwNpe();
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(marginLayoutParams);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.MarginLayoutParams marginLayoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (marginLayoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(marginLayoutParams));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, FrameLayout.LayoutParams layoutParams, Function1<? super FrameLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        init.invoke(layoutParams2);
        receiver$0.setLayoutParams(layoutParams2);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, FrameLayout.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new FrameLayout.LayoutParams(layoutParams));
        return receiver$0;
    }
}
