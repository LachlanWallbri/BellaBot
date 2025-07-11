package io.netty.handler.proxy;

import io.netty.util.internal.StringUtil;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ProxyConnectionEvent {
    private final String authScheme;
    private final SocketAddress destinationAddress;
    private final String protocol;
    private final SocketAddress proxyAddress;
    private String strVal;

    public ProxyConnectionEvent(String str, String str2, SocketAddress socketAddress, SocketAddress socketAddress2) {
        if (str == null) {
            throw new NullPointerException("protocol");
        }
        if (str2 == null) {
            throw new NullPointerException("authScheme");
        }
        if (socketAddress == null) {
            throw new NullPointerException("proxyAddress");
        }
        if (socketAddress2 == null) {
            throw new NullPointerException("destinationAddress");
        }
        this.protocol = str;
        this.authScheme = str2;
        this.proxyAddress = socketAddress;
        this.destinationAddress = socketAddress2;
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
