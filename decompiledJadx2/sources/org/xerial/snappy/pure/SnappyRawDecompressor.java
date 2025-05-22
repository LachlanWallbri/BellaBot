package org.xerial.snappy.pure;

import java.nio.ByteOrder;
import kotlin.UShort;
import org.xerial.snappy.SnappyError;
import org.xerial.snappy.SnappyErrorCode;

/* loaded from: classes9.dex */
public final class SnappyRawDecompressor {
    private static final int[] DEC_32_TABLE = {4, 1, 2, 1, 4, 4, 4, 4};
    private static final int[] DEC_64_TABLE = {0, 0, 0, -1, 0, 1, 2, 3};
    private static final ByteOrder byteOrder = ByteOrder.nativeOrder();
    private static final int[] wordmask = {0, 255, 65535, 16777215, -1};
    private static final short[] opLookupTable = {1, 2052, 4097, 8193, 2, 2053, 4098, 8194, 3, 2054, 4099, 8195, 4, 2055, 4100, 8196, 5, 2056, 4101, 8197, 6, 2057, 4102, 8198, 7, 2058, 4103, 8199, 8, 2059, 4104, 8200, 9, 2308, 4105, 8201, 10, 2309, 4106, 8202, 11, 2310, 4107, 8203, 12, 2311, 4108, 8204, 13, 2312, 4109, 8205, 14, 2313, 4110, 8206, 15, 2314, 4111, 8207, 16, 2315, 4112, 8208, 17, 2564, 4113, 8209, 18, 2565, 4114, 8210, 19, 2566, 4115, 8211, 20, 2567, 4116, 8212, 21, 2568, 4117, 8213, 22, 2569, 4118, 8214, 23, 2570, 4119, 8215, 24, 2571, 4120, 8216, 25, 2820, 4121, 8217, 26, 2821, 4122, 8218, 27, 2822, 4123, 8219, 28, 2823, 4124, 8220, 29, 2824, 4125, 8221, 30, 2825, 4126, 8222, 31, 2826, 4127, 8223, 32, 2827, 4128, 8224, 33, 3076, 4129, 8225, 34, 3077, 4130, 8226, 35, 3078, 4131, 8227, 36, 3079, 4132, 8228, 37, 3080, 4133, 8229, 38, 3081, 4134, 8230, 39, 3082, 4135, 8231, 40, 3083, 4136, 8232, 41, 3332, 4137, 8233, 42, 3333, 4138, 8234, 43, 3334, 4139, 8235, 44, 3335, 4140, 8236, 45, 3336, 4141, 8237, 46, 3337, 4142, 8238, 47, 3338, 4143, 8239, 48, 3339, 4144, 8240, 49, 3588, 4145, 8241, 50, 3589, 4146, 8242, 51, 3590, 4147, 8243, 52, 3591, 4148, 8244, 53, 3592, 4149, 8245, 54, 3593, 4150, 8246, 55, 3594, 4151, 8247, 56, 3595, 4152, 8248, 57, 3844, 4153, 8249, 58, 3845, 4154, 8250, 59, 3846, 4155, 8251, 60, 3847, 4156, 8252, 2049, 3848, 4157, 8253, 4097, 3849, 4158, 8254, 6145, 3850, 4159, 8255, 8193, 3851, 4160, 8256};

    private SnappyRawDecompressor() {
    }

    private static int littleEndian(int i) {
        return byteOrder == ByteOrder.LITTLE_ENDIAN ? i : Integer.reverseBytes(i);
    }

    public static int getUncompressedLength(Object obj, long j, long j2) {
        return readUncompressedLength(obj, j, j2)[0];
    }

    public static int decompress(Object obj, long j, long j2, Object obj2, long j3, long j4) {
        int i = readUncompressedLength(obj, j, j2)[0];
        long j5 = j + r0[1];
        long j6 = j4 - j3;
        if (i > j6) {
            throw new SnappyError(SnappyErrorCode.INVALID_CHUNK_SIZE, String.format("Uncompressed length %s must be less than %s", Integer.valueOf(i), Long.valueOf(j6)));
        }
        int uncompressAll = uncompressAll(obj, j5, j2, obj2, j3, j4);
        if (i == uncompressAll) {
            return i;
        }
        throw new SnappyError(SnappyErrorCode.INVALID_CHUNK_SIZE, String.format("Recorded length is %s bytes but actual length after decompression is %s bytes ", Integer.valueOf(i), Integer.valueOf(uncompressAll)));
    }

    private static int uncompressAll(Object obj, long j, long j2, Object obj2, long j3, long j4) {
        long j5;
        long j6;
        Object obj3;
        int i;
        int i2;
        int i3;
        int i4;
        Object obj4;
        long j7;
        String str;
        long j8;
        long j9;
        long j10;
        long j11;
        Object obj5 = obj;
        long j12 = j4 - 8;
        long j13 = j;
        long j14 = j3;
        while (j13 < j2) {
            long j15 = j13 + 1;
            int i5 = UnsafeUtil.UNSAFE.getByte(obj5, j13) & 255;
            int i6 = opLookupTable[i5] & UShort.MAX_VALUE;
            int i7 = i6 >>> 11;
            if (j15 + 4 < j2) {
                j5 = j14;
                j6 = j12;
                i2 = littleEndian(UnsafeUtil.UNSAFE.getInt(obj5, j15)) & wordmask[i7];
                obj3 = obj5;
            } else {
                if (i7 + j15 > j2) {
                    throw new SnappyError(SnappyErrorCode.PARSING_ERROR, String.format("position: %d", Long.valueOf(j15 - j)));
                }
                if (i7 != 1) {
                    if (i7 != 2) {
                        if (i7 == 3) {
                            j6 = j12;
                            obj3 = obj;
                            i4 = 0;
                        } else if (i7 != 4) {
                            j5 = j14;
                            j6 = j12;
                            i2 = 0;
                            obj3 = obj;
                        } else {
                            j6 = j12;
                            obj3 = obj;
                            i4 = (UnsafeUtil.UNSAFE.getByte(obj3, j15 + 3) & 255) << 24;
                        }
                        i3 = i4 | ((UnsafeUtil.UNSAFE.getByte(obj3, j15 + 2) & 255) << 16);
                    } else {
                        j6 = j12;
                        obj3 = obj;
                        i3 = 0;
                    }
                    j5 = j14;
                    i = i3 | ((UnsafeUtil.UNSAFE.getByte(obj3, j15 + 1) & 255) << 8);
                } else {
                    j5 = j14;
                    j6 = j12;
                    obj3 = obj;
                    i = 0;
                }
                i2 = i | (UnsafeUtil.UNSAFE.getByte(obj3, j15) & 255);
            }
            if (i2 < 0) {
                throw new SnappyError(SnappyErrorCode.PARSING_ERROR, String.format("position: %d", Long.valueOf(j15 - j)));
            }
            long j16 = j15 + i7;
            int i8 = i6 & 255;
            if (i8 == 0) {
                obj5 = obj3;
                j12 = j6;
                j14 = j5;
                j13 = j16;
            } else {
                if ((i5 & 3) != 0) {
                    int i9 = (i6 & 1792) + i2;
                    long j17 = j5 - i9;
                    if (j17 >= j3) {
                        long j18 = j5 + i8;
                        if (j18 <= j4) {
                            if (j5 > j6) {
                                long j19 = j5;
                                while (j19 < j18) {
                                    UnsafeUtil.UNSAFE.putByte(obj2, j19, UnsafeUtil.UNSAFE.getByte(obj2, j17));
                                    j19++;
                                    j17++;
                                }
                                obj4 = obj2;
                                j7 = j18;
                            } else {
                                if (i9 < 8) {
                                    int i10 = DEC_32_TABLE[i9];
                                    int i11 = DEC_64_TABLE[i9];
                                    long j20 = j5;
                                    UnsafeUtil.UNSAFE.putByte(obj2, j20, UnsafeUtil.UNSAFE.getByte(obj2, j17));
                                    obj4 = obj2;
                                    str = "position: %d";
                                    j7 = j18;
                                    UnsafeUtil.UNSAFE.putByte(obj4, j20 + 1, UnsafeUtil.UNSAFE.getByte(obj4, j17 + 1));
                                    UnsafeUtil.UNSAFE.putByte(obj4, j20 + 2, UnsafeUtil.UNSAFE.getByte(obj4, j17 + 2));
                                    UnsafeUtil.UNSAFE.putByte(obj4, j20 + 3, UnsafeUtil.UNSAFE.getByte(obj4, j17 + 3));
                                    long j21 = j20 + 4;
                                    long j22 = j17 + i10;
                                    UnsafeUtil.UNSAFE.putInt(obj4, j21, UnsafeUtil.UNSAFE.getInt(obj4, j22));
                                    j9 = j21 + 4;
                                    j8 = j22 - i11;
                                } else {
                                    obj4 = obj2;
                                    j7 = j18;
                                    long j23 = j5;
                                    str = "position: %d";
                                    UnsafeUtil.UNSAFE.putLong(obj2, j23, UnsafeUtil.UNSAFE.getLong(obj4, j17));
                                    j8 = j17 + 8;
                                    j9 = j23 + 8;
                                }
                                if (j7 <= j6) {
                                    long j24 = j8;
                                    for (long j25 = j9; j25 < j7; j25 += 8) {
                                        UnsafeUtil.UNSAFE.putLong(obj2, j25, UnsafeUtil.UNSAFE.getLong(obj4, j24));
                                        j24 += 8;
                                    }
                                } else {
                                    if (j18 > j4) {
                                        throw new SnappyError(SnappyErrorCode.PARSING_ERROR, String.format(str, Long.valueOf(j16 - j)));
                                    }
                                    long j26 = j8;
                                    long j27 = j9;
                                    while (j27 < j6) {
                                        UnsafeUtil.UNSAFE.putLong(obj2, j27, UnsafeUtil.UNSAFE.getLong(obj4, j26));
                                        j26 += 8;
                                        j27 += 8;
                                    }
                                    while (j27 < j7) {
                                        UnsafeUtil.UNSAFE.putByte(obj4, j27, UnsafeUtil.UNSAFE.getByte(obj4, j26));
                                        j27++;
                                        j26++;
                                    }
                                }
                            }
                            j10 = 8;
                            j14 = j7;
                        }
                    }
                    throw new SnappyError(SnappyErrorCode.PARSING_ERROR, String.format("position: %d", Long.valueOf(j16 - j)));
                }
                long j28 = i8 + i2;
                long j29 = j5 + j28;
                if (j29 <= j6 && j16 + j28 <= j2 - 8) {
                    long j30 = j16;
                    do {
                        UnsafeUtil.UNSAFE.putLong(obj2, j5, UnsafeUtil.UNSAFE.getLong(obj3, j30));
                        j30 += 8;
                        j5 += 8;
                    } while (j5 < j29);
                    j11 = j30 - (j5 - j29);
                } else {
                    if (j29 > j4) {
                        throw new SnappyError(SnappyErrorCode.PARSING_ERROR, String.format("position: %d", Long.valueOf(j16 - j)));
                    }
                    UnsafeUtil.UNSAFE.copyMemory(obj, j16, obj2, j5, j28);
                    j11 = j16 + j28;
                }
                j16 = j11;
                j14 = j29;
                j10 = 8;
                obj4 = obj2;
                obj5 = obj3;
                j12 = j6;
                j13 = j16;
            }
        }
        return (int) (j14 - j3);
    }

    static int[] readUncompressedLength(Object obj, long j, long j2) {
        int i;
        int unsignedByteSafe = getUnsignedByteSafe(obj, 0 + j, j2);
        int i2 = unsignedByteSafe & 127;
        if ((unsignedByteSafe & 128) != 0) {
            int unsignedByteSafe2 = getUnsignedByteSafe(obj, 1 + j, j2);
            i2 |= (unsignedByteSafe2 & 127) << 7;
            if ((unsignedByteSafe2 & 128) != 0) {
                int unsignedByteSafe3 = getUnsignedByteSafe(obj, 2 + j, j2);
                i = 3;
                i2 |= (unsignedByteSafe3 & 127) << 14;
                if ((unsignedByteSafe3 & 128) != 0) {
                    int unsignedByteSafe4 = getUnsignedByteSafe(obj, 3 + j, j2);
                    i = 4;
                    i2 |= (unsignedByteSafe4 & 127) << 21;
                    if ((unsignedByteSafe4 & 128) != 0) {
                        int unsignedByteSafe5 = getUnsignedByteSafe(obj, 4 + j, j2);
                        i2 |= (unsignedByteSafe5 & 127) << 28;
                        if ((unsignedByteSafe5 & 128) != 0) {
                            throw new SnappyError(SnappyErrorCode.PARSING_ERROR, String.format("position: %d, error: %s", Long.valueOf(j + 5), "last byte of compressed length int has high bit set"));
                        }
                        i = 5;
                    }
                }
            } else {
                i = 2;
            }
        } else {
            i = 1;
        }
        return new int[]{i2, i};
    }

    private static int getUnsignedByteSafe(Object obj, long j, long j2) {
        if (j >= j2) {
            throw new SnappyError(SnappyErrorCode.PARSING_ERROR, String.format("position: %d, error: %s", Long.valueOf(j2 - j), "Input is truncated"));
        }
        return UnsafeUtil.UNSAFE.getByte(obj, j) & 255;
    }
}
