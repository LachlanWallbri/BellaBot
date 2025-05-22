package com.pudutech.tts_sdk.utils;

import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: AudioTrackUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\r\u001a\u00020\u000e2#\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J5\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010J\u0006\u0010\u0018\u001a\u00020\u000eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/tts_sdk/utils/AudioTrackUtils;", "", "()V", "audioTrack", "Landroid/media/AudioTrack;", "bufferSize", "", "executor", "Ljava/util/concurrent/Executor;", "isStop", "", "mainHander", "Landroid/os/Handler;", "notify", "", "listener", "Lkotlin/Function1;", "Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "Lkotlin/ParameterName;", "name", "event", "play", "path", "", "stop", "AudioPlayEvent", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class AudioTrackUtils {
    public static final AudioTrackUtils INSTANCE = new AudioTrackUtils();
    private static AudioTrack audioTrack;
    private static final int bufferSize;
    private static final Executor executor;
    private static volatile boolean isStop;
    private static final Handler mainHander;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: AudioTrackUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "", "(Ljava/lang/String;I)V", "STOP", "COMPLETE", "PLAYING", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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
        bufferSize = AudioTrack.getMinBufferSize(16000, 4, 2);
        mainHander = new Handler(Looper.getMainLooper());
    }

    private AudioTrackUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void play$default(AudioTrackUtils audioTrackUtils, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        audioTrackUtils.play(str, function1);
    }

    public final void play(final String path, final Function1<? super AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Pdlog.m3273d(TtsConfig.TAG, "AudioTrackUtils play " + path);
        isStop = true;
        executor.execute(new Runnable() { // from class: com.pudutech.tts_sdk.utils.AudioTrackUtils$play$1
            /* JADX WARN: Removed duplicated region for block: B:103:? A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:104:0x01e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:116:0x01bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:96:0x0216 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                int i;
                FileInputStream fileInputStream;
                Throwable th;
                Exception e;
                boolean z;
                AudioTrack audioTrack2;
                AudioTrack audioTrack3;
                AudioTrack audioTrack4;
                boolean z2;
                AudioTrack audioTrack5;
                AudioTrack audioTrack6;
                AudioTrack audioTrack7;
                AudioTrack audioTrack8;
                AudioTrack audioTrack9;
                int i2;
                boolean z3;
                boolean z4;
                AudioTrack audioTrack10;
                AudioTrack audioTrack11;
                AudioTrack audioTrack12;
                boolean z5;
                AudioTrack audioTrack13;
                AudioTrackUtils audioTrackUtils = AudioTrackUtils.INSTANCE;
                int pcm_play_stream_type = TtsConfig.INSTANCE.getPCM_PLAY_STREAM_TYPE();
                AudioTrackUtils audioTrackUtils2 = AudioTrackUtils.INSTANCE;
                i = AudioTrackUtils.bufferSize;
                AudioTrackUtils.audioTrack = new AudioTrack(pcm_play_stream_type, 16000, 4, 2, i, 1);
                AudioTrackUtils audioTrackUtils3 = AudioTrackUtils.INSTANCE;
                AudioTrackUtils.isStop = false;
                FileInputStream fileInputStream2 = (FileInputStream) null;
                try {
                    AudioTrackUtils audioTrackUtils4 = AudioTrackUtils.INSTANCE;
                    audioTrack8 = AudioTrackUtils.audioTrack;
                    if (audioTrack8 == null) {
                        Intrinsics.throwNpe();
                    }
                    audioTrack8.setVolume(TtsConfig.INSTANCE.getPCM_PLAY_VOLUME());
                    AudioTrackUtils audioTrackUtils5 = AudioTrackUtils.INSTANCE;
                    audioTrack9 = AudioTrackUtils.audioTrack;
                    if (audioTrack9 == null) {
                        Intrinsics.throwNpe();
                    }
                    audioTrack9.play();
                    AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.PLAYING);
                    fileInputStream = new FileInputStream(path);
                } catch (Exception e2) {
                    fileInputStream = fileInputStream2;
                    e = e2;
                } catch (Throwable th2) {
                    fileInputStream = fileInputStream2;
                    th = th2;
                    Pdlog.m3273d(TtsConfig.TAG, "AudioTrackUtils play finally");
                    AudioTrackUtils audioTrackUtils6 = AudioTrackUtils.INSTANCE;
                    z = AudioTrackUtils.isStop;
                    if (z) {
                    }
                    AudioTrackUtils audioTrackUtils7 = AudioTrackUtils.INSTANCE;
                    AudioTrackUtils.isStop = false;
                    AudioTrackUtils audioTrackUtils8 = AudioTrackUtils.INSTANCE;
                    audioTrack2 = AudioTrackUtils.audioTrack;
                    if (audioTrack2 != null) {
                    }
                    if (fileInputStream == null) {
                    }
                }
                try {
                    try {
                        AudioTrackUtils audioTrackUtils9 = AudioTrackUtils.INSTANCE;
                        i2 = AudioTrackUtils.bufferSize;
                        byte[] bArr = new byte[i2];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            AudioTrackUtils audioTrackUtils10 = AudioTrackUtils.INSTANCE;
                            z5 = AudioTrackUtils.isStop;
                            if (z5) {
                                break;
                            }
                            AudioTrackUtils audioTrackUtils11 = AudioTrackUtils.INSTANCE;
                            audioTrack13 = AudioTrackUtils.audioTrack;
                            if (audioTrack13 == null) {
                                Intrinsics.throwNpe();
                            }
                            audioTrack13.write(bArr, 0, read);
                        }
                        AudioTrackUtils audioTrackUtils12 = AudioTrackUtils.INSTANCE;
                        z3 = AudioTrackUtils.isStop;
                        if (!z3) {
                            Thread.sleep(500L);
                            AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.COMPLETE);
                        }
                        Pdlog.m3273d(TtsConfig.TAG, "AudioTrackUtils play finally");
                        AudioTrackUtils audioTrackUtils13 = AudioTrackUtils.INSTANCE;
                        z4 = AudioTrackUtils.isStop;
                        if (z4) {
                            try {
                                AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                            } catch (Exception e3) {
                                Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e3));
                            }
                        }
                        AudioTrackUtils audioTrackUtils14 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.isStop = false;
                        AudioTrackUtils audioTrackUtils15 = AudioTrackUtils.INSTANCE;
                        audioTrack10 = AudioTrackUtils.audioTrack;
                        if (audioTrack10 != null) {
                            try {
                                AudioTrackUtils audioTrackUtils16 = AudioTrackUtils.INSTANCE;
                                audioTrack11 = AudioTrackUtils.audioTrack;
                                if (audioTrack11 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack11.stop();
                                AudioTrackUtils audioTrackUtils17 = AudioTrackUtils.INSTANCE;
                                audioTrack12 = AudioTrackUtils.audioTrack;
                                if (audioTrack12 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack12.release();
                            } catch (Exception e4) {
                                Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e4));
                            }
                            AudioTrackUtils audioTrackUtils18 = AudioTrackUtils.INSTANCE;
                            AudioTrackUtils.audioTrack = (AudioTrack) null;
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e5));
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        Pdlog.m3273d(TtsConfig.TAG, "AudioTrackUtils play finally");
                        AudioTrackUtils audioTrackUtils62 = AudioTrackUtils.INSTANCE;
                        z = AudioTrackUtils.isStop;
                        if (z) {
                            try {
                                AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                            } catch (Exception e6) {
                                Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e6));
                            }
                        }
                        AudioTrackUtils audioTrackUtils72 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.isStop = false;
                        AudioTrackUtils audioTrackUtils82 = AudioTrackUtils.INSTANCE;
                        audioTrack2 = AudioTrackUtils.audioTrack;
                        if (audioTrack2 != null) {
                            try {
                                AudioTrackUtils audioTrackUtils19 = AudioTrackUtils.INSTANCE;
                                audioTrack3 = AudioTrackUtils.audioTrack;
                                if (audioTrack3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack3.stop();
                                AudioTrackUtils audioTrackUtils20 = AudioTrackUtils.INSTANCE;
                                audioTrack4 = AudioTrackUtils.audioTrack;
                                if (audioTrack4 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack4.release();
                            } catch (Exception e7) {
                                Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e7));
                            }
                            AudioTrackUtils audioTrackUtils21 = AudioTrackUtils.INSTANCE;
                            AudioTrackUtils.audioTrack = (AudioTrack) null;
                        }
                        if (fileInputStream == null) {
                            try {
                                fileInputStream.close();
                                throw th;
                            } catch (IOException e8) {
                                Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e8));
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (Exception e9) {
                    e = e9;
                    AudioTrackUtils audioTrackUtils22 = AudioTrackUtils.INSTANCE;
                    AudioTrackUtils.isStop = true;
                    Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e));
                    Pdlog.m3273d(TtsConfig.TAG, "AudioTrackUtils play finally");
                    AudioTrackUtils audioTrackUtils23 = AudioTrackUtils.INSTANCE;
                    z2 = AudioTrackUtils.isStop;
                    if (z2) {
                        try {
                            AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                        } catch (Exception e10) {
                            Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e10));
                        }
                    }
                    AudioTrackUtils audioTrackUtils24 = AudioTrackUtils.INSTANCE;
                    AudioTrackUtils.isStop = false;
                    AudioTrackUtils audioTrackUtils25 = AudioTrackUtils.INSTANCE;
                    audioTrack5 = AudioTrackUtils.audioTrack;
                    if (audioTrack5 != null) {
                        try {
                            AudioTrackUtils audioTrackUtils26 = AudioTrackUtils.INSTANCE;
                            audioTrack6 = AudioTrackUtils.audioTrack;
                            if (audioTrack6 == null) {
                                Intrinsics.throwNpe();
                            }
                            audioTrack6.stop();
                            AudioTrackUtils audioTrackUtils27 = AudioTrackUtils.INSTANCE;
                            audioTrack7 = AudioTrackUtils.audioTrack;
                            if (audioTrack7 == null) {
                                Intrinsics.throwNpe();
                            }
                            audioTrack7.release();
                        } catch (Exception e11) {
                            Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e11));
                        }
                        AudioTrackUtils audioTrackUtils28 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.audioTrack = (AudioTrack) null;
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e12) {
                            Pdlog.m3274e(TtsConfig.TAG, Log.getStackTraceString(e12));
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notify(final Function1<? super AudioPlayEvent, Unit> listener, final AudioPlayEvent event) {
        mainHander.post(new Runnable() { // from class: com.pudutech.tts_sdk.utils.AudioTrackUtils$notify$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }
        });
    }

    public final void stop() {
        Pdlog.m3273d(TtsConfig.TAG, "AudioTrackUtils stop");
        isStop = true;
    }
}
