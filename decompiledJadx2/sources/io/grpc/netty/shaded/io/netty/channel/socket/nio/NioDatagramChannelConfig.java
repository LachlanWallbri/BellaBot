package io.grpc.netty.shaded.io.netty.channel.socket.nio;

import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SocketUtils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;
import java.util.Enumeration;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class NioDatagramChannelConfig extends DefaultDatagramChannelConfig {
    private static final Method GET_OPTION;
    private static final Object IP_MULTICAST_IF;
    private static final Object IP_MULTICAST_LOOP;
    private static final Object IP_MULTICAST_TTL;
    private static final Method SET_OPTION;
    private final DatagramChannel javaChannel;

    static {
        Class<?> cls;
        Class<?> cls2;
        Object obj;
        Object obj2;
        Object obj3;
        Class<?> cls3;
        Method declaredMethod;
        ClassLoader classLoader = PlatformDependent.getClassLoader(DatagramChannel.class);
        Method method = null;
        try {
            cls = Class.forName("java.net.SocketOption", true, classLoader);
        } catch (Exception unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("java.net.StandardSocketOptions", true, classLoader);
        } catch (Exception unused2) {
            cls2 = null;
        }
        if (cls != null) {
            try {
                obj = cls2.getDeclaredField("IP_MULTICAST_TTL").get(null);
                try {
                    obj2 = cls2.getDeclaredField("IP_MULTICAST_IF").get(null);
                    try {
                        obj3 = cls2.getDeclaredField("IP_MULTICAST_LOOP").get(null);
                        try {
                            cls3 = Class.forName("java.nio.channels.NetworkChannel", true, classLoader);
                        } catch (Throwable unused3) {
                            cls3 = null;
                        }
                        if (cls3 == null) {
                            declaredMethod = null;
                        } else {
                            try {
                                method = cls3.getDeclaredMethod("getOption", cls);
                                try {
                                    declaredMethod = cls3.getDeclaredMethod("setOption", cls, Object.class);
                                } catch (Exception e) {
                                    throw new Error("cannot locate the setOption() method", e);
                                }
                            } catch (Exception e2) {
                                throw new Error("cannot locate the getOption() method", e2);
                            }
                        }
                    } catch (Exception e3) {
                        throw new Error("cannot locate the IP_MULTICAST_LOOP field", e3);
                    }
                } catch (Exception e4) {
                    throw new Error("cannot locate the IP_MULTICAST_IF field", e4);
                }
            } catch (Exception e5) {
                throw new Error("cannot locate the IP_MULTICAST_TTL field", e5);
            }
        } else {
            declaredMethod = null;
            obj3 = null;
            obj = null;
            obj2 = null;
        }
        IP_MULTICAST_TTL = obj;
        IP_MULTICAST_IF = obj2;
        IP_MULTICAST_LOOP = obj3;
        GET_OPTION = method;
        SET_OPTION = declaredMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NioDatagramChannelConfig(NioDatagramChannel nioDatagramChannel, DatagramChannel datagramChannel) {
        super(nioDatagramChannel, datagramChannel.socket());
        this.javaChannel = datagramChannel;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public int getTimeToLive() {
        return ((Integer) getOption0(IP_MULTICAST_TTL)).intValue();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public DatagramChannelConfig setTimeToLive(int i) {
        setOption0(IP_MULTICAST_TTL, Integer.valueOf(i));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public InetAddress getInterface() {
        NetworkInterface networkInterface = getNetworkInterface();
        if (networkInterface == null) {
            return null;
        }
        Enumeration<InetAddress> addressesFromNetworkInterface = SocketUtils.addressesFromNetworkInterface(networkInterface);
        if (addressesFromNetworkInterface.hasMoreElements()) {
            return addressesFromNetworkInterface.nextElement();
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public DatagramChannelConfig setInterface(InetAddress inetAddress) {
        try {
            setNetworkInterface(NetworkInterface.getByInetAddress(inetAddress));
            return this;
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public NetworkInterface getNetworkInterface() {
        return (NetworkInterface) getOption0(IP_MULTICAST_IF);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public DatagramChannelConfig setNetworkInterface(NetworkInterface networkInterface) {
        setOption0(IP_MULTICAST_IF, networkInterface);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public boolean isLoopbackModeDisabled() {
        return ((Boolean) getOption0(IP_MULTICAST_LOOP)).booleanValue();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig
    public DatagramChannelConfig setLoopbackModeDisabled(boolean z) {
        setOption0(IP_MULTICAST_LOOP, Boolean.valueOf(z));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public DatagramChannelConfig setAutoRead(boolean z) {
        super.setAutoRead(z);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig
    protected void autoReadCleared() {
        ((NioDatagramChannel) this.channel).clearReadPending0();
    }

    private Object getOption0(Object obj) {
        Method method = GET_OPTION;
        if (method == null) {
            throw new UnsupportedOperationException();
        }
        try {
            return method.invoke(this.javaChannel, obj);
        } catch (Exception e) {
            throw new ChannelException(e);
        }
    }

    private void setOption0(Object obj, Object obj2) {
        Method method = SET_OPTION;
        if (method == null) {
            throw new UnsupportedOperationException();
        }
        try {
            method.invoke(this.javaChannel, obj, obj2);
        } catch (Exception e) {
            throw new ChannelException(e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
        if (PlatformDependent.javaVersion() >= 7 && (channelOption instanceof NioChannelOption)) {
            return NioChannelOption.setOption(this.javaChannel, (NioChannelOption) channelOption, t);
        }
        return super.setOption(channelOption, t);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public <T> T getOption(ChannelOption<T> channelOption) {
        if (PlatformDependent.javaVersion() >= 7 && (channelOption instanceof NioChannelOption)) {
            return (T) NioChannelOption.getOption(this.javaChannel, (NioChannelOption) channelOption);
        }
        return (T) super.getOption(channelOption);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DefaultDatagramChannelConfig, io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig, io.grpc.netty.shaded.io.netty.channel.ChannelConfig
    public Map<ChannelOption<?>, Object> getOptions() {
        if (PlatformDependent.javaVersion() >= 7) {
            return getOptions(super.getOptions(), NioChannelOption.getOptions(this.javaChannel));
        }
        return super.getOptions();
    }
}
