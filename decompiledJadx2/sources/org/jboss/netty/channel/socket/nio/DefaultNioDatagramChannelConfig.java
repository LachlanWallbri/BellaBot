package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.DatagramChannel;
import java.util.Enumeration;
import java.util.Map;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.socket.DefaultDatagramChannelConfig;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.ConversionUtil;
import org.jboss.netty.util.internal.DetectionUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class DefaultNioDatagramChannelConfig extends DefaultDatagramChannelConfig implements NioDatagramChannelConfig {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultNioDatagramChannelConfig.class);
    private final DatagramChannel channel;
    private volatile int writeBufferHighWaterMark;
    private volatile int writeBufferLowWaterMark;
    private volatile int writeSpinCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultNioDatagramChannelConfig(DatagramChannel datagramChannel) {
        super(datagramChannel.socket());
        this.writeBufferHighWaterMark = 65536;
        this.writeBufferLowWaterMark = 32768;
        this.writeSpinCount = 16;
        this.channel = datagramChannel;
    }

    @Override // org.jboss.netty.channel.DefaultChannelConfig, org.jboss.netty.channel.ChannelConfig
    public void setOptions(Map<String, Object> map) {
        super.setOptions(map);
        if (getWriteBufferHighWaterMark() < getWriteBufferLowWaterMark()) {
            setWriteBufferLowWaterMark0(getWriteBufferHighWaterMark() >>> 1);
            if (logger.isWarnEnabled()) {
                logger.warn("writeBufferLowWaterMark cannot be greater than writeBufferHighWaterMark; setting to the half of the writeBufferHighWaterMark.");
            }
        }
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.DefaultChannelConfig, org.jboss.netty.channel.ChannelConfig
    public boolean setOption(String str, Object obj) {
        if (super.setOption(str, obj)) {
            return true;
        }
        if (str.equals("writeBufferHighWaterMark")) {
            setWriteBufferHighWaterMark0(ConversionUtil.toInt(obj));
        } else if (str.equals("writeBufferLowWaterMark")) {
            setWriteBufferLowWaterMark0(ConversionUtil.toInt(obj));
        } else {
            if (!str.equals("writeSpinCount")) {
                return false;
            }
            setWriteSpinCount(ConversionUtil.toInt(obj));
        }
        return true;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioChannelConfig
    public int getWriteBufferHighWaterMark() {
        return this.writeBufferHighWaterMark;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioChannelConfig
    public void setWriteBufferHighWaterMark(int i) {
        if (i < getWriteBufferLowWaterMark()) {
            throw new IllegalArgumentException("writeBufferHighWaterMark cannot be less than writeBufferLowWaterMark (" + getWriteBufferLowWaterMark() + "): " + i);
        }
        setWriteBufferHighWaterMark0(i);
    }

    private void setWriteBufferHighWaterMark0(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("writeBufferHighWaterMark: " + i);
        }
        this.writeBufferHighWaterMark = i;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioChannelConfig
    public int getWriteBufferLowWaterMark() {
        return this.writeBufferLowWaterMark;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioChannelConfig
    public void setWriteBufferLowWaterMark(int i) {
        if (i > getWriteBufferHighWaterMark()) {
            throw new IllegalArgumentException("writeBufferLowWaterMark cannot be greater than writeBufferHighWaterMark (" + getWriteBufferHighWaterMark() + "): " + i);
        }
        setWriteBufferLowWaterMark0(i);
    }

    private void setWriteBufferLowWaterMark0(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("writeBufferLowWaterMark: " + i);
        }
        this.writeBufferLowWaterMark = i;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioChannelConfig
    public int getWriteSpinCount() {
        return this.writeSpinCount;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioChannelConfig
    public void setWriteSpinCount(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("writeSpinCount must be a positive integer.");
        }
        this.writeSpinCount = i;
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setNetworkInterface(NetworkInterface networkInterface) {
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        try {
            this.channel.setOption((SocketOption<SocketOption>) StandardSocketOptions.IP_MULTICAST_IF, (SocketOption) networkInterface);
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public NetworkInterface getNetworkInterface() {
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        try {
            return (NetworkInterface) this.channel.getOption(StandardSocketOptions.IP_MULTICAST_IF);
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public int getTimeToLive() {
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        try {
            return ((Integer) this.channel.getOption(StandardSocketOptions.IP_MULTICAST_TTL)).intValue();
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setTimeToLive(int i) {
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        try {
            this.channel.setOption((SocketOption<SocketOption>) StandardSocketOptions.IP_MULTICAST_TTL, (SocketOption) Integer.valueOf(i));
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public InetAddress getInterface() {
        NetworkInterface networkInterface = getNetworkInterface();
        if (networkInterface == null) {
            return null;
        }
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        if (inetAddresses.hasMoreElements()) {
            return inetAddresses.nextElement();
        }
        return null;
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setInterface(InetAddress inetAddress) {
        try {
            setNetworkInterface(NetworkInterface.getByInetAddress(inetAddress));
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public boolean isLoopbackModeDisabled() {
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        try {
            return ((Boolean) this.channel.getOption(StandardSocketOptions.IP_MULTICAST_LOOP)).booleanValue();
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DefaultDatagramChannelConfig, org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setLoopbackModeDisabled(boolean z) {
        if (DetectionUtil.javaVersion() < 7) {
            throw new UnsupportedOperationException();
        }
        try {
            this.channel.setOption((SocketOption<SocketOption>) StandardSocketOptions.IP_MULTICAST_LOOP, (SocketOption) Boolean.valueOf(z));
        } catch (IOException e) {
            throw new ChannelException(e);
        }
    }
}
