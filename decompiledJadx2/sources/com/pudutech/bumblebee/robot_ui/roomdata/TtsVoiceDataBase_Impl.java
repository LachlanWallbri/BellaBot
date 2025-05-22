package com.pudutech.bumblebee.robot_ui.roomdata;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.p003db.SupportSQLiteDatabase;
import androidx.sqlite.p003db.SupportSQLiteOpenHelper;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes3.dex */
public final class TtsVoiceDataBase_Impl extends TtsVoiceDataBase {
    private volatile CallHistoryDao _callHistoryDao;
    private volatile PalletTtsSchemeDao _palletTtsSchemeDao;
    private volatile TtsVoiceDao _ttsVoiceDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(3) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDataBase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `ttsvoice_data` (`mid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `md5` TEXT, `name` TEXT, `fliePath` TEXT, `currentTime` INTEGER NOT NULL, `isSelect` INTEGER NOT NULL, `locale` TEXT, `ttsType` TEXT, `isOldData` INTEGER NOT NULL, `extraInfo` TEXT, `expand` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `PalletTtsScheme` (`mid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `locale` TEXT, `taskName` TEXT, `ttsPalletArrived` TEXT, `isTtsPalletArrivedOn` INTEGER NOT NULL, `ttsPalletMoving` TEXT, `isTtsPalletMovingOn` INTEGER NOT NULL, `interval` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `call_history_data` (`mid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `completeTime` INTEGER NOT NULL, `destination` TEXT)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7c64159b335b751a319e8ebd6e781bad')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `ttsvoice_data`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `PalletTtsScheme`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `call_history_data`");
                if (TtsVoiceDataBase_Impl.this.mCallbacks != null) {
                    int size = TtsVoiceDataBase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) TtsVoiceDataBase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (TtsVoiceDataBase_Impl.this.mCallbacks != null) {
                    int size = TtsVoiceDataBase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) TtsVoiceDataBase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                TtsVoiceDataBase_Impl.this.mDatabase = supportSQLiteDatabase;
                TtsVoiceDataBase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (TtsVoiceDataBase_Impl.this.mCallbacks != null) {
                    int size = TtsVoiceDataBase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) TtsVoiceDataBase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(11);
                hashMap.put("mid", new TableInfo.Column("mid", "INTEGER", true, 1, null, 1));
                hashMap.put("md5", new TableInfo.Column("md5", "TEXT", false, 0, null, 1));
                hashMap.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap.put("fliePath", new TableInfo.Column("fliePath", "TEXT", false, 0, null, 1));
                hashMap.put("currentTime", new TableInfo.Column("currentTime", "INTEGER", true, 0, null, 1));
                hashMap.put("isSelect", new TableInfo.Column("isSelect", "INTEGER", true, 0, null, 1));
                hashMap.put("locale", new TableInfo.Column("locale", "TEXT", false, 0, null, 1));
                hashMap.put("ttsType", new TableInfo.Column("ttsType", "TEXT", false, 0, null, 1));
                hashMap.put("isOldData", new TableInfo.Column("isOldData", "INTEGER", true, 0, null, 1));
                hashMap.put("extraInfo", new TableInfo.Column("extraInfo", "TEXT", false, 0, null, 1));
                hashMap.put("expand", new TableInfo.Column("expand", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("ttsvoice_data", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "ttsvoice_data");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "ttsvoice_data(com.pudutech.bumblebee.robot_ui.bean.TtsVoiceData).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(8);
                hashMap2.put("mid", new TableInfo.Column("mid", "INTEGER", true, 1, null, 1));
                hashMap2.put("locale", new TableInfo.Column("locale", "TEXT", false, 0, null, 1));
                hashMap2.put("taskName", new TableInfo.Column("taskName", "TEXT", false, 0, null, 1));
                hashMap2.put("ttsPalletArrived", new TableInfo.Column("ttsPalletArrived", "TEXT", false, 0, null, 1));
                hashMap2.put("isTtsPalletArrivedOn", new TableInfo.Column("isTtsPalletArrivedOn", "INTEGER", true, 0, null, 1));
                hashMap2.put("ttsPalletMoving", new TableInfo.Column("ttsPalletMoving", "TEXT", false, 0, null, 1));
                hashMap2.put("isTtsPalletMovingOn", new TableInfo.Column("isTtsPalletMovingOn", "INTEGER", true, 0, null, 1));
                hashMap2.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("PalletTtsScheme", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "PalletTtsScheme");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "PalletTtsScheme(com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(3);
                hashMap3.put("mid", new TableInfo.Column("mid", "INTEGER", true, 1, null, 1));
                hashMap3.put("completeTime", new TableInfo.Column("completeTime", "INTEGER", true, 0, null, 1));
                hashMap3.put("destination", new TableInfo.Column("destination", "TEXT", false, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("call_history_data", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "call_history_data");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "call_history_data(com.pudutech.bumblebee.robot_ui.bean.CallHistoryData).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "7c64159b335b751a319e8ebd6e781bad", "48cdb2081192ec38031e2bda1a6a0338")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "ttsvoice_data", "PalletTtsScheme", "call_history_data");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `ttsvoice_data`");
            writableDatabase.execSQL("DELETE FROM `PalletTtsScheme`");
            writableDatabase.execSQL("DELETE FROM `call_history_data`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDataBase
    public TtsVoiceDao getTtsVoiceDao() {
        TtsVoiceDao ttsVoiceDao;
        if (this._ttsVoiceDao != null) {
            return this._ttsVoiceDao;
        }
        synchronized (this) {
            if (this._ttsVoiceDao == null) {
                this._ttsVoiceDao = new TtsVoiceDao_Impl(this);
            }
            ttsVoiceDao = this._ttsVoiceDao;
        }
        return ttsVoiceDao;
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDataBase
    public PalletTtsSchemeDao getPalletTtsScheme() {
        PalletTtsSchemeDao palletTtsSchemeDao;
        if (this._palletTtsSchemeDao != null) {
            return this._palletTtsSchemeDao;
        }
        synchronized (this) {
            if (this._palletTtsSchemeDao == null) {
                this._palletTtsSchemeDao = new PalletTtsSchemeDao_Impl(this);
            }
            palletTtsSchemeDao = this._palletTtsSchemeDao;
        }
        return palletTtsSchemeDao;
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.TtsVoiceDataBase
    public CallHistoryDao getCallHistoryDao() {
        CallHistoryDao callHistoryDao;
        if (this._callHistoryDao != null) {
            return this._callHistoryDao;
        }
        synchronized (this) {
            if (this._callHistoryDao == null) {
                this._callHistoryDao = new CallHistoryDao_Impl(this);
            }
            callHistoryDao = this._callHistoryDao;
        }
        return callHistoryDao;
    }
}
