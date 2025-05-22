package io.minio;

import okhttp3.Headers;

/* loaded from: classes7.dex */
public class AbortMultipartUploadResponse extends GenericResponse {
    private String uploadId;

    public AbortMultipartUploadResponse(Headers headers, String str, String str2, String str3, String str4) {
        super(headers, str, str2, str3);
        this.uploadId = str4;
    }

    public String uploadId() {
        return this.uploadId;
    }
}
