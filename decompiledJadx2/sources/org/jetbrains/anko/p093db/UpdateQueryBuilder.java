package org.jetbrains.anko.p093db;

import android.content.ContentValues;
import com.pudutech.freeinstall_ui.utils.Constants;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: UpdateQueryBuilder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001c\u0010\u0004\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0013\u001a\u00020\u0014J9\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00032\u0010\u0010\u0019\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0003H\u0007JA\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00032*\u0010\u001d\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00060\u0005\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006H\u0007¢\u0006\u0002\u0010\u001eJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0003J?\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00032*\u0010\u001d\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00060\u0005\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\u0010\u001eJ'\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00032\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0002\u0010 J)\u0010!\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00032\u0012\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003H\u0007¢\u0006\u0002\u0010 R\u001a\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\u0004\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00060\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\""}, m3961d2 = {"Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "", "tableName", "", "values", "", "Lkotlin/Pair;", "(Ljava/lang/String;[Lkotlin/Pair;)V", "nativeSelectionArgs", "[Ljava/lang/String;", "selection", "selectionApplied", "", "getTableName", "()Ljava/lang/String;", "useNativeSelection", "getValues", "()[Lkotlin/Pair;", "[Lkotlin/Pair;", "exec", "", "execUpdate", Constants.POINT_TYPE_TABLE, "Landroid/content/ContentValues;", "whereClause", "whereArgs", "(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "where", "select", "args", "(Ljava/lang/String;[Lkotlin/Pair;)Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "whereSimple", "(Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "whereSupport", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public abstract class UpdateQueryBuilder {
    private String[] nativeSelectionArgs;
    private String selection;
    private boolean selectionApplied;
    private final String tableName;
    private boolean useNativeSelection;
    private final Pair<String, Object>[] values;

    public abstract int execUpdate(String table, ContentValues values, String whereClause, String[] whereArgs);

    public UpdateQueryBuilder(String tableName, Pair<String, ? extends Object>[] values) {
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.tableName = tableName;
        this.values = values;
    }

    public final String getTableName() {
        return this.tableName;
    }

    public final Pair<String, Object>[] getValues() {
        return this.values;
    }

    @Deprecated(message = "Use whereArgs() instead.", replaceWith = @ReplaceWith(expression = "whereArgs(select, *args)", imports = {}))
    public final UpdateQueryBuilder where(String select, Pair<String, ? extends Object>... args) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(args, "args");
        return whereArgs(select, (Pair[]) Arrays.copyOf(args, args.length));
    }

    public final UpdateQueryBuilder whereArgs(String select, Pair<String, ? extends Object>... args) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(args, "args");
        if (this.selectionApplied) {
            throw new AnkoException("Query selection was already applied.");
        }
        this.selectionApplied = true;
        this.useNativeSelection = false;
        HashMap hashMap = new HashMap();
        for (Pair<String, ? extends Object> pair : args) {
            hashMap.put(pair.getFirst(), pair.getSecond());
        }
        this.selection = DatabaseKt.applyArguments(select, hashMap);
        return this;
    }

    @Deprecated(message = "Use whereArgs() instead.", replaceWith = @ReplaceWith(expression = "whereArgs(select)", imports = {}))
    public final UpdateQueryBuilder where(String select) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        return whereArgs(select);
    }

    public final UpdateQueryBuilder whereArgs(String select) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        if (this.selectionApplied) {
            throw new AnkoException("Query selection was already applied.");
        }
        this.selectionApplied = true;
        this.useNativeSelection = false;
        this.selection = select;
        return this;
    }

    public final UpdateQueryBuilder whereSimple(String select, String... args) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(args, "args");
        if (this.selectionApplied) {
            throw new AnkoException("Query selection was already applied.");
        }
        this.selectionApplied = true;
        this.useNativeSelection = true;
        this.selection = select;
        this.nativeSelectionArgs = args;
        return this;
    }

    @Deprecated(message = "Use whereSimple() instead", replaceWith = @ReplaceWith(expression = "whereSimple(select, *args)", imports = {}))
    public final UpdateQueryBuilder whereSupport(String select, String... args) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(args, "args");
        return whereSimple(select, (String[]) Arrays.copyOf(args, args.length));
    }

    public final int exec() {
        String[] strArr = null;
        String str = this.selectionApplied ? this.selection : null;
        if (this.selectionApplied && this.useNativeSelection) {
            strArr = this.nativeSelectionArgs;
        }
        return execUpdate(this.tableName, DatabaseKt.toContentValues(this.values), str, strArr);
    }
}
