package com.pudutech.voiceinteraction.component.dialogflow;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer;
import java.io.File;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CMediaPlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010\u0011\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer;", "", "()V", "player", "Landroid/media/MediaPlayer;", "play", "", "context", "Landroid/content/Context;", "assetName", "", "file", "Ljava/io/File;", "listener", "Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer$OnPlayStateChangedListener;", "filePath", "release", "stop", "Companion", "OnPlayStateChangedListener", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CMediaPlayer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<CMediaPlayer>() { // from class: com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CMediaPlayer invoke() {
            return new CMediaPlayer(null);
        }
    });
    private static final String TAG = "CMediaPlayer";
    private MediaPlayer player;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: CMediaPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer$OnPlayStateChangedListener;", "", "onPlayError", "", "onPlayFinished", "onPlaying", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface OnPlayStateChangedListener {
        void onPlayError();

        void onPlayFinished();

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
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer;", "getINSTANCE", "()Lcom/pudutech/voiceinteraction/component/dialogflow/CMediaPlayer;", "INSTANCE$delegate", "Lkotlin/Lazy;", "TAG", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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
        if (!(str == null || str.length() == 0)) {
            play(new File(filePath), listener);
        } else if (listener != null) {
            listener.onPlayError();
        }
    }

    public final void play(final File file, final OnPlayStateChangedListener listener) {
        if (file == null) {
            if (listener != null) {
                listener.onPlayError();
                return;
            }
            return;
        }
        if (!file.exists() || !file.isFile()) {
            if (listener != null) {
                listener.onPlayError();
                return;
            }
            return;
        }
        release();
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = VoiceCommentConfig.INSTANCE.getTtsVolume() / 100.0f;
        this.player = new MediaPlayer();
        final MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer != null) {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepareAsync();
            mediaPlayer.setVolume(floatRef.element, floatRef.element);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer$play$$inlined$let$lambda$1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    CMediaPlayer.OnPlayStateChangedListener onPlayStateChangedListener = listener;
                    if (onPlayStateChangedListener != null) {
                        onPlayStateChangedListener.onPlaying();
                    }
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer$play$$inlined$let$lambda$2
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    CMediaPlayer.OnPlayStateChangedListener onPlayStateChangedListener = listener;
                    if (onPlayStateChangedListener != null) {
                        onPlayStateChangedListener.onPlayFinished();
                    }
                    CMediaPlayer.INSTANCE.getINSTANCE().release();
                }
            });
        }
    }

    public final void play(Context context, String assetName) {
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
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer$play$2$1
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer2) {
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.voiceinteraction.component.dialogflow.CMediaPlayer$play$2$2
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer2) {
                        CMediaPlayer.INSTANCE.getINSTANCE().release();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public final void stop() {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer != null) {
            try {
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
                    mediaPlayer.stop();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            this.player = (MediaPlayer) null;
        } finally {
            mediaPlayer.release();
        }
    }
}
