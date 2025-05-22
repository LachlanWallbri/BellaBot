package io.minio;

import okhttp3.Headers;

/* loaded from: classes7.dex */
public class GenericResponse {
    private String bucket;
    private Headers headers;
    private String object;
    private String region;

    public GenericResponse(Headers headers, String str, String str2, String str3) {
        this.headers = headers;
        this.bucket = str;
        this.region = str2;
        this.object = str3;
    }

    public Headers headers() {
        return this.headers;
    }

    public String bucket() {
        return this.bucket;
    }

    public String region() {
        return this.region;
    }

    public String object() {
        return this.object;
    }
}
