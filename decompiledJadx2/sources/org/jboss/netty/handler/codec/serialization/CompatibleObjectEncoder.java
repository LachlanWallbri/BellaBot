package org.jboss.netty.handler.codec.serialization;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicReference;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/* loaded from: classes7.dex */
public class CompatibleObjectEncoder extends OneToOneEncoder {
    private final AtomicReference<ChannelBuffer> buffer;
    private volatile ObjectOutputStream oout;
    private final int resetInterval;
    private int writtenObjects;

    public CompatibleObjectEncoder() {
        this(16);
    }

    public CompatibleObjectEncoder(int i) {
        this.buffer = new AtomicReference<>();
        if (i < 0) {
            throw new IllegalArgumentException("resetInterval: " + i);
        }
        this.resetInterval = i;
    }

    protected ObjectOutputStream newObjectOutputStream(OutputStream outputStream) throws Exception {
        return new ObjectOutputStream(outputStream);
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        ChannelBuffer buffer = buffer(channelHandlerContext);
        ObjectOutputStream objectOutputStream = this.oout;
        int i = this.resetInterval;
        if (i != 0) {
            this.writtenObjects++;
            if (this.writtenObjects % i == 0) {
                objectOutputStream.reset();
                buffer.discardReadBytes();
            }
        }
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        return buffer.readBytes(buffer.readableBytes());
    }

    private ChannelBuffer buffer(ChannelHandlerContext channelHandlerContext) throws Exception {
        ChannelBuffer channelBuffer = this.buffer.get();
        if (channelBuffer != null) {
            return channelBuffer;
        }
        ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer(channelHandlerContext.getChannel().getConfig().getBufferFactory());
        if (this.buffer.compareAndSet(null, dynamicBuffer)) {
            try {
                this.oout = newObjectOutputStream(new ChannelBufferOutputStream(dynamicBuffer));
                return dynamicBuffer;
            } catch (Throwable th) {
                this.oout = null;
                throw th;
            }
        }
        return this.buffer.get();
    }
}
