package org.jetbrains.anko.p093db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Database.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J*\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u00130\u0015¢\u0006\u0002\b\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lorg/jetbrains/anko/db/ManagedSQLiteOpenHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "ctx", "Landroid/content/Context;", "name", "", "factory", "Landroid/database/sqlite/SQLiteDatabase$CursorFactory;", "version", "", "(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V", "counter", "Ljava/util/concurrent/atomic/AtomicInteger;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "closeDatabase", "", "openDatabase", "use", ExifInterface.GPS_DIRECTION_TRUE, C3898x.f4339h, "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public abstract class ManagedSQLiteOpenHelper extends SQLiteOpenHelper {
    private final AtomicInteger counter;
    private SQLiteDatabase db;

    public /* synthetic */ ManagedSQLiteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i2 & 4) != 0 ? (SQLiteDatabase.CursorFactory) null : cursorFactory, (i2 & 8) != 0 ? 1 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManagedSQLiteOpenHelper(Context ctx, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(ctx, str, cursorFactory, i);
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        this.counter = new AtomicInteger();
    }

    public final <T> T use(Function1<? super SQLiteDatabase, ? extends T> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        try {
            return f.invoke(openDatabase());
        } finally {
            closeDatabase();
        }
    }

    private final synchronized SQLiteDatabase openDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (this.counter.incrementAndGet() == 1) {
            this.db = getWritableDatabase();
        }
        sQLiteDatabase = this.db;
        if (sQLiteDatabase == null) {
            Intrinsics.throwNpe();
        }
        return sQLiteDatabase;
    }

    private final synchronized void closeDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (this.counter.decrementAndGet() == 0 && (sQLiteDatabase = this.db) != null) {
            sQLiteDatabase.close();
        }
    }
}
