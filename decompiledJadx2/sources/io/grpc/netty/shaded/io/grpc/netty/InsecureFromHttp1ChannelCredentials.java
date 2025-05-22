package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.ChannelCredentials;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InsecureFromHttp1ChannelCredentials {
    private InsecureFromHttp1ChannelCredentials() {
    }

    public static ChannelCredentials create() {
        return NettyChannelCredentials.create(ProtocolNegotiators.plaintextUpgradeClientFactory());
    }
}
