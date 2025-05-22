package org.jboss.netty.handler.codec.http;

import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
final class HttpCodecUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0025. Please report as an issue. */
    public static void validateHeaderName(String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt > 127) {
                throw new IllegalArgumentException("name contains non-ascii character: " + str);
            }
            if (charAt != ' ' && charAt != ',' && charAt != '=' && charAt != ':' && charAt != ';') {
                switch (charAt) {
                    case '\t':
                    case '\n':
                    case 11:
                    case '\f':
                    case '\r':
                        break;
                    default:
                }
            }
            throw new IllegalArgumentException("name contains one of the following prohibited characters: =,;: \\t\\r\\n\\v\\f: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateHeaderValue(String str) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        char c = 0;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == 11) {
                throw new IllegalArgumentException("value contains a prohibited character '\\v': " + str);
            }
            if (charAt == '\f') {
                throw new IllegalArgumentException("value contains a prohibited character '\\f': " + str);
            }
            if (c == 0) {
                if (charAt != '\n') {
                    if (charAt == '\r') {
                        c = 1;
                    }
                }
                c = 2;
            } else if (c == 1) {
                if (charAt != '\n') {
                    throw new IllegalArgumentException("Only '\\n' is allowed after '\\r': " + str);
                }
                c = 2;
            } else if (c != 2) {
                continue;
            } else {
                if (charAt != '\t' && charAt != ' ') {
                    throw new IllegalArgumentException("Only ' ' and '\\t' are allowed after '\\n': " + str);
                }
                c = 0;
            }
        }
        if (c == 0) {
            return;
        }
        throw new IllegalArgumentException("value must not end with '\\r' or '\\n':" + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTransferEncodingChunked(HttpMessage httpMessage) {
        List<String> headers = httpMessage.getHeaders("Transfer-Encoding");
        if (headers.isEmpty()) {
            return false;
        }
        Iterator<String> it = headers.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase("chunked")) {
                return true;
            }
        }
        return false;
    }

    private HttpCodecUtil() {
    }
}
