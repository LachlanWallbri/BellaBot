package com.pudutech.bumblebee.presenter.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PackageUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/PackageUtil;", "", "()V", "appIsRunning", "", "packageName", "", "getVersionCode", "", "context", "Landroid/content/Context;", "isAppInstalled", "silentInstallApp", "", "appPath", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
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

    /* JADX WARN: Removed duplicated region for block: B:19:0x005d A[Catch: Exception -> 0x0072, TRY_LEAVE, TryCatch #0 {Exception -> 0x0072, blocks: (B:3:0x0006, B:5:0x000f, B:10:0x0045, B:12:0x004b, B:14:0x0051, B:19:0x005d), top: B:2:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean appIsRunning(String packageName) {
        Pair<Integer, String> execCommand;
        boolean z;
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        try {
            execCommand = Tools.execCommand("busybox ps aux", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (execCommand == null) {
            return false;
        }
        Pdlog.m3273d("PackageUtil", "first[" + ((Integer) execCommand.first) + "], second[" + ((String) execCommand.second) + ']');
        Integer num = (Integer) execCommand.first;
        if (num != null && num.intValue() == 0) {
            CharSequence charSequence = (CharSequence) execCommand.second;
            if (charSequence != null && charSequence.length() != 0) {
                z = false;
                if (!z) {
                    Object obj = execCommand.second;
                    Intrinsics.checkExpressionValueIsNotNull(obj, "pair.second");
                    if (StringsKt.contains$default((CharSequence) obj, (CharSequence) packageName, false, 2, (Object) null)) {
                        return true;
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        return false;
    }
}
