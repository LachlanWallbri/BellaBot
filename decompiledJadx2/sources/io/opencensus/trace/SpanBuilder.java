package io.opencensus.trace;

import io.opencensus.common.Scope;
import io.opencensus.internal.Utils;
import io.opencensus.trace.Span;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class SpanBuilder {
    public abstract SpanBuilder setParentLinks(List<Span> list);

    public abstract SpanBuilder setRecordEvents(boolean z);

    public abstract SpanBuilder setSampler(Sampler sampler);

    public SpanBuilder setSpanKind(@Nullable Span.Kind kind) {
        return this;
    }

    public abstract Span startSpan();

    public final Scope startScopedSpan() {
        return CurrentSpanUtils.withSpan(startSpan(), true);
    }

    public final void startSpanAndRun(Runnable runnable) {
        CurrentSpanUtils.withSpan(startSpan(), true, runnable).run();
    }

    public final <V> V startSpanAndCall(Callable<V> callable) throws Exception {
        return (V) CurrentSpanUtils.withSpan(startSpan(), true, (Callable) callable).call();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static final class NoopSpanBuilder extends SpanBuilder {
        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setParentLinks(List<Span> list) {
            return this;
        }

        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setRecordEvents(boolean z) {
            return this;
        }

        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setSampler(@Nullable Sampler sampler) {
            return this;
        }

        @Override // io.opencensus.trace.SpanBuilder
        public SpanBuilder setSpanKind(@Nullable Span.Kind kind) {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static NoopSpanBuilder createWithParent(String str, @Nullable Span span) {
            return new NoopSpanBuilder(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static NoopSpanBuilder createWithRemoteParent(String str, @Nullable SpanContext spanContext) {
            return new NoopSpanBuilder(str);
        }

        @Override // io.opencensus.trace.SpanBuilder
        public Span startSpan() {
            return BlankSpan.INSTANCE;
        }

        private NoopSpanBuilder(String str) {
            Utils.checkNotNull(str, "name");
        }
    }
}
