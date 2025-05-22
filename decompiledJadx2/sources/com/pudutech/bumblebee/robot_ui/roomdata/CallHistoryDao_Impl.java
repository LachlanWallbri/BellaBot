package com.pudutech.bumblebee.robot_ui.roomdata;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p003db.SupportSQLiteStatement;
import com.pudutech.bumblebee.robot_ui.bean.CallHistoryData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class CallHistoryDao_Impl implements CallHistoryDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<CallHistoryData> __deletionAdapterOfCallHistoryData;
    private final EntityInsertionAdapter<CallHistoryData> __insertionAdapterOfCallHistoryData;
    private final SharedSQLiteStatement __preparedStmtOfDeleteCallHistory;

    public CallHistoryDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfCallHistoryData = new EntityInsertionAdapter<CallHistoryData>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.CallHistoryDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `call_history_data` (`mid`,`completeTime`,`destination`) VALUES (nullif(?, 0),?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CallHistoryData callHistoryData) {
                supportSQLiteStatement.bindLong(1, callHistoryData.getMid());
                supportSQLiteStatement.bindLong(2, callHistoryData.getCompleteTime());
                if (callHistoryData.getDestination() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, callHistoryData.getDestination());
                }
            }
        };
        this.__deletionAdapterOfCallHistoryData = new EntityDeletionOrUpdateAdapter<CallHistoryData>(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.CallHistoryDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `call_history_data` WHERE `mid` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CallHistoryData callHistoryData) {
                supportSQLiteStatement.bindLong(1, callHistoryData.getMid());
            }
        };
        this.__preparedStmtOfDeleteCallHistory = new SharedSQLiteStatement(roomDatabase) { // from class: com.pudutech.bumblebee.robot_ui.roomdata.CallHistoryDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM call_history_data where completeTime < ?";
            }
        };
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.CallHistoryDao
    public void addCallHistory(CallHistoryData callHistoryData) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCallHistoryData.insert((EntityInsertionAdapter<CallHistoryData>) callHistoryData);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.CallHistoryDao
    public void deleteCallHistory(CallHistoryData callHistoryData) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfCallHistoryData.handle(callHistoryData);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.CallHistoryDao
    public void deleteCallHistory(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteCallHistory.acquire();
        acquire.bindLong(1, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteCallHistory.release(acquire);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.roomdata.CallHistoryDao
    public List<CallHistoryData> getCallHistoryList(long j, int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM call_history_data where completeTime >= ? ORDER BY completeTime DESC limit ?", 2);
        acquire.bindLong(1, j);
        acquire.bindLong(2, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "completeTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "destination");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                CallHistoryData callHistoryData = new CallHistoryData();
                callHistoryData.setMid(query.getLong(columnIndexOrThrow));
                callHistoryData.setCompleteTime(query.getLong(columnIndexOrThrow2));
                callHistoryData.setDestination(query.getString(columnIndexOrThrow3));
                arrayList.add(callHistoryData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
