package io.netty.handler.codec.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class CompatibleObjectEncoder extends MessageToByteEncoder<Serializable> {
    private final int resetInterval;
    private int writtenObjects;

    public CompatibleObjectEncoder() {
        this(16);
    }

    public CompatibleObjectEncoder(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("resetInterval: " + i);
        }
        this.resetInterval = i;
    }

    protected ObjectOutputStream newObjectOutputStream(OutputStream outputStream) throws Exception {
        return new ObjectOutputStream(outputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, Serializable serializable, ByteBuf byteBuf) throws Exception {
        ObjectOutputStream newObjectOutputStream = newObjectOutputStream(new ByteBufOutputStream(byteBuf));
        try {
            if (this.resetInterval != 0) {
                int i = this.writtenObjects + 1;
                this.writtenObjects = i;
                if (i % this.resetInterval == 0) {
                    newObjectOutputStream.reset();
                }
            }
            newObjectOutputStream.writeObject(serializable);
            newObjectOutputStream.flush();
        } finally {
            newObjectOutputStream.close();
        }
    }
}
