package io.netty.channel.rxtx;

import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
@Deprecated
/* loaded from: classes.dex */
public class RxtxDeviceAddress extends SocketAddress {
    private static final long serialVersionUID = -2907820090993709523L;
    private final String value;

    public RxtxDeviceAddress(String str) {
        this.value = str;
    }

    public String value() {
        return this.value;
    }
}
