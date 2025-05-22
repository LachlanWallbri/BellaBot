package org.jetbrains.anko;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.loc.C3898x;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AndroidAlertBuilder.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u00104\u001a\u00020\u0002H\u0016Ji\u00105\u001a\u000206\"\u0004\b\u0000\u001072\f\u00105\u001a\b\u0012\u0004\u0012\u0002H7082K\u00109\u001aG\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H7¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(?\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002060:H\u0016JN\u00105\u001a\u0002062\f\u00105\u001a\b\u0012\u0004\u0012\u00020%0826\u00109\u001a2\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002060AH\u0016J3\u0010B\u001a\u0002062\u0006\u0010C\u001a\u00020\u001a2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010B\u001a\u0002062\u0006\u0010F\u001a\u00020G2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010H\u001a\u0002062\u0006\u0010C\u001a\u00020\u001a2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010H\u001a\u0002062\u0006\u0010F\u001a\u00020G2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J\u001c\u0010I\u001a\u0002062\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002060EH\u0016JU\u0010K\u001a\u0002062K\u0010J\u001aG\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110M¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020 0:H\u0016J3\u0010O\u001a\u0002062\u0006\u0010C\u001a\u00020\u001a2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J3\u0010O\u001a\u0002062\u0006\u0010F\u001a\u00020G2!\u0010D\u001a\u001d\u0012\u0013\u0012\u00110;¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060EH\u0016J\b\u0010P\u001a\u00020\u0002H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00148W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u001a8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u00020 2\u0006\u0010\n\u001a\u00020 8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010&\u001a\u00020%2\u0006\u0010\n\u001a\u00020%8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u0010+\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u001a8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b,\u0010\u001d\"\u0004\b-\u0010\u001fR$\u0010.\u001a\u00020%2\u0006\u0010\n\u001a\u00020%8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b/\u0010(\"\u0004\b0\u0010*R$\u00101\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u001a8W@VX\u0096\u000e¢\u0006\f\u001a\u0004\b2\u0010\u001d\"\u0004\b3\u0010\u001f¨\u0006Q"}, m3961d2 = {"Lorg/jetbrains/anko/AndroidAlertBuilder;", "Lorg/jetbrains/anko/AlertBuilder;", "Landroid/app/AlertDialog;", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;)V", "builder", "Landroid/app/AlertDialog$Builder;", "getCtx", "()Landroid/content/Context;", ES6Iterator.VALUE_PROPERTY, "Landroid/view/View;", "customTitle", "getCustomTitle", "()Landroid/view/View;", "setCustomTitle", "(Landroid/view/View;)V", "customView", "getCustomView", "setCustomView", "Landroid/graphics/drawable/Drawable;", "icon", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "", "iconResource", "getIconResource", "()I", "setIconResource", "(I)V", "", "isCancelable", "()Z", "setCancelable", "(Z)V", "", "message", "getMessage", "()Ljava/lang/CharSequence;", "setMessage", "(Ljava/lang/CharSequence;)V", "messageResource", "getMessageResource", "setMessageResource", "title", "getTitle", "setTitle", "titleResource", "getTitleResource", "setTitleResource", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "items", "", ExifInterface.GPS_DIRECTION_TRUE, "", "onItemSelected", "Lkotlin/Function3;", "Landroid/content/DialogInterface;", "Lkotlin/ParameterName;", "name", "dialog", "item", "index", "Lkotlin/Function2;", "negativeButton", "buttonTextResource", "onClicked", "Lkotlin/Function1;", "buttonText", "", "neutralPressed", "onCancelled", "handler", "onKeyPressed", "keyCode", "Landroid/view/KeyEvent;", C3898x.f4338g, "positiveButton", "show", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AndroidAlertBuilder implements AlertBuilder<AlertDialog> {
    private final AlertDialog.Builder builder;
    private final Context ctx;

    public AndroidAlertBuilder(Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        this.ctx = ctx;
        this.builder = new AlertDialog.Builder(getCtx());
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public Context getCtx() {
        return this.ctx;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public CharSequence getTitle() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setTitle(CharSequence value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.builder.setTitle(value);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public int getTitleResource() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setTitleResource(int i) {
        this.builder.setTitle(i);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public CharSequence getMessage() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setMessage(CharSequence value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.builder.setMessage(value);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public int getMessageResource() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setMessageResource(int i) {
        this.builder.setMessage(i);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public Drawable getIcon() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setIcon(Drawable value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.builder.setIcon(value);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public int getIconResource() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setIconResource(int i) {
        this.builder.setIcon(i);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public View getCustomTitle() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setCustomTitle(View value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.builder.setCustomTitle(value);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public View getCustomView() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setCustomView(View value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.builder.setView(value);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    @Deprecated(level = DeprecationLevel.ERROR, message = AnkoInternals.NO_GETTER)
    public boolean isCancelable() {
        AnkoInternals.INSTANCE.noGetter();
        throw null;
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void setCancelable(boolean z) {
        this.builder.setCancelable(z);
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void onCancelled(final Function1<? super DialogInterface, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.builder.setOnCancelListener(handler == null ? null : new DialogInterface.OnCancelListener() { // from class: org.jetbrains.anko.AndroidAlertBuilderKt$sam$OnCancelListener$99168184
            @Override // android.content.DialogInterface.OnCancelListener
            public final /* synthetic */ void onCancel(DialogInterface dialogInterface) {
                Intrinsics.checkExpressionValueIsNotNull(Function1.this.invoke(dialogInterface), "invoke(...)");
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void onKeyPressed(final Function3<? super DialogInterface, ? super Integer, ? super KeyEvent, Boolean> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.builder.setOnKeyListener(handler == null ? null : new DialogInterface.OnKeyListener() { // from class: org.jetbrains.anko.AndroidAlertBuilderKt$sam$OnKeyListener$13459c89
            @Override // android.content.DialogInterface.OnKeyListener
            public final /* synthetic */ boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                Object invoke = Function3.this.invoke(dialogInterface, Integer.valueOf(i), keyEvent);
                Intrinsics.checkExpressionValueIsNotNull(invoke, "invoke(...)");
                return ((Boolean) invoke).booleanValue();
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void positiveButton(String buttonText, final Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(buttonText, "buttonText");
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$positiveButton$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function1.invoke(dialog);
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void positiveButton(int buttonTextResource, final Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setPositiveButton(buttonTextResource, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$positiveButton$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function1.invoke(dialog);
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void negativeButton(String buttonText, final Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(buttonText, "buttonText");
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNegativeButton(buttonText, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$negativeButton$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function1.invoke(dialog);
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void negativeButton(int buttonTextResource, final Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNegativeButton(buttonTextResource, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$negativeButton$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function1.invoke(dialog);
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void neutralPressed(String buttonText, final Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(buttonText, "buttonText");
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNeutralButton(buttonText, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$neutralPressed$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function1.invoke(dialog);
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void neutralPressed(int buttonTextResource, final Function1<? super DialogInterface, Unit> onClicked) {
        Intrinsics.checkParameterIsNotNull(onClicked, "onClicked");
        this.builder.setNeutralButton(buttonTextResource, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$neutralPressed$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function1.invoke(dialog);
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public void items(List<? extends CharSequence> items, final Function2<? super DialogInterface, ? super Integer, Unit> onItemSelected) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onItemSelected, "onItemSelected");
        AlertDialog.Builder builder = this.builder;
        int size = items.size();
        String[] strArr = new String[size];
        int i = size - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                strArr[i2] = items.get(i2).toString();
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        builder.setItems(strArr, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$items$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i3) {
                Function2 function2 = Function2.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function2.invoke(dialog, Integer.valueOf(i3));
            }
        });
    }

    @Override // org.jetbrains.anko.AlertBuilder
    public <T> void items(final List<? extends T> items, final Function3<? super DialogInterface, ? super T, ? super Integer, Unit> onItemSelected) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        Intrinsics.checkParameterIsNotNull(onItemSelected, "onItemSelected");
        AlertDialog.Builder builder = this.builder;
        int size = items.size();
        String[] strArr = new String[size];
        int i = size - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                strArr[i2] = String.valueOf(items.get(i2));
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        builder.setItems(strArr, new DialogInterface.OnClickListener() { // from class: org.jetbrains.anko.AndroidAlertBuilder$items$4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialog, int i3) {
                Function3 function3 = Function3.this;
                Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog");
                function3.invoke(dialog, items.get(i3), Integer.valueOf(i3));
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.jetbrains.anko.AlertBuilder
    public AlertDialog build() {
        AlertDialog create = this.builder.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "builder.create()");
        return create;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.jetbrains.anko.AlertBuilder
    public AlertDialog show() {
        AlertDialog show = this.builder.show();
        Intrinsics.checkExpressionValueIsNotNull(show, "builder.show()");
        return show;
    }
}
