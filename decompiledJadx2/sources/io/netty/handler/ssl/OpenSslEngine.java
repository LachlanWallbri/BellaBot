package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class OpenSslEngine extends ReferenceCountedOpenSslEngine {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslEngine(OpenSslContext openSslContext, ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        super(openSslContext, byteBufAllocator, str, i, z, false);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        OpenSsl.releaseIfNeeded(this);
    }
}
