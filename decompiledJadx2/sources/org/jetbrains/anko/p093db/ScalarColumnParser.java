package org.jetbrains.anko.p093db;

import android.database.sqlite.SQLiteException;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0006\b\u0001\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u0003B\u001d\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\t\u001a\u00028\u00012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010\rR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, m3961d2 = {"Lorg/jetbrains/anko/db/ScalarColumnParser;", "R", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/jetbrains/anko/db/RowParser;", "modifier", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)V", "getModifier", "()Lkotlin/jvm/functions/Function1;", "parseRow", "columns", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
final class ScalarColumnParser<R, T> implements RowParser<T> {
    private final Function1<R, T> modifier;

    public ScalarColumnParser() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ScalarColumnParser(Function1<? super R, ? extends T> function1) {
        this.modifier = function1;
    }

    public /* synthetic */ ScalarColumnParser(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Function1) null : function1);
    }

    public final Function1<R, T> getModifier() {
        return this.modifier;
    }

    @Override // org.jetbrains.anko.p093db.RowParser
    public T parseRow(Object[] columns) {
        Intrinsics.checkParameterIsNotNull(columns, "columns");
        if (columns.length != 1) {
            throw new SQLiteException("Invalid row: row for SingleColumnParser must contain exactly one column");
        }
        Function1<R, T> function1 = this.modifier;
        if (function1 == null) {
            return (T) columns[0];
        }
        if (function1 == null) {
            Intrinsics.throwNpe();
        }
        return (T) function1.invoke(columns[0]);
    }
}
