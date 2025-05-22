package org.jetbrains.anko.p093db;

import android.database.Cursor;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00020\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0017\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002H\u0096\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, m3961d2 = {"Lorg/jetbrains/anko/db/CursorMapIterator;", "", "", "", "", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)V", "getCursor", "()Landroid/database/Cursor;", "hasNext", "", ES6Iterator.NEXT_METHOD, "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
final class CursorMapIterator implements Iterator<Map<String, ? extends Object>>, KMappedMarker {
    private final Cursor cursor;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public CursorMapIterator(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        this.cursor = cursor;
    }

    public final Cursor getCursor() {
        return this.cursor;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.cursor.getPosition() < this.cursor.getCount() - 1;
    }

    @Override // java.util.Iterator
    public Map<String, ? extends Object> next() {
        Map<String, ? extends Object> readColumnsMap;
        this.cursor.moveToNext();
        readColumnsMap = SqlParsersKt.readColumnsMap(this.cursor);
        return readColumnsMap;
    }
}
