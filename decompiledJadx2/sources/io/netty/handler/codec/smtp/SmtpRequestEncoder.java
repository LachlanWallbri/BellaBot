package io.netty.handler.codec.smtp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SmtpRequestEncoder extends MessageToMessageEncoder<Object> {
    private static final int CRLF_SHORT = 3338;
    private static final ByteBuf DOT_CRLF_BUFFER = Unpooled.unreleasableBuffer(Unpooled.directBuffer(3).writeByte(46).writeByte(13).writeByte(10));

    /* renamed from: SP */
    private static final byte f8517SP = 32;
    private boolean contentExpected;

    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return (obj instanceof SmtpRequest) || (obj instanceof SmtpContent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, Object obj, List<Object> list) throws Exception {
        boolean z = false;
        if (obj instanceof SmtpRequest) {
            SmtpRequest smtpRequest = (SmtpRequest) obj;
            if (this.contentExpected) {
                if (smtpRequest.command().equals(SmtpCommand.RSET)) {
                    this.contentExpected = false;
                } else {
                    throw new IllegalStateException("SmtpContent expected");
                }
            }
            ByteBuf buffer = channelHandlerContext.alloc().buffer();
            try {
                smtpRequest.command().encode(buffer);
                writeParameters(smtpRequest.parameters(), buffer);
                ByteBufUtil.writeShortBE(buffer, CRLF_SHORT);
                list.add(buffer);
                try {
                    if (smtpRequest.command().isContentExpected()) {
                        this.contentExpected = true;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (z) {
                        buffer.release();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                z = true;
            }
        }
        if (obj instanceof SmtpContent) {
            if (!this.contentExpected) {
                throw new IllegalStateException("No SmtpContent expected");
            }
            list.add(((SmtpContent) obj).content().retain());
            if (obj instanceof LastSmtpContent) {
                list.add(DOT_CRLF_BUFFER.retainedDuplicate());
                this.contentExpected = false;
            }
        }
    }

    private static void writeParameters(List<CharSequence> list, ByteBuf byteBuf) {
        if (list.isEmpty()) {
            return;
        }
        byteBuf.writeByte(32);
        if (list instanceof RandomAccess) {
            int size = list.size() - 1;
            for (int i = 0; i < size; i++) {
                ByteBufUtil.writeAscii(byteBuf, list.get(i));
                byteBuf.writeByte(32);
            }
            ByteBufUtil.writeAscii(byteBuf, list.get(size));
            return;
        }
        Iterator<CharSequence> it = list.iterator();
        while (true) {
            ByteBufUtil.writeAscii(byteBuf, it.next());
            if (!it.hasNext()) {
                return;
            } else {
                byteBuf.writeByte(32);
            }
        }
    }
}
