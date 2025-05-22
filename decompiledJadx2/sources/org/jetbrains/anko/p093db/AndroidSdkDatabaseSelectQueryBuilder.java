package org.jetbrains.anko.p093db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SelectQueryBuilder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006Jk\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0014¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lorg/jetbrains/anko/db/AndroidSdkDatabaseSelectQueryBuilder;", "Lorg/jetbrains/anko/db/SelectQueryBuilder;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "tableName", "", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V", "execQuery", "Landroid/database/Cursor;", "distinct", "", "columns", "", "selection", "selectionArgs", "groupBy", "having", "orderBy", "limit", "(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class AndroidSdkDatabaseSelectQueryBuilder extends SelectQueryBuilder {
    private final SQLiteDatabase db;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidSdkDatabaseSelectQueryBuilder(SQLiteDatabase db, String tableName) {
        super(tableName);
        Intrinsics.checkParameterIsNotNull(db, "db");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        this.db = db;
    }

    @Override // org.jetbrains.anko.p093db.SelectQueryBuilder
    protected Cursor execQuery(boolean distinct, String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(columns, "columns");
        Intrinsics.checkParameterIsNotNull(groupBy, "groupBy");
        Intrinsics.checkParameterIsNotNull(orderBy, "orderBy");
        Cursor query = this.db.query(distinct, tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        Intrinsics.checkExpressionValueIsNotNull(query, "db.query(distinct, table…, having, orderBy, limit)");
        return query;
    }
}
