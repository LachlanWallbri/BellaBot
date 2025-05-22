package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.OkHostnameVerifier;
import io.grpc.okhttp.internal.Protocol;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class OkHttpTlsUpgrader {
    static final List<Protocol> TLS_PROTOCOLS = Collections.unmodifiableList(Arrays.asList(Protocol.HTTP_2));

    OkHttpTlsUpgrader() {
    }

    public static SSLSocket upgrade(SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, Socket socket, String str, int i, ConnectionSpec connectionSpec) throws IOException {
        Preconditions.checkNotNull(sSLSocketFactory, "sslSocketFactory");
        Preconditions.checkNotNull(socket, "socket");
        Preconditions.checkNotNull(connectionSpec, "spec");
        SSLSocket sSLSocket = (SSLSocket) sSLSocketFactory.createSocket(socket, str, i, true);
        connectionSpec.apply(sSLSocket, false);
        String negotiate = OkHttpProtocolNegotiator.get().negotiate(sSLSocket, str, connectionSpec.supportsTlsExtensions() ? TLS_PROTOCOLS : null);
        Preconditions.checkState(TLS_PROTOCOLS.contains(Protocol.get(negotiate)), "Only " + TLS_PROTOCOLS + " are supported, but negotiated protocol is %s", negotiate);
        if (hostnameVerifier == null) {
            hostnameVerifier = OkHostnameVerifier.INSTANCE;
        }
        if (hostnameVerifier.verify(canonicalizeHost(str), sSLSocket.getSession())) {
            return sSLSocket;
        }
        throw new SSLPeerUnverifiedException("Cannot verify hostname: " + str);
    }

    static String canonicalizeHost(String str) {
        return (str.startsWith("[") && str.endsWith("]")) ? str.substring(1, str.length() - 1) : str;
    }
}
