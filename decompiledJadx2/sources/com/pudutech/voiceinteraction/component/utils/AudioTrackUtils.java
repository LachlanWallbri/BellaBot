package com.pudutech.voiceinteraction.component.utils;

import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.protobuf.ByteString;
import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.utils.AudioTrackUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: AudioTrackUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\fJ5\u0010\u0010\u001a\u00020\u00112#\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00020\u0014H\u0002J5\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2%\b\u0002\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013J\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0011J\u0006\u0010\u001f\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/AudioTrackUtils;", "", "()V", "TAG", "", "audioTrack", "Landroid/media/AudioTrack;", "bufferSize", "", "executor", "Ljava/util/concurrent/Executor;", "isStop", "", "mainHander", "Landroid/os/Handler;", "isPlaying", "notify", "", "listener", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/utils/AudioTrackUtils$AudioPlayEvent;", "Lkotlin/ParameterName;", "name", "event", "play", "audioData", "Lcom/google/protobuf/ByteString;", "setVolume", "v", "", "stop", "volumeSlowDownStop", "AudioPlayEvent", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class AudioTrackUtils {
    private static AudioTrack audioTrack;
    private static final int bufferSize;
    private static final Executor executor;
    private static volatile boolean isStop;
    private static final Handler mainHander;
    public static final AudioTrackUtils INSTANCE = new AudioTrackUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* compiled from: AudioTrackUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/AudioTrackUtils$AudioPlayEvent;", "", "(Ljava/lang/String;I)V", "STOP", "COMPLETE", "PLAYING", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public enum AudioPlayEvent {
        STOP,
        COMPLETE,
        PLAYING
    }

    static {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkExpressionValueIsNotNull(newSingleThreadExecutor, "Executors.newSingleThreadExecutor()");
        executor = newSingleThreadExecutor;
        bufferSize = AudioTrack.getMinBufferSize(24000, 4, 2);
        mainHander = new Handler(Looper.getMainLooper());
    }

    private AudioTrackUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void play$default(AudioTrackUtils audioTrackUtils, ByteString byteString, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        audioTrackUtils.play(byteString, function1);
    }

    public final void play(final ByteString audioData, final Function1<? super AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(audioData, "audioData");
        isStop = true;
        executor.execute(new Runnable() { // from class: com.pudutech.voiceinteraction.component.utils.AudioTrackUtils$play$1
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                String str;
                boolean z;
                String str2;
                AudioTrack audioTrack2;
                String str3;
                AudioTrack audioTrack3;
                AudioTrack audioTrack4;
                String str4;
                String str5;
                boolean z2;
                String str6;
                AudioTrack audioTrack5;
                String str7;
                AudioTrack audioTrack6;
                AudioTrack audioTrack7;
                AudioTrack audioTrack8;
                AudioTrack audioTrack9;
                AudioTrack audioTrack10;
                boolean z3;
                String str8;
                boolean z4;
                String str9;
                AudioTrack audioTrack11;
                String str10;
                AudioTrack audioTrack12;
                AudioTrack audioTrack13;
                AudioTrack audioTrack14;
                AudioTrack audioTrack15;
                AudioTrackUtils audioTrackUtils = AudioTrackUtils.INSTANCE;
                AudioTrackUtils audioTrackUtils2 = AudioTrackUtils.INSTANCE;
                i = AudioTrackUtils.bufferSize;
                AudioTrackUtils.audioTrack = new AudioTrack(3, 24000, 4, 2, i, 1);
                AudioTrackUtils audioTrackUtils3 = AudioTrackUtils.INSTANCE;
                AudioTrackUtils.isStop = false;
                try {
                    try {
                        AudioTrackUtils audioTrackUtils4 = AudioTrackUtils.INSTANCE;
                        audioTrack8 = AudioTrackUtils.audioTrack;
                        if (audioTrack8 != null) {
                            audioTrack8.setVolume(VoiceCommentConfig.INSTANCE.getTtsVolume() / 100.0f);
                        }
                        AudioTrackUtils audioTrackUtils5 = AudioTrackUtils.INSTANCE;
                        audioTrack9 = AudioTrackUtils.audioTrack;
                        if (audioTrack9 != null) {
                            audioTrack9.play();
                        }
                        AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.PLAYING);
                        AudioTrackUtils audioTrackUtils6 = AudioTrackUtils.INSTANCE;
                        audioTrack10 = AudioTrackUtils.audioTrack;
                        if (audioTrack10 != null) {
                            audioTrack10.write(audioData.toByteArray(), 44, audioData.toByteArray().length - 44);
                        }
                        AudioTrackUtils audioTrackUtils7 = AudioTrackUtils.INSTANCE;
                        z3 = AudioTrackUtils.isStop;
                        if (!z3) {
                            AudioTrackUtils audioTrackUtils8 = AudioTrackUtils.INSTANCE;
                            audioTrack14 = AudioTrackUtils.audioTrack;
                            if (audioTrack14 != null) {
                                audioTrack14.stop();
                            }
                            AudioTrackUtils audioTrackUtils9 = AudioTrackUtils.INSTANCE;
                            audioTrack15 = AudioTrackUtils.audioTrack;
                            if (audioTrack15 != null) {
                                audioTrack15.release();
                            }
                            AudioTrackUtils audioTrackUtils10 = AudioTrackUtils.INSTANCE;
                            AudioTrackUtils.audioTrack = (AudioTrack) null;
                            Thread.sleep(100L);
                            AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.COMPLETE);
                        }
                        AudioTrackUtils audioTrackUtils11 = AudioTrackUtils.INSTANCE;
                        str8 = AudioTrackUtils.TAG;
                        Pdlog.m3273d(str8, "AudioTrackUtils play finally");
                        AudioTrackUtils audioTrackUtils12 = AudioTrackUtils.INSTANCE;
                        z4 = AudioTrackUtils.isStop;
                        if (z4) {
                            try {
                                AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                            } catch (Exception e) {
                                AudioTrackUtils audioTrackUtils13 = AudioTrackUtils.INSTANCE;
                                str9 = AudioTrackUtils.TAG;
                                Pdlog.m3274e(str9, Log.getStackTraceString(e));
                            }
                        }
                        AudioTrackUtils audioTrackUtils14 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.isStop = false;
                        AudioTrackUtils audioTrackUtils15 = AudioTrackUtils.INSTANCE;
                        audioTrack11 = AudioTrackUtils.audioTrack;
                    } catch (Throwable th) {
                        AudioTrackUtils audioTrackUtils16 = AudioTrackUtils.INSTANCE;
                        str = AudioTrackUtils.TAG;
                        Pdlog.m3273d(str, "AudioTrackUtils play finally");
                        AudioTrackUtils audioTrackUtils17 = AudioTrackUtils.INSTANCE;
                        z = AudioTrackUtils.isStop;
                        if (z) {
                            try {
                                AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                            } catch (Exception e2) {
                                AudioTrackUtils audioTrackUtils18 = AudioTrackUtils.INSTANCE;
                                str2 = AudioTrackUtils.TAG;
                                Pdlog.m3274e(str2, Log.getStackTraceString(e2));
                            }
                        }
                        AudioTrackUtils audioTrackUtils19 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.isStop = false;
                        AudioTrackUtils audioTrackUtils20 = AudioTrackUtils.INSTANCE;
                        audioTrack2 = AudioTrackUtils.audioTrack;
                        if (audioTrack2 == null) {
                            throw th;
                        }
                        try {
                            AudioTrackUtils audioTrackUtils21 = AudioTrackUtils.INSTANCE;
                            audioTrack3 = AudioTrackUtils.audioTrack;
                            if (audioTrack3 != null) {
                                audioTrack3.stop();
                            }
                            AudioTrackUtils audioTrackUtils22 = AudioTrackUtils.INSTANCE;
                            audioTrack4 = AudioTrackUtils.audioTrack;
                            if (audioTrack4 != null) {
                                audioTrack4.release();
                            }
                        } catch (Exception e3) {
                            AudioTrackUtils audioTrackUtils23 = AudioTrackUtils.INSTANCE;
                            str3 = AudioTrackUtils.TAG;
                            Pdlog.m3274e(str3, Log.getStackTraceString(e3));
                        }
                        AudioTrackUtils audioTrackUtils24 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.audioTrack = (AudioTrack) null;
                        throw th;
                    }
                } catch (Exception e4) {
                    AudioTrackUtils audioTrackUtils25 = AudioTrackUtils.INSTANCE;
                    str4 = AudioTrackUtils.TAG;
                    Pdlog.m3274e(str4, Log.getStackTraceString(e4));
                    AudioTrackUtils audioTrackUtils26 = AudioTrackUtils.INSTANCE;
                    str5 = AudioTrackUtils.TAG;
                    Pdlog.m3273d(str5, "AudioTrackUtils play finally");
                    AudioTrackUtils audioTrackUtils27 = AudioTrackUtils.INSTANCE;
                    z2 = AudioTrackUtils.isStop;
                    if (z2) {
                        try {
                            AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                        } catch (Exception e5) {
                            AudioTrackUtils audioTrackUtils28 = AudioTrackUtils.INSTANCE;
                            str6 = AudioTrackUtils.TAG;
                            Pdlog.m3274e(str6, Log.getStackTraceString(e5));
                        }
                    }
                    AudioTrackUtils audioTrackUtils29 = AudioTrackUtils.INSTANCE;
                    AudioTrackUtils.isStop = false;
                    AudioTrackUtils audioTrackUtils30 = AudioTrackUtils.INSTANCE;
                    audioTrack5 = AudioTrackUtils.audioTrack;
                    if (audioTrack5 == null) {
                        return;
                    }
                    try {
                        AudioTrackUtils audioTrackUtils31 = AudioTrackUtils.INSTANCE;
                        audioTrack6 = AudioTrackUtils.audioTrack;
                        if (audioTrack6 != null) {
                            audioTrack6.stop();
                        }
                        AudioTrackUtils audioTrackUtils32 = AudioTrackUtils.INSTANCE;
                        audioTrack7 = AudioTrackUtils.audioTrack;
                        if (audioTrack7 != null) {
                            audioTrack7.release();
                        }
                    } catch (Exception e6) {
                        AudioTrackUtils audioTrackUtils33 = AudioTrackUtils.INSTANCE;
                        str7 = AudioTrackUtils.TAG;
                        Pdlog.m3274e(str7, Log.getStackTraceString(e6));
                    }
                }
                if (audioTrack11 != null) {
                    try {
                        AudioTrackUtils audioTrackUtils34 = AudioTrackUtils.INSTANCE;
                        audioTrack12 = AudioTrackUtils.audioTrack;
                        if (audioTrack12 != null) {
                            audioTrack12.stop();
                        }
                        AudioTrackUtils audioTrackUtils35 = AudioTrackUtils.INSTANCE;
                        audioTrack13 = AudioTrackUtils.audioTrack;
                        if (audioTrack13 != null) {
                            audioTrack13.release();
                        }
                    } catch (Exception e7) {
                        AudioTrackUtils audioTrackUtils36 = AudioTrackUtils.INSTANCE;
                        str10 = AudioTrackUtils.TAG;
                        Pdlog.m3274e(str10, Log.getStackTraceString(e7));
                    }
                    AudioTrackUtils audioTrackUtils37 = AudioTrackUtils.INSTANCE;
                    AudioTrackUtils.audioTrack = (AudioTrack) null;
                }
            }
        });
    }

    public final void setVolume(float v) {
        try {
            AudioTrack audioTrack2 = audioTrack;
            if (audioTrack2 != null) {
                audioTrack2.setVolume(v);
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "setVolume : v = " + e.toString() + "; ");
        }
    }

    public final boolean isPlaying() {
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("audioTrack  = ");
        sb.append(audioTrack);
        sb.append(" state ");
        AudioTrack audioTrack2 = audioTrack;
        sb.append(audioTrack2 != null ? Integer.valueOf(audioTrack2.getPlayState()) : null);
        sb.append("isStop = ");
        sb.append(isStop);
        sb.append(' ');
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        AudioTrack audioTrack3 = audioTrack;
        return (audioTrack3 == null || audioTrack3.getPlayState() != 3 || isStop) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notify(final Function1<? super AudioPlayEvent, Unit> listener, final AudioPlayEvent event) {
        mainHander.post(new Runnable() { // from class: com.pudutech.voiceinteraction.component.utils.AudioTrackUtils$notify$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        });
    }

    public final void volumeSlowDownStop() {
        if (!isStop) {
            IntProgression step = RangesKt.step(RangesKt.downTo(8, 2), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 < 0 ? first >= last : first <= last) {
                while (true) {
                    Thread.sleep(80L);
                    if (isStop || first == last) {
                        break;
                    } else {
                        first += step2;
                    }
                }
            }
        }
        stop();
    }

    public final void stop() {
        Pdlog.m3273d(TAG, "AudioTrackUtils stop");
        AudioTrack audioTrack2 = audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.stop();
            audioTrack2.release();
            audioTrack = (AudioTrack) null;
            Thread.sleep(100L);
            isStop = false;
        }
    }
}
