package org.jetbrains.anko;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: CustomViews.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a5\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a5\u0010\u000e\u001a\u00020\u000f*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a5\u0010\u000e\u001a\u00020\u000f*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u000f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\u0014\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u0015\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\u0016\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u0017\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\u0019\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u001a\u001a$\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\u001b\u001aB\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u0007H\u0011¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b¢\u0006\u0002\u0010\u001c\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a5\u0010\u001d\u001a\u00020\u001e*\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u001f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a5\u0010\u001d\u001a\u00020\u001e*\u00020\f2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u001f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a5\u0010\u001d\u001a\u00020\u001e*\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\t\u0012\u00070\u001f¢\u0006\u0002\b\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\b¨\u0006 "}, m3961d2 = {"editText", "Landroid/widget/EditText;", "Landroid/app/Activity;", "constraints", "Lorg/jetbrains/anko/InputConstraints;", "theme", "", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/AnkoViewDslMarker;", "", "Lkotlin/ExtensionFunctionType;", "Landroid/content/Context;", "Landroid/view/ViewManager;", "horizontalProgressBar", "Landroid/widget/ProgressBar;", "include", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "layoutId", "(Landroid/app/Activity;I)Landroid/view/View;", "(Landroid/app/Activity;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/content/Context;I)Landroid/view/View;", "(Landroid/content/Context;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;I)Landroid/view/View;", "(Landroid/view/ViewGroup;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "(Landroid/view/ViewManager;I)Landroid/view/View;", "(Landroid/view/ViewManager;ILkotlin/jvm/functions/Function1;)Landroid/view/View;", "verticalLayout", "Landroid/widget/LinearLayout;", "Lorg/jetbrains/anko/_LinearLayout;", "platform-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class CustomViewsKt {
    public static /* synthetic */ LinearLayout verticalLayout$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ LinearLayout verticalLayout$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final LinearLayout verticalLayout(ViewManager receiver$0, int i, Function1<? super _LinearLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static /* synthetic */ LinearLayout verticalLayout$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ LinearLayout verticalLayout$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final LinearLayout verticalLayout(Context receiver$0, int i, Function1<? super _LinearLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ LinearLayout verticalLayout$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ LinearLayout verticalLayout$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final LinearLayout verticalLayout(Activity receiver$0, int i, Function1<? super _LinearLayout, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static /* synthetic */ EditText editText$default(ViewManager receiver$0, InputConstraints constraints, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        EditText editText = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static /* synthetic */ EditText editText$default(ViewManager receiver$0, InputConstraints constraints, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(init, "init");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        EditText editText = invoke;
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static final EditText editText(ViewManager receiver$0, InputConstraints constraints, int i, Function1<? super EditText, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(init, "init");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        EditText editText = invoke;
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static /* synthetic */ EditText editText$default(Context receiver$0, InputConstraints constraints, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        EditText editText = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static /* synthetic */ EditText editText$default(Context receiver$0, InputConstraints constraints, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(init, "init");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        EditText editText = invoke;
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static final EditText editText(Context receiver$0, InputConstraints constraints, int i, Function1<? super EditText, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(init, "init");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        EditText editText = invoke;
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static /* synthetic */ EditText editText$default(Activity receiver$0, InputConstraints constraints, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        EditText editText = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static /* synthetic */ EditText editText$default(Activity receiver$0, InputConstraints constraints, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(init, "init");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        EditText editText = invoke;
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static final EditText editText(Activity receiver$0, InputConstraints constraints, int i, Function1<? super EditText, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        Intrinsics.checkParameterIsNotNull(init, "init");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        EditText editText = invoke;
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static /* synthetic */ ProgressBar horizontalProgressBar$default(ViewManager receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        ProgressBar progressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return progressBar;
    }

    public static /* synthetic */ ProgressBar horizontalProgressBar$default(ViewManager receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return invoke;
    }

    public static final ProgressBar horizontalProgressBar(ViewManager receiver$0, int i, Function1<? super ProgressBar, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return invoke;
    }

    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Context receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        ProgressBar progressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return progressBar;
    }

    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Context receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final ProgressBar horizontalProgressBar(Context receiver$0, int i, Function1<? super ProgressBar, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Activity receiver$0, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        ProgressBar progressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return progressBar;
    }

    public static /* synthetic */ ProgressBar horizontalProgressBar$default(Activity receiver$0, int i, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final ProgressBar horizontalProgressBar(Activity receiver$0, int i, Function1<? super ProgressBar, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        init.invoke(invoke);
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final <T extends View> T include(ViewGroup receiver$0, int i, Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        ViewGroup viewGroup = receiver$0;
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewGroup), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, receiver$0, false);
            if (inflate == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            init.invoke(inflate);
            AnkoInternals.INSTANCE.addView((ViewManager) viewGroup, (ViewGroup) inflate);
            return inflate;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public static final LinearLayout verticalLayout(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        AnkoInternals.INSTANCE.addView(receiver$0, invoke);
        return invoke;
    }

    public static final LinearLayout verticalLayout(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return invoke;
    }

    public static final LinearLayout verticalLayout(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        _LinearLayout invoke = C$$Anko$Factories$CustomViews.INSTANCE.getVERTICAL_LAYOUT_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return invoke;
    }

    public static final EditText editText(ViewManager receiver$0, InputConstraints constraints, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        EditText editText = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static final EditText editText(Context receiver$0, InputConstraints constraints, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        EditText editText = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static final EditText editText(Activity receiver$0, InputConstraints constraints, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        EditText invoke = C$$Anko$Factories$CustomViews.INSTANCE.getEDIT_TEXT().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        EditText editText = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        editText.setInputType(constraints.getValue());
        return editText;
    }

    public static final ProgressBar horizontalProgressBar(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), i));
        ProgressBar progressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) invoke);
        return progressBar;
    }

    public static final ProgressBar horizontalProgressBar(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        ProgressBar progressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Context) invoke);
        return progressBar;
    }

    public static final ProgressBar horizontalProgressBar(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ProgressBar invoke = C$$Anko$Factories$CustomViews.INSTANCE.getHORIZONTAL_PROGRESS_BAR_FACTORY().invoke(AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, i));
        ProgressBar progressBar = invoke;
        AnkoInternals.INSTANCE.addView(receiver$0, (Activity) invoke);
        return progressBar;
    }

    public static final <T extends View> T include(ViewManager receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            T t = (T) ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) t);
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public static final <T extends View> T include(ViewManager receiver$0, int i, Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(receiver$0), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
            if (inflate == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            init.invoke(inflate);
            AnkoInternals.INSTANCE.addView(receiver$0, (ViewManager) inflate);
            return inflate;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public static final <T extends View> T include(ViewGroup receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ViewGroup viewGroup = receiver$0;
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(AnkoInternals.INSTANCE.getContext(viewGroup), 0).getSystemService("layout_inflater");
        if (systemService != null) {
            T t = (T) ((LayoutInflater) systemService).inflate(i, receiver$0, false);
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            AnkoInternals.INSTANCE.addView((ViewManager) viewGroup, (ViewGroup) t);
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public static final <T extends View> T include(Context receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            T t = (T) ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            AnkoInternals.INSTANCE.addView(receiver$0, (Context) t);
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public static final <T extends View> T include(Context receiver$0, int i, Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
            if (inflate == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            init.invoke(inflate);
            AnkoInternals.INSTANCE.addView(receiver$0, (Context) inflate);
            return inflate;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public static final <T extends View> T include(Activity receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            T t = (T) ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            AnkoInternals.INSTANCE.addView(receiver$0, (Activity) t);
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public static final <T extends View> T include(Activity receiver$0, int i, Function1<? super T, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        Object systemService = AnkoInternals.INSTANCE.wrapContextIfNeeded(receiver$0, 0).getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
            if (inflate == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            init.invoke(inflate);
            AnkoInternals.INSTANCE.addView(receiver$0, (Activity) inflate);
            return inflate;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }
}
