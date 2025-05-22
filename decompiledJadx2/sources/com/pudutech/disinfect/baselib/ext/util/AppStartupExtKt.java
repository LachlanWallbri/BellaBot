package com.pudutech.disinfect.baselib.ext.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.CrossProcessTask;
import com.pudutech.disinfect.baselib.util.NavigationBar;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: AppStartupExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001\u001a-\u0010\b\u001a\u00020\t*\u00020\u00032!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u000b¨\u0006\u000f"}, m3961d2 = {"getAppMetaData", "", "context", "Landroid/content/Context;", TransferTable.COLUMN_KEY, "installApp", "", "path", "startDebugFunction", "", "failCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", IoTConfig.PARAM_ERROR_MSG, "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AppStartupExtKt {
    public static final boolean installApp(Context context, String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Uri fromFile = Uri.fromFile(new File(str));
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static final String getAppMetaData(Context context, String str) {
        ApplicationInfo applicationInfo;
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = (String) null;
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || applicationInfo.metaData == null) ? str2 : applicationInfo.metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static final void startDebugFunction(Context startDebugFunction, Function1<? super String, Unit> failCallback) {
        Intrinsics.checkParameterIsNotNull(startDebugFunction, "$this$startDebugFunction");
        Intrinsics.checkParameterIsNotNull(failCallback, "failCallback");
        try {
            NavigationBar.statusBarDisable(62849024, startDebugFunction);
            CrossProcessTask.INSTANCE.jumpActivity(startDebugFunction, "com.pudutech.mirsdk", "com.pudutech.mirsdk.activity.MirSDKActivity");
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("startDebugFunction#  ");
            sb.append(e.getLocalizedMessage());
            sb.append(": ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            sb.append(ArraysKt.contentDeepToString(stackTrace));
            Pdlog.m3274e(LogExtKt.TAG, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("startDebugFunction#  ");
            StackTraceElement[] stackTrace2 = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace2, "e.stackTrace");
            sb2.append(ArraysKt.contentDeepToString(stackTrace2));
            failCallback.invoke(sb2.toString());
        }
    }
}
