package org.jboss.netty.handler.codec.http;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.compression.ZlibDecoder;
import org.jboss.netty.handler.codec.compression.ZlibWrapper;
import org.jboss.netty.handler.codec.embedder.DecoderEmbedder;

/* loaded from: classes7.dex */
public class HttpContentDecompressor extends HttpContentDecoder {
    @Override // org.jboss.netty.handler.codec.http.HttpContentDecoder
    protected DecoderEmbedder<ChannelBuffer> newContentDecoder(String str) throws Exception {
        if ("gzip".equalsIgnoreCase(str) || "x-gzip".equalsIgnoreCase(str)) {
            return new DecoderEmbedder<>(new ZlibDecoder(ZlibWrapper.GZIP));
        }
        if ("deflate".equalsIgnoreCase(str) || "x-deflate".equalsIgnoreCase(str)) {
            return new DecoderEmbedder<>(new ZlibDecoder(ZlibWrapper.ZLIB_OR_NONE));
        }
        return null;
    }
}
