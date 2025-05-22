package org.bouncycastle.crypto.prng;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class VMPCRandomGenerator implements RandomGenerator {

    /* renamed from: n */
    private byte f9615n = 0;

    /* renamed from: P */
    private byte[] f9614P = {-69, 44, 98, Byte.MAX_VALUE, -75, -86, -44, 13, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -2, -78, -126, -53, -96, -95, 8, 24, 113, 86, -24, PrinterUtils.BarCode.CODE128, 2, 16, -60, -34, TarConstants.LF_DIR, -91, -20, Byte.MIN_VALUE, 18, -72, 105, -38, 47, 117, -52, -94, 9, TarConstants.LF_FIFO, 3, 97, 45, -3, -32, SlipConfig.ESC_ESC, 5, 67, -112, -83, -56, -31, -81, 87, -101, TarConstants.LF_GNUTYPE_LONGNAME, -40, 81, -82, 80, -123, 60, 10, -28, -13, -100, 38, BinaryMemcacheOpcodes.GATK, TarConstants.LF_GNUTYPE_SPARSE, -55, -125, -105, 70, ClassDefinitionUtils.OPS_return, -103, 100, TarConstants.LF_LINK, 119, -43, 29, -42, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -67, 94, ClassDefinitionUtils.OPS_areturn, -118, 34, 56, -8, 104, 43, ClassDefinitionUtils.OPS_aload_0, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, 111, -33, 4, -27, -107, 62, 37, -122, -90, 11, -113, -15, BinaryMemcacheOpcodes.GATKQ, 14, -41, 64, -77, -49, 126, 6, 21, -102, 77, 28, -93, SlipConfig.ESC, TarConstants.LF_SYMLINK, -110, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 17, 39, -12, ClassDefinitionUtils.OPS_dup, -48, 78, 106, 23, 91, -84, -1, 7, SlipConfig.END, 101, 121, -4, -57, -51, 118, 66, 93, -25, 58, TarConstants.LF_BLK, 122, TarConstants.LF_NORMAL, 40, 15, 115, 1, -7, -47, -46, 25, -23, -111, -71, 90, -19, 65, 109, -76, -61, -98, ByteSourceJsonBootstrapper.UTF8_BOM_3, 99, -6, Ascii.f1926US, TarConstants.LF_CHR, 96, 71, Constans.CAN_REV_UV_SET_RESULT, -16, -106, 26, 95, -109, 61, TarConstants.LF_CONTIG, TarConstants.LF_GNUTYPE_LONGLINK, -39, -88, -63, 27, -10, 57, -117, ClassDefinitionUtils.OPS_invokespecial, 12, 32, -50, Constans.CAN_REV_UV_SYS_RESULT, 110, -74, 116, -114, -115, 22, 41, -14, Constans.CAN_REV_SPRAY_SYS_RESULT, -11, -21, 112, -29, -5, 85, -97, -58, 68, 74, 69, 125, -30, 107, 92, 108, 102, -87, -116, -18, Constans.CAN_REV_DIS_MODE_SWITCH, 19, -89, 30, -99, SlipConfig.ESC_END, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, PrinterUtils.BarCode.CODE93, -70, 46, -26, -92, -85, 124, -108, 0, BinaryMemcacheOpcodes.SASL_AUTH, ByteSourceJsonBootstrapper.UTF8_BOM_1, -22, -66, -54, 114, 79, 82, -104, 63, -62, 20, 123, 59, 84};

    /* renamed from: s */
    private byte f9616s = -66;

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.f9614P;
            byte b2 = this.f9616s;
            byte b3 = this.f9615n;
            this.f9616s = bArr2[(b2 + bArr2[b3 & 255] + b) & 255];
            byte b4 = bArr2[b3 & 255];
            byte b5 = this.f9616s;
            bArr2[b3 & 255] = bArr2[b5 & 255];
            bArr2[b5 & 255] = b4;
            this.f9615n = (byte) ((b3 + 1) & 255);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this.f9614P) {
            int i3 = i2 + i;
            while (i != i3) {
                this.f9616s = this.f9614P[(this.f9616s + this.f9614P[this.f9615n & 255]) & 255];
                bArr[i] = this.f9614P[(this.f9614P[this.f9614P[this.f9616s & 255] & 255] + 1) & 255];
                byte b = this.f9614P[this.f9615n & 255];
                this.f9614P[this.f9615n & 255] = this.f9614P[this.f9616s & 255];
                this.f9614P[this.f9616s & 255] = b;
                this.f9615n = (byte) ((this.f9615n + 1) & 255);
                i++;
            }
        }
    }
}
