package com.pudutech.bumblebee.robot_ui.database.greendao;

import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import com.pudutech.bumblebee.robot_ui.bean.BeeperCard;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes2.dex */
public class DaoSession extends AbstractDaoSession {
    private final BeeperCardDao beeperCardDao;
    private final DaoConfig beeperCardDaoConfig;
    private final BeeperDao beeperDao;
    private final DaoConfig beeperDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.beeperDaoConfig = map.get(BeeperDao.class).clone();
        this.beeperDaoConfig.initIdentityScope(identityScopeType);
        this.beeperCardDaoConfig = map.get(BeeperCardDao.class).clone();
        this.beeperCardDaoConfig.initIdentityScope(identityScopeType);
        this.beeperDao = new BeeperDao(this.beeperDaoConfig, this);
        this.beeperCardDao = new BeeperCardDao(this.beeperCardDaoConfig, this);
        registerDao(Beeper.class, this.beeperDao);
        registerDao(BeeperCard.class, this.beeperCardDao);
    }

    public void clear() {
        this.beeperDaoConfig.clearIdentityScope();
        this.beeperCardDaoConfig.clearIdentityScope();
    }

    public BeeperDao getBeeperDao() {
        return this.beeperDao;
    }

    public BeeperCardDao getBeeperCardDao() {
        return this.beeperCardDao;
    }
}
