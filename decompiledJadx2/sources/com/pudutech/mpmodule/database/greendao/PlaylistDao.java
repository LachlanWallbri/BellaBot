package com.pudutech.mpmodule.database.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.media.Media;
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
public class PlaylistDao extends AbstractDao<Playlist, Long> {
    public static final String TABLENAME = "table_playlist";
    private final Playlist.CEnumStringConverter defaultModeConverter;
    private final Playlist.CListStringConverter mediaListConverter;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class Properties {

        /* renamed from: Id */
        public static final Property f6790Id = new Property(0, Long.class, "id", true, TransferTable.COLUMN_ID);
        public static final Property ListId = new Property(1, String.class, "listId", false, "LIST_ID");
        public static final Property ListName = new Property(2, String.class, "listName", false, "LIST_NAME");
        public static final Property IsCustom = new Property(3, Boolean.TYPE, "isCustom", false, "IS_CUSTOM");
        public static final Property MediaList = new Property(4, String.class, "mediaList", false, "MEDIA_LIST");
        public static final Property DefaultMode = new Property(5, String.class, "defaultMode", false, "DEFAULT_MODE");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public PlaylistDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.mediaListConverter = new Playlist.CListStringConverter();
        this.defaultModeConverter = new Playlist.CEnumStringConverter();
    }

    public PlaylistDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.mediaListConverter = new Playlist.CListStringConverter();
        this.defaultModeConverter = new Playlist.CEnumStringConverter();
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"table_playlist\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"LIST_ID\" TEXT NOT NULL UNIQUE ,\"LIST_NAME\" TEXT NOT NULL ,\"IS_CUSTOM\" INTEGER NOT NULL ,\"MEDIA_LIST\" TEXT,\"DEFAULT_MODE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"table_playlist\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, Playlist playlist) {
        databaseStatement.clearBindings();
        Long id = playlist.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindString(2, playlist.getListId());
        databaseStatement.bindString(3, playlist.getListName());
        databaseStatement.bindLong(4, playlist.getIsCustom() ? 1L : 0L);
        List<Media> mediaList = playlist.getMediaList();
        if (mediaList != null) {
            databaseStatement.bindString(5, this.mediaListConverter.convertToDatabaseValue(mediaList));
        }
        ModeEnum defaultMode = playlist.getDefaultMode();
        if (defaultMode != null) {
            databaseStatement.bindString(6, this.defaultModeConverter.convertToDatabaseValue(defaultMode));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, Playlist playlist) {
        sQLiteStatement.clearBindings();
        Long id = playlist.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindString(2, playlist.getListId());
        sQLiteStatement.bindString(3, playlist.getListName());
        sQLiteStatement.bindLong(4, playlist.getIsCustom() ? 1L : 0L);
        List<Media> mediaList = playlist.getMediaList();
        if (mediaList != null) {
            sQLiteStatement.bindString(5, this.mediaListConverter.convertToDatabaseValue(mediaList));
        }
        ModeEnum defaultMode = playlist.getDefaultMode();
        if (defaultMode != null) {
            sQLiteStatement.bindString(6, this.defaultModeConverter.convertToDatabaseValue(defaultMode));
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
    public Playlist readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        String string = cursor.getString(i + 1);
        String string2 = cursor.getString(i + 2);
        boolean z = cursor.getShort(i + 3) != 0;
        int i3 = i + 4;
        int i4 = i + 5;
        return new Playlist(valueOf, string, string2, z, cursor.isNull(i3) ? null : this.mediaListConverter.convertToEntityProperty(cursor.getString(i3)), cursor.isNull(i4) ? null : this.defaultModeConverter.convertToEntityProperty(cursor.getString(i4)));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, Playlist playlist, int i) {
        int i2 = i + 0;
        playlist.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        playlist.setListId(cursor.getString(i + 1));
        playlist.setListName(cursor.getString(i + 2));
        playlist.setIsCustom(cursor.getShort(i + 3) != 0);
        int i3 = i + 4;
        playlist.setMediaList(cursor.isNull(i3) ? null : this.mediaListConverter.convertToEntityProperty(cursor.getString(i3)));
        int i4 = i + 5;
        playlist.setDefaultMode(cursor.isNull(i4) ? null : this.defaultModeConverter.convertToEntityProperty(cursor.getString(i4)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(Playlist playlist, long j) {
        playlist.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(Playlist playlist) {
        if (playlist != null) {
            return playlist.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(Playlist playlist) {
        return playlist.getId() != null;
    }
}
