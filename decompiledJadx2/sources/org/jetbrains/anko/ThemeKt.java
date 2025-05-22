package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Theme.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00060\u0006R\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\t2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u001a\u0010\n\u001a\u00020\u0004*\u00060\u0006R\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0016\u0010\u000b\u001a\u00020\u0004*\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u000b\u001a\u00020\u0004*\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001b\u0010\u000b\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\t2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\f\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0016\u0010\f\u001a\u00020\u0004*\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\f\u001a\u00020\u0004*\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001b\u0010\f\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\t2\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0087\b¨\u0006\r"}, m3961d2 = {"attr", "Landroid/util/TypedValue;", "Landroid/app/Fragment;", "attribute", "", "Landroid/content/Context;", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "Landroid/view/View;", "Lorg/jetbrains/anko/AnkoContext;", TypedValues.Custom.S_COLOR, "colorAttr", "dimenAttr", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class ThemeKt {
    public static final TypedValue attr(Resources.Theme receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        TypedValue typedValue = new TypedValue();
        if (receiver.resolveAttribute(i, typedValue, true)) {
            return typedValue;
        }
        throw new IllegalArgumentException("Failed to resolve attribute: " + i);
    }

    public static final int color(Resources.Theme receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        TypedValue attr = attr(receiver, i);
        if (attr.type < 28 || attr.type > 31) {
            throw new IllegalArgumentException("Attribute value type is not color: " + i);
        }
        return attr.data;
    }

    public static final TypedValue attr(Context receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return attr(receiver.getTheme(), i);
    }

    public static final int dimenAttr(Context receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return TypedValue.complexToDimensionPixelSize(attr(receiver, i).data, receiver.getResources().getDisplayMetrics());
    }

    public static final int colorAttr(Context receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return color(receiver.getTheme(), i);
    }

    public static final int dimenAttr(AnkoContext<?> receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dimenAttr(receiver.getCtx(), i);
    }

    public static final int colorAttr(AnkoContext<?> receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return colorAttr(receiver.getCtx(), i);
    }

    public static final TypedValue attr(AnkoContext<?> receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return attr(receiver.getCtx(), i);
    }

    public static final int dimenAttr(View receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dimenAttr(receiver.getContext(), i);
    }

    public static final int colorAttr(View receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return colorAttr(receiver.getContext(), i);
    }

    public static final TypedValue attr(View receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return attr(receiver.getContext(), i);
    }

    public static final int dimenAttr(Fragment receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return dimenAttr(receiver.getActivity(), i);
    }

    public static final int colorAttr(Fragment receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return colorAttr(receiver.getActivity(), i);
    }

    public static final TypedValue attr(Fragment receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return attr(receiver.getActivity(), i);
    }
}
