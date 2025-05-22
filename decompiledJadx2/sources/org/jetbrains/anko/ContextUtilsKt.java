package org.jetbrains.anko;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import com.amitshekhar.utils.DataType;
import com.iflytek.aiui.AIUIConstant;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: ContextUtils.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a;\u00100\u001a\u0002012.\u00102\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u0001060403\"\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u00010604¢\u0006\u0002\u00107\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00012\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010<\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020=2\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010>\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00042\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010?\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00122\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010@\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00012\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010<\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020=2\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010>\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00042\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010?\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00122\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010@\u001aL\u0010B\u001a\u0002H9\"\b\b\u0000\u00109*\u00020\u0004*\u0002H92.\u00102\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u0001060403\"\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u00010604H\u0086\b¢\u0006\u0002\u0010C\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0016\u0010\u000b\u001a\u00020\f*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u001a\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010\"\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0016\u0010\u0015\u001a\u00020\r*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0016\u0010\u0015\u001a\u00020\r*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018\"\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001d\"\u001a\u0010\u0019\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001e\"\u0016\u0010\u001f\u001a\u00020 *\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u001a\u0010\u001f\u001a\u00020 *\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010#\"\u0016\u0010$\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b&\u0010'\"\u0016\u0010(\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b)\u0010'\"\u0016\u0010*\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b+\u0010'\"\u001a\u0010,\u001a\u00020-*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u0006D"}, m3961d2 = {"act", "Landroid/app/Activity;", "getAct", "(Landroid/app/Activity;)Landroid/app/Activity;", "Landroid/app/Fragment;", "(Landroid/app/Fragment;)Landroid/app/Activity;", AIUIConstant.RES_TYPE_ASSETS, "Landroid/content/res/AssetManager;", "Lorg/jetbrains/anko/AnkoContext;", "getAssets", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/AssetManager;", "configuration", "Landroid/content/res/Configuration;", "Landroid/content/Context;", "getConfiguration", "(Landroid/content/Context;)Landroid/content/res/Configuration;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Configuration;", "contentView", "Landroid/view/View;", "getContentView", "(Landroid/app/Activity;)Landroid/view/View;", "ctx", "getCtx", "(Landroid/app/Fragment;)Landroid/content/Context;", "(Landroid/content/Context;)Landroid/content/Context;", "defaultSharedPreferences", "Landroid/content/SharedPreferences;", "getDefaultSharedPreferences", "(Landroid/app/Fragment;)Landroid/content/SharedPreferences;", "(Landroid/content/Context;)Landroid/content/SharedPreferences;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/SharedPreferences;", "displayMetrics", "Landroid/util/DisplayMetrics;", "getDisplayMetrics", "(Landroid/content/Context;)Landroid/util/DisplayMetrics;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/util/DisplayMetrics;", "landscape", "", "getLandscape", "(Landroid/content/res/Configuration;)Z", DataType.LONG, "getLong", "portrait", "getPortrait", "resources", "Landroid/content/res/Resources;", "getResources", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Resources;", "bundleOf", "Landroid/os/Bundle;", "params", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "find", ExifInterface.GPS_DIRECTION_TRUE, "id", "", "(Landroid/app/Activity;I)Landroid/view/View;", "Landroid/app/Dialog;", "(Landroid/app/Dialog;I)Landroid/view/View;", "(Landroid/app/Fragment;I)Landroid/view/View;", "(Landroid/view/View;I)Landroid/view/View;", "findOptional", "withArguments", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/app/Fragment;", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class ContextUtilsKt {
    public static final Activity getAct(Activity receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver;
    }

    public static final Context getCtx(Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver;
    }

    public static final Resources getResources(AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Resources resources = receiver.getCtx().getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "ctx.resources");
        return resources;
    }

    public static final AssetManager getAssets(AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        AssetManager assets = receiver.getCtx().getAssets();
        Intrinsics.checkExpressionValueIsNotNull(assets, "ctx.assets");
        return assets;
    }

    public static final SharedPreferences getDefaultSharedPreferences(AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(receiver.getCtx());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDefaultSharedPreferences(ctx)");
        return defaultSharedPreferences;
    }

    public static final SharedPreferences getDefaultSharedPreferences(Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(receiver);
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…ltSharedPreferences(this)");
        return defaultSharedPreferences;
    }

    public static final SharedPreferences getDefaultSharedPreferences(Fragment receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(receiver.getActivity());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…aredPreferences(activity)");
        return defaultSharedPreferences;
    }

    public static final Activity getAct(Fragment receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Activity activity = receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        return activity;
    }

    public static final Context getCtx(Fragment receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Activity activity = receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        return activity;
    }

    private static final <T extends View> T find(View view, int i) {
        View findViewById = view.findViewById(i);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T find(Activity activity, int i) {
        View findViewById = activity.findViewById(i);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T find(Fragment fragment, int i) {
        View view = fragment.getView();
        View findViewById = view != null ? view.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T find(Dialog dialog, int i) {
        View findViewById = dialog.findViewById(i);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(View view, int i) {
        View findViewById = view.findViewById(i);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(Activity activity, int i) {
        View findViewById = activity.findViewById(i);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(Fragment fragment, int i) {
        View view = fragment.getView();
        View findViewById = view != null ? view.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(Dialog dialog, int i) {
        View findViewById = dialog.findViewById(i);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    public static final <T extends Fragment> T withArguments(T receiver, Pair<String, ? extends Object>... params) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(params, "params");
        receiver.setArguments(bundleOf((Pair[]) Arrays.copyOf(params, params.length)));
        return receiver;
    }

    public static final Bundle bundleOf(Pair<String, ? extends Object>... params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Bundle bundle = new Bundle();
        for (Pair<String, ? extends Object> pair : params) {
            String component1 = pair.component1();
            Object component2 = pair.component2();
            if (Intrinsics.areEqual(component2, (Object) null)) {
                bundle.putSerializable(component1, null);
            } else if (component2 instanceof Boolean) {
                bundle.putBoolean(component1, ((Boolean) component2).booleanValue());
            } else if (component2 instanceof Byte) {
                bundle.putByte(component1, ((Number) component2).byteValue());
            } else if (component2 instanceof Character) {
                bundle.putChar(component1, ((Character) component2).charValue());
            } else if (component2 instanceof Short) {
                bundle.putShort(component1, ((Number) component2).shortValue());
            } else if (component2 instanceof Integer) {
                bundle.putInt(component1, ((Number) component2).intValue());
            } else if (component2 instanceof Long) {
                bundle.putLong(component1, ((Number) component2).longValue());
            } else if (component2 instanceof Float) {
                bundle.putFloat(component1, ((Number) component2).floatValue());
            } else if (component2 instanceof Double) {
                bundle.putDouble(component1, ((Number) component2).doubleValue());
            } else if (component2 instanceof String) {
                bundle.putString(component1, (String) component2);
            } else if (component2 instanceof CharSequence) {
                bundle.putCharSequence(component1, (CharSequence) component2);
            } else if (component2 instanceof Parcelable) {
                bundle.putParcelable(component1, (Parcelable) component2);
            } else if (component2 instanceof Serializable) {
                bundle.putSerializable(component1, (Serializable) component2);
            } else if (component2 instanceof boolean[]) {
                bundle.putBooleanArray(component1, (boolean[]) component2);
            } else if (component2 instanceof byte[]) {
                bundle.putByteArray(component1, (byte[]) component2);
            } else if (component2 instanceof char[]) {
                bundle.putCharArray(component1, (char[]) component2);
            } else if (component2 instanceof double[]) {
                bundle.putDoubleArray(component1, (double[]) component2);
            } else if (component2 instanceof float[]) {
                bundle.putFloatArray(component1, (float[]) component2);
            } else if (component2 instanceof int[]) {
                bundle.putIntArray(component1, (int[]) component2);
            } else if (component2 instanceof long[]) {
                bundle.putLongArray(component1, (long[]) component2);
            } else if (component2 instanceof Object[]) {
                Object[] objArr = (Object[]) component2;
                if (objArr instanceof Parcelable[]) {
                    if (component2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out android.os.Parcelable>");
                    }
                    bundle.putParcelableArray(component1, (Parcelable[]) component2);
                } else if (objArr instanceof CharSequence[]) {
                    if (component2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.CharSequence>");
                    }
                    bundle.putCharSequenceArray(component1, (CharSequence[]) component2);
                } else {
                    if (!(objArr instanceof String[])) {
                        throw new AnkoException("Unsupported bundle component (" + objArr.getClass() + ")");
                    }
                    if (component2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.String>");
                    }
                    bundle.putStringArray(component1, (String[]) component2);
                }
            } else if (component2 instanceof short[]) {
                bundle.putShortArray(component1, (short[]) component2);
            } else {
                if (!(component2 instanceof Bundle)) {
                    throw new AnkoException("Unsupported bundle component (" + component2.getClass() + ")");
                }
                bundle.putBundle(component1, (Bundle) component2);
            }
        }
        return bundle;
    }

    public static final DisplayMetrics getDisplayMetrics(Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DisplayMetrics displayMetrics = receiver.getResources().getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "resources.displayMetrics");
        return displayMetrics;
    }

    public static final Configuration getConfiguration(Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Configuration configuration = receiver.getResources().getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(configuration, "resources.configuration");
        return configuration;
    }

    public static final DisplayMetrics getDisplayMetrics(AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DisplayMetrics displayMetrics = receiver.getCtx().getResources().getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "ctx.resources.displayMetrics");
        return displayMetrics;
    }

    public static final Configuration getConfiguration(AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Configuration configuration = receiver.getCtx().getResources().getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(configuration, "ctx.resources.configuration");
        return configuration;
    }

    public static final boolean getPortrait(Configuration receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.orientation == 1;
    }

    public static final boolean getLandscape(Configuration receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.orientation == 2;
    }

    public static final boolean getLong(Configuration receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (receiver.screenLayout & 32) != 0;
    }

    public static final View getContentView(Activity receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        View findViewById = receiver.findViewById(R.id.content);
        if (!(findViewById instanceof ViewGroup)) {
            findViewById = null;
        }
        ViewGroup viewGroup = (ViewGroup) findViewById;
        if (viewGroup != null) {
            return viewGroup.getChildAt(0);
        }
        return null;
    }
}
