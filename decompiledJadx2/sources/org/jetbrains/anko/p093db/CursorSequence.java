package org.jetbrains.anko.p093db;

import android.database.Cursor;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\nH\u0096\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m3961d2 = {"Lorg/jetbrains/anko/db/CursorSequence;", "Lkotlin/sequences/Sequence;", "", "", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)V", "getCursor", "()Landroid/database/Cursor;", "iterator", "Lorg/jetbrains/anko/db/CursorIterator;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
final class CursorSequence implements Sequence<Object[]> {
    private final Cursor cursor;

    public CursorSequence(Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        this.cursor = cursor;
    }

    public final Cursor getCursor() {
        return this.cursor;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<Object[]> iterator() {
        return new CursorIterator(this.cursor);
    }
}
