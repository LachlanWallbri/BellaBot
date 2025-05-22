package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.ProtocolException;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class RetryableSink implements Sink {
    private boolean closed;
    private final Buffer content;
    private final int limit;

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
    }

    public RetryableSink(int i) {
        this.content = new Buffer();
        this.limit = i;
    }

    public RetryableSink() {
        this(-1);
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (this.content.size() >= this.limit) {
            return;
        }
        throw new ProtocolException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Util.checkOffsetAndCount(buffer.size(), 0L, j);
        if (this.limit != -1 && this.content.size() > this.limit - j) {
            throw new ProtocolException("exceeded content-length limit of " + this.limit + " bytes");
        }
        this.content.write(buffer, j);
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public long contentLength() throws IOException {
        return this.content.size();
    }

    public void writeToSocket(Sink sink) throws IOException {
        Buffer buffer = new Buffer();
        Buffer buffer2 = this.content;
        buffer2.copyTo(buffer, 0L, buffer2.size());
        sink.write(buffer, buffer.size());
    }
}
