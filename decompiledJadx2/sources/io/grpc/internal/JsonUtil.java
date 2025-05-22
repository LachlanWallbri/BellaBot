package io.grpc.internal;

import com.google.common.math.LongMath;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class JsonUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long DURATION_SECONDS_MAX = 315576000000L;
    private static final long DURATION_SECONDS_MIN = -315576000000L;
    private static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);

    private static long saturatedAdd(long j, long j2) {
        long j3 = j + j2;
        return (((j2 ^ j) > 0L ? 1 : ((j2 ^ j) == 0L ? 0 : -1)) < 0) | ((j ^ j3) >= 0) ? j3 : ((j3 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @Nullable
    public static List<?> getList(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (!(obj instanceof List)) {
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not List", obj, str, map));
        }
        return (List) obj;
    }

    @Nullable
    public static List<Map<String, ?>> getListOfObjects(Map<String, ?> map, String str) {
        List<?> list = getList(map, str);
        if (list == null) {
            return null;
        }
        return checkObjectList(list);
    }

    @Nullable
    public static List<String> getListOfStrings(Map<String, ?> map, String str) {
        List<?> list = getList(map, str);
        if (list == null) {
            return null;
        }
        return checkStringList(list);
    }

    @Nullable
    public static Map<String, ?> getObject(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (!(obj instanceof Map)) {
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not object", obj, str, map));
        }
        return (Map) obj;
    }

    @Nullable
    public static Double getNumber(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (!(obj instanceof Double)) {
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Double", obj, str, map));
        }
        return (Double) obj;
    }

    public static Integer getNumberAsInteger(Map<String, ?> map, String str) {
        Double number = getNumber(map, str);
        if (number == null) {
            return null;
        }
        int intValue = number.intValue();
        if (intValue != number.doubleValue()) {
            throw new ClassCastException("Number expected to be integer: " + number);
        }
        return Integer.valueOf(intValue);
    }

    public static Long getNumberAsLong(Map<String, ?> map, String str) {
        Double number = getNumber(map, str);
        if (number == null) {
            return null;
        }
        long longValue = number.longValue();
        if (longValue != number.doubleValue()) {
            throw new ClassCastException("Number expected to be long: " + number);
        }
        return Long.valueOf(longValue);
    }

    @Nullable
    public static String getString(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (!(obj instanceof String)) {
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not String", obj, str, map));
        }
        return (String) obj;
    }

    public static Long getStringAsDuration(Map<String, ?> map, String str) {
        String string = getString(map, str);
        if (string == null) {
            return null;
        }
        try {
            return Long.valueOf(parseDuration(string));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static Boolean getBoolean(Map<String, ?> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (!(obj instanceof Boolean)) {
            throw new ClassCastException(String.format("value '%s' for key '%s' in '%s' is not Boolean", obj, str, map));
        }
        return (Boolean) obj;
    }

    public static List<Map<String, ?>> checkObjectList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i) instanceof Map)) {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not object", list.get(i), Integer.valueOf(i), list));
            }
        }
        return list;
    }

    public static List<String> checkStringList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i) instanceof String)) {
                throw new ClassCastException(String.format("value '%s' for idx %d in '%s' is not string", list.get(i), Integer.valueOf(i), list));
            }
        }
        return list;
    }

    private static long parseDuration(String str) throws ParseException {
        boolean z;
        String str2;
        if (str.isEmpty() || str.charAt(str.length() - 1) != 's') {
            throw new ParseException("Invalid duration string: " + str, 0);
        }
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        String substring = str.substring(0, str.length() - 1);
        int indexOf = substring.indexOf(46);
        if (indexOf != -1) {
            str2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        } else {
            str2 = "";
        }
        long parseLong = Long.parseLong(substring);
        int parseNanos = str2.isEmpty() ? 0 : parseNanos(str2);
        if (parseLong < 0) {
            throw new ParseException("Invalid duration string: " + str, 0);
        }
        if (z) {
            parseLong = -parseLong;
            parseNanos = -parseNanos;
        }
        try {
            return normalizedDuration(parseLong, parseNanos);
        } catch (IllegalArgumentException unused) {
            throw new ParseException("Duration value is out of range.", 0);
        }
    }

    private static int parseNanos(String str) throws ParseException {
        int i = 0;
        for (int i2 = 0; i2 < 9; i2++) {
            i *= 10;
            if (i2 < str.length()) {
                if (str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                    throw new ParseException("Invalid nanoseconds.", 0);
                }
                i += str.charAt(i2) - '0';
            }
        }
        return i;
    }

    private static long normalizedDuration(long j, int i) {
        long j2 = i;
        long j3 = NANOS_PER_SECOND;
        if (j2 <= (-j3) || j2 >= j3) {
            j = LongMath.checkedAdd(j, j2 / NANOS_PER_SECOND);
            i = (int) (j2 % NANOS_PER_SECOND);
        }
        if (j > 0 && i < 0) {
            i = (int) (i + NANOS_PER_SECOND);
            j--;
        }
        if (j < 0 && i > 0) {
            i = (int) (i - NANOS_PER_SECOND);
            j++;
        }
        if (!durationIsValid(j, i)) {
            throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", Long.valueOf(j), Integer.valueOf(i)));
        }
        return saturatedAdd(TimeUnit.SECONDS.toNanos(j), i);
    }

    private static boolean durationIsValid(long j, int i) {
        if (j >= -315576000000L && j <= 315576000000L) {
            long j2 = i;
            if (j2 >= -999999999 && j2 < NANOS_PER_SECOND) {
                if (j >= 0 && i >= 0) {
                    return true;
                }
                if (j <= 0 && i <= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
