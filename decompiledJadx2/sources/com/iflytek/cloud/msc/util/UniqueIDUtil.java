package com.iflytek.cloud.msc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.C3692ad;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.codec.language.Soundex;

/* loaded from: classes3.dex */
public class UniqueIDUtil {
    private static final String CHECK_CODE = "2";
    private static final String FILE_NAME = ".2F6E2C5B63F0F83B";
    private static final String PREFERENCES_KEY = "pref.deviceid.key";
    private static final String PREFERENCES_NAME = "com.iflytek.id";
    private static final String SETTING_KEY = "iflytek.deviceid.key";
    public static boolean sUseWifiMac = true;
    private static final Pattern PATTERN_ID = Pattern.compile("[0-3][0-9a-f]{32}");
    private static String sDeviceID = null;

    public static synchronized String getUniqueID(Context context) {
        String str;
        synchronized (UniqueIDUtil.class) {
            if (sDeviceID == null) {
                sDeviceID = achieveID(context);
            }
            str = sDeviceID;
        }
        return str;
    }

    public static void cleanRecord(Context context) {
        putSettingString(context, SETTING_KEY, "");
        putPreferencesString(context, PREFERENCES_NAME, PREFERENCES_KEY, "");
        new File(Environment.getExternalStorageDirectory() + "/msc", FILE_NAME).delete();
        sDeviceID = null;
    }

    private static String achieveID(final Context context) {
        String settingString = getSettingString(context, SETTING_KEY);
        if (!checkInvalid(settingString)) {
            return settingString;
        }
        final String sDCardString = getSDCardString(context, FILE_NAME);
        if (!checkInvalid(sDCardString)) {
            new Thread(new Runnable() { // from class: com.iflytek.cloud.msc.util.UniqueIDUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    UniqueIDUtil.putSettingString(context, UniqueIDUtil.SETTING_KEY, sDCardString);
                }
            }).start();
            return sDCardString;
        }
        final String preferencesString = getPreferencesString(context, PREFERENCES_NAME, PREFERENCES_KEY, null);
        if (!checkInvalid(preferencesString)) {
            new Thread(new Runnable() { // from class: com.iflytek.cloud.msc.util.UniqueIDUtil.2
                @Override // java.lang.Runnable
                public void run() {
                    UniqueIDUtil.putSettingString(context, UniqueIDUtil.SETTING_KEY, preferencesString);
                    UniqueIDUtil.putSDCardString(context, UniqueIDUtil.FILE_NAME, preferencesString);
                }
            }).start();
            return preferencesString;
        }
        final String createNewID = createNewID(context);
        new Thread(new Runnable() { // from class: com.iflytek.cloud.msc.util.UniqueIDUtil.3
            @Override // java.lang.Runnable
            public void run() {
                UniqueIDUtil.putSettingString(context, UniqueIDUtil.SETTING_KEY, createNewID);
                UniqueIDUtil.putSDCardString(context, UniqueIDUtil.FILE_NAME, createNewID);
                UniqueIDUtil.putPreferencesString(context, UniqueIDUtil.PREFERENCES_NAME, UniqueIDUtil.PREFERENCES_KEY, createNewID);
            }
        }).start();
        return createNewID;
    }

    private static String getSettingString(Context context, String str) {
        try {
            return Settings.System.getString(context.getContentResolver(), str);
        } catch (Exception e) {
            DebugLog.LogS(e);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putSettingString(Context context, String str, String str2) {
        try {
            Settings.System.putString(context.getContentResolver(), str, str2);
        } catch (Exception e) {
            DebugLog.LogS(e);
        }
    }

    private static String getPreferencesString(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            DebugLog.LogS(th);
            return str3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putPreferencesString(Context context, String str, String str2, String str3) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(str2, str3);
                edit.commit();
            }
        } catch (Throwable th) {
            DebugLog.LogS(th);
        }
    }

    private static String getSDCardString(Context context, String str) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            DebugLog.LogD("create file:" + str);
            return readFile(new File(Environment.getExternalStorageDirectory() + "/msc", str));
        } catch (Throwable th) {
            DebugLog.LogS(th);
            return null;
        }
    }

    private static String readFile(File file) {
        try {
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[33];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr, 0, read);
        } catch (Throwable th) {
            DebugLog.LogS(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putSDCardString(Context context, String str, String str2) {
        writeFile(new File(Environment.getExternalStorageDirectory() + "/msc", str), str2);
    }

    private static void writeFile(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (checkSDKVersion(9)) {
                file.getClass().getMethod("setReadable", Boolean.TYPE, Boolean.TYPE).invoke(file, true, false);
            } else {
                Runtime.getRuntime().exec("chmod 444 " + file.getAbsolutePath());
            }
        } catch (Throwable th) {
            DebugLog.LogS(th);
        }
    }

    private static String createNewID(Context context) {
        return "2" + calMD5(createDeviceString(context));
    }

    private static String createDeviceString(Context context) {
        C3692ad appInfo = AppInfoUtil.getAppInfo(context);
        String m1833e = appInfo.m1833e(AppInfoUtil.OS_IMEI);
        String m1833e2 = appInfo.m1833e(AppInfoUtil.OS_ANDROID_ID);
        String m1833e3 = appInfo.m1833e(AppInfoUtil.getMac(context));
        if (m1833e == null && m1833e2 == null && m1833e3 == null) {
            return System.currentTimeMillis() + "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(m1833e);
        sb.append(Soundex.SILENT_MARKER);
        sb.append(m1833e2);
        if (sUseWifiMac) {
            sb.append(Soundex.SILENT_MARKER);
            sb.append(m1833e3);
        }
        return sb.toString();
    }

    private static boolean checkSDKVersion(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    private static final boolean checkInvalid(String str) {
        return str == null || "".equals(str.trim()) || !PATTERN_ID.matcher(str).matches();
    }

    private static String calMD5(String str) {
        try {
            return turnToHexString(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    private static String turnToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }
}
