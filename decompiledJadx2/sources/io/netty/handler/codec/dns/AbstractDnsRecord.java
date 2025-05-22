package io.netty.handler.codec.dns;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.IDN;
import kotlin.UShort;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractDnsRecord implements DnsRecord {
    private final short dnsClass;
    private int hashCode;
    private final String name;
    private final long timeToLive;
    private final DnsRecordType type;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDnsRecord(String str, DnsRecordType dnsRecordType, long j) {
        this(str, dnsRecordType, 1, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDnsRecord(String str, DnsRecordType dnsRecordType, int i, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("timeToLive: " + j + " (expected: >= 0)");
        }
        this.name = appendTrailingDot(IDN.toASCII((String) ObjectUtil.checkNotNull(str, "name")));
        this.type = (DnsRecordType) ObjectUtil.checkNotNull(dnsRecordType, "type");
        this.dnsClass = (short) i;
        this.timeToLive = j;
    }

    private static String appendTrailingDot(String str) {
        if (str.length() <= 0 || str.charAt(str.length() - 1) == '.') {
            return str;
        }
        return str + FilenameUtils.EXTENSION_SEPARATOR;
    }

    @Override // io.netty.handler.codec.dns.DnsRecord
    public String name() {
        return this.name;
    }

    @Override // io.netty.handler.codec.dns.DnsRecord
    public DnsRecordType type() {
        return this.type;
    }

    @Override // io.netty.handler.codec.dns.DnsRecord
    public int dnsClass() {
        return this.dnsClass & UShort.MAX_VALUE;
    }

    @Override // io.netty.handler.codec.dns.DnsRecord
    public long timeToLive() {
        return this.timeToLive;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DnsRecord)) {
            return false;
        }
        DnsRecord dnsRecord = (DnsRecord) obj;
        int i = this.hashCode;
        if (i == 0 || i == dnsRecord.hashCode()) {
            return type().intValue() == dnsRecord.type().intValue() && dnsClass() == dnsRecord.dnsClass() && name().equals(dnsRecord.name());
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = (this.name.hashCode() * 31) + (type().intValue() * 31) + dnsClass();
        this.hashCode = hashCode;
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(StringUtil.simpleClassName(this));
        sb.append('(');
        sb.append(name());
        sb.append(' ');
        sb.append(timeToLive());
        sb.append(' ');
        StringBuilder appendRecordClass = DnsMessageUtil.appendRecordClass(sb, dnsClass());
        appendRecordClass.append(' ');
        appendRecordClass.append(type().name());
        appendRecordClass.append(')');
        return sb.toString();
    }
}
