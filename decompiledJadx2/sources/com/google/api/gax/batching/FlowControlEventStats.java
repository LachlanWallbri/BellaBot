package com.google.api.gax.batching;

import com.google.api.core.InternalApi;
import com.google.api.gax.batching.FlowController;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For google-cloud-java client use only")
/* loaded from: classes2.dex */
public class FlowControlEventStats {
    private volatile FlowControlEvent lastFlowControlEvent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordFlowControlEvent(FlowControlEvent flowControlEvent) {
        if (this.lastFlowControlEvent == null || flowControlEvent.compareTo(this.lastFlowControlEvent) > 0) {
            this.lastFlowControlEvent = flowControlEvent;
        }
    }

    public FlowControlEvent getLastFlowControlEvent() {
        return this.lastFlowControlEvent;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class FlowControlEvent implements Comparable<FlowControlEvent> {
        private FlowController.FlowControlException exception;
        private Long throttledTimeMs;
        private long timestampMs;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FlowControlEvent createReserveDelayed(long j) {
            return createReserveDelayed(System.currentTimeMillis(), j);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FlowControlEvent createReserveDenied(FlowController.FlowControlException flowControlException) {
            return createReserveDenied(System.currentTimeMillis(), flowControlException);
        }

        static FlowControlEvent createReserveDelayed(long j, long j2) {
            Preconditions.checkArgument(j > 0, "timestamp must be greater than 0");
            Preconditions.checkArgument(j2 > 0, "throttled time must be greater than 0");
            return new FlowControlEvent(j, Long.valueOf(j2), null);
        }

        static FlowControlEvent createReserveDenied(long j, FlowController.FlowControlException flowControlException) {
            Preconditions.checkArgument(j > 0, "timestamp must be greater than 0");
            Preconditions.checkNotNull(flowControlException, "FlowControlException can't be null when reserve is denied");
            return new FlowControlEvent(j, null, flowControlException);
        }

        private FlowControlEvent(long j, @Nullable Long l, @Nullable FlowController.FlowControlException flowControlException) {
            this.timestampMs = j;
            this.throttledTimeMs = l;
            this.exception = flowControlException;
        }

        public long getTimestampMs() {
            return this.timestampMs;
        }

        @Nullable
        public FlowController.FlowControlException getException() {
            return this.exception;
        }

        @Nullable
        public Long getThrottledTime(TimeUnit timeUnit) {
            Long l = this.throttledTimeMs;
            if (l == null) {
                return null;
            }
            return Long.valueOf(timeUnit.convert(l.longValue(), TimeUnit.MILLISECONDS));
        }

        @Override // java.lang.Comparable
        public int compareTo(FlowControlEvent flowControlEvent) {
            return Long.compare(getTimestampMs(), flowControlEvent.getTimestampMs());
        }
    }
}
