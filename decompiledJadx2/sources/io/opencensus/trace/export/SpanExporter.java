package io.opencensus.trace.export;

import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class SpanExporter {
    private static final SpanExporter NOOP_SPAN_EXPORTER = new NoopSpanExporter();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Handler {
        public abstract void export(Collection<SpanData> collection);
    }

    public abstract void registerHandler(String str, Handler handler);

    public abstract void unregisterHandler(String str);

    public static SpanExporter getNoopSpanExporter() {
        return NOOP_SPAN_EXPORTER;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopSpanExporter extends SpanExporter {
        @Override // io.opencensus.trace.export.SpanExporter
        public void registerHandler(String str, Handler handler) {
        }

        @Override // io.opencensus.trace.export.SpanExporter
        public void unregisterHandler(String str) {
        }

        private NoopSpanExporter() {
        }
    }
}
