package com.pudutech.mpmodule.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.mpmodule.bean.PreviousPlayBean;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes6.dex */
public class PreviousPlayBeanDao extends AbstractDao<PreviousPlayBean, Long> {
    public static final String TABLENAME = "table_previous_play";

    /* loaded from: classes6.dex */
    public static class Properties {

        /* renamed from: Id */
        public static final Property f6791Id = new Property(0, Long.class, "id", true, TransferTable.COLUMN_ID);
        public static final Property ModeId = new Property(1, String.class, "modeId", false, "MODE_ID");
        public static final Property PlayedId = new Property(2, String.class, "playedId", false, "PLAYED_ID");
        public static final Property SeekTime = new Property(3, Long.TYPE, "seekTime", false, "SEEK_TIME");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public PreviousPlayBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public PreviousPlayBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"table_previous_play\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"MODE_ID\" TEXT NOT NULL UNIQUE ,\"PLAYED_ID\" TEXT,\"SEEK_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"table_previous_play\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, PreviousPlayBean previousPlayBean) {
        databaseStatement.clearBindings();
        Long id = previousPlayBean.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindString(2, previousPlayBean.getModeId());
        String playedId = previousPlayBean.getPlayedId();
        if (playedId != null) {
            databaseStatement.bindString(3, playedId);
        }
        databaseStatement.bindLong(4, previousPlayBean.getSeekTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, PreviousPlayBean previousPlayBean) {
        sQLiteStatement.clearBindings();
        Long id = previousPlayBean.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindString(2, previousPlayBean.getModeId());
        String playedId = previousPlayBean.getPlayedId();
        if (playedId != null) {
            sQLiteStatement.bindString(3, playedId);
        }
        sQLiteStatement.bindLong(4, previousPlayBean.getSeekTime());
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
    public PreviousPlayBean readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 2;
        return new PreviousPlayBean(valueOf, cursor.getString(i + 1), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getLong(i + 3));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, PreviousPlayBean previousPlayBean, int i) {
        int i2 = i + 0;
        previousPlayBean.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        previousPlayBean.setModeId(cursor.getString(i + 1));
        int i3 = i + 2;
        previousPlayBean.setPlayedId(cursor.isNull(i3) ? null : cursor.getString(i3));
        previousPlayBean.setSeekTime(cursor.getLong(i + 3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(PreviousPlayBean previousPlayBean, long j) {
        previousPlayBean.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(PreviousPlayBean previousPlayBean) {
        if (previousPlayBean != null) {
            return previousPlayBean.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(PreviousPlayBean previousPlayBean) {
        return previousPlayBean.getId() != null;
    }
}
