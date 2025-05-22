package io.opencensus.trace.export;

import io.opencensus.internal.Utils;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class RunningSpanStore {
    private static final RunningSpanStore NOOP_RUNNING_SPAN_STORE = new NoopRunningSpanStore();

    public abstract Collection<SpanData> getRunningSpans(Filter filter);

    public abstract Summary getSummary();

    public abstract void setMaxNumberOfSpans(int i);

    protected RunningSpanStore() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RunningSpanStore getNoopRunningSpanStore() {
        return NOOP_RUNNING_SPAN_STORE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Summary {
        public abstract Map<String, PerSpanNameSummary> getPerSpanNameSummary();

        public static Summary create(Map<String, PerSpanNameSummary> map) {
            return new AutoValue_RunningSpanStore_Summary(Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "perSpanNameSummary"))));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class PerSpanNameSummary {
        public abstract int getNumRunningSpans();

        public static PerSpanNameSummary create(int i) {
            Utils.checkArgument(i >= 0, "Negative numRunningSpans.");
            return new AutoValue_RunningSpanStore_PerSpanNameSummary(i);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Filter {
        public abstract int getMaxSpansToReturn();

        public abstract String getSpanName();

        public static Filter create(String str, int i) {
            Utils.checkArgument(i >= 0, "Negative maxSpansToReturn.");
            return new AutoValue_RunningSpanStore_Filter(str, i);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopRunningSpanStore extends RunningSpanStore {
        private static final Summary EMPTY_SUMMARY = Summary.create(Collections.emptyMap());

        private NoopRunningSpanStore() {
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public Summary getSummary() {
            return EMPTY_SUMMARY;
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public Collection<SpanData> getRunningSpans(Filter filter) {
            Utils.checkNotNull(filter, "filter");
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.export.RunningSpanStore
        public void setMaxNumberOfSpans(int i) {
            Utils.checkArgument(i >= 0, "Invalid negative maxNumberOfElements");
        }
    }
}
