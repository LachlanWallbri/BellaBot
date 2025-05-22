package io.netty.channel.socket.nio;

import io.netty.channel.socket.InternetProtocolFamily;
import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
final class ProtocolFamilyConverter {
    private ProtocolFamilyConverter() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* renamed from: io.netty.channel.socket.nio.ProtocolFamilyConverter$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C70821 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$socket$InternetProtocolFamily;

        static {
            int[] iArr = new int[InternetProtocolFamily.values().length];
            $SwitchMap$io$netty$channel$socket$InternetProtocolFamily = iArr;
            try {
                iArr[InternetProtocolFamily.IPv4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$channel$socket$InternetProtocolFamily[InternetProtocolFamily.IPv6.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static ProtocolFamily convert(InternetProtocolFamily internetProtocolFamily) {
        int i = C70821.$SwitchMap$io$netty$channel$socket$InternetProtocolFamily[internetProtocolFamily.ordinal()];
        if (i == 1) {
            return StandardProtocolFamily.INET;
        }
        if (i == 2) {
            return StandardProtocolFamily.INET6;
        }
        throw new IllegalArgumentException();
    }
}
