package org.apache.http;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface HttpRequestFactory {
    HttpRequest newHttpRequest(String str, String str2) throws MethodNotSupportedException;

    HttpRequest newHttpRequest(RequestLine requestLine) throws MethodNotSupportedException;
}
