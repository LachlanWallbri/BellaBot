package com.pudutech.bumblebee.robot_ui.database.greendao;

import com.pudutech.bumblebee.business.base.BaseApplication;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GreenDaoDatabaseHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseHelper;", "", "()V", "daoSession", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/DaoSession;", "checkInit", "", "getBeeperCardDao", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/BeeperCardDao;", "getBeeperDao", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/BeeperDao;", "init", "release", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class GreenDaoDatabaseHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<GreenDaoDatabaseHelper>() { // from class: com.pudutech.bumblebee.robot_ui.database.greendao.GreenDaoDatabaseHelper$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GreenDaoDatabaseHelper invoke() {
            return new GreenDaoDatabaseHelper(null);
        }
    });
    private DaoSession daoSession;

    private GreenDaoDatabaseHelper() {
    }

    public /* synthetic */ GreenDaoDatabaseHelper(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: GreenDaoDatabaseHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseHelper$Companion;", "", "()V", "instance", "Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseHelper;", "getInstance", "()Lcom/pudutech/bumblebee/robot_ui/database/greendao/GreenDaoDatabaseHelper;", "instance$delegate", "Lkotlin/Lazy;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        public final GreenDaoDatabaseHelper getInstance() {
            Lazy lazy = GreenDaoDatabaseHelper.instance$delegate;
            Companion companion = GreenDaoDatabaseHelper.INSTANCE;
            return (GreenDaoDatabaseHelper) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void init() {
        this.daoSession = new DaoMaster(new CDevOpenHelper(BaseApplication.INSTANCE.getInstance(), "hola.db").getWritableDatabase()).newSession();
    }

    public final BeeperCardDao getBeeperCardDao() {
        checkInit();
        DaoSession daoSession = this.daoSession;
        if (daoSession != null) {
            return daoSession.getBeeperCardDao();
        }
        return null;
    }

    public final BeeperDao getBeeperDao() {
        checkInit();
        DaoSession daoSession = this.daoSession;
        if (daoSession != null) {
            return daoSession.getBeeperDao();
        }
        return null;
    }

    private final void checkInit() {
        if (this.daoSession == null) {
            init();
        }
    }

    public final void release() {
        DaoSession daoSession = this.daoSession;
        if (daoSession != null) {
            daoSession.clear();
            this.daoSession = (DaoSession) null;
        }
    }
}
