package com.pudu.library.loracall.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p003db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class TableBindDao_Impl implements TableBindDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<TableMatchBean> __deletionAdapterOfTableMatchBean;
    private final EntityInsertionAdapter<TableMatchBean> __insertionAdapterOfTableMatchBean;

    public TableBindDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfTableMatchBean = new EntityInsertionAdapter<TableMatchBean>(roomDatabase) { // from class: com.pudu.library.loracall.dao.TableBindDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `TableMatchBean` (`devAdder`,`tableName`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TableMatchBean tableMatchBean) {
                if (tableMatchBean.getDevAdder() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, tableMatchBean.getDevAdder());
                }
                if (tableMatchBean.getTableName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, tableMatchBean.getTableName());
                }
            }
        };
        this.__deletionAdapterOfTableMatchBean = new EntityDeletionOrUpdateAdapter<TableMatchBean>(roomDatabase) { // from class: com.pudu.library.loracall.dao.TableBindDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `TableMatchBean` WHERE `devAdder` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TableMatchBean tableMatchBean) {
                if (tableMatchBean.getDevAdder() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, tableMatchBean.getDevAdder());
                }
            }
        };
    }

    @Override // com.pudu.library.loracall.dao.TableBindDao
    public void insert(TableMatchBean tableMatchBean) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTableMatchBean.insert((EntityInsertionAdapter<TableMatchBean>) tableMatchBean);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudu.library.loracall.dao.TableBindDao
    public void insert(List<TableMatchBean> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTableMatchBean.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudu.library.loracall.dao.TableBindDao
    public void delete(TableMatchBean tableMatchBean) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTableMatchBean.handle(tableMatchBean);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.pudu.library.loracall.dao.TableBindDao
    public List<TableMatchBean> getTableList() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from TableMatchBean ", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "devAdder");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "tableName");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TableMatchBean tableMatchBean = new TableMatchBean(query.getString(columnIndexOrThrow2));
                tableMatchBean.setDevAdder(query.getString(columnIndexOrThrow));
                arrayList.add(tableMatchBean);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.pudu.library.loracall.dao.TableBindDao
    public TableMatchBean getTable(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from TableMatchBean where devAdder=? ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        TableMatchBean tableMatchBean = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "devAdder");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "tableName");
            if (query.moveToFirst()) {
                TableMatchBean tableMatchBean2 = new TableMatchBean(query.getString(columnIndexOrThrow2));
                tableMatchBean2.setDevAdder(query.getString(columnIndexOrThrow));
                tableMatchBean = tableMatchBean2;
            }
            return tableMatchBean;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.pudu.library.loracall.dao.TableBindDao
    public List<TableMatchBean> getTableNameList(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from TableMatchBean where tableName=? ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "devAdder");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "tableName");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TableMatchBean tableMatchBean = new TableMatchBean(query.getString(columnIndexOrThrow2));
                tableMatchBean.setDevAdder(query.getString(columnIndexOrThrow));
                arrayList.add(tableMatchBean);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
