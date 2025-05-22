package io.netty.handler.ssl;

import io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.util.LinkedHashSet;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import org.eclipse.jetty.npn.NextProtoNego;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class JettyNpnSslEngine extends JdkSslEngine {
    private static boolean available;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAvailable() {
        updateAvailability();
        return available;
    }

    private static void updateAvailability() {
        if (available) {
            return;
        }
        try {
            Class.forName("sun.security.ssl.NextProtoNegoExtension", true, null);
            available = true;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JettyNpnSslEngine(SSLEngine sSLEngine, final JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
        super(sSLEngine);
        ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator, "applicationNegotiator");
        if (z) {
            final JdkApplicationProtocolNegotiator.ProtocolSelectionListener protocolSelectionListener = (JdkApplicationProtocolNegotiator.ProtocolSelectionListener) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolListenerFactory().newListener(this, jdkApplicationProtocolNegotiator.protocols()), "protocolListener");
            NextProtoNego.put(sSLEngine, new NextProtoNego.ServerProvider() { // from class: io.netty.handler.ssl.JettyNpnSslEngine.1
                public void unsupported() {
                    protocolSelectionListener.unsupported();
                }

                public List<String> protocols() {
                    return jdkApplicationProtocolNegotiator.protocols();
                }

                public void protocolSelected(String str) {
                    try {
                        protocolSelectionListener.selected(str);
                    } catch (Throwable th) {
                        PlatformDependent.throwException(th);
                    }
                }
            });
        } else {
            final JdkApplicationProtocolNegotiator.ProtocolSelector protocolSelector = (JdkApplicationProtocolNegotiator.ProtocolSelector) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(jdkApplicationProtocolNegotiator.protocols())), "protocolSelector");
            NextProtoNego.put(sSLEngine, new NextProtoNego.ClientProvider() { // from class: io.netty.handler.ssl.JettyNpnSslEngine.2
                public boolean supports() {
                    return true;
                }

                public void unsupported() {
                    protocolSelector.unsupported();
                }

                public String selectProtocol(List<String> list) {
                    try {
                        return protocolSelector.select(list);
                    } catch (Throwable th) {
                        PlatformDependent.throwException(th);
                        return null;
                    }
                }
            });
        }
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public void closeInbound() throws SSLException {
        NextProtoNego.remove(getWrappedEngine());
        super.closeInbound();
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public void closeOutbound() {
        NextProtoNego.remove(getWrappedEngine());
        super.closeOutbound();
    }
}
