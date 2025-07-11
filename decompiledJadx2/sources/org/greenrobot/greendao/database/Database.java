package org.greenrobot.greendao.database;

import android.database.Cursor;
import android.database.SQLException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface Database {
    void beginTransaction();

    void close();

    DatabaseStatement compileStatement(String str);

    void endTransaction();

    void execSQL(String str) throws SQLException;

    void execSQL(String str, Object[] objArr) throws SQLException;

    Object getRawDatabase();

    boolean inTransaction();

    boolean isDbLockedByCurrentThread();

    Cursor rawQuery(String str, String[] strArr);

    void setTransactionSuccessful();
}
