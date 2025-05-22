package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@CheckReturnValue
/* loaded from: classes7.dex */
public final class ProtocolNegotiationEvent {
    static final ProtocolNegotiationEvent DEFAULT = new ProtocolNegotiationEvent(Attributes.EMPTY, null);
    private final Attributes attributes;

    @Nullable
    private final InternalChannelz.Security security;

    private ProtocolNegotiationEvent(Attributes attributes, @Nullable InternalChannelz.Security security) {
        this.attributes = (Attributes) Preconditions.checkNotNull(attributes, "attributes");
        this.security = security;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public InternalChannelz.Security getSecurity() {
        return this.security;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Attributes getAttributes() {
        return this.attributes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolNegotiationEvent withAttributes(Attributes attributes) {
        return new ProtocolNegotiationEvent(attributes, this.security);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtocolNegotiationEvent withSecurity(@Nullable InternalChannelz.Security security) {
        return new ProtocolNegotiationEvent(this.attributes, security);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("attributes", this.attributes).add("security", this.security).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.attributes, this.security);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProtocolNegotiationEvent)) {
            return false;
        }
        ProtocolNegotiationEvent protocolNegotiationEvent = (ProtocolNegotiationEvent) obj;
        return Objects.equal(this.attributes, protocolNegotiationEvent.attributes) && Objects.equal(this.security, protocolNegotiationEvent.security);
    }
}
