package org.jetbrains.anko;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewManager;
import android.widget.ListAdapter;
import com.loc.C3898x;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AlertDialogBuilder.kt */
@Deprecated(message = "Use AlertBuilder class instead.")
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J9\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ1\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u001f2!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ!\u0010 \u001a\u00020\u00142\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0010\u0010#\u001a\u00020\u00142\b\b\u0002\u0010#\u001a\u00020$J\b\u0010%\u001a\u00020\u0014H\u0002J\u000e\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(J\u001f\u0010&\u001a\u00020\u00142\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u000e\u0010+\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(J\u001f\u0010+\u001a\u00020\u00142\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0006\u0010,\u001a\u00020\u0014J\u000e\u0010-\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.J\u000e\u0010-\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u001bJ<\u0010/\u001a\u00020\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u000201002!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\u00102J1\u0010/\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u001b2!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ7\u0010/\u001a\u00020\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u000201042!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00140\u001aJ\u000e\u00105\u001a\u00020\u00142\u0006\u00105\u001a\u000201J\u000e\u00105\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u001bJ)\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u0002012\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J)\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u001b2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J)\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u0002012\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J+\u00108\u001a\u00020\u00142\b\b\u0002\u00109\u001a\u00020\u001b2\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J!\u0010:\u001a\u00020\u00142\u0019\b\u0002\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u001f\u0010;\u001a\u00020\u00142\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0014\u0010<\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140=J>\u0010>\u001a\u00020\u001426\u0010\u0019\u001a2\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110A¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(B\u0012\u0004\u0012\u00020$0?J'\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u0002012\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J'\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u00020\u001b2\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"J\u0006\u0010E\u001a\u00020\u0000J\u000e\u0010F\u001a\u00020\u00142\u0006\u0010F\u001a\u000201J\u000e\u0010F\u001a\u00020\u00142\u0006\u0010F\u001a\u00020\u001bJ\u001f\u0010G\u001a\u00020\u00142\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00140\u001a¢\u0006\u0002\b\"R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006H"}, m3961d2 = {"Lorg/jetbrains/anko/AlertDialogBuilder;", "", "ankoContext", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;)V", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "builder", "Landroid/app/AlertDialog$Builder;", "getCtx", "()Landroid/content/Context;", "<set-?>", "Landroid/app/AlertDialog;", "dialog", "getDialog", "()Landroid/app/AlertDialog;", "setDialog", "(Landroid/app/AlertDialog;)V", "adapter", "", "cursor", "Landroid/database/Cursor;", "labelColumn", "", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "which", "Landroid/widget/ListAdapter;", "cancelButton", "Landroid/content/DialogInterface;", "Lkotlin/ExtensionFunctionType;", "cancellable", "", "checkBuilder", "customTitle", "view", "Landroid/view/View;", "dsl", "Landroid/view/ViewManager;", "customView", "dismiss", "icon", "Landroid/graphics/drawable/Drawable;", "items", "", "", "([Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)V", "itemsId", "", "message", "negativeButton", "negativeText", "neutralButton", "neutralText", "noButton", "okButton", "onCancel", "Lkotlin/Function0;", "onKey", "Lkotlin/Function2;", "keyCode", "Landroid/view/KeyEvent;", C3898x.f4338g, "positiveButton", "positiveText", "show", "title", "yesButton", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AlertDialogBuilder {
    private AlertDialog.Builder builder;
    private final Context ctx;
    private AlertDialog dialog;

    public AlertDialogBuilder(Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        this.ctx = ctx;
        this.builder = new AlertDialog.Builder(this.ctx);
    }

    public final Context getCtx() {
        return this.ctx;
    }

    private final void setDialog(AlertDialog alertDialog) {
        this.dialog = alertDialog;
    }

    public final AlertDialog getDialog() {
        return this.dialog;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AlertDialogBuilder(AnkoContext<?> ankoContext) {
        this(ankoContext.getCtx());
        Intrinsics.checkParameterIsNotNull(ankoContext, "ankoContext");
    }

    public final void dismiss() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private final void checkBuilder() {
        if (this.builder == null) {
            throw new IllegalStateException("show() was already called for this AlertDialogBuilder");
        }
    }

    public final AlertDialogBuilder show() {
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        AlertDialog create = builder.create();
        this.dialog = create;
        this.builder = (AlertDialog.Builder) null;
        if (create == null) {
            Intrinsics.throwNpe();
        }
        create.show();
        return this;
    }

    public final void title(CharSequence title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setTitle(title);
    }

    public final void title(int title) {
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setTitle(title);
    }

    public final void message(CharSequence message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setMessage(message);
    }

    public final void message(int message) {
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setMessage(message);
    }

    public final void icon(int icon) {
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setIcon(icon);
    }

    public final void icon(Drawable icon) {
        Intrinsics.checkParameterIsNotNull(icon, "icon");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setIcon(icon);
    }

    public final void customTitle(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCustomTitle(view);
    }

    public final void customTitle(Function1<? super ViewManager, Unit> dsl) {
        Intrinsics.checkParameterIsNotNull(dsl, "dsl");
        checkBuilder();
        Context context = this.ctx;
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(context, context, false);
        dsl.invoke(ankoContextImpl);
        View view = ankoContextImpl.getView();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCustomTitle(view);
    }

    public final void customView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setView(view);
    }

    public final void customView(Function1<? super ViewManager, Unit> dsl) {
        Intrinsics.checkParameterIsNotNull(dsl, "dsl");
        checkBuilder();
        Context context = this.ctx;
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(context, context, false);
        dsl.invoke(ankoContextImpl);
        View view = ankoContextImpl.getView();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setView(view);
    }

    public static /* bridge */ /* synthetic */ void cancellable$default(AlertDialogBuilder alertDialogBuilder, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        alertDialogBuilder.cancellable(z);
    }

    public final void cancellable(boolean cancellable) {
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCancelable(cancellable);
    }

    public final void onCancel(final Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$onCancel$1
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                Function0.this.invoke();
            }
        });
    }

    public final void onKey(final Function2<? super Integer, ? super KeyEvent, Boolean> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$onKey$1
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent event) {
                Function2 function2 = Function2.this;
                Integer valueOf = Integer.valueOf(i);
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                return ((Boolean) function2.invoke(valueOf, event)).booleanValue();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* bridge */ /* synthetic */ void neutralButton$default(AlertDialogBuilder alertDialogBuilder, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = R.string.ok;
        }
        if ((i2 & 2) != 0) {
            function1 = new Function1<DialogInterface, Unit>() { // from class: org.jetbrains.anko.AlertDialogBuilder$neutralButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                    invoke2(dialogInterface);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DialogInterface receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.dismiss();
                }
            };
        }
        alertDialogBuilder.neutralButton(i, (Function1<? super DialogInterface, Unit>) function1);
    }

    public final void neutralButton(int neutralText, Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String string = this.ctx.getString(neutralText);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(neutralText)");
        neutralButton(string, callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* bridge */ /* synthetic */ void neutralButton$default(AlertDialogBuilder alertDialogBuilder, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<DialogInterface, Unit>() { // from class: org.jetbrains.anko.AlertDialogBuilder$neutralButton$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                    invoke2(dialogInterface);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DialogInterface receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.dismiss();
                }
            };
        }
        alertDialogBuilder.neutralButton(charSequence, (Function1<? super DialogInterface, Unit>) function1);
    }

    public final void neutralButton(CharSequence neutralText, final Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(neutralText, "neutralText");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setNeutralButton(neutralText, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$neutralButton$3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Function1.this.invoke(dialogInterface);
            }
        });
    }

    public final void positiveButton(int positiveText, Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String string = this.ctx.getString(positiveText);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(positiveText)");
        positiveButton(string, callback);
    }

    public final void okButton(Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String string = this.ctx.getString(R.string.ok);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.ok)");
        positiveButton(string, callback);
    }

    public final void yesButton(Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String string = this.ctx.getString(R.string.yes);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.yes)");
        positiveButton(string, callback);
    }

    public final void positiveButton(CharSequence positiveText, final Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(positiveText, "positiveText");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$positiveButton$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Function1.this.invoke(dialogInterface);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* bridge */ /* synthetic */ void negativeButton$default(AlertDialogBuilder alertDialogBuilder, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = new Function1<DialogInterface, Unit>() { // from class: org.jetbrains.anko.AlertDialogBuilder$negativeButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                    invoke2(dialogInterface);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DialogInterface receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.dismiss();
                }
            };
        }
        alertDialogBuilder.negativeButton(i, (Function1<? super DialogInterface, Unit>) function1);
    }

    public final void negativeButton(int negativeText, Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String string = this.ctx.getString(negativeText);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(negativeText)");
        negativeButton(string, callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* bridge */ /* synthetic */ void cancelButton$default(AlertDialogBuilder alertDialogBuilder, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<DialogInterface, Unit>() { // from class: org.jetbrains.anko.AlertDialogBuilder$cancelButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                    invoke2(dialogInterface);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DialogInterface receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.dismiss();
                }
            };
        }
        alertDialogBuilder.cancelButton(function1);
    }

    public final void cancelButton(Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String string = this.ctx.getString(R.string.cancel);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.cancel)");
        negativeButton(string, callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* bridge */ /* synthetic */ void noButton$default(AlertDialogBuilder alertDialogBuilder, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<DialogInterface, Unit>() { // from class: org.jetbrains.anko.AlertDialogBuilder$noButton$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                    invoke2(dialogInterface);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DialogInterface receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.dismiss();
                }
            };
        }
        alertDialogBuilder.noButton(function1);
    }

    public final void noButton(Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        String string = this.ctx.getString(R.string.no);
        Intrinsics.checkExpressionValueIsNotNull(string, "ctx.getString(R.string.no)");
        negativeButton(string, callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* bridge */ /* synthetic */ void negativeButton$default(AlertDialogBuilder alertDialogBuilder, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<DialogInterface, Unit>() { // from class: org.jetbrains.anko.AlertDialogBuilder$negativeButton$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                    invoke2(dialogInterface);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DialogInterface receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.dismiss();
                }
            };
        }
        alertDialogBuilder.negativeButton(charSequence, (Function1<? super DialogInterface, Unit>) function1);
    }

    public final void negativeButton(CharSequence negativeText, final Function1<? super DialogInterface, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(negativeText, "negativeText");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$negativeButton$3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Function1.this.invoke(dialogInterface);
            }
        });
    }

    public final void items(int itemsId, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Resources resources = this.ctx.getResources();
        if (resources == null) {
            Intrinsics.throwNpe();
        }
        CharSequence[] textArray = resources.getTextArray(itemsId);
        Intrinsics.checkExpressionValueIsNotNull(textArray, "ctx.resources!!.getTextArray(itemsId)");
        items(textArray, callback);
    }

    public final void items(List<? extends CharSequence> items, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        List<? extends CharSequence> list = items;
        if (list != null) {
            List<? extends CharSequence> list2 = list;
            Object[] array = list2.toArray(new CharSequence[list2.size()]);
            if (array != null) {
                items((CharSequence[]) array, callback);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }

    public final void items(CharSequence[] items, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setItems(items, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$items$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Function1.this.invoke(Integer.valueOf(i));
            }
        });
    }

    public final void adapter(ListAdapter adapter, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$adapter$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Function1.this.invoke(Integer.valueOf(i));
            }
        });
    }

    public final void adapter(Cursor cursor, String labelColumn, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        Intrinsics.checkParameterIsNotNull(labelColumn, "labelColumn");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        checkBuilder();
        AlertDialog.Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwNpe();
        }
        builder.setCursor(cursor, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AlertDialogBuilder$adapter$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Function1.this.invoke(Integer.valueOf(i));
            }
        }, labelColumn);
    }
}
