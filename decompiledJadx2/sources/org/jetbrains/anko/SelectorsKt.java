package org.jetbrains.anko;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Selectors.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001ay\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2 \b\b\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000fH\u0086\b\u001ar\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00072\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u001e\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f\u001a}\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00112$\b\b\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2 \b\b\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000fH\u0086\b¨\u0006\u0012"}, m3961d2 = {"selector", "", "D", "Landroid/content/DialogInterface;", "Landroid/app/Fragment;", "factory", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/AlertBuilder;", "Lorg/jetbrains/anko/AlertBuilderFactory;", "title", "", "items", "", "onClick", "Lkotlin/Function3;", "", "Lorg/jetbrains/anko/AnkoContext;", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class SelectorsKt {
    public static /* bridge */ /* synthetic */ void selector$default(AnkoContext receiver, Function1 factory, CharSequence charSequence, List items, Function3 onClick, int i, Object obj) {
        if ((i & 2) != 0) {
            charSequence = (CharSequence) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getCtx(), factory, charSequence, (List<? extends CharSequence>) items, (Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit>) onClick);
    }

    public static final <D extends DialogInterface> void selector(AnkoContext<?> receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, CharSequence charSequence, List<? extends CharSequence> items, Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getCtx(), factory, charSequence, items, onClick);
    }

    public static /* bridge */ /* synthetic */ void selector$default(Fragment receiver, Function1 factory, CharSequence charSequence, List items, Function3 onClick, int i, Object obj) {
        if ((i & 2) != 0) {
            charSequence = (CharSequence) null;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getActivity(), factory, charSequence, (List<? extends CharSequence>) items, (Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit>) onClick);
    }

    public static final <D extends DialogInterface> void selector(Fragment receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, CharSequence charSequence, List<? extends CharSequence> items, Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        selector(receiver.getActivity(), factory, charSequence, items, onClick);
    }

    public static /* bridge */ /* synthetic */ void selector$default(Context context, Function1 function1, CharSequence charSequence, List list, Function3 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            charSequence = (CharSequence) null;
        }
        selector(context, function1, charSequence, (List<? extends CharSequence>) list, (Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit>) function3);
    }

    public static final <D extends DialogInterface> void selector(Context receiver, Function1<? super Context, ? extends AlertBuilder<? extends D>> factory, CharSequence charSequence, List<? extends CharSequence> items, Function3<? super DialogInterface, ? super CharSequence, ? super Integer, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        AlertBuilder<? extends D> invoke = factory.invoke(receiver);
        if (charSequence != null) {
            invoke.setTitle(charSequence);
        }
        invoke.items(items, onClick);
        invoke.show();
    }
}
