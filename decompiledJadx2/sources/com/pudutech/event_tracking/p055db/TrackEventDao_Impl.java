package com.pudutech.event_tracking.p055db;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p003db.SupportSQLiteStatement;
import com.pudutech.event_tracking.bean.TrackEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import org.apache.commons.logging.LogFactory;

/* loaded from: classes5.dex */
public final class TrackEventDao_Impl implements TrackEventDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<TrackEvent> __deletionAdapterOfTrackEvent;
    private final EntityInsertionAdapter<TrackEvent> __insertionAdapterOfTrackEvent;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllUpload;
    private final SharedSQLiteStatement __preparedStmtOfDeleteOldest;
    private final EntityDeletionOrUpdateAdapter<TrackEvent> __updateAdapterOfTrackEvent;

    public TrackEventDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfTrackEvent = new EntityInsertionAdapter<TrackEvent>(roomDatabase) { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `TrackEvent` (`id`,`uuid`,`appInstallId`,`appPackage`,`appChannel`,`appId`,`appName`,`appVersion`,`appLanguage`,`customEvent`,`deviceType`,`deviceId`,`deviceUniqueId`,`deviceIp`,`deviceModel`,`deviceTimezone`,`elementId`,`event`,`header`,`headerCustom`,`latitude`,`locationCity`,`locationProvince`,`longitude`,`networkType`,`osVersion`,`pageId`,`pagePreId`,`params`,`screenDensity`,`screenResolution`,`sessionId`,`time`,`userId`,`abtestId`,`abtestGroup`,`userUniqueId`,`hardwareVersion`,`upload`,`priority`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TrackEvent trackEvent) {
                supportSQLiteStatement.bindLong(1, trackEvent.getId());
                if (trackEvent.getUuid() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, trackEvent.getUuid());
                }
                if (trackEvent.getAppInstallId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, trackEvent.getAppInstallId());
                }
                if (trackEvent.getAppPackage() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, trackEvent.getAppPackage());
                }
                if (trackEvent.getAppChannel() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, trackEvent.getAppChannel());
                }
                supportSQLiteStatement.bindLong(6, trackEvent.getAppId());
                if (trackEvent.getAppName() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, trackEvent.getAppName());
                }
                if (trackEvent.getAppVersion() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, trackEvent.getAppVersion());
                }
                if (trackEvent.getAppLanguage() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, trackEvent.getAppLanguage());
                }
                if (trackEvent.getCustomEvent() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, trackEvent.getCustomEvent());
                }
                if (trackEvent.getDeviceType() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, trackEvent.getDeviceType());
                }
                if (trackEvent.getDeviceId() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, trackEvent.getDeviceId());
                }
                if (trackEvent.getDeviceUniqueId() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, trackEvent.getDeviceUniqueId());
                }
                if (trackEvent.getDeviceIp() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, trackEvent.getDeviceIp());
                }
                if (trackEvent.getDeviceModel() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, trackEvent.getDeviceModel());
                }
                if (trackEvent.getDeviceTimezone() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, trackEvent.getDeviceTimezone());
                }
                if (trackEvent.getElementId() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, trackEvent.getElementId());
                }
                if (trackEvent.getEvent() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, trackEvent.getEvent());
                }
                if (trackEvent.getHeader() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, trackEvent.getHeader());
                }
                if (trackEvent.getHeaderCustom() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, trackEvent.getHeaderCustom());
                }
                if (trackEvent.getLatitude() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, trackEvent.getLatitude());
                }
                if (trackEvent.getLocationCity() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, trackEvent.getLocationCity());
                }
                if (trackEvent.getLocationProvince() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, trackEvent.getLocationProvince());
                }
                if (trackEvent.getLongitude() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, trackEvent.getLongitude());
                }
                if (trackEvent.getNetworkType() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, trackEvent.getNetworkType());
                }
                if (trackEvent.getOsVersion() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, trackEvent.getOsVersion());
                }
                if (trackEvent.getPageId() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, trackEvent.getPageId());
                }
                if (trackEvent.getPagePreId() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, trackEvent.getPagePreId());
                }
                if (trackEvent.getParams() == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, trackEvent.getParams());
                }
                if (trackEvent.getScreenDensity() == null) {
                    supportSQLiteStatement.bindNull(30);
                } else {
                    supportSQLiteStatement.bindString(30, trackEvent.getScreenDensity());
                }
                if (trackEvent.getScreenResolution() == null) {
                    supportSQLiteStatement.bindNull(31);
                } else {
                    supportSQLiteStatement.bindString(31, trackEvent.getScreenResolution());
                }
                if (trackEvent.getSessionId() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, trackEvent.getSessionId());
                }
                supportSQLiteStatement.bindLong(33, trackEvent.getTime());
                if (trackEvent.getUserId() == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, trackEvent.getUserId());
                }
                if (trackEvent.getAbtestId() == null) {
                    supportSQLiteStatement.bindNull(35);
                } else {
                    supportSQLiteStatement.bindString(35, trackEvent.getAbtestId());
                }
                if (trackEvent.getAbtestGroup() == null) {
                    supportSQLiteStatement.bindNull(36);
                } else {
                    supportSQLiteStatement.bindString(36, trackEvent.getAbtestGroup());
                }
                if (trackEvent.getUserUniqueId() == null) {
                    supportSQLiteStatement.bindNull(37);
                } else {
                    supportSQLiteStatement.bindString(37, trackEvent.getUserUniqueId());
                }
                if (trackEvent.getHardwareVersion() == null) {
                    supportSQLiteStatement.bindNull(38);
                } else {
                    supportSQLiteStatement.bindString(38, trackEvent.getHardwareVersion());
                }
                supportSQLiteStatement.bindLong(39, trackEvent.getUpload());
                supportSQLiteStatement.bindLong(40, trackEvent.getPriority());
            }
        };
        this.__deletionAdapterOfTrackEvent = new EntityDeletionOrUpdateAdapter<TrackEvent>(roomDatabase) { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `TrackEvent` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TrackEvent trackEvent) {
                supportSQLiteStatement.bindLong(1, trackEvent.getId());
            }
        };
        this.__updateAdapterOfTrackEvent = new EntityDeletionOrUpdateAdapter<TrackEvent>(roomDatabase) { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `TrackEvent` SET `id` = ?,`uuid` = ?,`appInstallId` = ?,`appPackage` = ?,`appChannel` = ?,`appId` = ?,`appName` = ?,`appVersion` = ?,`appLanguage` = ?,`customEvent` = ?,`deviceType` = ?,`deviceId` = ?,`deviceUniqueId` = ?,`deviceIp` = ?,`deviceModel` = ?,`deviceTimezone` = ?,`elementId` = ?,`event` = ?,`header` = ?,`headerCustom` = ?,`latitude` = ?,`locationCity` = ?,`locationProvince` = ?,`longitude` = ?,`networkType` = ?,`osVersion` = ?,`pageId` = ?,`pagePreId` = ?,`params` = ?,`screenDensity` = ?,`screenResolution` = ?,`sessionId` = ?,`time` = ?,`userId` = ?,`abtestId` = ?,`abtestGroup` = ?,`userUniqueId` = ?,`hardwareVersion` = ?,`upload` = ?,`priority` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TrackEvent trackEvent) {
                supportSQLiteStatement.bindLong(1, trackEvent.getId());
                if (trackEvent.getUuid() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, trackEvent.getUuid());
                }
                if (trackEvent.getAppInstallId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, trackEvent.getAppInstallId());
                }
                if (trackEvent.getAppPackage() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, trackEvent.getAppPackage());
                }
                if (trackEvent.getAppChannel() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, trackEvent.getAppChannel());
                }
                supportSQLiteStatement.bindLong(6, trackEvent.getAppId());
                if (trackEvent.getAppName() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, trackEvent.getAppName());
                }
                if (trackEvent.getAppVersion() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, trackEvent.getAppVersion());
                }
                if (trackEvent.getAppLanguage() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, trackEvent.getAppLanguage());
                }
                if (trackEvent.getCustomEvent() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, trackEvent.getCustomEvent());
                }
                if (trackEvent.getDeviceType() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, trackEvent.getDeviceType());
                }
                if (trackEvent.getDeviceId() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, trackEvent.getDeviceId());
                }
                if (trackEvent.getDeviceUniqueId() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, trackEvent.getDeviceUniqueId());
                }
                if (trackEvent.getDeviceIp() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, trackEvent.getDeviceIp());
                }
                if (trackEvent.getDeviceModel() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, trackEvent.getDeviceModel());
                }
                if (trackEvent.getDeviceTimezone() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, trackEvent.getDeviceTimezone());
                }
                if (trackEvent.getElementId() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, trackEvent.getElementId());
                }
                if (trackEvent.getEvent() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, trackEvent.getEvent());
                }
                if (trackEvent.getHeader() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, trackEvent.getHeader());
                }
                if (trackEvent.getHeaderCustom() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, trackEvent.getHeaderCustom());
                }
                if (trackEvent.getLatitude() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, trackEvent.getLatitude());
                }
                if (trackEvent.getLocationCity() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, trackEvent.getLocationCity());
                }
                if (trackEvent.getLocationProvince() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, trackEvent.getLocationProvince());
                }
                if (trackEvent.getLongitude() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, trackEvent.getLongitude());
                }
                if (trackEvent.getNetworkType() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, trackEvent.getNetworkType());
                }
                if (trackEvent.getOsVersion() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, trackEvent.getOsVersion());
                }
                if (trackEvent.getPageId() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, trackEvent.getPageId());
                }
                if (trackEvent.getPagePreId() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, trackEvent.getPagePreId());
                }
                if (trackEvent.getParams() == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, trackEvent.getParams());
                }
                if (trackEvent.getScreenDensity() == null) {
                    supportSQLiteStatement.bindNull(30);
                } else {
                    supportSQLiteStatement.bindString(30, trackEvent.getScreenDensity());
                }
                if (trackEvent.getScreenResolution() == null) {
                    supportSQLiteStatement.bindNull(31);
                } else {
                    supportSQLiteStatement.bindString(31, trackEvent.getScreenResolution());
                }
                if (trackEvent.getSessionId() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, trackEvent.getSessionId());
                }
                supportSQLiteStatement.bindLong(33, trackEvent.getTime());
                if (trackEvent.getUserId() == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, trackEvent.getUserId());
                }
                if (trackEvent.getAbtestId() == null) {
                    supportSQLiteStatement.bindNull(35);
                } else {
                    supportSQLiteStatement.bindString(35, trackEvent.getAbtestId());
                }
                if (trackEvent.getAbtestGroup() == null) {
                    supportSQLiteStatement.bindNull(36);
                } else {
                    supportSQLiteStatement.bindString(36, trackEvent.getAbtestGroup());
                }
                if (trackEvent.getUserUniqueId() == null) {
                    supportSQLiteStatement.bindNull(37);
                } else {
                    supportSQLiteStatement.bindString(37, trackEvent.getUserUniqueId());
                }
                if (trackEvent.getHardwareVersion() == null) {
                    supportSQLiteStatement.bindNull(38);
                } else {
                    supportSQLiteStatement.bindString(38, trackEvent.getHardwareVersion());
                }
                supportSQLiteStatement.bindLong(39, trackEvent.getUpload());
                supportSQLiteStatement.bindLong(40, trackEvent.getPriority());
                supportSQLiteStatement.bindLong(41, trackEvent.getId());
            }
        };
        this.__preparedStmtOfDeleteOldest = new SharedSQLiteStatement(roomDatabase) { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from TrackEvent where id < (select id from TrackEvent order by id asc limit ?, 1)";
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM TrackEvent where upload = 1 and time<?";
            }
        };
        this.__preparedStmtOfDeleteAllUpload = new SharedSQLiteStatement(roomDatabase) { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM TrackEvent where upload = 1";
            }
        };
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object insert(final TrackEvent trackEvent, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.7
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    TrackEventDao_Impl.this.__insertionAdapterOfTrackEvent.insert((EntityInsertionAdapter) trackEvent);
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object insert(final List<TrackEvent> list, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.8
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    TrackEventDao_Impl.this.__insertionAdapterOfTrackEvent.insert((Iterable) list);
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object delete(final TrackEvent trackEvent, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.9
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    TrackEventDao_Impl.this.__deletionAdapterOfTrackEvent.handle(trackEvent);
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object delete(final List<TrackEvent> list, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.10
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    TrackEventDao_Impl.this.__deletionAdapterOfTrackEvent.handleMultiple(list);
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object update(final List<TrackEvent> list, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.11
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    TrackEventDao_Impl.this.__updateAdapterOfTrackEvent.handleMultiple(list);
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object update(final TrackEvent trackEvent, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.12
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    TrackEventDao_Impl.this.__updateAdapterOfTrackEvent.handle(trackEvent);
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object deleteOldest(final long j, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.13
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = TrackEventDao_Impl.this.__preparedStmtOfDeleteOldest.acquire();
                acquire.bindLong(1, j);
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                    TrackEventDao_Impl.this.__preparedStmtOfDeleteOldest.release(acquire);
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object delete(final long j, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.14
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = TrackEventDao_Impl.this.__preparedStmtOfDelete.acquire();
                acquire.bindLong(1, j);
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                    TrackEventDao_Impl.this.__preparedStmtOfDelete.release(acquire);
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object deleteAllUpload(Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.15
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = TrackEventDao_Impl.this.__preparedStmtOfDeleteAllUpload.acquire();
                TrackEventDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    TrackEventDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    TrackEventDao_Impl.this.__db.endTransaction();
                    TrackEventDao_Impl.this.__preparedStmtOfDeleteAllUpload.release(acquire);
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object getNewestList(Continuation<? super List<TrackEvent>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from TrackEvent where upload = 0 order by priority desc, id desc limit 50", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<List<TrackEvent>>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.16
            @Override // java.util.concurrent.Callable
            public List<TrackEvent> call() throws Exception {
                CallableC446616 callableC446616;
                Cursor query = DBUtil.query(TrackEventDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "uuid");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "appInstallId");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "appPackage");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "appChannel");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "appId");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "appName");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "appVersion");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "appLanguage");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "customEvent");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "deviceId");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "deviceUniqueId");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "deviceIp");
                    try {
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "deviceModel");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "deviceTimezone");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "elementId");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "event");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "header");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "headerCustom");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "latitude");
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "locationCity");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "locationProvince");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "longitude");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "networkType");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "osVersion");
                        int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "pageId");
                        int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "pagePreId");
                        int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "params");
                        int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "screenDensity");
                        int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "screenResolution");
                        int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "sessionId");
                        int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "time");
                        int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "userId");
                        int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "abtestId");
                        int columnIndexOrThrow36 = CursorUtil.getColumnIndexOrThrow(query, "abtestGroup");
                        int columnIndexOrThrow37 = CursorUtil.getColumnIndexOrThrow(query, "userUniqueId");
                        int columnIndexOrThrow38 = CursorUtil.getColumnIndexOrThrow(query, "hardwareVersion");
                        int columnIndexOrThrow39 = CursorUtil.getColumnIndexOrThrow(query, "upload");
                        int columnIndexOrThrow40 = CursorUtil.getColumnIndexOrThrow(query, LogFactory.PRIORITY_KEY);
                        int i = columnIndexOrThrow14;
                        ArrayList arrayList = new ArrayList(query.getCount());
                        while (query.moveToNext()) {
                            long j = query.getLong(columnIndexOrThrow);
                            String string = query.getString(columnIndexOrThrow2);
                            String string2 = query.getString(columnIndexOrThrow3);
                            String string3 = query.getString(columnIndexOrThrow4);
                            String string4 = query.getString(columnIndexOrThrow5);
                            int i2 = query.getInt(columnIndexOrThrow6);
                            String string5 = query.getString(columnIndexOrThrow7);
                            String string6 = query.getString(columnIndexOrThrow8);
                            String string7 = query.getString(columnIndexOrThrow9);
                            String string8 = query.getString(columnIndexOrThrow10);
                            String string9 = query.getString(columnIndexOrThrow11);
                            String string10 = query.getString(columnIndexOrThrow12);
                            String string11 = query.getString(columnIndexOrThrow13);
                            int i3 = i;
                            String string12 = query.getString(i3);
                            int i4 = columnIndexOrThrow;
                            int i5 = columnIndexOrThrow15;
                            String string13 = query.getString(i5);
                            columnIndexOrThrow15 = i5;
                            int i6 = columnIndexOrThrow16;
                            String string14 = query.getString(i6);
                            columnIndexOrThrow16 = i6;
                            int i7 = columnIndexOrThrow17;
                            String string15 = query.getString(i7);
                            columnIndexOrThrow17 = i7;
                            int i8 = columnIndexOrThrow18;
                            String string16 = query.getString(i8);
                            columnIndexOrThrow18 = i8;
                            int i9 = columnIndexOrThrow19;
                            String string17 = query.getString(i9);
                            columnIndexOrThrow19 = i9;
                            int i10 = columnIndexOrThrow20;
                            String string18 = query.getString(i10);
                            columnIndexOrThrow20 = i10;
                            int i11 = columnIndexOrThrow21;
                            String string19 = query.getString(i11);
                            columnIndexOrThrow21 = i11;
                            int i12 = columnIndexOrThrow22;
                            String string20 = query.getString(i12);
                            columnIndexOrThrow22 = i12;
                            int i13 = columnIndexOrThrow23;
                            String string21 = query.getString(i13);
                            columnIndexOrThrow23 = i13;
                            int i14 = columnIndexOrThrow24;
                            String string22 = query.getString(i14);
                            columnIndexOrThrow24 = i14;
                            int i15 = columnIndexOrThrow25;
                            String string23 = query.getString(i15);
                            columnIndexOrThrow25 = i15;
                            int i16 = columnIndexOrThrow26;
                            String string24 = query.getString(i16);
                            columnIndexOrThrow26 = i16;
                            int i17 = columnIndexOrThrow27;
                            String string25 = query.getString(i17);
                            columnIndexOrThrow27 = i17;
                            int i18 = columnIndexOrThrow28;
                            String string26 = query.getString(i18);
                            columnIndexOrThrow28 = i18;
                            int i19 = columnIndexOrThrow29;
                            String string27 = query.getString(i19);
                            columnIndexOrThrow29 = i19;
                            int i20 = columnIndexOrThrow30;
                            String string28 = query.getString(i20);
                            columnIndexOrThrow30 = i20;
                            int i21 = columnIndexOrThrow31;
                            String string29 = query.getString(i21);
                            columnIndexOrThrow31 = i21;
                            int i22 = columnIndexOrThrow32;
                            String string30 = query.getString(i22);
                            columnIndexOrThrow32 = i22;
                            int i23 = columnIndexOrThrow33;
                            long j2 = query.getLong(i23);
                            columnIndexOrThrow33 = i23;
                            int i24 = columnIndexOrThrow34;
                            String string31 = query.getString(i24);
                            columnIndexOrThrow34 = i24;
                            int i25 = columnIndexOrThrow35;
                            String string32 = query.getString(i25);
                            columnIndexOrThrow35 = i25;
                            int i26 = columnIndexOrThrow36;
                            String string33 = query.getString(i26);
                            columnIndexOrThrow36 = i26;
                            int i27 = columnIndexOrThrow37;
                            String string34 = query.getString(i27);
                            columnIndexOrThrow37 = i27;
                            int i28 = columnIndexOrThrow38;
                            String string35 = query.getString(i28);
                            columnIndexOrThrow38 = i28;
                            int i29 = columnIndexOrThrow39;
                            int i30 = query.getInt(i29);
                            columnIndexOrThrow39 = i29;
                            int i31 = columnIndexOrThrow40;
                            columnIndexOrThrow40 = i31;
                            arrayList.add(new TrackEvent(j, string, string2, string3, string4, i2, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, string18, string19, string20, string21, string22, string23, string24, string25, string26, string27, string28, string29, string30, j2, string31, string32, string33, string34, string35, i30, query.getInt(i31)));
                            columnIndexOrThrow = i4;
                            i = i3;
                        }
                        query.close();
                        acquire.release();
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        callableC446616 = this;
                        query.close();
                        acquire.release();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    callableC446616 = this;
                }
            }
        }, continuation);
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Flow<Integer> getCountFlow() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM TrackEvent ", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"TrackEvent"}, new Callable<Integer>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.17
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                Integer num = null;
                Cursor query = DBUtil.query(TrackEventDao_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        num = Integer.valueOf(query.getInt(0));
                    }
                    return num;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Flow<Integer> getCountFlowByUpload(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM TrackEvent where upload=?", 1);
        acquire.bindLong(1, i);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"TrackEvent"}, new Callable<Integer>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.18
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                Integer num = null;
                Cursor query = DBUtil.query(TrackEventDao_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        num = Integer.valueOf(query.getInt(0));
                    }
                    return num;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    @Override // com.pudutech.event_tracking.p055db.TrackEventDao
    public Object getCount(Continuation<? super Integer> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*) FROM TrackEvent ", 0);
        return CoroutinesRoom.execute(this.__db, false, new Callable<Integer>() { // from class: com.pudutech.event_tracking.db.TrackEventDao_Impl.19
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                Integer num = null;
                Cursor query = DBUtil.query(TrackEventDao_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        num = Integer.valueOf(query.getInt(0));
                    }
                    return num;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }
}
