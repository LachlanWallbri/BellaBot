package io.minio.credentials;

import java.security.ProviderException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* loaded from: classes7.dex */
public class ChainedProvider implements Provider {
    private Credentials credentials;
    private Provider currentProvider;
    private final List<Provider> providers;

    public ChainedProvider(@Nonnull Provider... providerArr) {
        this.providers = Arrays.asList(providerArr);
    }

    @Override // io.minio.credentials.Provider
    public synchronized Credentials fetch() {
        if (this.credentials != null && !this.credentials.isExpired()) {
            return this.credentials;
        }
        if (this.currentProvider != null) {
            try {
                this.credentials = this.currentProvider.fetch();
                return this.credentials;
            } catch (ProviderException unused) {
            }
        }
        Iterator<Provider> it = this.providers.iterator();
        while (it.hasNext()) {
            Provider next = it.next();
            try {
                this.credentials = next.fetch();
                this.currentProvider = next;
                return this.credentials;
            } catch (ProviderException unused2) {
            }
        }
        throw new ProviderException("All providers fail to fetch credentials");
    }
}
