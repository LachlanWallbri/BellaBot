package com.pudutech.bumblebee.robot_ui.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.manager.SoundPoolHelper;
import com.pudutech.bumblebee.robot_ui.manager.SystemSoundManager;
import java.io.IOException;

/* loaded from: classes4.dex */
public final class PlaySound {
    private static SoundPoolHelper btnSoundPoolHelper;
    private static Handler handler;
    private static MediaPlayer mMediaPlayer;
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
        } catch (Exception e) {
            Pdlog.m3273d("PlaySound", Log.getStackTraceString(e));
        }
        initBtnPlaySoundIfNeed(context);
        Pdlog.m3273d("PlaySound", "init end");
    }

    public static void playBtnVoice(Context context, int i) {
        Pdlog.m3273d("PlaySound", "playBtnVoice start");
        if (btnSoundPoolHelper == null) {
            return;
        }
        try {
            handler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.util.PlaySound.1
                @Override // java.lang.Runnable
                public void run() {
                    PlaySound.btnSoundPoolHelper.playDefault(SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getBtnVoice() / 100.0f);
                }
            });
            Pdlog.m3273d("PlaySound", "playBtnVoice end");
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    private static void initBtnPlaySoundIfNeed(Context context) {
        if (btnSoundPoolHelper == null) {
            btnSoundPoolHelper = new SoundPoolHelper(1, SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE());
            btnSoundPoolHelper.load(context, "default", C4188R.raw.btn_click_1);
        }
    }

    public static void playEmptyVoice() {
        Pdlog.m3273d("PlaySound", "playEmptyVoice start");
        try {
            play(null, C4188R.raw.empty, 3, 1.0f);
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    public static void playChargingVoice() {
        Pdlog.m3273d("PlaySound", "playChargingVoice start");
        try {
            play(null, C4188R.raw.charge_warning, SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getNOW_AUDIO_TYPE(), SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getBtnVoice() / 100.0f);
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    public static void playGameWinVoice() {
        Pdlog.m3273d("PlaySound", "playGameWinVoice start");
        try {
            play(null, C4188R.raw.game_win, 3, 1.0f);
        } catch (Exception e) {
            Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
        }
    }

    public static void play(Context context, final int i, final int i2, final float f) {
        initIfNeed();
        handler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.util.PlaySound.2
            @Override // java.lang.Runnable
            public void run() {
                Pdlog.m3273d("PlaySound", "play start streamType = " + i2);
                try {
                    MediaPlayer unused = PlaySound.mMediaPlayer = new MediaPlayer();
                    PlaySound.mMediaPlayer.setAudioStreamType(i2);
                    PlaySound.mMediaPlayer.setDataSource(RobotContext.context, Uri.parse("android.resource://" + RobotContext.context.getPackageName() + "/" + i));
                    PlaySound.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.bumblebee.robot_ui.util.PlaySound.2.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Pdlog.m3273d("PlaySound", "onCompletion --- ");
                            try {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                                MediaPlayer unused2 = PlaySound.mMediaPlayer = null;
                            } catch (Exception e) {
                                Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
                            }
                        }
                    });
                    PlaySound.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.pudutech.bumblebee.robot_ui.util.PlaySound.2.2
                        @Override // android.media.MediaPlayer.OnErrorListener
                        public boolean onError(MediaPlayer mediaPlayer, int i3, int i4) {
                            Pdlog.m3273d("PlaySound", "onError --- what: " + i3 + "===extra: " + i4);
                            try {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                                MediaPlayer unused2 = PlaySound.mMediaPlayer = null;
                            } catch (Exception e) {
                                Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
                            }
                            return false;
                        }
                    });
                    PlaySound.mMediaPlayer.setVolume(f, f);
                    PlaySound.mMediaPlayer.prepare();
                    PlaySound.mMediaPlayer.start();
                } catch (IOException e) {
                    Pdlog.m3274e("PlaySound", Log.getStackTraceString(e));
                }
            }
        });
    }
}
