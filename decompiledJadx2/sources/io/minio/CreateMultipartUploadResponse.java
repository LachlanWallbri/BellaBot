package io.minio;

import io.minio.messages.InitiateMultipartUploadResult;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class CreateMultipartUploadResponse extends GenericResponse {
    private InitiateMultipartUploadResult result;

    public CreateMultipartUploadResponse(Headers headers, String str, String str2, String str3, InitiateMultipartUploadResult initiateMultipartUploadResult) {
        super(headers, str, str2, str3);
        this.result = initiateMultipartUploadResult;
    }

    public InitiateMultipartUploadResult result() {
        return this.result;
    }
}
