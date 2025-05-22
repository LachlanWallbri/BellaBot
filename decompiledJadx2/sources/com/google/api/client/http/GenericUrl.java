package com.google.api.client.http;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.escape.PercentEscaper;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class GenericUrl extends GenericData {
    private static final Escaper URI_FRAGMENT_ESCAPER = new PercentEscaper("=&-_.!~*'()@:$,;/?:");
    private String fragment;
    private String host;
    private List<String> pathParts;
    private int port;
    private String scheme;
    private String userInfo;
    private boolean verbatim;

    public GenericUrl() {
        this.port = -1;
    }

    public GenericUrl(String str) {
        this(str, false);
    }

    public GenericUrl(String str, boolean z) {
        this(parseURL(str), z);
    }

    public GenericUrl(URI uri) {
        this(uri, false);
    }

    public GenericUrl(URI uri, boolean z) {
        this(uri.getScheme(), uri.getHost(), uri.getPort(), uri.getRawPath(), uri.getRawFragment(), uri.getRawQuery(), uri.getRawUserInfo(), z);
    }

    public GenericUrl(URL url) {
        this(url, false);
    }

    public GenericUrl(URL url, boolean z) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo(), z);
    }

    private GenericUrl(String str, String str2, int i, String str3, String str4, String str5, String str6, boolean z) {
        this.port = -1;
        this.scheme = str.toLowerCase(Locale.US);
        this.host = str2;
        this.port = i;
        this.pathParts = toPathParts(str3, z);
        this.verbatim = z;
        if (z) {
            this.fragment = str4;
            if (str5 != null) {
                UrlEncodedParser.parse(str5, (Object) this, false);
            }
            this.userInfo = str6;
            return;
        }
        this.fragment = str4 != null ? CharEscapers.decodeUri(str4) : null;
        if (str5 != null) {
            UrlEncodedParser.parse(str5, this);
        }
        this.userInfo = str6 != null ? CharEscapers.decodeUri(str6) : null;
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return build().hashCode();
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && (obj instanceof GenericUrl)) {
            return build().equals(((GenericUrl) obj).build());
        }
        return false;
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap
    public String toString() {
        return build();
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap
    public GenericUrl clone() {
        GenericUrl genericUrl = (GenericUrl) super.clone();
        List<String> list = this.pathParts;
        if (list != null) {
            genericUrl.pathParts = new ArrayList(list);
        }
        return genericUrl;
    }

    @Override // com.google.api.client.util.GenericData
    public GenericUrl set(String str, Object obj) {
        return (GenericUrl) super.set(str, obj);
    }

    public final String getScheme() {
        return this.scheme;
    }

    public final void setScheme(String str) {
        this.scheme = (String) Preconditions.checkNotNull(str);
    }

    public String getHost() {
        return this.host;
    }

    public final void setHost(String str) {
        this.host = (String) Preconditions.checkNotNull(str);
    }

    public final String getUserInfo() {
        return this.userInfo;
    }

    public final void setUserInfo(String str) {
        this.userInfo = str;
    }

    public int getPort() {
        return this.port;
    }

    public final void setPort(int i) {
        Preconditions.checkArgument(i >= -1, "expected port >= -1");
        this.port = i;
    }

    public List<String> getPathParts() {
        return this.pathParts;
    }

    public void setPathParts(List<String> list) {
        this.pathParts = list;
    }

    public String getFragment() {
        return this.fragment;
    }

    public final void setFragment(String str) {
        this.fragment = str;
    }

    public final String build() {
        return buildAuthority() + buildRelativeUrl();
    }

    public final String buildAuthority() {
        StringBuilder sb = new StringBuilder();
        sb.append((String) Preconditions.checkNotNull(this.scheme));
        sb.append("://");
        String str = this.userInfo;
        if (str != null) {
            if (!this.verbatim) {
                str = CharEscapers.escapeUriUserInfo(str);
            }
            sb.append(str);
            sb.append('@');
        }
        sb.append((String) Preconditions.checkNotNull(this.host));
        int i = this.port;
        if (i != -1) {
            sb.append(':');
            sb.append(i);
        }
        return sb.toString();
    }

    public final String buildRelativeUrl() {
        StringBuilder sb = new StringBuilder();
        if (this.pathParts != null) {
            appendRawPathFromParts(sb);
        }
        addQueryParams(entrySet(), sb, this.verbatim);
        String str = this.fragment;
        if (str != null) {
            sb.append('#');
            if (!this.verbatim) {
                str = URI_FRAGMENT_ESCAPER.escape(str);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public final URI toURI() {
        return toURI(build());
    }

    public final URL toURL() {
        return parseURL(build());
    }

    public final URL toURL(String str) {
        try {
            return new URL(toURL(), str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Object getFirst(String str) {
        Object obj = get(str);
        if (!(obj instanceof Collection)) {
            return obj;
        }
        Iterator it = ((Collection) obj).iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public Collection<Object> getAll(String str) {
        Object obj = get(str);
        if (obj == null) {
            return Collections.emptySet();
        }
        if (obj instanceof Collection) {
            return Collections.unmodifiableCollection((Collection) obj);
        }
        return Collections.singleton(obj);
    }

    public String getRawPath() {
        if (this.pathParts == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        appendRawPathFromParts(sb);
        return sb.toString();
    }

    public void setRawPath(String str) {
        this.pathParts = toPathParts(str, this.verbatim);
    }

    public void appendRawPath(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        List<String> pathParts = toPathParts(str, this.verbatim);
        List<String> list = this.pathParts;
        if (list == null || list.isEmpty()) {
            this.pathParts = pathParts;
            return;
        }
        int size = this.pathParts.size();
        int i = size - 1;
        this.pathParts.set(i, this.pathParts.get(i) + pathParts.get(0));
        this.pathParts.addAll(pathParts.subList(1, pathParts.size()));
    }

    public static List<String> toPathParts(String str) {
        return toPathParts(str, false);
    }

    public static List<String> toPathParts(String str, boolean z) {
        String substring;
        if (str == null || str.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z2 = true;
        while (z2) {
            int indexOf = str.indexOf(47, i);
            boolean z3 = indexOf != -1;
            if (z3) {
                substring = str.substring(i, indexOf);
            } else {
                substring = str.substring(i);
            }
            if (!z) {
                substring = CharEscapers.decodeUriPath(substring);
            }
            arrayList.add(substring);
            i = indexOf + 1;
            z2 = z3;
        }
        return arrayList;
    }

    private void appendRawPathFromParts(StringBuilder sb) {
        int size = this.pathParts.size();
        for (int i = 0; i < size; i++) {
            String str = this.pathParts.get(i);
            if (i != 0) {
                sb.append('/');
            }
            if (str.length() != 0) {
                if (!this.verbatim) {
                    str = CharEscapers.escapeUriPath(str);
                }
                sb.append(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addQueryParams(Set<Map.Entry<String, Object>> set, StringBuilder sb, boolean z) {
        String escapeUriQuery;
        boolean z2 = true;
        for (Map.Entry<String, Object> entry : set) {
            Object value = entry.getValue();
            if (value != null) {
                if (z) {
                    escapeUriQuery = entry.getKey();
                } else {
                    escapeUriQuery = CharEscapers.escapeUriQuery(entry.getKey());
                }
                if (value instanceof Collection) {
                    Iterator it = ((Collection) value).iterator();
                    while (it.hasNext()) {
                        z2 = appendParam(z2, sb, escapeUriQuery, it.next(), z);
                    }
                } else {
                    z2 = appendParam(z2, sb, escapeUriQuery, value, z);
                }
            }
        }
    }

    private static boolean appendParam(boolean z, StringBuilder sb, String str, Object obj, boolean z2) {
        if (z) {
            z = false;
            sb.append('?');
        } else {
            sb.append(Typography.amp);
        }
        sb.append(str);
        String obj2 = z2 ? obj.toString() : CharEscapers.escapeUriQuery(obj.toString());
        if (obj2.length() != 0) {
            sb.append('=');
            sb.append(obj2);
        }
        return z;
    }

    private static URI toURI(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static URL parseURL(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
