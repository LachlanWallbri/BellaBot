package io.minio.credentials;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes7.dex */
public class StaticProvider implements Provider {
    private final Credentials credentials;

    public StaticProvider(@Nonnull String str, @Nonnull String str2, @Nullable String str3) {
        this.credentials = new Credentials(str, str2, str3, null);
    }

    @Override // io.minio.credentials.Provider
    public Credentials fetch() {
        return this.credentials;
    }
}
