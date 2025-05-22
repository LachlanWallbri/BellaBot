package com.google.auth.oauth2;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class StsTokenExchangeResponse {
    private final AccessToken accessToken;
    private final Long expiresInSeconds;
    private final String issuedTokenType;

    @Nullable
    private final String refreshToken;

    @Nullable
    private final List<String> scopes;
    private final String tokenType;

    private StsTokenExchangeResponse(String str, String str2, String str3, Long l, @Nullable String str4, @Nullable List<String> list) {
        Preconditions.checkNotNull(str);
        this.expiresInSeconds = (Long) Preconditions.checkNotNull(l);
        this.accessToken = new AccessToken(str, new Date(System.currentTimeMillis() + (l.longValue() * 1000)));
        this.issuedTokenType = (String) Preconditions.checkNotNull(str2);
        this.tokenType = (String) Preconditions.checkNotNull(str3);
        this.refreshToken = str4;
        this.scopes = list;
    }

    public static Builder newBuilder(String str, String str2, String str3, Long l) {
        return new Builder(str, str2, str3, l);
    }

    public AccessToken getAccessToken() {
        return this.accessToken;
    }

    public String getIssuedTokenType() {
        return this.issuedTokenType;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public Long getExpiresInSeconds() {
        return this.expiresInSeconds;
    }

    @Nullable
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Nullable
    public List<String> getScopes() {
        List<String> list = this.scopes;
        if (list == null) {
            return null;
        }
        return new ArrayList(list);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private final String accessToken;
        private final Long expiresInSeconds;
        private final String issuedTokenType;

        @Nullable
        private String refreshToken;

        @Nullable
        private List<String> scopes;
        private final String tokenType;

        private Builder(String str, String str2, String str3, Long l) {
            this.accessToken = str;
            this.issuedTokenType = str2;
            this.tokenType = str3;
            this.expiresInSeconds = l;
        }

        public Builder setRefreshToken(String str) {
            this.refreshToken = str;
            return this;
        }

        public Builder setScopes(List<String> list) {
            if (list != null) {
                this.scopes = new ArrayList(list);
            }
            return this;
        }

        public StsTokenExchangeResponse build() {
            return new StsTokenExchangeResponse(this.accessToken, this.issuedTokenType, this.tokenType, this.expiresInSeconds, this.refreshToken, this.scopes);
        }
    }
}
