package io.minio;

import io.minio.messages.ListMultipartUploadsResult;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class ListMultipartUploadsResponse extends GenericResponse {
    private ListMultipartUploadsResult result;

    public ListMultipartUploadsResponse(Headers headers, String str, String str2, ListMultipartUploadsResult listMultipartUploadsResult) {
        super(headers, str, str2, null);
        this.result = listMultipartUploadsResult;
    }

    public ListMultipartUploadsResult result() {
        return this.result;
    }
}
