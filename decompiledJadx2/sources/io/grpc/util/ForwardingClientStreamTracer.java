package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.Status;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ForwardingClientStreamTracer extends ClientStreamTracer {
    protected abstract ClientStreamTracer delegate();

    @Override // io.grpc.ClientStreamTracer
    public void outboundHeaders() {
        delegate().outboundHeaders();
    }

    @Override // io.grpc.ClientStreamTracer
    public void inboundHeaders() {
        delegate().inboundHeaders();
    }

    @Override // io.grpc.ClientStreamTracer
    public void inboundTrailers(Metadata metadata) {
        delegate().inboundTrailers(metadata);
    }

    @Override // io.grpc.StreamTracer
    public void streamClosed(Status status) {
        delegate().streamClosed(status);
    }

    @Override // io.grpc.StreamTracer
    public void outboundMessage(int i) {
        delegate().outboundMessage(i);
    }

    @Override // io.grpc.StreamTracer
    public void inboundMessage(int i) {
        delegate().inboundMessage(i);
    }

    @Override // io.grpc.StreamTracer
    public void outboundMessageSent(int i, long j, long j2) {
        delegate().outboundMessageSent(i, j, j2);
    }

    @Override // io.grpc.StreamTracer
    public void inboundMessageRead(int i, long j, long j2) {
        delegate().inboundMessageRead(i, j, j2);
    }

    @Override // io.grpc.StreamTracer
    public void outboundWireSize(long j) {
        delegate().outboundWireSize(j);
    }

    @Override // io.grpc.StreamTracer
    public void outboundUncompressedSize(long j) {
        delegate().outboundUncompressedSize(j);
    }

    @Override // io.grpc.StreamTracer
    public void inboundWireSize(long j) {
        delegate().inboundWireSize(j);
    }

    @Override // io.grpc.StreamTracer
    public void inboundUncompressedSize(long j) {
        delegate().inboundUncompressedSize(j);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
