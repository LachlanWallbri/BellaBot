package io.netty.handler.codec.smtp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SmtpResponseDecoder extends LineBasedFrameDecoder {
    private List<CharSequence> details;

    public SmtpResponseDecoder(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.LineBasedFrameDecoder
    public SmtpResponse decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        ByteBuf byteBuf2 = (ByteBuf) super.decode(channelHandlerContext, byteBuf);
        if (byteBuf2 == null) {
            return null;
        }
        try {
            int readableBytes = byteBuf2.readableBytes();
            int readerIndex = byteBuf2.readerIndex();
            if (readableBytes < 3) {
                throw newDecoderException(byteBuf, readerIndex, readableBytes);
            }
            int parseCode = parseCode(byteBuf2);
            byte readByte = byteBuf2.readByte();
            String byteBuf3 = byteBuf2.isReadable() ? byteBuf2.toString(CharsetUtil.US_ASCII) : null;
            List list = this.details;
            if (readByte == 32) {
                this.details = null;
                if (list == null) {
                    list = byteBuf3 == null ? Collections.emptyList() : Collections.singletonList(byteBuf3);
                } else if (byteBuf3 != null) {
                    list.add(byteBuf3);
                }
                return new DefaultSmtpResponse(parseCode, (List<CharSequence>) list);
            }
            if (readByte != 45) {
                throw newDecoderException(byteBuf, readerIndex, readableBytes);
            }
            if (byteBuf3 != null) {
                if (list == null) {
                    list = new ArrayList(4);
                    this.details = list;
                }
                list.add(byteBuf3);
            }
            return null;
        } finally {
            byteBuf2.release();
        }
    }

    private static DecoderException newDecoderException(ByteBuf byteBuf, int i, int i2) {
        return new DecoderException("Received invalid line: '" + byteBuf.toString(i, i2, CharsetUtil.US_ASCII) + '\'');
    }

    private static int parseCode(ByteBuf byteBuf) {
        return (parseNumber(byteBuf.readByte()) * 100) + (parseNumber(byteBuf.readByte()) * 10) + parseNumber(byteBuf.readByte());
    }

    private static int parseNumber(byte b) {
        return Character.digit((char) b, 10);
    }
}
