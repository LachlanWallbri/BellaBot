package com.pudutech.tts_sdk;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: TtsConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0015\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\bR\u001a\u0010\u001f\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012R\u001a\u0010\"\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0006\"\u0004\b.\u0010\bR\u001a\u0010/\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0006\"\u0004\b1\u0010\bR\u001a\u00102\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0019\"\u0004\b4\u0010\u001bR\u001a\u00105\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0006\"\u0004\b7\u0010\bR\u001a\u00108\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0006\"\u0004\b:\u0010\b¨\u0006;"}, m3961d2 = {"Lcom/pudutech/tts_sdk/TtsConfig;", "", "()V", "AUDIO_FORMAT", "", "getAUDIO_FORMAT", "()Ljava/lang/String;", "setAUDIO_FORMAT", "(Ljava/lang/String;)V", "CACHE_VERSION", "IFLY_APPID", "getIFLY_APPID", "setIFLY_APPID", "IFLY_SPEECH_PITCH", "", "getIFLY_SPEECH_PITCH", "()I", "setIFLY_SPEECH_PITCH", "(I)V", "IFLY_SPEECH_SPEED", "getIFLY_SPEECH_SPEED", "setIFLY_SPEECH_SPEED", "IFLY_SPEECH_TIME_OUT", "", "getIFLY_SPEECH_TIME_OUT", "()J", "setIFLY_SPEECH_TIME_OUT", "(J)V", "IFLY_SPEECH_VOICE_NAME", "getIFLY_SPEECH_VOICE_NAME", "setIFLY_SPEECH_VOICE_NAME", "IFLY_SPEECH_VOLUME", "getIFLY_SPEECH_VOLUME", "setIFLY_SPEECH_VOLUME", "PCM_PLAY_STREAM_TYPE", "getPCM_PLAY_STREAM_TYPE", "setPCM_PLAY_STREAM_TYPE", "PCM_PLAY_VOLUME", "", "getPCM_PLAY_VOLUME", "()F", "setPCM_PLAY_VOLUME", "(F)V", "TAG", "cachePath", "getCachePath", "setCachePath", "googleTtsFileType", "getGoogleTtsFileType", "setGoogleTtsFileType", "googleTtsTimeout", "getGoogleTtsTimeout", "setGoogleTtsTimeout", "googleTtsVoiceLanguage", "getGoogleTtsVoiceLanguage", "setGoogleTtsVoiceLanguage", "googleTtsVoiceName", "getGoogleTtsVoiceName", "setGoogleTtsVoiceName", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class TtsConfig {
    public static final String CACHE_VERSION = "v1";
    public static final String TAG = "PdTtsSdk";
    public static final TtsConfig INSTANCE = new TtsConfig();
    private static String cachePath = "/sdcard/msc/cache/";
    private static String IFLY_APPID = "5d9c055f";
    private static long IFLY_SPEECH_TIME_OUT = 8000;
    private static int IFLY_SPEECH_SPEED = 50;
    private static int IFLY_SPEECH_PITCH = 50;
    private static int IFLY_SPEECH_VOLUME = 50;
    private static String IFLY_SPEECH_VOICE_NAME = "xiaoyan";
    private static float PCM_PLAY_VOLUME = 1.0f;
    private static int PCM_PLAY_STREAM_TYPE = 4;
    private static String googleTtsVoiceName = "en-US-Wavenet-C";
    private static String googleTtsVoiceLanguage = "en-US";
    private static long googleTtsTimeout = 5;
    private static String googleTtsFileType = "mp3";
    private static String AUDIO_FORMAT = "pcm";

    private TtsConfig() {
    }

    public final String getCachePath() {
        return cachePath;
    }

    public final void setCachePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        cachePath = str;
    }

    public final String getIFLY_APPID() {
        return IFLY_APPID;
    }

    public final void setIFLY_APPID(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        IFLY_APPID = str;
    }

    public final long getIFLY_SPEECH_TIME_OUT() {
        return IFLY_SPEECH_TIME_OUT;
    }

    public final void setIFLY_SPEECH_TIME_OUT(long j) {
        IFLY_SPEECH_TIME_OUT = j;
    }

    public final int getIFLY_SPEECH_SPEED() {
        return IFLY_SPEECH_SPEED;
    }

    public final void setIFLY_SPEECH_SPEED(int i) {
        IFLY_SPEECH_SPEED = i;
    }

    public final int getIFLY_SPEECH_PITCH() {
        return IFLY_SPEECH_PITCH;
    }

    public final void setIFLY_SPEECH_PITCH(int i) {
        IFLY_SPEECH_PITCH = i;
    }

    public final int getIFLY_SPEECH_VOLUME() {
        return IFLY_SPEECH_VOLUME;
    }

    public final void setIFLY_SPEECH_VOLUME(int i) {
        IFLY_SPEECH_VOLUME = i;
    }

    public final String getIFLY_SPEECH_VOICE_NAME() {
        return IFLY_SPEECH_VOICE_NAME;
    }

    public final void setIFLY_SPEECH_VOICE_NAME(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        IFLY_SPEECH_VOICE_NAME = str;
    }

    public final float getPCM_PLAY_VOLUME() {
        return PCM_PLAY_VOLUME;
    }

    public final void setPCM_PLAY_VOLUME(float f) {
        PCM_PLAY_VOLUME = f;
    }

    public final int getPCM_PLAY_STREAM_TYPE() {
        return PCM_PLAY_STREAM_TYPE;
    }

    public final void setPCM_PLAY_STREAM_TYPE(int i) {
        PCM_PLAY_STREAM_TYPE = i;
    }

    public final String getGoogleTtsVoiceName() {
        return googleTtsVoiceName;
    }

    public final void setGoogleTtsVoiceName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        googleTtsVoiceName = str;
    }

    public final String getGoogleTtsVoiceLanguage() {
        return googleTtsVoiceLanguage;
    }

    public final void setGoogleTtsVoiceLanguage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        googleTtsVoiceLanguage = str;
    }

    public final long getGoogleTtsTimeout() {
        return googleTtsTimeout;
    }

    public final void setGoogleTtsTimeout(long j) {
        googleTtsTimeout = j;
    }

    public final String getGoogleTtsFileType() {
        return googleTtsFileType;
    }

    public final void setGoogleTtsFileType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        googleTtsFileType = str;
    }

    public final String getAUDIO_FORMAT() {
        return AUDIO_FORMAT;
    }

    public final void setAUDIO_FORMAT(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        AUDIO_FORMAT = str;
    }
}
