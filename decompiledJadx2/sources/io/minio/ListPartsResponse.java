package io.minio;

import io.minio.messages.ListPartsResult;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class ListPartsResponse extends GenericResponse {
    private ListPartsResult result;

    public ListPartsResponse(Headers headers, String str, String str2, String str3, ListPartsResult listPartsResult) {
        super(headers, str, str2, str3);
        this.result = listPartsResult;
    }

    public ListPartsResult result() {
        return this.result;
    }
}
