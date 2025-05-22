package org.jetbrains.anko.p093db;

import android.database.Cursor;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00020\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, m3961d2 = {"Lorg/jetbrains/anko/db/CursorMapSequence;", "Lkotlin/sequences/Sequence;", "", "", "", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)V", "getCursor", "()Landroid/database/Cursor;", "iterator", "Lorg/jetbrains/anko/db/CursorMapIterator;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
final class CursorMapSequence implements Sequence<Map<String, ? extends Object>> {
    private final Cursor cursor;

    public CursorMapSequence(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        this.cursor = cursor;
    }

    public final Cursor getCursor() {
        return this.cursor;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<Map<String, ? extends Object>> iterator() {
        return new CursorMapIterator(this.cursor);
    }
}
