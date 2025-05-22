package com.amazonaws.mobile.client;

import com.amazonaws.internal.keyvaluestore.AWSKeyValueStore;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
final class AWSMobileClientStore implements KeyValueStore {
    private final AWSKeyValueStore mAWSKeyValueStore;
    private final ReadWriteLock mReadWriteLock = new ReentrantReadWriteLock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AWSMobileClientStore(AWSMobileClient aWSMobileClient) {
        this.mAWSKeyValueStore = new AWSKeyValueStore(aWSMobileClient.mContext, "com.amazonaws.mobile.client", aWSMobileClient.mIsPersistenceEnabled);
    }

    @Override // com.amazonaws.mobile.client.KeyValueStore
    public Map<String, String> get(String... strArr) {
        try {
            this.mReadWriteLock.readLock().lock();
            HashMap hashMap = new HashMap();
            for (String str : strArr) {
                hashMap.put(str, this.mAWSKeyValueStore.get(str));
            }
            return hashMap;
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.amazonaws.mobile.client.KeyValueStore
    public String get(String str) {
        try {
            this.mReadWriteLock.readLock().lock();
            return this.mAWSKeyValueStore.get(str);
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.amazonaws.mobile.client.KeyValueStore
    public void set(Map<String, String> map) {
        try {
            this.mReadWriteLock.writeLock().lock();
            for (String str : map.keySet()) {
                this.mAWSKeyValueStore.put(str, map.get(str));
            }
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.amazonaws.mobile.client.KeyValueStore
    public void set(String str, String str2) {
        try {
            this.mReadWriteLock.writeLock().lock();
            this.mAWSKeyValueStore.put(str, str2);
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.amazonaws.mobile.client.KeyValueStore
    public void clear() {
        this.mAWSKeyValueStore.clear();
    }
}
