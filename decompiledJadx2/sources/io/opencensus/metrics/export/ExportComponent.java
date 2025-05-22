package io.opencensus.metrics.export;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class ExportComponent {
    public abstract MetricProducerManager getMetricProducerManager();

    public static ExportComponent newNoopExportComponent() {
        return new NoopExportComponent();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopExportComponent extends ExportComponent {
        private static final MetricProducerManager METRIC_PRODUCER_MANAGER = MetricProducerManager.newNoopMetricProducerManager();

        private NoopExportComponent() {
        }

        @Override // io.opencensus.metrics.export.ExportComponent
        public MetricProducerManager getMetricProducerManager() {
            return METRIC_PRODUCER_MANAGER;
        }
    }
}
