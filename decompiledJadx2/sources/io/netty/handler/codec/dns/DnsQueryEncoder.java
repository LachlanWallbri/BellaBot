package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes7.dex */
final class DnsQueryEncoder {
    private final DnsRecordEncoder recordEncoder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DnsQueryEncoder(DnsRecordEncoder dnsRecordEncoder) {
        this.recordEncoder = (DnsRecordEncoder) ObjectUtil.checkNotNull(dnsRecordEncoder, "recordEncoder");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void encode(DnsQuery dnsQuery, ByteBuf byteBuf) throws Exception {
        encodeHeader(dnsQuery, byteBuf);
        encodeQuestions(dnsQuery, byteBuf);
        encodeRecords(dnsQuery, DnsSection.ADDITIONAL, byteBuf);
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
