package org.jboss.netty.handler.codec.spdy;

import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/* loaded from: classes7.dex */
public class SpdySessionHandler extends SimpleChannelUpstreamHandler implements ChannelDownstreamHandler {
    private static final int DEFAULT_WINDOW_SIZE = 65536;
    private static final SpdyProtocolException PROTOCOL_EXCEPTION = new SpdyProtocolException();
    private volatile ChannelFuture closeSessionFuture;
    private final boolean flowControl;
    private final Object flowControlLock;
    private volatile int initialReceiveWindowSize;
    private volatile int initialSendWindowSize;
    private volatile int lastGoodStreamID;
    private volatile int localConcurrentStreams;
    private volatile int maxConcurrentStreams;
    private final AtomicInteger pings;
    private volatile boolean receivedGoAwayFrame;
    private volatile int remoteConcurrentStreams;
    private volatile boolean sentGoAwayFrame;
    private final boolean server;
    private final SpdySession spdySession;

    @Deprecated
    public SpdySessionHandler(boolean z) {
        this(2, z);
    }

    public SpdySessionHandler(int i, boolean z) {
        this.spdySession = new SpdySession();
        this.initialSendWindowSize = 65536;
        this.initialReceiveWindowSize = 65536;
        this.flowControlLock = new Object();
        this.pings = new AtomicInteger();
        if (i < 2 || i > 3) {
            throw new IllegalArgumentException("unsupported version: " + i);
        }
        this.server = z;
        this.flowControl = i >= 3;
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        int value;
        Object message = messageEvent.getMessage();
        if (message instanceof SpdyDataFrame) {
            SpdyDataFrame spdyDataFrame = (SpdyDataFrame) message;
            int streamId = spdyDataFrame.getStreamId();
            if (!this.spdySession.isActiveStream(streamId)) {
                if (streamId <= this.lastGoodStreamID) {
                    issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId, SpdyStreamStatus.PROTOCOL_ERROR);
                    return;
                } else {
                    if (this.sentGoAwayFrame) {
                        return;
                    }
                    issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId, SpdyStreamStatus.INVALID_STREAM);
                    return;
                }
            }
            if (this.spdySession.isRemoteSideClosed(streamId)) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId, SpdyStreamStatus.STREAM_ALREADY_CLOSED);
                return;
            }
            if (!isRemoteInitiatedID(streamId) && !this.spdySession.hasReceivedReply(streamId)) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId, SpdyStreamStatus.PROTOCOL_ERROR);
                return;
            }
            if (this.flowControl) {
                int updateReceiveWindowSize = this.spdySession.updateReceiveWindowSize(streamId, spdyDataFrame.getData().readableBytes() * (-1));
                if (updateReceiveWindowSize < this.spdySession.getReceiveWindowSizeLowerBound(streamId)) {
                    issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId, SpdyStreamStatus.FLOW_CONTROL_ERROR);
                    return;
                }
                if (updateReceiveWindowSize < 0) {
                    while (spdyDataFrame.getData().readableBytes() > this.initialReceiveWindowSize) {
                        DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(streamId);
                        defaultSpdyDataFrame.setData(spdyDataFrame.getData().readSlice(this.initialReceiveWindowSize));
                        Channels.fireMessageReceived(channelHandlerContext, defaultSpdyDataFrame, messageEvent.getRemoteAddress());
                    }
                }
                if (updateReceiveWindowSize <= this.initialReceiveWindowSize / 2 && !spdyDataFrame.isLast()) {
                    int i = this.initialReceiveWindowSize - updateReceiveWindowSize;
                    this.spdySession.updateReceiveWindowSize(streamId, i);
                    Channels.write(channelHandlerContext, Channels.future(messageEvent.getChannel()), new DefaultSpdyWindowUpdateFrame(streamId, i), messageEvent.getRemoteAddress());
                }
            }
            if (spdyDataFrame.isLast()) {
                halfCloseStream(streamId, true);
            }
        } else if (message instanceof SpdySynStreamFrame) {
            SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame) message;
            int streamId2 = spdySynStreamFrame.getStreamId();
            if (spdySynStreamFrame.isInvalid() || !isRemoteInitiatedID(streamId2) || this.spdySession.isActiveStream(streamId2)) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId2, SpdyStreamStatus.PROTOCOL_ERROR);
                return;
            } else if (streamId2 <= this.lastGoodStreamID) {
                issueSessionError(channelHandlerContext, messageEvent.getChannel(), messageEvent.getRemoteAddress(), SpdySessionStatus.PROTOCOL_ERROR);
                return;
            } else if (!acceptStream(streamId2, spdySynStreamFrame.getPriority(), spdySynStreamFrame.isLast(), spdySynStreamFrame.isUnidirectional())) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId2, SpdyStreamStatus.REFUSED_STREAM);
                return;
            }
        } else if (message instanceof SpdySynReplyFrame) {
            SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame) message;
            int streamId3 = spdySynReplyFrame.getStreamId();
            if (spdySynReplyFrame.isInvalid() || isRemoteInitiatedID(streamId3) || this.spdySession.isRemoteSideClosed(streamId3)) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId3, SpdyStreamStatus.INVALID_STREAM);
                return;
            } else if (this.spdySession.hasReceivedReply(streamId3)) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId3, SpdyStreamStatus.STREAM_IN_USE);
                return;
            } else {
                this.spdySession.receivedReply(streamId3);
                if (spdySynReplyFrame.isLast()) {
                    halfCloseStream(streamId3, true);
                }
            }
        } else if (message instanceof SpdyRstStreamFrame) {
            removeStream(((SpdyRstStreamFrame) message).getStreamId());
        } else if (message instanceof SpdySettingsFrame) {
            SpdySettingsFrame spdySettingsFrame = (SpdySettingsFrame) message;
            int value2 = spdySettingsFrame.getValue(4);
            if (value2 >= 0) {
                updateConcurrentStreams(value2, true);
            }
            if (spdySettingsFrame.isPersisted(7)) {
                spdySettingsFrame.removeValue(7);
            }
            spdySettingsFrame.setPersistValue(7, false);
            if (this.flowControl && (value = spdySettingsFrame.getValue(7)) >= 0) {
                updateInitialSendWindowSize(value);
            }
        } else if (message instanceof SpdyPingFrame) {
            SpdyPingFrame spdyPingFrame = (SpdyPingFrame) message;
            if (isRemoteInitiatedID(spdyPingFrame.getId())) {
                Channels.write(channelHandlerContext, Channels.future(messageEvent.getChannel()), spdyPingFrame, messageEvent.getRemoteAddress());
                return;
            } else if (this.pings.get() == 0) {
                return;
            } else {
                this.pings.getAndDecrement();
            }
        } else if (message instanceof SpdyGoAwayFrame) {
            this.receivedGoAwayFrame = true;
        } else if (message instanceof SpdyHeadersFrame) {
            SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame) message;
            int streamId4 = spdyHeadersFrame.getStreamId();
            if (spdyHeadersFrame.isInvalid()) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId4, SpdyStreamStatus.PROTOCOL_ERROR);
                return;
            } else if (this.spdySession.isRemoteSideClosed(streamId4)) {
                issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId4, SpdyStreamStatus.INVALID_STREAM);
                return;
            } else if (spdyHeadersFrame.isLast()) {
                halfCloseStream(streamId4, true);
            }
        } else if (message instanceof SpdyWindowUpdateFrame) {
            if (this.flowControl) {
                SpdyWindowUpdateFrame spdyWindowUpdateFrame = (SpdyWindowUpdateFrame) message;
                int streamId5 = spdyWindowUpdateFrame.getStreamId();
                int deltaWindowSize = spdyWindowUpdateFrame.getDeltaWindowSize();
                if (this.spdySession.isLocalSideClosed(streamId5)) {
                    return;
                }
                if (this.spdySession.getSendWindowSize(streamId5) > Integer.MAX_VALUE - deltaWindowSize) {
                    issueStreamError(channelHandlerContext, messageEvent.getRemoteAddress(), streamId5, SpdyStreamStatus.FLOW_CONTROL_ERROR);
                    return;
                } else {
                    updateSendWindowSize(channelHandlerContext, streamId5, deltaWindowSize);
                    return;
                }
            }
            return;
        }
        super.messageReceived(channelHandlerContext, messageEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, ExceptionEvent exceptionEvent) throws Exception {
        if (exceptionEvent.getCause() instanceof SpdyProtocolException) {
            issueSessionError(channelHandlerContext, exceptionEvent.getChannel(), null, SpdySessionStatus.PROTOCOL_ERROR);
        }
        super.exceptionCaught(channelHandlerContext, exceptionEvent);
    }

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(final ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        int value;
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C87215.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if ((i == 1 || i == 2 || i == 3) && (Boolean.FALSE.equals(channelStateEvent.getValue()) || channelStateEvent.getValue() == null)) {
                sendGoAwayFrame(channelHandlerContext, channelStateEvent);
                return;
            }
        }
        if (!(channelEvent instanceof MessageEvent)) {
            channelHandlerContext.sendDownstream(channelEvent);
            return;
        }
        MessageEvent messageEvent = (MessageEvent) channelEvent;
        Object message = messageEvent.getMessage();
        if (message instanceof SpdyDataFrame) {
            SpdyDataFrame spdyDataFrame = (SpdyDataFrame) message;
            final int streamId = spdyDataFrame.getStreamId();
            if (this.spdySession.isLocalSideClosed(streamId)) {
                messageEvent.getFuture().setFailure(PROTOCOL_EXCEPTION);
                return;
            }
            if (this.flowControl) {
                synchronized (this.flowControlLock) {
                    int readableBytes = spdyDataFrame.getData().readableBytes();
                    int sendWindowSize = this.spdySession.getSendWindowSize(streamId);
                    if (sendWindowSize < readableBytes) {
                        if (sendWindowSize > 0) {
                            this.spdySession.updateSendWindowSize(streamId, sendWindowSize * (-1));
                            DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(streamId);
                            defaultSpdyDataFrame.setData(spdyDataFrame.getData().readSlice(sendWindowSize));
                            this.spdySession.putPendingWrite(streamId, messageEvent);
                            ChannelFuture future = Channels.future(messageEvent.getChannel());
                            final SocketAddress remoteAddress = messageEvent.getRemoteAddress();
                            messageEvent.getFuture().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.codec.spdy.SpdySessionHandler.2
                                @Override // org.jboss.netty.channel.ChannelFutureListener
                                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                    if (channelFuture.isSuccess()) {
                                        return;
                                    }
                                    SpdySessionHandler.this.issueStreamError(channelHandlerContext, remoteAddress, streamId, SpdyStreamStatus.INTERNAL_ERROR);
                                }
                            });
                            Channels.write(channelHandlerContext, future, defaultSpdyDataFrame, remoteAddress);
                            return;
                        }
                        this.spdySession.putPendingWrite(streamId, messageEvent);
                        return;
                    }
                    this.spdySession.updateSendWindowSize(streamId, readableBytes * (-1));
                    final SocketAddress remoteAddress2 = messageEvent.getRemoteAddress();
                    messageEvent.getFuture().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.codec.spdy.SpdySessionHandler.1
                        @Override // org.jboss.netty.channel.ChannelFutureListener
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                return;
                            }
                            SpdySessionHandler.this.issueStreamError(channelHandlerContext, remoteAddress2, streamId, SpdyStreamStatus.INTERNAL_ERROR);
                        }
                    });
                }
            }
            if (spdyDataFrame.isLast()) {
                halfCloseStream(streamId, false);
            }
        } else if (message instanceof SpdySynStreamFrame) {
            SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame) message;
            int streamId2 = spdySynStreamFrame.getStreamId();
            if (isRemoteInitiatedID(streamId2)) {
                messageEvent.getFuture().setFailure(PROTOCOL_EXCEPTION);
                return;
            } else if (!acceptStream(streamId2, spdySynStreamFrame.getPriority(), spdySynStreamFrame.isUnidirectional(), spdySynStreamFrame.isLast())) {
                messageEvent.getFuture().setFailure(PROTOCOL_EXCEPTION);
                return;
            }
        } else if (message instanceof SpdySynReplyFrame) {
            SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame) message;
            int streamId3 = spdySynReplyFrame.getStreamId();
            if (!isRemoteInitiatedID(streamId3) || this.spdySession.isLocalSideClosed(streamId3)) {
                messageEvent.getFuture().setFailure(PROTOCOL_EXCEPTION);
                return;
            } else if (spdySynReplyFrame.isLast()) {
                halfCloseStream(streamId3, false);
            }
        } else if (message instanceof SpdyRstStreamFrame) {
            removeStream(((SpdyRstStreamFrame) message).getStreamId());
        } else if (message instanceof SpdySettingsFrame) {
            SpdySettingsFrame spdySettingsFrame = (SpdySettingsFrame) message;
            int value2 = spdySettingsFrame.getValue(4);
            if (value2 >= 0) {
                updateConcurrentStreams(value2, false);
            }
            if (spdySettingsFrame.isPersisted(7)) {
                spdySettingsFrame.removeValue(7);
            }
            spdySettingsFrame.setPersistValue(7, false);
            if (this.flowControl && (value = spdySettingsFrame.getValue(7)) >= 0) {
                updateInitialReceiveWindowSize(value);
            }
        } else if (message instanceof SpdyPingFrame) {
            SpdyPingFrame spdyPingFrame = (SpdyPingFrame) message;
            if (isRemoteInitiatedID(spdyPingFrame.getId())) {
                messageEvent.getFuture().setFailure(new IllegalArgumentException("invalid PING ID: " + spdyPingFrame.getId()));
                return;
            }
            this.pings.getAndIncrement();
        } else {
            if (message instanceof SpdyGoAwayFrame) {
                messageEvent.getFuture().setFailure(PROTOCOL_EXCEPTION);
                return;
            }
            if (message instanceof SpdyHeadersFrame) {
                SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame) message;
                int streamId4 = spdyHeadersFrame.getStreamId();
                if (this.spdySession.isLocalSideClosed(streamId4)) {
                    messageEvent.getFuture().setFailure(PROTOCOL_EXCEPTION);
                    return;
                } else if (spdyHeadersFrame.isLast()) {
                    halfCloseStream(streamId4, false);
                }
            } else if (message instanceof SpdyWindowUpdateFrame) {
                messageEvent.getFuture().setFailure(PROTOCOL_EXCEPTION);
                return;
            }
        }
        channelHandlerContext.sendDownstream(channelEvent);
    }

    /* renamed from: org.jboss.netty.handler.codec.spdy.SpdySessionHandler$5 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87215 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void issueSessionError(ChannelHandlerContext channelHandlerContext, Channel channel, SocketAddress socketAddress, SpdySessionStatus spdySessionStatus) {
        sendGoAwayFrame(channelHandlerContext, channel, socketAddress, spdySessionStatus).addListener(ChannelFutureListener.CLOSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void issueStreamError(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, int i, SpdyStreamStatus spdyStreamStatus) {
        boolean z = !this.spdySession.isRemoteSideClosed(i);
        removeStream(i);
        DefaultSpdyRstStreamFrame defaultSpdyRstStreamFrame = new DefaultSpdyRstStreamFrame(i, spdyStreamStatus);
        Channels.write(channelHandlerContext, Channels.future(channelHandlerContext.getChannel()), defaultSpdyRstStreamFrame, socketAddress);
        if (z) {
            Channels.fireMessageReceived(channelHandlerContext, defaultSpdyRstStreamFrame, socketAddress);
        }
    }

    private boolean isRemoteInitiatedID(int i) {
        boolean isServerId = SpdyCodecUtil.isServerId(i);
        return (this.server && !isServerId) || (!this.server && isServerId);
    }

    private void updateConcurrentStreams(int i, boolean z) {
        if (z) {
            this.remoteConcurrentStreams = i;
        } else {
            this.localConcurrentStreams = i;
        }
        if (this.localConcurrentStreams == this.remoteConcurrentStreams) {
            this.maxConcurrentStreams = this.localConcurrentStreams;
            return;
        }
        if (this.localConcurrentStreams == 0) {
            this.maxConcurrentStreams = this.remoteConcurrentStreams;
            return;
        }
        if (this.remoteConcurrentStreams == 0) {
            this.maxConcurrentStreams = this.localConcurrentStreams;
        } else if (this.localConcurrentStreams > this.remoteConcurrentStreams) {
            this.maxConcurrentStreams = this.remoteConcurrentStreams;
        } else {
            this.maxConcurrentStreams = this.localConcurrentStreams;
        }
    }

    private synchronized void updateInitialSendWindowSize(int i) {
        int i2 = i - this.initialSendWindowSize;
        this.initialSendWindowSize = i;
        Iterator<Integer> it = this.spdySession.getActiveStreams().iterator();
        while (it.hasNext()) {
            this.spdySession.updateSendWindowSize(it.next().intValue(), i2);
        }
    }

    private synchronized void updateInitialReceiveWindowSize(int i) {
        int i2 = i - this.initialReceiveWindowSize;
        this.initialReceiveWindowSize = i;
        this.spdySession.updateAllReceiveWindowSizes(i2);
    }

    private synchronized boolean acceptStream(int i, byte b, boolean z, boolean z2) {
        if (!this.receivedGoAwayFrame && !this.sentGoAwayFrame) {
            int i2 = this.maxConcurrentStreams;
            if (i2 != 0 && this.spdySession.numActiveStreams() >= i2) {
                return false;
            }
            this.spdySession.acceptStream(i, b, z, z2, this.initialSendWindowSize, this.initialReceiveWindowSize);
            if (isRemoteInitiatedID(i)) {
                this.lastGoodStreamID = i;
            }
            return true;
        }
        return false;
    }

    private void halfCloseStream(int i, boolean z) {
        if (z) {
            this.spdySession.closeRemoteSide(i);
        } else {
            this.spdySession.closeLocalSide(i);
        }
        if (this.closeSessionFuture == null || !this.spdySession.noActiveStreams()) {
            return;
        }
        this.closeSessionFuture.setSuccess();
    }

    private void removeStream(int i) {
        this.spdySession.removeStream(i);
        if (this.closeSessionFuture == null || !this.spdySession.noActiveStreams()) {
            return;
        }
        this.closeSessionFuture.setSuccess();
    }

    private void updateSendWindowSize(final ChannelHandlerContext channelHandlerContext, final int i, int i2) {
        synchronized (this.flowControlLock) {
            int updateSendWindowSize = this.spdySession.updateSendWindowSize(i, i2);
            while (updateSendWindowSize > 0) {
                MessageEvent pendingWrite = this.spdySession.getPendingWrite(i);
                if (pendingWrite == null) {
                    break;
                }
                SpdyDataFrame spdyDataFrame = (SpdyDataFrame) pendingWrite.getMessage();
                int readableBytes = spdyDataFrame.getData().readableBytes();
                if (updateSendWindowSize >= readableBytes) {
                    this.spdySession.removePendingWrite(i);
                    updateSendWindowSize = this.spdySession.updateSendWindowSize(i, readableBytes * (-1));
                    final SocketAddress remoteAddress = pendingWrite.getRemoteAddress();
                    pendingWrite.getFuture().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.codec.spdy.SpdySessionHandler.3
                        @Override // org.jboss.netty.channel.ChannelFutureListener
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                return;
                            }
                            SpdySessionHandler.this.issueStreamError(channelHandlerContext, remoteAddress, i, SpdyStreamStatus.INTERNAL_ERROR);
                        }
                    });
                    if (spdyDataFrame.isLast()) {
                        halfCloseStream(i, false);
                    }
                    Channels.write(channelHandlerContext, pendingWrite.getFuture(), spdyDataFrame, pendingWrite.getRemoteAddress());
                } else {
                    this.spdySession.updateSendWindowSize(i, updateSendWindowSize * (-1));
                    DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(i);
                    defaultSpdyDataFrame.setData(spdyDataFrame.getData().readSlice(updateSendWindowSize));
                    ChannelFuture future = Channels.future(pendingWrite.getChannel());
                    final SocketAddress remoteAddress2 = pendingWrite.getRemoteAddress();
                    pendingWrite.getFuture().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.codec.spdy.SpdySessionHandler.4
                        @Override // org.jboss.netty.channel.ChannelFutureListener
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                return;
                            }
                            SpdySessionHandler.this.issueStreamError(channelHandlerContext, remoteAddress2, i, SpdyStreamStatus.INTERNAL_ERROR);
                        }
                    });
                    Channels.write(channelHandlerContext, future, defaultSpdyDataFrame, remoteAddress2);
                    updateSendWindowSize = 0;
                }
            }
        }
    }

    private void sendGoAwayFrame(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) {
        if (!channelStateEvent.getChannel().isConnected()) {
            channelHandlerContext.sendDownstream(channelStateEvent);
            return;
        }
        ChannelFuture sendGoAwayFrame = sendGoAwayFrame(channelHandlerContext, channelStateEvent.getChannel(), null, SpdySessionStatus.f10045OK);
        if (this.spdySession.noActiveStreams()) {
            sendGoAwayFrame.addListener(new ClosingChannelFutureListener(channelHandlerContext, channelStateEvent));
        } else {
            this.closeSessionFuture = Channels.future(channelStateEvent.getChannel());
            this.closeSessionFuture.addListener(new ClosingChannelFutureListener(channelHandlerContext, channelStateEvent));
        }
    }

    private synchronized ChannelFuture sendGoAwayFrame(ChannelHandlerContext channelHandlerContext, Channel channel, SocketAddress socketAddress, SpdySessionStatus spdySessionStatus) {
        if (!this.sentGoAwayFrame) {
            this.sentGoAwayFrame = true;
            DefaultSpdyGoAwayFrame defaultSpdyGoAwayFrame = new DefaultSpdyGoAwayFrame(this.lastGoodStreamID, spdySessionStatus);
            ChannelFuture future = Channels.future(channel);
            Channels.write(channelHandlerContext, future, defaultSpdyGoAwayFrame, socketAddress);
            return future;
        }
        return Channels.succeededFuture(channel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class ClosingChannelFutureListener implements ChannelFutureListener {
        private final ChannelHandlerContext ctx;

        /* renamed from: e */
        private final ChannelStateEvent f10044e;

        ClosingChannelFutureListener(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) {
            this.ctx = channelHandlerContext;
            this.f10044e = channelStateEvent;
        }

        @Override // org.jboss.netty.channel.ChannelFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (!(channelFuture.getCause() instanceof ClosedChannelException)) {
                Channels.close(this.ctx, this.f10044e.getFuture());
            } else {
                this.f10044e.getFuture().setSuccess();
            }
        }
    }
}
