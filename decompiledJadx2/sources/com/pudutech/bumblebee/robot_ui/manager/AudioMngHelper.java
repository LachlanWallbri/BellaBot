package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.mpmodule.MusicPlayerHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioMngHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\"\u0018\u0000 32\u00020\u0001:\u0003345B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0019\u001a\u00020\u0006J\u0006\u0010\u001a\u001a\u00020\u0000J\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001c\u001a\u00020\u0006J\u0006\u0010\u001d\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u0006J\u0006\u0010\u001f\u001a\u00020\u0006J\u0006\u0010 \u001a\u00020\u0006J\u0006\u0010!\u001a\u00020\u0006J\u0006\u0010\"\u001a\u00020\u0006J\u0006\u0010#\u001a\u00020\u0014J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u0006J\u000e\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0006J\u000e\u0010*\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010+\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010,\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010-\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u0006J\u0006\u00100\u001a\u00020\u0006J\u0006\u00101\u001a\u00020\u0000J\u000e\u00102\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR7\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00066"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AudioMngHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "NOW_AUDIO_TYPE", "", "NOW_FLAG", "TAG", "", "VOICE_STEP_100", "audioManager", "Landroid/media/AudioManager;", "getContext", "()Landroid/content/Context;", "onChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "size", "", "getOnChange", "()Lkotlin/jvm/functions/Function1;", "setOnChange", "(Lkotlin/jvm/functions/Function1;)V", "addVoice100", "addVoiceSystem", "get100CurrentVolume", "getAudioType", "getBtnVoice", "getMusicVoice", "getSoundVocie", "getSystemCurrentVolume", "getSystemMaxVolume", "getTtsSoundVoice", "keepVolumeMax", "setAudioType", "type", "setBtnVoice", "num", "setFlag", "flag", "setMusicVoice", "setSoundVoice", "setTtsSoundVoice", "setVoice", "setVoiceStep100", "step", "subVoice100", "subVoiceSystem", "voice100ToSystem", "Companion", "FLAG", "TYPE", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AudioMngHelper {
    public static final int FLAG_NOTHING = 0;
    public static final int FLAG_PLAY_SOUND = 4;
    public static final int FLAG_SHOW_UI = 1;
    public static final int TYPE_ALARM = 4;
    public static final int TYPE_MUSIC = 3;
    public static final int TYPE_NOTIFICATION = 5;
    public static final int TYPE_RING = 2;
    private int NOW_AUDIO_TYPE;
    private int NOW_FLAG;
    private final String TAG;
    private int VOICE_STEP_100;
    private AudioManager audioManager;
    private final Context context;
    private Function1<? super Integer, Unit> onChange;

    /* compiled from: AudioMngHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AudioMngHelper$FLAG;", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    /* loaded from: classes3.dex */
    public @interface FLAG {
    }

    /* compiled from: AudioMngHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AudioMngHelper$TYPE;", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    /* loaded from: classes3.dex */
    public @interface TYPE {
    }

    public AudioMngHelper(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "AudioMngHelper";
        Object systemService = this.context.getSystemService(InternalConstant.DTYPE_AUDIO);
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
        }
        this.audioManager = (AudioManager) systemService;
        this.NOW_AUDIO_TYPE = 3;
        this.VOICE_STEP_100 = 2;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Function1<Integer, Unit> getOnChange() {
        return this.onChange;
    }

    public final void setOnChange(Function1<? super Integer, Unit> function1) {
        this.onChange = function1;
    }

    public final int getSystemMaxVolume() {
        return this.audioManager.getStreamMaxVolume(this.NOW_AUDIO_TYPE);
    }

    public final int getSystemCurrentVolume() {
        return this.audioManager.getStreamVolume(this.NOW_AUDIO_TYPE);
    }

    public final int get100CurrentVolume() {
        return (getSystemCurrentVolume() * 100) / getSystemMaxVolume();
    }

    /* renamed from: getAudioType, reason: from getter */
    public final int getNOW_AUDIO_TYPE() {
        return this.NOW_AUDIO_TYPE;
    }

    public final AudioMngHelper setAudioType(int type) {
        this.NOW_AUDIO_TYPE = type;
        return this;
    }

    public final AudioMngHelper setVoiceStep100(int step) {
        this.VOICE_STEP_100 = step;
        return this;
    }

    public final AudioMngHelper setFlag(int flag) {
        this.NOW_FLAG = flag;
        return this;
    }

    public final AudioMngHelper addVoiceSystem() {
        this.audioManager.adjustStreamVolume(this.NOW_AUDIO_TYPE, 1, this.NOW_FLAG);
        return this;
    }

    public final AudioMngHelper subVoiceSystem() {
        this.audioManager.adjustStreamVolume(this.NOW_AUDIO_TYPE, -1, this.NOW_FLAG);
        return this;
    }

    public final void keepVolumeMax() {
        Pdlog.m3273d(this.TAG, "keepVolumeMax");
        int systemMaxVolume = getSystemMaxVolume();
        if (systemMaxVolume != getSystemCurrentVolume()) {
            Pdlog.m3273d(this.TAG, "current volume for stream type " + this.NOW_AUDIO_TYPE + " is inconsistent with max volume, try reset. ");
            setVoice(systemMaxVolume);
        }
    }

    public final void setVoice(int num) {
        try {
            this.audioManager.setStreamVolume(this.NOW_AUDIO_TYPE, num, 0);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    public final void setSoundVoice(int num) {
        int voice100ToSystem = voice100ToSystem(num);
        Pdlog.m3273d(this.TAG, "setSoundVoice stream type: " + this.NOW_AUDIO_TYPE + " , soundVoice to save: " + num + ", volume index to save = " + voice100ToSystem + ", current volume index in system: " + getSystemCurrentVolume());
        try {
            this.audioManager.setStreamVolume(this.NOW_AUDIO_TYPE, voice100ToSystem, 0);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
        SpUtils.set(RobotContext.INSTANCE.getContext(), "KEY_SOUND_VOICE", num);
    }

    public final void setTtsSoundVoice(int num) {
        int voice100ToSystem = voice100ToSystem(num);
        Pdlog.m3273d(this.TAG, "setTtsSoundVoice stream type: " + this.NOW_AUDIO_TYPE + " , soundVoice to save: " + num + ", volume index to save = " + voice100ToSystem + ", current volume index in system: " + getSystemCurrentVolume());
        try {
            this.audioManager.setStreamVolume(this.NOW_AUDIO_TYPE, voice100ToSystem, 0);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
        SpUtils.set(RobotContext.INSTANCE.getContext(), SystemSoundManager.KEY_TTS_SOUND_VOICE, num);
        TtsVoiceHelper.INSTANCE.updateVolume(num);
    }

    public final void setBtnVoice(int num) {
        Pdlog.m3273d(this.TAG, "setBtnVoice num to save: " + num);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "KEY_BTN_VOICE", num);
    }

    public final void setMusicVoice(int num) {
        Pdlog.m3273d(this.TAG, "setMusicVoice num to save: " + num);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "KEY_MUSIC_VOICE", num);
        float f = ((float) num) / 100.0f;
        MusicPlayerHelper.getInstance().setVolume(f, f);
    }

    public final int getSoundVocie() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "KEY_SOUND_VOICE", 70);
    }

    public final int getTtsSoundVoice() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), SystemSoundManager.KEY_TTS_SOUND_VOICE, getSoundVocie());
    }

    public final int getBtnVoice() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "KEY_BTN_VOICE", 70);
    }

    public final int getMusicVoice() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "KEY_MUSIC_VOICE", 70);
    }

    public final int voice100ToSystem(int num) {
        int ceil = (int) Math.ceil(num * getSystemMaxVolume() * 0.01d);
        if (ceil <= 0) {
            ceil = 0;
        }
        if (ceil >= 100) {
            return 100;
        }
        return ceil;
    }

    public final int addVoice100() {
        int ceil = (int) Math.ceil((this.VOICE_STEP_100 + get100CurrentVolume()) * getSystemMaxVolume() * 0.01d);
        if (ceil <= 0) {
            ceil = 0;
        }
        if (ceil >= 100) {
            ceil = 100;
        }
        this.audioManager.setStreamVolume(this.NOW_AUDIO_TYPE, ceil, this.NOW_FLAG);
        return get100CurrentVolume();
    }

    public final int subVoice100() {
        int floor = (int) Math.floor((get100CurrentVolume() - this.VOICE_STEP_100) * getSystemMaxVolume() * 0.01d);
        if (floor <= 0) {
            floor = 0;
        }
        if (floor >= 100) {
            floor = 100;
        }
        this.audioManager.setStreamVolume(this.NOW_AUDIO_TYPE, floor, this.NOW_FLAG);
        return get100CurrentVolume();
    }
}
