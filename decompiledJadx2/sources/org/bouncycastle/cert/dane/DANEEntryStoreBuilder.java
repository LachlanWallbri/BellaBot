package org.bouncycastle.cert.dane;

/* loaded from: classes9.dex */
public class DANEEntryStoreBuilder {
    private final DANEEntryFetcherFactory daneEntryFetcher;

    public DANEEntryStoreBuilder(DANEEntryFetcherFactory dANEEntryFetcherFactory) {
        this.daneEntryFetcher = dANEEntryFetcherFactory;
    }

    public DANEEntryStore build(String str) throws DANEException {
        return new DANEEntryStore(this.daneEntryFetcher.build(str).getEntries());
    }
}
