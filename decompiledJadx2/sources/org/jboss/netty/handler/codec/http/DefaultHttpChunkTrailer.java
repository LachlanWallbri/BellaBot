package org.jboss.netty.handler.codec.http;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/* loaded from: classes7.dex */
public class DefaultHttpChunkTrailer implements HttpChunkTrailer {
    private final HttpHeaders headers = new HttpHeaders() { // from class: org.jboss.netty.handler.codec.http.DefaultHttpChunkTrailer.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jboss.netty.handler.codec.http.HttpHeaders
        public void validateHeaderName(String str) {
            super.validateHeaderName(str);
            if (str.equalsIgnoreCase("Content-Length") || str.equalsIgnoreCase("Transfer-Encoding") || str.equalsIgnoreCase("Trailer")) {
                throw new IllegalArgumentException("prohibited trailing header: " + str);
            }
        }
    };

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer, org.jboss.netty.handler.codec.http.HttpChunk
    public boolean isLast() {
        return true;
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public void addHeader(String str, Object obj) {
        this.headers.addHeader(str, obj);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public void setHeader(String str, Object obj) {
        this.headers.setHeader(str, obj);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public void setHeader(String str, Iterable<?> iterable) {
        this.headers.setHeader(str, iterable);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public void removeHeader(String str) {
        this.headers.removeHeader(str);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public void clearHeaders() {
        this.headers.clearHeaders();
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public String getHeader(String str) {
        return this.headers.getHeader(str);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public List<String> getHeaders(String str) {
        return this.headers.getHeaders(str);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public List<Map.Entry<String, String>> getHeaders() {
        return this.headers.getHeaders();
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public boolean containsHeader(String str) {
        return this.headers.containsHeader(str);
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
    public Set<String> getHeaderNames() {
        return this.headers.getHeaderNames();
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunk
    public ChannelBuffer getContent() {
        return ChannelBuffers.EMPTY_BUFFER;
    }

    @Override // org.jboss.netty.handler.codec.http.HttpChunk
    public void setContent(ChannelBuffer channelBuffer) {
        throw new IllegalStateException("read-only");
    }
}
