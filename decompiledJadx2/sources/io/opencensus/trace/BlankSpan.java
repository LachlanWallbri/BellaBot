package io.opencensus.trace;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.opencensus.internal.Utils;
import java.util.Map;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class BlankSpan extends Span {
    public static final BlankSpan INSTANCE = new BlankSpan();

    @Override // io.opencensus.trace.Span
    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
    }

    public String toString() {
        return "BlankSpan";
    }

    private BlankSpan() {
        super(SpanContext.INVALID, null);
    }

    @Override // io.opencensus.trace.Span
    public void putAttribute(String str, AttributeValue attributeValue) {
        Utils.checkNotNull(str, TransferTable.COLUMN_KEY);
        Utils.checkNotNull(attributeValue, ES6Iterator.VALUE_PROPERTY);
    }

    @Override // io.opencensus.trace.Span
    public void putAttributes(Map<String, AttributeValue> map) {
        Utils.checkNotNull(map, "attributes");
    }

    @Override // io.opencensus.trace.Span
    public void addAnnotation(String str, Map<String, AttributeValue> map) {
        Utils.checkNotNull(str, "description");
        Utils.checkNotNull(map, "attributes");
    }

    @Override // io.opencensus.trace.Span
    public void addAnnotation(Annotation annotation) {
        Utils.checkNotNull(annotation, "annotation");
    }

    @Override // io.opencensus.trace.Span
    public void addMessageEvent(MessageEvent messageEvent) {
        Utils.checkNotNull(messageEvent, "messageEvent");
    }

    @Override // io.opencensus.trace.Span
    public void addLink(Link link) {
        Utils.checkNotNull(link, "link");
    }

    @Override // io.opencensus.trace.Span
    public void setStatus(Status status) {
        Utils.checkNotNull(status, "status");
    }

    @Override // io.opencensus.trace.Span
    public void end(EndSpanOptions endSpanOptions) {
        Utils.checkNotNull(endSpanOptions, "options");
    }
}
