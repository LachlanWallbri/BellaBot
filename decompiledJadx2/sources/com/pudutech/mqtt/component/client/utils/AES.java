package com.pudutech.mqtt.component.client.utils;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class AES {
    private static final String ALGORITHM_AES = "AES";
    public static final int KEY_SIZE_128 = 128;
    public static final int KEY_SIZE_192 = 192;
    public static final int KEY_SIZE_256 = 256;
    public static final String MODE_CBC = "CBC";
    public static final String MODE_ECB = "ECB";
    public static final String PADDING_ISO10126 = "ISO10126Padding";
    public static final String PADDING_PKCS5 = "PKCS5Padding";
    private static String aesIv = "pudu201820192020";
    private static String aesKey = "pudu202020192018";

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface AesKeySize {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface Mode {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface Padding {
    }

    public static void main(String[] strArr) {
    }

    public static byte[] generateKey() {
        return generateKey(128);
    }

    public static byte[] generateKey(int i) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(i);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str) {
        return encrypt(str, aesKey.getBytes(), MODE_CBC, PADDING_PKCS5);
    }

    public static String encrypt(String str, byte[] bArr, String str2, String str3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/" + str2 + "/" + str3);
            if (MODE_ECB.equals(str2)) {
                cipher.init(1, secretKeySpec);
            } else {
                cipher.init(1, secretKeySpec, new IvParameterSpec(aesIv.getBytes()));
            }
            return HexUtil.bytesToHexStr(cipher.doFinal(str.getBytes()));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
            return "加密失败：" + str;
        }
    }

    public static String decrypt(String str) {
        return decrypt(str, aesKey.getBytes(), MODE_CBC, PADDING_PKCS5);
    }

    public static String decrypt(String str, byte[] bArr, String str2, String str3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/" + str2 + "/" + str3);
            if (MODE_ECB.equals(str2)) {
                cipher.init(2, secretKeySpec);
            } else {
                cipher.init(2, secretKeySpec, new IvParameterSpec(aesIv.getBytes()));
            }
            return new String(cipher.doFinal(HexUtil.decodeHex(str.toCharArray())), "UTF-8");
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
            return "解密失败：" + str;
        }
    }

    private static byte[] generateIvBytes(int i) {
        Random random = new Random();
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) random.nextInt(256);
        }
        return bArr;
    }
}
