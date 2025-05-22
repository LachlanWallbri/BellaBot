package com.pudutech.robot.module.voice;

import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.voice.AudioTrackUtils;
import java.io.FileInputStream;
import java.io.IOException;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\fJ5\u0010\u0010\u001a\u00020\u00112#\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00020\u0014H\u0002J5\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00042%\b\u0002\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013J\u000e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0011J\u0006\u0010\u001e\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/robot/module/voice/AudioTrackUtils;", "", "()V", "TAG", "", "audioTrack", "Landroid/media/AudioTrack;", "bufferSize", "", "executor", "Ljava/util/concurrent/Executor;", "isStop", "", "mainHander", "Landroid/os/Handler;", "isPlaying", "notify", "", "listener", "Lkotlin/Function1;", "Lcom/pudutech/robot/module/voice/AudioTrackUtils$AudioPlayEvent;", "Lkotlin/ParameterName;", "name", "event", "play", "path", "setVolume", "v", "", "stop", "volumeSlowDownStop", "AudioPlayEvent", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
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
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/AudioTrackUtils$AudioPlayEvent;", "", "(Ljava/lang/String;I)V", "STOP", "COMPLETE", "PLAYING", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
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
        Pdlog.m3273d(TAG, "AudioTrackUtils play " + path);
        isStop = true;
        executor.execute(new Runnable() { // from class: com.pudutech.robot.module.voice.AudioTrackUtils$play$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:102:0x0270 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:109:? A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:110:0x0236 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:122:0x020b A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Type inference failed for: r5v0 */
            /* JADX WARN: Type inference failed for: r5v2 */
            /* JADX WARN: Type inference failed for: r5v5, types: [java.io.FileInputStream] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                int i;
                Throwable th;
                FileInputStream fileInputStream;
                Exception e;
                String str;
                boolean z;
                String str2;
                AudioTrack audioTrack2;
                String str3;
                AudioTrack audioTrack3;
                AudioTrack audioTrack4;
                String str4;
                String str5;
                String str6;
                boolean z2;
                String str7;
                AudioTrack audioTrack5;
                String str8;
                AudioTrack audioTrack6;
                AudioTrack audioTrack7;
                String str9;
                AudioTrack audioTrack8;
                AudioTrack audioTrack9;
                int i2;
                boolean z3;
                String str10;
                boolean z4;
                String str11;
                AudioTrack audioTrack10;
                String str12;
                String str13;
                AudioTrack audioTrack11;
                AudioTrack audioTrack12;
                AudioTrack audioTrack13;
                AudioTrack audioTrack14;
                boolean z5;
                AudioTrack audioTrack15;
                AudioTrackUtils audioTrackUtils = AudioTrackUtils.INSTANCE;
                AudioTrackUtils audioTrackUtils2 = AudioTrackUtils.INSTANCE;
                i = AudioTrackUtils.bufferSize;
                ?? r5 = 4;
                AudioTrackUtils.audioTrack = new AudioTrack(3, 16000, 4, 2, i, 1);
                AudioTrackUtils audioTrackUtils3 = AudioTrackUtils.INSTANCE;
                AudioTrackUtils.isStop = false;
                FileInputStream fileInputStream2 = (FileInputStream) null;
                try {
                    try {
                        AudioTrackUtils audioTrackUtils4 = AudioTrackUtils.INSTANCE;
                        audioTrack8 = AudioTrackUtils.audioTrack;
                        if (audioTrack8 == null) {
                            Intrinsics.throwNpe();
                        }
                        audioTrack8.setVolume(RobotVoicePlayer.INSTANCE.getVolume());
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
                        r5 = fileInputStream2;
                        th = th2;
                        AudioTrackUtils audioTrackUtils6 = AudioTrackUtils.INSTANCE;
                        str = AudioTrackUtils.TAG;
                        Pdlog.m3273d(str, "AudioTrackUtils play finally");
                        AudioTrackUtils audioTrackUtils7 = AudioTrackUtils.INSTANCE;
                        z = AudioTrackUtils.isStop;
                        if (z) {
                        }
                        AudioTrackUtils audioTrackUtils8 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.isStop = false;
                        AudioTrackUtils audioTrackUtils9 = AudioTrackUtils.INSTANCE;
                        audioTrack2 = AudioTrackUtils.audioTrack;
                        if (audioTrack2 != null) {
                        }
                        if (r5 == 0) {
                        }
                    }
                    try {
                        AudioTrackUtils audioTrackUtils10 = AudioTrackUtils.INSTANCE;
                        i2 = AudioTrackUtils.bufferSize;
                        byte[] bArr = new byte[i2];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            AudioTrackUtils audioTrackUtils11 = AudioTrackUtils.INSTANCE;
                            z5 = AudioTrackUtils.isStop;
                            if (z5) {
                                break;
                            }
                            AudioTrackUtils audioTrackUtils12 = AudioTrackUtils.INSTANCE;
                            audioTrack15 = AudioTrackUtils.audioTrack;
                            if (audioTrack15 == null) {
                                Intrinsics.throwNpe();
                            }
                            audioTrack15.write(bArr, 0, read);
                        }
                        AudioTrackUtils audioTrackUtils13 = AudioTrackUtils.INSTANCE;
                        z3 = AudioTrackUtils.isStop;
                        if (!z3) {
                            AudioTrackUtils audioTrackUtils14 = AudioTrackUtils.INSTANCE;
                            audioTrack13 = AudioTrackUtils.audioTrack;
                            if (audioTrack13 != null) {
                                audioTrack13.stop();
                            }
                            AudioTrackUtils audioTrackUtils15 = AudioTrackUtils.INSTANCE;
                            audioTrack14 = AudioTrackUtils.audioTrack;
                            if (audioTrack14 != null) {
                                audioTrack14.release();
                            }
                            AudioTrackUtils audioTrackUtils16 = AudioTrackUtils.INSTANCE;
                            AudioTrackUtils.audioTrack = (AudioTrack) null;
                            Thread.sleep(100L);
                            AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.COMPLETE);
                        }
                        AudioTrackUtils audioTrackUtils17 = AudioTrackUtils.INSTANCE;
                        str10 = AudioTrackUtils.TAG;
                        Pdlog.m3273d(str10, "AudioTrackUtils play finally");
                        AudioTrackUtils audioTrackUtils18 = AudioTrackUtils.INSTANCE;
                        z4 = AudioTrackUtils.isStop;
                        if (z4) {
                            try {
                                AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                            } catch (Exception e3) {
                                AudioTrackUtils audioTrackUtils19 = AudioTrackUtils.INSTANCE;
                                str11 = AudioTrackUtils.TAG;
                                Pdlog.m3274e(str11, Log.getStackTraceString(e3));
                            }
                        }
                        AudioTrackUtils audioTrackUtils20 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.isStop = false;
                        AudioTrackUtils audioTrackUtils21 = AudioTrackUtils.INSTANCE;
                        audioTrack10 = AudioTrackUtils.audioTrack;
                        if (audioTrack10 != null) {
                            try {
                                AudioTrackUtils audioTrackUtils22 = AudioTrackUtils.INSTANCE;
                                audioTrack11 = AudioTrackUtils.audioTrack;
                                if (audioTrack11 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack11.stop();
                                AudioTrackUtils audioTrackUtils23 = AudioTrackUtils.INSTANCE;
                                audioTrack12 = AudioTrackUtils.audioTrack;
                                if (audioTrack12 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack12.release();
                            } catch (Exception e4) {
                                AudioTrackUtils audioTrackUtils24 = AudioTrackUtils.INSTANCE;
                                str13 = AudioTrackUtils.TAG;
                                Pdlog.m3274e(str13, Log.getStackTraceString(e4));
                            }
                            AudioTrackUtils audioTrackUtils25 = AudioTrackUtils.INSTANCE;
                            AudioTrackUtils.audioTrack = (AudioTrack) null;
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            AudioTrackUtils audioTrackUtils26 = AudioTrackUtils.INSTANCE;
                            str12 = AudioTrackUtils.TAG;
                            Pdlog.m3274e(str12, Log.getStackTraceString(e5));
                        }
                    } catch (Exception e6) {
                        e = e6;
                        AudioTrackUtils audioTrackUtils27 = AudioTrackUtils.INSTANCE;
                        str5 = AudioTrackUtils.TAG;
                        Pdlog.m3274e(str5, Log.getStackTraceString(e));
                        AudioTrackUtils audioTrackUtils28 = AudioTrackUtils.INSTANCE;
                        str6 = AudioTrackUtils.TAG;
                        Pdlog.m3273d(str6, "AudioTrackUtils play finally");
                        AudioTrackUtils audioTrackUtils29 = AudioTrackUtils.INSTANCE;
                        z2 = AudioTrackUtils.isStop;
                        if (z2) {
                            try {
                                AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                            } catch (Exception e7) {
                                AudioTrackUtils audioTrackUtils30 = AudioTrackUtils.INSTANCE;
                                str7 = AudioTrackUtils.TAG;
                                Pdlog.m3274e(str7, Log.getStackTraceString(e7));
                            }
                        }
                        AudioTrackUtils audioTrackUtils31 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.isStop = false;
                        AudioTrackUtils audioTrackUtils32 = AudioTrackUtils.INSTANCE;
                        audioTrack5 = AudioTrackUtils.audioTrack;
                        if (audioTrack5 != null) {
                            try {
                                AudioTrackUtils audioTrackUtils33 = AudioTrackUtils.INSTANCE;
                                audioTrack6 = AudioTrackUtils.audioTrack;
                                if (audioTrack6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack6.stop();
                                AudioTrackUtils audioTrackUtils34 = AudioTrackUtils.INSTANCE;
                                audioTrack7 = AudioTrackUtils.audioTrack;
                                if (audioTrack7 == null) {
                                    Intrinsics.throwNpe();
                                }
                                audioTrack7.release();
                            } catch (Exception e8) {
                                AudioTrackUtils audioTrackUtils35 = AudioTrackUtils.INSTANCE;
                                str8 = AudioTrackUtils.TAG;
                                Pdlog.m3274e(str8, Log.getStackTraceString(e8));
                            }
                            AudioTrackUtils audioTrackUtils36 = AudioTrackUtils.INSTANCE;
                            AudioTrackUtils.audioTrack = (AudioTrack) null;
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e9) {
                                AudioTrackUtils audioTrackUtils37 = AudioTrackUtils.INSTANCE;
                                str9 = AudioTrackUtils.TAG;
                                Pdlog.m3274e(str9, Log.getStackTraceString(e9));
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    AudioTrackUtils audioTrackUtils62 = AudioTrackUtils.INSTANCE;
                    str = AudioTrackUtils.TAG;
                    Pdlog.m3273d(str, "AudioTrackUtils play finally");
                    AudioTrackUtils audioTrackUtils72 = AudioTrackUtils.INSTANCE;
                    z = AudioTrackUtils.isStop;
                    if (z) {
                        try {
                            AudioTrackUtils.INSTANCE.notify(Function1.this, AudioTrackUtils.AudioPlayEvent.STOP);
                        } catch (Exception e10) {
                            AudioTrackUtils audioTrackUtils38 = AudioTrackUtils.INSTANCE;
                            str2 = AudioTrackUtils.TAG;
                            Pdlog.m3274e(str2, Log.getStackTraceString(e10));
                        }
                    }
                    AudioTrackUtils audioTrackUtils82 = AudioTrackUtils.INSTANCE;
                    AudioTrackUtils.isStop = false;
                    AudioTrackUtils audioTrackUtils92 = AudioTrackUtils.INSTANCE;
                    audioTrack2 = AudioTrackUtils.audioTrack;
                    if (audioTrack2 != null) {
                        try {
                            AudioTrackUtils audioTrackUtils39 = AudioTrackUtils.INSTANCE;
                            audioTrack3 = AudioTrackUtils.audioTrack;
                            if (audioTrack3 == null) {
                                Intrinsics.throwNpe();
                            }
                            audioTrack3.stop();
                            AudioTrackUtils audioTrackUtils40 = AudioTrackUtils.INSTANCE;
                            audioTrack4 = AudioTrackUtils.audioTrack;
                            if (audioTrack4 == null) {
                                Intrinsics.throwNpe();
                            }
                            audioTrack4.release();
                        } catch (Exception e11) {
                            AudioTrackUtils audioTrackUtils41 = AudioTrackUtils.INSTANCE;
                            str3 = AudioTrackUtils.TAG;
                            Pdlog.m3274e(str3, Log.getStackTraceString(e11));
                        }
                        AudioTrackUtils audioTrackUtils42 = AudioTrackUtils.INSTANCE;
                        AudioTrackUtils.audioTrack = (AudioTrack) null;
                    }
                    if (r5 == 0) {
                        try {
                            r5.close();
                            throw th;
                        } catch (IOException e12) {
                            AudioTrackUtils audioTrackUtils43 = AudioTrackUtils.INSTANCE;
                            str4 = AudioTrackUtils.TAG;
                            Pdlog.m3274e(str4, Log.getStackTraceString(e12));
                            throw th;
                        }
                    }
                    throw th;
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
        mainHander.post(new Runnable() { // from class: com.pudutech.robot.module.voice.AudioTrackUtils$notify$1
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
                    setVolume(RobotVoicePlayer.INSTANCE.getVolume() * (first / 10.0f));
                    Thread.sleep(80L);
                    if (isStop || first == last) {
                        break;
                    } else {
                        first += step2;
                    }
                }
            }
        }
        setVolume(RobotVoicePlayer.INSTANCE.getVolume());
        stop();
    }

    public final void stop() {
        Pdlog.m3273d(TAG, "AudioTrackUtils stop");
        isStop = true;
    }
}
