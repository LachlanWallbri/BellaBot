package org.bouncycastle.crypto.engines;

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
import org.bouncycastle.util.Arrays;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class GOST3412_2015Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;

    /* renamed from: PI */
    private static final byte[] f9387PI = {-4, -18, SlipConfig.ESC_ESC, 17, -49, 110, TarConstants.LF_LINK, 22, -5, -60, -6, -38, BinaryMemcacheOpcodes.GATK, -59, 4, 77, -23, 119, -16, SlipConfig.ESC, -109, 46, -103, -70, 23, TarConstants.LF_FIFO, -15, -69, 20, -51, 95, -63, -7, 24, 101, 90, -30, 92, ByteSourceJsonBootstrapper.UTF8_BOM_1, BinaryMemcacheOpcodes.SASL_AUTH, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 28, 60, 66, -117, 1, -114, 79, 5, Constans.CAN_REV_DIS_MODE_SWITCH, 2, -82, -29, 106, -113, -96, 6, 11, -19, -104, Byte.MAX_VALUE, -44, -45, Ascii.f1926US, -21, TarConstants.LF_BLK, 44, 81, -22, -56, PrinterUtils.BarCode.CODE93, -85, -14, ClassDefinitionUtils.OPS_aload_0, 104, -94, -3, 58, -50, -52, -75, 112, 14, 86, 8, 12, 118, 18, ByteSourceJsonBootstrapper.UTF8_BOM_3, 114, 19, 71, -100, ClassDefinitionUtils.OPS_invokespecial, 93, Constans.CAN_REV_SPRAY_SYS_RESULT, 21, -95, -106, 41, 16, 123, -102, -57, -13, -111, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 111, -99, -98, -78, ClassDefinitionUtils.OPS_return, TarConstants.LF_SYMLINK, 117, 25, 61, -1, TarConstants.LF_DIR, -118, 126, 109, 84, -58, Byte.MIN_VALUE, -61, -67, 13, 87, -33, -11, BinaryMemcacheOpcodes.GATKQ, -87, 62, -88, 67, -55, -41, 121, -42, -10, 124, 34, -71, 3, -32, 15, -20, -34, 122, -108, ClassDefinitionUtils.OPS_areturn, PSSSigner.TRAILER_IMPLICIT, SlipConfig.ESC_END, -24, 40, 80, 78, TarConstants.LF_CHR, 10, 74, -89, -105, 96, 115, 30, 0, 98, 68, 26, -72, 56, -126, 100, -97, 38, 65, -83, 69, 70, -110, 39, 94, 85, 47, -116, -93, -91, 125, 105, -43, -107, 59, 7, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -77, 64, -122, -84, 29, -9, TarConstants.LF_NORMAL, TarConstants.LF_CONTIG, 107, -28, Constans.CAN_REV_UV_SYS_RESULT, -39, -25, Constans.CAN_REV_UV_SET_RESULT, -31, 27, -125, PrinterUtils.BarCode.CODE128, TarConstants.LF_GNUTYPE_LONGNAME, 63, -8, -2, -115, TarConstants.LF_GNUTYPE_SPARSE, -86, -112, -54, -40, -123, 97, 32, 113, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -92, 45, 43, 9, 91, -53, -101, 37, -48, -66, -27, 108, 82, ClassDefinitionUtils.OPS_dup, -90, 116, -46, -26, -12, -76, SlipConfig.END, -47, 102, -81, -62, 57, TarConstants.LF_GNUTYPE_LONGLINK, 99, -74};
    private static final byte[] inversePI = {-91, 45, TarConstants.LF_SYMLINK, -113, 14, TarConstants.LF_NORMAL, 56, SlipConfig.END, 84, -26, -98, 57, 85, 126, 82, -111, 100, 3, 87, 90, 28, 96, 7, 24, BinaryMemcacheOpcodes.SASL_AUTH, 114, -88, -47, 41, -58, -92, 63, -32, 39, -115, 12, -126, -22, -82, -76, -102, 99, PrinterUtils.BarCode.CODE128, -27, 66, -28, 21, ClassDefinitionUtils.OPS_invokespecial, -56, 6, 112, -99, 65, 117, 25, -55, -86, -4, 77, ByteSourceJsonBootstrapper.UTF8_BOM_3, ClassDefinitionUtils.OPS_aload_0, 115, Constans.CAN_REV_DIS_MODE_SWITCH, -43, -61, -81, 43, -122, -89, ClassDefinitionUtils.OPS_return, -78, 91, 70, -45, -97, -3, -44, 15, -100, 47, -101, 67, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 121, -74, TarConstants.LF_GNUTYPE_SPARSE, Byte.MAX_VALUE, -63, -16, BinaryMemcacheOpcodes.GATK, -25, 37, 94, -75, 30, -94, -33, -90, -2, -84, 34, -7, -30, 74, PSSSigner.TRAILER_IMPLICIT, TarConstants.LF_DIR, -54, -18, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 5, 107, 81, -31, ClassDefinitionUtils.OPS_dup, -93, -14, 113, 86, 17, 106, Constans.CAN_REV_UV_SET_RESULT, -108, 101, -116, -69, 119, 60, 123, 40, -85, -46, TarConstants.LF_LINK, -34, -60, 95, -52, -49, 118, 44, -72, -40, 46, TarConstants.LF_FIFO, SlipConfig.ESC, 105, -77, 20, -107, -66, 98, -95, 59, 22, 102, -23, 92, 108, 109, -83, TarConstants.LF_CONTIG, 97, TarConstants.LF_GNUTYPE_LONGLINK, -71, -29, -70, -15, -96, -123, -125, -38, 71, -59, ClassDefinitionUtils.OPS_areturn, TarConstants.LF_CHR, -6, -106, 111, 110, -62, -10, 80, -1, 93, -87, -114, 23, 27, -105, 125, -20, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -9, Ascii.f1926US, -5, 124, 9, 13, 122, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 69, Constans.CAN_REV_SPRAY_SYS_RESULT, SlipConfig.ESC_END, -24, 79, 29, 78, 4, -21, -8, -13, 62, 61, -67, -118, Constans.CAN_REV_UV_SYS_RESULT, SlipConfig.ESC_ESC, -51, 11, 19, -104, 2, -109, Byte.MIN_VALUE, -112, -48, BinaryMemcacheOpcodes.GATKQ, TarConstants.LF_BLK, -53, -19, -12, -50, -103, 16, 68, 64, -110, 58, 1, 38, 18, 26, PrinterUtils.BarCode.CODE93, 104, -11, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -117, -57, -42, 32, 10, 8, 0, TarConstants.LF_GNUTYPE_LONGNAME, -41, 116};
    private boolean forEncryption;
    private final byte[] lFactors = {-108, 32, -123, 16, -62, SlipConfig.END, 1, -5, 1, SlipConfig.END, -62, 16, -123, 32, -108, 1};
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = this.KEY_LENGTH / 2;
    private byte[][] subKeys = (byte[][]) null;
    private byte[][] _gf_mul = init_gf256_mul_table();

    /* renamed from: C */
    private void m4074C(byte[] bArr, int i) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i;
        m4076L(bArr);
    }

    /* renamed from: F */
    private void m4075F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] LSX = LSX(bArr, bArr2);
        m4079X(LSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(LSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[][] bArr3;
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i, bArr4, 0, 16);
        int i3 = 9;
        if (this.forEncryption) {
            for (int i4 = 0; i4 < 9; i4++) {
                bArr4 = Arrays.copyOf(LSX(this.subKeys[i4], bArr4), 16);
            }
            m4079X(bArr4, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (i3 <= 0) {
                    break;
                }
                bArr4 = Arrays.copyOf(XSL(bArr3[i3], bArr4), 16);
                i3--;
            }
            m4079X(bArr4, bArr3[0]);
        }
        System.arraycopy(bArr4, 0, bArr2, i2, 16);
    }

    /* renamed from: L */
    private void m4076L(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            m4077R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        m4079X(copyOf, bArr2);
        m4078S(copyOf);
        m4076L(copyOf);
        return copyOf;
    }

    /* renamed from: R */
    private void m4077R(byte[] bArr) {
        byte m4080l = m4080l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = m4080l;
    }

    /* renamed from: S */
    private void m4078S(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = f9387PI[unsignedByte(bArr[i])];
        }
    }

    /* renamed from: X */
    private void m4079X(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        m4079X(copyOf, bArr2);
        inverseL(copyOf);
        inverseS(copyOf);
        return copyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int i;
        if (bArr.length != this.KEY_LENGTH) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.subKeys = new byte[10];
        for (int i2 = 0; i2 < 10; i2++) {
            this.subKeys[i2] = new byte[this.SUB_LENGTH];
        }
        int i3 = this.SUB_LENGTH;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        while (true) {
            i = this.SUB_LENGTH;
            if (i4 >= i) {
                break;
            }
            byte[][] bArr4 = this.subKeys;
            byte[] bArr5 = bArr4[0];
            byte b = bArr[i4];
            bArr2[i4] = b;
            bArr5[i4] = b;
            byte[] bArr6 = bArr4[1];
            byte b2 = bArr[i + i4];
            bArr3[i4] = b2;
            bArr6[i4] = b2;
            i4++;
        }
        byte[] bArr7 = new byte[i];
        for (int i5 = 1; i5 < 5; i5++) {
            for (int i6 = 1; i6 <= 8; i6++) {
                m4074C(bArr7, ((i5 - 1) * 8) + i6);
                m4075F(bArr7, bArr2, bArr3);
            }
            int i7 = i5 * 2;
            System.arraycopy(bArr2, 0, this.subKeys[i7], 0, this.SUB_LENGTH);
            System.arraycopy(bArr3, 0, this.subKeys[i7 + 1], 0, this.SUB_LENGTH);
        }
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr[i] = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i][i2] = kuz_mul_gf256_slow((byte) i, (byte) i2);
            }
        }
        return bArr;
    }

    private void inverseL(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte m4080l = m4080l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = m4080l;
    }

    private void inverseS(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = inversePI[unsignedByte(bArr[i])];
        }
    }

    private static byte kuz_mul_gf256_slow(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & 128);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    /* renamed from: l */
    private byte m4080l(byte[] bArr) {
        byte b = bArr[15];
        for (int i = 14; i >= 0; i--) {
            b = (byte) (b ^ this._gf_mul[unsignedByte(bArr[i])][unsignedByte(this.lFactors[i])]);
        }
        return b;
    }

    private int unsignedByte(byte b) {
        return b & 255;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else {
            if (cipherParameters == null) {
                return;
            }
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.subKeys == null) {
            throw new IllegalStateException("GOST3412_2015 engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        GOST3412_2015Func(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
