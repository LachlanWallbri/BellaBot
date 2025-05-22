package io.minio;

import okhttp3.Headers;

/* loaded from: classes7.dex */
public class ObjectWriteResponse extends GenericResponse {
    private String etag;
    private String versionId;

    public ObjectWriteResponse(Headers headers, String str, String str2, String str3, String str4, String str5) {
        super(headers, str, str2, str3);
        this.etag = str4;
        this.versionId = str5;
    }

    public String etag() {
        return this.etag;
    }

    public String versionId() {
        return this.versionId;
    }
}
