package org.jboss.netty.handler.codec.spdy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
public class SpdyHeaders {
    private static final int BUCKET_SIZE = 17;
    private final Entry[] entries = new Entry[17];
    private final Entry head = new Entry(-1, null, null);

    /* loaded from: classes7.dex */
    public static final class HttpNames {
        public static final String HOST = ":host";
        public static final String METHOD = ":method";
        public static final String PATH = ":path";
        public static final String SCHEME = ":scheme";
        public static final String STATUS = ":status";
        public static final String VERSION = ":version";

        private HttpNames() {
        }
    }

    /* loaded from: classes7.dex */
    public static final class Spdy2HttpNames {
        public static final String METHOD = "method";
        public static final String SCHEME = "scheme";
        public static final String STATUS = "status";
        public static final String URL = "url";
        public static final String VERSION = "version";

        private Spdy2HttpNames() {
        }
    }

    public static String getHeader(SpdyHeaderBlock spdyHeaderBlock, String str) {
        return spdyHeaderBlock.getHeader(str);
    }

    public static String getHeader(SpdyHeaderBlock spdyHeaderBlock, String str, String str2) {
        String header = spdyHeaderBlock.getHeader(str);
        return header == null ? str2 : header;
    }

    public static void setHeader(SpdyHeaderBlock spdyHeaderBlock, String str, Object obj) {
        spdyHeaderBlock.setHeader(str, obj);
    }

    public static void setHeader(SpdyHeaderBlock spdyHeaderBlock, String str, Iterable<?> iterable) {
        spdyHeaderBlock.setHeader(str, iterable);
    }

    public static void addHeader(SpdyHeaderBlock spdyHeaderBlock, String str, Object obj) {
        spdyHeaderBlock.addHeader(str, obj);
    }

    public static void removeHost(SpdyHeaderBlock spdyHeaderBlock) {
        spdyHeaderBlock.removeHeader(HttpNames.HOST);
    }

    public static String getHost(SpdyHeaderBlock spdyHeaderBlock) {
        return spdyHeaderBlock.getHeader(HttpNames.HOST);
    }

    public static void setHost(SpdyHeaderBlock spdyHeaderBlock, String str) {
        spdyHeaderBlock.setHeader(HttpNames.HOST, str);
    }

    @Deprecated
    public static void removeMethod(SpdyHeaderBlock spdyHeaderBlock) {
        removeMethod(2, spdyHeaderBlock);
    }

    public static void removeMethod(int i, SpdyHeaderBlock spdyHeaderBlock) {
        if (i < 3) {
            spdyHeaderBlock.removeHeader("method");
        } else {
            spdyHeaderBlock.removeHeader(":method");
        }
    }

    @Deprecated
    public static HttpMethod getMethod(SpdyHeaderBlock spdyHeaderBlock) {
        return getMethod(2, spdyHeaderBlock);
    }

    public static HttpMethod getMethod(int i, SpdyHeaderBlock spdyHeaderBlock) {
        try {
            if (i < 3) {
                return HttpMethod.valueOf(spdyHeaderBlock.getHeader("method"));
            }
            return HttpMethod.valueOf(spdyHeaderBlock.getHeader(":method"));
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static void setMethod(SpdyHeaderBlock spdyHeaderBlock, HttpMethod httpMethod) {
        setMethod(2, spdyHeaderBlock, httpMethod);
    }

    public static void setMethod(int i, SpdyHeaderBlock spdyHeaderBlock, HttpMethod httpMethod) {
        if (i < 3) {
            spdyHeaderBlock.setHeader("method", httpMethod.getName());
        } else {
            spdyHeaderBlock.setHeader(":method", httpMethod.getName());
        }
    }

    @Deprecated
    public static void removeScheme(SpdyHeaderBlock spdyHeaderBlock) {
        removeMethod(2, spdyHeaderBlock);
    }

    public static void removeScheme(int i, SpdyHeaderBlock spdyHeaderBlock) {
        if (i < 2) {
            spdyHeaderBlock.removeHeader(Spdy2HttpNames.SCHEME);
        } else {
            spdyHeaderBlock.removeHeader(":scheme");
        }
    }

    @Deprecated
    public static String getScheme(SpdyHeaderBlock spdyHeaderBlock) {
        return getScheme(2, spdyHeaderBlock);
    }

    public static String getScheme(int i, SpdyHeaderBlock spdyHeaderBlock) {
        if (i < 3) {
            return spdyHeaderBlock.getHeader(Spdy2HttpNames.SCHEME);
        }
        return spdyHeaderBlock.getHeader(":scheme");
    }

    @Deprecated
    public static void setScheme(SpdyHeaderBlock spdyHeaderBlock, String str) {
        setScheme(2, spdyHeaderBlock, str);
    }

    public static void setScheme(int i, SpdyHeaderBlock spdyHeaderBlock, String str) {
        if (i < 3) {
            spdyHeaderBlock.setHeader(Spdy2HttpNames.SCHEME, str);
        } else {
            spdyHeaderBlock.setHeader(":scheme", str);
        }
    }

    @Deprecated
    public static void removeStatus(SpdyHeaderBlock spdyHeaderBlock) {
        removeMethod(2, spdyHeaderBlock);
    }

    public static void removeStatus(int i, SpdyHeaderBlock spdyHeaderBlock) {
        if (i < 3) {
            spdyHeaderBlock.removeHeader("status");
        } else {
            spdyHeaderBlock.removeHeader(":status");
        }
    }

    @Deprecated
    public static HttpResponseStatus getStatus(SpdyHeaderBlock spdyHeaderBlock) {
        return getStatus(2, spdyHeaderBlock);
    }

    public static HttpResponseStatus getStatus(int i, SpdyHeaderBlock spdyHeaderBlock) {
        String header;
        try {
            if (i < 3) {
                header = spdyHeaderBlock.getHeader("status");
            } else {
                header = spdyHeaderBlock.getHeader(":status");
            }
            int indexOf = header.indexOf(32);
            if (indexOf == -1) {
                return HttpResponseStatus.valueOf(Integer.parseInt(header));
            }
            int parseInt = Integer.parseInt(header.substring(0, indexOf));
            String substring = header.substring(indexOf + 1);
            HttpResponseStatus valueOf = HttpResponseStatus.valueOf(parseInt);
            return valueOf.getReasonPhrase().equals(substring) ? valueOf : new HttpResponseStatus(parseInt, substring);
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static void setStatus(SpdyHeaderBlock spdyHeaderBlock, HttpResponseStatus httpResponseStatus) {
        setStatus(2, spdyHeaderBlock, httpResponseStatus);
    }

    public static void setStatus(int i, SpdyHeaderBlock spdyHeaderBlock, HttpResponseStatus httpResponseStatus) {
        if (i < 3) {
            spdyHeaderBlock.setHeader("status", httpResponseStatus.toString());
        } else {
            spdyHeaderBlock.setHeader(":status", httpResponseStatus.toString());
        }
    }

    @Deprecated
    public static void removeUrl(SpdyHeaderBlock spdyHeaderBlock) {
        removeUrl(2, spdyHeaderBlock);
    }

    public static void removeUrl(int i, SpdyHeaderBlock spdyHeaderBlock) {
        if (i < 3) {
            spdyHeaderBlock.removeHeader("url");
        } else {
            spdyHeaderBlock.removeHeader(":path");
        }
    }

    @Deprecated
    public static String getUrl(SpdyHeaderBlock spdyHeaderBlock) {
        return getUrl(2, spdyHeaderBlock);
    }

    public static String getUrl(int i, SpdyHeaderBlock spdyHeaderBlock) {
        if (i < 3) {
            return spdyHeaderBlock.getHeader("url");
        }
        return spdyHeaderBlock.getHeader(":path");
    }

    @Deprecated
    public static void setUrl(SpdyHeaderBlock spdyHeaderBlock, String str) {
        setUrl(2, spdyHeaderBlock, str);
    }

    public static void setUrl(int i, SpdyHeaderBlock spdyHeaderBlock, String str) {
        if (i < 3) {
            spdyHeaderBlock.setHeader("url", str);
        } else {
            spdyHeaderBlock.setHeader(":path", str);
        }
    }

    @Deprecated
    public static void removeVersion(SpdyHeaderBlock spdyHeaderBlock) {
        removeVersion(2, spdyHeaderBlock);
    }

    public static void removeVersion(int i, SpdyHeaderBlock spdyHeaderBlock) {
        if (i < 3) {
            spdyHeaderBlock.removeHeader("version");
        } else {
            spdyHeaderBlock.removeHeader(HttpNames.VERSION);
        }
    }

    @Deprecated
    public static HttpVersion getVersion(SpdyHeaderBlock spdyHeaderBlock) {
        return getVersion(2, spdyHeaderBlock);
    }

    public static HttpVersion getVersion(int i, SpdyHeaderBlock spdyHeaderBlock) {
        try {
            if (i < 3) {
                return HttpVersion.valueOf(spdyHeaderBlock.getHeader("version"));
            }
            return HttpVersion.valueOf(spdyHeaderBlock.getHeader(HttpNames.VERSION));
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static void setVersion(SpdyHeaderBlock spdyHeaderBlock, HttpVersion httpVersion) {
        setVersion(2, spdyHeaderBlock, httpVersion);
    }

    public static void setVersion(int i, SpdyHeaderBlock spdyHeaderBlock, HttpVersion httpVersion) {
        if (i < 3) {
            spdyHeaderBlock.setHeader("version", httpVersion.getText());
        } else {
            spdyHeaderBlock.setHeader(HttpNames.VERSION, httpVersion.getText());
        }
    }

    private static int hash(String str) {
        int i = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if (charAt >= 'A' && charAt <= 'Z') {
                charAt = (char) (charAt + ' ');
            }
            i = (i * 31) + charAt;
        }
        if (i > 0) {
            return i;
        }
        if (i == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return -i;
    }

    /* renamed from: eq */
    private static boolean m4159eq(String str, String str2) {
        int length = str.length();
        if (length != str2.length()) {
            return false;
        }
        for (int i = length - 1; i >= 0; i--) {
            char charAt = str.charAt(i);
            char charAt2 = str2.charAt(i);
            if (charAt != charAt2) {
                if (charAt >= 'A' && charAt <= 'Z') {
                    charAt = (char) (charAt + ' ');
                }
                if (charAt2 >= 'A' && charAt2 <= 'Z') {
                    charAt2 = (char) (charAt2 + ' ');
                }
                if (charAt != charAt2) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int index(int i) {
        return i % 17;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpdyHeaders() {
        Entry entry = this.head;
        entry.after = entry;
        entry.before = entry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addHeader(String str, Object obj) {
        String lowerCase = str.toLowerCase();
        SpdyCodecUtil.validateHeaderName(lowerCase);
        String spdyHeaders = toString(obj);
        SpdyCodecUtil.validateHeaderValue(spdyHeaders);
        int hash = hash(lowerCase);
        addHeader0(hash, index(hash), lowerCase, spdyHeaders);
    }

    private void addHeader0(int i, int i2, String str, String str2) {
        Entry[] entryArr = this.entries;
        Entry entry = entryArr[i2];
        Entry entry2 = new Entry(i, str, str2);
        entryArr[i2] = entry2;
        entry2.next = entry;
        entry2.addBefore(this.head);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeHeader(String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        String lowerCase = str.toLowerCase();
        int hash = hash(lowerCase);
        removeHeader0(hash, index(hash), lowerCase);
    }

    private void removeHeader0(int i, int i2, String str) {
        Entry entry = this.entries[i2];
        if (entry == null) {
            return;
        }
        while (entry.hash == i && m4159eq(str, entry.key)) {
            entry.remove();
            entry = entry.next;
            if (entry != null) {
                this.entries[i2] = entry;
            } else {
                this.entries[i2] = null;
                return;
            }
        }
        while (true) {
            Entry entry2 = entry.next;
            if (entry2 == null) {
                return;
            }
            if (entry2.hash == i && m4159eq(str, entry2.key)) {
                entry.next = entry2.next;
                entry2.remove();
            } else {
                entry = entry2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHeader(String str, Object obj) {
        String lowerCase = str.toLowerCase();
        SpdyCodecUtil.validateHeaderName(lowerCase);
        String spdyHeaders = toString(obj);
        SpdyCodecUtil.validateHeaderValue(spdyHeaders);
        int hash = hash(lowerCase);
        int index = index(hash);
        removeHeader0(hash, index, lowerCase);
        addHeader0(hash, index, lowerCase, spdyHeaders);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHeader(String str, Iterable<?> iterable) {
        Object next;
        if (iterable == null) {
            throw new NullPointerException("values");
        }
        String lowerCase = str.toLowerCase();
        SpdyCodecUtil.validateHeaderName(lowerCase);
        int hash = hash(lowerCase);
        int index = index(hash);
        removeHeader0(hash, index, lowerCase);
        Iterator<?> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            String spdyHeaders = toString(next);
            SpdyCodecUtil.validateHeaderValue(spdyHeaders);
            addHeader0(hash, index, lowerCase, spdyHeaders);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearHeaders() {
        int i = 0;
        while (true) {
            Entry[] entryArr = this.entries;
            if (i < entryArr.length) {
                entryArr[i] = null;
                i++;
            } else {
                Entry entry = this.head;
                entry.after = entry;
                entry.before = entry;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getHeader(String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        int hash = hash(str);
        for (Entry entry = this.entries[index(hash)]; entry != null; entry = entry.next) {
            if (entry.hash == hash && m4159eq(str, entry.key)) {
                return entry.value;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getHeaders(String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        LinkedList linkedList = new LinkedList();
        int hash = hash(str);
        for (Entry entry = this.entries[index(hash)]; entry != null; entry = entry.next) {
            if (entry.hash == hash && m4159eq(str, entry.key)) {
                linkedList.addFirst(entry.value);
            }
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Map.Entry<String, String>> getHeaders() {
        LinkedList linkedList = new LinkedList();
        for (Entry entry = this.head.after; entry != this.head; entry = entry.after) {
            linkedList.add(entry);
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean containsHeader(String str) {
        return getHeader(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> getHeaderNames() {
        TreeSet treeSet = new TreeSet();
        for (Entry entry = this.head.after; entry != this.head; entry = entry.after) {
            treeSet.add(entry.key);
        }
        return treeSet;
    }

    private static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class Entry implements Map.Entry<String, String> {
        Entry after;
        Entry before;
        final int hash;
        final String key;
        Entry next;
        String value;

        Entry(int i, String str, String str2) {
            this.hash = i;
            this.key = str;
            this.value = str2;
        }

        void remove() {
            Entry entry = this.before;
            entry.after = this.after;
            this.after.before = entry;
        }

        void addBefore(Entry entry) {
            this.after = entry;
            this.before = entry.before;
            this.before.after = this;
            this.after.before = this;
        }

        @Override // java.util.Map.Entry
        public String getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public String getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public String setValue(String str) {
            if (str == null) {
                throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
            }
            SpdyCodecUtil.validateHeaderValue(str);
            String str2 = this.value;
            this.value = str;
            return str2;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
