package org.jetbrains.anko.appcompat.p092v7;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.ButtonBarLayout;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J0\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\n\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0086\b¢\u0006\u0002\u0010\u000bJI\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\n\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0011J&\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\b¢\u0006\u0002\u0010\u0014J?\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0015J&\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0086\b¢\u0006\u0002\u0010\u0018J?\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0019J&\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000eH\u0086\b¢\u0006\u0002\u0010\u001aJ?\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001bJ0\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001dH\u0086\b¢\u0006\u0002\u0010\u001fJI\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010 J8\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0086\b¢\u0006\u0002\u0010#JQ\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t*\u0002H\b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010$¨\u0006%"}, m3961d2 = {"Lorg/jetbrains/anko/appcompat/v7/_ButtonBarLayout;", "Landroidx/appcompat/widget/ButtonBarLayout;", "ctx", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "lparams", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "c", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;)Landroid/view/View;", "init", "Lkotlin/Function1;", "Landroid/widget/LinearLayout$LayoutParams;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "p", "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", MapElement.Source.SOURCE, "Landroid/view/ViewGroup$MarginLayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$MarginLayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$MarginLayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/view/View;Landroid/widget/LinearLayout$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/widget/LinearLayout$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "width", "", "height", "(Landroid/view/View;II)Landroid/view/View;", "(Landroid/view/View;IILkotlin/jvm/functions/Function1;)Landroid/view/View;", "weight", "", "(Landroid/view/View;IIF)Landroid/view/View;", "(Landroid/view/View;IIFLkotlin/jvm/functions/Function1;)Landroid/view/View;", "anko-appcompat-v7_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public class _ButtonBarLayout extends ButtonBarLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public _ButtonBarLayout(Context ctx, AttributeSet attributeSet) {
        super(ctx, null);
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
    }

    public final <T extends View> T lparams(T receiver$0, Context context, AttributeSet attributeSet, Function1<? super LinearLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (attributeSet == null) {
            Intrinsics.throwNpe();
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(context, attributeSet);
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
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(context, attributeSet));
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ButtonBarLayout _buttonbarlayout, View receiver$0, int i, int i2, Function1 init, int i3, Object obj) {
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
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, Function1<? super LinearLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ButtonBarLayout _buttonbarlayout, View receiver$0, int i, int i2, int i3, Object obj) {
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
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ButtonBarLayout _buttonbarlayout, View receiver$0, int i, int i2, float f, Function1 init, int i3, Object obj) {
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
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2, f);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, float f, Function1<? super LinearLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2, f);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_ButtonBarLayout _buttonbarlayout, View receiver$0, int i, int i2, float f, int i3, Object obj) {
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
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(i, i2, f));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, float f) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(i, i2, f));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams, Function1<? super LinearLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(layoutParams);
        init.invoke(layoutParams2);
        receiver$0.setLayoutParams(layoutParams2);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(layoutParams));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.MarginLayoutParams marginLayoutParams, Function1<? super LinearLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (marginLayoutParams == null) {
            Intrinsics.throwNpe();
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginLayoutParams);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.MarginLayoutParams marginLayoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (marginLayoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(marginLayoutParams));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, LinearLayout.LayoutParams layoutParams, Function1<? super LinearLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(layoutParams);
        init.invoke(layoutParams2);
        receiver$0.setLayoutParams(layoutParams2);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, LinearLayout.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new LinearLayout.LayoutParams(layoutParams));
        return receiver$0;
    }
}
