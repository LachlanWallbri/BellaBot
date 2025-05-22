package org.jboss.netty.handler.codec.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes7.dex */
public class HttpVersion implements Comparable<HttpVersion> {
    private final boolean keepAliveDefault;
    private final int majorVersion;
    private final int minorVersion;
    private final String protocolName;
    private final String text;
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\S+)/(\\d+)\\.(\\d+)");
    public static final HttpVersion HTTP_1_0 = new HttpVersion(org.apache.http.HttpVersion.HTTP, 1, 0, false);
    public static final HttpVersion HTTP_1_1 = new HttpVersion(org.apache.http.HttpVersion.HTTP, 1, 1, true);

    public static HttpVersion valueOf(String str) {
        if (str == null) {
            throw new NullPointerException("text");
        }
        String upperCase = str.trim().toUpperCase();
        if (upperCase.equals("HTTP/1.1")) {
            return HTTP_1_1;
        }
        if (upperCase.equals("HTTP/1.0")) {
            return HTTP_1_0;
        }
        return new HttpVersion(upperCase, true);
    }

    @Deprecated
    public HttpVersion(String str) {
        this(str, true);
    }

    public HttpVersion(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("text");
        }
        String upperCase = str.trim().toUpperCase();
        if (upperCase.length() == 0) {
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
    }

    @Deprecated
    public HttpVersion(String str, int i, int i2) {
        this(str, i, i2, true);
    }

    public HttpVersion(String str, int i, int i2, boolean z) {
        if (str == null) {
            throw new NullPointerException("protocolName");
        }
        String upperCase = str.trim().toUpperCase();
        if (upperCase.length() == 0) {
            throw new IllegalArgumentException("empty protocolName");
        }
        for (int i3 = 0; i3 < upperCase.length(); i3++) {
            if (Character.isISOControl(upperCase.charAt(i3)) || Character.isWhitespace(upperCase.charAt(i3))) {
                throw new IllegalArgumentException("invalid character in protocolName");
            }
        }
        if (i < 0) {
            throw new IllegalArgumentException("negative majorVersion");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("negative minorVersion");
        }
        this.protocolName = upperCase;
        this.majorVersion = i;
        this.minorVersion = i2;
        this.text = upperCase + '/' + i + FilenameUtils.EXTENSION_SEPARATOR + i2;
        this.keepAliveDefault = z;
    }

    public String getProtocolName() {
        return this.protocolName;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public String getText() {
        return this.text;
    }

    public boolean isKeepAliveDefault() {
        return this.keepAliveDefault;
    }

    public String toString() {
        return getText();
    }

    public int hashCode() {
        return (((getProtocolName().hashCode() * 31) + getMajorVersion()) * 31) + getMinorVersion();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpVersion)) {
            return false;
        }
        HttpVersion httpVersion = (HttpVersion) obj;
        return getMinorVersion() == httpVersion.getMinorVersion() && getMajorVersion() == httpVersion.getMajorVersion() && getProtocolName().equals(httpVersion.getProtocolName());
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpVersion httpVersion) {
        int compareTo = getProtocolName().compareTo(httpVersion.getProtocolName());
        if (compareTo != 0) {
            return compareTo;
        }
        int majorVersion = getMajorVersion() - httpVersion.getMajorVersion();
        return majorVersion != 0 ? majorVersion : getMinorVersion() - httpVersion.getMinorVersion();
    }
}
