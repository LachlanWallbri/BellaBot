package org.jboss.netty.handler.codec.protobuf;

import com.google.protobuf.CodedInputStream;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.CorruptedFrameException;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/* loaded from: classes7.dex */
public class ProtobufVarint32FrameDecoder extends FrameDecoder {
    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        channelBuffer.markReaderIndex();
        byte[] bArr = new byte[5];
        for (int i = 0; i < bArr.length; i++) {
            if (!channelBuffer.readable()) {
                channelBuffer.resetReaderIndex();
                return null;
            }
            bArr[i] = channelBuffer.readByte();
            if (bArr[i] >= 0) {
                int readRawVarint32 = CodedInputStream.newInstance(bArr, 0, i + 1).readRawVarint32();
                if (readRawVarint32 < 0) {
                    throw new CorruptedFrameException("negative length: " + readRawVarint32);
                }
                if (channelBuffer.readableBytes() < readRawVarint32) {
                    channelBuffer.resetReaderIndex();
                    return null;
                }
                return channelBuffer.readBytes(readRawVarint32);
            }
        }
        throw new CorruptedFrameException("length wider than 32-bit");
    }
}
