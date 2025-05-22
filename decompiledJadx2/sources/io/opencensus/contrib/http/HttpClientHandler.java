package io.opencensus.contrib.http;

import com.google.common.base.Preconditions;
import com.iflytek.cloud.msc.util.AppInfoUtil;
import io.opencensus.contrib.http.util.HttpMeasureConstants;
import io.opencensus.stats.Stats;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagContextBuilder;
import io.opencensus.tags.TagKey;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.Tags;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.propagation.TextFormat;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public class HttpClientHandler<Q, P, C> extends AbstractHttpHandler<Q, P> {
    private final TextFormat.Setter<C> setter;
    private final StatsRecorder statsRecorder;
    private final Tagger tagger;
    private final TextFormat textFormat;
    private final Tracer tracer;

    @Override // io.opencensus.contrib.http.AbstractHttpHandler
    public /* bridge */ /* synthetic */ Span getSpanFromContext(HttpRequestContext httpRequestContext) {
        return super.getSpanFromContext(httpRequestContext);
    }

    public HttpClientHandler(Tracer tracer, HttpExtractor<Q, P> httpExtractor, TextFormat textFormat, TextFormat.Setter<C> setter) {
        super(httpExtractor);
        Preconditions.checkNotNull(setter, "setter");
        Preconditions.checkNotNull(textFormat, "textFormat");
        Preconditions.checkNotNull(tracer, "tracer");
        this.setter = setter;
        this.textFormat = textFormat;
        this.tracer = tracer;
        this.statsRecorder = Stats.getStatsRecorder();
        this.tagger = Tags.getTagger();
    }

    public HttpRequestContext handleStart(@Nullable Span span, C c, Q q) {
        Preconditions.checkNotNull(c, AppInfoUtil.CARRIER);
        Preconditions.checkNotNull(q, "request");
        if (span == null) {
            span = this.tracer.getCurrentSpan();
        }
        Span startSpan = this.tracer.spanBuilderWithExplicitParent(getSpanName(q, this.extractor), span).setSpanKind(Span.Kind.CLIENT).startSpan();
        if (startSpan.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            addSpanRequestAttributes(startSpan, q, this.extractor);
        }
        SpanContext context = startSpan.getContext();
        if (!context.equals(SpanContext.INVALID)) {
            this.textFormat.inject(context, c, this.setter);
        }
        return getNewContext(startSpan, this.tagger.getCurrentTagContext());
    }

    public void handleEnd(HttpRequestContext httpRequestContext, @Nullable Q q, @Nullable P p, @Nullable Throwable th) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        int statusCode = this.extractor.getStatusCode(p);
        recordStats(httpRequestContext, q, statusCode);
        spanEnd(httpRequestContext.span, statusCode, th);
    }

    private void recordStats(HttpRequestContext httpRequestContext, @Nullable Q q, int i) {
        double millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - httpRequestContext.requestStartTime);
        String method = q == null ? "" : this.extractor.getMethod(q);
        String host = q == null ? "null_request" : this.extractor.getHost(q);
        TagContextBuilder builder = this.tagger.toBuilder(httpRequestContext.tagContext);
        TagKey tagKey = HttpMeasureConstants.HTTP_CLIENT_HOST;
        if (host == null) {
            host = "null_host";
        }
        this.statsRecorder.newMeasureMap().put(HttpMeasureConstants.HTTP_CLIENT_ROUNDTRIP_LATENCY, millis).put(HttpMeasureConstants.HTTP_CLIENT_SENT_BYTES, httpRequestContext.sentMessageSize.get()).put(HttpMeasureConstants.HTTP_CLIENT_RECEIVED_BYTES, httpRequestContext.receiveMessageSize.get()).record(builder.put(tagKey, TagValue.create(host), HttpRequestContext.METADATA_NO_PROPAGATION).put(HttpMeasureConstants.HTTP_CLIENT_METHOD, TagValue.create(method != null ? method : ""), HttpRequestContext.METADATA_NO_PROPAGATION).put(HttpMeasureConstants.HTTP_CLIENT_STATUS, TagValue.create(i == 0 ? "error" : Integer.toString(i)), HttpRequestContext.METADATA_NO_PROPAGATION).build());
    }
}
