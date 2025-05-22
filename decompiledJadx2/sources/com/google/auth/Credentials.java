package com.google.auth;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class Credentials implements Serializable {
    private static final long serialVersionUID = 808575179767517313L;

    public abstract String getAuthenticationType();

    public abstract Map<String, List<String>> getRequestMetadata(URI uri) throws IOException;

    public abstract boolean hasRequestMetadata();

    public abstract boolean hasRequestMetadataOnly();

    public abstract void refresh() throws IOException;

    public Map<String, List<String>> getRequestMetadata() throws IOException {
        return getRequestMetadata(null);
    }

    public void getRequestMetadata(final URI uri, Executor executor, final RequestMetadataCallback requestMetadataCallback) {
        executor.execute(new Runnable() { // from class: com.google.auth.Credentials.1
            @Override // java.lang.Runnable
            public void run() {
                Credentials.this.blockingGetToCallback(uri, requestMetadataCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void blockingGetToCallback(URI uri, RequestMetadataCallback requestMetadataCallback) {
        try {
            requestMetadataCallback.onSuccess(getRequestMetadata(uri));
        } catch (Throwable th) {
            requestMetadataCallback.onFailure(th);
        }
    }
}
