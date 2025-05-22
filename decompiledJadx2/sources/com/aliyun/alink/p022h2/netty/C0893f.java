package com.aliyun.alink.p022h2.netty;

import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NettyMessageFormatter.java */
/* renamed from: com.aliyun.alink.h2.netty.f */
/* loaded from: classes.dex */
final class C0893f {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C0888a m258a(String str, Object obj) {
        return m260a(str, new Object[]{obj});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C0888a m259a(String str, Object obj, Object obj2) {
        return m260a(str, new Object[]{obj, obj2});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C0888a m260a(String str, Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            int length = objArr.length - 1;
            Object obj = objArr[length];
            Throwable th = obj instanceof Throwable ? (Throwable) obj : null;
            if (str == null) {
                return new C0888a((String) null, th);
            }
            int indexOf = str.indexOf("{}");
            if (indexOf == -1) {
                return new C0888a(str, th);
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
                    m262a(sb, objArr[i2], (Set<Object[]>) null);
                    i2++;
                    if (i2 > length) {
                        break;
                    }
                } else {
                    sb.append("{}");
                }
                indexOf = str.indexOf("{}", i);
            } while (indexOf != -1);
            sb.append((CharSequence) str, i, str.length());
            return new C0888a(sb.toString(), i2 <= length ? th : null);
        }
        return new C0888a(str, (Throwable) null);
    }

    /* renamed from: a */
    private static void m262a(StringBuilder sb, Object obj, Set<Object[]> set) {
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
                if (cls != Integer.class && cls != Short.class && cls != Byte.class) {
                    if (cls == Double.class) {
                        sb.append(((Double) obj).doubleValue());
                        return;
                    } else if (cls == Float.class) {
                        sb.append(((Float) obj).floatValue());
                        return;
                    } else {
                        m261a(sb, obj);
                        return;
                    }
                }
                sb.append(((Number) obj).intValue());
                return;
            }
            m261a(sb, obj);
            return;
        }
        sb.append('[');
        if (cls == boolean[].class) {
            m271a(sb, (boolean[]) obj);
        } else if (cls == byte[].class) {
            m263a(sb, (byte[]) obj);
        } else if (cls == char[].class) {
            m264a(sb, (char[]) obj);
        } else if (cls == short[].class) {
            m270a(sb, (short[]) obj);
        } else if (cls == int[].class) {
            m267a(sb, (int[]) obj);
        } else if (cls == long[].class) {
            m268a(sb, (long[]) obj);
        } else if (cls == float[].class) {
            m266a(sb, (float[]) obj);
        } else if (cls == double[].class) {
            m265a(sb, (double[]) obj);
        } else {
            m269a(sb, (Object[]) obj, set);
        }
        sb.append(']');
    }

    /* renamed from: a */
    private static void m261a(StringBuilder sb, Object obj) {
        try {
            sb.append(obj.toString());
        } catch (Throwable th) {
            System.err.println("SLF4J: Failed toString() invocation on an object of type [" + obj.getClass().getName() + ']');
            th.printStackTrace();
            sb.append("[FAILED toString()]");
        }
    }

    /* renamed from: a */
    private static void m269a(StringBuilder sb, Object[] objArr, Set<Object[]> set) {
        if (objArr.length != 0) {
            if (set == null) {
                set = new HashSet<>(objArr.length);
            }
            if (set.add(objArr)) {
                m262a(sb, objArr[0], set);
                for (int i = 1; i < objArr.length; i++) {
                    sb.append(", ");
                    m262a(sb, objArr[i], set);
                }
                set.remove(objArr);
                return;
            }
            sb.append("...");
        }
    }

    /* renamed from: a */
    private static void m271a(StringBuilder sb, boolean[] zArr) {
        if (zArr.length != 0) {
            sb.append(zArr[0]);
            for (int i = 1; i < zArr.length; i++) {
                sb.append(", ");
                sb.append(zArr[i]);
            }
        }
    }

    /* renamed from: a */
    private static void m263a(StringBuilder sb, byte[] bArr) {
        if (bArr.length != 0) {
            sb.append((int) bArr[0]);
            for (int i = 1; i < bArr.length; i++) {
                sb.append(", ");
                sb.append((int) bArr[i]);
            }
        }
    }

    /* renamed from: a */
    private static void m264a(StringBuilder sb, char[] cArr) {
        if (cArr.length != 0) {
            sb.append(cArr[0]);
            for (int i = 1; i < cArr.length; i++) {
                sb.append(", ");
                sb.append(cArr[i]);
            }
        }
    }

    /* renamed from: a */
    private static void m270a(StringBuilder sb, short[] sArr) {
        if (sArr.length != 0) {
            sb.append((int) sArr[0]);
            for (int i = 1; i < sArr.length; i++) {
                sb.append(", ");
                sb.append((int) sArr[i]);
            }
        }
    }

    /* renamed from: a */
    private static void m267a(StringBuilder sb, int[] iArr) {
        if (iArr.length != 0) {
            sb.append(iArr[0]);
            for (int i = 1; i < iArr.length; i++) {
                sb.append(", ");
                sb.append(iArr[i]);
            }
        }
    }

    /* renamed from: a */
    private static void m268a(StringBuilder sb, long[] jArr) {
        if (jArr.length != 0) {
            sb.append(jArr[0]);
            for (int i = 1; i < jArr.length; i++) {
                sb.append(", ");
                sb.append(jArr[i]);
            }
        }
    }

    /* renamed from: a */
    private static void m266a(StringBuilder sb, float[] fArr) {
        if (fArr.length != 0) {
            sb.append(fArr[0]);
            for (int i = 1; i < fArr.length; i++) {
                sb.append(", ");
                sb.append(fArr[i]);
            }
        }
    }

    /* renamed from: a */
    private static void m265a(StringBuilder sb, double[] dArr) {
        if (dArr.length != 0) {
            sb.append(dArr[0]);
            for (int i = 1; i < dArr.length; i++) {
                sb.append(", ");
                sb.append(dArr[i]);
            }
        }
    }
}
