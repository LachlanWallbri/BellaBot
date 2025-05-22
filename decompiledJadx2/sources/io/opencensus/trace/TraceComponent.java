package io.opencensus.trace;

import io.opencensus.common.Clock;
import io.opencensus.internal.ZeroTimeClock;
import io.opencensus.trace.config.TraceConfig;
import io.opencensus.trace.export.ExportComponent;
import io.opencensus.trace.propagation.PropagationComponent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class TraceComponent {
    public abstract Clock getClock();

    public abstract ExportComponent getExportComponent();

    public abstract PropagationComponent getPropagationComponent();

    public abstract TraceConfig getTraceConfig();

    public abstract Tracer getTracer();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TraceComponent newNoopTraceComponent() {
        return new NoopTraceComponent();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopTraceComponent extends TraceComponent {
        private final ExportComponent noopExportComponent;

        @Override // io.opencensus.trace.TraceComponent
        public Tracer getTracer() {
            return Tracer.getNoopTracer();
        }

        @Override // io.opencensus.trace.TraceComponent
        public PropagationComponent getPropagationComponent() {
            return PropagationComponent.getNoopPropagationComponent();
        }

        @Override // io.opencensus.trace.TraceComponent
        public Clock getClock() {
            return ZeroTimeClock.getInstance();
        }

        @Override // io.opencensus.trace.TraceComponent
        public ExportComponent getExportComponent() {
            return this.noopExportComponent;
        }

        @Override // io.opencensus.trace.TraceComponent
        public TraceConfig getTraceConfig() {
            return TraceConfig.getNoopTraceConfig();
        }

        private NoopTraceComponent() {
            this.noopExportComponent = ExportComponent.newNoopExportComponent();
        }
    }
}
