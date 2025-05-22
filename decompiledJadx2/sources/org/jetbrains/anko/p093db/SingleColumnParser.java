package org.jetbrains.anko.p093db;

import android.database.sqlite.SQLiteException;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00028\u00002\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, m3961d2 = {"Lorg/jetbrains/anko/db/SingleColumnParser;", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/jetbrains/anko/db/RowParser;", "()V", "parseRow", "columns", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
final class SingleColumnParser<T> implements RowParser<T> {
    @Override // org.jetbrains.anko.p093db.RowParser
    public T parseRow(Object[] columns) {
        Intrinsics.checkParameterIsNotNull(columns, "columns");
        if (columns.length != 1) {
            throw new SQLiteException("Invalid row: row for SingleColumnParser must contain exactly one column");
        }
        return (T) columns[0];
    }
}
