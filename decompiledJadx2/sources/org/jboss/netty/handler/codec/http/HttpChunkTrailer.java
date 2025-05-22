package org.jboss.netty.handler.codec.http;

import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public interface HttpChunkTrailer extends HttpChunk {
    void addHeader(String str, Object obj);

    void clearHeaders();

    boolean containsHeader(String str);

    String getHeader(String str);

    Set<String> getHeaderNames();

    List<Map.Entry<String, String>> getHeaders();

    List<String> getHeaders(String str);

    @Override // org.jboss.netty.handler.codec.http.HttpChunk
    boolean isLast();

    void removeHeader(String str);

    void setHeader(String str, Iterable<?> iterable);

    void setHeader(String str, Object obj);
}
