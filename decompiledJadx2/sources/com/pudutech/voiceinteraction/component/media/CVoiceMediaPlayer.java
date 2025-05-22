package com.pudutech.voiceinteraction.component.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.config.VoiceMediaType;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CVoiceMediaPlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/media/CVoiceMediaPlayer;", "", "()V", "mediaPlayer", "Landroid/media/MediaPlayer;", "playSound", "", "context", "Landroid/content/Context;", "voiceMediaType", "Lcom/pudutech/voiceinteraction/component/config/VoiceMediaType;", "release", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CVoiceMediaPlayer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<CVoiceMediaPlayer>() { // from class: com.pudutech.voiceinteraction.component.media.CVoiceMediaPlayer$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CVoiceMediaPlayer invoke() {
            return new CVoiceMediaPlayer();
        }
    });
    private MediaPlayer mediaPlayer;

    /* compiled from: CVoiceMediaPlayer.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/media/CVoiceMediaPlayer$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/voiceinteraction/component/media/CVoiceMediaPlayer;", "getINSTANCE", "()Lcom/pudutech/voiceinteraction/component/media/CVoiceMediaPlayer;", "INSTANCE$delegate", "Lkotlin/Lazy;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final CVoiceMediaPlayer getINSTANCE() {
            Lazy lazy = CVoiceMediaPlayer.INSTANCE$delegate;
            Companion companion = CVoiceMediaPlayer.INSTANCE;
            return (CVoiceMediaPlayer) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void playSound(final Context context, final VoiceMediaType voiceMediaType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(voiceMediaType, "voiceMediaType");
        release();
        this.mediaPlayer = new MediaPlayer();
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            try {
                AssetFileDescriptor openFd = context.getAssets().openFd(voiceMediaType.getVoiceMediaName());
                Intrinsics.checkExpressionValueIsNotNull(openFd, "context.assets.openFd(vo…MediaType.voiceMediaName)");
                mediaPlayer.reset();
                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.pudutech.voiceinteraction.component.media.CVoiceMediaPlayer$playSound$1$1
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer2) {
                        mediaPlayer2.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.voiceinteraction.component.media.CVoiceMediaPlayer$playSound$$inlined$let$lambda$1
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer2) {
                        Pdlog.m3273d("CVoiceMediaPlayer", "play complete, begin release");
                        CVoiceMediaPlayer.this.release();
                        Pdlog.m3273d("CVoiceMediaPlayer", "play complete, end release");
                    }
                });
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public final void release() {
        Pdlog.m3273d("CVoiceMediaPlayer", "release");
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
        }
        this.mediaPlayer = (MediaPlayer) null;
    }
}
