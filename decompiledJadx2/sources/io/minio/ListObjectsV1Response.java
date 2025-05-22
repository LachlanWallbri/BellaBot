package io.minio;

import io.minio.messages.ListBucketResultV1;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class ListObjectsV1Response extends GenericResponse {
    private ListBucketResultV1 result;

    public ListObjectsV1Response(Headers headers, String str, String str2, ListBucketResultV1 listBucketResultV1) {
        super(headers, str, str2, null);
        this.result = listBucketResultV1;
    }

    public ListBucketResultV1 result() {
        return this.result;
    }
}
