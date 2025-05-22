package io.grpc;

import java.io.IOException;
import java.net.SocketAddress;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ProxyDetector {
    @Nullable
    ProxiedSocketAddress proxyFor(SocketAddress socketAddress) throws IOException;
}
