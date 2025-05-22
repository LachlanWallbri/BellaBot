package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.GaxProperties;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class ApiClientHeaderProvider implements HeaderProvider, Serializable {
    static final String QUOTA_PROJECT_ID_HEADER_KEY = "x-goog-user-project";
    private static final long serialVersionUID = -8876627296793342119L;
    private final Map<String, String> headers;

    public static String getDefaultApiClientHeaderKey() {
        return "x-goog-api-client";
    }

    public static String getDefaultResourceHeaderKey() {
        return "google-cloud-resource-prefix";
    }

    protected ApiClientHeaderProvider(Builder builder) {
        ImmutableMap.Builder builder2 = ImmutableMap.builder();
        if (builder.getApiClientHeaderKey() != null) {
            StringBuilder sb = new StringBuilder();
            appendToken(sb, builder.getJvmToken());
            appendToken(sb, builder.getClientLibToken());
            appendToken(sb, builder.getGeneratedLibToken());
            appendToken(sb, builder.getGeneratedRuntimeToken());
            appendToken(sb, builder.getTransportToken());
            if (sb.length() > 0) {
                builder2.put(builder.getApiClientHeaderKey(), sb.toString());
            }
        }
        if (builder.getResourceHeaderKey() != null && builder.getResourceToken() != null) {
            builder2.put(builder.getResourceHeaderKey(), builder.getResourceToken());
        }
        if (builder.getQuotaProjectIdToken() != null) {
            builder2.put(QUOTA_PROJECT_ID_HEADER_KEY, builder.getQuotaProjectIdToken());
        }
        this.headers = builder2.build();
    }

    @Override // com.google.api.gax.rpc.HeaderProvider
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    protected static void appendToken(StringBuilder sb, String str) {
        if (str != null) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(str);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private String apiClientHeaderKey = ApiClientHeaderProvider.getDefaultApiClientHeaderKey();
        private String clientLibToken;
        private String generatedLibToken;
        private String generatedRuntimeToken;
        private String jvmToken;
        private String quotaProjectIdToken;
        private String resourceHeaderKey;
        private String resourceToken;
        private String transportToken;

        protected Builder() {
            setJvmToken(GaxProperties.getJavaVersion());
            this.clientLibToken = null;
            this.generatedLibToken = null;
            setClientRuntimeToken(GaxProperties.getGaxVersion());
            this.transportToken = null;
            this.quotaProjectIdToken = null;
            this.resourceHeaderKey = ApiClientHeaderProvider.getDefaultResourceHeaderKey();
            this.resourceToken = null;
        }

        public String getApiClientHeaderKey() {
            return this.apiClientHeaderKey;
        }

        public Builder setApiClientHeaderKey(String str) {
            this.apiClientHeaderKey = str;
            return this;
        }

        public String getJvmToken() {
            return this.jvmToken;
        }

        public Builder setJvmToken(String str) {
            this.jvmToken = constructToken("gl-java", str);
            return this;
        }

        public String getClientLibToken() {
            return this.clientLibToken;
        }

        public Builder setClientLibToken(String str, String str2) {
            this.clientLibToken = constructToken(str, str2);
            return this;
        }

        public String getGeneratedLibToken() {
            return this.generatedLibToken;
        }

        public Builder setGeneratedLibToken(String str, String str2) {
            this.generatedLibToken = constructToken(str, str2);
            return this;
        }

        public String getGeneratedRuntimeToken() {
            return this.generatedRuntimeToken;
        }

        public Builder setClientRuntimeToken(String str) {
            this.generatedRuntimeToken = constructToken("gax", str);
            return this;
        }

        public String getTransportToken() {
            return this.transportToken;
        }

        public Builder setTransportToken(String str, String str2) {
            this.transportToken = constructToken(str, str2);
            return this;
        }

        public String getQuotaProjectIdToken() {
            return this.quotaProjectIdToken;
        }

        public Builder setQuotaProjectIdToken(String str) {
            this.quotaProjectIdToken = str;
            return this;
        }

        public String getResourceHeaderKey() {
            return this.resourceHeaderKey;
        }

        public Builder setResourceHeaderKey(String str) {
            this.resourceHeaderKey = str;
            return this;
        }

        public String getResourceToken() {
            return this.resourceToken;
        }

        public Builder setResourceToken(String str) {
            this.resourceToken = str;
            return this;
        }

        private String constructToken(String str, String str2) {
            if (str2 == null) {
                return null;
            }
            if (str == null) {
                throw new IllegalArgumentException("Token name cannot be null");
            }
            return str + '/' + str2;
        }

        public ApiClientHeaderProvider build() {
            return new ApiClientHeaderProvider(this);
        }
    }
}
