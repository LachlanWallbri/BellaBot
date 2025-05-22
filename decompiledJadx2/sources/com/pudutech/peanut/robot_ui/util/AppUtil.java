package com.pudutech.peanut.robot_ui.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.CrossProcessTask;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes5.dex */
public class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();
    private static Set<String> fileItemList = new HashSet();

    static {
        fileItemList.add("META-INF/CERT.RSA");
        fileItemList.add("META-INF/CERT.SF");
        fileItemList.add("META-INF/MANIFEST.MF");
        fileItemList.add("AndroidManifest.xml");
        fileItemList.add("resources.arsc");
        fileItemList.add("classes.dex");
    }

    public static String getApplicationName(Context context) {
        String str = (String) context.getApplicationInfo().loadLabel(context.getPackageManager());
        if (str == null || "".equals(str)) {
            return null;
        }
        return str;
    }

    public static String getSystemVersion() {
        return Build.MODEL + "," + Build.VERSION.SDK_INT + "," + Build.VERSION.RELEASE;
    }

    public static String getMac(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getMacAddress() == null) {
            return null;
        }
        return connectionInfo.getMacAddress().replaceAll(":", "").toLowerCase(Locale.getDefault());
    }

    public static String getVersionName(Context context) {
        try {
            synchronized (context) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 65536);
                r0 = packageInfo != null ? packageInfo.versionName : null;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, "getVersionName1()", e);
        }
        return r0;
    }

    public static String getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, "getAppVersionCode()", e);
            return null;
        }
    }

    public static String getAppVersionCode(Context context) {
        return getVersionName(context).replaceAll("\\.", "") + getVersionCode(context);
    }

    public static String getTotalMemory(Context context) {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            j = Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024;
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Formatter.formatFileSize(context, j);
    }

    public static String getAvailMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(context, memoryInfo.availMem);
    }

    public static String getMemoryPercent(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        long j = memoryInfo.availMem;
        long j2 = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            j2 = Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024;
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(1.0f - ((((float) j) * 1.0f) / ((float) j2)));
    }

    public static String getSdcardPercent() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return "UNKNOWN";
        }
        new StatFs(Environment.getExternalStorageDirectory().getPath()).getBlockSize();
        return String.valueOf(1.0f - ((r1.getAvailableBlocks() * 1.0f) / r1.getBlockCount()));
    }

    public static String getDiskPercent() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return String.valueOf(1.0f - ((statFs.getAvailableBlocks() * 1.0f) / statFs.getBlockCount()));
    }

    public static boolean isLegalApk(String str) {
        ZipInputStream zipInputStream;
        Throwable th;
        ZipFile zipFile;
        String str2;
        StringBuilder sb;
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z2 = false;
        try {
            File file = new File(str);
            zipFile = new ZipFile(file);
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(file));
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry != null) {
                            if (fileItemList.contains(nextEntry.getName())) {
                                InputStream inputStream = zipFile.getInputStream(nextEntry);
                                inputStream.skip(10240000L);
                                inputStream.close();
                            }
                        } else {
                            try {
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                                z = false;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            th.printStackTrace();
                            if (zipInputStream != null) {
                                try {
                                    zipInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (zipFile != null) {
                                try {
                                    zipFile.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            str2 = TAG;
                            sb = new StringBuilder();
                            sb.append("check used time:");
                            sb.append(System.currentTimeMillis() - currentTimeMillis);
                            sb.append("ms.");
                            Log.d(str2, sb.toString());
                            return z2;
                        } catch (Throwable th3) {
                            if (zipInputStream != null) {
                                try {
                                    zipInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (zipFile != null) {
                                try {
                                    zipFile.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            Log.d(TAG, "check used time:" + (System.currentTimeMillis() - currentTimeMillis) + "ms.");
                            throw th3;
                        }
                    }
                }
                zipInputStream.close();
                z = true;
                try {
                    zipFile.close();
                    z2 = z;
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                str2 = TAG;
                sb = new StringBuilder();
            } catch (Throwable th4) {
                zipInputStream = null;
                th = th4;
            }
        } catch (Throwable th5) {
            zipInputStream = null;
            th = th5;
            zipFile = null;
        }
        sb.append("check used time:");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        sb.append("ms.");
        Log.d(str2, sb.toString());
        return z2;
    }

    public static boolean installApp(Context context, String str) {
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

    public static String getAppMetaData(Context context, String str) {
        ApplicationInfo applicationInfo;
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || applicationInfo.metaData == null) {
                return null;
            }
            return applicationInfo.metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void startDebugFunction(MyBaseActivity myBaseActivity) {
        try {
            CrossProcessTask.INSTANCE.jumpActivity(myBaseActivity, "com.pudutech.mirsdk", "com.pudutech.mirsdk.activity.MirSDKActivity");
        } catch (Exception unused) {
            Pdlog.m3274e(TAG, "startDebugFunction# ${e.localizedMessage}: ${e.stackTrace.contentDeepToString()}");
            myBaseActivity.showTipDialog("startDebugFunction# ${e.stackTrace.contentDeepToString()}", null, null, null);
        }
    }
}
