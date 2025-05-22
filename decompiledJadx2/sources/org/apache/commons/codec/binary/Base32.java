package org.apache.commons.codec.binary;

import com.google.common.base.Ascii;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, Ascii.f1926US, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, PrinterUtils.BarCode.CODE93, PrinterUtils.BarCode.CODE128, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, ClassDefinitionUtils.OPS_dup, 90, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, Ascii.f1926US, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, Ascii.f1926US};
    private static final byte[] HEX_ENCODE_TABLE = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 65, 66, 67, 68, 69, 70, 71, PrinterUtils.BarCode.CODE93, PrinterUtils.BarCode.CODE128, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, 77, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86};
    private static final int MASK_5BITS = 31;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base32() {
        this(false);
    }

    public Base32(byte b) {
        this(false, b);
    }

    public Base32(boolean z) {
        this(0, null, z, (byte) 61);
    }

    public Base32(boolean z, byte b) {
        this(0, null, z, b);
    }

    public Base32(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base32(int i, byte[] bArr) {
        this(i, bArr, false, (byte) 61);
    }

    public Base32(int i, byte[] bArr, boolean z) {
        this(i, bArr, z, (byte) 61);
    }

    public Base32(int i, byte[] bArr, boolean z, byte b) {
        super(5, 8, i, bArr == null ? 0 : bArr.length, b);
        if (z) {
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;
        }
        if (i <= 0) {
            this.encodeSize = 8;
            this.lineSeparator = null;
        } else {
            if (bArr == null) {
                throw new IllegalArgumentException("lineLength " + i + " > 0, but lineSeparator is null");
            }
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
            }
            this.encodeSize = bArr.length + 8;
            byte[] bArr2 = new byte[bArr.length];
            this.lineSeparator = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        this.decodeSize = this.encodeSize - 1;
        if (isInAlphabet(b) || isWhiteSpace(b)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v26 */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        byte b;
        if (context.eof) {
            return;
        }
        ?? r3 = 1;
        if (i2 < 0) {
            context.eof = true;
        }
        int i3 = 0;
        int i4 = i;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            int i5 = i4 + 1;
            byte b2 = bArr[i4];
            if (b2 == this.pad) {
                context.eof = r3;
                break;
            }
            byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
            if (b2 >= 0) {
                byte[] bArr2 = this.decodeTable;
                if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                    context.modulus = (context.modulus + r3) % 8;
                    context.lbitWorkArea = (context.lbitWorkArea << 5) + b;
                    if (context.modulus == 0) {
                        int i6 = context.pos;
                        context.pos = i6 + 1;
                        ensureBufferSize[i6] = (byte) ((context.lbitWorkArea >> 32) & 255);
                        int i7 = context.pos;
                        context.pos = i7 + 1;
                        ensureBufferSize[i7] = (byte) ((context.lbitWorkArea >> 24) & 255);
                        int i8 = context.pos;
                        context.pos = i8 + 1;
                        ensureBufferSize[i8] = (byte) ((context.lbitWorkArea >> 16) & 255);
                        int i9 = context.pos;
                        context.pos = i9 + 1;
                        ensureBufferSize[i9] = (byte) ((context.lbitWorkArea >> 8) & 255);
                        int i10 = context.pos;
                        context.pos = i10 + 1;
                        ensureBufferSize[i10] = (byte) (context.lbitWorkArea & 255);
                    }
                }
            }
            i3++;
            i4 = i5;
            r3 = 1;
        }
        if (!context.eof || context.modulus < 2) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        switch (context.modulus) {
            case 2:
                int i11 = context.pos;
                context.pos = i11 + 1;
                ensureBufferSize2[i11] = (byte) ((context.lbitWorkArea >> 2) & 255);
                return;
            case 3:
                int i12 = context.pos;
                context.pos = i12 + 1;
                ensureBufferSize2[i12] = (byte) ((context.lbitWorkArea >> 7) & 255);
                return;
            case 4:
                context.lbitWorkArea >>= 4;
                int i13 = context.pos;
                context.pos = i13 + 1;
                ensureBufferSize2[i13] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int i14 = context.pos;
                context.pos = i14 + 1;
                ensureBufferSize2[i14] = (byte) (context.lbitWorkArea & 255);
                return;
            case 5:
                context.lbitWorkArea >>= 1;
                int i15 = context.pos;
                context.pos = i15 + 1;
                ensureBufferSize2[i15] = (byte) ((context.lbitWorkArea >> 16) & 255);
                int i16 = context.pos;
                context.pos = i16 + 1;
                ensureBufferSize2[i16] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int i17 = context.pos;
                context.pos = i17 + 1;
                ensureBufferSize2[i17] = (byte) (context.lbitWorkArea & 255);
                return;
            case 6:
                context.lbitWorkArea >>= 6;
                int i18 = context.pos;
                context.pos = i18 + 1;
                ensureBufferSize2[i18] = (byte) ((context.lbitWorkArea >> 16) & 255);
                int i19 = context.pos;
                context.pos = i19 + 1;
                ensureBufferSize2[i19] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int i20 = context.pos;
                context.pos = i20 + 1;
                ensureBufferSize2[i20] = (byte) (context.lbitWorkArea & 255);
                return;
            case 7:
                context.lbitWorkArea >>= 3;
                int i21 = context.pos;
                context.pos = i21 + 1;
                ensureBufferSize2[i21] = (byte) ((context.lbitWorkArea >> 24) & 255);
                int i22 = context.pos;
                context.pos = i22 + 1;
                ensureBufferSize2[i22] = (byte) ((context.lbitWorkArea >> 16) & 255);
                int i23 = context.pos;
                context.pos = i23 + 1;
                ensureBufferSize2[i23] = (byte) ((context.lbitWorkArea >> 8) & 255);
                int i24 = context.pos;
                context.pos = i24 + 1;
                ensureBufferSize2[i24] = (byte) (context.lbitWorkArea & 255);
                return;
            default:
                throw new IllegalStateException("Impossible modulus " + context.modulus);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                byte[] ensureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + 1) % 5;
                int i4 = i + 1;
                int i5 = bArr[i];
                if (i5 < 0) {
                    i5 += 256;
                }
                context.lbitWorkArea = (context.lbitWorkArea << 8) + i5;
                if (context.modulus == 0) {
                    int i6 = context.pos;
                    context.pos = i6 + 1;
                    ensureBufferSize[i6] = this.encodeTable[((int) (context.lbitWorkArea >> 35)) & 31];
                    int i7 = context.pos;
                    context.pos = i7 + 1;
                    ensureBufferSize[i7] = this.encodeTable[((int) (context.lbitWorkArea >> 30)) & 31];
                    int i8 = context.pos;
                    context.pos = i8 + 1;
                    ensureBufferSize[i8] = this.encodeTable[((int) (context.lbitWorkArea >> 25)) & 31];
                    int i9 = context.pos;
                    context.pos = i9 + 1;
                    ensureBufferSize[i9] = this.encodeTable[((int) (context.lbitWorkArea >> 20)) & 31];
                    int i10 = context.pos;
                    context.pos = i10 + 1;
                    ensureBufferSize[i10] = this.encodeTable[((int) (context.lbitWorkArea >> 15)) & 31];
                    int i11 = context.pos;
                    context.pos = i11 + 1;
                    ensureBufferSize[i11] = this.encodeTable[((int) (context.lbitWorkArea >> 10)) & 31];
                    int i12 = context.pos;
                    context.pos = i12 + 1;
                    ensureBufferSize[i12] = this.encodeTable[((int) (context.lbitWorkArea >> 5)) & 31];
                    int i13 = context.pos;
                    context.pos = i13 + 1;
                    ensureBufferSize[i13] = this.encodeTable[((int) context.lbitWorkArea) & 31];
                    context.currentLinePos += 8;
                    if (this.lineLength > 0 && this.lineLength <= context.currentLinePos) {
                        System.arraycopy(this.lineSeparator, 0, ensureBufferSize, context.pos, this.lineSeparator.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                i3++;
                i = i4;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] ensureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i14 = context.pos;
        int i15 = context.modulus;
        if (i15 != 0) {
            if (i15 == 1) {
                int i16 = context.pos;
                context.pos = i16 + 1;
                ensureBufferSize2[i16] = this.encodeTable[((int) (context.lbitWorkArea >> 3)) & 31];
                int i17 = context.pos;
                context.pos = i17 + 1;
                ensureBufferSize2[i17] = this.encodeTable[((int) (context.lbitWorkArea << 2)) & 31];
                int i18 = context.pos;
                context.pos = i18 + 1;
                ensureBufferSize2[i18] = this.pad;
                int i19 = context.pos;
                context.pos = i19 + 1;
                ensureBufferSize2[i19] = this.pad;
                int i20 = context.pos;
                context.pos = i20 + 1;
                ensureBufferSize2[i20] = this.pad;
                int i21 = context.pos;
                context.pos = i21 + 1;
                ensureBufferSize2[i21] = this.pad;
                int i22 = context.pos;
                context.pos = i22 + 1;
                ensureBufferSize2[i22] = this.pad;
                int i23 = context.pos;
                context.pos = i23 + 1;
                ensureBufferSize2[i23] = this.pad;
            } else if (i15 == 2) {
                int i24 = context.pos;
                context.pos = i24 + 1;
                ensureBufferSize2[i24] = this.encodeTable[((int) (context.lbitWorkArea >> 11)) & 31];
                int i25 = context.pos;
                context.pos = i25 + 1;
                ensureBufferSize2[i25] = this.encodeTable[((int) (context.lbitWorkArea >> 6)) & 31];
                int i26 = context.pos;
                context.pos = i26 + 1;
                ensureBufferSize2[i26] = this.encodeTable[((int) (context.lbitWorkArea >> 1)) & 31];
                int i27 = context.pos;
                context.pos = i27 + 1;
                ensureBufferSize2[i27] = this.encodeTable[((int) (context.lbitWorkArea << 4)) & 31];
                int i28 = context.pos;
                context.pos = i28 + 1;
                ensureBufferSize2[i28] = this.pad;
                int i29 = context.pos;
                context.pos = i29 + 1;
                ensureBufferSize2[i29] = this.pad;
                int i30 = context.pos;
                context.pos = i30 + 1;
                ensureBufferSize2[i30] = this.pad;
                int i31 = context.pos;
                context.pos = i31 + 1;
                ensureBufferSize2[i31] = this.pad;
            } else if (i15 == 3) {
                int i32 = context.pos;
                context.pos = i32 + 1;
                ensureBufferSize2[i32] = this.encodeTable[((int) (context.lbitWorkArea >> 19)) & 31];
                int i33 = context.pos;
                context.pos = i33 + 1;
                ensureBufferSize2[i33] = this.encodeTable[((int) (context.lbitWorkArea >> 14)) & 31];
                int i34 = context.pos;
                context.pos = i34 + 1;
                ensureBufferSize2[i34] = this.encodeTable[((int) (context.lbitWorkArea >> 9)) & 31];
                int i35 = context.pos;
                context.pos = i35 + 1;
                ensureBufferSize2[i35] = this.encodeTable[((int) (context.lbitWorkArea >> 4)) & 31];
                int i36 = context.pos;
                context.pos = i36 + 1;
                ensureBufferSize2[i36] = this.encodeTable[((int) (context.lbitWorkArea << 1)) & 31];
                int i37 = context.pos;
                context.pos = i37 + 1;
                ensureBufferSize2[i37] = this.pad;
                int i38 = context.pos;
                context.pos = i38 + 1;
                ensureBufferSize2[i38] = this.pad;
                int i39 = context.pos;
                context.pos = i39 + 1;
                ensureBufferSize2[i39] = this.pad;
            } else if (i15 == 4) {
                int i40 = context.pos;
                context.pos = i40 + 1;
                ensureBufferSize2[i40] = this.encodeTable[((int) (context.lbitWorkArea >> 27)) & 31];
                int i41 = context.pos;
                context.pos = i41 + 1;
                ensureBufferSize2[i41] = this.encodeTable[((int) (context.lbitWorkArea >> 22)) & 31];
                int i42 = context.pos;
                context.pos = i42 + 1;
                ensureBufferSize2[i42] = this.encodeTable[((int) (context.lbitWorkArea >> 17)) & 31];
                int i43 = context.pos;
                context.pos = i43 + 1;
                ensureBufferSize2[i43] = this.encodeTable[((int) (context.lbitWorkArea >> 12)) & 31];
                int i44 = context.pos;
                context.pos = i44 + 1;
                ensureBufferSize2[i44] = this.encodeTable[((int) (context.lbitWorkArea >> 7)) & 31];
                int i45 = context.pos;
                context.pos = i45 + 1;
                ensureBufferSize2[i45] = this.encodeTable[((int) (context.lbitWorkArea >> 2)) & 31];
                int i46 = context.pos;
                context.pos = i46 + 1;
                ensureBufferSize2[i46] = this.encodeTable[((int) (context.lbitWorkArea << 3)) & 31];
                int i47 = context.pos;
                context.pos = i47 + 1;
                ensureBufferSize2[i47] = this.pad;
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
        context.currentLinePos += context.pos - i14;
        if (this.lineLength <= 0 || context.currentLinePos <= 0) {
            return;
        }
        System.arraycopy(this.lineSeparator, 0, ensureBufferSize2, context.pos, this.lineSeparator.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            if (b < bArr.length && bArr[b] != -1) {
                return true;
            }
        }
        return false;
    }
}
