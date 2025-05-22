package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpVersion implements Comparable<HttpVersion> {
    private static final String HTTP_1_0_STRING = "HTTP/1.0";
    private static final String HTTP_1_1_STRING = "HTTP/1.1";
    private final byte[] bytes;
    private final boolean keepAliveDefault;
    private final int majorVersion;
    private final int minorVersion;
    private final String protocolName;
    private final String text;
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\S+)/(\\d+)\\.(\\d+)");
    public static final HttpVersion HTTP_1_0 = new HttpVersion(org.apache.http.HttpVersion.HTTP, 1, 0, false, true);
    public static final HttpVersion HTTP_1_1 = new HttpVersion(org.apache.http.HttpVersion.HTTP, 1, 1, true, true);

    public static HttpVersion valueOf(String str) {
        ObjectUtil.checkNotNull(str, "text");
        String trim = str.trim();
        if (trim.isEmpty()) {
            throw new IllegalArgumentException("text is empty (possibly HTTP/0.9)");
        }
        HttpVersion version0 = version0(trim);
        return version0 == null ? new HttpVersion(trim, true) : version0;
    }

    private static HttpVersion version0(String str) {
        if (HTTP_1_1_STRING.equals(str)) {
            return HTTP_1_1;
        }
        if (HTTP_1_0_STRING.equals(str)) {
            return HTTP_1_0;
        }
        return null;
    }

    public HttpVersion(String str, boolean z) {
        ObjectUtil.checkNotNull(str, "text");
        String upperCase = str.trim().toUpperCase();
        if (upperCase.isEmpty()) {
            throw new IllegalArgumentException("empty text");
        }
        Matcher matcher = VERSION_PATTERN.matcher(upperCase);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid version format: " + upperCase);
        }
        this.protocolName = matcher.group(1);
        this.majorVersion = Integer.parseInt(matcher.group(2));
        this.minorVersion = Integer.parseInt(matcher.group(3));
        this.text = this.protocolName + '/' + this.majorVersion + FilenameUtils.EXTENSION_SEPARATOR + this.minorVersion;
        this.keepAliveDefault = z;
        this.bytes = null;
    }

    public HttpVersion(String str, int i, int i2, boolean z) {
        this(str, i, i2, z, false);
    }

    private HttpVersion(String str, int i, int i2, boolean z, boolean z2) {
        ObjectUtil.checkNotNull(str, "protocolName");
        String upperCase = str.trim().toUpperCase();
        if (upperCase.isEmpty()) {
            throw new IllegalArgumentException("empty protocolName");
        }
        for (int i3 = 0; i3 < upperCase.length(); i3++) {
            if (Character.isISOControl(upperCase.charAt(i3)) || Character.isWhitespace(upperCase.charAt(i3))) {
                throw new IllegalArgumentException("invalid character in protocolName");
            }
        }
        ObjectUtil.checkPositiveOrZero(i, "majorVersion");
        ObjectUtil.checkPositiveOrZero(i2, "minorVersion");
        this.protocolName = upperCase;
        this.majorVersion = i;
        this.minorVersion = i2;
        this.text = upperCase + '/' + i + FilenameUtils.EXTENSION_SEPARATOR + i2;
        this.keepAliveDefault = z;
        if (z2) {
            this.bytes = this.text.getBytes(CharsetUtil.US_ASCII);
        } else {
            this.bytes = null;
        }
    }

    public String protocolName() {
        return this.protocolName;
    }

    public int majorVersion() {
        return this.majorVersion;
    }

    public int minorVersion() {
        return this.minorVersion;
    }

    public String text() {
        return this.text;
    }

    public boolean isKeepAliveDefault() {
        return this.keepAliveDefault;
    }

    public String toString() {
        return text();
    }

    public int hashCode() {
        return (((protocolName().hashCode() * 31) + majorVersion()) * 31) + minorVersion();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpVersion)) {
            return false;
        }
        HttpVersion httpVersion = (HttpVersion) obj;
        return minorVersion() == httpVersion.minorVersion() && majorVersion() == httpVersion.majorVersion() && protocolName().equals(httpVersion.protocolName());
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpVersion httpVersion) {
        int compareTo = protocolName().compareTo(httpVersion.protocolName());
        if (compareTo != 0) {
            return compareTo;
        }
        int majorVersion = majorVersion() - httpVersion.majorVersion();
        return majorVersion != 0 ? majorVersion : minorVersion() - httpVersion.minorVersion();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void encode(ByteBuf byteBuf) {
        byte[] bArr = this.bytes;
        if (bArr == null) {
            byteBuf.writeCharSequence(this.text, CharsetUtil.US_ASCII);
        } else {
            byteBuf.writeBytes(bArr);
        }
    }
}
