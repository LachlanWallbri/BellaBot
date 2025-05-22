package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class SendResponseHeadersCommand extends WriteQueue.AbstractQueuedCommand {
    private final Http2Headers headers;
    private final Status status;
    private final StreamIdHolder stream;

    private SendResponseHeadersCommand(StreamIdHolder streamIdHolder, Http2Headers http2Headers, Status status) {
        this.stream = (StreamIdHolder) Preconditions.checkNotNull(streamIdHolder, "stream");
        this.headers = (Http2Headers) Preconditions.checkNotNull(http2Headers, "headers");
        this.status = status;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SendResponseHeadersCommand createHeaders(StreamIdHolder streamIdHolder, Http2Headers http2Headers) {
        return new SendResponseHeadersCommand(streamIdHolder, http2Headers, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SendResponseHeadersCommand createTrailers(StreamIdHolder streamIdHolder, Http2Headers http2Headers, Status status) {
        return new SendResponseHeadersCommand(streamIdHolder, http2Headers, (Status) Preconditions.checkNotNull(status, "status"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamIdHolder stream() {
        return this.stream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Headers headers() {
        return this.headers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean endOfStream() {
        return this.status != null;
    }

    Status status() {
        return this.status;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(SendResponseHeadersCommand.class)) {
            return false;
        }
        SendResponseHeadersCommand sendResponseHeadersCommand = (SendResponseHeadersCommand) obj;
        return sendResponseHeadersCommand.stream.equals(this.stream) && sendResponseHeadersCommand.headers.equals(this.headers) && sendResponseHeadersCommand.status.equals(this.status);
    }

    public String toString() {
        return getClass().getSimpleName() + "(stream=" + this.stream.mo3906id() + ", headers=" + this.headers + ", status=" + this.status + ")";
    }

    public int hashCode() {
        return Objects.hashCode(this.stream, this.status);
    }
}
