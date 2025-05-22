package org.jboss.netty.handler.codec.compression;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.jboss.netty.util.internal.jzlib.JZlib;
import org.jboss.netty.util.internal.jzlib.ZStream;

/* loaded from: classes7.dex */
public class ZlibDecoder extends OneToOneDecoder {
    private byte[] dictionary;
    private volatile boolean finished;

    /* renamed from: z */
    private final ZStream f10026z;

    public ZlibDecoder() {
        this(ZlibWrapper.ZLIB);
    }

    public ZlibDecoder(ZlibWrapper zlibWrapper) {
        this.f10026z = new ZStream();
        if (zlibWrapper == null) {
            throw new NullPointerException("wrapper");
        }
        synchronized (this.f10026z) {
            int inflateInit = this.f10026z.inflateInit(ZlibUtil.convertWrapperType(zlibWrapper));
            if (inflateInit != 0) {
                ZlibUtil.fail(this.f10026z, "initialization failure", inflateInit);
            }
        }
    }

    public ZlibDecoder(byte[] bArr) {
        this.f10026z = new ZStream();
        if (bArr == null) {
            throw new NullPointerException("dictionary");
        }
        this.dictionary = bArr;
        synchronized (this.f10026z) {
            int inflateInit = this.f10026z.inflateInit(JZlib.W_ZLIB);
            if (inflateInit != 0) {
                ZlibUtil.fail(this.f10026z, "initialization failure", inflateInit);
            }
        }
    }

    public boolean isClosed() {
        return this.finished;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneDecoder
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        if (!(obj instanceof ChannelBuffer) || this.finished) {
            return obj;
        }
        synchronized (this.f10026z) {
            try {
                ChannelBuffer channelBuffer = (ChannelBuffer) obj;
                byte[] bArr = new byte[channelBuffer.readableBytes()];
                channelBuffer.readBytes(bArr);
                this.f10026z.next_in = bArr;
                this.f10026z.next_in_index = 0;
                this.f10026z.avail_in = bArr.length;
                byte[] bArr2 = new byte[bArr.length << 1];
                ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer(channelBuffer.order(), bArr2.length, channelHandlerContext.getChannel().getConfig().getBufferFactory());
                this.f10026z.next_out = bArr2;
                this.f10026z.next_out_index = 0;
                this.f10026z.avail_out = bArr2.length;
                while (true) {
                    int inflate = this.f10026z.inflate(2);
                    if (this.f10026z.next_out_index > 0) {
                        dynamicBuffer.writeBytes(bArr2, 0, this.f10026z.next_out_index);
                        this.f10026z.avail_out = bArr2.length;
                    }
                    this.f10026z.next_out_index = 0;
                    if (inflate != -5) {
                        if (inflate != 0) {
                            if (inflate == 1) {
                                this.finished = true;
                                this.f10026z.inflateEnd();
                                break;
                            }
                            if (inflate == 2) {
                                if (this.dictionary == null) {
                                    ZlibUtil.fail(this.f10026z, "decompression failure", inflate);
                                } else {
                                    int inflateSetDictionary = this.f10026z.inflateSetDictionary(this.dictionary, this.dictionary.length);
                                    if (inflateSetDictionary != 0) {
                                        ZlibUtil.fail(this.f10026z, "failed to set the dictionary", inflateSetDictionary);
                                    }
                                }
                            } else {
                                ZlibUtil.fail(this.f10026z, "decompression failure", inflate);
                            }
                        } else {
                            continue;
                        }
                    } else if (this.f10026z.avail_in <= 0) {
                        break;
                    }
                }
                if (dynamicBuffer.writerIndex() != 0) {
                    return dynamicBuffer;
                }
                return null;
            } finally {
                this.f10026z.next_in = null;
                this.f10026z.next_out = null;
            }
        }
    }
}
