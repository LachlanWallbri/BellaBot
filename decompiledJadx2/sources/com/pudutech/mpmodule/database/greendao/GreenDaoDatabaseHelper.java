package com.pudutech.mpmodule.database.greendao;

import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.MusicPlayerApp;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class GreenDaoDatabaseHelper {
    private static GreenDaoDatabaseHelper instance;
    private DaoSession daoSession;

    private GreenDaoDatabaseHelper() {
    }

    public static GreenDaoDatabaseHelper getInstance() {
        if (instance == null) {
            synchronized (GreenDaoDatabaseHelper.class) {
                if (instance == null) {
                    instance = new GreenDaoDatabaseHelper();
                }
            }
        }
        return instance;
    }

    public void initDatabase() {
        this.daoSession = new DaoMaster(new CDevOpenHelper(MusicPlayerApp.getInstance().getContext(), "music_player.db").getWritableDatabase()).newSession();
        Pdlog.m3273d("database", "database[music_player.db] initial successfully, daoSession is " + this.daoSession);
    }

    public PlaylistDao getPlaylistDao() {
        if (this.daoSession == null) {
            initDatabase();
        }
        DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            return null;
        }
        return daoSession.getPlaylistDao();
    }

    public PlayModeDao getPlayModeDao() {
        if (this.daoSession == null) {
            initDatabase();
        }
        DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            return null;
        }
        return daoSession.getPlayModeDao();
    }

    public PreviousPlayBeanDao getPreviousPlayDao() {
        if (this.daoSession == null) {
            initDatabase();
        }
        DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            return null;
        }
        return daoSession.getPreviousPlayBeanDao();
    }

    public void release() {
        DaoSession daoSession = this.daoSession;
        if (daoSession != null) {
            daoSession.clear();
            this.daoSession = null;
        }
        instance = null;
    }
}
