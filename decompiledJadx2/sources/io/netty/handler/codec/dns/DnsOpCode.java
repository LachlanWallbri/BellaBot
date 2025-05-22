package io.netty.handler.codec.dns;

import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class DnsOpCode implements Comparable<DnsOpCode> {
    private final byte byteValue;
    private final String name;
    private String text;
    public static final DnsOpCode QUERY = new DnsOpCode(0, "QUERY");
    public static final DnsOpCode IQUERY = new DnsOpCode(1, "IQUERY");
    public static final DnsOpCode STATUS = new DnsOpCode(2, "STATUS");
    public static final DnsOpCode NOTIFY = new DnsOpCode(4, "NOTIFY");
    public static final DnsOpCode UPDATE = new DnsOpCode(5, "UPDATE");

    public static DnsOpCode valueOf(int i) {
        if (i == 0) {
            return QUERY;
        }
        if (i == 1) {
            return IQUERY;
        }
        if (i == 2) {
            return STATUS;
        }
        if (i == 4) {
            return NOTIFY;
        }
        if (i == 5) {
            return UPDATE;
        }
        return new DnsOpCode(i);
    }

    private DnsOpCode(int i) {
        this(i, "UNKNOWN");
    }

    public DnsOpCode(int i, String str) {
        this.byteValue = (byte) i;
        this.name = (String) ObjectUtil.checkNotNull(str, "name");
    }

    public byte byteValue() {
        return this.byteValue;
    }

    public int hashCode() {
        return this.byteValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DnsOpCode) && this.byteValue == ((DnsOpCode) obj).byteValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(DnsOpCode dnsOpCode) {
        return this.byteValue - dnsOpCode.byteValue;
    }

    public String toString() {
        String str = this.text;
        if (str != null) {
            return str;
        }
        String str2 = this.name + '(' + (this.byteValue & 255) + ')';
        this.text = str2;
        return str2;
    }
}
