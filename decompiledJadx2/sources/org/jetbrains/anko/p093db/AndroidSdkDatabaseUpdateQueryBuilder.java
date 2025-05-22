package org.jetbrains.anko.p093db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.pudutech.freeinstall_ui.utils.Constants;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: UpdateQueryBuilder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\t0\b0\u0007¢\u0006\u0002\u0010\nJ9\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0007H\u0016¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lorg/jetbrains/anko/db/AndroidSdkDatabaseUpdateQueryBuilder;", "Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "db", "Landroid/database/sqlite/SQLiteDatabase;", Constants.POINT_TYPE_TABLE, "", "values", "", "Lkotlin/Pair;", "", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Lkotlin/Pair;)V", "execUpdate", "", "Landroid/content/ContentValues;", "whereClause", "whereArgs", "(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class AndroidSdkDatabaseUpdateQueryBuilder extends UpdateQueryBuilder {
    private final SQLiteDatabase db;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidSdkDatabaseUpdateQueryBuilder(SQLiteDatabase db, String table, Pair<String, ? extends Object>[] values) {
        super(table, values);
        Intrinsics.checkParameterIsNotNull(db, "db");
        Intrinsics.checkParameterIsNotNull(table, "table");
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.db = db;
    }

    @Override // org.jetbrains.anko.p093db.UpdateQueryBuilder
    public int execUpdate(String table, ContentValues values, String whereClause, String[] whereArgs) {
        Intrinsics.checkParameterIsNotNull(table, "table");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return this.db.update(table, values, whereClause, whereArgs);
    }
}
