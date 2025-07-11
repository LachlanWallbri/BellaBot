package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public interface DnsRawRecord extends DnsRecord, ByteBufHolder {
    @Override // io.netty.buffer.ByteBufHolder
    DnsRawRecord copy();

    @Override // io.netty.buffer.ByteBufHolder
    DnsRawRecord duplicate();

    @Override // io.netty.buffer.ByteBufHolder
    DnsRawRecord replace(ByteBuf byteBuf);

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    DnsRawRecord retain();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    DnsRawRecord retain(int i);

    @Override // io.netty.buffer.ByteBufHolder
    DnsRawRecord retainedDuplicate();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    DnsRawRecord touch();

    @Override // io.netty.buffer.ByteBufHolder, io.netty.util.ReferenceCounted
    DnsRawRecord touch(Object obj);
}
