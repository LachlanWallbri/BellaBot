package io.grpc;

import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CompositeChannelCredentials extends ChannelCredentials {
    private final CallCredentials callCredentials;
    private final ChannelCredentials channelCredentials;

    public static ChannelCredentials create(ChannelCredentials channelCredentials, CallCredentials callCredentials) {
        return new CompositeChannelCredentials(channelCredentials, callCredentials);
    }

    private CompositeChannelCredentials(ChannelCredentials channelCredentials, CallCredentials callCredentials) {
        this.channelCredentials = (ChannelCredentials) Preconditions.checkNotNull(channelCredentials, "channelCreds");
        this.callCredentials = (CallCredentials) Preconditions.checkNotNull(callCredentials, "callCreds");
    }

    public ChannelCredentials getChannelCredentials() {
        return this.channelCredentials;
    }

    public CallCredentials getCallCredentials() {
        return this.callCredentials;
    }

    @Override // io.grpc.ChannelCredentials
    public ChannelCredentials withoutBearerTokens() {
        return this.channelCredentials.withoutBearerTokens();
    }
}
