package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public class SimpleChannelDownstreamHandler implements ChannelDownstreamHandler {
    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof MessageEvent) {
            writeRequested(channelHandlerContext, (MessageEvent) channelEvent);
            return;
        }
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C86761.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if (i == 1) {
                if (Boolean.TRUE.equals(channelStateEvent.getValue())) {
                    return;
                }
                closeRequested(channelHandlerContext, channelStateEvent);
                return;
            }
            if (i == 2) {
                if (channelStateEvent.getValue() != null) {
                    bindRequested(channelHandlerContext, channelStateEvent);
                    return;
                } else {
                    unbindRequested(channelHandlerContext, channelStateEvent);
                    return;
                }
            }
            if (i != 3) {
                if (i == 4) {
                    setInterestOpsRequested(channelHandlerContext, channelStateEvent);
                    return;
                } else {
                    channelHandlerContext.sendDownstream(channelEvent);
                    return;
                }
            }
            if (channelStateEvent.getValue() != null) {
                connectRequested(channelHandlerContext, channelStateEvent);
                return;
            } else {
                disconnectRequested(channelHandlerContext, channelStateEvent);
                return;
            }
        }
        channelHandlerContext.sendDownstream(channelEvent);
    }

    /* renamed from: org.jboss.netty.channel.SimpleChannelDownstreamHandler$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86761 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.INTEREST_OPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void writeRequested(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        channelHandlerContext.sendDownstream(messageEvent);
    }

    public void bindRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendDownstream(channelStateEvent);
    }

    public void connectRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendDownstream(channelStateEvent);
    }

    public void setInterestOpsRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendDownstream(channelStateEvent);
    }

    public void disconnectRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendDownstream(channelStateEvent);
    }

    public void unbindRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendDownstream(channelStateEvent);
    }

    public void closeRequested(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendDownstream(channelStateEvent);
    }
}
