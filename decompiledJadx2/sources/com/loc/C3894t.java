package com.loc;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.json.JSONObject;

/* compiled from: Utils.java */
/* renamed from: com.loc.t */
/* loaded from: classes4.dex */
public final class C3894t {

    /* renamed from: a */
    static String f4324a;

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("=");
        }
        f4324a = sb.toString();
    }

    /* renamed from: a */
    public static String m3223a(long j) {
        try {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m3224a(Throwable th) {
        StringWriter stringWriter;
        PrintWriter printWriter;
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
                String obj = stringWriter.toString();
                try {
                    stringWriter.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                try {
                    printWriter.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                return obj;
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
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    return null;
                } finally {
                }
            }
        } catch (Throwable th8) {
            th = th8;
            stringWriter = null;
            printWriter = null;
        }
    }

    /* renamed from: a */
    public static String m3225a(Map<String, String> map) {
        String value;
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (z) {
                    z = false;
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    value = entry.getValue();
                } else {
                    stringBuffer.append("&");
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    value = entry.getValue();
                }
                stringBuffer.append(value);
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "Utils", "assembleParams");
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m3226a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    /* renamed from: a */
    public static void m3227a(Context context, String str, String str2, String str3) {
        Log.i("authErrLog", f4324a);
        Log.i("authErrLog", "                                   鉴权错误信息                                  ");
        Log.i("authErrLog", f4324a);
        m3242e("SHA1Package:" + C3885k.m3126e(context));
        m3242e("key:" + C3885k.m3127f(context));
        m3242e("csid:" + str);
        m3242e("gsid:" + str2);
        m3242e("json:" + str3);
        Log.i("authErrLog", "                                                                               ");
        Log.i("authErrLog", "请仔细检查 SHA1Package与Key申请信息是否对应，Key是否删除，平台是否匹配                ");
        Log.i("authErrLog", "如若确认无误，仍存在问题，请将全部log信息提交到工单系统，多谢合作                       ");
        Log.i("authErrLog", f4324a);
    }

    /* renamed from: a */
    public static void m3228a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        try {
            byteArrayOutputStream.write(new byte[]{b});
            int i = b & 255;
            if (i < 255 && i > 0) {
                byteArrayOutputStream.write(bArr);
            } else if (i == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (IOException e) {
            C3897w.m3249a(e, "Utils", "writeField");
        }
    }

    /* renamed from: a */
    public static void m3229a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            try {
                byteArrayOutputStream.write(new byte[]{0});
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int length = str.length();
        if (length > 255) {
            length = 255;
        }
        m3228a(byteArrayOutputStream, (byte) length, m3232a(str));
    }

    /* renamed from: a */
    public static boolean m3230a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    /* renamed from: a */
    public static byte[] m3231a() {
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            String[] split2 = new StringBuffer(new String(C3889o.m3182b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split2.length];
            for (int i2 = 0; i2 < split2.length; i2++) {
                bArr2[i2] = Byte.parseByte(split2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            C3897w.m3249a(th, "Utils", "getIV");
            return new byte[16];
        }
    }

    /* renamed from: a */
    public static byte[] m3232a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    /* renamed from: b */
    public static String m3233b(String str) {
        if (str == null) {
            return null;
        }
        String m3181b = C3889o.m3181b(m3232a(str));
        return ((char) ((m3181b.length() % 26) + 65)) + m3181b;
    }

    /* renamed from: b */
    public static String m3234b(Map<String, String> map) {
        String str;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }
            str = sb.toString();
        } else {
            str = null;
        }
        return m3239d(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static PublicKey m3235b() throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(C3889o.m3182b("MIICnjCCAgegAwIBAgIJAJ0Pdzos7ZfYMA0GCSqGSIb3DQEBBQUAMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjAeFw0xMzA4MTUwNzU2NTVaFw0yMzA4MTMwNzU2NTVaMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA8eWAyHbFPoFPfdx5AD+D4nYFq4dbJ1p7SIKt19Oz1oivF/6H43v5Fo7s50pD1UF8+Qu4JoUQxlAgOt8OCyQ8DYdkaeB74XKb1wxkIYg/foUwN1CMHPZ9O9ehgna6K4EJXZxR7Y7XVZnbjHZIVn3VpPU/Rdr2v37LjTw+qrABJxMCAwEAAaNQME4wHQYDVR0OBBYEFOM/MLGP8xpVFuVd+3qZkw7uBvOTMB8GA1UdIwQYMBaAFOM/MLGP8xpVFuVd+3qZkw7uBvOTMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEA4LY3g8aAD8JkxAOqUXDDyLuCCGOc2pTIhn0TwMNaVdH4hZlpTeC/wuRD5LJ0z3j+IQ0vLvuQA5uDjVyEOlBrvVIGwSem/1XGUo13DfzgAJ5k1161S5l+sFUo5TxpHOXr8Z5nqJMjieXmhnE/I99GFyHpQmw4cC6rhYUhdhtg+Zk="));
        } catch (Throwable th) {
            th = th;
            byteArrayInputStream = null;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Certificate generateCertificate = certificateFactory.generateCertificate(byteArrayInputStream);
            if (generateCertificate != null && keyFactory != null) {
                PublicKey generatePublic = keyFactory.generatePublic(new X509EncodedKeySpec(generateCertificate.getPublicKey().getEncoded()));
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return generatePublic;
            }
            try {
                byteArrayInputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            try {
                th.printStackTrace();
                return null;
            } finally {
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public static byte[] m3236b(byte[] bArr) {
        try {
            return m3244g(bArr);
        } catch (Throwable th) {
            C3897w.m3249a(th, "Utils", "gZip");
            return new byte[0];
        }
    }

    /* renamed from: c */
    public static String m3237c(String str) {
        return str.length() < 2 ? "" : C3889o.m3177a(str.substring(1));
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x003c -> B:16:0x005c). Please report as a decompilation issue!!! */
    /* renamed from: c */
    public static byte[] m3238c(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
            } catch (Throwable th) {
                C3897w.m3249a(th, "Utils", "zip2");
            }
            if (bArr.length != 0) {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        zipOutputStream = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = null;
                    zipOutputStream = null;
                }
                try {
                    zipOutputStream.putNextEntry(new ZipEntry("log"));
                    zipOutputStream.write(bArr);
                    zipOutputStream.closeEntry();
                    zipOutputStream.finish();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    try {
                        zipOutputStream.close();
                    } catch (Throwable th4) {
                        C3897w.m3249a(th4, "Utils", "zip1");
                    }
                    byteArrayOutputStream.close();
                } catch (Throwable th5) {
                    th = th5;
                    try {
                        C3897w.m3249a(th, "Utils", ArchiveStreamFactory.ZIP);
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.close();
                            } catch (Throwable th6) {
                                C3897w.m3249a(th6, "Utils", "zip1");
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        return bArr2;
                    } finally {
                    }
                }
                return bArr2;
            }
        }
        return null;
    }

    /* renamed from: d */
    private static String m3239d(String str) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "Utils", "sortParams");
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(str2);
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static String m3240d(byte[] bArr) {
        try {
            return m3243f(bArr);
        } catch (Throwable th) {
            C3897w.m3249a(th, "Utils", "HexString");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static String m3241e(byte[] bArr) {
        try {
            return m3243f(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    private static void m3242e(String str) {
        int i;
        while (true) {
            if (str.length() < 78) {
                break;
            }
            Log.i("authErrLog", "|" + str.substring(0, 78) + "|");
            str = str.substring(78);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        sb.append(str);
        for (i = 0; i < 78 - str.length(); i++) {
            sb.append(" ");
        }
        sb.append("|");
        Log.i("authErrLog", sb.toString());
    }

    /* renamed from: f */
    public static String m3243f(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* renamed from: g */
    private static byte[] m3244g(byte[] bArr) throws IOException, Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        GZIPOutputStream gZIPOutputStream2 = null;
        if (bArr == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream2 = gZIPOutputStream;
            try {
                throw th;
            } catch (Throwable th4) {
                if (gZIPOutputStream2 != null) {
                    gZIPOutputStream2.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th4;
            }
        }
    }
}
