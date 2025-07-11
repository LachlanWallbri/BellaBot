package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes.dex */
public class DatagramDnsQueryDecoder extends MessageToMessageDecoder<DatagramPacket> {
    private final DnsRecordDecoder recordDecoder;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageDecoder
    public /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List list) throws Exception {
        decode2(channelHandlerContext, datagramPacket, (List<Object>) list);
    }

    public DatagramDnsQueryDecoder() {
        this(DnsRecordDecoder.DEFAULT);
    }

    public DatagramDnsQueryDecoder(DnsRecordDecoder dnsRecordDecoder) {
        this.recordDecoder = (DnsRecordDecoder) ObjectUtil.checkNotNull(dnsRecordDecoder, "recordDecoder");
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        ByteBuf byteBuf = (ByteBuf) datagramPacket.content();
        DnsQuery newQuery = newQuery(datagramPacket, byteBuf);
        try {
            int readUnsignedShort = byteBuf.readUnsignedShort();
            int readUnsignedShort2 = byteBuf.readUnsignedShort();
            int readUnsignedShort3 = byteBuf.readUnsignedShort();
            int readUnsignedShort4 = byteBuf.readUnsignedShort();
            decodeQuestions(newQuery, byteBuf, readUnsignedShort);
            decodeRecords(newQuery, DnsSection.ANSWER, byteBuf, readUnsignedShort2);
            decodeRecords(newQuery, DnsSection.AUTHORITY, byteBuf, readUnsignedShort3);
            decodeRecords(newQuery, DnsSection.ADDITIONAL, byteBuf, readUnsignedShort4);
            list.add(newQuery);
        } catch (Throwable th) {
            newQuery.release();
            throw th;
        }
    }

    private static DnsQuery newQuery(DatagramPacket datagramPacket, ByteBuf byteBuf) {
        int readUnsignedShort = byteBuf.readUnsignedShort();
        int readUnsignedShort2 = byteBuf.readUnsignedShort();
        if ((readUnsignedShort2 >> 15) == 1) {
            throw new CorruptedFrameException("not a query");
        }
        DatagramDnsQuery datagramDnsQuery = new DatagramDnsQuery(datagramPacket.sender(), datagramPacket.recipient(), readUnsignedShort, DnsOpCode.valueOf((byte) ((readUnsignedShort2 >> 11) & 15)));
        datagramDnsQuery.setRecursionDesired(((readUnsignedShort2 >> 8) & 1) == 1);
        datagramDnsQuery.setZ((readUnsignedShort2 >> 4) & 7);
        return datagramDnsQuery;
    }

    private void decodeQuestions(DnsQuery dnsQuery, ByteBuf byteBuf, int i) throws Exception {
        while (i > 0) {
            dnsQuery.addRecord(DnsSection.QUESTION, (DnsRecord) this.recordDecoder.decodeQuestion(byteBuf));
            i--;
        }
    }

    private void decodeRecords(DnsQuery dnsQuery, DnsSection dnsSection, ByteBuf byteBuf, int i) throws Exception {
        while (i > 0) {
            DnsRecord decodeRecord = this.recordDecoder.decodeRecord(byteBuf);
            if (decodeRecord == null) {
                return;
            }
            dnsQuery.addRecord(dnsSection, decodeRecord);
            i--;
        }
    }
}
