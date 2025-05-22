package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalProtocolNegotiationEvent {
    private InternalProtocolNegotiationEvent() {
    }

    public static ProtocolNegotiationEvent getDefault() {
        return ProtocolNegotiationEvent.DEFAULT;
    }

    public static ProtocolNegotiationEvent withAttributes(ProtocolNegotiationEvent protocolNegotiationEvent, Attributes attributes) {
        return protocolNegotiationEvent.withAttributes(attributes);
    }

    public static ProtocolNegotiationEvent withSecurity(ProtocolNegotiationEvent protocolNegotiationEvent, @Nullable InternalChannelz.Security security) {
        return protocolNegotiationEvent.withSecurity(security);
    }

    public static Attributes getAttributes(ProtocolNegotiationEvent protocolNegotiationEvent) {
        return protocolNegotiationEvent.getAttributes();
    }

    @Nullable
    public static InternalChannelz.Security getSecurity(ProtocolNegotiationEvent protocolNegotiationEvent) {
        return protocolNegotiationEvent.getSecurity();
    }
}
