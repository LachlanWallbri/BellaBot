package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientStream;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class CreateStreamCommand extends WriteQueue.AbstractQueuedCommand {
    private final boolean get;
    private final Http2Headers headers;
    private final boolean shouldBeCountedForInUse;
    private final NettyClientStream.TransportState stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CreateStreamCommand(Http2Headers http2Headers, NettyClientStream.TransportState transportState, boolean z, boolean z2) {
        this.stream = (NettyClientStream.TransportState) Preconditions.checkNotNull(transportState, "stream");
        this.headers = (Http2Headers) Preconditions.checkNotNull(http2Headers, "headers");
        this.shouldBeCountedForInUse = z;
        this.get = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyClientStream.TransportState stream() {
        return this.stream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Headers headers() {
        return this.headers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldBeCountedForInUse() {
        return this.shouldBeCountedForInUse;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isGet() {
        return this.get;
    }
}
