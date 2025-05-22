package org.jboss.netty.channel.socket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import org.apache.commons.io.FilenameUtils;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.DefaultChannelConfig;
import org.jboss.netty.channel.FixedReceiveBufferSizePredictorFactory;
import org.jboss.netty.channel.ReceiveBufferSizePredictor;
import org.jboss.netty.channel.ReceiveBufferSizePredictorFactory;
import org.jboss.netty.util.internal.ConversionUtil;

/* loaded from: classes7.dex */
public class DefaultDatagramChannelConfig extends DefaultChannelConfig implements DatagramChannelConfig {
    private static final ReceiveBufferSizePredictorFactory DEFAULT_PREDICTOR_FACTORY = new FixedReceiveBufferSizePredictorFactory(768);
    private volatile ReceiveBufferSizePredictor predictor;
    private volatile ReceiveBufferSizePredictorFactory predictorFactory = DEFAULT_PREDICTOR_FACTORY;
    private final DatagramSocket socket;

    public DefaultDatagramChannelConfig(DatagramSocket datagramSocket) {
        if (datagramSocket == null) {
            throw new NullPointerException("socket");
        }
        this.socket = datagramSocket;
    }

    @Override // org.jboss.netty.channel.DefaultChannelConfig, org.jboss.netty.channel.ChannelConfig
    public boolean setOption(String str, Object obj) {
        if (super.setOption(str, obj)) {
            return true;
        }
        if (str.equals("broadcast")) {
            setBroadcast(ConversionUtil.toBoolean(obj));
        } else if (str.equals("receiveBufferSize")) {
            setReceiveBufferSize(ConversionUtil.toInt(obj));
        } else if (str.equals("sendBufferSize")) {
            setSendBufferSize(ConversionUtil.toInt(obj));
        } else if (str.equals("receiveBufferSizePredictorFactory")) {
            setReceiveBufferSizePredictorFactory((ReceiveBufferSizePredictorFactory) obj);
        } else if (str.equals("receiveBufferSizePredictor")) {
            setReceiveBufferSizePredictor((ReceiveBufferSizePredictor) obj);
        } else if (str.equals("reuseAddress")) {
            setReuseAddress(ConversionUtil.toBoolean(obj));
        } else if (str.equals("loopbackModeDisabled")) {
            setLoopbackModeDisabled(ConversionUtil.toBoolean(obj));
        } else if (str.equals("interface")) {
            setInterface((InetAddress) obj);
        } else if (str.equals("networkInterface")) {
            setNetworkInterface((NetworkInterface) obj);
        } else if (str.equals("timeToLive")) {
            setTimeToLive(ConversionUtil.toInt(obj));
        } else {
            if (!str.equals("trafficClass")) {
                return false;
            }
            setTrafficClass(ConversionUtil.toInt(obj));
        }
        return true;
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public boolean isBroadcast() {
        try {
            return this.socket.getBroadcast();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setBroadcast(boolean z) {
        try {
            this.socket.setBroadcast(z);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public InetAddress getInterface() {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                return ((MulticastSocket) datagramSocket).getInterface();
            } catch (SocketException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setInterface(InetAddress inetAddress) {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                ((MulticastSocket) datagramSocket).setInterface(inetAddress);
                return;
            } catch (SocketException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public boolean isLoopbackModeDisabled() {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                return ((MulticastSocket) datagramSocket).getLoopbackMode();
            } catch (SocketException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setLoopbackModeDisabled(boolean z) {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                ((MulticastSocket) datagramSocket).setLoopbackMode(z);
                return;
            } catch (SocketException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public NetworkInterface getNetworkInterface() {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                return ((MulticastSocket) datagramSocket).getNetworkInterface();
            } catch (SocketException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setNetworkInterface(NetworkInterface networkInterface) {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                ((MulticastSocket) datagramSocket).setNetworkInterface(networkInterface);
                return;
            } catch (SocketException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public boolean isReuseAddress() {
        try {
            return this.socket.getReuseAddress();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setReuseAddress(boolean z) {
        try {
            this.socket.setReuseAddress(z);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public int getReceiveBufferSize() {
        try {
            return this.socket.getReceiveBufferSize();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setReceiveBufferSize(int i) {
        try {
            this.socket.setReceiveBufferSize(i);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public int getSendBufferSize() {
        try {
            return this.socket.getSendBufferSize();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setSendBufferSize(int i) {
        try {
            this.socket.setSendBufferSize(i);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public int getTimeToLive() {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                return ((MulticastSocket) datagramSocket).getTimeToLive();
            } catch (IOException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setTimeToLive(int i) {
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket instanceof MulticastSocket) {
            try {
                ((MulticastSocket) datagramSocket).setTimeToLive(i);
                return;
            } catch (IOException e) {
                throw new ChannelException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public int getTrafficClass() {
        try {
            return this.socket.getTrafficClass();
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setTrafficClass(int i) {
        try {
            this.socket.setTrafficClass(i);
        } catch (SocketException e) {
            throw new ChannelException(e);
        }
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
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

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setReceiveBufferSizePredictor(ReceiveBufferSizePredictor receiveBufferSizePredictor) {
        if (receiveBufferSizePredictor == null) {
            throw new NullPointerException("predictor");
        }
        this.predictor = receiveBufferSizePredictor;
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public ReceiveBufferSizePredictorFactory getReceiveBufferSizePredictorFactory() {
        return this.predictorFactory;
    }

    @Override // org.jboss.netty.channel.socket.DatagramChannelConfig
    public void setReceiveBufferSizePredictorFactory(ReceiveBufferSizePredictorFactory receiveBufferSizePredictorFactory) {
        if (receiveBufferSizePredictorFactory == null) {
            throw new NullPointerException("predictorFactory");
        }
        this.predictorFactory = receiveBufferSizePredictorFactory;
    }
}
