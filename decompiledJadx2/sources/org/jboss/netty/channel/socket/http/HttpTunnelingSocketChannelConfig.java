package org.jboss.netty.channel.socket.http;

import java.util.Map;
import javax.net.ssl.SSLContext;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.SocketChannelConfig;
import org.jboss.netty.util.internal.ConversionUtil;

/* loaded from: classes7.dex */
public final class HttpTunnelingSocketChannelConfig implements SocketChannelConfig {
    private final HttpTunnelingClientSocketChannel channel;
    private volatile String[] enabledSslCipherSuites;
    private volatile String[] enabledSslProtocols;
    private volatile String serverName;
    private volatile SSLContext sslContext;
    private volatile String serverPath = "/netty-tunnel";
    private volatile boolean enableSslSessionCreation = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpTunnelingSocketChannelConfig(HttpTunnelingClientSocketChannel httpTunnelingClientSocketChannel) {
        this.channel = httpTunnelingClientSocketChannel;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public String getServerPath() {
        return this.serverPath;
    }

    public void setServerPath(String str) {
        if (str == null) {
            throw new NullPointerException("serverPath");
        }
        this.serverPath = str;
    }

    public SSLContext getSslContext() {
        return this.sslContext;
    }

    public void setSslContext(SSLContext sSLContext) {
        this.sslContext = sSLContext;
    }

    public String[] getEnabledSslCipherSuites() {
        String[] strArr = this.enabledSslCipherSuites;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setEnabledSslCipherSuites(String[] strArr) {
        if (strArr == null) {
            this.enabledSslCipherSuites = null;
        } else {
            this.enabledSslCipherSuites = (String[]) strArr.clone();
        }
    }

    public String[] getEnabledSslProtocols() {
        String[] strArr = this.enabledSslProtocols;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setEnabledSslProtocols(String[] strArr) {
        if (strArr == null) {
            this.enabledSslProtocols = null;
        } else {
            this.enabledSslProtocols = (String[]) strArr.clone();
        }
    }

    public boolean isEnableSslSessionCreation() {
        return this.enableSslSessionCreation;
    }

    public void setEnableSslSessionCreation(boolean z) {
        this.enableSslSessionCreation = z;
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setOptions(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            setOption(entry.getKey(), entry.getValue());
        }
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public boolean setOption(String str, Object obj) {
        if (this.channel.realChannel.getConfig().setOption(str, obj)) {
            return true;
        }
        if (str.equals("serverName")) {
            setServerName(String.valueOf(obj));
        } else if (str.equals("serverPath")) {
            setServerPath(String.valueOf(obj));
        } else if (str.equals("sslContext")) {
            setSslContext((SSLContext) obj);
        } else if (str.equals("enabledSslCipherSuites")) {
            setEnabledSslCipherSuites(ConversionUtil.toStringArray(obj));
        } else if (str.equals("enabledSslProtocols")) {
            setEnabledSslProtocols(ConversionUtil.toStringArray(obj));
        } else {
            if (!str.equals("enableSslSessionCreation")) {
                return false;
            }
            setEnableSslSessionCreation(ConversionUtil.toBoolean(obj));
        }
        return true;
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getReceiveBufferSize() {
        return this.channel.realChannel.getConfig().getReceiveBufferSize();
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getSendBufferSize() {
        return this.channel.realChannel.getConfig().getSendBufferSize();
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getSoLinger() {
        return this.channel.realChannel.getConfig().getSoLinger();
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public int getTrafficClass() {
        return this.channel.realChannel.getConfig().getTrafficClass();
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public boolean isKeepAlive() {
        return this.channel.realChannel.getConfig().isKeepAlive();
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public boolean isReuseAddress() {
        return this.channel.realChannel.getConfig().isReuseAddress();
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public boolean isTcpNoDelay() {
        return this.channel.realChannel.getConfig().isTcpNoDelay();
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setKeepAlive(boolean z) {
        this.channel.realChannel.getConfig().setKeepAlive(z);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setPerformancePreferences(int i, int i2, int i3) {
        this.channel.realChannel.getConfig().setPerformancePreferences(i, i2, i3);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setReceiveBufferSize(int i) {
        this.channel.realChannel.getConfig().setReceiveBufferSize(i);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setReuseAddress(boolean z) {
        this.channel.realChannel.getConfig().setReuseAddress(z);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setSendBufferSize(int i) {
        this.channel.realChannel.getConfig().setSendBufferSize(i);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setSoLinger(int i) {
        this.channel.realChannel.getConfig().setSoLinger(i);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setTcpNoDelay(boolean z) {
        this.channel.realChannel.getConfig().setTcpNoDelay(z);
    }

    @Override // org.jboss.netty.channel.socket.SocketChannelConfig
    public void setTrafficClass(int i) {
        this.channel.realChannel.getConfig().setTrafficClass(i);
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public ChannelBufferFactory getBufferFactory() {
        return this.channel.realChannel.getConfig().getBufferFactory();
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public int getConnectTimeoutMillis() {
        return this.channel.realChannel.getConfig().getConnectTimeoutMillis();
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public ChannelPipelineFactory getPipelineFactory() {
        return this.channel.realChannel.getConfig().getPipelineFactory();
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setBufferFactory(ChannelBufferFactory channelBufferFactory) {
        this.channel.realChannel.getConfig().setBufferFactory(channelBufferFactory);
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setConnectTimeoutMillis(int i) {
        this.channel.realChannel.getConfig().setConnectTimeoutMillis(i);
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setPipelineFactory(ChannelPipelineFactory channelPipelineFactory) {
        this.channel.realChannel.getConfig().setPipelineFactory(channelPipelineFactory);
    }
}
