package io.netty.handler.codec.dns;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ItemTouchHelper;
import io.netty.util.collection.IntObjectHashMap;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class DnsRecordType implements Comparable<DnsRecordType> {
    private static final String EXPECTED;
    private final int intValue;
    private final String name;
    private String text;

    /* renamed from: A */
    public static final DnsRecordType f8484A = new DnsRecordType(1, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);

    /* renamed from: NS */
    public static final DnsRecordType f8488NS = new DnsRecordType(2, "NS");
    public static final DnsRecordType CNAME = new DnsRecordType(5, "CNAME");
    public static final DnsRecordType SOA = new DnsRecordType(6, "SOA");
    public static final DnsRecordType PTR = new DnsRecordType(12, "PTR");

    /* renamed from: MX */
    public static final DnsRecordType f8487MX = new DnsRecordType(15, "MX");
    public static final DnsRecordType TXT = new DnsRecordType(16, "TXT");

    /* renamed from: RP */
    public static final DnsRecordType f8489RP = new DnsRecordType(17, "RP");
    public static final DnsRecordType AFSDB = new DnsRecordType(18, "AFSDB");
    public static final DnsRecordType SIG = new DnsRecordType(24, "SIG");
    public static final DnsRecordType KEY = new DnsRecordType(25, "KEY");
    public static final DnsRecordType AAAA = new DnsRecordType(28, "AAAA");
    public static final DnsRecordType LOC = new DnsRecordType(29, "LOC");
    public static final DnsRecordType SRV = new DnsRecordType(33, "SRV");
    public static final DnsRecordType NAPTR = new DnsRecordType(35, "NAPTR");

    /* renamed from: KX */
    public static final DnsRecordType f8486KX = new DnsRecordType(36, "KX");
    public static final DnsRecordType CERT = new DnsRecordType(37, "CERT");
    public static final DnsRecordType DNAME = new DnsRecordType(39, "DNAME");
    public static final DnsRecordType OPT = new DnsRecordType(41, "OPT");
    public static final DnsRecordType APL = new DnsRecordType(42, "APL");

    /* renamed from: DS */
    public static final DnsRecordType f8485DS = new DnsRecordType(43, "DS");
    public static final DnsRecordType SSHFP = new DnsRecordType(44, "SSHFP");
    public static final DnsRecordType IPSECKEY = new DnsRecordType(45, "IPSECKEY");
    public static final DnsRecordType RRSIG = new DnsRecordType(46, "RRSIG");
    public static final DnsRecordType NSEC = new DnsRecordType(47, "NSEC");
    public static final DnsRecordType DNSKEY = new DnsRecordType(48, "DNSKEY");
    public static final DnsRecordType DHCID = new DnsRecordType(49, "DHCID");
    public static final DnsRecordType NSEC3 = new DnsRecordType(50, "NSEC3");
    public static final DnsRecordType NSEC3PARAM = new DnsRecordType(51, "NSEC3PARAM");
    public static final DnsRecordType TLSA = new DnsRecordType(52, "TLSA");
    public static final DnsRecordType HIP = new DnsRecordType(55, "HIP");
    public static final DnsRecordType SPF = new DnsRecordType(99, "SPF");
    public static final DnsRecordType TKEY = new DnsRecordType(249, "TKEY");
    public static final DnsRecordType TSIG = new DnsRecordType(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "TSIG");
    public static final DnsRecordType IXFR = new DnsRecordType(251, "IXFR");
    public static final DnsRecordType AXFR = new DnsRecordType(252, "AXFR");
    public static final DnsRecordType ANY = new DnsRecordType(255, "ANY");
    public static final DnsRecordType CAA = new DnsRecordType(257, "CAA");

    /* renamed from: TA */
    public static final DnsRecordType f8490TA = new DnsRecordType(32768, "TA");
    public static final DnsRecordType DLV = new DnsRecordType(32769, "DLV");
    private static final Map<String, DnsRecordType> BY_NAME = new HashMap();
    private static final IntObjectHashMap<DnsRecordType> BY_TYPE = new IntObjectHashMap<>();

    static {
        DnsRecordType[] dnsRecordTypeArr = {f8484A, f8488NS, CNAME, SOA, PTR, f8487MX, TXT, f8489RP, AFSDB, SIG, KEY, AAAA, LOC, SRV, NAPTR, f8486KX, CERT, DNAME, OPT, APL, f8485DS, SSHFP, IPSECKEY, RRSIG, NSEC, DNSKEY, DHCID, NSEC3, NSEC3PARAM, TLSA, HIP, SPF, TKEY, TSIG, IXFR, AXFR, ANY, CAA, f8490TA, DLV};
        StringBuilder sb = new StringBuilder(512);
        sb.append(" (expected: ");
        for (int i = 0; i < 40; i++) {
            DnsRecordType dnsRecordType = dnsRecordTypeArr[i];
            BY_NAME.put(dnsRecordType.name(), dnsRecordType);
            BY_TYPE.put(dnsRecordType.intValue(), (int) dnsRecordType);
            sb.append(dnsRecordType.name());
            sb.append('(');
            sb.append(dnsRecordType.intValue());
            sb.append("), ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(')');
        EXPECTED = sb.toString();
    }

    public static DnsRecordType valueOf(int i) {
        DnsRecordType dnsRecordType = BY_TYPE.get(i);
        return dnsRecordType == null ? new DnsRecordType(i) : dnsRecordType;
    }

    public static DnsRecordType valueOf(String str) {
        DnsRecordType dnsRecordType = BY_NAME.get(str);
        if (dnsRecordType != null) {
            return dnsRecordType;
        }
        throw new IllegalArgumentException("name: " + str + EXPECTED);
    }

    private DnsRecordType(int i) {
        this(i, "UNKNOWN");
    }

    public DnsRecordType(int i, String str) {
        if ((65535 & i) != i) {
            throw new IllegalArgumentException("intValue: " + i + " (expected: 0 ~ 65535)");
        }
        this.intValue = i;
        this.name = str;
    }

    public String name() {
        return this.name;
    }

    public int intValue() {
        return this.intValue;
    }

    public int hashCode() {
        return this.intValue;
    }

    public boolean equals(Object obj) {
        return (obj instanceof DnsRecordType) && ((DnsRecordType) obj).intValue == this.intValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(DnsRecordType dnsRecordType) {
        return intValue() - dnsRecordType.intValue();
    }

    public String toString() {
        String str = this.text;
        if (str != null) {
            return str;
        }
        String str2 = this.name + '(' + intValue() + ')';
        this.text = str2;
        return str2;
    }
}
