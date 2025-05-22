package io.opencensus.contrib.http;

import com.google.common.base.Preconditions;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagMetadata;
import io.opencensus.trace.Span;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public class HttpRequestContext {
    static final long INVALID_STARTTIME = -1;
    static final TagMetadata METADATA_NO_PROPAGATION = TagMetadata.create(TagMetadata.TagTtl.NO_PROPAGATION);
    final long requestStartTime;
    final Span span;
    final TagContext tagContext;
    AtomicLong sentMessageSize = new AtomicLong();
    AtomicLong receiveMessageSize = new AtomicLong();
    AtomicLong sentSeqId = new AtomicLong();
    AtomicLong receviedSeqId = new AtomicLong();

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequestContext(Span span, TagContext tagContext) {
        Preconditions.checkNotNull(span, "span");
        Preconditions.checkNotNull(tagContext, "tagContext");
        this.span = span;
        this.tagContext = tagContext;
        this.requestStartTime = System.nanoTime();
    }
}
