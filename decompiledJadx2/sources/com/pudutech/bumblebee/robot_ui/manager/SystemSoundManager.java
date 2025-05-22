package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.base.musicplayer.VolumeConfig;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.mpmodule.MusicPlayerHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemSoundManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u000bJ\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u000bJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/SystemSoundManager;", "", "()V", "KEY_BTN_VOICE", "", "KEY_MUSIC_VOICE", "KEY_SOUND_VOICE", SystemSoundManager.KEY_TTS_SOUND_VOICE, "TAG", "kotlin.jvm.PlatformType", "btnVoiceAndMusicHelper", "Lcom/pudutech/bumblebee/robot_ui/manager/AudioMngHelper;", "isInit", "", "soundVoiceHelper", "ttsVoiceHelper", "getBtnVoiceAndMusicHelper", "getSoundHelper", "getTtsSoundHelper", "init", "", "context", "Landroid/content/Context;", "initVolumeChangeBroadcast", "updateVolume", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SystemSoundManager {
    public static final SystemSoundManager INSTANCE;
    public static final String KEY_BTN_VOICE = "KEY_BTN_VOICE";
    public static final String KEY_MUSIC_VOICE = "KEY_MUSIC_VOICE";
    public static final String KEY_SOUND_VOICE = "KEY_SOUND_VOICE";
    public static final String KEY_TTS_SOUND_VOICE = "KEY_TTS_SOUND_VOICE";
    private static final String TAG;
    private static AudioMngHelper btnVoiceAndMusicHelper;
    private static boolean isInit;
    private static AudioMngHelper soundVoiceHelper;
    private static AudioMngHelper ttsVoiceHelper;

    static {
        SystemSoundManager systemSoundManager = new SystemSoundManager();
        INSTANCE = systemSoundManager;
        TAG = systemSoundManager.getClass().getSimpleName();
    }

    private SystemSoundManager() {
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        isInit = true;
        soundVoiceHelper = new AudioMngHelper(context);
        AudioMngHelper audioMngHelper = soundVoiceHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        audioMngHelper.setAudioType(3);
        ttsVoiceHelper = new AudioMngHelper(context);
        AudioMngHelper audioMngHelper2 = ttsVoiceHelper;
        if (audioMngHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceHelper");
        }
        audioMngHelper2.setAudioType(4);
        btnVoiceAndMusicHelper = new AudioMngHelper(context);
        AudioMngHelper audioMngHelper3 = btnVoiceAndMusicHelper;
        if (audioMngHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        audioMngHelper3.setAudioType(2);
        AudioMngHelper audioMngHelper4 = btnVoiceAndMusicHelper;
        if (audioMngHelper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        AudioMngHelper audioMngHelper5 = btnVoiceAndMusicHelper;
        if (audioMngHelper5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        audioMngHelper4.setVoice(audioMngHelper5.getSystemMaxVolume());
        VoicePlayer.INSTANCE.setVolume(1.0f);
        VolumeConfig.set(RobotContext.INSTANCE.getContext(), VolumeConfig.KEY_VOICE, 1.0f);
        VolumeConfig.set(RobotContext.INSTANCE.getContext(), VolumeConfig.KEY_MUSIC, 1.0f);
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        AudioMngHelper audioMngHelper6 = btnVoiceAndMusicHelper;
        if (audioMngHelper6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        musicPlayerHelper.setAudioStreamType(audioMngHelper6.getNOW_AUDIO_TYPE());
        updateVolume();
        initVolumeChangeBroadcast();
    }

    private final void initVolumeChangeBroadcast() {
        Pdlog.m3273d(TAG, "initVolumeChangeBroadcast");
        VolumeBroadcastReceiver volumeBroadcastReceiver = new VolumeBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        intentFilter.setPriority(Integer.MAX_VALUE);
        try {
            RobotContext.INSTANCE.getContext().registerReceiver(volumeBroadcastReceiver, intentFilter);
        } catch (Exception e) {
            Pdlog.m3273d(TAG, Log.getStackTraceString(e));
        }
    }

    private final void updateVolume() {
        AudioMngHelper audioMngHelper = btnVoiceAndMusicHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        AudioMngHelper audioMngHelper2 = btnVoiceAndMusicHelper;
        if (audioMngHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        audioMngHelper.setMusicVoice(audioMngHelper2.getMusicVoice());
        AudioMngHelper audioMngHelper3 = btnVoiceAndMusicHelper;
        if (audioMngHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        AudioMngHelper audioMngHelper4 = btnVoiceAndMusicHelper;
        if (audioMngHelper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        audioMngHelper3.setBtnVoice(audioMngHelper4.getBtnVoice());
        AudioMngHelper audioMngHelper5 = soundVoiceHelper;
        if (audioMngHelper5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        AudioMngHelper audioMngHelper6 = soundVoiceHelper;
        if (audioMngHelper6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        audioMngHelper5.setSoundVoice(audioMngHelper6.getSoundVocie());
        AudioMngHelper audioMngHelper7 = ttsVoiceHelper;
        if (audioMngHelper7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceHelper");
        }
        AudioMngHelper audioMngHelper8 = ttsVoiceHelper;
        if (audioMngHelper8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceHelper");
        }
        audioMngHelper7.setTtsSoundVoice(audioMngHelper8.getTtsSoundVoice());
    }

    public final AudioMngHelper getSoundHelper() {
        AudioMngHelper audioMngHelper = soundVoiceHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        return audioMngHelper;
    }

    public final AudioMngHelper getTtsSoundHelper() {
        AudioMngHelper audioMngHelper = ttsVoiceHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceHelper");
        }
        return audioMngHelper;
    }

    public final AudioMngHelper getBtnVoiceAndMusicHelper() {
        AudioMngHelper audioMngHelper = btnVoiceAndMusicHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnVoiceAndMusicHelper");
        }
        return audioMngHelper;
    }
}
