package com.pudutech.event_tracking.p055db.fix;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.Pair;
import androidx.sqlite.p003db.SimpleSQLiteQuery;
import androidx.sqlite.p003db.SupportSQLiteDatabase;
import androidx.sqlite.p003db.SupportSQLiteQuery;
import androidx.sqlite.p003db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final SQLiteDatabase mDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameworkSQLiteDatabase(SQLiteDatabase sQLiteDatabase) {
        this.mDelegate = sQLiteDatabase;
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public SupportSQLiteStatement compileStatement(String str) {
        return new FrameworkSQLiteStatement(this.mDelegate.compileStatement(str));
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void beginTransaction() {
        this.mDelegate.beginTransaction();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void beginTransactionNonExclusive() {
        this.mDelegate.beginTransactionNonExclusive();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mDelegate.beginTransactionWithListener(sQLiteTransactionListener);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mDelegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void endTransaction() {
        this.mDelegate.endTransaction();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void setTransactionSuccessful() {
        this.mDelegate.setTransactionSuccessful();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean inTransaction() {
        return this.mDelegate.inTransaction();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean isDbLockedByCurrentThread() {
        return this.mDelegate.isDbLockedByCurrentThread();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely() {
        return this.mDelegate.yieldIfContendedSafely();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean yieldIfContendedSafely(long j) {
        return this.mDelegate.yieldIfContendedSafely(j);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public int getVersion() {
        return this.mDelegate.getVersion();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void setVersion(int i) {
        this.mDelegate.setVersion(i);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public long getMaximumSize() {
        return this.mDelegate.getMaximumSize();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public long setMaximumSize(long j) {
        return this.mDelegate.setMaximumSize(j);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public long getPageSize() {
        return this.mDelegate.getPageSize();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void setPageSize(long j) {
        this.mDelegate.setPageSize(j);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public Cursor query(String str) {
        return query(new SimpleSQLiteQuery(str));
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public Cursor query(String str, Object[] objArr) {
        return query(new SimpleSQLiteQuery(str, objArr));
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery) {
        return this.mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: com.pudutech.event_tracking.db.fix.FrameworkSQLiteDatabase.1
            @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
            public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                supportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(sQLiteQuery));
                return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
            }
        }, supportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, null);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        return this.mDelegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: com.pudutech.event_tracking.db.fix.FrameworkSQLiteDatabase.2
            @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
            public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                supportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(sQLiteQuery));
                return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
            }
        }, supportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, null, cancellationSignal);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public long insert(String str, int i, ContentValues contentValues) throws SQLException {
        return this.mDelegate.insertWithOnConflict(str, null, contentValues, i);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public int delete(String str, String str2, Object[] objArr) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(str);
        if (TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            str3 = " WHERE " + str2;
        }
        sb.append(str3);
        SupportSQLiteStatement compileStatement = compileStatement(sb.toString());
        SimpleSQLiteQuery.bind(compileStatement, objArr);
        return compileStatement.executeUpdateDelete();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        if (contentValues == null || contentValues.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        StringBuilder sb = new StringBuilder(120);
        sb.append("UPDATE ");
        sb.append(CONFLICT_VALUES[i]);
        sb.append(str);
        sb.append(" SET ");
        int size = contentValues.size();
        int length = objArr == null ? size : objArr.length + size;
        Object[] objArr2 = new Object[length];
        int i2 = 0;
        for (String str3 : contentValues.keySet()) {
            sb.append(i2 > 0 ? "," : "");
            sb.append(str3);
            objArr2[i2] = contentValues.get(str3);
            sb.append("=?");
            i2++;
        }
        if (objArr != null) {
            for (int i3 = size; i3 < length; i3++) {
                objArr2[i3] = objArr[i3 - size];
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(" WHERE ");
            sb.append(str2);
        }
        SupportSQLiteStatement compileStatement = compileStatement(sb.toString());
        SimpleSQLiteQuery.bind(compileStatement, objArr2);
        return compileStatement.executeUpdateDelete();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void execSQL(String str) throws SQLException {
        this.mDelegate.execSQL(str);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void execSQL(String str, Object[] objArr) throws SQLException {
        this.mDelegate.execSQL(str, objArr);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean isReadOnly() {
        return this.mDelegate.isReadOnly();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean isOpen() {
        return this.mDelegate.isOpen();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean needUpgrade(int i) {
        return this.mDelegate.needUpgrade(i);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public String getPath() {
        return this.mDelegate.getPath();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void setLocale(Locale locale) {
        this.mDelegate.setLocale(locale);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void setMaxSqlCacheSize(int i) {
        this.mDelegate.setMaxSqlCacheSize(i);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void setForeignKeyConstraintsEnabled(boolean z) {
        this.mDelegate.setForeignKeyConstraintsEnabled(z);
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public void disableWriteAheadLogging() {
        this.mDelegate.disableWriteAheadLogging();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean isWriteAheadLoggingEnabled() {
        return this.mDelegate.isWriteAheadLoggingEnabled();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public List<Pair<String, String>> getAttachedDbs() {
        return this.mDelegate.getAttachedDbs();
    }

    @Override // androidx.sqlite.p003db.SupportSQLiteDatabase
    public boolean isDatabaseIntegrityOk() {
        return this.mDelegate.isDatabaseIntegrityOk();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mDelegate.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDelegate(SQLiteDatabase sQLiteDatabase) {
        return this.mDelegate == sQLiteDatabase;
    }
}
