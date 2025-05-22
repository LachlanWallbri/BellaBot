package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.amazonaws.services.p048s3.util.Mimetypes;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import org.jboss.netty.handler.codec.http.multipart.HttpPostBodyUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpHeaderValues {
    public static final AsciiString APPLICATION_JSON = AsciiString.cached("application/json");
    public static final AsciiString APPLICATION_X_WWW_FORM_URLENCODED = AsciiString.cached("application/x-www-form-urlencoded");
    public static final AsciiString APPLICATION_OCTET_STREAM = AsciiString.cached("application/octet-stream");
    public static final AsciiString APPLICATION_XHTML = AsciiString.cached("application/xhtml+xml");
    public static final AsciiString APPLICATION_XML = AsciiString.cached(Mimetypes.MIMETYPE_XML);
    public static final AsciiString ATTACHMENT = AsciiString.cached(HttpPostBodyUtil.ATTACHMENT);
    public static final AsciiString BASE64 = AsciiString.cached("base64");
    public static final AsciiString BINARY = AsciiString.cached("binary");
    public static final AsciiString BOUNDARY = AsciiString.cached("boundary");
    public static final AsciiString BYTES = AsciiString.cached("bytes");
    public static final AsciiString CHARSET = AsciiString.cached("charset");
    public static final AsciiString CHUNKED = AsciiString.cached("chunked");
    public static final AsciiString CLOSE = AsciiString.cached("close");
    public static final AsciiString COMPRESS = AsciiString.cached("compress");
    public static final AsciiString CONTINUE = AsciiString.cached("100-continue");
    public static final AsciiString DEFLATE = AsciiString.cached("deflate");
    public static final AsciiString X_DEFLATE = AsciiString.cached("x-deflate");
    public static final AsciiString FILE = AsciiString.cached("file");
    public static final AsciiString FILENAME = AsciiString.cached("filename");
    public static final AsciiString FORM_DATA = AsciiString.cached(HttpPostBodyUtil.FORM_DATA);
    public static final AsciiString GZIP = AsciiString.cached("gzip");
    public static final AsciiString GZIP_DEFLATE = AsciiString.cached("gzip,deflate");
    public static final AsciiString X_GZIP = AsciiString.cached("x-gzip");
    public static final AsciiString IDENTITY = AsciiString.cached("identity");
    public static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");
    public static final AsciiString MAX_AGE = AsciiString.cached("max-age");
    public static final AsciiString MAX_STALE = AsciiString.cached("max-stale");
    public static final AsciiString MIN_FRESH = AsciiString.cached("min-fresh");
    public static final AsciiString MULTIPART_FORM_DATA = AsciiString.cached("multipart/form-data");
    public static final AsciiString MULTIPART_MIXED = AsciiString.cached(HttpPostBodyUtil.MULTIPART_MIXED);
    public static final AsciiString MUST_REVALIDATE = AsciiString.cached("must-revalidate");
    public static final AsciiString NAME = AsciiString.cached("name");
    public static final AsciiString NO_CACHE = AsciiString.cached("no-cache");
    public static final AsciiString NO_STORE = AsciiString.cached("no-store");
    public static final AsciiString NO_TRANSFORM = AsciiString.cached("no-transform");
    public static final AsciiString NONE = AsciiString.cached("none");
    public static final AsciiString ZERO = AsciiString.cached("0");
    public static final AsciiString ONLY_IF_CACHED = AsciiString.cached("only-if-cached");
    public static final AsciiString PRIVATE = AsciiString.cached("private");
    public static final AsciiString PROXY_REVALIDATE = AsciiString.cached("proxy-revalidate");
    public static final AsciiString PUBLIC = AsciiString.cached("public");
    public static final AsciiString QUOTED_PRINTABLE = AsciiString.cached("quoted-printable");
    public static final AsciiString S_MAXAGE = AsciiString.cached("s-maxage");
    public static final AsciiString TEXT_CSS = AsciiString.cached("text/css");
    public static final AsciiString TEXT_HTML = AsciiString.cached("text/html");
    public static final AsciiString TEXT_EVENT_STREAM = AsciiString.cached("text/event-stream");
    public static final AsciiString TEXT_PLAIN = AsciiString.cached("text/plain");
    public static final AsciiString TRAILERS = AsciiString.cached("trailers");
    public static final AsciiString UPGRADE = AsciiString.cached("upgrade");
    public static final AsciiString WEBSOCKET = AsciiString.cached("websocket");
    public static final AsciiString XML_HTTP_REQUEST = AsciiString.cached("XmlHttpRequest");

    private HttpHeaderValues() {
    }
}
