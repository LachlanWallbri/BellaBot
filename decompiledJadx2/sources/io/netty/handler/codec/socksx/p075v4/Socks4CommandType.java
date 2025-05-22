package io.netty.handler.codec.socksx.p075v4;

import com.google.api.client.http.HttpMethods;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class Socks4CommandType implements Comparable<Socks4CommandType> {
    private final byte byteValue;
    private final String name;
    private String text;
    public static final Socks4CommandType CONNECT = new Socks4CommandType(1, HttpMethods.CONNECT);
    public static final Socks4CommandType BIND = new Socks4CommandType(2, "BIND");

    public static Socks4CommandType valueOf(byte b) {
        if (b == 1) {
            return CONNECT;
        }
        if (b == 2) {
            return BIND;
        }
        return new Socks4CommandType(b);
    }

    public Socks4CommandType(int i) {
        this(i, "UNKNOWN");
    }

    public Socks4CommandType(int i, String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        this.byteValue = (byte) i;
        this.name = str;
    }

    public byte byteValue() {
        return this.byteValue;
    }

    public int hashCode() {
        return this.byteValue;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Socks4CommandType) && this.byteValue == ((Socks4CommandType) obj).byteValue;
    }

    @Override // java.lang.Comparable
    public int compareTo(Socks4CommandType socks4CommandType) {
        return this.byteValue - socks4CommandType.byteValue;
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
