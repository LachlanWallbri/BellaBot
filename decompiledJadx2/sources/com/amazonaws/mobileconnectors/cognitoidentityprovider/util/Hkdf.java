package com.amazonaws.mobileconnectors.cognitoidentityprovider.util;

import com.amazonaws.util.StringUtils;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class Hkdf {
    private static final byte[] EMPTY_ARRAY = new byte[0];
    private static final int MAX_KEY_SIZE = 255;
    private final String algorithm;
    private SecretKey prk = null;

    public static Hkdf getInstance(String str) throws NoSuchAlgorithmException {
        Mac.getInstance(str);
        return new Hkdf(str);
    }

    public void init(byte[] bArr) {
        init(bArr, (byte[]) null);
    }

    public void init(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr2 == null ? EMPTY_ARRAY : (byte[]) bArr2.clone();
        byte[] bArr4 = EMPTY_ARRAY;
        try {
            try {
                Mac mac = Mac.getInstance(this.algorithm);
                if (bArr3.length == 0) {
                    bArr3 = new byte[mac.getMacLength()];
                    Arrays.fill(bArr3, (byte) 0);
                }
                mac.init(new SecretKeySpec(bArr3, this.algorithm));
                bArr4 = mac.doFinal(bArr);
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr4, this.algorithm);
                Arrays.fill(bArr4, (byte) 0);
                unsafeInitWithoutKeyExtraction(secretKeySpec);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("Unexpected exception", e);
            }
        } finally {
            Arrays.fill(bArr4, (byte) 0);
        }
    }

    public void unsafeInitWithoutKeyExtraction(SecretKey secretKey) throws InvalidKeyException {
        if (!secretKey.getAlgorithm().equals(this.algorithm)) {
            throw new InvalidKeyException("Algorithm for the provided key must match the algorithm for this Hkdf. Expected " + this.algorithm + " but found " + secretKey.getAlgorithm());
        }
        this.prk = secretKey;
    }

    private Hkdf(String str) {
        if (!str.startsWith("Hmac")) {
            throw new IllegalArgumentException("Invalid algorithm " + str + ". Hkdf may only be used with Hmac algorithms.");
        }
        this.algorithm = str;
    }

    public byte[] deriveKey(String str, int i) {
        return deriveKey(str != null ? str.getBytes(StringUtils.UTF8) : null, i);
    }

    public byte[] deriveKey(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        try {
            deriveKey(bArr, i, bArr2, 0);
            return bArr2;
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public void deriveKey(byte[] bArr, int i, byte[] bArr2, int i2) throws ShortBufferException {
        int i3;
        assertInitialized();
        if (i < 0) {
            throw new IllegalArgumentException("Length must be a non-negative value.");
        }
        if (bArr2.length < i2 + i) {
            throw new ShortBufferException();
        }
        Mac createMac = createMac();
        if (i > createMac.getMacLength() * 255) {
            throw new IllegalArgumentException("Requested keys may not be longer than 255 times the underlying HMAC length.");
        }
        byte b = 1;
        byte[] bArr3 = EMPTY_ARRAY;
        for (int i4 = 0; i4 < i; i4 = i3) {
            try {
                createMac.update(bArr3);
                createMac.update(bArr);
                createMac.update(b);
                bArr3 = createMac.doFinal();
                i3 = i4;
                int i5 = 0;
                while (i5 < bArr3.length && i3 < i) {
                    bArr2[i3] = bArr3[i5];
                    i5++;
                    i3++;
                }
                b = (byte) (b + 1);
            } finally {
                Arrays.fill(bArr3, (byte) 0);
            }
        }
    }

    private Mac createMac() {
        try {
            Mac mac = Mac.getInstance(this.algorithm);
            mac.init(this.prk);
            return mac;
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void assertInitialized() {
        if (this.prk == null) {
            throw new IllegalStateException("Hkdf has not been initialized");
        }
    }
}
