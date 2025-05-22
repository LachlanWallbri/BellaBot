package com.pudutech.mpmodule.database.greendao;

import com.pudutech.mpmodule.bean.PlayMode;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.bean.PreviousPlayBean;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class DaoSession extends AbstractDaoSession {
    private final PlayModeDao playModeDao;
    private final DaoConfig playModeDaoConfig;
    private final PlaylistDao playlistDao;
    private final DaoConfig playlistDaoConfig;
    private final PreviousPlayBeanDao previousPlayBeanDao;
    private final DaoConfig previousPlayBeanDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.playModeDaoConfig = map.get(PlayModeDao.class).clone();
        this.playModeDaoConfig.initIdentityScope(identityScopeType);
        this.playlistDaoConfig = map.get(PlaylistDao.class).clone();
        this.playlistDaoConfig.initIdentityScope(identityScopeType);
        this.previousPlayBeanDaoConfig = map.get(PreviousPlayBeanDao.class).clone();
        this.previousPlayBeanDaoConfig.initIdentityScope(identityScopeType);
        this.playModeDao = new PlayModeDao(this.playModeDaoConfig, this);
        this.playlistDao = new PlaylistDao(this.playlistDaoConfig, this);
        this.previousPlayBeanDao = new PreviousPlayBeanDao(this.previousPlayBeanDaoConfig, this);
        registerDao(PlayMode.class, this.playModeDao);
        registerDao(Playlist.class, this.playlistDao);
        registerDao(PreviousPlayBean.class, this.previousPlayBeanDao);
    }

    public void clear() {
        this.playModeDaoConfig.clearIdentityScope();
        this.playlistDaoConfig.clearIdentityScope();
        this.previousPlayBeanDaoConfig.clearIdentityScope();
    }

    public PlayModeDao getPlayModeDao() {
        return this.playModeDao;
    }

    public PlaylistDao getPlaylistDao() {
        return this.playlistDao;
    }

    public PreviousPlayBeanDao getPreviousPlayBeanDao() {
        return this.previousPlayBeanDao;
    }
}
