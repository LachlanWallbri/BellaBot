package io.opencensus.stats;

import io.opencensus.common.Functions;
import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import io.opencensus.stats.Measure;
import io.opencensus.stats.View;
import io.opencensus.stats.ViewData;
import io.opencensus.tags.TagContext;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class NoopStats {
    private NoopStats() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StatsComponent newNoopStatsComponent() {
        return new NoopStatsComponent();
    }

    static StatsRecorder getNoopStatsRecorder() {
        return NoopStatsRecorder.INSTANCE;
    }

    static MeasureMap newNoopMeasureMap() {
        return new NoopMeasureMap();
    }

    static ViewManager newNoopViewManager() {
        return new NoopViewManager();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopStatsComponent extends StatsComponent {
        private volatile boolean isRead;
        private final ViewManager viewManager;

        private NoopStatsComponent() {
            this.viewManager = NoopStats.newNoopViewManager();
        }

        @Override // io.opencensus.stats.StatsComponent
        public ViewManager getViewManager() {
            return this.viewManager;
        }

        @Override // io.opencensus.stats.StatsComponent
        public StatsRecorder getStatsRecorder() {
            return NoopStats.getNoopStatsRecorder();
        }

        @Override // io.opencensus.stats.StatsComponent
        public StatsCollectionState getState() {
            this.isRead = true;
            return StatsCollectionState.DISABLED;
        }

        @Override // io.opencensus.stats.StatsComponent
        @Deprecated
        public void setState(StatsCollectionState statsCollectionState) {
            Utils.checkNotNull(statsCollectionState, "state");
            Utils.checkState(!this.isRead, "State was already read, cannot set state.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopStatsRecorder extends StatsRecorder {
        static final StatsRecorder INSTANCE = new NoopStatsRecorder();

        private NoopStatsRecorder() {
        }

        @Override // io.opencensus.stats.StatsRecorder
        public MeasureMap newMeasureMap() {
            return NoopStats.newNoopMeasureMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopMeasureMap extends MeasureMap {
        private static final Logger logger = Logger.getLogger(NoopMeasureMap.class.getName());
        private boolean hasUnsupportedValues;

        @Override // io.opencensus.stats.MeasureMap
        public void record() {
        }

        private NoopMeasureMap() {
        }

        @Override // io.opencensus.stats.MeasureMap
        public MeasureMap put(Measure.MeasureDouble measureDouble, double d) {
            if (d < 0.0d) {
                this.hasUnsupportedValues = true;
            }
            return this;
        }

        @Override // io.opencensus.stats.MeasureMap
        public MeasureMap put(Measure.MeasureLong measureLong, long j) {
            if (j < 0) {
                this.hasUnsupportedValues = true;
            }
            return this;
        }

        @Override // io.opencensus.stats.MeasureMap
        public void record(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            if (this.hasUnsupportedValues) {
                logger.log(Level.WARNING, "Dropping values, value to record must be non-negative.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static final class NoopViewManager extends ViewManager {
        private static final Timestamp ZERO_TIMESTAMP = Timestamp.create(0, 0);

        @Nullable
        private volatile Set<View> exportedViews;
        private final Map<View.Name, View> registeredViews;

        private NoopViewManager() {
            this.registeredViews = new HashMap();
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x002a A[Catch: all -> 0x0035, TryCatch #0 {, blocks: (B:5:0x0009, B:7:0x0019, B:11:0x0023, B:13:0x002a, B:14:0x0033), top: B:4:0x0009 }] */
        @Override // io.opencensus.stats.ViewManager
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void registerView(View view) {
            boolean z;
            Utils.checkNotNull(view, "newView");
            synchronized (this.registeredViews) {
                this.exportedViews = null;
                View view2 = this.registeredViews.get(view.getName());
                if (view2 != null && !view.equals(view2)) {
                    z = false;
                    Utils.checkArgument(z, "A different view with the same name already exists.");
                    if (view2 == null) {
                        this.registeredViews.put(view.getName(), view);
                    }
                }
                z = true;
                Utils.checkArgument(z, "A different view with the same name already exists.");
                if (view2 == null) {
                }
            }
        }

        @Override // io.opencensus.stats.ViewManager
        @Nullable
        public ViewData getView(View.Name name) {
            Utils.checkNotNull(name, "name");
            synchronized (this.registeredViews) {
                View view = this.registeredViews.get(name);
                if (view == null) {
                    return null;
                }
                return ViewData.create(view, Collections.emptyMap(), (ViewData.AggregationWindowData) view.getWindow().match(Functions.returnConstant(ViewData.AggregationWindowData.CumulativeData.create(ZERO_TIMESTAMP, ZERO_TIMESTAMP)), Functions.returnConstant(ViewData.AggregationWindowData.IntervalData.create(ZERO_TIMESTAMP)), Functions.throwAssertionError()));
            }
        }

        @Override // io.opencensus.stats.ViewManager
        public Set<View> getAllExportedViews() {
            Set<View> set = this.exportedViews;
            if (set == null) {
                synchronized (this.registeredViews) {
                    set = filterExportedViews(this.registeredViews.values());
                    this.exportedViews = set;
                }
            }
            return set;
        }

        private static Set<View> filterExportedViews(Collection<View> collection) {
            HashSet hashSet = new HashSet();
            for (View view : collection) {
                if (!(view.getWindow() instanceof View.AggregationWindow.Interval)) {
                    hashSet.add(view);
                }
            }
            return Collections.unmodifiableSet(hashSet);
        }
    }
}
