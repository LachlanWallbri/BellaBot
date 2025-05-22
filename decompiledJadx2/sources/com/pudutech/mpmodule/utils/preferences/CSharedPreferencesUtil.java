package com.pudutech.mpmodule.utils.preferences;

import android.content.SharedPreferences;
import com.pudutech.mpmodule.MusicPlayerApp;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CSharedPreferencesUtil {
    private SharedPreferences sPreferences;

    private CSharedPreferencesUtil() {
        this.sPreferences = MusicPlayerApp.getInstance().getContext().getSharedPreferences("prefs_common", 0);
    }

    public static CSharedPreferencesUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static final CSharedPreferencesUtil INSTANCE = new CSharedPreferencesUtil();

        private SingletonHolder() {
        }
    }

    public void put(String str, String str2) {
        SharedPreferences.Editor edit = this.sPreferences.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public void put(String str, long j) {
        SharedPreferences.Editor edit = this.sPreferences.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public void put(String str, int i) {
        SharedPreferences.Editor edit = this.sPreferences.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public void put(String str, float f) {
        SharedPreferences.Editor edit = this.sPreferences.edit();
        edit.putFloat(str, f);
        edit.apply();
    }

    public void put(String str, boolean z) {
        SharedPreferences.Editor edit = this.sPreferences.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public String getString(String str, String str2) {
        return this.sPreferences.getString(str, str2);
    }

    public long getLong(String str) {
        return getLong(str, 0L);
    }

    public long getLong(String str, long j) {
        return this.sPreferences.getLong(str, j);
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public int getInt(String str, int i) {
        return this.sPreferences.getInt(str, i);
    }

    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    public float getFloat(String str, float f) {
        return this.sPreferences.getFloat(str, f);
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.sPreferences.getBoolean(str, z);
    }
}
