package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes.dex */
public class DatagramDnsQueryEncoder extends MessageToMessageEncoder<AddressedEnvelope<DnsQuery, InetSocketAddress>> {
    private final DnsRecordEncoder recordEncoder;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, AddressedEnvelope<DnsQuery, InetSocketAddress> addressedEnvelope, List list) throws Exception {
        encode2(channelHandlerContext, addressedEnvelope, (List<Object>) list);
    }

    public DatagramDnsQueryEncoder() {
        this(DnsRecordEncoder.DEFAULT);
    }

    public DatagramDnsQueryEncoder(DnsRecordEncoder dnsRecordEncoder) {
        this.recordEncoder = (DnsRecordEncoder) ObjectUtil.checkNotNull(dnsRecordEncoder, "recordEncoder");
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, AddressedEnvelope<DnsQuery, InetSocketAddress> addressedEnvelope, List<Object> list) throws Exception {
        InetSocketAddress recipient = addressedEnvelope.recipient();
        DnsQuery content = addressedEnvelope.content();
        ByteBuf allocateBuffer = allocateBuffer(channelHandlerContext, addressedEnvelope);
        try {
            encodeHeader(content, allocateBuffer);
            encodeQuestions(content, allocateBuffer);
            encodeRecords(content, DnsSection.ADDITIONAL, allocateBuffer);
            list.add(new DatagramPacket(allocateBuffer, recipient, null));
        } catch (Throwable th) {
            allocateBuffer.release();
            throw th;
        }
    }

    protected ByteBuf allocateBuffer(ChannelHandlerContext channelHandlerContext, AddressedEnvelope<DnsQuery, InetSocketAddress> addressedEnvelope) throws Exception {
        return channelHandlerContext.alloc().ioBuffer(1024);
    }

    private static void encodeHeader(DnsQuery dnsQuery, ByteBuf byteBuf) {
        byteBuf.writeShort(dnsQuery.mo3938id());
        int byteValue = ((dnsQuery.opCode().byteValue() & 255) << 14) | 0;
        if (dnsQuery.isRecursionDesired()) {
            byteValue |= 256;
        }
        byteBuf.writeShort(byteValue);
        byteBuf.writeShort(dnsQuery.count(DnsSection.QUESTION));
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
        byteBuf.writeShort(dnsQuery.count(DnsSection.ADDITIONAL));
    }

    private void encodeQuestions(DnsQuery dnsQuery, ByteBuf byteBuf) throws Exception {
        int count = dnsQuery.count(DnsSection.QUESTION);
        for (int i = 0; i < count; i++) {
            this.recordEncoder.encodeQuestion((DnsQuestion) dnsQuery.recordAt(DnsSection.QUESTION, i), byteBuf);
        }
    }

    private void encodeRecords(DnsQuery dnsQuery, DnsSection dnsSection, ByteBuf byteBuf) throws Exception {
        int count = dnsQuery.count(dnsSection);
        for (int i = 0; i < count; i++) {
            this.recordEncoder.encodeRecord(dnsQuery.recordAt(dnsSection, i), byteBuf);
        }
    }
}
