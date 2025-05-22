package com.pudutech.bumblebee.presenter.utils;

import com.amazonaws.services.p048s3.internal.Constants;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public class RSAUtil {
    private static String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/8PBlU8Yx3Mmm3cR1i060SorEwBaHX/WN2oCnCs9T6YThO4qLNedsRkxgXX8VsXzurLPlPZO2UpiSK/bYGuc1LHX3blYntxPUEv3HuRsRKmaT6KrJ0KfKBZl4mN0Zl1xiZ+MsdTSh61Wg1lhc7CoOlgD2nPv6fggf5khQH6MZwQIDAQAB";

    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static String getPublicKey(KeyPair keyPair) {
        return byte2Base64(keyPair.getPublic().getEncoded());
    }

    public static String getPrivateKey(KeyPair keyPair) {
        return byte2Base64(keyPair.getPrivate().getEncoded());
    }

    public static PublicKey string2PublicKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(base642Byte(str)));
    }

    public static PrivateKey string2PrivateKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(base642Byte(str)));
    }

    public static byte[] publicEncrypt(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, publicKey);
        return cipher.doFinal(bArr);
    }

    public static byte[] privateDecrypt(byte[] bArr, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, privateKey);
        return cipher.doFinal(bArr);
    }

    public static String byte2Base64(byte[] bArr) {
        return Base64Util.encode(bArr);
    }

    public static byte[] base642Byte(String str) throws IOException {
        return Base64Util.decode(str);
    }

    public static String rsaKey(String str) {
        try {
            return byte2Base64(publicEncrypt((str + getSixRandomCode()).getBytes(), string2PublicKey(PUBLICKEY)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getSixRandomCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            if ("char".equalsIgnoreCase(random.nextInt(2) % 2 == 0 ? "char" : "num")) {
                sb.append((char) ((random.nextInt(2) % 2 == 0 ? 65 : 97) + random.nextInt(26)));
            } else {
                sb.append(random.nextInt(10));
            }
        }
        StringBuilder sb2 = new StringBuilder(sb.toString().toLowerCase());
        System.out.println(sb2);
        return sb2.toString();
    }

    public static String hamcsha1(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, Constants.HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(Constants.HMAC_SHA1_ALGORITHM);
            mac.init(secretKeySpec);
            return byte2hex(mac.doFinal(bArr));
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String byte2hex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; bArr != null && i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }
}
