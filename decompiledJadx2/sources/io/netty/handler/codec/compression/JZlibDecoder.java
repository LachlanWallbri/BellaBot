package io.netty.handler.codec.compression;

import com.jcraft.jzlib.Inflater;
import com.jcraft.jzlib.JZlib;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class JZlibDecoder extends ZlibDecoder {
    private byte[] dictionary;
    private volatile boolean finished;

    /* renamed from: z */
    private final Inflater f8477z;

    public JZlibDecoder() {
        this(ZlibWrapper.ZLIB);
    }

    public JZlibDecoder(ZlibWrapper zlibWrapper) {
        Inflater inflater = new Inflater();
        this.f8477z = inflater;
        if (zlibWrapper == null) {
            throw new NullPointerException("wrapper");
        }
        int init = inflater.init(ZlibUtil.convertWrapperType(zlibWrapper));
        if (init != 0) {
            ZlibUtil.fail(this.f8477z, "initialization failure", init);
        }
    }

    public JZlibDecoder(byte[] bArr) {
        Inflater inflater = new Inflater();
        this.f8477z = inflater;
        if (bArr == null) {
            throw new NullPointerException("dictionary");
        }
        this.dictionary = bArr;
        int inflateInit = inflater.inflateInit(JZlib.W_ZLIB);
        if (inflateInit != 0) {
            ZlibUtil.fail(this.f8477z, "initialization failure", inflateInit);
        }
    }

    @Override // io.netty.handler.codec.compression.ZlibDecoder
    public boolean isClosed() {
        return this.finished;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c3, code lost:
    
        r7.finished = true;
        r7.f8477z.inflateEnd();
     */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
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
            this.f8477z.avail_in = readableBytes;
            if (byteBuf.hasArray()) {
                this.f8477z.next_in = byteBuf.array();
                this.f8477z.next_in_index = byteBuf.arrayOffset() + byteBuf.readerIndex();
            } else {
                byte[] bArr = new byte[readableBytes];
                byteBuf.getBytes(byteBuf.readerIndex(), bArr);
                this.f8477z.next_in = bArr;
                this.f8477z.next_in_index = 0;
            }
            int i = this.f8477z.next_in_index;
            ByteBuf heapBuffer = channelHandlerContext.alloc().heapBuffer(readableBytes << 1);
            while (true) {
                try {
                    heapBuffer.ensureWritable(this.f8477z.avail_in << 1);
                    this.f8477z.avail_out = heapBuffer.writableBytes();
                    this.f8477z.next_out = heapBuffer.array();
                    this.f8477z.next_out_index = heapBuffer.arrayOffset() + heapBuffer.writerIndex();
                    int i2 = this.f8477z.next_out_index;
                    int inflate = this.f8477z.inflate(2);
                    int i3 = this.f8477z.next_out_index - i2;
                    if (i3 > 0) {
                        heapBuffer.writerIndex(heapBuffer.writerIndex() + i3);
                    }
                    if (inflate != -5) {
                        if (inflate != 0) {
                            if (inflate == 1) {
                                break;
                            }
                            if (inflate == 2) {
                                if (this.dictionary == null) {
                                    ZlibUtil.fail(this.f8477z, "decompression failure", inflate);
                                } else {
                                    int inflateSetDictionary = this.f8477z.inflateSetDictionary(this.dictionary, this.dictionary.length);
                                    if (inflateSetDictionary != 0) {
                                        ZlibUtil.fail(this.f8477z, "failed to set the dictionary", inflateSetDictionary);
                                    }
                                }
                            } else {
                                ZlibUtil.fail(this.f8477z, "decompression failure", inflate);
                            }
                        } else {
                            continue;
                        }
                    } else if (this.f8477z.avail_in <= 0) {
                        break;
                    }
                } finally {
                    byteBuf.skipBytes(this.f8477z.next_in_index - i);
                    if (heapBuffer.isReadable()) {
                        list.add(heapBuffer);
                    } else {
                        heapBuffer.release();
                    }
                }
            }
        } finally {
            this.f8477z.next_in = null;
            this.f8477z.next_out = null;
        }
    }
}
