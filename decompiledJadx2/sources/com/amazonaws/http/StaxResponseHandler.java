package com.amazonaws.http;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.VoidStaxUnmarshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes.dex */
public class StaxResponseHandler<T> implements HttpResponseHandler<AmazonWebServiceResponse<T>> {
    private static final XmlPullParserFactory XML_PULL_PARSER_FACTORY;
    private static final Log log = LogFactory.getLog("com.amazonaws.request");
    private Unmarshaller<T, StaxUnmarshallerContext> responseUnmarshaller;

    @Override // com.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return false;
    }

    protected void registerAdditionalMetadataExpressions(StaxUnmarshallerContext staxUnmarshallerContext) {
    }

    static {
        try {
            XML_PULL_PARSER_FACTORY = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new AmazonClientException("Couldn't initialize XmlPullParserFactory", e);
        }
    }

    public StaxResponseHandler(Unmarshaller<T, StaxUnmarshallerContext> unmarshaller) {
        this.responseUnmarshaller = unmarshaller;
        if (this.responseUnmarshaller == null) {
            this.responseUnmarshaller = new VoidStaxUnmarshaller();
        }
    }

    @Override // com.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<T> handle(HttpResponse httpResponse) throws Exception {
        log.trace("Parsing service response XML");
        InputStream content = httpResponse.getContent();
        if (content == null) {
            content = new ByteArrayInputStream("<eof/>".getBytes(StringUtils.UTF8));
        }
        XmlPullParser newPullParser = XML_PULL_PARSER_FACTORY.newPullParser();
        newPullParser.setInput(content, null);
        AmazonWebServiceResponse<T> amazonWebServiceResponse = new AmazonWebServiceResponse<>();
        StaxUnmarshallerContext staxUnmarshallerContext = new StaxUnmarshallerContext(newPullParser, httpResponse.getHeaders());
        staxUnmarshallerContext.registerMetadataExpression("ResponseMetadata/RequestId", 2, ResponseMetadata.AWS_REQUEST_ID);
        staxUnmarshallerContext.registerMetadataExpression("requestId", 2, ResponseMetadata.AWS_REQUEST_ID);
        registerAdditionalMetadataExpressions(staxUnmarshallerContext);
        amazonWebServiceResponse.setResult(this.responseUnmarshaller.unmarshall(staxUnmarshallerContext));
        Map<String, String> metadata = staxUnmarshallerContext.getMetadata();
        Map<String, String> headers = httpResponse.getHeaders();
        if (headers != null && headers.get("x-amzn-RequestId") != null) {
            metadata.put(ResponseMetadata.AWS_REQUEST_ID, headers.get("x-amzn-RequestId"));
        }
        amazonWebServiceResponse.setResponseMetadata(new ResponseMetadata(metadata));
        log.trace("Done parsing service response");
        return amazonWebServiceResponse;
    }
}
