package io.netty.handler.codec.dns;

import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractDnsOptPseudoRrRecord extends AbstractDnsRecord implements DnsOptPseudoRecord {
    private static long packIntoLong(int i, int i2) {
        return (((i & 255) << 24) | ((i2 & 255) << 16) | 0 | 0) & 4294967295L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDnsOptPseudoRrRecord(int i, int i2, int i3) {
        super("", DnsRecordType.OPT, i, packIntoLong(i2, i3));
    }

    protected AbstractDnsOptPseudoRrRecord(int i) {
        super("", DnsRecordType.OPT, i, 0L);
    }

    @Override // io.netty.handler.codec.dns.DnsOptPseudoRecord
    public int extendedRcode() {
        return (short) ((((int) timeToLive()) >> 24) & 255);
    }

    @Override // io.netty.handler.codec.dns.DnsOptPseudoRecord
    public int version() {
        return (short) ((((int) timeToLive()) >> 16) & 255);
    }

    @Override // io.netty.handler.codec.dns.DnsOptPseudoRecord
    public int flags() {
        return (short) (((short) timeToLive()) & 255);
    }

    @Override // io.netty.handler.codec.dns.AbstractDnsRecord
    public String toString() {
        return toStringBuilder().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(StringUtil.simpleClassName(this));
        sb.append('(');
        sb.append("OPT flags:");
        sb.append(flags());
        sb.append(" version:");
        sb.append(version());
        sb.append(" extendedRecode:");
        sb.append(extendedRcode());
        sb.append(" udp:");
        sb.append(dnsClass());
        sb.append(')');
        return sb;
    }
}
