package org.jboss.netty.util.internal.jzlib;

import org.jboss.netty.util.internal.jzlib.JZlib;

/* loaded from: classes7.dex */
final class Inflate {
    private static final int BAD = 13;
    private static final int BLOCKS = 7;
    private static final int CHECK1 = 11;
    private static final int CHECK2 = 10;
    private static final int CHECK3 = 9;
    private static final int CHECK4 = 8;
    private static final int DICT0 = 6;
    private static final int DICT1 = 5;
    private static final int DICT2 = 4;
    private static final int DICT3 = 3;
    private static final int DICT4 = 2;
    private static final int DONE = 12;
    private static final int FLAG = 1;
    private static final int GZIP_CM = 16;
    private static final int GZIP_CRC32 = 24;
    private static final int GZIP_FCOMMENT = 22;
    private static final int GZIP_FEXTRA = 20;
    private static final int GZIP_FHCRC = 23;
    private static final int GZIP_FLG = 17;
    private static final int GZIP_FNAME = 21;
    private static final int GZIP_ID1 = 14;
    private static final int GZIP_ID2 = 15;
    private static final int GZIP_ISIZE = 25;
    private static final int GZIP_MTIME_XFL_OS = 18;
    private static final int GZIP_XLEN = 19;
    private static final int METHOD = 0;
    private static final byte[] mark = {0, 0, -1, -1};
    private InfBlocks blocks;
    private int gzipBytesToRead;
    private int gzipCRC32;
    private int gzipFlag;
    private int gzipISize;
    private int gzipUncompressedBytes;
    private int gzipXLen;
    private int marker;
    private int method;
    private int mode;
    private long need;
    private final long[] was = new long[1];
    private int wbits;
    private JZlib.WrapperType wrapperType;

    private int inflateReset(ZStream zStream) {
        if (zStream == null || zStream.istate == null) {
            return -2;
        }
        zStream.total_out = 0L;
        zStream.total_in = 0L;
        zStream.msg = null;
        int i = C87451.$SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[this.wrapperType.ordinal()];
        if (i == 1) {
            zStream.istate.mode = 7;
        } else if (i == 2 || i == 3) {
            zStream.istate.mode = 0;
        } else if (i == 4) {
            zStream.istate.mode = 14;
        }
        zStream.istate.blocks.reset(zStream, null);
        this.gzipUncompressedBytes = 0;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.util.internal.jzlib.Inflate$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C87451 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType = new int[JZlib.WrapperType.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.ZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.ZLIB_OR_NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.GZIP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int inflateEnd(ZStream zStream) {
        InfBlocks infBlocks = this.blocks;
        if (infBlocks != null) {
            infBlocks.free(zStream);
        }
        this.blocks = null;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int inflateInit(ZStream zStream, int i, JZlib.WrapperType wrapperType) {
        zStream.msg = null;
        this.blocks = null;
        this.wrapperType = wrapperType;
        if (i < 0) {
            throw new IllegalArgumentException("w: " + i);
        }
        if (i < 8 || i > 15) {
            inflateEnd(zStream);
            return -2;
        }
        this.wbits = i;
        zStream.istate.blocks = new InfBlocks(zStream, zStream.istate.wrapperType != JZlib.WrapperType.NONE ? this : null, 1 << i);
        inflateReset(zStream);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0281, code lost:
    
        if (r0 != 0) goto L263;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x026a, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0283, code lost:
    
        r22.gzipBytesToRead = 2;
        r23.istate.mode = 23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0526, code lost:
    
        if (r23.avail_in != 0) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0528, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0529, code lost:
    
        r23.avail_in--;
        r23.total_in++;
        r3 = r23.istate;
        r4 = r23.next_in;
        r23.next_in_index = r23.next_in_index + 1;
        r3.need = ((r4[r6] & 255) << 24) & 4278190080L;
        r23.istate.mode = 3;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0555, code lost:
    
        if (r23.avail_in != 0) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0557, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0558, code lost:
    
        r23.avail_in--;
        r23.total_in++;
        r3 = r23.istate;
        r6 = r3.need;
        r4 = r23.next_in;
        r23.next_in_index = r23.next_in_index + 1;
        r3.need = r6 + (((r4[r8] & 255) << 16) & 16711680);
        r23.istate.mode = 4;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0584, code lost:
    
        if (r23.avail_in != 0) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x0586, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0587, code lost:
    
        r23.avail_in--;
        r23.total_in++;
        r3 = r23.istate;
        r6 = r3.need;
        r4 = r23.next_in;
        r23.next_in_index = r23.next_in_index + 1;
        r3.need = r6 + (((r4[r8] & 255) << 8) & 65280);
        r23.istate.mode = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x05b2, code lost:
    
        if (r23.avail_in != 0) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x05b4, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x05b5, code lost:
    
        r23.avail_in--;
        r23.total_in++;
        r0 = r23.istate;
        r3 = r0.need;
        r5 = r23.next_in;
        r23.next_in_index = r23.next_in_index + 1;
        r0.need = r3 + (r5[r6] & 255);
        r23.adler = r23.istate.need;
        r23.istate.mode = 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x05e0, code lost:
    
        return 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x023c, code lost:
    
        if ((8 & r22.gzipFlag) == 0) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0240, code lost:
    
        if (r23.avail_in != 0) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0243, code lost:
    
        r23.avail_in--;
        r23.total_in++;
        r0 = r23.next_in;
        r6 = r23.next_in_index;
        r23.next_in_index = r6 + 1;
        r0 = r0[r6];
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0259, code lost:
    
        if (r0 != 0) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0242, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x025b, code lost:
    
        r23.istate.mode = 22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0264, code lost:
    
        if ((r22.gzipFlag & 16) != 0) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0268, code lost:
    
        if (r23.avail_in != 0) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x026b, code lost:
    
        r23.avail_in--;
        r23.total_in++;
        r0 = r23.next_in;
        r6 = r23.next_in_index;
        r23.next_in_index = r6 + 1;
        r0 = r0[r6];
        r6 = r5;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x002d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01d9 A[LOOP:2: B:73:0x01d9->B:77:0x01e2, LOOP_START, PHI: r6
      0x01d9: PHI (r6v120 int) = (r6v119 int), (r6v127 int) binds: [B:72:0x01d7, B:77:0x01e2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02b6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x01b4 -> B:66:0x01aa). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int inflate(ZStream zStream, int i) {
        if (zStream == null || zStream.istate == null || zStream.next_in == null) {
            return -2;
        }
        int i2 = 4;
        int i3 = i == 4 ? -5 : 0;
        int i4 = -5;
        while (true) {
            switch (zStream.istate.mode) {
                case 0:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    if (zStream.istate.wrapperType == JZlib.WrapperType.ZLIB_OR_NONE) {
                        if ((zStream.next_in[zStream.next_in_index] & 15) != 8 || (zStream.next_in[zStream.next_in_index] >> 4) + 8 > zStream.istate.wbits) {
                            zStream.istate.wrapperType = JZlib.WrapperType.NONE;
                            zStream.istate.mode = 7;
                            i2 = 4;
                        } else {
                            zStream.istate.wrapperType = JZlib.WrapperType.ZLIB;
                        }
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    Inflate inflate = zStream.istate;
                    byte[] bArr = zStream.next_in;
                    int i5 = zStream.next_in_index;
                    zStream.next_in_index = i5 + 1;
                    byte b = bArr[i5];
                    inflate.method = b;
                    if ((b & 15) != 8) {
                        zStream.istate.mode = 13;
                        zStream.msg = "unknown compression method";
                        zStream.istate.marker = 5;
                    } else if ((zStream.istate.method >> 4) + 8 > zStream.istate.wbits) {
                        zStream.istate.mode = 13;
                        zStream.msg = "invalid window size";
                        zStream.istate.marker = 5;
                    } else {
                        zStream.istate.mode = 1;
                        i4 = i3;
                    }
                    i4 = i3;
                    i2 = 4;
                    break;
                case 1:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    byte[] bArr2 = zStream.next_in;
                    int i6 = zStream.next_in_index;
                    zStream.next_in_index = i6 + 1;
                    int i7 = bArr2[i6] & 255;
                    if (((zStream.istate.method << 8) + i7) % 31 == 0) {
                        if ((i7 & 32) != 0) {
                            zStream.istate.mode = 2;
                            i4 = i3;
                            break;
                        } else {
                            zStream.istate.mode = 7;
                        }
                    } else {
                        zStream.istate.mode = 13;
                        zStream.msg = "incorrect header check";
                        zStream.istate.marker = 5;
                    }
                    i4 = i3;
                    i2 = 4;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    i3 = i4;
                    break;
                case 6:
                    zStream.istate.mode = 13;
                    zStream.msg = "need dictionary";
                    zStream.istate.marker = 0;
                    return -2;
                case 7:
                    int i8 = zStream.next_out_index;
                    try {
                        i4 = zStream.istate.blocks.proc(zStream, i4);
                        if (i4 == -3) {
                            zStream.istate.mode = 13;
                            zStream.istate.marker = 0;
                            i2 = 4;
                        } else {
                            if (i4 == 0) {
                                i4 = i3;
                            }
                            if (i4 != 1) {
                                return i4;
                            }
                            zStream.istate.blocks.reset(zStream, zStream.istate.was);
                            int i9 = zStream.next_out_index - i8;
                            this.gzipUncompressedBytes += i9;
                            zStream.crc32 = CRC32.crc32(zStream.crc32, zStream.next_out, i8, i9);
                            if (zStream.istate.wrapperType == JZlib.WrapperType.NONE) {
                                zStream.istate.mode = 12;
                            } else if (zStream.istate.wrapperType == JZlib.WrapperType.ZLIB) {
                                zStream.istate.mode = 8;
                                i4 = i3;
                            } else if (zStream.istate.wrapperType == JZlib.WrapperType.GZIP) {
                                this.gzipCRC32 = 0;
                                this.gzipISize = 0;
                                this.gzipBytesToRead = 4;
                                zStream.istate.mode = 24;
                            } else {
                                zStream.istate.mode = 13;
                                zStream.msg = "unexpected state";
                                zStream.istate.marker = 0;
                            }
                            i4 = i3;
                            i2 = 4;
                        }
                    } finally {
                        int i10 = zStream.next_out_index - i8;
                        this.gzipUncompressedBytes += i10;
                        zStream.crc32 = CRC32.crc32(zStream.crc32, zStream.next_out, i8, i10);
                    }
                case 8:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    Inflate inflate2 = zStream.istate;
                    byte[] bArr3 = zStream.next_in;
                    zStream.next_in_index = zStream.next_in_index + 1;
                    inflate2.need = ((bArr3[r9] & 255) << 24) & 4278190080L;
                    zStream.istate.mode = 9;
                    i4 = i3;
                case 9:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    Inflate inflate3 = zStream.istate;
                    long j = inflate3.need;
                    byte[] bArr4 = zStream.next_in;
                    zStream.next_in_index = zStream.next_in_index + 1;
                    inflate3.need = j + (((bArr4[r13] & 255) << 16) & 16711680);
                    zStream.istate.mode = 10;
                    i4 = i3;
                case 10:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    Inflate inflate4 = zStream.istate;
                    long j2 = inflate4.need;
                    byte[] bArr5 = zStream.next_in;
                    zStream.next_in_index = zStream.next_in_index + 1;
                    inflate4.need = j2 + (((bArr5[r10] & 255) << 8) & 65280);
                    zStream.istate.mode = 11;
                    i4 = i3;
                case 11:
                    if (zStream.avail_in != 0) {
                        zStream.avail_in--;
                        zStream.total_in++;
                        Inflate inflate5 = zStream.istate;
                        long j3 = inflate5.need;
                        byte[] bArr6 = zStream.next_in;
                        zStream.next_in_index = zStream.next_in_index + 1;
                        inflate5.need = j3 + (bArr6[r9] & 255);
                        if (((int) zStream.istate.was[0]) == ((int) zStream.istate.need)) {
                            zStream.istate.mode = 12;
                            break;
                        } else {
                            zStream.istate.mode = 13;
                            zStream.msg = "incorrect data check";
                            zStream.istate.marker = 5;
                            i4 = i3;
                            i2 = 4;
                        }
                    } else {
                        return i4;
                    }
                case 12:
                    break;
                case 13:
                    return -3;
                case 14:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    byte[] bArr7 = zStream.next_in;
                    int i11 = zStream.next_in_index;
                    zStream.next_in_index = i11 + 1;
                    if ((bArr7[i11] & 255) != 31) {
                        zStream.istate.mode = 13;
                        zStream.msg = "not a gzip stream";
                        zStream.istate.marker = 5;
                        i4 = i3;
                        i2 = 4;
                    } else {
                        zStream.istate.mode = 15;
                        i4 = i3;
                    }
                case 15:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    byte[] bArr8 = zStream.next_in;
                    int i12 = zStream.next_in_index;
                    zStream.next_in_index = i12 + 1;
                    if ((bArr8[i12] & 255) != 139) {
                        zStream.istate.mode = 13;
                        zStream.msg = "not a gzip stream";
                        zStream.istate.marker = 5;
                        i4 = i3;
                        i2 = 4;
                    } else {
                        zStream.istate.mode = 16;
                        i4 = i3;
                    }
                case 16:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    byte[] bArr9 = zStream.next_in;
                    int i13 = zStream.next_in_index;
                    zStream.next_in_index = i13 + 1;
                    if ((bArr9[i13] & 255) != 8) {
                        zStream.istate.mode = 13;
                        zStream.msg = "unknown compression method";
                        zStream.istate.marker = 5;
                        i4 = i3;
                        i2 = 4;
                    } else {
                        zStream.istate.mode = 17;
                        i4 = i3;
                    }
                case 17:
                    if (zStream.avail_in == 0) {
                        return i4;
                    }
                    zStream.avail_in--;
                    zStream.total_in++;
                    byte[] bArr10 = zStream.next_in;
                    int i14 = zStream.next_in_index;
                    zStream.next_in_index = i14 + 1;
                    this.gzipFlag = bArr10[i14] & 255;
                    if ((this.gzipFlag & 226) != 0) {
                        zStream.istate.mode = 13;
                        zStream.msg = "unsupported flag";
                        zStream.istate.marker = 5;
                        i4 = i3;
                        i2 = 4;
                    } else {
                        this.gzipBytesToRead = 6;
                        zStream.istate.mode = 18;
                        i4 = i3;
                        if (this.gzipBytesToRead <= 0) {
                            zStream.istate.mode = 19;
                            this.gzipXLen = 0;
                            this.gzipBytesToRead = 2;
                            if ((this.gzipFlag & i2) == 0) {
                                while (this.gzipBytesToRead > 0) {
                                    if (zStream.avail_in == 0) {
                                        return i4;
                                    }
                                    zStream.avail_in--;
                                    zStream.total_in++;
                                    int i15 = this.gzipXLen;
                                    byte[] bArr11 = zStream.next_in;
                                    int i16 = zStream.next_in_index;
                                    zStream.next_in_index = i16 + 1;
                                    int i17 = bArr11[i16] & 255;
                                    int i18 = this.gzipBytesToRead;
                                    this.gzipXLen = i15 | (i17 << ((1 - i18) * 8));
                                    this.gzipBytesToRead = i18 - 1;
                                    i4 = i3;
                                }
                                this.gzipBytesToRead = this.gzipXLen;
                                zStream.istate.mode = 20;
                                while (this.gzipBytesToRead > 0) {
                                    if (zStream.avail_in == 0) {
                                        return i4;
                                    }
                                    zStream.avail_in--;
                                    zStream.total_in++;
                                    zStream.next_in_index++;
                                    this.gzipBytesToRead--;
                                    i4 = i3;
                                }
                                zStream.istate.mode = 21;
                                break;
                            } else {
                                zStream.istate.mode = 21;
                                i2 = 4;
                            }
                        } else {
                            if (zStream.avail_in == 0) {
                                return i4;
                            }
                            zStream.avail_in--;
                            zStream.total_in++;
                            zStream.next_in_index++;
                            this.gzipBytesToRead--;
                            i4 = i3;
                            if (this.gzipBytesToRead <= 0) {
                            }
                        }
                    }
                case 18:
                    if (this.gzipBytesToRead <= 0) {
                    }
                    break;
                case 19:
                    if ((this.gzipFlag & i2) == 0) {
                    }
                    break;
                case 20:
                    while (this.gzipBytesToRead > 0) {
                    }
                    zStream.istate.mode = 21;
                case 21:
                    break;
                case 22:
                    break;
                case 23:
                    if ((this.gzipFlag & 2) != 0) {
                        while (this.gzipBytesToRead > 0) {
                            if (zStream.avail_in == 0) {
                                return i4;
                            }
                            zStream.avail_in--;
                            zStream.total_in++;
                            zStream.next_in_index++;
                            this.gzipBytesToRead--;
                            i4 = i3;
                        }
                    }
                    zStream.istate.mode = 7;
                    i2 = 4;
                case 24:
                    while (this.gzipBytesToRead > 0) {
                        if (zStream.avail_in == 0) {
                            return i4;
                        }
                        zStream.avail_in--;
                        zStream.total_in++;
                        this.gzipBytesToRead--;
                        Inflate inflate6 = zStream.istate;
                        int i19 = inflate6.gzipCRC32;
                        byte[] bArr12 = zStream.next_in;
                        int i20 = zStream.next_in_index;
                        zStream.next_in_index = i20 + 1;
                        inflate6.gzipCRC32 = i19 | ((bArr12[i20] & 255) << ((3 - this.gzipBytesToRead) * 8));
                        i4 = i3;
                    }
                    if (zStream.crc32 != zStream.istate.gzipCRC32) {
                        zStream.istate.mode = 13;
                        zStream.msg = "incorrect CRC32 checksum";
                        zStream.istate.marker = 5;
                        i2 = 4;
                    } else {
                        this.gzipBytesToRead = i2;
                        zStream.istate.mode = 25;
                        while (this.gzipBytesToRead > 0) {
                            if (zStream.avail_in == 0) {
                                return i4;
                            }
                            zStream.avail_in--;
                            zStream.total_in++;
                            this.gzipBytesToRead--;
                            Inflate inflate7 = zStream.istate;
                            int i21 = inflate7.gzipISize;
                            byte[] bArr13 = zStream.next_in;
                            int i22 = zStream.next_in_index;
                            zStream.next_in_index = i22 + 1;
                            inflate7.gzipISize = i21 | ((bArr13[i22] & 255) << ((3 - this.gzipBytesToRead) * 8));
                            i4 = i3;
                        }
                        if (this.gzipUncompressedBytes == zStream.istate.gzipISize) {
                            zStream.istate.mode = 13;
                            zStream.msg = "incorrect ISIZE checksum";
                            zStream.istate.marker = 5;
                        } else {
                            zStream.istate.mode = 12;
                        }
                        i2 = 4;
                    }
                case 25:
                    while (this.gzipBytesToRead > 0) {
                    }
                    if (this.gzipUncompressedBytes == zStream.istate.gzipISize) {
                    }
                    i2 = 4;
                    break;
                default:
                    return -2;
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int inflateSetDictionary(ZStream zStream, byte[] bArr, int i) {
        int i2;
        int i3;
        if (zStream == null || zStream.istate == null || zStream.istate.mode != 6) {
            return -2;
        }
        if (Adler32.adler32(1L, bArr, 0, i) != zStream.adler) {
            return -3;
        }
        zStream.adler = Adler32.adler32(0L, null, 0, 0);
        if (i >= (1 << zStream.istate.wbits)) {
            i2 = (1 << zStream.istate.wbits) - 1;
            i3 = i - i2;
        } else {
            i2 = i;
            i3 = 0;
        }
        zStream.istate.blocks.set_dictionary(bArr, i3, i2);
        zStream.istate.mode = 7;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int inflateSync(ZStream zStream) {
        if (zStream == null || zStream.istate == null) {
            return -2;
        }
        if (zStream.istate.mode != 13) {
            zStream.istate.mode = 13;
            zStream.istate.marker = 0;
        }
        int i = zStream.avail_in;
        if (i == 0) {
            return -5;
        }
        int i2 = zStream.next_in_index;
        int i3 = zStream.istate.marker;
        while (i != 0 && i3 < 4) {
            if (zStream.next_in[i2] == mark[i3]) {
                i3++;
            } else {
                i3 = zStream.next_in[i2] != 0 ? 0 : 4 - i3;
            }
            i2++;
            i--;
        }
        zStream.total_in += i2 - zStream.next_in_index;
        zStream.next_in_index = i2;
        zStream.avail_in = i;
        zStream.istate.marker = i3;
        if (i3 != 4) {
            return -3;
        }
        long j = zStream.total_in;
        long j2 = zStream.total_out;
        inflateReset(zStream);
        zStream.total_in = j;
        zStream.total_out = j2;
        zStream.istate.mode = 7;
        return 0;
    }
}
