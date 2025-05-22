package io.minio;

import io.minio.messages.ListBucketResultV2;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class ListObjectsV2Response extends GenericResponse {
    private ListBucketResultV2 result;

    public ListObjectsV2Response(Headers headers, String str, String str2, ListBucketResultV2 listBucketResultV2) {
        super(headers, str, str2, null);
        this.result = listBucketResultV2;
    }

    public ListBucketResultV2 result() {
        return this.result;
    }
}
