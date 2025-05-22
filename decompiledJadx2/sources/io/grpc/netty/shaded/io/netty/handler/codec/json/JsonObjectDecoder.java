package io.grpc.netty.shaded.io.netty.handler.codec.json;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.CorruptedFrameException;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class JsonObjectDecoder extends ByteToMessageDecoder {
    private static final int ST_CORRUPTED = -1;
    private static final int ST_DECODING_ARRAY_STREAM = 2;
    private static final int ST_DECODING_NORMAL = 1;
    private static final int ST_INIT = 0;
    private int idx;
    private boolean insideString;
    private int lastReaderIndex;
    private final int maxObjectLength;
    private int openBraces;
    private int state;
    private final boolean streamArrayElements;

    public JsonObjectDecoder() {
        this(1048576);
    }

    public JsonObjectDecoder(int i) {
        this(i, false);
    }

    public JsonObjectDecoder(boolean z) {
        this(1048576, z);
    }

    public JsonObjectDecoder(int i, boolean z) {
        if (i < 1) {
            throw new IllegalArgumentException("maxObjectLength must be a positive int");
        }
        this.maxObjectLength = i;
        this.streamArrayElements = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (this.state == -1) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            return;
        }
        if (this.idx > byteBuf.readerIndex() && this.lastReaderIndex != byteBuf.readerIndex()) {
            this.idx = byteBuf.readerIndex() + (this.idx - this.lastReaderIndex);
        }
        int i = this.idx;
        int writerIndex = byteBuf.writerIndex();
        if (writerIndex > this.maxObjectLength) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            reset();
            throw new TooLongFrameException("object length exceeds " + this.maxObjectLength + ": " + writerIndex + " bytes discarded");
        }
        while (i < writerIndex) {
            byte b = byteBuf.getByte(i);
            int i2 = this.state;
            if (i2 == 1) {
                decodeByte(b, byteBuf, i);
                if (this.openBraces == 0) {
                    int i3 = i + 1;
                    ByteBuf extractObject = extractObject(channelHandlerContext, byteBuf, byteBuf.readerIndex(), i3 - byteBuf.readerIndex());
                    if (extractObject != null) {
                        list.add(extractObject);
                    }
                    byteBuf.readerIndex(i3);
                    reset();
                }
            } else if (i2 == 2) {
                decodeByte(b, byteBuf, i);
                if (!this.insideString && ((this.openBraces == 1 && b == 44) || (this.openBraces == 0 && b == 93))) {
                    for (int readerIndex = byteBuf.readerIndex(); Character.isWhitespace(byteBuf.getByte(readerIndex)); readerIndex++) {
                        byteBuf.skipBytes(1);
                    }
                    int i4 = i - 1;
                    while (i4 >= byteBuf.readerIndex() && Character.isWhitespace(byteBuf.getByte(i4))) {
                        i4--;
                    }
                    ByteBuf extractObject2 = extractObject(channelHandlerContext, byteBuf, byteBuf.readerIndex(), (i4 + 1) - byteBuf.readerIndex());
                    if (extractObject2 != null) {
                        list.add(extractObject2);
                    }
                    byteBuf.readerIndex(i + 1);
                    if (b == 93) {
                        reset();
                    }
                }
            } else if (b == 123 || b == 91) {
                initDecoding(b);
                if (this.state == 2) {
                    byteBuf.skipBytes(1);
                }
            } else if (Character.isWhitespace(b)) {
                byteBuf.skipBytes(1);
            } else {
                this.state = -1;
                throw new CorruptedFrameException("invalid JSON received at byte position " + i + ": " + ByteBufUtil.hexDump(byteBuf));
            }
            i++;
        }
        if (byteBuf.readableBytes() == 0) {
            this.idx = 0;
        } else {
            this.idx = i;
        }
        this.lastReaderIndex = byteBuf.readerIndex();
    }

    protected ByteBuf extractObject(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, int i, int i2) {
        return byteBuf.retainedSlice(i, i2);
    }

    private void decodeByte(byte b, ByteBuf byteBuf, int i) {
        if ((b == 123 || b == 91) && !this.insideString) {
            this.openBraces++;
            return;
        }
        if ((b == 125 || b == 93) && !this.insideString) {
            this.openBraces--;
            return;
        }
        if (b == 34) {
            if (!this.insideString) {
                this.insideString = true;
                return;
            }
            int i2 = 0;
            for (int i3 = i - 1; i3 >= 0 && byteBuf.getByte(i3) == 92; i3--) {
                i2++;
            }
            if (i2 % 2 == 0) {
                this.insideString = false;
            }
        }
    }

    private void initDecoding(byte b) {
        this.openBraces = 1;
        if (b == 91 && this.streamArrayElements) {
            this.state = 2;
        } else {
            this.state = 1;
        }
    }

    private void reset() {
        this.insideString = false;
        this.state = 0;
        this.openBraces = 0;
    }
}
