package com.pudutech.lib_update.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.File;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class PackageUtils {
    public static final int APP_INSTALL_AUTO = 0;
    public static final int APP_INSTALL_EXTERNAL = 2;
    public static final int APP_INSTALL_INTERNAL = 1;
    public static final int DELETE_FAILED_DEVICE_POLICY_MANAGER = -2;
    public static final int DELETE_FAILED_INTERNAL_ERROR = -1;
    public static final int DELETE_FAILED_INVALID_PACKAGE = -3;
    public static final int DELETE_FAILED_PERMISSION_DENIED = -4;
    public static final int DELETE_SUCCEEDED = 1;
    public static final int INSTALL_FAILED_ALREADY_EXISTS = -1;
    public static final int INSTALL_FAILED_CONFLICTING_PROVIDER = -13;
    public static final int INSTALL_FAILED_CONTAINER_ERROR = -18;
    public static final int INSTALL_FAILED_CPU_ABI_INCOMPATIBLE = -16;
    public static final int INSTALL_FAILED_DEXOPT = -11;
    public static final int INSTALL_FAILED_DUPLICATE_PACKAGE = -5;
    public static final int INSTALL_FAILED_INSUFFICIENT_STORAGE = -4;
    public static final int INSTALL_FAILED_INTERNAL_ERROR = -110;
    public static final int INSTALL_FAILED_INVALID_APK = -2;
    public static final int INSTALL_FAILED_INVALID_INSTALL_LOCATION = -19;
    public static final int INSTALL_FAILED_INVALID_URI = -3;
    public static final int INSTALL_FAILED_MEDIA_UNAVAILABLE = -20;
    public static final int INSTALL_FAILED_MISSING_FEATURE = -17;
    public static final int INSTALL_FAILED_MISSING_SHARED_LIBRARY = -9;
    public static final int INSTALL_FAILED_NEWER_SDK = -14;
    public static final int INSTALL_FAILED_NO_SHARED_USER = -6;
    public static final int INSTALL_FAILED_OLDER_SDK = -12;
    public static final int INSTALL_FAILED_OTHER = -1000000;
    public static final int INSTALL_FAILED_PACKAGE_CHANGED = -23;
    public static final int INSTALL_FAILED_REPLACE_COULDNT_DELETE = -10;
    public static final int INSTALL_FAILED_SHARED_USER_INCOMPATIBLE = -8;
    public static final int INSTALL_FAILED_TEST_ONLY = -15;
    public static final int INSTALL_FAILED_UID_CHANGED = -24;
    public static final int INSTALL_FAILED_UPDATE_INCOMPATIBLE = -7;
    public static final int INSTALL_FAILED_VERIFICATION_FAILURE = -22;
    public static final int INSTALL_FAILED_VERIFICATION_TIMEOUT = -21;
    public static final int INSTALL_PARSE_FAILED_BAD_MANIFEST = -101;
    public static final int INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME = -106;
    public static final int INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID = -107;
    public static final int INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING = -105;
    public static final int INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES = -104;
    public static final int INSTALL_PARSE_FAILED_MANIFEST_EMPTY = -109;
    public static final int INSTALL_PARSE_FAILED_MANIFEST_MALFORMED = -108;
    public static final int INSTALL_PARSE_FAILED_NOT_APK = -100;
    public static final int INSTALL_PARSE_FAILED_NO_CERTIFICATES = -103;
    public static final int INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION = -102;
    public static final int INSTALL_SUCCEEDED = 1;
    public static final String TAG = "PackageUtils";

    private PackageUtils() {
        throw new AssertionError();
    }

    public static final int install(Context context, String str) {
        if (isSystemApplication(context) || ShellUtils.checkRootPermission()) {
            return installSilent(context, str);
        }
        return installNormal(context, str) ? 1 : -3;
    }

    public static boolean installNormal(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        File file = new File(str);
        if (!file.exists() || !file.isFile() || file.length() <= 0) {
            return false;
        }
        intent.setDataAndType(Uri.parse("file://" + str), "application/vnd.android.package-archive");
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
        return true;
    }

    public static int installSilent(Context context, String str) {
        return installSilent(context, str, " -r " + getInstallLocationParams());
    }

    public static int installSilent(Context context, String str, String str2) {
        if (str != null && str.length() != 0) {
            File file = new File(str);
            if (file.length() > 0 && file.exists() && file.isFile()) {
                StringBuilder sb = new StringBuilder();
                sb.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install ");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                sb.append(" ");
                sb.append(str.replace(" ", "\\ "));
                ShellUtils.CommandResult execCommand = ShellUtils.execCommand(sb.toString(), !isSystemApplication(context), true);
                if (execCommand.successMsg != null && (execCommand.successMsg.contains("Success") || execCommand.successMsg.contains("success"))) {
                    return 1;
                }
                Log.e(TAG, "installSilent successMsg:" + execCommand.successMsg + ", ErrorMsg:" + execCommand.errorMsg);
                if (execCommand.errorMsg == null) {
                    return INSTALL_FAILED_OTHER;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_ALREADY_EXISTS")) {
                    return -1;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_INVALID_APK")) {
                    return -2;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_INVALID_URI")) {
                    return -3;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_INSUFFICIENT_STORAGE")) {
                    return -4;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_DUPLICATE_PACKAGE")) {
                    return -5;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_NO_SHARED_USER")) {
                    return -6;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_UPDATE_INCOMPATIBLE")) {
                    return -7;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_SHARED_USER_INCOMPATIBLE")) {
                    return -8;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_MISSING_SHARED_LIBRARY")) {
                    return -9;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_REPLACE_COULDNT_DELETE")) {
                    return -10;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_DEXOPT")) {
                    return -11;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_OLDER_SDK")) {
                    return -12;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_CONFLICTING_PROVIDER")) {
                    return -13;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_NEWER_SDK")) {
                    return -14;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_TEST_ONLY")) {
                    return -15;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_CPU_ABI_INCOMPATIBLE")) {
                    return -16;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_MISSING_FEATURE")) {
                    return -17;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_CONTAINER_ERROR")) {
                    return -18;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_INVALID_INSTALL_LOCATION")) {
                    return -19;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_MEDIA_UNAVAILABLE")) {
                    return -20;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_VERIFICATION_TIMEOUT")) {
                    return -21;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_VERIFICATION_FAILURE")) {
                    return -22;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_PACKAGE_CHANGED")) {
                    return -23;
                }
                if (execCommand.errorMsg.contains("INSTALL_FAILED_UID_CHANGED")) {
                    return -24;
                }
                if (execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_NOT_APK")) {
                    return -100;
                }
                return execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_BAD_MANIFEST") ? INSTALL_PARSE_FAILED_BAD_MANIFEST : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION") ? INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_NO_CERTIFICATES") ? INSTALL_PARSE_FAILED_NO_CERTIFICATES : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES") ? INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING") ? INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME") ? INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID") ? INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_MANIFEST_MALFORMED") ? INSTALL_PARSE_FAILED_MANIFEST_MALFORMED : execCommand.errorMsg.contains("INSTALL_PARSE_FAILED_MANIFEST_EMPTY") ? INSTALL_PARSE_FAILED_MANIFEST_EMPTY : execCommand.errorMsg.contains("INSTALL_FAILED_INTERNAL_ERROR") ? INSTALL_FAILED_INTERNAL_ERROR : INSTALL_FAILED_OTHER;
            }
        }
        return -3;
    }

    public static final int uninstall(Context context, String str) {
        if (isSystemApplication(context) || ShellUtils.checkRootPermission()) {
            return uninstallSilent(context, str);
        }
        return uninstallNormal(context, str) ? 1 : -3;
    }

    public static boolean uninstallNormal(Context context, String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append("package:");
        sb.append(str);
        Intent intent = new Intent("android.intent.action.DELETE", Uri.parse(sb.toString()));
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
        return true;
    }

    public static int uninstallSilent(Context context, String str) {
        return uninstallSilent(context, str, true);
    }

    public static int uninstallSilent(Context context, String str, boolean z) {
        if (str == null || str.length() == 0) {
            return -3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm uninstall");
        sb.append(z ? " -k " : " ");
        sb.append(str.replace(" ", "\\ "));
        ShellUtils.CommandResult execCommand = ShellUtils.execCommand(sb.toString(), !isSystemApplication(context), true);
        if (execCommand.successMsg != null && (execCommand.successMsg.contains("Success") || execCommand.successMsg.contains("success"))) {
            return 1;
        }
        Log.e(TAG, "uninstallSilent successMsg:" + execCommand.successMsg + ", ErrorMsg:" + execCommand.errorMsg);
        return (execCommand.errorMsg != null && execCommand.errorMsg.contains("Permission denied")) ? -4 : -1;
    }

    public static boolean isSystemApplication(Context context) {
        if (context == null) {
            return false;
        }
        return isSystemApplication(context, context.getPackageName());
    }

    public static boolean isSystemApplication(Context context, String str) {
        if (context == null) {
            return false;
        }
        return isSystemApplication(context.getPackageManager(), str);
    }

    public static boolean isSystemApplication(PackageManager packageManager, String str) {
        if (packageManager != null && str != null && str.length() != 0) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                if (applicationInfo != null) {
                    if ((applicationInfo.flags & 1) > 0) {
                        return true;
                    }
                }
                return false;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int getInstallLocation() {
        int i = 1;
        ShellUtils.CommandResult execCommand = ShellUtils.execCommand("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm get-install-location", false, true);
        if (execCommand.result == 0 && execCommand.successMsg != null && execCommand.successMsg.length() > 0) {
            try {
                int parseInt = Integer.parseInt(execCommand.successMsg.substring(0, 1));
                if (parseInt != 1) {
                    i = 2;
                    if (parseInt != 2) {
                    }
                }
                return i;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Log.e(TAG, "pm get-install-location error");
            }
        }
        return 0;
    }

    private static String getInstallLocationParams() {
        int installLocation = getInstallLocation();
        return installLocation != 1 ? installLocation != 2 ? "" : "-s" : "-f";
    }

    public static void startInstalledAppDetails(Context context, String str) {
        Intent intent = new Intent();
        int i = Build.VERSION.SDK_INT;
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", str, null));
        } else {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra(i == 8 ? "pkg" : "com.android.settings.ApplicationPkgName", str);
        }
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }
}
