package io.grpc.netty.shaded.io.netty.channel.socket.nio;

import io.grpc.netty.shaded.io.netty.channel.socket.InternetProtocolFamily;
import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class ProtocolFamilyConverter {
    private ProtocolFamilyConverter() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.channel.socket.nio.ProtocolFamilyConverter$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64241 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$socket$InternetProtocolFamily = new int[InternetProtocolFamily.values().length];

        static {
            try {
                $SwitchMap$io$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv6.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static ProtocolFamily convert(InternetProtocolFamily internetProtocolFamily) {
        int i = C64241.$SwitchMap$io$netty$channel$socket$InternetProtocolFamily[internetProtocolFamily.ordinal()];
        if (i == 1) {
            return StandardProtocolFamily.INET;
        }
        if (i == 2) {
            return StandardProtocolFamily.INET6;
        }
        throw new IllegalArgumentException();
    }
}
