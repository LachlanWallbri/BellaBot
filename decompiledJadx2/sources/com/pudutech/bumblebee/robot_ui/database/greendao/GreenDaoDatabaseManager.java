package com.pudutech.bumblebee.robot_ui.database.greendao;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import com.pudutech.bumblebee.robot_ui.bean.BeeperCard;
import com.pudutech.bumblebee.robot_ui.database.greendao.BeeperCardDao;
import com.pudutech.bumblebee.robot_ui.database.greendao.BeeperDao;
import com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* compiled from: GreenDaoDatabaseManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 $2\u00020\u0001:\u0001$B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u0016H\u0016J&\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u0016H\u0016J\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016J\u0012\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u001e\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0013H\u0016J$\u0010 \u001a\u00020\b2\u001a\u0010!\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u0016H\u0016J$\u0010\"\u001a\u00020\b2\u001a\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u0016H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseManager;", "Lcom/pudutech/bumblebee/robot_ui/database/interf/IDatabaseInterface;", "()V", "beeperCardDao", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/BeeperCardDao;", "beeperDao", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/BeeperDao;", "deleteAllBeeperCardList", "", "deleteAllBeeperList", "deleteBeeper", "beeperMac", "", "deleteBeeperCard", "rfid", "init", "queryBeeper", "Lcom/pudutech/bumblebee/robot_ui/bean/Beeper;", "queryBeeperCard", "Lcom/pudutech/bumblebee/robot_ui/bean/BeeperCard;", "queryBeeperCardList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "callPointName", "queryBeeperList", "queryCallPoint", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "release", "saveBeeper", "beeper", "saveBeeperCard", "beeperCard", "saveBeeperCardList", "beeperCardList", "saveBeeperList", "beeperList", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class GreenDaoDatabaseManager implements IDatabaseInterface {
    public static final String TAG = "GreenDaoDatabaseManager";
    private BeeperCardDao beeperCardDao;
    private BeeperDao beeperDao;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<GreenDaoDatabaseManager>() { // from class: com.pudutech.bumblebee.robot_ui.database.greendao.GreenDaoDatabaseManager$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GreenDaoDatabaseManager invoke() {
            return new GreenDaoDatabaseManager(null);
        }
    });

    private GreenDaoDatabaseManager() {
        this.beeperCardDao = GreenDaoDatabaseHelper.INSTANCE.getInstance().getBeeperCardDao();
        this.beeperDao = GreenDaoDatabaseHelper.INSTANCE.getInstance().getBeeperDao();
        Pdlog.m3273d(TAG, "auto init DatabaseManager");
    }

    public /* synthetic */ GreenDaoDatabaseManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: GreenDaoDatabaseManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseManager$Companion;", "", "()V", "TAG", "", "instance", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseManager;", "getInstance", "()Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseManager;", "instance$delegate", "Lkotlin/Lazy;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        public final GreenDaoDatabaseManager getInstance() {
            Lazy lazy = GreenDaoDatabaseManager.instance$delegate;
            Companion companion = GreenDaoDatabaseManager.INSTANCE;
            return (GreenDaoDatabaseManager) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void init() {
        Pdlog.m3273d(TAG, "init");
        release();
        GreenDaoDatabaseHelper.INSTANCE.getInstance().init();
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void saveBeeperCard(BeeperCard beeperCard) {
        BeeperCardDao beeperCardDao;
        Pdlog.m3273d(TAG, "saveBeeperCard " + beeperCard);
        if (beeperCard == null || (beeperCardDao = this.beeperCardDao) == null) {
            return;
        }
        beeperCardDao.insertOrReplace(beeperCard);
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void saveBeeperCardList(ArrayList<BeeperCard> beeperCardList) {
        BeeperCardDao beeperCardDao;
        Pdlog.m3273d(TAG, "saveBeeperCardList " + beeperCardList);
        if (beeperCardList == null || (beeperCardDao = this.beeperCardDao) == null) {
            return;
        }
        beeperCardDao.insertOrReplaceInTx(beeperCardList);
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public BeeperCard queryBeeperCard(String rfid) {
        BeeperCardDao beeperCardDao;
        QueryBuilder<BeeperCard> queryBuilder;
        QueryBuilder<BeeperCard> where;
        Query<BeeperCard> build;
        Pdlog.m3273d(TAG, "queryBeeperCard " + rfid);
        if (rfid == null || (beeperCardDao = this.beeperCardDao) == null || (queryBuilder = beeperCardDao.queryBuilder()) == null || (where = queryBuilder.where(BeeperCardDao.Properties.Rfid.m4147eq(rfid), new WhereCondition[0])) == null || (build = where.build()) == null) {
            return null;
        }
        return build.unique();
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public ArrayList<BeeperCard> queryBeeperCardList(String callPointName) {
        QueryBuilder<BeeperCard> queryBuilder;
        QueryBuilder<BeeperCard> where;
        Query<BeeperCard> build;
        Pdlog.m3273d(TAG, "queryBeeperCardList " + callPointName);
        List<BeeperCard> list = null;
        if (callPointName == null) {
            return null;
        }
        BeeperCardDao beeperCardDao = this.beeperCardDao;
        if (beeperCardDao != null && (queryBuilder = beeperCardDao.queryBuilder()) != null && (where = queryBuilder.where(BeeperCardDao.Properties.CallPointName.m4147eq(callPointName), new WhereCondition[0])) != null && (build = where.build()) != null) {
            list = build.list();
        }
        return (ArrayList) list;
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public ArrayList<BeeperCard> queryBeeperCardList() {
        QueryBuilder<BeeperCard> queryBuilder;
        Query<BeeperCard> build;
        Pdlog.m3273d(TAG, "queryBeeperCardList");
        BeeperCardDao beeperCardDao = this.beeperCardDao;
        return (ArrayList) ((beeperCardDao == null || (queryBuilder = beeperCardDao.queryBuilder()) == null || (build = queryBuilder.build()) == null) ? null : build.list());
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void deleteBeeperCard(String rfid) {
        BeeperCardDao beeperCardDao;
        Pdlog.m3273d(TAG, "deleteBeeperCard " + rfid);
        BeeperCard queryBeeperCard = queryBeeperCard(rfid);
        if (queryBeeperCard == null || (beeperCardDao = this.beeperCardDao) == null) {
            return;
        }
        beeperCardDao.delete(queryBeeperCard);
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void deleteAllBeeperCardList() {
        Pdlog.m3273d(TAG, "deleteAllBeeperCardList");
        BeeperCardDao beeperCardDao = this.beeperCardDao;
        if (beeperCardDao != null) {
            beeperCardDao.deleteAll();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public CallPoint queryCallPoint(String rfid) {
        Pdlog.m3273d(TAG, "queryCallPoint: " + rfid);
        if (rfid != null) {
            BeeperCard queryBeeperCard = queryBeeperCard(rfid);
            if (queryBeeperCard == null) {
            } else {
                String callPointType = queryBeeperCard.getCallPointType();
                Intrinsics.checkExpressionValueIsNotNull(callPointType, "callPointType");
                String callPointName = queryBeeperCard.getCallPointName();
                Intrinsics.checkExpressionValueIsNotNull(callPointName, "callPointName");
                return new CallPoint(callPointType, callPointName);
            }
        }
        return null;
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public ArrayList<Beeper> queryBeeperList() {
        QueryBuilder<Beeper> queryBuilder;
        Query<Beeper> build;
        Pdlog.m3273d(TAG, "queryBeeperList");
        BeeperDao beeperDao = this.beeperDao;
        return (ArrayList) ((beeperDao == null || (queryBuilder = beeperDao.queryBuilder()) == null || (build = queryBuilder.build()) == null) ? null : build.list());
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void deleteAllBeeperList() {
        Pdlog.m3273d(TAG, "deleteAllBeeperList");
        BeeperDao beeperDao = this.beeperDao;
        if (beeperDao != null) {
            beeperDao.deleteAll();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void saveBeeper(Beeper beeper) {
        BeeperDao beeperDao;
        Pdlog.m3273d(TAG, "saveBeeper " + beeper);
        if (beeper == null || (beeperDao = this.beeperDao) == null) {
            return;
        }
        beeperDao.insertOrReplace(beeper);
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void saveBeeperList(ArrayList<Beeper> beeperList) {
        BeeperDao beeperDao;
        Pdlog.m3273d(TAG, "saveBeeperList " + beeperList);
        if (beeperList == null || (beeperDao = this.beeperDao) == null) {
            return;
        }
        beeperDao.insertOrReplaceInTx(beeperList);
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void deleteBeeper(String beeperMac) {
        BeeperDao beeperDao;
        Pdlog.m3273d(TAG, "deleteBeeper " + beeperMac);
        Beeper queryBeeper = queryBeeper(beeperMac);
        if (queryBeeper == null || (beeperDao = this.beeperDao) == null) {
            return;
        }
        beeperDao.delete(queryBeeper);
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public Beeper queryBeeper(String beeperMac) {
        BeeperDao beeperDao;
        QueryBuilder<Beeper> queryBuilder;
        QueryBuilder<Beeper> where;
        Query<Beeper> build;
        Pdlog.m3273d(TAG, "queryBeeper " + beeperMac);
        if (beeperMac == null || (beeperDao = this.beeperDao) == null || (queryBuilder = beeperDao.queryBuilder()) == null || (where = queryBuilder.where(BeeperDao.Properties.Mac.m4147eq(beeperMac), new WhereCondition[0])) == null || (build = where.build()) == null) {
            return null;
        }
        return build.unique();
    }

    @Override // com.pudutech.bumblebee.robot_ui.database.interf.IDatabaseInterface
    public void release() {
        Pdlog.m3273d(TAG, "release");
        GreenDaoDatabaseHelper.INSTANCE.getInstance().release();
    }
}
