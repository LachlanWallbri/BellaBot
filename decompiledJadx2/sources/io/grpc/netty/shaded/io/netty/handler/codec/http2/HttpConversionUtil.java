package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.handler.codec.UnsupportedValueConverter;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpScheme;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.internal.InternalThreadLocalMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpConversionUtil {
    private static final AsciiString EMPTY_REQUEST_PATH;
    private static final CharSequenceMap<AsciiString> HTTP_TO_HTTP2_HEADER_BLACKLIST = new CharSequenceMap<>();
    public static final HttpMethod OUT_OF_MESSAGE_SEQUENCE_METHOD;
    public static final String OUT_OF_MESSAGE_SEQUENCE_PATH = "";
    public static final HttpResponseStatus OUT_OF_MESSAGE_SEQUENCE_RETURN_CODE;

    static {
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) HttpHeaderNames.CONNECTION, AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) HttpHeaderNames.KEEP_ALIVE, AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) HttpHeaderNames.PROXY_CONNECTION, AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) HttpHeaderNames.TRANSFER_ENCODING, AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) HttpHeaderNames.HOST, AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) HttpHeaderNames.UPGRADE, AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) ExtensionHeaderNames.STREAM_ID.text(), AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) ExtensionHeaderNames.SCHEME.text(), AsciiString.EMPTY_STRING);
        HTTP_TO_HTTP2_HEADER_BLACKLIST.add((CharSequenceMap<AsciiString>) ExtensionHeaderNames.PATH.text(), AsciiString.EMPTY_STRING);
        OUT_OF_MESSAGE_SEQUENCE_METHOD = HttpMethod.OPTIONS;
        OUT_OF_MESSAGE_SEQUENCE_RETURN_CODE = HttpResponseStatus.f8370OK;
        EMPTY_REQUEST_PATH = AsciiString.cached("/");
    }

    private HttpConversionUtil() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum ExtensionHeaderNames {
        STREAM_ID("x-http2-stream-id"),
        SCHEME("x-http2-scheme"),
        PATH("x-http2-path"),
        STREAM_PROMISE_ID("x-http2-stream-promise-id"),
        STREAM_DEPENDENCY_ID("x-http2-stream-dependency-id"),
        STREAM_WEIGHT("x-http2-stream-weight");

        private final AsciiString text;

        ExtensionHeaderNames(String str) {
            this.text = AsciiString.cached(str);
        }

        public AsciiString text() {
            return this.text;
        }
    }

    public static HttpResponseStatus parseStatus(CharSequence charSequence) throws Http2Exception {
        try {
            HttpResponseStatus parseLine = HttpResponseStatus.parseLine(charSequence);
            if (parseLine != HttpResponseStatus.SWITCHING_PROTOCOLS) {
                return parseLine;
            }
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 status code '%d'", Integer.valueOf(parseLine.code()));
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, th, "Unrecognized HTTP status code '%s' encountered in translation to HTTP/1.x", charSequence);
        }
    }

    public static FullHttpResponse toFullHttpResponse(int i, Http2Headers http2Headers, ByteBufAllocator byteBufAllocator, boolean z) throws Http2Exception {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, parseStatus(http2Headers.status()), byteBufAllocator.buffer(), z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultFullHttpResponse, false);
            return defaultFullHttpResponse;
        } catch (Http2Exception e) {
            defaultFullHttpResponse.release();
            throw e;
        } catch (Throwable th) {
            defaultFullHttpResponse.release();
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static FullHttpRequest toFullHttpRequest(int i, Http2Headers http2Headers, ByteBufAllocator byteBufAllocator, boolean z) throws Http2Exception {
        DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.valueOf(((CharSequence) ObjectUtil.checkNotNull(http2Headers.method(), "method header cannot be null in conversion to HTTP/1.x")).toString()), ((CharSequence) ObjectUtil.checkNotNull(http2Headers.path(), "path header cannot be null in conversion to HTTP/1.x")).toString(), byteBufAllocator.buffer(), z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultFullHttpRequest, false);
            return defaultFullHttpRequest;
        } catch (Http2Exception e) {
            defaultFullHttpRequest.release();
            throw e;
        } catch (Throwable th) {
            defaultFullHttpRequest.release();
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static HttpRequest toHttpRequest(int i, Http2Headers http2Headers, boolean z) throws Http2Exception {
        DefaultHttpRequest defaultHttpRequest = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.valueOf(((CharSequence) ObjectUtil.checkNotNull(http2Headers.method(), "method header cannot be null in conversion to HTTP/1.x")).toString()), ((CharSequence) ObjectUtil.checkNotNull(http2Headers.path(), "path header cannot be null in conversion to HTTP/1.x")).toString(), z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultHttpRequest.headers(), defaultHttpRequest.protocolVersion(), false, true);
            return defaultHttpRequest;
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static HttpResponse toHttpResponse(int i, Http2Headers http2Headers, boolean z) throws Http2Exception {
        DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, parseStatus(http2Headers.status()), z);
        try {
            addHttp2ToHttpHeaders(i, http2Headers, defaultHttpResponse.headers(), defaultHttpResponse.protocolVersion(), false, true);
            return defaultHttpResponse;
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static void addHttp2ToHttpHeaders(int i, Http2Headers http2Headers, FullHttpMessage fullHttpMessage, boolean z) throws Http2Exception {
        addHttp2ToHttpHeaders(i, http2Headers, z ? fullHttpMessage.trailingHeaders() : fullHttpMessage.headers(), fullHttpMessage.protocolVersion(), z, fullHttpMessage instanceof HttpRequest);
    }

    public static void addHttp2ToHttpHeaders(int i, Http2Headers http2Headers, HttpHeaders httpHeaders, HttpVersion httpVersion, boolean z, boolean z2) throws Http2Exception {
        try {
            new Http2ToHttpHeaderTranslator(i, httpHeaders, z2).translateHeaders(http2Headers);
            httpHeaders.remove(HttpHeaderNames.TRANSFER_ENCODING);
            httpHeaders.remove(HttpHeaderNames.TRAILER);
            if (z) {
                return;
            }
            httpHeaders.setInt(ExtensionHeaderNames.STREAM_ID.text(), i);
            HttpUtil.setKeepAlive(httpHeaders, httpVersion, true);
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.streamError(i, Http2Error.PROTOCOL_ERROR, th, "HTTP/2 to HTTP/1.x headers conversion error", new Object[0]);
        }
    }

    public static Http2Headers toHttp2Headers(HttpMessage httpMessage, boolean z) {
        HttpHeaders headers = httpMessage.headers();
        DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers(z, headers.size());
        if (httpMessage instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpMessage;
            URI create = URI.create(httpRequest.uri());
            defaultHttp2Headers.path(toHttp2Path(create));
            defaultHttp2Headers.method(httpRequest.method().asciiName());
            setHttp2Scheme(headers, create, defaultHttp2Headers);
            if (!HttpUtil.isOriginForm(create) && !HttpUtil.isAsteriskForm(create)) {
                String asString = headers.getAsString(HttpHeaderNames.HOST);
                if (asString == null || asString.isEmpty()) {
                    asString = create.getAuthority();
                }
                setHttp2Authority(asString, defaultHttp2Headers);
            }
        } else if (httpMessage instanceof HttpResponse) {
            defaultHttp2Headers.status(((HttpResponse) httpMessage).status().codeAsText());
        }
        toHttp2Headers(headers, defaultHttp2Headers);
        return defaultHttp2Headers;
    }

    public static Http2Headers toHttp2Headers(HttpHeaders httpHeaders, boolean z) {
        if (httpHeaders.isEmpty()) {
            return EmptyHttp2Headers.INSTANCE;
        }
        DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers(z, httpHeaders.size());
        toHttp2Headers(httpHeaders, defaultHttp2Headers);
        return defaultHttp2Headers;
    }

    private static CharSequenceMap<AsciiString> toLowercaseMap(Iterator<? extends CharSequence> it, int i) {
        CharSequenceMap<AsciiString> charSequenceMap = new CharSequenceMap<>(true, UnsupportedValueConverter.instance(), i);
        while (it.hasNext()) {
            AsciiString lowerCase = AsciiString.m3920of(it.next()).toLowerCase();
            try {
                int forEachByte = lowerCase.forEachByte(ByteProcessor.FIND_COMMA);
                if (forEachByte != -1) {
                    int i2 = forEachByte;
                    int i3 = 0;
                    do {
                        charSequenceMap.add((CharSequenceMap<AsciiString>) lowerCase.subSequence(i3, i2, false).trim(), AsciiString.EMPTY_STRING);
                        i3 = i2 + 1;
                        if (i3 >= lowerCase.length()) {
                            break;
                        }
                        i2 = lowerCase.forEachByte(i3, lowerCase.length() - i3, ByteProcessor.FIND_COMMA);
                    } while (i2 != -1);
                    charSequenceMap.add((CharSequenceMap<AsciiString>) lowerCase.subSequence(i3, lowerCase.length(), false).trim(), AsciiString.EMPTY_STRING);
                } else {
                    charSequenceMap.add((CharSequenceMap<AsciiString>) lowerCase.trim(), AsciiString.EMPTY_STRING);
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return charSequenceMap;
    }

    private static void toHttp2HeadersFilterTE(Map.Entry<CharSequence, CharSequence> entry, Http2Headers http2Headers) {
        if (AsciiString.indexOf(entry.getValue(), ',', 0) == -1) {
            if (AsciiString.contentEqualsIgnoreCase(AsciiString.trim(entry.getValue()), HttpHeaderValues.TRAILERS)) {
                http2Headers.add((Http2Headers) HttpHeaderNames.f8368TE, HttpHeaderValues.TRAILERS);
            }
        } else {
            Iterator<CharSequence> it = StringUtil.unescapeCsvFields(entry.getValue()).iterator();
            while (it.hasNext()) {
                if (AsciiString.contentEqualsIgnoreCase(AsciiString.trim(it.next()), HttpHeaderValues.TRAILERS)) {
                    http2Headers.add((Http2Headers) HttpHeaderNames.f8368TE, HttpHeaderValues.TRAILERS);
                    return;
                }
            }
        }
    }

    public static void toHttp2Headers(HttpHeaders httpHeaders, Http2Headers http2Headers) {
        Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence = httpHeaders.iteratorCharSequence();
        CharSequenceMap<AsciiString> lowercaseMap = toLowercaseMap(httpHeaders.valueCharSequenceIterator(HttpHeaderNames.CONNECTION), 8);
        while (iteratorCharSequence.hasNext()) {
            Map.Entry<CharSequence, CharSequence> next = iteratorCharSequence.next();
            AsciiString lowerCase = AsciiString.m3920of(next.getKey()).toLowerCase();
            if (!HTTP_TO_HTTP2_HEADER_BLACKLIST.contains(lowerCase) && !lowercaseMap.contains(lowerCase)) {
                if (lowerCase.contentEqualsIgnoreCase(HttpHeaderNames.f8368TE)) {
                    toHttp2HeadersFilterTE(next, http2Headers);
                } else if (lowerCase.contentEqualsIgnoreCase(HttpHeaderNames.COOKIE)) {
                    AsciiString m3920of = AsciiString.m3920of(next.getValue());
                    try {
                        int forEachByte = m3920of.forEachByte(ByteProcessor.FIND_SEMI_COLON);
                        if (forEachByte != -1) {
                            int i = forEachByte;
                            int i2 = 0;
                            do {
                                http2Headers.add((Http2Headers) HttpHeaderNames.COOKIE, m3920of.subSequence(i2, i, false));
                                i2 = i + 2;
                                if (i2 >= m3920of.length()) {
                                    break;
                                } else {
                                    i = m3920of.forEachByte(i2, m3920of.length() - i2, ByteProcessor.FIND_SEMI_COLON);
                                }
                            } while (i != -1);
                            if (i2 >= m3920of.length()) {
                                throw new IllegalArgumentException("cookie value is of unexpected format: " + ((Object) m3920of));
                            }
                            http2Headers.add((Http2Headers) HttpHeaderNames.COOKIE, m3920of.subSequence(i2, m3920of.length(), false));
                        } else {
                            http2Headers.add((Http2Headers) HttpHeaderNames.COOKIE, m3920of);
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                } else {
                    http2Headers.add((Http2Headers) lowerCase, (AsciiString) next.getValue());
                }
            }
        }
    }

    private static AsciiString toHttp2Path(URI uri) {
        StringBuilder sb = new StringBuilder(StringUtil.length(uri.getRawPath()) + StringUtil.length(uri.getRawQuery()) + StringUtil.length(uri.getRawFragment()) + 2);
        if (!StringUtil.isNullOrEmpty(uri.getRawPath())) {
            sb.append(uri.getRawPath());
        }
        if (!StringUtil.isNullOrEmpty(uri.getRawQuery())) {
            sb.append('?');
            sb.append(uri.getRawQuery());
        }
        if (!StringUtil.isNullOrEmpty(uri.getRawFragment())) {
            sb.append('#');
            sb.append(uri.getRawFragment());
        }
        String sb2 = sb.toString();
        return sb2.isEmpty() ? EMPTY_REQUEST_PATH : new AsciiString(sb2);
    }

    static void setHttp2Authority(String str, Http2Headers http2Headers) {
        if (str != null) {
            if (str.isEmpty()) {
                http2Headers.authority(AsciiString.EMPTY_STRING);
                return;
            }
            int indexOf = str.indexOf(64) + 1;
            int length = str.length() - indexOf;
            if (length == 0) {
                throw new IllegalArgumentException("authority: " + str);
            }
            http2Headers.authority(new AsciiString(str, indexOf, length));
        }
    }

    private static void setHttp2Scheme(HttpHeaders httpHeaders, URI uri, Http2Headers http2Headers) {
        String scheme = uri.getScheme();
        if (scheme != null) {
            http2Headers.scheme(new AsciiString(scheme));
            return;
        }
        String str = httpHeaders.get(ExtensionHeaderNames.SCHEME.text());
        if (str != null) {
            http2Headers.scheme(AsciiString.m3920of(str));
        } else if (uri.getPort() == HttpScheme.HTTPS.port()) {
            http2Headers.scheme(HttpScheme.HTTPS.name());
        } else {
            if (uri.getPort() == HttpScheme.HTTP.port()) {
                http2Headers.scheme(HttpScheme.HTTP.name());
                return;
            }
            throw new IllegalArgumentException(":scheme must be specified. see https://tools.ietf.org/html/rfc7540#section-8.1.2.3");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Http2ToHttpHeaderTranslator {
        private static final CharSequenceMap<AsciiString> REQUEST_HEADER_TRANSLATIONS = new CharSequenceMap<>();
        private static final CharSequenceMap<AsciiString> RESPONSE_HEADER_TRANSLATIONS = new CharSequenceMap<>();
        private final HttpHeaders output;
        private final int streamId;
        private final CharSequenceMap<AsciiString> translations;

        static {
            RESPONSE_HEADER_TRANSLATIONS.add((CharSequenceMap<AsciiString>) Http2Headers.PseudoHeaderName.AUTHORITY.value(), HttpHeaderNames.HOST);
            RESPONSE_HEADER_TRANSLATIONS.add((CharSequenceMap<AsciiString>) Http2Headers.PseudoHeaderName.SCHEME.value(), ExtensionHeaderNames.SCHEME.text());
            REQUEST_HEADER_TRANSLATIONS.add(RESPONSE_HEADER_TRANSLATIONS);
            RESPONSE_HEADER_TRANSLATIONS.add((CharSequenceMap<AsciiString>) Http2Headers.PseudoHeaderName.PATH.value(), ExtensionHeaderNames.PATH.text());
        }

        Http2ToHttpHeaderTranslator(int i, HttpHeaders httpHeaders, boolean z) {
            this.streamId = i;
            this.output = httpHeaders;
            this.translations = z ? REQUEST_HEADER_TRANSLATIONS : RESPONSE_HEADER_TRANSLATIONS;
        }

        public void translateHeaders(Iterable<Map.Entry<CharSequence, CharSequence>> iterable) throws Http2Exception {
            StringBuilder sb = null;
            for (Map.Entry<CharSequence, CharSequence> entry : iterable) {
                CharSequence key = entry.getKey();
                CharSequence value = entry.getValue();
                AsciiString asciiString = this.translations.get(key);
                if (asciiString != null) {
                    this.output.add(asciiString, AsciiString.m3920of(value));
                } else if (Http2Headers.PseudoHeaderName.isPseudoHeader(key)) {
                    continue;
                } else {
                    if (key.length() == 0 || key.charAt(0) == ':') {
                        throw Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 header '%s' encountered in translation to HTTP/1.x", key);
                    }
                    if (HttpHeaderNames.COOKIE.equals(key)) {
                        if (sb == null) {
                            sb = InternalThreadLocalMap.get().stringBuilder();
                        } else if (sb.length() > 0) {
                            sb.append("; ");
                        }
                        sb.append(value);
                    } else {
                        this.output.add(key, value);
                    }
                }
            }
            if (sb != null) {
                this.output.add(HttpHeaderNames.COOKIE, sb.toString());
            }
        }
    }
}
