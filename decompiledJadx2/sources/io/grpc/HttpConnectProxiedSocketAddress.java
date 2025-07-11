package io.grpc;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpConnectProxiedSocketAddress extends ProxiedSocketAddress {
    private static final long serialVersionUID = 0;

    @Nullable
    private final String password;
    private final SocketAddress proxyAddress;
    private final InetSocketAddress targetAddress;

    @Nullable
    private final String username;

    private HttpConnectProxiedSocketAddress(SocketAddress socketAddress, InetSocketAddress inetSocketAddress, @Nullable String str, @Nullable String str2) {
        Preconditions.checkNotNull(socketAddress, "proxyAddress");
        Preconditions.checkNotNull(inetSocketAddress, "targetAddress");
        if (socketAddress instanceof InetSocketAddress) {
            Preconditions.checkState(!((InetSocketAddress) socketAddress).isUnresolved(), "The proxy address %s is not resolved", socketAddress);
        }
        this.proxyAddress = socketAddress;
        this.targetAddress = inetSocketAddress;
        this.username = str;
        this.password = str2;
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    @Nullable
    public String getUsername() {
        return this.username;
    }

    public SocketAddress getProxyAddress() {
        return this.proxyAddress;
    }

    public InetSocketAddress getTargetAddress() {
        return this.targetAddress;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpConnectProxiedSocketAddress)) {
            return false;
        }
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress = (HttpConnectProxiedSocketAddress) obj;
        return Objects.equal(this.proxyAddress, httpConnectProxiedSocketAddress.proxyAddress) && Objects.equal(this.targetAddress, httpConnectProxiedSocketAddress.targetAddress) && Objects.equal(this.username, httpConnectProxiedSocketAddress.username) && Objects.equal(this.password, httpConnectProxiedSocketAddress.password);
    }

    public int hashCode() {
        return Objects.hashCode(this.proxyAddress, this.targetAddress, this.username, this.password);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("proxyAddr", this.proxyAddress).add("targetAddr", this.targetAddress).add(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, this.username).add("hasPassword", this.password != null).toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {

        @Nullable
        private String password;
        private SocketAddress proxyAddress;
        private InetSocketAddress targetAddress;

        @Nullable
        private String username;

        private Builder() {
        }

        public Builder setProxyAddress(SocketAddress socketAddress) {
            this.proxyAddress = (SocketAddress) Preconditions.checkNotNull(socketAddress, "proxyAddress");
            return this;
        }

        public Builder setTargetAddress(InetSocketAddress inetSocketAddress) {
            this.targetAddress = (InetSocketAddress) Preconditions.checkNotNull(inetSocketAddress, "targetAddress");
            return this;
        }

        public Builder setUsername(@Nullable String str) {
            this.username = str;
            return this;
        }

        public Builder setPassword(@Nullable String str) {
            this.password = str;
            return this;
        }

        public HttpConnectProxiedSocketAddress build() {
            return new HttpConnectProxiedSocketAddress(this.proxyAddress, this.targetAddress, this.username, this.password);
        }
    }
}
