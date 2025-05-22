package org.jboss.netty.handler.stream;

/* loaded from: classes7.dex */
public interface ChunkedInput {
    void close() throws Exception;

    boolean hasNextChunk() throws Exception;

    boolean isEndOfInput() throws Exception;

    Object nextChunk() throws Exception;
}
