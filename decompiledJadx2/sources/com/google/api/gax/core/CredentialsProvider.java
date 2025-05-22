package com.google.api.gax.core;

import com.google.auth.Credentials;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface CredentialsProvider {
    Credentials getCredentials() throws IOException;
}
