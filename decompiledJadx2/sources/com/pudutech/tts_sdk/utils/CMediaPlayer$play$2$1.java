package com.pudutech.tts_sdk.utils;

import android.media.MediaPlayer;
import kotlin.Metadata;

/* compiled from: CMediaPlayer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/media/MediaPlayer;", "kotlin.jvm.PlatformType", "onPrepared"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class CMediaPlayer$play$2$1 implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ MediaPlayer $player;

    CMediaPlayer$play$2$1(MediaPlayer mediaPlayer) {
        this.$player = mediaPlayer;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.$player.start();
    }
}
