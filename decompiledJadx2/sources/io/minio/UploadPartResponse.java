package io.minio;

import okhttp3.Headers;

/* loaded from: classes7.dex */
public class UploadPartResponse extends GenericResponse {
    private String etag;
    private int partNumber;
    private String uploadId;

    public UploadPartResponse(Headers headers, String str, String str2, String str3, String str4, int i, String str5) {
        super(headers, str, str2, str3);
        this.uploadId = str4;
        this.partNumber = i;
        this.etag = str5;
    }

    public String uploadId() {
        return this.uploadId;
    }

    public int partNumber() {
        return this.partNumber;
    }

    public String etag() {
        return this.etag;
    }
}
