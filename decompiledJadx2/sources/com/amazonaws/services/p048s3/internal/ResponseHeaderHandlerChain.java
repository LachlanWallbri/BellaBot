package com.amazonaws.services.p048s3.internal;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.transform.Unmarshaller;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ResponseHeaderHandlerChain<T> extends S3XmlResponseHandler<T> {
    private final List<HeaderHandler<T>> headerHandlers;

    public ResponseHeaderHandlerChain(Unmarshaller<T, InputStream> unmarshaller, HeaderHandler<T>... headerHandlerArr) {
        super(unmarshaller);
        this.headerHandlers = Arrays.asList(headerHandlerArr);
    }

    @Override // com.amazonaws.services.p048s3.internal.S3XmlResponseHandler, com.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<T> handle(HttpResponse httpResponse) throws Exception {
        AmazonWebServiceResponse<T> handle = super.handle(httpResponse);
        T result = handle.getResult();
        if (result != null) {
            Iterator<HeaderHandler<T>> it = this.headerHandlers.iterator();
            while (it.hasNext()) {
                it.next().handle(result, httpResponse);
            }
        }
        return handle;
    }
}
