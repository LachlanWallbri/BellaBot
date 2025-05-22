package io.netty.handler.codec.http;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import org.apache.http.HttpStatus;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class HttpResponseStatus implements Comparable<HttpResponseStatus> {
    private final byte[] bytes;
    private final int code;
    private final AsciiString codeAsText;
    private HttpStatusClass codeClass;
    private final String reasonPhrase;
    public static final HttpResponseStatus CONTINUE = newStatus(100, "Continue");
    public static final HttpResponseStatus SWITCHING_PROTOCOLS = newStatus(101, "Switching Protocols");
    public static final HttpResponseStatus PROCESSING = newStatus(102, "Processing");

    /* renamed from: OK */
    public static final HttpResponseStatus f8500OK = newStatus(200, "OK");
    public static final HttpResponseStatus CREATED = newStatus(201, "Created");
    public static final HttpResponseStatus ACCEPTED = newStatus(202, "Accepted");
    public static final HttpResponseStatus NON_AUTHORITATIVE_INFORMATION = newStatus(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, "Non-Authoritative Information");
    public static final HttpResponseStatus NO_CONTENT = newStatus(204, "No Content");
    public static final HttpResponseStatus RESET_CONTENT = newStatus(HttpStatus.SC_RESET_CONTENT, "Reset Content");
    public static final HttpResponseStatus PARTIAL_CONTENT = newStatus(HttpStatus.SC_PARTIAL_CONTENT, "Partial Content");
    public static final HttpResponseStatus MULTI_STATUS = newStatus(HttpStatus.SC_MULTI_STATUS, "Multi-Status");
    public static final HttpResponseStatus MULTIPLE_CHOICES = newStatus(300, "Multiple Choices");
    public static final HttpResponseStatus MOVED_PERMANENTLY = newStatus(301, "Moved Permanently");
    public static final HttpResponseStatus FOUND = newStatus(302, "Found");
    public static final HttpResponseStatus SEE_OTHER = newStatus(303, "See Other");
    public static final HttpResponseStatus NOT_MODIFIED = newStatus(304, "Not Modified");
    public static final HttpResponseStatus USE_PROXY = newStatus(305, "Use Proxy");
    public static final HttpResponseStatus TEMPORARY_REDIRECT = newStatus(307, "Temporary Redirect");
    public static final HttpResponseStatus PERMANENT_REDIRECT = newStatus(308, "Permanent Redirect");
    public static final HttpResponseStatus BAD_REQUEST = newStatus(400, "Bad Request");
    public static final HttpResponseStatus UNAUTHORIZED = newStatus(401, "Unauthorized");
    public static final HttpResponseStatus PAYMENT_REQUIRED = newStatus(402, "Payment Required");
    public static final HttpResponseStatus FORBIDDEN = newStatus(403, "Forbidden");
    public static final HttpResponseStatus NOT_FOUND = newStatus(404, "Not Found");
    public static final HttpResponseStatus METHOD_NOT_ALLOWED = newStatus(405, "Method Not Allowed");
    public static final HttpResponseStatus NOT_ACCEPTABLE = newStatus(HttpStatus.SC_NOT_ACCEPTABLE, "Not Acceptable");
    public static final HttpResponseStatus PROXY_AUTHENTICATION_REQUIRED = newStatus(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required");
    public static final HttpResponseStatus REQUEST_TIMEOUT = newStatus(HttpStatus.SC_REQUEST_TIMEOUT, "Request Timeout");
    public static final HttpResponseStatus CONFLICT = newStatus(409, "Conflict");
    public static final HttpResponseStatus GONE = newStatus(HttpStatus.SC_GONE, "Gone");
    public static final HttpResponseStatus LENGTH_REQUIRED = newStatus(HttpStatus.SC_LENGTH_REQUIRED, "Length Required");
    public static final HttpResponseStatus PRECONDITION_FAILED = newStatus(412, "Precondition Failed");
    public static final HttpResponseStatus REQUEST_ENTITY_TOO_LARGE = newStatus(HttpStatus.SC_REQUEST_TOO_LONG, "Request Entity Too Large");
    public static final HttpResponseStatus REQUEST_URI_TOO_LONG = newStatus(HttpStatus.SC_REQUEST_URI_TOO_LONG, "Request-URI Too Long");
    public static final HttpResponseStatus UNSUPPORTED_MEDIA_TYPE = newStatus(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
    public static final HttpResponseStatus REQUESTED_RANGE_NOT_SATISFIABLE = newStatus(416, "Requested Range Not Satisfiable");
    public static final HttpResponseStatus EXPECTATION_FAILED = newStatus(HttpStatus.SC_EXPECTATION_FAILED, "Expectation Failed");
    public static final HttpResponseStatus MISDIRECTED_REQUEST = newStatus(TypedValues.Cycle.TYPE_WAVE_SHAPE, "Misdirected Request");
    public static final HttpResponseStatus UNPROCESSABLE_ENTITY = newStatus(422, "Unprocessable Entity");
    public static final HttpResponseStatus LOCKED = newStatus(423, "Locked");
    public static final HttpResponseStatus FAILED_DEPENDENCY = newStatus(424, "Failed Dependency");
    public static final HttpResponseStatus UNORDERED_COLLECTION = newStatus(TypedValues.Cycle.TYPE_WAVE_PHASE, "Unordered Collection");
    public static final HttpResponseStatus UPGRADE_REQUIRED = newStatus(426, "Upgrade Required");
    public static final HttpResponseStatus PRECONDITION_REQUIRED = newStatus(428, "Precondition Required");
    public static final HttpResponseStatus TOO_MANY_REQUESTS = newStatus(429, "Too Many Requests");
    public static final HttpResponseStatus REQUEST_HEADER_FIELDS_TOO_LARGE = newStatus(431, "Request Header Fields Too Large");
    public static final HttpResponseStatus INTERNAL_SERVER_ERROR = newStatus(500, "Internal Server Error");
    public static final HttpResponseStatus NOT_IMPLEMENTED = newStatus(501, "Not Implemented");
    public static final HttpResponseStatus BAD_GATEWAY = newStatus(502, "Bad Gateway");
    public static final HttpResponseStatus SERVICE_UNAVAILABLE = newStatus(503, "Service Unavailable");
    public static final HttpResponseStatus GATEWAY_TIMEOUT = newStatus(504, "Gateway Timeout");
    public static final HttpResponseStatus HTTP_VERSION_NOT_SUPPORTED = newStatus(505, "HTTP Version Not Supported");
    public static final HttpResponseStatus VARIANT_ALSO_NEGOTIATES = newStatus(506, "Variant Also Negotiates");
    public static final HttpResponseStatus INSUFFICIENT_STORAGE = newStatus(507, "Insufficient Storage");
    public static final HttpResponseStatus NOT_EXTENDED = newStatus(510, "Not Extended");
    public static final HttpResponseStatus NETWORK_AUTHENTICATION_REQUIRED = newStatus(511, "Network Authentication Required");

    private static HttpResponseStatus newStatus(int i, String str) {
        return new HttpResponseStatus(i, str, true);
    }

    public static HttpResponseStatus valueOf(int i) {
        HttpResponseStatus valueOf0 = valueOf0(i);
        return valueOf0 != null ? valueOf0 : new HttpResponseStatus(i);
    }

    private static HttpResponseStatus valueOf0(int i) {
        if (i == 307) {
            return TEMPORARY_REDIRECT;
        }
        if (i == 308) {
            return PERMANENT_REDIRECT;
        }
        if (i == 428) {
            return PRECONDITION_REQUIRED;
        }
        if (i == 429) {
            return TOO_MANY_REQUESTS;
        }
        if (i == 431) {
            return REQUEST_HEADER_FIELDS_TOO_LARGE;
        }
        if (i == 510) {
            return NOT_EXTENDED;
        }
        if (i != 511) {
            switch (i) {
                case 100:
                    return CONTINUE;
                case 101:
                    return SWITCHING_PROTOCOLS;
                case 102:
                    return PROCESSING;
                default:
                    switch (i) {
                        case 200:
                            return f8500OK;
                        case 201:
                            return CREATED;
                        case 202:
                            return ACCEPTED;
                        case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION /* 203 */:
                            return NON_AUTHORITATIVE_INFORMATION;
                        case 204:
                            return NO_CONTENT;
                        case HttpStatus.SC_RESET_CONTENT /* 205 */:
                            return RESET_CONTENT;
                        case HttpStatus.SC_PARTIAL_CONTENT /* 206 */:
                            return PARTIAL_CONTENT;
                        case HttpStatus.SC_MULTI_STATUS /* 207 */:
                            return MULTI_STATUS;
                        default:
                            switch (i) {
                                case 300:
                                    return MULTIPLE_CHOICES;
                                case 301:
                                    return MOVED_PERMANENTLY;
                                case 302:
                                    return FOUND;
                                case 303:
                                    return SEE_OTHER;
                                case 304:
                                    return NOT_MODIFIED;
                                case 305:
                                    return USE_PROXY;
                                default:
                                    switch (i) {
                                        case 400:
                                            return BAD_REQUEST;
                                        case 401:
                                            return UNAUTHORIZED;
                                        case 402:
                                            return PAYMENT_REQUIRED;
                                        case 403:
                                            return FORBIDDEN;
                                        case 404:
                                            return NOT_FOUND;
                                        case 405:
                                            return METHOD_NOT_ALLOWED;
                                        case HttpStatus.SC_NOT_ACCEPTABLE /* 406 */:
                                            return NOT_ACCEPTABLE;
                                        case HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED /* 407 */:
                                            return PROXY_AUTHENTICATION_REQUIRED;
                                        case HttpStatus.SC_REQUEST_TIMEOUT /* 408 */:
                                            return REQUEST_TIMEOUT;
                                        case 409:
                                            return CONFLICT;
                                        case HttpStatus.SC_GONE /* 410 */:
                                            return GONE;
                                        case HttpStatus.SC_LENGTH_REQUIRED /* 411 */:
                                            return LENGTH_REQUIRED;
                                        case 412:
                                            return PRECONDITION_FAILED;
                                        case HttpStatus.SC_REQUEST_TOO_LONG /* 413 */:
                                            return REQUEST_ENTITY_TOO_LARGE;
                                        case HttpStatus.SC_REQUEST_URI_TOO_LONG /* 414 */:
                                            return REQUEST_URI_TOO_LONG;
                                        case HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE /* 415 */:
                                            return UNSUPPORTED_MEDIA_TYPE;
                                        case 416:
                                            return REQUESTED_RANGE_NOT_SATISFIABLE;
                                        case HttpStatus.SC_EXPECTATION_FAILED /* 417 */:
                                            return EXPECTATION_FAILED;
                                        default:
                                            switch (i) {
                                                case TypedValues.Cycle.TYPE_WAVE_SHAPE /* 421 */:
                                                    return MISDIRECTED_REQUEST;
                                                case 422:
                                                    return UNPROCESSABLE_ENTITY;
                                                case 423:
                                                    return LOCKED;
                                                case 424:
                                                    return FAILED_DEPENDENCY;
                                                case TypedValues.Cycle.TYPE_WAVE_PHASE /* 425 */:
                                                    return UNORDERED_COLLECTION;
                                                case 426:
                                                    return UPGRADE_REQUIRED;
                                                default:
                                                    switch (i) {
                                                        case 500:
                                                            return INTERNAL_SERVER_ERROR;
                                                        case 501:
                                                            return NOT_IMPLEMENTED;
                                                        case 502:
                                                            return BAD_GATEWAY;
                                                        case 503:
                                                            return SERVICE_UNAVAILABLE;
                                                        case 504:
                                                            return GATEWAY_TIMEOUT;
                                                        case 505:
                                                            return HTTP_VERSION_NOT_SUPPORTED;
                                                        case 506:
                                                            return VARIANT_ALSO_NEGOTIATES;
                                                        case 507:
                                                            return INSUFFICIENT_STORAGE;
                                                        default:
                                                            return null;
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return NETWORK_AUTHENTICATION_REQUIRED;
    }

    public static HttpResponseStatus valueOf(int i, String str) {
        HttpResponseStatus valueOf0 = valueOf0(i);
        return (valueOf0 == null || !valueOf0.reasonPhrase().contentEquals(str)) ? new HttpResponseStatus(i, str) : valueOf0;
    }

    public static HttpResponseStatus parseLine(CharSequence charSequence) {
        return charSequence instanceof AsciiString ? parseLine((AsciiString) charSequence) : parseLine(charSequence.toString());
    }

    public static HttpResponseStatus parseLine(String str) {
        try {
            int indexOf = str.indexOf(32);
            if (indexOf == -1) {
                return valueOf(Integer.parseInt(str));
            }
            return valueOf(Integer.parseInt(str.substring(0, indexOf)), str.substring(indexOf + 1));
        } catch (Exception e) {
            throw new IllegalArgumentException("malformed status line: " + str, e);
        }
    }

    public static HttpResponseStatus parseLine(AsciiString asciiString) {
        try {
            int forEachByte = asciiString.forEachByte(ByteProcessor.FIND_ASCII_SPACE);
            return forEachByte == -1 ? valueOf(asciiString.parseInt()) : valueOf(asciiString.parseInt(0, forEachByte), asciiString.toString(forEachByte + 1));
        } catch (Exception e) {
            throw new IllegalArgumentException("malformed status line: " + ((Object) asciiString), e);
        }
    }

    private HttpResponseStatus(int i) {
        this(i, ((Object) HttpStatusClass.valueOf(i).defaultReasonPhrase()) + " (" + i + ')', false);
    }

    public HttpResponseStatus(int i, String str) {
        this(i, str, false);
    }

    private HttpResponseStatus(int i, String str, boolean z) {
        if (i < 0) {
            throw new IllegalArgumentException("code: " + i + " (expected: 0+)");
        }
        if (str == null) {
            throw new NullPointerException("reasonPhrase");
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\n' || charAt == '\r') {
                throw new IllegalArgumentException("reasonPhrase contains one of the following prohibited characters: \\r\\n: " + str);
            }
        }
        this.code = i;
        String num = Integer.toString(i);
        this.codeAsText = new AsciiString(num);
        this.reasonPhrase = str;
        if (z) {
            this.bytes = (num + ' ' + str).getBytes(CharsetUtil.US_ASCII);
            return;
        }
        this.bytes = null;
    }

    public int code() {
        return this.code;
    }

    public AsciiString codeAsText() {
        return this.codeAsText;
    }

    public String reasonPhrase() {
        return this.reasonPhrase;
    }

    public HttpStatusClass codeClass() {
        HttpStatusClass httpStatusClass = this.codeClass;
        if (httpStatusClass != null) {
            return httpStatusClass;
        }
        HttpStatusClass valueOf = HttpStatusClass.valueOf(this.code);
        this.codeClass = valueOf;
        return valueOf;
    }

    public int hashCode() {
        return code();
    }

    public boolean equals(Object obj) {
        return (obj instanceof HttpResponseStatus) && code() == ((HttpResponseStatus) obj).code();
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpResponseStatus httpResponseStatus) {
        return code() - httpResponseStatus.code();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.reasonPhrase.length() + 4);
        sb.append((CharSequence) this.codeAsText);
        sb.append(' ');
        sb.append(this.reasonPhrase);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void encode(ByteBuf byteBuf) {
        byte[] bArr = this.bytes;
        if (bArr == null) {
            ByteBufUtil.copy(this.codeAsText, byteBuf);
            byteBuf.writeByte(32);
            byteBuf.writeCharSequence(this.reasonPhrase, CharsetUtil.US_ASCII);
            return;
        }
        byteBuf.writeBytes(bArr);
    }
}
