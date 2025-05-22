package com.amazonaws.services.p048s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.internal.S3RequesterChargedResult;

/* loaded from: classes.dex */
public class S3RequesterChargedHeaderHandler<T extends S3RequesterChargedResult> implements HeaderHandler<T> {
    @Override // com.amazonaws.services.p048s3.internal.HeaderHandler
    public void handle(T t, HttpResponse httpResponse) {
        t.setRequesterCharged(httpResponse.getHeaders().get(Headers.REQUESTER_CHARGED_HEADER) != null);
    }
}
