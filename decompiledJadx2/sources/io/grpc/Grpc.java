package io.grpc;

import io.grpc.Attributes;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Grpc {
    public static final Attributes.Key<SocketAddress> TRANSPORT_ATTR_REMOTE_ADDR = Attributes.Key.create("remote-addr");
    public static final Attributes.Key<SocketAddress> TRANSPORT_ATTR_LOCAL_ADDR = Attributes.Key.create("local-addr");
    public static final Attributes.Key<SSLSession> TRANSPORT_ATTR_SSL_SESSION = Attributes.Key.create("ssl-session");

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface TransportAttr {
    }

    private Grpc() {
    }

    public static ManagedChannelBuilder<?> newChannelBuilder(String str, ChannelCredentials channelCredentials) {
        return ManagedChannelRegistry.getDefaultRegistry().newChannelBuilder(str, channelCredentials);
    }

    public static ManagedChannelBuilder<?> newChannelBuilderForAddress(String str, int i, ChannelCredentials channelCredentials) {
        return newChannelBuilder(authorityFromHostAndPort(str, i), channelCredentials);
    }

    private static String authorityFromHostAndPort(String str, int i) {
        try {
            return new URI(null, null, str, i, null, null, null).getAuthority();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid host or port: " + str + " " + i, e);
        }
    }

    public static ServerBuilder<?> newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        return ServerRegistry.getDefaultRegistry().newServerBuilderForPort(i, serverCredentials);
    }
}
