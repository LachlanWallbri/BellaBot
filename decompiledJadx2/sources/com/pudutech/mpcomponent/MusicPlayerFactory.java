package com.pudutech.mpcomponent;

import com.pudutech.mpcomponent.interf.IMusicPlayerInterface;
import com.pudutech.mpcomponent.mediaplayer.HMusicPlayer;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MusicPlayerFactory {
    public static IMusicPlayerInterface getMusicPlayer() {
        return HMusicPlayer.getInstance();
    }
}
