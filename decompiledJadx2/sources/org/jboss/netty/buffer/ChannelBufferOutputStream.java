package org.jboss.netty.buffer;

import com.iflytek.cloud.SpeechEvent;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes7.dex */
public class ChannelBufferOutputStream extends OutputStream implements DataOutput {
    private final ChannelBuffer buffer;
    private final int startIndex;
    private final DataOutputStream utf8out = new DataOutputStream(this);

    public ChannelBufferOutputStream(ChannelBuffer channelBuffer) {
        if (channelBuffer == null) {
            throw new NullPointerException(SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }
        this.buffer = channelBuffer;
        this.startIndex = channelBuffer.writerIndex();
    }

    public int writtenBytes() {
        return this.buffer.writerIndex() - this.startIndex;
    }

    @Override // java.io.OutputStream, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return;
        }
        this.buffer.writeBytes(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        this.buffer.writeBytes(bArr);
    }

    @Override // java.io.OutputStream, java.io.DataOutput
    public void write(int i) throws IOException {
        this.buffer.writeByte((byte) i);
    }

    @Override // java.io.DataOutput
    public void writeBoolean(boolean z) throws IOException {
        write(z ? 1 : 0);
    }

    @Override // java.io.DataOutput
    public void writeByte(int i) throws IOException {
        write(i);
    }

    @Override // java.io.DataOutput
    public void writeBytes(String str) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            write((byte) str.charAt(i));
        }
    }

    @Override // java.io.DataOutput
    public void writeChar(int i) throws IOException {
        writeShort((short) i);
    }

    @Override // java.io.DataOutput
    public void writeChars(String str) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            writeChar(str.charAt(i));
        }
    }

    @Override // java.io.DataOutput
    public void writeDouble(double d) throws IOException {
        writeLong(Double.doubleToLongBits(d));
    }

    @Override // java.io.DataOutput
    public void writeFloat(float f) throws IOException {
        writeInt(Float.floatToIntBits(f));
    }

    @Override // java.io.DataOutput
    public void writeInt(int i) throws IOException {
        this.buffer.writeInt(i);
    }

    @Override // java.io.DataOutput
    public void writeLong(long j) throws IOException {
        this.buffer.writeLong(j);
    }

    @Override // java.io.DataOutput
    public void writeShort(int i) throws IOException {
        this.buffer.writeShort((short) i);
    }

    @Override // java.io.DataOutput
    public void writeUTF(String str) throws IOException {
        this.utf8out.writeUTF(str);
    }

    public ChannelBuffer buffer() {
        return this.buffer;
    }
}
