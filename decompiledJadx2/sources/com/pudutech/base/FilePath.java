package com.pudutech.base;

import android.os.Environment;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class FilePath {
    public static final String SDCARD = Environment.getExternalStorageDirectory().getPath();
    public static final String WELCOME_LOGO_DIR = SDCARD + "/PuduRobotMedia/logo";
    public static final String WELCOME_LOGO = WELCOME_LOGO_DIR + "/logo.png";
    public static final String CUSTOM_WELCOME_LOGO = SDCARD + "/PuduLogo/logo.png";
    public static final String CUSTOM_DEBUG_LOGIN_LOGO = SDCARD + "/PuduLogo/login_logo.png";
    public static final String RPLIDAR_A3_CONFIG = SDCARD + "/RPLIDAR_A3/Config.json";
    public static final String VOICE = SDCARD + "/PuduRobotMedia";
    public static final String CUSTOM_VOICE = SDCARD + "/PuduVoice";
    public static final String CUSTOM_MUSIC = SDCARD + "/PuduMusic";
}
