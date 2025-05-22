package io.netty.channel.unix;

import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class DatagramSocketAddress extends InetSocketAddress {
    private static final long serialVersionUID = 3094819287843178401L;
    private final int receivedAmount;

    DatagramSocketAddress(String str, int i, int i2) {
        super(str, i);
        this.receivedAmount = i2;
    }

    public int receivedAmount() {
        return this.receivedAmount;
    }
}
