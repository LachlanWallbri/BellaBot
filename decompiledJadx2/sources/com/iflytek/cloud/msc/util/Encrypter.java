package com.iflytek.cloud.msc.util;

import android.os.Build;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes3.dex */
public class Encrypter {
    public static final int BUFFER_SIZE = 1024;
    public static final byte GZIP_KEY = 5;
    private static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCt8H0BF3SquJmk6xIo2bTldgvtazLIeSbR4cle\np7zeUAtI/mC7UgFl8xXFCTemVambyQFnM5GsZOI1BpAMJO7N/YHRX7hvCZG6D0rEXQEdKXhKFIBQ\nmOYRYZP042vWRcKZ6iQLdLYmyg6tIzjYVfH0f6YX8OLIU7fy0TA/c88rzwIDAQAB";
    private static final int SDK_VERSION_KITKAT = 19;

    public static byte[] intToByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] lightcode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 5);
        }
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] zip5xEncode(byte[] bArr) {
        byte[] bArr2;
        GZIPOutputStream gZIPOutputStream;
        byte[] bArr3 = null;
        bArr3 = null;
        GZIPOutputStream gZIPOutputStream2 = null;
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.finish();
                    if (Build.VERSION.SDK_INT < 19) {
                        gZIPOutputStream.flush();
                    }
                    bArr3 = byteArrayOutputStream.toByteArray();
                    for (int i = 0; i < bArr3.length; i++) {
                        bArr3[i] = (byte) (bArr3[i] ^ 5);
                    }
                    try {
                        gZIPOutputStream.close();
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        DebugLog.LogE(e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    bArr2 = bArr3;
                    gZIPOutputStream2 = gZIPOutputStream;
                    DebugLog.LogE(e);
                    if (gZIPOutputStream2 != null) {
                        try {
                            gZIPOutputStream2.close();
                        } catch (IOException e3) {
                            DebugLog.LogE(e3);
                            bArr3 = bArr2;
                            return bArr3;
                        }
                    }
                    byteArrayOutputStream.close();
                    bArr3 = bArr2;
                    return bArr3;
                } catch (Throwable th) {
                    th = th;
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (IOException e4) {
                            DebugLog.LogE(e4);
                            throw th;
                        }
                    }
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                bArr2 = null;
            }
            return bArr3;
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = bArr3;
        }
    }

    public static byte[] zip5xDecode(byte[] bArr) {
        GZIPInputStream gZIPInputStream;
        Throwable th;
        byte[] bArr2;
        GZIPInputStream gZIPInputStream2 = null;
        byte[] bArr3 = null;
        gZIPInputStream2 = null;
        if (bArr == null) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 5);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr4 = new byte[1024];
                    while (true) {
                        int read = gZIPInputStream.read(bArr4, 0, bArr4.length);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr4, 0, read);
                        } else {
                            bArr3 = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.flush();
                            try {
                                gZIPInputStream.close();
                                byteArrayInputStream.close();
                                byteArrayOutputStream.close();
                                return bArr3;
                            } catch (IOException e) {
                                DebugLog.LogE(e);
                                return bArr3;
                            }
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    bArr2 = bArr3;
                    gZIPInputStream2 = gZIPInputStream;
                    DebugLog.LogE(e);
                    if (gZIPInputStream2 != null) {
                        try {
                            gZIPInputStream2.close();
                        } catch (IOException e3) {
                            DebugLog.LogE(e3);
                            return bArr2;
                        }
                    }
                    byteArrayInputStream.close();
                    byteArrayOutputStream.close();
                    return bArr2;
                } catch (Throwable th2) {
                    th = th2;
                    if (gZIPInputStream != null) {
                        try {
                            gZIPInputStream.close();
                        } catch (IOException e4) {
                            DebugLog.LogE(e4);
                            throw th;
                        }
                    }
                    byteArrayInputStream.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (Throwable th3) {
                gZIPInputStream = gZIPInputStream2;
                th = th3;
            }
        } catch (IOException e5) {
            e = e5;
            bArr2 = null;
        }
    }

    public static synchronized String MD5(String str) {
        String stringBuffer;
        synchronized (Encrypter.class) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                char[] charArray = str.toCharArray();
                byte[] bArr = new byte[charArray.length];
                for (int i = 0; i < charArray.length; i++) {
                    bArr[i] = (byte) charArray[i];
                }
                byte[] digest = messageDigest.digest(bArr);
                StringBuffer stringBuffer2 = new StringBuffer();
                for (byte b : digest) {
                    int i2 = b & 255;
                    if (i2 < 16) {
                        stringBuffer2.append("0");
                    }
                    stringBuffer2.append(Integer.toHexString(i2));
                }
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                DebugLog.LogE(e);
                return "";
            }
        }
        return stringBuffer;
    }

    public static synchronized String cut16MD5(String str) {
        synchronized (Encrypter.class) {
            DebugLog.LogD("cut16MD5 start:" + str);
            String MD5 = MD5(str);
            DebugLog.LogD("cut16MD5 start:" + MD5);
            StringBuffer stringBuffer = new StringBuffer();
            if (MD5 != null && MD5.length() != 0) {
                DebugLog.LogD("cut16MD5 md5 size is:" + MD5.length());
                for (int i = 0; i < MD5.length(); i++) {
                    if (i % 2 == 0) {
                        DebugLog.LogD("cut16MD5 result i:" + MD5.charAt(i));
                        stringBuffer.append(MD5.charAt(i));
                    }
                    DebugLog.LogD("cut16MD5 result i = :" + i);
                }
                DebugLog.LogD("cut16MD5 result:" + ((Object) stringBuffer));
                return stringBuffer.toString();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getFileMd5(File file) {
        FileInputStream fileInputStream;
        String str = null;
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
            if (fileInputStream != null) {
            }
            throw th;
        }
        try {
            try {
                MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                messageDigest.update(map);
                str = new BigInteger(1, messageDigest.digest()).toString(16);
                fileInputStream.close();
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        }
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static byte[] zip(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        byte[] bArr2 = 0;
        bArr2 = 0;
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            } catch (IOException e2) {
                e = e2;
                gZIPOutputStream = null;
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                }
                byteArrayOutputStream.close();
                throw th;
            }
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.finish();
                if (Build.VERSION.SDK_INT < 19) {
                    gZIPOutputStream.flush();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                bArr2 = byteArray;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                byteArrayOutputStream.close();
                return bArr2;
            }
            return bArr2;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                try {
                    bArr2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                    throw th;
                }
            }
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public static byte[] encrypt(byte[] bArr) {
        try {
            byte[] encoded = AESUtil.generateKey().getEncoded();
            byte[] encrypt = bArr != null ? AESUtil.encrypt(zip(bArr), encoded) : null;
            byte[] encryptByPublicKey = RSAUtil.encryptByPublicKey(encoded, RSAUtil.loadPublicKey(RSA_PUBLIC_KEY));
            if (encryptByPublicKey != null && encrypt != null) {
                int length = encryptByPublicKey.length;
                int length2 = encrypt.length;
                byte[] bArr2 = new byte[length + length2 + 10];
                bArr2[0] = 1;
                System.arraycopy(intToByteArray(length), 0, bArr2, 1, 4);
                System.arraycopy(encryptByPublicKey, 0, bArr2, 5, length);
                int i = 5 + length;
                bArr2[i] = 3;
                int i2 = i + 1;
                System.arraycopy(intToByteArray(length2), 0, bArr2, i2, 4);
                System.arraycopy(encrypt, 0, bArr2, i2 + 4, length2);
                return bArr2;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static int byteArrayToInt(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i += (bArr[i2] & 255) << ((3 - i2) * 8);
        }
        return i;
    }
}
