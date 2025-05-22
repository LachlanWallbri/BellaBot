package com.pudutech.remotemaintenance.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PackageUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/utils/PackageUtil;", "", "()V", "getVersionCode", "", "context", "Landroid/content/Context;", "packageName", "", "isAppInstalled", "", "silentInstallApp", "", "appPath", "uninstallApp", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PackageUtil {
    public static final PackageUtil INSTANCE = new PackageUtil();

    private PackageUtil() {
    }

    public final boolean isAppInstalled(Context context, String packageName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        try {
            context.getPackageManager().getPackageInfo(packageName, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final void silentInstallApp(String appPath) {
        Intrinsics.checkParameterIsNotNull(appPath, "appPath");
        try {
            Pair<Integer, String> execCommand = Tools.execCommand("pm install -r " + appPath, true);
            if (execCommand != null) {
                Pdlog.m3273d("PackageUtil", "first[" + ((Integer) execCommand.first) + "], second[" + ((String) execCommand.second) + ']');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final int getVersionCode(Context context, String packageName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = packageName;
        boolean z = true;
        if (str == null || str.length() == 0) {
            return 0;
        }
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        List<PackageInfo> list = installedPackages;
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        if (z) {
            return 0;
        }
        for (PackageInfo packageInfo : installedPackages) {
            if (Intrinsics.areEqual(packageName, packageInfo.packageName)) {
                return packageInfo.versionCode;
            }
        }
        return 0;
    }

    public final boolean uninstallApp(Context context, String packageName) {
        Pair<Integer, String> execCommand;
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = packageName;
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            execCommand = Tools.execCommand("pm uninstall " + packageName, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (execCommand == null) {
            return false;
        }
        Pdlog.m3273d("PackageUtil", "pair = " + execCommand);
        Integer num = (Integer) execCommand.first;
        if (num != null && num.intValue() == 0) {
            if (Intrinsics.areEqual((String) execCommand.second, "Success\n")) {
                return true;
            }
        }
        return false;
    }
}
