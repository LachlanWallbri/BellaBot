package io.grpc.netty.shaded.io.netty.handler.proxy;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ProxyConnectionEvent {
    private final String authScheme;
    private final SocketAddress destinationAddress;
    private final String protocol;
    private final SocketAddress proxyAddress;
    private String strVal;

    public ProxyConnectionEvent(String str, String str2, SocketAddress socketAddress, SocketAddress socketAddress2) {
        this.protocol = (String) ObjectUtil.checkNotNull(str, "protocol");
        this.authScheme = (String) ObjectUtil.checkNotNull(str2, "authScheme");
        this.proxyAddress = (SocketAddress) ObjectUtil.checkNotNull(socketAddress, "proxyAddress");
        this.destinationAddress = (SocketAddress) ObjectUtil.checkNotNull(socketAddress2, "destinationAddress");
    }

    public String protocol() {
        return this.protocol;
    }

    public String authScheme() {
        return this.authScheme;
    }

    public <T extends SocketAddress> T proxyAddress() {
        return (T) this.proxyAddress;
    }

    public <T extends SocketAddress> T destinationAddress() {
        return (T) this.destinationAddress;
    }

    public String toString() {
        String str = this.strVal;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append(StringUtil.simpleClassName(this));
        sb.append('(');
        sb.append(this.protocol);
        sb.append(", ");
        sb.append(this.authScheme);
        sb.append(", ");
        sb.append(this.proxyAddress);
        sb.append(" => ");
        sb.append(this.destinationAddress);
        sb.append(')');
        String sb2 = sb.toString();
        this.strVal = sb2;
        return sb2;
    }
}
