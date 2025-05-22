package io.minio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class ServerSideEncryptionKms extends ServerSideEncryption {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, String> headers;

    public String toString() {
        return "SSE-KMS";
    }

    public ServerSideEncryptionKms(String str, Map<String, String> map) throws JsonProcessingException {
        if (str == null) {
            throw new IllegalArgumentException("Key ID cannot be null");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("X-Amz-Server-Side-Encryption", "aws:kms");
        hashMap.put("X-Amz-Server-Side-Encryption-Aws-Kms-Key-Id", str);
        if (map != null) {
            hashMap.put("X-Amz-Server-Side-Encryption-Context", Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(map).getBytes(StandardCharsets.UTF_8)));
        }
        this.headers = Collections.unmodifiableMap(hashMap);
    }

    @Override // io.minio.ServerSideEncryption
    public final Map<String, String> headers() {
        return this.headers;
    }
}
