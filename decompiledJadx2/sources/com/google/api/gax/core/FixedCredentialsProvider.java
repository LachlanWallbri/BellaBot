package com.google.api.gax.core;

import com.google.auth.Credentials;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class FixedCredentialsProvider implements CredentialsProvider {
    @Override // com.google.api.gax.core.CredentialsProvider
    @Nullable
    public abstract Credentials getCredentials();

    public static FixedCredentialsProvider create(Credentials credentials) {
        return new AutoValue_FixedCredentialsProvider(credentials);
    }
}
