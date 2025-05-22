package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class FastLzFrameEncoder extends MessageToByteEncoder<ByteBuf> {
    private final Checksum checksum;
    private final int level;

    public FastLzFrameEncoder() {
        this(0, null);
    }

    public FastLzFrameEncoder(int i) {
        this(i, null);
    }

    public FastLzFrameEncoder(boolean z) {
        this(0, z ? new Adler32() : null);
    }

    public FastLzFrameEncoder(int i, Checksum checksum) {
        super(false);
        if (i != 0 && i != 1 && i != 2) {
            throw new IllegalArgumentException(String.format("level: %d (expected: %d or %d or %d)", Integer.valueOf(i), 0, 1, 2));
        }
        this.level = i;
        this.checksum = checksum;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00df  */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        byte[] bArr;
        int i;
        int compress;
        int i2;
        byte[] bArr2;
        int i3;
        Checksum checksum = this.checksum;
        while (byteBuf.isReadable()) {
            int readerIndex = byteBuf.readerIndex();
            int min = Math.min(byteBuf.readableBytes(), 65535);
            int writerIndex = byteBuf2.writerIndex();
            byteBuf2.setMedium(writerIndex, 4607066);
            int i4 = writerIndex + 4;
            int i5 = i4 + (checksum != null ? 4 : 0);
            if (min < 32) {
                byteBuf2.ensureWritable(i5 + 2 + min);
                byte[] array = byteBuf2.array();
                int arrayOffset = byteBuf2.arrayOffset() + i5 + 2;
                if (checksum != null) {
                    if (byteBuf.hasArray()) {
                        bArr2 = byteBuf.array();
                        i3 = readerIndex + byteBuf.arrayOffset();
                    } else {
                        bArr2 = new byte[min];
                        byteBuf.getBytes(readerIndex, bArr2);
                        i3 = 0;
                    }
                    checksum.reset();
                    checksum.update(bArr2, i3, min);
                    byteBuf2.setInt(i4, (int) checksum.getValue());
                    System.arraycopy(bArr2, i3, array, arrayOffset, min);
                } else {
                    byteBuf.getBytes(readerIndex, array, arrayOffset, min);
                }
            } else {
                if (byteBuf.hasArray()) {
                    byte[] array2 = byteBuf.array();
                    int arrayOffset2 = byteBuf.arrayOffset() + readerIndex;
                    bArr = array2;
                    i = arrayOffset2;
                } else {
                    byte[] bArr3 = new byte[min];
                    byteBuf.getBytes(readerIndex, bArr3);
                    bArr = bArr3;
                    i = 0;
                }
                if (checksum != null) {
                    checksum.reset();
                    checksum.update(bArr, i, min);
                    byteBuf2.setInt(i4, (int) checksum.getValue());
                }
                byteBuf2.ensureWritable(i5 + 4 + FastLz.calculateOutputBufferLength(min));
                byte[] array3 = byteBuf2.array();
                int arrayOffset3 = byteBuf2.arrayOffset() + i5 + 4;
                compress = FastLz.compress(bArr, i, min, array3, arrayOffset3, this.level);
                if (compress < min) {
                    i2 = 1;
                    byteBuf2.setShort(i5, compress);
                    i5 += 2;
                    byteBuf2.setShort(i5, min);
                    byteBuf2.setByte(writerIndex + 3, (checksum == null ? 16 : 0) | i2);
                    byteBuf2.writerIndex(i5 + 2 + compress);
                    byteBuf.skipBytes(min);
                } else {
                    System.arraycopy(bArr, i, array3, arrayOffset3 - 2, min);
                }
            }
            compress = min;
            i2 = 0;
            byteBuf2.setShort(i5, min);
            byteBuf2.setByte(writerIndex + 3, (checksum == null ? 16 : 0) | i2);
            byteBuf2.writerIndex(i5 + 2 + compress);
            byteBuf.skipBytes(min);
        }
    }
}
