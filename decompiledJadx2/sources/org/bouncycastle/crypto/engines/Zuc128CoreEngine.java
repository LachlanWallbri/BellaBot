package org.bouncycastle.crypto.engines;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import kotlin.UShort;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class Zuc128CoreEngine implements StreamCipher, Memoable {
    private final int[] BRC;

    /* renamed from: F */
    private final int[] f9440F;
    private final int[] LFSR;
    private final byte[] keyStream;
    private int theIndex;
    private int theIterations;
    private Zuc128CoreEngine theResetState;

    /* renamed from: S0 */
    private static final byte[] f9438S0 = {62, 114, 91, 71, -54, -32, 0, TarConstants.LF_CHR, 4, -47, 84, -104, 9, -71, 109, -53, 123, 27, -7, TarConstants.LF_SYMLINK, -81, -99, 106, -91, -72, 45, -4, 29, 8, TarConstants.LF_GNUTYPE_SPARSE, 3, -112, 77, 78, Constans.CAN_REV_DIS_MODE_SWITCH, -103, -28, -50, -39, -111, SlipConfig.ESC_ESC, -74, -123, PrinterUtils.BarCode.CODE93, -117, 41, 110, -84, -51, -63, -8, 30, 115, 67, 105, -58, -75, -67, -3, 57, 99, 32, -44, 56, 118, 125, -78, -89, -49, -19, 87, -59, -13, 44, -69, 20, BinaryMemcacheOpcodes.SASL_AUTH, 6, 85, -101, -29, ByteSourceJsonBootstrapper.UTF8_BOM_1, 94, TarConstants.LF_LINK, 79, Byte.MAX_VALUE, 90, -92, 13, -126, 81, PrinterUtils.BarCode.CODE128, 95, -70, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 28, 74, 22, -43, 23, -88, -110, BinaryMemcacheOpcodes.GATKQ, Ascii.f1926US, -116, -1, -40, -82, 46, 1, -45, -83, 59, TarConstants.LF_GNUTYPE_LONGLINK, -38, 70, -21, -55, -34, -102, -113, Constans.CAN_REV_SPRAY_SYS_RESULT, -41, 58, Byte.MIN_VALUE, 111, 47, -56, ClassDefinitionUtils.OPS_return, -76, TarConstants.LF_CONTIG, -9, 10, 34, 19, 40, 124, -52, 60, Constans.CAN_REV_UV_SET_RESULT, -57, -61, -106, 86, 7, ByteSourceJsonBootstrapper.UTF8_BOM_3, 126, -16, 11, 43, -105, 82, TarConstants.LF_DIR, 65, 121, 97, -90, TarConstants.LF_GNUTYPE_LONGNAME, 16, -2, PSSSigner.TRAILER_IMPLICIT, 38, -107, Constans.CAN_REV_UV_SYS_RESULT, -118, ClassDefinitionUtils.OPS_areturn, -93, -5, SlipConfig.END, 24, -108, -14, -31, -27, -23, 93, -48, SlipConfig.ESC_END, 17, 102, 100, 92, -20, ClassDefinitionUtils.OPS_dup, 66, 117, 18, -11, 116, -100, -86, BinaryMemcacheOpcodes.GATK, 14, -122, -85, -66, ClassDefinitionUtils.OPS_aload_0, 2, -25, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -26, 68, -94, 108, -62, -109, -97, -15, -10, -6, TarConstants.LF_FIFO, -46, 80, 104, -98, 98, 113, 21, 61, -42, 64, -60, -30, 15, -114, -125, 119, 107, 37, 5, 63, 12, TarConstants.LF_NORMAL, -22, 112, ClassDefinitionUtils.OPS_invokespecial, -95, -24, -87, 101, -115, 39, 26, SlipConfig.ESC, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -77, -96, -12, 69, 122, 25, -33, -18, TarConstants.LF_PAX_EXTENDED_HEADER_LC, TarConstants.LF_BLK, 96};

    /* renamed from: S1 */
    private static final byte[] f9439S1 = {85, -62, 99, 113, 59, -56, 71, -122, -97, 60, -38, 91, 41, -86, -3, 119, -116, -59, -108, 12, -90, 26, 19, 0, -29, -88, 22, 114, 64, -7, -8, 66, 68, 38, 104, -106, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -39, 69, 62, 16, 118, -58, -89, -117, 57, 67, -31, 58, -75, 86, ClassDefinitionUtils.OPS_aload_0, SlipConfig.END, 109, -77, 5, 34, 102, ByteSourceJsonBootstrapper.UTF8_BOM_3, SlipConfig.ESC_END, 11, -6, 98, PrinterUtils.BarCode.CODE93, SlipConfig.ESC_ESC, 32, 17, 6, TarConstants.LF_FIFO, -55, -63, -49, -10, 39, 82, -69, 105, -11, -44, Constans.CAN_REV_SPRAY_SYS_RESULT, Byte.MAX_VALUE, Constans.CAN_REV_DIS_MODE_SWITCH, TarConstants.LF_GNUTYPE_LONGNAME, -46, -100, 87, -92, PSSSigner.TRAILER_IMPLICIT, 79, -102, -33, -2, -42, -115, 122, -21, 43, TarConstants.LF_GNUTYPE_SPARSE, -40, 92, -95, 20, 23, -5, BinaryMemcacheOpcodes.GATK, -43, 125, TarConstants.LF_NORMAL, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 115, 8, 9, -18, ClassDefinitionUtils.OPS_invokespecial, 112, 63, 97, -78, 25, -114, 78, -27, TarConstants.LF_GNUTYPE_LONGLINK, -109, -113, 93, SlipConfig.ESC, -87, -83, -15, -82, 46, -53, 13, -4, -12, 45, 70, 110, 29, -105, -24, -47, -23, 77, TarConstants.LF_CONTIG, -91, 117, 94, -125, -98, -85, -126, -99, -71, 28, -32, -51, PrinterUtils.BarCode.CODE128, Constans.CAN_REV_UV_SET_RESULT, 1, -74, -67, TarConstants.LF_PAX_EXTENDED_HEADER_UC, BinaryMemcacheOpcodes.GATKQ, -94, 95, 56, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -103, 21, -112, 80, -72, -107, -28, -48, -111, -57, -50, -19, 15, -76, 111, -96, -52, -16, 2, 74, 121, -61, -34, -93, ByteSourceJsonBootstrapper.UTF8_BOM_1, -22, 81, -26, 107, 24, -20, 27, 44, Byte.MIN_VALUE, -9, 116, -25, -1, BinaryMemcacheOpcodes.SASL_AUTH, 90, 106, 84, 30, 65, TarConstants.LF_LINK, -110, TarConstants.LF_DIR, -60, TarConstants.LF_CHR, 7, 10, -70, 126, 14, TarConstants.LF_BLK, Constans.CAN_REV_UV_SYS_RESULT, ClassDefinitionUtils.OPS_return, -104, 124, -13, 61, 96, 108, 123, -54, -45, Ascii.f1926US, TarConstants.LF_SYMLINK, 101, 4, 40, 100, -66, -123, -101, 47, ClassDefinitionUtils.OPS_dup, -118, -41, ClassDefinitionUtils.OPS_areturn, 37, -84, -81, 18, 3, -30, -14};
    private static final short[] EK_d = {17623, 9916, 25195, 4958, 22409, 13794, 28981, 2479, 19832, 12051, 27588, 6897, 24102, 15437, 30874, 18348};

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine() {
        this.LFSR = new int[16];
        this.f9440F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine(Zuc128CoreEngine zuc128CoreEngine) {
        this.LFSR = new int[16];
        this.f9440F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
        reset(zuc128CoreEngine);
    }

    private int AddM(int i, int i2) {
        int i3 = i + i2;
        return (Integer.MAX_VALUE & i3) + (i3 >>> 31);
    }

    private void BitReorganization() {
        int[] iArr = this.BRC;
        int[] iArr2 = this.LFSR;
        iArr[0] = ((iArr2[15] & 2147450880) << 1) | (iArr2[14] & 65535);
        iArr[1] = ((iArr2[11] & 65535) << 16) | (iArr2[9] >>> 15);
        iArr[2] = ((iArr2[7] & 65535) << 16) | (iArr2[5] >>> 15);
        iArr[3] = (iArr2[0] >>> 15) | ((iArr2[2] & 65535) << 16);
    }

    /* renamed from: L1 */
    private static int m4102L1(int i) {
        return ROT(i, 24) ^ (((ROT(i, 2) ^ i) ^ ROT(i, 10)) ^ ROT(i, 18));
    }

    /* renamed from: L2 */
    private static int m4103L2(int i) {
        return ROT(i, 30) ^ (((ROT(i, 8) ^ i) ^ ROT(i, 14)) ^ ROT(i, 22));
    }

    private void LFSRWithInitialisationMode(int i) {
        int[] iArr = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(AddM(iArr[0], MulByPow2(iArr[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15)), i);
        int[] iArr2 = this.LFSR;
        iArr2[0] = iArr2[1];
        iArr2[1] = iArr2[2];
        iArr2[2] = iArr2[3];
        iArr2[3] = iArr2[4];
        iArr2[4] = iArr2[5];
        iArr2[5] = iArr2[6];
        iArr2[6] = iArr2[7];
        iArr2[7] = iArr2[8];
        iArr2[8] = iArr2[9];
        iArr2[9] = iArr2[10];
        iArr2[10] = iArr2[11];
        iArr2[11] = iArr2[12];
        iArr2[12] = iArr2[13];
        iArr2[13] = iArr2[14];
        iArr2[14] = iArr2[15];
        iArr2[15] = AddM;
    }

    private void LFSRWithWorkMode() {
        int[] iArr = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(iArr[0], MulByPow2(iArr[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15));
        int[] iArr2 = this.LFSR;
        iArr2[0] = iArr2[1];
        iArr2[1] = iArr2[2];
        iArr2[2] = iArr2[3];
        iArr2[3] = iArr2[4];
        iArr2[4] = iArr2[5];
        iArr2[5] = iArr2[6];
        iArr2[6] = iArr2[7];
        iArr2[7] = iArr2[8];
        iArr2[8] = iArr2[9];
        iArr2[9] = iArr2[10];
        iArr2[10] = iArr2[11];
        iArr2[11] = iArr2[12];
        iArr2[12] = iArr2[13];
        iArr2[13] = iArr2[14];
        iArr2[14] = iArr2[15];
        iArr2[15] = AddM;
    }

    private static int MAKEU31(byte b, short s, byte b2) {
        return ((b & 255) << 23) | ((s & UShort.MAX_VALUE) << 8) | (b2 & 255);
    }

    private static int MAKEU32(byte b, byte b2, byte b3, byte b4) {
        return ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    private static int MulByPow2(int i, int i2) {
        return ((i >>> (31 - i2)) | (i << i2)) & Integer.MAX_VALUE;
    }

    static int ROT(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static void encode32be(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    private void makeKeyStream() {
        encode32be(makeKeyStreamWord(), this.keyStream, 0);
    }

    private void setKeyAndIV(byte[] bArr, byte[] bArr2) {
        setKeyAndIV(this.LFSR, bArr, bArr2);
        int[] iArr = this.f9440F;
        iArr[0] = 0;
        iArr[1] = 0;
        int i = 32;
        while (true) {
            BitReorganization();
            if (i <= 0) {
                m4104F();
                LFSRWithWorkMode();
                return;
            } else {
                LFSRWithInitialisationMode(m4104F() >>> 1);
                i--;
            }
        }
    }

    /* renamed from: F */
    int m4104F() {
        int[] iArr = this.BRC;
        int i = iArr[0];
        int[] iArr2 = this.f9440F;
        int i2 = (i ^ iArr2[0]) + iArr2[1];
        int i3 = iArr2[0] + iArr[1];
        int i4 = iArr[2] ^ iArr2[1];
        int m4102L1 = m4102L1((i3 << 16) | (i4 >>> 16));
        int m4103L2 = m4103L2((i4 << 16) | (i3 >>> 16));
        int[] iArr3 = this.f9440F;
        byte[] bArr = f9438S0;
        byte b = bArr[m4102L1 >>> 24];
        byte[] bArr2 = f9439S1;
        iArr3[0] = MAKEU32(b, bArr2[(m4102L1 >>> 16) & 255], bArr[(m4102L1 >>> 8) & 255], bArr2[m4102L1 & 255]);
        int[] iArr4 = this.f9440F;
        byte[] bArr3 = f9438S0;
        byte b2 = bArr3[m4103L2 >>> 24];
        byte[] bArr4 = f9439S1;
        iArr4[1] = MAKEU32(b2, bArr4[(m4103L2 >>> 16) & 255], bArr3[(m4103L2 >>> 8) & 255], bArr4[m4103L2 & 255]);
        return i2;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new Zuc128CoreEngine(this);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Zuc-128";
    }

    protected int getMaxIterations() {
        return MapFilePathConfig.MAX_DELETE_CODE_VALUE;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] bArr;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            bArr = null;
        }
        byte[] key = cipherParameters instanceof KeyParameter ? ((KeyParameter) cipherParameters).getKey() : null;
        this.theIndex = 0;
        this.theIterations = 0;
        setKeyAndIV(key, bArr);
        this.theResetState = (Zuc128CoreEngine) copy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int makeKeyStreamWord() {
        int i = this.theIterations;
        this.theIterations = i + 1;
        if (i >= getMaxIterations()) {
            throw new IllegalStateException("Too much data processed by singleKey/IV");
        }
        BitReorganization();
        int m4104F = m4104F() ^ this.BRC[3];
        LFSRWithWorkMode();
        return m4104F;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (this.theResetState == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i4 + i3] = returnByte(bArr[i4 + i]);
        }
        return i2;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theResetState;
        if (zuc128CoreEngine != null) {
            reset(zuc128CoreEngine);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        Zuc128CoreEngine zuc128CoreEngine = (Zuc128CoreEngine) memoable;
        int[] iArr = zuc128CoreEngine.LFSR;
        int[] iArr2 = this.LFSR;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = zuc128CoreEngine.f9440F;
        int[] iArr4 = this.f9440F;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        int[] iArr5 = zuc128CoreEngine.BRC;
        int[] iArr6 = this.BRC;
        System.arraycopy(iArr5, 0, iArr6, 0, iArr6.length);
        byte[] bArr = zuc128CoreEngine.keyStream;
        byte[] bArr2 = this.keyStream;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.theIndex = zuc128CoreEngine.theIndex;
        this.theIterations = zuc128CoreEngine.theIterations;
        this.theResetState = zuc128CoreEngine;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.theIndex == 0) {
            makeKeyStream();
        }
        byte[] bArr = this.keyStream;
        int i = this.theIndex;
        byte b2 = (byte) (b ^ bArr[i]);
        this.theIndex = (i + 1) % 4;
        return b2;
    }

    protected void setKeyAndIV(int[] iArr, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("A key of 16 bytes is needed");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("An IV of 16 bytes is needed");
        }
        this.LFSR[0] = MAKEU31(bArr[0], EK_d[0], bArr2[0]);
        this.LFSR[1] = MAKEU31(bArr[1], EK_d[1], bArr2[1]);
        this.LFSR[2] = MAKEU31(bArr[2], EK_d[2], bArr2[2]);
        this.LFSR[3] = MAKEU31(bArr[3], EK_d[3], bArr2[3]);
        this.LFSR[4] = MAKEU31(bArr[4], EK_d[4], bArr2[4]);
        this.LFSR[5] = MAKEU31(bArr[5], EK_d[5], bArr2[5]);
        this.LFSR[6] = MAKEU31(bArr[6], EK_d[6], bArr2[6]);
        this.LFSR[7] = MAKEU31(bArr[7], EK_d[7], bArr2[7]);
        this.LFSR[8] = MAKEU31(bArr[8], EK_d[8], bArr2[8]);
        this.LFSR[9] = MAKEU31(bArr[9], EK_d[9], bArr2[9]);
        this.LFSR[10] = MAKEU31(bArr[10], EK_d[10], bArr2[10]);
        this.LFSR[11] = MAKEU31(bArr[11], EK_d[11], bArr2[11]);
        this.LFSR[12] = MAKEU31(bArr[12], EK_d[12], bArr2[12]);
        this.LFSR[13] = MAKEU31(bArr[13], EK_d[13], bArr2[13]);
        this.LFSR[14] = MAKEU31(bArr[14], EK_d[14], bArr2[14]);
        this.LFSR[15] = MAKEU31(bArr[15], EK_d[15], bArr2[15]);
    }
}
