package org.jboss.netty.channel;

import com.pudutech.bumblebee.robot_ui.config.Constans;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentMap;
import org.jboss.netty.util.internal.ConcurrentHashMap;

/* loaded from: classes7.dex */
public abstract class AbstractChannel implements Channel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final ConcurrentMap<Integer, Channel> allChannels = new ConcurrentHashMap();
    private volatile Object attachment;
    private final ChannelCloseFuture closeFuture;
    private final ChannelFactory factory;

    /* renamed from: id */
    private final Integer f10017id;
    private volatile int interestOps;
    private final Channel parent;
    private final ChannelPipeline pipeline;
    private String strVal;
    private boolean strValConnected;
    private final ChannelFuture succeededFuture;

    public final boolean equals(Object obj) {
        return this == obj;
    }

    private static Integer allocateId(Channel channel) {
        Integer valueOf = Integer.valueOf(System.identityHashCode(channel));
        while (allChannels.putIfAbsent(valueOf, channel) != null) {
            valueOf = Integer.valueOf(valueOf.intValue() + 1);
        }
        return valueOf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractChannel(Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        this.succeededFuture = new SucceededChannelFuture(this);
        this.closeFuture = new ChannelCloseFuture();
        this.interestOps = 1;
        this.parent = channel;
        this.factory = channelFactory;
        this.pipeline = channelPipeline;
        this.f10017id = allocateId(this);
        channelPipeline.attach(this, channelSink);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractChannel(Integer num, Channel channel, ChannelFactory channelFactory, ChannelPipeline channelPipeline, ChannelSink channelSink) {
        this.succeededFuture = new SucceededChannelFuture(this);
        this.closeFuture = new ChannelCloseFuture();
        this.interestOps = 1;
        this.f10017id = num;
        this.parent = channel;
        this.factory = channelFactory;
        this.pipeline = channelPipeline;
        channelPipeline.attach(this, channelSink);
    }

    @Override // org.jboss.netty.channel.Channel
    public final Integer getId() {
        return this.f10017id;
    }

    @Override // org.jboss.netty.channel.Channel
    public Channel getParent() {
        return this.parent;
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFactory getFactory() {
        return this.factory;
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelPipeline getPipeline() {
        return this.pipeline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ChannelFuture getSucceededFuture() {
        return this.succeededFuture;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ChannelFuture getUnsupportedOperationFuture() {
        return new FailedChannelFuture(this, new UnsupportedOperationException());
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Channel channel) {
        return getId().compareTo(channel.getId());
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isOpen() {
        return !this.closeFuture.isDone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean setClosed() {
        allChannels.remove(this.f10017id);
        return this.closeFuture.setClosed();
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture bind(SocketAddress socketAddress) {
        return Channels.bind(this, socketAddress);
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture unbind() {
        return Channels.unbind(this);
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture close() {
        Channels.close(this);
        return this.closeFuture;
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture getCloseFuture() {
        return this.closeFuture;
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture connect(SocketAddress socketAddress) {
        return Channels.connect(this, socketAddress);
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture disconnect() {
        return Channels.disconnect(this);
    }

    @Override // org.jboss.netty.channel.Channel
    public int getInterestOps() {
        return this.interestOps;
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture setInterestOps(int i) {
        return Channels.setInterestOps(this, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInterestOpsNow(int i) {
        this.interestOps = i;
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isReadable() {
        return (getInterestOps() & 1) != 0;
    }

    @Override // org.jboss.netty.channel.Channel
    public boolean isWritable() {
        return (getInterestOps() & 4) == 0;
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture setReadable(boolean z) {
        if (z) {
            return setInterestOps(getInterestOps() | 1);
        }
        return setInterestOps(getInterestOps() & (-2));
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj) {
        return Channels.write(this, obj);
    }

    @Override // org.jboss.netty.channel.Channel
    public ChannelFuture write(Object obj, SocketAddress socketAddress) {
        return Channels.write(this, obj, socketAddress);
    }

    @Override // org.jboss.netty.channel.Channel
    public Object getAttachment() {
        return this.attachment;
    }

    @Override // org.jboss.netty.channel.Channel
    public void setAttachment(Object obj) {
        this.attachment = obj;
    }

    public String toString() {
        String str;
        boolean isConnected = isConnected();
        if (this.strValConnected == isConnected && (str = this.strVal) != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("[id: 0x");
        sb.append(getIdString());
        SocketAddress localAddress = getLocalAddress();
        SocketAddress remoteAddress = getRemoteAddress();
        if (remoteAddress != null) {
            sb.append(", ");
            if (getParent() == null) {
                sb.append(localAddress);
                sb.append(isConnected ? " => " : " :> ");
                sb.append(remoteAddress);
            } else {
                sb.append(remoteAddress);
                sb.append(isConnected ? " => " : " :> ");
                sb.append(localAddress);
            }
        } else if (localAddress != null) {
            sb.append(", ");
            sb.append(localAddress);
        }
        sb.append(']');
        String sb2 = sb.toString();
        this.strVal = sb2;
        this.strValConnected = isConnected;
        return sb2;
    }

    private String getIdString() {
        String hexString = Integer.toHexString(this.f10017id.intValue());
        switch (hexString.length()) {
            case 0:
                return "00000000";
            case 1:
                return "0000000" + hexString;
            case 2:
                return Constans.PWD_TEST_MODE + hexString;
            case 3:
                return "00000" + hexString;
            case 4:
                return "0000" + hexString;
            case 5:
                return "000" + hexString;
            case 6:
                return "00" + hexString;
            case 7:
                return "0" + hexString;
            default:
                return hexString;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class ChannelCloseFuture extends DefaultChannelFuture {
        @Override // org.jboss.netty.channel.DefaultChannelFuture, org.jboss.netty.channel.ChannelFuture
        public boolean setFailure(Throwable th) {
            return false;
        }

        @Override // org.jboss.netty.channel.DefaultChannelFuture, org.jboss.netty.channel.ChannelFuture
        public boolean setSuccess() {
            return false;
        }

        public ChannelCloseFuture() {
            super(AbstractChannel.this, false);
        }

        boolean setClosed() {
            return super.setSuccess();
        }
    }
}
