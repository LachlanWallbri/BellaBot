package io.netty.handler.codec.compression;

import com.ning.compress.BufferRecycler;
import com.ning.compress.lzf.ChunkEncoder;
import com.ning.compress.lzf.LZFEncoder;
import com.ning.compress.lzf.util.ChunkEncoderFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class LzfEncoder extends MessageToByteEncoder<ByteBuf> {
    private static final int MIN_BLOCK_TO_COMPRESS = 16;
    private final ChunkEncoder encoder;
    private final BufferRecycler recycler;

    public LzfEncoder() {
        this(false, 65535);
    }

    public LzfEncoder(boolean z) {
        this(z, 65535);
    }

    public LzfEncoder(int i) {
        this(false, i);
    }

    public LzfEncoder(boolean z, int i) {
        super(false);
        ChunkEncoder optimalNonAllocatingInstance;
        if (i < 16 || i > 65535) {
            throw new IllegalArgumentException("totalLength: " + i + " (expected: 16" + Soundex.SILENT_MARKER + "65535)");
        }
        if (z) {
            optimalNonAllocatingInstance = ChunkEncoderFactory.safeNonAllocatingInstance(i);
        } else {
            optimalNonAllocatingInstance = ChunkEncoderFactory.optimalNonAllocatingInstance(i);
        }
        this.encoder = optimalNonAllocatingInstance;
        this.recycler = BufferRecycler.instance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        byte[] allocInputBuffer;
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
        byteBuf2.writerIndex(byteBuf2.writerIndex() + (LZFEncoder.appendEncoded(this.encoder, bArr, i, readableBytes, array, arrayOffset) - arrayOffset));
        byteBuf.skipBytes(readableBytes);
        if (byteBuf.hasArray()) {
            return;
        }
        this.recycler.releaseInputBuffer(bArr);
    }
}
