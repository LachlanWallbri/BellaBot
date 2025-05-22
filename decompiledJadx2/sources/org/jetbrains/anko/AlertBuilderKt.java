package org.jetbrains.anko;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AlertBuilder.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a6\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022#\b\b\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a'\u0010\t\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\f\u001a'\u0010\r\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\f\u001a6\u0010\u000e\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022#\b\b\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a6\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022#\b\b\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a6\u0010\u0010\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022#\b\b\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¨\u0006\u0011"}, m3961d2 = {"cancelButton", "", "Lorg/jetbrains/anko/AlertBuilder;", "handler", "Lkotlin/Function1;", "Landroid/content/DialogInterface;", "Lkotlin/ParameterName;", "name", "dialog", "customTitle", "dsl", "Landroid/view/ViewManager;", "Lkotlin/ExtensionFunctionType;", "customView", "noButton", "okButton", "yesButton", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AlertBuilderKt {
    public static final void customTitle(AlertBuilder<?> receiver, Function1<? super ViewManager, Unit> dsl) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(dsl, "dsl");
        Context ctx = receiver.getCtx();
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(ctx, ctx, false);
        dsl.invoke(ankoContextImpl);
        receiver.setCustomTitle(ankoContextImpl.getView());
    }

    public static final void customView(AlertBuilder<?> receiver, Function1<? super ViewManager, Unit> dsl) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(dsl, "dsl");
        Context ctx = receiver.getCtx();
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(ctx, ctx, false);
        dsl.invoke(ankoContextImpl);
        receiver.setCustomView(ankoContextImpl.getView());
    }

    public static final void okButton(AlertBuilder<?> receiver, Function1<? super DialogInterface, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver.positiveButton(R.string.ok, handler);
    }

    public static final void cancelButton(AlertBuilder<?> receiver, Function1<? super DialogInterface, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver.negativeButton(R.string.cancel, handler);
    }

    public static final void yesButton(AlertBuilder<?> receiver, Function1<? super DialogInterface, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver.positiveButton(R.string.yes, handler);
    }

    public static final void noButton(AlertBuilder<?> receiver, Function1<? super DialogInterface, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        receiver.negativeButton(R.string.no, handler);
    }
}
