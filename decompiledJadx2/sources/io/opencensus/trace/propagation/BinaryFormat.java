package io.opencensus.trace.propagation;

import io.opencensus.internal.Utils;
import io.opencensus.trace.SpanContext;
import java.text.ParseException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class BinaryFormat {
    static final NoopBinaryFormat NOOP_BINARY_FORMAT = new NoopBinaryFormat();

    @Deprecated
    public byte[] toBinaryValue(SpanContext spanContext) {
        return toByteArray(spanContext);
    }

    public byte[] toByteArray(SpanContext spanContext) {
        return toBinaryValue(spanContext);
    }

    @Deprecated
    public SpanContext fromBinaryValue(byte[] bArr) throws ParseException {
        try {
            return fromByteArray(bArr);
        } catch (SpanContextParseException e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    public SpanContext fromByteArray(byte[] bArr) throws SpanContextParseException {
        try {
            return fromBinaryValue(bArr);
        } catch (ParseException e) {
            throw new SpanContextParseException("Error while parsing.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BinaryFormat getNoopBinaryFormat() {
        return NOOP_BINARY_FORMAT;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    private static final class NoopBinaryFormat extends BinaryFormat {
        @Override // io.opencensus.trace.propagation.BinaryFormat
        public byte[] toByteArray(SpanContext spanContext) {
            Utils.checkNotNull(spanContext, "spanContext");
            return new byte[0];
        }

        @Override // io.opencensus.trace.propagation.BinaryFormat
        public SpanContext fromByteArray(byte[] bArr) {
            Utils.checkNotNull(bArr, "bytes");
            return SpanContext.INVALID;
        }

        private NoopBinaryFormat() {
        }
    }
}
