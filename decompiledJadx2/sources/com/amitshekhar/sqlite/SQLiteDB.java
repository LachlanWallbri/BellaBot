package com.amitshekhar.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface SQLiteDB {
    void close();

    int delete(String str, String str2, String[] strArr);

    void execSQL(String str) throws SQLException;

    int getVersion();

    long insert(String str, String str2, ContentValues contentValues);

    boolean isOpen();

    Cursor rawQuery(String str, String[] strArr);

    int update(String str, ContentValues contentValues, String str2, String[] strArr);
}
