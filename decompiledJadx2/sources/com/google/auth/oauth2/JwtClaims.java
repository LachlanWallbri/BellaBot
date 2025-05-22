package com.google.auth.oauth2;

import com.google.auth.oauth2.AutoValue_JwtClaims;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class JwtClaims implements Serializable {
    private static final long serialVersionUID = 4974444151019426702L;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract JwtClaims build();

        public abstract Builder setAdditionalClaims(Map<String, String> map);

        public abstract Builder setAudience(String str);

        public abstract Builder setIssuer(String str);

        public abstract Builder setSubject(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Map<String, String> getAdditionalClaims();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getAudience();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getIssuer();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getSubject();

    public static Builder newBuilder() {
        return new AutoValue_JwtClaims.Builder().setAdditionalClaims(ImmutableMap.m653of());
    }

    public JwtClaims merge(JwtClaims jwtClaims) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.putAll(getAdditionalClaims());
        builder.putAll(jwtClaims.getAdditionalClaims());
        return newBuilder().setAudience(jwtClaims.getAudience() == null ? getAudience() : jwtClaims.getAudience()).setIssuer(jwtClaims.getIssuer() == null ? getIssuer() : jwtClaims.getIssuer()).setSubject(jwtClaims.getSubject() == null ? getSubject() : jwtClaims.getSubject()).setAdditionalClaims(builder.build()).build();
    }

    public boolean isComplete() {
        return (getAudience() == null || getIssuer() == null || getSubject() == null) ? false : true;
    }
}
