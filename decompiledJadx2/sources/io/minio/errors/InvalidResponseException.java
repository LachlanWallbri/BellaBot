package io.minio.errors;

/* loaded from: classes7.dex */
public class InvalidResponseException extends MinioException {
    private static final long serialVersionUID = -4793742105569629274L;

    public InvalidResponseException(int i, String str, String str2, String str3) {
        super("Non-XML response from server. Response code: " + i + ", Content-Type: " + str + ", body: " + str2, str3);
    }
}
