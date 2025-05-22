package com.pudutech.base.musicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class MusicPlayer {
    private static final String TAG = "MusicPlayer";
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.pudutech.base.musicplayer.MusicPlayer.2
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            Pdlog.m3273d(MusicPlayer.TAG, "focus change " + i);
            if (i != -2 && i == -1) {
            }
        }
    };
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private int musicIndex;
    private File[] musics;

    static /* synthetic */ int access$108(MusicPlayer musicPlayer) {
        int i = musicPlayer.musicIndex;
        musicPlayer.musicIndex = i + 1;
        return i;
    }

    public MusicPlayer(AudioManager audioManager) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mediaPlayer = mediaPlayer;
        this.audioManager = audioManager;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.base.musicplayer.MusicPlayer.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer2) {
                if (MusicPlayer.this.musics == null || MusicPlayer.this.musics.length <= 0) {
                    return;
                }
                MusicPlayer.access$108(MusicPlayer.this);
                if (MusicPlayer.this.musicIndex == MusicPlayer.this.musics.length) {
                    MusicPlayer.this.musicIndex = 0;
                }
                try {
                    mediaPlayer2.reset();
                    mediaPlayer2.setDataSource(MusicPlayer.this.musics[MusicPlayer.this.musicIndex].getAbsolutePath());
                    mediaPlayer2.prepare();
                    mediaPlayer2.start();
                    Pdlog.m3273d(MusicPlayer.TAG, "start play " + MusicPlayer.this.musicIndex);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stop() {
        this.mediaPlayer.stop();
    }

    public void replay() {
        if (!this.mediaPlayer.isPlaying()) {
            Pdlog.m3273d(TAG, "replay");
            this.mediaPlayer.start();
        } else {
            Pdlog.m3273d(TAG, "replay when playing");
        }
    }

    public void setVolume(float f) {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f);
        }
    }

    public void pause() {
        if (this.mediaPlayer.isPlaying()) {
            Pdlog.m3273d(TAG, "pause quickly");
            this.mediaPlayer.pause();
        }
    }

    public void playAllInFolder(String str) {
        File file = new File(str);
        if (!file.isDirectory()) {
            Pdlog.m3273d(TAG, "folder is not directory? folder: " + file.getName());
            return;
        }
        File[] listFiles = file.listFiles();
        this.musics = listFiles;
        if (listFiles.length == 0) {
            Pdlog.m3273d(TAG, "musics in folder " + file.getName() + " number " + this.musics.length);
            return;
        }
        this.musicIndex = 0;
        requestFocus();
        try {
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(this.musics[0].getAbsolutePath());
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
            Pdlog.m3273d(TAG, "play 0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean requestFocus() {
        return this.audioManager.requestAudioFocus(this.afChangeListener, 3, 1) == 1;
    }
}
