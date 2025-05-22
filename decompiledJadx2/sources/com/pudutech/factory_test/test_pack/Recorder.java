package com.pudutech.factory_test.test_pack;

import android.content.Context;
import android.content.SharedPreferences;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Recorder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u000bJ\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/Recorder;", "", "()V", "FILE_NAME", "", "TAG", "editor", "Landroid/content/SharedPreferences$Editor;", "sharedPreferences", "Landroid/content/SharedPreferences;", TmpConstant.PROPERTY_IDENTIFIER_GET, "Lcom/pudutech/factory_test/test_pack/TestStatus;", "name", "getDescription", "default", "init", "", "context", "Landroid/content/Context;", "save", "state", "description", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class Recorder {
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;
    public static final Recorder INSTANCE = new Recorder();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String FILE_NAME = FILE_NAME;
    private static final String FILE_NAME = FILE_NAME;

    private Recorder() {
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences2, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        sharedPreferences = sharedPreferences2;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        SharedPreferences.Editor edit = sharedPreferences2.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "sharedPreferences.edit()");
        editor = edit;
    }

    public final void save(String name, TestStatus state) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Pdlog.m3273d(TAG, "save name=" + name + "  state=" + state);
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editor");
        }
        editor2.putString(name, state.toString()).apply();
    }

    public final TestStatus get(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        String string = sharedPreferences2.getString(name, TestStatus.UNTESTED.getStr());
        if (string != null) {
            try {
                return TestStatus.valueOf(string);
            } catch (Exception e) {
                Pdlog.m3274e(TAG, String.valueOf(e));
            }
        }
        return TestStatus.UNTESTED;
    }

    public final void save(String name, String description) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Pdlog.m3273d(TAG, "save name=" + name + "  description=" + description);
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editor");
        }
        editor2.putString(name, description).apply();
    }

    public final String getDescription(String name, String r4) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(r4, "default");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        String string = sharedPreferences2.getString(name, null);
        return string != null ? string : r4;
    }
}
