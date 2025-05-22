package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Toasts.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0019\u0010\b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u0005H\u0086\b¨\u0006\t"}, m3961d2 = {"longToast", "Landroid/widget/Toast;", "Landroid/app/Fragment;", "message", "", "", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "toast", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class ToastsKt {
    public static final Toast toast(AnkoContext<?> receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver.getCtx(), i, 0);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast toast(Fragment receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver.getActivity(), i, 0);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast toast(Context receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver, i, 0);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast toast(AnkoContext<?> receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver.getCtx(), message, 0);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast toast(Fragment receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver.getActivity(), message, 0);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast toast(Context receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver, message, 0);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast longToast(AnkoContext<?> receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver.getCtx(), i, 1);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast longToast(Fragment receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver.getActivity(), i, 1);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast longToast(Context receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Toast makeText = Toast.makeText(receiver, i, 1);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast longToast(AnkoContext<?> receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver.getCtx(), message, 1);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast longToast(Fragment receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver.getActivity(), message, 1);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }

    public static final Toast longToast(Context receiver, CharSequence message) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Toast makeText = Toast.makeText(receiver, message, 1);
        Toast toast = makeText;
        toast.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return toast;
    }
}
