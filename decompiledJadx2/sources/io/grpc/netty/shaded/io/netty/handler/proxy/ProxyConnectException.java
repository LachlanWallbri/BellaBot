package io.grpc.netty.shaded.io.netty.handler.proxy;

import java.net.ConnectException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ProxyConnectException extends ConnectException {
    private static final long serialVersionUID = 5211364632246265538L;

    public ProxyConnectException() {
    }

    public ProxyConnectException(String str) {
        super(str);
    }

    public ProxyConnectException(Throwable th) {
        initCause(th);
    }

    public ProxyConnectException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
