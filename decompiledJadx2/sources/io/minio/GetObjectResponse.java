package io.minio;

import java.io.FilterInputStream;
import java.io.InputStream;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class GetObjectResponse extends FilterInputStream {
    private GenericResponse response;

    public GetObjectResponse(Headers headers, String str, String str2, String str3, InputStream inputStream) {
        super(inputStream);
        this.response = new GenericResponse(headers, str, str2, str3);
    }

    public Headers headers() {
        return this.response.headers();
    }

    public String bucket() {
        return this.response.bucket();
    }

    public String region() {
        return this.response.region();
    }

    public String object() {
        return this.response.object();
    }
}
