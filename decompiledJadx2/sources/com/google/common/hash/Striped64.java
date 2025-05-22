package com.google.common.hash;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import org.mozilla.javascript.ES6Iterator;
import sun.misc.Unsafe;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
abstract class Striped64 extends Number {
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    volatile transient long base;
    volatile transient int busy;
    volatile transient Cell[] cells;
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    static final Random rng = new Random();
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    /* renamed from: fn */
    abstract long mo738fn(long j, long j2);

    static /* synthetic */ Unsafe access$000() {
        return getUnsafe();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;

        /* renamed from: p0 */
        volatile long f1980p0;

        /* renamed from: p1 */
        volatile long f1981p1;

        /* renamed from: p2 */
        volatile long f1982p2;

        /* renamed from: p3 */
        volatile long f1983p3;

        /* renamed from: p4 */
        volatile long f1984p4;

        /* renamed from: p5 */
        volatile long f1985p5;

        /* renamed from: p6 */
        volatile long f1986p6;

        /* renamed from: q0 */
        volatile long f1987q0;

        /* renamed from: q1 */
        volatile long f1988q1;

        /* renamed from: q2 */
        volatile long f1989q2;

        /* renamed from: q3 */
        volatile long f1990q3;

        /* renamed from: q4 */
        volatile long f1991q4;

        /* renamed from: q5 */
        volatile long f1992q5;

        /* renamed from: q6 */
        volatile long f1993q6;
        volatile long value;

        Cell(long j) {
            this.value = j;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean cas(long j, long j2) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j, j2);
        }

        static {
            try {
                UNSAFE = Striped64.access$000();
                valueOffset = UNSAFE.objectFieldOffset(Cell.class.getDeclaredField(ES6Iterator.VALUE_PROPERTY));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    static {
        try {
            UNSAFE = getUnsafe();
            baseOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean casBase(long j, long j2) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j, j2);
    }

    final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x009d, code lost:
    
        r16.cells = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x008d, code lost:
    
        if (r16.cells != r9) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x008f, code lost:
    
        r8 = new com.google.common.hash.Striped64.Cell[r10 << 1];
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0094, code lost:
    
        if (r11 >= r10) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0096, code lost:
    
        r8[r11] = r9[r11];
        r11 = r11 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ec A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0022 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void retryUpdate(long j, int[] iArr, boolean z) {
        int i;
        int[] iArr2;
        boolean z2;
        int length;
        boolean z3;
        int length2;
        if (iArr == null) {
            iArr2 = new int[1];
            threadHashCode.set(iArr2);
            i = rng.nextInt();
            if (i == 0) {
                i = 1;
            }
            iArr2[0] = i;
        } else {
            i = iArr[0];
            iArr2 = iArr;
        }
        boolean z4 = z;
        while (true) {
            boolean z5 = false;
            while (true) {
                Cell[] cellArr = this.cells;
                if (cellArr != null && (length = cellArr.length) > 0) {
                    Cell cell = cellArr[(length - 1) & i];
                    if (cell == null) {
                        if (this.busy == 0) {
                            Cell cell2 = new Cell(j);
                            if (this.busy == 0 && casBusy()) {
                                try {
                                    Cell[] cellArr2 = this.cells;
                                    if (cellArr2 != null && (length2 = cellArr2.length) > 0) {
                                        int i2 = (length2 - 1) & i;
                                        if (cellArr2[i2] == null) {
                                            cellArr2[i2] = cell2;
                                            z3 = true;
                                            if (!z3) {
                                                return;
                                            }
                                        }
                                    }
                                    z3 = false;
                                    if (!z3) {
                                    }
                                } finally {
                                }
                            }
                        }
                    } else {
                        if (z4) {
                            long j2 = cell.value;
                            if (cell.cas(j2, mo738fn(j2, j))) {
                                return;
                            }
                            if (length < NCPU && this.cells == cellArr) {
                                if (!z5) {
                                    z5 = true;
                                } else if (this.busy == 0 && casBusy()) {
                                    try {
                                        break;
                                    } finally {
                                    }
                                }
                            }
                        } else {
                            z4 = true;
                        }
                        int i3 = i ^ (i << 13);
                        int i4 = i3 ^ (i3 >>> 17);
                        i = i4 ^ (i4 << 5);
                        iArr2[0] = i;
                    }
                    z5 = false;
                    int i32 = i ^ (i << 13);
                    int i42 = i32 ^ (i32 >>> 17);
                    i = i42 ^ (i42 << 5);
                    iArr2[0] = i;
                } else if (this.busy == 0 && this.cells == cellArr && casBusy()) {
                    try {
                        if (this.cells == cellArr) {
                            Cell[] cellArr3 = new Cell[2];
                            cellArr3[i & 1] = new Cell(j);
                            this.cells = cellArr3;
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            return;
                        }
                    } finally {
                    }
                } else {
                    long j3 = this.base;
                    if (casBase(j3, mo738fn(j3, j))) {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void internalReset(long j) {
        Cell[] cellArr = this.cells;
        this.base = j;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = j;
                }
            }
        }
    }

    private static Unsafe getUnsafe() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (SecurityException unused) {
                return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.hash.Striped64.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Unsafe run() throws Exception {
                        for (Field field : Unsafe.class.getDeclaredFields()) {
                            field.setAccessible(true);
                            Object obj = field.get(null);
                            if (Unsafe.class.isInstance(obj)) {
                                return (Unsafe) Unsafe.class.cast(obj);
                            }
                        }
                        throw new NoSuchFieldError("the Unsafe");
                    }
                });
            }
        } catch (PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics", e.getCause());
        }
    }
}
