package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public interface DnsRecordDecoder {
    public static final DnsRecordDecoder DEFAULT = new DefaultDnsRecordDecoder();

    DnsQuestion decodeQuestion(ByteBuf byteBuf) throws Exception;

    <T extends DnsRecord> T decodeRecord(ByteBuf byteBuf) throws Exception;
}
