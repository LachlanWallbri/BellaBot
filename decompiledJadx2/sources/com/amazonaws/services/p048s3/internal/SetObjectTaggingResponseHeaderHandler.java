package com.amazonaws.services.p048s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.model.SetObjectTaggingResult;

/* loaded from: classes.dex */
public class SetObjectTaggingResponseHeaderHandler implements HeaderHandler<SetObjectTaggingResult> {
    @Override // com.amazonaws.services.p048s3.internal.HeaderHandler
    public void handle(SetObjectTaggingResult setObjectTaggingResult, HttpResponse httpResponse) {
        setObjectTaggingResult.setVersionId(httpResponse.getHeaders().get(Headers.S3_VERSION_ID));
    }
}
