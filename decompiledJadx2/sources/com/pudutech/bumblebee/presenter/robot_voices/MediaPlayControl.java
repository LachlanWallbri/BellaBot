package com.pudutech.bumblebee.presenter.robot_voices;

import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import com.pudutech.bumblebee.presenter.robot_voices.IPlayControl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaPlayControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0016J\"\u0010\u0010\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u000eH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/MediaPlayControl;", "Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl;", "Landroid/media/MediaPlayer$OnCompletionListener;", "Landroid/media/MediaPlayer$OnErrorListener;", "streamType", "", "(I)V", "listener", "Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl$IPlayStatusListener;", "mediaPlayer", "Landroid/media/MediaPlayer;", "isPlay", "", "onCompletion", "", "mp", "onError", "what", "extra", "playAssets", "afd", "Landroid/content/res/AssetFileDescriptor;", "playDataSource", "dataSource", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceDataSource;", "playFile", "path", "", "release", "setPlayStatusListener", "setVolume", "volume", "", "stop", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MediaPlayControl implements IPlayControl, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private IPlayControl.IPlayStatusListener listener;
    private final MediaPlayer mediaPlayer = new MediaPlayer();

    public MediaPlayControl(int i) {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setLegacyStreamType(i);
        this.mediaPlayer.setAudioAttributes(builder.build());
        this.mediaPlayer.setOnCompletionListener(this);
        this.mediaPlayer.setOnErrorListener(this);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void setVolume(float volume) {
        this.mediaPlayer.setVolume(volume, volume);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void playFile(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.mediaPlayer.reset();
        this.mediaPlayer.setDataSource(path);
        this.mediaPlayer.prepare();
        this.mediaPlayer.start();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void playAssets(AssetFileDescriptor afd) {
        Intrinsics.checkParameterIsNotNull(afd, "afd");
        this.mediaPlayer.reset();
        this.mediaPlayer.setDataSource(afd);
        this.mediaPlayer.prepare();
        this.mediaPlayer.start();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void playDataSource(VoiceDataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        this.mediaPlayer.reset();
        this.mediaPlayer.setDataSource(dataSource);
        this.mediaPlayer.prepare();
        this.mediaPlayer.start();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void stop() {
        this.mediaPlayer.stop();
        IPlayControl.IPlayStatusListener iPlayStatusListener = this.listener;
        if (iPlayStatusListener != null) {
            iPlayStatusListener.onStop();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    /* renamed from: isPlay */
    public boolean getIsPlay() {
        return this.mediaPlayer.isPlaying();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void setPlayStatusListener(IPlayControl.IPlayStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.IPlayControl
    public void release() {
        this.listener = (IPlayControl.IPlayStatusListener) null;
        this.mediaPlayer.release();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mp) {
        IPlayControl.IPlayStatusListener iPlayStatusListener = this.listener;
        if (iPlayStatusListener != null) {
            iPlayStatusListener.onCompletion();
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mp, int what, int extra) {
        IPlayControl.IPlayStatusListener iPlayStatusListener = this.listener;
        if (iPlayStatusListener == null) {
            return false;
        }
        iPlayStatusListener.onError(what, "");
        return false;
    }
}
