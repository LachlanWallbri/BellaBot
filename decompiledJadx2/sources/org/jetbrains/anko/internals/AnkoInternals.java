package org.jetbrains.anko.internals;

import android.app.Activity;
import android.app.Service;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import androidx.exifinterface.media.ExifInterface;
import com.amitshekhar.utils.DataType;
import com.loc.C3898x;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Locale;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.text.StringsKt;
import org.jetbrains.anko.AnkoContext;
import org.jetbrains.anko.AnkoContextImpl;
import org.jetbrains.anko.AnkoException;
import org.jetbrains.anko.Orientation;
import org.jetbrains.anko.ScreenSize;
import org.jetbrains.anko.UiMode;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Internals.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002UVB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\u0007¢\u0006\u0002\u0010\fJ%\u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u0002H\u0007¢\u0006\u0002\u0010\u000fJ%\u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u0002H\u0007¢\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\b2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0016JI\u0010\u0017\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u00072\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u0010\u001eJ3\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00182\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0003¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J-\u0010#\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00070\u001aH\u0007¢\u0006\u0002\u0010%JC\u0010&\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u0010'JK\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\n2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u001a2\u0006\u0010*\u001a\u00020+2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u0010,JE\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u00101JC\u00102\u001a\u0002032\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001d0\u001cH\u0007¢\u0006\u0002\u00104J\u0006\u00105\u001a\u000206J\u0089\u0001\u00107\u001a\u0002032\u0006\u0010\r\u001a\u00020\u000e2\b\u00108\u001a\u0004\u0018\u0001092\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010\u00042\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010?\u001a\u0004\u0018\u0001032\b\u0010@\u001a\u0004\u0018\u00010+2\b\u0010A\u001a\u0004\u0018\u00010+2\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u0001032\b\u0010E\u001a\u0004\u0018\u0001032\b\u0010F\u001a\u0004\u0018\u00010+H\u0007¢\u0006\u0002\u0010GJ0\u0010H\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010I\u001a\u00020J2\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u0002H\u00070\u0016H\u0087\b¢\u0006\u0002\u0010LJ\u0016\u0010M\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010N\u001a\u00020+JO\u0010O\u001a\b\u0012\u0004\u0012\u0002H\u00070P\"\u0004\b\u0000\u0010\u0007*\u0002H\u00072\u0006\u0010\r\u001a\u00020\u000e2\u001d\u0010Q\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070P\u0012\u0004\u0012\u00020\u00060\u0016¢\u0006\u0002\bR2\b\b\u0002\u0010S\u001a\u000203H\u0086\b¢\u0006\u0002\u0010TR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006W"}, m3961d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals;", "", "()V", "NO_GETTER", "", "addView", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "activity", "Landroid/app/Activity;", "view", "(Landroid/app/Activity;Landroid/view/View;)V", "ctx", "Landroid/content/Context;", "(Landroid/content/Context;Landroid/view/View;)V", "manager", "Landroid/view/ViewManager;", "(Landroid/view/ViewManager;Landroid/view/View;)V", "applyRecursively", "v", "style", "Lkotlin/Function1;", "createIntent", "Landroid/content/Intent;", "clazz", "Ljava/lang/Class;", "params", "", "Lkotlin/Pair;", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)Landroid/content/Intent;", "fillIntentArguments", "intent", "(Landroid/content/Intent;[Lkotlin/Pair;)V", "getContext", "initiateView", "viewClass", "(Landroid/content/Context;Ljava/lang/Class;)Landroid/view/View;", "internalStartActivity", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)V", "internalStartActivityForResult", "act", "requestCode", "", "(Landroid/app/Activity;Ljava/lang/Class;I[Lkotlin/Pair;)V", "internalStartService", "Landroid/content/ComponentName;", "service", "Landroid/app/Service;", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)Landroid/content/ComponentName;", "internalStopService", "", "(Landroid/content/Context;Ljava/lang/Class;[Lkotlin/Pair;)Z", "noGetter", "", "testConfiguration", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "language", "orientation", "Lorg/jetbrains/anko/Orientation;", DataType.LONG, "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "(Landroid/content/Context;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;)Z", "useCursor", "cursor", "Landroid/database/Cursor;", C3898x.f4339h, "(Landroid/database/Cursor;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "wrapContextIfNeeded", "theme", "createAnkoContext", "Lorg/jetbrains/anko/AnkoContext;", "init", "Lkotlin/ExtensionFunctionType;", "setContentView", "(Ljava/lang/Object;Landroid/content/Context;Lkotlin/jvm/functions/Function1;Z)Lorg/jetbrains/anko/AnkoContext;", "AnkoContextThemeWrapper", "InternalConfiguration", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AnkoInternals {
    public static final AnkoInternals INSTANCE = null;
    public static final String NO_GETTER = "Property does not have a getter";

    static {
        new AnkoInternals();
    }

    private AnkoInternals() {
        INSTANCE = this;
    }

    public final Void noGetter() {
        throw new AnkoException(NO_GETTER);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: Internals.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals$AnkoContextThemeWrapper;", "Landroid/view/ContextThemeWrapper;", "base", "Landroid/content/Context;", "theme", "", "(Landroid/content/Context;I)V", "getTheme", "()I", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    private static final class AnkoContextThemeWrapper extends ContextThemeWrapper {
        private final int theme;

        public AnkoContextThemeWrapper(Context context, int i) {
            super(context, i);
            this.theme = i;
        }

        @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
        public final int getTheme() {
            return this.theme;
        }
    }

    public final <T extends View> void addView(ViewManager manager, T view) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (manager instanceof ViewGroup) {
            ((ViewGroup) manager).addView(view);
        } else {
            if (manager instanceof AnkoContext) {
                manager.addView(view, null);
                return;
            }
            throw new AnkoException(manager + " is the wrong parent");
        }
    }

    public final Context wrapContextIfNeeded(Context ctx, int theme) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        return theme != 0 ? ((ctx instanceof AnkoContextThemeWrapper) && ((AnkoContextThemeWrapper) ctx).getTheme() == theme) ? ctx : new AnkoContextThemeWrapper(ctx, theme) : ctx;
    }

    public final void applyRecursively(View v, Function1<? super View, Unit> style) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(style, "style");
        style.invoke(v);
        if (!(v instanceof ViewGroup)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) v;
        int childCount = viewGroup.getChildCount() - 1;
        int i = 0;
        if (childCount < 0) {
            return;
        }
        while (true) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null) {
                INSTANCE.applyRecursively(childAt, style);
                Unit unit = Unit.INSTANCE;
            }
            if (i == childCount) {
                return;
            } else {
                i++;
            }
        }
    }

    public final Context getContext(ViewManager manager) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        if (manager instanceof ViewGroup) {
            Context context = ((ViewGroup) manager).getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "manager.context");
            return context;
        }
        if (manager instanceof AnkoContext) {
            return ((AnkoContext) manager).getCtx();
        }
        throw new AnkoException(manager + " is the wrong parent");
    }

    public static /* bridge */ /* synthetic */ AnkoContext createAnkoContext$default(AnkoInternals ankoInternals, Object obj, Context ctx, Function1 init, boolean z, int i, Object obj2) {
        if ((i & 4) != 0) {
            z = false;
        }
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(ctx, obj, z);
        init.invoke(ankoContextImpl);
        return ankoContextImpl;
    }

    public final <T> AnkoContext<T> createAnkoContext(T t, Context ctx, Function1<? super AnkoContext<? extends T>, Unit> init, boolean z) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(ctx, t, z);
        init.invoke(ankoContextImpl);
        return ankoContextImpl;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: Internals.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lorg/jetbrains/anko/internals/AnkoInternals$InternalConfiguration;", "", "()V", "SCREENLAYOUT_LAYOUTDIR_MASK", "", "getSCREENLAYOUT_LAYOUTDIR_MASK", "()I", "SCREENLAYOUT_LAYOUTDIR_RTL", "getSCREENLAYOUT_LAYOUTDIR_RTL", "SCREENLAYOUT_LAYOUTDIR_SHIFT", "getSCREENLAYOUT_LAYOUTDIR_SHIFT", "UI_MODE_TYPE_APPLIANCE", "getUI_MODE_TYPE_APPLIANCE", "UI_MODE_TYPE_WATCH", "getUI_MODE_TYPE_WATCH", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    private static final class InternalConfiguration {
        public static final InternalConfiguration INSTANCE = null;
        private static final int SCREENLAYOUT_LAYOUTDIR_MASK = 192;
        private static final int SCREENLAYOUT_LAYOUTDIR_RTL = 128;
        private static final int SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
        private static final int UI_MODE_TYPE_APPLIANCE = 5;
        private static final int UI_MODE_TYPE_WATCH = 6;

        static {
            new InternalConfiguration();
        }

        private InternalConfiguration() {
            INSTANCE = this;
            SCREENLAYOUT_LAYOUTDIR_MASK = 192;
            SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
            SCREENLAYOUT_LAYOUTDIR_RTL = 2 << 6;
            UI_MODE_TYPE_APPLIANCE = 5;
            UI_MODE_TYPE_WATCH = 6;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_MASK() {
            return SCREENLAYOUT_LAYOUTDIR_MASK;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_SHIFT() {
            return SCREENLAYOUT_LAYOUTDIR_SHIFT;
        }

        public final int getSCREENLAYOUT_LAYOUTDIR_RTL() {
            return SCREENLAYOUT_LAYOUTDIR_RTL;
        }

        public final int getUI_MODE_TYPE_APPLIANCE() {
            return UI_MODE_TYPE_APPLIANCE;
        }

        public final int getUI_MODE_TYPE_WATCH() {
            return UI_MODE_TYPE_WATCH;
        }
    }

    @JvmStatic
    public static final <T> Intent createIntent(Context ctx, Class<? extends T> clazz, Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intent intent = new Intent(ctx, clazz);
        if (!(params.length == 0)) {
            fillIntentArguments(intent, params);
        }
        return intent;
    }

    @JvmStatic
    public static final void internalStartActivity(Context ctx, Class<? extends Activity> activity, Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(params, "params");
        ctx.startActivity(createIntent(ctx, activity, params));
    }

    @JvmStatic
    public static final void internalStartActivityForResult(Activity act, Class<? extends Activity> activity, int requestCode, Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(act, "act");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(params, "params");
        act.startActivityForResult(createIntent(act, activity, params), requestCode);
    }

    @JvmStatic
    public static final ComponentName internalStartService(Context ctx, Class<? extends Service> service, Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(service, "service");
        Intrinsics.checkParameterIsNotNull(params, "params");
        return ctx.startService(createIntent(ctx, service, params));
    }

    @JvmStatic
    public static final boolean internalStopService(Context ctx, Class<? extends Service> service, Pair<String, ? extends Object>[] params) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(service, "service");
        Intrinsics.checkParameterIsNotNull(params, "params");
        return ctx.stopService(createIntent(ctx, service, params));
    }

    @JvmStatic
    private static final void fillIntentArguments(Intent intent, Pair<String, ? extends Object>[] params) {
        for (Pair<String, ? extends Object> pair : params) {
            Object second = pair.getSecond();
            if (Intrinsics.areEqual(second, (Object) null)) {
                intent.putExtra(pair.getFirst(), (Serializable) null);
            } else if (second instanceof Integer) {
                intent.putExtra(pair.getFirst(), ((Number) second).intValue());
            } else if (second instanceof Long) {
                intent.putExtra(pair.getFirst(), ((Number) second).longValue());
            } else if (second instanceof CharSequence) {
                intent.putExtra(pair.getFirst(), (CharSequence) second);
            } else if (second instanceof String) {
                intent.putExtra(pair.getFirst(), (String) second);
            } else if (second instanceof Float) {
                intent.putExtra(pair.getFirst(), ((Number) second).floatValue());
            } else if (second instanceof Double) {
                intent.putExtra(pair.getFirst(), ((Number) second).doubleValue());
            } else if (second instanceof Character) {
                intent.putExtra(pair.getFirst(), ((Character) second).charValue());
            } else if (second instanceof Short) {
                intent.putExtra(pair.getFirst(), ((Number) second).shortValue());
            } else if (second instanceof Boolean) {
                intent.putExtra(pair.getFirst(), ((Boolean) second).booleanValue());
            } else if (second instanceof Serializable) {
                intent.putExtra(pair.getFirst(), (Serializable) second);
            } else if (second instanceof Bundle) {
                intent.putExtra(pair.getFirst(), (Bundle) second);
            } else if (second instanceof Parcelable) {
                intent.putExtra(pair.getFirst(), (Parcelable) second);
            } else if (second instanceof Object[]) {
                Object[] objArr = (Object[]) second;
                if (objArr instanceof CharSequence[]) {
                    intent.putExtra(pair.getFirst(), (Serializable) second);
                } else if (objArr instanceof String[]) {
                    intent.putExtra(pair.getFirst(), (Serializable) second);
                } else {
                    if (!(objArr instanceof Parcelable[])) {
                        throw new AnkoException("Intent extra " + pair.getFirst() + " has wrong type " + objArr.getClass().getName());
                    }
                    intent.putExtra(pair.getFirst(), (Serializable) second);
                }
            } else if (second instanceof int[]) {
                intent.putExtra(pair.getFirst(), (int[]) second);
            } else if (second instanceof long[]) {
                intent.putExtra(pair.getFirst(), (long[]) second);
            } else if (second instanceof float[]) {
                intent.putExtra(pair.getFirst(), (float[]) second);
            } else if (second instanceof double[]) {
                intent.putExtra(pair.getFirst(), (double[]) second);
            } else if (second instanceof char[]) {
                intent.putExtra(pair.getFirst(), (char[]) second);
            } else if (second instanceof short[]) {
                intent.putExtra(pair.getFirst(), (short[]) second);
            } else {
                if (!(second instanceof boolean[])) {
                    throw new AnkoException("Intent extra " + pair.getFirst() + " has wrong type " + second.getClass().getName());
                }
                intent.putExtra(pair.getFirst(), (boolean[]) second);
            }
        }
    }

    @JvmStatic
    public static final <T> T useCursor(Cursor cursor, Function1<? super Cursor, ? extends T> f) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        Intrinsics.checkParameterIsNotNull(f, "f");
        try {
            return f.invoke(cursor);
        } finally {
            InlineMarker.finallyStart(1);
            try {
                cursor.close();
            } catch (Exception unused) {
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @JvmStatic
    public static final <T extends View> T initiateView(Context ctx, final Class<T> viewClass) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(viewClass, "viewClass");
        Function function = new Function0<Constructor<T>>() { // from class: org.jetbrains.anko.internals.AnkoInternals$initiateView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Constructor<T> invoke() {
                return viewClass.getConstructor(Context.class);
            }
        };
        Function function2 = new Function0<Constructor<T>>() { // from class: org.jetbrains.anko.internals.AnkoInternals$initiateView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Constructor<T> invoke() {
                return viewClass.getConstructor(Context.class, AttributeSet.class);
            }
        };
        try {
            try {
                Object newInstance = ((AnkoInternals$initiateView$1) function).invoke().newInstance(ctx);
                Intrinsics.checkExpressionValueIsNotNull(newInstance, "getConstructor1().newInstance(ctx)");
                return (T) newInstance;
            } catch (NoSuchMethodException unused) {
                throw new AnkoException("Can't initiate View of class " + viewClass.getName() + ": can't find proper constructor");
            }
        } catch (NoSuchMethodException unused2) {
            Object newInstance2 = ((AnkoInternals$initiateView$2) function2).invoke().newInstance(ctx, null);
            Intrinsics.checkExpressionValueIsNotNull(newInstance2, "getConstructor2().newInstance(ctx, null)");
            return (T) newInstance2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x01a2, code lost:
    
        if (r31.booleanValue() != false) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0087, code lost:
    
        if (r1 != r24.getEndInclusive().intValue()) goto L38;
     */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean testConfiguration(Context ctx, ScreenSize screenSize, ClosedRange<Integer> density, String language, Orientation orientation, Boolean r27, Integer fromSdk, Integer sdk, UiMode uiMode, Boolean nightMode, Boolean rightToLeft, Integer smallestWidth) {
        int nightMode2;
        DisplayMetrics displayMetrics;
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Resources resources = ctx.getResources();
        Configuration configuration = resources != null ? resources.getConfiguration() : null;
        if (screenSize != null) {
            if (configuration == null) {
                return false;
            }
            int i = configuration.screenLayout & 15;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (!Intrinsics.areEqual(screenSize, ScreenSize.LARGE)) {
                            return false;
                        }
                    } else if (i == 4 && (!Intrinsics.areEqual(screenSize, ScreenSize.XLARGE))) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(screenSize, ScreenSize.NORMAL)) {
                    return false;
                }
            } else if (!Intrinsics.areEqual(screenSize, ScreenSize.SMALL)) {
                return false;
            }
        }
        if (density != null) {
            Resources resources2 = ctx.getResources();
            if (resources2 != null && (displayMetrics = resources2.getDisplayMetrics()) != null) {
                int i2 = displayMetrics.densityDpi;
                if (!(!density.contains(Integer.valueOf(i2)))) {
                }
            }
            return false;
        }
        if (language != null) {
            Locale locale = Locale.getDefault();
            if (!Intrinsics.areEqual(StringsKt.indexOf$default((CharSequence) language, '_', 0, false, 6, (Object) null) >= 0 ? locale.toString() : locale.getLanguage(), language)) {
                return false;
            }
        }
        if (orientation != null) {
            if (configuration == null) {
                return false;
            }
            int i3 = configuration.orientation;
            if (i3 != 1) {
                if (i3 == 2) {
                    if (!Intrinsics.areEqual(orientation, Orientation.LANDSCAPE)) {
                        return false;
                    }
                } else if (i3 == 3 && (!Intrinsics.areEqual(orientation, Orientation.SQUARE))) {
                    return false;
                }
            } else if (!Intrinsics.areEqual(orientation, Orientation.PORTRAIT)) {
                return false;
            }
        }
        if (r27 != null) {
            if (configuration == null) {
                return false;
            }
            int i4 = configuration.screenLayout & 48;
            if (i4 == 32 && !r27.booleanValue()) {
                return false;
            }
            if (i4 == 16 && r27.booleanValue()) {
                return false;
            }
        }
        if (fromSdk != null && Intrinsics.compare(Build.VERSION.SDK_INT, fromSdk.intValue()) < 0) {
            return false;
        }
        if (sdk != null && (!Intrinsics.areEqual(Integer.valueOf(Build.VERSION.SDK_INT), sdk))) {
            return false;
        }
        if (uiMode != null) {
            if (configuration == null) {
                return false;
            }
            int i5 = configuration.uiMode & 15;
            if (i5 == 1) {
                if (!Intrinsics.areEqual(uiMode, UiMode.NORMAL)) {
                    return false;
                }
            } else if (i5 == 2) {
                if (!Intrinsics.areEqual(uiMode, UiMode.DESK)) {
                    return false;
                }
            } else if (i5 == 3) {
                if (!Intrinsics.areEqual(uiMode, UiMode.CAR)) {
                    return false;
                }
            } else if (i5 == 4) {
                if (!Intrinsics.areEqual(uiMode, UiMode.TELEVISION)) {
                    return false;
                }
            } else if (i5 == InternalConfiguration.INSTANCE.getUI_MODE_TYPE_APPLIANCE()) {
                if (!Intrinsics.areEqual(uiMode, UiMode.APPLIANCE)) {
                    return false;
                }
            } else if (i5 == InternalConfiguration.INSTANCE.getUI_MODE_TYPE_WATCH() && (!Intrinsics.areEqual(uiMode, UiMode.WATCH))) {
                return false;
            }
        }
        if (nightMode != null) {
            Object systemService = ctx.getSystemService("uimode");
            UiModeManager uiModeManager = (UiModeManager) (!(systemService instanceof UiModeManager) ? null : systemService);
            if (uiModeManager == null || ((nightMode2 = uiModeManager.getNightMode()) == 2 && !nightMode.booleanValue())) {
                return false;
            }
            if (nightMode2 == 1) {
            }
        }
        if (rightToLeft != null) {
            if (configuration == null) {
                return false;
            }
            if (!Intrinsics.areEqual(Boolean.valueOf((configuration.screenLayout & InternalConfiguration.INSTANCE.getSCREENLAYOUT_LAYOUTDIR_MASK()) == InternalConfiguration.INSTANCE.getSCREENLAYOUT_LAYOUTDIR_RTL()), rightToLeft)) {
                return false;
            }
        }
        if (smallestWidth != null) {
            if (configuration == null) {
                return false;
            }
            if (configuration.smallestScreenWidthDp == 0) {
                if (!Intrinsics.areEqual((Object) smallestWidth, (Object) 0)) {
                    return false;
                }
            } else if (Intrinsics.compare(configuration.smallestScreenWidthDp, smallestWidth.intValue()) < 0) {
                return false;
            }
        }
        return true;
    }

    public final <T extends View> void addView(Context ctx, T view) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(view, "view");
        INSTANCE.addView((ViewManager) new AnkoContextImpl(ctx, ctx, false), (AnkoContextImpl) view);
    }

    public final <T extends View> void addView(Activity activity, T view) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(view, "view");
        INSTANCE.addView((ViewManager) new AnkoContextImpl(activity, this, true), (AnkoContextImpl) view);
    }
}
