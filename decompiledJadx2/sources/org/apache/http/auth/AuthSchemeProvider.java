package org.apache.http.auth;

import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface AuthSchemeProvider {
    AuthScheme create(HttpContext httpContext);
}
