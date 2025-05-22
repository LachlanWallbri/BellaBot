package com.google.auth.oauth2;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class AwsRequestSignature {
    private String authorizationHeader;
    private AwsSecurityCredentials awsSecurityCredentials;
    private Map<String, String> canonicalHeaders;
    private String credentialScope;
    private String date;
    private String httpMethod;
    private String region;
    private String signature;
    private String url;

    private AwsRequestSignature(AwsSecurityCredentials awsSecurityCredentials, Map<String, String> map, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.awsSecurityCredentials = awsSecurityCredentials;
        this.canonicalHeaders = map;
        this.signature = str;
        this.credentialScope = str2;
        this.url = str3;
        this.httpMethod = str4;
        this.date = str5;
        this.region = str6;
        this.authorizationHeader = str7;
    }

    String getSignature() {
        return this.signature;
    }

    String getCredentialScope() {
        return this.credentialScope;
    }

    AwsSecurityCredentials getSecurityCredentials() {
        return this.awsSecurityCredentials;
    }

    String getUrl() {
        return this.url;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getHttpMethod() {
        return this.httpMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, String> getCanonicalHeaders() {
        return new HashMap(this.canonicalHeaders);
    }

    String getDate() {
        return this.date;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getRegion() {
        return this.region;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAuthorizationHeader() {
        return this.authorizationHeader;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class Builder {
        private String authorizationHeader;
        private AwsSecurityCredentials awsSecurityCredentials;
        private Map<String, String> canonicalHeaders;
        private String credentialScope;
        private String date;
        private String httpMethod;
        private String region;
        private String signature;
        private String url;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setSignature(String str) {
            this.signature = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setCredentialScope(String str) {
            this.credentialScope = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setSecurityCredentials(AwsSecurityCredentials awsSecurityCredentials) {
            this.awsSecurityCredentials = awsSecurityCredentials;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setHttpMethod(String str) {
            this.httpMethod = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setCanonicalHeaders(Map<String, String> map) {
            this.canonicalHeaders = new HashMap(map);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setDate(String str) {
            this.date = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setRegion(String str) {
            this.region = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setAuthorizationHeader(String str) {
            this.authorizationHeader = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public AwsRequestSignature build() {
            return new AwsRequestSignature(this.awsSecurityCredentials, this.canonicalHeaders, this.signature, this.credentialScope, this.url, this.httpMethod, this.date, this.region, this.authorizationHeader);
        }
    }
}
