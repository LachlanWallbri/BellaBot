package com.iflytek.aiui.pro;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.pudu.library.loracall.SlipConfig;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import io.netty.handler.codec.memcache.binary.DefaultBinaryMemcacheResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.json.JSONObject;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.n */
/* loaded from: classes.dex */
public class C3628n {
    /* renamed from: a */
    public static int m1406a(String str, String str2) {
        try {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            int i = 0;
            for (int i2 = 0; i2 < min; i2++) {
                i = split[i2].length() - split2[i2].length();
                if (i != 0 || (i = split[i2].compareTo(split2[i2])) != 0) {
                    break;
                }
            }
            return i != 0 ? i : split.length - split2.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public static String m1407a(Throwable th) {
        StringWriter stringWriter;
        PrintWriter printWriter;
        String str = null;
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
            } catch (Throwable th2) {
                th = th2;
                printWriter = null;
            }
            try {
                th.printStackTrace(printWriter);
                while (true) {
                    th = th.getCause();
                    if (th == null) {
                        break;
                    }
                    th.printStackTrace(printWriter);
                }
                str = stringWriter.toString();
                try {
                    stringWriter.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                try {
                    printWriter.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                    return str;
                }
            } catch (Throwable th5) {
                th = th5;
                try {
                    th.printStackTrace();
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable th6) {
                            th6.printStackTrace();
                        }
                    }
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                            return null;
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    return str;
                } finally {
                }
            }
        } catch (Throwable th8) {
            th = th8;
            stringWriter = null;
            printWriter = null;
        }
        return str;
    }

    /* renamed from: a */
    public static String m1408a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (z) {
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(entry.getValue());
                    z = false;
                } else {
                    stringBuffer.append("&");
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(entry.getValue());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static Proxy m1409a(Context context, URI uri) {
        Proxy proxy;
        if (C3618i.m1365i(context) == 0) {
            try {
                List<Proxy> select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty() || (proxy = select.get(0)) == null) {
                    return null;
                }
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                return proxy;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static PublicKey m1410a(Context context) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(new byte[]{TarConstants.LF_NORMAL, -126, 2, -98, TarConstants.LF_NORMAL, -126, 2, 7, -96, 3, 2, 1, 2, 2, 9, 0, -99, 15, 119, 58, 44, -19, -105, -40, TarConstants.LF_NORMAL, 13, 6, 9, ClassDefinitionUtils.OPS_aload_0, -122, PrinterUtils.BarCode.CODE93, -122, -9, 13, 1, 1, 5, 5, 0, TarConstants.LF_NORMAL, 104, TarConstants.LF_LINK, 11, TarConstants.LF_NORMAL, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, TarConstants.LF_LINK, 19, TarConstants.LF_NORMAL, 17, 6, 3, 85, 4, 8, 12, 10, TarConstants.LF_GNUTYPE_SPARSE, 111, 109, 101, 45, TarConstants.LF_GNUTYPE_SPARSE, 116, 97, 116, 101, TarConstants.LF_LINK, 16, TarConstants.LF_NORMAL, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, TarConstants.LF_LINK, 17, TarConstants.LF_NORMAL, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, TarConstants.LF_LINK, Ascii.f1926US, TarConstants.LF_NORMAL, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, TarConstants.LF_NORMAL, 30, 23, 13, TarConstants.LF_LINK, TarConstants.LF_CHR, TarConstants.LF_NORMAL, 56, TarConstants.LF_LINK, TarConstants.LF_DIR, TarConstants.LF_NORMAL, TarConstants.LF_CONTIG, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_DIR, TarConstants.LF_DIR, 90, 23, 13, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_NORMAL, 56, TarConstants.LF_LINK, TarConstants.LF_CHR, TarConstants.LF_NORMAL, TarConstants.LF_CONTIG, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_DIR, TarConstants.LF_DIR, 90, TarConstants.LF_NORMAL, 104, TarConstants.LF_LINK, 11, TarConstants.LF_NORMAL, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, TarConstants.LF_LINK, 19, TarConstants.LF_NORMAL, 17, 6, 3, 85, 4, 8, 12, 10, TarConstants.LF_GNUTYPE_SPARSE, 111, 109, 101, 45, TarConstants.LF_GNUTYPE_SPARSE, 116, 97, 116, 101, TarConstants.LF_LINK, 16, TarConstants.LF_NORMAL, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, TarConstants.LF_LINK, 17, TarConstants.LF_NORMAL, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, TarConstants.LF_LINK, Ascii.f1926US, TarConstants.LF_NORMAL, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, TarConstants.LF_NORMAL, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -97, TarConstants.LF_NORMAL, 13, 6, 9, ClassDefinitionUtils.OPS_aload_0, -122, PrinterUtils.BarCode.CODE93, -122, -9, 13, 1, 1, 1, 5, 0, 3, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, -115, 0, TarConstants.LF_NORMAL, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, Constans.CAN_REV_UV_SET_RESULT, 2, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 0, -15, -27, Byte.MIN_VALUE, -56, 118, -59, 62, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 79, 125, SlipConfig.ESC_END, 121, 0, 63, -125, -30, 118, 5, -85, Constans.CAN_REV_SPRAY_SYS_RESULT, 91, 39, 90, 123, PrinterUtils.BarCode.CODE93, -126, -83, -41, -45, -77, -42, Constans.CAN_REV_UV_SYS_RESULT, -81, 23, -2, Constans.CAN_REV_SPRAY_SYS_RESULT, -29, 123, -7, 22, -114, -20, -25, 74, 67, -43, 65, 124, -7, 11, -72, 38, -123, 16, -58, 80, 32, 58, -33, 14, 11, BinaryMemcacheOpcodes.GATKQ, 60, 13, Constans.CAN_REV_SPRAY_SYS_RESULT, 100, 105, -32, 123, -31, 114, -101, -41, 12, 100, BinaryMemcacheOpcodes.SASL_AUTH, Constans.CAN_REV_UV_SYS_RESULT, 63, 126, -123, TarConstants.LF_NORMAL, TarConstants.LF_CONTIG, 80, -116, 28, -10, 125, 59, -41, -95, -126, 118, -70, 43, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 9, 93, -100, 81, -19, -114, -41, 85, -103, SlipConfig.ESC, -116, 118, PrinterUtils.BarCode.CODE93, 86, 125, -43, -92, -11, 63, 69, -38, -10, ByteSourceJsonBootstrapper.UTF8_BOM_3, 126, -53, -115, 60, 62, -86, ClassDefinitionUtils.OPS_areturn, 1, 39, 19, 2, 3, 1, 0, 1, -93, 80, TarConstants.LF_NORMAL, 78, TarConstants.LF_NORMAL, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -29, 63, TarConstants.LF_NORMAL, ClassDefinitionUtils.OPS_return, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, TarConstants.LF_NORMAL, Ascii.f1926US, 6, 3, 85, 29, BinaryMemcacheOpcodes.GATK, 4, 24, TarConstants.LF_NORMAL, 22, Byte.MIN_VALUE, 20, -29, 63, TarConstants.LF_NORMAL, ClassDefinitionUtils.OPS_return, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, TarConstants.LF_NORMAL, 12, 6, 3, 85, 29, 19, 4, 5, TarConstants.LF_NORMAL, 3, 1, 1, -1, TarConstants.LF_NORMAL, 13, 6, 9, ClassDefinitionUtils.OPS_aload_0, -122, PrinterUtils.BarCode.CODE93, -122, -9, 13, 1, 1, 5, 5, 0, 3, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, DefaultBinaryMemcacheResponse.RESPONSE_MAGIC_BYTE, 0, -32, -74, TarConstants.LF_CONTIG, -125, -58, Byte.MIN_VALUE, 15, -62, 100, -60, 3, -86, 81, 112, -61, -56, -69, -126, 8, 99, -100, -38, -108, -56, -122, 125, 19, SlipConfig.END, -61, 90, 85, -47, -8, -123, -103, 105, 77, -32, ByteSourceJsonBootstrapper.UTF8_BOM_3, -62, -28, 67, -28, -78, 116, -49, TarConstants.LF_PAX_EXTENDED_HEADER_LC, -2, BinaryMemcacheOpcodes.SASL_AUTH, 13, 47, 46, -5, -112, 3, -101, -125, -115, 92, Constans.CAN_REV_DIS_MODE_SWITCH, 58, 80, 107, -67, 82, 6, -63, 39, -90, -1, 85, -58, 82, -115, 119, 13, -4, -32, 0, -98, 100, -41, 94, -75, TarConstants.LF_GNUTYPE_LONGLINK, -103, 126, ClassDefinitionUtils.OPS_areturn, 85, 40, -27, 60, 105, 28, -27, -21, -15, -98, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, -88, -109, BinaryMemcacheOpcodes.GATK, Constans.CAN_REV_UV_SET_RESULT, -27, -26, -122, 113, 63, BinaryMemcacheOpcodes.GATK, -33, 70, 23, BinaryMemcacheOpcodes.SASL_AUTH, -23, 66, 108, 56, 112, 46, -85, -123, -123, BinaryMemcacheOpcodes.SASL_AUTH, 118, 27, 96, -7, -103});
                try {
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    Certificate generateCertificate = certificateFactory.generateCertificate(byteArrayInputStream);
                    if (generateCertificate != null && keyFactory != null) {
                        PublicKey generatePublic = keyFactory.generatePublic(new X509EncodedKeySpec(generateCertificate.getPublicKey().getEncoded()));
                        try {
                            byteArrayInputStream.close();
                            return generatePublic;
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                            return generatePublic;
                        }
                    }
                    byteArrayInputStream.close();
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        th.printStackTrace();
                        if (byteArrayInputStream == null) {
                            return null;
                        }
                        byteArrayInputStream.close();
                        return null;
                    } finally {
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                byteArrayInputStream = null;
            }
        } catch (Throwable th5) {
            th5.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m1411a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        boolean z = true;
        try {
            byteArrayOutputStream.write(new byte[]{b});
            boolean z2 = b > 0;
            int i = b & 255;
            if (i >= 255) {
                z = false;
            }
            if (z && z2) {
                byteArrayOutputStream.write(bArr);
            } else if (i == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static boolean m1412a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    /* renamed from: a */
    public static boolean m1413a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    /* renamed from: a */
    public static byte[] m1414a() {
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            String[] split2 = new StringBuffer(new String(C3624l.m1401d(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split2.length];
            for (int i2 = 0; i2 < split2.length; i2++) {
                bArr2[i2] = Byte.parseByte(split2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[16];
        }
    }

    /* renamed from: a */
    public static byte[] m1415a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    /* renamed from: a */
    public static byte[] m1416a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        if (bArr != null && bArr.length != 0) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                } catch (Throwable th) {
                    th = th;
                    zipOutputStream = null;
                }
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
                zipOutputStream = null;
            }
            try {
                zipOutputStream.putNextEntry(new ZipEntry("log"));
                zipOutputStream.write(bArr);
                zipOutputStream.closeEntry();
                zipOutputStream.finish();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    zipOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                try {
                    byteArrayOutputStream.close();
                    return byteArray;
                } catch (Throwable th4) {
                    th4.printStackTrace();
                    return byteArray;
                }
            } catch (Throwable th5) {
                th = th5;
                try {
                    th.printStackTrace();
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (Throwable th6) {
                            th6.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                            return null;
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                } finally {
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    private static String m1417b() {
        String str;
        try {
            str = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            th.printStackTrace();
            str = null;
        }
        return str == null ? "null" : str;
    }

    /* renamed from: b */
    public static String m1418b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    /* renamed from: b */
    public static Proxy m1419b(Context context) {
        try {
            return Build.VERSION.SDK_INT >= 11 ? m1409a(context, new URI("http://restapi.amap.com")) : m1421c(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private static int m1420c() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0063, code lost:
    
        if (r8 == (-1)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0092, code lost:
    
        r12 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008f, code lost:
    
        if (r8 == (-1)) goto L48;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x009a A[Catch: all -> 0x009f, TRY_ENTER, TRY_LEAVE, TryCatch #10 {all -> 0x009f, blocks: (B:10:0x009a, B:69:0x0107), top: B:4:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0110 A[Catch: all -> 0x011c, TRY_LEAVE, TryCatch #9 {all -> 0x011c, blocks: (B:12:0x010a, B:14:0x0110), top: B:11:0x010a }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00bf A[Catch: all -> 0x0121, TryCatch #1 {all -> 0x0121, blocks: (B:85:0x00a7, B:55:0x00b9, B:57:0x00bf, B:59:0x00d3, B:61:0x00d9, B:73:0x00e9, B:75:0x00ef, B:77:0x00f5), top: B:5:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0107 A[Catch: all -> 0x009f, TRY_ENTER, TRY_LEAVE, TryCatch #10 {all -> 0x009f, blocks: (B:10:0x009a, B:69:0x0107), top: B:4:0x0022 }] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.ContentResolver, android.database.Cursor] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:109:0x00a0 -> B:11:0x010a). Please report as a decompilation issue!!! */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Proxy m1421c(Context context) {
        String str;
        Cursor cursor;
        String m1367k;
        int m1420c;
        str = "10.0.0.200";
        if (C3618i.m1365i(context) == 0) {
            Uri parse = Uri.parse("content://telephony/carriers/preferapn");
            ?? contentResolver = context.getContentResolver();
            int i = 0;
            boolean z = true;
            int i2 = 80;
            int i3 = -1;
            try {
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                try {
                    cursor = contentResolver.query(parse, null, null, null, null);
                } catch (SecurityException unused) {
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                }
                try {
                    if (cursor != null) {
                        try {
                        } catch (SecurityException unused2) {
                            i = -1;
                            m1367k = C3618i.m1367k(context);
                            int i4 = i;
                            if (m1367k != null) {
                                String lowerCase = m1367k.toLowerCase(Locale.US);
                                String m1417b = m1417b();
                                i4 = m1420c();
                                if (lowerCase.indexOf(NetworkUtil.NET_CTWAP) != -1) {
                                    if (TextUtils.isEmpty(m1417b) || m1417b.equals("null")) {
                                        m1417b = null;
                                        z = false;
                                    }
                                    if (z) {
                                        str = m1417b;
                                    }
                                    if (i4 == -1) {
                                    }
                                    i2 = i4;
                                } else {
                                    i4 = i4;
                                    if (lowerCase.indexOf("wap") != -1) {
                                        if (TextUtils.isEmpty(m1417b) || m1417b.equals("null")) {
                                            m1417b = null;
                                            z = false;
                                        }
                                        if (z) {
                                            str = m1417b;
                                        }
                                    }
                                }
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (m1412a(str, i2)) {
                                }
                                return null;
                            }
                            str = null;
                            i2 = i4;
                            if (cursor != null) {
                            }
                            if (m1412a(str, i2)) {
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                        if (cursor.moveToFirst()) {
                            String string = cursor.getString(cursor.getColumnIndex("apn"));
                            String str2 = string;
                            if (string != null) {
                                Locale locale = Locale.US;
                                str2 = string.toLowerCase(locale);
                                i = locale;
                            }
                            try {
                                if (str2 != null && str2.contains(NetworkUtil.NET_CTWAP)) {
                                    String m1417b2 = m1417b();
                                    m1420c = m1420c();
                                    if (TextUtils.isEmpty(m1417b2) || m1417b2.equals("null")) {
                                        m1417b2 = null;
                                        z = false;
                                    }
                                    str = z ? m1417b2 : "10.0.0.200";
                                } else if (str2 != null && str2.contains("wap")) {
                                    String m1417b3 = m1417b();
                                    m1420c = m1420c();
                                    if (TextUtils.isEmpty(m1417b3) || m1417b3.equals("null")) {
                                        m1417b3 = null;
                                        z = false;
                                    }
                                    str = !z ? "10.0.0.172" : m1417b3;
                                }
                                if (cursor != null) {
                                    cursor.close();
                                }
                            } catch (SecurityException unused3) {
                                m1367k = C3618i.m1367k(context);
                                int i42 = i;
                                if (m1367k != null) {
                                }
                                str = null;
                                i2 = i42;
                                if (cursor != null) {
                                }
                                if (m1412a(str, i2)) {
                                }
                                return null;
                            } catch (Throwable th4) {
                                th = th4;
                                i3 = i;
                                th.printStackTrace();
                                if (cursor != null) {
                                    try {
                                        cursor.close();
                                    } catch (Throwable th5) {
                                        th5.printStackTrace();
                                    }
                                }
                                str = null;
                                i2 = i3;
                                if (m1412a(str, i2)) {
                                }
                                return null;
                            }
                            if (m1412a(str, i2)) {
                                return new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(str, i2));
                            }
                        }
                    }
                    if (m1412a(str, i2)) {
                    }
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
                str = null;
                i2 = -1;
                if (cursor != null) {
                }
            } catch (Throwable th7) {
                if (contentResolver != 0) {
                    try {
                        contentResolver.close();
                    } catch (Throwable th8) {
                        th8.printStackTrace();
                    }
                }
                throw th7;
            }
        }
        return null;
    }

    /* renamed from: c */
    public static byte[] m1422c(byte[] bArr) {
        try {
            return m1425f(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    /* renamed from: d */
    public static String m1423d(byte[] bArr) {
        try {
            return m1424e(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public static String m1424e(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0036: IF  (r2 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:44:0x003e, block:B:43:0x0036 */
    /* renamed from: f */
    private static byte[] m1425f(byte[] bArr) throws IOException, Throwable {
        GZIPOutputStream gZIPOutputStream;
        GZIPOutputStream gZIPOutputStream2;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            if (bArr == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream2);
                } catch (IOException e) {
                    e = e;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    gZIPOutputStream2.write(bArr);
                    gZIPOutputStream2.finish();
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    gZIPOutputStream2.close();
                    byteArrayOutputStream2.close();
                    return byteArray;
                } catch (IOException e2) {
                    e = e2;
                    throw e;
                }
            } catch (IOException e3) {
                throw e3;
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            if (0 != 0) {
                byteArrayOutputStream.close();
            }
            throw th3;
        }
    }
}
