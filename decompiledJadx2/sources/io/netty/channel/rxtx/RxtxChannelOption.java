package io.netty.channel.rxtx;

import com.pudutech.pd_network.interceptor.TimeoutInterceptor;
import io.netty.channel.ChannelOption;
import io.netty.channel.rxtx.RxtxChannelConfig;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
@Deprecated
/* loaded from: classes.dex */
public final class RxtxChannelOption<T> extends ChannelOption<T> {
    public static final ChannelOption<Integer> BAUD_RATE = valueOf(RxtxChannelOption.class, "BAUD_RATE");
    public static final ChannelOption<Boolean> DTR = valueOf(RxtxChannelOption.class, "DTR");
    public static final ChannelOption<Boolean> RTS = valueOf(RxtxChannelOption.class, "RTS");
    public static final ChannelOption<RxtxChannelConfig.Stopbits> STOP_BITS = valueOf(RxtxChannelOption.class, "STOP_BITS");
    public static final ChannelOption<RxtxChannelConfig.Databits> DATA_BITS = valueOf(RxtxChannelOption.class, "DATA_BITS");
    public static final ChannelOption<RxtxChannelConfig.Paritybit> PARITY_BIT = valueOf(RxtxChannelOption.class, "PARITY_BIT");
    public static final ChannelOption<Integer> WAIT_TIME = valueOf(RxtxChannelOption.class, "WAIT_TIME");
    public static final ChannelOption<Integer> READ_TIMEOUT = valueOf(RxtxChannelOption.class, TimeoutInterceptor.READ_TIMEOUT);

    private RxtxChannelOption() {
        super(null);
    }
}
