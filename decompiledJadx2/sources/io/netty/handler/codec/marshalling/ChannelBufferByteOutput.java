package io.netty.handler.codec.marshalling;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.jboss.marshalling.ByteOutput;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
class ChannelBufferByteOutput implements ByteOutput {
    private final ByteBuf buffer;

    public void close() throws IOException {
    }

    public void flush() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelBufferByteOutput(ByteBuf byteBuf) {
        this.buffer = byteBuf;
    }

    public void write(int i) throws IOException {
        this.buffer.writeByte(i);
    }

    public void write(byte[] bArr) throws IOException {
        this.buffer.writeBytes(bArr);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.buffer.writeBytes(bArr, i, i2);
    }

    ByteBuf getBuffer() {
        return this.buffer;
    }
}
