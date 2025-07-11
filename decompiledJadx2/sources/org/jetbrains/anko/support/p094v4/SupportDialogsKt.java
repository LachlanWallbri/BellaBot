package org.jetbrains.anko.support.p094v4;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AlertBuilder;
import org.jetbrains.anko.AndroidDialogsKt;
import org.jetbrains.anko.AndroidSelectorsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SupportDialogs.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\u0086\b¢\u0006\u0002\u0010\u000b\u001aJ\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\f0\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\r2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\r2!\b\n\u0010\u0007\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\u0086\b\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u001f\b\b\u0010\u0007\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086\b\u001aG\u0010\u000e\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\u0087\b¢\u0006\u0002\u0010\u0010\u001aB\u0010\u000e\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\r2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\u0087\b\u001a\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0086\b\u001a\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\u0086\b\u001aG\u0010\u0016\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\u0087\b¢\u0006\u0002\u0010\u0010\u001aB\u0010\u0016\u001a\u00020\u000f*\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\r2\u001b\b\n\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\b\nH\u0087\b\u001aC\u0010\u0017\u001a\u00020\t*\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00192\u001a\b\b\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u001bH\u0086\b\u001a\u0015\u0010\u001c\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0086\b\u001a\u0015\u0010\u001c\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\u0086\b¨\u0006\u001d"}, m3961d2 = {"alert", "Lorg/jetbrains/anko/AlertBuilder;", "Landroid/content/DialogInterface;", "Landroidx/fragment/app/Fragment;", "message", "", "title", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/support/v4/app/Fragment;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Landroid/app/AlertDialog;", "", "indeterminateProgressDialog", "Landroid/app/ProgressDialog;", "(Landroid/support/v4/app/Fragment;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Landroid/app/ProgressDialog;", "longToast", "Landroid/widget/Toast;", "text", "", "textResource", "progressDialog", "selector", "items", "", "onClick", "Lkotlin/Function2;", "toast", "supportV4-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportDialogsKt {
    public static final Toast toast(Fragment receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Toast makeText = Toast.makeText(requireActivity, i, 0);
        makeText.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast toast(Fragment receiver$0, CharSequence text) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(text, "text");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Toast makeText = Toast.makeText(requireActivity, text, 0);
        makeText.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(Fragment receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Toast makeText = Toast.makeText(requireActivity, i, 1);
        makeText.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static final Toast longToast(Fragment receiver$0, CharSequence text) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(text, "text");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Toast makeText = Toast.makeText(requireActivity, text, 1);
        makeText.show();
        Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
        return makeText;
    }

    public static /* synthetic */ void selector$default(Fragment receiver$0, CharSequence charSequence, List items, Function2 onClick, int i, Object obj) {
        if ((i & 1) != 0) {
            charSequence = (CharSequence) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        AndroidSelectorsKt.selector(requireActivity, charSequence, (List<? extends CharSequence>) items, (Function2<? super DialogInterface, ? super Integer, Unit>) onClick);
    }

    public static final void selector(Fragment receiver$0, CharSequence charSequence, List<? extends CharSequence> items, Function2<? super DialogInterface, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        AndroidSelectorsKt.selector(requireActivity, charSequence, items, onClick);
    }

    public static /* synthetic */ AlertBuilder alert$default(Fragment receiver$0, String message, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(message, "message");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.alert(requireActivity, message, str, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) function1);
    }

    public static final AlertBuilder<AlertDialog> alert(Fragment receiver$0, String message, String str, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(message, "message");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.alert(requireActivity, message, str, function1);
    }

    public static /* synthetic */ AlertBuilder alert$default(Fragment receiver$0, int i, Integer num, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = (Integer) null;
        }
        if ((i2 & 4) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.alert(requireActivity, i, num, (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) function1);
    }

    public static final AlertBuilder<DialogInterface> alert(Fragment receiver$0, int i, Integer num, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.alert(requireActivity, i, num, function1);
    }

    public static final AlertBuilder<DialogInterface> alert(Fragment receiver$0, Function1<? super AlertBuilder<? extends DialogInterface>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.alert(requireActivity, init);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog progressDialog$default(Fragment receiver$0, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.progressDialog(requireActivity, str, str2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog progressDialog(Fragment receiver$0, String str, String str2, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.progressDialog(requireActivity, str, str2, function1);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Fragment receiver$0, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.indeterminateProgressDialog(requireActivity, str, str2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog indeterminateProgressDialog(Fragment receiver$0, String str, String str2, Function1<? super ProgressDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return AndroidDialogsKt.indeterminateProgressDialog(requireActivity, str, str2, function1);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog progressDialog$default(Fragment receiver$0, Integer num, Integer num2, Function1 function1, int i, Object obj) {
        String str;
        String str2 = null;
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        FragmentActivity fragmentActivity = requireActivity;
        if (num != null) {
            str = receiver$0.requireActivity().getString(num.intValue());
        } else {
            str = null;
        }
        String str3 = str;
        if (num2 != null) {
            str2 = receiver$0.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.progressDialog(fragmentActivity, str3, str2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog progressDialog(Fragment receiver$0, Integer num, Integer num2, Function1<? super ProgressDialog, Unit> function1) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        FragmentActivity fragmentActivity = requireActivity;
        String str2 = null;
        if (num != null) {
            str = receiver$0.requireActivity().getString(num.intValue());
        } else {
            str = null;
        }
        String str3 = str;
        if (num2 != null) {
            str2 = receiver$0.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.progressDialog(fragmentActivity, str3, str2, function1);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static /* synthetic */ ProgressDialog indeterminateProgressDialog$default(Fragment receiver$0, Integer num, Integer num2, Function1 function1, int i, Object obj) {
        String str;
        String str2 = null;
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        FragmentActivity fragmentActivity = requireActivity;
        if (num != null) {
            str = receiver$0.requireActivity().getString(num.intValue());
        } else {
            str = null;
        }
        String str3 = str;
        if (num2 != null) {
            str2 = receiver$0.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.indeterminateProgressDialog(fragmentActivity, str3, str2, (Function1<? super ProgressDialog, Unit>) function1);
    }

    @Deprecated(message = "Android progress dialogs are deprecated")
    public static final ProgressDialog indeterminateProgressDialog(Fragment receiver$0, Integer num, Integer num2, Function1<? super ProgressDialog, Unit> function1) {
        String str;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        FragmentActivity fragmentActivity = requireActivity;
        String str2 = null;
        if (num != null) {
            str = receiver$0.requireActivity().getString(num.intValue());
        } else {
            str = null;
        }
        String str3 = str;
        if (num2 != null) {
            str2 = receiver$0.requireActivity().getString(num2.intValue());
        }
        return AndroidDialogsKt.indeterminateProgressDialog(fragmentActivity, str3, str2, function1);
    }
}
