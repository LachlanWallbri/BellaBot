package com.pudutech.bumblebee.robot_ui.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes2.dex */
public class BeeperDao extends AbstractDao<Beeper, Long> {
    public static final String TABLENAME = "table_beeper";

    /* loaded from: classes2.dex */
    public static class Properties {

        /* renamed from: Id */
        public static final Property f4842Id = new Property(0, Long.class, "id", true, TransferTable.COLUMN_ID);
        public static final Property Mac = new Property(1, String.class, "mac", false, TmpConstant.DATA_KEY_DEVICENAME);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BeeperDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BeeperDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"table_beeper\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"MAC\" TEXT NOT NULL UNIQUE );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"table_beeper\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, Beeper beeper) {
        databaseStatement.clearBindings();
        Long id = beeper.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindString(2, beeper.getMac());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, Beeper beeper) {
        sQLiteStatement.clearBindings();
        Long id = beeper.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindString(2, beeper.getMac());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Beeper readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        return new Beeper(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getString(i + 1));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, Beeper beeper, int i) {
        int i2 = i + 0;
        beeper.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        beeper.setMac(cursor.getString(i + 1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(Beeper beeper, long j) {
        beeper.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(Beeper beeper) {
        if (beeper != null) {
            return beeper.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(Beeper beeper) {
        return beeper.getId() != null;
    }
}
