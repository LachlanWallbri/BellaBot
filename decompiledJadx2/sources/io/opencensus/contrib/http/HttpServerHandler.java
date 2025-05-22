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
import io.opencensus.trace.Link;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanBuilder;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.propagation.SpanContextParseException;
import io.opencensus.trace.propagation.TextFormat;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public class HttpServerHandler<Q, P, C> extends AbstractHttpHandler<Q, P> {
    private final TextFormat.Getter<C> getter;
    private final Boolean publicEndpoint;
    private final StatsRecorder statsRecorder;
    private final Tagger tagger;
    private final TextFormat textFormat;
    private final Tracer tracer;

    @Override // io.opencensus.contrib.http.AbstractHttpHandler
    public /* bridge */ /* synthetic */ Span getSpanFromContext(HttpRequestContext httpRequestContext) {
        return super.getSpanFromContext(httpRequestContext);
    }

    public HttpServerHandler(Tracer tracer, HttpExtractor<Q, P> httpExtractor, TextFormat textFormat, TextFormat.Getter<C> getter, Boolean bool) {
        super(httpExtractor);
        Preconditions.checkNotNull(tracer, "tracer");
        Preconditions.checkNotNull(textFormat, "textFormat");
        Preconditions.checkNotNull(getter, "getter");
        Preconditions.checkNotNull(bool, "publicEndpoint");
        this.tracer = tracer;
        this.textFormat = textFormat;
        this.getter = getter;
        this.publicEndpoint = bool;
        this.statsRecorder = Stats.getStatsRecorder();
        this.tagger = Tags.getTagger();
    }

    public HttpRequestContext handleStart(C c, Q q) {
        SpanContext spanContext;
        SpanBuilder spanBuilder;
        Preconditions.checkNotNull(c, AppInfoUtil.CARRIER);
        Preconditions.checkNotNull(q, "request");
        String spanName = getSpanName(q, this.extractor);
        try {
            spanContext = this.textFormat.extract(c, this.getter);
        } catch (SpanContextParseException unused) {
            spanContext = null;
        }
        if (spanContext == null || this.publicEndpoint.booleanValue()) {
            spanBuilder = this.tracer.spanBuilder(spanName);
        } else {
            spanBuilder = this.tracer.spanBuilderWithRemoteParent(spanName, spanContext);
        }
        Span startSpan = spanBuilder.setSpanKind(Span.Kind.SERVER).startSpan();
        if (this.publicEndpoint.booleanValue() && spanContext != null) {
            startSpan.addLink(Link.fromSpanContext(spanContext, Link.Type.PARENT_LINKED_SPAN));
        }
        if (startSpan.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            addSpanRequestAttributes(startSpan, q, this.extractor);
        }
        return getNewContext(startSpan, this.tagger.getCurrentTagContext());
    }

    public void handleEnd(HttpRequestContext httpRequestContext, Q q, @Nullable P p, @Nullable Throwable th) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        Preconditions.checkNotNull(q, "request");
        int statusCode = this.extractor.getStatusCode(p);
        recordStats(httpRequestContext, q, statusCode);
        spanEnd(httpRequestContext.span, statusCode, th);
    }

    private void recordStats(HttpRequestContext httpRequestContext, Q q, int i) {
        double millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - httpRequestContext.requestStartTime);
        String method = this.extractor.getMethod(q);
        String route = this.extractor.getRoute(q);
        TagContextBuilder builder = this.tagger.toBuilder(httpRequestContext.tagContext);
        TagKey tagKey = HttpMeasureConstants.HTTP_SERVER_METHOD;
        if (method == null) {
            method = "";
        }
        TagContextBuilder put = builder.put(tagKey, TagValue.create(method), HttpRequestContext.METADATA_NO_PROPAGATION);
        TagKey tagKey2 = HttpMeasureConstants.HTTP_SERVER_ROUTE;
        if (route == null) {
            route = "";
        }
        this.statsRecorder.newMeasureMap().put(HttpMeasureConstants.HTTP_SERVER_LATENCY, millis).put(HttpMeasureConstants.HTTP_SERVER_RECEIVED_BYTES, httpRequestContext.receiveMessageSize.get()).put(HttpMeasureConstants.HTTP_SERVER_SENT_BYTES, httpRequestContext.sentMessageSize.get()).record(put.put(tagKey2, TagValue.create(route), HttpRequestContext.METADATA_NO_PROPAGATION).put(HttpMeasureConstants.HTTP_SERVER_STATUS, TagValue.create(i == 0 ? "error" : Integer.toString(i)), HttpRequestContext.METADATA_NO_PROPAGATION).build());
    }
}
