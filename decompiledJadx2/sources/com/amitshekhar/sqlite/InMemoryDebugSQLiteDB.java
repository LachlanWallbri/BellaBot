package com.amitshekhar.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import androidx.sqlite.p003db.SupportSQLiteDatabase;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class InMemoryDebugSQLiteDB implements SQLiteDB {
    private final SupportSQLiteDatabase database;

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public void close() {
    }

    public InMemoryDebugSQLiteDB(SupportSQLiteDatabase supportSQLiteDatabase) {
        this.database = supportSQLiteDatabase;
    }

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public int delete(String str, String str2, String[] strArr) {
        return this.database.delete(str, str2, strArr);
    }

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public boolean isOpen() {
        return this.database.isOpen();
    }

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public Cursor rawQuery(String str, String[] strArr) {
        return this.database.query(str, strArr);
    }

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public void execSQL(String str) throws SQLException {
        this.database.execSQL(str);
    }

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public long insert(String str, String str2, ContentValues contentValues) {
        return this.database.insert(str, 0, contentValues);
    }

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        return this.database.update(str, 0, contentValues, str2, strArr);
    }

    @Override // com.amitshekhar.sqlite.SQLiteDB
    public int getVersion() {
        return this.database.getVersion();
    }
}
