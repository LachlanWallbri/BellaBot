package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.batching.FlowControlEventStats;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class FlowController {
    private static final long RESERVE_FLOW_CONTROL_THRESHOLD_MS = 1;
    private final FlowControlEventStats flowControlEventStats;
    private final LimitExceededBehavior limitExceededBehavior;

    @Nullable
    private final Long maxElementCountLimit;

    @Nullable
    private final Long maxRequestBytesLimit;

    @Nullable
    private final Long minElementCountLimit;

    @Nullable
    private final Long minRequestBytesLimit;

    @Nullable
    private final Semaphore64 outstandingByteCount;

    @Nullable
    private final Semaphore64 outstandingElementCount;
    private final Object updateLimitLock;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @BetaApi
    /* loaded from: classes2.dex */
    public enum LimitExceededBehavior {
        ThrowException,
        Block,
        Ignore
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class FlowControlException extends Exception {
        /* synthetic */ FlowControlException(C20601 c20601) {
            this();
        }

        private FlowControlException() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @BetaApi
    /* loaded from: classes2.dex */
    public static class FlowControlRuntimeException extends RuntimeException {
        private FlowControlRuntimeException(FlowControlException flowControlException) {
            super(flowControlException);
        }

        public static FlowControlRuntimeException fromFlowControlException(FlowControlException flowControlException) {
            return new FlowControlRuntimeException(flowControlException);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @BetaApi
    /* loaded from: classes2.dex */
    public static final class MaxOutstandingElementCountReachedException extends FlowControlException {
        private final long currentMaxElementCount;

        public MaxOutstandingElementCountReachedException(long j) {
            super(null);
            this.currentMaxElementCount = j;
        }

        public long getCurrentMaxBatchElementCount() {
            return this.currentMaxElementCount;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return String.format("The maximum number of batch elements: %d have been reached.", Long.valueOf(this.currentMaxElementCount));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @BetaApi
    /* loaded from: classes2.dex */
    public static final class MaxOutstandingRequestBytesReachedException extends FlowControlException {
        private final long currentMaxBytes;

        public MaxOutstandingRequestBytesReachedException(long j) {
            super(null);
            this.currentMaxBytes = j;
        }

        public long getCurrentMaxBatchBytes() {
            return this.currentMaxBytes;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return String.format("The maximum number of batch bytes: %d have been reached.", Long.valueOf(this.currentMaxBytes));
        }
    }

    public FlowController(FlowControlSettings flowControlSettings) {
        this(convertFlowControlSettings(flowControlSettings));
    }

    @InternalApi("For google-cloud-java client use only")
    public FlowController(DynamicFlowControlSettings dynamicFlowControlSettings) {
        this.limitExceededBehavior = dynamicFlowControlSettings.getLimitExceededBehavior();
        this.updateLimitLock = new Object();
        this.flowControlEventStats = new FlowControlEventStats();
        int i = C20601.f1343x5dbf874a[dynamicFlowControlSettings.getLimitExceededBehavior().ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3) {
                this.maxElementCountLimit = null;
                this.maxRequestBytesLimit = null;
                this.minElementCountLimit = null;
                this.minRequestBytesLimit = null;
                this.outstandingElementCount = null;
                this.outstandingByteCount = null;
                return;
            }
            throw new IllegalArgumentException("Unknown LimitBehaviour: " + dynamicFlowControlSettings.getLimitExceededBehavior());
        }
        this.maxElementCountLimit = dynamicFlowControlSettings.getMaxOutstandingElementCount();
        this.minElementCountLimit = dynamicFlowControlSettings.getMinOutstandingElementCount();
        Long initialOutstandingElementCount = dynamicFlowControlSettings.getInitialOutstandingElementCount();
        if (initialOutstandingElementCount == null) {
            this.outstandingElementCount = null;
        } else if (dynamicFlowControlSettings.getLimitExceededBehavior() == LimitExceededBehavior.Block) {
            this.outstandingElementCount = new BlockingSemaphore(initialOutstandingElementCount.longValue());
        } else {
            this.outstandingElementCount = new NonBlockingSemaphore(initialOutstandingElementCount.longValue());
        }
        this.maxRequestBytesLimit = dynamicFlowControlSettings.getMaxOutstandingRequestBytes();
        this.minRequestBytesLimit = dynamicFlowControlSettings.getMinOutstandingRequestBytes();
        Long initialOutstandingRequestBytes = dynamicFlowControlSettings.getInitialOutstandingRequestBytes();
        if (initialOutstandingRequestBytes == null) {
            this.outstandingByteCount = null;
        } else if (dynamicFlowControlSettings.getLimitExceededBehavior() == LimitExceededBehavior.Block) {
            this.outstandingByteCount = new BlockingSemaphore(initialOutstandingRequestBytes.longValue());
        } else {
            this.outstandingByteCount = new NonBlockingSemaphore(initialOutstandingRequestBytes.longValue());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.google.api.gax.batching.FlowController$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C20601 {

        /* renamed from: $SwitchMap$com$google$api$gax$batching$FlowController$LimitExceededBehavior */
        static final /* synthetic */ int[] f1343x5dbf874a = new int[LimitExceededBehavior.values().length];

        static {
            try {
                f1343x5dbf874a[LimitExceededBehavior.ThrowException.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1343x5dbf874a[LimitExceededBehavior.Block.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1343x5dbf874a[LimitExceededBehavior.Ignore.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void reserve(long j, long j2) throws FlowControlException {
        Preconditions.checkArgument(j >= 0);
        Preconditions.checkArgument(j2 >= 0);
        Stopwatch createStarted = Stopwatch.createStarted();
        Semaphore64 semaphore64 = this.outstandingElementCount;
        if (semaphore64 != null && !semaphore64.acquire(j)) {
            MaxOutstandingElementCountReachedException maxOutstandingElementCountReachedException = new MaxOutstandingElementCountReachedException(this.outstandingElementCount.getPermitLimit());
            this.flowControlEventStats.recordFlowControlEvent(FlowControlEventStats.FlowControlEvent.createReserveDenied(maxOutstandingElementCountReachedException));
            throw maxOutstandingElementCountReachedException;
        }
        Semaphore64 semaphore642 = this.outstandingByteCount;
        if (semaphore642 != null && !semaphore642.acquirePartial(j2)) {
            Semaphore64 semaphore643 = this.outstandingElementCount;
            if (semaphore643 != null) {
                semaphore643.release(j);
            }
            MaxOutstandingRequestBytesReachedException maxOutstandingRequestBytesReachedException = new MaxOutstandingRequestBytesReachedException(this.outstandingByteCount.getPermitLimit());
            this.flowControlEventStats.recordFlowControlEvent(FlowControlEventStats.FlowControlEvent.createReserveDenied(maxOutstandingRequestBytesReachedException));
            throw maxOutstandingRequestBytesReachedException;
        }
        long elapsed = createStarted.elapsed(TimeUnit.MILLISECONDS);
        if (elapsed >= 1) {
            this.flowControlEventStats.recordFlowControlEvent(FlowControlEventStats.FlowControlEvent.createReserveDelayed(elapsed));
        }
    }

    public void release(long j, long j2) {
        Preconditions.checkArgument(j >= 0);
        Preconditions.checkArgument(j2 >= 0);
        Semaphore64 semaphore64 = this.outstandingElementCount;
        if (semaphore64 != null) {
            semaphore64.release(j);
        }
        Semaphore64 semaphore642 = this.outstandingByteCount;
        if (semaphore642 != null) {
            semaphore642.release(j2);
        }
    }

    @InternalApi("For google-cloud-java client use only")
    public void increaseThresholds(long j, long j2) {
        Preconditions.checkArgument(j >= 0);
        Preconditions.checkArgument(j2 >= 0);
        synchronized (this.updateLimitLock) {
            if (this.outstandingElementCount != null) {
                this.outstandingElementCount.increasePermitLimit(Math.min(j, this.maxElementCountLimit.longValue() - this.outstandingElementCount.getPermitLimit()));
            }
            if (this.outstandingByteCount != null) {
                this.outstandingByteCount.increasePermitLimit(Math.min(j2, this.maxRequestBytesLimit.longValue() - this.outstandingByteCount.getPermitLimit()));
            }
        }
    }

    @InternalApi("For google-cloud-java client use only")
    public void decreaseThresholds(long j, long j2) {
        Preconditions.checkArgument(j >= 0);
        Preconditions.checkArgument(j2 >= 0);
        synchronized (this.updateLimitLock) {
            if (this.outstandingElementCount != null) {
                this.outstandingElementCount.reducePermitLimit(Math.min(j, this.outstandingElementCount.getPermitLimit() - this.minElementCountLimit.longValue()));
            }
            if (this.outstandingByteCount != null) {
                this.outstandingByteCount.reducePermitLimit(Math.min(j2, this.outstandingByteCount.getPermitLimit() - this.minRequestBytesLimit.longValue()));
            }
        }
    }

    private static DynamicFlowControlSettings convertFlowControlSettings(FlowControlSettings flowControlSettings) {
        return DynamicFlowControlSettings.newBuilder().setInitialOutstandingElementCount(flowControlSettings.getMaxOutstandingElementCount()).setMinOutstandingElementCount(flowControlSettings.getMaxOutstandingElementCount()).setMaxOutstandingElementCount(flowControlSettings.getMaxOutstandingElementCount()).setInitialOutstandingRequestBytes(flowControlSettings.getMaxOutstandingRequestBytes()).setMinOutstandingRequestBytes(flowControlSettings.getMaxOutstandingRequestBytes()).setMaxOutstandingRequestBytes(flowControlSettings.getMaxOutstandingRequestBytes()).setLimitExceededBehavior(flowControlSettings.getLimitExceededBehavior()).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LimitExceededBehavior getLimitExceededBehavior() {
        return this.limitExceededBehavior;
    }

    @InternalApi("For internal use by google-cloud-java clients only")
    @Nullable
    public Long getMaxElementCountLimit() {
        return this.maxElementCountLimit;
    }

    @InternalApi("For internal use by google-cloud-java clients only")
    @Nullable
    public Long getMaxRequestBytesLimit() {
        return this.maxRequestBytesLimit;
    }

    @InternalApi("For google-cloud-java client use only")
    @Nullable
    public Long getMinElementCountLimit() {
        return this.minElementCountLimit;
    }

    @InternalApi("For google-cloud-java client use only")
    @Nullable
    public Long getMinRequestBytesLimit() {
        return this.minRequestBytesLimit;
    }

    @InternalApi("For google-cloud-java client use only")
    @Nullable
    public Long getCurrentElementCountLimit() {
        Semaphore64 semaphore64 = this.outstandingElementCount;
        if (semaphore64 == null) {
            return null;
        }
        return Long.valueOf(semaphore64.getPermitLimit());
    }

    @InternalApi("For google-cloud-java client use only")
    @Nullable
    public Long getCurrentRequestBytesLimit() {
        Semaphore64 semaphore64 = this.outstandingByteCount;
        if (semaphore64 == null) {
            return null;
        }
        return Long.valueOf(semaphore64.getPermitLimit());
    }

    @InternalApi("For google-cloud-java client use only")
    public FlowControlEventStats getFlowControlEventStats() {
        return this.flowControlEventStats;
    }
}
