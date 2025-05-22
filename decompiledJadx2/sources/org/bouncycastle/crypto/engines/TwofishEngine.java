package org.bouncycastle.crypto.engines;

import androidx.core.view.InputDeviceCompat;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;

    /* renamed from: P */
    private static final byte[][] f9433P = {new byte[]{-87, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -77, -24, 4, -3, -93, 118, -102, -110, Byte.MIN_VALUE, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -28, SlipConfig.ESC_ESC, -47, 56, 13, -58, TarConstants.LF_DIR, -104, 24, -9, -20, 108, 67, 117, TarConstants.LF_CONTIG, 38, -6, 19, -108, PrinterUtils.BarCode.CODE93, -14, -48, -117, TarConstants.LF_NORMAL, Constans.CAN_REV_DIS_MODE_SWITCH, 84, -33, BinaryMemcacheOpcodes.GATK, 25, 91, 61, ClassDefinitionUtils.OPS_dup, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, SlipConfig.END, -116, 58, -11, 115, 44, 37, 11, -69, 78, Constans.CAN_REV_UV_SET_RESULT, 107, TarConstants.LF_GNUTYPE_SPARSE, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, -75, -23, -49, ByteSourceJsonBootstrapper.UTF8_BOM_3, -70, -22, 119, 57, -81, TarConstants.LF_CHR, -55, 98, 113, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 121, 9, -83, BinaryMemcacheOpcodes.GATKQ, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, TarConstants.LF_LINK, -62, 39, -112, 32, -10, 96, -1, -106, 92, ClassDefinitionUtils.OPS_return, -85, -98, -100, 82, 27, 95, -109, 10, ByteSourceJsonBootstrapper.UTF8_BOM_1, -111, -123, PrinterUtils.BarCode.CODE128, -18, 45, 79, -113, 59, 71, Constans.CAN_REV_SPRAY_SYS_RESULT, 109, 70, -42, 62, 105, 100, ClassDefinitionUtils.OPS_aload_0, -50, -53, 47, -4, -105, 5, 122, -84, Byte.MAX_VALUE, -43, 26, TarConstants.LF_GNUTYPE_LONGLINK, 14, -89, 90, 40, 20, 63, 41, Constans.CAN_REV_UV_SYS_RESULT, 60, TarConstants.LF_GNUTYPE_LONGNAME, 2, -72, -38, ClassDefinitionUtils.OPS_areturn, 23, 85, Ascii.f1926US, -118, 125, 87, -57, -115, 116, ClassDefinitionUtils.OPS_invokespecial, -60, -97, 114, 126, 21, 34, 18, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 7, -103, TarConstants.LF_BLK, 110, 80, -34, 104, 101, PSSSigner.TRAILER_IMPLICIT, SlipConfig.ESC, -8, -56, -88, 43, 64, SlipConfig.ESC_END, -2, TarConstants.LF_SYMLINK, -92, -54, 16, BinaryMemcacheOpcodes.SASL_AUTH, -16, -45, 93, 15, 0, 111, -99, TarConstants.LF_FIFO, 66, 74, 94, -63, -32}, new byte[]{117, -13, -58, -12, SlipConfig.ESC, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, TarConstants.LF_GNUTYPE_LONGLINK, -42, TarConstants.LF_SYMLINK, -40, -3, TarConstants.LF_CONTIG, 113, -15, -31, TarConstants.LF_NORMAL, 15, -8, 27, Constans.CAN_REV_SPRAY_SYS_RESULT, -6, 6, 63, 94, -70, -82, 91, -118, 0, PSSSigner.TRAILER_IMPLICIT, -99, 109, -63, ClassDefinitionUtils.OPS_return, 14, Byte.MIN_VALUE, 93, -46, -43, -96, Constans.CAN_REV_DIS_MODE_SWITCH, 7, 20, -75, -112, 44, -93, -78, 115, TarConstants.LF_GNUTYPE_LONGNAME, 84, -110, 116, TarConstants.LF_FIFO, 81, 56, ClassDefinitionUtils.OPS_areturn, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, BinaryMemcacheOpcodes.GATKQ, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, ByteSourceJsonBootstrapper.UTF8_BOM_3, 64, -25, 43, -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 29, 61, -16, -34, -77, 11, 114, -89, 28, ByteSourceJsonBootstrapper.UTF8_BOM_1, -47, TarConstants.LF_GNUTYPE_SPARSE, 62, -113, TarConstants.LF_CHR, 38, 95, -20, 118, ClassDefinitionUtils.OPS_aload_0, PrinterUtils.BarCode.CODE128, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, Constans.CAN_REV_UV_SYS_RESULT, -18, BinaryMemcacheOpcodes.SASL_AUTH, -60, 26, -21, -39, -59, 57, -103, -51, -83, TarConstants.LF_LINK, -117, 1, 24, BinaryMemcacheOpcodes.GATK, SlipConfig.ESC_ESC, Ascii.f1926US, 78, 45, -7, PrinterUtils.BarCode.CODE93, 79, -14, 101, -114, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 92, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 25, -115, -27, -104, 87, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, Byte.MAX_VALUE, 5, 100, -81, 99, -74, -2, -11, ClassDefinitionUtils.OPS_invokespecial, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, ClassDefinitionUtils.OPS_dup, -88, 10, -98, 110, 71, -33, TarConstants.LF_BLK, TarConstants.LF_DIR, 106, -49, SlipConfig.ESC_END, 34, -55, SlipConfig.END, -101, Constans.CAN_REV_UV_SET_RESULT, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private int[] gSBox;
    private int[] gSubKeys;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (int i = 0; i < 256; i++) {
            int i2 = f9433P[0][i] & 255;
            iArr[0] = i2;
            iArr2[0] = Mx_X(i2) & 255;
            iArr3[0] = Mx_Y(i2) & 255;
            int i3 = f9433P[1][i] & 255;
            iArr[1] = i3;
            iArr2[1] = Mx_X(i3) & 255;
            iArr3[1] = Mx_Y(i3) & 255;
            this.gMDS0[i] = iArr[1] | (iArr2[1] << 8) | (iArr3[1] << 16) | (iArr3[1] << 24);
            this.gMDS1[i] = iArr3[0] | (iArr3[0] << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            this.gMDS2[i] = (iArr3[1] << 24) | iArr2[1] | (iArr3[1] << 8) | (iArr[1] << 16);
            this.gMDS3[i] = iArr2[0] | (iArr[0] << 8) | (iArr3[0] << 16) | (iArr2[0] << 24);
        }
    }

    private void Bits32ToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
    }

    private int BytesTo32Bits(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private int F32(int i, int[] iArr) {
        int i2;
        int i3;
        int m4098b0 = m4098b0(i);
        int m4099b1 = m4099b1(i);
        int m4100b2 = m4100b2(i);
        int m4101b3 = m4101b3(i);
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = this.k64Cnt & 3;
        if (i8 != 0) {
            if (i8 == 1) {
                i2 = (this.gMDS0[(f9433P[0][m4098b0] & 255) ^ m4098b0(i4)] ^ this.gMDS1[(f9433P[0][m4099b1] & 255) ^ m4099b1(i4)]) ^ this.gMDS2[(f9433P[1][m4100b2] & 255) ^ m4100b2(i4)];
                i3 = this.gMDS3[(f9433P[1][m4101b3] & 255) ^ m4101b3(i4)];
                return i2 ^ i3;
            }
            if (i8 != 2) {
                if (i8 != 3) {
                    return 0;
                }
            }
            int[] iArr2 = this.gMDS0;
            byte[][] bArr = f9433P;
            int i9 = iArr2[(bArr[0][(bArr[0][m4098b0] & 255) ^ m4098b0(i5)] & 255) ^ m4098b0(i4)];
            int[] iArr3 = this.gMDS1;
            byte[][] bArr2 = f9433P;
            int i10 = i9 ^ iArr3[(bArr2[0][(bArr2[1][m4099b1] & 255) ^ m4099b1(i5)] & 255) ^ m4099b1(i4)];
            int[] iArr4 = this.gMDS2;
            byte[][] bArr3 = f9433P;
            i2 = i10 ^ iArr4[(bArr3[1][(bArr3[0][m4100b2] & 255) ^ m4100b2(i5)] & 255) ^ m4100b2(i4)];
            int[] iArr5 = this.gMDS3;
            byte[][] bArr4 = f9433P;
            i3 = iArr5[(bArr4[1][(bArr4[1][m4101b3] & 255) ^ m4101b3(i5)] & 255) ^ m4101b3(i4)];
            return i2 ^ i3;
        }
        m4098b0 = (f9433P[1][m4098b0] & 255) ^ m4098b0(i7);
        m4099b1 = (f9433P[0][m4099b1] & 255) ^ m4099b1(i7);
        m4100b2 = (f9433P[0][m4100b2] & 255) ^ m4100b2(i7);
        m4101b3 = (f9433P[1][m4101b3] & 255) ^ m4101b3(i7);
        m4098b0 = m4098b0(i6) ^ (f9433P[1][m4098b0] & 255);
        m4099b1 = m4099b1(i6) ^ (f9433P[1][m4099b1] & 255);
        m4100b2 = m4100b2(i6) ^ (f9433P[0][m4100b2] & 255);
        m4101b3 = (f9433P[0][m4101b3] & 255) ^ m4101b3(i6);
        int[] iArr22 = this.gMDS0;
        byte[][] bArr5 = f9433P;
        int i92 = iArr22[(bArr5[0][(bArr5[0][m4098b0] & 255) ^ m4098b0(i5)] & 255) ^ m4098b0(i4)];
        int[] iArr32 = this.gMDS1;
        byte[][] bArr22 = f9433P;
        int i102 = i92 ^ iArr32[(bArr22[0][(bArr22[1][m4099b1] & 255) ^ m4099b1(i5)] & 255) ^ m4099b1(i4)];
        int[] iArr42 = this.gMDS2;
        byte[][] bArr32 = f9433P;
        i2 = i102 ^ iArr42[(bArr32[1][(bArr32[0][m4100b2] & 255) ^ m4100b2(i5)] & 255) ^ m4100b2(i4)];
        int[] iArr52 = this.gMDS3;
        byte[][] bArr42 = f9433P;
        i3 = iArr52[(bArr42[1][(bArr42[1][m4101b3] & 255) ^ m4101b3(i5)] & 255) ^ m4101b3(i4)];
        return i2 ^ i3;
    }

    private int Fe32_0(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 24) & 255) * 2) + InputDeviceCompat.SOURCE_DPAD] ^ ((iArr[((i & 255) * 2) + 0] ^ iArr[(((i >>> 8) & 255) * 2) + 1]) ^ iArr[(((i >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int i) {
        int[] iArr = this.gSBox;
        return iArr[(((i >>> 16) & 255) * 2) + InputDeviceCompat.SOURCE_DPAD] ^ ((iArr[(((i >>> 24) & 255) * 2) + 0] ^ iArr[((i & 255) * 2) + 1]) ^ iArr[(((i >>> 8) & 255) * 2) + 512]);
    }

    private int LFSR1(int i) {
        return ((i & 1) != 0 ? 180 : 0) ^ (i >> 1);
    }

    private int LFSR2(int i) {
        return ((i >> 2) ^ ((i & 2) != 0 ? 180 : 0)) ^ ((i & 1) != 0 ? 90 : 0);
    }

    private int Mx_X(int i) {
        return i ^ LFSR2(i);
    }

    private int Mx_Y(int i) {
        return LFSR2(i) ^ (LFSR1(i) ^ i);
    }

    private int RS_MDS_Encode(int i, int i2) {
        int i3 = i2;
        for (int i4 = 0; i4 < 4; i4++) {
            i3 = RS_rem(i3);
        }
        int i5 = i ^ i3;
        for (int i6 = 0; i6 < 4; i6++) {
            i5 = RS_rem(i5);
        }
        return i5;
    }

    private int RS_rem(int i) {
        int i2 = (i >>> 24) & 255;
        int i3 = ((i2 << 1) ^ ((i2 & 128) != 0 ? RS_GF_FDBK : 0)) & 255;
        int i4 = ((i2 >>> 1) ^ ((i2 & 1) != 0 ? 166 : 0)) ^ i3;
        return ((((i << 8) ^ (i4 << 24)) ^ (i3 << 16)) ^ (i4 << 8)) ^ i2;
    }

    /* renamed from: b0 */
    private int m4098b0(int i) {
        return i & 255;
    }

    /* renamed from: b1 */
    private int m4099b1(int i) {
        return (i >>> 8) & 255;
    }

    /* renamed from: b2 */
    private int m4100b2(int i) {
        return (i >>> 16) & 255;
    }

    /* renamed from: b3 */
    private int m4101b3(int i) {
        return (i >>> 24) & 255;
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int BytesTo32Bits = BytesTo32Bits(bArr, i) ^ this.gSubKeys[4];
        int BytesTo32Bits2 = BytesTo32Bits(bArr, i + 4) ^ this.gSubKeys[5];
        int BytesTo32Bits3 = BytesTo32Bits(bArr, i + 8) ^ this.gSubKeys[6];
        int i3 = 39;
        int i4 = BytesTo32Bits2;
        int i5 = BytesTo32Bits;
        int BytesTo32Bits4 = BytesTo32Bits(bArr, i + 12) ^ this.gSubKeys[7];
        for (int i6 = 0; i6 < 16; i6 += 2) {
            int Fe32_0 = Fe32_0(i5);
            int Fe32_3 = Fe32_3(i4);
            int[] iArr = this.gSubKeys;
            int i7 = i3 - 1;
            int i8 = BytesTo32Bits4 ^ (((Fe32_3 * 2) + Fe32_0) + iArr[i3]);
            int i9 = i7 - 1;
            BytesTo32Bits3 = ((BytesTo32Bits3 >>> 31) | (BytesTo32Bits3 << 1)) ^ ((Fe32_0 + Fe32_3) + iArr[i7]);
            BytesTo32Bits4 = (i8 << 31) | (i8 >>> 1);
            int Fe32_02 = Fe32_0(BytesTo32Bits3);
            int Fe32_32 = Fe32_3(BytesTo32Bits4);
            int[] iArr2 = this.gSubKeys;
            int i10 = i9 - 1;
            int i11 = i4 ^ (((Fe32_32 * 2) + Fe32_02) + iArr2[i9]);
            i3 = i10 - 1;
            i5 = ((i5 >>> 31) | (i5 << 1)) ^ ((Fe32_02 + Fe32_32) + iArr2[i10]);
            i4 = (i11 << 31) | (i11 >>> 1);
        }
        Bits32ToBytes(this.gSubKeys[0] ^ BytesTo32Bits3, bArr2, i2);
        Bits32ToBytes(this.gSubKeys[1] ^ BytesTo32Bits4, bArr2, i2 + 4);
        Bits32ToBytes(this.gSubKeys[2] ^ i5, bArr2, i2 + 8);
        Bits32ToBytes(this.gSubKeys[3] ^ i4, bArr2, i2 + 12);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int BytesTo32Bits = BytesTo32Bits(bArr, i) ^ this.gSubKeys[0];
        int BytesTo32Bits2 = BytesTo32Bits(bArr, i + 4) ^ this.gSubKeys[1];
        int BytesTo32Bits3 = BytesTo32Bits(bArr, i + 8) ^ this.gSubKeys[2];
        int BytesTo32Bits4 = BytesTo32Bits(bArr, i + 12) ^ this.gSubKeys[3];
        int i4 = 8;
        while (i3 < 16) {
            int Fe32_0 = Fe32_0(BytesTo32Bits);
            int Fe32_3 = Fe32_3(BytesTo32Bits2);
            int[] iArr = this.gSubKeys;
            int i5 = i4 + 1;
            int i6 = BytesTo32Bits3 ^ ((Fe32_0 + Fe32_3) + iArr[i4]);
            BytesTo32Bits3 = (i6 >>> 1) | (i6 << 31);
            int i7 = (BytesTo32Bits4 >>> 31) | (BytesTo32Bits4 << 1);
            int i8 = i5 + 1;
            BytesTo32Bits4 = i7 ^ ((Fe32_0 + (Fe32_3 * 2)) + iArr[i5]);
            int Fe32_02 = Fe32_0(BytesTo32Bits3);
            int Fe32_32 = Fe32_3(BytesTo32Bits4);
            int[] iArr2 = this.gSubKeys;
            int i9 = i8 + 1;
            int i10 = BytesTo32Bits ^ ((Fe32_02 + Fe32_32) + iArr2[i8]);
            BytesTo32Bits = (i10 >>> 1) | (i10 << 31);
            i3 += 2;
            BytesTo32Bits2 = ((BytesTo32Bits2 << 1) | (BytesTo32Bits2 >>> 31)) ^ ((Fe32_02 + (Fe32_32 * 2)) + iArr2[i9]);
            i4 = i9 + 1;
        }
        Bits32ToBytes(this.gSubKeys[4] ^ BytesTo32Bits3, bArr2, i2);
        Bits32ToBytes(BytesTo32Bits4 ^ this.gSubKeys[5], bArr2, i2 + 4);
        Bits32ToBytes(this.gSubKeys[6] ^ BytesTo32Bits, bArr2, i2 + 8);
        Bits32ToBytes(this.gSubKeys[7] ^ BytesTo32Bits2, bArr2, i2 + 12);
    }

    private void setKey(byte[] bArr) {
        int m4098b0;
        int m4099b1;
        int m4100b2;
        int m4101b3;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.gSubKeys = new int[40];
        int i5 = this.k64Cnt;
        if (i5 < 1) {
            throw new IllegalArgumentException("Key size less than 64 bits");
        }
        if (i5 > 4) {
            throw new IllegalArgumentException("Key size larger than 256 bits");
        }
        for (int i6 = 0; i6 < this.k64Cnt; i6++) {
            int i7 = i6 * 8;
            iArr[i6] = BytesTo32Bits(bArr, i7);
            iArr2[i6] = BytesTo32Bits(bArr, i7 + 4);
            iArr3[(this.k64Cnt - 1) - i6] = RS_MDS_Encode(iArr[i6], iArr2[i6]);
        }
        for (int i8 = 0; i8 < 20; i8++) {
            int i9 = SK_STEP * i8;
            int F32 = F32(i9, iArr);
            int F322 = F32(i9 + 16843009, iArr2);
            int i10 = (F322 >>> 24) | (F322 << 8);
            int i11 = F32 + i10;
            int[] iArr4 = this.gSubKeys;
            int i12 = i8 * 2;
            iArr4[i12] = i11;
            int i13 = i11 + i10;
            iArr4[i12 + 1] = (i13 << 9) | (i13 >>> 23);
        }
        int i14 = iArr3[0];
        int i15 = iArr3[1];
        int i16 = 2;
        int i17 = iArr3[2];
        int i18 = iArr3[3];
        this.gSBox = new int[1024];
        int i19 = 0;
        while (i19 < 256) {
            int i20 = this.k64Cnt & 3;
            if (i20 != 0) {
                if (i20 == 1) {
                    int i21 = i19 * 2;
                    this.gSBox[i21] = this.gMDS0[(f9433P[0][i19] & 255) ^ m4098b0(i14)];
                    this.gSBox[i21 + 1] = this.gMDS1[(f9433P[0][i19] & 255) ^ m4099b1(i14)];
                    this.gSBox[i21 + 512] = this.gMDS2[(f9433P[1][i19] & 255) ^ m4100b2(i14)];
                    this.gSBox[i21 + InputDeviceCompat.SOURCE_DPAD] = this.gMDS3[(f9433P[1][i19] & 255) ^ m4101b3(i14)];
                } else if (i20 == i16) {
                    i4 = i19;
                    i3 = i4;
                    i2 = i3;
                    i = i2;
                    int[] iArr5 = this.gSBox;
                    int i22 = i19 * 2;
                    int[] iArr6 = this.gMDS0;
                    byte[][] bArr2 = f9433P;
                    iArr5[i22] = iArr6[(bArr2[0][(bArr2[0][i4] & 255) ^ m4098b0(i15)] & 255) ^ m4098b0(i14)];
                    int[] iArr7 = this.gMDS1;
                    byte[][] bArr3 = f9433P;
                    this.gSBox[i22 + 1] = iArr7[(bArr3[0][(bArr3[1][i3] & 255) ^ m4099b1(i15)] & 255) ^ m4099b1(i14)];
                    int[] iArr8 = this.gMDS2;
                    byte[][] bArr4 = f9433P;
                    this.gSBox[i22 + 512] = iArr8[(bArr4[1][(bArr4[0][i2] & 255) ^ m4100b2(i15)] & 255) ^ m4100b2(i14)];
                    int[] iArr9 = this.gSBox;
                    int i23 = i22 + InputDeviceCompat.SOURCE_DPAD;
                    int[] iArr10 = this.gMDS3;
                    byte[][] bArr5 = f9433P;
                    iArr9[i23] = iArr10[(bArr5[1][(bArr5[1][i] & 255) ^ m4101b3(i15)] & 255) ^ m4101b3(i14)];
                } else if (i20 == 3) {
                    m4098b0 = i19;
                    m4099b1 = m4098b0;
                    m4100b2 = m4099b1;
                    m4101b3 = m4100b2;
                }
                i19++;
                i16 = 2;
            } else {
                m4098b0 = (f9433P[1][i19] & 255) ^ m4098b0(i18);
                m4099b1 = (f9433P[0][i19] & 255) ^ m4099b1(i18);
                m4100b2 = (f9433P[0][i19] & 255) ^ m4100b2(i18);
                m4101b3 = (f9433P[1][i19] & 255) ^ m4101b3(i18);
            }
            i4 = (f9433P[1][m4098b0] & 255) ^ m4098b0(i17);
            i3 = (f9433P[1][m4099b1] & 255) ^ m4099b1(i17);
            i2 = (f9433P[0][m4100b2] & 255) ^ m4100b2(i17);
            i = (f9433P[0][m4101b3] & 255) ^ m4101b3(i17);
            int[] iArr52 = this.gSBox;
            int i222 = i19 * 2;
            int[] iArr62 = this.gMDS0;
            byte[][] bArr22 = f9433P;
            iArr52[i222] = iArr62[(bArr22[0][(bArr22[0][i4] & 255) ^ m4098b0(i15)] & 255) ^ m4098b0(i14)];
            int[] iArr72 = this.gMDS1;
            byte[][] bArr32 = f9433P;
            this.gSBox[i222 + 1] = iArr72[(bArr32[0][(bArr32[1][i3] & 255) ^ m4099b1(i15)] & 255) ^ m4099b1(i14)];
            int[] iArr82 = this.gMDS2;
            byte[][] bArr42 = f9433P;
            this.gSBox[i222 + 512] = iArr82[(bArr42[1][(bArr42[0][i2] & 255) ^ m4100b2(i15)] & 255) ^ m4100b2(i14)];
            int[] iArr92 = this.gSBox;
            int i232 = i222 + InputDeviceCompat.SOURCE_DPAD;
            int[] iArr102 = this.gMDS3;
            byte[][] bArr52 = f9433P;
            iArr92[i232] = iArr102[(bArr52[1][(bArr52[1][i] & 255) ^ m4101b3(i15)] & 255) ^ m4101b3(i14)];
            i19++;
            i16 = 2;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z;
        this.workingKey = ((KeyParameter) cipherParameters).getKey();
        byte[] bArr = this.workingKey;
        this.k64Cnt = bArr.length / 8;
        setKey(bArr);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Twofish not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 16;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }
}
