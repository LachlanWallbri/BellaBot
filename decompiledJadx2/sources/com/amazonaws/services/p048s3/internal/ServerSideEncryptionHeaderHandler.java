package com.amazonaws.services.p048s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.internal.ServerSideEncryptionResult;

/* loaded from: classes.dex */
public class ServerSideEncryptionHeaderHandler<T extends ServerSideEncryptionResult> implements HeaderHandler<T> {
    @Override // com.amazonaws.services.p048s3.internal.HeaderHandler
    public void handle(T t, HttpResponse httpResponse) {
        t.setSSEAlgorithm(httpResponse.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION));
        t.setSSECustomerAlgorithm(httpResponse.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM));
        t.setSSECustomerKeyMd5(httpResponse.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5));
    }
}
