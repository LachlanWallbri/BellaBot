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
import org.bouncycastle.util.Pack;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: X */
    private final int[] f9417X = new int[4];

    /* renamed from: rk */
    private int[] f9418rk;
    private static final byte[] Sbox = {-42, -112, -23, -2, -52, -31, 61, ClassDefinitionUtils.OPS_invokespecial, 22, -74, 20, -62, 40, -5, 44, 5, 43, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -102, 118, ClassDefinitionUtils.OPS_aload_0, -66, 4, -61, -86, 68, 19, 38, PrinterUtils.BarCode.CODE128, -122, 6, -103, -100, 66, 80, -12, -111, ByteSourceJsonBootstrapper.UTF8_BOM_1, -104, 122, TarConstants.LF_CHR, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, 115, 23, -70, -125, ClassDefinitionUtils.OPS_dup, 60, 25, -26, -123, 79, -88, 104, 107, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -78, 113, 100, -38, -117, -8, -21, 15, TarConstants.LF_GNUTYPE_LONGLINK, 112, 86, -99, TarConstants.LF_DIR, 30, BinaryMemcacheOpcodes.GATKQ, 14, 94, 99, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -47, -94, 37, 34, 124, 59, 1, BinaryMemcacheOpcodes.SASL_AUTH, TarConstants.LF_PAX_EXTENDED_HEADER_LC, Constans.CAN_REV_SPRAY_SYS_RESULT, -44, 0, 70, 87, -97, -45, 39, 82, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_FIFO, 2, -25, -96, -60, -56, -98, -22, ByteSourceJsonBootstrapper.UTF8_BOM_3, -118, -46, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, -95, -32, -82, 93, -92, -101, TarConstants.LF_BLK, 26, 85, -83, -109, TarConstants.LF_SYMLINK, TarConstants.LF_NORMAL, -11, -116, ClassDefinitionUtils.OPS_return, -29, 29, -10, -30, 46, -126, 102, -54, 96, SlipConfig.END, 41, BinaryMemcacheOpcodes.GATK, -85, 13, TarConstants.LF_GNUTYPE_SPARSE, 78, 111, -43, SlipConfig.ESC, TarConstants.LF_CONTIG, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, -69, SlipConfig.ESC_ESC, PSSSigner.TRAILER_IMPLICIT, Byte.MAX_VALUE, 17, -39, 92, 65, Ascii.f1926US, 16, 90, -40, 10, -63, TarConstants.LF_LINK, Constans.CAN_REV_UV_SYS_RESULT, -91, -51, 123, -67, 45, 116, -48, 18, -72, -27, -76, ClassDefinitionUtils.OPS_areturn, Constans.CAN_REV_UV_SET_RESULT, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, Constans.CAN_REV_DIS_MODE_SWITCH, 24, -16, 125, -20, 58, SlipConfig.ESC_END, 77, 32, 121, -18, 95, 62, -41, -53, 57, PrinterUtils.BarCode.CODE93};

    /* renamed from: CK */
    private static final int[] f9415CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    /* renamed from: FK */
    private static final int[] f9416FK = {-1548633402, 1453994832, 1736282519, -1301273892};

    /* renamed from: F0 */
    private int m4089F0(int[] iArr, int i) {
        return m4094T((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    /* renamed from: F1 */
    private int m4090F1(int[] iArr, int i) {
        return m4094T((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    /* renamed from: F2 */
    private int m4091F2(int[] iArr, int i) {
        return m4094T((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    /* renamed from: F3 */
    private int m4092F3(int[] iArr, int i) {
        return m4094T((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    /* renamed from: L */
    private int m4093L(int i) {
        return rotateLeft(i, 24) ^ (((rotateLeft(i, 2) ^ i) ^ rotateLeft(i, 10)) ^ rotateLeft(i, 18));
    }

    private int L_ap(int i) {
        return rotateLeft(i, 23) ^ (rotateLeft(i, 13) ^ i);
    }

    /* renamed from: T */
    private int m4094T(int i) {
        return m4093L(tau(i));
    }

    private int T_ap(int i) {
        return L_ap(tau(i));
    }

    private int[] expandKey(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), Pack.bigEndianToInt(bArr, 12)};
        int i = iArr2[0];
        int[] iArr3 = f9416FK;
        int[] iArr4 = {i ^ iArr3[0], iArr2[1] ^ iArr3[1], iArr2[2] ^ iArr3[2], iArr2[3] ^ iArr3[3]};
        if (z) {
            iArr[0] = iArr4[0] ^ T_ap(((iArr4[1] ^ iArr4[2]) ^ iArr4[3]) ^ f9415CK[0]);
            iArr[1] = iArr4[1] ^ T_ap(((iArr4[2] ^ iArr4[3]) ^ iArr[0]) ^ f9415CK[1]);
            iArr[2] = iArr4[2] ^ T_ap(((iArr4[3] ^ iArr[0]) ^ iArr[1]) ^ f9415CK[2]);
            iArr[3] = iArr4[3] ^ T_ap(((iArr[0] ^ iArr[1]) ^ iArr[2]) ^ f9415CK[3]);
            for (int i2 = 4; i2 < 32; i2++) {
                iArr[i2] = iArr[i2 - 4] ^ T_ap(((iArr[i2 - 3] ^ iArr[i2 - 2]) ^ iArr[i2 - 1]) ^ f9415CK[i2]);
            }
        } else {
            iArr[31] = iArr4[0] ^ T_ap(((iArr4[1] ^ iArr4[2]) ^ iArr4[3]) ^ f9415CK[0]);
            iArr[30] = iArr4[1] ^ T_ap(((iArr4[2] ^ iArr4[3]) ^ iArr[31]) ^ f9415CK[1]);
            iArr[29] = iArr4[2] ^ T_ap(((iArr4[3] ^ iArr[31]) ^ iArr[30]) ^ f9415CK[2]);
            iArr[28] = iArr4[3] ^ T_ap(((iArr[30] ^ iArr[31]) ^ iArr[29]) ^ f9415CK[3]);
            for (int i3 = 27; i3 >= 0; i3--) {
                iArr[i3] = iArr[i3 + 4] ^ T_ap(((iArr[i3 + 3] ^ iArr[i3 + 2]) ^ iArr[i3 + 1]) ^ f9415CK[31 - i3]);
            }
        }
        return iArr;
    }

    private int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private int tau(int i) {
        byte[] bArr = Sbox;
        return (bArr[i & 255] & 255) | ((bArr[(i >> 24) & 255] & 255) << 24) | ((bArr[(i >> 16) & 255] & 255) << 16) | ((bArr[(i >> 8) & 255] & 255) << 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("SM4 requires a 128 bit key");
        }
        this.f9418rk = expandKey(z, key);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.f9418rk == null) {
            throw new IllegalStateException("SM4 not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.f9417X[0] = Pack.bigEndianToInt(bArr, i);
        this.f9417X[1] = Pack.bigEndianToInt(bArr, i + 4);
        this.f9417X[2] = Pack.bigEndianToInt(bArr, i + 8);
        this.f9417X[3] = Pack.bigEndianToInt(bArr, i + 12);
        for (int i3 = 0; i3 < 32; i3 += 4) {
            int[] iArr = this.f9417X;
            iArr[0] = m4089F0(iArr, this.f9418rk[i3]);
            int[] iArr2 = this.f9417X;
            iArr2[1] = m4090F1(iArr2, this.f9418rk[i3 + 1]);
            int[] iArr3 = this.f9417X;
            iArr3[2] = m4091F2(iArr3, this.f9418rk[i3 + 2]);
            int[] iArr4 = this.f9417X;
            iArr4[3] = m4092F3(iArr4, this.f9418rk[i3 + 3]);
        }
        Pack.intToBigEndian(this.f9417X[3], bArr2, i2);
        Pack.intToBigEndian(this.f9417X[2], bArr2, i2 + 4);
        Pack.intToBigEndian(this.f9417X[1], bArr2, i2 + 8);
        Pack.intToBigEndian(this.f9417X[0], bArr2, i2 + 12);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
