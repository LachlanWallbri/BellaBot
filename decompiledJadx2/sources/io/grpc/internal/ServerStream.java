package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Decompressor;
import io.grpc.Metadata;
import io.grpc.Status;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ServerStream extends Stream {
    void cancel(Status status);

    void close(Status status, Metadata metadata);

    Attributes getAttributes();

    @Nullable
    String getAuthority();

    void setDecompressor(Decompressor decompressor);

    void setListener(ServerStreamListener serverStreamListener);

    StatsTraceContext statsTraceContext();

    int streamId();

    void writeHeaders(Metadata metadata);
}
