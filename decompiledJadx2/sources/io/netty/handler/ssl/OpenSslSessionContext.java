package io.netty.handler.ssl;

import io.netty.internal.tcnative.SSL;
import io.netty.internal.tcnative.SSLContext;
import io.netty.internal.tcnative.SessionTicketKey;
import io.netty.util.internal.ObjectUtil;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class OpenSslSessionContext implements SSLSessionContext {
    private static final Enumeration<byte[]> EMPTY = new EmptyEnumeration();
    final ReferenceCountedOpenSslContext context;
    private final OpenSslSessionStats stats;

    public abstract boolean isSessionCacheEnabled();

    public abstract void setSessionCacheEnabled(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslSessionContext(ReferenceCountedOpenSslContext referenceCountedOpenSslContext) {
        this.context = referenceCountedOpenSslContext;
        this.stats = new OpenSslSessionStats(referenceCountedOpenSslContext);
    }

    @Override // javax.net.ssl.SSLSessionContext
    public SSLSession getSession(byte[] bArr) {
        if (bArr != null) {
            return null;
        }
        throw new NullPointerException("bytes");
    }

    @Override // javax.net.ssl.SSLSessionContext
    public Enumeration<byte[]> getIds() {
        return EMPTY;
    }

    @Deprecated
    public void setTicketKeys(byte[] bArr) {
        if (bArr.length % 48 != 0) {
            throw new IllegalArgumentException("keys.length % 48 != 0");
        }
        int length = bArr.length / 48;
        SessionTicketKey[] sessionTicketKeyArr = new SessionTicketKey[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i2, 16);
            int i3 = i2 + 16;
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, i3, 16);
            int i4 = i + 16;
            byte[] copyOfRange3 = Arrays.copyOfRange(bArr, i3, 16);
            i2 = i3 + 16;
            sessionTicketKeyArr[i4] = new SessionTicketKey(copyOfRange, copyOfRange2, copyOfRange3);
            i = i4 + 1;
        }
        Lock writeLock = this.context.ctxLock.writeLock();
        writeLock.lock();
        try {
            SSLContext.clearOptions(this.context.ctx, SSL.SSL_OP_NO_TICKET);
            SSLContext.setSessionTicketKeys(this.context.ctx, sessionTicketKeyArr);
        } finally {
            writeLock.unlock();
        }
    }

    public void setTicketKeys(OpenSslSessionTicketKey... openSslSessionTicketKeyArr) {
        ObjectUtil.checkNotNull(openSslSessionTicketKeyArr, "keys");
        int length = openSslSessionTicketKeyArr.length;
        SessionTicketKey[] sessionTicketKeyArr = new SessionTicketKey[length];
        for (int i = 0; i < length; i++) {
            sessionTicketKeyArr[i] = openSslSessionTicketKeyArr[i].key;
        }
        Lock writeLock = this.context.ctxLock.writeLock();
        writeLock.lock();
        try {
            SSLContext.clearOptions(this.context.ctx, SSL.SSL_OP_NO_TICKET);
            SSLContext.setSessionTicketKeys(this.context.ctx, sessionTicketKeyArr);
        } finally {
            writeLock.unlock();
        }
    }

    public OpenSslSessionStats stats() {
        return this.stats;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private static final class EmptyEnumeration implements Enumeration<byte[]> {
        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        private EmptyEnumeration() {
        }

        @Override // java.util.Enumeration
        public byte[] nextElement() {
            throw new NoSuchElementException();
        }
    }
}
