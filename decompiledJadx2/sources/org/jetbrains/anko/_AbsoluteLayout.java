package org.jetbrains.anko;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0086\b¢\u0006\u0002\u0010\u000bJI\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0011J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\b¢\u0006\u0002\u0010\u0014J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0015J@\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0017H\u0086\b¢\u0006\u0002\u0010\u001bJY\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, m3961d2 = {"Lorg/jetbrains/anko/_AbsoluteLayout;", "Landroid/widget/AbsoluteLayout;", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lparams", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "c", "attrs", "Landroid/util/AttributeSet;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;)Landroid/view/View;", "init", "Lkotlin/Function1;", "Landroid/widget/AbsoluteLayout$LayoutParams;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", MapElement.Source.SOURCE, "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "width", "", "height", "x", "y", "(Landroid/view/View;IIII)Landroid/view/View;", "(Landroid/view/View;IIIILkotlin/jvm/functions/Function1;)Landroid/view/View;", "anko-sdk27_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public class _AbsoluteLayout extends AbsoluteLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public _AbsoluteLayout(Context ctx) {
        super(ctx);
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
    }

    public static /* synthetic */ View lparams$default(_AbsoluteLayout _absolutelayout, View receiver$0, int i, int i2, int i3, int i4, Function1 init, int i5, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
        }
        if ((i5 & 1) != 0) {
            i = -2;
        }
        if ((i5 & 2) != 0) {
            i2 = -2;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(i, i2, i3, i4);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, int i3, int i4, Function1<? super AbsoluteLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(i, i2, i3, i4);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public static /* synthetic */ View lparams$default(_AbsoluteLayout _absolutelayout, View receiver$0, int i, int i2, int i3, int i4, int i5, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lparams");
        }
        if ((i5 & 1) != 0) {
            i = -2;
        }
        if ((i5 & 2) != 0) {
            i2 = -2;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new AbsoluteLayout.LayoutParams(i, i2, i3, i4));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, int i, int i2, int i3, int i4) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new AbsoluteLayout.LayoutParams(i, i2, i3, i4));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, Context context, AttributeSet attributeSet, Function1<? super AbsoluteLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (attributeSet == null) {
            Intrinsics.throwNpe();
        }
        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(context, attributeSet);
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
        receiver$0.setLayoutParams(new AbsoluteLayout.LayoutParams(context, attributeSet));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams, Function1<? super AbsoluteLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        AbsoluteLayout.LayoutParams layoutParams2 = new AbsoluteLayout.LayoutParams(layoutParams);
        init.invoke(layoutParams2);
        receiver$0.setLayoutParams(layoutParams2);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new AbsoluteLayout.LayoutParams(layoutParams));
        return receiver$0;
    }
}
