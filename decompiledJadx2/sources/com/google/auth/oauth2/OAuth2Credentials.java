package com.google.auth.oauth2;

import com.google.api.client.util.Clock;
import com.google.auth.Credentials;
import com.google.auth.RequestMetadataCallback;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class OAuth2Credentials extends Credentials {
    private static final long serialVersionUID = 4556936364828217687L;
    private transient List<CredentialsChangedListener> changeListeners;
    transient Clock clock;
    final Object lock;
    transient ListenableFutureTask<OAuthValue> refreshTask;
    private volatile OAuthValue value;
    static final long MINIMUM_TOKEN_MILLISECONDS = TimeUnit.MINUTES.toMillis(5);
    static final long REFRESH_MARGIN_MILLISECONDS = MINIMUM_TOKEN_MILLISECONDS + TimeUnit.MINUTES.toMillis(1);
    private static final ImmutableMap<String, List<String>> EMPTY_EXTRA_HEADERS = ImmutableMap.m653of();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum CacheState {
        FRESH,
        STALE,
        EXPIRED
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface CredentialsChangedListener {
        void onChanged(OAuth2Credentials oAuth2Credentials) throws IOException;
    }

    @Override // com.google.auth.Credentials
    public String getAuthenticationType() {
        return "OAuth2";
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadata() {
        return true;
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadataOnly() {
        return true;
    }

    public static OAuth2Credentials create(AccessToken accessToken) {
        return newBuilder().setAccessToken(accessToken).build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OAuth2Credentials() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OAuth2Credentials(AccessToken accessToken) {
        this.lock = new byte[0];
        this.value = null;
        this.clock = Clock.SYSTEM;
        if (accessToken != null) {
            this.value = OAuthValue.create(accessToken, EMPTY_EXTRA_HEADERS);
        }
    }

    public final AccessToken getAccessToken() {
        OAuthValue oAuthValue = this.value;
        if (oAuthValue != null) {
            return oAuthValue.temporaryAccess;
        }
        return null;
    }

    @Override // com.google.auth.Credentials
    public void getRequestMetadata(URI uri, Executor executor, RequestMetadataCallback requestMetadataCallback) {
        Futures.addCallback(asyncFetch(executor), new FutureCallbackToMetadataCallbackAdapter(requestMetadataCallback), MoreExecutors.directExecutor());
    }

    @Override // com.google.auth.Credentials
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        return ((OAuthValue) unwrapDirectFuture(asyncFetch(MoreExecutors.directExecutor()))).requestMetadata;
    }

    @Override // com.google.auth.Credentials
    public void refresh() throws IOException {
        AsyncRefreshResult orCreateRefreshTask = getOrCreateRefreshTask();
        orCreateRefreshTask.executeIfNew(MoreExecutors.directExecutor());
        unwrapDirectFuture(orCreateRefreshTask.task);
    }

    public void refreshIfExpired() throws IOException {
        unwrapDirectFuture(asyncFetch(MoreExecutors.directExecutor()));
    }

    private ListenableFuture<OAuthValue> asyncFetch(Executor executor) {
        AsyncRefreshResult orCreateRefreshTask;
        if (getState() == CacheState.FRESH) {
            return Futures.immediateFuture(this.value);
        }
        synchronized (this.lock) {
            orCreateRefreshTask = getState() != CacheState.FRESH ? getOrCreateRefreshTask() : null;
        }
        if (orCreateRefreshTask != null) {
            orCreateRefreshTask.executeIfNew(executor);
        }
        synchronized (this.lock) {
            if (getState() != CacheState.EXPIRED) {
                return Futures.immediateFuture(this.value);
            }
            if (orCreateRefreshTask == null) {
                return Futures.immediateFailedFuture(new IllegalStateException("Credentials expired, but there is no task to refresh"));
            }
            return orCreateRefreshTask.task;
        }
    }

    private AsyncRefreshResult getOrCreateRefreshTask() {
        synchronized (this.lock) {
            if (this.refreshTask != null) {
                return new AsyncRefreshResult(this.refreshTask, false);
            }
            final ListenableFutureTask<OAuthValue> create = ListenableFutureTask.create(new Callable<OAuthValue>() { // from class: com.google.auth.oauth2.OAuth2Credentials.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public OAuthValue call() throws Exception {
                    return OAuthValue.create(OAuth2Credentials.this.refreshAccessToken(), OAuth2Credentials.this.getAdditionalHeaders());
                }
            });
            create.addListener(new Runnable() { // from class: com.google.auth.oauth2.OAuth2Credentials.2
                @Override // java.lang.Runnable
                public void run() {
                    OAuth2Credentials.this.finishRefreshAsync(create);
                }
            }, MoreExecutors.directExecutor());
            this.refreshTask = create;
            return new AsyncRefreshResult(this.refreshTask, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
    
        if (r4.refreshTask == r5) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x002d, code lost:
    
        if (r4.refreshTask != r5) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void finishRefreshAsync(ListenableFuture<OAuthValue> listenableFuture) {
        synchronized (this.lock) {
            try {
                try {
                    try {
                        this.value = listenableFuture.get();
                        Iterator<CredentialsChangedListener> it = this.changeListeners.iterator();
                        while (it.hasNext()) {
                            it.next().onChanged(this);
                        }
                    } catch (Throwable th) {
                        if (this.refreshTask == listenableFuture) {
                            this.refreshTask = null;
                        }
                        throw th;
                    }
                } catch (Exception unused) {
                }
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
                if (this.refreshTask == listenableFuture) {
                    this.refreshTask = null;
                }
            }
        }
    }

    private static <T> T unwrapDirectFuture(Future<T> future) throws IOException {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Interrupted while asynchronously refreshing the access token", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new IOException("Unexpected error refreshing access token", cause);
        }
    }

    private CacheState getState() {
        OAuthValue oAuthValue = this.value;
        if (oAuthValue == null) {
            return CacheState.EXPIRED;
        }
        Date expirationTime = oAuthValue.temporaryAccess.getExpirationTime();
        if (expirationTime == null) {
            return CacheState.FRESH;
        }
        long time = expirationTime.getTime() - this.clock.currentTimeMillis();
        if (time <= MINIMUM_TOKEN_MILLISECONDS) {
            return CacheState.EXPIRED;
        }
        if (time <= REFRESH_MARGIN_MILLISECONDS) {
            return CacheState.STALE;
        }
        return CacheState.FRESH;
    }

    public AccessToken refreshAccessToken() throws IOException {
        throw new IllegalStateException("OAuth2Credentials instance does not support refreshing the access token. An instance with a new access token should be used, or a derived type that supports refreshing.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<String, List<String>> getAdditionalHeaders() {
        return EMPTY_EXTRA_HEADERS;
    }

    public final void addChangeListener(CredentialsChangedListener credentialsChangedListener) {
        synchronized (this.lock) {
            if (this.changeListeners == null) {
                this.changeListeners = new ArrayList();
            }
            this.changeListeners.add(credentialsChangedListener);
        }
    }

    public final void removeChangeListener(CredentialsChangedListener credentialsChangedListener) {
        synchronized (this.lock) {
            if (this.changeListeners != null) {
                this.changeListeners.remove(credentialsChangedListener);
            }
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public Map<String, List<String>> getRequestMetadataInternal() {
        OAuthValue oAuthValue = this.value;
        if (oAuthValue != null) {
            return oAuthValue.requestMetadata;
        }
        return null;
    }

    public String toString() {
        AccessToken accessToken;
        OAuthValue oAuthValue = this.value;
        Map map = null;
        if (oAuthValue != null) {
            map = oAuthValue.requestMetadata;
            accessToken = oAuthValue.temporaryAccess;
        } else {
            accessToken = null;
        }
        return MoreObjects.toStringHelper(this).add("requestMetadata", map).add("temporaryAccess", accessToken).toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof OAuth2Credentials) {
            return Objects.equals(this.value, ((OAuth2Credentials) obj).value);
        }
        return false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.clock = Clock.SYSTEM;
        this.refreshTask = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T> T newInstance(String str) throws IOException, ClassNotFoundException {
        try {
            return (T) Class.forName(str).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IOException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T> T getFromServiceLoader(Class<? extends T> cls, T t) {
        return (T) Iterables.getFirst(ServiceLoader.load(cls), t);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class OAuthValue implements Serializable {
        private final Map<String, List<String>> requestMetadata;
        private final AccessToken temporaryAccess;

        static OAuthValue create(AccessToken accessToken, Map<String, List<String>> map) {
            return new OAuthValue(accessToken, ImmutableMap.builder().put("Authorization", ImmutableList.m635of("Bearer " + accessToken.getTokenValue())).putAll(map).build());
        }

        private OAuthValue(AccessToken accessToken, Map<String, List<String>> map) {
            this.temporaryAccess = accessToken;
            this.requestMetadata = map;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof OAuthValue)) {
                return false;
            }
            OAuthValue oAuthValue = (OAuthValue) obj;
            return Objects.equals(this.requestMetadata, oAuthValue.requestMetadata) && Objects.equals(this.temporaryAccess, oAuthValue.temporaryAccess);
        }

        public int hashCode() {
            return Objects.hash(this.temporaryAccess, this.requestMetadata);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class FutureCallbackToMetadataCallbackAdapter implements FutureCallback<OAuthValue> {
        private final RequestMetadataCallback callback;

        public FutureCallbackToMetadataCallbackAdapter(RequestMetadataCallback requestMetadataCallback) {
            this.callback = requestMetadataCallback;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(@Nullable OAuthValue oAuthValue) {
            this.callback.onSuccess(oAuthValue.requestMetadata);
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            if (th instanceof ExecutionException) {
                th = th.getCause();
            }
            this.callback.onFailure(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class AsyncRefreshResult {
        private final boolean isNew;
        private final ListenableFutureTask<OAuthValue> task;

        AsyncRefreshResult(ListenableFutureTask<OAuthValue> listenableFutureTask, boolean z) {
            this.task = listenableFutureTask;
            this.isNew = z;
        }

        void executeIfNew(Executor executor) {
            if (this.isNew) {
                executor.execute(this.task);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private AccessToken accessToken;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
        }

        protected Builder(OAuth2Credentials oAuth2Credentials) {
            this.accessToken = oAuth2Credentials.getAccessToken();
        }

        public Builder setAccessToken(AccessToken accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public AccessToken getAccessToken() {
            return this.accessToken;
        }

        public OAuth2Credentials build() {
            return new OAuth2Credentials(this.accessToken);
        }
    }
}
