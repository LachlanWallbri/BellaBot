package io.minio;

import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import com.google.common.io.BaseEncoding;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes7.dex */
public class ServerSideEncryptionCustomerKey extends ServerSideEncryption {
    private final Map<String, String> copySourceHeaders;
    private final Map<String, String> headers;
    private boolean isDestroyed = false;
    private final SecretKey secretKey;

    public String toString() {
        return "SSE-C";
    }

    public ServerSideEncryptionCustomerKey(SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
        if (secretKey == null || !secretKey.getAlgorithm().equals(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM) || secretKey.getEncoded().length != 32) {
            throw new IllegalArgumentException("Secret key must be 256 bit AES key");
        }
        if (secretKey.isDestroyed()) {
            throw new IllegalArgumentException("Secret key already destroyed");
        }
        this.secretKey = secretKey;
        byte[] encoded = secretKey.getEncoded();
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        messageDigest.update(encoded);
        String encode = BaseEncoding.base64().encode(encoded);
        String encode2 = BaseEncoding.base64().encode(messageDigest.digest());
        HashMap hashMap = new HashMap();
        hashMap.put("X-Amz-Server-Side-Encryption-Customer-Algorithm", ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        hashMap.put("X-Amz-Server-Side-Encryption-Customer-Key", encode);
        hashMap.put("X-Amz-Server-Side-Encryption-Customer-Key-Md5", encode2);
        this.headers = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("X-Amz-Copy-Source-Server-Side-Encryption-Customer-Algorithm", ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        hashMap2.put("X-Amz-Copy-Source-Server-Side-Encryption-Customer-Key", encode);
        hashMap2.put("X-Amz-Copy-Source-Server-Side-Encryption-Customer-Key-Md5", encode2);
        this.copySourceHeaders = Collections.unmodifiableMap(hashMap2);
    }

    @Override // io.minio.ServerSideEncryption
    public final Map<String, String> headers() {
        if (this.isDestroyed) {
            throw new IllegalStateException("Secret key was destroyed");
        }
        return this.headers;
    }

    @Override // io.minio.ServerSideEncryption
    public final Map<String, String> copySourceHeaders() {
        if (this.isDestroyed) {
            throw new IllegalStateException("Secret key was destroyed");
        }
        return this.copySourceHeaders;
    }

    public final void destroy() throws DestroyFailedException {
        this.secretKey.destroy();
        this.isDestroyed = true;
    }
}
