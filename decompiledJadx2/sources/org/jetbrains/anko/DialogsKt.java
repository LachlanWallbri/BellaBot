package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Dialogs.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u007f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u000f\u001az\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00102!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\u0086\b\u001ad\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u001f\b\b\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\b\u000eH\u0086\b\u001az\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\u0011\u001a\u00020\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\n2!\b\u0002\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u0013\u001au\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00102!\b\u0002\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000e\u001a]\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u001d\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\b\u000e\u001a\u0083\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00142$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\u0086\b¢\u0006\u0002\u0010\u0015\u001a~\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00142$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u0006\u0010\t\u001a\u00020\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00102!\b\n\u0010\f\u001a\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006¢\u0006\u0002\b\u000eH\u0086\b\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00142$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\b2\u001f\b\b\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\b\u000eH\u0086\b*4\u0010\u0016\u001a\u0004\b\u0000\u0010\u0002\"\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u00062\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0006¨\u0006\u0017"}, m3961d2 = {"alert", "Lorg/jetbrains/anko/AlertBuilder;", "D", "Landroid/content/DialogInterface;", "Landroid/app/Fragment;", "factory", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/AlertBuilderFactory;", "message", "", "title", "init", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/app/Fragment;Lkotlin/jvm/functions/Function1;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "", "messageResource", "titleResource", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;Lkotlin/jvm/functions/Function1;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/anko/AlertBuilder;", "AlertBuilderFactory", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class DialogsKt {
    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(AnkoContext receiver, Function1 factory, String message, String str, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getCtx(), factory, message, str, function1);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(AnkoContext<?> receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, String message, String str, Function1<? super AlertBuilder<? extends D>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getCtx(), factory, message, str, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Fragment receiver, Function1 factory, String message, String str, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            str = (String) null;
        }
        if ((i & 8) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getActivity(), factory, message, str, function1);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(Fragment receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, String message, String str, Function1<? super AlertBuilder<? extends D>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return alert(receiver.getActivity(), factory, message, str, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Context context, Function1 function1, String str, String str2, Function1 function12, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            function12 = (Function1) null;
        }
        return alert(context, function1, str, str2, function12);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(Context receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, String message, String str, Function1<? super AlertBuilder<? extends D>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(message, "message");
        AlertBuilder<? extends D> invoke = factory.invoke(receiver);
        if (str != null) {
            invoke.setTitle(str);
        }
        invoke.setMessage(message);
        if (function1 != null) {
            function1.invoke(invoke);
        }
        return invoke;
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(AnkoContext receiver, Function1 factory, int i, Integer num, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            num = (Integer) null;
        }
        if ((i2 & 8) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        return alert(receiver.getCtx(), factory, i, num, function1);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(AnkoContext<?> receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, int i, Integer num, Function1<? super AlertBuilder<? extends D>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        return alert(receiver.getCtx(), factory, i, num, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Fragment receiver, Function1 factory, int i, Integer num, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            num = (Integer) null;
        }
        if ((i2 & 8) != 0) {
            function1 = (Function1) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        return alert(receiver.getActivity(), factory, i, num, function1);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(Fragment receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, int i, Integer num, Function1<? super AlertBuilder<? extends D>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        return alert(receiver.getActivity(), factory, i, num, function1);
    }

    public static /* bridge */ /* synthetic */ AlertBuilder alert$default(Context context, Function1 function1, int i, Integer num, Function1 function12, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            num = (Integer) null;
        }
        if ((i2 & 8) != 0) {
            function12 = (Function1) null;
        }
        return alert(context, function1, i, num, function12);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(Context receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, int i, Integer num, Function1<? super AlertBuilder<? extends D>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        AlertBuilder<? extends D> invoke = factory.invoke(receiver);
        if (num != null) {
            invoke.setTitleResource(num.intValue());
        }
        invoke.setMessageResource(i);
        if (function1 != null) {
            function1.invoke(invoke);
        }
        return invoke;
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(AnkoContext<?> receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert(receiver.getCtx(), factory, init);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(Fragment receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        return alert(receiver.getActivity(), factory, init);
    }

    public static final <D extends DialogInterface> AlertBuilder<D> alert(Context receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, Function1<? super AlertBuilder<? extends D>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AlertBuilder<? extends D> invoke = factory.invoke(receiver);
        init.invoke(invoke);
        return invoke;
    }
}
