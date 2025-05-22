package com.pudutech.bumblebee.presenter.robot_voices;

import android.content.res.AssetFileDescriptor;
import kotlin.Metadata;

/* compiled from: IPlayControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0016J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0005H&¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl;", "", "isPlay", "", "playAssets", "", "afd", "Landroid/content/res/AssetFileDescriptor;", "playDataSource", "dataSource", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceDataSource;", "playFile", "path", "", "release", "setPlayStatusListener", "listener", "Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl$IPlayStatusListener;", "setVolume", "volume", "", "stop", "IPlayStatusListener", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface IPlayControl {

    /* compiled from: IPlayControl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/IPlayControl$IPlayStatusListener;", "", "onCompletion", "", "onError", "what", "", "error", "", "onStop", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface IPlayStatusListener {
        void onCompletion();

        void onError(int what, String error);

        void onStop();
    }

    boolean isPlay();

    void playAssets(AssetFileDescriptor afd);

    void playDataSource(VoiceDataSource dataSource);

    void playFile(String path);

    void release();

    void setPlayStatusListener(IPlayStatusListener listener);

    void setVolume(float volume);

    void stop();
}
