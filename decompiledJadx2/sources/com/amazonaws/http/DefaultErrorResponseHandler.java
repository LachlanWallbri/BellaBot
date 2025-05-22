package com.amazonaws.http;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.IOUtils;
import com.amazonaws.util.XpathUtils;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public class DefaultErrorResponseHandler implements HttpResponseHandler<AmazonServiceException> {
    private static final Log log = LogFactory.getLog((Class<?>) DefaultErrorResponseHandler.class);
    private List<Unmarshaller<AmazonServiceException, Node>> unmarshallerList;

    @Override // com.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return false;
    }

    public DefaultErrorResponseHandler(List<Unmarshaller<AmazonServiceException, Node>> list) {
        this.unmarshallerList = list;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazonaws.http.HttpResponseHandler
    public AmazonServiceException handle(HttpResponse httpResponse) throws Exception {
        try {
            String iOUtils = IOUtils.toString(httpResponse.getContent());
            try {
                Document documentFrom = XpathUtils.documentFrom(iOUtils);
                Iterator<Unmarshaller<AmazonServiceException, Node>> it = this.unmarshallerList.iterator();
                while (it.hasNext()) {
                    AmazonServiceException unmarshall = it.next().unmarshall(documentFrom);
                    if (unmarshall != null) {
                        unmarshall.setStatusCode(httpResponse.getStatusCode());
                        return unmarshall;
                    }
                }
                throw new AmazonClientException("Unable to unmarshall error response from service");
            } catch (Exception e) {
                return newAmazonServiceException(String.format("Unable to unmarshall error response (%s)", iOUtils), httpResponse, e);
            }
        } catch (IOException e2) {
            if (log.isDebugEnabled()) {
                log.debug("Failed in reading the error response", e2);
            }
            return newAmazonServiceException("Unable to unmarshall error response", httpResponse, e2);
        }
    }

    private AmazonServiceException newAmazonServiceException(String str, HttpResponse httpResponse, Exception exc) {
        AmazonServiceException amazonServiceException = new AmazonServiceException(str, exc);
        int statusCode = httpResponse.getStatusCode();
        amazonServiceException.setErrorCode(statusCode + " " + httpResponse.getStatusText());
        amazonServiceException.setErrorType(AmazonServiceException.ErrorType.Unknown);
        amazonServiceException.setStatusCode(statusCode);
        return amazonServiceException;
    }
}
