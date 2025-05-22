package com.google.api.gax.retrying;

import com.google.api.core.InternalApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public class ServerStreamingAttemptException extends RuntimeException {
    private final boolean canResume;
    private final boolean seenResponses;

    public ServerStreamingAttemptException(Throwable th, boolean z, boolean z2) {
        super(th.getMessage(), th);
        this.canResume = z;
        this.seenResponses = z2;
    }

    public boolean canResume() {
        return this.canResume;
    }

    public boolean hasSeenResponses() {
        return this.seenResponses;
    }
}
