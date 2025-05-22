package io.minio;

import io.minio.messages.ListVersionsResult;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class ListObjectVersionsResponse extends GenericResponse {
    private ListVersionsResult result;

    public ListObjectVersionsResponse(Headers headers, String str, String str2, ListVersionsResult listVersionsResult) {
        super(headers, str, str2, null);
        this.result = listVersionsResult;
    }

    public ListVersionsResult result() {
        return this.result;
    }
}
