package org.jboss.netty.handler.codec.marshalling;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;
import org.jboss.netty.channel.ChannelHandlerContext;

/* loaded from: classes7.dex */
public class ThreadLocalUnmarshallerProvider implements UnmarshallerProvider {
    private final MarshallingConfiguration config;
    private final MarshallerFactory factory;
    private final ThreadLocal<Unmarshaller> unmarshallers = new ThreadLocal<>();

    public ThreadLocalUnmarshallerProvider(MarshallerFactory marshallerFactory, MarshallingConfiguration marshallingConfiguration) {
        this.factory = marshallerFactory;
        this.config = marshallingConfiguration;
    }

    @Override // org.jboss.netty.handler.codec.marshalling.UnmarshallerProvider
    public Unmarshaller getUnmarshaller(ChannelHandlerContext channelHandlerContext) throws Exception {
        Unmarshaller unmarshaller = this.unmarshallers.get();
        if (unmarshaller != null) {
            return unmarshaller;
        }
        Unmarshaller createUnmarshaller = this.factory.createUnmarshaller(this.config);
        this.unmarshallers.set(createUnmarshaller);
        return createUnmarshaller;
    }
}
