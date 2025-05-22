package org.jboss.netty.handler.codec.marshalling;

import org.jboss.marshalling.Unmarshaller;
import org.jboss.netty.channel.ChannelHandlerContext;

/* loaded from: classes7.dex */
public interface UnmarshallerProvider {
    Unmarshaller getUnmarshaller(ChannelHandlerContext channelHandlerContext) throws Exception;
}
