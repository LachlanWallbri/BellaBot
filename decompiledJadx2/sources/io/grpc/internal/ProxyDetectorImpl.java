package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.ProxiedSocketAddress;
import io.grpc.ProxyDetector;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class ProxyDetectorImpl implements ProxyDetector {

    @Deprecated
    private static final String GRPC_PROXY_ENV_VAR = "GRPC_PROXY_EXP";
    static final String PROXY_SCHEME = "https";
    private final AuthenticationProvider authenticationProvider;
    private final InetSocketAddress overrideProxyAddress;
    private final Supplier<ProxySelector> proxySelector;
    private static final Logger log = Logger.getLogger(ProxyDetectorImpl.class.getName());
    private static final AuthenticationProvider DEFAULT_AUTHENTICATOR = new AuthenticationProvider() { // from class: io.grpc.internal.ProxyDetectorImpl.1
        @Override // io.grpc.internal.ProxyDetectorImpl.AuthenticationProvider
        public PasswordAuthentication requestPasswordAuthentication(String str, InetAddress inetAddress, int i, String str2, String str3, String str4) {
            URL url;
            try {
                url = new URL(str2, str, i, "");
            } catch (MalformedURLException unused) {
                ProxyDetectorImpl.log.log(Level.WARNING, String.format("failed to create URL for Authenticator: %s %s", str2, str));
                url = null;
            }
            return Authenticator.requestPasswordAuthentication(str, inetAddress, i, str2, str3, str4, url, Authenticator.RequestorType.PROXY);
        }
    };
    private static final Supplier<ProxySelector> DEFAULT_PROXY_SELECTOR = new Supplier<ProxySelector>() { // from class: io.grpc.internal.ProxyDetectorImpl.2
        @Override // com.google.common.base.Supplier, java.util.function.Supplier
        public ProxySelector get() {
            return ProxySelector.getDefault();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface AuthenticationProvider {
        PasswordAuthentication requestPasswordAuthentication(String str, InetAddress inetAddress, int i, String str2, String str3, String str4);
    }

    public ProxyDetectorImpl() {
        this(DEFAULT_PROXY_SELECTOR, DEFAULT_AUTHENTICATOR, System.getenv(GRPC_PROXY_ENV_VAR));
    }

    ProxyDetectorImpl(Supplier<ProxySelector> supplier, AuthenticationProvider authenticationProvider, @Nullable String str) {
        this.proxySelector = (Supplier) Preconditions.checkNotNull(supplier);
        this.authenticationProvider = (AuthenticationProvider) Preconditions.checkNotNull(authenticationProvider);
        if (str != null) {
            this.overrideProxyAddress = overrideProxy(str);
        } else {
            this.overrideProxyAddress = null;
        }
    }

    @Override // io.grpc.ProxyDetector
    @Nullable
    public ProxiedSocketAddress proxyFor(SocketAddress socketAddress) throws IOException {
        if (!(socketAddress instanceof InetSocketAddress)) {
            return null;
        }
        if (this.overrideProxyAddress != null) {
            return HttpConnectProxiedSocketAddress.newBuilder().setProxyAddress(this.overrideProxyAddress).setTargetAddress((InetSocketAddress) socketAddress).build();
        }
        return detectProxy((InetSocketAddress) socketAddress);
    }

    private ProxiedSocketAddress detectProxy(InetSocketAddress inetSocketAddress) throws IOException {
        try {
            try {
                URI uri = new URI(PROXY_SCHEME, null, GrpcUtil.getHost(inetSocketAddress), inetSocketAddress.getPort(), null, null, null);
                ProxySelector proxySelector = this.proxySelector.get();
                if (proxySelector == null) {
                    log.log(Level.FINE, "proxy selector is null, so continuing without proxy lookup");
                    return null;
                }
                List<Proxy> select = proxySelector.select(uri);
                if (select.size() > 1) {
                    log.warning("More than 1 proxy detected, gRPC will select the first one");
                }
                Proxy proxy = select.get(0);
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                InetSocketAddress inetSocketAddress2 = (InetSocketAddress) proxy.address();
                PasswordAuthentication requestPasswordAuthentication = this.authenticationProvider.requestPasswordAuthentication(GrpcUtil.getHost(inetSocketAddress2), inetSocketAddress2.getAddress(), inetSocketAddress2.getPort(), PROXY_SCHEME, "", null);
                if (inetSocketAddress2.isUnresolved()) {
                    inetSocketAddress2 = new InetSocketAddress(InetAddress.getByName(inetSocketAddress2.getHostName()), inetSocketAddress2.getPort());
                }
                HttpConnectProxiedSocketAddress.Builder proxyAddress = HttpConnectProxiedSocketAddress.newBuilder().setTargetAddress(inetSocketAddress).setProxyAddress(inetSocketAddress2);
                if (requestPasswordAuthentication == null) {
                    return proxyAddress.build();
                }
                return proxyAddress.setUsername(requestPasswordAuthentication.getUserName()).setPassword(requestPasswordAuthentication.getPassword() != null ? new String(requestPasswordAuthentication.getPassword()) : null).build();
            } catch (URISyntaxException e) {
                log.log(Level.WARNING, "Failed to construct URI for proxy lookup, proceeding without proxy", (Throwable) e);
                return null;
            }
        } catch (Throwable th) {
            log.log(Level.WARNING, "Failed to get host for proxy lookup, proceeding without proxy", th);
            return null;
        }
    }

    private static InetSocketAddress overrideProxy(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":", 2);
        int parseInt = split.length > 1 ? Integer.parseInt(split[1]) : 80;
        log.warning("Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
        return new InetSocketAddress(split[0], parseInt);
    }
}
