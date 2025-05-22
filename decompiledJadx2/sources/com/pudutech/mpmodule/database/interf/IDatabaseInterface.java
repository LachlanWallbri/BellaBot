package com.pudutech.mpmodule.database.interf;

import android.content.Context;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.PlayMode;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.media.Media;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public interface IDatabaseInterface extends PreviousDbInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface DatabaseCallBack {
        void onGetMedialist(List<Media> list);

        void onGetPlaylist(Playlist playlist);

        void onGetPlaylists(List<Playlist> list);
    }

    void dealWithLegacy();

    void deletePlaylist(Playlist playlist);

    void getMusicListByMode(ModeEnum modeEnum, QueryPlayListCallBacker queryPlayListCallBacker, boolean z, Context context);

    boolean hasMeida(String str);

    void initDatabase();

    void initDatabase(List<ModeEnum> list);

    void initDefaultPlaylists(Context context);

    boolean isExistsSamePlaylist(String str);

    boolean isInitDefaultModes();

    List<Playlist> queryAllDefaultPlaylists();

    List<PlayMode> queryAllPlayModes();

    List<Playlist> queryAllPlaylists();

    PlayMode queryPlayMode(String str);

    Playlist queryPlaylistById(String str);

    Playlist queryPlaylistByName(String str);

    void release();

    void saveMode(PlayMode playMode);

    void saveMode(List<PlayMode> list);

    void savePlaylist(Playlist playlist);

    void savePlaylist(List<Playlist> list);
}
