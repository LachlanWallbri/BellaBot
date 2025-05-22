package org.conscrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
final class CryptoUpcalls {
    private static final Logger logger = Logger.getLogger(CryptoUpcalls.class.getName());

    private CryptoUpcalls() {
    }

    private static ArrayList<Provider> getExternalProviders(String str) {
        ArrayList<Provider> arrayList = new ArrayList<>(1);
        for (Provider provider : Security.getProviders(str)) {
            if (!Conscrypt.isConscrypt(provider)) {
                arrayList.add(provider);
            }
        }
        if (arrayList.isEmpty()) {
            logger.warning("Could not find external provider for algorithm: " + str);
        }
        return arrayList;
    }

    static byte[] ecSignDigestWithPrivateKey(PrivateKey privateKey, byte[] bArr) {
        if (!"EC".equals(privateKey.getAlgorithm())) {
            throw new RuntimeException("Unexpected key type: " + privateKey.toString());
        }
        return signDigestWithPrivateKey(privateKey, bArr, "NONEwithECDSA");
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
    
        if (org.conscrypt.Conscrypt.isConscrypt(r1.getProvider()) != false) goto L8;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static byte[] signDigestWithPrivateKey(PrivateKey privateKey, byte[] bArr, String str) {
        Signature signature;
        Iterator<Provider> it;
        Signature signature2;
        RuntimeException runtimeException;
        try {
            signature = Signature.getInstance(str);
            signature.initSign(privateKey);
        } catch (InvalidKeyException e) {
            logger.warning("Preferred provider doesn't support key:");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException unused) {
            logger.warning("Unsupported signature algorithm: " + str);
            return null;
        }
        while (it.hasNext()) {
            try {
                signature2 = Signature.getInstance(str, it.next());
                signature2.initSign(privateKey);
                break;
            } catch (RuntimeException e2) {
                if (runtimeException == null) {
                    runtimeException = e2;
                }
                signature2 = null;
            } catch (InvalidKeyException | NoSuchAlgorithmException unused2) {
                signature2 = null;
            }
        }
        if (signature2 != null) {
            if (runtimeException != null) {
                throw runtimeException;
            }
            logger.warning("Could not find provider for algorithm: " + str);
            return null;
        }
        signature = signature2;
        try {
            signature.update(bArr);
            return signature.sign();
        } catch (Exception e3) {
            logger.log(Level.WARNING, "Exception while signing message with " + privateKey.getAlgorithm() + " private key:", (Throwable) e3);
            return null;
        }
        signature = null;
        if (signature == null) {
            it = getExternalProviders("Signature." + str).iterator();
            signature2 = signature;
            runtimeException = null;
            while (it.hasNext()) {
            }
            if (signature2 != null) {
            }
        }
        signature.update(bArr);
        return signature.sign();
    }

    static byte[] rsaSignDigestWithPrivateKey(PrivateKey privateKey, int i, byte[] bArr) {
        return rsaOpWithPrivateKey(privateKey, i, 1, bArr);
    }

    static byte[] rsaDecryptWithPrivateKey(PrivateKey privateKey, int i, byte[] bArr) {
        return rsaOpWithPrivateKey(privateKey, i, 2, bArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x006e, code lost:
    
        if (org.conscrypt.Conscrypt.isConscrypt(r1.getProvider()) != false) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static byte[] rsaOpWithPrivateKey(PrivateKey privateKey, int i, int i2, byte[] bArr) {
        String str;
        String str2;
        Cipher cipher;
        Iterator<Provider> it;
        String algorithm = privateKey.getAlgorithm();
        if (!"RSA".equals(algorithm)) {
            logger.warning("Unexpected key type: " + algorithm);
            return null;
        }
        if (i == 1) {
            str = "PKCS1Padding";
        } else if (i == 3) {
            str = "NoPadding";
        } else {
            if (i != 4) {
                logger.warning("Unsupported OpenSSL/BoringSSL padding: " + i);
                return null;
            }
            str = "OAEPPadding";
        }
        str2 = "RSA/ECB/" + str;
        try {
            cipher = Cipher.getInstance(str2);
            cipher.init(i2, privateKey);
        } catch (InvalidKeyException e) {
            logger.log(Level.WARNING, "Preferred provider doesn't support key:", (Throwable) e);
        } catch (NoSuchAlgorithmException unused) {
            logger.warning("Unsupported cipher algorithm: " + str2);
            return null;
        } catch (NoSuchPaddingException unused2) {
            logger.warning("Unsupported cipher algorithm: " + str2);
            return null;
        }
        while (it.hasNext()) {
            try {
                cipher = Cipher.getInstance(str2, it.next());
                cipher.init(i2, privateKey);
                break;
            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException unused3) {
                cipher = null;
            }
        }
        if (cipher == null) {
            logger.warning("Could not find provider for algorithm: " + str2);
            return null;
        }
        try {
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            logger.log(Level.WARNING, "Exception while decrypting message with " + privateKey.getAlgorithm() + " private key using " + str2 + ":", (Throwable) e2);
            return null;
        }
        cipher = null;
        if (cipher == null) {
            it = getExternalProviders("Cipher." + str2).iterator();
            while (it.hasNext()) {
            }
            if (cipher == null) {
            }
        }
        return cipher.doFinal(bArr);
    }
}
