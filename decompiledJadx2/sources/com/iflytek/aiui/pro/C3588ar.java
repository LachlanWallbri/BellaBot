package com.iflytek.aiui.pro;

import android.content.Context;
import android.media.AudioManager;
import com.iflytek.aiui.constant.InternalConstant;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ar */
/* loaded from: classes.dex */
public class C3588ar {

    /* renamed from: a */
    private static int f2369a;

    /* renamed from: a */
    public static boolean m1056a(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            ((AudioManager) context.getSystemService(InternalConstant.DTYPE_AUDIO)).requestAudioFocus(onAudioFocusChangeListener, 3, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m1057b(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            ((AudioManager) context.getSystemService(InternalConstant.DTYPE_AUDIO)).abandonAudioFocus(onAudioFocusChangeListener);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
