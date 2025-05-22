package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.jcraft.jzlib.Inflater;
import com.jcraft.jzlib.JZlib;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class JZlibDecoder extends ZlibDecoder {
    private byte[] dictionary;
    private volatile boolean finished;

    /* renamed from: z */
    private final Inflater f8359z;

    public JZlibDecoder() {
        this(ZlibWrapper.ZLIB, 0);
    }

    public JZlibDecoder(int i) {
        this(ZlibWrapper.ZLIB, i);
    }

    public JZlibDecoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, 0);
    }

    public JZlibDecoder(ZlibWrapper zlibWrapper, int i) {
        super(i);
        this.f8359z = new Inflater();
        ObjectUtil.checkNotNull(zlibWrapper, "wrapper");
        int init = this.f8359z.init(ZlibUtil.convertWrapperType(zlibWrapper));
        if (init != 0) {
            ZlibUtil.fail(this.f8359z, "initialization failure", init);
        }
    }

    public JZlibDecoder(byte[] bArr) {
        this(bArr, 0);
    }

    public JZlibDecoder(byte[] bArr, int i) {
        super(i);
        this.f8359z = new Inflater();
        this.dictionary = (byte[]) ObjectUtil.checkNotNull(bArr, "dictionary");
        int inflateInit = this.f8359z.inflateInit(JZlib.W_ZLIB);
        if (inflateInit != 0) {
            ZlibUtil.fail(this.f8359z, "initialization failure", inflateInit);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibDecoder
    public boolean isClosed() {
        return this.finished;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c0, code lost:
    
        r8.finished = true;
        r8.f8359z.inflateEnd();
     */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (this.finished) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            return;
        }
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            return;
        }
        try {
            this.f8359z.avail_in = readableBytes;
            if (byteBuf.hasArray()) {
                this.f8359z.next_in = byteBuf.array();
                this.f8359z.next_in_index = byteBuf.arrayOffset() + byteBuf.readerIndex();
            } else {
                byte[] bArr = new byte[readableBytes];
                byteBuf.getBytes(byteBuf.readerIndex(), bArr);
                this.f8359z.next_in = bArr;
                this.f8359z.next_in_index = 0;
            }
            int i = this.f8359z.next_in_index;
            ByteBuf prepareDecompressBuffer = prepareDecompressBuffer(channelHandlerContext, null, readableBytes << 1);
            while (true) {
                try {
                    prepareDecompressBuffer = prepareDecompressBuffer(channelHandlerContext, prepareDecompressBuffer, this.f8359z.avail_in << 1);
                    this.f8359z.avail_out = prepareDecompressBuffer.writableBytes();
                    this.f8359z.next_out = prepareDecompressBuffer.array();
                    this.f8359z.next_out_index = prepareDecompressBuffer.arrayOffset() + prepareDecompressBuffer.writerIndex();
                    int i2 = this.f8359z.next_out_index;
                    int inflate = this.f8359z.inflate(2);
                    int i3 = this.f8359z.next_out_index - i2;
                    if (i3 > 0) {
                        prepareDecompressBuffer.writerIndex(prepareDecompressBuffer.writerIndex() + i3);
                    }
                    if (inflate != -5) {
                        if (inflate != 0) {
                            if (inflate == 1) {
                                break;
                            }
                            if (inflate == 2) {
                                if (this.dictionary == null) {
                                    ZlibUtil.fail(this.f8359z, "decompression failure", inflate);
                                } else {
                                    int inflateSetDictionary = this.f8359z.inflateSetDictionary(this.dictionary, this.dictionary.length);
                                    if (inflateSetDictionary != 0) {
                                        ZlibUtil.fail(this.f8359z, "failed to set the dictionary", inflateSetDictionary);
                                    }
                                }
                            } else {
                                ZlibUtil.fail(this.f8359z, "decompression failure", inflate);
                            }
                        } else {
                            continue;
                        }
                    } else if (this.f8359z.avail_in <= 0) {
                        break;
                    }
                } finally {
                    byteBuf.skipBytes(this.f8359z.next_in_index - i);
                    if (prepareDecompressBuffer.isReadable()) {
                        list.add(prepareDecompressBuffer);
                    } else {
                        prepareDecompressBuffer.release();
                    }
                }
            }
        } finally {
            Inflater inflater = this.f8359z;
            inflater.next_in = null;
            inflater.next_out = null;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibDecoder
    protected void decompressionBufferExhausted(ByteBuf byteBuf) {
        this.finished = true;
    }
}
