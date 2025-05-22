package org.jboss.netty.handler.codec.http;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.http.cookie.ClientCookie;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
public class DefaultCookie implements Cookie {
    private String comment;
    private String commentUrl;
    private boolean discard;
    private String domain;
    private boolean httpOnly;
    private final String name;
    private String path;
    private boolean secure;
    private String value;
    private int version;
    private Set<Integer> ports = Collections.emptySet();
    private Set<Integer> unmodifiablePorts = this.ports;
    private int maxAge = -1;

    public DefaultCookie(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            throw new IllegalArgumentException("empty name");
        }
        for (int i = 0; i < trim.length(); i++) {
            char charAt = trim.charAt(i);
            if (charAt > 127) {
                throw new IllegalArgumentException("name contains non-ascii character: " + trim);
            }
            if (charAt != ' ' && charAt != ',' && charAt != ';' && charAt != '=') {
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
            throw new IllegalArgumentException("name contains one of the following prohibited characters: =,; \\t\\r\\n\\v\\f: " + trim);
        }
        if (trim.charAt(0) == '$') {
            throw new IllegalArgumentException("name starting with '$' not allowed: " + trim);
        }
        this.name = trim;
        setValue(str2);
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public String getName() {
        return this.name;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public String getValue() {
        return this.value;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setValue(String str) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        this.value = str;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public String getDomain() {
        return this.domain;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setDomain(String str) {
        this.domain = validateValue("domain", str);
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public String getPath() {
        return this.path;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setPath(String str) {
        this.path = validateValue("path", str);
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public String getComment() {
        return this.comment;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setComment(String str) {
        this.comment = validateValue(ClientCookie.COMMENT_ATTR, str);
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public String getCommentUrl() {
        return this.commentUrl;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setCommentUrl(String str) {
        this.commentUrl = validateValue("commentUrl", str);
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public boolean isDiscard() {
        return this.discard;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setDiscard(boolean z) {
        this.discard = z;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public Set<Integer> getPorts() {
        if (this.unmodifiablePorts == null) {
            this.unmodifiablePorts = Collections.unmodifiableSet(this.ports);
        }
        return this.unmodifiablePorts;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setPorts(int... iArr) {
        if (iArr == null) {
            throw new NullPointerException("ports");
        }
        int[] iArr2 = (int[]) iArr.clone();
        if (iArr2.length == 0) {
            Set<Integer> emptySet = Collections.emptySet();
            this.ports = emptySet;
            this.unmodifiablePorts = emptySet;
            return;
        }
        TreeSet treeSet = new TreeSet();
        for (int i : iArr2) {
            if (i <= 0 || i > 65535) {
                throw new IllegalArgumentException("port out of range: " + i);
            }
            treeSet.add(Integer.valueOf(i));
        }
        this.ports = treeSet;
        this.unmodifiablePorts = null;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setPorts(Iterable<Integer> iterable) {
        TreeSet treeSet = new TreeSet();
        Iterator<Integer> it = iterable.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue <= 0 || intValue > 65535) {
                throw new IllegalArgumentException("port out of range: " + intValue);
            }
            treeSet.add(Integer.valueOf(intValue));
        }
        if (treeSet.isEmpty()) {
            Set<Integer> emptySet = Collections.emptySet();
            this.ports = emptySet;
            this.unmodifiablePorts = emptySet;
        } else {
            this.ports = treeSet;
            this.unmodifiablePorts = null;
        }
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public int getMaxAge() {
        return this.maxAge;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setMaxAge(int i) {
        if (i < -1) {
            throw new IllegalArgumentException("maxAge must be either -1, 0, or a positive integer: " + i);
        }
        this.maxAge = i;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public int getVersion() {
        return this.version;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setVersion(int i) {
        this.version = i;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public boolean isSecure() {
        return this.secure;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setSecure(boolean z) {
        this.secure = z;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public boolean isHttpOnly() {
        return this.httpOnly;
    }

    @Override // org.jboss.netty.handler.codec.http.Cookie
    public void setHttpOnly(boolean z) {
        this.httpOnly = z;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (!getName().equalsIgnoreCase(cookie.getName())) {
            return false;
        }
        if (getPath() == null) {
            if (cookie.getPath() != null) {
                return false;
            }
        } else if (cookie.getPath() == null || !getPath().equals(cookie.getPath())) {
            return false;
        }
        if (getDomain() == null) {
            return cookie.getDomain() == null;
        }
        if (cookie.getDomain() == null) {
            return false;
        }
        return getDomain().equalsIgnoreCase(cookie.getDomain());
    }

    @Override // java.lang.Comparable
    public int compareTo(Cookie cookie) {
        int compareToIgnoreCase = getName().compareToIgnoreCase(cookie.getName());
        if (compareToIgnoreCase != 0) {
            return compareToIgnoreCase;
        }
        if (getPath() == null) {
            if (cookie.getPath() != null) {
                return -1;
            }
        } else {
            if (cookie.getPath() == null) {
                return 1;
            }
            int compareTo = getPath().compareTo(cookie.getPath());
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (getDomain() == null) {
            return cookie.getDomain() != null ? -1 : 0;
        }
        if (cookie.getDomain() == null) {
            return 1;
        }
        return getDomain().compareToIgnoreCase(cookie.getDomain());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append('=');
        sb.append(getValue());
        if (getDomain() != null) {
            sb.append(", domain=");
            sb.append(getDomain());
        }
        if (getPath() != null) {
            sb.append(", path=");
            sb.append(getPath());
        }
        if (getComment() != null) {
            sb.append(", comment=");
            sb.append(getComment());
        }
        if (getMaxAge() >= 0) {
            sb.append(", maxAge=");
            sb.append(getMaxAge());
            sb.append('s');
        }
        if (isSecure()) {
            sb.append(", secure");
        }
        if (isHttpOnly()) {
            sb.append(", HTTPOnly");
        }
        return sb.toString();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x001e. Please report as an issue. */
    private static String validateValue(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        String trim = str2.trim();
        if (trim.length() == 0) {
            return null;
        }
        for (int i = 0; i < trim.length(); i++) {
            char charAt = trim.charAt(i);
            if (charAt != ';') {
                switch (charAt) {
                    case '\n':
                    case 11:
                    case '\f':
                    case '\r':
                        break;
                    default:
                }
            }
            throw new IllegalArgumentException(str + " contains one of the following prohibited characters: ;\\r\\n\\f\\v (" + trim + ')');
        }
        return trim;
    }
}
