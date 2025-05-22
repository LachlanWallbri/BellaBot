package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import java.lang.reflect.Array;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;

    /* renamed from: A0 */
    private long f9409A0;

    /* renamed from: A1 */
    private long f9410A1;

    /* renamed from: A2 */
    private long f9411A2;

    /* renamed from: A3 */
    private long f9412A3;

    /* renamed from: BC */
    private int f9413BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;
    private static final byte[] logtable = {0, 0, 25, 1, TarConstants.LF_SYMLINK, 2, 26, -58, TarConstants.LF_GNUTYPE_LONGLINK, -57, 27, 104, TarConstants.LF_CHR, -18, -33, 3, 100, 4, -32, 14, TarConstants.LF_BLK, -115, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, ByteSourceJsonBootstrapper.UTF8_BOM_1, TarConstants.LF_GNUTYPE_LONGNAME, 113, 8, -56, -8, 105, 28, -63, 125, -62, 29, -75, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 101, 47, -118, 5, BinaryMemcacheOpcodes.SASL_AUTH, 15, -31, BinaryMemcacheOpcodes.GATKQ, 18, -16, -126, 69, TarConstants.LF_DIR, -109, -38, -114, -106, -113, SlipConfig.ESC, -67, TarConstants.LF_FIFO, -48, -50, -108, 19, 92, -46, -15, 64, 70, -125, 56, 102, SlipConfig.ESC_ESC, -3, TarConstants.LF_NORMAL, ByteSourceJsonBootstrapper.UTF8_BOM_3, 6, -117, 98, -77, 37, -30, -104, 34, Constans.CAN_REV_UV_SYS_RESULT, -111, 16, 126, 110, PrinterUtils.BarCode.CODE93, -61, -93, -74, 30, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, 121, 10, 21, -101, -97, 94, -54, 78, -44, -84, -27, -13, 115, -89, 87, -81, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -88, 80, -12, -22, -42, 116, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, 22, 11, -11, ClassDefinitionUtils.OPS_dup, -53, 95, ClassDefinitionUtils.OPS_areturn, -100, -87, 81, -96, Byte.MAX_VALUE, 12, -10, 111, 23, -60, PrinterUtils.BarCode.CODE128, -20, -40, 67, Ascii.f1926US, 45, -92, 118, 123, ClassDefinitionUtils.OPS_invokespecial, -52, -69, 62, 90, -5, 96, ClassDefinitionUtils.OPS_return, -122, 59, 82, -95, 108, -86, 85, 41, -99, -105, -78, Constans.CAN_REV_SPRAY_SYS_RESULT, -112, 97, -66, SlipConfig.ESC_END, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, TarConstants.LF_CONTIG, 63, 91, -47, TarConstants.LF_GNUTYPE_SPARSE, 57, Constans.CAN_REV_DIS_MODE_SWITCH, 60, 65, -94, 109, 71, 20, ClassDefinitionUtils.OPS_aload_0, -98, 93, 86, -14, -45, -85, 68, 17, -110, -39, BinaryMemcacheOpcodes.GATK, 32, 46, Constans.CAN_REV_UV_SET_RESULT, -76, 124, -72, 38, 119, -103, -29, -91, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 74, -19, -34, -59, TarConstants.LF_LINK, -2, 24, 13, 99, -116, Byte.MIN_VALUE, SlipConfig.END, -9, 112, 7};
    private static final byte[] aLogtable = {0, 3, 5, 15, 17, TarConstants.LF_CHR, 85, -1, 26, 46, 114, -106, -95, -8, 19, TarConstants.LF_DIR, 95, -31, 56, PrinterUtils.BarCode.CODE93, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, TarConstants.LF_BLK, 92, -28, TarConstants.LF_CONTIG, ClassDefinitionUtils.OPS_dup, -21, 38, 106, -66, -39, 112, -112, -85, -26, TarConstants.LF_LINK, TarConstants.LF_GNUTYPE_SPARSE, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, TarConstants.LF_GNUTYPE_LONGNAME, -44, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -87, -32, 59, 77, -41, 98, -90, -15, 8, 24, 40, TarConstants.LF_PAX_EXTENDED_HEADER_LC, Constans.CAN_REV_UV_SYS_RESULT, -125, -98, -71, -48, 107, -67, SlipConfig.ESC_END, Byte.MAX_VALUE, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -104, -77, -50, PrinterUtils.BarCode.CODE128, SlipConfig.ESC, 118, -102, -75, -60, 87, -7, 16, TarConstants.LF_NORMAL, 80, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, 125, Constans.CAN_REV_SPRAY_SYS_RESULT, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, 22, 58, 78, -46, 109, ClassDefinitionUtils.OPS_invokespecial, -62, 93, -25, TarConstants.LF_SYMLINK, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, 64, SlipConfig.END, 91, -19, 44, 116, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, -38, 117, -97, -70, -43, 100, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, ClassDefinitionUtils.OPS_aload_0, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, Constans.CAN_REV_UV_SET_RESULT, Byte.MIN_VALUE, -101, -74, -63, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -24, BinaryMemcacheOpcodes.GATK, 101, -81, -22, 37, 111, ClassDefinitionUtils.OPS_return, -56, 67, -59, 84, -4, Ascii.f1926US, BinaryMemcacheOpcodes.SASL_AUTH, 99, -91, -12, 7, 9, 27, 45, 119, -103, ClassDefinitionUtils.OPS_areturn, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, 18, TarConstants.LF_FIFO, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, 23, 57, TarConstants.LF_GNUTYPE_LONGLINK, SlipConfig.ESC_ESC, 124, Constans.CAN_REV_DIS_MODE_SWITCH, -105, -94, -3, 28, BinaryMemcacheOpcodes.GATKQ, 108, -76, -57, 82, -10, 1, 3, 5, 15, 17, TarConstants.LF_CHR, 85, -1, 26, 46, 114, -106, -95, -8, 19, TarConstants.LF_DIR, 95, -31, 56, PrinterUtils.BarCode.CODE93, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, TarConstants.LF_BLK, 92, -28, TarConstants.LF_CONTIG, ClassDefinitionUtils.OPS_dup, -21, 38, 106, -66, -39, 112, -112, -85, -26, TarConstants.LF_LINK, TarConstants.LF_GNUTYPE_SPARSE, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, TarConstants.LF_GNUTYPE_LONGNAME, -44, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -87, -32, 59, 77, -41, 98, -90, -15, 8, 24, 40, TarConstants.LF_PAX_EXTENDED_HEADER_LC, Constans.CAN_REV_UV_SYS_RESULT, -125, -98, -71, -48, 107, -67, SlipConfig.ESC_END, Byte.MAX_VALUE, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -104, -77, -50, PrinterUtils.BarCode.CODE128, SlipConfig.ESC, 118, -102, -75, -60, 87, -7, 16, TarConstants.LF_NORMAL, 80, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, 125, Constans.CAN_REV_SPRAY_SYS_RESULT, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, 22, 58, 78, -46, 109, ClassDefinitionUtils.OPS_invokespecial, -62, 93, -25, TarConstants.LF_SYMLINK, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, 64, SlipConfig.END, 91, -19, 44, 116, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, -38, 117, -97, -70, -43, 100, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, ClassDefinitionUtils.OPS_aload_0, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, Constans.CAN_REV_UV_SET_RESULT, Byte.MIN_VALUE, -101, -74, -63, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -24, BinaryMemcacheOpcodes.GATK, 101, -81, -22, 37, 111, ClassDefinitionUtils.OPS_return, -56, 67, -59, 84, -4, Ascii.f1926US, BinaryMemcacheOpcodes.SASL_AUTH, 99, -91, -12, 7, 9, 27, 45, 119, -103, ClassDefinitionUtils.OPS_areturn, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, 18, TarConstants.LF_FIFO, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, 23, 57, TarConstants.LF_GNUTYPE_LONGLINK, SlipConfig.ESC_ESC, 124, Constans.CAN_REV_DIS_MODE_SWITCH, -105, -94, -3, 28, BinaryMemcacheOpcodes.GATKQ, 108, -76, -57, 82, -10, 1};

    /* renamed from: S */
    private static final byte[] f9407S = {99, 124, 119, 123, -14, 107, 111, -59, TarConstants.LF_NORMAL, 1, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, ClassDefinitionUtils.OPS_dup, 71, -16, -83, -44, -94, -81, -100, -92, 114, SlipConfig.END, ClassDefinitionUtils.OPS_invokespecial, -3, -109, 38, TarConstants.LF_FIFO, 63, -9, -52, TarConstants.LF_BLK, -91, -27, -15, 113, -40, TarConstants.LF_LINK, 21, 4, -57, BinaryMemcacheOpcodes.GATK, -61, 24, -106, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, Constans.CAN_REV_DIS_MODE_SWITCH, TarConstants.LF_GNUTYPE_SPARSE, -47, 0, -19, 32, -4, ClassDefinitionUtils.OPS_return, 91, 106, -53, -66, 57, 74, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, TarConstants.LF_CHR, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, BinaryMemcacheOpcodes.SASL_AUTH, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 79, SlipConfig.ESC_END, 34, ClassDefinitionUtils.OPS_aload_0, -112, Constans.CAN_REV_UV_SYS_RESULT, 70, -18, -72, 20, -34, 94, 11, SlipConfig.ESC, -32, TarConstants.LF_SYMLINK, 58, 10, PrinterUtils.BarCode.CODE128, 6, BinaryMemcacheOpcodes.GATKQ, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, TarConstants.LF_CONTIG, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 37, 46, 28, -90, -76, -58, -24, SlipConfig.ESC_ESC, 116, Ascii.f1926US, TarConstants.LF_GNUTYPE_LONGLINK, -67, -117, -118, 112, 62, -75, 102, PrinterUtils.BarCode.CODE93, 3, -10, 14, 97, TarConstants.LF_DIR, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, Constans.CAN_REV_SPRAY_SYS_RESULT, -23, -50, 85, 40, -33, -116, -95, Constans.CAN_REV_UV_SET_RESULT, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, 45, 15, ClassDefinitionUtils.OPS_areturn, 84, -69, 22};

    /* renamed from: Si */
    private static final byte[] f9408Si = {82, 9, 106, -43, TarConstants.LF_NORMAL, TarConstants.LF_FIFO, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, 64, -93, -98, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, Constans.CAN_REV_SPRAY_SYS_RESULT, TarConstants.LF_BLK, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, TarConstants.LF_SYMLINK, -90, -62, BinaryMemcacheOpcodes.GATK, 61, -18, TarConstants.LF_GNUTYPE_LONGNAME, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, BinaryMemcacheOpcodes.GATKQ, -78, 118, 91, -94, PrinterUtils.BarCode.CODE128, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, PrinterUtils.BarCode.CODE93, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, Constans.CAN_REV_DIS_MODE_SWITCH, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, SlipConfig.ESC_END, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, TarConstants.LF_DIR, -123, -30, -7, TarConstants.LF_CONTIG, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, Constans.CAN_REV_UV_SET_RESULT, 111, ClassDefinitionUtils.OPS_invokespecial, 98, 14, -86, 24, -66, 27, -4, 86, 62, TarConstants.LF_GNUTYPE_LONGLINK, -58, -46, 121, 32, -102, SlipConfig.ESC, SlipConfig.END, -2, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -51, 90, -12, Ascii.f1926US, SlipConfig.ESC_ESC, -88, TarConstants.LF_CHR, Constans.CAN_REV_UV_SYS_RESULT, 7, -57, TarConstants.LF_LINK, ClassDefinitionUtils.OPS_return, 18, 16, ClassDefinitionUtils.OPS_dup, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, 59, 77, -82, ClassDefinitionUtils.OPS_aload_0, -11, ClassDefinitionUtils.OPS_areturn, -56, -21, -69, 60, -125, TarConstants.LF_GNUTYPE_SPARSE, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, BinaryMemcacheOpcodes.SASL_AUTH, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};
    static byte[][] shifts0 = {new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, 24, 32}};
    static byte[][] shifts1 = {new byte[]{0, 24, 16, 8}, new byte[]{0, 32, 24, 16}, new byte[]{0, 40, 32, 24}, new byte[]{0, TarConstants.LF_NORMAL, 40, 24}, new byte[]{0, 56, 40, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.f9413BC = 32;
            this.BC_MASK = 4294967295L;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (i == 160) {
            this.f9413BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (i == 192) {
            this.f9413BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (i == 224) {
            this.f9413BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else {
            if (i != 256) {
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
            }
            this.f9413BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        }
        this.blockBits = i;
    }

    private void InvMixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.f9413BC; i += 8) {
            int i2 = (int) ((this.f9409A0 >> i) & 255);
            int i3 = (int) ((this.f9410A1 >> i) & 255);
            int i4 = (int) ((this.f9411A2 >> i) & 255);
            long j5 = j4;
            int i5 = (int) ((this.f9412A3 >> i) & 255);
            int i6 = -1;
            int i7 = i2 != 0 ? logtable[i2 & 255] & 255 : -1;
            int i8 = i3 != 0 ? logtable[i3 & 255] & 255 : -1;
            int i9 = i4 != 0 ? logtable[i4 & 255] & 255 : -1;
            if (i5 != 0) {
                i6 = logtable[i5 & 255] & 255;
            }
            j |= ((((mul0xe(i7) ^ mul0xb(i8)) ^ mul0xd(i9)) ^ mul0x9(i6)) & 255) << i;
            j2 |= ((((mul0xe(i8) ^ mul0xb(i9)) ^ mul0xd(i6)) ^ mul0x9(i7)) & 255) << i;
            j3 |= ((((mul0xe(i9) ^ mul0xb(i6)) ^ mul0xd(i7)) ^ mul0x9(i8)) & 255) << i;
            j4 = (((((mul0xe(i6) ^ mul0xb(i7)) ^ mul0xd(i8)) ^ mul0x9(i9)) & 255) << i) | j5;
        }
        this.f9409A0 = j;
        this.f9410A1 = j2;
        this.f9411A2 = j3;
        this.f9412A3 = j4;
    }

    private void KeyAddition(long[] jArr) {
        this.f9409A0 ^= jArr[0];
        this.f9410A1 ^= jArr[1];
        this.f9411A2 ^= jArr[2];
        this.f9412A3 ^= jArr[3];
    }

    private void MixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.f9413BC; i += 8) {
            int i2 = (int) ((this.f9409A0 >> i) & 255);
            int i3 = (int) ((this.f9410A1 >> i) & 255);
            int i4 = (int) ((this.f9411A2 >> i) & 255);
            long j5 = j4;
            int i5 = (int) ((this.f9412A3 >> i) & 255);
            j |= ((((mul0x2(i2) ^ mul0x3(i3)) ^ i4) ^ i5) & 255) << i;
            j2 |= ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i2) & 255) << i;
            j3 |= ((((mul0x2(i4) ^ mul0x3(i5)) ^ i2) ^ i3) & 255) << i;
            j4 = (((((mul0x2(i5) ^ mul0x3(i2)) ^ i3) ^ i4) & 255) << i) | j5;
        }
        this.f9409A0 = j;
        this.f9410A1 = j2;
        this.f9411A2 = j3;
        this.f9412A3 = j4;
    }

    private void ShiftRow(byte[] bArr) {
        this.f9410A1 = shift(this.f9410A1, bArr[1]);
        this.f9411A2 = shift(this.f9411A2, bArr[2]);
        this.f9412A3 = shift(this.f9412A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.f9409A0 = applyS(this.f9409A0, bArr);
        this.f9410A1 = applyS(this.f9410A1, bArr);
        this.f9411A2 = applyS(this.f9411A2, bArr);
        this.f9412A3 = applyS(this.f9412A3, bArr);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.f9413BC; i += 8) {
            j2 |= (bArr[(int) ((j >> i) & 255)] & 255) << i;
        }
        return j2;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(f9408Si);
        ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; i--) {
            KeyAddition(jArr[i]);
            InvMixColumn();
            Substitution(f9408Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i = 1; i < this.ROUNDS; i++) {
            Substitution(f9407S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i]);
        }
        Substitution(f9407S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i;
        int i2;
        int i3;
        int length = bArr.length * 8;
        int i4 = 4;
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance((Class<?>) long.class, 15, 4);
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
            }
            i = 8;
        }
        this.ROUNDS = length >= this.blockBits ? i + 6 : (this.f9413BC / 8) + 6;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < bArr.length) {
            bArr2[i6 % 4][i6 / 4] = bArr[i7];
            i6++;
            i7++;
        }
        int i8 = 0;
        int i9 = 0;
        while (true) {
            i2 = 1;
            if (i8 >= i || i9 >= (this.ROUNDS + 1) * (this.f9413BC / 8)) {
                break;
            }
            int i10 = 0;
            while (i10 < i4) {
                int i11 = this.f9413BC;
                long[] jArr2 = jArr[i9 / (i11 / 8)];
                jArr2[i10] = ((bArr2[i10][i8] & 255) << ((i9 * 8) % i11)) | jArr2[i10];
                i10++;
                bArr2 = bArr2;
                i4 = 4;
            }
            i8++;
            i9++;
            i4 = 4;
        }
        byte[][] bArr3 = bArr2;
        int i12 = 0;
        while (i9 < (this.ROUNDS + i2) * (this.f9413BC / 8)) {
            int i13 = i5;
            while (i13 < 4) {
                byte[] bArr4 = bArr3[i13];
                i13++;
                bArr4[i5] = (byte) (bArr4[i5] ^ f9407S[bArr3[i13 % 4][i - 1] & 255]);
            }
            byte[] bArr5 = bArr3[i5];
            int i14 = i12 + 1;
            bArr5[i5] = (byte) (rcon[i12] ^ bArr5[i5]);
            int i15 = i2;
            if (i <= 6) {
                while (i15 < i) {
                    for (int i16 = i5; i16 < 4; i16++) {
                        byte[] bArr6 = bArr3[i16];
                        bArr6[i15] = (byte) (bArr6[i15] ^ bArr3[i16][i15 - 1]);
                    }
                    i15++;
                }
            } else {
                while (true) {
                    i3 = 4;
                    if (i15 >= 4) {
                        break;
                    }
                    int i17 = i5;
                    while (i17 < i3) {
                        byte[] bArr7 = bArr3[i17];
                        bArr7[i15] = (byte) (bArr7[i15] ^ bArr3[i17][i15 - 1]);
                        i17++;
                        i3 = 4;
                    }
                    i15++;
                }
                for (int i18 = i5; i18 < 4; i18++) {
                    byte[] bArr8 = bArr3[i18];
                    bArr8[4] = (byte) (bArr8[4] ^ f9407S[bArr3[i18][3] & 255]);
                }
                int i19 = 5;
                while (i19 < i) {
                    int i20 = i5;
                    while (i20 < i3) {
                        byte[] bArr9 = bArr3[i20];
                        bArr9[i19] = (byte) (bArr9[i19] ^ bArr3[i20][i19 - 1]);
                        i20++;
                        i3 = 4;
                    }
                    i19++;
                    i3 = 4;
                }
            }
            int i21 = i5;
            while (i21 < i && i9 < (this.ROUNDS + i2) * (this.f9413BC / 8)) {
                int i22 = i5;
                while (i22 < 4) {
                    int i23 = this.f9413BC;
                    long[] jArr3 = jArr[i9 / (i23 / 8)];
                    jArr3[i22] = ((bArr3[i22][i21] & 255) << ((i9 * 8) % i23)) | jArr3[i22];
                    i22++;
                    i14 = i14;
                }
                i21++;
                i9++;
                i5 = 0;
                i2 = 1;
            }
            i12 = i14;
            i5 = 0;
            i2 = 1;
        }
        return jArr;
    }

    private byte mul0x2(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int i) {
        if (i >= 0) {
            return aLogtable[i + 199];
        }
        return (byte) 0;
    }

    private byte mul0xb(int i) {
        if (i >= 0) {
            return aLogtable[i + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int i) {
        if (i >= 0) {
            return aLogtable[i + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int i) {
        if (i >= 0) {
            return aLogtable[i + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.f9413BC; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) (this.f9409A0 >> i2);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (this.f9410A1 >> i2);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.f9411A2 >> i2);
            i = i5 + 1;
            bArr[i5] = (byte) (this.f9412A3 >> i2);
        }
    }

    private long shift(long j, int i) {
        return ((j << (this.f9413BC - i)) | (j >>> i)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int i) {
        this.f9409A0 = bArr[i] & 255;
        this.f9410A1 = bArr[r0] & 255;
        this.f9411A2 = bArr[r8] & 255;
        int i2 = i + 1 + 1 + 1 + 1;
        this.f9412A3 = bArr[r0] & 255;
        for (int i3 = 8; i3 != this.f9413BC; i3 += 8) {
            this.f9409A0 |= (bArr[i2] & 255) << i3;
            this.f9410A1 |= (bArr[r3] & 255) << i3;
            this.f9411A2 |= (bArr[r8] & 255) << i3;
            i2 = i2 + 1 + 1 + 1 + 1;
            this.f9412A3 |= (bArr[r3] & 255) << i3;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f9413BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
        int i3 = this.f9413BC;
        if ((i3 / 2) + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if ((i3 / 2) + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        long[][] jArr = this.workingKey;
        if (z) {
            encryptBlock(jArr);
        } else {
            decryptBlock(jArr);
        }
        packBlock(bArr2, i2);
        return this.f9413BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
