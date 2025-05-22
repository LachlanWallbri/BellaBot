package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLHandshakeException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class JdkBaseApplicationProtocolNegotiator implements JdkApplicationProtocolNegotiator {
    private final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory listenerFactory;
    private final List<String> protocols;
    private final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory selectorFactory;
    private final JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory;
    static final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory FAIL_SELECTOR_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectorFactory() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator.1
        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelectorFactory
        public JdkApplicationProtocolNegotiator.ProtocolSelector newSelector(SSLEngine sSLEngine, Set<String> set) {
            return new FailProtocolSelector((JdkSslEngine) sSLEngine, set);
        }
    };
    static final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory NO_FAIL_SELECTOR_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectorFactory() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator.2
        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelectorFactory
        public JdkApplicationProtocolNegotiator.ProtocolSelector newSelector(SSLEngine sSLEngine, Set<String> set) {
            return new NoFailProtocolSelector((JdkSslEngine) sSLEngine, set);
        }
    };
    static final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory FAIL_SELECTION_LISTENER_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator.3
        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory
        public JdkApplicationProtocolNegotiator.ProtocolSelectionListener newListener(SSLEngine sSLEngine, List<String> list) {
            return new FailProtocolSelectionListener((JdkSslEngine) sSLEngine, list);
        }
    };
    static final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory NO_FAIL_SELECTION_LISTENER_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator.4
        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory
        public JdkApplicationProtocolNegotiator.ProtocolSelectionListener newListener(SSLEngine sSLEngine, List<String> list) {
            return new NoFailProtocolSelectionListener((JdkSslEngine) sSLEngine, list);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory sslEngineWrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, Iterable<String> iterable) {
        this(sslEngineWrapperFactory, protocolSelectorFactory, protocolSelectionListenerFactory, ApplicationProtocolUtil.toList(iterable));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory sslEngineWrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, String... strArr) {
        this(sslEngineWrapperFactory, protocolSelectorFactory, protocolSelectionListenerFactory, ApplicationProtocolUtil.toList(strArr));
    }

    private JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory sslEngineWrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, List<String> list) {
        this.wrapperFactory = (JdkApplicationProtocolNegotiator.SslEngineWrapperFactory) ObjectUtil.checkNotNull(sslEngineWrapperFactory, "wrapperFactory");
        this.selectorFactory = (JdkApplicationProtocolNegotiator.ProtocolSelectorFactory) ObjectUtil.checkNotNull(protocolSelectorFactory, "selectorFactory");
        this.listenerFactory = (JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory) ObjectUtil.checkNotNull(protocolSelectionListenerFactory, "listenerFactory");
        this.protocols = Collections.unmodifiableList((List) ObjectUtil.checkNotNull(list, "protocols"));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.ApplicationProtocolNegotiator
    public List<String> protocols() {
        return this.protocols;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator
    public JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory() {
        return this.selectorFactory;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator
    public JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolListenerFactory() {
        return this.listenerFactory;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator
    public JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory() {
        return this.wrapperFactory;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static class NoFailProtocolSelector implements JdkApplicationProtocolNegotiator.ProtocolSelector {
        private final JdkSslEngine engineWrapper;
        private final Set<String> supportedProtocols;

        NoFailProtocolSelector(JdkSslEngine jdkSslEngine, Set<String> set) {
            this.engineWrapper = jdkSslEngine;
            this.supportedProtocols = set;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelector
        public void unsupported() {
            this.engineWrapper.setNegotiatedApplicationProtocol(null);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelector
        public String select(List<String> list) throws Exception {
            for (String str : this.supportedProtocols) {
                if (list.contains(str)) {
                    this.engineWrapper.setNegotiatedApplicationProtocol(str);
                    return str;
                }
            }
            return noSelectMatchFound();
        }

        public String noSelectMatchFound() throws Exception {
            this.engineWrapper.setNegotiatedApplicationProtocol(null);
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class FailProtocolSelector extends NoFailProtocolSelector {
        FailProtocolSelector(JdkSslEngine jdkSslEngine, Set<String> set) {
            super(jdkSslEngine, set);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator.NoFailProtocolSelector
        public String noSelectMatchFound() throws Exception {
            throw new SSLHandshakeException("Selected protocol is not supported");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class NoFailProtocolSelectionListener implements JdkApplicationProtocolNegotiator.ProtocolSelectionListener {
        private final JdkSslEngine engineWrapper;
        private final List<String> supportedProtocols;

        protected void noSelectedMatchFound(String str) throws Exception {
        }

        NoFailProtocolSelectionListener(JdkSslEngine jdkSslEngine, List<String> list) {
            this.engineWrapper = jdkSslEngine;
            this.supportedProtocols = list;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelectionListener
        public void unsupported() {
            this.engineWrapper.setNegotiatedApplicationProtocol(null);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkApplicationProtocolNegotiator.ProtocolSelectionListener
        public void selected(String str) throws Exception {
            if (this.supportedProtocols.contains(str)) {
                this.engineWrapper.setNegotiatedApplicationProtocol(str);
            } else {
                noSelectedMatchFound(str);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class FailProtocolSelectionListener extends NoFailProtocolSelectionListener {
        FailProtocolSelectionListener(JdkSslEngine jdkSslEngine, List<String> list) {
            super(jdkSslEngine, list);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.ssl.JdkBaseApplicationProtocolNegotiator.NoFailProtocolSelectionListener
        protected void noSelectedMatchFound(String str) throws Exception {
            throw new SSLHandshakeException("No compatible protocols found");
        }
    }
}
