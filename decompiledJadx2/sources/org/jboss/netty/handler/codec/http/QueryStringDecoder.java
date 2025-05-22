package org.jboss.netty.handler.codec.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.text.Typography;

/* loaded from: classes7.dex */
public class QueryStringDecoder {
    private static final int DEFAULT_MAX_PARAMS = 1024;
    private final Charset charset;
    private final boolean hasPath;
    private final int maxParams;
    private int nParams;
    private Map<String, List<String>> params;
    private String path;
    private final String uri;

    private static char decodeHexNibble(char c) {
        int i;
        if ('0' > c || c > '9') {
            char c2 = 'a';
            if ('a' > c || c > 'f') {
                c2 = 'A';
                if ('A' > c || c > 'F') {
                    return CharCompanionObject.MAX_VALUE;
                }
            }
            i = (c - c2) + 10;
        } else {
            i = c - '0';
        }
        return (char) i;
    }

    public QueryStringDecoder(String str) {
        this(str, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringDecoder(String str, boolean z) {
        this(str, HttpConstants.DEFAULT_CHARSET, z);
    }

    public QueryStringDecoder(String str, Charset charset) {
        this(str, charset, true);
    }

    public QueryStringDecoder(String str, Charset charset, boolean z) {
        this(str, charset, z, 1024);
    }

    public QueryStringDecoder(String str, Charset charset, boolean z, int i) {
        if (str == null) {
            throw new NullPointerException("uri");
        }
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("maxParams: " + i + " (expected: a positive integer)");
        }
        this.uri = str.replace(';', Typography.amp);
        this.charset = charset;
        this.maxParams = i;
        this.hasPath = z;
    }

    @Deprecated
    public QueryStringDecoder(String str, String str2) {
        this(str, Charset.forName(str2));
    }

    public QueryStringDecoder(URI uri) {
        this(uri, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringDecoder(URI uri, Charset charset) {
        this(uri, charset, 1024);
    }

    public QueryStringDecoder(URI uri, Charset charset, int i) {
        if (uri == null) {
            throw new NullPointerException("uri");
        }
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("maxParams: " + i + " (expected: a positive integer)");
        }
        String rawPath = uri.getRawPath();
        if (rawPath != null) {
            this.hasPath = true;
        } else {
            this.hasPath = false;
            rawPath = "";
        }
        this.uri = (rawPath + "?" + uri.getRawQuery()).replace(';', Typography.amp);
        this.charset = charset;
        this.maxParams = i;
    }

    @Deprecated
    public QueryStringDecoder(URI uri, String str) {
        this(uri, Charset.forName(str));
    }

    public String getPath() {
        if (this.path == null) {
            if (!this.hasPath) {
                this.path = "";
                return "";
            }
            int indexOf = this.uri.indexOf(63);
            if (indexOf < 0) {
                this.path = this.uri;
            } else {
                String substring = this.uri.substring(0, indexOf);
                this.path = substring;
                return substring;
            }
        }
        return this.path;
    }

    public Map<String, List<String>> getParameters() {
        if (this.params == null) {
            if (this.hasPath) {
                int length = getPath().length();
                if (this.uri.length() == length) {
                    return Collections.emptyMap();
                }
                decodeParams(this.uri.substring(length + 1));
            } else {
                if (this.uri.length() == 0) {
                    return Collections.emptyMap();
                }
                decodeParams(this.uri);
            }
        }
        return this.params;
    }

    private void decodeParams(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.params = linkedHashMap;
        int i = 0;
        this.nParams = 0;
        int i2 = 0;
        String str2 = null;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == '=' && str2 == null) {
                if (i2 != i) {
                    str2 = decodeComponent(str.substring(i2, i), this.charset);
                }
            } else if (charAt != '&') {
                continue;
                i++;
            } else if (str2 != null || i2 == i) {
                if (str2 != null) {
                    if (!addParam(linkedHashMap, str2, decodeComponent(str.substring(i2, i), this.charset))) {
                        return;
                    } else {
                        str2 = null;
                    }
                }
            } else if (!addParam(linkedHashMap, decodeComponent(str.substring(i2, i), this.charset), "")) {
                return;
            }
            i2 = i + 1;
            i++;
        }
        if (i2 == i) {
            if (str2 == null || addParam(linkedHashMap, str2, "")) {
            }
        } else if (str2 == null) {
            if (addParam(linkedHashMap, decodeComponent(str.substring(i2, i), this.charset), "")) {
            }
        } else if (addParam(linkedHashMap, str2, decodeComponent(str.substring(i2, i), this.charset))) {
        }
    }

    private boolean addParam(Map<String, List<String>> map, String str, String str2) {
        if (this.nParams >= this.maxParams) {
            return false;
        }
        List<String> list = map.get(str);
        if (list == null) {
            list = new ArrayList<>(1);
            map.put(str, list);
        }
        list.add(str2);
        this.nParams++;
        return true;
    }

    public static String decodeComponent(String str) {
        return decodeComponent(str, HttpConstants.DEFAULT_CHARSET);
    }

    public static String decodeComponent(String str, Charset charset) {
        int i;
        if (str == null) {
            return "";
        }
        int length = str.length();
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == '%') {
                i2++;
            } else if (charAt != '+') {
                i2++;
            }
            z = true;
            i2++;
        }
        if (!z) {
            return str;
        }
        byte[] bArr = new byte[length];
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            char charAt2 = str.charAt(i3);
            if (charAt2 != '%') {
                if (charAt2 == '+') {
                    i = i4 + 1;
                    bArr[i4] = 32;
                    i4 = i;
                }
                bArr[i4] = (byte) charAt2;
                i4++;
            } else {
                int i5 = length - 1;
                if (i3 == i5) {
                    throw new IllegalArgumentException("unterminated escape sequence at end of string: " + str);
                }
                i3++;
                char charAt3 = str.charAt(i3);
                if (charAt3 == '%') {
                    i = i4 + 1;
                    bArr[i4] = 37;
                    i4 = i;
                } else {
                    if (i3 == i5) {
                        throw new IllegalArgumentException("partial escape sequence at end of string: " + str);
                    }
                    char decodeHexNibble = decodeHexNibble(charAt3);
                    i3++;
                    char decodeHexNibble2 = decodeHexNibble(str.charAt(i3));
                    if (decodeHexNibble == 65535 || decodeHexNibble2 == 65535) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("invalid escape sequence `%");
                        sb.append(str.charAt(i3 - 1));
                        sb.append(str.charAt(i3));
                        sb.append("' at index ");
                        sb.append(i3 - 2);
                        sb.append(" of: ");
                        sb.append(str);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    charAt2 = (char) ((decodeHexNibble * 16) + decodeHexNibble2);
                    bArr[i4] = (byte) charAt2;
                    i4++;
                }
            }
            i3++;
        }
        try {
            return new String(bArr, 0, i4, charset.name());
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalArgumentException("unsupported encoding: " + charset.name());
        }
    }
}
