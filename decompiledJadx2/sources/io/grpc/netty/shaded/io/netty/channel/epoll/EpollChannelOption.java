package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.unix.UnixChannelOption;
import java.net.InetAddress;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class EpollChannelOption<T> extends UnixChannelOption<T> {
    public static final ChannelOption<Boolean> TCP_CORK = valueOf(EpollChannelOption.class, "TCP_CORK");
    public static final ChannelOption<Long> TCP_NOTSENT_LOWAT = valueOf(EpollChannelOption.class, "TCP_NOTSENT_LOWAT");
    public static final ChannelOption<Integer> TCP_KEEPIDLE = valueOf(EpollChannelOption.class, "TCP_KEEPIDLE");
    public static final ChannelOption<Integer> TCP_KEEPINTVL = valueOf(EpollChannelOption.class, "TCP_KEEPINTVL");
    public static final ChannelOption<Integer> TCP_KEEPCNT = valueOf(EpollChannelOption.class, "TCP_KEEPCNT");
    public static final ChannelOption<Integer> TCP_USER_TIMEOUT = valueOf(EpollChannelOption.class, "TCP_USER_TIMEOUT");
    public static final ChannelOption<Boolean> IP_FREEBIND = valueOf("IP_FREEBIND");
    public static final ChannelOption<Boolean> IP_TRANSPARENT = valueOf("IP_TRANSPARENT");
    public static final ChannelOption<Boolean> IP_RECVORIGDSTADDR = valueOf("IP_RECVORIGDSTADDR");
    public static final ChannelOption<Integer> TCP_FASTOPEN = valueOf(EpollChannelOption.class, "TCP_FASTOPEN");
    public static final ChannelOption<Boolean> TCP_FASTOPEN_CONNECT = valueOf(EpollChannelOption.class, "TCP_FASTOPEN_CONNECT");
    public static final ChannelOption<Integer> TCP_DEFER_ACCEPT = ChannelOption.valueOf(EpollChannelOption.class, "TCP_DEFER_ACCEPT");
    public static final ChannelOption<Boolean> TCP_QUICKACK = valueOf(EpollChannelOption.class, "TCP_QUICKACK");
    public static final ChannelOption<Integer> SO_BUSY_POLL = valueOf(EpollChannelOption.class, "SO_BUSY_POLL");
    public static final ChannelOption<EpollMode> EPOLL_MODE = ChannelOption.valueOf(EpollChannelOption.class, "EPOLL_MODE");
    public static final ChannelOption<Map<InetAddress, byte[]>> TCP_MD5SIG = valueOf("TCP_MD5SIG");
    public static final ChannelOption<Integer> MAX_DATAGRAM_PAYLOAD_SIZE = valueOf("MAX_DATAGRAM_PAYLOAD_SIZE");

    private EpollChannelOption() {
    }
}
