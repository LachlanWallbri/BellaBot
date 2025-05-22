package com.pudutech.bumblebee.robot_ui.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.bumblebee.robot_ui.bean.BeeperCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes2.dex */
public class BeeperCardDao extends AbstractDao<BeeperCard, Long> {
    public static final String TABLENAME = "table_beeper_card";

    /* loaded from: classes2.dex */
    public static class Properties {

        /* renamed from: Id */
        public static final Property f4841Id = new Property(0, Long.class, "id", true, TransferTable.COLUMN_ID);
        public static final Property Rfid = new Property(1, String.class, "rfid", false, "RFID");
        public static final Property CallPointName = new Property(2, String.class, "callPointName", false, "CALL_POINT_NAME");
        public static final Property CallPointType = new Property(3, String.class, "callPointType", false, "CALL_POINT_TYPE");
        public static final Property Timestamp = new Property(4, Long.TYPE, "timestamp", false, CognitoServiceConstants.CHLG_RESP_TIMESTAMP);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public BeeperCardDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BeeperCardDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"table_beeper_card\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"RFID\" TEXT NOT NULL UNIQUE ,\"CALL_POINT_NAME\" TEXT,\"CALL_POINT_TYPE\" TEXT,\"TIMESTAMP\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"table_beeper_card\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, BeeperCard beeperCard) {
        databaseStatement.clearBindings();
        Long id = beeperCard.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindString(2, beeperCard.getRfid());
        String callPointName = beeperCard.getCallPointName();
        if (callPointName != null) {
            databaseStatement.bindString(3, callPointName);
        }
        String callPointType = beeperCard.getCallPointType();
        if (callPointType != null) {
            databaseStatement.bindString(4, callPointType);
        }
        databaseStatement.bindLong(5, beeperCard.getTimestamp());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, BeeperCard beeperCard) {
        sQLiteStatement.clearBindings();
        Long id = beeperCard.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindString(2, beeperCard.getRfid());
        String callPointName = beeperCard.getCallPointName();
        if (callPointName != null) {
            sQLiteStatement.bindString(3, callPointName);
        }
        String callPointType = beeperCard.getCallPointType();
        if (callPointType != null) {
            sQLiteStatement.bindString(4, callPointType);
        }
        sQLiteStatement.bindLong(5, beeperCard.getTimestamp());
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
    public BeeperCard readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        String string = cursor.getString(i + 1);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new BeeperCard(valueOf, string, string2, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getLong(i + 4));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, BeeperCard beeperCard, int i) {
        int i2 = i + 0;
        beeperCard.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        beeperCard.setRfid(cursor.getString(i + 1));
        int i3 = i + 2;
        beeperCard.setCallPointName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        beeperCard.setCallPointType(cursor.isNull(i4) ? null : cursor.getString(i4));
        beeperCard.setTimestamp(cursor.getLong(i + 4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(BeeperCard beeperCard, long j) {
        beeperCard.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(BeeperCard beeperCard) {
        if (beeperCard != null) {
            return beeperCard.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(BeeperCard beeperCard) {
        return beeperCard.getId() != null;
    }
}
