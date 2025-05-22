package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Settings;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class GrpcHttp2ConnectionHandler extends Http2ConnectionHandler {

    @Nullable
    protected final ChannelPromise channelUnused;
    private final ChannelLogger negotiationLogger;

    public void handleProtocolNegotiationCompleted(Attributes attributes, InternalChannelz.Security security) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GrpcHttp2ConnectionHandler(ChannelPromise channelPromise, Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, ChannelLogger channelLogger) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings);
        this.channelUnused = channelPromise;
        this.negotiationLogger = channelLogger;
    }

    @Deprecated
    public void handleProtocolNegotiationCompleted(Attributes attributes) {
        handleProtocolNegotiationCompleted(attributes, null);
    }

    public ChannelLogger getNegotiationLogger() {
        Preconditions.checkState(this.negotiationLogger != null, "NegotiationLogger must not be null");
        return this.negotiationLogger;
    }

    public void notifyUnused() {
        this.channelUnused.setSuccess((Void) null);
    }

    public Attributes getEagAttributes() {
        return Attributes.EMPTY;
    }

    public String getAuthority() {
        throw new UnsupportedOperationException();
    }
}
