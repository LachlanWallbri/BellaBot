package org.greenrobot.greendao;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.greenrobot.greendao.database.Database;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DbUtils {
    public static void vacuum(Database database) {
        database.execSQL("VACUUM");
    }

    public static int executeSqlScript(Context context, Database database, String str) throws IOException {
        return executeSqlScript(context, database, str, true);
    }

    public static int executeSqlScript(Context context, Database database, String str, boolean z) throws IOException {
        int executeSqlStatements;
        String[] split = new String(readAsset(context, str), "UTF-8").split(";(\\s)*[\n\r]");
        if (z) {
            executeSqlStatements = executeSqlStatementsInTx(database, split);
        } else {
            executeSqlStatements = executeSqlStatements(database, split);
        }
        DaoLog.m4140i("Executed " + executeSqlStatements + " statements from SQL script '" + str + "'");
        return executeSqlStatements;
    }

    public static int executeSqlStatementsInTx(Database database, String[] strArr) {
        database.beginTransaction();
        try {
            int executeSqlStatements = executeSqlStatements(database, strArr);
            database.setTransactionSuccessful();
            return executeSqlStatements;
        } finally {
            database.endTransaction();
        }
    }

    public static int executeSqlStatements(Database database, String[] strArr) {
        int i = 0;
        for (String str : strArr) {
            String trim = str.trim();
            if (trim.length() > 0) {
                database.execSQL(trim);
                i++;
            }
        }
        return i;
    }

    public static int copyAllBytes(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return i;
            }
            outputStream.write(bArr, 0, read);
            i += read;
        }
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyAllBytes(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] readAsset(Context context, String str) throws IOException {
        InputStream open = context.getResources().getAssets().open(str);
        try {
            return readAllBytes(open);
        } finally {
            open.close();
        }
    }

    public static void logTableDump(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query = sQLiteDatabase.query(str, null, null, null, null, null, null);
        try {
            DaoLog.m4136d(DatabaseUtils.dumpCursorToString(query));
        } finally {
            query.close();
        }
    }
}
