package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2HeadersEncoder {
    public static final SensitivityDetector NEVER_SENSITIVE = new SensitivityDetector() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder.1
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder.SensitivityDetector
        public boolean isSensitive(CharSequence charSequence, CharSequence charSequence2) {
            return false;
        }
    };
    public static final SensitivityDetector ALWAYS_SENSITIVE = new SensitivityDetector() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder.2
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersEncoder.SensitivityDetector
        public boolean isSensitive(CharSequence charSequence, CharSequence charSequence2) {
            return true;
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Configuration {
        long maxHeaderListSize();

        void maxHeaderListSize(long j) throws Http2Exception;

        long maxHeaderTableSize();

        void maxHeaderTableSize(long j) throws Http2Exception;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface SensitivityDetector {
        boolean isSensitive(CharSequence charSequence, CharSequence charSequence2);
    }

    Configuration configuration();

    void encodeHeaders(int i, Http2Headers http2Headers, ByteBuf byteBuf) throws Http2Exception;
}
