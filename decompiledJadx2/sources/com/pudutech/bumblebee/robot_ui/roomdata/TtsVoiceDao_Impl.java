package com.pudutech.bumblebee.robot_ui.roomdata;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p003db.SupportSQLiteStatement;
import com.pudutech.bumblebee.robot_ui.bean.TtsVoiceData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes3.dex */
public final class TtsVoiceDao_Impl implements TtsVoiceDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<TtsVoiceData> __deletionAdapterOfTtsVoiceData;
    private final EntityInsertionAdapter<TtsVoiceData> __insertionAdapterOfTtsVoiceData;
    private final SharedSQLiteStatement __preparedStmtOfDeleteTtsByMd5;
    private final EntityDeletionOrUpdateAdapter<TtsVoiceData> __updateAdapterOfTtsVoiceData;

    public TtsVoiceDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfTtsVoiceData = new EntityInsertionAdapter<TtsVoiceData>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `ttsvoice_data` (`mid`,`md5`,`name`,`fliePath`,`currentTime`,`isSelect`,`locale`,`ttsType`,`isOldData`,`extraInfo`,`expand`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TtsVoiceData ttsVoiceData) {
                supportSQLiteStatement.bindLong(1, ttsVoiceData.getMid());
                if (ttsVoiceData.getMd5() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, ttsVoiceData.getMd5());
                }
                if (ttsVoiceData.getName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, ttsVoiceData.getName());
                }
                if (ttsVoiceData.getFliePath() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, ttsVoiceData.getFliePath());
                }
                supportSQLiteStatement.bindLong(5, ttsVoiceData.getCurrentTime());
                supportSQLiteStatement.bindLong(6, ttsVoiceData.getIsSelect() ? 1L : 0L);
                if (ttsVoiceData.getLocale() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, ttsVoiceData.getLocale());
                }
                if (ttsVoiceData.getTtsType() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, ttsVoiceData.getTtsType());
                }
                supportSQLiteStatement.bindLong(9, ttsVoiceData.getIsOldData() ? 1L : 0L);
                if (ttsVoiceData.getExtraInfo() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, ttsVoiceData.getExtraInfo());
                }
                if (ttsVoiceData.getExpand() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, ttsVoiceData.getExpand());
                }
            }
        };
        this.__deletionAdapterOfTtsVoiceData = new EntityDeletionOrUpdateAdapter<TtsVoiceData>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `ttsvoice_data` WHERE `mid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TtsVoiceData ttsVoiceData) {
                supportSQLiteStatement.bindLong(1, ttsVoiceData.getMid());
            }
        };
        this.__updateAdapterOfTtsVoiceData = new EntityDeletionOrUpdateAdapter<TtsVoiceData>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `ttsvoice_data` SET `mid` = ?,`md5` = ?,`name` = ?,`fliePath` = ?,`currentTime` = ?,`isSelect` = ?,`locale` = ?,`ttsType` = ?,`isOldData` = ?,`extraInfo` = ?,`expand` = ? WHERE `mid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TtsVoiceData ttsVoiceData) {
                supportSQLiteStatement.bindLong(1, ttsVoiceData.getMid());
                if (ttsVoiceData.getMd5() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, ttsVoiceData.getMd5());
                }
                if (ttsVoiceData.getName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, ttsVoiceData.getName());
                }
                if (ttsVoiceData.getFliePath() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, ttsVoiceData.getFliePath());
                }
                supportSQLiteStatement.bindLong(5, ttsVoiceData.getCurrentTime());
                supportSQLiteStatement.bindLong(6, ttsVoiceData.getIsSelect() ? 1L : 0L);
                if (ttsVoiceData.getLocale() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, ttsVoiceData.getLocale());
                }
                if (ttsVoiceData.getTtsType() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, ttsVoiceData.getTtsType());
                }
                supportSQLiteStatement.bindLong(9, ttsVoiceData.getIsOldData() ? 1L : 0L);
                if (ttsVoiceData.getExtraInfo() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, ttsVoiceData.getExtraInfo());
                }
                if (ttsVoiceData.getExpand() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, ttsVoiceData.getExpand());
                }
                supportSQLiteStatement.bindLong(12, ttsVoiceData.getMid());
            }
        };
        this.__preparedStmtOfDeleteTtsByMd5 = new SharedSQLiteStatement(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM ttsvoice_data WHERE md5 = ?";
            }
        };
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public void addTtsAll(List<TtsVoiceData> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTtsVoiceData.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public long addTts(TtsVoiceData ttsVoiceData) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfTtsVoiceData.insertAndReturnId(ttsVoiceData);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public void deleteTts(TtsVoiceData ttsVoiceData) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTtsVoiceData.handle(ttsVoiceData);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public void updataTts(TtsVoiceData ttsVoiceData) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTtsVoiceData.handle(ttsVoiceData);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public void updataTts(List<TtsVoiceData> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTtsVoiceData.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public void deleteTtsByMd5(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteTtsByMd5.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteTtsByMd5.release(acquire);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public List<TtsVoiceData> getTtsAllList() {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ttsvoice_data", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "fliePath");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "currentTime");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSelect");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "locale");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "ttsType");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isOldData");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "extraInfo");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "expand");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TtsVoiceData ttsVoiceData = new TtsVoiceData();
                roomSQLiteQuery = acquire;
                try {
                    ttsVoiceData.setMid(query.getLong(columnIndexOrThrow));
                    ttsVoiceData.setMd5(query.getString(columnIndexOrThrow2));
                    ttsVoiceData.setName(query.getString(columnIndexOrThrow3));
                    ttsVoiceData.setFliePath(query.getString(columnIndexOrThrow4));
                    ttsVoiceData.setCurrentTime(query.getLong(columnIndexOrThrow5));
                    boolean z = true;
                    ttsVoiceData.setSelect(query.getInt(columnIndexOrThrow6) != 0);
                    ttsVoiceData.setLocale(query.getString(columnIndexOrThrow7));
                    ttsVoiceData.setTtsType(query.getString(columnIndexOrThrow8));
                    if (query.getInt(columnIndexOrThrow9) == 0) {
                        z = false;
                    }
                    ttsVoiceData.setOldData(z);
                    ttsVoiceData.setExtraInfo(query.getString(columnIndexOrThrow10));
                    ttsVoiceData.setExpand(query.getString(columnIndexOrThrow11));
                    arrayList.add(ttsVoiceData);
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

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public LiveData<List<TtsVoiceData>> getTtsAllLiveData() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ttsvoice_data", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"ttsvoice_data"}, false, new Callable<List<TtsVoiceData>>() { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao_Impl.5
            @Override // java.util.concurrent.Callable
            public List<TtsVoiceData> call() throws Exception {
                Cursor query = DBUtil.query(TtsVoiceDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "md5");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "fliePath");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "currentTime");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSelect");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "locale");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "ttsType");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isOldData");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "extraInfo");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "expand");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        TtsVoiceData ttsVoiceData = new TtsVoiceData();
                        int i = columnIndexOrThrow2;
                        ttsVoiceData.setMid(query.getLong(columnIndexOrThrow));
                        ttsVoiceData.setMd5(query.getString(i));
                        ttsVoiceData.setName(query.getString(columnIndexOrThrow3));
                        ttsVoiceData.setFliePath(query.getString(columnIndexOrThrow4));
                        int i2 = columnIndexOrThrow;
                        ttsVoiceData.setCurrentTime(query.getLong(columnIndexOrThrow5));
                        boolean z = true;
                        ttsVoiceData.setSelect(query.getInt(columnIndexOrThrow6) != 0);
                        ttsVoiceData.setLocale(query.getString(columnIndexOrThrow7));
                        ttsVoiceData.setTtsType(query.getString(columnIndexOrThrow8));
                        if (query.getInt(columnIndexOrThrow9) == 0) {
                            z = false;
                        }
                        ttsVoiceData.setOldData(z);
                        ttsVoiceData.setExtraInfo(query.getString(columnIndexOrThrow10));
                        ttsVoiceData.setExpand(query.getString(columnIndexOrThrow11));
                        arrayList.add(ttsVoiceData);
                        columnIndexOrThrow = i2;
                        columnIndexOrThrow2 = i;
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public List<TtsVoiceData> getTtsMd5List(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ttsvoice_data WHERE md5 = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "fliePath");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "currentTime");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSelect");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "locale");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "ttsType");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isOldData");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "extraInfo");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "expand");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TtsVoiceData ttsVoiceData = new TtsVoiceData();
                ArrayList arrayList2 = arrayList;
                ttsVoiceData.setMid(query.getLong(columnIndexOrThrow));
                ttsVoiceData.setMd5(query.getString(columnIndexOrThrow2));
                ttsVoiceData.setName(query.getString(columnIndexOrThrow3));
                ttsVoiceData.setFliePath(query.getString(columnIndexOrThrow4));
                ttsVoiceData.setCurrentTime(query.getLong(columnIndexOrThrow5));
                ttsVoiceData.setSelect(query.getInt(columnIndexOrThrow6) != 0);
                ttsVoiceData.setLocale(query.getString(columnIndexOrThrow7));
                ttsVoiceData.setTtsType(query.getString(columnIndexOrThrow8));
                ttsVoiceData.setOldData(query.getInt(columnIndexOrThrow9) != 0);
                ttsVoiceData.setExtraInfo(query.getString(columnIndexOrThrow10));
                ttsVoiceData.setExpand(query.getString(columnIndexOrThrow11));
                arrayList2.add(ttsVoiceData);
                arrayList = arrayList2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public TtsVoiceData getTtsMd5Data(String str, String str2) {
        TtsVoiceData ttsVoiceData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ttsvoice_data WHERE md5 = ? AND ttsType = ?", 2);
        boolean z = true;
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "fliePath");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "currentTime");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSelect");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "locale");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "ttsType");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isOldData");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "extraInfo");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "expand");
            if (query.moveToFirst()) {
                ttsVoiceData = new TtsVoiceData();
                ttsVoiceData.setMid(query.getLong(columnIndexOrThrow));
                ttsVoiceData.setMd5(query.getString(columnIndexOrThrow2));
                ttsVoiceData.setName(query.getString(columnIndexOrThrow3));
                ttsVoiceData.setFliePath(query.getString(columnIndexOrThrow4));
                ttsVoiceData.setCurrentTime(query.getLong(columnIndexOrThrow5));
                ttsVoiceData.setSelect(query.getInt(columnIndexOrThrow6) != 0);
                ttsVoiceData.setLocale(query.getString(columnIndexOrThrow7));
                ttsVoiceData.setTtsType(query.getString(columnIndexOrThrow8));
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                ttsVoiceData.setOldData(z);
                ttsVoiceData.setExtraInfo(query.getString(columnIndexOrThrow10));
                ttsVoiceData.setExpand(query.getString(columnIndexOrThrow11));
            } else {
                ttsVoiceData = null;
            }
            return ttsVoiceData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public List<TtsVoiceData> getTtsList(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ttsvoice_data WHERE locale = ? AND ttsType = ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "md5");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "fliePath");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "currentTime");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSelect");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "locale");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "ttsType");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isOldData");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "extraInfo");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "expand");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TtsVoiceData ttsVoiceData = new TtsVoiceData();
                ArrayList arrayList2 = arrayList;
                roomSQLiteQuery = acquire;
                try {
                    ttsVoiceData.setMid(query.getLong(columnIndexOrThrow));
                    ttsVoiceData.setMd5(query.getString(columnIndexOrThrow2));
                    ttsVoiceData.setName(query.getString(columnIndexOrThrow3));
                    ttsVoiceData.setFliePath(query.getString(columnIndexOrThrow4));
                    ttsVoiceData.setCurrentTime(query.getLong(columnIndexOrThrow5));
                    ttsVoiceData.setSelect(query.getInt(columnIndexOrThrow6) != 0);
                    ttsVoiceData.setLocale(query.getString(columnIndexOrThrow7));
                    ttsVoiceData.setTtsType(query.getString(columnIndexOrThrow8));
                    ttsVoiceData.setOldData(query.getInt(columnIndexOrThrow9) != 0);
                    ttsVoiceData.setExtraInfo(query.getString(columnIndexOrThrow10));
                    ttsVoiceData.setExpand(query.getString(columnIndexOrThrow11));
                    arrayList = arrayList2;
                    arrayList.add(ttsVoiceData);
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

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao
    public LiveData<List<TtsVoiceData>> getTtsLiveData(String str, String str2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM ttsvoice_data WHERE locale = ? AND ttsType = ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"ttsvoice_data"}, false, new Callable<List<TtsVoiceData>>() { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<TtsVoiceData> call() throws Exception {
                Cursor query = DBUtil.query(TtsVoiceDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "md5");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "fliePath");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "currentTime");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isSelect");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "locale");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "ttsType");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isOldData");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "extraInfo");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "expand");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        TtsVoiceData ttsVoiceData = new TtsVoiceData();
                        int i = columnIndexOrThrow2;
                        ttsVoiceData.setMid(query.getLong(columnIndexOrThrow));
                        ttsVoiceData.setMd5(query.getString(i));
                        ttsVoiceData.setName(query.getString(columnIndexOrThrow3));
                        ttsVoiceData.setFliePath(query.getString(columnIndexOrThrow4));
                        int i2 = columnIndexOrThrow;
                        ttsVoiceData.setCurrentTime(query.getLong(columnIndexOrThrow5));
                        boolean z = true;
                        ttsVoiceData.setSelect(query.getInt(columnIndexOrThrow6) != 0);
                        ttsVoiceData.setLocale(query.getString(columnIndexOrThrow7));
                        ttsVoiceData.setTtsType(query.getString(columnIndexOrThrow8));
                        if (query.getInt(columnIndexOrThrow9) == 0) {
                            z = false;
                        }
                        ttsVoiceData.setOldData(z);
                        ttsVoiceData.setExtraInfo(query.getString(columnIndexOrThrow10));
                        ttsVoiceData.setExpand(query.getString(columnIndexOrThrow11));
                        arrayList.add(ttsVoiceData);
                        columnIndexOrThrow = i2;
                        columnIndexOrThrow2 = i;
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }
}
