package io.grpc.netty.shaded.io.netty.handler.codec.http;

import com.google.api.client.http.HttpMethods;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.internal.MathUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpMethod implements Comparable<HttpMethod> {
    private final AsciiString name;
    public static final HttpMethod OPTIONS = new HttpMethod("OPTIONS");
    public static final HttpMethod GET = new HttpMethod("GET");
    public static final HttpMethod HEAD = new HttpMethod("HEAD");
    public static final HttpMethod POST = new HttpMethod("POST");
    public static final HttpMethod PUT = new HttpMethod("PUT");
    public static final HttpMethod PATCH = new HttpMethod("PATCH");
    public static final HttpMethod DELETE = new HttpMethod("DELETE");
    public static final HttpMethod TRACE = new HttpMethod("TRACE");
    public static final HttpMethod CONNECT = new HttpMethod(HttpMethods.CONNECT);
    private static final EnumNameMap<HttpMethod> methodMap = new EnumNameMap<>(new EnumNameMap.Node(OPTIONS.toString(), OPTIONS), new EnumNameMap.Node(GET.toString(), GET), new EnumNameMap.Node(HEAD.toString(), HEAD), new EnumNameMap.Node(POST.toString(), POST), new EnumNameMap.Node(PUT.toString(), PUT), new EnumNameMap.Node(PATCH.toString(), PATCH), new EnumNameMap.Node(DELETE.toString(), DELETE), new EnumNameMap.Node(TRACE.toString(), TRACE), new EnumNameMap.Node(CONNECT.toString(), CONNECT));

    public static HttpMethod valueOf(String str) {
        HttpMethod httpMethod = methodMap.get(str);
        return httpMethod != null ? httpMethod : new HttpMethod(str);
    }

    public HttpMethod(String str) {
        String trim = ((String) ObjectUtil.checkNotNull(str, "name")).trim();
        if (trim.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }
        for (int i = 0; i < trim.length(); i++) {
            char charAt = trim.charAt(i);
            if (Character.isISOControl(charAt) || Character.isWhitespace(charAt)) {
                throw new IllegalArgumentException("invalid character in name");
            }
        }
        this.name = AsciiString.cached(trim);
    }

    public String name() {
        return this.name.toString();
    }

    public AsciiString asciiName() {
        return this.name;
    }

    public int hashCode() {
        return name().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HttpMethod) {
            return name().equals(((HttpMethod) obj).name());
        }
        return false;
    }

    public String toString() {
        return this.name.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpMethod httpMethod) {
        if (httpMethod == this) {
            return 0;
        }
        return name().compareTo(httpMethod.name());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class EnumNameMap<T> {
        private final Node<T>[] values;
        private final int valuesMask;

        EnumNameMap(Node<T>... nodeArr) {
            this.values = new Node[MathUtil.findNextPositivePowerOfTwo(nodeArr.length)];
            this.valuesMask = this.values.length - 1;
            for (Node<T> node : nodeArr) {
                int hashCode = hashCode(node.key) & this.valuesMask;
                Node<T>[] nodeArr2 = this.values;
                if (nodeArr2[hashCode] != null) {
                    throw new IllegalArgumentException("index " + hashCode + " collision between values: [" + this.values[hashCode].key + ", " + node.key + ']');
                }
                nodeArr2[hashCode] = node;
            }
        }

        T get(String str) {
            Node<T> node = this.values[hashCode(str) & this.valuesMask];
            if (node == null || !node.key.equals(str)) {
                return null;
            }
            return node.value;
        }

        private static int hashCode(String str) {
            return str.hashCode() >>> 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Node<T> {
            final String key;
            final T value;

            Node(String str, T t) {
                this.key = str;
                this.value = t;
            }
        }
    }
}
