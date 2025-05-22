package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.grpc.internal.GrpcUtil;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import org.apache.http.cookie.ClientCookie;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpHeaderNames {
    public static final AsciiString ACCEPT = AsciiString.cached("accept");
    public static final AsciiString ACCEPT_CHARSET = AsciiString.cached("accept-charset");
    public static final AsciiString ACCEPT_ENCODING = AsciiString.cached(GrpcUtil.CONTENT_ACCEPT_ENCODING);
    public static final AsciiString ACCEPT_LANGUAGE = AsciiString.cached("accept-language");
    public static final AsciiString ACCEPT_RANGES = AsciiString.cached("accept-ranges");
    public static final AsciiString ACCEPT_PATCH = AsciiString.cached("accept-patch");
    public static final AsciiString ACCESS_CONTROL_ALLOW_CREDENTIALS = AsciiString.cached("access-control-allow-credentials");
    public static final AsciiString ACCESS_CONTROL_ALLOW_HEADERS = AsciiString.cached("access-control-allow-headers");
    public static final AsciiString ACCESS_CONTROL_ALLOW_METHODS = AsciiString.cached("access-control-allow-methods");
    public static final AsciiString ACCESS_CONTROL_ALLOW_ORIGIN = AsciiString.cached("access-control-allow-origin");
    public static final AsciiString ACCESS_CONTROL_EXPOSE_HEADERS = AsciiString.cached("access-control-expose-headers");
    public static final AsciiString ACCESS_CONTROL_MAX_AGE = AsciiString.cached("access-control-max-age");
    public static final AsciiString ACCESS_CONTROL_REQUEST_HEADERS = AsciiString.cached("access-control-request-headers");
    public static final AsciiString ACCESS_CONTROL_REQUEST_METHOD = AsciiString.cached("access-control-request-method");
    public static final AsciiString AGE = AsciiString.cached("age");
    public static final AsciiString ALLOW = AsciiString.cached("allow");
    public static final AsciiString AUTHORIZATION = AsciiString.cached("authorization");
    public static final AsciiString CACHE_CONTROL = AsciiString.cached("cache-control");
    public static final AsciiString CONNECTION = AsciiString.cached("connection");
    public static final AsciiString CONTENT_BASE = AsciiString.cached("content-base");
    public static final AsciiString CONTENT_ENCODING = AsciiString.cached(GrpcUtil.CONTENT_ENCODING);
    public static final AsciiString CONTENT_LANGUAGE = AsciiString.cached("content-language");
    public static final AsciiString CONTENT_LENGTH = AsciiString.cached(StreamUtil.CONTENT_LENGTH);
    public static final AsciiString CONTENT_LOCATION = AsciiString.cached("content-location");
    public static final AsciiString CONTENT_TRANSFER_ENCODING = AsciiString.cached("content-transfer-encoding");
    public static final AsciiString CONTENT_DISPOSITION = AsciiString.cached("content-disposition");
    public static final AsciiString CONTENT_MD5 = AsciiString.cached("content-md5");
    public static final AsciiString CONTENT_RANGE = AsciiString.cached("content-range");
    public static final AsciiString CONTENT_SECURITY_POLICY = AsciiString.cached("content-security-policy");
    public static final AsciiString CONTENT_TYPE = AsciiString.cached("content-type");
    public static final AsciiString COOKIE = AsciiString.cached("cookie");
    public static final AsciiString DATE = AsciiString.cached(TmpConstant.TYPE_VALUE_DATE);
    public static final AsciiString DNT = AsciiString.cached("dnt");
    public static final AsciiString ETAG = AsciiString.cached(TransferTable.COLUMN_ETAG);
    public static final AsciiString EXPECT = AsciiString.cached("expect");
    public static final AsciiString EXPIRES = AsciiString.cached(ClientCookie.EXPIRES_ATTR);
    public static final AsciiString FROM = AsciiString.cached("from");
    public static final AsciiString HOST = AsciiString.cached("host");
    public static final AsciiString IF_MATCH = AsciiString.cached("if-match");
    public static final AsciiString IF_MODIFIED_SINCE = AsciiString.cached("if-modified-since");
    public static final AsciiString IF_NONE_MATCH = AsciiString.cached("if-none-match");
    public static final AsciiString IF_RANGE = AsciiString.cached("if-range");
    public static final AsciiString IF_UNMODIFIED_SINCE = AsciiString.cached("if-unmodified-since");

    @Deprecated
    public static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");
    public static final AsciiString LAST_MODIFIED = AsciiString.cached("last-modified");
    public static final AsciiString LOCATION = AsciiString.cached(RequestParameters.SUBRESOURCE_LOCATION);
    public static final AsciiString MAX_FORWARDS = AsciiString.cached("max-forwards");
    public static final AsciiString ORIGIN = AsciiString.cached("origin");
    public static final AsciiString PRAGMA = AsciiString.cached("pragma");
    public static final AsciiString PROXY_AUTHENTICATE = AsciiString.cached("proxy-authenticate");
    public static final AsciiString PROXY_AUTHORIZATION = AsciiString.cached("proxy-authorization");

    @Deprecated
    public static final AsciiString PROXY_CONNECTION = AsciiString.cached("proxy-connection");
    public static final AsciiString RANGE = AsciiString.cached("range");
    public static final AsciiString REFERER = AsciiString.cached(RequestParameters.SUBRESOURCE_REFERER);
    public static final AsciiString RETRY_AFTER = AsciiString.cached("retry-after");
    public static final AsciiString SEC_WEBSOCKET_KEY1 = AsciiString.cached("sec-websocket-key1");
    public static final AsciiString SEC_WEBSOCKET_KEY2 = AsciiString.cached("sec-websocket-key2");
    public static final AsciiString SEC_WEBSOCKET_LOCATION = AsciiString.cached("sec-websocket-location");
    public static final AsciiString SEC_WEBSOCKET_ORIGIN = AsciiString.cached("sec-websocket-origin");
    public static final AsciiString SEC_WEBSOCKET_PROTOCOL = AsciiString.cached("sec-websocket-protocol");
    public static final AsciiString SEC_WEBSOCKET_VERSION = AsciiString.cached("sec-websocket-version");
    public static final AsciiString SEC_WEBSOCKET_KEY = AsciiString.cached("sec-websocket-key");
    public static final AsciiString SEC_WEBSOCKET_ACCEPT = AsciiString.cached("sec-websocket-accept");
    public static final AsciiString SEC_WEBSOCKET_EXTENSIONS = AsciiString.cached("sec-websocket-extensions");
    public static final AsciiString SERVER = AsciiString.cached("server");
    public static final AsciiString SET_COOKIE = AsciiString.cached("set-cookie");
    public static final AsciiString SET_COOKIE2 = AsciiString.cached("set-cookie2");

    /* renamed from: TE */
    public static final AsciiString f8368TE = AsciiString.cached("te");
    public static final AsciiString TRAILER = AsciiString.cached("trailer");
    public static final AsciiString TRANSFER_ENCODING = AsciiString.cached("transfer-encoding");
    public static final AsciiString UPGRADE = AsciiString.cached("upgrade");
    public static final AsciiString UPGRADE_INSECURE_REQUESTS = AsciiString.cached("upgrade-insecure-requests");
    public static final AsciiString USER_AGENT = AsciiString.cached("user-agent");
    public static final AsciiString VARY = AsciiString.cached("vary");
    public static final AsciiString VIA = AsciiString.cached("via");
    public static final AsciiString WARNING = AsciiString.cached("warning");
    public static final AsciiString WEBSOCKET_LOCATION = AsciiString.cached("websocket-location");
    public static final AsciiString WEBSOCKET_ORIGIN = AsciiString.cached("websocket-origin");
    public static final AsciiString WEBSOCKET_PROTOCOL = AsciiString.cached("websocket-protocol");
    public static final AsciiString WWW_AUTHENTICATE = AsciiString.cached("www-authenticate");
    public static final AsciiString X_FRAME_OPTIONS = AsciiString.cached("x-frame-options");
    public static final AsciiString X_REQUESTED_WITH = AsciiString.cached("x-requested-with");

    private HttpHeaderNames() {
    }
}
