package com.iflytek.cloud.msc.util;

import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class AESUtil {
    private static final String CipherMode = "AES/ECB/PKCS5Padding";

    public static SecretKeySpec generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            keyGenerator.init(128, new SecureRandom());
            return new SecretKeySpec(keyGenerator.generateKey().getEncoded(), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateKeyString() {
        return byte2hex(generateKey().getEncoded());
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(1, new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(1, new SecretKeySpec(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM));
            return byte2hex(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str, String str2) {
        byte[] bArr;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        return byte2hex(encrypt(bArr, new SecretKeySpec(hex2byte(str2), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM).getEncoded()));
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(2, new SecretKeySpec(bArr2, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String str, String str2) {
        byte[] bArr;
        try {
            bArr = hex2byte(str);
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        byte[] decrypt = decrypt(bArr, hex2byte(str2));
        if (decrypt == null) {
            return null;
        }
        try {
            return new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String str, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(2, new SecretKeySpec(bArr, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM));
            return new String(cipher.doFinal(hex2byte(str)), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String byte2hex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    public static byte[] hex2byte(String str) {
        if (str == null || str.length() < 2) {
            return new byte[0];
        }
        String lowerCase = str.toLowerCase();
        int length = lowerCase.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Integer.parseInt(lowerCase.substring(i2, i2 + 2), 16) & 255);
        }
        return bArr;
    }
}
