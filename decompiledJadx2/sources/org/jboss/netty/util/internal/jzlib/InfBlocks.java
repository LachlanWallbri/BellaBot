package org.jboss.netty.util.internal.jzlib;

import com.pudutech.mirsdk.config.MapFilePathConfig;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class InfBlocks {
    private static final int BAD = 9;
    private static final int BTREE = 4;
    private static final int CODES = 6;
    private static final int DONE = 8;
    private static final int DRY = 7;
    private static final int DTREE = 5;
    private static final int LENS = 1;
    private static final int STORED = 2;
    private static final int TABLE = 3;
    private static final int TYPE = 0;
    int bitb;
    int bitk;
    private int[] blens;
    private long check;
    private final Object checkfn;
    final int end;
    private int index;
    private int last;
    private int left;
    int read;
    private int table;
    byte[] window;
    int write;
    private static final int[] inflate_mask = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, MapFilePathConfig.MAX_DELETE_CODE_VALUE, 4095, 8191, 16383, 32767, 65535};
    private static final int[] border = {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    /* renamed from: bb */
    private final int[] f10057bb = new int[1];

    /* renamed from: tb */
    private final int[] f10058tb = new int[1];
    private final InfCodes codes = new InfCodes();
    private final InfTree inftree = new InfTree();
    private int[] hufts = new int[4320];
    private int mode = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InfBlocks(ZStream zStream, Object obj, int i) {
        this.window = new byte[i];
        this.end = i;
        this.checkfn = obj;
        reset(zStream, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset(ZStream zStream, long[] jArr) {
        if (jArr != null) {
            jArr[0] = this.check;
        }
        this.mode = 0;
        this.bitk = 0;
        this.bitb = 0;
        this.write = 0;
        this.read = 0;
        if (this.checkfn != null) {
            long adler32 = Adler32.adler32(0L, null, 0, 0);
            this.check = adler32;
            zStream.adler = adler32;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x03b1, code lost:
    
        r31.blens = null;
        r31.mode = 9;
        r32.msg = "invalid bit length repeat";
        r31.bitb = r8;
        r31.bitk = r2;
        r32.avail_in = r3;
        r32.total_in += r9 - r32.next_in_index;
        r32.next_in_index = r9;
        r31.write = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x03d4, code lost:
    
        return inflate_flush(r32, r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0272, code lost:
    
        r31.write = r14;
        r5 = inflate_flush(r32, r5);
        r14 = r31.write;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x027c, code lost:
    
        if (r31.read == r14) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x027e, code lost:
    
        r31.bitb = r3;
        r31.bitk = r4;
        r32.avail_in = r2;
        r32.total_in += r1 - r32.next_in_index;
        r32.next_in_index = r1;
        r31.write = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x03d8, code lost:
    
        r31.mode = r5;
        r32.msg = "too many length or distance symbols";
        r31.bitb = r3;
        r31.bitk = r4;
        r32.avail_in = r2;
        r32.total_in += r1 - r32.next_in_index;
        r32.next_in_index = r1;
        r31.write = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x03f6, code lost:
    
        return inflate_flush(r32, r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0296, code lost:
    
        return inflate_flush(r32, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0297, code lost:
    
        r31.mode = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x029b, code lost:
    
        r31.bitb = r3;
        r31.bitk = r4;
        r32.avail_in = r2;
        r32.total_in += r1 - r32.next_in_index;
        r32.next_in_index = r1;
        r31.write = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x02b4, code lost:
    
        return inflate_flush(r32, 1);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0191 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x00e0 A[ADDED_TO_REGION, LOOP:7: B:135:0x00e0->B:137:0x00e4, LOOP_START, PHI: r4 r5 r6 r7 r8
      0x00e0: PHI (r4v28 int) = (r4v24 int), (r4v29 int) binds: [B:134:0x00de, B:137:0x00e4] A[DONT_GENERATE, DONT_INLINE]
      0x00e0: PHI (r5v29 int) = (r5v26 int), (r5v30 int) binds: [B:134:0x00de, B:137:0x00e4] A[DONT_GENERATE, DONT_INLINE]
      0x00e0: PHI (r6v40 int) = (r6v38 int), (r6v42 int) binds: [B:134:0x00de, B:137:0x00e4] A[DONT_GENERATE, DONT_INLINE]
      0x00e0: PHI (r7v20 int) = (r7v18 int), (r7v21 int) binds: [B:134:0x00de, B:137:0x00e4] A[DONT_GENERATE, DONT_INLINE]
      0x00e0: PHI (r8v18 int) = (r8v16 int), (r8v20 int) binds: [B:134:0x00de, B:137:0x00e4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x012a A[LOOP:8: B:144:0x0124->B:146:0x012a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0137 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int proc(ZStream zStream, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z;
        int i17 = zStream.next_in_index;
        int i18 = zStream.avail_in;
        int i19 = this.bitb;
        int i20 = this.bitk;
        int i21 = this.write;
        int i22 = this.read;
        int i23 = i21 < i22 ? (i22 - i21) - 1 : this.end - i21;
        int i24 = 0;
        int i25 = i21;
        int i26 = i;
        while (true) {
            int i27 = 9;
            int i28 = -3;
            int i29 = 3;
            switch (this.mode) {
                case 0:
                    for (int i30 = 3; i20 < i30; i30 = 3) {
                        if (i18 == 0) {
                            this.bitb = i19;
                            this.bitk = i20;
                            zStream.avail_in = i18;
                            zStream.total_in += i17 - zStream.next_in_index;
                            zStream.next_in_index = i17;
                            this.write = i25;
                            return inflate_flush(zStream, i26);
                        }
                        i18--;
                        i19 |= (zStream.next_in[i17] & 255) << i20;
                        i20 += 8;
                        i17++;
                        i26 = 0;
                    }
                    int i31 = i19 & 7;
                    this.last = i31 & 1;
                    int i32 = i31 >>> 1;
                    if (i32 == 0) {
                        i24 = 0;
                        int i33 = i20 - 3;
                        int i34 = i33 & 7;
                        i19 = (i19 >>> 3) >>> i34;
                        i20 = i33 - i34;
                        z = true;
                        this.mode = 1;
                    } else if (i32 != 1) {
                        if (i32 == 2) {
                            i19 >>>= 3;
                            i20 -= 3;
                            this.mode = 3;
                        } else if (i32 == 3) {
                            this.mode = 9;
                            zStream.msg = "invalid block type";
                            this.bitb = i19 >>> 3;
                            this.bitk = i20 - 3;
                            zStream.avail_in = i18;
                            zStream.total_in += i17 - zStream.next_in_index;
                            zStream.next_in_index = i17;
                            this.write = i25;
                            return inflate_flush(zStream, -3);
                        }
                        z = true;
                        i24 = 0;
                    } else {
                        int[] iArr = new int[1];
                        int[] iArr2 = new int[1];
                        int[][] iArr3 = new int[1];
                        int[][] iArr4 = new int[1];
                        InfTree.inflate_trees_fixed(iArr, iArr2, iArr3, iArr4);
                        i24 = 0;
                        this.codes.init(iArr[0], iArr2[0], iArr3[0], 0, iArr4[0], 0);
                        i19 >>>= 3;
                        i20 -= 3;
                        this.mode = 6;
                        z = true;
                    }
                case 1:
                    while (i20 < 32) {
                        if (i18 == 0) {
                            this.bitb = i19;
                            this.bitk = i20;
                            zStream.avail_in = i18;
                            zStream.total_in += i17 - zStream.next_in_index;
                            zStream.next_in_index = i17;
                            this.write = i25;
                            return inflate_flush(zStream, i26);
                        }
                        i18--;
                        i19 |= (zStream.next_in[i17] & 255) << i20;
                        i20 += 8;
                        i17++;
                        i26 = 0;
                    }
                    int i35 = 65535 & i19;
                    if ((((~i19) >>> 16) & 65535) != i35) {
                        this.mode = 9;
                        zStream.msg = "invalid stored block lengths";
                        this.bitb = i19;
                        this.bitk = i20;
                        zStream.avail_in = i18;
                        zStream.total_in += i17 - zStream.next_in_index;
                        zStream.next_in_index = i17;
                        this.write = i25;
                        return inflate_flush(zStream, -3);
                    }
                    this.left = i35;
                    this.mode = this.left != 0 ? 2 : this.last != 0 ? 7 : 0;
                    i19 = 0;
                    i20 = 0;
                    i24 = 0;
                case 2:
                    if (i18 == 0) {
                        this.bitb = i19;
                        this.bitk = i20;
                        zStream.avail_in = i18;
                        zStream.total_in += i17 - zStream.next_in_index;
                        zStream.next_in_index = i17;
                        this.write = i25;
                        return inflate_flush(zStream, i26);
                    }
                    if (i23 == 0) {
                        int i36 = this.end;
                        if (i25 == i36 && (i3 = this.read) != 0) {
                            i23 = i3 > 0 ? (i3 + 0) - 1 : i36 + 0;
                            i25 = 0;
                        }
                        if (i23 == 0) {
                            this.write = i25;
                            int inflate_flush = inflate_flush(zStream, i26);
                            int i37 = this.write;
                            int i38 = this.read;
                            i23 = i37 < i38 ? (i38 - i37) - 1 : this.end - i37;
                            int i39 = this.end;
                            if (i37 != i39 || (i2 = this.read) == 0) {
                                i25 = i37;
                            } else {
                                i23 = i2 > 0 ? (i2 + 0) - 1 : i39 + 0;
                                i25 = 0;
                            }
                            if (i23 == 0) {
                                this.bitb = i19;
                                this.bitk = i20;
                                zStream.avail_in = i18;
                                zStream.total_in += i17 - zStream.next_in_index;
                                zStream.next_in_index = i17;
                                this.write = i25;
                                return inflate_flush(zStream, inflate_flush);
                            }
                        }
                    }
                    int i40 = this.left;
                    if (i40 > i18) {
                        i40 = i18;
                    }
                    if (i40 > i23) {
                        i40 = i23;
                    }
                    System.arraycopy(zStream.next_in, i17, this.window, i25, i40);
                    i17 += i40;
                    i18 -= i40;
                    i25 += i40;
                    i23 -= i40;
                    int i41 = this.left - i40;
                    this.left = i41;
                    if (i41 == 0) {
                        this.mode = this.last != 0 ? 7 : 0;
                    }
                    i26 = 0;
                    i24 = 0;
                    break;
                case 3:
                    while (i20 < 14) {
                        if (i18 == 0) {
                            this.bitb = i19;
                            this.bitk = i20;
                            zStream.avail_in = i18;
                            zStream.total_in += i17 - zStream.next_in_index;
                            zStream.next_in_index = i17;
                            this.write = i25;
                            return inflate_flush(zStream, i26);
                        }
                        i18--;
                        i19 |= (zStream.next_in[i17] & 255) << i20;
                        i20 += 8;
                        i17++;
                        i26 = i24;
                    }
                    int i42 = i19 & 16383;
                    this.table = i42;
                    int i43 = i42 & 31;
                    if (i43 <= 29) {
                        int i44 = (i42 >> 5) & 31;
                        if (i44 <= 29) {
                            int i45 = i43 + 258 + i44;
                            int[] iArr5 = this.blens;
                            if (iArr5 == null || iArr5.length < i45) {
                                this.blens = new int[i45];
                            } else {
                                for (int i46 = i24; i46 < i45; i46++) {
                                    this.blens[i46] = i24;
                                }
                            }
                            i19 >>>= 14;
                            i20 -= 14;
                            this.index = i24;
                            this.mode = 4;
                            int i47 = i19;
                            int i48 = i20;
                            int i49 = i26;
                            int i50 = i17;
                            int i51 = i18;
                            while (this.index < (this.table >>> 10) + 4) {
                                while (i48 < i29) {
                                    if (i51 == 0) {
                                        this.bitb = i47;
                                        this.bitk = i48;
                                        zStream.avail_in = i51;
                                        zStream.total_in += i50 - zStream.next_in_index;
                                        zStream.next_in_index = i50;
                                        this.write = i25;
                                        return inflate_flush(zStream, i49);
                                    }
                                    i51--;
                                    i47 |= (zStream.next_in[i50] & 255) << i48;
                                    i48 += 8;
                                    i50++;
                                    i49 = i24;
                                }
                                int[] iArr6 = this.blens;
                                int[] iArr7 = border;
                                int i52 = this.index;
                                this.index = i52 + 1;
                                iArr6[iArr7[i52]] = i47 & 7;
                                i47 >>>= 3;
                                i48 -= 3;
                                i29 = 3;
                            }
                            while (true) {
                                i6 = this.index;
                                if (i6 >= 19) {
                                    int[] iArr8 = this.blens;
                                    int[] iArr9 = border;
                                    this.index = i6 + 1;
                                    iArr8[iArr9[i6]] = i24;
                                } else {
                                    int[] iArr10 = this.f10057bb;
                                    iArr10[i24] = 7;
                                    int i53 = i51;
                                    int i54 = i50;
                                    int i55 = i48;
                                    int inflate_trees_bits = this.inftree.inflate_trees_bits(this.blens, iArr10, this.f10058tb, this.hufts, zStream);
                                    if (inflate_trees_bits != 0) {
                                        if (inflate_trees_bits == -3) {
                                            this.blens = null;
                                            this.mode = 9;
                                        }
                                        this.bitb = i47;
                                        this.bitk = i55;
                                        zStream.avail_in = i53;
                                        zStream.total_in += i54 - zStream.next_in_index;
                                        zStream.next_in_index = i54;
                                        this.write = i25;
                                        return inflate_flush(zStream, inflate_trees_bits);
                                    }
                                    this.index = 0;
                                    this.mode = 5;
                                    i7 = i49;
                                    i8 = i53;
                                    i9 = i47;
                                    i10 = i54;
                                    i11 = i55;
                                    while (true) {
                                        i12 = this.table;
                                        if (this.index >= (i12 & 31) + 258 + ((i12 >> 5) & 31)) {
                                            int i56 = i8;
                                            int i57 = i11;
                                            int i58 = i28;
                                            int i59 = i10;
                                            int i60 = this.f10057bb[0];
                                            while (i57 < i60) {
                                                if (i56 == 0) {
                                                    this.bitb = i9;
                                                    this.bitk = i57;
                                                    zStream.avail_in = i56;
                                                    zStream.total_in += i59 - zStream.next_in_index;
                                                    zStream.next_in_index = i59;
                                                    this.write = i25;
                                                    return inflate_flush(zStream, i7);
                                                }
                                                i56--;
                                                i9 |= (zStream.next_in[i59] & 255) << i57;
                                                i57 += 8;
                                                i59++;
                                                i7 = 0;
                                            }
                                            int[] iArr11 = this.f10058tb;
                                            int i61 = iArr11[0];
                                            int[] iArr12 = this.hufts;
                                            int i62 = iArr11[0];
                                            int[] iArr13 = inflate_mask;
                                            int i63 = iArr12[((i62 + (iArr13[i60] & i9)) * 3) + 1];
                                            int i64 = iArr12[((iArr11[0] + (iArr13[i63] & i9)) * 3) + 2];
                                            if (i64 < 16) {
                                                i16 = i57 - i63;
                                                int[] iArr14 = this.blens;
                                                int i65 = this.index;
                                                this.index = i65 + 1;
                                                iArr14[i65] = i64;
                                                i8 = i56;
                                                i9 >>>= i63;
                                                i10 = i59;
                                            } else {
                                                int i66 = i64 == 18 ? 7 : i64 - 14;
                                                int i67 = i64 == 18 ? 11 : 3;
                                                while (i57 < i63 + i66) {
                                                    if (i56 == 0) {
                                                        this.bitb = i9;
                                                        this.bitk = i57;
                                                        zStream.avail_in = i56;
                                                        zStream.total_in += i59 - zStream.next_in_index;
                                                        zStream.next_in_index = i59;
                                                        this.write = i25;
                                                        return inflate_flush(zStream, i7);
                                                    }
                                                    i56--;
                                                    i9 |= (zStream.next_in[i59] & 255) << i57;
                                                    i57 += 8;
                                                    i59++;
                                                    i7 = 0;
                                                }
                                                int i68 = i9 >>> i63;
                                                int i69 = i67 + (inflate_mask[i66] & i68);
                                                int i70 = i68 >>> i66;
                                                i16 = (i57 - i63) - i66;
                                                int i71 = this.index;
                                                int i72 = this.table;
                                                if (i71 + i69 <= (i72 & 31) + 258 + ((i72 >> 5) & 31) && (i64 != 16 || i71 >= 1)) {
                                                    int i73 = i64 == 16 ? this.blens[i71 - 1] : 0;
                                                    while (true) {
                                                        int i74 = i71 + 1;
                                                        this.blens[i71] = i73;
                                                        i69--;
                                                        if (i69 == 0) {
                                                            this.index = i74;
                                                            i8 = i56;
                                                            i9 = i70;
                                                            i10 = i59;
                                                        } else {
                                                            i71 = i74;
                                                        }
                                                    }
                                                }
                                            }
                                            i11 = i16;
                                            i28 = i58;
                                            i27 = 9;
                                        } else {
                                            this.f10058tb[0] = -1;
                                            int[] iArr15 = new int[1];
                                            int i75 = i7;
                                            int[] iArr16 = new int[1];
                                            int[] iArr17 = {i27};
                                            int[] iArr18 = {6};
                                            int i76 = i8;
                                            int i77 = i10;
                                            int i78 = i11;
                                            int inflate_trees_dynamic = this.inftree.inflate_trees_dynamic((i12 & 31) + 257, ((i12 >> 5) & 31) + 1, this.blens, iArr17, iArr18, iArr15, iArr16, this.hufts, zStream);
                                            if (inflate_trees_dynamic != 0) {
                                                if (inflate_trees_dynamic == -3) {
                                                    this.blens = null;
                                                    this.mode = 9;
                                                }
                                                this.bitb = i9;
                                                this.bitk = i78;
                                                zStream.avail_in = i76;
                                                zStream.total_in += i77 - zStream.next_in_index;
                                                zStream.next_in_index = i77;
                                                this.write = i25;
                                                return inflate_flush(zStream, inflate_trees_dynamic);
                                            }
                                            i13 = i77;
                                            i14 = i78;
                                            InfCodes infCodes = this.codes;
                                            int i79 = iArr17[0];
                                            int i80 = iArr18[0];
                                            int[] iArr19 = this.hufts;
                                            infCodes.init(i79, i80, iArr19, iArr15[0], iArr19, iArr16[0]);
                                            this.mode = 6;
                                            i15 = i76;
                                            i19 = i9;
                                            i26 = i75;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            i5 = 9;
                            i4 = -3;
                            break;
                        }
                    } else {
                        i4 = -3;
                        i5 = 9;
                        break;
                    }
                    break;
                case 4:
                    int i472 = i19;
                    int i482 = i20;
                    int i492 = i26;
                    int i502 = i17;
                    int i512 = i18;
                    while (this.index < (this.table >>> 10) + 4) {
                    }
                    while (true) {
                        i6 = this.index;
                        if (i6 >= 19) {
                        }
                        int[] iArr82 = this.blens;
                        int[] iArr92 = border;
                        this.index = i6 + 1;
                        iArr82[iArr92[i6]] = i24;
                    }
                    break;
                case 5:
                    i10 = i17;
                    i8 = i18;
                    i9 = i19;
                    i11 = i20;
                    i7 = i26;
                    while (true) {
                        i12 = this.table;
                        if (this.index >= (i12 & 31) + 258 + ((i12 >> 5) & 31)) {
                        }
                        i28 = i58;
                        i27 = 9;
                    }
                    break;
                case 6:
                    i13 = i17;
                    i15 = i18;
                    i14 = i20;
                    this.bitb = i19;
                    this.bitk = i14;
                    zStream.avail_in = i15;
                    zStream.total_in += i13 - zStream.next_in_index;
                    zStream.next_in_index = i13;
                    this.write = i25;
                    int proc = this.codes.proc(this, zStream, i26);
                    if (proc != 1) {
                        return inflate_flush(zStream, proc);
                    }
                    i17 = zStream.next_in_index;
                    int i81 = zStream.avail_in;
                    int i82 = this.bitb;
                    int i83 = this.bitk;
                    i25 = this.write;
                    int i84 = this.read;
                    i23 = i25 < i84 ? (i84 - i25) - 1 : this.end - i25;
                    if (this.last == 0) {
                        this.mode = 0;
                        i18 = i81;
                        i19 = i82;
                        i20 = i83;
                        i26 = 0;
                        i24 = 0;
                    } else {
                        this.mode = 7;
                        i18 = i81;
                        i19 = i82;
                        i20 = i83;
                        i26 = 0;
                        break;
                    }
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    this.bitb = i19;
                    this.bitk = i20;
                    zStream.avail_in = i18;
                    zStream.total_in += i17 - zStream.next_in_index;
                    zStream.next_in_index = i17;
                    this.write = i25;
                    return inflate_flush(zStream, -3);
                default:
                    this.bitb = i19;
                    this.bitk = i20;
                    zStream.avail_in = i18;
                    zStream.total_in += i17 - zStream.next_in_index;
                    zStream.next_in_index = i17;
                    this.write = i25;
                    return inflate_flush(zStream, -2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free(ZStream zStream) {
        reset(zStream, null);
        this.window = null;
        this.hufts = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set_dictionary(byte[] bArr, int i, int i2) {
        System.arraycopy(bArr, i, this.window, 0, i2);
        this.write = i2;
        this.read = i2;
    }

    int sync_point() {
        return this.mode == 1 ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int inflate_flush(ZStream zStream, int i) {
        int i2 = zStream.next_out_index;
        int i3 = this.read;
        int i4 = this.write;
        if (i3 > i4) {
            i4 = this.end;
        }
        int i5 = i4 - i3;
        if (i5 > zStream.avail_out) {
            i5 = zStream.avail_out;
        }
        if (i5 != 0 && i == -5) {
            i = 0;
        }
        zStream.avail_out -= i5;
        zStream.total_out += i5;
        if (this.checkfn != null) {
            long adler32 = Adler32.adler32(this.check, this.window, i3, i5);
            this.check = adler32;
            zStream.adler = adler32;
        }
        System.arraycopy(this.window, i3, zStream.next_out, i2, i5);
        int i6 = i2 + i5;
        int i7 = i3 + i5;
        int i8 = this.end;
        if (i7 == i8) {
            if (this.write == i8) {
                this.write = 0;
            }
            int i9 = this.write - 0;
            if (i9 > zStream.avail_out) {
                i9 = zStream.avail_out;
            }
            if (i9 != 0 && i == -5) {
                i = 0;
            }
            zStream.avail_out -= i9;
            zStream.total_out += i9;
            if (this.checkfn != null) {
                long adler322 = Adler32.adler32(this.check, this.window, 0, i9);
                this.check = adler322;
                zStream.adler = adler322;
            }
            System.arraycopy(this.window, 0, zStream.next_out, i6, i9);
            i6 += i9;
            i7 = i9 + 0;
        }
        zStream.next_out_index = i6;
        this.read = i7;
        return i;
    }
}
