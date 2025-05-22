package io.opencensus.trace;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.opencensus.internal.Utils;
import io.opencensus.trace.internal.BaseMessageEventUtils;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Span {
    private final SpanContext context;
    private final Set<Options> options;
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.emptyMap();
    private static final Set<Options> DEFAULT_OPTIONS = Collections.unmodifiableSet(EnumSet.noneOf(Options.class));

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public enum Kind {
        SERVER,
        CLIENT
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public enum Options {
        RECORD_EVENTS
    }

    public abstract void addAnnotation(Annotation annotation);

    public abstract void addAnnotation(String str, Map<String, AttributeValue> map);

    public abstract void addLink(Link link);

    public abstract void end(EndSpanOptions endSpanOptions);

    /* JADX INFO: Access modifiers changed from: protected */
    public Span(SpanContext spanContext, @Nullable EnumSet<Options> enumSet) {
        Set<Options> unmodifiableSet;
        this.context = (SpanContext) Utils.checkNotNull(spanContext, "context");
        if (enumSet == null) {
            unmodifiableSet = DEFAULT_OPTIONS;
        } else {
            unmodifiableSet = Collections.unmodifiableSet(EnumSet.copyOf((EnumSet) enumSet));
        }
        this.options = unmodifiableSet;
        Utils.checkArgument(!spanContext.getTraceOptions().isSampled() || this.options.contains(Options.RECORD_EVENTS), "Span is sampled, but does not have RECORD_EVENTS set.");
    }

    public void putAttribute(String str, AttributeValue attributeValue) {
        Utils.checkNotNull(str, TransferTable.COLUMN_KEY);
        Utils.checkNotNull(attributeValue, ES6Iterator.VALUE_PROPERTY);
        putAttributes(Collections.singletonMap(str, attributeValue));
    }

    public void putAttributes(Map<String, AttributeValue> map) {
        Utils.checkNotNull(map, "attributes");
        addAttributes(map);
    }

    @Deprecated
    public void addAttributes(Map<String, AttributeValue> map) {
        putAttributes(map);
    }

    public final void addAnnotation(String str) {
        Utils.checkNotNull(str, "description");
        addAnnotation(str, EMPTY_ATTRIBUTES);
    }

    @Deprecated
    public void addNetworkEvent(NetworkEvent networkEvent) {
        addMessageEvent(BaseMessageEventUtils.asMessageEvent(networkEvent));
    }

    public void addMessageEvent(MessageEvent messageEvent) {
        Utils.checkNotNull(messageEvent, "messageEvent");
        addNetworkEvent(BaseMessageEventUtils.asNetworkEvent(messageEvent));
    }

    public void setStatus(Status status) {
        Utils.checkNotNull(status, "status");
    }

    public final void end() {
        end(EndSpanOptions.DEFAULT);
    }

    public final SpanContext getContext() {
        return this.context;
    }

    public final Set<Options> getOptions() {
        return this.options;
    }
}
