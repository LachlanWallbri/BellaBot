package com.amazonaws.mobile.client.internal.oauth2;

import com.amazonaws.internal.keyvaluestore.AWSKeyValueStore;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OAuth2Client.java */
/* loaded from: classes.dex */
public class OAuth2ClientStore {
    private final AWSKeyValueStore mKeyValueStore;
    ReadWriteLock mReadWriteLock = new ReentrantReadWriteLock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public OAuth2ClientStore(OAuth2Client oAuth2Client) {
        this.mKeyValueStore = new AWSKeyValueStore(oAuth2Client.mContext, OAuth2Client.SHARED_PREFERENCES_KEY, oAuth2Client.mIsPersistenceEnabled);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(OAuth2Tokens oAuth2Tokens) {
        try {
            this.mReadWriteLock.writeLock().lock();
            this.mKeyValueStore.put(OAuth2Constants.TokenResponseFields.ACCESS_TOKEN.toString(), oAuth2Tokens.getAccessToken());
            this.mKeyValueStore.put(OAuth2Constants.TokenResponseFields.ID_TOKEN.toString(), oAuth2Tokens.getIdToken());
            this.mKeyValueStore.put(OAuth2Constants.TokenResponseFields.REFRESH_TOKEN.toString(), oAuth2Tokens.getRefreshToken());
            this.mKeyValueStore.put(OAuth2Constants.TokenResponseFields.EXPIRES_IN.toString(), oAuth2Tokens.getExpiresIn() == null ? null : oAuth2Tokens.getExpiresIn().toString());
            this.mKeyValueStore.put(OAuth2Client.CREATE_DATE, oAuth2Tokens.getCreateDate().toString());
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OAuth2Tokens getTokens() {
        try {
            this.mReadWriteLock.readLock().lock();
            String str = this.mKeyValueStore.get(OAuth2Constants.TokenResponseFields.EXPIRES_IN.toString());
            return new OAuth2Tokens(this.mKeyValueStore.get(OAuth2Constants.TokenResponseFields.ACCESS_TOKEN.toString()), this.mKeyValueStore.get(OAuth2Constants.TokenResponseFields.ID_TOKEN.toString()), this.mKeyValueStore.get(OAuth2Constants.TokenResponseFields.REFRESH_TOKEN.toString()), this.mKeyValueStore.get(OAuth2Constants.TokenResponseFields.TOKEN_TYPE.toString()), str == null ? null : Long.decode(str), Long.valueOf(this.mKeyValueStore.get(OAuth2Client.CREATE_DATE)), this.mKeyValueStore.get(OAuth2Constants.TokenResponseFields.SCOPES.toString()));
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    Map<String, String> get(String... strArr) {
        try {
            this.mReadWriteLock.readLock().lock();
            HashMap hashMap = new HashMap();
            for (String str : strArr) {
                hashMap.put(str, this.mKeyValueStore.get(str));
            }
            return hashMap;
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String get(String str) {
        try {
            this.mReadWriteLock.readLock().lock();
            return this.mKeyValueStore.get(str);
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    void set(Map<String, String> map) {
        try {
            this.mReadWriteLock.writeLock().lock();
            for (String str : map.keySet()) {
                this.mKeyValueStore.put(str, map.get(str));
            }
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(String str, String str2) {
        try {
            this.mReadWriteLock.writeLock().lock();
            this.mKeyValueStore.put(str, str2);
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.mKeyValueStore.clear();
    }

    public void setPersistenceEnabled(boolean z) {
        this.mKeyValueStore.setPersistenceEnabled(z);
    }
}
