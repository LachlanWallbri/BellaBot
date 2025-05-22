package io.grpc.internal;

import io.grpc.Decompressor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Deframer {
    void close();

    void closeWhenComplete();

    void deframe(ReadableBuffer readableBuffer);

    void request(int i);

    void setDecompressor(Decompressor decompressor);

    void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer);

    void setMaxInboundMessageSize(int i);
}
