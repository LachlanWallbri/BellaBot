package org.bouncycastle.crypto.digests;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import java.lang.reflect.Array;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class Haraka256Digest extends HarakaBase {

    /* renamed from: RC */
    private static final byte[][] f9202RC = {new byte[]{6, Constans.CAN_REV_DIS_MODE_SWITCH, 112, TarConstants.LF_GNUTYPE_LONGNAME, -26, 32, SlipConfig.END, 10, -78, -59, -2, -16, 117, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 123, -99}, new byte[]{-117, 102, -76, -31, Constans.CAN_REV_UV_SYS_RESULT, -13, -96, 107, 100, 15, 107, -92, 47, 8, -9, 23}, new byte[]{TarConstants.LF_BLK, 2, -34, 45, TarConstants.LF_GNUTYPE_SPARSE, -14, Constans.CAN_REV_DIS_MODE_SWITCH, -104, -49, 2, -99, 96, -97, 2, -111, 20}, new byte[]{14, -42, -22, -26, 46, 123, 79, 8, -69, -13, PSSSigner.TRAILER_IMPLICIT, -81, -3, 91, 79, 121}, new byte[]{-53, -49, ClassDefinitionUtils.OPS_areturn, -53, PrinterUtils.BarCode.CODE93, 114, 68, -117, 121, -18, -51, 28, -66, 57, 112, 68}, new byte[]{126, -22, -51, -18, 110, -112, TarConstants.LF_SYMLINK, ClassDefinitionUtils.OPS_invokespecial, -115, TarConstants.LF_GNUTYPE_SPARSE, TarConstants.LF_DIR, -19, 43, -118, 5, 123}, new byte[]{TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -62, -113, 67, 94, 46, 124, -48, -30, 65, 39, 97, -38, 79, ByteSourceJsonBootstrapper.UTF8_BOM_1, 27}, new byte[]{41, BinaryMemcacheOpcodes.GATKQ, -39, ClassDefinitionUtils.OPS_areturn, -81, -54, -52, 7, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 95, -3, -30, Ascii.f1926US, -57, 11, 59}, new byte[]{-85, 77, 99, -15, -26, -122, Byte.MAX_VALUE, -23, -20, SlipConfig.ESC, -113, -54, -71, -44, 101, -18}, new byte[]{28, TarConstants.LF_NORMAL, ByteSourceJsonBootstrapper.UTF8_BOM_3, Constans.CAN_REV_DIS_MODE_SWITCH, -44, ClassDefinitionUtils.OPS_invokespecial, -51, 100, 91, ClassDefinitionUtils.OPS_aload_0, 64, 79, -83, 3, 126, TarConstants.LF_CHR}, new byte[]{-78, -52, 11, -71, -108, 23, BinaryMemcacheOpcodes.GATK, ByteSourceJsonBootstrapper.UTF8_BOM_3, 105, 2, -117, 46, -115, -10, -104, 0}, new byte[]{-6, 4, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -90, -34, 111, 85, 114, 74, -86, -98, -56, 92, -99, 45, -118}, new byte[]{-33, -76, -97, 43, 107, 119, ClassDefinitionUtils.OPS_aload_0, 18, 14, -6, 79, 46, 41, 18, -97, -44}, new byte[]{30, -95, 3, 68, -12, PrinterUtils.BarCode.CODE128, -94, TarConstants.LF_FIFO, TarConstants.LF_SYMLINK, -42, 17, -82, -69, 106, 18, -18}, new byte[]{-81, 4, PrinterUtils.BarCode.CODE128, Constans.CAN_REV_UV_SYS_RESULT, TarConstants.LF_GNUTYPE_LONGLINK, 5, 0, Constans.CAN_REV_DIS_MODE_SWITCH, 95, -106, 0, -55, -100, -88, -20, -90}, new byte[]{BinaryMemcacheOpcodes.SASL_AUTH, 2, 94, -40, -99, 25, -100, 79, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -94, -57, -29, 39, -27, -109, -20}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_3, 58, -86, -8, -89, ClassDefinitionUtils.OPS_dup, -55, ClassDefinitionUtils.OPS_invokespecial, -71, 40, 46, -51, -126, -44, 1, 115}, new byte[]{98, 96, 112, 13, 97, -122, ClassDefinitionUtils.OPS_areturn, 23, TarConstants.LF_CONTIG, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 16, TarConstants.LF_NORMAL, 125, 107}, new byte[]{90, -54, 69, -62, BinaryMemcacheOpcodes.SASL_AUTH, TarConstants.LF_NORMAL, 4, 67, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -62, -111, TarConstants.LF_GNUTYPE_SPARSE, -10, -4, -102, -58}, new byte[]{-110, BinaryMemcacheOpcodes.GATK, -105, 60, 34, 107, 104, -69, 44, -81, -110, -24, TarConstants.LF_FIFO, -47, -108, 58}};
    private final byte[] buffer;
    private int off;

    public Haraka256Digest() {
        this.buffer = new byte[32];
    }

    public Haraka256Digest(Haraka256Digest haraka256Digest) {
        this.buffer = Arrays.clone(haraka256Digest.buffer);
        this.off = haraka256Digest.off;
    }

    private int haraka256256(byte[] bArr, byte[] bArr2, int i) {
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) byte.class, 2, 16);
        byte[][] bArr4 = (byte[][]) Array.newInstance((Class<?>) byte.class, 2, 16);
        System.arraycopy(bArr, 0, bArr3[0], 0, 16);
        System.arraycopy(bArr, 16, bArr3[1], 0, 16);
        bArr3[0] = aesEnc(bArr3[0], f9202RC[0]);
        bArr3[1] = aesEnc(bArr3[1], f9202RC[1]);
        bArr3[0] = aesEnc(bArr3[0], f9202RC[2]);
        bArr3[1] = aesEnc(bArr3[1], f9202RC[3]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], f9202RC[4]);
        bArr3[1] = aesEnc(bArr4[1], f9202RC[5]);
        bArr3[0] = aesEnc(bArr3[0], f9202RC[6]);
        bArr3[1] = aesEnc(bArr3[1], f9202RC[7]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], f9202RC[8]);
        bArr3[1] = aesEnc(bArr4[1], f9202RC[9]);
        bArr3[0] = aesEnc(bArr3[0], f9202RC[10]);
        bArr3[1] = aesEnc(bArr3[1], f9202RC[11]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], f9202RC[12]);
        bArr3[1] = aesEnc(bArr4[1], f9202RC[13]);
        bArr3[0] = aesEnc(bArr3[0], f9202RC[14]);
        bArr3[1] = aesEnc(bArr3[1], f9202RC[15]);
        mix256(bArr3, bArr4);
        bArr3[0] = aesEnc(bArr4[0], f9202RC[16]);
        bArr3[1] = aesEnc(bArr4[1], f9202RC[17]);
        bArr3[0] = aesEnc(bArr3[0], f9202RC[18]);
        bArr3[1] = aesEnc(bArr3[1], f9202RC[19]);
        mix256(bArr3, bArr4);
        bArr3[0] = xor(bArr4[0], bArr, 0);
        bArr3[1] = xor(bArr4[1], bArr, 16);
        System.arraycopy(bArr3[0], 0, bArr2, i, 16);
        System.arraycopy(bArr3[1], 0, bArr2, i + 16, 16);
        return 32;
    }

    private void mix256(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 0, bArr2[0], 0, 4);
        System.arraycopy(bArr[1], 0, bArr2[0], 4, 4);
        System.arraycopy(bArr[0], 4, bArr2[0], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[0], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[1], 0, 4);
        System.arraycopy(bArr[1], 8, bArr2[1], 4, 4);
        System.arraycopy(bArr[0], 12, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 12, bArr2[1], 12, 4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        if (this.off != 32) {
            throw new IllegalStateException("input must be exactly 32 bytes");
        }
        if (bArr.length - i < 32) {
            throw new IllegalArgumentException("output too short to receive digest");
        }
        int haraka256256 = haraka256256(this.buffer, bArr, i);
        reset();
        return haraka256256;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Haraka-256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.off;
        if (i + 1 > 32) {
            throw new IllegalArgumentException("total input cannot be more than 32 bytes");
        }
        byte[] bArr = this.buffer;
        this.off = i + 1;
        bArr[i] = b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        if (i3 + i2 > 32) {
            throw new IllegalArgumentException("total input cannot be more than 32 bytes");
        }
        System.arraycopy(bArr, i, this.buffer, i3, i2);
        this.off += i2;
    }
}
