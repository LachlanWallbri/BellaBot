package com.pudutech.base.musicplayer;

import android.media.AudioManager;
import com.pudutech.base.FilePath;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class BackgroundMusic {
    private static final String PATH = FilePath.CUSTOM_MUSIC;
    private static final String TAG = "BackgroundMusic";
    private int index = 0;
    MusicPlayer musicPlayer;

    public BackgroundMusic(AudioManager audioManager) {
        this.musicPlayer = new MusicPlayer(audioManager);
    }

    public void replay() {
        this.musicPlayer.replay();
    }

    public void pause() {
        this.musicPlayer.pause();
    }

    public void setVolume(float f) {
        this.musicPlayer.setVolume(f);
    }

    public String playNextFolder() {
        List<String> load = load();
        if (load.size() == 0) {
            return "no music";
        }
        this.musicPlayer.stop();
        String str = load.get(this.index);
        this.musicPlayer.playAllInFolder(PATH + "/" + str);
        int i = this.index + 1;
        this.index = i;
        if (i == load.size()) {
            this.index = 0;
        }
        return str.replace("_", " ");
    }

    private List<String> load() {
        ArrayList arrayList = new ArrayList();
        SecurityManager securityManager = new SecurityManager();
        File file = new File(PATH);
        securityManager.checkRead(PATH);
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!str.startsWith(".")) {
                    if (new File(PATH + "/" + str).isDirectory()) {
                        arrayList.add(str);
                    }
                }
            }
        }
        return arrayList;
    }
}
