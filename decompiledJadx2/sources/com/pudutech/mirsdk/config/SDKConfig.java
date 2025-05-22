package com.pudutech.mirsdk.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.mirsdk.update.ApiConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/config/SDKConfig;", "", "()V", TmpConstant.DATA_KEY_DEVICENAME, "", "getMAC", "()Ljava/lang/String;", "setMAC", "(Ljava/lang/String;)V", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "setPreferences", "(Landroid/content/SharedPreferences;)V", "processContext", "Landroid/content/Context;", "getProcessContext", "()Landroid/content/Context;", "setProcessContext", "(Landroid/content/Context;)V", "init", "", "context", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SDKConfig {
    public static final SDKConfig INSTANCE = new SDKConfig();
    private static String MAC = ApiConstants.MAC_ADDRESS;
    public static SharedPreferences preferences;
    public static Context processContext;

    private SDKConfig() {
    }

    public final SharedPreferences getPreferences() {
        SharedPreferences sharedPreferences = preferences;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        return sharedPreferences;
    }

    public final void setPreferences(SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "<set-?>");
        preferences = sharedPreferences;
    }

    public final Context getProcessContext() {
        Context context = processContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("processContext");
        }
        return context;
    }

    public final void setProcessContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        processContext = context;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        processContext = applicationContext;
        SharedPreferences sharedPreferences = context.getSharedPreferences("mirsdk", 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "context.getSharedPreferences(\"mirsdk\", 0)");
        preferences = sharedPreferences;
    }

    public final String getMAC() {
        return MAC;
    }

    public final void setMAC(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        MAC = str;
    }
}
