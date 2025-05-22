package org.apache.http.auth;

import java.security.Principal;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface Credentials {
    String getPassword();

    Principal getUserPrincipal();
}
