package io.grpc;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class StatusException extends Exception {
    private static final long serialVersionUID = -660954903976144640L;
    private final boolean fillInStackTrace;
    private final Status status;
    private final Metadata trailers;

    public StatusException(Status status) {
        this(status, null);
    }

    public StatusException(Status status, @Nullable Metadata metadata) {
        this(status, metadata, true);
    }

    StatusException(Status status, @Nullable Metadata metadata, boolean z) {
        super(Status.formatThrowableMessage(status), status.getCause());
        this.status = status;
        this.trailers = metadata;
        this.fillInStackTrace = z;
        fillInStackTrace();
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        return this.fillInStackTrace ? super.fillInStackTrace() : this;
    }

    public final Status getStatus() {
        return this.status;
    }

    public final Metadata getTrailers() {
        return this.trailers;
    }
}
