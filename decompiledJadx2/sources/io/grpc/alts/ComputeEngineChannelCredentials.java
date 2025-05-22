package io.grpc.alts;

import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.common.collect.ImmutableList;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.CompositeChannelCredentials;
import io.grpc.Status;
import io.grpc.alts.internal.AltsProtocolNegotiator;
import io.grpc.auth.MoreCallCredentials;
import io.grpc.internal.SharedResourcePool;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelCredentials;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import javax.net.ssl.SSLException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ComputeEngineChannelCredentials {
    private ComputeEngineChannelCredentials() {
    }

    public static ChannelCredentials create() {
        CallCredentials failingCallCredentials;
        ChannelCredentials create = InternalNettyChannelCredentials.create(createClientFactory());
        if (InternalCheckGcpEnvironment.isOnGcp()) {
            failingCallCredentials = MoreCallCredentials.from(ComputeEngineCredentials.create());
        } else {
            failingCallCredentials = new FailingCallCredentials(Status.INTERNAL.withDescription("Compute Engine Credentials can only be used on Google Cloud Platform"));
        }
        return CompositeChannelCredentials.create(create, failingCallCredentials);
    }

    private static InternalProtocolNegotiator.ClientFactory createClientFactory() {
        try {
            return new AltsProtocolNegotiator.GoogleDefaultProtocolNegotiatorFactory(ImmutableList.m634of(), SharedResourcePool.forResource(HandshakerServiceChannel.SHARED_HANDSHAKER_CHANNEL), GrpcSslContexts.forClient().build());
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }
}
