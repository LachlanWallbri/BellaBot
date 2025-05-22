package com.amazonaws;

import com.amazonaws.http.HttpResponse;

/* loaded from: classes.dex */
public final class Response<T> {
    private final HttpResponse httpResponse;
    private final T response;

    public Response(T t, HttpResponse httpResponse) {
        this.response = t;
        this.httpResponse = httpResponse;
    }

    public T getAwsResponse() {
        return this.response;
    }

    public HttpResponse getHttpResponse() {
        return this.httpResponse;
    }
}
