package com.google.api.client.util.store;

import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class AbstractDataStore<V extends Serializable> implements DataStore<V> {
    private final DataStoreFactory dataStoreFactory;

    /* renamed from: id */
    private final String f1341id;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDataStore(DataStoreFactory dataStoreFactory, String str) {
        this.dataStoreFactory = (DataStoreFactory) Preconditions.checkNotNull(dataStoreFactory);
        this.f1341id = (String) Preconditions.checkNotNull(str);
    }

    @Override // com.google.api.client.util.store.DataStore
    public DataStoreFactory getDataStoreFactory() {
        return this.dataStoreFactory;
    }

    @Override // com.google.api.client.util.store.DataStore
    public final String getId() {
        return this.f1341id;
    }

    @Override // com.google.api.client.util.store.DataStore
    public boolean containsKey(String str) throws IOException {
        return get(str) != null;
    }

    @Override // com.google.api.client.util.store.DataStore
    public boolean containsValue(V v) throws IOException {
        return values().contains(v);
    }

    @Override // com.google.api.client.util.store.DataStore
    public boolean isEmpty() throws IOException {
        return size() == 0;
    }

    @Override // com.google.api.client.util.store.DataStore
    public int size() throws IOException {
        return keySet().size();
    }
}
