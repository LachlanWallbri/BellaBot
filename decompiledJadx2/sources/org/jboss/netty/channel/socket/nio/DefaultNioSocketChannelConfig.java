package org.jboss.netty.channel.socket.nio;

import java.net.Socket;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictorFactory;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ReceiveBufferSizePredictor;
import org.jboss.netty.channel.ReceiveBufferSizePredictorFactory;
import org.jboss.netty.channel.socket.DefaultSocketChannelConfig;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.ConversionUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class DefaultNioSocketChannelConfig extends DefaultSocketChannelConfig implements NioSocketChannelConfig {
    private volatile ReceiveBufferSizePredictor predictor;
    private volatile ReceiveBufferSizePredictorFactory predictorFactory;
    private volatile int writeBufferHighWaterMark;
    private volatile int writeBufferLowWaterMark;
    private volatile int writeSpinCount;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultNioSocketChannelConfig.class);
    private static final ReceiveBufferSizePredictorFactory DEFAULT_PREDICTOR_FACTORY = new AdaptiveReceiveBufferSizePredictorFactory();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultNioSocketChannelConfig(Socket socket) {
        super(socket);
        this.writeBufferHighWaterMark = 65536;
        this.writeBufferLowWaterMark = 32768;
        this.predictorFactory = DEFAULT_PREDICTOR_FACTORY;
        this.writeSpinCount = 16;
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

    @Override // org.jboss.netty.channel.socket.DefaultSocketChannelConfig, org.jboss.netty.channel.DefaultChannelConfig, org.jboss.netty.channel.ChannelConfig
    public boolean setOption(String str, Object obj) {
        if (super.setOption(str, obj)) {
            return true;
        }
        if (str.equals("writeBufferHighWaterMark")) {
            setWriteBufferHighWaterMark0(ConversionUtil.toInt(obj));
        } else if (str.equals("writeBufferLowWaterMark")) {
            setWriteBufferLowWaterMark0(ConversionUtil.toInt(obj));
        } else if (str.equals("writeSpinCount")) {
            setWriteSpinCount(ConversionUtil.toInt(obj));
        } else if (str.equals("receiveBufferSizePredictorFactory")) {
            setReceiveBufferSizePredictorFactory((ReceiveBufferSizePredictorFactory) obj);
        } else {
            if (!str.equals("receiveBufferSizePredictor")) {
                return false;
            }
            setReceiveBufferSizePredictor((ReceiveBufferSizePredictor) obj);
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

    @Override // org.jboss.netty.channel.socket.nio.NioSocketChannelConfig
    public ReceiveBufferSizePredictor getReceiveBufferSizePredictor() {
        ReceiveBufferSizePredictor receiveBufferSizePredictor = this.predictor;
        if (receiveBufferSizePredictor != null) {
            return receiveBufferSizePredictor;
        }
        try {
            ReceiveBufferSizePredictor predictor = getReceiveBufferSizePredictorFactory().getPredictor();
            this.predictor = predictor;
            return predictor;
        } catch (Exception e) {
            throw new ChannelException("Failed to create a new " + ReceiveBufferSizePredictor.class.getSimpleName() + FilenameUtils.EXTENSION_SEPARATOR, e);
        }
    }

    @Override // org.jboss.netty.channel.socket.nio.NioSocketChannelConfig
    public void setReceiveBufferSizePredictor(ReceiveBufferSizePredictor receiveBufferSizePredictor) {
        if (receiveBufferSizePredictor == null) {
            throw new NullPointerException("predictor");
        }
        this.predictor = receiveBufferSizePredictor;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioSocketChannelConfig
    public ReceiveBufferSizePredictorFactory getReceiveBufferSizePredictorFactory() {
        return this.predictorFactory;
    }

    @Override // org.jboss.netty.channel.socket.nio.NioSocketChannelConfig
    public void setReceiveBufferSizePredictorFactory(ReceiveBufferSizePredictorFactory receiveBufferSizePredictorFactory) {
        if (receiveBufferSizePredictorFactory == null) {
            throw new NullPointerException("predictorFactory");
        }
        this.predictorFactory = receiveBufferSizePredictorFactory;
    }
}
