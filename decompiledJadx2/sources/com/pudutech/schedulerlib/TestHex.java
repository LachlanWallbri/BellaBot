package com.pudutech.schedulerlib;

import android.util.Log;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes2.dex */
public class TestHex {
    public static byte[] buff = {Ascii.f1926US, -117, 8, 0, 0, 0, 0, 0, 0, 0, -29, -30, 113, TarConstants.LF_DIR, TarConstants.LF_BLK, ClassDefinitionUtils.OPS_return, TarConstants.LF_NORMAL, TarConstants.LF_CONTIG, 117, -78, 112, TarConstants.LF_LINK, -74, 116, 20, 90, -63, -56, -59, 90, -108, -97, -108, 95, 34, TarConstants.LF_BLK, -113, -111, TarConstants.LF_GNUTYPE_LONGLINK, -48, -59, -60, -54, -48, -56, -54, -60, -40, -54, -62, -52, -54, -52, SlipConfig.ESC_END, -54, -60, TarConstants.LF_GNUTYPE_LONGNAME, 96, TarConstants.LF_CHR, -97, 20, 99, -86, 2, -93, -106, 126, TarConstants.LF_GNUTYPE_LONGLINK, 3, -53, Constans.CAN_REV_DIS_MODE_SWITCH, 6, -107, 37, 13, 41, 7, 26, -114, TarConstants.LF_NORMAL, 62, 105, 56, -62, TarConstants.LF_BLK, -95, -15, 9, -13, -111, -58, 37, -84, 13, 77, 45, -20, 71, -102, 90, 56, 39, TarConstants.LF_BLK, 47, -31, 126, -46, -4, Constans.CAN_REV_DIS_MODE_SWITCH, -9, 64, -53, 17, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -113, 44, 70, -52, 69, 6, -58, 86, 64, -62, -44, -125, 43, 64, BinaryMemcacheOpcodes.GATKQ, -118, -97, -9, -20, -103, TarConstants.LF_CHR, -74, -94, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -49, -8, -40, -55, TarConstants.LF_SYMLINK, TarConstants.LF_NORMAL, TarConstants.LF_NORMAL, -40, 39, -15, -13, TarConstants.LF_SYMLINK, TarConstants.LF_NORMAL, 56, 56, -118, -74, -66, -42, 113, -110, 5, -118, -38, 102, -15, -13, -106, ByteSourceJsonBootstrapper.UTF8_BOM_1, TarConstants.LF_GNUTYPE_LONGLINK, 118, 4, -86, -32, 116, -110, 93, -11, ClassDefinitionUtils.OPS_return, -55, -66, Constans.CAN_REV_UV_SYS_RESULT, -97, -41, -106, -53, SlipConfig.ESC_ESC, 81, 84, -66, -75, -39, PrinterUtils.BarCode.CODE128, -42, -40, TarConstants.LF_PAX_EXTENDED_HEADER_LC, ClassDefinitionUtils.OPS_invokespecial, BinaryMemcacheOpcodes.GATK, 0, -81, 111, TarConstants.LF_FIFO, -116, -71, 0, 0, 0, 0, 0, 0, 0};

    public static byte[] intToByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    private static int megreShort(byte b, byte b2) {
        return (b << 8) | (b2 & 255);
    }

    public static String hexToString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i <= bArr.length - 1; i++) {
            byte b = bArr[i];
            sb.append("0x");
            sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b & 15));
            sb.append(" ");
        }
        return sb.toString();
    }

    public static byte checkCrc(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("crcin=");
        int i2 = 65535;
        sb.append(65535);
        sb.append(",cpoly=");
        sb.append(4129);
        sb.append(",byte[7]=");
        sb.append((int) bArr[7]);
        Log.d("TestHex", sb.toString());
        for (int i3 = 0; i3 < i; i3++) {
            i2 ^= bArr[i3] << 8;
            for (int i4 = 0; i4 < 8; i4++) {
                i2 = (short) ((32768 & i2) > 0 ? (i2 << 1) ^ 4129 : i2 << 1);
            }
        }
        return (byte) i2;
    }

    public static void main(String[] strArr) {
        int megreShort = megreShort((byte) -107, (byte) -48);
        int megreShort2 = megreShort((byte) -48, (byte) -107);
        System.out.println("megreShort=" + megreShort + ",megreShort2=" + megreShort2);
    }
}
