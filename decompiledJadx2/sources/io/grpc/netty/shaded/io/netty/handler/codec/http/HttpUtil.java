package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.iflytek.speech.VoiceWakeuperAidl;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpUtil {
    private static final AsciiString CHARSET_EQUALS = AsciiString.m3920of(((Object) HttpHeaderValues.CHARSET) + "=");
    private static final AsciiString SEMICOLON = AsciiString.cached(VoiceWakeuperAidl.PARAMS_SEPARATE);

    private HttpUtil() {
    }

    public static boolean isOriginForm(URI uri) {
        return uri.getScheme() == null && uri.getSchemeSpecificPart() == null && uri.getHost() == null && uri.getAuthority() == null;
    }

    public static boolean isAsteriskForm(URI uri) {
        return "*".equals(uri.getPath()) && uri.getScheme() == null && uri.getSchemeSpecificPart() == null && uri.getHost() == null && uri.getAuthority() == null && uri.getQuery() == null && uri.getFragment() == null;
    }

    public static boolean isKeepAlive(HttpMessage httpMessage) {
        return !httpMessage.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE, true) && (httpMessage.protocolVersion().isKeepAliveDefault() || httpMessage.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE, true));
    }

    public static void setKeepAlive(HttpMessage httpMessage, boolean z) {
        setKeepAlive(httpMessage.headers(), httpMessage.protocolVersion(), z);
    }

    public static void setKeepAlive(HttpHeaders httpHeaders, HttpVersion httpVersion, boolean z) {
        if (httpVersion.isKeepAliveDefault()) {
            if (z) {
                httpHeaders.remove(HttpHeaderNames.CONNECTION);
                return;
            } else {
                httpHeaders.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                return;
            }
        }
        if (z) {
            httpHeaders.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        } else {
            httpHeaders.remove(HttpHeaderNames.CONNECTION);
        }
    }

    public static long getContentLength(HttpMessage httpMessage) {
        String str = httpMessage.headers().get(HttpHeaderNames.CONTENT_LENGTH);
        if (str != null) {
            return Long.parseLong(str);
        }
        long webSocketContentLength = getWebSocketContentLength(httpMessage);
        if (webSocketContentLength >= 0) {
            return webSocketContentLength;
        }
        throw new NumberFormatException("header not found: " + ((Object) HttpHeaderNames.CONTENT_LENGTH));
    }

    public static long getContentLength(HttpMessage httpMessage, long j) {
        String str = httpMessage.headers().get(HttpHeaderNames.CONTENT_LENGTH);
        if (str != null) {
            return Long.parseLong(str);
        }
        long webSocketContentLength = getWebSocketContentLength(httpMessage);
        return webSocketContentLength >= 0 ? webSocketContentLength : j;
    }

    public static int getContentLength(HttpMessage httpMessage, int i) {
        return (int) Math.min(2147483647L, getContentLength(httpMessage, i));
    }

    private static int getWebSocketContentLength(HttpMessage httpMessage) {
        HttpHeaders headers = httpMessage.headers();
        return httpMessage instanceof HttpRequest ? (HttpMethod.GET.equals(((HttpRequest) httpMessage).method()) && headers.contains(HttpHeaderNames.SEC_WEBSOCKET_KEY1) && headers.contains(HttpHeaderNames.SEC_WEBSOCKET_KEY2)) ? 8 : -1 : ((httpMessage instanceof HttpResponse) && ((HttpResponse) httpMessage).status().code() == 101 && headers.contains(HttpHeaderNames.SEC_WEBSOCKET_ORIGIN) && headers.contains(HttpHeaderNames.SEC_WEBSOCKET_LOCATION)) ? 16 : -1;
    }

    public static void setContentLength(HttpMessage httpMessage, long j) {
        httpMessage.headers().set(HttpHeaderNames.CONTENT_LENGTH, Long.valueOf(j));
    }

    public static boolean isContentLengthSet(HttpMessage httpMessage) {
        return httpMessage.headers().contains(HttpHeaderNames.CONTENT_LENGTH);
    }

    public static boolean is100ContinueExpected(HttpMessage httpMessage) {
        return isExpectHeaderValid(httpMessage) && httpMessage.headers().contains((CharSequence) HttpHeaderNames.EXPECT, (CharSequence) HttpHeaderValues.CONTINUE, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUnsupportedExpectation(HttpMessage httpMessage) {
        String str;
        return (!isExpectHeaderValid(httpMessage) || (str = httpMessage.headers().get(HttpHeaderNames.EXPECT)) == null || HttpHeaderValues.CONTINUE.toString().equalsIgnoreCase(str)) ? false : true;
    }

    private static boolean isExpectHeaderValid(HttpMessage httpMessage) {
        return (httpMessage instanceof HttpRequest) && httpMessage.protocolVersion().compareTo(HttpVersion.HTTP_1_1) >= 0;
    }

    public static void set100ContinueExpected(HttpMessage httpMessage, boolean z) {
        if (z) {
            httpMessage.headers().set(HttpHeaderNames.EXPECT, HttpHeaderValues.CONTINUE);
        } else {
            httpMessage.headers().remove(HttpHeaderNames.EXPECT);
        }
    }

    public static boolean isTransferEncodingChunked(HttpMessage httpMessage) {
        return httpMessage.headers().containsValue(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED, true);
    }

    public static void setTransferEncodingChunked(HttpMessage httpMessage, boolean z) {
        if (z) {
            httpMessage.headers().set(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED);
            httpMessage.headers().remove(HttpHeaderNames.CONTENT_LENGTH);
            return;
        }
        List<String> all = httpMessage.headers().getAll(HttpHeaderNames.TRANSFER_ENCODING);
        if (all.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(all);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (HttpHeaderValues.CHUNKED.contentEqualsIgnoreCase((CharSequence) it.next())) {
                it.remove();
            }
        }
        if (arrayList.isEmpty()) {
            httpMessage.headers().remove(HttpHeaderNames.TRANSFER_ENCODING);
        } else {
            httpMessage.headers().set((CharSequence) HttpHeaderNames.TRANSFER_ENCODING, (Iterable<?>) arrayList);
        }
    }

    public static Charset getCharset(HttpMessage httpMessage) {
        return getCharset(httpMessage, CharsetUtil.ISO_8859_1);
    }

    public static Charset getCharset(CharSequence charSequence) {
        if (charSequence != null) {
            return getCharset(charSequence, CharsetUtil.ISO_8859_1);
        }
        return CharsetUtil.ISO_8859_1;
    }

    public static Charset getCharset(HttpMessage httpMessage, Charset charset) {
        String str = httpMessage.headers().get(HttpHeaderNames.CONTENT_TYPE);
        return str != null ? getCharset(str, charset) : charset;
    }

    public static Charset getCharset(CharSequence charSequence, Charset charset) {
        CharSequence charsetAsSequence;
        if (charSequence != null && (charsetAsSequence = getCharsetAsSequence(charSequence)) != null) {
            try {
                return Charset.forName(charsetAsSequence.toString());
            } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            }
        }
        return charset;
    }

    @Deprecated
    public static CharSequence getCharsetAsString(HttpMessage httpMessage) {
        return getCharsetAsSequence(httpMessage);
    }

    public static CharSequence getCharsetAsSequence(HttpMessage httpMessage) {
        String str = httpMessage.headers().get(HttpHeaderNames.CONTENT_TYPE);
        if (str != null) {
            return getCharsetAsSequence(str);
        }
        return null;
    }

    public static CharSequence getCharsetAsSequence(CharSequence charSequence) {
        int length;
        ObjectUtil.checkNotNull(charSequence, "contentTypeValue");
        int indexOfIgnoreCaseAscii = AsciiString.indexOfIgnoreCaseAscii(charSequence, CHARSET_EQUALS, 0);
        if (indexOfIgnoreCaseAscii == -1 || (length = indexOfIgnoreCaseAscii + CHARSET_EQUALS.length()) >= charSequence.length()) {
            return null;
        }
        CharSequence subSequence = charSequence.subSequence(length, charSequence.length());
        int indexOfIgnoreCaseAscii2 = AsciiString.indexOfIgnoreCaseAscii(subSequence, SEMICOLON, 0);
        return indexOfIgnoreCaseAscii2 == -1 ? subSequence : subSequence.subSequence(0, indexOfIgnoreCaseAscii2);
    }

    public static CharSequence getMimeType(HttpMessage httpMessage) {
        String str = httpMessage.headers().get(HttpHeaderNames.CONTENT_TYPE);
        if (str != null) {
            return getMimeType(str);
        }
        return null;
    }

    public static CharSequence getMimeType(CharSequence charSequence) {
        ObjectUtil.checkNotNull(charSequence, "contentTypeValue");
        int indexOfIgnoreCaseAscii = AsciiString.indexOfIgnoreCaseAscii(charSequence, SEMICOLON, 0);
        if (indexOfIgnoreCaseAscii != -1) {
            return charSequence.subSequence(0, indexOfIgnoreCaseAscii);
        }
        if (charSequence.length() > 0) {
            return charSequence;
        }
        return null;
    }

    public static String formatHostnameForHttp(InetSocketAddress inetSocketAddress) {
        String hostname = NetUtil.getHostname(inetSocketAddress);
        if (!NetUtil.isValidIpV6Address(hostname)) {
            return hostname;
        }
        if (!inetSocketAddress.isUnresolved()) {
            hostname = NetUtil.toAddressString(inetSocketAddress.getAddress());
        }
        return '[' + hostname + ']';
    }
}
