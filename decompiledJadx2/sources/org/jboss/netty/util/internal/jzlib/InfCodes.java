package org.jboss.netty.util.internal.jzlib;

import com.pudutech.mirsdk.config.MapFilePathConfig;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class InfCodes {
    private static final int BADCODE = 9;
    private static final int COPY = 5;
    private static final int DIST = 3;
    private static final int DISTEXT = 4;
    private static final int END = 8;
    private static final int LEN = 1;
    private static final int LENEXT = 2;
    private static final int LIT = 6;
    private static final int START = 0;
    private static final int WASH = 7;
    private static final int[] inflate_mask = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, MapFilePathConfig.MAX_DELETE_CODE_VALUE, 4095, 8191, 16383, 32767, 65535};
    private byte dbits;
    private int dist;
    private int[] dtree;
    private int dtree_index;
    private int get;
    private byte lbits;
    private int len;
    private int lit;
    private int[] ltree;
    private int ltree_index;
    private int mode;
    private int need;
    private int[] tree;
    private int tree_index;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(int i, int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        this.mode = 0;
        this.lbits = (byte) i;
        this.dbits = (byte) i2;
        this.ltree = iArr;
        this.ltree_index = i3;
        this.dtree = iArr2;
        this.dtree_index = i4;
        this.tree = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008f, code lost:
    
        r20.bitb = r3;
        r20.bitk = r4;
        r21.avail_in = r2;
        r21.total_in += r1 - r21.next_in_index;
        r21.next_in_index = r1;
        r20.write = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a7, code lost:
    
        return r20.inflate_flush(r21, r11);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0025. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0290 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0281 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0163 A[LOOP:2: B:36:0x0161->B:37:0x0163, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x023b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int proc(InfBlocks infBlocks, ZStream zStream, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = zStream.next_in_index;
        int i9 = zStream.avail_in;
        int i10 = infBlocks.bitb;
        int i11 = infBlocks.bitk;
        int i12 = infBlocks.write;
        int i13 = 1;
        int i14 = i12 < infBlocks.read ? (infBlocks.read - i12) - 1 : infBlocks.end - i12;
        int i15 = 0;
        int i16 = i;
        while (true) {
            switch (this.mode) {
                case 0:
                    if (i14 >= 258 && i9 >= 10) {
                        infBlocks.bitb = i10;
                        infBlocks.bitk = i11;
                        zStream.avail_in = i9;
                        zStream.total_in += i8 - zStream.next_in_index;
                        zStream.next_in_index = i8;
                        infBlocks.write = i12;
                        i16 = inflate_fast(this.lbits, this.dbits, this.ltree, this.ltree_index, this.dtree, this.dtree_index, infBlocks, zStream);
                        i8 = zStream.next_in_index;
                        i9 = zStream.avail_in;
                        i10 = infBlocks.bitb;
                        i11 = infBlocks.bitk;
                        i12 = infBlocks.write;
                        i14 = i12 < infBlocks.read ? (infBlocks.read - i12) - i13 : infBlocks.end - i12;
                        if (i16 != 0) {
                            this.mode = i16 != i13 ? 9 : 7;
                        }
                    }
                    this.need = this.lbits;
                    this.tree = this.ltree;
                    this.tree_index = this.ltree_index;
                    this.mode = i13;
                    i3 = this.need;
                    while (i11 < i3) {
                        if (i9 == 0) {
                            infBlocks.bitb = i10;
                            infBlocks.bitk = i11;
                            zStream.avail_in = i9;
                            zStream.total_in += i8 - zStream.next_in_index;
                            zStream.next_in_index = i8;
                            infBlocks.write = i12;
                            return infBlocks.inflate_flush(zStream, i16);
                        }
                        i9--;
                        i10 |= (zStream.next_in[i8] & 255) << i11;
                        i11 += 8;
                        i16 = i15;
                        i8++;
                    }
                    int i17 = (this.tree_index + (inflate_mask[i3] & i10)) * 3;
                    int[] iArr = this.tree;
                    int i18 = i17 + 1;
                    i10 >>>= iArr[i18];
                    i11 -= iArr[i18];
                    i4 = iArr[i17];
                    if (i4 != 0) {
                        this.lit = iArr[i17 + 2];
                        this.mode = 6;
                    } else if ((i4 & 16) != 0) {
                        this.get = i4 & 15;
                        this.len = iArr[i17 + 2];
                        this.mode = 2;
                    } else if ((i4 & 64) == 0) {
                        this.need = i4;
                        this.tree_index = (i17 / 3) + iArr[i17 + 2];
                    } else if ((i4 & 32) != 0) {
                        this.mode = 7;
                    } else {
                        this.mode = 9;
                        zStream.msg = "invalid literal/length code";
                        infBlocks.bitb = i10;
                        infBlocks.bitk = i11;
                        zStream.avail_in = i9;
                        zStream.total_in += i8 - zStream.next_in_index;
                        zStream.next_in_index = i8;
                        infBlocks.write = i12;
                        return infBlocks.inflate_flush(zStream, -3);
                    }
                    i13 = 1;
                    break;
                case 1:
                    i3 = this.need;
                    while (i11 < i3) {
                    }
                    int i172 = (this.tree_index + (inflate_mask[i3] & i10)) * 3;
                    int[] iArr2 = this.tree;
                    int i182 = i172 + 1;
                    i10 >>>= iArr2[i182];
                    i11 -= iArr2[i182];
                    i4 = iArr2[i172];
                    if (i4 != 0) {
                    }
                    i13 = 1;
                    break;
                case 2:
                    int i19 = this.get;
                    while (i11 < i19) {
                        if (i9 == 0) {
                            infBlocks.bitb = i10;
                            infBlocks.bitk = i11;
                            zStream.avail_in = i9;
                            zStream.total_in += i8 - zStream.next_in_index;
                            zStream.next_in_index = i8;
                            infBlocks.write = i12;
                            return infBlocks.inflate_flush(zStream, i16);
                        }
                        i9--;
                        i10 |= (zStream.next_in[i8] & 255) << i11;
                        i11 += 8;
                        i16 = i15;
                        i8++;
                    }
                    this.len += i10 & inflate_mask[i19];
                    i10 >>= i19;
                    i11 -= i19;
                    this.need = this.dbits;
                    this.tree = this.dtree;
                    this.tree_index = this.dtree_index;
                    this.mode = 3;
                    i5 = this.need;
                    while (i11 < i5) {
                        if (i9 == 0) {
                            infBlocks.bitb = i10;
                            infBlocks.bitk = i11;
                            zStream.avail_in = i9;
                            zStream.total_in += i8 - zStream.next_in_index;
                            zStream.next_in_index = i8;
                            infBlocks.write = i12;
                            return infBlocks.inflate_flush(zStream, i16);
                        }
                        i9--;
                        i10 |= (zStream.next_in[i8] & 255) << i11;
                        i11 += 8;
                        i16 = i15;
                        i8++;
                    }
                    int i20 = (this.tree_index + (inflate_mask[i5] & i10)) * 3;
                    int[] iArr3 = this.tree;
                    int i21 = i20 + 1;
                    i10 >>= iArr3[i21];
                    i11 -= iArr3[i21];
                    i6 = iArr3[i20];
                    if ((i6 & 16) == 0) {
                        this.get = i6 & 15;
                        this.dist = iArr3[i20 + 2];
                        this.mode = 4;
                    } else if ((i6 & 64) == 0) {
                        this.need = i6;
                        this.tree_index = (i20 / 3) + iArr3[i20 + 2];
                    } else {
                        this.mode = 9;
                        zStream.msg = "invalid distance code";
                        infBlocks.bitb = i10;
                        infBlocks.bitk = i11;
                        zStream.avail_in = i9;
                        zStream.total_in += i8 - zStream.next_in_index;
                        zStream.next_in_index = i8;
                        infBlocks.write = i12;
                        return infBlocks.inflate_flush(zStream, -3);
                    }
                case 3:
                    i5 = this.need;
                    while (i11 < i5) {
                    }
                    int i202 = (this.tree_index + (inflate_mask[i5] & i10)) * 3;
                    int[] iArr32 = this.tree;
                    int i212 = i202 + 1;
                    i10 >>= iArr32[i212];
                    i11 -= iArr32[i212];
                    i6 = iArr32[i202];
                    if ((i6 & 16) == 0) {
                    }
                    break;
                case 4:
                    int i22 = this.get;
                    while (i11 < i22) {
                        if (i9 == 0) {
                            infBlocks.bitb = i10;
                            infBlocks.bitk = i11;
                            zStream.avail_in = i9;
                            zStream.total_in += i8 - zStream.next_in_index;
                            zStream.next_in_index = i8;
                            infBlocks.write = i12;
                            return infBlocks.inflate_flush(zStream, i16);
                        }
                        i9--;
                        i10 |= (zStream.next_in[i8] & 255) << i11;
                        i11 += 8;
                        i16 = i15;
                        i8++;
                    }
                    this.dist += inflate_mask[i22] & i10;
                    i10 >>= i22;
                    i11 -= i22;
                    this.mode = 5;
                    i7 = i12 - this.dist;
                    while (i7 < 0) {
                        i7 += infBlocks.end;
                    }
                    while (this.len != 0) {
                        if (i14 == 0) {
                            if (i12 == infBlocks.end && infBlocks.read != 0) {
                                i14 = infBlocks.read > 0 ? (infBlocks.read - i15) - i13 : infBlocks.end - i15;
                                i12 = i15;
                            }
                            if (i14 == 0) {
                                infBlocks.write = i12;
                                i16 = infBlocks.inflate_flush(zStream, i16);
                                i12 = infBlocks.write;
                                i14 = i12 < infBlocks.read ? (infBlocks.read - i12) - i13 : infBlocks.end - i12;
                                if (i12 == infBlocks.end && infBlocks.read != 0) {
                                    i14 = infBlocks.read > 0 ? (infBlocks.read - i15) - i13 : infBlocks.end - i15;
                                    i12 = i15;
                                }
                                if (i14 == 0) {
                                    infBlocks.bitb = i10;
                                    infBlocks.bitk = i11;
                                    zStream.avail_in = i9;
                                    zStream.total_in += i8 - zStream.next_in_index;
                                    zStream.next_in_index = i8;
                                    infBlocks.write = i12;
                                    return infBlocks.inflate_flush(zStream, i16);
                                }
                            }
                        }
                        int i23 = i12 + 1;
                        int i24 = i7 + 1;
                        infBlocks.window[i12] = infBlocks.window[i7];
                        i14--;
                        i7 = i24 == infBlocks.end ? 0 : i24;
                        this.len -= i13;
                        i12 = i23;
                        i15 = 0;
                    }
                    this.mode = i15;
                    break;
                case 5:
                    i7 = i12 - this.dist;
                    while (i7 < 0) {
                    }
                    while (this.len != 0) {
                    }
                    this.mode = i15;
                case 6:
                    if (i14 == 0) {
                        if (i12 == infBlocks.end && infBlocks.read != 0) {
                            i14 = infBlocks.read > 0 ? (infBlocks.read - i15) - i13 : infBlocks.end - i15;
                            i12 = i15;
                        }
                        if (i14 == 0) {
                            infBlocks.write = i12;
                            int inflate_flush = infBlocks.inflate_flush(zStream, i16);
                            int i25 = infBlocks.write;
                            int i26 = i25 < infBlocks.read ? (infBlocks.read - i25) - i13 : infBlocks.end - i25;
                            if (i25 != infBlocks.end || infBlocks.read == 0) {
                                int i27 = i26;
                                i2 = i25;
                                i14 = i27;
                            } else {
                                i14 = infBlocks.read > 0 ? (infBlocks.read - i15) - i13 : infBlocks.end - i15;
                                i2 = i15;
                            }
                            if (i14 == 0) {
                                infBlocks.bitb = i10;
                                infBlocks.bitk = i11;
                                zStream.avail_in = i9;
                                zStream.total_in += i8 - zStream.next_in_index;
                                zStream.next_in_index = i8;
                                infBlocks.write = i2;
                                return infBlocks.inflate_flush(zStream, inflate_flush);
                            }
                            i12 = i2;
                        }
                    }
                    infBlocks.window[i12] = (byte) this.lit;
                    i14--;
                    this.mode = i15;
                    i12++;
                    i16 = i15;
                    break;
                case 7:
                    if (i11 > 7) {
                        i11 -= 8;
                        i9++;
                        i8--;
                    }
                    infBlocks.write = i12;
                    int inflate_flush2 = infBlocks.inflate_flush(zStream, i16);
                    int i28 = infBlocks.write;
                    if (infBlocks.read != infBlocks.write) {
                        infBlocks.bitb = i10;
                        infBlocks.bitk = i11;
                        zStream.avail_in = i9;
                        zStream.total_in += i8 - zStream.next_in_index;
                        zStream.next_in_index = i8;
                        infBlocks.write = i28;
                        return infBlocks.inflate_flush(zStream, inflate_flush2);
                    }
                    this.mode = 8;
                    i12 = i28;
                    break;
                case 8:
                    break;
                case 9:
                    infBlocks.bitb = i10;
                    infBlocks.bitk = i11;
                    zStream.avail_in = i9;
                    zStream.total_in += i8 - zStream.next_in_index;
                    zStream.next_in_index = i8;
                    infBlocks.write = i12;
                    return infBlocks.inflate_flush(zStream, -3);
                default:
                    infBlocks.bitb = i10;
                    infBlocks.bitk = i11;
                    zStream.avail_in = i9;
                    zStream.total_in += i8 - zStream.next_in_index;
                    zStream.next_in_index = i8;
                    infBlocks.write = i12;
                    return infBlocks.inflate_flush(zStream, -2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x01cc, code lost:
    
        r7 = r29.avail_in - r3;
        r8 = r5 >> 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x01d1, code lost:
    
        if (r8 >= r7) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x01d4, code lost:
    
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x01d5, code lost:
    
        r28.bitb = r4;
        r28.bitk = r5 - (r8 << 3);
        r29.avail_in = r3 + r8;
        r29.total_in += r2 - r29.next_in_index;
        r29.next_in_index = r2 - r8;
        r28.write = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x01ee, code lost:
    
        return 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01cc A[ADDED_TO_REGION, EDGE_INSN: B:25:0x01cc->B:17:0x01cc BREAK  A[LOOP:0: B:5:0x0021->B:24:0x0021], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static int inflate_fast(int i, int i2, int[] iArr, int i3, int[] iArr2, int i4, InfBlocks infBlocks, ZStream zStream) {
        int i5;
        int i6;
        int i7 = zStream.next_in_index;
        int i8 = zStream.avail_in;
        int i9 = infBlocks.bitb;
        int i10 = infBlocks.bitk;
        int i11 = infBlocks.write;
        int i12 = i11 < infBlocks.read ? (infBlocks.read - i11) - 1 : infBlocks.end - i11;
        int[] iArr3 = inflate_mask;
        int i13 = iArr3[i];
        int i14 = iArr3[i2];
        while (true) {
            if (i10 < 20) {
                i8--;
                i9 |= (zStream.next_in[i7] & 255) << i10;
                i10 += 8;
                i7++;
            } else {
                int i15 = i9 & i13;
                int i16 = (i3 + i15) * 3;
                int i17 = iArr[i16];
                if (i17 == 0) {
                    int i18 = i16 + 1;
                    i9 >>= iArr[i18];
                    i10 -= iArr[i18];
                    i6 = i11 + 1;
                    infBlocks.window[i11] = (byte) iArr[i16 + 2];
                    i12--;
                    i11 = i6;
                    if (i12 >= 258 || i8 < 10) {
                        break;
                    }
                }
                do {
                    int i19 = i16 + 1;
                    i9 >>= iArr[i19];
                    i10 -= iArr[i19];
                    if ((i17 & 16) != 0) {
                        int i20 = i17 & 15;
                        int i21 = iArr[i16 + 2] + (inflate_mask[i20] & i9);
                        int i22 = i9 >> i20;
                        int i23 = i10 - i20;
                        while (i23 < 15) {
                            i8--;
                            i22 |= (zStream.next_in[i7] & 255) << i23;
                            i23 += 8;
                            i7++;
                        }
                        int i24 = i22 & i14;
                        int i25 = (i4 + i24) * 3;
                        int i26 = iArr2[i25];
                        while (true) {
                            int i27 = i25 + 1;
                            i22 >>= iArr2[i27];
                            i23 -= iArr2[i27];
                            if ((i26 & 16) != 0) {
                                int i28 = i26 & 15;
                                int i29 = i7;
                                int i30 = i8;
                                while (i23 < i28) {
                                    i30--;
                                    i22 |= (zStream.next_in[i29] & 255) << i23;
                                    i23 += 8;
                                    i29++;
                                }
                                int i31 = iArr2[i25 + 2] + (inflate_mask[i28] & i22);
                                int i32 = i22 >> i28;
                                int i33 = i23 - i28;
                                int i34 = i12 - i21;
                                if (i11 >= i31) {
                                    int i35 = i11 - i31;
                                    int i36 = i11 - i35;
                                    if (i36 > 0 && 2 > i36) {
                                        int i37 = i11 + 1;
                                        int i38 = i35 + 1;
                                        infBlocks.window[i11] = infBlocks.window[i35];
                                        i11 = i37 + 1;
                                        infBlocks.window[i37] = infBlocks.window[i38];
                                        i21 -= 2;
                                        i5 = i38 + 1;
                                    } else {
                                        System.arraycopy(infBlocks.window, i35, infBlocks.window, i11, 2);
                                        i11 += 2;
                                        i5 = i35 + 2;
                                        i21 -= 2;
                                    }
                                } else {
                                    i5 = i11 - i31;
                                    do {
                                        i5 += infBlocks.end;
                                    } while (i5 < 0);
                                    int i39 = infBlocks.end - i5;
                                    if (i21 > i39) {
                                        i21 -= i39;
                                        int i40 = i11 - i5;
                                        if (i40 <= 0 || i39 <= i40) {
                                            System.arraycopy(infBlocks.window, i5, infBlocks.window, i11, i39);
                                            i11 += i39;
                                        } else {
                                            while (true) {
                                                int i41 = i5 + 1;
                                                infBlocks.window[i11] = infBlocks.window[i5];
                                                i39--;
                                                i11++;
                                                if (i39 == 0) {
                                                    break;
                                                }
                                                i5 = i41;
                                            }
                                        }
                                        i5 = 0;
                                    }
                                }
                                int i42 = i11 - i5;
                                if (i42 <= 0 || i21 <= i42) {
                                    System.arraycopy(infBlocks.window, i5, infBlocks.window, i11, i21);
                                    i11 += i21;
                                } else {
                                    while (true) {
                                        int i43 = i5 + 1;
                                        infBlocks.window[i11] = infBlocks.window[i5];
                                        i21--;
                                        i11++;
                                        if (i21 == 0) {
                                            break;
                                        }
                                        i5 = i43;
                                    }
                                }
                                i8 = i30;
                                i7 = i29;
                                i9 = i32;
                                i10 = i33;
                                i12 = i34;
                            } else if ((i26 & 64) == 0) {
                                i24 = i24 + iArr2[i25 + 2] + (inflate_mask[i26] & i22);
                                i25 = (i4 + i24) * 3;
                                i26 = iArr2[i25];
                            } else {
                                zStream.msg = "invalid distance code";
                                int i44 = zStream.avail_in - i8;
                                int i45 = i23 >> 3;
                                if (i45 >= i44) {
                                    i45 = i44;
                                }
                                infBlocks.bitb = i22;
                                infBlocks.bitk = i23 - (i45 << 3);
                                zStream.avail_in = i8 + i45;
                                zStream.total_in += r2 - zStream.next_in_index;
                                zStream.next_in_index = i7 - i45;
                                infBlocks.write = i11;
                                return -3;
                            }
                        }
                        if (i12 >= 258) {
                            break;
                        }
                        break;
                    }
                    if ((i17 & 64) != 0) {
                        if ((i17 & 32) != 0) {
                            int i46 = zStream.avail_in - i8;
                            int i47 = i10 >> 3;
                            if (i47 >= i46) {
                                i47 = i46;
                            }
                            infBlocks.bitb = i9;
                            infBlocks.bitk = i10 - (i47 << 3);
                            zStream.avail_in = i8 + i47;
                            zStream.total_in += r2 - zStream.next_in_index;
                            zStream.next_in_index = i7 - i47;
                            infBlocks.write = i11;
                            return 1;
                        }
                        zStream.msg = "invalid literal/length code";
                        int i48 = zStream.avail_in - i8;
                        int i49 = i10 >> 3;
                        if (i49 >= i48) {
                            i49 = i48;
                        }
                        infBlocks.bitb = i9;
                        infBlocks.bitk = i10 - (i49 << 3);
                        zStream.avail_in = i8 + i49;
                        zStream.total_in += r2 - zStream.next_in_index;
                        zStream.next_in_index = i7 - i49;
                        infBlocks.write = i11;
                        return -3;
                    }
                    i15 = i15 + iArr[i16 + 2] + (inflate_mask[i17] & i9);
                    i16 = (i3 + i15) * 3;
                    i17 = iArr[i16];
                } while (i17 != 0);
                int i50 = i16 + 1;
                i9 >>= iArr[i50];
                i10 -= iArr[i50];
                i6 = i11 + 1;
                infBlocks.window[i11] = (byte) iArr[i16 + 2];
                i12--;
                i11 = i6;
                if (i12 >= 258) {
                }
            }
        }
    }
}
