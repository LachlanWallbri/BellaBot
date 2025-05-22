package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class AbstractMemoryDataStore<V extends Serializable> extends AbstractDataStore<V> {
    protected HashMap<String, byte[]> keyValueMap;
    private final Lock lock;

    public void save() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMemoryDataStore(DataStoreFactory dataStoreFactory, String str) {
        super(dataStoreFactory, str);
        this.lock = new ReentrantLock();
        this.keyValueMap = Maps.newHashMap();
    }

    @Override // com.google.api.client.util.store.DataStore
    public final Set<String> keySet() throws IOException {
        this.lock.lock();
        try {
            return Collections.unmodifiableSet(this.keyValueMap.keySet());
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public final Collection<V> values() throws IOException {
        this.lock.lock();
        try {
            ArrayList newArrayList = Lists.newArrayList();
            Iterator<byte[]> it = this.keyValueMap.values().iterator();
            while (it.hasNext()) {
                newArrayList.add(IOUtils.deserialize(it.next()));
            }
            return Collections.unmodifiableList(newArrayList);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public final V get(String str) throws IOException {
        if (str == null) {
            return null;
        }
        this.lock.lock();
        try {
            return (V) IOUtils.deserialize(this.keyValueMap.get(str));
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public final DataStore<V> set(String str, V v) throws IOException {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(v);
        this.lock.lock();
        try {
            this.keyValueMap.put(str, IOUtils.serialize(v));
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public DataStore<V> delete(String str) throws IOException {
        if (str == null) {
            return this;
        }
        this.lock.lock();
        try {
            this.keyValueMap.remove(str);
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.DataStore
    public final DataStore<V> clear() throws IOException {
        this.lock.lock();
        try {
            this.keyValueMap.clear();
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public boolean containsKey(String str) throws IOException {
        if (str == null) {
            return false;
        }
        this.lock.lock();
        try {
            return this.keyValueMap.containsKey(str);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public boolean containsValue(V v) throws IOException {
        if (v == null) {
            return false;
        }
        this.lock.lock();
        try {
            byte[] serialize = IOUtils.serialize(v);
            Iterator<byte[]> it = this.keyValueMap.values().iterator();
            while (it.hasNext()) {
                if (Arrays.equals(serialize, it.next())) {
                    return true;
                }
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public boolean isEmpty() throws IOException {
        this.lock.lock();
        try {
            return this.keyValueMap.isEmpty();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.google.api.client.util.store.AbstractDataStore, com.google.api.client.util.store.DataStore
    public int size() throws IOException {
        this.lock.lock();
        try {
            return this.keyValueMap.size();
        } finally {
            this.lock.unlock();
        }
    }

    public String toString() {
        return DataStoreUtils.toString(this);
    }
}
