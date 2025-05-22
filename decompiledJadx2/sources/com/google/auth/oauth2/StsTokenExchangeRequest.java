package com.google.auth.oauth2;

import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class StsTokenExchangeRequest {
    private static final String GRANT_TYPE = "urn:ietf:params:oauth:grant-type:token-exchange";

    @Nullable
    private final ActingParty actingParty;

    @Nullable
    private final String audience;

    @Nullable
    private final String requestedTokenType;

    @Nullable
    private final String resource;

    @Nullable
    private final List<String> scopes;
    private final String subjectToken;
    private final String subjectTokenType;

    public String getGrantType() {
        return GRANT_TYPE;
    }

    private StsTokenExchangeRequest(String str, String str2, @Nullable ActingParty actingParty, @Nullable List<String> list, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        this.subjectToken = (String) Preconditions.checkNotNull(str);
        this.subjectTokenType = (String) Preconditions.checkNotNull(str2);
        this.actingParty = actingParty;
        this.scopes = list;
        this.resource = str3;
        this.audience = str4;
        this.requestedTokenType = str5;
    }

    public static Builder newBuilder(String str, String str2) {
        return new Builder(str, str2);
    }

    public String getSubjectToken() {
        return this.subjectToken;
    }

    public String getSubjectTokenType() {
        return this.subjectTokenType;
    }

    @Nullable
    public String getResource() {
        return this.resource;
    }

    @Nullable
    public String getAudience() {
        return this.audience;
    }

    @Nullable
    public String getRequestedTokenType() {
        return this.requestedTokenType;
    }

    @Nullable
    public List<String> getScopes() {
        return this.scopes;
    }

    @Nullable
    public ActingParty getActingParty() {
        return this.actingParty;
    }

    public boolean hasResource() {
        String str = this.resource;
        return (str == null || str.isEmpty()) ? false : true;
    }

    public boolean hasAudience() {
        String str = this.audience;
        return (str == null || str.isEmpty()) ? false : true;
    }

    public boolean hasRequestedTokenType() {
        String str = this.requestedTokenType;
        return (str == null || str.isEmpty()) ? false : true;
    }

    public boolean hasScopes() {
        List<String> list = this.scopes;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public boolean hasActingParty() {
        return this.actingParty != null;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {

        @Nullable
        private ActingParty actingParty;

        @Nullable
        private String audience;

        @Nullable
        private String requestedTokenType;

        @Nullable
        private String resource;

        @Nullable
        private List<String> scopes;
        private final String subjectToken;
        private final String subjectTokenType;

        private Builder(String str, String str2) {
            this.subjectToken = str;
            this.subjectTokenType = str2;
        }

        public Builder setResource(String str) {
            this.resource = str;
            return this;
        }

        public Builder setAudience(String str) {
            this.audience = str;
            return this;
        }

        public Builder setRequestTokenType(String str) {
            this.requestedTokenType = str;
            return this;
        }

        public Builder setScopes(List<String> list) {
            this.scopes = list;
            return this;
        }

        public Builder setActingParty(ActingParty actingParty) {
            this.actingParty = actingParty;
            return this;
        }

        public StsTokenExchangeRequest build() {
            return new StsTokenExchangeRequest(this.subjectToken, this.subjectTokenType, this.actingParty, this.scopes, this.resource, this.audience, this.requestedTokenType);
        }
    }
}
