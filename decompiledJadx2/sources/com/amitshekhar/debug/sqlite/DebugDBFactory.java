package com.amitshekhar.debug.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amitshekhar.sqlite.DBFactory;
import com.amitshekhar.sqlite.SQLiteDB;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DebugDBFactory implements DBFactory {
    @Override // com.amitshekhar.sqlite.DBFactory
    public SQLiteDB create(Context context, String str, String str2) {
        return new DebugSQLiteDB(SQLiteDatabase.openOrCreateDatabase(str, (SQLiteDatabase.CursorFactory) null));
    }
}
