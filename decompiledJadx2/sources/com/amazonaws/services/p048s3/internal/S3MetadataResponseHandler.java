package com.amazonaws.services.p048s3.internal;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.p048s3.model.ObjectMetadata;

/* loaded from: classes.dex */
public class S3MetadataResponseHandler extends AbstractS3ResponseHandler<ObjectMetadata> {
    @Override // com.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<ObjectMetadata> handle(HttpResponse httpResponse) throws Exception {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        populateObjectMetadata(httpResponse, objectMetadata);
        AmazonWebServiceResponse<ObjectMetadata> parseResponseMetadata = parseResponseMetadata(httpResponse);
        parseResponseMetadata.setResult(objectMetadata);
        return parseResponseMetadata;
    }
}
