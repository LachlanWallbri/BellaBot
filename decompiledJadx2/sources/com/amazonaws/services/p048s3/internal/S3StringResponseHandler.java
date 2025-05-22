package com.amazonaws.services.p048s3.internal;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.util.StringUtils;
import java.io.InputStream;

/* loaded from: classes.dex */
public class S3StringResponseHandler extends AbstractS3ResponseHandler<String> {
    private static final int DEFAULT_BYTE_SIZE = 1024;

    @Override // com.amazonaws.http.HttpResponseHandler
    public AmazonWebServiceResponse<String> handle(HttpResponse httpResponse) throws Exception {
        AmazonWebServiceResponse<String> parseResponseMetadata = parseResponseMetadata(httpResponse);
        byte[] bArr = new byte[1024];
        StringBuilder sb = new StringBuilder();
        InputStream content = httpResponse.getContent();
        while (true) {
            int read = content.read(bArr);
            if (read > 0) {
                sb.append(new String(bArr, 0, read, StringUtils.UTF8));
            } else {
                parseResponseMetadata.setResult(sb.toString());
                return parseResponseMetadata;
            }
        }
    }
}
