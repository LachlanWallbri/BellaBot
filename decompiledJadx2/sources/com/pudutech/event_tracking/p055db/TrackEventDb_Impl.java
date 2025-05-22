package com.pudutech.event_tracking.p055db;

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
import org.apache.commons.logging.LogFactory;

/* loaded from: classes5.dex */
public final class TrackEventDb_Impl extends TrackEventDb {
    private volatile TrackEventDao _trackEventDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) { // from class: com.pudutech.event_tracking.db.TrackEventDb_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `TrackEvent` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `appInstallId` TEXT NOT NULL, `appPackage` TEXT NOT NULL, `appChannel` TEXT NOT NULL, `appId` INTEGER NOT NULL, `appName` TEXT NOT NULL, `appVersion` TEXT NOT NULL, `appLanguage` TEXT NOT NULL, `customEvent` TEXT NOT NULL, `deviceType` TEXT NOT NULL, `deviceId` TEXT NOT NULL, `deviceUniqueId` TEXT NOT NULL, `deviceIp` TEXT NOT NULL, `deviceModel` TEXT NOT NULL, `deviceTimezone` TEXT NOT NULL, `elementId` TEXT NOT NULL, `event` TEXT NOT NULL, `header` TEXT NOT NULL, `headerCustom` TEXT NOT NULL, `latitude` TEXT NOT NULL, `locationCity` TEXT NOT NULL, `locationProvince` TEXT NOT NULL, `longitude` TEXT NOT NULL, `networkType` TEXT NOT NULL, `osVersion` TEXT NOT NULL, `pageId` TEXT NOT NULL, `pagePreId` TEXT NOT NULL, `params` TEXT NOT NULL, `screenDensity` TEXT NOT NULL, `screenResolution` TEXT NOT NULL, `sessionId` TEXT NOT NULL, `time` INTEGER NOT NULL, `userId` TEXT NOT NULL, `abtestId` TEXT NOT NULL, `abtestGroup` TEXT NOT NULL, `userUniqueId` TEXT NOT NULL, `hardwareVersion` TEXT NOT NULL, `upload` INTEGER NOT NULL, `priority` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7aef27775eee4644b9ff57eb4a44ebbc')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `TrackEvent`");
                if (TrackEventDb_Impl.this.mCallbacks != null) {
                    int size = TrackEventDb_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) TrackEventDb_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (TrackEventDb_Impl.this.mCallbacks != null) {
                    int size = TrackEventDb_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) TrackEventDb_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                TrackEventDb_Impl.this.mDatabase = supportSQLiteDatabase;
                TrackEventDb_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (TrackEventDb_Impl.this.mCallbacks != null) {
                    int size = TrackEventDb_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) TrackEventDb_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(40);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 0, null, 1));
                hashMap.put("appInstallId", new TableInfo.Column("appInstallId", "TEXT", true, 0, null, 1));
                hashMap.put("appPackage", new TableInfo.Column("appPackage", "TEXT", true, 0, null, 1));
                hashMap.put("appChannel", new TableInfo.Column("appChannel", "TEXT", true, 0, null, 1));
                hashMap.put("appId", new TableInfo.Column("appId", "INTEGER", true, 0, null, 1));
                hashMap.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, 1));
                hashMap.put("appVersion", new TableInfo.Column("appVersion", "TEXT", true, 0, null, 1));
                hashMap.put("appLanguage", new TableInfo.Column("appLanguage", "TEXT", true, 0, null, 1));
                hashMap.put("customEvent", new TableInfo.Column("customEvent", "TEXT", true, 0, null, 1));
                hashMap.put("deviceType", new TableInfo.Column("deviceType", "TEXT", true, 0, null, 1));
                hashMap.put("deviceId", new TableInfo.Column("deviceId", "TEXT", true, 0, null, 1));
                hashMap.put("deviceUniqueId", new TableInfo.Column("deviceUniqueId", "TEXT", true, 0, null, 1));
                hashMap.put("deviceIp", new TableInfo.Column("deviceIp", "TEXT", true, 0, null, 1));
                hashMap.put("deviceModel", new TableInfo.Column("deviceModel", "TEXT", true, 0, null, 1));
                hashMap.put("deviceTimezone", new TableInfo.Column("deviceTimezone", "TEXT", true, 0, null, 1));
                hashMap.put("elementId", new TableInfo.Column("elementId", "TEXT", true, 0, null, 1));
                hashMap.put("event", new TableInfo.Column("event", "TEXT", true, 0, null, 1));
                hashMap.put("header", new TableInfo.Column("header", "TEXT", true, 0, null, 1));
                hashMap.put("headerCustom", new TableInfo.Column("headerCustom", "TEXT", true, 0, null, 1));
                hashMap.put("latitude", new TableInfo.Column("latitude", "TEXT", true, 0, null, 1));
                hashMap.put("locationCity", new TableInfo.Column("locationCity", "TEXT", true, 0, null, 1));
                hashMap.put("locationProvince", new TableInfo.Column("locationProvince", "TEXT", true, 0, null, 1));
                hashMap.put("longitude", new TableInfo.Column("longitude", "TEXT", true, 0, null, 1));
                hashMap.put("networkType", new TableInfo.Column("networkType", "TEXT", true, 0, null, 1));
                hashMap.put("osVersion", new TableInfo.Column("osVersion", "TEXT", true, 0, null, 1));
                hashMap.put("pageId", new TableInfo.Column("pageId", "TEXT", true, 0, null, 1));
                hashMap.put("pagePreId", new TableInfo.Column("pagePreId", "TEXT", true, 0, null, 1));
                hashMap.put("params", new TableInfo.Column("params", "TEXT", true, 0, null, 1));
                hashMap.put("screenDensity", new TableInfo.Column("screenDensity", "TEXT", true, 0, null, 1));
                hashMap.put("screenResolution", new TableInfo.Column("screenResolution", "TEXT", true, 0, null, 1));
                hashMap.put("sessionId", new TableInfo.Column("sessionId", "TEXT", true, 0, null, 1));
                hashMap.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, 1));
                hashMap.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, 1));
                hashMap.put("abtestId", new TableInfo.Column("abtestId", "TEXT", true, 0, null, 1));
                hashMap.put("abtestGroup", new TableInfo.Column("abtestGroup", "TEXT", true, 0, null, 1));
                hashMap.put("userUniqueId", new TableInfo.Column("userUniqueId", "TEXT", true, 0, null, 1));
                hashMap.put("hardwareVersion", new TableInfo.Column("hardwareVersion", "TEXT", true, 0, null, 1));
                hashMap.put("upload", new TableInfo.Column("upload", "INTEGER", true, 0, null, 1));
                hashMap.put(LogFactory.PRIORITY_KEY, new TableInfo.Column(LogFactory.PRIORITY_KEY, "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("TrackEvent", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "TrackEvent");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "TrackEvent(com.pudutech.event_tracking.bean.TrackEvent).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "7aef27775eee4644b9ff57eb4a44ebbc", "f1f37e92515e7da33473e9016561bbdb")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "TrackEvent");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `TrackEvent`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDb
    public TrackEventDao trackEventDao() {
        TrackEventDao trackEventDao;
        if (this._trackEventDao != null) {
            return this._trackEventDao;
        }
        synchronized (this) {
            if (this._trackEventDao == null) {
                this._trackEventDao = new TrackEventDao_Impl(this);
            }
            trackEventDao = this._trackEventDao;
        }
        return trackEventDao;
    }
}
