package com.pudutech.opengl_draw.namespace;

import androidx.core.util.Preconditions;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class GraphName {
    static final String ANONYMOUS_PREFIX = "anonymous_";
    private static final String ROOT = "/";
    private static final String SEPARATOR = "/";
    private static final Pattern VALID_GRAPH_NAME_PATTERN = Pattern.compile("^([\\~\\/A-Za-z][\\w_\\/]*)?$");
    private static AtomicInteger anonymousCounter = new AtomicInteger();
    private final String name;

    public static GraphName newAnonymous() {
        return new GraphName(ANONYMOUS_PREFIX + anonymousCounter.incrementAndGet());
    }

    public static GraphName root() {
        return new GraphName("/");
    }

    public static GraphName empty() {
        return new GraphName("");
    }

    /* renamed from: of */
    public static GraphName m3302of(String str) {
        return new GraphName(canonicalize(str));
    }

    private GraphName(String str) {
        Preconditions.checkNotNull(str);
        this.name = str;
    }

    private static String canonicalize(String str) {
        if (!VALID_GRAPH_NAME_PATTERN.matcher(str).matches()) {
            throw new RuntimeException("Invalid graph name: " + str);
        }
        while (!str.equals("/") && str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        if (!str.startsWith("~/")) {
            return str;
        }
        return "~" + str.substring(2);
    }

    public boolean isGlobal() {
        return !isEmpty() && this.name.charAt(0) == '/';
    }

    public boolean isRoot() {
        return this.name.equals("/");
    }

    public boolean isEmpty() {
        return this.name.isEmpty();
    }

    public boolean isPrivate() {
        return !isEmpty() && this.name.charAt(0) == '~';
    }

    public boolean isRelative() {
        return (isPrivate() || isGlobal()) ? false : true;
    }

    public GraphName getParent() {
        if (this.name.length() == 0) {
            return empty();
        }
        if (this.name.equals("/")) {
            return root();
        }
        int lastIndexOf = this.name.lastIndexOf(47);
        if (lastIndexOf > 1) {
            return new GraphName(this.name.substring(0, lastIndexOf));
        }
        if (isGlobal()) {
            return root();
        }
        return empty();
    }

    public GraphName getBasename() {
        int lastIndexOf = this.name.lastIndexOf(47);
        if (lastIndexOf <= -1) {
            return this;
        }
        int i = lastIndexOf + 1;
        if (i < this.name.length()) {
            return new GraphName(this.name.substring(i));
        }
        return empty();
    }

    public GraphName toRelative() {
        return (isPrivate() || isGlobal()) ? new GraphName(this.name.substring(1)) : this;
    }

    public GraphName toGlobal() {
        if (isGlobal()) {
            return this;
        }
        if (isPrivate()) {
            return new GraphName("/" + this.name.substring(1));
        }
        return new GraphName("/" + this.name);
    }

    public GraphName join(GraphName graphName) {
        if (graphName.isGlobal() || isEmpty()) {
            return graphName;
        }
        if (isRoot()) {
            return graphName.toGlobal();
        }
        if (graphName.isEmpty()) {
            return this;
        }
        return new GraphName(toString() + "/" + graphName.toString());
    }

    public GraphName join(String str) {
        return join(m3302of(str));
    }

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GraphName graphName = (GraphName) obj;
        String str = this.name;
        if (str == null) {
            if (graphName.name != null) {
                return false;
            }
        } else if (!str.equals(graphName.name)) {
            return false;
        }
        return true;
    }
}
