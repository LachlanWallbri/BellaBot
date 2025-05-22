package io.opencensus.contrib.http;

import com.google.common.base.Preconditions;
import io.opencensus.contrib.http.util.HttpTraceAttributeConstants;
import io.opencensus.contrib.http.util.HttpTraceUtil;
import io.opencensus.tags.TagContext;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.Span;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
abstract class AbstractHttpHandler<Q, P> {
    final HttpExtractor<Q, P> extractor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractHttpHandler(HttpExtractor<Q, P> httpExtractor) {
        Preconditions.checkNotNull(httpExtractor, "extractor");
        this.extractor = httpExtractor;
    }

    static void recordMessageEvent(Span span, long j, MessageEvent.Type type, long j2, long j3) {
        span.addMessageEvent(MessageEvent.builder(type, j).setUncompressedMessageSize(j2).setCompressedMessageSize(j3).build());
    }

    private static void putAttributeIfNotEmptyOrNull(Span span, String str, @Nullable String str2) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        span.putAttribute(str, AttributeValue.stringAttributeValue(str2));
    }

    public final void handleMessageSent(HttpRequestContext httpRequestContext, long j) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        httpRequestContext.sentMessageSize.addAndGet(j);
        if (httpRequestContext.span.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            recordMessageEvent(httpRequestContext.span, httpRequestContext.sentSeqId.addAndGet(1L), MessageEvent.Type.SENT, j, 0L);
        }
    }

    public final void handleMessageReceived(HttpRequestContext httpRequestContext, long j) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        httpRequestContext.receiveMessageSize.addAndGet(j);
        if (httpRequestContext.span.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            recordMessageEvent(httpRequestContext.span, httpRequestContext.receviedSeqId.addAndGet(1L), MessageEvent.Type.RECEIVED, j, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void spanEnd(Span span, int i, @Nullable Throwable th) {
        if (span.getOptions().contains(Span.Options.RECORD_EVENTS)) {
            span.putAttribute(HttpTraceAttributeConstants.HTTP_STATUS_CODE, AttributeValue.longAttributeValue(i));
            span.setStatus(HttpTraceUtil.parseResponseStatus(i, th));
        }
        span.end();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getSpanName(Q q, HttpExtractor<Q, P> httpExtractor) {
        String path = httpExtractor.getPath(q);
        if (path == null) {
            path = "/";
        }
        if (path.startsWith("/")) {
            return path;
        }
        return "/" + path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addSpanRequestAttributes(Span span, Q q, HttpExtractor<Q, P> httpExtractor) {
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_USER_AGENT, httpExtractor.getUserAgent(q));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_HOST, httpExtractor.getHost(q));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_METHOD, httpExtractor.getMethod(q));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_PATH, httpExtractor.getPath(q));
        putAttributeIfNotEmptyOrNull(span, "http.route", httpExtractor.getRoute(q));
        putAttributeIfNotEmptyOrNull(span, HttpTraceAttributeConstants.HTTP_URL, httpExtractor.getUrl(q));
    }

    public Span getSpanFromContext(HttpRequestContext httpRequestContext) {
        Preconditions.checkNotNull(httpRequestContext, "context");
        return httpRequestContext.span;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequestContext getNewContext(Span span, TagContext tagContext) {
        return new HttpRequestContext(span, tagContext);
    }
}
