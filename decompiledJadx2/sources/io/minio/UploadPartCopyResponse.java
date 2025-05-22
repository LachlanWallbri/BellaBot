package io.minio;

import io.minio.messages.CopyPartResult;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class UploadPartCopyResponse extends GenericResponse {
    private int partNumber;
    private CopyPartResult result;
    private String uploadId;

    public UploadPartCopyResponse(Headers headers, String str, String str2, String str3, String str4, int i, CopyPartResult copyPartResult) {
        super(headers, str, str2, str3);
        this.uploadId = str4;
        this.partNumber = i;
        this.result = copyPartResult;
    }

    public String uploadId() {
        return this.uploadId;
    }

    public int partNumber() {
        return this.partNumber;
    }

    public CopyPartResult result() {
        return this.result;
    }
}
