package io.opencensus.trace.export;

import io.opencensus.internal.Utils;
import io.opencensus.trace.Status;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class SampledSpanStore {
    public abstract Collection<SpanData> getErrorSampledSpans(ErrorFilter errorFilter);

    public abstract Collection<SpanData> getLatencySampledSpans(LatencyFilter latencyFilter);

    public abstract Set<String> getRegisteredSpanNamesForCollection();

    public abstract Summary getSummary();

    @Deprecated
    public abstract void registerSpanNamesForCollection(Collection<String> collection);

    @Deprecated
    public abstract void unregisterSpanNamesForCollection(Collection<String> collection);

    protected SampledSpanStore() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SampledSpanStore newNoopSampledSpanStore() {
        return new NoopSampledSpanStore();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Summary {
        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();

        public static Summary create(Map<String, PerSpanNameSummary> map) {
            return new AutoValue_SampledSpanStore_Summary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "perSpanNameSummary"))));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class PerSpanNameSummary {
        public abstract Map<Status.CanonicalCode, Integer> getNumbersOfErrorSampledSpans();

        public abstract Map<LatencyBucketBoundaries, Integer> getNumbersOfLatencySampledSpans();

        public static PerSpanNameSummary create(Map<LatencyBucketBoundaries, Integer> map, Map<Status.CanonicalCode, Integer> map2) {
            return new AutoValue_SampledSpanStore_PerSpanNameSummary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "numbersOfLatencySampledSpans"))), Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map2, "numbersOfErrorSampledSpans"))));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public enum LatencyBucketBoundaries {
        ZERO_MICROSx10(0, TimeUnit.MICROSECONDS.toNanos(10)),
        MICROSx10_MICROSx100(TimeUnit.MICROSECONDS.toNanos(10), TimeUnit.MICROSECONDS.toNanos(100)),
        MICROSx100_MILLIx1(TimeUnit.MICROSECONDS.toNanos(100), TimeUnit.MILLISECONDS.toNanos(1)),
        MILLIx1_MILLIx10(TimeUnit.MILLISECONDS.toNanos(1), TimeUnit.MILLISECONDS.toNanos(10)),
        MILLIx10_MILLIx100(TimeUnit.MILLISECONDS.toNanos(10), TimeUnit.MILLISECONDS.toNanos(100)),
        MILLIx100_SECONDx1(TimeUnit.MILLISECONDS.toNanos(100), TimeUnit.SECONDS.toNanos(1)),
        SECONDx1_SECONDx10(TimeUnit.SECONDS.toNanos(1), TimeUnit.SECONDS.toNanos(10)),
        SECONDx10_SECONDx100(TimeUnit.SECONDS.toNanos(10), TimeUnit.SECONDS.toNanos(100)),
        SECONDx100_MAX(TimeUnit.SECONDS.toNanos(100), Long.MAX_VALUE);

        private final long latencyLowerNs;
        private final long latencyUpperNs;

        LatencyBucketBoundaries(long j, long j2) {
            this.latencyLowerNs = j;
            this.latencyUpperNs = j2;
        }

        public long getLatencyLowerNs() {
            return this.latencyLowerNs;
        }

        public long getLatencyUpperNs() {
            return this.latencyUpperNs;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class LatencyFilter {
        public abstract long getLatencyLowerNs();

        public abstract long getLatencyUpperNs();

        public abstract int getMaxSpansToReturn();

        public abstract String getSpanName();

        public static LatencyFilter create(String str, long j, long j2, int i) {
            Utils.checkArgument(i >= 0, "Negative maxSpansToReturn.");
            Utils.checkArgument(j >= 0, "Negative latencyLowerNs");
            Utils.checkArgument(j2 >= 0, "Negative latencyUpperNs");
            return new AutoValue_SampledSpanStore_LatencyFilter(str, j, j2, i);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class ErrorFilter {
        @Nullable
        public abstract Status.CanonicalCode getCanonicalCode();

        public abstract int getMaxSpansToReturn();

        public abstract String getSpanName();

        public static ErrorFilter create(String str, @Nullable Status.CanonicalCode canonicalCode, int i) {
            if (canonicalCode != null) {
                Utils.checkArgument(canonicalCode != Status.CanonicalCode.OK, "Invalid canonical code.");
            }
            Utils.checkArgument(i >= 0, "Negative maxSpansToReturn.");
            return new AutoValue_SampledSpanStore_ErrorFilter(str, canonicalCode, i);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopSampledSpanStore extends SampledSpanStore {
        private static final PerSpanNameSummary EMPTY_PER_SPAN_NAME_SUMMARY = PerSpanNameSummary.create(Collections.emptyMap(), Collections.emptyMap());
        private final Set<String> registeredSpanNames;

        private NoopSampledSpanStore() {
            this.registeredSpanNames = new HashSet();
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Summary getSummary() {
            HashMap hashMap = new HashMap();
            synchronized (this.registeredSpanNames) {
                Iterator<String> it = this.registeredSpanNames.iterator();
                while (it.hasNext()) {
                    hashMap.put(it.next(), EMPTY_PER_SPAN_NAME_SUMMARY);
                }
            }
            return Summary.create(hashMap);
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Collection<SpanData> getLatencySampledSpans(LatencyFilter latencyFilter) {
            Utils.checkNotNull(latencyFilter, "latencyFilter");
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Collection<SpanData> getErrorSampledSpans(ErrorFilter errorFilter) {
            Utils.checkNotNull(errorFilter, "errorFilter");
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public void registerSpanNamesForCollection(Collection<String> collection) {
            Utils.checkNotNull(collection, "spanNames");
            synchronized (this.registeredSpanNames) {
                this.registeredSpanNames.addAll(collection);
            }
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public void unregisterSpanNamesForCollection(Collection<String> collection) {
            Utils.checkNotNull(collection, "spanNames");
            synchronized (this.registeredSpanNames) {
                this.registeredSpanNames.removeAll(collection);
            }
        }

        @Override // io.opencensus.trace.export.SampledSpanStore
        public Set<String> getRegisteredSpanNamesForCollection() {
            Set<String> unmodifiableSet;
            synchronized (this.registeredSpanNames) {
                unmodifiableSet = Collections.unmodifiableSet(new HashSet(this.registeredSpanNames));
            }
            return unmodifiableSet;
        }
    }
}
