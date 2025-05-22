package io.grpc.internal;

import io.grpc.InternalChannelz;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CallTracer {
    static final Factory DEFAULT_FACTORY = new Factory() { // from class: io.grpc.internal.CallTracer.1
        @Override // io.grpc.internal.CallTracer.Factory
        public CallTracer create() {
            return new CallTracer(TimeProvider.SYSTEM_TIME_PROVIDER);
        }
    };
    private volatile long lastCallStartedNanos;
    private final TimeProvider timeProvider;
    private final LongCounter callsStarted = LongCounterFactory.create();
    private final LongCounter callsSucceeded = LongCounterFactory.create();
    private final LongCounter callsFailed = LongCounterFactory.create();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Factory {
        CallTracer create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallTracer(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public void reportCallStarted() {
        this.callsStarted.add(1L);
        this.lastCallStartedNanos = this.timeProvider.currentTimeNanos();
    }

    public void reportCallEnded(boolean z) {
        if (z) {
            this.callsSucceeded.add(1L);
        } else {
            this.callsFailed.add(1L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateBuilder(InternalChannelz.ChannelStats.Builder builder) {
        builder.setCallsStarted(this.callsStarted.value()).setCallsSucceeded(this.callsSucceeded.value()).setCallsFailed(this.callsFailed.value()).setLastCallStartedNanos(this.lastCallStartedNanos);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateBuilder(InternalChannelz.ServerStats.Builder builder) {
        builder.setCallsStarted(this.callsStarted.value()).setCallsSucceeded(this.callsSucceeded.value()).setCallsFailed(this.callsFailed.value()).setLastCallStartedNanos(this.lastCallStartedNanos);
    }

    public static Factory getDefaultFactory() {
        return DEFAULT_FACTORY;
    }
}
