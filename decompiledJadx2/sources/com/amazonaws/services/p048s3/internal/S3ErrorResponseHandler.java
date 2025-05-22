package com.amazonaws.services.p048s3.internal;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.p048s3.Headers;
import com.amazonaws.services.p048s3.model.AmazonS3Exception;
import com.amazonaws.util.IOUtils;
import com.amazonaws.util.XpathUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;

/* loaded from: classes.dex */
public class S3ErrorResponseHandler implements HttpResponseHandler<AmazonServiceException> {
    private static final int HTTP_INTERNAL_FAILURE_STATUS_CODE = 500;
    private static final Log log = LogFactory.getLog((Class<?>) S3ErrorResponseHandler.class);

    @Override // com.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazonaws.http.HttpResponseHandler
    public AmazonServiceException handle(HttpResponse httpResponse) throws IOException {
        InputStream content = httpResponse.getContent();
        if (content == null) {
            return newAmazonS3Exception(httpResponse.getStatusText(), httpResponse);
        }
        try {
            String iOUtils = IOUtils.toString(content);
            try {
                Document documentFrom = XpathUtils.documentFrom(iOUtils);
                String asString = XpathUtils.asString("Error/Message", documentFrom);
                String asString2 = XpathUtils.asString("Error/Code", documentFrom);
                String asString3 = XpathUtils.asString("Error/RequestId", documentFrom);
                String asString4 = XpathUtils.asString("Error/HostId", documentFrom);
                AmazonS3Exception amazonS3Exception = new AmazonS3Exception(asString);
                int statusCode = httpResponse.getStatusCode();
                amazonS3Exception.setStatusCode(statusCode);
                amazonS3Exception.setErrorType(errorTypeOf(statusCode));
                amazonS3Exception.setErrorCode(asString2);
                amazonS3Exception.setRequestId(asString3);
                amazonS3Exception.setExtendedRequestId(asString4);
                amazonS3Exception.setCloudFrontId(httpResponse.getHeaders().get(Headers.CLOUD_FRONT_ID));
                return amazonS3Exception;
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.debug("Failed in parsing the response as XML: " + iOUtils, e);
                }
                return newAmazonS3Exception(iOUtils, httpResponse);
            }
        } catch (IOException e2) {
            if (log.isDebugEnabled()) {
                log.debug("Failed in reading the error response", e2);
            }
            return newAmazonS3Exception(httpResponse.getStatusText(), httpResponse);
        }
    }

    private AmazonS3Exception newAmazonS3Exception(String str, HttpResponse httpResponse) {
        AmazonS3Exception amazonS3Exception = new AmazonS3Exception(str);
        int statusCode = httpResponse.getStatusCode();
        amazonS3Exception.setErrorCode(statusCode + " " + httpResponse.getStatusText());
        amazonS3Exception.setStatusCode(statusCode);
        amazonS3Exception.setErrorType(errorTypeOf(statusCode));
        Map<String, String> headers = httpResponse.getHeaders();
        amazonS3Exception.setRequestId(headers.get(Headers.REQUEST_ID));
        amazonS3Exception.setExtendedRequestId(headers.get(Headers.EXTENDED_REQUEST_ID));
        amazonS3Exception.setCloudFrontId(headers.get(Headers.CLOUD_FRONT_ID));
        HashMap hashMap = new HashMap();
        hashMap.put(Headers.S3_BUCKET_REGION, headers.get(Headers.S3_BUCKET_REGION));
        amazonS3Exception.setAdditionalDetails(hashMap);
        return amazonS3Exception;
    }

    private AmazonServiceException.ErrorType errorTypeOf(int i) {
        return i >= 500 ? AmazonServiceException.ErrorType.Service : AmazonServiceException.ErrorType.Client;
    }
}
