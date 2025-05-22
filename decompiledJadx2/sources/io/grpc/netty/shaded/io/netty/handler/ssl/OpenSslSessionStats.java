package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext;
import java.util.concurrent.locks.Lock;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslSessionStats {
    private final ReferenceCountedOpenSslContext context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslSessionStats(ReferenceCountedOpenSslContext referenceCountedOpenSslContext) {
        this.context = referenceCountedOpenSslContext;
    }

    public long number() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionNumber(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long connect() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionConnect(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long connectGood() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionConnectGood(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long connectRenegotiate() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionConnectRenegotiate(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long accept() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionAccept(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long acceptGood() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionAcceptGood(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long acceptRenegotiate() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionAcceptRenegotiate(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long hits() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionHits(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long cbHits() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionCbHits(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long misses() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionMisses(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long timeouts() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionTimeouts(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long cacheFull() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionCacheFull(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long ticketKeyFail() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionTicketKeyFail(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long ticketKeyNew() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionTicketKeyNew(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long ticketKeyRenew() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionTicketKeyRenew(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }

    public long ticketKeyResume() {
        Lock readLock = this.context.ctxLock.readLock();
        readLock.lock();
        try {
            return SSLContext.sessionTicketKeyResume(this.context.ctx);
        } finally {
            readLock.unlock();
        }
    }
}
