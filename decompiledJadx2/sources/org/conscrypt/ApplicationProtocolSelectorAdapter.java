package org.conscrypt;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocket;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
final class ApplicationProtocolSelectorAdapter {
    private static final int NO_PROTOCOL_SELECTED = -1;
    private final SSLEngine engine;
    private final ApplicationProtocolSelector selector;
    private final SSLSocket socket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApplicationProtocolSelectorAdapter(SSLEngine sSLEngine, ApplicationProtocolSelector applicationProtocolSelector) {
        this.engine = (SSLEngine) Preconditions.checkNotNull(sSLEngine, "engine");
        this.socket = null;
        this.selector = (ApplicationProtocolSelector) Preconditions.checkNotNull(applicationProtocolSelector, "selector");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApplicationProtocolSelectorAdapter(SSLSocket sSLSocket, ApplicationProtocolSelector applicationProtocolSelector) {
        this.engine = null;
        this.socket = (SSLSocket) Preconditions.checkNotNull(sSLSocket, "socket");
        this.selector = (ApplicationProtocolSelector) Preconditions.checkNotNull(applicationProtocolSelector, "selector");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int selectApplicationProtocol(byte[] bArr) {
        String selectApplicationProtocol;
        if (bArr != null && bArr.length != 0) {
            List<String> asList = Arrays.asList(SSLUtils.decodeProtocols(bArr));
            SSLEngine sSLEngine = this.engine;
            if (sSLEngine != null) {
                selectApplicationProtocol = this.selector.selectApplicationProtocol(sSLEngine, asList);
            } else {
                selectApplicationProtocol = this.selector.selectApplicationProtocol(this.socket, asList);
            }
            if (selectApplicationProtocol != null && !selectApplicationProtocol.isEmpty()) {
                int i = 0;
                for (String str : asList) {
                    if (selectApplicationProtocol.equals(str)) {
                        return i;
                    }
                    i += str.length() + 1;
                }
            }
        }
        return -1;
    }
}
