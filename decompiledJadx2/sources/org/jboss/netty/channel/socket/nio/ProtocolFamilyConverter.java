package org.jboss.netty.channel.socket.nio;

import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;
import org.jboss.netty.channel.socket.InternetProtocolFamily;

/* loaded from: classes7.dex */
final class ProtocolFamilyConverter {
    private ProtocolFamilyConverter() {
    }

    /* renamed from: org.jboss.netty.channel.socket.nio.ProtocolFamilyConverter$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C86981 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily = new int[InternetProtocolFamily.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv6.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static ProtocolFamily convert(InternetProtocolFamily internetProtocolFamily) {
        int i = C86981.$SwitchMap$org$jboss$netty$channel$socket$InternetProtocolFamily[internetProtocolFamily.ordinal()];
        if (i == 1) {
            return StandardProtocolFamily.INET;
        }
        if (i == 2) {
            return StandardProtocolFamily.INET6;
        }
        throw new IllegalArgumentException();
    }
}
