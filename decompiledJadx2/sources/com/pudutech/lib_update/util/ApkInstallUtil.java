package com.pudutech.lib_update.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import com.pudutech.lib_update.AppUpdateContext;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ApkInstallUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\b\u0010\n\u001a\u00020\bH\u0002¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/lib_update/util/ApkInstallUtil;", "", "()V", "getFileProviderName", "", "context", "Landroid/content/Context;", "install", "", "apkFilePath", "startInstallPermissionSettingActivity", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ApkInstallUtil {
    public static final ApkInstallUtil INSTANCE = new ApkInstallUtil();

    private ApkInstallUtil() {
    }

    @JvmStatic
    public static final void install(String apkFilePath) {
        Intrinsics.checkParameterIsNotNull(apkFilePath, "apkFilePath");
        if (new File(apkFilePath).exists()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            if (Build.VERSION.SDK_INT >= 24) {
                Context context = AppUpdateContext.context;
                ApkInstallUtil apkInstallUtil = INSTANCE;
                Context context2 = AppUpdateContext.context;
                Intrinsics.checkExpressionValueIsNotNull(context2, "AppUpdateContext.context");
                intent.setDataAndType(FileProvider.getUriForFile(context, apkInstallUtil.getFileProviderName(context2), new File(apkFilePath)), "application/vnd.android.package-archive");
                intent.addFlags(1);
                if (Build.VERSION.SDK_INT >= 26 && !AppUpdateContext.context.getPackageManager().canRequestPackageInstalls()) {
                    INSTANCE.startInstallPermissionSettingActivity();
                    return;
                }
            } else {
                Intrinsics.checkExpressionValueIsNotNull(intent.setDataAndType(Uri.fromFile(new File(apkFilePath)), "application/vnd.android.package-archive"), "intent.setDataAndType(Ur…android.package-archive\")");
            }
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            AppUpdateContext.context.startActivity(intent);
        }
    }

    private final void startInstallPermissionSettingActivity() {
        Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        AppUpdateContext.context.startActivity(intent);
    }

    private final String getFileProviderName(Context context) {
        return context.getPackageName() + ".fileprovider";
    }
}
