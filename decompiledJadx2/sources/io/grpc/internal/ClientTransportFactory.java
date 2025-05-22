package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.HttpConnectProxiedSocketAddress;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ClientTransportFactory extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    ScheduledExecutorService getScheduledExecutorService();

    ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger);

    @CheckReturnValue
    @Nullable
    SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ClientTransportOptions {
        private ChannelLogger channelLogger;

        @Nullable
        private HttpConnectProxiedSocketAddress connectProxiedSocketAddr;

        @Nullable
        private String userAgent;
        private String authority = "unknown-authority";
        private Attributes eagAttributes = Attributes.EMPTY;

        public ChannelLogger getChannelLogger() {
            return this.channelLogger;
        }

        public ClientTransportOptions setChannelLogger(ChannelLogger channelLogger) {
            this.channelLogger = channelLogger;
            return this;
        }

        public String getAuthority() {
            return this.authority;
        }

        public ClientTransportOptions setAuthority(String str) {
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
            return this;
        }

        public Attributes getEagAttributes() {
            return this.eagAttributes;
        }

        public ClientTransportOptions setEagAttributes(Attributes attributes) {
            Preconditions.checkNotNull(attributes, "eagAttributes");
            this.eagAttributes = attributes;
            return this;
        }

        @Nullable
        public String getUserAgent() {
            return this.userAgent;
        }

        public ClientTransportOptions setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        @Nullable
        public HttpConnectProxiedSocketAddress getHttpConnectProxiedSocketAddress() {
            return this.connectProxiedSocketAddr;
        }

        public ClientTransportOptions setHttpConnectProxiedSocketAddress(@Nullable HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress) {
            this.connectProxiedSocketAddr = httpConnectProxiedSocketAddress;
            return this;
        }

        public int hashCode() {
            return Objects.hashCode(this.authority, this.eagAttributes, this.userAgent, this.connectProxiedSocketAddr);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ClientTransportOptions)) {
                return false;
            }
            ClientTransportOptions clientTransportOptions = (ClientTransportOptions) obj;
            return this.authority.equals(clientTransportOptions.authority) && this.eagAttributes.equals(clientTransportOptions.eagAttributes) && Objects.equal(this.userAgent, clientTransportOptions.userAgent) && Objects.equal(this.connectProxiedSocketAddr, clientTransportOptions.connectProxiedSocketAddr);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SwapChannelCredentialsResult {

        @Nullable
        final CallCredentials callCredentials;
        final ClientTransportFactory transportFactory;

        public SwapChannelCredentialsResult(ClientTransportFactory clientTransportFactory, @Nullable CallCredentials callCredentials) {
            this.transportFactory = (ClientTransportFactory) Preconditions.checkNotNull(clientTransportFactory, "transportFactory");
            this.callCredentials = callCredentials;
        }
    }
}
