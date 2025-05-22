package com.iflytek.cloud.msc.util;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.view.View;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* loaded from: classes3.dex */
public class FuncAdapterSdk10 {
    private static int avoidValue;

    public void avoidSystemError(int i) {
        DebugLog.LogD("avoidSystemError");
        avoidValue = i;
    }

    public static boolean Lock(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            DebugLog.LogD("start request music_stream focus");
            ((AudioManager) context.getSystemService(InternalConstant.DTYPE_AUDIO)).requestAudioFocus(onAudioFocusChangeListener, 3, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean UnLock(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        try {
            DebugLog.LogD("start abandon audio focus");
            ((AudioManager) context.getSystemService(InternalConstant.DTYPE_AUDIO)).abandonAudioFocus(onAudioFocusChangeListener);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void CloseHardWareAccelerate(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setLayerType(1, null);
        }
    }
}
