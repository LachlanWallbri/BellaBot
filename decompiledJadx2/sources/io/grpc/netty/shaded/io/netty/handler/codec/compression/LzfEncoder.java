package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.ning.compress.BufferRecycler;
import com.ning.compress.lzf.ChunkEncoder;
import com.ning.compress.lzf.LZFChunk;
import com.ning.compress.lzf.LZFEncoder;
import com.ning.compress.lzf.util.ChunkEncoderFactory;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class LzfEncoder extends MessageToByteEncoder<ByteBuf> {
    private static final int MIN_BLOCK_TO_COMPRESS = 16;
    private final int compressThreshold;
    private final ChunkEncoder encoder;
    private final BufferRecycler recycler;

    public LzfEncoder() {
        this(false);
    }

    public LzfEncoder(boolean z) {
        this(z, 65535);
    }

    public LzfEncoder(boolean z, int i) {
        this(z, i, 16);
    }

    public LzfEncoder(int i) {
        this(false, i);
    }

    public LzfEncoder(boolean z, int i, int i2) {
        super(false);
        ChunkEncoder optimalNonAllocatingInstance;
        if (i < 16 || i > 65535) {
            throw new IllegalArgumentException("totalLength: " + i + " (expected: 16" + Soundex.SILENT_MARKER + "65535)");
        }
        if (i2 < 16) {
            throw new IllegalArgumentException("compressThreshold:" + i2 + " expected >=16");
        }
        this.compressThreshold = i2;
        if (z) {
            optimalNonAllocatingInstance = ChunkEncoderFactory.safeNonAllocatingInstance(i);
        } else {
            optimalNonAllocatingInstance = ChunkEncoderFactory.optimalNonAllocatingInstance(i);
        }
        this.encoder = optimalNonAllocatingInstance;
        this.recycler = BufferRecycler.instance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        byte[] allocInputBuffer;
        int encodeNonCompress;
        int readableBytes = byteBuf.readableBytes();
        int readerIndex = byteBuf.readerIndex();
        int i = 0;
        if (byteBuf.hasArray()) {
            allocInputBuffer = byteBuf.array();
            i = byteBuf.arrayOffset() + readerIndex;
        } else {
            allocInputBuffer = this.recycler.allocInputBuffer(readableBytes);
            byteBuf.getBytes(readerIndex, allocInputBuffer, 0, readableBytes);
        }
        byte[] bArr = allocInputBuffer;
        byteBuf2.ensureWritable(LZFEncoder.estimateMaxWorkspaceSize(readableBytes));
        byte[] array = byteBuf2.array();
        int arrayOffset = byteBuf2.arrayOffset() + byteBuf2.writerIndex();
        if (readableBytes >= this.compressThreshold) {
            encodeNonCompress = encodeCompress(bArr, i, readableBytes, array, arrayOffset);
        } else {
            encodeNonCompress = encodeNonCompress(bArr, i, readableBytes, array, arrayOffset);
        }
        byteBuf2.writerIndex(byteBuf2.writerIndex() + encodeNonCompress);
        byteBuf.skipBytes(readableBytes);
        if (byteBuf.hasArray()) {
            return;
        }
        this.recycler.releaseInputBuffer(bArr);
    }

    private int encodeCompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        return LZFEncoder.appendEncoded(this.encoder, bArr, i, i2, bArr2, i3) - i3;
    }

    private static int lzfEncodeNonCompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int min = Math.min(65535, i2);
        int appendNonCompressed = LZFChunk.appendNonCompressed(bArr, i, min, bArr2, i3);
        int i4 = i2 - min;
        if (i4 < 1) {
            return appendNonCompressed;
        }
        int i5 = i + min;
        do {
            int min2 = Math.min(i4, 65535);
            appendNonCompressed = LZFChunk.appendNonCompressed(bArr, i5, min2, bArr2, appendNonCompressed);
            i5 += min2;
            i4 -= min2;
        } while (i4 > 0);
        return appendNonCompressed;
    }

    private static int encodeNonCompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        return lzfEncodeNonCompress(bArr, i, i2, bArr2, i3) - i3;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.encoder.close();
        super.handlerRemoved(channelHandlerContext);
    }
}
