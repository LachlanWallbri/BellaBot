package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public interface ChannelHandlerContext {
    boolean canHandleDownstream();

    boolean canHandleUpstream();

    Object getAttachment();

    Channel getChannel();

    ChannelHandler getHandler();

    String getName();

    ChannelPipeline getPipeline();

    void sendDownstream(ChannelEvent channelEvent);

    void sendUpstream(ChannelEvent channelEvent);

    void setAttachment(Object obj);
}
