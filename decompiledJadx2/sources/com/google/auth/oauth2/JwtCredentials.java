package com.google.auth.oauth2;

import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Clock;
import com.google.auth.Credentials;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class JwtCredentials extends Credentials implements JwtProvider {
    private static final long CLOCK_SKEW = TimeUnit.MINUTES.toSeconds(5);
    private static final String JWT_ACCESS_PREFIX = "Bearer ";
    private static final String JWT_INCOMPLETE_ERROR_MESSAGE = "JWT claims must contain audience, issuer, and subject.";
    transient Clock clock;
    private transient Long expiryInSeconds;
    private transient String jwt;
    private final JwtClaims jwtClaims;
    private final Long lifeSpanSeconds;
    private final Object lock;
    private final PrivateKey privateKey;
    private final String privateKeyId;

    @Override // com.google.auth.Credentials
    public String getAuthenticationType() {
        return "JWT";
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadata() {
        return true;
    }

    @Override // com.google.auth.Credentials
    public boolean hasRequestMetadataOnly() {
        return true;
    }

    private JwtCredentials(Builder builder) {
        this.lock = new byte[0];
        this.privateKey = (PrivateKey) Preconditions.checkNotNull(builder.getPrivateKey());
        this.privateKeyId = builder.getPrivateKeyId();
        this.jwtClaims = (JwtClaims) Preconditions.checkNotNull(builder.getJwtClaims());
        Preconditions.checkState(this.jwtClaims.isComplete(), JWT_INCOMPLETE_ERROR_MESSAGE);
        this.lifeSpanSeconds = (Long) Preconditions.checkNotNull(builder.getLifeSpanSeconds());
        this.clock = (Clock) Preconditions.checkNotNull(builder.getClock());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.google.auth.Credentials
    public void refresh() throws IOException {
        JsonWebSignature.Header header = new JsonWebSignature.Header();
        header.setAlgorithm("RS256");
        header.setType("JWT");
        header.setKeyId(this.privateKeyId);
        JsonWebToken.Payload payload = new JsonWebToken.Payload();
        payload.setAudience(this.jwtClaims.getAudience());
        payload.setIssuer(this.jwtClaims.getIssuer());
        payload.setSubject(this.jwtClaims.getSubject());
        long currentTimeMillis = this.clock.currentTimeMillis() / 1000;
        payload.setIssuedAtTimeSeconds(Long.valueOf(currentTimeMillis));
        payload.setExpirationTimeSeconds(Long.valueOf(currentTimeMillis + this.lifeSpanSeconds.longValue()));
        payload.putAll(this.jwtClaims.getAdditionalClaims());
        synchronized (this.lock) {
            this.expiryInSeconds = payload.getExpirationTimeSeconds();
            try {
                this.jwt = JsonWebSignature.signUsingRsaSha256(this.privateKey, OAuth2Utils.JSON_FACTORY, header, payload);
            } catch (GeneralSecurityException e) {
                throw new IOException("Error signing service account JWT access header with private key.", e);
            }
        }
    }

    private boolean shouldRefresh() {
        return this.expiryInSeconds == null || getClock().currentTimeMillis() / 1000 > this.expiryInSeconds.longValue() - CLOCK_SKEW;
    }

    @Override // com.google.auth.oauth2.JwtProvider
    public JwtCredentials jwtWithClaims(JwtClaims jwtClaims) {
        return newBuilder().setPrivateKey(this.privateKey).setPrivateKeyId(this.privateKeyId).setJwtClaims(this.jwtClaims.merge(jwtClaims)).build();
    }

    @Override // com.google.auth.Credentials
    public Map<String, List<String>> getRequestMetadata(URI uri) throws IOException {
        Map<String, List<String>> singletonMap;
        synchronized (this.lock) {
            if (shouldRefresh()) {
                refresh();
            }
            singletonMap = Collections.singletonMap("Authorization", Collections.singletonList(JWT_ACCESS_PREFIX + this.jwt));
        }
        return singletonMap;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JwtCredentials)) {
            return false;
        }
        JwtCredentials jwtCredentials = (JwtCredentials) obj;
        return Objects.equals(this.privateKey, jwtCredentials.privateKey) && Objects.equals(this.privateKeyId, jwtCredentials.privateKeyId) && Objects.equals(this.jwtClaims, jwtCredentials.jwtClaims) && Objects.equals(this.lifeSpanSeconds, jwtCredentials.lifeSpanSeconds);
    }

    public int hashCode() {
        return Objects.hash(this.privateKey, this.privateKeyId, this.jwtClaims, this.lifeSpanSeconds);
    }

    Clock getClock() {
        if (this.clock == null) {
            this.clock = Clock.SYSTEM;
        }
        return this.clock;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private JwtClaims jwtClaims;
        private PrivateKey privateKey;
        private String privateKeyId;
        private Clock clock = Clock.SYSTEM;
        private Long lifeSpanSeconds = Long.valueOf(TimeUnit.HOURS.toSeconds(1));

        protected Builder() {
        }

        public Builder setPrivateKey(PrivateKey privateKey) {
            this.privateKey = (PrivateKey) Preconditions.checkNotNull(privateKey);
            return this;
        }

        public PrivateKey getPrivateKey() {
            return this.privateKey;
        }

        public Builder setPrivateKeyId(String str) {
            this.privateKeyId = str;
            return this;
        }

        public String getPrivateKeyId() {
            return this.privateKeyId;
        }

        public Builder setJwtClaims(JwtClaims jwtClaims) {
            this.jwtClaims = (JwtClaims) Preconditions.checkNotNull(jwtClaims);
            return this;
        }

        public JwtClaims getJwtClaims() {
            return this.jwtClaims;
        }

        public Builder setLifeSpanSeconds(Long l) {
            this.lifeSpanSeconds = (Long) Preconditions.checkNotNull(l);
            return this;
        }

        public Long getLifeSpanSeconds() {
            return this.lifeSpanSeconds;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder setClock(Clock clock) {
            this.clock = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        Clock getClock() {
            return this.clock;
        }

        public JwtCredentials build() {
            return new JwtCredentials(this);
        }
    }
}
