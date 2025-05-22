package com.google.api.gax.core;

import com.google.auth.Credentials;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_FixedCredentialsProvider extends FixedCredentialsProvider {
    private final Credentials credentials;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_FixedCredentialsProvider(@Nullable Credentials credentials) {
        this.credentials = credentials;
    }

    @Override // com.google.api.gax.core.FixedCredentialsProvider, com.google.api.gax.core.CredentialsProvider
    @Nullable
    public Credentials getCredentials() {
        return this.credentials;
    }

    public String toString() {
        return "FixedCredentialsProvider{credentials=" + this.credentials + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FixedCredentialsProvider)) {
            return false;
        }
        Credentials credentials = this.credentials;
        Credentials credentials2 = ((FixedCredentialsProvider) obj).getCredentials();
        return credentials == null ? credentials2 == null : credentials.equals(credentials2);
    }

    public int hashCode() {
        Credentials credentials = this.credentials;
        return (credentials == null ? 0 : credentials.hashCode()) ^ 1000003;
    }
}
