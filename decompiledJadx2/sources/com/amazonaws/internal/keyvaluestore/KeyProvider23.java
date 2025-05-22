package com.amazonaws.internal.keyvaluestore;

import android.security.keystore.KeyGenParameterSpec;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import java.security.Key;
import java.security.KeyStore;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/* loaded from: classes.dex */
class KeyProvider23 implements KeyProvider {
    private static final String AES_KEY_ALGORITHM = "AES";
    private static final String ANDROID_KEY_STORE_NAME = "AndroidKeyStore";
    static final String AWS_KEY_VALUE_STORE_VERSION_1_KEY_STORE_ALIAS_FOR_AES_SUFFIX = ".aesKeyStoreAlias";
    private static final int CIPHER_AES_GCM_NOPADDING_KEY_LENGTH_IN_BITS = 256;
    private static final Log logger = LogFactory.getLog((Class<?>) KeyProvider23.class);

    @Override // com.amazonaws.internal.keyvaluestore.KeyProvider
    public synchronized Key retrieveKey(String str) throws KeyNotFoundException {
        Key key;
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
            keyStore.load(null);
            if (keyStore.containsAlias(str)) {
                logger.debug("AndroidKeyStore contains keyAlias " + str);
                logger.debug("Loading the encryption key from Android KeyStore.");
                key = keyStore.getKey(str, null);
                if (key == null) {
                    throw new KeyNotFoundException("Key is null even though the keyAlias: " + str + " is present in " + ANDROID_KEY_STORE_NAME);
                }
            } else {
                throw new KeyNotFoundException("AndroidKeyStore does not contain the keyAlias: " + str);
            }
        } catch (Exception e) {
            throw new KeyNotFoundException("Error occurred while accessing AndroidKeyStore to retrieve the key for keyAlias: " + str, e);
        }
        return key;
    }

    @Override // com.amazonaws.internal.keyvaluestore.KeyProvider
    public synchronized Key generateKey(String str) throws KeyNotGeneratedException {
        SecretKey generateKey;
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
            keyStore.load(null);
            if (!keyStore.containsAlias(str)) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEY_STORE_NAME);
                keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).setRandomizedEncryptionRequired(false).build());
                generateKey = keyGenerator.generateKey();
                logger.info("Generated the encryption key identified by the keyAlias: " + str + " using " + ANDROID_KEY_STORE_NAME);
            } else {
                throw new KeyNotGeneratedException("Key already exists for the keyAlias: " + str + " in " + ANDROID_KEY_STORE_NAME);
            }
        } catch (Exception e) {
            throw new KeyNotGeneratedException("Cannot generate a key for alias: " + str + " in " + ANDROID_KEY_STORE_NAME, e);
        }
        return generateKey;
    }

    @Override // com.amazonaws.internal.keyvaluestore.KeyProvider
    public synchronized void deleteKey(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
            keyStore.load(null);
            keyStore.deleteEntry(str);
        } catch (Exception e) {
            logger.error("Error in deleting the key for keyAlias: " + str + " from Android KeyStore.", e);
        }
    }
}
