package com.pudutech.bumblebee.presenter.utils;

import android.content.Context;
import android.provider.Settings;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: SoundPoolVoiceUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/SoundPoolVoiceUtil;", "", "()V", "TAG", "", "soundPoolHelper", "Lcom/pudutech/bumblebee/presenter/utils/SoundPoolHelper;", "soundPoolVolume", "", "getSoundPoolVolume", "()F", "setSoundPoolVolume", "(F)V", "loadPoolRes", "", "context", "Landroid/content/Context;", "streamType", "", "play", "voice", "Lcom/pudutech/bumblebee/presenter/utils/SoundPoolVoiceUtil$Voice;", "volume", "Voice", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SoundPoolVoiceUtil {
    private static SoundPoolHelper soundPoolHelper;
    public static final SoundPoolVoiceUtil INSTANCE = new SoundPoolVoiceUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static float soundPoolVolume = 1.0f;

    private SoundPoolVoiceUtil() {
    }

    public final float getSoundPoolVolume() {
        return soundPoolVolume;
    }

    public final void setSoundPoolVolume(float f) {
        soundPoolVolume = f;
    }

    /* compiled from: SoundPoolVoiceUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/SoundPoolVoiceUtil$Voice;", "", "voiceName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getVoiceName", "()Ljava/lang/String;", "CLICK_1", "CLICK_2", "EMPTY", "ERROR", "SCAN", "SCAN_FAILED", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Voice {
        CLICK_1("CLICK_1"),
        CLICK_2("CLICK_2"),
        EMPTY("EMPTY"),
        ERROR("ERROR"),
        SCAN("SCAN"),
        SCAN_FAILED("SCAN_FAILED");

        private final String voiceName;

        Voice(String str) {
            this.voiceName = str;
        }

        public final String getVoiceName() {
            return this.voiceName;
        }
    }

    public final void loadPoolRes(Context context, int streamType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(TAG, "init start");
        try {
            Settings.System.putInt(context.getContentResolver(), "sound_effects_enabled", 0);
        } catch (Exception unused) {
        }
        soundPoolHelper = new SoundPoolHelper(20, streamType);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SoundPoolVoiceUtil$loadPoolRes$1(context, null), 2, null);
    }

    public final void play(Voice voice, float volume) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        Pdlog.m3273d(TAG, "play : voice = " + voice + "; volume = " + volume + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SoundPoolVoiceUtil$play$1(voice, volume, null), 2, null);
    }

    public final void play(Voice voice) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        play(voice, soundPoolVolume);
    }
}
