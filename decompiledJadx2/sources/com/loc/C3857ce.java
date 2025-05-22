package com.loc;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: Encrypt.java */
/* renamed from: com.loc.ce */
/* loaded from: classes4.dex */
public final class C3857ce {

    /* renamed from: a */
    private static final char[] f3900a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b */
    private static final byte[] f3901b = {0, 1, 1, 2, 3, 5, 8, 13, 8, 7, 6, 5, 4, 3, 2, 1};

    /* renamed from: c */
    private static final IvParameterSpec f3902c = new IvParameterSpec(f3901b);

    /* renamed from: a */
    public static String m2795a(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    return m2796a(MessageDigestAlgorithms.MD5, m2796a("SHA1", str) + str);
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "Encrypt", "generatorKey");
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m2796a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            byte[] m3187a = C3890p.m3187a(str2.getBytes("UTF-8"), str);
            int length = m3187a.length;
            StringBuilder sb = new StringBuilder(length * 2);
            for (int i = 0; i < length; i++) {
                sb.append(f3900a[(m3187a[i] >> 4) & 15]);
                sb.append(f3900a[m3187a[i] & 15]);
            }
            return sb.toString();
        } catch (Throwable th) {
            C3880f.m3097a(th, "Encrypt", "encode");
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m2797a(byte[] bArr) {
        try {
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[bArr.length - 16];
            System.arraycopy(bArr, 0, bArr2, 0, 16);
            System.arraycopy(bArr, 16, bArr3, 0, bArr.length - 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
            cipher.init(2, secretKeySpec, new IvParameterSpec(C3894t.m3231a()));
            return cipher.doFinal(bArr3);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Encrypt", "decryptRsponse");
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized byte[] m2798a(byte[] bArr, String str) throws Exception {
        byte[] byteArray;
        synchronized (C3857ce.class) {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(C3889o.m3182b(str)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, generatePrivate);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    byte[] doFinal = i3 > 245 ? cipher.doFinal(bArr, i, 245) : cipher.doFinal(bArr, i, i3);
                    byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                    i2++;
                    i = i2 * 245;
                } else {
                    byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                }
            }
        }
        return byteArray;
    }

    /* renamed from: a */
    public static byte[] m2799a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        try {
            BigInteger modPow = new BigInteger(bArr).modPow(bigInteger, bigInteger2);
            byte[] bArr2 = new byte[16];
            BigInteger bigInteger3 = new BigInteger("256");
            int i = 0;
            while (modPow.bitCount() > 0 && i < 16) {
                BigInteger[] divideAndRemainder = modPow.divideAndRemainder(bigInteger3);
                BigInteger bigInteger4 = divideAndRemainder[0];
                bArr2[i] = (byte) divideAndRemainder[1].intValue();
                i++;
                modPow = bigInteger4;
            }
            return bArr2;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m2800a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
        cipher.init(2, new SecretKeySpec(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM), f3902c);
        return cipher.doFinal(bArr2);
    }

    /* renamed from: b */
    private static SecretKeySpec m2801b(String str) {
        byte[] bArr;
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        while (true) {
            stringBuffer.append(str);
            if (stringBuffer.length() >= 16) {
                break;
            }
            str = "0";
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            bArr = stringBuffer.toString().getBytes("UTF-8");
        } catch (Throwable th) {
            C3880f.m3097a(th, "Encrypt", "createKey");
            bArr = null;
        }
        return new SecretKeySpec(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
    }

    /* renamed from: b */
    public static synchronized byte[] m2802b(byte[] bArr, String str) throws Exception {
        byte[] byteArray;
        synchronized (C3857ce.class) {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(C3889o.m3182b(str)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, generatePrivate);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    byte[] doFinal = i3 > 256 ? cipher.doFinal(bArr, i, 256) : cipher.doFinal(bArr, i, i3);
                    byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                    i2++;
                    i = i2 * 256;
                } else {
                    byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                }
            }
        }
        return byteArray;
    }

    /* renamed from: b */
    public static byte[] m2803b(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: c */
    public static byte[] m2804c(byte[] bArr, String str) {
        try {
            SecretKeySpec m2801b = m2801b(str);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(C3894t.m3231a());
            Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
            cipher.init(1, m2801b, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Encrypt", "aesEncrypt");
            return null;
        }
    }

    /* renamed from: d */
    public static byte[] m2805d(byte[] bArr, String str) {
        try {
            SecretKeySpec m2801b = m2801b(str);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(C3894t.m3231a());
            Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
            cipher.init(2, m2801b, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Encrypt", "aesDecrypt");
            return null;
        }
    }
}
