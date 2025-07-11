package org.jboss.netty.handler.codec.marshalling;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.netty.channel.ChannelHandlerContext;

/* loaded from: classes7.dex */
public class ThreadLocalMarshallerProvider implements MarshallerProvider {
    private final MarshallingConfiguration config;
    private final MarshallerFactory factory;
    private final ThreadLocal<Marshaller> marshallers = new ThreadLocal<>();

    public ThreadLocalMarshallerProvider(MarshallerFactory marshallerFactory, MarshallingConfiguration marshallingConfiguration) {
        this.factory = marshallerFactory;
        this.config = marshallingConfiguration;
    }

    @Override // org.jboss.netty.handler.codec.marshalling.MarshallerProvider
    public Marshaller getMarshaller(ChannelHandlerContext channelHandlerContext) throws Exception {
        Marshaller marshaller = this.marshallers.get();
        if (marshaller != null) {
            return marshaller;
        }
        Marshaller createMarshaller = this.factory.createMarshaller(this.config);
        this.marshallers.set(createMarshaller);
        return createMarshaller;
    }
}
