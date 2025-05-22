package org.jboss.netty.handler.codec.serialization;

import java.io.FilterInputStream;
import java.io.InputStream;

/* loaded from: classes7.dex */
final class SwitchableInputStream extends FilterInputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SwitchableInputStream() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void switchStream(InputStream inputStream) {
        this.in = inputStream;
    }
}
