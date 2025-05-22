package io.grpc.alts;

import io.grpc.Channel;
import io.grpc.ServerCredentials;
import io.grpc.Status;
import io.grpc.alts.AltsChannelCredentials;
import io.grpc.alts.internal.AltsProtocolNegotiator;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourcePool;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyServerCredentials;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsServerCredentials {
    private static final Logger logger = Logger.getLogger(AltsServerCredentials.class.getName());

    private AltsServerCredentials() {
    }

    public static ServerCredentials create() {
        return newBuilder().build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private boolean enableUntrustedAlts;
        private ObjectPool<Channel> handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.SHARED_HANDSHAKER_CHANNEL);

        public Builder enableUntrustedAltsForTesting() {
            this.enableUntrustedAlts = true;
            return this;
        }

        public Builder setHandshakerAddressForTesting(String str) {
            this.handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.getHandshakerChannelForTesting(str));
            return this;
        }

        public ServerCredentials build() {
            return InternalNettyServerCredentials.create(buildProtocolNegotiator());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public InternalProtocolNegotiator.ProtocolNegotiator buildProtocolNegotiator() {
            if (!InternalCheckGcpEnvironment.isOnGcp()) {
                if (this.enableUntrustedAlts) {
                    AltsServerCredentials.logger.log(Level.WARNING, "Untrusted ALTS mode is enabled and we cannot guarantee the trustworthiness of the ALTS handshaker service");
                } else {
                    return new AltsChannelCredentials.FailingProtocolNegotiator(Status.INTERNAL.withDescription("ALTS is only allowed to run on Google Cloud Platform"));
                }
            }
            return AltsProtocolNegotiator.serverAltsProtocolNegotiator(this.handshakerChannelPool);
        }
    }
}
