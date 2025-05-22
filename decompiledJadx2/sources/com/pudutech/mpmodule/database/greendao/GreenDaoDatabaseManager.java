package com.pudutech.mpmodule.database.greendao;

import android.content.Context;
import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.PlayMode;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.bean.PreviousPlayBean;
import com.pudutech.mpmodule.database.greendao.PlayModeDao;
import com.pudutech.mpmodule.database.greendao.PlaylistDao;
import com.pudutech.mpmodule.database.greendao.PreviousPlayBeanDao;
import com.pudutech.mpmodule.database.interf.IDatabaseInterface;
import com.pudutech.mpmodule.database.interf.QueryPlayListCallBacker;
import com.pudutech.mpmodule.utils.CThreadPoolExecutor;
import com.pudutech.mpmodule.utils.Lists;
import com.pudutech.mpmodule.utils.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class GreenDaoDatabaseManager implements IDatabaseInterface {
    private static GreenDaoDatabaseManager instance;
    private List<ModeEnum> currentModes = Lists.newArrayList(ModeEnum.DELIVERY_AND_RETURNING, ModeEnum.BIRTHDAY, ModeEnum.BIRTHDAY_BACK, ModeEnum.CRUISE, ModeEnum.ARRIVED, ModeEnum.NEWYEAR, ModeEnum.SPECIAL, ModeEnum.GREETER, ModeEnum.GUIDE, ModeEnum.GUIDE_BACK, ModeEnum.RECYCLE);
    private PlaylistDao playlistDao = GreenDaoDatabaseHelper.getInstance().getPlaylistDao();
    private PlayModeDao playModeDao = GreenDaoDatabaseHelper.getInstance().getPlayModeDao();
    private PreviousPlayBeanDao previousPlayDao = GreenDaoDatabaseHelper.getInstance().getPreviousPlayDao();

    private boolean checkDao(AbstractDao abstractDao) {
        return abstractDao != null;
    }

    private GreenDaoDatabaseManager() {
    }

    public static GreenDaoDatabaseManager getInstance() {
        if (instance == null) {
            synchronized (GreenDaoDatabaseManager.class) {
                if (instance == null) {
                    instance = new GreenDaoDatabaseManager();
                    Pdlog.m3273d("DatabaseMng", "instance:" + instance.hashCode());
                }
            }
        }
        return instance;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void initDatabase(List<ModeEnum> list) {
        initDatabase();
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void initDatabase() {
        GreenDaoDatabaseHelper.getInstance().initDatabase();
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void initDefaultPlaylists(final Context context) {
        CThreadPoolExecutor.runInBackground(new Runnable() { // from class: com.pudutech.mpmodule.database.greendao.-$$Lambda$GreenDaoDatabaseManager$TvdAcv-XrVNQla7Ppt4X7f3MQR4
            @Override // java.lang.Runnable
            public final void run() {
                GreenDaoDatabaseManager.this.lambda$initDefaultPlaylists$0$GreenDaoDatabaseManager(context);
            }
        });
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void getMusicListByMode(final ModeEnum modeEnum, final QueryPlayListCallBacker queryPlayListCallBacker, final boolean z, final Context context) {
        if (modeEnum == null || context == null) {
            return;
        }
        CThreadPoolExecutor.runInBackground(new Runnable() { // from class: com.pudutech.mpmodule.database.greendao.-$$Lambda$GreenDaoDatabaseManager$h5MVAK1EzHG8zQ3pohgc2nkrmEQ
            @Override // java.lang.Runnable
            public final void run() {
                GreenDaoDatabaseManager.this.lambda$getMusicListByMode$2$GreenDaoDatabaseManager(z, context, modeEnum, queryPlayListCallBacker);
            }
        });
    }

    public /* synthetic */ void lambda$getMusicListByMode$2$GreenDaoDatabaseManager(boolean z, Context context, ModeEnum modeEnum, final QueryPlayListCallBacker queryPlayListCallBacker) {
        if (z) {
            lambda$initDefaultPlaylists$0$GreenDaoDatabaseManager(context);
        }
        PlayMode queryPlayMode = queryPlayMode(modeEnum.getId());
        final Playlist playlist = null;
        if (queryPlayMode != null) {
            List<String> playListIds = queryPlayMode.getPlayListIds();
            if (Lists.isNotEmpty(playListIds)) {
                playlist = queryPlaylistById(playListIds.get(0));
            }
        }
        if (queryPlayListCallBacker != null) {
            CThreadPoolExecutor.runOnMainThread(new Runnable() { // from class: com.pudutech.mpmodule.database.greendao.-$$Lambda$GreenDaoDatabaseManager$x-uczNTPFjegvQDX97VUK7pQkiI
                @Override // java.lang.Runnable
                public final void run() {
                    QueryPlayListCallBacker.this.onGetPlaylist(playlist);
                }
            });
        }
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void savePlaylist(List<Playlist> list) {
        if (!Lists.isEmpty(list) && checkDao(this.playlistDao)) {
            this.playlistDao.insertOrReplaceInTx(list);
        }
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void savePlaylist(Playlist playlist) {
        if (playlist != null && checkDao(this.playlistDao)) {
            this.playlistDao.insertOrReplace(playlist);
        }
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void deletePlaylist(Playlist playlist) {
        if (playlist != null && checkDao(this.playlistDao)) {
            this.playlistDao.delete(playlist);
        }
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public List<Playlist> queryAllPlaylists() {
        if (checkDao(this.playlistDao)) {
            return this.playlistDao.queryBuilder().build().list();
        }
        return null;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public Playlist queryPlaylistById(String str) {
        if (!StringUtil.isEmpty(str) && checkDao(this.playlistDao)) {
            return this.playlistDao.queryBuilder().where(PlaylistDao.Properties.ListId.m4147eq(str), new WhereCondition[0]).build().unique();
        }
        return null;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public Playlist queryPlaylistByName(String str) {
        if (!StringUtil.isEmpty(str) && checkDao(this.playlistDao)) {
            return this.playlistDao.queryBuilder().where(PlaylistDao.Properties.ListName.m4147eq(str), new WhereCondition[0]).build().unique();
        }
        return null;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public List<Playlist> queryAllDefaultPlaylists() {
        if (checkDao(this.playlistDao)) {
            return this.playlistDao.queryBuilder().where(PlaylistDao.Properties.IsCustom.m4147eq(false), new WhereCondition[0]).build().list();
        }
        return null;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public boolean isExistsSamePlaylist(String str) {
        if (StringUtil.isEmpty(str) || !checkDao(this.playlistDao)) {
            return false;
        }
        List<Playlist> queryAllPlaylists = queryAllPlaylists();
        if (Lists.isEmpty(queryAllPlaylists)) {
            return false;
        }
        Iterator<Playlist> it = queryAllPlaylists.iterator();
        while (it.hasNext()) {
            if (StringUtil.equals(str, it.next().getListName())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void saveMode(List<PlayMode> list) {
        if (!Lists.isEmpty(list) && checkDao(this.playModeDao)) {
            this.playModeDao.insertOrReplaceInTx(list);
        }
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void saveMode(PlayMode playMode) {
        if (playMode != null && checkDao(this.playModeDao)) {
            this.playModeDao.insertOrReplace(playMode);
        }
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public List<PlayMode> queryAllPlayModes() {
        if (!checkDao(this.playModeDao)) {
            return null;
        }
        List<PlayMode> list = this.playModeDao.queryBuilder().build().list();
        Collections.sort(list, new Comparator<PlayMode>() { // from class: com.pudutech.mpmodule.database.greendao.GreenDaoDatabaseManager.1
            @Override // java.util.Comparator
            public int compare(PlayMode playMode, PlayMode playMode2) {
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i2 >= GreenDaoDatabaseManager.this.currentModes.size()) {
                        i2 = 0;
                        break;
                    }
                    if (TextUtils.equals(((ModeEnum) GreenDaoDatabaseManager.this.currentModes.get(i2)).getId(), playMode.getModeId())) {
                        break;
                    }
                    i2++;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= GreenDaoDatabaseManager.this.currentModes.size()) {
                        break;
                    }
                    if (TextUtils.equals(((ModeEnum) GreenDaoDatabaseManager.this.currentModes.get(i3)).getId(), playMode.getModeId())) {
                        i = i3;
                        break;
                    }
                    i3++;
                }
                return i2 - i;
            }
        });
        return list;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public PlayMode queryPlayMode(String str) {
        if (checkDao(this.playModeDao)) {
            return this.playModeDao.queryBuilder().where(PlayModeDao.Properties.ModeId.m4147eq(str), new WhereCondition[0]).build().unique();
        }
        return null;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public boolean hasMeida(String str) {
        PlayMode queryPlayMode;
        boolean z = false;
        if (StringUtil.isEmpty(str) || (queryPlayMode = queryPlayMode(str)) == null) {
            return false;
        }
        List<String> playListIds = queryPlayMode.getPlayListIds();
        if (Lists.isEmpty(playListIds)) {
            return false;
        }
        Iterator<String> it = playListIds.iterator();
        while (it.hasNext()) {
            Playlist queryPlaylistById = queryPlaylistById(it.next());
            if (queryPlaylistById != null && !Lists.isEmpty(queryPlaylistById.getMediaList())) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public boolean isInitDefaultModes() {
        return checkDao(this.playModeDao) && this.playModeDao.queryBuilder().build().list().size() > 0;
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public void release() {
        GreenDaoDatabaseHelper.getInstance().release();
    }

    @Override // com.pudutech.mpmodule.database.interf.IDatabaseInterface
    public synchronized void dealWithLegacy() {
        Pdlog.m3273d("DatabaseMng", "dealWithLegacy: start");
        List<PlayMode> list = this.playModeDao.queryBuilder().where(PlayModeDao.Properties.ModeId.m4147eq("1003"), new WhereCondition[0]).build().list();
        if (list != null && !list.isEmpty()) {
            Pdlog.m3273d("DatabaseMng", "dealWithLegacy: oldBirthdayModeList.size = " + list.size());
            List<PlayMode> list2 = this.playModeDao.queryBuilder().where(PlayModeDao.Properties.ModeId.m4147eq("1002"), new WhereCondition[0]).build().list();
            if (list2 != null && !list2.isEmpty()) {
                Pdlog.m3273d("DatabaseMng", "dealWithLegacy: newBirthdayModeList is not empty");
                return;
            }
            PlayMode playMode = list.get(0);
            playMode.setModeId(ModeEnum.BIRTHDAY.getId());
            this.playModeDao.update(playMode);
            Pdlog.m3273d("DatabaseMng", "dealWithLegacy: end");
            return;
        }
        Pdlog.m3273d("DatabaseMng", "dealWithLegacy: oldBirthdayModeList is empty");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initDefaultLists, reason: merged with bridge method [inline-methods] */
    public synchronized void lambda$initDefaultPlaylists$0$GreenDaoDatabaseManager(Context context) {
        boolean z;
        ArrayList newArrayList = Lists.newArrayList();
        ArrayList newArrayList2 = Lists.newArrayList();
        for (ModeEnum modeEnum : this.currentModes) {
            newArrayList.add(modeEnum.getId());
            newArrayList2.add(modeEnum.getName());
        }
        Pdlog.m3273d("DatabaseMng", "modeIds size " + newArrayList.size());
        this.playModeDao.queryBuilder().where(PlayModeDao.Properties.ModeId.notIn(newArrayList), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        this.playlistDao.queryBuilder().where(PlaylistDao.Properties.DefaultMode.notIn(newArrayList2), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        this.previousPlayDao.queryBuilder().where(PreviousPlayBeanDao.Properties.ModeId.notIn(newArrayList), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        List<Playlist> queryAllDefaultPlaylists = queryAllDefaultPlaylists();
        if (queryAllDefaultPlaylists == null) {
            queryAllDefaultPlaylists = Lists.newArrayList();
        }
        Pdlog.m3273d("DatabaseMng", "defaultlist size " + queryAllDefaultPlaylists.size());
        List<PlayMode> queryAllPlayModes = queryAllPlayModes();
        ArrayList newArrayList3 = Lists.newArrayList();
        if (queryAllPlayModes != null && queryAllPlayModes.size() > 0) {
            for (int i = 0; i < this.currentModes.size(); i++) {
                Iterator<PlayMode> it = queryAllPlayModes.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (TextUtils.equals(it.next().getModeId(), this.currentModes.get(i).getId())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    newArrayList3.add(this.currentModes.get(i));
                }
            }
        } else {
            newArrayList3.addAll(this.currentModes);
        }
        Pdlog.m3273d("DatabaseMng", "needAddList size " + newArrayList3.size());
        if (newArrayList3.size() > 0) {
            Pdlog.m3273d("DatabaseMng", "modes add");
            ArrayList newArrayList4 = Lists.newArrayList();
            ArrayList newArrayList5 = Lists.newArrayList();
            for (int i2 = 0; i2 < newArrayList3.size(); i2++) {
                Playlist playlist = new Playlist(UUID.randomUUID().toString(), context.getString(((ModeEnum) newArrayList3.get(i2)).getListNameResources()), false, (ModeEnum) newArrayList3.get(i2));
                newArrayList4.add(playlist);
                newArrayList5.add(new PlayMode(((ModeEnum) newArrayList3.get(i2)).getId(), (ModeEnum) newArrayList3.get(i2), Lists.newArrayList(playlist.getListId())));
            }
            savePlaylist(newArrayList4);
            saveMode(newArrayList5);
        }
        updateListsName(queryAllDefaultPlaylists, context);
    }

    private void updateListsName(List<Playlist> list, Context context) {
        if (Lists.isEmpty(list) || context == null || !checkDao(this.playlistDao)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Playlist playlist = list.get(i);
            if (!playlist.getIsCustom()) {
                playlist.setListName(context.getString(playlist.getDefaultMode().getListNameResources()));
            }
        }
        this.playlistDao.updateInTx(list);
    }

    @Override // com.pudutech.mpmodule.database.interf.PreviousDbInterface
    public PreviousPlayBean queryPreviousPlayByModeId(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.previousPlayDao.queryBuilder().where(PreviousPlayBeanDao.Properties.ModeId.m4147eq(str), new WhereCondition[0]).build().unique();
    }

    @Override // com.pudutech.mpmodule.database.interf.PreviousDbInterface
    public void deletePreviousPlayByModeId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.previousPlayDao.queryBuilder().where(PreviousPlayBeanDao.Properties.ModeId.m4147eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.pudutech.mpmodule.database.interf.PreviousDbInterface
    public void insertOrReplacePreviousPlay(PreviousPlayBean previousPlayBean) {
        if (previousPlayBean == null) {
            return;
        }
        this.previousPlayDao.insertOrReplace(previousPlayBean);
    }

    @Override // com.pudutech.mpmodule.database.interf.PreviousDbInterface
    public void deleteAllPreviousPlay() {
        this.previousPlayDao.deleteAll();
    }
}
