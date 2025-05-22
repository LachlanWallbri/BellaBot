package com.pudutech.robot.module.voice.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.voice.C5698R;
import java.io.IOException;

/* loaded from: classes6.dex */
public final class SoundPlayUtils {
    private static SoundPoolHelper btnSoundPoolHelper = null;
    static float btnVolume = 1.0f;
    private static Context context;
    private static Handler handler;
    private static HandlerThread threadHandle;

    private static synchronized void initIfNeed() {
        synchronized (SoundPlayUtils.class) {
            if (threadHandle == null || !threadHandle.isAlive()) {
                if (threadHandle != null) {
                    try {
                        threadHandle.quit();
                    } catch (Exception unused) {
                    }
                }
                threadHandle = new HandlerThread("PlaySound");
                threadHandle.start();
                handler = new Handler(threadHandle.getLooper());
            }
        }
    }

    public static void init(Context context2) {
        Pdlog.m3273d("PlaySound", "init start");
        context = context2;
        try {
            Settings.System.putInt(context.getContentResolver(), "sound_effects_enabled", 0);
        } catch (Exception unused) {
        }
        initBtnPlaySoundIfNeed(context);
        Pdlog.m3273d("PlaySound", "init end");
    }

    public static void playBtnVoice() {
        Pdlog.m3273d("PlaySound", "playBtnVoice start");
        SoundPoolHelper soundPoolHelper = btnSoundPoolHelper;
        if (soundPoolHelper == null) {
            return;
        }
        try {
            soundPoolHelper.playDefault(btnVolume);
            Pdlog.m3273d("PlaySound", "playBtnVoice end");
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    private static void initBtnPlaySoundIfNeed(Context context2) {
        if (btnSoundPoolHelper == null) {
            btnSoundPoolHelper = new SoundPoolHelper(1, 3);
            btnSoundPoolHelper.load(context2, "default", C5698R.raw.btn_click_1);
        }
    }

    public static void playEmptyVoice() {
        Pdlog.m3273d("PlaySound", "playEmptyVoice start");
        try {
            play(C5698R.raw.empty, 3, 1.0f);
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    public static void playChargingVoice(float f) {
        Pdlog.m3273d("PlaySound", "playChargingVoice start");
        try {
            play(C5698R.raw.charge_warning, 3, f);
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    public static void play(final int i, final int i2, final float f) {
        initIfNeed();
        handler.post(new Runnable() { // from class: com.pudutech.robot.module.voice.utils.SoundPlayUtils.1
            @Override // java.lang.Runnable
            public void run() {
                Pdlog.m3273d("PlaySound", "play start streamType = " + i2);
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(i2);
                    mediaPlayer.setDataSource(SoundPlayUtils.context, Uri.parse("android.resource://" + SoundPlayUtils.context.getPackageName() + "/" + i));
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.robot.module.voice.utils.SoundPlayUtils.1.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer2) {
                            Pdlog.m3273d("PlaySound", "onCompletion --- ");
                            try {
                                mediaPlayer2.stop();
                                mediaPlayer2.release();
                            } catch (Exception e) {
                                Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
                            }
                        }
                    });
                    mediaPlayer.setVolume(f, f);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
                }
            }
        });
    }
}
