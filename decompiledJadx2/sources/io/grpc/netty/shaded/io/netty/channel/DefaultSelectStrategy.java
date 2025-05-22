package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.IntSupplier;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class DefaultSelectStrategy implements SelectStrategy {
    static final SelectStrategy INSTANCE = new DefaultSelectStrategy();

    private DefaultSelectStrategy() {
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.SelectStrategy
    public int calculateStrategy(IntSupplier intSupplier, boolean z) throws Exception {
        if (z) {
            return intSupplier.get();
        }
        return -1;
    }
}
