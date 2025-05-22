package org.jboss.netty.handler.ssl;

import javax.net.ssl.SSLException;

/* loaded from: classes7.dex */
public class NotSslRecordException extends SSLException {
    private static final long serialVersionUID = -4316784434770656841L;

    public NotSslRecordException(String str) {
        super(str);
    }
}
