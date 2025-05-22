package org.jetbrains.anko.p093db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Database.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aA\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00070\u0006\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u0000¢\u0006\u0002\u0010\t\u001a$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\nH\u0000\u001aG\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010\u0014\u001aM\u0010\u0015\u001a\u00020\f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00112*\u0010\u0013\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00160\u00070\u0006\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00160\u0007¢\u0006\u0002\u0010\u0017\u001aM\u0010\u0018\u001a\u00020\u0019*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00070\u0006\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\u001a\u001a\u001c\u0010\u001b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0011\u001a\u001c\u0010\u001d\u001a\u00020\f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0011\u001aG\u0010\u001e\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001aG\u0010\"\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001aG\u0010#\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001aG\u0010$\u001a\u00020\u001f*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010!\u001a\u0012\u0010%\u001a\u00020&*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0003\u001a+\u0010%\u001a\u00020&*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010'\u001a'\u0010(\u001a\u00020)*\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006H\u0000¢\u0006\u0002\u0010*\u001a#\u0010+\u001a\u00020\f*\u00020\r2\u0017\u0010,\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\f0-¢\u0006\u0002\b.\u001aG\u0010/\u001a\u000200*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032.\u0010 \u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\"\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u00101\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"ARG_PATTERN", "Ljava/util/regex/Pattern;", "applyArguments", "", "whereClause", "args", "", "Lkotlin/Pair;", "", "(Ljava/lang/String;[Lkotlin/Pair;)Ljava/lang/String;", "", "createIndex", "", "Landroid/database/sqlite/SQLiteDatabase;", "indexName", "tableName", "unique", "", "ifNotExists", "columns", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;ZZ[Ljava/lang/String;)V", "createTable", "Lorg/jetbrains/anko/db/SqlType;", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Z[Lkotlin/Pair;)V", RequestParameters.SUBRESOURCE_DELETE, "", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;[Lkotlin/Pair;)I", "dropIndex", "ifExists", "dropTable", "insert", "", "values", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Lkotlin/Pair;)J", "insertOrThrow", "replace", "replaceOrThrow", "select", "Lorg/jetbrains/anko/db/SelectQueryBuilder;", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/anko/db/SelectQueryBuilder;", "toContentValues", "Landroid/content/ContentValues;", "([Lkotlin/Pair;)Landroid/content/ContentValues;", "transaction", "code", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "update", "Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Lkotlin/Pair;)Lorg/jetbrains/anko/db/UpdateQueryBuilder;", "sqlite-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class DatabaseKt {
    private static final Pattern ARG_PATTERN;

    public static final long insert(SQLiteDatabase receiver$0, String tableName, Pair<String, ? extends Object>... values) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return receiver$0.insert(tableName, null, toContentValues(values));
    }

    public static final long insertOrThrow(SQLiteDatabase receiver$0, String tableName, Pair<String, ? extends Object>... values) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return receiver$0.insertOrThrow(tableName, null, toContentValues(values));
    }

    public static final long replace(SQLiteDatabase receiver$0, String tableName, Pair<String, ? extends Object>... values) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return receiver$0.replace(tableName, null, toContentValues(values));
    }

    public static final long replaceOrThrow(SQLiteDatabase receiver$0, String tableName, Pair<String, ? extends Object>... values) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return receiver$0.replaceOrThrow(tableName, null, toContentValues(values));
    }

    public static final void transaction(SQLiteDatabase receiver$0, Function1<? super SQLiteDatabase, Unit> code) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(code, "code");
        try {
            receiver$0.beginTransaction();
            code.invoke(receiver$0);
            receiver$0.setTransactionSuccessful();
        } catch (TransactionAbortException unused) {
        } catch (Throwable th) {
            receiver$0.endTransaction();
            throw th;
        }
        receiver$0.endTransaction();
    }

    public static final SelectQueryBuilder select(SQLiteDatabase receiver$0, String tableName) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        return new AndroidSdkDatabaseSelectQueryBuilder(receiver$0, tableName);
    }

    public static final SelectQueryBuilder select(SQLiteDatabase receiver$0, String tableName, String... columns) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(columns, "columns");
        AndroidSdkDatabaseSelectQueryBuilder androidSdkDatabaseSelectQueryBuilder = new AndroidSdkDatabaseSelectQueryBuilder(receiver$0, tableName);
        androidSdkDatabaseSelectQueryBuilder.columns((String[]) Arrays.copyOf(columns, columns.length));
        return androidSdkDatabaseSelectQueryBuilder;
    }

    public static final UpdateQueryBuilder update(SQLiteDatabase receiver$0, String tableName, Pair<String, ? extends Object>... values) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return new AndroidSdkDatabaseUpdateQueryBuilder(receiver$0, tableName, values);
    }

    public static /* synthetic */ int delete$default(SQLiteDatabase sQLiteDatabase, String str, String str2, Pair[] pairArr, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return delete(sQLiteDatabase, str, str2, pairArr);
    }

    public static final int delete(SQLiteDatabase receiver$0, String tableName, String whereClause, Pair<String, ? extends Object>... args) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(whereClause, "whereClause");
        Intrinsics.checkParameterIsNotNull(args, "args");
        return receiver$0.delete(tableName, applyArguments(whereClause, (Pair<String, ? extends Object>[]) Arrays.copyOf(args, args.length)), null);
    }

    public static /* synthetic */ void createTable$default(SQLiteDatabase sQLiteDatabase, String str, boolean z, Pair[] pairArr, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        createTable(sQLiteDatabase, str, z, pairArr);
    }

    public static final void createTable(SQLiteDatabase receiver$0, String tableName, boolean z, Pair<String, ? extends SqlType>... columns) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(columns, "columns");
        String replace$default = StringsKt.replace$default(tableName, "`", "``", false, 4, (Object) null);
        String str = z ? "IF NOT EXISTS" : "";
        ArrayList arrayList = new ArrayList(columns.length);
        for (Pair<String, ? extends SqlType> pair : columns) {
            arrayList.add(pair.getFirst() + ' ' + pair.getSecond().render());
        }
        receiver$0.execSQL(CollectionsKt.joinToString$default(arrayList, ", ", "CREATE TABLE " + str + " `" + replace$default + "`(", ");", 0, null, null, 56, null));
    }

    public static /* synthetic */ void dropTable$default(SQLiteDatabase sQLiteDatabase, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        dropTable(sQLiteDatabase, str, z);
    }

    public static final void dropTable(SQLiteDatabase receiver$0, String tableName, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        String replace$default = StringsKt.replace$default(tableName, "`", "``", false, 4, (Object) null);
        receiver$0.execSQL("DROP TABLE " + (z ? "IF EXISTS" : "") + " `" + replace$default + "`;");
    }

    public static final void createIndex(SQLiteDatabase receiver$0, String indexName, String tableName, boolean z, boolean z2, String... columns) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(indexName, "indexName");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(columns, "columns");
        String replace$default = StringsKt.replace$default(indexName, "`", "``", false, 4, (Object) null);
        String replace$default2 = StringsKt.replace$default(tableName, "`", "``", false, 4, (Object) null);
        String str = z2 ? "IF NOT EXISTS" : "";
        receiver$0.execSQL(ArraysKt.joinToString$default(columns, ",", "CREATE " + (z ? "UNIQUE" : "") + " INDEX " + str + " `" + replace$default + "` ON `" + replace$default2 + "`(", ");", 0, (CharSequence) null, (Function1) null, 56, (Object) null));
    }

    public static /* synthetic */ void dropIndex$default(SQLiteDatabase sQLiteDatabase, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        dropIndex(sQLiteDatabase, str, z);
    }

    public static final void dropIndex(SQLiteDatabase receiver$0, String indexName, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(indexName, "indexName");
        String replace$default = StringsKt.replace$default(indexName, "`", "``", false, 4, (Object) null);
        receiver$0.execSQL("DROP INDEX " + (z ? "IF EXISTS" : "") + " `" + replace$default + "`;");
    }

    static {
        Pattern compile = Pattern.compile("([^\\\\])\\{([^{}]+)\\}");
        Intrinsics.checkExpressionValueIsNotNull(compile, "Pattern.compile(\"([^\\\\\\\\])\\\\{([^{}]+)\\\\}\")");
        ARG_PATTERN = compile;
    }

    public static final String applyArguments(String whereClause, Pair<String, ? extends Object>... args) {
        Intrinsics.checkParameterIsNotNull(whereClause, "whereClause");
        Intrinsics.checkParameterIsNotNull(args, "args");
        HashMap hashMap = new HashMap();
        for (Pair<String, ? extends Object> pair : args) {
            hashMap.put(pair.getFirst(), pair.getSecond());
        }
        return applyArguments(whereClause, hashMap);
    }

    public static final String applyArguments(String whereClause, Map<String, ? extends Object> args) {
        String obj;
        Intrinsics.checkParameterIsNotNull(whereClause, "whereClause");
        Intrinsics.checkParameterIsNotNull(args, "args");
        Matcher matcher = ARG_PATTERN.matcher(whereClause);
        StringBuffer stringBuffer = new StringBuffer(whereClause.length());
        while (matcher.find()) {
            String group = matcher.group(2);
            Object obj2 = args.get(group);
            if (obj2 == null) {
                throw new IllegalStateException("Can't find a value for key " + group);
            }
            if ((obj2 instanceof Integer) || (obj2 instanceof Long) || (obj2 instanceof Byte) || (obj2 instanceof Short)) {
                obj = obj2.toString();
            } else if (obj2 instanceof Boolean) {
                obj = ((Boolean) obj2).booleanValue() ? "1" : "0";
            } else if ((obj2 instanceof Float) || (obj2 instanceof Double)) {
                obj = obj2.toString();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(String.valueOf('\'') + StringsKt.replace$default(obj2.toString(), "'", "''", false, 4, (Object) null));
                sb.append('\'');
                obj = sb.toString();
            }
            matcher.appendReplacement(stringBuffer, matcher.group(1) + obj);
        }
        matcher.appendTail(stringBuffer);
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "buffer.toString()");
        return stringBuffer2;
    }

    public static final ContentValues toContentValues(Pair<String, ? extends Object>[] receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ContentValues contentValues = new ContentValues();
        for (Pair<String, ? extends Object> pair : receiver$0) {
            String component1 = pair.component1();
            Object component2 = pair.component2();
            if (component2 == null) {
                contentValues.putNull(component1);
            } else if (component2 instanceof Boolean) {
                contentValues.put(component1, (Boolean) component2);
            } else if (component2 instanceof Byte) {
                contentValues.put(component1, (Byte) component2);
            } else if (component2 instanceof byte[]) {
                contentValues.put(component1, (byte[]) component2);
            } else if (component2 instanceof Double) {
                contentValues.put(component1, (Double) component2);
            } else if (component2 instanceof Float) {
                contentValues.put(component1, (Float) component2);
            } else if (component2 instanceof Integer) {
                contentValues.put(component1, (Integer) component2);
            } else if (component2 instanceof Long) {
                contentValues.put(component1, (Long) component2);
            } else if (component2 instanceof Short) {
                contentValues.put(component1, (Short) component2);
            } else {
                if (!(component2 instanceof String)) {
                    throw new IllegalArgumentException("Non-supported value type: " + component2.getClass().getName());
                }
                contentValues.put(component1, (String) component2);
            }
        }
        return contentValues;
    }
}
