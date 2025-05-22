package com.pudutech.mpmodule;

import android.content.Context;
import android.content.Intent;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpcomponent.MusicPlayerFactory;
import com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.database.interf.QueryPlayListCallBacker;
import com.pudutech.mpmodule.service.MusicPlayerService;
import com.pudutech.mpmodule.utils.AppCommonUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MusicPlayerHelper {
    public static MusicPlayerHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final MusicPlayerHelper INSTANCE = new MusicPlayerHelper();

        private SingletonHolder() {
        }
    }

    public void gotoMusicPlayer(Context context) {
        context.startActivity(new Intent(context, (Class<?>) HomeActivity.class));
    }

    public void startPlay(ModeEnum modeEnum) {
        MusicPlayerService.startPlay(modeEnum);
    }

    public void resetConnectionPlayMode(boolean z) {
        MusicPlayerService.resetConnectionPlayMode(z);
    }

    public void pausePlay() {
        MusicPlayerService.pausePlay();
    }

    public void stopPlay() {
        MusicPlayerService.stopPlay();
    }

    public void reset() {
        MusicPlayerService.reset();
    }

    public void release() {
        MusicPlayerService.release();
    }

    public void setVolume(float f, float f2) {
        MusicPlayerService.setVolume(f, f2);
    }

    public void switchSong(Music music) {
        MusicPlayerService.switchSong(music);
    }

    public void playSinleSong(Music music) {
        MusicPlayerService.playSinleSong(music);
    }

    public void setAudioStreamType(int i) {
        MusicPlayerService.setAudioStreamType(i);
    }

    public void setOpenMusicSwitch(boolean z) {
        AppCommonUtil.setOpenMusicSwitch(z);
    }

    public boolean isOpenMusicSwitch() {
        return AppCommonUtil.isOpenMusicSwitch();
    }

    public void setOpenNewYearSwitch(boolean z) {
        AppCommonUtil.setOpenNewYearSwitch(z);
    }

    public boolean isOpenNewYearSwitch() {
        return AppCommonUtil.isOpenNewYearSwitch();
    }

    public void setOpenBirthdaySwitch(boolean z) {
        AppCommonUtil.setOpenBirthdaySwitch(z);
    }

    public boolean isOpenBirthdaySwitch() {
        return AppCommonUtil.isOpenBirthdaySwitch();
    }

    public void setMusicPlayerStateCallback(IMusicPlayerStateCallback iMusicPlayerStateCallback) {
        MusicPlayerFactory.getMusicPlayer().setMusicPlayerStateCallback(iMusicPlayerStateCallback);
    }

    public void getMusicListByMode(Context context, ModeEnum modeEnum, QueryPlayListCallBacker queryPlayListCallBacker) {
        DatabaseManagerFactory.getDatabaseManager().getMusicListByMode(modeEnum, queryPlayListCallBacker, true, context);
    }

    public void gotoAddMusicForMode(final Context context, ModeEnum modeEnum) {
        DatabaseManagerFactory.getDatabaseManager().getMusicListByMode(modeEnum, new QueryPlayListCallBacker() { // from class: com.pudutech.mpmodule.MusicPlayerHelper.1
            @Override // com.pudutech.mpmodule.database.interf.QueryPlayListCallBacker, com.pudutech.mpmodule.database.interf.IDatabaseInterface.DatabaseCallBack
            public void onGetPlaylist(Playlist playlist) {
                Context context2 = context;
                if (context2 == null || playlist == null) {
                    return;
                }
                AddMusicActivity.actionActivity(context2, playlist);
            }
        }, false, context);
    }

    public void resetMusicProgress(ModeEnum modeEnum) {
        DatabaseManagerFactory.getDatabaseManager().deletePreviousPlayByModeId(modeEnum.getId());
    }
}
