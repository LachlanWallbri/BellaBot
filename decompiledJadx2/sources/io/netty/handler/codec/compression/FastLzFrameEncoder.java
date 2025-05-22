package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
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
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00e3  */
    @Override // io.netty.handler.codec.MessageToByteEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        byte[] bArr;
        int i;
        int compress;
        int i2;
        int i3;
        int i4;
        byte[] bArr2;
        int i5;
        Checksum checksum = this.checksum;
        while (byteBuf.isReadable()) {
            int readerIndex = byteBuf.readerIndex();
            int min = Math.min(byteBuf.readableBytes(), 65535);
            int writerIndex = byteBuf2.writerIndex();
            byteBuf2.setMedium(writerIndex, 4607066);
            int i6 = writerIndex + 4;
            int i7 = i6 + (checksum != null ? 4 : 0);
            if (min < 32) {
                byteBuf2.ensureWritable(i7 + 2 + min);
                byte[] array = byteBuf2.array();
                int arrayOffset = byteBuf2.arrayOffset() + i7 + 2;
                if (checksum != null) {
                    if (byteBuf.hasArray()) {
                        bArr2 = byteBuf.array();
                        i5 = byteBuf.arrayOffset() + readerIndex;
                    } else {
                        bArr2 = new byte[min];
                        byteBuf.getBytes(readerIndex, bArr2);
                        i5 = 0;
                    }
                    checksum.reset();
                    checksum.update(bArr2, i5, min);
                    i4 = i7;
                    byteBuf2.setInt(i6, (int) checksum.getValue());
                    System.arraycopy(bArr2, i5, array, arrayOffset, min);
                } else {
                    i4 = i7;
                    byteBuf.getBytes(readerIndex, array, arrayOffset, min);
                }
                compress = min;
                i2 = i4;
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
                    byteBuf2.setInt(i6, (int) checksum.getValue());
                }
                byteBuf2.ensureWritable(i7 + 4 + FastLz.calculateOutputBufferLength(min));
                byte[] array3 = byteBuf2.array();
                int arrayOffset3 = byteBuf2.arrayOffset() + i7 + 4;
                compress = FastLz.compress(bArr, i, min, array3, arrayOffset3, this.level);
                if (compress < min) {
                    i3 = 1;
                    byteBuf2.setShort(i7, compress);
                    i2 = i7 + 2;
                    byteBuf2.setShort(i2, min);
                    byteBuf2.setByte(writerIndex + 3, i3 | (checksum == null ? 16 : 0));
                    byteBuf2.writerIndex(i2 + 2 + compress);
                    byteBuf.skipBytes(min);
                } else {
                    System.arraycopy(bArr, i, array3, arrayOffset3 - 2, min);
                    compress = min;
                    i2 = i7;
                }
            }
            i3 = 0;
            byteBuf2.setShort(i2, min);
            byteBuf2.setByte(writerIndex + 3, i3 | (checksum == null ? 16 : 0));
            byteBuf2.writerIndex(i2 + 2 + compress);
            byteBuf.skipBytes(min);
        }
    }
}
