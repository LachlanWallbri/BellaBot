package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
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
import org.bouncycastle.util.Pack;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: m1 */
    private static final int f9341m1 = -2139062144;

    /* renamed from: m2 */
    private static final int f9342m2 = 2139062143;

    /* renamed from: m3 */
    private static final int f9343m3 = 27;

    /* renamed from: m4 */
    private static final int f9344m4 = -1061109568;

    /* renamed from: m5 */
    private static final int f9345m5 = 1061109567;

    /* renamed from: C0 */
    private int f9346C0;

    /* renamed from: C1 */
    private int f9347C1;

    /* renamed from: C2 */
    private int f9348C2;

    /* renamed from: C3 */
    private int f9349C3;
    private int ROUNDS;
    private int[][] WorkingKey = (int[][]) null;
    private boolean forEncryption;

    /* renamed from: S */
    private static final byte[] f9339S = {99, 124, 119, 123, -14, 107, 111, -59, TarConstants.LF_NORMAL, 1, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, ClassDefinitionUtils.OPS_dup, 71, -16, -83, -44, -94, -81, -100, -92, 114, SlipConfig.END, ClassDefinitionUtils.OPS_invokespecial, -3, -109, 38, TarConstants.LF_FIFO, 63, -9, -52, TarConstants.LF_BLK, -91, -27, -15, 113, -40, TarConstants.LF_LINK, 21, 4, -57, BinaryMemcacheOpcodes.GATK, -61, 24, -106, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, Constans.CAN_REV_DIS_MODE_SWITCH, TarConstants.LF_GNUTYPE_SPARSE, -47, 0, -19, 32, -4, ClassDefinitionUtils.OPS_return, 91, 106, -53, -66, 57, 74, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, TarConstants.LF_CHR, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, BinaryMemcacheOpcodes.SASL_AUTH, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 79, SlipConfig.ESC_END, 34, ClassDefinitionUtils.OPS_aload_0, -112, Constans.CAN_REV_UV_SYS_RESULT, 70, -18, -72, 20, -34, 94, 11, SlipConfig.ESC, -32, TarConstants.LF_SYMLINK, 58, 10, PrinterUtils.BarCode.CODE128, 6, BinaryMemcacheOpcodes.GATKQ, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, TarConstants.LF_CONTIG, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 37, 46, 28, -90, -76, -58, -24, SlipConfig.ESC_ESC, 116, Ascii.f1926US, TarConstants.LF_GNUTYPE_LONGLINK, -67, -117, -118, 112, 62, -75, 102, PrinterUtils.BarCode.CODE93, 3, -10, 14, 97, TarConstants.LF_DIR, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, Constans.CAN_REV_SPRAY_SYS_RESULT, -23, -50, 85, 40, -33, -116, -95, Constans.CAN_REV_UV_SET_RESULT, 13, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, 45, 15, ClassDefinitionUtils.OPS_areturn, 84, -69, 22};

    /* renamed from: Si */
    private static final byte[] f9340Si = {82, 9, 106, -43, TarConstants.LF_NORMAL, TarConstants.LF_FIFO, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, 64, -93, -98, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, Constans.CAN_REV_SPRAY_SYS_RESULT, TarConstants.LF_BLK, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, TarConstants.LF_SYMLINK, -90, -62, BinaryMemcacheOpcodes.GATK, 61, -18, TarConstants.LF_GNUTYPE_LONGNAME, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, BinaryMemcacheOpcodes.GATKQ, -78, 118, 91, -94, PrinterUtils.BarCode.CODE128, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, PrinterUtils.BarCode.CODE93, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, Constans.CAN_REV_DIS_MODE_SWITCH, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, SlipConfig.ESC_END, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, TarConstants.LF_DIR, -123, -30, -7, TarConstants.LF_CONTIG, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, Constans.CAN_REV_UV_SET_RESULT, 111, ClassDefinitionUtils.OPS_invokespecial, 98, 14, -86, 24, -66, 27, -4, 86, 62, TarConstants.LF_GNUTYPE_LONGLINK, -58, -46, 121, 32, -102, SlipConfig.ESC, SlipConfig.END, -2, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -51, 90, -12, Ascii.f1926US, SlipConfig.ESC_ESC, -88, TarConstants.LF_CHR, Constans.CAN_REV_UV_SYS_RESULT, 7, -57, TarConstants.LF_LINK, ClassDefinitionUtils.OPS_return, 18, 16, ClassDefinitionUtils.OPS_dup, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, 59, 77, -82, ClassDefinitionUtils.OPS_aload_0, -11, ClassDefinitionUtils.OPS_areturn, -56, -21, -69, 60, -125, TarConstants.LF_GNUTYPE_SPARSE, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, BinaryMemcacheOpcodes.SASL_AUTH, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};

    private static int FFmulX(int i) {
        return (((i & f9341m1) >>> 7) * 27) ^ ((f9342m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (f9345m5 & i) << 2;
        int i3 = i & f9344m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private void decryptBlock(int[][] iArr) {
        int i = this.f9346C0;
        int i2 = this.ROUNDS;
        int i3 = i ^ iArr[i2][0];
        int i4 = this.f9347C1 ^ iArr[i2][1];
        int i5 = this.f9348C2 ^ iArr[i2][2];
        int i6 = i2 - 1;
        int i7 = iArr[i2][3] ^ this.f9349C3;
        while (true) {
            byte[] bArr = f9340Si;
            int i8 = i3 & 255;
            if (i6 <= 1) {
                int inv_mcol = inv_mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
                byte[] bArr2 = f9340Si;
                int inv_mcol2 = inv_mcol((bArr2[(i5 >> 24) & 255] << 24) ^ (((bArr2[i4 & 255] & 255) ^ ((bArr2[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
                byte[] bArr3 = f9340Si;
                int inv_mcol3 = inv_mcol((bArr3[(i7 >> 24) & 255] << 24) ^ (((bArr3[i5 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
                byte[] bArr4 = f9340Si;
                int inv_mcol4 = inv_mcol((bArr4[(i3 >> 24) & 255] << 24) ^ (((bArr4[i7 & 255] & 255) ^ ((bArr4[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i6][3];
                byte[] bArr5 = f9340Si;
                this.f9346C0 = ((((bArr5[inv_mcol & 255] & 255) ^ ((bArr5[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol2 >> 24) & 255] << 24)) ^ iArr[0][0];
                this.f9347C1 = ((((bArr5[inv_mcol2 & 255] & 255) ^ ((bArr5[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol3 >> 24) & 255] << 24)) ^ iArr[0][1];
                this.f9348C2 = ((((bArr5[inv_mcol3 & 255] & 255) ^ ((bArr5[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol4 >> 24) & 255] << 24)) ^ iArr[0][2];
                this.f9349C3 = iArr[0][3] ^ ((((bArr5[inv_mcol4 & 255] & 255) ^ ((bArr5[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr5[(inv_mcol >> 24) & 255] << 24));
                return;
            }
            int inv_mcol5 = inv_mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i8] & 255) ^ ((bArr[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i6][0];
            byte[] bArr6 = f9340Si;
            int inv_mcol6 = inv_mcol((bArr6[(i5 >> 24) & 255] << 24) ^ (((bArr6[i4 & 255] & 255) ^ ((bArr6[(i3 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i6][1];
            byte[] bArr7 = f9340Si;
            int inv_mcol7 = inv_mcol((bArr7[(i7 >> 24) & 255] << 24) ^ (((bArr7[i5 & 255] & 255) ^ ((bArr7[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(i3 >> 16) & 255] & 255) << 16))) ^ iArr[i6][2];
            byte[] bArr8 = f9340Si;
            int inv_mcol8 = inv_mcol((bArr8[(i3 >> 24) & 255] << 24) ^ (((bArr8[i7 & 255] & 255) ^ ((bArr8[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr8[(i4 >> 16) & 255] & 255) << 16)));
            int i9 = i6 - 1;
            int i10 = inv_mcol8 ^ iArr[i6][3];
            byte[] bArr9 = f9340Si;
            int inv_mcol9 = inv_mcol((bArr9[(inv_mcol6 >> 24) & 255] << 24) ^ (((bArr9[inv_mcol5 & 255] & 255) ^ ((bArr9[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(inv_mcol7 >> 16) & 255] & 255) << 16))) ^ iArr[i9][0];
            byte[] bArr10 = f9340Si;
            int inv_mcol10 = inv_mcol((bArr10[(inv_mcol7 >> 24) & 255] << 24) ^ (((bArr10[inv_mcol6 & 255] & 255) ^ ((bArr10[(inv_mcol5 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(i10 >> 16) & 255] & 255) << 16))) ^ iArr[i9][1];
            byte[] bArr11 = f9340Si;
            int inv_mcol11 = inv_mcol((bArr11[(i10 >> 24) & 255] << 24) ^ (((bArr11[inv_mcol7 & 255] & 255) ^ ((bArr11[(inv_mcol6 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(inv_mcol5 >> 16) & 255] & 255) << 16))) ^ iArr[i9][2];
            byte[] bArr12 = f9340Si;
            int inv_mcol12 = inv_mcol((((bArr12[i10 & 255] & 255) ^ ((bArr12[(inv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr12[(inv_mcol6 >> 16) & 255] & 255) << 16)) ^ (bArr12[(inv_mcol5 >> 24) & 255] << 24));
            int i11 = i9 - 1;
            i7 = iArr[i9][3] ^ inv_mcol12;
            i3 = inv_mcol9;
            i4 = inv_mcol10;
            i5 = inv_mcol11;
            i6 = i11;
        }
    }

    private void encryptBlock(int[][] iArr) {
        int i = this.f9346C0 ^ iArr[0][0];
        int i2 = this.f9347C1 ^ iArr[0][1];
        int i3 = this.f9348C2 ^ iArr[0][2];
        int i4 = this.f9349C3 ^ iArr[0][3];
        int i5 = i3;
        int i6 = i2;
        int i7 = i;
        int i8 = 1;
        while (i8 < this.ROUNDS - 1) {
            byte[] bArr = f9339S;
            int mcol = mcol((bArr[(i4 >> 24) & 255] << 24) ^ (((bArr[i7 & 255] & 255) ^ ((bArr[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i8][0];
            byte[] bArr2 = f9339S;
            int mcol2 = mcol((bArr2[(i7 >> 24) & 255] << 24) ^ (((bArr2[i6 & 255] & 255) ^ ((bArr2[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i8][1];
            byte[] bArr3 = f9339S;
            int mcol3 = mcol((bArr3[(i6 >> 24) & 255] << 24) ^ (((bArr3[i5 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i8][2];
            byte[] bArr4 = f9339S;
            int mcol4 = mcol(((((bArr4[(i7 >> 8) & 255] & 255) << 8) ^ (bArr4[i4 & 255] & 255)) ^ ((bArr4[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i5 >> 24) & 255] << 24));
            int i9 = i8 + 1;
            int i10 = iArr[i8][3] ^ mcol4;
            byte[] bArr5 = f9339S;
            i7 = mcol((bArr5[(i10 >> 24) & 255] << 24) ^ (((bArr5[mcol & 255] & 255) ^ ((bArr5[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr5[(mcol3 >> 16) & 255] & 255) << 16))) ^ iArr[i9][0];
            byte[] bArr6 = f9339S;
            int mcol5 = mcol((bArr6[(mcol >> 24) & 255] << 24) ^ (((bArr6[mcol2 & 255] & 255) ^ ((bArr6[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr6[(i10 >> 16) & 255] & 255) << 16))) ^ iArr[i9][1];
            byte[] bArr7 = f9339S;
            int mcol6 = mcol((bArr7[(mcol2 >> 24) & 255] << 24) ^ (((bArr7[mcol3 & 255] & 255) ^ ((bArr7[(i10 >> 8) & 255] & 255) << 8)) ^ ((bArr7[(mcol >> 16) & 255] & 255) << 16))) ^ iArr[i9][2];
            byte[] bArr8 = f9339S;
            int mcol7 = mcol((((bArr8[i10 & 255] & 255) ^ ((bArr8[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr8[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr8[(mcol3 >> 24) & 255] << 24));
            int i11 = i9 + 1;
            int i12 = mcol7 ^ iArr[i9][3];
            i6 = mcol5;
            i5 = mcol6;
            i4 = i12;
            i8 = i11;
        }
        byte[] bArr9 = f9339S;
        int mcol8 = mcol((bArr9[(i4 >> 24) & 255] << 24) ^ (((bArr9[i7 & 255] & 255) ^ ((bArr9[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr9[(i5 >> 16) & 255] & 255) << 16))) ^ iArr[i8][0];
        byte[] bArr10 = f9339S;
        int mcol9 = mcol((bArr10[(i7 >> 24) & 255] << 24) ^ (((bArr10[i6 & 255] & 255) ^ ((bArr10[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr10[(i4 >> 16) & 255] & 255) << 16))) ^ iArr[i8][1];
        byte[] bArr11 = f9339S;
        int mcol10 = mcol((bArr11[(i6 >> 24) & 255] << 24) ^ (((bArr11[i5 & 255] & 255) ^ ((bArr11[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr11[(i7 >> 16) & 255] & 255) << 16))) ^ iArr[i8][2];
        byte[] bArr12 = f9339S;
        int mcol11 = mcol(((((bArr12[(i7 >> 8) & 255] & 255) << 8) ^ (bArr12[i4 & 255] & 255)) ^ ((bArr12[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr12[(i5 >> 24) & 255] << 24));
        int i13 = i8 + 1;
        int i14 = iArr[i8][3] ^ mcol11;
        byte[] bArr13 = f9339S;
        this.f9346C0 = iArr[i13][0] ^ ((((bArr13[mcol8 & 255] & 255) ^ ((bArr13[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr13[(i14 >> 24) & 255] << 24));
        this.f9347C1 = ((((bArr13[mcol9 & 255] & 255) ^ ((bArr13[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(i14 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol8 >> 24) & 255] << 24)) ^ iArr[i13][1];
        this.f9348C2 = ((((bArr13[mcol10 & 255] & 255) ^ ((bArr13[(i14 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol9 >> 24) & 255] << 24)) ^ iArr[i13][2];
        this.f9349C3 = iArr[i13][3] ^ ((((bArr13[i14 & 255] & 255) ^ ((bArr13[(mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr13[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr13[(mcol10 >> 24) & 255] << 24));
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >> 2;
        this.ROUNDS = i + 6;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, this.ROUNDS + 1, 4);
        if (i == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            int i2 = littleEndianToInt3;
            int i3 = littleEndianToInt;
            int i4 = littleEndianToInt4;
            for (int i5 = 1; i5 <= 10; i5++) {
                i3 ^= subWord(shift(i4, 8)) ^ rcon[i5 - 1];
                iArr[i5][0] = i3;
                littleEndianToInt2 ^= i3;
                iArr[i5][1] = littleEndianToInt2;
                i2 ^= littleEndianToInt2;
                iArr[i5][2] = i2;
                i4 ^= i2;
                iArr[i5][3] = i4;
            }
        } else if (i == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt9;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt10;
            int subWord = littleEndianToInt5 ^ (subWord(shift(littleEndianToInt10, 8)) ^ 1);
            iArr[1][2] = subWord;
            int i6 = littleEndianToInt6 ^ subWord;
            iArr[1][3] = i6;
            int i7 = littleEndianToInt7 ^ i6;
            iArr[2][0] = i7;
            int i8 = littleEndianToInt8 ^ i7;
            iArr[2][1] = i8;
            int i9 = littleEndianToInt9 ^ i8;
            iArr[2][2] = i9;
            int i10 = littleEndianToInt10 ^ i9;
            iArr[2][3] = i10;
            int i11 = i9;
            int i12 = 2;
            int i13 = i8;
            int i14 = i7;
            int i15 = subWord;
            int i16 = i10;
            for (int i17 = 3; i17 < 12; i17 += 3) {
                int subWord2 = subWord(shift(i16, 8)) ^ i12;
                int i18 = i12 << 1;
                int i19 = i15 ^ subWord2;
                iArr[i17][0] = i19;
                int i20 = i6 ^ i19;
                iArr[i17][1] = i20;
                int i21 = i14 ^ i20;
                iArr[i17][2] = i21;
                int i22 = i13 ^ i21;
                iArr[i17][3] = i22;
                int i23 = i11 ^ i22;
                int i24 = i17 + 1;
                iArr[i24][0] = i23;
                int i25 = i16 ^ i23;
                iArr[i24][1] = i25;
                int subWord3 = subWord(shift(i25, 8)) ^ i18;
                i12 = i18 << 1;
                i15 = i19 ^ subWord3;
                iArr[i24][2] = i15;
                i6 = i20 ^ i15;
                iArr[i24][3] = i6;
                i14 = i21 ^ i6;
                int i26 = i17 + 2;
                iArr[i26][0] = i14;
                i13 = i22 ^ i14;
                iArr[i26][1] = i13;
                i11 = i23 ^ i13;
                iArr[i26][2] = i11;
                i16 = i25 ^ i11;
                iArr[i26][3] = i16;
            }
            int subWord4 = (subWord(shift(i16, 8)) ^ i12) ^ i15;
            iArr[12][0] = subWord4;
            int i27 = subWord4 ^ i6;
            iArr[12][1] = i27;
            int i28 = i27 ^ i14;
            iArr[12][2] = i28;
            iArr[12][3] = i28 ^ i13;
        } else {
            if (i != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i29 = littleEndianToInt11;
            int i30 = littleEndianToInt17;
            int i31 = littleEndianToInt18;
            int i32 = littleEndianToInt16;
            int i33 = littleEndianToInt15;
            int i34 = 1;
            for (int i35 = 2; i35 < 14; i35 += 2) {
                int subWord5 = subWord(shift(i31, 8)) ^ i34;
                i34 <<= 1;
                i29 ^= subWord5;
                iArr[i35][0] = i29;
                littleEndianToInt12 ^= i29;
                iArr[i35][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr[i35][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr[i35][3] = littleEndianToInt14;
                i33 ^= subWord(littleEndianToInt14);
                int i36 = i35 + 1;
                iArr[i36][0] = i33;
                i32 ^= i33;
                iArr[i36][1] = i32;
                i30 ^= i32;
                iArr[i36][2] = i30;
                i31 ^= i30;
                iArr[i36][3] = i31;
            }
            int subWord6 = (subWord(shift(i31, 8)) ^ i34) ^ i29;
            iArr[14][0] = subWord6;
            int i37 = subWord6 ^ littleEndianToInt12;
            iArr[14][1] = i37;
            int i38 = i37 ^ littleEndianToInt13;
            iArr[14][2] = i38;
            iArr[14][3] = i38 ^ littleEndianToInt14;
        }
        if (!z) {
            for (int i39 = 1; i39 < this.ROUNDS; i39++) {
                for (int i40 = 0; i40 < 4; i40++) {
                    iArr[i39][i40] = inv_mcol(iArr[i39][i40]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int shift = shift(i, 8) ^ i;
        int FFmulX = i ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i) {
        int shift = shift(i, 8);
        int i2 = i ^ shift;
        return FFmulX(i2) ^ (shift ^ shift(i2, 16));
    }

    private void packBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = this.f9346C0;
        bArr[i] = (byte) i3;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >> 24);
        int i7 = i6 + 1;
        int i8 = this.f9347C1;
        bArr[i6] = (byte) i8;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >> 24);
        int i12 = i11 + 1;
        int i13 = this.f9348C2;
        bArr[i11] = (byte) i13;
        int i14 = i12 + 1;
        bArr[i12] = (byte) (i13 >> 8);
        int i15 = i14 + 1;
        bArr[i14] = (byte) (i13 >> 16);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i13 >> 24);
        int i17 = i16 + 1;
        int i18 = this.f9349C3;
        bArr[i16] = (byte) i18;
        int i19 = i17 + 1;
        bArr[i17] = (byte) (i18 >> 8);
        bArr[i19] = (byte) (i18 >> 16);
        bArr[i19 + 1] = (byte) (i18 >> 24);
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = f9339S;
        return (bArr[(i >> 24) & 255] << 24) | (bArr[i & 255] & 255) | ((bArr[(i >> 8) & 255] & 255) << 8) | ((bArr[(i >> 16) & 255] & 255) << 16);
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        this.f9346C0 = bArr[i] & 255;
        int i3 = i2 + 1;
        this.f9346C0 |= (bArr[i2] & 255) << 8;
        int i4 = i3 + 1;
        this.f9346C0 |= (bArr[i3] & 255) << 16;
        int i5 = i4 + 1;
        this.f9346C0 |= bArr[i4] << 24;
        int i6 = i5 + 1;
        this.f9347C1 = bArr[i5] & 255;
        int i7 = i6 + 1;
        this.f9347C1 = ((bArr[i6] & 255) << 8) | this.f9347C1;
        int i8 = i7 + 1;
        this.f9347C1 |= (bArr[i7] & 255) << 16;
        int i9 = i8 + 1;
        this.f9347C1 |= bArr[i8] << 24;
        int i10 = i9 + 1;
        this.f9348C2 = bArr[i9] & 255;
        int i11 = i10 + 1;
        this.f9348C2 = ((bArr[i10] & 255) << 8) | this.f9348C2;
        int i12 = i11 + 1;
        this.f9348C2 |= (bArr[i11] & 255) << 16;
        int i13 = i12 + 1;
        this.f9348C2 |= bArr[i12] << 24;
        int i14 = i13 + 1;
        this.f9349C3 = bArr[i13] & 255;
        int i15 = i14 + 1;
        this.f9349C3 = ((bArr[i14] & 255) << 8) | this.f9349C3;
        this.f9349C3 |= (bArr[i15] & 255) << 16;
        this.f9349C3 = (bArr[i15 + 1] << 24) | this.f9349C3;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        boolean z = this.forEncryption;
        unpackBlock(bArr, i);
        int[][] iArr = this.WorkingKey;
        if (z) {
            encryptBlock(iArr);
        } else {
            decryptBlock(iArr);
        }
        packBlock(bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
