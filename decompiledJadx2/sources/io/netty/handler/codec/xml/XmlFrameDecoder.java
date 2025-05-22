package io.netty.handler.codec.xml;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.TooLongFrameException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class XmlFrameDecoder extends ByteToMessageDecoder {
    private final int maxFrameLength;

    private static boolean isValidStartCharForXmlElement(byte b) {
        return (b >= 97 && b <= 122) || (b >= 65 && b <= 90) || b == 58 || b == 95;
    }

    public XmlFrameDecoder(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("maxFrameLength must be a positive int");
        }
        this.maxFrameLength = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x008d, code lost:
    
        if (r7 == 63) goto L43;
     */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i;
        int i2;
        int writerIndex = byteBuf.writerIndex();
        if (writerIndex > this.maxFrameLength) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            fail(writerIndex);
            return;
        }
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        long j = 0;
        int i4 = 0;
        boolean z3 = false;
        for (int readerIndex = byteBuf.readerIndex(); readerIndex < writerIndex; readerIndex++) {
            byte b = byteBuf.getByte(readerIndex);
            if (!z && Character.isWhitespace(b)) {
                i3++;
            } else {
                if (!z && b != 60) {
                    fail(channelHandlerContext);
                    byteBuf.skipBytes(byteBuf.readableBytes());
                    return;
                }
                if (!z2 && b == 60) {
                    int i5 = writerIndex - 1;
                    if (readerIndex < i5) {
                        byte b2 = byteBuf.getByte(readerIndex + 1);
                        if (b2 == 47) {
                            int i6 = readerIndex + 2;
                            while (true) {
                                if (i6 > i5) {
                                    break;
                                }
                                if (byteBuf.getByte(i6) == 62) {
                                    j--;
                                    break;
                                }
                                i6++;
                            }
                        } else if (isValidStartCharForXmlElement(b2)) {
                            j++;
                            z3 = true;
                        } else if (b2 == 33) {
                            if (!isCommentBlockStart(byteBuf, readerIndex)) {
                                if (isCDATABlockStart(byteBuf, readerIndex)) {
                                    j++;
                                    z2 = true;
                                }
                            }
                            j++;
                        }
                    }
                    z = true;
                } else if (z2 || b != 47) {
                    if (b == 62) {
                        i4 = readerIndex + 1;
                        int i7 = readerIndex - 1;
                        if (i7 > -1) {
                            byte b3 = byteBuf.getByte(i7);
                            if (!z2) {
                                if (b3 == 63 || (b3 == 45 && readerIndex - 2 > -1 && byteBuf.getByte(i2) == 45)) {
                                    j--;
                                }
                            } else if (b3 == 93 && readerIndex - 2 > -1 && byteBuf.getByte(i) == 93) {
                                j--;
                                z2 = false;
                            }
                        }
                        if (z3 && j == 0) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else if (readerIndex < writerIndex - 1 && byteBuf.getByte(readerIndex + 1) == 62) {
                    j--;
                }
            }
        }
        int readerIndex2 = byteBuf.readerIndex();
        int i8 = i4 - readerIndex2;
        if (j != 0 || i8 <= 0) {
            return;
        }
        if (readerIndex2 + i8 >= writerIndex) {
            i8 = byteBuf.readableBytes();
        }
        ByteBuf extractFrame = extractFrame(byteBuf, readerIndex2 + i3, i8 - i3);
        byteBuf.skipBytes(i8);
        list.add(extractFrame);
    }

    private void fail(long j) {
        if (j > 0) {
            throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + ": " + j + " - discarded");
        }
        throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + " - discarding");
    }

    private static void fail(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.fireExceptionCaught((Throwable) new CorruptedFrameException("frame contains content before the xml starts"));
    }

    private static ByteBuf extractFrame(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.copy(i, i2);
    }

    private static boolean isCommentBlockStart(ByteBuf byteBuf, int i) {
        return i < byteBuf.writerIndex() + (-3) && byteBuf.getByte(i + 2) == 45 && byteBuf.getByte(i + 3) == 45;
    }

    private static boolean isCDATABlockStart(ByteBuf byteBuf, int i) {
        return i < byteBuf.writerIndex() + (-8) && byteBuf.getByte(i + 2) == 91 && byteBuf.getByte(i + 3) == 67 && byteBuf.getByte(i + 4) == 68 && byteBuf.getByte(i + 5) == 65 && byteBuf.getByte(i + 6) == 84 && byteBuf.getByte(i + 7) == 65 && byteBuf.getByte(i + 8) == 91;
    }
}
