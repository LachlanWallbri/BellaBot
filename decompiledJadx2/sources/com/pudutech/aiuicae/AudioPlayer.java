package com.pudutech.aiuicae;

import android.media.MediaPlayer;

/* loaded from: classes.dex */
public class AudioPlayer {
    private MediaPlayer mediaPlayer = new MediaPlayer();

    public void start(String str) {
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mediaPlayer = mediaPlayer;
        mediaPlayer.setAudioStreamType(3);
        try {
            this.mediaPlayer.setDataSource(str);
            this.mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mediaPlayer.isPlaying()) {
            return;
        }
        this.mediaPlayer.start();
    }

    public void stop() {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
        }
        this.mediaPlayer.stop();
    }

    public void release() {
        this.mediaPlayer.release();
    }
}
