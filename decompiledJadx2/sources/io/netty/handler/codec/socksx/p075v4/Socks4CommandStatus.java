package io.netty.handler.codec.socksx.p075v4;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Socks4CommandStatus implements Comparable<Socks4CommandStatus> {
    private final byte byteValue;
    private final String name;
    private String text;
    public static final Socks4CommandStatus SUCCESS = new Socks4CommandStatus(90, "SUCCESS");
    public static final Socks4CommandStatus REJECTED_OR_FAILED = new Socks4CommandStatus(91, "REJECTED_OR_FAILED");
    public static final Socks4CommandStatus IDENTD_UNREACHABLE = new Socks4CommandStatus(92, "IDENTD_UNREACHABLE");
    public static final Socks4CommandStatus IDENTD_AUTH_FAILURE = new Socks4CommandStatus(93, "IDENTD_AUTH_FAILURE");

    public static Socks4CommandStatus valueOf(byte b) {
        switch (b) {
            case 90:
                return SUCCESS;
            case 91:
                return REJECTED_OR_FAILED;
            case 92:
                return IDENTD_UNREACHABLE;
            case 93:
                return IDENTD_AUTH_FAILURE;
            default:
                return new Socks4CommandStatus(b);
        }
    }

    public Socks4CommandStatus(int i) {
        this(i, "UNKNOWN");
    }

    public Socks4CommandStatus(int i, String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        this.byteValue = (byte) i;
        this.name = str;
    }

    public byte byteValue() {
        return this.byteValue;
    }

    public boolean isSuccess() {
        return this.byteValue == 90;
    }

    public int hashCode() {
        return this.byteValue;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Socks4CommandStatus) && this.byteValue == ((Socks4CommandStatus) obj).byteValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(Socks4CommandStatus socks4CommandStatus) {
        return this.byteValue - socks4CommandStatus.byteValue;
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
