package io.grpc.netty.shaded.io.netty.util.internal.logging;

import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class MessageFormatter {
    private static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormattingTuple format(String str, Object obj) {
        return arrayFormat(str, new Object[]{obj});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormattingTuple format(String str, Object obj, Object obj2) {
        return arrayFormat(str, new Object[]{obj, obj2});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FormattingTuple arrayFormat(String str, Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return new FormattingTuple(str, null);
        }
        int length = objArr.length - 1;
        Object obj = objArr[length];
        Throwable th = obj instanceof Throwable ? (Throwable) obj : null;
        if (str == null) {
            return new FormattingTuple(null, th);
        }
        int indexOf = str.indexOf(DELIM_STR);
        if (indexOf == -1) {
            return new FormattingTuple(str, th);
        }
        StringBuilder sb = new StringBuilder(str.length() + 50);
        int i = 0;
        int i2 = 0;
        do {
            boolean z = indexOf == 0 || str.charAt(indexOf + (-1)) != '\\';
            if (z) {
                sb.append((CharSequence) str, i, indexOf);
            } else {
                sb.append((CharSequence) str, i, indexOf - 1);
                z = indexOf >= 2 && str.charAt(indexOf + (-2)) == '\\';
            }
            i = indexOf + 2;
            if (z) {
                deeplyAppendParameter(sb, objArr[i2], null);
                i2++;
                if (i2 > length) {
                    break;
                }
            } else {
                sb.append(DELIM_STR);
            }
            indexOf = str.indexOf(DELIM_STR, i);
        } while (indexOf != -1);
        sb.append((CharSequence) str, i, str.length());
        return new FormattingTuple(sb.toString(), i2 <= length ? th : null);
    }

    private static void deeplyAppendParameter(StringBuilder sb, Object obj, Set<Object[]> set) {
        if (obj == null) {
            sb.append("null");
            return;
        }
        Class<?> cls = obj.getClass();
        if (!cls.isArray()) {
            if (Number.class.isAssignableFrom(cls)) {
                if (cls == Long.class) {
                    sb.append(((Long) obj).longValue());
                    return;
                }
                if (cls == Integer.class || cls == Short.class || cls == Byte.class) {
                    sb.append(((Number) obj).intValue());
                    return;
                }
                if (cls == Double.class) {
                    sb.append(((Double) obj).doubleValue());
                    return;
                } else if (cls == Float.class) {
                    sb.append(((Float) obj).floatValue());
                    return;
                } else {
                    safeObjectAppend(sb, obj);
                    return;
                }
            }
            safeObjectAppend(sb, obj);
            return;
        }
        sb.append('[');
        if (cls == boolean[].class) {
            booleanArrayAppend(sb, (boolean[]) obj);
        } else if (cls == byte[].class) {
            byteArrayAppend(sb, (byte[]) obj);
        } else if (cls == char[].class) {
            charArrayAppend(sb, (char[]) obj);
        } else if (cls == short[].class) {
            shortArrayAppend(sb, (short[]) obj);
        } else if (cls == int[].class) {
            intArrayAppend(sb, (int[]) obj);
        } else if (cls == long[].class) {
            longArrayAppend(sb, (long[]) obj);
        } else if (cls == float[].class) {
            floatArrayAppend(sb, (float[]) obj);
        } else if (cls == double[].class) {
            doubleArrayAppend(sb, (double[]) obj);
        } else {
            objectArrayAppend(sb, (Object[]) obj, set);
        }
        sb.append(']');
    }

    private static void safeObjectAppend(StringBuilder sb, Object obj) {
        try {
            sb.append(obj.toString());
        } catch (Throwable th) {
            System.err.println("SLF4J: Failed toString() invocation on an object of type [" + obj.getClass().getName() + ']');
            th.printStackTrace();
            sb.append("[FAILED toString()]");
        }
    }

    private static void objectArrayAppend(StringBuilder sb, Object[] objArr, Set<Object[]> set) {
        if (objArr.length == 0) {
            return;
        }
        if (set == null) {
            set = new HashSet<>(objArr.length);
        }
        if (set.add(objArr)) {
            deeplyAppendParameter(sb, objArr[0], set);
            for (int i = 1; i < objArr.length; i++) {
                sb.append(", ");
                deeplyAppendParameter(sb, objArr[i], set);
            }
            set.remove(objArr);
            return;
        }
        sb.append("...");
    }

    private static void booleanArrayAppend(StringBuilder sb, boolean[] zArr) {
        if (zArr.length == 0) {
            return;
        }
        sb.append(zArr[0]);
        for (int i = 1; i < zArr.length; i++) {
            sb.append(", ");
            sb.append(zArr[i]);
        }
    }

    private static void byteArrayAppend(StringBuilder sb, byte[] bArr) {
        if (bArr.length == 0) {
            return;
        }
        sb.append((int) bArr[0]);
        for (int i = 1; i < bArr.length; i++) {
            sb.append(", ");
            sb.append((int) bArr[i]);
        }
    }

    private static void charArrayAppend(StringBuilder sb, char[] cArr) {
        if (cArr.length == 0) {
            return;
        }
        sb.append(cArr[0]);
        for (int i = 1; i < cArr.length; i++) {
            sb.append(", ");
            sb.append(cArr[i]);
        }
    }

    private static void shortArrayAppend(StringBuilder sb, short[] sArr) {
        if (sArr.length == 0) {
            return;
        }
        sb.append((int) sArr[0]);
        for (int i = 1; i < sArr.length; i++) {
            sb.append(", ");
            sb.append((int) sArr[i]);
        }
    }

    private static void intArrayAppend(StringBuilder sb, int[] iArr) {
        if (iArr.length == 0) {
            return;
        }
        sb.append(iArr[0]);
        for (int i = 1; i < iArr.length; i++) {
            sb.append(", ");
            sb.append(iArr[i]);
        }
    }

    private static void longArrayAppend(StringBuilder sb, long[] jArr) {
        if (jArr.length == 0) {
            return;
        }
        sb.append(jArr[0]);
        for (int i = 1; i < jArr.length; i++) {
            sb.append(", ");
            sb.append(jArr[i]);
        }
    }

    private static void floatArrayAppend(StringBuilder sb, float[] fArr) {
        if (fArr.length == 0) {
            return;
        }
        sb.append(fArr[0]);
        for (int i = 1; i < fArr.length; i++) {
            sb.append(", ");
            sb.append(fArr[i]);
        }
    }

    private static void doubleArrayAppend(StringBuilder sb, double[] dArr) {
        if (dArr.length == 0) {
            return;
        }
        sb.append(dArr[0]);
        for (int i = 1; i < dArr.length; i++) {
            sb.append(", ");
            sb.append(dArr[i]);
        }
    }

    private MessageFormatter() {
    }
}
