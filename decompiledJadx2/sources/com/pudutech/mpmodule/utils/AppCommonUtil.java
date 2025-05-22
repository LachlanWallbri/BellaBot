package com.pudutech.mpmodule.utils;

import com.pudutech.mpmodule.utils.preferences.CSharedPreferencesUtil;
import com.pudutech.mpmodule.utils.preferences.PreferencesConfig;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class AppCommonUtil {
    public static void setOpenMusicSwitch(boolean z) {
        CSharedPreferencesUtil.getInstance().put(PreferencesConfig.KEY_IS_MUSIC_SWITCH_OPEN, z);
    }

    public static boolean isOpenMusicSwitch() {
        return CSharedPreferencesUtil.getInstance().getBoolean(PreferencesConfig.KEY_IS_MUSIC_SWITCH_OPEN, true);
    }

    public static void switchConnectionPlay(boolean z) {
        CSharedPreferencesUtil.getInstance().put(PreferencesConfig.KEY_IS_CONNECTION_PLAY, z);
    }

    public static boolean isOpenConnectionPlay() {
        return CSharedPreferencesUtil.getInstance().getBoolean(PreferencesConfig.KEY_IS_CONNECTION_PLAY, false);
    }

    public static void setOpenNewYearSwitch(boolean z) {
        CSharedPreferencesUtil.getInstance().put(PreferencesConfig.KEY_IS_NEW_YEAR_SWITCH, z);
    }

    public static boolean isOpenNewYearSwitch() {
        return CSharedPreferencesUtil.getInstance().getBoolean(PreferencesConfig.KEY_IS_NEW_YEAR_SWITCH, true);
    }

    public static void setOpenBirthdaySwitch(boolean z) {
        CSharedPreferencesUtil.getInstance().put(PreferencesConfig.KEY_IS_BIRTHDAY_SWITCH, z);
    }

    public static boolean isOpenBirthdaySwitch() {
        return CSharedPreferencesUtil.getInstance().getBoolean(PreferencesConfig.KEY_IS_BIRTHDAY_SWITCH, false);
    }
}
