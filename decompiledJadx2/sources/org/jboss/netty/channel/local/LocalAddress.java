package org.jboss.netty.channel.local;

import java.net.SocketAddress;

/* loaded from: classes7.dex */
public final class LocalAddress extends SocketAddress implements Comparable<LocalAddress> {
    public static final String EPHEMERAL = "ephemeral";
    private static final long serialVersionUID = -3601961747680808645L;
    private final boolean ephemeral;

    /* renamed from: id */
    private final String f10020id;

    public LocalAddress(int i) {
        this(String.valueOf(i));
    }

    public LocalAddress(String str) {
        if (str == null) {
            throw new NullPointerException("id");
        }
        String lowerCase = str.trim().toLowerCase();
        if (lowerCase.length() == 0) {
            throw new IllegalArgumentException("empty id");
        }
        this.f10020id = lowerCase;
        this.ephemeral = lowerCase.equals(EPHEMERAL);
    }

    public String getId() {
        return this.f10020id;
    }

    public boolean isEphemeral() {
        return this.ephemeral;
    }

    public int hashCode() {
        if (this.ephemeral) {
            return System.identityHashCode(this);
        }
        return this.f10020id.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocalAddress)) {
            return false;
        }
        if (this.ephemeral) {
            return this == obj;
        }
        return getId().equals(((LocalAddress) obj).getId());
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalAddress localAddress) {
        if (this.ephemeral) {
            if (!localAddress.ephemeral) {
                return 1;
            }
            if (this == localAddress) {
                return 0;
            }
            int identityHashCode = System.identityHashCode(this);
            int identityHashCode2 = System.identityHashCode(localAddress);
            if (identityHashCode < identityHashCode2) {
                return -1;
            }
            if (identityHashCode > identityHashCode2) {
                return 1;
            }
            throw new Error("Two different ephemeral addresses have same identityHashCode.");
        }
        if (localAddress.ephemeral) {
            return -1;
        }
        return getId().compareTo(localAddress.getId());
    }

    public String toString() {
        return "local:" + getId();
    }
}
