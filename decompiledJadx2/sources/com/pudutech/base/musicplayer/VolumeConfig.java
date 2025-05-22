package com.pudutech.base.musicplayer;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class VolumeConfig {
    private static final String FILE_NAME = "VolumeSet";
    public static final String KEY_MUSIC = "MusicVolume";
    public static final String KEY_VOICE = "VoiceVolume";

    public static void set(Context context, String str, float f) {
        getPreferences(context).edit().putFloat(str, f).commit();
    }

    public static float get(Context context, String str, float f) {
        return getPreferences(context).getFloat(str, f);
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(FILE_NAME, 0);
    }
}
