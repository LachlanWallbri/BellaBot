package org.jboss.netty.channel.socket;

import java.net.ServerSocket;
import java.net.SocketException;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.DefaultServerChannelConfig;
import org.jboss.netty.util.internal.ConversionUtil;

/* loaded from: classes7.dex */
public class DefaultServerSocketChannelConfig extends DefaultServerChannelConfig implements ServerSocketChannelConfig {
    private volatile int backlog;
    private final ServerSocket socket;

    public DefaultServerSocketChannelConfig(ServerSocket serverSocket) {
        if (serverSocket == null) {
            throw new NullPointerException("socket");
        }
        this.socket = serverSocket;
    }

    @Override // org.jboss.netty.channel.DefaultServerChannelConfig, org.jboss.netty.channel.ChannelConfig
    public boolean setOption(String str, Object obj) {
        if (super.setOption(str, obj)) {
            return true;
        }
        if (str.equals("receiveBufferSize")) {
            setReceiveBufferSize(ConversionUtil.toInt(obj));
        } else if (str.equals("reuseAddress")) {
            setReuseAddress(ConversionUtil.toBoolean(obj));
        } else {
            if (!str.equals("backlog")) {
                return false;
            }
            setBacklog(ConversionUtil.toInt(obj));
        }
        return true;
    }

    @Override // org.jboss.netty.channel.socket.ServerSocketChannelConfig
    public boolean isReuseAddress() {
        try {
            return this.socket.getReuseAddress();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.ServerSocketChannelConfig
    public void setReuseAddress(boolean z) {
        try {
            this.socket.setReuseAddress(z);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.ServerSocketChannelConfig
    public int getReceiveBufferSize() {
        try {
            return this.socket.getReceiveBufferSize();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.ServerSocketChannelConfig
    public void setReceiveBufferSize(int i) {
        try {
            this.socket.setReceiveBufferSize(i);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.ServerSocketChannelConfig
    public void setPerformancePreferences(int i, int i2, int i3) {
        this.socket.setPerformancePreferences(i, i2, i3);
    }

    @Override // org.jboss.netty.channel.socket.ServerSocketChannelConfig
    public int getBacklog() {
        return this.backlog;
    }

    @Override // org.jboss.netty.channel.socket.ServerSocketChannelConfig
    public void setBacklog(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("backlog: " + i);
        }
        this.backlog = i;
    }
}
