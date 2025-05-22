package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class QueryStringDecoder {
    private static final int DEFAULT_MAX_PARAMS = 1024;
    private final Charset charset;
    private final int maxParams;
    private Map<String, List<String>> params;
    private String path;
    private int pathEndIdx;
    private final boolean semicolonIsNormalChar;
    private final String uri;

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
        this(str, charset, z, i, false);
    }

    public QueryStringDecoder(String str, Charset charset, boolean z, int i, boolean z2) {
        this.uri = (String) ObjectUtil.checkNotNull(str, "uri");
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
        this.maxParams = ObjectUtil.checkPositive(i, "maxParams");
        this.semicolonIsNormalChar = z2;
        this.pathEndIdx = z ? -1 : 0;
    }

    public QueryStringDecoder(URI uri) {
        this(uri, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringDecoder(URI uri, Charset charset) {
        this(uri, charset, 1024);
    }

    public QueryStringDecoder(URI uri, Charset charset, int i) {
        this(uri, charset, i, false);
    }

    public QueryStringDecoder(URI uri, Charset charset, int i, boolean z) {
        String str;
        String rawPath = uri.getRawPath();
        rawPath = rawPath == null ? "" : rawPath;
        String rawQuery = uri.getRawQuery();
        if (rawQuery == null) {
            str = rawPath;
        } else {
            str = rawPath + '?' + rawQuery;
        }
        this.uri = str;
        this.charset = (Charset) ObjectUtil.checkNotNull(charset, "charset");
        this.maxParams = ObjectUtil.checkPositive(i, "maxParams");
        this.semicolonIsNormalChar = z;
        this.pathEndIdx = rawPath.length();
    }

    public String toString() {
        return uri();
    }

    public String uri() {
        return this.uri;
    }

    public String path() {
        if (this.path == null) {
            this.path = decodeComponent(this.uri, 0, pathEndIdx(), this.charset, true);
        }
        return this.path;
    }

    public Map<String, List<String>> parameters() {
        if (this.params == null) {
            this.params = decodeParams(this.uri, pathEndIdx(), this.charset, this.maxParams, this.semicolonIsNormalChar);
        }
        return this.params;
    }

    public String rawPath() {
        return this.uri.substring(0, pathEndIdx());
    }

    public String rawQuery() {
        int pathEndIdx = pathEndIdx() + 1;
        return pathEndIdx < this.uri.length() ? this.uri.substring(pathEndIdx) : "";
    }

    private int pathEndIdx() {
        if (this.pathEndIdx == -1) {
            this.pathEndIdx = findPathEndIndex(this.uri);
        }
        return this.pathEndIdx;
    }

    private static Map<String, List<String>> decodeParams(String str, int i, Charset charset, int i2, boolean z) {
        int length = str.length();
        if (i >= length) {
            return Collections.emptyMap();
        }
        if (str.charAt(i) == '?') {
            i++;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i3 = i;
        int i4 = i2;
        int i5 = -1;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '#') {
                break;
            }
            if (charAt != '&') {
                if (charAt != ';') {
                    if (charAt == '=') {
                        if (i3 != i) {
                            if (i5 < i3) {
                                i5 = i + 1;
                            }
                        }
                        i3 = i + 1;
                    }
                } else if (z) {
                    continue;
                }
                i++;
            }
            if (addParam(str, i3, i5, i, linkedHashMap, charset) && i4 - 1 == 0) {
                return linkedHashMap;
            }
            i3 = i + 1;
            i++;
        }
        addParam(str, i3, i5, i, linkedHashMap, charset);
        return linkedHashMap;
    }

    private static boolean addParam(String str, int i, int i2, int i3, Map<String, List<String>> map, Charset charset) {
        if (i >= i3) {
            return false;
        }
        if (i2 <= i) {
            i2 = i3 + 1;
        }
        String decodeComponent = decodeComponent(str, i, i2 - 1, charset, false);
        String decodeComponent2 = decodeComponent(str, i2, i3, charset, false);
        List<String> list = map.get(decodeComponent);
        if (list == null) {
            list = new ArrayList<>(1);
            map.put(decodeComponent, list);
        }
        list.add(decodeComponent2);
        return true;
    }

    public static String decodeComponent(String str) {
        return decodeComponent(str, HttpConstants.DEFAULT_CHARSET);
    }

    public static String decodeComponent(String str, Charset charset) {
        return str == null ? "" : decodeComponent(str, 0, str.length(), charset, false);
    }

    private static String decodeComponent(String str, int i, int i2, Charset charset, boolean z) {
        int i3;
        int i4;
        int i5 = i2 - i;
        if (i5 <= 0) {
            return "";
        }
        int i6 = i;
        while (true) {
            if (i6 >= i2) {
                i6 = -1;
                break;
            }
            char charAt = str.charAt(i6);
            if (charAt == '%' || (charAt == '+' && !z)) {
                break;
            }
            i6++;
        }
        if (i6 == -1) {
            return str.substring(i, i2);
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray((i2 - i6) / 3);
        StringBuilder sb = new StringBuilder(i5);
        sb.append((CharSequence) str, i, i6);
        while (i6 < i2) {
            char charAt2 = str.charAt(i6);
            if (charAt2 != '%') {
                if (charAt2 == '+' && !z) {
                    charAt2 = ' ';
                }
                sb.append(charAt2);
            } else {
                int i7 = 0;
                while (true) {
                    i3 = i6 + 3;
                    if (i3 > i2) {
                        throw new IllegalArgumentException("unterminated escape sequence at index " + i6 + " of: " + str);
                    }
                    i4 = i7 + 1;
                    allocateUninitializedArray[i7] = StringUtil.decodeHexByte(str, i6 + 1);
                    if (i3 >= i2 || str.charAt(i3) != '%') {
                        break;
                    }
                    i6 = i3;
                    i7 = i4;
                }
                i6 = i3 - 1;
                sb.append(new String(allocateUninitializedArray, 0, i4, charset));
            }
            i6++;
        }
        return sb.toString();
    }

    private static int findPathEndIndex(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '?' || charAt == '#') {
                return i;
            }
        }
        return length;
    }
}
