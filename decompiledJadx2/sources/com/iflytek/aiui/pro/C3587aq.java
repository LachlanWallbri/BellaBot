package com.iflytek.aiui.pro;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.aq */
/* loaded from: classes.dex */
public class C3587aq {

    /* renamed from: a */
    public static int f2367a = 9;

    /* renamed from: b */
    public static int f2368b = 14;

    /* renamed from: a */
    public static boolean m1054a(Context context, Boolean bool, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (!bool.booleanValue() || Build.VERSION.SDK_INT < f2367a) {
            return false;
        }
        C3588ar.m1056a(context, onAudioFocusChangeListener);
        return false;
    }

    /* renamed from: b */
    public static boolean m1055b(Context context, Boolean bool, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (!bool.booleanValue() || Build.VERSION.SDK_INT < f2367a) {
            return false;
        }
        return C3588ar.m1057b(context, onAudioFocusChangeListener);
    }
}
