package org.xerial.snappy.pure;

import android.support.v4.media.session.PlaybackStateCompat;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.UShort;

/* loaded from: classes9.dex */
public final class SnappyRawCompressor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BLOCK_LOG = 16;
    private static final int BLOCK_SIZE = 65536;
    private static final int HIGH_BIT_MASK = 128;
    private static final int INPUT_MARGIN_BYTES = 15;
    private static final int MAX_HASH_TABLE_BITS = 14;
    public static final int MAX_HASH_TABLE_SIZE = 16384;
    private static final ByteOrder byteOrder = ByteOrder.nativeOrder();

    private static int hashBytes(int i, int i2) {
        return (i * 506832829) >>> i2;
    }

    private SnappyRawCompressor() {
    }

    private static int littleEndian(int i) {
        return byteOrder == ByteOrder.LITTLE_ENDIAN ? i : Integer.reverseBytes(i);
    }

    private static long littleEndian(long j) {
        return byteOrder == ByteOrder.LITTLE_ENDIAN ? j : Long.reverseBytes(j);
    }

    private static short littleEndian(short s) {
        return byteOrder == ByteOrder.LITTLE_ENDIAN ? s : Short.reverseBytes(s);
    }

    public static int maxCompressedLength(int i) {
        return i + 32 + (i / 6);
    }

    public static int compress(Object obj, long j, long j2, Object obj2, long j3, long j4, short[] sArr) {
        long j5;
        long j6;
        long j7;
        int i;
        long j8;
        long j9 = j2;
        int i2 = (int) (j9 - j);
        int maxCompressedLength = maxCompressedLength(i2);
        if (j4 - j3 < maxCompressedLength) {
            throw new IllegalArgumentException("Output buffer must be at least " + maxCompressedLength + " bytes");
        }
        long writeUncompressedLength = writeUncompressedLength(obj2, j3, i2);
        for (long j10 = j; j10 < j9; j10 = j5) {
            long j11 = j10 + PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
            long min = Math.min(j9, j11);
            int hashTableSize = getHashTableSize((int) (min - j10));
            Arrays.fill(sArr, 0, hashTableSize, (short) 0);
            int log2Floor = 32 - log2Floor(hashTableSize);
            long j12 = min - 15;
            long j13 = j10;
            while (true) {
                if (j13 > j12) {
                    j5 = j11;
                    break;
                }
                int i3 = 32;
                long j14 = 0;
                long j15 = j13 + 1;
                while (true) {
                    j6 = j11;
                    j7 = (i3 >>> 5) + j15;
                    if (j7 > j12) {
                        i = log2Floor;
                        break;
                    }
                    int littleEndian = littleEndian(UnsafeUtil.UNSAFE.getInt(obj, j15));
                    i = log2Floor;
                    long j16 = j10 + (sArr[r8] & UShort.MAX_VALUE);
                    sArr[hashBytes(littleEndian, log2Floor)] = (short) (j15 - j10);
                    if (littleEndian == littleEndian(UnsafeUtil.UNSAFE.getInt(obj, j16))) {
                        j14 = j16;
                        break;
                    }
                    i3++;
                    j11 = j6;
                    j15 = j7;
                    j14 = j16;
                    log2Floor = i;
                }
                if (j7 > j12) {
                    j5 = j6;
                    break;
                }
                int i4 = (int) (j15 - j13);
                long j17 = j15;
                int i5 = i;
                writeUncompressedLength = fastCopy(obj, j13, obj2, emitLiteralLength(obj2, writeUncompressedLength, i4), i4);
                while (true) {
                    int count = count(obj, j17 + 4, j14 + 4, min) + 4;
                    j8 = j6;
                    writeUncompressedLength = emitCopy(obj2, writeUncompressedLength, j17, j14, count);
                    j17 += count;
                    if (j17 >= j12) {
                        break;
                    }
                    long littleEndian2 = littleEndian(UnsafeUtil.UNSAFE.getLong(obj, j17 - 1));
                    int i6 = (int) littleEndian2;
                    int i7 = (int) (littleEndian2 >>> 8);
                    int hashBytes = hashBytes(i6, i5);
                    sArr[hashBytes] = (short) (r4 - 1);
                    int hashBytes2 = hashBytes(i7, i5);
                    long j18 = j10 + (sArr[hashBytes2] & UShort.MAX_VALUE);
                    sArr[hashBytes2] = (short) (j17 - j10);
                    if (i7 != littleEndian(UnsafeUtil.UNSAFE.getInt(obj, j18))) {
                        break;
                    }
                    j14 = j18;
                    j6 = j8;
                }
                log2Floor = i5;
                j13 = j17;
                j11 = j8;
            }
            if (j13 < min) {
                int i8 = (int) (min - j13);
                long emitLiteralLength = emitLiteralLength(obj2, writeUncompressedLength, i8);
                long j19 = i8;
                UnsafeUtil.UNSAFE.copyMemory(obj, j13, obj2, emitLiteralLength, j19);
                writeUncompressedLength = emitLiteralLength + j19;
            }
            j9 = j2;
        }
        return (int) (writeUncompressedLength - j3);
    }

    private static int count(Object obj, long j, long j2, long j3) {
        long j4 = j2;
        long j5 = j;
        while (true) {
            if (j5 < j3 - 7) {
                if ((littleEndian(UnsafeUtil.UNSAFE.getLong(obj, j4)) ^ littleEndian(UnsafeUtil.UNSAFE.getLong(obj, j5))) != 0) {
                    j5 += Long.numberOfTrailingZeros(r2) >> 3;
                    break;
                }
                j5 += 8;
                j4 += 8;
            } else {
                if (j5 < j3 - 3 && littleEndian(UnsafeUtil.UNSAFE.getInt(obj, j4)) == littleEndian(UnsafeUtil.UNSAFE.getInt(obj, j5))) {
                    j5 += 4;
                    j4 += 4;
                }
                if (j5 < j3 - 1 && littleEndian(UnsafeUtil.UNSAFE.getShort(obj, j4)) == littleEndian(UnsafeUtil.UNSAFE.getShort(obj, j5))) {
                    j5 += 2;
                    j4 += 2;
                }
                if (j5 < j3 && UnsafeUtil.UNSAFE.getByte(obj, j4) == UnsafeUtil.UNSAFE.getByte(obj, j5)) {
                    j5++;
                }
            }
        }
        return (int) (j5 - j);
    }

    private static long emitLiteralLength(Object obj, long j, int i) {
        long j2;
        int i2 = 1;
        int i3 = i - 1;
        if (i3 < 60) {
            long j3 = 1 + j;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) (i3 << 2));
            return j3;
        }
        if (i3 < 256) {
            j2 = 1 + j;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) -16);
        } else if (i3 < 65536) {
            j2 = 1 + j;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) -12);
            i2 = 2;
        } else if (i3 < 16777216) {
            j2 = 1 + j;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) -8);
            i2 = 3;
        } else {
            j2 = 1 + j;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) -4);
            i2 = 4;
        }
        UnsafeUtil.UNSAFE.putInt(obj, j2, littleEndian(i3));
        return j2 + i2;
    }

    private static long fastCopy(Object obj, long j, Object obj2, long j2, int i) {
        long j3 = i + j2;
        do {
            UnsafeUtil.UNSAFE.putLong(obj2, j2, UnsafeUtil.UNSAFE.getLong(obj, j));
            j += 8;
            j2 += 8;
        } while (j2 < j3);
        return j3;
    }

    private static long emitCopy(Object obj, long j, long j2, long j3, int i) {
        long j4 = j2 - j3;
        while (i >= 68) {
            long j5 = 1 + j;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) -2);
            UnsafeUtil.UNSAFE.putShort(obj, j5, littleEndian((short) j4));
            j = j5 + 2;
            i -= 64;
        }
        if (i > 64) {
            long j6 = j + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) -18);
            UnsafeUtil.UNSAFE.putShort(obj, j6, littleEndian((short) j4));
            j = j6 + 2;
            i -= 60;
        }
        if (i < 12 && j4 < PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) {
            long j7 = j + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) (((i - 4) << 2) + 1 + ((j4 >>> 8) << 5)));
            long j8 = 1 + j7;
            UnsafeUtil.UNSAFE.putByte(obj, j7, (byte) j4);
            return j8;
        }
        long j9 = 1 + j;
        UnsafeUtil.UNSAFE.putByte(obj, j, (byte) (((i - 1) << 2) + 2));
        UnsafeUtil.UNSAFE.putShort(obj, j9, littleEndian((short) j4));
        return j9 + 2;
    }

    private static int getHashTableSize(int i) {
        return Math.max(Math.min(Integer.highestOneBit(i - 1) << 1, 16384), 256);
    }

    private static int log2Floor(int i) {
        if (i == 0) {
            return -1;
        }
        return Integer.numberOfLeadingZeros(i) ^ 31;
    }

    private static long writeUncompressedLength(Object obj, long j, int i) {
        if (i < 128 && i >= 0) {
            long j2 = 1 + j;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) i);
            return j2;
        }
        if (i < 16384 && i > 0) {
            long j3 = j + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) (i | 128));
            long j4 = 1 + j3;
            UnsafeUtil.UNSAFE.putByte(obj, j3, (byte) (i >>> 7));
            return j4;
        }
        if (i < 2097152 && i > 0) {
            long j5 = j + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) (i | 128));
            long j6 = j5 + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j5, (byte) ((i >>> 7) | 128));
            long j7 = 1 + j6;
            UnsafeUtil.UNSAFE.putByte(obj, j6, (byte) (i >>> 14));
            return j7;
        }
        if (i < 268435456 && i > 0) {
            long j8 = j + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j, (byte) (i | 128));
            long j9 = j8 + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j8, (byte) ((i >>> 7) | 128));
            long j10 = j9 + 1;
            UnsafeUtil.UNSAFE.putByte(obj, j9, (byte) ((i >>> 14) | 128));
            long j11 = 1 + j10;
            UnsafeUtil.UNSAFE.putByte(obj, j10, (byte) (i >>> 21));
            return j11;
        }
        long j12 = j + 1;
        UnsafeUtil.UNSAFE.putByte(obj, j, (byte) (i | 128));
        long j13 = j12 + 1;
        UnsafeUtil.UNSAFE.putByte(obj, j12, (byte) ((i >>> 7) | 128));
        long j14 = j13 + 1;
        UnsafeUtil.UNSAFE.putByte(obj, j13, (byte) ((i >>> 14) | 128));
        long j15 = j14 + 1;
        UnsafeUtil.UNSAFE.putByte(obj, j14, (byte) ((i >>> 21) | 128));
        long j16 = 1 + j15;
        UnsafeUtil.UNSAFE.putByte(obj, j15, (byte) (i >>> 28));
        return j16;
    }
}
