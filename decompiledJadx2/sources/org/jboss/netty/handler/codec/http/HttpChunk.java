package org.jboss.netty.handler.codec.http;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/* loaded from: classes7.dex */
public interface HttpChunk {
    public static final HttpChunkTrailer LAST_CHUNK = new HttpChunkTrailer() { // from class: org.jboss.netty.handler.codec.http.HttpChunk.1
        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public void clearHeaders() {
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public boolean containsHeader(String str) {
            return false;
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public String getHeader(String str) {
            return null;
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer, org.jboss.netty.handler.codec.http.HttpChunk
        public boolean isLast() {
            return true;
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public void removeHeader(String str) {
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunk
        public ChannelBuffer getContent() {
            return ChannelBuffers.EMPTY_BUFFER;
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunk
        public void setContent(ChannelBuffer channelBuffer) {
            throw new IllegalStateException("read-only");
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public void addHeader(String str, Object obj) {
            throw new IllegalStateException("read-only");
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public Set<String> getHeaderNames() {
            return Collections.emptySet();
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public List<String> getHeaders(String str) {
            return Collections.emptyList();
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public List<Map.Entry<String, String>> getHeaders() {
            return Collections.emptyList();
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public void setHeader(String str, Object obj) {
            throw new IllegalStateException("read-only");
        }

        @Override // org.jboss.netty.handler.codec.http.HttpChunkTrailer
        public void setHeader(String str, Iterable<?> iterable) {
            throw new IllegalStateException("read-only");
        }
    };

    ChannelBuffer getContent();

    boolean isLast();

    void setContent(ChannelBuffer channelBuffer);
}
