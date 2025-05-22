package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class HttpEncodingStreamingContent implements StreamingContent {
    private final StreamingContent content;
    private final HttpEncoding encoding;

    public HttpEncodingStreamingContent(StreamingContent streamingContent, HttpEncoding httpEncoding) {
        this.content = (StreamingContent) Preconditions.checkNotNull(streamingContent);
        this.encoding = (HttpEncoding) Preconditions.checkNotNull(httpEncoding);
    }

    @Override // com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) throws IOException {
        this.encoding.encode(this.content, outputStream);
    }

    public StreamingContent getContent() {
        return this.content;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }
}
