package io.opencensus.trace.propagation;

import com.iflytek.cloud.msc.util.AppInfoUtil;
import io.opencensus.internal.Utils;
import io.opencensus.trace.SpanContext;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class TextFormat {
    private static final NoopTextFormat NOOP_TEXT_FORMAT = new NoopTextFormat();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Getter<C> {
        @Nullable
        public abstract String get(C c, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Setter<C> {
        public abstract void put(C c, String str, String str2);
    }

    public abstract <C> SpanContext extract(C c, Getter<C> getter) throws SpanContextParseException;

    public abstract List<String> fields();

    public abstract <C> void inject(SpanContext spanContext, C c, Setter<C> setter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TextFormat getNoopTextFormat() {
        return NOOP_TEXT_FORMAT;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopTextFormat extends TextFormat {
        private NoopTextFormat() {
        }

        @Override // io.opencensus.trace.propagation.TextFormat
        public List<String> fields() {
            return Collections.emptyList();
        }

        @Override // io.opencensus.trace.propagation.TextFormat
        public <C> void inject(SpanContext spanContext, C c, Setter<C> setter) {
            Utils.checkNotNull(spanContext, "spanContext");
            Utils.checkNotNull(c, AppInfoUtil.CARRIER);
            Utils.checkNotNull(setter, "setter");
        }

        @Override // io.opencensus.trace.propagation.TextFormat
        public <C> SpanContext extract(C c, Getter<C> getter) {
            Utils.checkNotNull(c, AppInfoUtil.CARRIER);
            Utils.checkNotNull(getter, "getter");
            return SpanContext.INVALID;
        }
    }
}
