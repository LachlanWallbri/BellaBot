package io.opencensus.contrib.http.util;

import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedInts;
import com.google.common.primitives.UnsignedLongs;
import com.iflytek.cloud.msc.util.AppInfoUtil;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import io.opencensus.trace.TraceOptions;
import io.opencensus.trace.Tracestate;
import io.opencensus.trace.propagation.SpanContextParseException;
import io.opencensus.trace.propagation.TextFormat;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class CloudTraceFormat extends TextFormat {
    static final int CLOUD_TRACE_IS_SAMPLED = 1;
    static final int MIN_HEADER_SIZE = 34;
    static final String NOT_SAMPLED = "0";
    static final String SAMPLED = "1";
    static final char SPAN_ID_DELIMITER = '/';
    static final int SPAN_ID_START_POS = 33;
    static final int TRACE_ID_SIZE = 32;
    static final String TRACE_OPTION_DELIMITER = ";o=";
    static final String HEADER_NAME = "X-Cloud-Trace-Context";
    static final List<String> FIELDS = Collections.singletonList(HEADER_NAME);
    static final TraceOptions OPTIONS_SAMPLED = TraceOptions.builder().setIsSampled(true).build();
    static final TraceOptions OPTIONS_NOT_SAMPLED = TraceOptions.DEFAULT;
    static final int TRACE_OPTION_DELIMITER_SIZE = 3;
    private static final Tracestate TRACESTATE_DEFAULT = Tracestate.builder().build();

    @Override // io.opencensus.trace.propagation.TextFormat
    public List<String> fields() {
        return FIELDS;
    }

    @Override // io.opencensus.trace.propagation.TextFormat
    public <C> void inject(SpanContext spanContext, C c, TextFormat.Setter<C> setter) {
        Preconditions.checkNotNull(spanContext, "spanContext");
        Preconditions.checkNotNull(setter, "setter");
        Preconditions.checkNotNull(c, AppInfoUtil.CARRIER);
        StringBuilder sb = new StringBuilder();
        sb.append(spanContext.getTraceId().toLowerBase16());
        sb.append('/');
        sb.append(UnsignedLongs.toString(spanIdToLong(spanContext.getSpanId())));
        sb.append(TRACE_OPTION_DELIMITER);
        sb.append(spanContext.getTraceOptions().isSampled() ? "1" : "0");
        setter.put(c, HEADER_NAME, sb.toString());
    }

    @Override // io.opencensus.trace.propagation.TextFormat
    public <C> SpanContext extract(C c, TextFormat.Getter<C> getter) throws SpanContextParseException {
        Preconditions.checkNotNull(c, AppInfoUtil.CARRIER);
        Preconditions.checkNotNull(getter, "getter");
        try {
            String str = getter.get(c, HEADER_NAME);
            if (str == null || str.length() < 34) {
                throw new SpanContextParseException("Missing or too short header: X-Cloud-Trace-Context");
            }
            Preconditions.checkArgument(str.charAt(32) == '/', "Invalid TRACE_ID size");
            TraceId fromLowerBase16 = TraceId.fromLowerBase16(str.subSequence(0, 32));
            int indexOf = str.indexOf(TRACE_OPTION_DELIMITER, 32);
            SpanId longToSpanId = longToSpanId(UnsignedLongs.parseUnsignedLong(str.subSequence(33, indexOf < 0 ? str.length() : indexOf).toString(), 10));
            TraceOptions traceOptions = OPTIONS_NOT_SAMPLED;
            if (indexOf > 0 && (UnsignedInts.parseUnsignedInt(str.substring(indexOf + TRACE_OPTION_DELIMITER_SIZE), 10) & 1) != 0) {
                traceOptions = OPTIONS_SAMPLED;
            }
            return SpanContext.create(fromLowerBase16, longToSpanId, traceOptions, TRACESTATE_DEFAULT);
        } catch (IllegalArgumentException e) {
            throw new SpanContextParseException("Invalid input", e);
        }
    }

    private static SpanId longToSpanId(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(j);
        return SpanId.fromBytes(allocate.array());
    }

    private static long spanIdToLong(SpanId spanId) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.put(spanId.getBytes());
        return allocate.getLong(0);
    }
}
