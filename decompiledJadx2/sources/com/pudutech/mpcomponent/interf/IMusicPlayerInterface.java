package com.pudutech.mpcomponent.interf;

import com.pudutech.mpcomponent.Music;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public interface IMusicPlayerInterface {
    IMusicPlayerInterface init();

    boolean isInit();

    void pause();

    void release();

    void reset();

    void resetConnectionPlayMode(boolean z);

    void setAudioStreamType(int i);

    void setMusicPlayerStateCallback(IMusicPlayerStateCallback iMusicPlayerStateCallback);

    void setSaveTimeCallback(SaveMusicPlayTimeCallback saveMusicPlayTimeCallback);

    void setVolume(float f, float f2);

    void start(boolean z);

    void stop();

    void switchSong(Music music, boolean z);

    IMusicPlayerInterface updatePlaylist(List<Music> list);
}
