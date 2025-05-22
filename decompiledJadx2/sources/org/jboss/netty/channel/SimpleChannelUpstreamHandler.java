package org.jboss.netty.channel;

import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class SimpleChannelUpstreamHandler implements ChannelUpstreamHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(SimpleChannelUpstreamHandler.class.getName());

    @Override // org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        if (channelEvent instanceof MessageEvent) {
            messageReceived(channelHandlerContext, (MessageEvent) channelEvent);
            return;
        }
        if (channelEvent instanceof WriteCompletionEvent) {
            writeComplete(channelHandlerContext, (WriteCompletionEvent) channelEvent);
            return;
        }
        if (channelEvent instanceof ChildChannelStateEvent) {
            ChildChannelStateEvent childChannelStateEvent = (ChildChannelStateEvent) channelEvent;
            if (childChannelStateEvent.getChildChannel().isOpen()) {
                childChannelOpen(channelHandlerContext, childChannelStateEvent);
                return;
            } else {
                childChannelClosed(channelHandlerContext, childChannelStateEvent);
                return;
            }
        }
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C86781.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if (i == 1) {
                if (Boolean.TRUE.equals(channelStateEvent.getValue())) {
                    channelOpen(channelHandlerContext, channelStateEvent);
                    return;
                } else {
                    channelClosed(channelHandlerContext, channelStateEvent);
                    return;
                }
            }
            if (i == 2) {
                if (channelStateEvent.getValue() != null) {
                    channelBound(channelHandlerContext, channelStateEvent);
                    return;
                } else {
                    channelUnbound(channelHandlerContext, channelStateEvent);
                    return;
                }
            }
            if (i != 3) {
                if (i == 4) {
                    channelInterestChanged(channelHandlerContext, channelStateEvent);
                    return;
                } else {
                    channelHandlerContext.sendUpstream(channelEvent);
                    return;
                }
            }
            if (channelStateEvent.getValue() != null) {
                channelConnected(channelHandlerContext, channelStateEvent);
                return;
            } else {
                channelDisconnected(channelHandlerContext, channelStateEvent);
                return;
            }
        }
        if (channelEvent instanceof ExceptionEvent) {
            exceptionCaught(channelHandlerContext, (ExceptionEvent) channelEvent);
        } else {
            channelHandlerContext.sendUpstream(channelEvent);
        }
    }

    /* renamed from: org.jboss.netty.channel.SimpleChannelUpstreamHandler$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86781 {
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

    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        channelHandlerContext.sendUpstream(messageEvent);
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, ExceptionEvent exceptionEvent) throws Exception {
        if (this == channelHandlerContext.getPipeline().getLast()) {
            logger.warn("EXCEPTION, please implement " + getClass().getName() + ".exceptionCaught() for proper handling.", exceptionEvent.getCause());
        }
        channelHandlerContext.sendUpstream(exceptionEvent);
    }

    public void channelOpen(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    public void channelBound(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    public void channelConnected(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    public void channelInterestChanged(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    public void channelDisconnected(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    public void channelUnbound(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    public void channelClosed(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(channelStateEvent);
    }

    public void writeComplete(ChannelHandlerContext channelHandlerContext, WriteCompletionEvent writeCompletionEvent) throws Exception {
        channelHandlerContext.sendUpstream(writeCompletionEvent);
    }

    public void childChannelOpen(ChannelHandlerContext channelHandlerContext, ChildChannelStateEvent childChannelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(childChannelStateEvent);
    }

    public void childChannelClosed(ChannelHandlerContext channelHandlerContext, ChildChannelStateEvent childChannelStateEvent) throws Exception {
        channelHandlerContext.sendUpstream(childChannelStateEvent);
    }
}
