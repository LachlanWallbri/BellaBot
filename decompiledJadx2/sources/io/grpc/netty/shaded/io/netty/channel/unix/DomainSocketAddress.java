package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.File;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DomainSocketAddress extends SocketAddress {
    private static final long serialVersionUID = -6934618000832236893L;
    private final String socketPath;

    public DomainSocketAddress(String str) {
        this.socketPath = (String) ObjectUtil.checkNotNull(str, "socketPath");
    }

    public DomainSocketAddress(File file) {
        this(file.getPath());
    }

    public String path() {
        return this.socketPath;
    }

    public String toString() {
        return path();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DomainSocketAddress) {
            return ((DomainSocketAddress) obj).socketPath.equals(this.socketPath);
        }
        return false;
    }

    public int hashCode() {
        return this.socketPath.hashCode();
    }
}
