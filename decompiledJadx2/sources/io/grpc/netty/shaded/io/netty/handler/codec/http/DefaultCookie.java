package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.http.cookie.ClientCookie;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class DefaultCookie extends io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.DefaultCookie implements Cookie {
    private String comment;
    private String commentUrl;
    private boolean discard;
    private Set<Integer> ports;
    private Set<Integer> unmodifiablePorts;
    private int version;

    public DefaultCookie(String str, String str2) {
        super(str, str2);
        this.ports = Collections.emptySet();
        this.unmodifiablePorts = this.ports;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String getName() {
        return name();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String getValue() {
        return value();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String getDomain() {
        return domain();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String getPath() {
        return path();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String getComment() {
        return comment();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String comment() {
        return this.comment;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public void setComment(String str) {
        this.comment = validateValue(ClientCookie.COMMENT_ATTR, str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String getCommentUrl() {
        return commentUrl();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public String commentUrl() {
        return this.commentUrl;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public void setCommentUrl(String str) {
        this.commentUrl = validateValue("commentUrl", str);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public boolean isDiscard() {
        return this.discard;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public void setDiscard(boolean z) {
        this.discard = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public Set<Integer> getPorts() {
        return ports();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public Set<Integer> ports() {
        if (this.unmodifiablePorts == null) {
            this.unmodifiablePorts = Collections.unmodifiableSet(this.ports);
        }
        return this.unmodifiablePorts;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public void setPorts(int... iArr) {
        ObjectUtil.checkNotNull(iArr, "ports");
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

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
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

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public long getMaxAge() {
        return maxAge();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public int getVersion() {
        return version();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public int version() {
        return this.version;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.Cookie
    @Deprecated
    public void setVersion(int i) {
        this.version = i;
    }
}
