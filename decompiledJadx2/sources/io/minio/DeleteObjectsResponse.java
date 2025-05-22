package io.minio;

import io.minio.messages.DeleteResult;
import okhttp3.Headers;

/* loaded from: classes7.dex */
public class DeleteObjectsResponse extends GenericResponse {
    private DeleteResult result;

    public DeleteObjectsResponse(Headers headers, String str, String str2, DeleteResult deleteResult) {
        super(headers, str, str2, null);
        this.result = deleteResult;
    }

    public DeleteResult result() {
        return this.result;
    }
}
