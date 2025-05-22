package io.minio;

import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class ServerSideEncryptionS3 extends ServerSideEncryption {
    private static final Map<String, String> headers;

    @Override // io.minio.ServerSideEncryption
    public final boolean tlsRequired() {
        return false;
    }

    public String toString() {
        return "SSE-S3";
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("X-Amz-Server-Side-Encryption", ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        headers = Collections.unmodifiableMap(hashMap);
    }

    @Override // io.minio.ServerSideEncryption
    public final Map<String, String> headers() {
        return headers;
    }
}
