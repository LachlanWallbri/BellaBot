package com.google.api.client.testing.http;

import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.util.TestableByteArrayInputStream;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class MockLowLevelHttpResponse extends LowLevelHttpResponse {
    private InputStream content;
    private String contentEncoding;
    private String contentType;
    private boolean isDisconnected;
    private String reasonPhrase;
    private int statusCode = 200;
    private List<String> headerNames = new ArrayList();
    private List<String> headerValues = new ArrayList();
    private long contentLength = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public MockLowLevelHttpResponse addHeader(String str, String str2) {
        this.headerNames.add(Preconditions.checkNotNull(str));
        this.headerValues.add(Preconditions.checkNotNull(str2));
        return this;
    }

    public MockLowLevelHttpResponse setContent(String str) {
        if (str == null) {
            return setZeroContent();
        }
        return setContent(StringUtils.getBytesUtf8(str));
    }

    public MockLowLevelHttpResponse setContent(byte[] bArr) {
        if (bArr == null) {
            return setZeroContent();
        }
        this.content = new TestableByteArrayInputStream(bArr);
        setContentLength(bArr.length);
        return this;
    }

    public MockLowLevelHttpResponse setZeroContent() {
        this.content = null;
        setContentLength(0L);
        return this;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public InputStream getContent() throws IOException {
        return this.content;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public String getContentEncoding() {
        return this.contentEncoding;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public long getContentLength() {
        return this.contentLength;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public final String getContentType() {
        return this.contentType;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public int getHeaderCount() {
        return this.headerNames.size();
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public String getHeaderName(int i) {
        return this.headerNames.get(i);
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public String getHeaderValue(int i) {
        return this.headerValues.get(i);
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public String getStatusLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.statusCode);
        String str = this.reasonPhrase;
        if (str != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public final List<String> getHeaderNames() {
        return this.headerNames;
    }

    public MockLowLevelHttpResponse setHeaderNames(List<String> list) {
        this.headerNames = (List) Preconditions.checkNotNull(list);
        return this;
    }

    public final List<String> getHeaderValues() {
        return this.headerValues;
    }

    public MockLowLevelHttpResponse setHeaderValues(List<String> list) {
        this.headerValues = (List) Preconditions.checkNotNull(list);
        return this;
    }

    public MockLowLevelHttpResponse setContent(InputStream inputStream) {
        this.content = inputStream;
        return this;
    }

    public MockLowLevelHttpResponse setContentType(String str) {
        this.contentType = str;
        return this;
    }

    public MockLowLevelHttpResponse setContentEncoding(String str) {
        this.contentEncoding = str;
        return this;
    }

    public MockLowLevelHttpResponse setContentLength(long j) {
        this.contentLength = j;
        Preconditions.checkArgument(j >= -1);
        return this;
    }

    public MockLowLevelHttpResponse setStatusCode(int i) {
        this.statusCode = i;
        return this;
    }

    public MockLowLevelHttpResponse setReasonPhrase(String str) {
        this.reasonPhrase = str;
        return this;
    }

    @Override // com.google.api.client.http.LowLevelHttpResponse
    public void disconnect() throws IOException {
        this.isDisconnected = true;
        super.disconnect();
    }

    public boolean isDisconnected() {
        return this.isDisconnected;
    }
}
