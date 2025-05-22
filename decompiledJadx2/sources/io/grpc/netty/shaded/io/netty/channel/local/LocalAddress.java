package io.grpc.netty.shaded.io.netty.channel.local;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.SocketAddress;
import org.mozilla.javascript.typedarrays.Conversions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class LocalAddress extends SocketAddress implements Comparable<LocalAddress> {
    public static final LocalAddress ANY = new LocalAddress("ANY");
    private static final long serialVersionUID = 4644331421130916435L;

    /* renamed from: id */
    private final String f8338id;
    private final String strVal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalAddress(Channel channel) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("local:E");
        sb.append(Long.toHexString((channel.hashCode() & 4294967295L) | Conversions.THIRTYTWO_BIT));
        sb.setCharAt(7, ':');
        this.f8338id = sb.substring(6);
        this.strVal = sb.toString();
    }

    public LocalAddress(String str) {
        ObjectUtil.checkNotNull(str, "id");
        String lowerCase = str.trim().toLowerCase();
        if (lowerCase.isEmpty()) {
            throw new IllegalArgumentException("empty id");
        }
        this.f8338id = lowerCase;
        this.strVal = "local:" + lowerCase;
    }

    /* renamed from: id */
    public String m3911id() {
        return this.f8338id;
    }

    public int hashCode() {
        return this.f8338id.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof LocalAddress) {
            return this.f8338id.equals(((LocalAddress) obj).f8338id);
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalAddress localAddress) {
        return this.f8338id.compareTo(localAddress.f8338id);
    }

    public String toString() {
        return this.strVal;
    }
}
