package com.pudutech.peanut.robot_ui.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.manager.SoundPoolHelper;
import com.pudutech.peanut.robot_ui.manager.SystemSoundManager;
import java.io.IOException;

/* loaded from: classes5.dex */
public final class PlaySound {
    private static SoundPoolHelper btnSoundPoolHelper;
    private static Handler handler;
    private static HandlerThread threadHandle;

    private static synchronized void initIfNeed() {
        synchronized (PlaySound.class) {
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

    public static void init(Context context) {
        Pdlog.m3273d("PlaySound", "init start");
        try {
            Settings.System.putInt(context.getContentResolver(), "sound_effects_enabled", 0);
        } catch (Exception unused) {
        }
        initBtnPlaySoundIfNeed(context);
        Pdlog.m3273d("PlaySound", "init end");
    }

    public static void playBtnVoice(Context context, int i) {
        Pdlog.m3273d("PlaySound", "playBtnVoice start");
        SoundPoolHelper soundPoolHelper = btnSoundPoolHelper;
        if (soundPoolHelper == null) {
            return;
        }
        try {
            soundPoolHelper.playDefault(SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getBtnVoice() / 100.0f);
            Pdlog.m3273d("PlaySound", "playBtnVoice end");
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    private static void initBtnPlaySoundIfNeed(Context context) {
        if (btnSoundPoolHelper == null) {
            btnSoundPoolHelper = new SoundPoolHelper(1, SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE());
            btnSoundPoolHelper.load(context, "default", C5508R.raw.btn_click_1);
        }
    }

    public static void playEmptyVoice() {
        Pdlog.m3273d("PlaySound", "playEmptyVoice start");
        try {
            play(null, C5508R.raw.empty, 3, 1.0f);
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    public static void playChargingVoice() {
        Pdlog.m3273d("PlaySound", "playChargingVoice start");
        try {
            play(null, C5508R.raw.charge_warning, SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE(), SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getBtnVoice() / 100.0f);
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    public static void play(Context context, final int i, final int i2, final float f) {
        initIfNeed();
        handler.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.util.PlaySound.1
            @Override // java.lang.Runnable
            public void run() {
                Pdlog.m3273d("PlaySound", "play start streamType = " + i2);
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(i2);
                    mediaPlayer.setDataSource(RobotContext.context, Uri.parse("android.resource://" + RobotContext.context.getPackageName() + "/" + i));
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.peanut.robot_ui.util.PlaySound.1.1
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
