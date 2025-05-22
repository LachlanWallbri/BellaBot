package io.grpc.netty.shaded.io.grpc.netty;

import java.security.Provider;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class UnhelpfulSecurityProvider extends Provider {
    private static final long serialVersionUID = 0;

    public UnhelpfulSecurityProvider() {
        super("UnhelpfulSecurityProvider", 0.0d, "A Provider that provides nothing");
    }
}
