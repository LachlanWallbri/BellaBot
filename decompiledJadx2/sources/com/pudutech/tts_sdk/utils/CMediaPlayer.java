package com.pudutech.tts_sdk.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.tts_sdk.utils.CMediaPlayer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CMediaPlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\"\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\f\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\f\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\u0015\u001a\u00020\tJ\u0006\u0010\u0016\u001a\u00020\tR\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/tts_sdk/utils/CMediaPlayer;", "", "()V", "onPlayStateChangedListenerList", "", "Lcom/pudutech/tts_sdk/utils/CMediaPlayer$OnPlayStateChangedListener;", "player", "Landroid/media/MediaPlayer;", "callPlay", "", "type", "", "play", "context", "Landroid/content/Context;", "assetName", "", "listener", "file", "Ljava/io/File;", "filePath", "release", "stop", "Companion", "OnPlayStateChangedListener", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CMediaPlayer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<CMediaPlayer>() { // from class: com.pudutech.tts_sdk.utils.CMediaPlayer$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CMediaPlayer invoke() {
            return new CMediaPlayer(null);
        }
    });
    private static final String TAG = "CMediaPlayer";
    private List<OnPlayStateChangedListener> onPlayStateChangedListenerList;
    private MediaPlayer player;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: CMediaPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/tts_sdk/utils/CMediaPlayer$OnPlayStateChangedListener;", "", "onPlayFinished", "", "onPlayStop", "onPlaying", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface OnPlayStateChangedListener {

        /* compiled from: CMediaPlayer.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes7.dex */
        public static final class DefaultImpls {
            public static void onPlayStop(OnPlayStateChangedListener onPlayStateChangedListener) {
            }
        }

        void onPlayFinished();

        void onPlayStop();

        void onPlaying();
    }

    private CMediaPlayer() {
    }

    public /* synthetic */ CMediaPlayer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: CMediaPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/tts_sdk/utils/CMediaPlayer$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/tts_sdk/utils/CMediaPlayer;", "getINSTANCE", "()Lcom/pudutech/tts_sdk/utils/CMediaPlayer;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public final CMediaPlayer getINSTANCE() {
            Lazy lazy = CMediaPlayer.INSTANCE$delegate;
            Companion companion = CMediaPlayer.INSTANCE;
            return (CMediaPlayer) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void play(String filePath, OnPlayStateChangedListener listener) {
        String str = filePath;
        if (str == null || str.length() == 0) {
            return;
        }
        play(new File(filePath), listener);
    }

    public final void play(final File file, OnPlayStateChangedListener listener) {
        if (file != null && file.exists() && file.isFile()) {
            release();
            if (this.onPlayStateChangedListenerList == null) {
                this.onPlayStateChangedListenerList = new ArrayList();
            }
            List<OnPlayStateChangedListener> list = this.onPlayStateChangedListenerList;
            if (list != null && listener != null) {
                list.add(listener);
            }
            this.player = new MediaPlayer();
            final MediaPlayer mediaPlayer = this.player;
            if (mediaPlayer != null) {
                float pcm_play_volume = TtsConfig.INSTANCE.getPCM_PLAY_VOLUME();
                int pcm_play_stream_type = TtsConfig.INSTANCE.getPCM_PLAY_STREAM_TYPE();
                mediaPlayer.setDataSource(file.getPath());
                mediaPlayer.prepareAsync();
                mediaPlayer.setVolume(pcm_play_volume, pcm_play_volume);
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(pcm_play_stream_type).build());
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.pudutech.tts_sdk.utils.CMediaPlayer$play$$inlined$let$lambda$1
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer2) {
                        this.callPlay(1);
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.tts_sdk.utils.CMediaPlayer$play$$inlined$let$lambda$2
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer2) {
                        CMediaPlayer.this.callPlay(2);
                        CMediaPlayer.INSTANCE.getINSTANCE().release();
                    }
                });
            }
        }
    }

    public final void play(final Context context, final String assetName, final OnPlayStateChangedListener listener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = assetName;
        if (str == null || str.length() == 0) {
            return;
        }
        release();
        this.player = new MediaPlayer();
        final MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer != null) {
            try {
                AssetFileDescriptor openFd = context.getAssets().openFd(assetName);
                Intrinsics.checkExpressionValueIsNotNull(openFd, "context.assets.openFd(assetName)");
                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                mediaPlayer.prepareAsync();
                float pcm_play_volume = TtsConfig.INSTANCE.getPCM_PLAY_VOLUME();
                mediaPlayer.setVolume(pcm_play_volume, pcm_play_volume);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.pudutech.tts_sdk.utils.CMediaPlayer$play$$inlined$let$lambda$3
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer2) {
                        CMediaPlayer.OnPlayStateChangedListener onPlayStateChangedListener = listener;
                        if (onPlayStateChangedListener != null) {
                            onPlayStateChangedListener.onPlaying();
                        }
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.tts_sdk.utils.CMediaPlayer$play$$inlined$let$lambda$4
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer2) {
                        CMediaPlayer.OnPlayStateChangedListener onPlayStateChangedListener = listener;
                        if (onPlayStateChangedListener != null) {
                            onPlayStateChangedListener.onPlayFinished();
                        }
                        CMediaPlayer.INSTANCE.getINSTANCE().release();
                    }
                });
            } catch (IOException e) {
                if (listener != null) {
                    listener.onPlayFinished();
                }
                e.printStackTrace();
            }
        }
    }

    public final void stop() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer != null) {
            try {
                callPlay(3);
                mediaPlayer.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public final void release() {
        MediaPlayer mediaPlayer = this.player;
        try {
            if (mediaPlayer != null) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        callPlay(3);
                    }
                    mediaPlayer.stop();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            List<OnPlayStateChangedListener> list = this.onPlayStateChangedListenerList;
            if (list != null) {
                list.clear();
            }
            this.onPlayStateChangedListenerList = (List) null;
            this.player = (MediaPlayer) null;
        } finally {
            mediaPlayer.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callPlay(int type) {
        List<OnPlayStateChangedListener> list = this.onPlayStateChangedListenerList;
        if (list != null) {
            for (OnPlayStateChangedListener onPlayStateChangedListener : list) {
                if (type == 1) {
                    onPlayStateChangedListener.onPlaying();
                } else if (type == 2) {
                    onPlayStateChangedListener.onPlayFinished();
                } else if (type == 3) {
                    onPlayStateChangedListener.onPlayStop();
                }
            }
        }
    }
}
