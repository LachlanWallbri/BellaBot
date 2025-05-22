package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lzma.sdk.ICodeProgress;
import lzma.sdk.lzma.Encoder;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class LzmaFrameEncoder extends MessageToByteEncoder<ByteBuf> {
    private static final int DEFAULT_LC = 3;
    private static final int DEFAULT_LP = 0;
    private static final int DEFAULT_MATCH_FINDER = 1;
    private static final int DEFAULT_PB = 2;
    private static final int MAX_FAST_BYTES = 273;
    private static final int MEDIUM_DICTIONARY_SIZE = 65536;
    private static final int MEDIUM_FAST_BYTES = 32;
    private static final int MIN_FAST_BYTES = 5;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) LzmaFrameEncoder.class);
    private static boolean warningLogged;
    private final Encoder encoder;
    private final int littleEndianDictionarySize;
    private final byte properties;

    private static int maxOutputBufferLength(int i) {
        return ((int) (i * (i < 200 ? 1.5d : i < 500 ? 1.2d : i < 1000 ? 1.1d : i < 10000 ? 1.05d : 1.02d))) + 13;
    }

    public LzmaFrameEncoder() {
        this(65536);
    }

    public LzmaFrameEncoder(int i, int i2, int i3) {
        this(i, i2, i3, 65536);
    }

    public LzmaFrameEncoder(int i) {
        this(3, 0, 2, i);
    }

    public LzmaFrameEncoder(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, false, 32);
    }

    public LzmaFrameEncoder(int i, int i2, int i3, int i4, boolean z, int i5) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("lc: " + i + " (expected: 0-8)");
        }
        if (i2 < 0 || i2 > 4) {
            throw new IllegalArgumentException("lp: " + i2 + " (expected: 0-4)");
        }
        if (i3 < 0 || i3 > 4) {
            throw new IllegalArgumentException("pb: " + i3 + " (expected: 0-4)");
        }
        if (i + i2 > 4 && !warningLogged) {
            logger.warn("The latest versions of LZMA libraries (for example, XZ Utils) has an additional requirement: lc + lp <= 4. Data which don't follow this requirement cannot be decompressed with this libraries.");
            warningLogged = true;
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("dictionarySize: " + i4 + " (expected: 0+)");
        }
        if (i5 < 5 || i5 > 273) {
            throw new IllegalArgumentException(String.format("numFastBytes: %d (expected: %d-%d)", Integer.valueOf(i5), 5, 273));
        }
        Encoder encoder = new Encoder();
        this.encoder = encoder;
        encoder.setDictionarySize(i4);
        this.encoder.setEndMarkerMode(z);
        this.encoder.setMatchFinder(1);
        this.encoder.setNumFastBytes(i5);
        this.encoder.setLcLpPb(i, i2, i3);
        this.properties = (byte) ((((i3 * 5) + i2) * 9) + i);
        this.littleEndianDictionarySize = Integer.reverseBytes(i4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        ByteBufOutputStream byteBufOutputStream;
        ByteBufInputStream byteBufInputStream;
        int readableBytes = byteBuf.readableBytes();
        ByteBufInputStream byteBufInputStream2 = null;
        try {
            byteBufInputStream = new ByteBufInputStream(byteBuf);
            try {
                byteBufOutputStream = new ByteBufOutputStream(byteBuf2);
            } catch (Throwable th) {
                th = th;
                byteBufOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            byteBufOutputStream = null;
        }
        try {
            byteBufOutputStream.writeByte(this.properties);
            byteBufOutputStream.writeInt(this.littleEndianDictionarySize);
            byteBufOutputStream.writeLong(Long.reverseBytes(readableBytes));
            this.encoder.code(byteBufInputStream, byteBufOutputStream, -1L, -1L, (ICodeProgress) null);
            byteBufInputStream.close();
            byteBufOutputStream.close();
        } catch (Throwable th3) {
            th = th3;
            byteBufInputStream2 = byteBufInputStream;
            if (byteBufInputStream2 != null) {
                byteBufInputStream2.close();
            }
            if (byteBufOutputStream != null) {
                byteBufOutputStream.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToByteEncoder
    public ByteBuf allocateBuffer(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, boolean z) throws Exception {
        return channelHandlerContext.alloc().ioBuffer(maxOutputBufferLength(byteBuf.readableBytes()));
    }
}
