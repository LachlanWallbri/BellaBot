package com.google.auth.oauth2;

import com.google.auth.oauth2.JwtClaims;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_JwtClaims extends JwtClaims {
    private static final long serialVersionUID = 4974444151019426702L;
    private final Map<String, String> additionalClaims;
    private final String audience;
    private final String issuer;
    private final String subject;

    private AutoValue_JwtClaims(@Nullable String str, @Nullable String str2, @Nullable String str3, Map<String, String> map) {
        this.audience = str;
        this.issuer = str2;
        this.subject = str3;
        this.additionalClaims = map;
    }

    @Override // com.google.auth.oauth2.JwtClaims
    @Nullable
    String getAudience() {
        return this.audience;
    }

    @Override // com.google.auth.oauth2.JwtClaims
    @Nullable
    String getIssuer() {
        return this.issuer;
    }

    @Override // com.google.auth.oauth2.JwtClaims
    @Nullable
    String getSubject() {
        return this.subject;
    }

    @Override // com.google.auth.oauth2.JwtClaims
    Map<String, String> getAdditionalClaims() {
        return this.additionalClaims;
    }

    public String toString() {
        return "JwtClaims{audience=" + this.audience + ", issuer=" + this.issuer + ", subject=" + this.subject + ", additionalClaims=" + this.additionalClaims + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JwtClaims)) {
            return false;
        }
        JwtClaims jwtClaims = (JwtClaims) obj;
        String str = this.audience;
        if (str != null ? str.equals(jwtClaims.getAudience()) : jwtClaims.getAudience() == null) {
            String str2 = this.issuer;
            if (str2 != null ? str2.equals(jwtClaims.getIssuer()) : jwtClaims.getIssuer() == null) {
                String str3 = this.subject;
                if (str3 != null ? str3.equals(jwtClaims.getSubject()) : jwtClaims.getSubject() == null) {
                    if (this.additionalClaims.equals(jwtClaims.getAdditionalClaims())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.audience;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.issuer;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.subject;
        return ((hashCode2 ^ (str3 != null ? str3.hashCode() : 0)) * 1000003) ^ this.additionalClaims.hashCode();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static final class Builder extends JwtClaims.Builder {
        private Map<String, String> additionalClaims;
        private String audience;
        private String issuer;
        private String subject;

        @Override // com.google.auth.oauth2.JwtClaims.Builder
        public JwtClaims.Builder setAudience(String str) {
            this.audience = str;
            return this;
        }

        @Override // com.google.auth.oauth2.JwtClaims.Builder
        public JwtClaims.Builder setIssuer(String str) {
            this.issuer = str;
            return this;
        }

        @Override // com.google.auth.oauth2.JwtClaims.Builder
        public JwtClaims.Builder setSubject(String str) {
            this.subject = str;
            return this;
        }

        @Override // com.google.auth.oauth2.JwtClaims.Builder
        public JwtClaims.Builder setAdditionalClaims(Map<String, String> map) {
            if (map == null) {
                throw new NullPointerException("Null additionalClaims");
            }
            this.additionalClaims = map;
            return this;
        }

        @Override // com.google.auth.oauth2.JwtClaims.Builder
        public JwtClaims build() {
            String str = "";
            if (this.additionalClaims == null) {
                str = " additionalClaims";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_JwtClaims(this.audience, this.issuer, this.subject, this.additionalClaims);
        }
    }
}
