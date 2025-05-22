package org.jetbrains.anko.p093db;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u00028\u00002\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lorg/jetbrains/anko/db/RowParser;", ExifInterface.GPS_DIRECTION_TRUE, "", "parseRow", "columns", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public interface RowParser<T> {
    T parseRow(Object[] columns);
}
