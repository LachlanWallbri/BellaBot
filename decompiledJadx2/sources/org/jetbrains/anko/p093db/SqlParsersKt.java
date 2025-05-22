package org.jetbrains.anko.p093db;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParsers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001d\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002¢\u0006\u0002\u0010\u001c\u001a\u001e\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u001e\u0010\u001f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e0 *\u00020\u001b\u001a\u0018\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180 *\u00020\u001b\u001a\u0016\u0010\"\u001a\u0004\u0018\u00010\u0019*\u00020\u001b2\u0006\u0010#\u001a\u00020\fH\u0002\u001a \u0010$\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001e0 *\u00020\u001bH\u0007\u001a(\u0010%\u001a\b\u0012\u0004\u0012\u0002H'0&\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)\u001a(\u0010%\u001a\b\u0012\u0004\u0012\u0002H'0&\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001\u001a)\u0010*\u001a\u0004\u0018\u0001H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)¢\u0006\u0002\u0010+\u001a)\u0010*\u001a\u0004\u0018\u0001H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001¢\u0006\u0002\u0010,\u001a'\u0010-\u001a\u0002H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0)¢\u0006\u0002\u0010+\u001a'\u0010-\u001a\u0002H'\"\b\b\u0000\u0010'*\u00020\u0019*\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\u0001¢\u0006\u0002\u0010,\u001a\u001a\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00180 *\u00020\u001bH\u0007\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0004\"\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0004\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0004\"\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0004\"\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0004\"\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0004¨\u0006/"}, m3961d2 = {"BlobParser", "Lorg/jetbrains/anko/db/RowParser;", "", "getBlobParser", "()Lorg/jetbrains/anko/db/RowParser;", "DoubleParser", "", "getDoubleParser", "FloatParser", "", "getFloatParser", "IntParser", "", "getIntParser", "LongParser", "", "getLongParser", "ShortParser", "", "getShortParser", "StringParser", "", "getStringParser", "readColumnsArray", "", "", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)[Ljava/lang/Object;", "readColumnsMap", "", "asMapSequence", "Lkotlin/sequences/Sequence;", "asSequence", "getColumnValue", "index", "mapSequence", "parseList", "", ExifInterface.GPS_DIRECTION_TRUE, "parser", "Lorg/jetbrains/anko/db/MapRowParser;", "parseOpt", "(Landroid/database/Cursor;Lorg/jetbrains/anko/db/MapRowParser;)Ljava/lang/Object;", "(Landroid/database/Cursor;Lorg/jetbrains/anko/db/RowParser;)Ljava/lang/Object;", "parseSingle", "sequence", "sqlite-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SqlParsersKt {
    private static final RowParser<Short> ShortParser = new ScalarColumnParser(SqlParsersKt$ShortParser$1.INSTANCE);
    private static final RowParser<Integer> IntParser = new ScalarColumnParser(SqlParsersKt$IntParser$1.INSTANCE);
    private static final RowParser<Long> LongParser = new SingleColumnParser();
    private static final RowParser<Float> FloatParser = new ScalarColumnParser(SqlParsersKt$FloatParser$1.INSTANCE);
    private static final RowParser<Double> DoubleParser = new SingleColumnParser();
    private static final RowParser<String> StringParser = new SingleColumnParser();
    private static final RowParser<byte[]> BlobParser = new SingleColumnParser();

    public static final RowParser<Short> getShortParser() {
        return ShortParser;
    }

    public static final RowParser<Integer> getIntParser() {
        return IntParser;
    }

    public static final RowParser<Long> getLongParser() {
        return LongParser;
    }

    public static final RowParser<Float> getFloatParser() {
        return FloatParser;
    }

    public static final RowParser<Double> getDoubleParser() {
        return DoubleParser;
    }

    public static final RowParser<String> getStringParser() {
        return StringParser;
    }

    public static final RowParser<byte[]> getBlobParser() {
        return BlobParser;
    }

    @Deprecated(message = "Use asSequence() instead", replaceWith = @ReplaceWith(expression = "asSequence()", imports = {}))
    public static final Sequence<Object[]> sequence(Cursor receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new CursorSequence(receiver$0);
    }

    @Deprecated(message = "Use asMapSequence() instead", replaceWith = @ReplaceWith(expression = "asMapSequence()", imports = {}))
    public static final Sequence<Map<String, Object>> mapSequence(Cursor receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new CursorMapSequence(receiver$0);
    }

    public static final Sequence<Object[]> asSequence(Cursor receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new CursorSequence(receiver$0);
    }

    public static final Sequence<Map<String, Object>> asMapSequence(Cursor receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new CursorMapSequence(receiver$0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Object getColumnValue(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        int type = cursor.getType(i);
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type != 4) {
            return null;
        }
        return (Serializable) cursor.getBlob(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] readColumnsArray(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        Object[] objArr = new Object[columnCount];
        int i = columnCount - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                objArr[i2] = getColumnValue(cursor, i2);
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, Object> readColumnsMap(Cursor cursor) {
        int columnCount = cursor.getColumnCount();
        HashMap hashMap = new HashMap();
        int i = columnCount - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                hashMap.put(cursor.getColumnName(i2), getColumnValue(cursor, i2));
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return hashMap;
    }

    public static final <T> T parseSingle(Cursor receiver$0, RowParser<? extends T> parser) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        if (Build.VERSION.SDK_INT < 16) {
            try {
                if (receiver$0.getCount() != 1) {
                    throw new SQLiteException("parseSingle accepts only cursors with a single entry");
                }
                receiver$0.moveToFirst();
                return parser.parseRow(readColumnsArray(receiver$0));
            } finally {
                try {
                    receiver$0.close();
                } catch (Exception unused) {
                }
            }
        }
        Cursor cursor = receiver$0;
        Throwable th = (Throwable) null;
        try {
            Cursor cursor2 = cursor;
            if (receiver$0.getCount() != 1) {
                throw new SQLiteException("parseSingle accepts only cursors with a single entry");
            }
            receiver$0.moveToFirst();
            T parseRow = parser.parseRow(readColumnsArray(receiver$0));
            CloseableKt.closeFinally(cursor, th);
            return parseRow;
        } finally {
        }
    }

    public static final <T> T parseOpt(Cursor receiver$0, RowParser<? extends T> parser) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        if (Build.VERSION.SDK_INT < 16) {
            try {
                if (receiver$0.getCount() > 1) {
                    throw new SQLiteException("parseSingle accepts only cursors with a single entry or empty cursors");
                }
                if (receiver$0.getCount() == 0) {
                    return null;
                }
                receiver$0.moveToFirst();
                T parseRow = parser.parseRow(readColumnsArray(receiver$0));
                try {
                    receiver$0.close();
                } catch (Exception unused) {
                }
                return parseRow;
            } finally {
                try {
                    receiver$0.close();
                } catch (Exception unused2) {
                }
            }
        }
        Cursor cursor = receiver$0;
        Throwable th = (Throwable) null;
        try {
            Cursor cursor2 = cursor;
            if (receiver$0.getCount() > 1) {
                throw new SQLiteException("parseSingle accepts only cursors with a single entry or empty cursors");
            }
            if (receiver$0.getCount() == 0) {
                CloseableKt.closeFinally(cursor, th);
                return null;
            }
            receiver$0.moveToFirst();
            T parseRow2 = parser.parseRow(readColumnsArray(receiver$0));
            CloseableKt.closeFinally(cursor, th);
            return parseRow2;
        } finally {
        }
    }

    public static final <T> List<T> parseList(Cursor receiver$0, RowParser<? extends T> parser) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        if (Build.VERSION.SDK_INT < 16) {
            try {
                ArrayList arrayList = new ArrayList(receiver$0.getCount());
                receiver$0.moveToFirst();
                while (!receiver$0.isAfterLast()) {
                    arrayList.add(parser.parseRow(readColumnsArray(receiver$0)));
                    receiver$0.moveToNext();
                }
                return arrayList;
            } finally {
                try {
                    receiver$0.close();
                } catch (Exception unused) {
                }
            }
        }
        Cursor cursor = receiver$0;
        Throwable th = (Throwable) null;
        try {
            Cursor cursor2 = cursor;
            ArrayList arrayList2 = new ArrayList(receiver$0.getCount());
            receiver$0.moveToFirst();
            while (!receiver$0.isAfterLast()) {
                arrayList2.add(parser.parseRow(readColumnsArray(receiver$0)));
                receiver$0.moveToNext();
            }
            ArrayList arrayList3 = arrayList2;
            CloseableKt.closeFinally(cursor, th);
            return arrayList3;
        } finally {
        }
    }

    public static final <T> T parseSingle(Cursor receiver$0, MapRowParser<? extends T> parser) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        if (Build.VERSION.SDK_INT < 16) {
            try {
                if (receiver$0.getCount() != 1) {
                    throw new SQLiteException("parseSingle accepts only cursors with getCount() == 1");
                }
                receiver$0.moveToFirst();
                return parser.parseRow(readColumnsMap(receiver$0));
            } finally {
                try {
                    receiver$0.close();
                } catch (Exception unused) {
                }
            }
        }
        Cursor cursor = receiver$0;
        Throwable th = (Throwable) null;
        try {
            Cursor cursor2 = cursor;
            if (receiver$0.getCount() != 1) {
                throw new SQLiteException("parseSingle accepts only cursors with getCount() == 1");
            }
            receiver$0.moveToFirst();
            T parseRow = parser.parseRow(readColumnsMap(receiver$0));
            CloseableKt.closeFinally(cursor, th);
            return parseRow;
        } finally {
        }
    }

    public static final <T> T parseOpt(Cursor receiver$0, MapRowParser<? extends T> parser) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        if (Build.VERSION.SDK_INT < 16) {
            try {
                if (receiver$0.getCount() > 1) {
                    throw new SQLiteException("parseSingle accepts only cursors with getCount() == 1 or empty cursors");
                }
                if (receiver$0.getCount() == 0) {
                    return null;
                }
                receiver$0.moveToFirst();
                T parseRow = parser.parseRow(readColumnsMap(receiver$0));
                try {
                    receiver$0.close();
                } catch (Exception unused) {
                }
                return parseRow;
            } finally {
                try {
                    receiver$0.close();
                } catch (Exception unused2) {
                }
            }
        }
        Cursor cursor = receiver$0;
        Throwable th = (Throwable) null;
        try {
            Cursor cursor2 = cursor;
            if (receiver$0.getCount() > 1) {
                throw new SQLiteException("parseSingle accepts only cursors with getCount() == 1 or empty cursors");
            }
            if (receiver$0.getCount() == 0) {
                CloseableKt.closeFinally(cursor, th);
                return null;
            }
            receiver$0.moveToFirst();
            T parseRow2 = parser.parseRow(readColumnsMap(receiver$0));
            CloseableKt.closeFinally(cursor, th);
            return parseRow2;
        } finally {
        }
    }

    public static final <T> List<T> parseList(Cursor receiver$0, MapRowParser<? extends T> parser) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        if (Build.VERSION.SDK_INT < 16) {
            try {
                ArrayList arrayList = new ArrayList(receiver$0.getCount());
                receiver$0.moveToFirst();
                while (!receiver$0.isAfterLast()) {
                    arrayList.add(parser.parseRow(readColumnsMap(receiver$0)));
                    receiver$0.moveToNext();
                }
                return arrayList;
            } finally {
                try {
                    receiver$0.close();
                } catch (Exception unused) {
                }
            }
        }
        Cursor cursor = receiver$0;
        Throwable th = (Throwable) null;
        try {
            Cursor cursor2 = cursor;
            ArrayList arrayList2 = new ArrayList(receiver$0.getCount());
            receiver$0.moveToFirst();
            while (!receiver$0.isAfterLast()) {
                arrayList2.add(parser.parseRow(readColumnsMap(receiver$0)));
                receiver$0.moveToNext();
            }
            ArrayList arrayList3 = arrayList2;
            CloseableKt.closeFinally(cursor, th);
            return arrayList3;
        } finally {
        }
    }
}
