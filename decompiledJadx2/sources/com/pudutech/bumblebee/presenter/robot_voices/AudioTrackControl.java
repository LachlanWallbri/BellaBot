package com.pudutech.bumblebee.presenter.robot_voices;

import android.content.res.AssetFileDescriptor;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.IPlayControl;
import com.pudutech.tts_sdk.TtsConfig;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioTrackControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0014H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/AudioTrackControl;", "Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl;", "streamType", "", "(I)V", "TAG", "", "audioTrack", "Landroid/media/AudioTrack;", "audioTrackPlayHandler", "Landroid/os/Handler;", "audioTrackPlayThread", "Landroid/os/HandlerThread;", "bufferSize", "isPlay", "", "isStop", "listener", "Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl$IPlayStatusListener;", "playAssets", "", "afd", "Landroid/content/res/AssetFileDescriptor;", "playDataSource", "dataSource", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceDataSource;", "playFile", "fis", "Ljava/io/InputStream;", "path", "release", "setPlayStatusListener", "setVolume", "volume", "", "stop", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AudioTrackControl implements IPlayControl {
    private AudioTrack audioTrack;
    private Handler audioTrackPlayHandler;
    private boolean isPlay;
    private boolean isStop;
    private IPlayControl.IPlayStatusListener listener;
    private final String TAG = "AudioTrackControl";
    private final int bufferSize = AudioTrack.getMinBufferSize(16000, 4, 2);
    private final HandlerThread audioTrackPlayThread = new HandlerThread("AudioTrackControl");

    public AudioTrackControl(int i) {
        this.audioTrack = new AudioTrack(i, 16000, 4, 2, this.bufferSize, 1);
        this.audioTrackPlayThread.start();
        this.audioTrackPlayHandler = new Handler(this.audioTrackPlayThread.getLooper());
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void setVolume(float volume) {
        this.audioTrack.setVolume(volume);
    }

    private final void playFile(final InputStream fis) {
        Handler handler = this.audioTrackPlayHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_voices.AudioTrackControl$playFile$1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    boolean z;
                    boolean z2;
                    String str2;
                    String str3;
                    AudioTrack audioTrack;
                    String str4;
                    IPlayControl.IPlayStatusListener iPlayStatusListener;
                    String str5;
                    boolean z3;
                    boolean z4;
                    String str6;
                    String str7;
                    AudioTrack audioTrack2;
                    AudioTrack audioTrack3;
                    AudioTrack audioTrack4;
                    int i;
                    boolean z5;
                    IPlayControl.IPlayStatusListener iPlayStatusListener2;
                    String str8;
                    boolean z6;
                    boolean z7;
                    String str9;
                    String str10;
                    AudioTrack audioTrack5;
                    IPlayControl.IPlayStatusListener iPlayStatusListener3;
                    boolean z8;
                    String str11;
                    boolean z9;
                    AudioTrack audioTrack6;
                    AudioTrackControl.this.isStop = false;
                    AudioTrackControl.this.isPlay = true;
                    try {
                        try {
                            audioTrack3 = AudioTrackControl.this.audioTrack;
                            audioTrack3.setVolume(TtsConfig.INSTANCE.getPCM_PLAY_VOLUME());
                            audioTrack4 = AudioTrackControl.this.audioTrack;
                            audioTrack4.play();
                            i = AudioTrackControl.this.bufferSize;
                            byte[] bArr = new byte[i];
                            while (true) {
                                int read = fis.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                z8 = AudioTrackControl.this.isStop;
                                if (z8) {
                                    break;
                                }
                                str11 = AudioTrackControl.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("playFile isStop: ");
                                z9 = AudioTrackControl.this.isStop;
                                sb.append(z9);
                                sb.append(",thread:");
                                sb.append(Thread.currentThread());
                                Pdlog.m3273d(str11, sb.toString());
                                audioTrack6 = AudioTrackControl.this.audioTrack;
                                audioTrack6.write(bArr, 0, read);
                            }
                            z5 = AudioTrackControl.this.isStop;
                            if (!z5) {
                                iPlayStatusListener3 = AudioTrackControl.this.listener;
                                if (iPlayStatusListener3 != null) {
                                    iPlayStatusListener3.onCompletion();
                                }
                            } else {
                                iPlayStatusListener2 = AudioTrackControl.this.listener;
                                if (iPlayStatusListener2 != null) {
                                    iPlayStatusListener2.onStop();
                                }
                            }
                            AudioTrackControl.this.isPlay = false;
                            AudioTrackControl.this.isStop = false;
                            str8 = AudioTrackControl.this.TAG;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("playFile finally isPlay:");
                            z6 = AudioTrackControl.this.isPlay;
                            sb2.append(z6);
                            sb2.append(",isStop:");
                            z7 = AudioTrackControl.this.isStop;
                            sb2.append(z7);
                            Pdlog.m3273d(str8, sb2.toString());
                            try {
                                audioTrack5 = AudioTrackControl.this.audioTrack;
                                audioTrack5.stop();
                            } catch (Exception e) {
                                str9 = AudioTrackControl.this.TAG;
                                Pdlog.m3274e(str9, Log.getStackTraceString(e));
                            }
                            InputStream inputStream = fis;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e2) {
                                    str10 = AudioTrackControl.this.TAG;
                                    Pdlog.m3274e(str10, Log.getStackTraceString(e2));
                                }
                            }
                        } catch (Exception e3) {
                            str4 = AudioTrackControl.this.TAG;
                            Pdlog.m3274e(str4, Log.getStackTraceString(e3));
                            iPlayStatusListener = AudioTrackControl.this.listener;
                            if (iPlayStatusListener != null) {
                                iPlayStatusListener.onError(0, e3.toString());
                            }
                            AudioTrackControl.this.isPlay = false;
                            AudioTrackControl.this.isStop = false;
                            str5 = AudioTrackControl.this.TAG;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("playFile finally isPlay:");
                            z3 = AudioTrackControl.this.isPlay;
                            sb3.append(z3);
                            sb3.append(",isStop:");
                            z4 = AudioTrackControl.this.isStop;
                            sb3.append(z4);
                            Pdlog.m3273d(str5, sb3.toString());
                            try {
                                audioTrack2 = AudioTrackControl.this.audioTrack;
                                audioTrack2.stop();
                            } catch (Exception e4) {
                                str6 = AudioTrackControl.this.TAG;
                                Pdlog.m3274e(str6, Log.getStackTraceString(e4));
                            }
                            InputStream inputStream2 = fis;
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e5) {
                                    str7 = AudioTrackControl.this.TAG;
                                    Pdlog.m3274e(str7, Log.getStackTraceString(e5));
                                }
                            }
                        }
                    } catch (Throwable th) {
                        AudioTrackControl.this.isPlay = false;
                        AudioTrackControl.this.isStop = false;
                        str = AudioTrackControl.this.TAG;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("playFile finally isPlay:");
                        z = AudioTrackControl.this.isPlay;
                        sb4.append(z);
                        sb4.append(",isStop:");
                        z2 = AudioTrackControl.this.isStop;
                        sb4.append(z2);
                        Pdlog.m3273d(str, sb4.toString());
                        try {
                            audioTrack = AudioTrackControl.this.audioTrack;
                            audioTrack.stop();
                        } catch (Exception e6) {
                            str2 = AudioTrackControl.this.TAG;
                            Pdlog.m3274e(str2, Log.getStackTraceString(e6));
                        }
                        InputStream inputStream3 = fis;
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                                throw th;
                            } catch (IOException e7) {
                                str3 = AudioTrackControl.this.TAG;
                                Pdlog.m3274e(str3, Log.getStackTraceString(e7));
                                throw th;
                            }
                        }
                        throw th;
                    }
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void playFile(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        playFile(new FileInputStream(path));
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void playAssets(AssetFileDescriptor afd) {
        Intrinsics.checkParameterIsNotNull(afd, "afd");
        FileInputStream createInputStream = afd.createInputStream();
        Intrinsics.checkExpressionValueIsNotNull(createInputStream, "afd.createInputStream()");
        playFile(createInputStream);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void playDataSource(VoiceDataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        playFile(new ByteArrayInputStream(dataSource.getMBuffer()));
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void stop() {
        this.isStop = true;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    /* renamed from: isPlay, reason: from getter */
    public boolean getIsPlay() {
        return this.isPlay;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void setPlayStatusListener(IPlayControl.IPlayStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void release() {
        this.listener = (IPlayControl.IPlayStatusListener) null;
        Handler handler = this.audioTrackPlayHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.audioTrackPlayHandler = (Handler) null;
        this.audioTrackPlayThread.quitSafely();
    }
}
