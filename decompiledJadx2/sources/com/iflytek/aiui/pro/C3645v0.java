package com.iflytek.aiui.pro;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

/* renamed from: com.iflytek.aiui.pro.v0 */
/* loaded from: classes4.dex */
public class C3645v0 {

    /* renamed from: a */
    public static int f2702a = 9;

    /* renamed from: a */
    public static boolean m1602a(Context context, Boolean bool, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (!bool.booleanValue() || Build.VERSION.SDK_INT < f2702a) {
            return false;
        }
        C3647w0.m1605a(context, onAudioFocusChangeListener);
        return false;
    }

    /* renamed from: b */
    public static boolean m1603b(Context context, Boolean bool, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        if (!bool.booleanValue() || Build.VERSION.SDK_INT < f2702a) {
            return false;
        }
        return C3647w0.m1606b(context, onAudioFocusChangeListener);
    }
}
