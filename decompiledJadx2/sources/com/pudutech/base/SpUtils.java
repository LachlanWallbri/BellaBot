package com.pudutech.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* compiled from: SpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\"\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H\u0007J\"\u0010\u000b\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\"\u0010\u000b\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0011H\u0007J\"\u0010\u000b\u001a\u00020\u00122\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0012H\u0007J\"\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J*\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H\u0007J*\u0010\u000b\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J*\u0010\u000b\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0011H\u0007J*\u0010\u000b\u001a\u00020\u00122\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0012H\u0007J*\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0004H\u0007J\"\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0006H\u0007J\"\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0010H\u0007J\"\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0011H\u0007J\"\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0012H\u0007J\"\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J*\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0006H\u0007J*\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0010H\u0007J*\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0011H\u0007J*\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0012H\u0007J*\u0010\u0017\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/base/SpUtils;", "", "()V", "PREF_NAME", "", "sIsAtLeastGB", "", "apply", "", "editor", "Landroid/content/SharedPreferences$Editor;", TmpConstant.PROPERTY_IDENTIFIER_GET, "context", "Landroid/content/Context;", TransferTable.COLUMN_KEY, "defValue", "", "", "", "pref_name", "getPreferences", "Landroid/content/SharedPreferences;", "prefName", TmpConstant.PROPERTY_IDENTIFIER_SET, ES6Iterator.VALUE_PROPERTY, "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SpUtils {
    public static final SpUtils INSTANCE = new SpUtils();
    private static final String PREF_NAME = "creativelocker.pref";
    private static boolean sIsAtLeastGB;

    static {
        if (Build.VERSION.SDK_INT >= 9) {
            sIsAtLeastGB = true;
        }
    }

    private SpUtils() {
    }

    public final void apply(SharedPreferences.Editor editor) {
        Intrinsics.checkParameterIsNotNull(editor, "editor");
        if (sIsAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new SpUtils$apply$1(null), 2, null);
    }

    @JvmStatic
    public static final void set(Context context, String key, int value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context).edit();
        editor.putInt(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String key, long value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context).edit();
        editor.putLong(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String key, boolean value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context).edit();
        editor.putBoolean(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String key, String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context).edit();
        editor.putString(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String key, float value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context).edit();
        editor.putFloat(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final boolean get(Context context, String key, boolean defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context).getBoolean(key, defValue);
    }

    @JvmStatic
    public static final String get(Context context, String key, String defValue) {
        String string;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        return (context == null || (string = INSTANCE.getPreferences(context).getString(key, defValue)) == null) ? defValue : string;
    }

    @JvmStatic
    public static final int get(Context context, String key, int defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context).getInt(key, defValue);
    }

    @JvmStatic
    public static final long get(Context context, String key, long defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context).getLong(key, defValue);
    }

    @JvmStatic
    public static final float get(Context context, String key, float defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context).getFloat(key, defValue);
    }

    @JvmStatic
    public static final void set(Context context, String pref_name, String key, int value) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context, pref_name).edit();
        editor.putInt(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String pref_name, String key, long value) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context, pref_name).edit();
        editor.putLong(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String pref_name, String key, boolean value) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context, pref_name).edit();
        editor.putBoolean(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String pref_name, String key, String value) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context, pref_name).edit();
        editor.putString(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final void set(Context context, String pref_name, String key, float value) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editor = INSTANCE.getPreferences(context, pref_name).edit();
        editor.putFloat(key, value);
        SpUtils spUtils = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(editor, "editor");
        spUtils.apply(editor);
    }

    @JvmStatic
    public static final boolean get(Context context, String pref_name, String key, boolean defValue) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context, pref_name).getBoolean(key, defValue);
    }

    @JvmStatic
    public static final String get(Context context, String pref_name, String key, String defValue) {
        String string;
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        return (context == null || (string = INSTANCE.getPreferences(context, pref_name).getString(key, defValue)) == null) ? defValue : string;
    }

    @JvmStatic
    public static final int get(Context context, String pref_name, String key, int defValue) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context, pref_name).getInt(key, defValue);
    }

    @JvmStatic
    public static final long get(Context context, String pref_name, String key, long defValue) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context, pref_name).getLong(key, defValue);
    }

    @JvmStatic
    public static final float get(Context context, String pref_name, String key, float defValue) {
        Intrinsics.checkParameterIsNotNull(pref_name, "pref_name");
        Intrinsics.checkParameterIsNotNull(key, "key");
        return context == null ? defValue : INSTANCE.getPreferences(context, pref_name).getFloat(key, defValue);
    }

    public final SharedPreferences getPreferences(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.applicationConte…xt.MODE_PRIVATE\n        )");
        return sharedPreferences;
    }

    public final SharedPreferences getPreferences(Context context, String prefName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(prefName, "prefName");
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(prefName, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.applicationConte…xt.MODE_PRIVATE\n        )");
        return sharedPreferences;
    }
}
