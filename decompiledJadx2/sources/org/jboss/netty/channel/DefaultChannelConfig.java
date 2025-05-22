package org.jboss.netty.channel;

import java.util.Map;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.util.internal.ConversionUtil;

/* loaded from: classes7.dex */
public class DefaultChannelConfig implements ChannelConfig {
    private volatile ChannelBufferFactory bufferFactory = HeapChannelBufferFactory.getInstance();
    private volatile int connectTimeoutMillis = 10000;

    @Override // org.jboss.netty.channel.ChannelConfig
    public ChannelPipelineFactory getPipelineFactory() {
        return null;
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setPipelineFactory(ChannelPipelineFactory channelPipelineFactory) {
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setOptions(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            setOption(entry.getKey(), entry.getValue());
        }
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public boolean setOption(String str, Object obj) {
        if (str.equals("pipelineFactory")) {
            setPipelineFactory((ChannelPipelineFactory) obj);
            return true;
        }
        if (str.equals("connectTimeoutMillis")) {
            setConnectTimeoutMillis(ConversionUtil.toInt(obj));
            return true;
        }
        if (!str.equals("bufferFactory")) {
            return false;
        }
        setBufferFactory((ChannelBufferFactory) obj);
        return true;
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public int getConnectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public ChannelBufferFactory getBufferFactory() {
        return this.bufferFactory;
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setBufferFactory(ChannelBufferFactory channelBufferFactory) {
        if (channelBufferFactory == null) {
            throw new NullPointerException("bufferFactory");
        }
        this.bufferFactory = channelBufferFactory;
    }

    @Override // org.jboss.netty.channel.ChannelConfig
    public void setConnectTimeoutMillis(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("connectTimeoutMillis: " + i);
        }
        this.connectTimeoutMillis = i;
    }
}
