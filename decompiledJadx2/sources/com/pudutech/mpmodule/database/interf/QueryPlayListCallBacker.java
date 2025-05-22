package com.pudutech.mpmodule.database.interf;

import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.database.interf.IDatabaseInterface;
import com.pudutech.mpmodule.media.Media;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class QueryPlayListCallBacker implements IDatabaseInterface.DatabaseCallBack {
    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface.DatabaseCallBack
    public void onGetMedialist(List<Media> list) {
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface.DatabaseCallBack
    public void onGetPlaylist(Playlist playlist) {
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface.DatabaseCallBack
    public void onGetPlaylists(List<Playlist> list) {
    }
}
