package com.pudutech.mpmodule.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.PlayMode;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlayModeDao extends AbstractDao<PlayMode, Long> {
    public static final String TABLENAME = "table_play_mode";
    private final PlayMode.CEnumStringConverter modeConverter;
    private final PlayMode.CListStringConverter playListIdsConverter;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class Properties {

        /* renamed from: Id */
        public static final Property f6789Id = new Property(0, Long.class, "id", true, TransferTable.COLUMN_ID);
        public static final Property ModeId = new Property(1, String.class, "modeId", false, "MODE_ID");
        public static final Property Mode = new Property(2, String.class, "mode", false, "MODE");
        public static final Property PlayListIds = new Property(3, String.class, "playListIds", false, "PLAY_LIST_IDS");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public PlayModeDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.modeConverter = new PlayMode.CEnumStringConverter();
        this.playListIdsConverter = new PlayMode.CListStringConverter();
    }

    public PlayModeDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.modeConverter = new PlayMode.CEnumStringConverter();
        this.playListIdsConverter = new PlayMode.CListStringConverter();
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"table_play_mode\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"MODE_ID\" TEXT NOT NULL UNIQUE ,\"MODE\" TEXT NOT NULL ,\"PLAY_LIST_IDS\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"table_play_mode\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, PlayMode playMode) {
        databaseStatement.clearBindings();
        Long id = playMode.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindString(2, playMode.getModeId());
        databaseStatement.bindString(3, this.modeConverter.convertToDatabaseValue(playMode.getMode()));
        List<String> playListIds = playMode.getPlayListIds();
        if (playListIds != null) {
            databaseStatement.bindString(4, this.playListIdsConverter.convertToDatabaseValue(playListIds));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, PlayMode playMode) {
        sQLiteStatement.clearBindings();
        Long id = playMode.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindString(2, playMode.getModeId());
        sQLiteStatement.bindString(3, this.modeConverter.convertToDatabaseValue(playMode.getMode()));
        List<String> playListIds = playMode.getPlayListIds();
        if (playListIds != null) {
            sQLiteStatement.bindString(4, this.playListIdsConverter.convertToDatabaseValue(playListIds));
        }
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
    public PlayMode readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        String string = cursor.getString(i + 1);
        ModeEnum convertToEntityProperty = this.modeConverter.convertToEntityProperty(cursor.getString(i + 2));
        int i3 = i + 3;
        return new PlayMode(valueOf, string, convertToEntityProperty, cursor.isNull(i3) ? null : this.playListIdsConverter.convertToEntityProperty(cursor.getString(i3)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, PlayMode playMode, int i) {
        int i2 = i + 0;
        playMode.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        playMode.setModeId(cursor.getString(i + 1));
        playMode.setMode(this.modeConverter.convertToEntityProperty(cursor.getString(i + 2)));
        int i3 = i + 3;
        playMode.setPlayListIds(cursor.isNull(i3) ? null : this.playListIdsConverter.convertToEntityProperty(cursor.getString(i3)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(PlayMode playMode, long j) {
        playMode.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(PlayMode playMode) {
        if (playMode != null) {
            return playMode.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(PlayMode playMode) {
        return playMode.getId() != null;
    }
}
