package com.pudutech.mpmodule.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpcomponent.MusicPlayerFactory;
import com.pudutech.mpcomponent.interf.IMusicPlayerInterface;
import com.pudutech.mpcomponent.interf.SaveMusicPlayTimeCallback;
import com.pudutech.mpmodule.MusicPlayerApp;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.PlayMode;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.bean.PreviousPlayBean;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.mpmodule.utils.AppCommonUtil;
import com.pudutech.mpmodule.utils.Lists;
import com.pudutech.mpmodule.utils.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MusicPlayerService extends Service {
    private static final int CMD_PAUSE_PLAY = 2;
    private static final int CMD_PLAY_SINGLE_SONG = 7;
    private static final int CMD_RELEASE = 5;
    private static final int CMD_RESET = 4;
    private static final int CMD_START_PLAY = 1;
    private static final int CMD_STOP_PLAY = 3;
    private static final int CMD_SWITCH_CONNECTION_PLAY_MODE = 8;
    private static final int CMD_SWITCH_SONG = 6;
    private static final String KEY_CMD = "key_cmd";
    private static final String KEY_IS_CONNCEIONT_PLAY = "key_is_connceiont_play";
    private static final String KEY_MODE = "key_mode";
    private static final String KEY_SONG = "key_song";
    private static final String TAG = "MusicPlayerService";
    private static int audioStreamType = 3;
    private static float leftVolume = 1.0f;
    private static float rightVolume = 1.0f;
    private static IMusicPlayerInterface sMusicPlayer;
    private ModeEnum lastMode;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startPlay(ModeEnum modeEnum) {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 1);
        intent.putExtra(KEY_MODE, modeEnum);
        context.startService(intent);
    }

    public static void pausePlay() {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 2);
        context.startService(intent);
    }

    public static void stopPlay() {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 3);
        context.startService(intent);
    }

    public static void reset() {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 4);
        context.startService(intent);
    }

    public static void release() {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 5);
        context.startService(intent);
    }

    public static void resetConnectionPlayMode(boolean z) {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 8);
        intent.putExtra(KEY_IS_CONNCEIONT_PLAY, z);
        context.startService(intent);
    }

    public static void setVolume(float f, float f2) {
        leftVolume = f;
        rightVolume = f2;
        initMusicPlayer();
    }

    public static void setAudioStreamType(int i) {
        audioStreamType = i;
        initMusicPlayer();
    }

    public static void switchSong(Music music) {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 6);
        intent.putExtra(KEY_SONG, music);
        context.startService(intent);
    }

    public static void playSinleSong(Music music) {
        Context context = MusicPlayerApp.getInstance().getContext();
        Intent intent = new Intent(context, (Class<?>) MusicPlayerService.class);
        intent.putExtra(KEY_CMD, 7);
        intent.putExtra(KEY_SONG, music);
        context.startService(intent);
    }

    private static void initMusicPlayer() {
        IMusicPlayerInterface iMusicPlayerInterface = sMusicPlayer;
        if (iMusicPlayerInterface == null || !iMusicPlayerInterface.isInit()) {
            sMusicPlayer = MusicPlayerFactory.getMusicPlayer().init();
        }
        sMusicPlayer.setVolume(leftVolume, rightVolume);
        sMusicPlayer.setAudioStreamType(audioStreamType);
        sMusicPlayer.setSaveTimeCallback(new SaveMusicPlayTimeCallback() { // from class: com.pudutech.mpmodule.service.-$$Lambda$MusicPlayerService$7JsCt7EfanGVKz3c600zf3F-DaY
            @Override // com.pudutech.mpcomponent.interf.SaveMusicPlayTimeCallback
            public final void saveCallback(String str, String str2, int i) {
                DatabaseManagerFactory.getDatabaseManager().insertOrReplacePreviousPlay(new PreviousPlayBean(str, str2, i));
            }
        });
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        PreviousPlayBean previousPlayBean;
        if (intent != null) {
            int intExtra = intent.getIntExtra(KEY_CMD, 1);
            ArrayList arrayList = null;
            if (intExtra == 1) {
                initMusicPlayer();
                ModeEnum modeEnum = (ModeEnum) intent.getSerializableExtra(KEY_MODE);
                String id = modeEnum != null ? modeEnum.getId() : null;
                Pdlog.m3273d(TAG, "startPlay() mode = " + modeEnum + "\tlastMode = " + this.lastMode);
                boolean isOpenConnectionPlay = AppCommonUtil.isOpenConnectionPlay();
                if (modeEnum != this.lastMode) {
                    if (modeEnum != null) {
                        Pdlog.m3273d(TAG, "startPlay() modeId = " + id);
                        PlayMode queryPlayMode = DatabaseManagerFactory.getDatabaseManager().queryPlayMode(id);
                        Pdlog.m3273d(TAG, "startPlay() playMode = " + queryPlayMode);
                        if (isOpenConnectionPlay) {
                            previousPlayBean = DatabaseManagerFactory.getDatabaseManager().queryPreviousPlayByModeId(id);
                        } else {
                            DatabaseManagerFactory.getDatabaseManager().deletePreviousPlayByModeId(id);
                            previousPlayBean = null;
                        }
                        if (queryPlayMode != null) {
                            List<String> playListIds = queryPlayMode.getPlayListIds();
                            if (Lists.isNotEmpty(playListIds)) {
                                arrayList = Lists.newArrayList();
                                Iterator<String> it = playListIds.iterator();
                                while (it.hasNext()) {
                                    Playlist queryPlaylistById = DatabaseManagerFactory.getDatabaseManager().queryPlaylistById(it.next());
                                    if (queryPlaylistById != null && !Lists.isEmpty(queryPlaylistById.getMediaList())) {
                                        ArrayList arrayList2 = new ArrayList();
                                        int i3 = -1;
                                        for (int i4 = 0; i4 < queryPlaylistById.getMediaList().size(); i4++) {
                                            Media media = queryPlaylistById.getMediaList().get(i4);
                                            Music music = new Music();
                                            music.setPath(media.getPath());
                                            music.setId(media.getId());
                                            music.setModeId(id);
                                            if (previousPlayBean != null && !TextUtils.isEmpty(previousPlayBean.getPlayedId()) && previousPlayBean.getPlayedId().equals(media.getId())) {
                                                music.setSeekTime(previousPlayBean.getSeekTime());
                                                i3 = i4;
                                            }
                                            if (i3 >= 0) {
                                                arrayList.add(music);
                                            } else {
                                                arrayList2.add(music);
                                            }
                                        }
                                        arrayList.addAll(arrayList2);
                                    }
                                }
                            }
                        }
                    }
                    Pdlog.m3273d(TAG, "startPlay() musicList = " + arrayList);
                    sMusicPlayer.updatePlaylist(arrayList);
                }
                this.lastMode = modeEnum;
                boolean isOpenMusicSwitch = AppCommonUtil.isOpenMusicSwitch();
                boolean isOpenConnectionPlay2 = AppCommonUtil.isOpenConnectionPlay();
                if (isOpenMusicSwitch) {
                    sMusicPlayer.start(isOpenConnectionPlay2);
                } else if (StringUtil.equals(ModeEnum.BIRTHDAY.getId(), id)) {
                    sMusicPlayer.start(isOpenConnectionPlay2);
                }
            } else if (intExtra == 2) {
                IMusicPlayerInterface iMusicPlayerInterface = sMusicPlayer;
                if (iMusicPlayerInterface != null) {
                    iMusicPlayerInterface.pause();
                }
            } else if (intExtra == 3) {
                IMusicPlayerInterface iMusicPlayerInterface2 = sMusicPlayer;
                if (iMusicPlayerInterface2 != null) {
                    iMusicPlayerInterface2.stop();
                }
            } else if (intExtra == 4) {
                this.lastMode = null;
                IMusicPlayerInterface iMusicPlayerInterface3 = sMusicPlayer;
                if (iMusicPlayerInterface3 != null) {
                    iMusicPlayerInterface3.reset();
                }
            } else {
                if (intExtra == 5) {
                    stopSelf();
                    this.lastMode = null;
                    IMusicPlayerInterface iMusicPlayerInterface4 = sMusicPlayer;
                    if (iMusicPlayerInterface4 != null) {
                        iMusicPlayerInterface4.release();
                        sMusicPlayer = null;
                    }
                    return 2;
                }
                if (intExtra == 6 || intExtra == 7) {
                    this.lastMode = null;
                    initMusicPlayer();
                    sMusicPlayer.switchSong((Music) intent.getSerializableExtra(KEY_SONG), intExtra == 6);
                } else if (intExtra == 8 && sMusicPlayer != null) {
                    boolean booleanExtra = intent.getBooleanExtra(KEY_IS_CONNCEIONT_PLAY, false);
                    if (!booleanExtra) {
                        DatabaseManagerFactory.getDatabaseManager().deleteAllPreviousPlay();
                    }
                    sMusicPlayer.resetConnectionPlayMode(booleanExtra);
                }
            }
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.lastMode = null;
        IMusicPlayerInterface iMusicPlayerInterface = sMusicPlayer;
        if (iMusicPlayerInterface != null) {
            iMusicPlayerInterface.release();
            sMusicPlayer = null;
        }
    }
}
