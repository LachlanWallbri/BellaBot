package io.opencensus.metrics;

import io.opencensus.metrics.export.ExportComponent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class MetricsComponent {
    public abstract ExportComponent getExportComponent();

    public abstract MetricRegistry getMetricRegistry();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MetricsComponent newNoopMetricsComponent() {
        return new NoopMetricsComponent();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopMetricsComponent extends MetricsComponent {
        private static final ExportComponent EXPORT_COMPONENT = ExportComponent.newNoopExportComponent();
        private static final MetricRegistry METRIC_REGISTRY = MetricRegistry.newNoopMetricRegistry();

        private NoopMetricsComponent() {
        }

        @Override // io.opencensus.metrics.MetricsComponent
        public ExportComponent getExportComponent() {
            return EXPORT_COMPONENT;
        }

        @Override // io.opencensus.metrics.MetricsComponent
        public MetricRegistry getMetricRegistry() {
            return METRIC_REGISTRY;
        }
    }
}
