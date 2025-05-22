package com.pudutech.bumblebee.robot.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: SharedPreferencesUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\n\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u0001J \u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/util/SharedPreferencesUtils;", "", "()V", "FILE_NAME", "", "clear", "", "context", "Landroid/content/Context;", "clearAll", "getParam", TransferTable.COLUMN_KEY, "defaultObject", "setParam", "obj", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SharedPreferencesUtils {
    private static final String FILE_NAME = "share_date";
    public static final SharedPreferencesUtils INSTANCE = new SharedPreferencesUtils();

    private SharedPreferencesUtils() {
    }

    public final void setParam(Context context, String key, Object obj) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "obj.javaClass.simpleName");
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "sp.edit()");
        if (Intrinsics.areEqual("String", simpleName)) {
            edit.putString(key, (String) obj);
        } else if (Intrinsics.areEqual("Integer", simpleName)) {
            edit.putInt(key, ((Integer) obj).intValue());
        } else if (Intrinsics.areEqual("Boolean", simpleName)) {
            edit.putBoolean(key, ((Boolean) obj).booleanValue());
        } else if (Intrinsics.areEqual("Float", simpleName)) {
            edit.putFloat(key, ((Float) obj).floatValue());
        } else if (Intrinsics.areEqual("Long", simpleName)) {
            edit.putLong(key, ((Long) obj).longValue());
        }
        edit.commit();
    }

    public final Object getParam(Context context, String key, Object defaultObject) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(defaultObject, "defaultObject");
        String simpleName = defaultObject.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "defaultObject.javaClass.simpleName");
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        switch (simpleName.hashCode()) {
            case -1808118735:
                if (simpleName.equals("String")) {
                    return sharedPreferences.getString(key, (String) defaultObject);
                }
                return null;
            case -672261858:
                if (simpleName.equals("Integer")) {
                    return Integer.valueOf(sharedPreferences.getInt(key, ((Integer) defaultObject).intValue()));
                }
                return null;
            case 2374300:
                if (simpleName.equals("Long")) {
                    return Long.valueOf(sharedPreferences.getLong(key, ((Long) defaultObject).longValue()));
                }
                return null;
            case 67973692:
                if (simpleName.equals("Float")) {
                    return Float.valueOf(sharedPreferences.getFloat(key, ((Float) defaultObject).floatValue()));
                }
                return null;
            case 1729365000:
                if (simpleName.equals("Boolean")) {
                    return Boolean.valueOf(sharedPreferences.getBoolean(key, ((Boolean) defaultObject).booleanValue()));
                }
                return null;
            default:
                return null;
        }
    }

    public final void clear(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…xt.MODE_PRIVATE\n        )");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "sp.edit()");
        edit.clear().commit();
    }

    public final void clearAll(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…xt.MODE_PRIVATE\n        )");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "sp.edit()");
        edit.remove("定义的键名");
        edit.commit();
    }
}
