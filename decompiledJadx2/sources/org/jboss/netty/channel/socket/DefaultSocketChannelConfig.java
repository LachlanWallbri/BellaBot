package org.jboss.netty.channel.socket;

import java.net.Socket;
import java.net.SocketException;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.DefaultChannelConfig;
import org.jboss.netty.util.internal.ConversionUtil;

/* loaded from: classes7.dex */
public class DefaultSocketChannelConfig extends DefaultChannelConfig implements SocketChannelConfig {
    private final Socket socket;

    public DefaultSocketChannelConfig(Socket socket) {
        if (socket == null) {
            throw new NullPointerException("socket");
        }
        this.socket = socket;
    }

    @Override // org.jboss.netty.channel.DefaultChannelConfig, org.jboss.netty.channel.ChannelConfig
    public boolean setOption(String str, Object obj) {
        if (super.setOption(str, obj)) {
            return true;
        }
        if (str.equals("receiveBufferSize")) {
            setReceiveBufferSize(ConversionUtil.toInt(obj));
        } else if (str.equals("sendBufferSize")) {
            setSendBufferSize(ConversionUtil.toInt(obj));
        } else if (str.equals("tcpNoDelay")) {
            setTcpNoDelay(ConversionUtil.toBoolean(obj));
        } else if (str.equals("keepAlive")) {
            setKeepAlive(ConversionUtil.toBoolean(obj));
        } else if (str.equals("reuseAddress")) {
            setReuseAddress(ConversionUtil.toBoolean(obj));
        } else if (str.equals("soLinger")) {
            setSoLinger(ConversionUtil.toInt(obj));
        } else {
            if (!str.equals("trafficClass")) {
                return false;
            }
            setTrafficClass(ConversionUtil.toInt(obj));
        }
        return true;
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getReceiveBufferSize() {
        try {
            return this.socket.getReceiveBufferSize();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getSendBufferSize() {
        try {
            return this.socket.getSendBufferSize();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getSoLinger() {
        try {
            return this.socket.getSoLinger();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getTrafficClass() {
        try {
            return this.socket.getTrafficClass();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public boolean isKeepAlive() {
        try {
            return this.socket.getKeepAlive();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public boolean isReuseAddress() {
        try {
            return this.socket.getReuseAddress();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public boolean isTcpNoDelay() {
        try {
            return this.socket.getTcpNoDelay();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setKeepAlive(boolean z) {
        try {
            this.socket.setKeepAlive(z);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setPerformancePreferences(int i, int i2, int i3) {
        this.socket.setPerformancePreferences(i, i2, i3);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setReceiveBufferSize(int i) {
        try {
            this.socket.setReceiveBufferSize(i);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setReuseAddress(boolean z) {
        try {
            this.socket.setReuseAddress(z);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setSendBufferSize(int i) {
        try {
            this.socket.setSendBufferSize(i);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setSoLinger(int i) {
        try {
            if (i < 0) {
                this.socket.setSoLinger(false, 0);
            } else {
                this.socket.setSoLinger(true, i);
            }
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setTcpNoDelay(boolean z) {
        try {
            this.socket.setTcpNoDelay(z);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setTrafficClass(int i) {
        try {
            this.socket.setTrafficClass(i);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }
}
