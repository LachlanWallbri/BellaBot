package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.handler.codec.Headers;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jboss.netty.handler.codec.spdy.SpdyHeaders;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface SpdyHeaders extends Headers<CharSequence, CharSequence, SpdyHeaders> {
    boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z);

    List<String> getAllAsString(CharSequence charSequence);

    String getAsString(CharSequence charSequence);

    Iterator<Map.Entry<String, String>> iteratorAsString();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class HttpNames {
        public static final AsciiString HOST = AsciiString.cached(SpdyHeaders.HttpNames.HOST);
        public static final AsciiString METHOD = AsciiString.cached(":method");
        public static final AsciiString PATH = AsciiString.cached(":path");
        public static final AsciiString SCHEME = AsciiString.cached(":scheme");
        public static final AsciiString STATUS = AsciiString.cached(":status");
        public static final AsciiString VERSION = AsciiString.cached(SpdyHeaders.HttpNames.VERSION);

        private HttpNames() {
        }
    }
}
