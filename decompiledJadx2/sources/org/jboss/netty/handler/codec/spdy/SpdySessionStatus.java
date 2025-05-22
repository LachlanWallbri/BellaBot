package org.jboss.netty.handler.codec.spdy;

/* loaded from: classes7.dex */
public class SpdySessionStatus implements Comparable<SpdySessionStatus> {
    private final int code;
    private final String statusPhrase;

    /* renamed from: OK */
    public static final SpdySessionStatus f10045OK = new SpdySessionStatus(0, "OK");
    public static final SpdySessionStatus PROTOCOL_ERROR = new SpdySessionStatus(1, "PROTOCOL_ERROR");
    public static final SpdySessionStatus INTERNAL_ERROR = new SpdySessionStatus(11, "INTERNAL_ERROR");

    public static SpdySessionStatus valueOf(int i) {
        if (i == 0) {
            return f10045OK;
        }
        if (i == 1) {
            return PROTOCOL_ERROR;
        }
        if (i == 11) {
            return INTERNAL_ERROR;
        }
        return new SpdySessionStatus(i, "UNKNOWN (" + i + ')');
    }

    public SpdySessionStatus(int i, String str) {
        if (str == null) {
            throw new NullPointerException("statusPhrase");
        }
        this.code = i;
        this.statusPhrase = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getStatusPhrase() {
        return this.statusPhrase;
    }

    public int hashCode() {
        return getCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof SpdySessionStatus) && getCode() == ((SpdySessionStatus) obj).getCode();
    }

    public String toString() {
        return getStatusPhrase();
    }

    @Override // java.lang.Comparable
    public int compareTo(SpdySessionStatus spdySessionStatus) {
        return getCode() - spdySessionStatus.getCode();
    }
}
