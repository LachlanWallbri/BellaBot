package com.google.api.client.testing.util;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.api.client.util.SecurityUtils;
import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SecurityTestUtils {
    private static final byte[] ENCODED_PRIVATE_KEY = {TarConstants.LF_NORMAL, -126, 2, 118, 2, 1, 0, TarConstants.LF_NORMAL, 13, 6, 9, ClassDefinitionUtils.OPS_aload_0, -122, PrinterUtils.BarCode.CODE93, -122, -9, 13, 1, 1, 1, 5, 0, 4, -126, 2, 96, TarConstants.LF_NORMAL, -126, 2, 92, 2, 1, 0, 2, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 0, -89, BinaryMemcacheOpcodes.SASL_AUTH, 8, Constans.CAN_REV_DIS_MODE_SWITCH, 110, -60, ClassDefinitionUtils.OPS_dup, 8, -62, 69, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 95, -59, -43, 13, -18, 123, 29, -31, 13, ClassDefinitionUtils.OPS_areturn, -76, 109, -62, ClassDefinitionUtils.OPS_return, 2, 104, -94, TarConstants.LF_GNUTYPE_LONGNAME, 59, ClassDefinitionUtils.OPS_invokespecial, -26, 99, 123, -57, -92, -100, 116, TarConstants.LF_SYMLINK, -25, 96, TarConstants.LF_DIR, 124, 95, TarConstants.LF_GNUTYPE_LONGNAME, -59, -84, 70, 27, 0, PrinterUtils.BarCode.CODE93, -63, 84, -77, -2, -107, -66, -32, Constans.CAN_REV_UV_SET_RESULT, 27, -95, TarConstants.LF_FIFO, -44, -89, 1, 71, 44, 7, -55, 126, 5, -78, 87, -105, -114, 65, -19, 58, -78, -95, 0, 118, TarConstants.LF_GNUTYPE_SPARSE, TarConstants.LF_GNUTYPE_LONGNAME, -88, 2, -21, Byte.MAX_VALUE, 64, 74, -103, -114, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -70, -81, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 125, SlipConfig.ESC, 21, 113, 20, -102, 46, SlipConfig.ESC, -111, -97, 97, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 32, 87, ClassDefinitionUtils.OPS_areturn, 105, 18, -19, 107, ClassDefinitionUtils.OPS_invokespecial, -50, -97, 11, -23, -59, -107, -107, TarConstants.LF_GNUTYPE_SPARSE, -25, 15, -93, -21, 2, 3, 1, 0, 1, 2, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, Byte.MIN_VALUE, 45, -34, -104, 26, -40, -41, -44, -29, SlipConfig.ESC_ESC, -123, -7, -110, ClassDefinitionUtils.OPS_invokespecial, -106, 80, -5, -118, 24, -38, 66, -54, -93, -54, -104, 43, -62, -48, 122, -14, -41, 85, 18, -53, 109, 22, -113, 44, 77, -116, 7, 10, -43, -61, 43, -40, -61, TarConstants.LF_GNUTYPE_LONGNAME, 19, -11, -89, 47, 80, -72, 113, -86, 70, -23, 27, 113, 37, -1, ClassDefinitionUtils.OPS_aload_0, TarConstants.LF_NORMAL, 84, ClassDefinitionUtils.OPS_areturn, 30, 86, BinaryMemcacheOpcodes.GATKQ, Constans.CAN_REV_DIS_MODE_SWITCH, -22, 79, -44, 87, -40, Ascii.f1926US, -41, -44, -16, -74, 85, 61, -122, -22, 10, -31, 78, 92, -123, -77, 12, ClassDefinitionUtils.OPS_areturn, 62, -52, 68, -46, ByteSourceJsonBootstrapper.UTF8_BOM_1, 67, 124, -78, -23, -105, -77, -2, ClassDefinitionUtils.OPS_dup, -16, -12, -56, -51, 26, 102, 46, 39, -61, -13, ClassDefinitionUtils.OPS_return, ByteSourceJsonBootstrapper.UTF8_BOM_3, -5, 126, 70, 29, Ascii.f1926US, 104, -109, 65, -23, -69, 23, -7, 2, 65, 0, -42, 18, 101, 10, -21, 37, 107, -3, -114, -29, -40, TarConstants.LF_GNUTYPE_LONGNAME, 107, -122, 40, 8, -58, -32, -12, TarConstants.LF_CONTIG, -4, -61, -66, 91, -56, -50, 78, Constans.CAN_REV_DIS_MODE_SWITCH, 11, -49, -62, Constans.CAN_REV_SPRAY_SYS_RESULT, -56, 70, -92, 90, 32, -112, TarConstants.LF_LINK, 26, -99, 113, 44, 26, ClassDefinitionUtils.OPS_aload_0, -99, -40, -123, 17, 93, 114, 125, BinaryMemcacheOpcodes.GATK, -118, -32, 125, SlipConfig.END, 61, 58, -58, -105, -105, -39, 93, 2, 65, 0, -57, SlipConfig.ESC_END, -22, -107, -42, ClassDefinitionUtils.OPS_return, 0, -118, 121, -76, TarConstants.LF_PAX_EXTENDED_HEADER_LC, TarConstants.LF_BLK, 110, Byte.MAX_VALUE, 115, 68, -86, -4, 96, -50, PrinterUtils.BarCode.CODE93, -60, -57, 125, 57, 21, -81, -44, 25, 112, -75, TarConstants.LF_GNUTYPE_SPARSE, 57, -55, 61, 24, 28, -112, -103, -8, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 110, -52, -108, -41, -76, -96, 87, -117, 69, 0, 64, 26, 4, 122, 13, 6, -106, 112, -51, -1, 79, 117, -25, 2, 64, Byte.MAX_VALUE, 68, 60, 81, -5, 110, 41, -1, 122, 93, -74, -113, -24, TarConstants.LF_BLK, ByteSourceJsonBootstrapper.UTF8_BOM_3, -60, PrinterUtils.BarCode.CODE93, 8, 32, -24, -48, 26, -57, 38, -26, 0, -48, -24, -21, -28, -66, 47, -33, 63, TarConstants.LF_NORMAL, 34, 108, -51, -116, -125, -40, ClassDefinitionUtils.OPS_aload_0, 26, 32, 12, PrinterUtils.BarCode.CODE128, -1, 25, 77, TarConstants.LF_CHR, -109, 7, 22, Constans.CAN_REV_DIS_MODE_SWITCH, 79, -26, TarConstants.LF_SYMLINK, -51, -76, 13, ClassDefinitionUtils.OPS_areturn, -66, 19, -7, 2, 65, 0, -90, 99, -20, 68, -4, -84, -11, -105, TarConstants.LF_GNUTYPE_SPARSE, -123, Constans.CAN_REV_DIS_MODE_SWITCH, -63, -103, -16, -81, 101, 78, -72, -72, 91, 100, -57, -74, -111, TarConstants.LF_LINK, 18, TarConstants.LF_FIFO, 4, -19, 125, 32, -24, 125, -26, 100, -33, -117, 0, 115, ByteSourceJsonBootstrapper.UTF8_BOM_3, BinaryMemcacheOpcodes.SASL_AUTH, 124, -107, 3, -95, -91, 118, 12, 12, 29, 80, -3, 12, -20, 7, TarConstants.LF_BLK, -118, -12, 122, TarConstants.LF_GNUTYPE_LONGLINK, 117, -81, -112, -89, 2, 64, 93, -21, -52, -110, -54, -9, 79, -123, 105, 125, -56, TarConstants.LF_GNUTYPE_LONGLINK, -77, -26, 125, -123, -69, 62, -2, 79, 8, PrinterUtils.BarCode.CODE93, -76, -67, 5, BinaryMemcacheOpcodes.SASL_AUTH, Constans.CAN_REV_SPRAY_SYS_RESULT, 1, -42, ByteSourceJsonBootstrapper.UTF8_BOM_1, 29, 69, -20, PSSSigner.TRAILER_IMPLICIT, -26, -23, 95, -7, -70, -50, -10, 58, 16, -15, -89, -24, Constans.CAN_REV_SPRAY_SYS_RESULT, -14, -72, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -89, -63, 66, 7, 77, -89, -54, -95, -90, 45, -44, -118, 69, -1};
    private static final byte[] ENCODED_PUBLIC_KEY = {TarConstants.LF_NORMAL, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -97, TarConstants.LF_NORMAL, 13, 6, 9, ClassDefinitionUtils.OPS_aload_0, -122, PrinterUtils.BarCode.CODE93, -122, -9, 13, 1, 1, 1, 5, 0, 3, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -115, 0, TarConstants.LF_NORMAL, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, Constans.CAN_REV_UV_SET_RESULT, 2, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 0, -89, BinaryMemcacheOpcodes.SASL_AUTH, 8, Constans.CAN_REV_DIS_MODE_SWITCH, 110, -60, ClassDefinitionUtils.OPS_dup, 8, -62, 69, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 95, -59, -43, 13, -18, 123, 29, -31, 13, ClassDefinitionUtils.OPS_areturn, -76, 109, -62, ClassDefinitionUtils.OPS_return, 2, 104, -94, TarConstants.LF_GNUTYPE_LONGNAME, 59, ClassDefinitionUtils.OPS_invokespecial, -26, 99, 123, -57, -92, -100, 116, TarConstants.LF_SYMLINK, -25, 96, TarConstants.LF_DIR, 124, 95, TarConstants.LF_GNUTYPE_LONGNAME, -59, -84, 70, 27, 0, PrinterUtils.BarCode.CODE93, -63, 84, -77, -2, -107, -66, -32, Constans.CAN_REV_UV_SET_RESULT, 27, -95, TarConstants.LF_FIFO, -44, -89, 1, 71, 44, 7, -55, 126, 5, -78, 87, -105, -114, 65, -19, 58, -78, -95, 0, 118, TarConstants.LF_GNUTYPE_SPARSE, TarConstants.LF_GNUTYPE_LONGNAME, -88, 2, -21, Byte.MAX_VALUE, 64, 74, -103, -114, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -70, -81, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 125, SlipConfig.ESC, 21, 113, 20, -102, 46, SlipConfig.ESC, -111, -97, 97, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 32, 87, ClassDefinitionUtils.OPS_areturn, 105, 18, -19, 107, ClassDefinitionUtils.OPS_invokespecial, -50, -97, 11, -23, -59, -107, -107, TarConstants.LF_GNUTYPE_SPARSE, -25, 15, -93, -21, 2, 3, 1, 0, 1};

    public static byte[] newEncodedRsaPrivateKeyBytes() {
        return (byte[]) ENCODED_PRIVATE_KEY.clone();
    }

    public static byte[] newEncodedRsaPublicKeyBytes() {
        return (byte[]) ENCODED_PUBLIC_KEY.clone();
    }

    public static RSAPrivateKey newRsaPrivateKey() throws GeneralSecurityException {
        return (RSAPrivateKey) SecurityUtils.getRsaKeyFactory().generatePrivate(new PKCS8EncodedKeySpec(ENCODED_PRIVATE_KEY));
    }

    public static RSAPublicKey newRsaPublicKey() throws GeneralSecurityException {
        return (RSAPublicKey) SecurityUtils.getRsaKeyFactory().generatePublic(new X509EncodedKeySpec(ENCODED_PUBLIC_KEY));
    }

    private SecurityTestUtils() {
    }
}
