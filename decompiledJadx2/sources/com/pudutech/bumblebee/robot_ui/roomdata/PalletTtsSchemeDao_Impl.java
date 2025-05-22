package com.pudutech.bumblebee.robot_ui.roomdata;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p003db.SupportSQLiteStatement;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class PalletTtsSchemeDao_Impl implements PalletTtsSchemeDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<PalletTtsScheme> __deletionAdapterOfPalletTtsScheme;
    private final EntityInsertionAdapter<PalletTtsScheme> __insertionAdapterOfPalletTtsScheme;
    private final EntityDeletionOrUpdateAdapter<PalletTtsScheme> __updateAdapterOfPalletTtsScheme;

    public PalletTtsSchemeDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPalletTtsScheme = new EntityInsertionAdapter<PalletTtsScheme>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.PalletTtsSchemeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `PalletTtsScheme` (`mid`,`locale`,`taskName`,`ttsPalletArrived`,`isTtsPalletArrivedOn`,`ttsPalletMoving`,`isTtsPalletMovingOn`,`interval`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PalletTtsScheme palletTtsScheme) {
                supportSQLiteStatement.bindLong(1, palletTtsScheme.getMid());
                if (palletTtsScheme.getLocale() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, palletTtsScheme.getLocale());
                }
                if (palletTtsScheme.getTaskName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, palletTtsScheme.getTaskName());
                }
                String ttsConfigDataToString = PalletTtsSchemeDao_Impl.this.__converters.ttsConfigDataToString(palletTtsScheme.getTtsPalletArrived());
                if (ttsConfigDataToString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, ttsConfigDataToString);
                }
                supportSQLiteStatement.bindLong(5, palletTtsScheme.getIsTtsPalletArrivedOn() ? 1L : 0L);
                String ttsConfigDataToString2 = PalletTtsSchemeDao_Impl.this.__converters.ttsConfigDataToString(palletTtsScheme.getTtsPalletMoving());
                if (ttsConfigDataToString2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, ttsConfigDataToString2);
                }
                supportSQLiteStatement.bindLong(7, palletTtsScheme.getIsTtsPalletMovingOn() ? 1L : 0L);
                supportSQLiteStatement.bindLong(8, palletTtsScheme.getInterval());
            }
        };
        this.__deletionAdapterOfPalletTtsScheme = new EntityDeletionOrUpdateAdapter<PalletTtsScheme>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.PalletTtsSchemeDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `PalletTtsScheme` WHERE `mid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PalletTtsScheme palletTtsScheme) {
                supportSQLiteStatement.bindLong(1, palletTtsScheme.getMid());
            }
        };
        this.__updateAdapterOfPalletTtsScheme = new EntityDeletionOrUpdateAdapter<PalletTtsScheme>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.PalletTtsSchemeDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `PalletTtsScheme` SET `mid` = ?,`locale` = ?,`taskName` = ?,`ttsPalletArrived` = ?,`isTtsPalletArrivedOn` = ?,`ttsPalletMoving` = ?,`isTtsPalletMovingOn` = ?,`interval` = ? WHERE `mid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PalletTtsScheme palletTtsScheme) {
                supportSQLiteStatement.bindLong(1, palletTtsScheme.getMid());
                if (palletTtsScheme.getLocale() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, palletTtsScheme.getLocale());
                }
                if (palletTtsScheme.getTaskName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, palletTtsScheme.getTaskName());
                }
                String ttsConfigDataToString = PalletTtsSchemeDao_Impl.this.__converters.ttsConfigDataToString(palletTtsScheme.getTtsPalletArrived());
                if (ttsConfigDataToString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, ttsConfigDataToString);
                }
                supportSQLiteStatement.bindLong(5, palletTtsScheme.getIsTtsPalletArrivedOn() ? 1L : 0L);
                String ttsConfigDataToString2 = PalletTtsSchemeDao_Impl.this.__converters.ttsConfigDataToString(palletTtsScheme.getTtsPalletMoving());
                if (ttsConfigDataToString2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, ttsConfigDataToString2);
                }
                supportSQLiteStatement.bindLong(7, palletTtsScheme.getIsTtsPalletMovingOn() ? 1L : 0L);
                supportSQLiteStatement.bindLong(8, palletTtsScheme.getInterval());
                supportSQLiteStatement.bindLong(9, palletTtsScheme.getMid());
            }
        };
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.PalletTtsSchemeDao
    public long insertPalletTtsScheme(PalletTtsScheme palletTtsScheme) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfPalletTtsScheme.insertAndReturnId(palletTtsScheme);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.PalletTtsSchemeDao
    public void deletePalletTtsScheme(PalletTtsScheme palletTtsScheme) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPalletTtsScheme.handle(palletTtsScheme);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.PalletTtsSchemeDao
    public void updatePalletTtsScheme(PalletTtsScheme palletTtsScheme) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPalletTtsScheme.handle(palletTtsScheme);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.PalletTtsSchemeDao
    public List<PalletTtsScheme> queryPalletTtsSchemeByLocale(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM PalletTtsScheme WHERE locale = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "locale");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "taskName");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "ttsPalletArrived");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isTtsPalletArrivedOn");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "ttsPalletMoving");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "isTtsPalletMovingOn");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PalletTtsScheme palletTtsScheme = new PalletTtsScheme();
                roomSQLiteQuery = acquire;
                try {
                    palletTtsScheme.setMid(query.getLong(columnIndexOrThrow));
                    palletTtsScheme.setLocale(query.getString(columnIndexOrThrow2));
                    palletTtsScheme.setTaskName(query.getString(columnIndexOrThrow3));
                    palletTtsScheme.setTtsPalletArrived(this.__converters.stringToTtsConfigData(query.getString(columnIndexOrThrow4)));
                    palletTtsScheme.setTtsPalletArrivedOn(query.getInt(columnIndexOrThrow5) != 0);
                    palletTtsScheme.setTtsPalletMoving(this.__converters.stringToTtsConfigData(query.getString(columnIndexOrThrow6)));
                    palletTtsScheme.setTtsPalletMovingOn(query.getInt(columnIndexOrThrow7) != 0);
                    palletTtsScheme.setInterval(query.getLong(columnIndexOrThrow8));
                    arrayList.add(palletTtsScheme);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }
}
