package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: S */
    private static final byte[] f9215S = {41, 46, 67, -55, -94, -40, 124, 1, 61, TarConstants.LF_FIFO, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, SlipConfig.END, -57, 115, -116, -104, -109, 43, -39, PSSSigner.TRAILER_IMPLICIT, TarConstants.LF_GNUTYPE_LONGNAME, -126, -54, 30, -101, 87, 60, -3, -44, -32, 22, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 66, 111, 24, -118, 23, -27, 18, -66, 78, -60, -42, -38, -98, -34, PrinterUtils.BarCode.CODE128, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, 21, -78, 7, 63, -108, -62, 16, Constans.CAN_REV_UV_SET_RESULT, 11, 34, 95, BinaryMemcacheOpcodes.SASL_AUTH, Byte.MIN_VALUE, Byte.MAX_VALUE, 93, -102, 90, -112, TarConstants.LF_SYMLINK, 39, TarConstants.LF_DIR, 62, -52, -25, ByteSourceJsonBootstrapper.UTF8_BOM_3, -9, -105, 3, -1, 25, TarConstants.LF_NORMAL, -77, PrinterUtils.BarCode.CODE93, -91, -75, -47, -41, 94, -110, ClassDefinitionUtils.OPS_aload_0, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, ClassDefinitionUtils.OPS_dup, 100, 113, Constans.CAN_REV_SPRAY_SYS_RESULT, 32, -122, 91, -49, 101, -26, 45, -88, 2, 27, 96, 37, -83, -82, ClassDefinitionUtils.OPS_areturn, -71, -10, 28, 70, 97, 105, TarConstants.LF_BLK, 64, 126, 15, 85, 71, -93, BinaryMemcacheOpcodes.GATK, SlipConfig.ESC_ESC, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, TarConstants.LF_GNUTYPE_SPARSE, 13, 110, -123, 40, Constans.CAN_REV_DIS_MODE_SWITCH, 9, -45, -33, -51, -12, 65, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 77, 82, 106, SlipConfig.ESC_END, TarConstants.LF_CONTIG, -56, 108, -63, -85, -6, BinaryMemcacheOpcodes.GATKQ, -31, 123, 8, 12, -67, ClassDefinitionUtils.OPS_return, 74, TarConstants.LF_PAX_EXTENDED_HEADER_LC, Constans.CAN_REV_UV_SYS_RESULT, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, 29, 57, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, ClassDefinitionUtils.OPS_invokespecial, 14, 102, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -48, -28, -90, 119, 114, -8, -21, 117, TarConstants.LF_GNUTYPE_LONGLINK, 10, TarConstants.LF_LINK, 68, 80, -76, -113, -19, Ascii.f1926US, 26, SlipConfig.ESC, -103, -115, TarConstants.LF_CHR, -97, 17, -125, 20};

    /* renamed from: C */
    private byte[] f9216C;
    private int COff;

    /* renamed from: M */
    private byte[] f9217M;

    /* renamed from: X */
    private byte[] f9218X;
    private int mOff;
    private int xOff;

    public MD2Digest() {
        this.f9218X = new byte[48];
        this.f9217M = new byte[16];
        this.f9216C = new byte[16];
        reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.f9218X = new byte[48];
        this.f9217M = new byte[16];
        this.f9216C = new byte[16];
        copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        byte[] bArr = mD2Digest.f9218X;
        System.arraycopy(bArr, 0, this.f9218X, 0, bArr.length);
        this.xOff = mD2Digest.xOff;
        byte[] bArr2 = mD2Digest.f9217M;
        System.arraycopy(bArr2, 0, this.f9217M, 0, bArr2.length);
        this.mOff = mD2Digest.mOff;
        byte[] bArr3 = mD2Digest.f9216C;
        System.arraycopy(bArr3, 0, this.f9216C, 0, bArr3.length);
        this.COff = mD2Digest.COff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD2Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int length = this.f9217M.length;
        int i2 = this.mOff;
        byte b = (byte) (length - i2);
        while (true) {
            byte[] bArr2 = this.f9217M;
            if (i2 >= bArr2.length) {
                processCheckSum(bArr2);
                processBlock(this.f9217M);
                processBlock(this.f9216C);
                System.arraycopy(this.f9218X, this.xOff, bArr, i, 16);
                reset();
                return 16;
            }
            bArr2[i2] = b;
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return MessageDigestAlgorithms.MD2;
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    protected void processBlock(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f9218X;
            bArr2[i + 16] = bArr[i];
            bArr2[i + 32] = (byte) (bArr[i] ^ bArr2[i]);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 18; i3++) {
            int i4 = i2;
            for (int i5 = 0; i5 < 48; i5++) {
                byte[] bArr3 = this.f9218X;
                byte b = (byte) (f9215S[i4] ^ bArr3[i5]);
                bArr3[i5] = b;
                i4 = b & 255;
            }
            i2 = (i4 + i3) % 256;
        }
    }

    protected void processCheckSum(byte[] bArr) {
        byte b = this.f9216C[15];
        for (int i = 0; i < 16; i++) {
            byte[] bArr2 = this.f9216C;
            bArr2[i] = (byte) (f9215S[(b ^ bArr[i]) & 255] ^ bArr2[i]);
            b = bArr2[i];
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.xOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f9218X;
            if (i == bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.mOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.f9217M;
            if (i2 == bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        this.COff = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.f9216C;
            if (i3 == bArr3.length) {
                return;
            }
            bArr3[i3] = 0;
            i3++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD2Digest) memoable);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.f9217M;
        int i = this.mOff;
        this.mOff = i + 1;
        bArr[i] = b;
        if (this.mOff == 16) {
            processCheckSum(bArr);
            processBlock(this.f9217M);
            this.mOff = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.mOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > 16) {
            System.arraycopy(bArr, i, this.f9217M, 0, 16);
            processCheckSum(this.f9217M);
            processBlock(this.f9217M);
            i2 -= 16;
            i += 16;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
