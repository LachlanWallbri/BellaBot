package io.netty.channel.local;

import io.netty.channel.Channel;
import java.net.SocketAddress;
import org.mozilla.javascript.typedarrays.Conversions;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class LocalAddress extends SocketAddress implements Comparable<LocalAddress> {
    public static final LocalAddress ANY = new LocalAddress("ANY");
    private static final long serialVersionUID = 4644331421130916435L;

    /* renamed from: id */
    private final String f8452id;
    private final String strVal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalAddress(Channel channel) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("local:E");
        sb.append(Long.toHexString((channel.hashCode() & 4294967295L) | Conversions.THIRTYTWO_BIT));
        sb.setCharAt(7, ':');
        this.f8452id = sb.substring(6);
        this.strVal = sb.toString();
    }

    public LocalAddress(String str) {
        if (str == null) {
            throw new NullPointerException("id");
        }
        String lowerCase = str.trim().toLowerCase();
        if (lowerCase.isEmpty()) {
            throw new IllegalArgumentException("empty id");
        }
        this.f8452id = lowerCase;
        this.strVal = "local:" + lowerCase;
    }

    /* renamed from: id */
    public String m3934id() {
        return this.f8452id;
    }

    public int hashCode() {
        return this.f8452id.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof LocalAddress) {
            return this.f8452id.equals(((LocalAddress) obj).f8452id);
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalAddress localAddress) {
        return this.f8452id.compareTo(localAddress.f8452id);
    }

    public String toString() {
        return this.strVal;
    }
}
