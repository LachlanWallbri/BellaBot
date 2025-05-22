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
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class DSTU7624Engine implements BlockCipher {
    private static final int ROUNDS_128 = 10;
    private static final int ROUNDS_256 = 14;
    private static final int ROUNDS_512 = 18;

    /* renamed from: S0 */
    private static final byte[] f9374S0 = {-88, 67, 95, 6, 107, 117, 108, ClassDefinitionUtils.OPS_dup, 113, -33, Constans.CAN_REV_SPRAY_SYS_RESULT, -107, 23, -16, -40, 9, 109, -13, 29, -53, -55, 77, 44, -81, 121, -32, -105, -3, 111, TarConstants.LF_GNUTYPE_LONGLINK, 69, 57, 62, SlipConfig.ESC_ESC, -93, 79, -76, -74, -102, 14, Ascii.f1926US, ByteSourceJsonBootstrapper.UTF8_BOM_3, 21, -31, PrinterUtils.BarCode.CODE128, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, 25, -43, -83, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -92, -69, -95, SlipConfig.ESC_END, -14, -125, TarConstants.LF_CONTIG, 66, -28, 122, TarConstants.LF_SYMLINK, -100, -52, -85, 74, -113, 110, 4, 39, 46, -25, -30, 90, -106, 22, BinaryMemcacheOpcodes.GATK, 43, -62, 101, 102, 15, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, TarConstants.LF_BLK, PrinterUtils.BarCode.CODE93, -4, ClassDefinitionUtils.OPS_invokespecial, 106, Constans.CAN_REV_UV_SYS_RESULT, -91, TarConstants.LF_GNUTYPE_SPARSE, -122, -7, 91, SlipConfig.ESC, 56, 123, -61, 30, 34, TarConstants.LF_CHR, BinaryMemcacheOpcodes.GATKQ, 40, TarConstants.LF_FIFO, -57, -78, 59, -114, 119, -70, -11, 20, -97, 8, 85, -101, TarConstants.LF_GNUTYPE_LONGNAME, -2, 96, 92, -38, 24, 70, -51, 125, BinaryMemcacheOpcodes.SASL_AUTH, ClassDefinitionUtils.OPS_areturn, 63, 27, Constans.CAN_REV_UV_SET_RESULT, -1, -21, Constans.CAN_REV_DIS_MODE_SWITCH, 105, 58, -99, -41, -45, 112, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 64, -75, -34, 93, TarConstants.LF_NORMAL, -111, ClassDefinitionUtils.OPS_return, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 17, 1, -27, 0, 104, -104, -96, -59, 2, -90, 116, 45, 11, -94, 118, -77, -66, -50, -67, -82, -23, -118, TarConstants.LF_LINK, 28, -20, -15, -103, -108, -86, -10, 38, 47, ByteSourceJsonBootstrapper.UTF8_BOM_1, -24, -116, TarConstants.LF_DIR, 3, -44, Byte.MAX_VALUE, -5, 5, -63, 94, -112, 32, 61, -126, -9, -22, 10, 13, 126, -8, 80, 26, -60, 7, 87, -72, 60, 98, -29, -56, -84, 82, 100, 16, -48, -39, 19, 12, 18, 41, 81, -71, -49, -42, 115, -115, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 84, SlipConfig.END, -19, 78, 68, -89, ClassDefinitionUtils.OPS_aload_0, -123, 37, -26, -54, 124, -117, 86, Byte.MIN_VALUE};

    /* renamed from: S1 */
    private static final byte[] f9375S1 = {-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, -42, -78, -46, -112, 23, -8, 66, 21, 86, -76, 101, 28, Constans.CAN_REV_UV_SYS_RESULT, 67, -59, 92, TarConstants.LF_FIFO, -70, -11, 87, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -115, TarConstants.LF_LINK, -10, 100, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -98, -12, 34, -86, 117, 15, 2, ClassDefinitionUtils.OPS_return, -33, 109, 115, 77, 124, 38, 46, -9, 8, 93, 68, 62, -97, 20, -56, -82, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, 26, 107, 105, -13, -67, TarConstants.LF_CHR, -85, -6, -47, -101, 104, 78, 22, -107, -111, -18, TarConstants.LF_GNUTYPE_LONGNAME, 99, -114, 91, -52, 60, 25, -95, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, PrinterUtils.BarCode.CODE128, 123, -39, 111, TarConstants.LF_CONTIG, 96, -54, -25, 43, PrinterUtils.BarCode.CODE93, -3, -106, 69, -4, 65, 18, 13, 121, -27, Constans.CAN_REV_UV_SET_RESULT, -116, -29, 32, TarConstants.LF_NORMAL, SlipConfig.ESC_END, ClassDefinitionUtils.OPS_invokespecial, 108, 74, -75, 63, -105, -44, 98, 45, 6, -92, -91, -125, 95, ClassDefinitionUtils.OPS_aload_0, -38, -55, 0, 126, -94, 85, ByteSourceJsonBootstrapper.UTF8_BOM_3, 17, -43, -100, -49, 14, 10, 61, 81, 125, -109, 27, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, ClassDefinitionUtils.OPS_areturn, -104, 24, TarConstants.LF_SYMLINK, 113, TarConstants.LF_GNUTYPE_LONGLINK, ByteSourceJsonBootstrapper.UTF8_BOM_1, 59, 112, -96, -28, 64, -1, -61, -87, -26, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -7, -117, 70, Byte.MIN_VALUE, 30, 56, -31, -72, -88, -32, 12, BinaryMemcacheOpcodes.GATK, 118, 29, 37, BinaryMemcacheOpcodes.GATKQ, 5, -15, 110, -108, 40, -102, Constans.CAN_REV_DIS_MODE_SWITCH, -24, -93, 79, 119, -45, -123, -30, 82, -14, -126, 80, 122, 47, 116, TarConstants.LF_GNUTYPE_SPARSE, -77, 97, -81, 57, TarConstants.LF_DIR, -34, -51, Ascii.f1926US, -103, -84, -83, 114, 44, SlipConfig.ESC_ESC, -48, Constans.CAN_REV_SPRAY_SYS_RESULT, -66, 94, -90, -20, 4, -58, 3, TarConstants.LF_BLK, -5, SlipConfig.ESC, ClassDefinitionUtils.OPS_dup, -74, -62, 1, -16, 90, -19, -89, 102, BinaryMemcacheOpcodes.SASL_AUTH, Byte.MAX_VALUE, -118, 39, -57, SlipConfig.END, 41, -41};

    /* renamed from: S2 */
    private static final byte[] f9376S2 = {-109, -39, -102, -75, -104, 34, 69, -4, -70, 106, -33, 2, -97, SlipConfig.ESC_END, 81, ClassDefinitionUtils.OPS_dup, 74, 23, 43, -62, -108, -12, -69, -93, 98, -28, 113, -44, -51, 112, 22, -31, PrinterUtils.BarCode.CODE128, 60, SlipConfig.END, -40, 92, -101, -83, -123, TarConstants.LF_GNUTYPE_SPARSE, -95, 122, -56, 45, -32, -47, 114, -90, 44, -60, -29, 118, TarConstants.LF_PAX_EXTENDED_HEADER_LC, ClassDefinitionUtils.OPS_invokespecial, -76, 9, 59, 14, 65, TarConstants.LF_GNUTYPE_LONGNAME, -34, -78, -112, 37, -91, -41, 3, 17, 0, -61, 46, -110, ByteSourceJsonBootstrapper.UTF8_BOM_1, 78, 18, -99, 125, -53, TarConstants.LF_DIR, 16, -43, 79, -98, 77, -87, 85, -58, -48, 123, 24, -105, -45, TarConstants.LF_FIFO, -26, PrinterUtils.BarCode.CODE93, 86, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -113, 119, -52, -100, -71, -30, -84, -72, 47, 21, -92, 124, -38, 56, 30, 11, 5, -42, 20, 110, 108, 126, 102, -3, ClassDefinitionUtils.OPS_return, -27, 96, -81, 94, TarConstants.LF_CHR, Constans.CAN_REV_SPRAY_SYS_RESULT, -55, -16, 93, 109, 63, Constans.CAN_REV_UV_SYS_RESULT, -115, -57, -9, 29, -23, -20, -19, Byte.MIN_VALUE, 41, 39, -49, -103, -88, 80, 15, TarConstants.LF_CONTIG, BinaryMemcacheOpcodes.GATKQ, 40, TarConstants.LF_NORMAL, -107, -46, 62, 91, 64, -125, -77, 105, 87, Ascii.f1926US, 7, 28, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, -18, TarConstants.LF_LINK, -94, 115, -7, -54, 58, 26, -5, 13, -63, -2, -6, -14, 111, -67, -106, SlipConfig.ESC_ESC, 67, 82, -74, 8, -13, -82, -66, 25, Constans.CAN_REV_UV_SET_RESULT, TarConstants.LF_SYMLINK, 38, ClassDefinitionUtils.OPS_areturn, -22, TarConstants.LF_GNUTYPE_LONGLINK, 100, Constans.CAN_REV_DIS_MODE_SWITCH, -126, 107, -11, 121, ByteSourceJsonBootstrapper.UTF8_BOM_3, 1, 95, 117, 99, 27, BinaryMemcacheOpcodes.GATK, 61, 104, ClassDefinitionUtils.OPS_aload_0, 101, -24, -111, -10, -1, 19, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -15, 71, 10, Byte.MAX_VALUE, -59, -89, -25, 97, 90, 6, 70, 68, 66, 4, -96, SlipConfig.ESC, 57, -122, 84, -86, -116, TarConstants.LF_BLK, BinaryMemcacheOpcodes.SASL_AUTH, -117, -8, 12, 116, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER};

    /* renamed from: S3 */
    private static final byte[] f9377S3 = {104, -115, -54, 77, 115, TarConstants.LF_GNUTYPE_LONGLINK, 78, ClassDefinitionUtils.OPS_aload_0, -44, 82, 38, -77, 84, 30, 25, Ascii.f1926US, 34, 3, 70, 61, 45, 74, TarConstants.LF_GNUTYPE_SPARSE, -125, 19, -118, ClassDefinitionUtils.OPS_invokespecial, -43, 37, 121, -11, -67, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 47, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, 22, 60, 102, 112, 93, -13, 69, 64, -52, -24, -108, 86, 8, -50, 26, 58, -46, -31, -33, -75, 56, 110, 14, -27, -12, -7, -122, -23, 79, -42, -123, BinaryMemcacheOpcodes.GATK, -49, TarConstants.LF_SYMLINK, -103, TarConstants.LF_LINK, 20, -82, -18, -56, PrinterUtils.BarCode.CODE93, -45, TarConstants.LF_NORMAL, -95, -110, 65, ClassDefinitionUtils.OPS_return, 24, -60, 44, 113, 114, 68, 21, -3, TarConstants.LF_CONTIG, -66, 95, -86, -101, Constans.CAN_REV_UV_SYS_RESULT, -40, -85, Constans.CAN_REV_UV_SET_RESULT, -100, -6, 96, -22, PSSSigner.TRAILER_IMPLICIT, 98, 12, BinaryMemcacheOpcodes.GATKQ, -90, -88, -20, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 32, SlipConfig.ESC, 124, 40, SlipConfig.ESC_ESC, -84, 91, TarConstants.LF_BLK, 126, 16, -15, 123, -113, 99, -96, 5, -102, 67, 119, BinaryMemcacheOpcodes.SASL_AUTH, ByteSourceJsonBootstrapper.UTF8_BOM_3, 39, 9, -61, -97, -74, -41, 41, -62, -21, SlipConfig.END, -92, -117, -116, 29, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, PrinterUtils.BarCode.CODE128, TarConstants.LF_CHR, -28, -39, -71, -48, 66, -57, 108, -112, 0, -114, 111, 80, 1, -59, -38, 71, 63, -51, 105, -94, -30, 122, -89, -58, -109, 15, 10, 6, -26, 43, -106, -93, 28, -81, 106, 18, Constans.CAN_REV_DIS_MODE_SWITCH, 57, -25, ClassDefinitionUtils.OPS_areturn, -126, -9, -2, -99, Constans.CAN_REV_SPRAY_SYS_RESULT, 92, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, TarConstants.LF_DIR, -34, -76, -91, -4, Byte.MIN_VALUE, ByteSourceJsonBootstrapper.UTF8_BOM_1, -53, -69, 107, 118, -70, 90, 125, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 11, -107, -29, -83, 116, -104, 59, TarConstants.LF_FIFO, 100, 109, SlipConfig.ESC_END, -16, ClassDefinitionUtils.OPS_dup, -87, TarConstants.LF_GNUTYPE_LONGNAME, 23, Byte.MAX_VALUE, -111, -72, -55, 87, 27, -32, 97};

    /* renamed from: T0 */
    private static final byte[] f9378T0 = {-92, -94, -87, -59, 78, -55, 3, -39, 126, 15, -46, -83, -25, -45, 39, 91, -29, -95, -24, -26, 124, ClassDefinitionUtils.OPS_aload_0, 85, 12, -122, 57, -41, -115, -72, 18, 111, 40, -51, -118, 112, 86, 114, -7, ByteSourceJsonBootstrapper.UTF8_BOM_3, 79, 115, -23, -9, 87, 22, -84, 80, SlipConfig.END, -99, ClassDefinitionUtils.OPS_invokespecial, 71, 113, 96, -60, 116, 67, 108, Ascii.f1926US, -109, 119, SlipConfig.ESC_END, -50, 32, -116, -103, 95, 68, 1, -11, 30, Constans.CAN_REV_SPRAY_SYS_RESULT, 94, 97, 44, TarConstants.LF_GNUTYPE_LONGLINK, 29, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 21, -12, BinaryMemcacheOpcodes.GATK, -42, -22, -31, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -15, Byte.MAX_VALUE, -2, -38, 60, 7, TarConstants.LF_GNUTYPE_SPARSE, 106, Constans.CAN_REV_DIS_MODE_SWITCH, -100, -53, 2, -125, TarConstants.LF_CHR, SlipConfig.ESC_ESC, TarConstants.LF_DIR, -30, ClassDefinitionUtils.OPS_dup, 90, -104, -91, -110, 100, 4, 6, 16, 77, 28, -105, 8, TarConstants.LF_LINK, -18, -85, 5, -81, 121, -96, 24, 70, 109, -4, Constans.CAN_REV_UV_SET_RESULT, -44, -57, -1, -16, -49, 66, -111, -8, 104, 10, 101, -114, -74, -3, -61, ByteSourceJsonBootstrapper.UTF8_BOM_1, TarConstants.LF_PAX_EXTENDED_HEADER_LC, TarConstants.LF_GNUTYPE_LONGNAME, -52, -98, TarConstants.LF_NORMAL, 46, PSSSigner.TRAILER_IMPLICIT, 11, 84, 26, -90, -69, 38, Byte.MIN_VALUE, PrinterUtils.BarCode.CODE93, -108, TarConstants.LF_SYMLINK, 125, -89, 63, -82, 34, 61, 102, -86, -10, 0, 93, -67, 74, -32, 59, -76, 23, -117, -97, 118, ClassDefinitionUtils.OPS_areturn, BinaryMemcacheOpcodes.GATKQ, -102, 37, 99, SlipConfig.ESC, -21, 122, 62, 92, -77, ClassDefinitionUtils.OPS_return, 41, -14, -54, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 110, -40, -88, 47, 117, -33, 20, -5, 19, PrinterUtils.BarCode.CODE128, Constans.CAN_REV_UV_SYS_RESULT, -78, -20, -28, TarConstants.LF_BLK, 45, -106, -58, 58, -19, -107, 14, -27, -123, 107, 64, BinaryMemcacheOpcodes.SASL_AUTH, -101, 9, 25, 43, 82, -34, 69, -93, -6, 81, -62, -75, -47, -112, -71, -13, TarConstants.LF_CONTIG, -63, 13, -70, 65, 17, 56, 123, -66, -48, -43, 105, TarConstants.LF_FIFO, -56, 98, 27, -126, -113};

    /* renamed from: T1 */
    private static final byte[] f9379T1 = {-125, -14, ClassDefinitionUtils.OPS_aload_0, -21, -23, ByteSourceJsonBootstrapper.UTF8_BOM_3, 123, -100, TarConstants.LF_BLK, -106, -115, -104, -71, 105, -116, 41, 61, Constans.CAN_REV_UV_SYS_RESULT, 104, 6, 57, 17, TarConstants.LF_GNUTYPE_LONGNAME, 14, -96, 86, 64, -110, 21, PSSSigner.TRAILER_IMPLICIT, -77, SlipConfig.ESC_END, 111, -8, 38, -70, -66, -67, TarConstants.LF_LINK, -5, -61, -2, Byte.MIN_VALUE, 97, -31, 122, TarConstants.LF_SYMLINK, -46, 112, 32, -95, 69, -20, -39, 26, 93, -76, -40, 9, -91, 85, -114, TarConstants.LF_CONTIG, 118, -87, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 16, 23, TarConstants.LF_FIFO, 101, ClassDefinitionUtils.OPS_return, -107, 98, ClassDefinitionUtils.OPS_dup, 116, -93, 80, 47, TarConstants.LF_GNUTYPE_LONGLINK, -56, -48, -113, -51, -44, 60, -122, 18, 29, BinaryMemcacheOpcodes.GATK, ByteSourceJsonBootstrapper.UTF8_BOM_1, -12, TarConstants.LF_GNUTYPE_SPARSE, 25, TarConstants.LF_DIR, -26, Byte.MAX_VALUE, 94, -42, 121, 81, 34, 20, -9, 30, 74, 66, -101, 65, 115, 45, -63, 92, -90, -94, -32, 46, -45, 40, -69, -55, -82, 106, -47, 90, TarConstants.LF_NORMAL, -112, Constans.CAN_REV_DIS_MODE_SWITCH, -7, -78, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -49, 126, -59, -53, -105, -28, 22, 108, -6, ClassDefinitionUtils.OPS_areturn, 109, Ascii.f1926US, 82, -103, 13, 78, 3, -111, -62, 77, 100, 119, -97, SlipConfig.ESC_ESC, -60, PrinterUtils.BarCode.CODE128, -118, -102, BinaryMemcacheOpcodes.GATKQ, 56, -89, 87, -123, -57, 124, 125, -25, -10, ClassDefinitionUtils.OPS_invokespecial, -84, 39, 70, -34, -33, 59, -41, -98, 43, 11, -43, 19, 117, -16, 114, -74, -99, 27, 1, 63, 68, -27, Constans.CAN_REV_SPRAY_SYS_RESULT, -3, 7, -15, -85, -108, 24, -22, -4, 58, -126, 95, 5, 84, SlipConfig.ESC, 0, -117, -29, PrinterUtils.BarCode.CODE93, 12, -54, TarConstants.LF_PAX_EXTENDED_HEADER_LC, Constans.CAN_REV_UV_SET_RESULT, 10, -1, 62, 91, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -18, 113, -30, -38, 44, -72, -75, -52, 110, -88, 107, -83, 96, -58, 8, 4, 2, -24, -11, 79, -92, -13, SlipConfig.END, -50, 67, 37, 28, BinaryMemcacheOpcodes.SASL_AUTH, TarConstants.LF_CHR, 15, -81, 71, -19, 102, 99, -109, -86};

    /* renamed from: T2 */
    private static final byte[] f9380T2 = {69, -44, 11, 67, -15, 114, -19, -92, -62, 56, -26, 113, -3, -74, 58, -107, 80, 68, TarConstants.LF_GNUTYPE_LONGLINK, -30, 116, 107, 30, 17, 90, -58, -76, -40, -91, -118, 112, -93, -88, -6, 5, -39, -105, 64, -55, -112, -104, -113, SlipConfig.ESC_END, 18, TarConstants.LF_LINK, 44, 71, 106, -103, -82, -56, Byte.MAX_VALUE, -7, 79, 93, -106, 111, -12, -77, 57, BinaryMemcacheOpcodes.SASL_AUTH, -38, -100, -123, -98, 59, -16, ByteSourceJsonBootstrapper.UTF8_BOM_3, ByteSourceJsonBootstrapper.UTF8_BOM_1, 6, -18, -27, 95, 32, 16, -52, 60, 84, 74, 82, -108, 14, SlipConfig.END, 40, -10, 86, 96, -94, -29, 15, -20, -99, BinaryMemcacheOpcodes.GATKQ, -125, 126, -43, 124, -21, 24, -41, -51, SlipConfig.ESC_ESC, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -1, SlipConfig.ESC, -95, 9, -48, 118, Constans.CAN_REV_DIS_MODE_SWITCH, 117, -69, 29, 26, 47, ClassDefinitionUtils.OPS_areturn, -2, -42, TarConstants.LF_BLK, 99, TarConstants.LF_DIR, -46, ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_dup, 109, 77, 119, -25, -114, 97, -49, -97, -50, 39, -11, Byte.MIN_VALUE, -122, -57, -90, -5, -8, Constans.CAN_REV_SPRAY_SYS_RESULT, -85, 98, 63, -33, PrinterUtils.BarCode.CODE93, 0, 20, -102, -67, 91, 4, -110, 2, 37, 101, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_GNUTYPE_SPARSE, 12, -14, 41, -81, 23, 108, 65, TarConstants.LF_NORMAL, -23, -109, 85, -9, -84, 104, 38, -60, 125, -54, 122, 62, -96, TarConstants.LF_CONTIG, 3, -63, TarConstants.LF_FIFO, 105, 102, 8, 22, -89, PSSSigner.TRAILER_IMPLICIT, -59, -45, 34, ClassDefinitionUtils.OPS_invokespecial, 19, 70, TarConstants.LF_SYMLINK, -24, 87, Constans.CAN_REV_UV_SYS_RESULT, 43, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -78, 78, 100, 28, -86, -111, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 46, -101, 92, 27, 81, 115, 66, BinaryMemcacheOpcodes.GATK, 1, 110, -13, 13, -66, 61, 10, 45, Ascii.f1926US, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, TarConstants.LF_CHR, 25, 123, 94, -22, -34, -117, -53, -87, -116, -115, -83, PrinterUtils.BarCode.CODE128, -126, -28, -70, -61, 21, -47, -32, Constans.CAN_REV_UV_SET_RESULT, -4, ClassDefinitionUtils.OPS_return, -71, -75, 7, 121, -72, -31};

    /* renamed from: T3 */
    private static final byte[] f9381T3 = {-78, -74, BinaryMemcacheOpcodes.GATK, 17, -89, Constans.CAN_REV_UV_SYS_RESULT, -59, -90, 57, -113, -60, -24, 115, 34, 67, -61, -126, 39, -51, 24, 81, 98, 45, -9, 92, 14, 59, -3, -54, -101, 13, 15, 121, -116, 16, TarConstants.LF_GNUTYPE_LONGNAME, 116, 28, 10, -114, 124, -108, 7, -57, 94, 20, -95, BinaryMemcacheOpcodes.SASL_AUTH, 87, 80, 78, -87, Byte.MIN_VALUE, -39, ByteSourceJsonBootstrapper.UTF8_BOM_1, 100, 65, -49, 60, -18, 46, 19, 41, -70, TarConstants.LF_BLK, 90, -82, -118, 97, TarConstants.LF_CHR, 18, -71, 85, -88, 21, 5, -10, 3, 6, PrinterUtils.BarCode.CODE128, -75, 37, 9, 22, 12, ClassDefinitionUtils.OPS_aload_0, 56, -4, 32, -12, -27, Byte.MAX_VALUE, -41, TarConstants.LF_LINK, 43, 102, 111, -1, 114, -122, -16, -93, 47, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 0, PSSSigner.TRAILER_IMPLICIT, -52, -30, ClassDefinitionUtils.OPS_areturn, -15, 66, -76, TarConstants.LF_NORMAL, 95, 96, 4, -20, -91, -29, -117, -25, 29, ByteSourceJsonBootstrapper.UTF8_BOM_3, Constans.CAN_REV_DIS_MODE_SWITCH, 123, -26, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -8, -34, -40, -46, 23, -50, TarConstants.LF_GNUTYPE_LONGLINK, 71, -42, 105, 108, 25, -103, -102, 1, -77, -123, ClassDefinitionUtils.OPS_return, -7, ClassDefinitionUtils.OPS_dup, -62, TarConstants.LF_CONTIG, -23, -56, -96, -19, 79, Constans.CAN_REV_UV_SET_RESULT, 104, 109, -43, 38, -111, Constans.CAN_REV_SPRAY_SYS_RESULT, TarConstants.LF_PAX_EXTENDED_HEADER_UC, -67, -55, -104, SlipConfig.ESC_END, 117, SlipConfig.END, 118, -11, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 107, 126, -21, 82, -53, -47, 91, -97, 11, SlipConfig.ESC, 64, -110, 26, -6, -84, -28, -31, 113, Ascii.f1926US, 101, -115, -105, -98, -107, -112, 93, ClassDefinitionUtils.OPS_invokespecial, -63, -81, 84, -5, 2, -32, TarConstants.LF_DIR, -69, 58, 77, -83, 44, 61, 86, 8, 27, 74, -109, 106, -85, -72, 122, -14, 125, -38, 63, -2, 62, -66, -22, -86, 68, -58, -48, TarConstants.LF_FIFO, PrinterUtils.BarCode.CODE93, 112, -106, 119, BinaryMemcacheOpcodes.GATKQ, TarConstants.LF_GNUTYPE_SPARSE, -33, -13, -125, 40, TarConstants.LF_SYMLINK, 69, 30, -92, -45, -94, 70, 110, -100, SlipConfig.ESC_ESC, 99, -44, -99};
    private boolean forEncryption;
    private long[] internalState;
    private long[][] roundKeys;
    private int roundsAmount;
    private int wordsInBlock;
    private int wordsInKey;
    private long[] workingKey;

    public DSTU7624Engine(int i) throws IllegalArgumentException {
        if (i != 128 && i != 256 && i != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        this.wordsInBlock = i >>> 6;
        this.internalState = new long[this.wordsInBlock];
    }

    private void addRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] + jArr[i2];
        }
    }

    private void decryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        long[][] jArr = this.roundKeys;
        int i3 = this.roundsAmount;
        long[] jArr2 = jArr[i3];
        long j = littleEndianToLong - jArr2[0];
        long j2 = littleEndianToLong2 - jArr2[1];
        while (true) {
            long mixColumnInv = mixColumnInv(j);
            long mixColumnInv2 = mixColumnInv(j2);
            int i4 = (int) mixColumnInv;
            int i5 = (int) (mixColumnInv >>> 32);
            int i6 = (int) mixColumnInv2;
            int i7 = (int) (mixColumnInv2 >>> 32);
            byte[] bArr3 = f9378T0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = f9379T1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = f9380T2;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = f9381T3;
            int i8 = (bArr6[i4 >>> 24] << 24) | ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8);
            byte b4 = bArr3[i7 & 255];
            byte b5 = bArr4[(i7 >>> 8) & 255];
            byte b6 = bArr5[(i7 >>> 16) & 255];
            long j3 = (((bArr6[i7 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (i8 & 4294967295L);
            int i9 = (bArr6[i6 >>> 24] << 24) | (bArr3[i6 & 255] & 255) | ((bArr4[(i6 >>> 8) & 255] & 255) << 8) | ((bArr5[(i6 >>> 16) & 255] & 255) << 16);
            byte b7 = bArr3[i5 & 255];
            byte b8 = bArr4[(i5 >>> 8) & 255];
            byte b9 = bArr5[(i5 >>> 16) & 255];
            long j4 = (i9 & 4294967295L) | (((bArr6[i5 >>> 24] << 24) | (((b7 & 255) | ((b8 & 255) << 8)) | ((b9 & 255) << 16))) << 32);
            i3--;
            if (i3 == 0) {
                long[] jArr3 = this.roundKeys[0];
                long j5 = j3 - jArr3[0];
                long j6 = j4 - jArr3[1];
                Pack.longToLittleEndian(j5, bArr2, i2);
                Pack.longToLittleEndian(j6, bArr2, i2 + 8);
                return;
            }
            long[] jArr4 = this.roundKeys[i3];
            long j7 = j3 ^ jArr4[0];
            j2 = j4 ^ jArr4[1];
            j = j7;
        }
    }

    private void encryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long littleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long littleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        long[] jArr = this.roundKeys[0];
        long j = littleEndianToLong + jArr[0];
        long j2 = littleEndianToLong2 + jArr[1];
        int i3 = 0;
        while (true) {
            int i4 = (int) j;
            int i5 = (int) (j >>> 32);
            int i6 = (int) j2;
            int i7 = (int) (j2 >>> 32);
            byte[] bArr3 = f9374S0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = f9375S1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = f9376S2;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = f9377S3;
            int i8 = ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8) | (bArr6[i4 >>> 24] << 24);
            byte b4 = bArr3[i7 & 255];
            byte b5 = bArr4[(i7 >>> 8) & 255];
            byte b6 = bArr5[(i7 >>> 16) & 255];
            long j3 = (((bArr6[i7 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32) | (i8 & 4294967295L);
            int i9 = (bArr6[i6 >>> 24] << 24) | (bArr3[i6 & 255] & 255) | ((bArr4[(i6 >>> 8) & 255] & 255) << 8) | ((bArr5[(i6 >>> 16) & 255] & 255) << 16);
            byte b7 = bArr3[i5 & 255];
            byte b8 = bArr4[(i5 >>> 8) & 255];
            byte b9 = bArr5[(i5 >>> 16) & 255];
            int i10 = bArr6[i5 >>> 24] << 24;
            long mixColumn = mixColumn(j3);
            long mixColumn2 = mixColumn((i9 & 4294967295L) | ((i10 | (((b7 & 255) | ((b8 & 255) << 8)) | ((b9 & 255) << 16))) << 32));
            i3++;
            int i11 = this.roundsAmount;
            if (i3 == i11) {
                long[] jArr2 = this.roundKeys[i11];
                long j4 = mixColumn + jArr2[0];
                long j5 = mixColumn2 + jArr2[1];
                Pack.longToLittleEndian(j4, bArr2, i2);
                Pack.longToLittleEndian(j5, bArr2, i2 + 8);
                return;
            }
            long[] jArr3 = this.roundKeys[i3];
            long j6 = mixColumn ^ jArr3[0];
            j2 = mixColumn2 ^ jArr3[1];
            j = j6;
        }
    }

    private void invShiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j5) & (-281470681808896L);
            long j9 = j4 ^ j8;
            long j10 = j5 ^ j8;
            long j11 = (j6 ^ j7) & (-281470681808896L);
            long j12 = j6 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-4294967296L);
            long j15 = j9 ^ j14;
            long j16 = (j10 ^ j13) & 281474976645120L;
            jArr2[0] = j15;
            jArr2[1] = j10 ^ j16;
            jArr2[2] = j12 ^ j14;
            jArr2[3] = j16 ^ j13;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j17 = jArr3[0];
        long j18 = jArr3[1];
        long j19 = jArr3[2];
        long j20 = jArr3[3];
        long j21 = jArr3[4];
        long j22 = jArr3[5];
        long j23 = jArr3[6];
        long j24 = jArr3[7];
        long j25 = (j17 ^ j18) & (-71777214294589696L);
        long j26 = j17 ^ j25;
        long j27 = j18 ^ j25;
        long j28 = (j19 ^ j20) & (-71777214294589696L);
        long j29 = j19 ^ j28;
        long j30 = j20 ^ j28;
        long j31 = (j21 ^ j22) & (-71777214294589696L);
        long j32 = j21 ^ j31;
        long j33 = j22 ^ j31;
        long j34 = (j23 ^ j24) & (-71777214294589696L);
        long j35 = j23 ^ j34;
        long j36 = j24 ^ j34;
        long j37 = (j26 ^ j29) & (-281470681808896L);
        long j38 = j26 ^ j37;
        long j39 = j29 ^ j37;
        long j40 = (j27 ^ j30) & 72056494543077120L;
        long j41 = j27 ^ j40;
        long j42 = j30 ^ j40;
        long j43 = (j32 ^ j35) & (-281470681808896L);
        long j44 = j32 ^ j43;
        long j45 = j35 ^ j43;
        long j46 = (j33 ^ j36) & 72056494543077120L;
        long j47 = j33 ^ j46;
        long j48 = j36 ^ j46;
        long j49 = (j38 ^ j44) & (-4294967296L);
        long j50 = j38 ^ j49;
        long j51 = j44 ^ j49;
        long j52 = (j41 ^ j47) & 72057594021150720L;
        long j53 = j41 ^ j52;
        long j54 = (j39 ^ j45) & 281474976645120L;
        long j55 = j39 ^ j54;
        long j56 = j54 ^ j45;
        long j57 = (j42 ^ j48) & 1099511627520L;
        jArr3[0] = j50;
        jArr3[1] = j53;
        jArr3[2] = j55;
        jArr3[3] = j42 ^ j57;
        jArr3[4] = j51;
        jArr3[5] = j47 ^ j52;
        jArr3[6] = j56;
        jArr3[7] = j48 ^ j57;
    }

    private void invSubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = f9378T0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = f9379T1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = f9380T2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            int i4 = (f9381T3[i2 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & 4294967295L) | (((r11[i3 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32);
        }
    }

    private static long mixColumn(long j) {
        long mulX = mulX(j);
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(16, rotate)) ^ rotate(48, j);
        return ((rotate(32, mulX2((j ^ rotate2) ^ mulX)) ^ rotate2) ^ rotate(40, mulX)) ^ rotate(48, mulX);
    }

    private static long mixColumnInv(long j) {
        long rotate = rotate(8, j) ^ j;
        long rotate2 = (rotate ^ rotate(32, rotate)) ^ rotate(48, j);
        long j2 = rotate2 ^ j;
        long rotate3 = rotate(48, j);
        long rotate4 = rotate(56, j);
        long mulX = mulX(j2 ^ rotate4) ^ rotate(56, j2);
        long mulX2 = mulX(rotate(40, mulX(mulX) ^ j) ^ (rotate(16, j2) ^ j)) ^ (j2 ^ rotate3);
        return mulX(rotate(40, ((j ^ rotate(32, j2)) ^ rotate4) ^ mulX(((rotate3 ^ (rotate(24, j) ^ j2)) ^ rotate4) ^ mulX(mulX(mulX2) ^ rotate(16, rotate2))))) ^ rotate2;
    }

    private void mixColumns() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumn(jArr[i]);
        }
    }

    private void mixColumnsInv() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumnInv(jArr[i]);
        }
    }

    private static long mulX(long j) {
        return (((j & (-9187201950435737472L)) >>> 7) * 29) ^ ((9187201950435737471L & j) << 1);
    }

    private static long mulX2(long j) {
        return (((j & 4629771061636907072L) >>> 6) * 29) ^ (((4557430888798830399L & j) << 2) ^ ((((-9187201950435737472L) & j) >>> 6) * 29));
    }

    private static long rotate(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    private void rotateLeft(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        if (i == 2) {
            long j = jArr[0];
            long j2 = jArr[1];
            jArr2[0] = (j >>> 56) | (j2 << 8);
            jArr2[1] = (j << 8) | (j2 >>> 56);
            return;
        }
        if (i == 4) {
            long j3 = jArr[0];
            long j4 = jArr[1];
            long j5 = jArr[2];
            long j6 = jArr[3];
            jArr2[0] = (j4 >>> 24) | (j5 << 40);
            jArr2[1] = (j5 >>> 24) | (j6 << 40);
            jArr2[2] = (j6 >>> 24) | (j3 << 40);
            jArr2[3] = (j3 >>> 24) | (j4 << 40);
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long j7 = jArr[0];
        long j8 = jArr[1];
        long j9 = jArr[2];
        long j10 = jArr[3];
        long j11 = jArr[4];
        long j12 = jArr[5];
        long j13 = jArr[6];
        long j14 = jArr[7];
        jArr2[0] = (j9 >>> 24) | (j10 << 40);
        jArr2[1] = (j10 >>> 24) | (j11 << 40);
        jArr2[2] = (j11 >>> 24) | (j12 << 40);
        jArr2[3] = (j12 >>> 24) | (j13 << 40);
        jArr2[4] = (j13 >>> 24) | (j14 << 40);
        jArr2[5] = (j14 >>> 24) | (j7 << 40);
        jArr2[6] = (j7 >>> 24) | (j8 << 40);
        jArr2[7] = (j8 >>> 24) | (j9 << 40);
    }

    private void shiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j6) & (-4294967296L);
            long j9 = j4 ^ j8;
            long j10 = j6 ^ j8;
            long j11 = (j5 ^ j7) & 281474976645120L;
            long j12 = j5 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-281470681808896L);
            long j15 = (j10 ^ j13) & (-281470681808896L);
            jArr2[0] = j9 ^ j14;
            jArr2[1] = j12 ^ j14;
            jArr2[2] = j10 ^ j15;
            jArr2[3] = j13 ^ j15;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j16 = jArr3[0];
        long j17 = jArr3[1];
        long j18 = jArr3[2];
        long j19 = jArr3[3];
        long j20 = jArr3[4];
        long j21 = jArr3[5];
        long j22 = jArr3[6];
        long j23 = jArr3[7];
        long j24 = (j16 ^ j20) & (-4294967296L);
        long j25 = j16 ^ j24;
        long j26 = j20 ^ j24;
        long j27 = (j17 ^ j21) & 72057594021150720L;
        long j28 = j17 ^ j27;
        long j29 = j21 ^ j27;
        long j30 = (j18 ^ j22) & 281474976645120L;
        long j31 = j18 ^ j30;
        long j32 = j22 ^ j30;
        long j33 = (j19 ^ j23) & 1099511627520L;
        long j34 = j19 ^ j33;
        long j35 = j23 ^ j33;
        long j36 = (j25 ^ j31) & (-281470681808896L);
        long j37 = j25 ^ j36;
        long j38 = j31 ^ j36;
        long j39 = (j28 ^ j34) & 72056494543077120L;
        long j40 = j28 ^ j39;
        long j41 = j34 ^ j39;
        long j42 = (j26 ^ j32) & (-281470681808896L);
        long j43 = j26 ^ j42;
        long j44 = j32 ^ j42;
        long j45 = (j29 ^ j35) & 72056494543077120L;
        long j46 = j29 ^ j45;
        long j47 = j35 ^ j45;
        long j48 = (j37 ^ j40) & (-71777214294589696L);
        long j49 = j37 ^ j48;
        long j50 = j40 ^ j48;
        long j51 = (j38 ^ j41) & (-71777214294589696L);
        long j52 = j38 ^ j51;
        long j53 = j41 ^ j51;
        long j54 = (j43 ^ j46) & (-71777214294589696L);
        long j55 = j43 ^ j54;
        long j56 = j46 ^ j54;
        long j57 = (j44 ^ j47) & (-71777214294589696L);
        jArr3[0] = j49;
        jArr3[1] = j50;
        jArr3[2] = j52;
        jArr3[3] = j53;
        jArr3[4] = j55;
        jArr3[5] = j56;
        jArr3[6] = j44 ^ j57;
        jArr3[7] = j47 ^ j57;
    }

    private void subBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = f9374S0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = f9375S1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = f9376S2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            int i4 = (f9377S3[i2 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
            byte b4 = bArr[i3 & 255];
            byte b5 = bArr2[(i3 >>> 8) & 255];
            byte b6 = bArr3[(i3 >>> 16) & 255];
            jArr[i] = (i4 & 4294967295L) | (((r11[i3 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16))) << 32);
        }
    }

    private void subRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] - jArr[i2];
        }
    }

    private void workingKeyExpandEven(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        int i3 = this.wordsInKey;
        long[] jArr3 = new long[i3];
        long[] jArr4 = new long[this.wordsInBlock];
        System.arraycopy(jArr, 0, jArr3, 0, i3);
        long j = 281479271743489L;
        int i4 = 0;
        while (true) {
            for (int i5 = 0; i5 < this.wordsInBlock; i5++) {
                jArr4[i5] = jArr2[i5] + j;
            }
            for (int i6 = 0; i6 < this.wordsInBlock; i6++) {
                this.internalState[i6] = jArr3[i6] + jArr4[i6];
            }
            subBytes();
            shiftRows();
            mixColumns();
            for (int i7 = 0; i7 < this.wordsInBlock; i7++) {
                long[] jArr5 = this.internalState;
                jArr5[i7] = jArr5[i7] ^ jArr4[i7];
            }
            subBytes();
            shiftRows();
            mixColumns();
            int i8 = 0;
            while (true) {
                i = this.wordsInBlock;
                if (i8 >= i) {
                    break;
                }
                long[] jArr6 = this.internalState;
                jArr6[i8] = jArr6[i8] + jArr4[i8];
                i8++;
            }
            System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i);
            if (this.roundsAmount == i4) {
                return;
            }
            if (this.wordsInBlock != this.wordsInKey) {
                i4 += 2;
                j <<= 1;
                for (int i9 = 0; i9 < this.wordsInBlock; i9++) {
                    jArr4[i9] = jArr2[i9] + j;
                }
                int i10 = 0;
                while (true) {
                    int i11 = this.wordsInBlock;
                    if (i10 >= i11) {
                        break;
                    }
                    this.internalState[i10] = jArr3[i11 + i10] + jArr4[i10];
                    i10++;
                }
                subBytes();
                shiftRows();
                mixColumns();
                for (int i12 = 0; i12 < this.wordsInBlock; i12++) {
                    long[] jArr7 = this.internalState;
                    jArr7[i12] = jArr7[i12] ^ jArr4[i12];
                }
                subBytes();
                shiftRows();
                mixColumns();
                int i13 = 0;
                while (true) {
                    i2 = this.wordsInBlock;
                    if (i13 >= i2) {
                        break;
                    }
                    long[] jArr8 = this.internalState;
                    jArr8[i13] = jArr8[i13] + jArr4[i13];
                    i13++;
                }
                System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i2);
                if (this.roundsAmount == i4) {
                    return;
                }
            }
            i4 += 2;
            j <<= 1;
            long j2 = jArr3[0];
            for (int i14 = 1; i14 < jArr3.length; i14++) {
                jArr3[i14 - 1] = jArr3[i14];
            }
            jArr3[jArr3.length - 1] = j2;
        }
    }

    private void workingKeyExpandKT(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        long[] jArr3 = new long[i];
        long[] jArr4 = new long[i];
        this.internalState = new long[i];
        long[] jArr5 = this.internalState;
        long j = jArr5[0];
        int i2 = this.wordsInKey;
        jArr5[0] = j + i + i2 + 1;
        if (i == i2) {
            System.arraycopy(jArr, 0, jArr3, 0, jArr3.length);
            System.arraycopy(jArr, 0, jArr4, 0, jArr4.length);
        } else {
            System.arraycopy(jArr, 0, jArr3, 0, i);
            int i3 = this.wordsInBlock;
            System.arraycopy(jArr, i3, jArr4, 0, i3);
        }
        int i4 = 0;
        while (true) {
            long[] jArr6 = this.internalState;
            if (i4 >= jArr6.length) {
                break;
            }
            jArr6[i4] = jArr6[i4] + jArr3[i4];
            i4++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i5 = 0;
        while (true) {
            long[] jArr7 = this.internalState;
            if (i5 >= jArr7.length) {
                break;
            }
            jArr7[i5] = jArr7[i5] ^ jArr4[i5];
            i5++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i6 = 0;
        while (true) {
            long[] jArr8 = this.internalState;
            if (i6 >= jArr8.length) {
                subBytes();
                shiftRows();
                mixColumns();
                System.arraycopy(this.internalState, 0, jArr2, 0, this.wordsInBlock);
                return;
            }
            jArr8[i6] = jArr8[i6] + jArr3[i6];
            i6++;
        }
    }

    private void workingKeyExpandOdd() {
        for (int i = 1; i < this.roundsAmount; i += 2) {
            long[][] jArr = this.roundKeys;
            rotateLeft(jArr[i - 1], jArr[i]);
        }
    }

    private void xorRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] ^ jArr[i2];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.wordsInBlock << 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005b A[LOOP:0: B:21:0x0056->B:23:0x005b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064 A[EDGE_INSN: B:24:0x0064->B:25:0x0064 BREAK  A[LOOP:0: B:21:0x0056->B:23:0x005b], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0086  */
    @Override // org.bouncycastle.crypto.BlockCipher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        int i;
        int i2;
        long[][] jArr;
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to DSTU7624Engine init");
        }
        this.forEncryption = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        int length = key.length << 3;
        int i3 = this.wordsInBlock << 6;
        if (length != 128 && length != 256 && length != 512) {
            throw new IllegalArgumentException("unsupported key length: only 128/256/512 are allowed");
        }
        if (length != i3 && length != i3 * 2) {
            throw new IllegalArgumentException("Unsupported key length");
        }
        if (length == 128) {
            i = 10;
        } else {
            if (length != 256) {
                if (length == 512) {
                    i = 18;
                }
                this.wordsInKey = length >>> 6;
                this.roundKeys = new long[this.roundsAmount + 1];
                i2 = 0;
                while (true) {
                    jArr = this.roundKeys;
                    if (i2 < jArr.length) {
                        break;
                    }
                    jArr[i2] = new long[this.wordsInBlock];
                    i2++;
                }
                this.workingKey = new long[this.wordsInKey];
                if (key.length == (length >>> 3)) {
                    throw new IllegalArgumentException("Invalid key parameter passed to DSTU7624Engine init");
                }
                Pack.littleEndianToLong(key, 0, this.workingKey);
                long[] jArr2 = new long[this.wordsInBlock];
                workingKeyExpandKT(this.workingKey, jArr2);
                workingKeyExpandEven(this.workingKey, jArr2);
                workingKeyExpandOdd();
                return;
            }
            i = 14;
        }
        this.roundsAmount = i;
        this.wordsInKey = length >>> 6;
        this.roundKeys = new long[this.roundsAmount + 1];
        i2 = 0;
        while (true) {
            jArr = this.roundKeys;
            if (i2 < jArr.length) {
            }
            jArr[i2] = new long[this.wordsInBlock];
            i2++;
        }
        this.workingKey = new long[this.wordsInKey];
        if (key.length == (length >>> 3)) {
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        if (this.workingKey == null) {
            throw new IllegalStateException("DSTU7624Engine not initialised");
        }
        if (getBlockSize() + i > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (getBlockSize() + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i4 = 0;
        if (this.forEncryption) {
            if (this.wordsInBlock != 2) {
                Pack.littleEndianToLong(bArr, i, this.internalState);
                addRoundKey(0);
                while (true) {
                    subBytes();
                    shiftRows();
                    mixColumns();
                    i4++;
                    i3 = this.roundsAmount;
                    if (i4 == i3) {
                        break;
                    }
                    xorRoundKey(i4);
                }
                addRoundKey(i3);
                Pack.longToLittleEndian(this.internalState, bArr2, i2);
            } else {
                encryptBlock_128(bArr, i, bArr2, i2);
            }
        } else if (this.wordsInBlock != 2) {
            Pack.littleEndianToLong(bArr, i, this.internalState);
            subRoundKey(this.roundsAmount);
            int i5 = this.roundsAmount;
            while (true) {
                mixColumnsInv();
                invShiftRows();
                invSubBytes();
                i5--;
                if (i5 == 0) {
                    break;
                }
                xorRoundKey(i5);
            }
            subRoundKey(0);
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        } else {
            decryptBlock_128(bArr, i, bArr2, i2);
        }
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.internalState, 0L);
    }
}
