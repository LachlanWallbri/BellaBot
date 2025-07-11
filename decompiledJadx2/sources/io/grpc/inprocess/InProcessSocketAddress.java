package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InProcessSocketAddress extends SocketAddress {
    private static final long serialVersionUID = -2803441206326023474L;
    private final String name;

    public InProcessSocketAddress(String str) {
        this.name = (String) Preconditions.checkNotNull(str, "name");
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof InProcessSocketAddress) {
            return this.name.equals(((InProcessSocketAddress) obj).name);
        }
        return false;
    }
}
