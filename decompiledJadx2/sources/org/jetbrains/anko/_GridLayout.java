package org.jetbrains.anko;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u0006H\u0086\b¢\u0006\u0002\u0010\bJ0\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0086\b¢\u0006\u0002\u0010\fJI\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\u0012J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0086\b¢\u0006\u0002\u0010\u0015J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\u0016J&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0017H\u0086\b¢\u0006\u0002\u0010\u0018J?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00172\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\u0019J5\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\u001aJ&\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u000fH\u0086\b¢\u0006\u0002\u0010\u001cJ?\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u000f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\u001dJ0\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0086\b¢\u0006\u0002\u0010!JI\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011H\u0086\b¢\u0006\u0002\u0010\"¨\u0006#"}, m3961d2 = {"Lorg/jetbrains/anko/_GridLayout;", "Landroid/widget/GridLayout;", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lparams", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "(Landroid/view/View;)Landroid/view/View;", "context", "attrs", "Landroid/util/AttributeSet;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;)Landroid/view/View;", "init", "Lkotlin/Function1;", "Landroid/widget/GridLayout$LayoutParams;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/view/View;Landroid/content/Context;Landroid/util/AttributeSet;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "params", "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "Landroid/view/ViewGroup$MarginLayoutParams;", "(Landroid/view/View;Landroid/view/ViewGroup$MarginLayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/view/ViewGroup$MarginLayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", MapElement.Source.SOURCE, "(Landroid/view/View;Landroid/widget/GridLayout$LayoutParams;)Landroid/view/View;", "(Landroid/view/View;Landroid/widget/GridLayout$LayoutParams;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "rowSpec", "Landroid/widget/GridLayout$Spec;", "columnSpec", "(Landroid/view/View;Landroid/widget/GridLayout$Spec;Landroid/widget/GridLayout$Spec;)Landroid/view/View;", "(Landroid/view/View;Landroid/widget/GridLayout$Spec;Landroid/widget/GridLayout$Spec;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "anko-sdk27_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public class _GridLayout extends GridLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public _GridLayout(Context ctx) {
        super(ctx);
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
    }

    public final <T extends View> T lparams(T receiver$0, GridLayout.Spec spec, GridLayout.Spec spec2, Function1<? super GridLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (spec == null) {
            Intrinsics.throwNpe();
        }
        if (spec2 == null) {
            Intrinsics.throwNpe();
        }
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(spec, spec2);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, GridLayout.Spec spec, GridLayout.Spec spec2) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (spec == null) {
            Intrinsics.throwNpe();
        }
        if (spec2 == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new GridLayout.LayoutParams(spec, spec2));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, Function1<? super GridLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.setLayoutParams(new GridLayout.LayoutParams());
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams, Function1<? super GridLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams(layoutParams);
        init.invoke(layoutParams2);
        receiver$0.setLayoutParams(layoutParams2);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new GridLayout.LayoutParams(layoutParams));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.MarginLayoutParams marginLayoutParams, Function1<? super GridLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (marginLayoutParams == null) {
            Intrinsics.throwNpe();
        }
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(marginLayoutParams);
        init.invoke(layoutParams);
        receiver$0.setLayoutParams(layoutParams);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, ViewGroup.MarginLayoutParams marginLayoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (marginLayoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new GridLayout.LayoutParams(marginLayoutParams));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, GridLayout.LayoutParams layoutParams, Function1<? super GridLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams(layoutParams);
        init.invoke(layoutParams2);
        receiver$0.setLayoutParams(layoutParams2);
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, GridLayout.LayoutParams layoutParams) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (layoutParams == null) {
            Intrinsics.throwNpe();
        }
        receiver$0.setLayoutParams(new GridLayout.LayoutParams(layoutParams));
        return receiver$0;
    }

    public final <T extends View> T lparams(T receiver$0, Context context, AttributeSet attributeSet, Function1<? super GridLayout.LayoutParams, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        if (context == null) {
            Intrinsics.throwNpe();
        }
        if (attributeSet == null) {
            Intrinsics.throwNpe();
        }
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(context, attributeSet);
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
        receiver$0.setLayoutParams(new GridLayout.LayoutParams(context, attributeSet));
        return receiver$0;
    }
}
