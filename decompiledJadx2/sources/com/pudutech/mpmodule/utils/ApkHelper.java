package com.pudutech.mpmodule.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class ApkHelper {
    public static final String PACKAGE_NAME_QQ_MUSIC = "com.tencent.qqmusicpad";
    public static final String PACKAGE_NAME_QQ_MUSIC_SERVICE = "com.tencent.qqmusicpad:QQPlayerService";
    public static final String PACKAGE_NAME_QQ_MUSIC_WNS = "com.tencent.qqmusicpad:wns";

    public static boolean isInstall(Context context, String str) {
        List<PackageInfo> installedPackages;
        if (!StringUtil.isEmpty(str) && (installedPackages = context.getPackageManager().getInstalledPackages(0)) != null && !installedPackages.isEmpty()) {
            for (int i = 0; i < installedPackages.size(); i++) {
                if (str.equals(installedPackages.get(i).packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void launcherApk(Context context, String str) {
        context.startActivity(getAppOpenIntentByPackageName(context, str));
    }

    public static Intent getAppOpenIntentByPackageName(Context context, String str) {
        String str2;
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 1);
        int i = 0;
        while (true) {
            if (i >= queryIntentActivities.size()) {
                str2 = null;
                break;
            }
            ResolveInfo resolveInfo = queryIntentActivities.get(i);
            if (resolveInfo.activityInfo.packageName.equals(str)) {
                str2 = resolveInfo.activityInfo.name;
                break;
            }
            i++;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        intent.setComponent(new ComponentName(str, str2));
        return intent;
    }
}
