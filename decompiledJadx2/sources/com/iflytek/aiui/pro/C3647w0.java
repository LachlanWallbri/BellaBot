package com.iflytek.aiui.pro;

import android.content.Context;
import android.media.AudioManager;
import com.iflytek.aiui.constant.InternalConstant;

/* renamed from: com.iflytek.aiui.pro.w0 */
/* loaded from: classes4.dex */
public class C3647w0 {
    /* renamed from: a */
    public static boolean m1605a(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            ((AudioManager) context.getSystemService(InternalConstant.DTYPE_AUDIO)).requestAudioFocus(onAudioFocusChangeListener, 3, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m1606b(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            ((AudioManager) context.getSystemService(InternalConstant.DTYPE_AUDIO)).abandonAudioFocus(onAudioFocusChangeListener);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
