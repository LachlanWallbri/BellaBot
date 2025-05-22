package com.google.api.gax.rpc.internal;

import com.google.auth.Credentials;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class QuotaProjectIdHidingCredentials extends Credentials {
    private static final String QUOTA_PROJECT_ID_HEADER_KEY = "x-goog-user-project";
    private final Credentials wrappedCredentials;

    public QuotaProjectIdHidingCredentials(Credentials credentials) {
        this.wrappedCredentials = credentials;
    }

    @Override // com.google.auth.Credentials
    public String getAuthenticationType() {
        return this.wrappedCredentials.getAuthenticationType();
    }

    @Override // com.google.auth.Credentials
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (Map.Entry<String, List<String>> entry : this.wrappedCredentials.getRequestMetadata(uri).entrySet()) {
            if (!entry.getKey().equals(QUOTA_PROJECT_ID_HEADER_KEY)) {
                builder.put(entry);
            }
        }
        return builder.build();
    }

    @Override // com.google.auth.Credentials
    public Map<String, List<String>> getRequestMetadata() throws IOException {
        return getRequestMetadata(null);
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadata() {
        return this.wrappedCredentials.hasRequestMetadata();
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadataOnly() {
        return this.wrappedCredentials.hasRequestMetadataOnly();
    }

    @Override // com.google.auth.Credentials
    public void refresh() throws IOException {
        this.wrappedCredentials.refresh();
    }
}
