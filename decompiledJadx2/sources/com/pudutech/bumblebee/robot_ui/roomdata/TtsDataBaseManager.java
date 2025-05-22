package com.pudutech.bumblebee.robot_ui.roomdata;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.p003db.SupportSQLiteDatabase;
import com.pudutech.bumblebee.business.base.BaseApplication;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsDataBaseManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\u0007\b\u0016¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsDataBaseManager;", "", "()V", "DATA_BASE_TTS", "", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "MIGRATION_2_3", "getMIGRATION_2_3", "mCallHistoryDao", "Lcom/pudutech/bumblebee/robot_ui/roomdata/CallHistoryDao;", "getMCallHistoryDao", "()Lcom/pudutech/bumblebee/robot_ui/roomdata/CallHistoryDao;", "mPalletTtsSchemeDao", "Lcom/pudutech/bumblebee/robot_ui/roomdata/PalletTtsSchemeDao;", "getMPalletTtsSchemeDao", "()Lcom/pudutech/bumblebee/robot_ui/roomdata/PalletTtsSchemeDao;", "mTtsVoiceDao", "Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsVoiceDao;", "getMTtsVoiceDao", "()Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsVoiceDao;", "mTtsVoiceDataBase", "Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsVoiceDataBase;", "Companion", "SingletonHolder", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsDataBaseManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TtsDataBaseManager instance = SingletonHolder.INSTANCE.getHolder();
    private final String DATA_BASE_TTS = "bumblebee_tts.db";
    private final Migration MIGRATION_1_2;
    private final Migration MIGRATION_2_3;
    private TtsVoiceDataBase mTtsVoiceDataBase;

    public final TtsVoiceDao getMTtsVoiceDao() {
        return this.mTtsVoiceDataBase.getTtsVoiceDao();
    }

    public final PalletTtsSchemeDao getMPalletTtsSchemeDao() {
        return this.mTtsVoiceDataBase.getPalletTtsScheme();
    }

    public final CallHistoryDao getMCallHistoryDao() {
        return this.mTtsVoiceDataBase.getCallHistoryDao();
    }

    public TtsDataBaseManager() {
        final int i = 1;
        final int i2 = 2;
        this.MIGRATION_1_2 = new Migration(i, i2) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsDataBaseManager$MIGRATION_1_2$1
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                Intrinsics.checkParameterIsNotNull(database, "database");
                database.execSQL("CREATE TABLE IF NOT EXISTS `PalletTtsScheme` (`mid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `locale` TEXT, `taskName` TEXT, `ttsPalletArrived` TEXT, `isTtsPalletArrivedOn` INTEGER NOT NULL, `ttsPalletMoving` TEXT, `isTtsPalletMovingOn` INTEGER NOT NULL, `interval` INTEGER NOT NULL)");
            }
        };
        final int i3 = 3;
        this.MIGRATION_2_3 = new Migration(i2, i3) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsDataBaseManager$MIGRATION_2_3$1
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                Intrinsics.checkParameterIsNotNull(database, "database");
                database.execSQL("CREATE TABLE IF NOT EXISTS `call_history_data` (`mid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  `completeTime` INTEGER NOT NULL DEFAULT 0,`destination` TEXT)");
            }
        };
        RoomDatabase build = Room.databaseBuilder(BaseApplication.INSTANCE.getInstance(), TtsVoiceDataBase.class, this.DATA_BASE_TTS).addMigrations(this.MIGRATION_1_2, this.MIGRATION_2_3).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Room.databaseBuilder(Bas…es()\n            .build()");
        this.mTtsVoiceDataBase = (TtsVoiceDataBase) build;
    }

    public final Migration getMIGRATION_1_2() {
        return this.MIGRATION_1_2;
    }

    public final Migration getMIGRATION_2_3() {
        return this.MIGRATION_2_3;
    }

    /* compiled from: TtsDataBaseManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsDataBaseManager$SingletonHolder;", "", "()V", "holder", "Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsDataBaseManager;", "getHolder", "()Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsDataBaseManager;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    private static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = new SingletonHolder();
        private static final TtsDataBaseManager holder = new TtsDataBaseManager();

        private SingletonHolder() {
        }

        public final TtsDataBaseManager getHolder() {
            return holder;
        }
    }

    /* compiled from: TtsDataBaseManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsDataBaseManager$Companion;", "", "()V", "instance", "Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsDataBaseManager;", "getInstance", "()Lcom/pudutech/bumblebee/robot_ui/roomdata/TtsDataBaseManager;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TtsDataBaseManager getInstance() {
            return TtsDataBaseManager.instance;
        }
    }
}
