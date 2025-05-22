package com.google.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Key;
import com.google.auth.http.HttpTransportFactory;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class TokenVerifier {
    private static final String FEDERATED_SIGNON_CERT_URL = "https://www.googleapis.com/oauth2/v3/certs";
    private static final String IAP_CERT_URL = "https://www.gstatic.com/iap/verify/public_key-jwk";
    private static final Set<String> SUPPORTED_ALGORITHMS = ImmutableSet.m678of("RS256", "ES256");
    private final String audience;
    private final String certificatesLocation;
    private final Clock clock;
    private final String issuer;
    private final PublicKey publicKey;
    private final LoadingCache<String, Map<String, PublicKey>> publicKeyCache;

    private TokenVerifier(Builder builder) {
        this.audience = builder.audience;
        this.certificatesLocation = builder.certificatesLocation;
        this.issuer = builder.issuer;
        this.publicKey = builder.publicKey;
        this.clock = builder.clock;
        this.publicKeyCache = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.HOURS).build(new PublicKeyLoader(builder.httpTransportFactory));
    }

    public static Builder newBuilder() {
        return new Builder().setClock(Clock.SYSTEM).setHttpTransportFactory(OAuth2Utils.HTTP_TRANSPORT_FACTORY);
    }

    public JsonWebSignature verify(String str) throws VerificationException {
        try {
            JsonWebSignature parse = JsonWebSignature.parse(OAuth2Utils.JSON_FACTORY, str);
            String str2 = this.audience;
            if (str2 != null && !str2.equals(parse.getPayload().getAudience())) {
                throw new VerificationException("Expected audience does not match");
            }
            String str3 = this.issuer;
            if (str3 != null && !str3.equals(parse.getPayload().getIssuer())) {
                throw new VerificationException("Expected issuer does not match");
            }
            Long expirationTimeSeconds = parse.getPayload().getExpirationTimeSeconds();
            if (expirationTimeSeconds != null && expirationTimeSeconds.longValue() <= this.clock.currentTimeMillis() / 1000) {
                throw new VerificationException("Token is expired");
            }
            if (!SUPPORTED_ALGORITHMS.contains(parse.getHeader().getAlgorithm())) {
                throw new VerificationException("Unexpected signing algorithm: expected either RS256 or ES256");
            }
            PublicKey publicKey = this.publicKey;
            if (publicKey == null) {
                try {
                    publicKey = this.publicKeyCache.get(getCertificateLocation(parse)).get(parse.getHeader().getKeyId());
                } catch (UncheckedExecutionException | ExecutionException e) {
                    throw new VerificationException("Error fetching PublicKey from certificate location", e);
                }
            }
            if (publicKey == null) {
                throw new VerificationException("Could not find PublicKey for provided keyId: " + parse.getHeader().getKeyId());
            }
            try {
                if (parse.verifySignature(publicKey)) {
                    return parse;
                }
                throw new VerificationException("Invalid signature");
            } catch (GeneralSecurityException e2) {
                throw new VerificationException("Error validating token", e2);
            }
        } catch (IOException e3) {
            throw new VerificationException("Error parsing JsonWebSignature token", e3);
        }
    }

    private String getCertificateLocation(JsonWebSignature jsonWebSignature) throws VerificationException {
        String str = this.certificatesLocation;
        if (str != null) {
            return str;
        }
        String algorithm = jsonWebSignature.getHeader().getAlgorithm();
        char c = 65535;
        int hashCode = algorithm.hashCode();
        if (hashCode != 66245349) {
            if (hashCode == 78251122 && algorithm.equals("RS256")) {
                c = 0;
            }
        } else if (algorithm.equals("ES256")) {
            c = 1;
        }
        if (c == 0) {
            return FEDERATED_SIGNON_CERT_URL;
        }
        if (c == 1) {
            return IAP_CERT_URL;
        }
        throw new VerificationException("Unknown algorithm");
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private String audience;
        private String certificatesLocation;
        private Clock clock;
        private HttpTransportFactory httpTransportFactory;
        private String issuer;
        private PublicKey publicKey;

        public Builder setAudience(String str) {
            this.audience = str;
            return this;
        }

        public Builder setCertificatesLocation(String str) {
            this.certificatesLocation = str;
            return this;
        }

        public Builder setIssuer(String str) {
            this.issuer = str;
            return this;
        }

        public Builder setPublicKey(PublicKey publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder setHttpTransportFactory(HttpTransportFactory httpTransportFactory) {
            this.httpTransportFactory = httpTransportFactory;
            return this;
        }

        public TokenVerifier build() {
            return new TokenVerifier(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class PublicKeyLoader extends CacheLoader<String, Map<String, PublicKey>> {
        private final HttpTransportFactory httpTransportFactory;

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public static class JsonWebKey {

            @Key
            public String alg;

            @Key
            public String crv;

            /* renamed from: e */
            @Key
            public String f1346e;

            @Key
            public String kid;

            @Key
            public String kty;

            /* renamed from: n */
            @Key
            public String f1347n;

            @Key
            public String use;

            /* renamed from: x */
            @Key
            public String f1348x;

            /* renamed from: y */
            @Key
            public String f1349y;
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes2.dex */
        public static class JsonWebKeySet extends GenericJson {

            @Key
            public List<JsonWebKey> keys;
        }

        PublicKeyLoader(HttpTransportFactory httpTransportFactory) {
            this.httpTransportFactory = httpTransportFactory;
        }

        @Override // com.google.common.cache.CacheLoader
        public Map<String, PublicKey> load(String str) throws Exception {
            try {
                JsonWebKeySet jsonWebKeySet = (JsonWebKeySet) this.httpTransportFactory.create().createRequestFactory().buildGetRequest(new GenericUrl(str)).setParser(OAuth2Utils.JSON_FACTORY.createJsonObjectParser()).execute().parseAs(JsonWebKeySet.class);
                ImmutableMap.Builder builder = new ImmutableMap.Builder();
                if (jsonWebKeySet.keys == null) {
                    for (String str2 : jsonWebKeySet.keySet()) {
                        builder.put(str2, buildPublicKey((String) jsonWebKeySet.get(str2)));
                    }
                } else {
                    for (JsonWebKey jsonWebKey : jsonWebKeySet.keys) {
                        try {
                            builder.put(jsonWebKey.kid, buildPublicKey(jsonWebKey));
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidParameterSpecException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return builder.build();
            } catch (IOException unused) {
                return ImmutableMap.m653of();
            }
        }

        private PublicKey buildPublicKey(JsonWebKey jsonWebKey) throws NoSuchAlgorithmException, InvalidParameterSpecException, InvalidKeySpecException {
            if ("ES256".equals(jsonWebKey.alg)) {
                return buildEs256PublicKey(jsonWebKey);
            }
            if ("RS256".equals(jsonWebKey.alg)) {
                return buildRs256PublicKey(jsonWebKey);
            }
            return null;
        }

        private PublicKey buildPublicKey(String str) throws CertificateException, UnsupportedEncodingException {
            return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(str.getBytes("UTF-8"))).getPublicKey();
        }

        private PublicKey buildRs256PublicKey(JsonWebKey jsonWebKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
            Preconditions.checkArgument("RSA".equals(jsonWebKey.kty));
            Preconditions.checkNotNull(jsonWebKey.f1346e);
            Preconditions.checkNotNull(jsonWebKey.f1347n);
            return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, Base64.decodeBase64(jsonWebKey.f1347n)), new BigInteger(1, Base64.decodeBase64(jsonWebKey.f1346e))));
        }

        private PublicKey buildEs256PublicKey(JsonWebKey jsonWebKey) throws NoSuchAlgorithmException, InvalidParameterSpecException, InvalidKeySpecException {
            Preconditions.checkArgument("EC".equals(jsonWebKey.kty));
            Preconditions.checkArgument("P-256".equals(jsonWebKey.crv));
            ECPoint eCPoint = new ECPoint(new BigInteger(1, Base64.decodeBase64(jsonWebKey.f1348x)), new BigInteger(1, Base64.decodeBase64(jsonWebKey.f1349y)));
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("EC");
            algorithmParameters.init(new ECGenParameterSpec("secp256r1"));
            return KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, (ECParameterSpec) algorithmParameters.getParameterSpec(ECParameterSpec.class)));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class VerificationException extends Exception {
        public VerificationException(String str) {
            super(str);
        }

        public VerificationException(String str, Throwable th) {
            super(str, th);
        }
    }
}
