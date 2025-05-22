package org.jetbrains.anko.p093db;

import android.database.Cursor;
import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoException;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SelectQueryBuilder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0003J\u001f\u0010\u0005\u001a\u00020\u00002\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u000f\"\u00020\u0003¢\u0006\u0002\u0010\u001aJ\u0006\u0010\b\u001a\u00020\u0000J\b\u0010\u001b\u001a\u00020\u001cH\u0001J*\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\u0017\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u0002H\u001e0 ¢\u0006\u0002\b!¢\u0006\u0002\u0010\"Jk\u0010#\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0010\u0010$\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0003H$¢\u0006\u0002\u0010%J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0003J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003J?\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00032*\u0010'\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(0\u000f\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\u0010)J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+J\u0016\u0010\r\u001a\u00020\u00002\u0006\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020+J\u0018\u0010\u0011\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020.J'\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u001e00\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e02H\u0086\bJ'\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u001e00\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e03H\u0086\bJ(\u00104\u001a\u0004\u0018\u0001H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e02H\u0086\b¢\u0006\u0002\u00105J(\u00104\u001a\u0004\u0018\u0001H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e03H\u0086\b¢\u0006\u0002\u00106J&\u00107\u001a\u0002H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e02H\u0086\b¢\u0006\u0002\u00105J&\u00107\u001a\u0002H\u001e\"\b\b\u0000\u0010\u001e*\u00020\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u0002H\u001e03H\u0086\b¢\u0006\u0002\u00106J\u0010\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0003H\u0007JA\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032*\u0010'\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(0\u000f\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(H\u0007¢\u0006\u0002\u0010)J\u000e\u0010:\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0003J?\u0010:\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032*\u0010'\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(0\u000f\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\u0010)J'\u0010;\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u000f\"\u00020\u0003¢\u0006\u0002\u0010<J)\u0010=\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u00032\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u000f\"\u00020\u0003H\u0007¢\u0006\u0002\u0010<R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0006j\b\u0012\u0004\u0012\u00020\u0003`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0006j\b\u0012\u0004\u0012\u00020\u0003`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0006j\b\u0012\u0004\u0012\u00020\u0003`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, m3961d2 = {"Lorg/jetbrains/anko/db/SelectQueryBuilder;", "", "tableName", "", "(Ljava/lang/String;)V", "columns", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "distinct", "", "groupBy", "having", "havingApplied", "limit", "nativeSelectionArgs", "", "[Ljava/lang/String;", "orderBy", "selection", "selectionApplied", "getTableName", "()Ljava/lang/String;", "useNativeSelection", "column", "name", "names", "([Ljava/lang/String;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "doExec", "Landroid/database/Cursor;", "exec", ExifInterface.GPS_DIRECTION_TRUE, C3898x.f4339h, "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "execQuery", "selectionArgs", "(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", ES6Iterator.VALUE_PROPERTY, "args", "Lkotlin/Pair;", "(Ljava/lang/String;[Lkotlin/Pair;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "count", "", TypedValues.Cycle.S_WAVE_OFFSET, "direction", "Lorg/jetbrains/anko/db/SqlOrderDirection;", "parseList", "", "parser", "Lorg/jetbrains/anko/db/MapRowParser;", "Lorg/jetbrains/anko/db/RowParser;", "parseOpt", "(Lorg/jetbrains/anko/db/MapRowParser;)Ljava/lang/Object;", "(Lorg/jetbrains/anko/db/RowParser;)Ljava/lang/Object;", "parseSingle", "where", "select", "whereArgs", "whereSimple", "(Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "whereSupport", "sqlite-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public abstract class SelectQueryBuilder {
    private final ArrayList<String> columns;
    private boolean distinct;
    private final ArrayList<String> groupBy;
    private String having;
    private boolean havingApplied;
    private String limit;
    private String[] nativeSelectionArgs;
    private final ArrayList<String> orderBy;
    private String selection;
    private boolean selectionApplied;
    private final String tableName;
    private boolean useNativeSelection;

    protected abstract Cursor execQuery(boolean distinct, String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    public SelectQueryBuilder(String tableName) {
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        this.tableName = tableName;
        this.columns = new ArrayList<>();
        this.groupBy = new ArrayList<>();
        this.orderBy = new ArrayList<>();
    }

    public final String getTableName() {
        return this.tableName;
    }

    public final <T> T exec(Function1<? super Cursor, ? extends T> f) {
        T invoke;
        Intrinsics.checkParameterIsNotNull(f, "f");
        Cursor doExec = doExec();
        if (Build.VERSION.SDK_INT >= 16) {
            Cursor cursor = doExec;
            Throwable th = (Throwable) null;
            try {
                Cursor cursor2 = cursor;
                invoke = f.invoke(doExec);
                CloseableKt.closeFinally(cursor, th);
            } finally {
            }
        } else {
            try {
                invoke = f.invoke(doExec);
            } finally {
                try {
                    doExec.close();
                } catch (Exception unused) {
                }
            }
        }
        return invoke;
    }

    public final <T> T parseSingle(RowParser<? extends T> parser) {
        T t;
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        Cursor doExec = doExec();
        if (Build.VERSION.SDK_INT >= 16) {
            Cursor cursor = doExec;
            Throwable th = (Throwable) null;
            try {
                t = (T) SqlParsersKt.parseSingle(cursor, parser);
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(cursor, th);
                InlineMarker.finallyEnd(1);
            } finally {
            }
        } else {
            try {
                t = (T) SqlParsersKt.parseSingle(doExec, parser);
            } finally {
                InlineMarker.finallyStart(1);
                try {
                    doExec.close();
                } catch (Exception unused) {
                }
                InlineMarker.finallyEnd(1);
            }
        }
        return t;
    }

    public final <T> T parseOpt(RowParser<? extends T> parser) {
        T t;
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        Cursor doExec = doExec();
        if (Build.VERSION.SDK_INT >= 16) {
            Cursor cursor = doExec;
            Throwable th = (Throwable) null;
            try {
                t = (T) SqlParsersKt.parseOpt(cursor, parser);
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(cursor, th);
                InlineMarker.finallyEnd(1);
            } finally {
            }
        } else {
            try {
                t = (T) SqlParsersKt.parseOpt(doExec, parser);
            } finally {
                InlineMarker.finallyStart(1);
                try {
                    doExec.close();
                } catch (Exception unused) {
                }
                InlineMarker.finallyEnd(1);
            }
        }
        return t;
    }

    public final <T> List<T> parseList(RowParser<? extends T> parser) {
        List<T> parseList;
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        Cursor doExec = doExec();
        if (Build.VERSION.SDK_INT >= 16) {
            Cursor cursor = doExec;
            Throwable th = (Throwable) null;
            try {
                parseList = SqlParsersKt.parseList(cursor, parser);
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(cursor, th);
                InlineMarker.finallyEnd(1);
            } finally {
            }
        } else {
            try {
                parseList = SqlParsersKt.parseList(doExec, parser);
            } finally {
                InlineMarker.finallyStart(1);
                try {
                    doExec.close();
                } catch (Exception unused) {
                }
                InlineMarker.finallyEnd(1);
            }
        }
        return parseList;
    }

    public final <T> T parseSingle(MapRowParser<? extends T> parser) {
        T t;
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        Cursor doExec = doExec();
        if (Build.VERSION.SDK_INT >= 16) {
            Cursor cursor = doExec;
            Throwable th = (Throwable) null;
            try {
                t = (T) SqlParsersKt.parseSingle(cursor, parser);
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(cursor, th);
                InlineMarker.finallyEnd(1);
            } finally {
            }
        } else {
            try {
                t = (T) SqlParsersKt.parseSingle(doExec, parser);
            } finally {
                InlineMarker.finallyStart(1);
                try {
                    doExec.close();
                } catch (Exception unused) {
                }
                InlineMarker.finallyEnd(1);
            }
        }
        return t;
    }

    public final <T> T parseOpt(MapRowParser<? extends T> parser) {
        T t;
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        Cursor doExec = doExec();
        if (Build.VERSION.SDK_INT >= 16) {
            Cursor cursor = doExec;
            Throwable th = (Throwable) null;
            try {
                t = (T) SqlParsersKt.parseOpt(cursor, parser);
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(cursor, th);
                InlineMarker.finallyEnd(1);
            } finally {
            }
        } else {
            try {
                t = (T) SqlParsersKt.parseOpt(doExec, parser);
            } finally {
                InlineMarker.finallyStart(1);
                try {
                    doExec.close();
                } catch (Exception unused) {
                }
                InlineMarker.finallyEnd(1);
            }
        }
        return t;
    }

    public final <T> List<T> parseList(MapRowParser<? extends T> parser) {
        List<T> parseList;
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        Cursor doExec = doExec();
        if (Build.VERSION.SDK_INT >= 16) {
            Cursor cursor = doExec;
            Throwable th = (Throwable) null;
            try {
                parseList = SqlParsersKt.parseList(cursor, parser);
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(cursor, th);
                InlineMarker.finallyEnd(1);
            } finally {
            }
        } else {
            try {
                parseList = SqlParsersKt.parseList(doExec, parser);
            } finally {
                InlineMarker.finallyStart(1);
                try {
                    doExec.close();
                } catch (Exception unused) {
                }
                InlineMarker.finallyEnd(1);
            }
        }
        return parseList;
    }

    public final Cursor doExec() {
        String str = this.selectionApplied ? this.selection : null;
        String[] strArr = (this.selectionApplied && this.useNativeSelection) ? this.nativeSelectionArgs : null;
        boolean z = this.distinct;
        String str2 = this.tableName;
        ArrayList<String> arrayList = this.columns;
        if (arrayList == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return execQuery(z, str2, (String[]) array, str, strArr, CollectionsKt.joinToString$default(this.groupBy, ", ", null, null, 0, null, null, 62, null), this.having, CollectionsKt.joinToString$default(this.orderBy, ", ", null, null, 0, null, null, 62, null), this.limit);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final SelectQueryBuilder distinct() {
        this.distinct = true;
        return this;
    }

    public final SelectQueryBuilder column(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.columns.add(name);
        return this;
    }

    public final SelectQueryBuilder groupBy(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.groupBy.add(value);
        return this;
    }

    public static /* synthetic */ SelectQueryBuilder orderBy$default(SelectQueryBuilder selectQueryBuilder, String str, SqlOrderDirection sqlOrderDirection, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: orderBy");
        }
        if ((i & 2) != 0) {
            sqlOrderDirection = SqlOrderDirection.ASC;
        }
        return selectQueryBuilder.orderBy(str, sqlOrderDirection);
    }

    public final SelectQueryBuilder orderBy(String value, SqlOrderDirection direction) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(direction, "direction");
        if (direction == SqlOrderDirection.DESC) {
            this.orderBy.add(value + " DESC");
        } else {
            this.orderBy.add(value);
        }
        return this;
    }

    public final SelectQueryBuilder limit(int count) {
        this.limit = String.valueOf(count);
        return this;
    }

    public final SelectQueryBuilder limit(int offset, int count) {
        this.limit = offset + ", " + count;
        return this;
    }

    public final SelectQueryBuilder columns(String... names) {
        Intrinsics.checkParameterIsNotNull(names, "names");
        CollectionsKt.addAll(this.columns, names);
        return this;
    }

    public final SelectQueryBuilder having(String having) {
        Intrinsics.checkParameterIsNotNull(having, "having");
        if (this.havingApplied) {
            throw new AnkoException("Query having was already applied.");
        }
        this.havingApplied = true;
        this.having = having;
        return this;
    }

    public final SelectQueryBuilder having(String having, Pair<String, ? extends Object>... args) {
        Intrinsics.checkParameterIsNotNull(having, "having");
        Intrinsics.checkParameterIsNotNull(args, "args");
        if (this.selectionApplied) {
            throw new AnkoException("Query having was already applied.");
        }
        this.havingApplied = true;
        this.having = DatabaseKt.applyArguments(having, (Pair<String, ? extends Object>[]) Arrays.copyOf(args, args.length));
        return this;
    }

    @Deprecated(message = "Use whereArgs(select, args) instead.", replaceWith = @ReplaceWith(expression = "whereArgs(select, args)", imports = {}))
    public final SelectQueryBuilder where(String select, Pair<String, ? extends Object>... args) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(args, "args");
        return whereArgs(select, (Pair[]) Arrays.copyOf(args, args.length));
    }

    public final SelectQueryBuilder whereArgs(String select, Pair<String, ? extends Object>... args) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(args, "args");
        if (this.selectionApplied) {
            throw new AnkoException("Query selection was already applied.");
        }
        this.selectionApplied = true;
        this.useNativeSelection = false;
        this.selection = DatabaseKt.applyArguments(select, (Pair<String, ? extends Object>[]) Arrays.copyOf(args, args.length));
        return this;
    }

    @Deprecated(message = "Use whereArgs(select) instead.", replaceWith = @ReplaceWith(expression = "whereArgs(select)", imports = {}))
    public final SelectQueryBuilder where(String select) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        return whereArgs(select);
    }

    public final SelectQueryBuilder whereArgs(String select) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        if (this.selectionApplied) {
            throw new AnkoException("Query selection was already applied.");
        }
        this.selectionApplied = true;
        this.useNativeSelection = false;
        this.selection = select;
        return this;
    }

    public final SelectQueryBuilder whereSimple(String select, String... args) {
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
    public final SelectQueryBuilder whereSupport(String select, String... args) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(args, "args");
        return whereSimple(select, (String[]) Arrays.copyOf(args, args.length));
    }
}
