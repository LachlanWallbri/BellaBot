package com.pudutech.mpcomponent.mediaplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpcomponent.config.PlayerState;
import com.pudutech.mpcomponent.interf.IMusicPlayerInterface;
import com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback;
import com.pudutech.mpcomponent.interf.SaveMusicPlayTimeCallback;
import com.pudutech.mpcomponent.utils.Lists;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class HMusicPlayer implements IMusicPlayerInterface, MediaPlayer.OnPreparedListener {
    private static final int DEMAND_PAUSE = 4;
    private static final int DEMAND_PLAY = 3;
    private static final int DEMAND_RELEASE = 7;
    private static final int DEMAND_RESET = 8;
    private static final int DEMAND_SAVE_PLAY_POINT = 9;
    private static final int DEMAND_STOP = 5;
    private static final String TAG = "HMusicPlayer";
    private static volatile HMusicPlayer instance;
    private static volatile HandlerThread sHandlerThread;
    private static volatile Handler sMusicHandler;
    private boolean isConnectionPlay;
    private volatile MediaPlayer mMediaPlayer;
    private volatile List<Music> mMusicList;
    private volatile IMusicPlayerStateCallback mMusicPlayerStateCallback;
    private volatile PlayerState mPlayerState;
    private SaveMusicPlayTimeCallback saveMusicPlayTimeCallback;
    private volatile int currentPlayIndex = 0;
    private volatile boolean isInit = false;
    private volatile boolean isReset = false;

    static /* synthetic */ int access$1208(HMusicPlayer hMusicPlayer) {
        int i = hMusicPlayer.currentPlayIndex;
        hMusicPlayer.currentPlayIndex = i + 1;
        return i;
    }

    private HMusicPlayer() {
    }

    public static HMusicPlayer getInstance() {
        if (instance == null) {
            synchronized (HMusicPlayer.class) {
                if (instance == null) {
                    instance = new HMusicPlayer();
                }
            }
        }
        return instance;
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public IMusicPlayerInterface init() {
        innerInit();
        return this;
    }

    private void innerInit() {
        Pdlog.m3273d(TAG, "init music player isInit: " + this.isInit);
        if (this.isInit) {
            return;
        }
        Pdlog.m3273d(TAG, "Start initializing MusicPlayer components");
        if (sMusicHandler != null) {
            sMusicHandler.removeCallbacksAndMessages(null);
        }
        if (sHandlerThread != null && !sHandlerThread.isAlive()) {
            Pdlog.m3275i(TAG, "MusicThread has already dead, try to initialize a new one");
            try {
                try {
                    sHandlerThread.quit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                sHandlerThread = null;
            }
        }
        if (sHandlerThread == null) {
            sHandlerThread = new HandlerThread("MusicThread") { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.1
                @Override // android.os.HandlerThread
                protected void onLooperPrepared() {
                    if (HMusicPlayer.sMusicHandler != null) {
                        HMusicPlayer.sMusicHandler.removeCallbacksAndMessages(null);
                    }
                    Handler unused = HMusicPlayer.sMusicHandler = new Handler(HMusicPlayer.sHandlerThread.getLooper()) { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.1.1
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            switch (message.what) {
                                case 3:
                                    HMusicPlayer.this.innerStart();
                                    if (HMusicPlayer.this.isConnectionPlay) {
                                        HMusicPlayer.sMusicHandler.sendEmptyMessageDelayed(9, 2000L);
                                        return;
                                    }
                                    return;
                                case 4:
                                    HMusicPlayer.this.innerPause();
                                    HMusicPlayer.this.recentlyPlayRecordOnce();
                                    return;
                                case 5:
                                    HMusicPlayer.this.recentlyPlayRecordOnce();
                                    HMusicPlayer.this.innerStop();
                                    return;
                                case 6:
                                default:
                                    return;
                                case 7:
                                    HMusicPlayer.this.innerRelease();
                                    HMusicPlayer.this.recentlyPlayRecordOnce();
                                    return;
                                case 8:
                                    HMusicPlayer.this.innerReset();
                                    HMusicPlayer.this.recentlyPlayRecordOnce();
                                    return;
                                case 9:
                                    if (!HMusicPlayer.this.isConnectionPlay || HMusicPlayer.this.mMediaPlayer == null || HMusicPlayer.this.mMusicList == null || HMusicPlayer.this.saveMusicPlayTimeCallback == null || HMusicPlayer.this.mMusicList.isEmpty() || HMusicPlayer.this.mMusicList.size() - 1 < HMusicPlayer.this.currentPlayIndex) {
                                        return;
                                    }
                                    Music music = (Music) HMusicPlayer.this.mMusicList.get(HMusicPlayer.this.currentPlayIndex);
                                    int currentPosition = HMusicPlayer.this.mMediaPlayer.getCurrentPosition();
                                    music.setSeekTime(currentPosition);
                                    HMusicPlayer.this.saveMusicPlayTimeCallback.saveCallback(music.getModeId(), music.getId(), currentPosition);
                                    HMusicPlayer.sMusicHandler.sendEmptyMessageDelayed(9, 2000L);
                                    return;
                            }
                        }
                    };
                    Pdlog.m3273d(HMusicPlayer.TAG, "MusicThread initalizes successfully!");
                    HMusicPlayer.this.initRealMediaPlayer();
                }
            };
            sHandlerThread.start();
        } else {
            initRealMediaPlayer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recentlyPlayRecordOnce() {
        sMusicHandler.sendEmptyMessage(9);
        sMusicHandler.removeMessages(9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRealMediaPlayer() {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.post(new Runnable() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.2
                @Override // java.lang.Runnable
                public void run() {
                    HMusicPlayer.this.mMediaPlayer = new MediaPlayer();
                    HMusicPlayer.this.mMediaPlayer.setOnPreparedListener(HMusicPlayer.this);
                    HMusicPlayer.this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.2.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Pdlog.m3273d(HMusicPlayer.TAG, "start()->onCompletion() currentPlayIndex = " + HMusicPlayer.this.currentPlayIndex);
                            ((Music) HMusicPlayer.this.mMusicList.get(HMusicPlayer.this.currentPlayIndex)).setSeekTime(0L);
                            if (HMusicPlayer.this.currentPlayIndex >= HMusicPlayer.this.mMusicList.size() - 1) {
                                HMusicPlayer.this.currentPlayIndex = 0;
                            } else {
                                HMusicPlayer.access$1208(HMusicPlayer.this);
                            }
                            HMusicPlayer.this.isReset = true;
                            HMusicPlayer.this.innerStart();
                        }
                    });
                    HMusicPlayer.this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.2.2
                        @Override // android.media.MediaPlayer.OnErrorListener
                        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                            Pdlog.m3273d(HMusicPlayer.TAG, "start()->onError() i = " + i + "\ti1 = " + i2);
                            return true;
                        }
                    });
                    HMusicPlayer.this.isInit = true;
                    HMusicPlayer.this.isReset = true;
                    HMusicPlayer.this.callbackPlayerState(PlayerState.INITIALIZED);
                    Pdlog.m3273d(HMusicPlayer.TAG, "MusicPlayer components initializes successfully!");
                }
            });
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public IMusicPlayerInterface updatePlaylist(final List<Music> list) {
        if (!checkMusicHandlerNotNull()) {
            return this;
        }
        sMusicHandler.post(new Runnable() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.3
            @Override // java.lang.Runnable
            public void run() {
                HMusicPlayer.this.innerUpdatePlaylist(list);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerUpdatePlaylist(List<Music> list) {
        this.mMusicList = list;
        innerReset();
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void setAudioStreamType(final int i) {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.post(new Runnable() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.4
                @Override // java.lang.Runnable
                public void run() {
                    HMusicPlayer.this.innerSetAudioStreamType(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerSetAudioStreamType(int i) {
        if (checkMediaPlayerNotNull()) {
            Pdlog.m3273d(TAG, "setAudioStreamType() streamType = " + i);
            try {
                this.mMediaPlayer.setAudioStreamType(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void setVolume(final float f, final float f2) {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.post(new Runnable() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.5
                @Override // java.lang.Runnable
                public void run() {
                    HMusicPlayer.this.innerSetVolume(f, f2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerSetVolume(float f, float f2) {
        if (checkMediaPlayerNotNull()) {
            Pdlog.m3273d(TAG, "setVolume() leftVolume = " + f + "\trightVolume = " + f2);
            try {
                this.mMediaPlayer.setVolume(f, f2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void setMusicPlayerStateCallback(IMusicPlayerStateCallback iMusicPlayerStateCallback) {
        this.mMusicPlayerStateCallback = iMusicPlayerStateCallback;
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void start(boolean z) {
        if (checkMusicHandlerNotNull()) {
            this.isConnectionPlay = z;
            sMusicHandler.sendEmptyMessage(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerStart() {
        if (checkMediaPlayerNotNull()) {
            if (Lists.isEmpty(this.mMusicList)) {
                Pdlog.m3274e(TAG, "start() mMusicList is null or empty.");
                return;
            }
            Pdlog.m3273d(TAG, "start() isReset = " + this.isReset + "\tmMusicList = " + this.mMusicList);
            try {
                if (this.isReset) {
                    Music music = this.mMusicList.get(this.currentPlayIndex);
                    this.mMediaPlayer.reset();
                    this.mMediaPlayer.setDataSource(music.getPath());
                    this.mMediaPlayer.prepare();
                    this.mMediaPlayer.seekTo((int) music.getSeekTime());
                } else {
                    this.mMediaPlayer.start();
                }
            } catch (Exception e) {
                callbackPlayerState(PlayerState.ERROR);
                e.printStackTrace();
            }
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void pause() {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.sendEmptyMessage(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerPause() {
        if (checkMediaPlayerNotNull()) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
            }
            callbackPlayerState(PlayerState.PAUSE);
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void stop() {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.sendEmptyMessage(5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerStop() {
        if (checkMediaPlayerNotNull()) {
            Pdlog.m3273d(TAG, "stop()");
            this.mMediaPlayer.stop();
            callbackPlayerState(PlayerState.STOP);
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void reset() {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.sendEmptyMessage(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerReset() {
        if (checkMediaPlayerNotNull()) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
            }
            this.mMediaPlayer.reset();
            this.isReset = true;
            this.currentPlayIndex = 0;
            callbackPlayerState(PlayerState.RESET);
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void switchSong(final Music music, final boolean z) {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.post(new Runnable() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.6
                @Override // java.lang.Runnable
                public void run() {
                    HMusicPlayer.this.innerSwitchSong(music, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerSwitchSong(Music music, boolean z) {
        if (checkMediaPlayerNotNull()) {
            if (music == null) {
                Pdlog.m3277w(TAG, "target song is empty, please check the file path");
                return;
            }
            if (Lists.isEmpty(this.mMusicList)) {
                innerUpdatePlaylist(Lists.newArrayList(music));
                innerStart();
                return;
            }
            boolean equals = TextUtils.equals(music.getPath(), this.mMusicList.get(this.currentPlayIndex).getPath());
            if (z) {
                if (equals) {
                    if (this.mMediaPlayer.isPlaying()) {
                        innerPause();
                        return;
                    } else {
                        innerStart();
                        return;
                    }
                }
                if (this.mMediaPlayer.isPlaying()) {
                    innerPause();
                }
                innerUpdatePlaylist(Lists.newArrayList(music));
                innerStart();
                return;
            }
            if (equals) {
                if (this.mMediaPlayer.isPlaying()) {
                    Pdlog.m3273d(TAG, "continue this song, do nothing.");
                    return;
                } else {
                    innerStart();
                    return;
                }
            }
            if (this.mMediaPlayer.isPlaying()) {
                innerPause();
            }
            innerUpdatePlaylist(Lists.newArrayList(music));
            innerStart();
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(final MediaPlayer mediaPlayer) {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.post(new Runnable() { // from class: com.pudutech.mpcomponent.mediaplayer.HMusicPlayer.7
                @Override // java.lang.Runnable
                public void run() {
                    HMusicPlayer.this.innerOnprepared(mediaPlayer);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerOnprepared(MediaPlayer mediaPlayer) {
        if (Lists.isEmpty(this.mMusicList)) {
            return;
        }
        try {
            Pdlog.m3273d(TAG, "onPrepared() mediaPlayer = " + mediaPlayer + "\tstart play music:" + this.mMusicList.get(this.currentPlayIndex).getPath());
            this.isReset = false;
            mediaPlayer.start();
            if (this.mPlayerState != PlayerState.PREPARED) {
                callbackPlayerState(PlayerState.PREPARED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.isReset = true;
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void release() {
        if (checkMusicHandlerNotNull()) {
            sMusicHandler.sendEmptyMessage(7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void innerRelease() {
        Pdlog.m3273d(TAG, "release musicplayer.");
        if (sMusicHandler != null) {
            sMusicHandler.removeCallbacksAndMessages(null);
        }
        try {
            if (checkMediaPlayerNotNull()) {
                try {
                    if (this.mMediaPlayer.isPlaying()) {
                        this.mMediaPlayer.pause();
                    }
                    this.mMediaPlayer.stop();
                    this.mMediaPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            callbackPlayerState(PlayerState.RELEASE);
            this.mMusicPlayerStateCallback = null;
            if (this.mMusicList != null) {
                this.mMusicList.clear();
            }
            this.isInit = false;
        } finally {
            this.mMediaPlayer = null;
        }
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public boolean isInit() {
        return this.isInit;
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void setSaveTimeCallback(SaveMusicPlayTimeCallback saveMusicPlayTimeCallback) {
        this.saveMusicPlayTimeCallback = saveMusicPlayTimeCallback;
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerInterface
    public void resetConnectionPlayMode(boolean z) {
        this.isConnectionPlay = z;
        if (z) {
            sMusicHandler.sendEmptyMessage(9);
            this.isReset = true;
        } else {
            sMusicHandler.removeMessages(9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPlayerState(PlayerState playerState) {
        this.mPlayerState = playerState;
        if (this.mMusicPlayerStateCallback == null) {
            return;
        }
        if (playerState == PlayerState.INITIALIZED) {
            this.mMusicPlayerStateCallback.onInitialized();
            return;
        }
        if (playerState == PlayerState.PREPARED) {
            this.mMusicPlayerStateCallback.onPrepared();
            return;
        }
        if (playerState == PlayerState.PAUSE) {
            this.mMusicPlayerStateCallback.onPause();
            return;
        }
        if (playerState == PlayerState.STOP) {
            this.mMusicPlayerStateCallback.onStop();
            return;
        }
        if (playerState == PlayerState.ERROR) {
            this.mMusicPlayerStateCallback.onError();
            return;
        }
        if (playerState == PlayerState.RESET) {
            this.mMusicPlayerStateCallback.onReset();
        } else if (playerState == PlayerState.RELEASE) {
            this.mMusicPlayerStateCallback.onRelease();
        } else if (playerState == PlayerState.COMPLETION) {
            this.mMusicPlayerStateCallback.onCompletion();
        }
    }

    private boolean checkMediaPlayerNotNull() {
        if (this.mMediaPlayer != null) {
            return true;
        }
        Pdlog.m3274e(TAG, "MediaPlayer is null.");
        return false;
    }

    private boolean checkMusicHandlerNotNull() {
        if (sMusicHandler != null) {
            return true;
        }
        Pdlog.m3274e(TAG, "MusicThreadHandler is null.");
        return false;
    }
}
