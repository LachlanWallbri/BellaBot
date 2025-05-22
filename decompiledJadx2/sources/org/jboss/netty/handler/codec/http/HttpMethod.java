package org.jboss.netty.handler.codec.http;

import com.google.api.client.http.HttpMethods;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class HttpMethod implements Comparable<HttpMethod> {
    private final String name;
    public static final HttpMethod OPTIONS = new HttpMethod("OPTIONS");
    public static final HttpMethod GET = new HttpMethod("GET");
    public static final HttpMethod HEAD = new HttpMethod("HEAD");
    public static final HttpMethod POST = new HttpMethod("POST");
    public static final HttpMethod PUT = new HttpMethod("PUT");
    public static final HttpMethod PATCH = new HttpMethod("PATCH");
    public static final HttpMethod DELETE = new HttpMethod("DELETE");
    public static final HttpMethod TRACE = new HttpMethod("TRACE");
    public static final HttpMethod CONNECT = new HttpMethod(HttpMethods.CONNECT);
    private static final Map<String, HttpMethod> methodMap = new HashMap();

    static {
        methodMap.put(OPTIONS.toString(), OPTIONS);
        methodMap.put(GET.toString(), GET);
        methodMap.put(HEAD.toString(), HEAD);
        methodMap.put(POST.toString(), POST);
        methodMap.put(PUT.toString(), PUT);
        methodMap.put(PATCH.toString(), PATCH);
        methodMap.put(DELETE.toString(), DELETE);
        methodMap.put(TRACE.toString(), TRACE);
        methodMap.put(CONNECT.toString(), CONNECT);
    }

    public static HttpMethod valueOf(String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        String upperCase = str.trim().toUpperCase();
        if (upperCase.length() == 0) {
            throw new IllegalArgumentException("empty name");
        }
        HttpMethod httpMethod = methodMap.get(upperCase);
        return httpMethod != null ? httpMethod : new HttpMethod(upperCase);
    }

    public HttpMethod(String str) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        String upperCase = str.trim().toUpperCase();
        if (upperCase.length() == 0) {
            throw new IllegalArgumentException("empty name");
        }
        for (int i = 0; i < upperCase.length(); i++) {
            if (Character.isISOControl(upperCase.charAt(i)) || Character.isWhitespace(upperCase.charAt(i))) {
                throw new IllegalArgumentException("invalid character in name");
            }
        }
        this.name = upperCase;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof HttpMethod) {
            return getName().equals(((HttpMethod) obj).getName());
        }
        return false;
    }

    public String toString() {
        return getName();
    }

    @Override // java.lang.Comparable
    public int compareTo(HttpMethod httpMethod) {
        return getName().compareTo(httpMethod.getName());
    }
}
