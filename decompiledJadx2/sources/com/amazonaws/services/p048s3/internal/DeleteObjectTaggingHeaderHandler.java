package com.amazonaws.services.p048s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.model.DeleteObjectTaggingResult;

/* loaded from: classes.dex */
public class DeleteObjectTaggingHeaderHandler implements HeaderHandler<DeleteObjectTaggingResult> {
    @Override // com.amazonaws.services.p048s3.internal.HeaderHandler
    public void handle(DeleteObjectTaggingResult deleteObjectTaggingResult, HttpResponse httpResponse) {
        deleteObjectTaggingResult.setVersionId(httpResponse.getHeaders().get(Headers.S3_VERSION_ID));
    }
}
