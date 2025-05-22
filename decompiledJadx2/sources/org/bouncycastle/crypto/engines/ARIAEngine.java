package org.bouncycastle.crypto.engines;

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
import org.bouncycastle.util.encoders.Hex;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class ARIAEngine implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;

    /* renamed from: C */
    private static final byte[][] f9350C = {Hex.decodeStrict("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decodeStrict("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decodeStrict("db92371d2126e9700324977504e8c90e")};
    private static final byte[] SB1_sbox = {99, 124, 119, 123, -14, 107, 111, -59, TarConstants.LF_NORMAL, 1, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, ClassDefinitionUtils.OPS_dup, 71, -16, -83, -44, -94, -81, -100, -92, 114, SlipConfig.END, ClassDefinitionUtils.OPS_invokespecial, -3, -109, 38, TarConstants.LF_FIFO, 63, -9, -52, TarConstants.LF_BLK, -91, -27, -15, 113, -40, TarConstants.LF_LINK, 21, 4, -57, BinaryMemcacheOpcodes.GATK, -61, 24, -106, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, Constans.CAN_REV_DIS_MODE_SWITCH, TarConstants.LF_GNUTYPE_SPARSE, -47, 0, -19, 32, -4, ClassDefinitionUtils.OPS_return, 91, 106, -53, -66, 57, 74, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, TarConstants.LF_CHR, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, BinaryMemcacheOpcodes.SASL_AUTH, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 79, SlipConfig.ESC_END, 34, ClassDefinitionUtils.OPS_aload_0, -112, Constans.CAN_REV_UV_SYS_RESULT, 70, -18, -72, 20, -34, 94, 11, SlipConfig.ESC, -32, TarConstants.LF_SYMLINK, 58, 10, PrinterUtils.BarCode.CODE128, 6, BinaryMemcacheOpcodes.GATKQ, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, TarConstants.LF_CONTIG, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 37, 46, 28, -90, -76, -58, -24, SlipConfig.ESC_ESC, 116, Ascii.f1926US, TarConstants.LF_GNUTYPE_LONGLINK, -67, -117, -118, 112, 62, -75, 102, PrinterUtils.BarCode.CODE93, 3, -10, 14, 97, TarConstants.LF_DIR, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, Constans.CAN_REV_SPRAY_SYS_RESULT, -23, -50, 85, 40, -33, -116, -95, Constans.CAN_REV_UV_SET_RESULT, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, 45, 15, ClassDefinitionUtils.OPS_areturn, 84, -69, 22};
    private static final byte[] SB2_sbox = {-30, 78, 84, -4, -108, -62, 74, -52, 98, 13, 106, 70, 60, 77, -117, -47, 94, -6, 100, -53, -76, -105, -66, 43, PSSSigner.TRAILER_IMPLICIT, 119, 46, 3, -45, 25, ClassDefinitionUtils.OPS_dup, -63, 29, 6, 65, 107, 85, -16, -103, 105, -22, -100, 24, -82, 99, -33, -25, -69, 0, 115, 102, -5, -106, TarConstants.LF_GNUTYPE_LONGNAME, -123, -28, 58, 9, 69, -86, 15, -18, 16, -21, 45, Byte.MAX_VALUE, -12, 41, -84, -49, -83, -111, -115, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -56, -107, -7, 47, -50, -51, 8, 122, Constans.CAN_REV_UV_SYS_RESULT, 56, 92, -125, ClassDefinitionUtils.OPS_aload_0, 40, 71, SlipConfig.ESC, -72, -57, -109, -92, 18, TarConstants.LF_GNUTYPE_SPARSE, -1, Constans.CAN_REV_SPRAY_SYS_RESULT, 14, TarConstants.LF_LINK, TarConstants.LF_FIFO, BinaryMemcacheOpcodes.SASL_AUTH, TarConstants.LF_PAX_EXTENDED_HEADER_UC, PrinterUtils.BarCode.CODE93, 1, -114, TarConstants.LF_CONTIG, 116, TarConstants.LF_SYMLINK, -54, -23, ClassDefinitionUtils.OPS_return, ClassDefinitionUtils.OPS_invokespecial, -85, 12, -41, -60, 86, 66, 38, 7, -104, 96, -39, -74, -71, 17, 64, -20, 32, -116, -67, -96, -55, Constans.CAN_REV_DIS_MODE_SWITCH, 4, PrinterUtils.BarCode.CODE128, BinaryMemcacheOpcodes.GATK, -15, 79, 80, Ascii.f1926US, 19, SlipConfig.ESC_END, -40, SlipConfig.END, -98, 87, -29, -61, 123, 101, 59, 2, -113, 62, -24, 37, -110, -27, 21, SlipConfig.ESC_ESC, -3, 23, -87, ByteSourceJsonBootstrapper.UTF8_BOM_3, -44, -102, 126, -59, 57, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -2, 118, -99, 67, -89, -31, -48, -11, 104, -14, 27, TarConstants.LF_BLK, 112, 5, -93, -118, -43, 121, -122, -88, TarConstants.LF_NORMAL, -58, 81, TarConstants.LF_GNUTYPE_LONGLINK, 30, -90, 39, -10, TarConstants.LF_DIR, -46, 110, BinaryMemcacheOpcodes.GATKQ, 22, -126, 95, -38, -26, 117, -94, ByteSourceJsonBootstrapper.UTF8_BOM_1, 44, -78, 28, -97, 93, 111, Byte.MIN_VALUE, 10, 114, 68, -101, 108, -112, 11, 91, TarConstants.LF_CHR, 125, 90, 82, -13, 97, -95, -9, ClassDefinitionUtils.OPS_areturn, -42, 63, 124, 109, -19, 20, -32, -91, 61, 34, -77, -8, Constans.CAN_REV_UV_SET_RESULT, -34, 113, 26, -81, -70, -75, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE};
    private static final byte[] SB3_sbox = {82, 9, 106, -43, TarConstants.LF_NORMAL, TarConstants.LF_FIFO, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, 64, -93, -98, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, Constans.CAN_REV_SPRAY_SYS_RESULT, TarConstants.LF_BLK, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, TarConstants.LF_SYMLINK, -90, -62, BinaryMemcacheOpcodes.GATK, 61, -18, TarConstants.LF_GNUTYPE_LONGNAME, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, BinaryMemcacheOpcodes.GATKQ, -78, 118, 91, -94, PrinterUtils.BarCode.CODE128, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, PrinterUtils.BarCode.CODE93, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, Constans.CAN_REV_DIS_MODE_SWITCH, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, SlipConfig.ESC_END, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, TarConstants.LF_DIR, -123, -30, -7, TarConstants.LF_CONTIG, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, Constans.CAN_REV_UV_SET_RESULT, 111, ClassDefinitionUtils.OPS_invokespecial, 98, 14, -86, 24, -66, 27, -4, 86, 62, TarConstants.LF_GNUTYPE_LONGLINK, -58, -46, 121, 32, -102, SlipConfig.ESC, SlipConfig.END, -2, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -51, 90, -12, Ascii.f1926US, SlipConfig.ESC_ESC, -88, TarConstants.LF_CHR, Constans.CAN_REV_UV_SYS_RESULT, 7, -57, TarConstants.LF_LINK, ClassDefinitionUtils.OPS_return, 18, 16, ClassDefinitionUtils.OPS_dup, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, 59, 77, -82, ClassDefinitionUtils.OPS_aload_0, -11, ClassDefinitionUtils.OPS_areturn, -56, -21, -69, 60, -125, TarConstants.LF_GNUTYPE_SPARSE, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, BinaryMemcacheOpcodes.SASL_AUTH, 12, 125};
    private static final byte[] SB4_sbox = {TarConstants.LF_NORMAL, 104, -103, 27, Constans.CAN_REV_SPRAY_SYS_RESULT, -71, BinaryMemcacheOpcodes.SASL_AUTH, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 80, 57, SlipConfig.ESC, -31, 114, 9, 98, 60, 62, 126, 94, -114, -15, -96, -52, -93, ClassDefinitionUtils.OPS_aload_0, 29, -5, -74, -42, 32, -60, -115, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 101, -11, Constans.CAN_REV_UV_SET_RESULT, -53, -99, 119, -58, 87, 67, 86, 23, -44, 64, 26, 77, SlipConfig.END, 99, 108, -29, ClassDefinitionUtils.OPS_invokespecial, -56, 100, 106, TarConstants.LF_GNUTYPE_SPARSE, -86, 56, -104, 12, -12, -101, -19, Byte.MAX_VALUE, 34, 118, -81, SlipConfig.ESC_ESC, 58, 11, TarConstants.LF_PAX_EXTENDED_HEADER_UC, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, Constans.CAN_REV_UV_SYS_RESULT, 6, -61, TarConstants.LF_DIR, 13, 1, -117, -116, -62, -26, 95, 2, BinaryMemcacheOpcodes.GATKQ, 117, -109, 102, 30, -27, -30, 84, -40, 16, -50, 122, -24, 8, 44, 18, -105, TarConstants.LF_SYMLINK, -85, -76, 39, 10, BinaryMemcacheOpcodes.GATK, -33, ByteSourceJsonBootstrapper.UTF8_BOM_1, -54, -39, -72, -6, SlipConfig.ESC_END, TarConstants.LF_LINK, 107, -47, -83, 25, PrinterUtils.BarCode.CODE128, -67, 81, -106, -18, -28, -88, 65, -38, -1, -51, 85, -122, TarConstants.LF_FIFO, -66, 97, 82, -8, -69, 14, -126, PrinterUtils.BarCode.CODE93, 105, -102, -32, 71, -98, 92, 4, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_BLK, 21, 121, 38, -89, -34, 41, -82, -110, -41, Constans.CAN_REV_DIS_MODE_SWITCH, -23, -46, -70, 93, -13, -59, ClassDefinitionUtils.OPS_areturn, ByteSourceJsonBootstrapper.UTF8_BOM_3, -92, 59, 113, 68, 70, 43, -4, -21, 111, -43, -10, 20, -2, 124, 112, 90, 125, -3, 47, 24, -125, 22, -91, -111, Ascii.f1926US, 5, -107, 116, -87, -63, 91, 74, -123, 109, 19, 7, 79, 78, 69, -78, 15, -55, 28, -90, PSSSigner.TRAILER_IMPLICIT, -20, 115, -112, 123, -49, ClassDefinitionUtils.OPS_dup, -113, -95, -7, 45, -14, ClassDefinitionUtils.OPS_return, 0, -108, TarConstants.LF_CONTIG, -97, -48, 46, -100, 110, 40, 63, Byte.MIN_VALUE, -16, 61, -45, 37, -118, -75, -25, 66, -77, -57, -22, -9, TarConstants.LF_GNUTYPE_LONGNAME, 17, TarConstants.LF_CHR, 3, -94, -84, 96};
    private byte[][] roundKeys;

    /* renamed from: A */
    protected static void m4067A(byte[] bArr) {
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        byte b6 = bArr[5];
        byte b7 = bArr[6];
        byte b8 = bArr[7];
        byte b9 = bArr[8];
        byte b10 = bArr[9];
        byte b11 = bArr[10];
        byte b12 = bArr[11];
        byte b13 = bArr[12];
        byte b14 = bArr[13];
        byte b15 = bArr[14];
        byte b16 = bArr[15];
        bArr[0] = (byte) ((((((b4 ^ b5) ^ b7) ^ b9) ^ b10) ^ b14) ^ b15);
        bArr[1] = (byte) ((((((b3 ^ b6) ^ b8) ^ b9) ^ b10) ^ b13) ^ b16);
        bArr[2] = (byte) ((((((b2 ^ b5) ^ b7) ^ b11) ^ b12) ^ b13) ^ b16);
        bArr[3] = (byte) ((((((b ^ b6) ^ b8) ^ b11) ^ b12) ^ b14) ^ b15);
        int i = b ^ b3;
        bArr[4] = (byte) (((((i ^ b6) ^ b9) ^ b12) ^ b15) ^ b16);
        int i2 = b2 ^ b4;
        bArr[5] = (byte) (((((i2 ^ b5) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[6] = (byte) (((((i ^ b8) ^ b10) ^ b11) ^ b13) ^ b14);
        bArr[7] = (byte) (((((i2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b14);
        int i3 = b ^ b2;
        bArr[8] = (byte) (((((i3 ^ b5) ^ b8) ^ b11) ^ b14) ^ b16);
        bArr[9] = (byte) (((((i3 ^ b6) ^ b7) ^ b12) ^ b13) ^ b15);
        int i4 = b3 ^ b4;
        bArr[10] = (byte) (((((i4 ^ b6) ^ b7) ^ b9) ^ b14) ^ b16);
        bArr[11] = (byte) (((((i4 ^ b5) ^ b8) ^ b10) ^ b13) ^ b15);
        int i5 = b2 ^ b3;
        bArr[12] = (byte) (((((i5 ^ b7) ^ b8) ^ b10) ^ b12) ^ b13);
        int i6 = b ^ b4;
        bArr[13] = (byte) (((((i6 ^ b7) ^ b8) ^ b9) ^ b11) ^ b14);
        bArr[14] = (byte) (((((i6 ^ b5) ^ b6) ^ b10) ^ b12) ^ b15);
        bArr[15] = (byte) (((((i5 ^ b5) ^ b6) ^ b9) ^ b11) ^ b16);
    }

    /* renamed from: FE */
    protected static void m4068FE(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL2(bArr);
        m4067A(bArr);
    }

    /* renamed from: FO */
    protected static void m4069FO(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        SL1(bArr);
        m4067A(bArr);
    }

    protected static byte SB1(byte b) {
        return SB1_sbox[b & 255];
    }

    protected static byte SB2(byte b) {
        return SB2_sbox[b & 255];
    }

    protected static byte SB3(byte b) {
        return SB3_sbox[b & 255];
    }

    protected static byte SB4(byte b) {
        return SB4_sbox[b & 255];
    }

    protected static void SL1(byte[] bArr) {
        bArr[0] = SB1(bArr[0]);
        bArr[1] = SB2(bArr[1]);
        bArr[2] = SB3(bArr[2]);
        bArr[3] = SB4(bArr[3]);
        bArr[4] = SB1(bArr[4]);
        bArr[5] = SB2(bArr[5]);
        bArr[6] = SB3(bArr[6]);
        bArr[7] = SB4(bArr[7]);
        bArr[8] = SB1(bArr[8]);
        bArr[9] = SB2(bArr[9]);
        bArr[10] = SB3(bArr[10]);
        bArr[11] = SB4(bArr[11]);
        bArr[12] = SB1(bArr[12]);
        bArr[13] = SB2(bArr[13]);
        bArr[14] = SB3(bArr[14]);
        bArr[15] = SB4(bArr[15]);
    }

    protected static void SL2(byte[] bArr) {
        bArr[0] = SB3(bArr[0]);
        bArr[1] = SB4(bArr[1]);
        bArr[2] = SB1(bArr[2]);
        bArr[3] = SB2(bArr[3]);
        bArr[4] = SB3(bArr[4]);
        bArr[5] = SB4(bArr[5]);
        bArr[6] = SB1(bArr[6]);
        bArr[7] = SB2(bArr[7]);
        bArr[8] = SB3(bArr[8]);
        bArr[9] = SB4(bArr[9]);
        bArr[10] = SB1(bArr[10]);
        bArr[11] = SB2(bArr[11]);
        bArr[12] = SB3(bArr[12]);
        bArr[13] = SB4(bArr[13]);
        bArr[14] = SB1(bArr[14]);
        bArr[15] = SB2(bArr[15]);
    }

    protected static byte[][] keySchedule(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = (length >>> 3) - 2;
        byte[][] bArr2 = f9350C;
        byte[] bArr3 = bArr2[i];
        byte[] bArr4 = bArr2[(i + 1) % 3];
        byte[] bArr5 = bArr2[(i + 2) % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        m4069FO(bArr9, bArr3);
        xor(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        m4068FE(bArr10, bArr4);
        xor(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        m4069FO(bArr11, bArr5);
        xor(bArr11, bArr9);
        int i2 = (i * 2) + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance((Class<?>) byte.class, i2 + 1, 16);
        keyScheduleRound(bArr12[0], bArr8, bArr9, 19);
        keyScheduleRound(bArr12[1], bArr9, bArr10, 19);
        keyScheduleRound(bArr12[2], bArr10, bArr11, 19);
        keyScheduleRound(bArr12[3], bArr11, bArr8, 19);
        keyScheduleRound(bArr12[4], bArr8, bArr9, 31);
        keyScheduleRound(bArr12[5], bArr9, bArr10, 31);
        keyScheduleRound(bArr12[6], bArr10, bArr11, 31);
        keyScheduleRound(bArr12[7], bArr11, bArr8, 31);
        keyScheduleRound(bArr12[8], bArr8, bArr9, 67);
        keyScheduleRound(bArr12[9], bArr9, bArr10, 67);
        keyScheduleRound(bArr12[10], bArr10, bArr11, 67);
        keyScheduleRound(bArr12[11], bArr11, bArr8, 67);
        keyScheduleRound(bArr12[12], bArr8, bArr9, 97);
        if (i2 > 12) {
            keyScheduleRound(bArr12[13], bArr9, bArr10, 97);
            keyScheduleRound(bArr12[14], bArr10, bArr11, 97);
            if (i2 > 14) {
                keyScheduleRound(bArr12[15], bArr11, bArr8, 97);
                keyScheduleRound(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            reverseKeys(bArr12);
            for (int i3 = 1; i3 < i2; i3++) {
                m4067A(bArr12[i3]);
            }
        }
        return bArr12;
    }

    protected static void keyScheduleRound(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        int i4 = 8 - i3;
        int i5 = bArr3[15 - i2] & 255;
        int i6 = 0;
        while (i6 < 16) {
            int i7 = bArr3[(i6 - i2) & 15] & 255;
            bArr[i6] = (byte) (((i5 << i4) | (i7 >>> i3)) ^ (bArr2[i6] & 255));
            i6++;
            i5 = i7;
        }
    }

    protected static void reverseKeys(byte[][] bArr) {
        int length = bArr.length;
        int i = length / 2;
        int i2 = length - 1;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            int i4 = i2 - i3;
            bArr[i3] = bArr[i4];
            bArr[i4] = bArr2;
        }
    }

    protected static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.roundKeys = keySchedule(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.roundKeys == null) {
            throw new IllegalStateException("ARIA engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, i, bArr3, 0, 16);
        int length = this.roundKeys.length - 3;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            m4069FO(bArr3, this.roundKeys[i3]);
            m4068FE(bArr3, this.roundKeys[i4]);
            i3 = i4 + 1;
        }
        int i5 = i3 + 1;
        m4069FO(bArr3, this.roundKeys[i3]);
        xor(bArr3, this.roundKeys[i5]);
        SL2(bArr3);
        xor(bArr3, this.roundKeys[i5 + 1]);
        System.arraycopy(bArr3, 0, bArr2, i2, 16);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
