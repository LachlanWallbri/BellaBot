package com.pudutech.robot.module.report;

import android.content.Context;
import android.content.SharedPreferences;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ReportSpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000bJ\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000bJ\u0016\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/module/report/ReportSpUtils;", "", "()V", "TAG", "", "TOKEN_SP", "gson", "Lcom/google/gson/Gson;", "sp", "Landroid/content/SharedPreferences;", TmpConstant.PROPERTY_IDENTIFIER_GET, "", TransferTable.COLUMN_KEY, "defValue", "init", "", "context", "Landroid/content/Context;", TmpConstant.PROPERTY_IDENTIFIER_SET, ES6Iterator.VALUE_PROPERTY, "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportSpUtils {
    private static SharedPreferences sp;
    public static final ReportSpUtils INSTANCE = new ReportSpUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String TOKEN_SP = TOKEN_SP;
    private static final String TOKEN_SP = TOKEN_SP;
    private static final Gson gson = new Gson();

    private ReportSpUtils() {
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_SP, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPrefere…SP, Context.MODE_PRIVATE)");
        sp = sharedPreferences;
    }

    public final long get(String key, long defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        return sharedPreferences.getLong(key, defValue);
    }

    public final String get(String key, String defValue) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(defValue, "defValue");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        String string = sharedPreferences.getString(key, defValue);
        return string != null ? string : "";
    }

    public final void set(String key, long value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public final void set(String key, String value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SharedPreferences sharedPreferences = sp;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sp");
        }
        sharedPreferences.edit().putString(key, value).apply();
    }
}
