package io.grpc.netty.shaded.io.netty.channel.unix;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DatagramSocketAddress extends InetSocketAddress {
    private static final long serialVersionUID = 3094819287843178401L;
    private final DatagramSocketAddress localAddress;
    private final int receivedAmount;

    DatagramSocketAddress(byte[] bArr, int i, int i2, int i3, DatagramSocketAddress datagramSocketAddress) throws UnknownHostException {
        super(newAddress(bArr, i), i2);
        this.receivedAmount = i3;
        this.localAddress = datagramSocketAddress;
    }

    public DatagramSocketAddress localAddress() {
        return this.localAddress;
    }

    public int receivedAmount() {
        return this.receivedAmount;
    }

    private static InetAddress newAddress(byte[] bArr, int i) throws UnknownHostException {
        if (bArr.length == 4) {
            return InetAddress.getByAddress(bArr);
        }
        return Inet6Address.getByAddress((String) null, bArr, i);
    }
}
