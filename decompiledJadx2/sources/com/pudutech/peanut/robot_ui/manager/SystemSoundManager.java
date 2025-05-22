package com.pudutech.peanut.robot_ui.manager;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.peanut.robot_ui.RobotContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemSoundManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/SystemSoundManager;", "", "()V", SystemSoundManager.KEY_AD_VOICE, "", "KEY_BTN_VOICE", "KEY_MUSIC_VOICE", "KEY_SOUND_VOICE", "TAG", "kotlin.jvm.PlatformType", "isInit", "", "soundVoiceHelper", "Lcom/pudutech/peanut/robot_ui/manager/AudioMngHelper;", "getBtnVoiceAndMusicHelper", "getSoundHelper", "init", "", "context", "Landroid/content/Context;", "initVolumeChangeBroadcast", "updateVolume", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SystemSoundManager {
    public static final SystemSoundManager INSTANCE;
    public static final String KEY_AD_VOICE = "KEY_AD_VOICE";
    public static final String KEY_BTN_VOICE = "KEY_BTN_VOICE";
    public static final String KEY_MUSIC_VOICE = "KEY_MUSIC_VOICE";
    public static final String KEY_SOUND_VOICE = "KEY_SOUND_VOICE";
    private static final String TAG;
    private static boolean isInit;
    private static AudioMngHelper soundVoiceHelper;

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
        AudioMngHelper audioMngHelper2 = soundVoiceHelper;
        if (audioMngHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        AudioMngHelper audioMngHelper3 = soundVoiceHelper;
        if (audioMngHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        audioMngHelper2.setVoice(audioMngHelper3.getSystemMaxVolume() - 1);
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        AudioMngHelper audioMngHelper4 = soundVoiceHelper;
        if (audioMngHelper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        musicPlayerHelper.setAudioStreamType(audioMngHelper4.getNOW_AUDIO_TYPE());
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
        AudioMngHelper audioMngHelper = soundVoiceHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        AudioMngHelper audioMngHelper2 = soundVoiceHelper;
        if (audioMngHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        audioMngHelper.setMusicVoice(audioMngHelper2.getMusicVoice());
        AudioMngHelper audioMngHelper3 = soundVoiceHelper;
        if (audioMngHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        AudioMngHelper audioMngHelper4 = soundVoiceHelper;
        if (audioMngHelper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
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
        AudioMngHelper audioMngHelper7 = soundVoiceHelper;
        if (audioMngHelper7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        AudioMngHelper audioMngHelper8 = soundVoiceHelper;
        if (audioMngHelper8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        audioMngHelper7.setAdVoice(audioMngHelper8.getAdVoice());
    }

    public final AudioMngHelper getSoundHelper() {
        AudioMngHelper audioMngHelper = soundVoiceHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        return audioMngHelper;
    }

    public final AudioMngHelper getBtnVoiceAndMusicHelper() {
        AudioMngHelper audioMngHelper = soundVoiceHelper;
        if (audioMngHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("soundVoiceHelper");
        }
        return audioMngHelper;
    }
}
